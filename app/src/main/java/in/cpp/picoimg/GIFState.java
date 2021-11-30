package in.cpp.picoimg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Vector;

class GIFState extends BaseState
{
    // SKIA sometimes fail to decode transparent GIFs, see https://issuetracker.google.com/issues/36983182
    private static boolean BUG62016_WORKAROUND_NEEDED = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) && (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M);

    private static final byte INT_EXTENSION = 0x21;
    private static final byte INT_IMAGE = 0x2C;
    private static final byte INT_END = 0x3B;

    private static final byte EXT_GRAPHIC_CTL = (byte) 0xF9;
    private static final byte EXT_APPLICATION = (byte) 0xFF;
    private static final byte[] EXT_NETSCAPE = {'N', 'E', 'T', 'S', 'C', 'A', 'P', 'E', '2', '.', '0'};

    private static final byte[] GIF_HEADER87 = {'G', 'I', 'F', '8', '7', 'a'};
    private static final byte[] GIF_HEADER89 = {'G', 'I', 'F', '8', '9', 'a'};
    private static final byte[] GIF_FOOTER = {0, INT_END};

    private static class Frame
    {
        int mOffX;
        int mOffY;
        int mWidth;
        int mHeight;
        int mDelay;
        int mTransp;
        int mLctSize;
        int mLctPos;
        int mDispose;
        List<byte[]> mData;
        SoftReference<Bitmap> mBitmap;
    }

    // source data
    private byte[] mCommonHeader;
    private List<Frame> mFrames;
    private int mBackColor = Color.TRANSPARENT;
    private boolean mHasDelays;
    private int mGctSize;
    private int mGctPos;

    // playback
    private Canvas mCanvas;
    private int mPrevDispose;
    private Rect mPrevRect;
    private ByteBuffer mPrevPixels;

    //region LOADER
    private static int read_ubyte(InputStream inp) throws IOException
    {
        int b1 = inp.read();
        if (b1 == -1)
            throw new IOException("Unexpected end of file");
        return b1;
    }

    private static int read_ushort_le(InputStream inp) throws IOException
    {
        int b1 = inp.read();
        int b2 = inp.read();
        if ((b1 == -1) || (b2 == -1))
            throw new IOException("Unexpected end of file");
        return (short) ((b2 << 8) | b1);
    }

    private static void read_bytes(InputStream inp, byte[] buffer, int offset, int length) throws IOException
    {
        while (length > 0)
        {
            int res = inp.read(buffer, offset, length);
            if (res < 1)
                throw new IOException("Unexpected end of file");
            length -= res;
            offset += res;
        }
    }

    private static int write_ushort_le(byte[] buffer, int pos, int data)
    {
        buffer[pos++] = (byte) (data & 0xFF);
        buffer[pos++] = (byte) ((data >> 8) & 0xFF);
        return pos;
    }

    GIFState(InputStream inp, int targetWidth, int targetHeight) throws IOException
    {
        // check header
        for (int i = 0; i < 6; ++i)
        {
            byte b = (byte) read_ubyte(inp);
            if ((GIF_HEADER87[i] != b) && (GIF_HEADER89[i] != b))
                throw new IOException("Invalid GIF header");
        }

        // read logical screen descriptor
        int origWidth = mWidth = read_ushort_le(inp);
        int origHeight = mHeight = read_ushort_le(inp);
        int flags = read_ubyte(inp), bg_idx;
        mGctSize = ((flags & 0x80) != 0) ? (3 * (2 << (flags & 7))) : 0;
        mCommonHeader = new byte[7 + mGctSize];
        write_ushort_le(mCommonHeader, 0, mWidth);
        write_ushort_le(mCommonHeader, 2, mHeight);
        mCommonHeader[4] = (byte) flags;
        mCommonHeader[5] = (byte) (bg_idx = read_ubyte(inp));
        mCommonHeader[6] = (byte) read_ubyte(inp); // pixel aspect ratio
        mGctPos = 7;
        read_bytes(inp, mCommonHeader, mGctPos, mGctSize);
        if ((flags & 0x80) != 0)
        {
            int bg_off = 7 + 3 * bg_idx;
            mBackColor = Color.rgb(((int) mCommonHeader[bg_off]) & 0xFF, (((int) mCommonHeader[bg_off + 1]) & 0xFF) << 8, (((int) mCommonHeader[bg_off + 2]) & 0xFF) << 16);
        }
        if ((targetWidth > 0) || (targetHeight > 0))
            scaleToTarget(targetWidth, targetHeight);

        // read data
        try
        {
            int gce_flags = 0;
            int gce_delay = 0;
            int gce_transp = 0;
            //
            while (true)
            {
                byte introducer = (byte) read_ubyte(inp);
                //
                if (INT_EXTENSION == introducer)
                {
                    byte label = (byte) read_ubyte(inp);
                    int len = read_ubyte(inp);
                    byte[] buf = null;
                    // handle known cases
                    if ((EXT_APPLICATION == label) && (EXT_NETSCAPE.length == len))
                    {
                        boolean match = true;
                        for (byte b: EXT_NETSCAPE)
                            if (b != (byte) read_ubyte(inp))
                                match = false;
                        len = read_ubyte(inp);
                        if (match && (3 == len))
                        {
                            read_ubyte(inp); // sub-block index
                            mNumPlays = read_ushort_le(inp);
                            len = read_ubyte(inp);
                        }
                    }
                    else if ((EXT_GRAPHIC_CTL == label) && (4 == len))
                    {
                        gce_flags = read_ubyte(inp);
                        gce_delay = read_ushort_le(inp);
                        gce_transp = read_ubyte(inp);
                        len = read_ubyte(inp);
                    }
                    // read out the rest
                    while (len > 0)
                    {
                        if (null == buf)
                            buf = new byte[256];
                        read_bytes(inp, buf, 0, len);
                        len = read_ubyte(inp);
                    }
                }
                else if (INT_IMAGE == introducer)
                {
                    Frame f = new Frame();
                    f.mOffX = read_ushort_le(inp);
                    f.mOffY = read_ushort_le(inp);
                    f.mWidth = read_ushort_le(inp);
                    f.mHeight = read_ushort_le(inp);
                    f.mDelay = gce_delay * 10;
                    f.mTransp = ((gce_flags & 1) != 0) ? gce_transp : -1;
                    f.mDispose = (gce_flags >> 2) & 7;
                    flags = read_ubyte(inp);
                    f.mLctSize = ((flags & 0x80) != 0) ? (3 * (2 << (flags & 7))) : 0;
                    // write frame data buffer
                    byte[] buf = new byte[((f.mTransp >= 0) ? 8 : 0) + 10 + f.mLctSize + 1];
                    int pos = 0;
                    if (f.mTransp >= 0)
                    {
                        buf[pos++] = INT_EXTENSION;
                        buf[pos++] = EXT_GRAPHIC_CTL;
                        buf[pos++] = 4; // len
                        buf[pos++] = 1; // has transparency
                        buf[pos++] = 0; // delay_l
                        buf[pos++] = 0; // delay_h
                        buf[pos++] = (byte) f.mTransp;
                        buf[pos++] = 0; // end
                    }
                    buf[pos++] = (byte) INT_IMAGE;
                    pos = write_ushort_le(buf, pos, 0); // x
                    pos = write_ushort_le(buf, pos, 0); // y
                    pos = write_ushort_le(buf, pos, f.mWidth);
                    pos = write_ushort_le(buf, pos, f.mHeight);
                    buf[pos++] = (byte) flags;
                    f.mLctPos = pos;
                    read_bytes(inp, buf, pos, f.mLctSize);
                    pos += f.mLctSize;
                    buf[pos] = (byte) read_ubyte(inp); // LZW min code size
                    f.mData = new Vector<>(10);
                    f.mData.add(buf);
                    // read pixels
                    while (true)
                    {
                        int len = read_ubyte(inp);
                        if (len < 1)
                            break;
                        buf = new byte[1 + len];
                        buf[0] = (byte) len;
                        read_bytes(inp, buf, 1, len);
                        f.mData.add(buf);
                    }
                    // done
                    if (null == mFrames)
                        mFrames = new Vector<>(32);
                    mFrames.add(f);
                    if (f.mDelay != 0)
                        mHasDelays = true;
                }
                else if (INT_END == introducer)
                {
                    break;
                }
                else
                    throw new IOException("Invalid GIF introducer " + introducer);
            }
        }
        catch (Throwable e)
        {
            if ((mFrames != null) && (mFrames.size() > 0))
                e.printStackTrace();
            else
                throw e;
        }

        // load the first bitmap and prepare for animation rendering
        if (null != mFrames)
        {
            Frame first = mFrames.get(0);

            // some androids fail to downsample interlaced gifs
            // detect it here to prevent further chaos
            mOutput = loadFrame(first);
            if ((mScaleShift > 0) && (mOutput.getWidth() == first.mWidth))
            {
                mScaleShift = 0;
                mWidth = origWidth;
                mHeight = origHeight;
            }

            if ((mFrames.size() > 1) || (first.mWidth != origWidth) || (first.mHeight != origHeight))
            {
                first.mBitmap = new SoftReference<>(mOutput);
                mOutput = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mOutput);
                step();
            }

            // don't animate static image
            if (mFrames.size() == 1)
            {
                mNextFrameTime = 0;
                mFrames = null;
            }
        }

        // final check
        if (null == mOutput)
            throw new IOException("Decoder failed unexpectedly");
    }

    @SuppressLint("NewApi")
    private Bitmap loadFrame(Frame f)
    {
        // update common header
        write_ushort_le(mCommonHeader,0, f.mWidth);
        write_ushort_le(mCommonHeader, 2, f.mHeight);

        // create buffer list
        byte[][] bufs = new byte[f.mData.size() + 3][];
        bufs[0] = (f.mTransp >= 0) ? GIF_HEADER89 : GIF_HEADER87;
        bufs[1] = mCommonHeader;
        for (int i = 0; i < f.mData.size(); ++i)
            bufs[2 + i] = f.mData.get(i);
        bufs[bufs.length - 1] = GIF_FOOTER;

        // SKIA bug62016 workaround step 1, change transparent color to unique
        int transp = 0, transpPos = 0, transpOrig = 0;
        byte[] transpTbl = null;
        if (BUG62016_WORKAROUND_NEEDED && (f.mTransp >= 0))
        {
            byte[] lct = f.mData.get(0);
            // find unique color
            transp = (int) (SystemClock.uptimeMillis() & 0xFFFFFF);
            while (true)
            {
                transp = (transp + 1) & 0xFFFFFF;
                boolean found = false;
                for (int i = 0; i < mGctSize; i += 3)
                {
                    if (transp == ((((int)mCommonHeader[mGctPos + i]) << 16) | (((int)mCommonHeader[mGctPos + i + 1]) << 8) | mCommonHeader[mGctPos + i + 2]))
                    {
                        found = true;
                        break;
                    }
                }
                if (found)
                    continue;
                for (int i = 0; i < f.mLctSize; i += 3)
                {
                    if (transp == ((((int)lct[f.mLctPos + i]) << 16) | (((int)lct[f.mLctPos + i + 1]) << 8) | lct[f.mLctPos + i + 2]))
                    {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    break;
            }
            // calculate transparent color position
            int off = f.mTransp * 3;
            if (off < f.mLctSize)
            {
                transpTbl = lct;
                transpPos = f.mLctPos + off;
            }
            else if (off < mGctSize)
            {
                transpTbl = mCommonHeader;
                transpPos = mGctPos + off;
            }
            // update
            if (null != transpTbl)
            {
                transpOrig = (((int)transpTbl[transpPos]) << 16) | (((int)transpTbl[transpPos + 1]) << 8) | transpTbl[transpPos + 2];
                transpTbl[transpPos] = (byte)((transp >> 16) & 0xFF);
                transpTbl[transpPos + 1] = (byte)((transp >> 8) & 0xFF);
                transpTbl[transpPos + 2] = (byte)(transp & 0xFF);
            }
        }

        // decode the resulting data
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1 << mScaleShift;
        Bitmap ret = BitmapFactory.decodeStream(new MultipleArrayInputStream(bufs), null, options);

        // SKIA bug62016 workaround step 2, finalize
        if (BUG62016_WORKAROUND_NEEDED && (f.mTransp >= 0))
        {
            // clean up color switcheroo
            if (null != transpTbl)
            {
                transpTbl[transpPos] = (byte)((transpOrig >> 16) & 0xFF);
                transpTbl[transpPos + 1] = (byte)((transpOrig >> 8) & 0xFF);
                transpTbl[transpPos + 2] = (byte)(transpOrig & 0xFF);
            }
            // check if our unique color is transparent
            if (null != ret)
            {
                int width = ret.getWidth();
                int height = ret.getHeight();
                int[] pixels = new int[width * height];
                ret.getPixels(pixels, 0, width, 0, 0, width, height);

                // check all pixels for alpha and color
                int idx = 0;
                for (; idx < pixels.length; ++idx)
                {
                    if (0xFF000000 != (pixels[idx] & 0xFF000000))
                    {
                        BUG62016_WORKAROUND_NEEDED = false;
                        break;
                    }
                    else if (transp == (pixels[idx] & 0xFFFFFF))
                        pixels[idx] = 0;
                }

                // failed? recreate bitmap
                if (idx >= pixels.length)
                {
                    ret = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    ret.setHasAlpha(true);
                    ret.setPixels(pixels, 0, width, 0, 0, width, height);
                }
            }
        }

        return ret;
    }
    //endregion

    //region RENDERER
    private boolean mPreloadRunning;
    private Runnable mPreloadRunnable = new Runnable()
    {
        @Override public void run()
        {
            mPreloadRunning = true;
            Frame f = mFrames.get(mPlayFrame);
            Bitmap bm = loadFrame(f);
            if (bm != null)
                f.mBitmap = new SoftReference<>(bm);
            mPreloadRunning = false;
        }
    };

    @Override
    protected void step()
    {
        // not animated? return silently
        if (null == mFrames)
            return;

        // we're early, probably we have multiple active drawables.
        // do nothing
        long time = SystemClock.uptimeMillis();
        if (time < mNextFrameTime)
            return;

        // we're way late, probably there were no active drawables for some time.
        // restart the animation
        if (time > mNextFrameTime + 1000)
            mPlayFrame = 0;

        // get the frame
        Frame f = mFrames.get(mPlayFrame);
        mNextFrameTime = time + Math.max(1, mHasDelays ? f.mDelay : 100);
        int fx = f.mOffX >> mScaleShift, fy = f.mOffY >> mScaleShift, fw = f.mWidth >> mScaleShift, fh = f.mHeight >> mScaleShift;

        // frame is still preloading
        // we're already updated Next Frame Time, so just return
        if (mPreloadRunning)
            return;

        // finish previous frame disposal
        if (mPlayFrame == 0)
        {
            mCanvas.drawColor((f.mTransp >= 0) ? Color.TRANSPARENT : mBackColor, PorterDuff.Mode.SRC);
            mPrevDispose = 0;
        }
        if (mPrevDispose > 2)
        {
            mPrevPixels.rewind();
            mOutput.copyPixelsFromBuffer(mPrevPixels);
        }
        else if (mPrevDispose == 2)
        {
            mCanvas.save();
            mCanvas.clipRect(mPrevRect);
            mCanvas.drawColor((f.mTransp >= 0) ? Color.TRANSPARENT : mBackColor, PorterDuff.Mode.SRC);
            mCanvas.restore();
        }

        // prepare new frame disposal
        mPrevDispose = f.mDispose;
        if (mPrevDispose > 2)
        {
            if (null == mPrevPixels)
                mPrevPixels = ByteBuffer.allocate(mOutput.getRowBytes() * mOutput.getHeight());
            mPrevPixels.rewind();
            mOutput.copyPixelsToBuffer(mPrevPixels);
        }
        else if (mPrevDispose == 2)
        {
            if (null == mPrevRect)
                mPrevRect = new Rect();
            mPrevRect.set(fx, fy, fx + fw, fy + fh);
        }

        // draw the frame
        Bitmap bm = null;
        if (null != f.mBitmap)
            bm = f.mBitmap.get();
        if (bm == null)
        {
            bm = loadFrame(f);
            if (bm != null)
                f.mBitmap = new SoftReference<>(bm);
        }
        if (null != bm)
            mCanvas.drawBitmap(bm, fx, fy, null);

        // step to the next frame
        if (++mPlayFrame == mFrames.size())
            mPlayFrame = 0;

        // preload bitmap for the next frame
        f = mFrames.get(mPlayFrame);
        if ((null == f.mBitmap) || (null == f.mBitmap.get()))
            PicoImg.sExecutor.execute(mPreloadRunnable);
    }
    //endregion

    static boolean check(InputStream inp) throws IOException
    {
        boolean ret = true;
        inp.mark(8);
        for (int i = 0; ret && (i < 6); ++i)
        {
            byte b = (byte) read_ubyte(inp);
            if ((GIF_HEADER87[i] != b) && (GIF_HEADER89[i] != b))
                ret = false;
        }
        inp.reset();
        return ret;
    }

    @Override
    boolean isAnimated()
    {
        return (mFrames != null);
    }
}
