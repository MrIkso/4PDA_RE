package in.cpp.picoimg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CRC32;

public class PicoImg
{
    static final int META_TOTAL_SIZE = 1;

    public static final int SCALE_TOP    = 0b00000001;
    public static final int SCALE_BOTTOM = 0b00000010;
    public static final int SCALE_LEFT   = 0b00000100;
    public static final int SCALE_RIGHT  = 0b00001000;
    public static final int SCALE_CROP   = 0b00010000;
    public static final int SCALE_FIT    = 0b00100000;
    public static final int SCALE_FILL   = 0b00110000;
    static final int SCALE_V_MASK        = 0b00000011;
    static final int SCALE_H_MASK        = 0b00001100;
    static final int SCALE_S_MASK        = 0b00110000;

    static Handler sHandler;
    static Executor sExecutor;
    static Map<String, SoftReference<BaseState>> sRamCache;
    static List<PicoImgRequest> sRequests;
    private static String sCachePath;
    static File sCacheDir;
    private static boolean sCacheExt;
    static SQLiteDatabase sCacheDB;
    static long sCacheLimit;
    static long sCacheSize;
    private static ThreadLocal<CRC32> sCrc32;
    private static AtomicBoolean sCleanupRunning;
    static AtomicInteger sID;

    static Stack<PicoDrawable> sDrawableRecycler;


    public static void init(Context ctx, String cacheDir, long cacheLimit)
    {
        sHandler = new Handler(ctx.getMainLooper());
        sCrc32 = new ThreadLocal<>();
        sCleanupRunning = new AtomicBoolean();
        sID = new AtomicInteger(1);

        // create executor for background tasks
        // We don't use framework executors because
        // AsyncTask.SERIAL_EXECUTOR will only run tasks one at a time and
        // AsyncTask.THREAD_POOL_EXECUTOR has a limited size queue with some catastrophic consequences on overflow
        int threads = 1 + Math.min(Math.max(Runtime.getRuntime().availableProcessors(), 2), 4);
        sExecutor = new ThreadPoolExecutor(threads, threads, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        // create maps
        sRamCache = new HashMap<>();
        sRequests = new Vector<>(30);
        sDrawableRecycler = new Stack<>();

        // initialize cache
        sCachePath = cacheDir;
        sCacheLimit = cacheLimit;
        initCachePath(ctx, false);
        initDB(ctx);
    }

    private static void initCachePath(Context ctx, boolean intOnly)
    {
        sCacheDir = null;
        sCacheExt = false;
        if (TextUtils.isEmpty(sCachePath))
        {
            if (!intOnly && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO))
            {
                try { sCacheDir = ctx.getExternalCacheDir(); }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (!intOnly && (sCacheDir == null) && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                try { sCacheDir = ctx.getExternalFilesDir(null); }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (null != sCacheDir)
                sCacheExt = true;
            if (sCacheDir == null)
            {
                try { sCacheDir = ctx.getCacheDir(); }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (sCacheDir == null)
            {
                try { sCacheDir = ctx.getFilesDir(); }
                catch (Exception e) { e.printStackTrace(); }
            }
            sCacheDir = new File(sCacheDir, "picoimg");
        }
        else
            sCacheDir = new File(sCachePath);
    }

    synchronized static void initDB(Context ctx)
    {
        // we already have a db? try to close
        try
        {
            if (null != sCacheDB)
                sCacheDB.close();
        }
        catch (Throwable e1)
        {
            e1.printStackTrace();
        }
        // ensure cache directory exists
        if (!sCacheDir.exists())
            sCacheDir.mkdirs();
        // open cache db
        File cacheFile = new File(sCacheDir, "cache.db");
        try
        {
            sCacheDB = SQLiteDatabase.openDatabase(cacheFile.getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY | SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        catch (Throwable e)
        {
            // usual problem is absent or non-writable external storage, try to handle it
            if (TextUtils.isEmpty(sCachePath) && sCacheExt)
            {
                initCachePath(ctx, true);
                initDB(ctx);
                return;
            }
            else
                throw e;
        }
        Cursor c1 = sCacheDB.rawQuery("PRAGMA journal_mode = TRUNCATE", null);
        c1.moveToNext();
        c1.getString(0);
        c1.close();
        // init cache db
        sCacheDB.execSQL("CREATE TABLE IF NOT EXISTS meta (id INTEGER PRIMARY KEY, value INTEGER);");
        sCacheDB.execSQL("CREATE TABLE IF NOT EXISTS cache (id INTEGER PRIMARY KEY, name TEXT, hash INTEGER, size INTEGER, used INTEGER);");
        sCacheDB.execSQL("CREATE INDEX IF NOT EXISTS i_cache_hash ON cache(hash);");
        sCacheDB.execSQL("CREATE INDEX IF NOT EXISTS i_cache_used ON cache(used);");
        // read cache size
        Cursor result = sCacheDB.query("meta", new String[]{"value"}, "id=" + META_TOTAL_SIZE, null, null, null, null);
        if (result.moveToFirst())
            sCacheSize = result.getLong(0);
        result.close();
    }

    static CRC32 getCrc32()
    {
        CRC32 crc32 = sCrc32.get();
        if (crc32 == null)
        {
            crc32 = new CRC32();
            sCrc32.set(crc32);
        }
        return crc32;
    }

    public static void cancel(ImageView v)
    {
        synchronized (sRequests)
        {
            for (PicoImgRequest r: PicoImg.sRequests)
                if (!r.mCancelled && v.equals(r.mTargetView))
                    r.mCancelled = true;
        }
    }

    public static long getCacheUsage()
    {
        return sCacheSize;
    }

    public static void setCacheSize(long limit)
    {
        sCacheLimit = limit;
        if ((sCacheLimit > 0) && (sCacheSize >= sCacheLimit))
            cleanupCache();
    }

    public static boolean emptyCache(final Context ctx, final Runnable onDone)
    {
        if (sCleanupRunning.compareAndSet(false, true))
        {
            sExecutor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try { sCacheDB.close(); }
                    catch (Throwable e) { e.printStackTrace(); }

                    // delete all files from the cache directory
                    File[] list = sCacheDir.listFiles();
                    if (list != null)
                        for (File f: list)
                            if (!f.isHidden())
                                f.delete();

                    // reinit
                    initDB(ctx);
                    sCacheSize = 0;
                    sRamCache.clear();

                    // done
                    sCleanupRunning.set(false);
                    if (onDone != null)
                        sHandler.post(onDone);
                }
            });
            return true;
        }
        return false;
    }

    static void cleanupCache()
    {
        if (sCleanupRunning.compareAndSet(false, true))
            sExecutor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    // delete old files
                    while ((sCacheLimit > 0) && (sCacheSize > ((sCacheLimit / 10) * 9)))
                    {
                        Cursor result = sCacheDB.rawQuery("SELECT `id`, `size` FROM `cache` ORDER BY `used` ASC LIMIT 10", null);
                        if (result.moveToFirst())
                        {
                            long id = result.getLong(0);
                            (new File(sCacheDir, String.valueOf(id))).delete();
                            sCacheDB.delete("cache", "id=" + id, null);
                            sCacheSize -= result.getInt(1);
                        }
                        else
                            sCacheSize = 0;
                        result.close();
                    }
                    // save new total cache size
                    ContentValues cv = new ContentValues();
                    cv.put("id", PicoImg.META_TOTAL_SIZE);
                    cv.put("value", PicoImg.sCacheSize);
                    PicoImg.sCacheDB.insertWithOnConflict("meta", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                    // done
                    sCleanupRunning.set(false);
                }
            });
    }

