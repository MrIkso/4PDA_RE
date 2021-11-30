package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

class PicoFCM {
    static Queue<String> f3200t;
    private final Context f3201a;
    private final Handler f3202b = new Handler(Looper.getMainLooper());
    private final String f3203c;
    private final String f3204d;
    private String f3205e;
    private String f3206f;
    private int f3207g;
    private int f3208h;
    private int f3209i;
    private final SparseArray<C0966i> f3210j;
    private Messenger f3211k;
    private Messenger f3212l;
    private C0963h f3213m;
    private Messenger f3214n;
    private Messenger f3215o;
    private C0963h f3216p;
    private ServiceConnectionC0969k f3217q;
    private Runnable f3218r;
    private boolean f3219s;

    public class HandlerC0956a extends Handler {
        HandlerC0956a(Looper looper) {
            super(looper);
        }

        @Override
        public final void handleMessage(Message message) {
            PicoFCM.this.m16s(message);
        }
    }

    class RunnableC0957b implements Runnable {
        RunnableC0957b() {
        }

        @Override
        public void run() {
            Log.w("PicoFCM", "ServiceConnTimeoutV2: Falling back to V1");
            PicoFCM.this.m43B();
        }
    }

    class HandlerC0958c extends Handler {
        HandlerC0958c(Looper looper) {
            super(looper);
        }

        @Override
        public final void handleMessage(Message message) {
            PicoFCM.this.m15t(message);
        }
    }

    class C0959d implements AbstractC0961f {
        final AbstractC0961f f3223a;

        C0959d(AbstractC0961f fVar) {
            this.f3223a = fVar;
        }

        @Override
        public void mo8a(int i, Bundle bundle) {
            String string = bundle.getString("registration_id");
            if (string != null) {
                PicoFCM.this.m35J(string);
            }
            this.f3223a.mo8a(i, bundle);
        }
    }

    public class C0960e implements AbstractC0961f {
        final C0966i f3225a;
        final AbstractC0961f f3226b;
        final Bundle f3227c;

        C0960e(C0966i iVar, AbstractC0961f fVar, Bundle bundle) {
            this.f3225a = iVar;
            this.f3226b = fVar;
            this.f3227c = bundle;
        }

        @Override
        public void mo8a(int i, Bundle bundle) {
            if (bundle == null || bundle.containsKey("google.messenger")) {
                this.f3225a.f3235b = this.f3226b;
                PicoFCM.this.f3210j.put(i, this.f3225a);
                PicoFCM.this.f3202b.postDelayed(this.f3225a.f3236c, TimeUnit.SECONDS.toMillis(30));
                PicoFCM.this.m41D(i, this.f3227c);
                return;
            }
            this.f3226b.mo8a(i, bundle);
        }
    }

    public interface AbstractC0961f {
        void mo8a(int i, Bundle bundle);
    }

    public static class C0962g {
        private String f3229a;
        private long f3230b;
        private String f3231c;

        C0962g(String str, long j, String str2, HandlerC0956a aVar) {
            this(str, j, str2);
        }

        public String m5c() {
            return this.f3229a;
        }

        private C0962g(String str, long j, String str2) {
            this.f3229a = str;
            this.f3230b = j;
            this.f3231c = str2;
        }
    }

    public static class C0963h implements Parcelable {
        public static final Parcelable.Creator<C0963h> CREATOR = new C0964a(null);
        private Messenger f3232a;
        private IBinder f3233b;

        private static class C0964a implements Parcelable.Creator<C0963h> {
            private C0964a() {
            }

            public final C0963h createFromParcel(Parcel parcel) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    return new C0963h(readStrongBinder);
                }
                return null;
            }

            public final C0963h[] newArray(int i) {
                return new C0963h[i];
            }

