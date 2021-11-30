package in.cpp.picoimg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class BaseState extends Drawable.ConstantState
{
    int mWidth;
    int mHeight;
    int mScaleShift;
    int mOrientation;
    Bitmap mOutput;

    // animation dummies
    int mNumPlays;
    int mPlayFrame;
    long mNextFrameTime;

    protected BaseState()
    {
    }

    BaseState(InputStream inp, int targetWidth, int targetHeight, Context ctx, int resId, String assetName, File urlCache) throws IOException
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        InputStream activeStream = inp;
        int origWidth = 0;

        // find sample size
        if ((targetWidth > 0) || (targetHeight > 0))
        {
            inp.mark(1024);
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inp, null, opts);
            origWidth = mWidth = opts.outWidth;
            mHeight = opts.outHeight;
            scaleToTarget(targetWidth, targetHeight);
            try
            {
                inp.reset();
            }
            catch (IOException e)
            {
                // we can't reset the provided stream, so we should open another one for decoding
                if (resId != 0)
                    activeStream = ctx.getResources().openRawResource(resId);
                else if (!TextUtils.isEmpty(assetName))
                    activeStream = ctx.getResources().getAssets().open(assetName);
                else if (null != urlCache)
                    activeStream = new FileInputStream(urlCache);
            }
        }

        // decode the bitmap
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = 1 << mScaleShift;
        mOutput = BitmapFactory.decodeStream(activeStream, null, opts);
        mWidth = opts.outWidth;
        mHeight = opts.outHeight;

        // some androids fail to downsample interlaced gifs
        // detect it here to prevent further chaos
        if ((mScaleShift > 0) && (mWidth == origWidth))
            mScaleShift = 0;

        // if we've opened second stream, it's time to close it
        if (activeStream != inp)
        {
            try
            {
                activeStream.close();
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
        }

        // ok?
        if (null == mOutput)
            throw new IOException("Wrong image format");

        // Try to read the Exif orientation
        try
        {
            // Get a working input stream
            try
            {
                activeStream = inp;
                inp.reset();
            }
            catch (IOException e)
            {
                // we can't reset the provided stream, so we should open another one for decoding
                if (resId != 0)
                    activeStream = ctx.getResources().openRawResource(resId);
                else if (!TextUtils.isEmpty(assetName))
                    activeStream = ctx.getResources().getAssets().open(assetName);
                else if (null != urlCache)
                    activeStream = new FileInputStream(urlCache);
            }
            // JPEG must start with SOI
            if (0xFFD8 == readExifInt(activeStream, 2, false))
            {
                while (true)
                {
                    // EOI or SOS. No point in proceeding
                    int marker = readExifInt(activeStream, 2, false);
                    if ((marker == 0xFFD9) || (marker == 0xFFDA))
                        break;
                    // Skip everything but APP1/Exif
                    int len = readExifInt(activeStream, 2, false);
                    if (marker != 0xFFE1)
                    {
                        if ((len - 2) != activeStream.skip(len - 2))
                            break;
                        continue;
                    }
                    // Check if this APP1 is indeed Exif
                    if (0x45786966 != readExifInt(activeStream, 4, false))
                        break;
                    if (2 != activeStream.skip(2))
                        break;
                    // read the exif header
                    boolean le = 0x4949 == readExifInt(activeStream, 2, false);
                    if (2 != activeStream.skip(2))
                        break;
                    int off = readExifInt(activeStream, 4, le);
                    if ((off - 8) != activeStream.skip(off - 8))
                        break;
                    // we only need IFD0 that must be first
                    int entries = readExifInt(activeStream, 2, le);
                    while (0 < entries--)
                    {
                        int tag = readExifInt(activeStream, 2, le);
                        int fmt = readExifInt(activeStream, 2, le);
                        if (4 != activeStream.skip(4))
                            break;
                        // Orientation
                        if (0x0112 == tag)
                        {
                            // This tag is specified to be a 16-bit value
                            if (3 == fmt)
                            {
                                mOrientation = readExifInt(activeStream, 2, le);
                                if ((mOrientation >= 5) && (mOrientation <= 8))
                                {
                                    int temp = mHeight;
                                    mHeight = mWidth;
                                    mWidth = temp;
                                }
                            }
                            break;
                        }
                        if (4 != activeStream.skip(4))
                            break;
                    }
                    // done
                    break;
                }
            }
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }

        // close stream opened for the Exif
        if (activeStream != inp)
        {
            try
            {
                activeStream.close();
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
        }
    }

    private int readExifInt(InputStream inp, int size, boolean le) throws java.io.IOException
    {
        int ret = 0;
        for (int i = 0; i < size; ++i)
        {
            int b = inp.read();
            if (b < 0)
                throw new IOException("readExifInt: EOF reached");
            ret |= (b << (8 * (le ? i : (size - 1 - i))));
        }
        return ret;
    }

    protected void scaleToTarget(int targetWidth, int targetHeight)
    {
        mScaleShift = 0;
        while ((targetWidth < (mWidth / (1 << (mScaleShift + 1)))) && (targetHeight < (mHeight / (1 << (mScaleShift + 1)))))
            ++mScaleShift;
        mWidth >>= mScaleShift;
        mHeight >>= mScaleShift;
    }

    protected void step()
    {
    }

    boolean isAnimated()
    {
        return false;
    }

    @Override
    public Drawable newDrawable()
    {
        return new PicoDrawable(this);
    }

    @Override
    public int getChangingConfigurations()
    {
        return 0; // no dependency on system events
    }
}