    static PicoDrawable cycleDrawable(ImageView v, Drawable placeholder, int width, int height, int scale)
    {
        // try to use existing drawable
        Drawable prevDrawable = null;
        if (null != v)
        {
            prevDrawable = v.getDrawable();
            if (prevDrawable instanceof PicoDrawable)
            {
                if ((prevDrawable.getIntrinsicWidth() == width) && (prevDrawable.getIntrinsicHeight() == height))
                {
                    ((PicoDrawable) prevDrawable).init(placeholder, width, height, scale);
                    return (PicoDrawable) prevDrawable;
                }
            }
            else
                prevDrawable = null;
        }

        // go with the new drawable
        PicoDrawable newDrawable;
        try
        {
            newDrawable = sDrawableRecycler.pop();
        }
        catch (EmptyStackException e)
        {
            newDrawable = new PicoDrawable();
        }
        newDrawable.init(placeholder, width, height, scale);
        if (null != v)
            v.setImageDrawable(newDrawable);
        if (null != prevDrawable)
            ((PicoDrawable) prevDrawable).recycle();
        return newDrawable;
    }

    public static PicoImgRequest loadResource(Context ctx, int resId)
    {
        return new PicoImgRequest(ctx, resId);
    }

    public static PicoImgRequest loadAsset(Context ctx, String name)
    {
        return new PicoImgRequest(ctx, name);
    }

    public static PicoImgRequest loadUrl(Context ctx, String url, String key)
    {
        return new PicoImgRequest(ctx, url, key);
    }

    public static PicoImgRequest loadUrl(Context ctx, String url)
    {
        return new PicoImgRequest(ctx, url, null);
    }

  /*  public long getVersionCode()
    {
        return BuildConfig.VERSION_CODE;
    }

    public String getVersionString()
    {
        return BuildConfig.VERSION_NAME;
    }*/
}
