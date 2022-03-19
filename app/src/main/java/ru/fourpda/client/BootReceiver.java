package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.List;
import java.util.Vector;

public class BootReceiver extends BroadcastReceiver {
    static List<Long> f635a = new Vector();

    @SuppressLint("WrongConstant")
    void m921a(Context context) {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(1, new ComponentName(context, FourpdaJobService.class)).setMinimumLatency(60000).setRequiredNetworkType(1).build());
                z = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (z) {
                Intent intent = new Intent(context.getPackageName());
                intent.putExtra("count", 1);
                ((AlarmManager) context.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + 1000, PendingIntent.getService(context, 0, intent, 268435456));
            }
        }
    }

    @Override
    @SuppressLint({"NewApi"})
    public void onReceive(Context context, Intent intent) {
        Intent intent2;
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                PicoFCM.C0968j z = PicoFCM.m9z(this, intent);
                int b = z.m0b();
                if (b == 1 || b == 2) {
                    new PicoFCM(context.getApplicationContext(), "1:1043483203481:android:43c96e036dc3fe54").m19p();
                } else if (b == 3) {
                    m921a(context);
                } else if (b == 6) {
                    DocumentManager.documentManager.saveUnreadData(z.m1a());
                }
            } else if (action.equals("com.huawei.android.push.intent.REGISTRATION")) {
                MainActivity.m897i(context, PicoHCM.m782u(intent));
            } else if (action.equals("com.huawei.android.push.intent.RECEIVE")) {
                DocumentManager.documentManager.parseUnreadData(PicoHCM.m783t(intent));
            } else if (action.equals("android.intent.action.DOWNLOAD_COMPLETE")) {
                Long valueOf = Long.valueOf(intent.getLongExtra("extra_download_id", 0));
                if (f635a.contains(valueOf)) {
                    @SuppressLint("WrongConstant") Cursor query = ((DownloadManager) context.getSystemService("download")).query(new DownloadManager.Query().setFilterById(valueOf.longValue()));
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("title"));
                        String string2 = query.getString(query.getColumnIndex("local_uri"));
                        int i = query.getInt(query.getColumnIndex("status"));
                        if (8 == i) {
                            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(string2));
                            if (intent3.resolveActivity(context.getPackageManager()) != null) {
                                intent2 = Intent.createChooser(intent3, "Открыть с помощью");
                            } else {
                                intent2 = new Intent("android.intent.action.VIEW_DOWNLOADS");
                            }
                            Notify.createNotify(context, (int) valueOf.longValue(), "4pda-download", false, true, string, "Загрузка завершена", intent2);
                            f635a.remove(valueOf);
                        } else if (16 == i) {
                            Notify.createNotify(context, (int) valueOf.longValue(), "4pda-download", false, true, string, "Ошибка загрузки", new Intent("android.intent.action.VIEW_DOWNLOADS"));
                            f635a.remove(valueOf);
                        }
                    }
                }
            } else if (context.getSharedPreferences("background", 0).getInt("background_mode", 3) != 0) {
                m921a(context);
            } else {
                System.exit(0);
            }
        }
    }
}