            C0964a(HandlerC0956a aVar) {
                this();
            }
        }

        public static final class C0965b extends ClassLoader {
            C0965b() {
            }

            @Override
            public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                if ("com.google.android.gms.iid.MessengerCompat".equals(str)) {
                    return C0963h.class;
                }
                return super.loadClass(str, z);
            }
        }

        C0963h(IBinder iBinder) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f3232a = new Messenger(iBinder);
            } else {
                this.f3233b = iBinder;
            }
        }

        final void m4a(Message message) throws RemoteException {
            if (this.f3233b != null) {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
                obtain.writeInt(1);
                message.writeToParcel(obtain, 0);
                try {
                    this.f3233b.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            } else {
                Messenger messenger = this.f3232a;
                if (messenger != null) {
                    messenger.send(message);
                }
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof C0963h) {
                C0963h hVar = (C0963h) obj;
                if (this.f3233b == hVar.f3233b && this.f3232a.getBinder() == hVar.f3232a.getBinder()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            IBinder iBinder = this.f3233b;
            if (iBinder == null) {
                iBinder = this.f3232a.getBinder();
            }
            return iBinder.hashCode();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            Messenger messenger = this.f3232a;
            if (messenger != null) {
                parcel.writeStrongBinder(messenger.getBinder());
            } else {
                parcel.writeStrongBinder(this.f3233b);
            }
        }
    }

    public class C0966i {
        Bundle f3234a;
        AbstractC0961f f3235b;
        Runnable f3236c;

        class RunnableC0967a implements Runnable {
            final int f3238a;

            RunnableC0967a(PicoFCM z0Var, int i) {
                this.f3238a = i;
            }

            @Override
            public void run() {
                PicoFCM.this.m36I(this.f3238a);
            }
        }

        C0966i(int i, Bundle bundle, AbstractC0961f fVar) {
            this.f3234a = bundle;
            this.f3235b = fVar;
            this.f3236c = new RunnableC0967a(PicoFCM.this, i);
        }
    }

    static class C0968j {
        private int f3240a;
        private Bundle f3241b;

        C0968j(int i, Bundle bundle) {
            this.f3240a = i;
            this.f3241b = bundle;
        }

        public Bundle m1a() {
            return this.f3241b;
        }

        public int m0b() {
            return this.f3240a;
        }
    }

    public class ServiceConnectionC0969k implements ServiceConnection {
        private ServiceConnectionC0969k() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PicoFCM.this.f3202b.removeCallbacks(PicoFCM.this.f3218r);
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("android.os.IMessenger".equals(interfaceDescriptor)) {
                    PicoFCM.this.f3215o = new Messenger(iBinder);
                } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
                    PicoFCM.this.f3216p = new C0963h(iBinder);
                } else {
                    Log.w("PicoFCM", "ServiceConnV2: Unknown interface descriptor " + interfaceDescriptor);
                    return;
                }
                synchronized (PicoFCM.this.f3210j) {
                    for (int i = 0; i < PicoFCM.this.f3210j.size(); i++) {
                        PicoFCM z0Var = PicoFCM.this;
                        z0Var.m37H(z0Var.f3210j.keyAt(i), ((C0966i) PicoFCM.this.f3210j.valueAt(i)).f3234a);
                    }
                }
            } catch (RemoteException e) {
                Log.w("PicoFCM", "ServiceConnV2: Exception in onServiceConnected " + e.getMessage() + ", falling back to V1");
                PicoFCM.this.m43B();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            PicoFCM.this.f3202b.removeCallbacks(PicoFCM.this.f3218r);
            PicoFCM.this.f3215o = null;
            PicoFCM.this.f3216p = null;
            Bundle o = PicoFCM.this.m20o("Service disconnected");
            synchronized (PicoFCM.this.f3210j) {
                for (int size = PicoFCM.this.f3210j.size() - 1; size >= 0; size--) {
                    PicoFCM z0Var = PicoFCM.this;
                    z0Var.m39F(z0Var.f3210j.keyAt(size), o);
                }
            }
        }

        ServiceConnectionC0969k(PicoFCM z0Var, HandlerC0956a aVar) {
            this();
        }
    }

    @SuppressLint("WrongConstant")
    public PicoFCM(Context context, String str) {
        this.f3208h = 0;
        this.f3201a = context;
        this.f3203c = str;
        int i = 1;
        this.f3204d = str.startsWith("1:") ? str.split(":")[1] : str;
        this.f3210j = new SparseArray<>(10);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f3205e = Integer.toString(packageInfo.versionCode);
            this.f3206f = packageInfo.versionName;
            this.f3207g = context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        PackageManager packageManager = this.f3201a.getPackageManager();
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == 0) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 26) {
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    this.f3208h = 1;
                }
            }
            if (this.f3208h == 0) {
                Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
                intent2.setPackage("com.google.android.gms");
                List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
                if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
                    this.f3208h = 2;
                }
            }
            if (this.f3208h == 0) {
                this.f3208h = i2 >= 26 ? 2 : i;
            }
        }
        this.f3211k = new Messenger(new HandlerC0956a(Looper.getMainLooper()));
        if (this.f3207g >= 12000000) {
            this.f3217q = new ServiceConnectionC0969k(this, null);
            this.f3218r = new RunnableC0957b();
            this.f3214n = new Messenger(new HandlerC0958c(Looper.getMainLooper()));
        }
    }

    public void m43B() {
        synchronized (this.f3210j) {
            if (!this.f3219s) {
                this.f3219s = true;
                try {
                    this.f3202b.removeCallbacks(this.f3218r);
                    this.f3201a.unbindService(this.f3217q);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int size = this.f3210j.size() - 1; size >= 0; size--) {
                int keyAt = this.f3210j.keyAt(size);
                C0966i valueAt = this.f3210j.valueAt(size);
                AbstractC0961f fVar = valueAt.f3235b;
                valueAt.f3235b = null;
                m39F(keyAt, null);
                m42C(keyAt, valueAt.f3234a, fVar);
            }
        }
    }

    private int m42C(int i, Bundle bundle, AbstractC0961f fVar) {
        bundle.putString("scope", "*");
        bundle.putString("sender", this.f3204d);
        bundle.putString("subtype", this.f3204d);
        bundle.putString("appid", m18q(true).m5c());
        bundle.putString("gmp_app_id", this.f3203c);
        bundle.putString("gmsv", Integer.toString(this.f3207g));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.f3205e);
        bundle.putString("app_ver_name", this.f3206f);
        bundle.putString("cliv", "fiid-20.0.2");
        if (i == 0) {
            i = m10y();
        }
        C0966i iVar = new C0966i(i, bundle, fVar);
        this.f3210j.put(i, iVar);
        this.f3202b.postDelayed(iVar.f3236c, TimeUnit.SECONDS.toMillis(30));
        if (this.f3207g >= 12000000 && !this.f3219s) {
            try {
                m40E(i, bundle);
                return i;
            } catch (Exception e) {
                Log.w("PicoFCM", "doRequest: Exception in reqPerformV2 " + e.getMessage() + ", falling back to V1");
            }
        }
        if (this.f3208h != 0) {
            iVar.f3235b = new C0960e(iVar, fVar, bundle);
            try {
                m41D(i, bundle);
                return i;
            } catch (Exception e2) {
                Log.w("PicoFCM", "doRequest: Exception in reqPerformV1 " + e2.getMessage());
            }
        }
        m39F(i, m20o("No working IID service"));
        return -1;
    }

    public int m41D(int i, Bundle bundle) {
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.f3208h == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        intent.putExtra("kid", "|ID|" + i + "|");
        intent.putExtra("google.messenger", this.f3211k);
        if (!(this.f3212l == null && this.f3213m == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                Messenger messenger = this.f3212l;
                if (messenger != null) {
                    messenger.send(obtain);
                } else {
                    this.f3213m.m4a(obtain);
                }
                return i;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (this.f3208h == 2) {
            this.f3201a.sendBroadcast(intent);
        } else {
            this.f3201a.startService(intent);
        }
        return i;
    }

    @SuppressLint("WrongConstant")
    private int m40E(int i, Bundle bundle) {
        if (this.f3215o == null && this.f3216p == null) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            this.f3201a.bindService(intent, this.f3217q, 1);
            this.f3202b.postDelayed(this.f3218r, TimeUnit.SECONDS.toMillis(10));
        } else {
            try {
                m37H(i, bundle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public void m39F(int i, Bundle bundle) {
        C0966i iVar;
        synchronized (this.f3210j) {
            iVar = this.f3210j.get(i);
            this.f3210j.remove(i);
        }
        if (iVar != null) {
            this.f3202b.removeCallbacks(iVar.f3236c);
            AbstractC0961f fVar = iVar.f3235b;
            if (fVar != null) {
                fVar.mo8a(i, bundle);
            }
        }
    }

    private void m38G(String str, Bundle bundle) {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (Exception unused) {
            i = 0;
        }
        if (i <= 0) {
            Log.w("PicoFCM", "requestResultV1: Invalid requestId " + str);
            return;
        }
        m39F(i, bundle);
    }

    public void m37H(int i, Bundle bundle) throws RemoteException {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = i;
        obtain.replyTo = this.f3214n;
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("oneWay", false);
        bundle2.putString("pkg", this.f3201a.getPackageName());
        bundle2.putBundle("data", bundle);
        obtain.setData(bundle2);
        Messenger messenger = this.f3215o;
        if (messenger != null) {
            messenger.send(obtain);
            return;
        }
        C0963h hVar = this.f3216p;
        if (hVar != null) {
            hVar.m4a(obtain);
        }
    }

    public void m36I(int i) {
        C0966i iVar;
        AbstractC0961f fVar;
        synchronized (this.f3210j) {
            iVar = this.f3210j.get(i);
            this.f3210j.remove(i);
        }
        if (iVar != null && (fVar = iVar.f3235b) != null) {
            fVar.mo8a(i, m20o("Operation timed out"));
        }
    }

    public void m35J(String str) {
        SharedPreferences.Editor edit = this.f3201a.getSharedPreferences("com.google.android.gms.appid", 0).edit();
        String str2 = "|T|" + this.f3204d + "|*";
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", str);
                jSONObject.put("appVersion", this.f3205e);
                jSONObject.put("timestamp", System.currentTimeMillis());
                edit.putString(str2, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            edit.remove(str2);
        }
        edit.commit();
    }

    public Bundle m20o(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("error", str);
        return bundle;
    }

    public void m16s(Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new C0963h.C0965b());
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof C0963h) {
                    this.f3213m = (C0963h) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.f3212l = (Messenger) parcelableExtra;
                }
                if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("registration_id");
                    if (stringExtra == null) {
                        stringExtra = intent.getStringExtra("unregistered");
                    }
                    if (stringExtra != null) {
                        Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                        if (matcher.matches()) {
                            Bundle extras = intent.getExtras();
                            extras.putString("registration_id", matcher.group(2));
                            m38G(matcher.group(1), extras);
                            return;
                        }
                        Log.w("PicoFCM", "incomingMessageV1: Invalid token string " + stringExtra);
                        return;
                    }
                    String stringExtra2 = intent.getStringExtra("error");
                    if (stringExtra2 == null) {
                        Log.w("PicoFCM", "incomingMessageV1: Empty extras");
                        return;
                    }
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (split.length <= 2 || !"ID".equals(split[1])) {
                            Log.w("PicoFCM", "incomingMessageV1: Unknown error structure " + stringExtra2);
                            return;
                        }
                        String str = split[3];
                        if (str.startsWith(":")) {
                            str = str.substring(1);
                        }
                        m38G(split[2], intent.putExtra("error", str).getExtras());
                    }
                    synchronized (this.f3210j) {
                        Bundle extras2 = intent.getExtras();
                        for (int size = this.f3210j.size() - 1; size >= 0; size--) {
                            m39F(this.f3210j.keyAt(size), extras2);
                        }
                    }
                    return;
                }
                Log.w("PicoFCM", "incomingMessageV1: Unknown action " + intent.getAction());
                return;
            }
        }
        Log.w("PicoFCM", "incomingMessageV1: Message without intent");
    }

    public void m15t(Message message) {
        Bundle data = message.getData();
        m39F(message.arg1, data.getBoolean("unsupported", false) ? m20o("Request not supported") : data.getBundle("data"));
        synchronized (this.f3210j) {
            if (this.f3210j.size() < 1) {
                this.f3201a.unbindService(this.f3217q);
            }
        }
    }

    @SuppressLint("WrongConstant")
    public static boolean m14u(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 0);
            if (packageInfo == null || packageInfo.versionCode <= 0) {
                return false;
            }
            if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m13v(C0962g gVar) {
        return gVar.f3230b < System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7);
    }

    private static String m11x(PublicKey publicKey) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(publicKey.getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("PicoFCM", "No SHA1? Am I running on a toaster?");
            return null;
        }
    }

    private synchronized int m10y() {
        int i;
        i = this.f3209i + 1;
        this.f3209i = i;
        return i;
    }

    public static C0968j m9z(BroadcastReceiver broadcastReceiver, Intent intent) {
        Bundle bundle;
        int i = 0;
        if (intent == null || !"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            bundle = null;
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (parcelableExtra instanceof Intent) {
                intent = (Intent) parcelableExtra;
            }
            if ("google.com/iid".equals(intent.getStringExtra("from"))) {
                String stringExtra = intent.getStringExtra("CMD");
                if (stringExtra != null) {
                    Log.d("PicoFCM", "onReceive: got IID command " + stringExtra);
                    if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                        i = 1;
                    } else if ("SYNC".equals(stringExtra)) {
                        i = 2;
                    }
                }
            } else {
                if (f3200t == null) {
                    f3200t = new ArrayDeque(10);
                }
                String stringExtra2 = intent.getStringExtra("google.message_id");
                String stringExtra3 = intent.getStringExtra("message_type");
                String stringExtra4 = intent.getStringExtra("gcm.rawData64");
                if (stringExtra4 != null) {
                    intent.putExtra("rawData", Base64.decode(stringExtra4, 0));
                    intent.removeExtra("gcm.rawData64");
                }
                if (!TextUtils.isEmpty(stringExtra2) && f3200t.contains(stringExtra2)) {
                    Log.d("PicoFCM", "onReceive got a duplicate message " + stringExtra2);
                    i = 7;
                } else if ("deleted_messages".equals(stringExtra3)) {
                    i = 3;
                } else if ("send_error".equals(stringExtra3)) {
                    i = 4;
                } else {
                    i = "send_event".equals(stringExtra3) ? 5 : 6;
                }
                if (!TextUtils.isEmpty(stringExtra2) && !f3200t.contains(stringExtra2)) {
                    if (f3200t.size() >= 10) {
                        f3200t.remove();
                    }
                    f3200t.add(stringExtra2);
                }
            }
            bundle = intent.getExtras();
        }
        if (broadcastReceiver.isOrderedBroadcast()) {
            broadcastReceiver.setResultCode(-1);
        }
        return new C0968j(i, bundle);
    }

    public int m44A(AbstractC0961f fVar) {
        return m42C(0, new Bundle(), new C0959d(fVar));
    }

    public void m19p() {
        m35J(null);
    }

    C0962g m18q(boolean z) {
        SharedPreferences sharedPreferences = this.f3201a.getSharedPreferences("com.google.android.gms.appid", 0);
        byte[] bArr = null;
        String string = sharedPreferences.getString("|S|id", null);
        if (string == null && string != null) {
            try {
                string = m11x(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(string, 8))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(string)) {
            try {
                return new C0962g(string, Long.parseLong(sharedPreferences.getString("|S|cre", "0")), null, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (z) {
            try {
                KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
                instance.initialize(2048);
                bArr = MessageDigest.getInstance("SHA1").digest(instance.generateKeyPair().getPublic().getEncoded());
            } catch (Exception e3) {
                e3.printStackTrace();
                z = false;
            }
        }
        if (!z) {
            bArr = new byte[8];
            new Random().nextBytes(bArr);
        }
        bArr[0] = (byte) ((bArr[0] & 15) + 112);
        String encodeToString = Base64.encodeToString(bArr, 0, 8, 11);
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("|S|id", encodeToString);
        edit.putString("|S|cre", String.valueOf(currentTimeMillis));
        edit.commit();
        return new C0962g(encodeToString, currentTimeMillis, null, null);
    }

    public C0962g m17r() {
        SharedPreferences sharedPreferences = this.f3201a.getSharedPreferences("com.google.android.gms.appid", 0);
        String string = sharedPreferences.getString("|T|" + this.f3204d + "|*", null);
        if (string != null && string.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                return new C0962g(jSONObject.getString("token"), jSONObject.getLong("timestamp"), jSONObject.getString("appVersion"), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean m12w(C0962g gVar) {
        return gVar != null && this.f3205e.equals(gVar.f3231c) && !m13v(gVar);
    }
}
