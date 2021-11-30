package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;

public class FourpdaService extends IntentService {
    Util.AbstractC0429j<Boolean, Object> f642a = new C0157a();

    class C0157a implements Util.AbstractC0429j<Boolean, Object> {
        C0157a() {
        }

        public Boolean mo222a(Object obj) {
            DocumentManager.f2746C.m656b(FourpdaService.this.f642a);
            FourpdaService.this.stopSelf();
            return Boolean.TRUE;
        }
    }

    public FourpdaService() {
        super("fourpdaservice");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        if (!DocumentManager.isLoggined()) {
            stopSelf();
            return 2;
        }
        DocumentManager.f2746C.m656b(this.f642a);
        DocumentManager.f2746C.m657a(this.f642a);
        int i3 = 0;
        if (intent != null) {
            i3 = intent.getIntExtra("count", 0);
        }
        DocumentManager.restartConnection1(i3 + 1);
        return 2;
    }
}
