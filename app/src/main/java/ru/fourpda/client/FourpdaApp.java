package ru.fourpda.client;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

public class FourpdaApp extends Application {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
      //  try {
           /* ACRA.init(this, new ConfigurationBuilder(this).setHttpMethod(HttpSender.Method.PUT)
                    .setReportType(HttpSender.Type.JSON)
                    .setFormUri("https://acra.4pda.ru/acra-fourpda/_design/acra-storage/_update/report")
                    .setFormUriBasicAuthLogin("report").setFormUriBasicAuthPassword("report")
                    .setExcludeMatchingSharedPreferencesKeys("login_key").build());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e2) {
              e2.printStackTrace();
            }
        }
        Prefs.initPreference(this);
        DataDB.m376a(this);
        DocumentManager.syncBookmarks(this);
        Notify.createNotificationChannel(this);
    }
}
