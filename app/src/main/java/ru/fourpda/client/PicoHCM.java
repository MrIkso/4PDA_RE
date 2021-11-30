package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class PicoHCM {
    private static final char[] f1106k = "0123456789ABCDEF".toCharArray();
    private final Context f1107a;
    private final String f1108b;
    private IBinder f1114h;
    private boolean f1113g = false;
    private int f1115i = 1;
    private String f1116j = null;
    private final Handler f1109c = new Handler(Looper.getMainLooper());
    private final ServiceConnectionC0301e f1110d = new ServiceConnectionC0301e(this, null);
    private final Runnable f1111e = new RunnableC0296a();
    private final List<BinderC0299d> f1112f = new Vector(10);

    class RunnableC0296a implements Runnable {
        RunnableC0296a() {
        }

        @Override
        public void run() {
            Log.e("PicoHCM", "Bind timeout");
            synchronized (PicoHCM.this.f1112f) {
                PicoHCM.this.f1113g = false;
                for (BinderC0299d dVar : (BinderC0299d[]) PicoHCM.this.f1112f.toArray(new BinderC0299d[0])) {
                    PicoHCM.this.m778y(dVar, 102, null);
                }
            }
        }
    }

    class C0297b implements AbstractC0298c {
        final SharedPreferences f1118a;
        final AbstractC0298c f1119b;

        C0297b(PicoHCM a1Var, SharedPreferences sharedPreferences, AbstractC0298c cVar) {
            this.f1118a = sharedPreferences;
            this.f1119b = cVar;
        }

        @Override
        public void mo772a(int i, Bundle bundle) {
            if (i == 0 && bundle != null) {
                this.f1118a.edit().putBoolean("hasRequestAgreement", true).commit();
            }
            this.f1119b.mo772a(i, bundle);
        }
    }

    public interface AbstractC0298c {
        void mo772a(int i, Bundle bundle);
    }

    public class BinderC0299d extends Binder implements IInterface {
        private String f1120a;
        private Bundle f1121b;
        private AbstractC0298c f1122c;
        private Runnable f1123d;

        class RunnableC0300a implements Runnable {
            RunnableC0300a(PicoHCM a1Var) {
            }

            @Override
            public void run() {
                BinderC0299d dVar = BinderC0299d.this;
                PicoHCM.this.m778y(dVar, 102, null);
            }
        }

        BinderC0299d(String str, Bundle bundle, AbstractC0298c cVar) {
            attachInterface(this, "com.huawei.hms.core.aidl.IAIDLCallback");
            this.f1120a = str;
            this.f1121b = bundle;
            this.f1122c = cVar;
            this.f1123d = new RunnableC0300a(PicoHCM.this);
        }

        public boolean m773d() {
            Parcel obtain = null;
            try {
                obtain = Parcel.obtain();
                PicoHCM.this.f1109c.postDelayed(this.f1123d, TimeUnit.SECONDS.toMillis(30));
                boolean z = false;
                try {
                    Bundle bundle = new Bundle();
                    bundle.putInt("apiLevel", 0);
                    PicoHCM.this.m803A(bundle, "apiNameList", new String[]{"HuaweiPush.API", "Core.API"});
                    bundle.putString("appId", PicoHCM.this.f1108b);
                    bundle.putString("packageName", PicoHCM.this.f1107a.getPackageName());
                    bundle.putInt("sdkVersion", 40000300);
                    bundle.putString("sessionId", PicoHCM.this.f1116j);
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLInvoke");
                    obtain.writeInt(1);
                    obtain.writeInt(PicoHCM.this.f1115i);
                    obtain.writeString(this.f1120a);
                    obtain.writeBundle(bundle);
                    obtain.writeBundle(this.f1121b);
                    obtain.writeStrongBinder(this);
                    PicoHCM.this.f1114h.transact(2, obtain, null, 1);
                    obtain.recycle();
                    z = true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    obtain.recycle();
                }
                if (!z) {
                    PicoHCM.this.m778y(this, 101, null);
                }
                return z;
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (1 != i) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLCallback");
            Log.i("PicoFCM", "Reply: " + parcel.toString());
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                String readString = parcel.readString();
                Log.i("PicoFCM", "Reply ver=" + readInt + " uri=" + readString);
                Bundle readBundle = parcel.readBundle();
                Bundle readBundle2 = parcel.readBundle();
                if (readBundle != null) {
                    for (String str : readBundle.keySet()) {
                        Log.d("Header", str + " = \"" + readBundle.get(str) + "\"");
                    }
                }
                if (readBundle2 != null) {
                    for (String str2 : readBundle2.keySet()) {
                        Log.d("Reply", str2 + " = \"" + readBundle2.get(str2) + "\"");
                    }
                }
                int i3 = 2;
                if (readBundle != null) {
                    i3 = readBundle.getInt("statusCode", 3);
                }
                PicoHCM.this.m778y(this, i3, readBundle2);
            } else {
                PicoHCM.this.m778y(this, 103, null);
            }
            return true;
        }
    }

    public class ServiceConnectionC0301e implements ServiceConnection {

        class C0302a implements AbstractC0298c {

            class RunnableC0303a implements Runnable {
                final BinderC0299d f1128a;

                RunnableC0303a(C0302a aVar, BinderC0299d dVar) {
                    this.f1128a = dVar;
                }

                @Override
                public void run() {
                    this.f1128a.m773d();
                }
            }

            C0302a() {
            }

            @Override
            public void mo772a(int i, Bundle bundle) {
                if (i == 0 && bundle != null) {
                    PicoHCM.this.f1116j = bundle.getString("sessionId");
                    Object[] v = PicoHCM.this.m781v(bundle, "protocolVersion");
                    if (v != null) {
                        for (Object obj : v) {
                            if ((obj instanceof Integer) && ((Integer) obj).intValue() == 2) {
                                PicoHCM.this.f1115i = 2;
                            }
                        }
                    }
                }
                if (PicoHCM.this.f1116j == null) {
                    synchronized (PicoHCM.this.f1112f) {
                        for (BinderC0299d dVar : (BinderC0299d[]) PicoHCM.this.f1112f.toArray(new BinderC0299d[0])) {
                            PicoHCM.this.m778y(dVar, 104, null);
                        }
                    }
                    return;
                }
                synchronized (PicoHCM.this.f1112f) {
                    for (BinderC0299d dVar2 : PicoHCM.this.f1112f) {
                        PicoHCM.this.f1109c.post(new RunnableC0303a(this, dVar2));
                    }
                }
            }
        }

        private ServiceConnectionC0301e() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PicoHCM.this.f1114h = iBinder;
            PicoHCM.this.f1109c.removeCallbacks(PicoHCM.this.f1111e);
            PicoHCM.this.f1113g = false;
            Log.i("PicoHCM", "connected");
            Bundle bundle = new Bundle();
            PicoHCM.this.m803A(bundle, "apiNameList", new String[]{"HuaweiPush.API", "Core.API"});
            bundle.putString("fingerprint", PicoHCM.m785r(PicoHCM.this.f1107a, PicoHCM.this.f1107a.getPackageName()));
            PicoHCM.this.m803A(bundle, "scopeList", new String[0]);
            bundle.putString("subAppID", null);
            new BinderC0299d("core.connect", bundle, new C0302a()).m773d();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            PicoHCM.this.f1114h = null;
            PicoHCM.this.f1109c.removeCallbacks(PicoHCM.this.f1111e);
            synchronized (PicoHCM.this.f1112f) {
                for (BinderC0299d dVar : (BinderC0299d[]) PicoHCM.this.f1112f.toArray(new BinderC0299d[0])) {
                    PicoHCM.this.m778y(dVar, 105, null);
                }
            }
        }

        ServiceConnectionC0301e(PicoHCM a1Var, RunnableC0296a aVar) {
            this();
        }
    }

    public PicoHCM(Context context, String str) {
        this.f1107a = context;
        this.f1108b = str;
    }

    private static String m788o(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f1106k;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    @SuppressLint({"NewApi"})
    private SharedPreferences m787p(String str) {
        return (Build.VERSION.SDK_INT >= 24 ? this.f1107a.createDeviceProtectedStorageContext() : this.f1107a).getSharedPreferences(str, 0);
    }

    public static String m785r(Context context, String str) {
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                return null;
            }
            return m788o(MessageDigest.getInstance("SHA-256").digest(packageInfo.signatures[0].toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean m784s(Context context) {
        int i;
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
                int applicationEnabledSetting = context.getPackageManager().getApplicationEnabledSetting("com.huawei.hwid");
                i = 3;
                if (packageInfo.versionCode < 30000000) {
                    i = 2;
                } else if (!(applicationEnabledSetting == 2 || applicationEnabledSetting == 3 || applicationEnabledSetting == 4)) {
                    i = !"B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05".equalsIgnoreCase(m785r(context, "com.huawei.hwid")) ? 9 : 0;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                i = 1;
            }
        } else {
            i = 21;
        }
        Log.i("PicoHCM", "Availability status=" + i);
        if (i == 0) {
            return true;
        }
        return false;
    }

    public static String m783t(Intent intent) {
        String stringExtra = intent.getStringExtra("msgIdStr");
        Log.i("PicoHCM", "got message #" + stringExtra);
        String z = m777z(intent.getByteArrayExtra("msg_data"));
        Log.i("PicoHCM", z);
        try {
            if (!z.contains("msgContent")) {
                return z;
            }
            JSONObject jSONObject = new JSONObject(z);
            if (jSONObject.has("msgContent")) {
                return jSONObject.getJSONObject("msgContent").getString("data");
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static String m782u(Intent intent) {
        return m777z(intent.getByteArrayExtra("device_token"));
    }

    @SuppressLint("WrongConstant")
    private void m779x(String str, Bundle bundle, AbstractC0298c cVar) {
        boolean z;
        boolean z2;
        BinderC0299d dVar = new BinderC0299d(str, bundle, cVar);
        synchronized (this.f1112f) {
            this.f1112f.add(dVar);
            z = false;
            if (this.f1114h == null) {
                boolean z3 = !this.f1113g;
                this.f1113g = true;
                z = z3;
                z2 = false;
            } else {
                z2 = true;
            }
        }
        if (z) {
            Log.i("PicoHCM", "binding");
            Intent intent = new Intent("com.huawei.hms.core.aidlservice");
            intent.setPackage("com.huawei.hwid");
            this.f1109c.postDelayed(this.f1111e, TimeUnit.SECONDS.toMillis(10));
            this.f1107a.bindService(intent, this.f1110d, 1);
        }
        if (z2) {
            dVar.m773d();
        }
    }

    public void m778y(BinderC0299d dVar, int i, Bundle bundle) {
        this.f1109c.removeCallbacks(dVar.f1123d);
        synchronized (this.f1112f) {
            this.f1112f.remove(dVar);
        }
        if (dVar.f1122c != null) {
            dVar.f1122c.mo772a(i, bundle);
        }
        synchronized (this.f1112f) {
            if (this.f1112f.size() == 0) {
                Log.i("PicoHCM", "unbinding");
                this.f1114h = null;
                this.f1107a.unbindService(this.f1110d);
            }
        }
    }

    private static String m777z(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new String(bArr);
        }
    }

    void m803A(Bundle bundle, String str, Object[] objArr) {
        Bundle bundle2 = new Bundle();
        bundle.putBundle(str, bundle2);
        bundle2.putInt("_val_type_", 1);
        int i = 0;
        if (this.f1115i == 2) {
            bundle2.putInt("_list_size_", objArr.length);
            while (i < objArr.length) {
                Object obj = objArr[i];
                if (obj instanceof String) {
                    bundle2.putString("_list_item_" + i, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle2.putInt("_list_item_" + i, ((Integer) obj).intValue());
                }
                i++;
            }
            return;
        }
        int length = objArr.length;
        while (i < length) {
            Object obj2 = objArr[i];
            Bundle bundle3 = new Bundle();
            if (obj2 instanceof String) {
                bundle3.putString("_value_", (String) obj2);
            } else if (obj2 instanceof Integer) {
                bundle3.putInt("_value_", ((Integer) obj2).intValue());
            } else {
                i++;
            }
            bundle2.putBundle("_next_item_", bundle3);
            bundle2 = bundle3;
            i++;
        }
    }

    String m786q() {
        SharedPreferences p = m787p("aaid");
        if (!p.contains("aaid")) {
            SharedPreferences.Editor edit = p.edit();
            edit.putString("aaid", UUID.randomUUID().toString());
            edit.putLong("creationTime", System.currentTimeMillis());
            edit.commit();
        }
        return p.getString("aaid", "");
    }

    Object[] m781v(Bundle bundle, String str) {
        int i;
        Bundle bundle2 = bundle.getBundle(str);
        if (1 != bundle2.getInt("_val_type_")) {
            return null;
        }
        int i2 = 0;
        if (2 != this.f1115i || (i = bundle2.getInt("_list_size_", -1)) < 0) {
            Bundle bundle3 = bundle2;
            int i3 = 0;
            while (bundle3.containsKey("_next_item_")) {
                bundle3 = bundle3.getBundle("_next_item_");
                i3++;
            }
            Object[] objArr = new Object[i3];
            while (bundle2.containsKey("_next_item_")) {
                bundle2 = bundle2.getBundle("_next_item_");
                objArr[i2] = bundle2.get("_value_");
                i2++;
            }
            return objArr;
        }
        Object[] objArr2 = new Object[i];
        while (i2 < i) {
            objArr2[i2] = bundle2.get("_list_item_" + i2);
            i2++;
        }
        return objArr2;
    }

    public void m780w(AbstractC0298c cVar) {
        SharedPreferences sharedPreferences = this.f1107a.getSharedPreferences("push_client_self_info", 0);
        boolean contains = sharedPreferences.contains("hasRequestAgreement");
        Bundle bundle = new Bundle();
        bundle.putString("aaid", m786q());
        bundle.putString("appId", this.f1108b);
        bundle.putBoolean("firstTime", !contains);
        bundle.putString("packageName", this.f1107a.getPackageName());
        bundle.putString("scope", "HCM");
        m779x("push.gettoken", bundle, new C0297b(this, sharedPreferences, cVar));
    }
}
