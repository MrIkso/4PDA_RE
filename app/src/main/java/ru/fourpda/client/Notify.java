package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

public class Notify {
    public static void m48a(Context context, int i, String str) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(str, i);
        } catch (Exception unused) {
        }
    }

    @SuppressLint({"NewApi"})
    public static void m47b(Context context, int i, String str, boolean z, boolean z2, String str2, String str3, Intent intent) {
        Notification notification;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.cancel(str, i);
        String c = Util.C0427h.UnEscapeString(str2);
        String c2 = Util.C0427h.UnEscapeString(str3);
        PendingIntent activity = PendingIntent.getActivity(context, i, intent, 0);
        int i2 = z ? (Prefs.f1172j || !Prefs.f1171i) ? 4 : 6 : 0;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 11) {
            notification = new Notification(R.drawable.notify, c2, System.currentTimeMillis());
            try {
                notification.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, c, c2, activity);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            }
            if (z && !Prefs.f1172j && !TextUtils.isEmpty(Prefs.f1170h)) {
                notification.sound = Uri.parse(Prefs.f1170h);
            }
            notification.defaults = i2;
            if (z2) {
                notification.flags |= 16;
            }
        } else {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentIntent(activity);
            builder.setSmallIcon(R.drawable.notify);
            builder.setContentText(c2);
            builder.setContentTitle(c);
            builder.setAutoCancel(z2);
            if (i3 >= 17) {
                builder.setShowWhen(true);
            }
            if (i3 >= 26) {
                String str4 = str.equals("4pda-qms") ? "4pda-qms-group" : str;
                if (str4.equals("4pda-forum") || str4.equals("4pda-topic") || str4.equals("4pda-pinupd")) {
                    str4 = "4pda-fav-group";
                }
                if (str4.equals("4pda-mention-forum") || str4.equals("4pda-mention-site")) {
                    str4 = "4pda-mention-group";
                }
                builder.setChannelId(str4);
                builder.setOnlyAlertOnce(!z);
                builder.setTicker(c2);
            } else {
                if (z && !Prefs.f1172j) {
                    builder.setTicker(c2);
                    if (!TextUtils.isEmpty(Prefs.f1170h)) {
                        builder.setSound(Uri.parse(Prefs.f1170h));
                    }
                }
                builder.setDefaults(i2);
            }
            notification = i3 < 16 ? builder.getNotification() : builder.build();
        }
        notificationManager.notify(str, i, notification);
    }

    public static void m46c(Context context, int i, String str, boolean z, boolean z2, String str2, String str3, Uri uri) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        intent.putExtra("fromNotification", true);
        m47b(context, i, str, z, z2, str2, str3, intent);
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            NotificationChannel notificationChannel = new NotificationChannel("4pda-qms-group", "QMS", 4);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationChannel notificationChannel2 = new NotificationChannel("4pda-fav-group", "Избранное", 3);
            notificationChannel2.enableVibration(true);
            notificationChannel2.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel2);
            NotificationChannel notificationChannel3 = new NotificationChannel("4pda-mention-group", "Упоминания", 4);
            notificationChannel3.enableVibration(true);
            notificationChannel3.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel3);
        }
    }
}
