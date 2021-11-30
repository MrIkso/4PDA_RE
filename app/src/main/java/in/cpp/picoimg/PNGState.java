package in.cpp.picoimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

class PNGState extends BaseState
{
    private static final byte[] PNG_HEADER = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};
    private static final byte[] PNG_FOOTER = {0, 0, 0, 0, 'I', 'E', 'N', 'D', (byte) 0xAE, 0x42, 0x60, (byte) 0x82};

    private static final int CHUNK_IHDR = 0x49484452;
    private static final int CHUNK_IEND = 0x49454E44;
    private static final int CHUNK_IDAT = 0x49444154;
    private static final int CHUNK_ACTL = 0x6163544C;
    private static final int CHUNK_FCTL = 0x6663544C;
    private static final int CHUNK_FDAT = 0x66644154;

    private static class Chunk
    {
        int mType;
        int mLength;
        int mCrc;
        byte[] mData;
    }

    private static class Frame
    {
        int mWidth;
        int mHeight;
        int mOffX;
        int mOffY;
        int mDelay;
        int mDispose;
        int mBlend;
        List<byte[]> mData;
        SoftReference<Bitmap> mBitmap;
    }

    // source data
    private byte[] mCommonHeader;
    private Frame[] mFrames;

    // playback
    private Canvas mCanvas;
    private int mPrevDispose;
    private Rect mPrevRect;
    private ByteBuffer mPrevPixels;

    //region LOADER
    private static byte read_byte(InputStream inp) throws IOException
    {
        int b1 = inp.read();
        if (b1 == -1)
            throw new IOException("Unexpected end of file");
        return (byte) b1;
    }

    private static short read_short(InputStream inp) throws IOException
    {
        int b1 = inp.read();
        int b2 = inp.read();
        if ((b1 == -1) || (b2 == -1))
            throw new IOException("Unexpected end of file");
        return (short) ((b1 << 8) | b2);
    }

    private static int read_int(InputStream inp) throws IOException
    {
        int b1 = inp.read();
        int b2 = inp.read();
        int b3 = inp.read();
        int b4 = inp.read();
        if ((b1 == -1) || (b2 == -1) || (b3 == -1) || (b4 == -1))
            throw new IOException("Unexpected end of file");
        return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
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

    private static int write_int(byte[] buffer, int pos, int data)
    {
        buffer[pos++] = (byte) ((data >> 24) & 0xFF);
        buffer[pos++] = (byte) ((data >> 16) & 0xFF);
        buffer[pos++] = (byte) ((data >> 8) & 0xFF);
        buffer[pos++] = (byte) (data & 0xFF);
        return pos;
    }

    PNGState(InputStream inp, int targetWidth, int targetHeight) throws IOException
    {
        List<byte[]> single_idat = null;
        List<Chunk> extra_chunks = null;
        byte bit_depth = 0, color_type = 0, compression_method = 0, filter_method = 0, interlace_method = 0;
        int origWidth = 0, origHeight = 0;

        // check header
        for (int i = 0; i < 8; ++i)
            if (PNG_HEADER[i] != read_byte(inp))
                throw new IOException("Invalid PNG header");

        try
        {
            // read chunks
            int anim_seq = -1;
            int anim_frame = -1;
            while (true)
            {
                int len = read_int(inp);
                int type = read_int(inp);

                // handle known chunks
                if ((CHUNK_IHDR == type) && (len == 13))
                {
                    origWidth = mWidth = read_int(inp);
                    origHeight = mHeight = read_int(inp);
                    bit_depth = read_byte(inp);
                    color_type = read_byte(inp);
                    compression_method = read_byte(inp);
                    filter_method = read_byte(inp);
                    interlace_method = read_byte(inp);
                    read_int(inp); // crc
                    if ((targetWidth > 0) || (targetHeight > 0))
                        scaleToTarget(targetWidth, targetHeight);
                }
                else if (CHUNK_IDAT == type)
                {
                    // read out the chunk contents
                    byte[] data = new byte[len + 12];
                    write_int(data, 0, len);
                    write_int(data, 4, type);
                    read_bytes(inp, data, 8, len);
                    write_int(data, data.length - 4, read_int(inp)); // crc
                    // save it somewhere
                    if ((mFrames != null) && (anim_frame >= 0) && (anim_frame < mFrames.length))
                    {
                        if (null == mFrames[anim_frame].mData)
                            mFrames[anim_frame].mData = new ArrayList<>(3);
                        mFrames[anim_frame].mData.add(data);
                    }
                    else
                    {
                        if (null == single_idat)
                            single_idat = new ArrayList<>(3);
                        single_idat.add(data);
                    }
                }
                else if ((CHUNK_ACTL == type) && (len == 8))
                {
                    // read out the chunk contents
                    int frames = read_int(inp);
                    mNumPlays = read_int(inp);
                    read_int(inp); // crc
                    // init frame array
                    if (frames > 0)
                    {
                        mFrames = new Frame[frames];
                        for (int i = 0; i < frames; ++i)
                            mFrames[i] = new Frame();
                    }
                }
                else if ((CHUNK_FCTL == type) && (len == 26))
                {
                    // read out the chunk contents
                    int seq = read_int(inp);
                    int width = read_int(inp);
                    int height = read_int(inp);
                    int offx = read_int(inp);
                    int offy = read_int(inp);
                    short delay_num = read_short(inp);
                    short delay_den = read_short(inp);
                    byte dispose = read_byte(inp);
                    byte blend = read_byte(inp);
                    read_int(inp); // crc
                    // check animation sequence
                    if (seq != ++anim_seq)
                        Log.w("picoimg", "Wrong apng sequence, " + seq + " != " + anim_seq);
                    // save
                    if ((mFrames != null) && (anim_frame < mFrames.length))
                    {
                        ++anim_frame;
                        mFrames[anim_frame].mWidth = width;
                        mFrames[anim_frame].mHeight = height;
                        mFrames[anim_frame].mOffX = offx;
                        mFrames[anim_frame].mOffY = offy;
                        mFrames[anim_frame].mDelay = (1000 * (int) delay_num) / ((delay_den != 0) ? delay_den : 100);
                        mFrames[anim_frame].mDispose = dispose;
                        mFrames[anim_frame].mBlend = blend;
                    }
                }
                else if ((CHUNK_FDAT == type) && (len > 4))
                {
                    // read out the chunk contents
                    int seq = read_int(inp);
                    byte[] data = new byte[len + 8];
                    write_int(data, 0, len - 4);
                    write_int(data, 4, CHUNK_IDAT);
                    read_bytes(inp, data, 8, len - 4);
                    // handle crc
                    read_int(inp);
                    CRC32 crc = PicoImg.getCrc32();
                    crc.reset();
                    crc.update(data, 4, data.length - 8);
                    write_int(data, data.length - 4, (int) crc.getValue());
                    // check animation sequence
                    if (seq != ++anim_seq)
                        Log.w("picoimg", "Wrong apng sequence, " + seq + " != " + anim_seq);
                    // save
                    if ((mFrames != null) && (anim_frame >= 0) && (anim_frame < mFrames.length))
                    {
                        if (null == mFrames[anim_frame].mData)
                            mFrames[anim_frame].mData = new ArrayList<>(3);
                        mFrames[anim_frame].mData.add(data);
                    }
                }
                else if (CHUNK_IEND == type)
                {
                    read_int(inp); // crc
                    break;
                }
                else
                {
                    Chunk c = new Chunk();
                    c.mLength = len;
                    c.mType = type;
                    if (len > 0)
                    {
                        c.mData = new byte[len];
                        read_bytes(inp, c.mData, 0, len);
                    }
                    c.mCrc = read_int(inp);
                    if (null == extra_chunks)
                        extra_chunks = new ArrayList<>(5);
                    extra_chunks.add(c);
                }
            }
        }
        catch (Throwable e)
        {
            if (((mFrames != null) && (mFrames.length > 0)) || (single_idat != null))
                e.printStackTrace();
            else
                throw e;
        }

        // prepare common header buffer
        int size = 12 + 13; // IHDR
        if (extra_chunks != null)
            for (Chunk ch: extra_chunks)
                size += 12 + ch.mLength;

        // write ihdr
        mCommonHeader = new byte[size];
        int pos = write_int(mCommonHeader, 0, 13);
        pos = write_int(mCommonHeader, pos, CHUNK_IHDR);
        pos = write_int(mCommonHeader, pos, 0); // width
        pos = write_int(mCommonHeader, pos, 0); // height
        mCommonHeader[pos++] = bit_depth;
        mCommonHeader[pos++] = color_type;
        mCommonHeader[pos++] = compression_method;
        mCommonHeader[pos++] = filter_method;
        mCommonHeader[pos++] = interlace_method;
        pos = write_int(mCommonHeader, pos, 0); // crc placeholder

        // write extra chunks
        if (null != extra_chunks)
        {
            for (Chunk ch: extra_chunks)
            {
                pos = write_int(mCommonHeader, pos, ch.mLength);
                pos = write_int(mCommonHeader, pos, ch.mType);
                System.arraycopy(ch.mData, 0, mCommonHeader, pos, ch.mLength);
                pos += ch.mLength;
                pos = write_int(mCommonHeader, pos, ch.mCrc);
            }
        }

        // load the first bitmap and prepare for animation rendering
        if ((null != mFrames) && (mFrames[0].mData != null))
        {
            if (mFrames.length > 1)
            {
                mOutput = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mOutput);
                step();
            }
            else if (null == single_idat)
                single_idat = mFrames[0].mData;
        }
        if ((null == mOutput) && (null != single_idat))
            mOutput = loadFrame(origWidth, origHeight, single_idat);

        // final check
        if (null == mOutput)
            throw new IOException("Decoder failed unexpectedly");
    }

    private Bitmap loadFrame(int width, int height, List<byte[]> idat)
    {
        // update common header
        write_int(mCommonHeader,8, width);
        write_int(mCommonHeader, 12, height);
        CRC32 crc = PicoImg.getCrc32();
        crc.reset();
        crc.update(mCommonHeader, 4, 17);
        write_int(mCommonHeader, 21, (int) crc.getValue());

        // create buffer list
        byte[][] bufs = new byte[idat.size() + 3][];
        bufs[0] = PNG_HEADER;
        bufs[1] = mCommonHeader;
        for (int i = 0; i < idat.size(); ++i)
            bufs[2 + i] = idat.get(i);
        bufs[bufs.length - 1] = PNG_FOOTER;

        // decode the resulting data
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1 << mScaleShift;
        return BitmapFactory.decodeStream(new MultipleArrayInputStream(bufs), null, options);
    }
    //endregion

    //region RENDERER
    private boolean mPreloadRunning;
    private Runnable mPreloadRunnable = new Runnable()
    {
        @Override public void run()
        {
            mPreloadRunning = true;
            Frame f = mFrames[mPlayFrame];
            Bitmap bm = loadFrame(f.mWidth, f.mHeight, f.mData);
            if (bm != null)
                f.mBitmap = new SoftReference<>(bm);
            mPreloadRunning = false;
        }
    };

    @Override
    protected void step()
    {
        // not animated? return silently
        if ((null == mFrames) || (mFrames.length < 2))
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
        Frame f = mFrames[mPlayFrame];
        mNextFrameTime = time + Math.max(1, f.mDelay);
        int fx = f.mOffX >> mScaleShift, fy = f.mOffY >> mScaleShift, fw = f.mWidth >> mScaleShift, fh = f.mHeight >> mScaleShift;

        // frame is still preloading
        // we're already updated Next Frame Time, so just return
        if (mPreloadRunning)
            return;

        // finish previous frame disposal
        if (mPlayFrame == 0)
        {
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mPrevDispose = 0;
        }
        if (mPrevDispose == 2)
        {
            mPrevPixels.rewind();
            mOutput.copyPixelsFromBuffer(mPrevPixels);
        }
        else if (mPrevDispose == 1)
        {
            mCanvas.save();
            mCanvas.clipRect(mPrevRect);
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mCanvas.restore();
        }

        // prepare new frame disposal
        mPrevDispose = f.mDispose;
        if (mPrevDispose == 2)
        {
            if (null == mPrevPixels)
                mPrevPixels = ByteBuffer.allocate(mOutput.getRowBytes() * mOutput.getHeight());
            mPrevPixels.rewind();
            mOutput.copyPixelsToBuffer(mPrevPixels);
        }
        else if (mPrevDispose == 1)
        {
            if (null == mPrevRect)
                mPrevRect = new Rect();
            mPrevRect.set(fx, fy, fx + fw, fy + fh);
        }

        // prepare new frame composition
        if (f.mBlend == 0)
        {
            mCanvas.save();
            mCanvas.clipRect(fx, fy, fx + fw, fy + fh);
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mCanvas.restore();
        }

        // draw the frame
        Bitmap bm = null;
        if (null != f.mBitmap)
            bm = f.mBitmap.get();
        if (bm == null)
        {
            bm = loadFrame(f.mWidth, f.mHeight, f.mData);
            if (bm != null)
                f.mBitmap = new SoftReference<>(bm);
        }
        if (null != bm)
            mCanvas.drawBitmap(bm, fx, fy, null);

        // step to the next frame
        if (++mPlayFrame == mFrames.length)
            mPlayFrame = 0;

        // preload bitmap for the next frame
        f = mFrames[mPlayFrame];
        if ((null == f.mBitmap) || (null == f.mBitmap.get()))
            PicoImg.sExecutor.execute(mPreloadRunnable);
    }
    //endregion

    static boolean check(InputStream inp) throws IOException
    {
        boolean ret = true;
        inp.mark(10);
        for (int i = 0; ret && (i < 8); ++i)
            if (PNG_HEADER[i] != read_byte(inp))
                ret = false;
        inp.reset();
        return ret;
    }

    @Override
    boolean isAnimated()
    {
        return (null != mFrames) && (mFrames.length > 1);
    }
}
