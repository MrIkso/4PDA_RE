package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.zip.CRC32;

public class DataDB {
    private static File f2491a;
    private static SQLiteDatabase f2492b;
    private static String[] f2493c = {"utype", "uid", "uactive", "utitle", "aid", "aname", "uver", "uverdisc", "uext1", "uext2", "uext3", "uext4"};
    private static String[] f2494d = {"id", "upd", "del", "flg", "par", "ord", "title", "url"};
    private static String[] f2495e = {"id", "tid", "loc", "title", "hid", "par"};

    public static class C0736a {
        int f2496a;
        int f2497b;
        boolean f2498c;
        int f2499d;
        int f2500e;
        int f2501f;
        String f2502g;
        String f2503h;
        int f2504i;

        public C0736a(int i, int i2, boolean z, int i3, int i4, int i5, String str, String str2) {
            this.f2496a = i;
            this.f2497b = i2;
            this.f2498c = z;
            this.f2499d = i3;
            this.f2500e = i4;
            this.f2501f = i5;
            this.f2502g = str;
            this.f2503h = str2;
        }

        public static C0736a m349b(Cursor cursor) {
            return new C0736a(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2) != 0, cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7));
        }
    }

    public static class C0737b {
        int f2505a;
        int f2506b;
        String f2507c;
        String f2508d;
        boolean f2509e;
        int f2510f;

        public C0737b(int i, int i2, String str, String str2, boolean z, int i3) {
            this.f2505a = i;
            this.f2506b = i2;
            this.f2507c = str;
            this.f2508d = str2;
            this.f2509e = z;
            this.f2510f = i3;
        }

        public static C0737b m347b(Cursor cursor) {
            return new C0737b(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) != 0, cursor.getInt(5));
        }
    }

    public static int m383A(int i) {
        int i2 = 0;
        m364m(false);
        try {
            Cursor query = f2492b.query("unread", new String[]{"COUNT(*)"}, "utype=" + i + " AND uactive=1", null, null, null, null);
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return i2;
    }

    public static void m382B(int i, int i2) {
        m364m(false);
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            sQLiteDatabase.delete("unread", "utype=" + i + " AND uid=" + i2, null);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static C0738c m381C(int i, int i2) {
        m364m(false);
        C0738c cVar = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2493c;
            Cursor query = sQLiteDatabase.query("unread", strArr, "utype=" + i + " AND uid=" + i2, null, null, null, null);
            if (query.moveToFirst()) {
                cVar = C0738c.m345b(query);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return cVar;
    }

    public static C0738c[] m380D(int i) {
        int i2 = 0;
        m364m(false);
        C0738c[] cVarArr = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2493c;
            Cursor query = sQLiteDatabase.query("unread", strArr, "utype=" + i + " AND uactive=1", null, null, null, null);
            if (query.moveToFirst()) {
                cVarArr = new C0738c[query.getCount()];
                while (true) {
                    int i3 = i2 + 1;
                    cVarArr[i2] = C0738c.m345b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i2 = i3;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return cVarArr;
    }

    public static C0738c[] m379E(int i, int[] iArr) {
        int i2 = 0;
        m364m(false);
        C0738c[] cVarArr = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("utype=");
            sb.append(i);
            sb.append(" AND uid IN(");
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (i3 > 0) {
                    sb.append(",");
                }
                sb.append(iArr[i3]);
            }
            sb.append(")");
            Cursor query = f2492b.query("unread", f2493c, sb.toString(), null, null, null, null);
            if (query.moveToFirst()) {
                cVarArr = new C0738c[query.getCount()];
                while (true) {
                    int i4 = i2 + 1;
                    cVarArr[i2] = C0738c.m345b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i2 = i4;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return cVarArr;
    }

    public static void m378F(C0738c cVar) {
        int i = 0;
        m364m(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cln", (Integer) 0);
            contentValues.put("utype", Integer.valueOf(cVar.f2511a));
            contentValues.put("uid", Integer.valueOf(cVar.f2512b));
            if (cVar.f2513c) {
                i = 1;
            }
            contentValues.put("uactive", Integer.valueOf(i));
            contentValues.put("utitle", cVar.f2514d);
            contentValues.put("aid", Integer.valueOf(cVar.f2515e));
            contentValues.put("aname", cVar.f2516f);
            contentValues.put("uver", Integer.valueOf(cVar.f2517g));
            contentValues.put("uverdisc", Integer.valueOf(cVar.f2518h));
            contentValues.put("uext1", Integer.valueOf(cVar.f2519i));
            contentValues.put("uext2", Integer.valueOf(cVar.f2520j));
            contentValues.put("uext3", Integer.valueOf(cVar.f2521k));
            contentValues.put("uext4", Integer.valueOf(cVar.f2522l));
            f2492b.insertWithOnConflict("unread", null, contentValues, 5);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static int m377G(int i, String str) {
        int i2 = 0;
        m364m(false);
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = {"SUM(" + str + ")"};
            Cursor query = sQLiteDatabase.query("unread", strArr, "utype=" + i + " AND uactive=1", null, null, null, null);
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return i2;
    }

    public static synchronized void m376a(Context context) {
        synchronized (DataDB.class) {
            File file = new File(context.getFilesDir(), "data.db");
            f2491a = file;
            try {
                file.getParentFile().mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearBookmarks(boolean z) {
        m364m(false);
        try {
            f2492b.delete("bookmarks", z ? "(flg & 2) != 0" : "", null);
            m362o(3, 0);
            m362o(4, 0);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static boolean m374c(C0736a aVar) {
        C0736a[] e;
        m364m(false);
        if (!((aVar.f2499d & 1) == 0 || (e = m372e(aVar.f2496a)) == null)) {
            for (C0736a aVar2 : e) {
                m374c(aVar2);
            }
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("del", (Integer) 1);
            contentValues.put("upd", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
            if (f2492b.update("bookmarks", contentValues, "id=" + aVar.f2496a + " AND del=0", null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            //ACRA.getErrorReporter().handleSilentException(e2);
            m364m(true);
            return false;
        }
    }

    public static C0736a[] m373d() {
        int i = 0;
        m364m(false);
        C0736a[] aVarArr = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2494d;
            Cursor query = sQLiteDatabase.query("bookmarks", strArr, "upd>" + m363n(3, 0), null, null, null, null);
            if (query.moveToFirst()) {
                aVarArr = new C0736a[query.getCount()];
                while (true) {
                    int i2 = i + 1;
                    aVarArr[i] = C0736a.m349b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i = i2;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return aVarArr;
    }

    public static C0736a[] m372e(int i) {
        int i2 = 0;
        m364m(false);
        C0736a[] aVarArr = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2494d;
            Cursor query = sQLiteDatabase.query("bookmarks", strArr, "par=" + i + " AND del=0", null, null, null, "ord ASC");
            if (query.moveToFirst()) {
                aVarArr = new C0736a[query.getCount()];
                while (true) {
                    int i3 = i2 + 1;
                    aVarArr[i2] = C0736a.m349b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i2 = i3;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return aVarArr;
    }

    public static int m371f(int i) {
        int i2 = 0;
        m364m(false);
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            Cursor query = sQLiteDatabase.query("bookmarks", new String[]{"MAX(ord)"}, "par=" + i + " and del=0", null, null, null, null);
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return i2;
    }

    public static void m370g(C0736a aVar, boolean z) {
        m364m(false);
        if (z) {
            try {
                aVar.f2497b = (int) (System.currentTimeMillis() / 1000);
                aVar.f2499d &= -3;
            } catch (Exception e) {
                //ACRA.getErrorReporter().handleSilentException(e);
                m364m(true);
                return;
            }
        }
        CRC32 crc32 = new CRC32();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.f2496a));
        contentValues.put("upd", Integer.valueOf(aVar.f2497b));
        contentValues.put("del", Integer.valueOf(aVar.f2498c ? 1 : 0));
        contentValues.put("flg", Integer.valueOf(aVar.f2499d));
        contentValues.put("par", Integer.valueOf(aVar.f2500e));
        contentValues.put("ord", Integer.valueOf(aVar.f2501f));
        contentValues.put("title", aVar.f2502g);
        contentValues.put("url", aVar.f2503h);
        crc32.update(aVar.f2503h.getBytes());
        contentValues.put("crc", Long.valueOf(crc32.getValue()));
        f2492b.insertWithOnConflict("bookmarks", null, contentValues, 5);
        if (z) {
            DocumentManager.syncBookmarks(false);
        }
    }

    public static void m369h(int i, int i2) {
        m364m(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(i2));
            SQLiteDatabase sQLiteDatabase = f2492b;
            sQLiteDatabase.update("bookmarks", contentValues, "id=" + i, null);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("par", Integer.valueOf(i2));
            SQLiteDatabase sQLiteDatabase2 = f2492b;
            sQLiteDatabase2.update("bookmarks", contentValues2, "par=" + i, null);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static void m368i(String str, String str2) {
        int n = m363n(4, 0) + 1;
        m362o(4, n);
        m370g(new C0736a(n, 0, false, str2.equals("folder") ? 1 : 0, 0, m371f(0) + 1, str, str2), true);
        DocumentManager.f2748E.m655c(null);
    }

    public static void m367j(String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("del", (Integer) 1);
            contentValues.put("upd", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            SQLiteDatabase sQLiteDatabase = f2492b;
            sQLiteDatabase.update("bookmarks", contentValues, "crc=" + crc32.getValue() + " AND del=0", null);
        } catch (Exception unused) {
        }
        DocumentManager.f2748E.m655c(null);
    }

    public static boolean m366k(String str) {
        boolean z = false;
        m364m(false);
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2494d;
            Cursor query = sQLiteDatabase.query("bookmarks", strArr, "crc=" + crc32.getValue() + " AND del=0", null, null, null, null);
            z = query.moveToFirst();
            query.close();
            return z;
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
            return z;
        }
    }

    public static void m365l(String str, String str2) {
        if (!m366k(str2)) {
            m368i(str, str2);
        } else {
            m367j(str2);
        }
    }

    private static synchronized void m364m(boolean z) {
        synchronized (DataDB.class) {
            if (z) {
                SQLiteDatabase sQLiteDatabase = f2492b;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    f2492b = null;
                }
                f2491a.delete();
            }
            if (f2492b == null) {
                @SuppressLint("WrongConstant") SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(f2491a.getPath(), null, 268435472);
                f2492b = openDatabase;
                Cursor rawQuery = openDatabase.rawQuery("PRAGMA journal_mode = TRUNCATE", null);
                rawQuery.moveToNext();
                rawQuery.getString(0);
                rawQuery.close();
                f2492b.execSQL("CREATE TABLE IF NOT EXISTS props (id INTEGER PRIMARY KEY, value INTEGER);");
                f2492b.execSQL("CREATE TABLE IF NOT EXISTS unread (id INTEGER PRIMARY KEY, cln INTEGER, utype INTEGER, uid INTEGER, uactive INTEGER, utitle TEXT, aid INTEGER, aname TEXT, uver INTEGER, uverdisc INTEGER, uext1 INTEGER, uext2 INTEGER, uext3 INTEGER, uext4 INTEGER);");
                f2492b.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_unread_ti ON unread(utype, uid);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_unread_ta ON unread(utype, uactive);");
                f2492b.execSQL("CREATE TABLE IF NOT EXISTS bookmarks (id INTEGER PRIMARY KEY, upd INTEGER, del INTEGER, flg INTEGER, par INTEGER, ord INTEGER, title TEXT, url TEXT, crc INTEGER);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_bm_view ON bookmarks(par, del);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_bm_sync ON bookmarks(upd);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_bm_chk ON bookmarks(crc, del);");
                f2492b.execSQL("CREATE TABLE IF NOT EXISTS sitemenu (id INTEGER PRIMARY KEY, tid INTEGER, loc TEXT, title TEXT, hid INTEGER, par INTEGER);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_sm_tid ON sitemenu(tid);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_sm_par ON sitemenu(par);");
                f2492b.execSQL("CREATE TABLE IF NOT EXISTS trackurls (id INTEGER PRIMARY KEY, url TEXT, sum INTEGER);");
                f2492b.execSQL("CREATE INDEX IF NOT EXISTS i_sum ON trackurls(sum);");
                int n = m363n(1, 0);
                if (n == 0) {
                    m362o(1, 3);
                } else if (3 != n && !z) {
                    m364m(true);
                }
            }
        }
    }

    public static int m363n(int i, int i2) {
        m364m(false);
        try {
            Cursor query = f2492b.query("props", new String[]{"value"}, "id=" + i, null, null, null, null);
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return i2;
    }

    public static void m362o(int i, int i2) {
        m364m(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", i);
            contentValues.put("value", i2);
            f2492b.insertWithOnConflict("props", null, contentValues, 5);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static void m361p() {
        m364m(false);
        try {
            f2492b.delete("sitemenu", "", null);
            m362o(5, 0);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static C0737b[] m360q(int i) {
        int i2 = 0;
        m364m(false);
        C0737b[] bVarArr = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2495e;
            Cursor query = sQLiteDatabase.query("sitemenu", strArr, "par=" + i, null, null, null, "id ASC");
            if (query.moveToFirst()) {
                bVarArr = new C0737b[query.getCount()];
                while (true) {
                    int i3 = i2 + 1;
                    bVarArr[i2] = C0737b.m347b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i2 = i3;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return bVarArr;
    }

    public static C0737b m359r(int i) {
        m364m(false);
        C0737b bVar = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2495e;
            Cursor query = sQLiteDatabase.query("sitemenu", strArr, "id=" + i, null, null, null, null);
            if (query.moveToFirst()) {
                bVar = C0737b.m347b(query);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return bVar;
    }

    public static C0737b m358s(int i) {
        m364m(false);
        C0737b bVar = null;
        try {
            SQLiteDatabase sQLiteDatabase = f2492b;
            String[] strArr = f2495e;
            Cursor query = sQLiteDatabase.query("sitemenu", strArr, "tid=" + i, null, null, null, null);
            if (query.moveToFirst()) {
                bVar = C0737b.m347b(query);
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return bVar;
    }

    public static void m357t(C0737b bVar) {
        int i = 0;
        m364m(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(bVar.f2505a));
            contentValues.put("tid", Integer.valueOf(bVar.f2506b));
            contentValues.put("loc", bVar.f2507c);
            contentValues.put("title", bVar.f2508d);
            if (bVar.f2509e) {
                i = 1;
            }
            contentValues.put("hid", Integer.valueOf(i));
            contentValues.put("par", Integer.valueOf(bVar.f2510f));
            f2492b.insertWithOnConflict("sitemenu", null, contentValues, 5);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static void m356u(String str) {
        m364m(false);
        try {
            String lowerCase = str.toLowerCase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("url", lowerCase);
            contentValues.put("sum", Integer.valueOf(lowerCase.hashCode()));
            f2492b.insert("trackurls", null, contentValues);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static boolean m355v(String str) {
        boolean z = false;
        m364m(false);
        try {
            String lowerCase = str.toLowerCase();
            Cursor query = f2492b.query("trackurls", new String[]{"url"}, "sum=" + lowerCase.hashCode(), null, null, null, null);
            if (query.moveToFirst()) {
                while (true) {
                    if (!lowerCase.equals(query.getString(0))) {
                        if (!query.moveToNext()) {
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return z;
    }

    public static void m354w() {
        m364m(false);
        try {
            f2492b.delete("trackurls", "", null);
            m362o(6, 0);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static void m353x() {
        m364m(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cln", (Integer) 1);
            f2492b.update("unread", contentValues, "", null);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static C0738c[] m352y() {
        int i = 0;
        m364m(false);
        C0738c[] cVarArr = null;
        try {
            Cursor query = f2492b.query("unread", f2493c, "cln=1", null, null, null, null);
            if (query.moveToFirst()) {
                cVarArr = new C0738c[query.getCount()];
                while (true) {
                    int i2 = i + 1;
                    cVarArr[i] = C0738c.m345b(query);
                    if (!query.moveToNext()) {
                        break;
                    }
                    i = i2;
                }
            }
            query.close();
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
        return cVarArr;
    }

    public static void m351z() {
        m364m(false);
        try {
            f2492b.delete("unread", "", null);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
            m364m(true);
        }
    }

    public static class C0738c {
        int f2511a;
        int f2512b;
        boolean f2513c;
        String f2514d;
        int f2515e;
        String f2516f;
        int f2517g;
        int f2518h;
        int f2519i;
        int f2520j;
        int f2521k;
        int f2522l;

        public C0738c(int i, int i2, boolean z, String str, int i3, String str2, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.f2511a = i;
            this.f2512b = i2;
            this.f2513c = z;
            this.f2514d = str;
            this.f2515e = i3;
            this.f2516f = str2;
            this.f2517g = i4;
            this.f2518h = i5;
            this.f2519i = i6;
            this.f2520j = i7;
            this.f2521k = i8;
            this.f2522l = i9;
        }

        public static C0738c m345b(Cursor cursor) {
            return new C0738c(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2) != 0, cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11));
        }

        public C0738c(C0738c cVar) {
            this(cVar.f2511a, cVar.f2512b, cVar.f2513c, cVar.f2514d, cVar.f2515e, cVar.f2516f, cVar.f2517g, cVar.f2518h, cVar.f2519i, cVar.f2520j, cVar.f2521k, cVar.f2522l);
        }
    }
}
