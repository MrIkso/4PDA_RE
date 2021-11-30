package in.cpp.picoimg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class PicoImgRequest implements Runnable
{
    public interface TargetCallback
    {
        void onPicoImgProgress(PicoImgRequest r, int progress, int total);
        void onPicoImgResult(PicoImgRequest r, PicoDrawable d);
        void onPicoImgError(PicoImgRequest r, Throwable e);
    }

    public static class CancelledException extends IOException {}

    Context mContext;
    boolean mDone;
    boolean mCancelled;

    // source
    private int mInputResId;
    private String mInputAsset;
    private String mInputUrl;
    private String mInputKey;

    // setup
    private boolean mDisableAnimation;
    private boolean mSkipRamLookup;
    private boolean mSkipRamStore;
    private boolean mSkipDiskLookup;
    private boolean mSkipDiskStore;
    private boolean mCachedOnly;
    private Drawable mPlaceholderDrawable;
    private int mFadeSteps = 1;
    private int mFadeDuration;
    private boolean mFadeAlways;
    private int mResizeWidth;
    private int mResizeHeight;
    private int mScaleType = PicoImg.SCALE_FIT;

    // target
    ImageView mTargetView;
    TargetCallback mTargetCallback;

    //
    private int mRequestId;
    private String mRamKey;
    private long mCacheKey = -1;
    private int mAppId;
    private Object mAppObj;
    BaseState mResult;
    private PicoDrawable mDrawable;
    private Throwable mResultError;

    PicoImgRequest(Context ctx, int resId)
    {
        mContext = ctx;
        mInputResId = resId;
        mInputKey = "#" + resId;
        mRequestId = PicoImg.sID.incrementAndGet();
    }

    PicoImgRequest(Context ctx, String asset)
    {
        mContext = ctx;
        mInputAsset = asset;
        mInputKey = asset;
        mRequestId = PicoImg.sID.incrementAndGet();
    }

    PicoImgRequest(Context ctx, String url, String key)
    {
        mContext = ctx;
        mInputUrl = url;
        mInputKey = TextUtils.isEmpty(key) ? url : key;
        mRequestId = PicoImg.sID.incrementAndGet();
    }

    public PicoImgRequest to(ImageView v)
    {
        mTargetView = v;
        return this;
    }

    public PicoImgRequest callback(TargetCallback c)
    {
        mTargetCallback = c;
        return this;
    }

    public PicoImgRequest placeholder(int resId)
    {
        mPlaceholderDrawable = mContext.getResources().getDrawable(resId);
        return this;
    }

    public PicoImgRequest placeholder(Drawable drawable)
    {
        mPlaceholderDrawable = drawable;
        return this;
    }

    public PicoImgRequest fade(int steps, int durationMillis, boolean always)
    {
        mFadeSteps = steps;
        mFadeDuration = durationMillis;
        mFadeAlways = always;
        return this;
    }

    public PicoImgRequest size(int width, int height)
    {
        mResizeWidth = width;
        mResizeHeight = height;
        return this;
    }

    public PicoImgRequest sizeToView()
    {
        if (null != mTargetView)
        {
            Drawable s = mTargetView.getDrawable();
            if (s != null)
            {
                mResizeWidth = s.getIntrinsicWidth();
                mResizeHeight = s.getIntrinsicHeight();
            }
            if ((mResizeWidth <= 0) || (mResizeHeight <= 0))
            {
                mResizeWidth = mTargetView.getWidth();
                mResizeHeight = mTargetView.getHeight();
                if ((mResizeWidth <= 0) || (mResizeHeight <= 0))
                {
                    ViewGroup.LayoutParams p = mTargetView.getLayoutParams();
                    if (null != p)
                    {
                        mResizeWidth = p.width;
                        mResizeHeight = p.height;
                    }
                    if ((mResizeWidth <= 0) || (mResizeHeight <= 0))
                        mResizeWidth = mResizeHeight = 0;
                }
            }
        }
        return this;
    }

    public PicoImgRequest sizeToScreen()
    {
        DisplayMetrics m = mContext.getResources().getDisplayMetrics();
        mResizeWidth = m.widthPixels;
        mResizeHeight = m.heightPixels;
        return this;
    }

    public PicoImgRequest scale(int scale)
    {
        mScaleType = scale;
        return this;
    }

    public PicoImgRequest disableAnimation(boolean disable)
    {
        mDisableAnimation = disable;
        return this;
    }

    public PicoImgRequest skipCache(boolean skipRamLookup, boolean skipRamStore, boolean skipDiskLookup, boolean skipDiskStore)
    {
        mSkipRamLookup = skipRamLookup;
        mSkipRamStore = skipRamStore;
        mSkipDiskLookup = skipDiskLookup;
        mSkipDiskStore = skipDiskStore;
        return this;
    }

    public PicoImgRequest cachedOnly(boolean cachedOnly)
    {
        mCachedOnly = cachedOnly;
        return this;
    }

    public void run()
    {
        // this is the second invocation running on the UI thread
        if (mDone)
        {
            // publish the result
            if (!mCancelled)
            {
                if (mResult != null)
                {
                    if (null == mDrawable)
                        mDrawable = PicoImg.cycleDrawable(mTargetView, mPlaceholderDrawable, ((mResizeWidth > 0) && (mResizeHeight > 0)) ? mResizeWidth : mResult.mWidth, ((mResizeWidth > 0) && (mResizeHeight > 0)) ? mResizeHeight : mResult.mHeight, mScaleType);
                    mDrawable.setConstantState(mResult, (mTargetView != null) ? mFadeSteps : 0, mFadeDuration);
                    if (null != mTargetCallback)
                        mTargetCallback.onPicoImgResult(this, mDrawable);
                }
                else if ((mTargetView != null) && (mDrawable == null) && (mPlaceholderDrawable == null))
                    mTargetView.setImageResource(android.R.color.transparent);
            }
            else if ((null != mTargetCallback) && (null == mResultError))
                mResultError = new CancelledException();

            // propagate the error
            if ((mResultError != null) && (null != mTargetCallback))
                mTargetCallback.onPicoImgError(this, mResultError);

            // remove ourselves from the list
            synchronized (PicoImg.sRequests)
            {
                PicoImg.sRequests.remove(this);
            }

            // done
            return;
        }


        // configure cache keys
        if ((null != mInputKey) && (mRamKey == null))
            generateRamKey();

        // find other request loading the same drawable
        PicoImgRequest preceedingReq = null;
        synchronized (PicoImg.sRequests)
        {
            boolean foundSelf = false;
            for (PicoImgRequest r: PicoImg.sRequests)
            {
                if (r.equals(this))
                    foundSelf = true;
                else if ((null != mInputKey) && mInputKey.equals(r.mInputKey) && (mRequestId > r.mRequestId))
                    preceedingReq = r;
            }
            // add ourselves
            if (!foundSelf)
                PicoImg.sRequests.add(this);
        }
        // wait for the request to reuse the result
        if (null != preceedingReq)
        {
            synchronized (preceedingReq)
            {
                while(!preceedingReq.mDone)
                {
                    try { preceedingReq.wait(); break; }
                    catch (InterruptedException e) {}
                }
            }
        }

        // check the ram cache
        if (!mSkipRamLookup && (null != mRamKey))
            checkRamCache();

        // ram cache missed? do the job
        if (null == mResult)
        {
            InputStream inp = null;
            File cacheFile = null;

            try
            {
                if (mInputResId != 0)
                    inp = mContext.getResources().openRawResource(mInputResId);
                else if (!TextUtils.isEmpty(mInputAsset))
                    inp = mContext.getResources().getAssets().open(mInputAsset);
                else if (!TextUtils.isEmpty(mInputUrl))
                {
                    int hash = mInputKey.hashCode();
                    boolean download = true;

                    // try to find existing file in cache
                    if (-1 == mCacheKey)
                    {
                        Cursor result = PicoImg.sCacheDB.query("cache", new String[]{"id", "size"}, "hash=? AND name=?", new String[]{String.valueOf(hash), mInputKey}, null, null, null);
                        if (result.moveToFirst())
                        {
                            mCacheKey = result.getLong(0);
                            download = mSkipDiskLookup || (result.getInt(1) == 0);
                        }
                        result.close();
                    }
                    // create temporary cache key
                    if (-1 == mCacheKey)
                        mCacheKey = -mRequestId;

                    // create cache file name
                    cacheFile = new File(PicoImg.sCacheDir, String.valueOf(mCacheKey));
                    if (!PicoImg.sCacheDir.exists() && !PicoImg.sCacheDir.mkdirs())
                        throw new IOException("Unable to create cache directory: " + PicoImg.sCacheDir.getAbsolutePath());
                    if (!download && !cacheFile.exists())
                        download = true;

                    // need to download the file?
                    if (download && !mCachedOnly)
                    {
                        URL url = new URL(mInputUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setInstanceFollowRedirects(true);
                        conn.setConnectTimeout(3000);
                        conn.setReadTimeout(3000);
                        int stat = conn.getResponseCode();
                        if (stat == HttpURLConnection.HTTP_OK)
                        {
                            int size = conn.getContentLength(), read, total = 0;
                            InputStream is = conn.getInputStream();
                            FileOutputStream os = new FileOutputStream(cacheFile);
                            byte[] buf = new byte[4096];
                            // do the pumping
                            while (0 < (read = is.read(buf, 0, buf.length)))
                            {
                                os.write(buf, 0, read);
                                total += read;
                                if (null != mTargetCallback)
                                    mTargetCallback.onPicoImgProgress(this, total, size);
                                if (mCancelled)
                                    break;
                            }
                            is.close();
                            os.close();
                            if (mCancelled && ((total == 0) || (size != total)))
                                throw new CancelledException();
                            if ((size > 0) && (size != total))
                                throw new IOException("Server promised " + size + " bytes and sent " + total);
                            // update disk cache
                            if (!mSkipDiskStore)
                            {
                                try
                                {
                                    ContentValues cv = new ContentValues();
                                    cv.put("used", (int)(System.currentTimeMillis() / 1000));
                                    // insert new cache entry
                                    if (mCacheKey < 0)
                                    {
                                        cv.put("name", mInputKey);
                                        cv.put("hash", hash);
                                        cv.put("size", total);
                                        long newKey = PicoImg.sCacheDB.insert("cache", null, cv);
                                        if (newKey >= 0)
                                        {
                                            File newFile = new File(PicoImg.sCacheDir, String.valueOf(newKey));
                                            if (cacheFile.renameTo(newFile))
                                            {
                                                mCacheKey = newKey;
                                                cacheFile = newFile;
                                            }
                                        }
                                        // update total size
                                        PicoImg.sCacheSize += total;
                                        cv.clear();
                                        cv.put("id", PicoImg.META_TOTAL_SIZE);
                                        cv.put("value", PicoImg.sCacheSize);
                                        PicoImg.sCacheDB.insertWithOnConflict("meta", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                                        // time to run cleanup?
                                        if ((PicoImg.sCacheLimit > 0) && (PicoImg.sCacheSize >= PicoImg.sCacheLimit))
                                            PicoImg.cleanupCache();
                                    }
                                    // update existing cache entry
                                    else
                                        PicoImg.sCacheDB.update("cache", cv, "id=" + mCacheKey, null);
                                }
                                catch (Throwable e)
                                {
                                    // we've got some error from the database
                                    // this usually means our cache was cleared by the third party
                                    // reopen the db
                                    PicoImg.initDB(mContext);
                                }
                            }
                        }
                        else
                            throw new IOException("Status " + stat + " from server");
                    }

                    // open cached file
                    inp = new FileInputStream(cacheFile);
                }

                // check if we have stream to read
                if (null == inp)
                    throw new IOException("No input stream opened");

                if (mCancelled)
                    throw new CancelledException();

                // we need to seek the stream to allow type guessing
                if (!inp.markSupported())
                    inp = new BufferedInputStream(inp);

                // create factory
                if (!mDisableAnimation && PNGState.check(inp))
                    mResult = new PNGState(inp, mResizeWidth, mResizeHeight);
                else if (!mDisableAnimation && GIFState.check(inp))
                    mResult = new GIFState(inp, mResizeWidth, mResizeHeight);
                else
                    mResult = new BaseState(inp, mResizeWidth, mResizeHeight, mContext, mInputResId, mInputAsset, cacheFile);

                // cache to ram
                if (!mSkipRamStore && (null != mRamKey))
                    PicoImg.sRamCache.put(mRamKey, new SoftReference<>(mResult));
            }
            catch (Throwable e)
            {
                if (mTargetCallback != null)
                    mResultError = e;
                else if (!(e instanceof CancelledException))
                    e.printStackTrace();
            }

            // finalize decoding
            if (null != inp)
            {
                try { inp.close(); }
                catch (Throwable e) { e.printStackTrace(); }
            }
            if ((mCacheKey < 0) && (null != cacheFile))
                cacheFile.delete();
        }

        // done loading
        synchronized (this)
        {
            mDone = true;
            this.notifyAll();
        }

        // restart on the main thread to publish the result
        if (Looper.getMainLooper().equals(Looper.myLooper()))
            run();
        else
            PicoImg.sHandler.post(this);
    }

    public void runAsync()
    {
        boolean mainThread = Looper.getMainLooper().equals(Looper.myLooper());

        // configure cache keys
        if ((null != mInputKey) && (mRamKey == null))
            generateRamKey();

        // check the ram cache
        if (!mSkipRamLookup && (null != mRamKey))
            checkRamCache();

        // check view target for conflicts
        if (null != mTargetView)
            PicoImg.cancel(mTargetView);

        // we've got a result?
        if (null != mResult)
        {
            // should we skip the fade?
            if (!mFadeAlways)
                mFadeSteps = 1;
            if (mainThread)
                run();
            else
                PicoImg.sHandler.post(this);
            return;
        }

        // if we're targeting ImageView we may try to pre-create the drawable
        // to prevent layout pass when real image is loaded
        if ((mTargetView != null) && mainThread)
        {
            if ((mResizeWidth > 0) && (mResizeHeight > 0))
                mDrawable = PicoImg.cycleDrawable(mTargetView, mPlaceholderDrawable, mResizeWidth, mResizeHeight, mScaleType);
            else if (null != mPlaceholderDrawable)
                mTargetView.setImageDrawable(mPlaceholderDrawable);
        }

        // add this request to the list of running requests
        synchronized (PicoImg.sRequests)
        {
            PicoImg.sRequests.add(this);
        }

        // schedule background worker
        PicoImg.sExecutor.execute(this);
    }

    public PicoImgRequest setAppId(int appId)
    {
        mAppId = appId;
        return this;
    }

    public int getAppId()
    {
        return mAppId;
    }

    public PicoImgRequest setAppObj(Object appObj)
    {
        mAppObj = appObj;
        return this;
    }

    public Object getAppObj()
    {
        return mAppObj;
    }

    private void generateRamKey()
    {
        if (TextUtils.isEmpty(mInputKey))
            mInputKey = null;
        if (mInputKey != null)
        {
            mRamKey = mInputKey;
            if (mDisableAnimation)
                mRamKey += "#noanim";
        }
    }

    private void checkRamCache()
    {
        // no reference
        SoftReference<BaseState> cachedRef = PicoImg.sRamCache.get(mRamKey);
        if (null == cachedRef)
            return;
        // cleared by garbage collection
        BaseState state = cachedRef.get();
        if (null == state)
            return;
        // cached image is downsampled and we have higher resolution target
        if ((state.mScaleShift > 0) && ((state.mWidth < mResizeWidth) || (state.mHeight < mResizeHeight) || ((mResizeWidth == 0) && (mResizeHeight == 0))))
            return;
        // ok
        mResult = state;
    }

    @Override
    public String toString()
    {
        if (mInputResId != 0)
            return "PicoImgRequest(res#" + mInputResId + ")";
        else if (mInputAsset != null)
            return "PicoImgRequest(asset " + mInputAsset + ")";
        else if (mInputUrl != null)
            return "PicoImgRequest(url " + mInputUrl + ")";
        return super.toString();
    }
}
