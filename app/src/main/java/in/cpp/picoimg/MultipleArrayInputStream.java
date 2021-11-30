package in.cpp.picoimg;

import java.io.IOException;
import java.io.InputStream;

class MultipleArrayInputStream extends InputStream
{
    private byte[][] mBufs;
    private int mCurrentBuf;
    private int mCurrentPos;
    private int mCurrentAvail;
    private int mMarkBuf;
    private int mMarkPos;
    private int mMarkAvail;

    public MultipleArrayInputStream(byte[][] bufs)
    {
        this.mBufs = bufs;
        for (byte[] buf: bufs)
            mCurrentAvail += buf.length;
        mMarkAvail = mCurrentAvail;
    }

    public synchronized int read()
    {
        if (mCurrentBuf >= mBufs.length)
            return -1;
        int ret = mBufs[mCurrentBuf][mCurrentPos];
        if (++mCurrentPos == mBufs[mCurrentBuf].length)
        {
            mCurrentPos = 0;
            mCurrentBuf++;
        }
        mCurrentAvail--;
        return ret;
    }

    public synchronized int read(byte[] b, int off, int len)
    {

        int ret = 0;
        while (ret < len)
        {
            if (mCurrentBuf >= mBufs.length)
                break;
            int reading = Math.min(mBufs[mCurrentBuf].length - mCurrentPos, len - ret);
            System.arraycopy(mBufs[mCurrentBuf], mCurrentPos, b, off, reading);
            off += reading;
            ret += reading;
            mCurrentPos += reading;
            mCurrentAvail -= reading;
            if (mCurrentPos >= mBufs[mCurrentBuf].length)
            {
                mCurrentPos = 0;
                ++mCurrentBuf;
            }
        }
        // eof?
        if ((ret == 0) && (mCurrentBuf >= mBufs.length))
            ret = -1;
        return ret;
    }

    public synchronized long skip(long n)
    {
        long ret = 0;
        while (ret < n)
        {
            if (mCurrentBuf >= mBufs.length)
                break;
            long skipping = Math.min(mBufs[mCurrentBuf].length - mCurrentPos, n);
            ret += skipping;
            mCurrentPos += skipping;
            mCurrentAvail -= skipping;
            if (mCurrentPos >= mBufs[mCurrentBuf].length)
            {
                mCurrentPos = 0;
                ++mCurrentBuf;
            }
        }
        return ret;
    }

    public synchronized int available()
    {
        return mCurrentAvail;
    }

    public boolean markSupported()
    {
        return true;
    }

    public void mark(int readAheadLimit)
    {
        mMarkBuf = mCurrentBuf;
        mMarkPos = mCurrentPos;
        mMarkAvail = mCurrentAvail;
    }

    public synchronized void reset()
    {
        mCurrentBuf = mMarkBuf;
        mCurrentPos = mMarkPos;
        mCurrentAvail = mMarkAvail;
    }

    public void close() throws IOException
    {
    }
}
