package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;
import java.util.Vector;
import java.util.zip.ZipFile;

public class DocumentManager extends Network {
    static DocumentManager documentManager;
    static Util.C0420b<Integer> f2754y = new Util.C0420b<>();
    static Util.C0420b<Integer> f2755z = new Util.C0420b<>();
    static Util.C0420b<Object> f2744A = new Util.C0420b<>();
    static Util.C0420b<String> f2745B = new Util.C0420b<>();
    static Util.C0420b<Object> f2746C = new Util.C0420b<>();
    static Util.C0420b<MemberInfoModel> f2747D = new Util.C0420b<>();
    static Util.C0420b<Object> f2748E = new Util.C0420b<>();
    static Util.C0420b<Object> f2749F = new Util.C0420b<>();
    static Util.C0421c<Integer, Integer> f2750G = new Util.C0421c<>();
    static Util.C0421c<Integer, Integer> f2751H = new Util.C0421c<>();
    static Util.C0422d<Integer, Integer, Object> f2752I = new Util.C0422d<>();
    final SparseArray<IGenerateRequest> requestSparseArray = new SparseArray<>(50);
    Context context;
    MessageHandler messageHandler;
    MemberInfoModel memberInfoModel;
    Unread2 unread2;
    BroadcastReceiver broadcastReceiver;
    int conStatus = 0;
    int conStatus1 = 3;
    int memberId = 0;

    int f2766v = 0;
    private int errorStatusCode = 0;
    private int f2767w = 0;

    private DocumentManager(Context context) {
        this.context = context;
        this.messageHandler = new MessageHandler(this.context.getMainLooper());
        MyBrodcastReceiver cVar = new MyBrodcastReceiver(this);
        this.broadcastReceiver = cVar;
        this.context.registerReceiver(cVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        initUnread();
    }

    public static long getStartTime() {
        return documentManager.getStartNETtime();
    }

    public static MemberInfoModel getMemberInfoModel() {
        if (isLoggined()) {
            return documentManager.memberInfoModel;
        }
        return null;
    }

    public static void syncBookmarks(Context context) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        DocumentManager vVar = new DocumentManager(context);
        documentManager = vVar;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(vVar.context);
        String str10 = "_param3";
        String str11 = "_param2";
        int i = 0;
        if (!defaultSharedPreferences.getBoolean("sync_bookmarks", true)) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.remove("sync_bookmarks");
            edit.commit();
            int i2 = defaultSharedPreferences.getInt("links_count", 0);
            int i3 = 0;
            while (i3 < i2) {
                int i4 = defaultSharedPreferences.getInt("link_" + i3 + "_type", i);
                String string = defaultSharedPreferences.getString("link_" + i3 + "_name", "");
                int i5 = defaultSharedPreferences.getInt("link_" + i3 + "_param1", i);
                int i6 = defaultSharedPreferences.getInt("link_" + i3 + str11, i);
                String string2 = defaultSharedPreferences.getString("link_" + i3 + str10, "");
                if (1 == i4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("forum/index.php?showtopic=");
                    sb.append(i5);
                    if (i6 != 0) {
                        str9 = "&st=" + i6;
                    } else {
                        str9 = "";
                    }
                    sb.append(str9);
                    string2 = sb.toString();
                } else if (2 == i4) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("forum/index.php?showforum=");
                    sb2.append(i5);
                    if (i6 != 0) {
                        str8 = "&st=" + i6;
                    } else {
                        str8 = "";
                    }
                    sb2.append(str8);
                    string2 = sb2.toString();
                } else {
                    str = str11;
                    if (3 == i4) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("forum/index.php?act=qms&t=");
                        sb3.append(i5);
                        if (!TextUtils.isEmpty(string2)) {
                            str7 = "&search=" + string2;
                        } else {
                            str7 = "";
                        }
                        sb3.append(str7);
                        string2 = sb3.toString();
                    } else if (4 == i4) {
                        string2 = "?p=" + i5;
                    } else if (5 == i4) {
                        StringBuilder sb4 = new StringBuilder();
                        if (i6 != 0) {
                            str6 = "tag/" + i6 + "/";
                        } else {
                            str6 = "";
                        }
                        sb4.append(str6);
                        sb4.append("page/1");
                        string2 = sb4.toString();
                    } else if (6 == i4) {
                        string2 = "forum/index.php?act=app-options";
                    } else if (7 == i4) {
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("forum/index.php?act=qms");
                        if (!TextUtils.isEmpty(string2)) {
                            str5 = "&search=" + string2;
                        } else {
                            str5 = "";
                        }
                        sb5.append(str5);
                        string2 = sb5.toString();
                    } else if (8 == i4) {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("forum/index.php?act=fav");
                        if (i5 != 0) {
                            str4 = "&st=" + i5;
                        } else {
                            str4 = "";
                        }
                        sb6.append(str4);
                        string2 = sb6.toString();
                    } else if (9 == i4) {
                        string2 = "forum/index.php?showuser=" + i5;
                    } else if (10 == i4) {
                        string2 = "forum/index.php?act=idx";
                    } else if (11 == i4) {
                        string2 = "devdb/" + string2;
                    } else if (12 == i4) {
                        string2 = "devdb/" + string2.replace(':', '/');
                    } else if (13 == i4) {
                        string2 = "devdb/" + string2;
                    } else if (14 == i4) {
                        string2 = "forum/index.php?act=announce&st=" + i5;
                    } else if (15 == i4) {
                        string2 = "forum/index.php?act=app-skins";
                    } else if (16 == i4) {
                        string2 = "forum/index.php?act=mentions";
                    } else if (17 == i4) {
                        string2 = "forum/index.php?showtopic=" + i6 + "&view=findpost&p=" + i5;
                    } else if (18 == i4) {
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append("forum/index.php?act=ticket");
                        if (i5 != 0) {
                            str2 = "&st=" + i5;
                        } else {
                            str2 = "";
                        }
                        sb7.append(str2);
                        if (i6 != 0) {
                            str3 = "&only-topic=" + i6;
                        } else {
                            str3 = "";
                        }
                        sb7.append(str3);
                        string2 = sb7.toString();
                    } else if (19 == i4) {
                        string2 = "forum/index.php?act=ticket&s=thread&t_id=" + i5;
                    } else if (20 == i4) {
                        string2 = "forum/index.php?act=history";
                    } else if (21 != i4) {
                        string2 = null;
                    }
                    DataDB.m368i(string, string2);
                    i3++;
                    i2 = i2;
                    str10 = str10;
                    str11 = str;
                    i = 0;
                }
                str = str11;
                DataDB.m368i(string, string2);
                i3++;
                i2 = i2;
                str10 = str10;
                str11 = str;
                i = 0;
            }
        }
        int i7 = defaultSharedPreferences.getInt("links_count", 0);
        if (i7 > 0) {
            SharedPreferences.Editor edit2 = defaultSharedPreferences.edit();
            for (int i8 = 0; i8 < i7; i8++) {
                edit2.remove("link_" + i8 + "_type");
                edit2.remove("link_" + i8 + "_name");
                edit2.remove("link_" + i8 + "_param1");
                edit2.remove("link_" + i8 + str11);
                edit2.remove("link_" + i8 + str10);
            }
            edit2.remove("links_count");
            edit2.commit();
        }
        int i9 = defaultSharedPreferences.getInt("unread_qms_count", 0);
        if (i9 > 0) {
            SharedPreferences.Editor edit3 = defaultSharedPreferences.edit();
            for (int i10 = 0; i10 < i9; i10++) {
                edit3.remove("unread_qms_id_" + i10);
                edit3.remove("unread_qms_msg_" + i10);
                edit3.remove("unread_qms_reset_" + i10);
                edit3.remove("unread_qms_signal_" + i10);
                edit3.remove("unread_qms_count_" + i10);
            }
            edit3.remove("unread_qms_count");
            edit3.commit();
        }
        int i11 = defaultSharedPreferences.getInt("unread_fav_count", 0);
        if (i11 > 0) {
            SharedPreferences.Editor edit4 = defaultSharedPreferences.edit();
            for (int i12 = 0; i12 < i11; i12++) {
                edit4.remove("unread_fav_id_" + i12);
                edit4.remove("unread_fav_time_" + i12);
                edit4.remove("unread_fav_type_" + i12);
                edit4.remove("unread_fav_reset_" + i12);
                edit4.remove("unread_fav_signal_" + i12);
            }
            edit4.remove("unread_fav_count");
            edit4.commit();
        }
        int i13 = defaultSharedPreferences.getInt("unread_men_count", 0);
        if (i13 > 0) {
            SharedPreferences.Editor edit5 = defaultSharedPreferences.edit();
            for (int i14 = 0; i14 < i13; i14++) {
                edit5.remove("unread_men_id_" + i14);
                edit5.remove("unread_men_time_" + i14);
                edit5.remove("unread_men_type_" + i14);
                edit5.remove("unread_men_reset_" + i14);
                edit5.remove("unread_men_signal_" + i14);
            }
            edit5.remove("unread_men_count");
            edit5.commit();
        }
    }

    @SuppressLint("WrongConstant")
    public static void restartConnection1(int i) {
        boolean z = true;
        if (documentManager.errorStatusCode != 1) {
            int i2 = 60000;
            if (i != 0) {
                int i3 = Prefs.f1169g;
                if (i3 == 4 || i3 == 5 || i3 == 6) {
                    i2 = 3600000;
                } else if (i3 == 2) {
                    i2 = Math.min(600000, Math.min(Math.max(i, 1), 100) * 60000);
                } else {
                    i2 = i3 == 3 ? Math.min(1200000, Math.min(Math.max(i, 1), 100) * 240000) : i3 == 1 ? Math.min(2400000, Math.min(Math.max(i, 1), 100) * 600000) : 0;
                }
            }

            if (i2 > 0) {
                if (Build.VERSION.SDK_INT >= 21) {
                    try {
                        ((JobScheduler) documentManager.context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(i, new ComponentName(documentManager.context, FourpdaJobService.class)).setMinimumLatency((long) i2).setRequiredNetworkType(1).build());
                    } catch (Throwable th) {
                        th.printStackTrace();
                        z = false;
                    }
                    if (!z) {
                        Intent intent = new Intent(documentManager.context.getPackageName());
                        intent.putExtra("count", i);
                        ((AlarmManager) documentManager.context.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + ((long) i2), PendingIntent.getService(documentManager.context, 0, intent, 268435456));
                    }
                }

            }
            if (i <= 0) {
                return;
            }
            if (documentManager.isWebSocketConnected()) {
                documentManager.closeSocketConnection();
                f2746C.m655c(null);
                return;
            }
            restartConnection(2);
        }
    }

    public static int getErrorStatusCode() {
        return documentManager.errorStatusCode;
    }

    public static int getMemberId() {
        return documentManager.memberId;
    }

    public static boolean isConnected3() {
        return documentManager.isConnected();
    }

    public static boolean isLoggined() {
        return documentManager.memberId > 0;
    }

    @SuppressLint("WrongConstant")
    public static void initAuthenticate() {
        documentManager.memberId = 0;
        documentManager.memberInfoModel = null;
        Unread2 g1Var = documentManager.unread2;
        if (g1Var != null) {
            g1Var.m696b();
            documentManager.unread2 = null;
        }
        try {
            ((NotificationManager) documentManager.context.getSystemService("notification")).cancelAll();
        } catch (Exception unused) {
            unused.printStackTrace();
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(documentManager.context).edit();
        edit.remove("member_id");
        edit.remove("login_key");
        edit.commit();
        getResultRequest(new SendLoginIdRequest(false, documentManager.memberId));
        int i = Prefs.f1169g;
        if (4 == i || i == 5 || i == 6) {
            MainActivity.m900f(documentManager.context);
        }

        getResultRequest((documentManager).new AuthRequest(0, "", false));
        DataDB.clearBookmarks(false);
        syncBookmarks(true);
        documentManager.messageHandler.sendEmptyMessage(9);
    }

    public static void syncBookmarks(boolean z) {
        if (isLoggined() || DataDB.m363n(3, 0) == 0) {
            documentManager.messageHandler.removeMessages(13);
            documentManager.messageHandler.sendEmptyMessageDelayed(13, z ? 100 : 10000);
        }
    }

    public static boolean isMemberValid() {
        if (!isLoggined()) {
            return false;
        }
        return getResultRequest(documentManager.new MemberSummaryRequest()) > 0;
    }

    static void closeConnection() {
        if (documentManager.getConnectTime() > 100) {
            documentManager.closeWebSocketConnection();
        }
    }

    public static int getResultRequest(IGenerateRequest request) {
        if (request.f2797d) {
            return request.result;
        }
        request.f2797d = true;
        if (!request.isAuth || request.result == 0) {
            request.result = Util.m671c();
        }
        synchronized (documentManager.requestSparseArray) {
            documentManager.requestSparseArray.put(request.result, request);
        }
        f2755z.m655c(1);
        if (documentManager.isConnected() && (request.isAuth || documentManager.conStatus >= 3)) {
            documentManager.sendRequest(request);
        }
        documentManager.messageHandler.sendEmptyMessageDelayed(2, 4000);
        return request.result;
    }

    @SuppressLint({"NewApi"})
    public static void restartConnection(int i) {
        int i2 = documentManager.errorStatusCode;
        documentManager.errorStatusCode = i;
        if (i != 0) {
            documentManager.f2766v = 0;
            if (Build.VERSION.SDK_INT >= 21 && i == 1) {
                ((JobScheduler) documentManager.context.getSystemService(Context.JOB_SCHEDULER_SERVICE)).cancelAll();
            }
            if (!documentManager.isWebSocketConnected()) {
                documentManager.initConnection();
                return;
            }
            if (i2 == 2) {
                if (documentManager.conStatus >= 3) {
                    getResultRequest(documentManager.new SendHandshake());
                    return;
                }
            }
            if (isLoggined()) {
                isMemberValid();
            }
        } else if (i2 != 1 || !isLoggined() || Prefs.f1169g == 0) {
            documentManager.closeSocketConnection();
            f2746C.m655c(null);
        } else {
            getResultRequest(documentManager.new SendLoginedData());
            restartConnection1(0);
        }
    }

    private void updateConnectionStatus() {
        if (4 == this.conStatus) {
            setConnectionStatus(this.conStatus1);
        }
        this.messageHandler.removeMessages(2);
    }

    public void loadServerData() {
        syncBookmarks(true);
        getResultRequest(new LoadStandardBookmarksRequest());
        getResultRequest(new API.ForumLoginAnonymous());
        setConnectionStatus(3);
        synchronized (this.requestSparseArray) {
            for (int i = 0; i < this.requestSparseArray.size(); i++) {
                IGenerateRequest request = this.requestSparseArray.valueAt(i);
                if (!request.isAuth) {
                    request.onErrorUploadFile();
                    sendRequest(request);
                }
            }
        }
    }

    public void setConnectionStatus(int connectionStatus) {
        this.conStatus = connectionStatus;
        this.messageHandler.sendEmptyMessage(1);
    }

    @Override
    protected void connectedServer() {
        int i = this.f2766v + 1;
        this.f2766v = i;
        if (this.errorStatusCode == 2 && i > 3) {
            restartConnection(0);
        }
        setConnectionStatus(1);
    }

    @Override
    protected void connectedForum() {
        setConnectionStatus(2);
        getResultRequest(2 == this.errorStatusCode ? new SendLoginedData() : new SendHandshake());
    }

    @Override
    protected void resetConnection() {
        int i = 0;
        setConnectionStatus(0);
        synchronized (this.requestSparseArray) {
            while (i < this.requestSparseArray.size()) {
                if (this.requestSparseArray.valueAt(i).isAuth) {
                    SparseArray<IGenerateRequest> sparseArray = this.requestSparseArray;
                    sparseArray.remove(sparseArray.keyAt(i));
                    i--;
                }
                i++;
            }
        }
    }

    @Override
    protected void handleDocument(Document document) {
        Log.d("DocumentManager", "handling document");
     //   Log.d("DocumentManager", document.toString());
        int i;
        updateConnectionStatus();
        if (document.getInt(0) == null || document.getInt(1) == null) {
            this.messageHandler.sendEmptyMessage(6);
            Log.e("DocumentManager", "Handler: Invalid Document header");
            //ACRA.getErrorReporter().handleSilentException(new IOException("Handler: Invalid Document header"));
        }
        int intValue = document.getInt(0) & 0xfffffff;
        int intValue2 = document.getInt(1);
        document.remove(1);
        document.remove(0);
        if (intValue == 0) {
            synchronized (this.requestSparseArray) {
                for (int i2 = 0; i2 < this.requestSparseArray.size(); i2++) {
                    messageHandler.sendMessage(messageHandler.obtainMessage(3, this.requestSparseArray.keyAt(i2), intValue2, document));
                }
                this.requestSparseArray.clear();
            }
        } else if (30309 == intValue && intValue2 == 0) {
            this.messageHandler.sendMessage(this.messageHandler.obtainMessage(12, 0, 0, document));
        } else {
            IGenerateRequest request = this.requestSparseArray.get(intValue);
            if (request != null) {
                request.f2797d = false;
                if (intValue2 == 0 && (27757 == (i = request.cmd) || 25453 == i)) {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
                    int intValue3 = document.getInt(0);
                    this.memberId = intValue3;
                    edit.putInt("member_id", intValue3);
                    edit.putString("login_key", document.getString(1));
                    edit.commit();
                    if (this.memberId != 0) {
                        isMemberValid();
                        DataDB.clearBookmarks(true);
                        syncBookmarks(true);
                    }
                }
                try {
                    Log.d("DocumentManager", "getResult document " + intValue2);
                    request.getResult(intValue2, document);
                } catch (Exception e) {
                    e.printStackTrace();
                    //ACRA.getErrorReporter().handleSilentException(new Exception("Handler:Async", e));
                }
                this.messageHandler.sendMessage(this.messageHandler.obtainMessage(3, intValue, intValue2, document));
            }
        }
    }

    @Override
    protected void parseDocumentError() {
        this.messageHandler.sendEmptyMessage(4);
    }

    @Override
    protected void mo88H() {
        updateConnectionStatus();
    }

    @Override
    protected void mo87I() {
        updateConnectionStatus();
    }

    @Override
    protected void mo86J() {
        this.messageHandler.sendEmptyMessage(5);
    }

    public void saveUnreadData(Bundle bundle) {
        String str;
        if (this.unread2 != null) {
            String string = bundle.getString("t");
            int parseInt = TextUtils.isEmpty(string) ? 0 : Integer.parseInt(string);
            String string2 = bundle.getString("i");
            int parseInt2 = TextUtils.isEmpty(string2) ? 0 : Integer.parseInt(string2);
            String string3 = bundle.getString("n");
            String str2 = TextUtils.isEmpty(string3) ? "" : string3;
            String string4 = bundle.getString("ai");
            int parseInt3 = TextUtils.isEmpty(string4) ? 0 : Integer.parseInt(string4);
            String string5 = bundle.getString("an");
            if (TextUtils.isEmpty(string5)) {
                str = "";
            } else {
                str = string5;
            }
            String string6 = bundle.getString("v");
            int parseInt4 = TextUtils.isEmpty(string6) ? 0 : Integer.parseInt(string6);
            String string7 = bundle.getString("vd");
            int parseInt5 = TextUtils.isEmpty(string7) ? 0 : Integer.parseInt(string7);
            String string8 = bundle.getString("e1");
            int parseInt6 = TextUtils.isEmpty(string8) ? 0 : Integer.parseInt(string8);
            String string9 = bundle.getString("e2");
            int parseInt7 = TextUtils.isEmpty(string9) ? 0 : Integer.parseInt(string9);
            String string10 = bundle.getString("e3");
            int parseInt8 = TextUtils.isEmpty(string10) ? 0 : Integer.parseInt(string10);
            String string11 = bundle.getString("e4");
            this.unread2.m688j(parseInt, parseInt2, str2, parseInt3, str, parseInt4, parseInt5, parseInt6, parseInt7, parseInt8, TextUtils.isEmpty(string11) ? 0 : Integer.parseInt(string11), false);
        }
    }

    public void parseUnreadData(String str) {
        String str2;
        if (this.unread2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt("t");
                int i2 = jSONObject.getInt("i");
                String string = jSONObject.getString("n");
                if (TextUtils.isEmpty(string)) {
                    string = "";
                }
                int i3 = jSONObject.getInt("ai");
                String string2 = jSONObject.getString("an");
                if (TextUtils.isEmpty(string2)) {
                    str2 = "";
                } else {
                    str2 = string2;
                }
                this.unread2.m688j(i, i2, string, i3, str2, jSONObject.getInt("v"), jSONObject.has("vd") ? jSONObject.getInt("vd") : 0, jSONObject.has("e1") ? jSONObject.getInt("e1") : 0, jSONObject.has("e2") ? jSONObject.getInt("e2") : 0, jSONObject.has("e3") ? jSONObject.getInt("e3") : 0, jSONObject.has("e4") ? jSONObject.getInt("e4") : 0, false);
            } catch (Exception e) {
                e.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(e);
            }
        }
    }

    public void initUnread() {
        int i = PreferenceManager.getDefaultSharedPreferences(this.context).getInt("member_id", 0);
        this.memberId = i;
        if (i > 0) {
            this.memberInfoModel = MemberInfoModel.getMemeberSummaryData(this.context);
        }
        this.unread2 = this.memberInfoModel == null ? null : new Unread2(this.context);
    }

    public static class MemberInfoModel {
        int memberId;
        String memberInfoName;
        String memberGroupName;
        int memberGroup;
        int memberPremod;
        int memberReadonly;
        int memberBan;
        String memberAvatar;
        int memberPostsCount;
        List<MemberMessageModel> memberMessageModels;
        List<MemberMessageModel> memberDeletedMessageModels;
        int memberPostsPerPage;
        int memberTopicsPerPage;
        boolean memberTickets;

        public MemberInfoModel(Document document) {
            this.memberId = document.getInt(0);
            this.memberInfoName = Util.C0427h.UnEscapeString(document.getString(1));
            this.memberGroup = document.getInt(2);
            this.memberGroupName = API.userGroups.get(memberGroup);
            this.memberPostsCount = document.getInt(3);
            this.memberAvatar = document.getString(4);
            this.memberPostsPerPage = document.getInt(5);
            this.memberTopicsPerPage = document.getInt(6);
            this.memberPremod = document.getInt(7);
            this.memberReadonly = document.getInt(8);
            this.memberBan = document.getInt(9);
            this.memberTickets = document.getInt(10) > 0;
            Document posts = document.getDocument(13);
            if (posts != null) {
                for (int i = 0; i < posts.count(); i++) {
                    Document post = posts.getDocument(i);
                    if (post.getInt(0) == 0) {
                        if (this.memberMessageModels == null) {
                            this.memberMessageModels = new Vector();
                        }
                        this.memberMessageModels.add(new MemberMessageModel(post));
                    } else if (post.getInt(0) == 1) {
                        if (this.memberDeletedMessageModels == null) {
                            this.memberDeletedMessageModels = new Vector();
                        }
                        this.memberDeletedMessageModels.add(new MemberMessageModel(post));
                    }
                }
            }
        }

        public static MemberInfoModel getMemeberSummaryData(Context context) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            int i = defaultSharedPreferences.getInt("member_info_id", -1);
            if (i == -1) {
                return null;
            }
            Document document = new Document(i);
            document.append(defaultSharedPreferences.getString("member_info_name", "green elefant"));
            document.append(defaultSharedPreferences.getInt("member_info_group", 0));
            document.append(defaultSharedPreferences.getInt("member_info_posts_count", 0));
            document.append(defaultSharedPreferences.getString("member_info_avatar", ""));
            document.append(defaultSharedPreferences.getInt("member_info_posts_per_page", 20));
            document.append(defaultSharedPreferences.getInt("member_info_topics_per_page", 30));
            document.append(defaultSharedPreferences.getInt("member_info_premod", 0));
            document.append(defaultSharedPreferences.getInt("member_info_readonly", 0));
            document.append(defaultSharedPreferences.getInt("member_info_ban", 0));
            document.append(defaultSharedPreferences.getBoolean("member_info_tickets", false) ? 1 : 0);
            document.append(0);
            document.append(0);
            int i2 = defaultSharedPreferences.getInt("member_info_msg_count", 0);
            if (i2 > 0) {
                Document uVar2 = new Document();
                for (int i3 = 0; i3 < i2; i3++) {
                    uVar2.append(new Document(defaultSharedPreferences.getInt("member_info_msg_type_" + i3, 0),
                            defaultSharedPreferences.getString("member_info_msg_text_" + i3, ""),
                            defaultSharedPreferences.getInt("member_info_msg_color_" + i3, 0),
                            defaultSharedPreferences.getInt("member_info_msg_created_" + i3, 0)));
                }
                document.append(uVar2);
            } else {
                document.append(0);
            }
            document.append(0);
            return new MemberInfoModel(document);
        }

        public static void parseMemberSummaryDocument(Context context, Document document) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putInt("member_info_id", document.getInt(0));
            edit.putString("member_info_name", Util.C0427h.UnEscapeString(document.getString(1)));
            edit.putInt("member_info_group", document.getInt(2));
            edit.putInt("member_info_posts_count", document.getInt(3));
            edit.putString("member_info_avatar", document.getString(4));
            edit.putInt("member_info_premod", document.getInt(7));
            edit.putInt("member_info_readonly", document.getInt(8));
            edit.putInt("member_info_ban", document.getInt(9));
            edit.putInt("member_info_posts_per_page", document.getInt(5));
            edit.putInt("member_info_topics_per_page", document.getInt(6));
            edit.putBoolean("member_info_tickets", document.getInt(10) > 0);
            Document l = document.getDocument(13);
            if (l != null) {
                edit.putInt("member_info_msg_count", l.count());
                for (int i = 0; i < l.count(); i++) {
                    Document l2 = l.getDocument(i);
                    edit.putInt("member_info_msg_type_" + i, l2.getInt(0));
                    edit.putString("member_info_msg_text_" + i, l2.getString(1));
                    edit.putInt("member_info_msg_color_" + i, l2.getInt(2));
                    edit.putInt("member_info_msg_created_" + i, l2.getInt(3));
                }
            }
            edit.commit();
        }

        public static class MemberMessageModel {
            public String memberMsgText;
            public int memberMsgColor;
            public int memberMsgCreated;

            public MemberMessageModel(Document document) {
                this.memberMsgText = document.getString(1);
                this.memberMsgColor = document.getInt(2);
                this.memberMsgCreated = document.getInt(3);
            }
        }
    }

    private class MyBrodcastReceiver extends BroadcastReceiver {
        int networkType;

        private MyBrodcastReceiver(DocumentManager vVar) {
            this.networkType = -1;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            int type;
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                int i = -1;
                if (activeNetworkInfo != null && ((type = activeNetworkInfo.getType()) == 1 || type == 0)) {
                    i = type;
                }
                if (this.networkType != i) {
                    DocumentManager.closeConnection();
                }
                this.networkType = i;
            }
        }
    }

    public class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            Unread2 unread2;
            Unread2 g1Var2;
            Unread2 g1Var3;
            Unread2 g1Var4;
            try {
                switch (message.what) {
                    case 1:
                        DocumentManager.f2754y.m655c(DocumentManager.this.conStatus);
                        return;
                    case 2:
                        //      Log.d("DocumentMamager","message 2 callsed");
                        DocumentManager.this.messageHandler.removeMessages(2);
                        DocumentManager vVar = DocumentManager.this;
                        int i = vVar.conStatus;
                        if (!(i == 3 || i == 2)) {
                            if (i == 4) {
                                vVar.closeWebSocketConnection();
                                return;
                            }
                            return;
                        }
                        if ((DocumentManager.documentManager.getConnectTime() > 120000 && DocumentManager.documentManager.getSleepTime() > 3000) || DocumentManager.documentManager.getSleepTime() > 7000) {
                            DocumentManager vVar2 = DocumentManager.this;
                            vVar2.conStatus1 = vVar2.conStatus;
                            vVar2.setConnectionStatus(4);
                            DocumentManager.this.isDataSent();
                        }
                        DocumentManager.this.messageHandler.sendEmptyMessageDelayed(2, 4000);
                        return;
                    case 3:
                        int messageId = message.arg1;
                        IGenerateRequest request = DocumentManager.this.requestSparseArray.get(messageId);
                        if (request != null) {
                            int index = message.arg2;
                            Document document = (Document) message.obj;
                            if (index == 0 && (unread2 = DocumentManager.this.unread2) != null) {
                                int i4 = request.cmd;
                                if (29809 == i4) {
                                    unread2.m691g(1, document.getInt(0), 0);
                                } else if (29542 == i4) {
                                    unread2.m691g(2, document.getInt(1), 0);
                                } else if (29286 == i4) {
                                    Document subDoc = document.getDocument(12);
                                    DocumentManager.this.unread2.m691g(3, document.getInt(1), (subDoc == null || subDoc.count() <= 0) ? 0 : subDoc.getDocument(subDoc.count() - 1).getInt(7));
                                    int[] iArr = new int[subDoc.count()];
                                    for (int i5 = 0; i5 < subDoc.count(); i5++) {
                                        iArr[i5] = subDoc.getDocument(i5).getInt(0);
                                    }
                                    DocumentManager.this.unread2.m690h(4, iArr, 0);
                                } else if (24947 == i4) {
                                    unread2.m691g(5, 0, document.getInt(0));
                                } else if (28013 == i4) {
                                    unread2.m691g(4, 0, 0);
                                    DocumentManager.this.unread2.m691g(5, 0, 0);
                                }
                            }
                            try {
                                Log.d("DocumentManager", "send document");
                                request.prepareResult(index, document);
                            } catch (Exception e) {
                                e.printStackTrace();
                                //ACRA.getErrorReporter().handleSilentException(new Exception("Handler:Reply", e));
                            }
                            synchronized (DocumentManager.this.requestSparseArray) {
                                DocumentManager.this.requestSparseArray.remove(messageId);
                            }
                            DocumentManager.f2755z.m655c(-1);
                            if (2 == DocumentManager.this.errorStatusCode && DocumentManager.this.requestSparseArray.size() == 0) {
                                DocumentManager.restartConnection(0);
                                return;
                            }
                            return;
                        }
                        return;
                    case 4:
                    case 5:
                    case 6:
                        synchronized (DocumentManager.this.requestSparseArray) {
                            for (int i6 = 0; i6 < DocumentManager.this.requestSparseArray.size(); i6++) {
                                DocumentManager.this.requestSparseArray.valueAt(i6).m245h();
                            }
                            DocumentManager.this.requestSparseArray.clear();
                        }
                        DocumentManager.f2755z.m655c(-1);
                        return;
                    case 7:
                        DocumentManager.this.closeSocketConnection();
                        DocumentManager.f2749F.m655c(null);
                        return;
                    case 8:
                        DocumentManager.f2744A.m655c(null);
                        return;
                    case 9:
                        DocumentManager.f2747D.m655c(DocumentManager.this.memberInfoModel);
                        return;
                    case 10:
                        DocumentManager.f2750G.m652c(message.arg1, message.arg2);
                        return;
                    case 11:
                        DocumentManager.f2751H.m652c(message.arg1, message.arg2);
                        return;
                    case 12:
                        Document uVar2 = (Document) message.obj;
                        String n = uVar2.getString(0);
                        if (!TextUtils.isEmpty(n)) {
                            char charAt = n.charAt(0);
                            int parseInt = Integer.parseInt(n.substring(1));
                            int intValue = uVar2.getInt(1);
                            int intValue2 = uVar2.getInt(2);
                            if (!(charAt == 'u' || charAt == 'g')) {
                                if (charAt == 'q') {
                                    if (1 == intValue) {
                                        Unread2 g1Var5 = DocumentManager.this.unread2;
                                        if (g1Var5 != null) {
                                            g1Var5.m689i(1, parseInt, intValue2);
                                            return;
                                        }
                                        return;
                                    } else if (2 == intValue) {
                                        Unread2 g1Var6 = DocumentManager.this.unread2;
                                        if (g1Var6 != null) {
                                            g1Var6.m691g(1, parseInt, intValue2);
                                            return;
                                        }
                                        return;
                                    } else if (101 == intValue) {
                                        MessageHandler dVar = DocumentManager.this.messageHandler;
                                        dVar.sendMessage(dVar.obtainMessage(10, parseInt, intValue2));
                                        return;
                                    } else if (102 == intValue) {
                                        MessageHandler dVar2 = DocumentManager.this.messageHandler;
                                        dVar2.sendMessage(dVar2.obtainMessage(11, parseInt, intValue2));
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (charAt == 'f') {
                                    if (1 == intValue) {
                                        Unread2 g1Var7 = DocumentManager.this.unread2;
                                        if (g1Var7 != null) {
                                            g1Var7.m689i(2, parseInt, intValue2);
                                            return;
                                        }
                                        return;
                                    } else if (2 == intValue && (g1Var4 = DocumentManager.this.unread2) != null) {
                                        g1Var4.m691g(2, parseInt, 0);
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (charAt == 't') {
                                    if (1 == intValue) {
                                        Unread2 g1Var8 = DocumentManager.this.unread2;
                                        if (g1Var8 != null) {
                                            g1Var8.m689i(3, parseInt, intValue2);
                                            return;
                                        }
                                        return;
                                    } else if (2 == intValue) {
                                        Unread2 g1Var9 = DocumentManager.this.unread2;
                                        if (g1Var9 != null) {
                                            g1Var9.m691g(3, parseInt, 0);
                                            return;
                                        }
                                        return;
                                    } else if (3 == intValue) {
                                        Unread2 g1Var10 = DocumentManager.this.unread2;
                                        if (g1Var10 != null) {
                                            g1Var10.m689i(4, intValue2, parseInt);
                                            return;
                                        }
                                        return;
                                    } else if (4 == intValue && (g1Var3 = DocumentManager.this.unread2) != null) {
                                        g1Var3.m689i(3, parseInt, intValue2);
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (charAt == 's' && 3 == intValue && (g1Var2 = DocumentManager.this.unread2) != null) {
                                    g1Var2.m689i(5, intValue2, parseInt);
                                    return;
                                } else {
                                    return;
                                }
                            }
                            DocumentManager.isMemberValid();
                            return;
                        }
                        return;
                    case 13:
                        DocumentManager.getResultRequest(new LoadBookmarkRequest());
                        return;
                    default:
                        return;
                }
            } catch (Exception e2) {
               /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                StringBuilder sb = new StringBuilder();
                sb.append("Event=");
                sb.append(message.what);
                sb.append(" arg1=");
                sb.append(message.arg1);
                sb.append(" arg2=");
                sb.append(message.arg2);
                sb.append(" obj=");
                Object obj = message.obj;
                sb.append(obj != null ? obj.getClass().getCanonicalName() : "(null)");
                errorReporter.putCustomData("extra", sb.toString());*/
                //ACRA.getErrorReporter().handleSilentException(new Exception("DM.HandleMessage common", e2));
                //ACRA.getErrorReporter().removeCustomData("extra");
                e2.printStackTrace();
            }
        }
    }

    public static abstract class IGenerateRequest {
        int result;
        int cmd;
        boolean isAuth;
        boolean f2797d;
        int f2798e;
        String statusMessage;

        public IGenerateRequest(int cmd) {
            this.cmd = cmd;
        }

        void m245h() {
        }

        void prepareResult(int status, Document document) {
        }

        void getResult(int status, Document document) {
        }

        public void onErrorUploadFile() {
            this.f2798e = Math.min(this.f2798e + 1, 7);
        }

        public int onUploadFile(byte[] bytes, int len) {
            return 0;
        }

        public int isFileSize() {
            return 0;
        }

        abstract Document generate();

        public Document getDocument() {
            Document genDoc = generate();
            Document document = genDoc == null ? new Document() : genDoc.cloneDocument();
            document.prepend(new String(new char[]{(char) (cmd & 255), (char) ((cmd >> 8) & 255)}));
            document.prepend(this.result | (this.f2798e << 28));
            return document;
        }
    }

    static class LoadBookmarkRequest extends IGenerateRequest {
        LoadBookmarkRequest() {
            super(25197);
            this.isAuth = true;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (status == 0 && uVar.getDocument(1).count() + uVar.getDocument(2).count() > 0) {
                DocumentManager.f2748E.m655c(null);
            }
        }

        @Override
        void getResult(int status, Document uVar) {
            if (status == 0) {
                DataDB.m362o(3, uVar.getInt(0));
                int i2 = 4;
                DataDB.m362o(4, 0);
                Document l = uVar.getDocument(1);
                for (int i3 = 0; i3 < l.count(); i3++) {
                    Document l2 = l.getDocument(i3);
                    DataDB.m369h(l2.getInt(0), l2.getInt(1));
                }
                Document l3 = uVar.getDocument(2);
                int i4 = 0;
                while (i4 < l3.count()) {
                    Document l4 = l3.getDocument(i4);
                    DataDB.m370g(new DataDB.C0736a(l4.getInt(0), l4.getInt(1), l4.getInt(2) != 0, l4.getInt(3), l4.getInt(i2).intValue(), l4.getInt(5).intValue(), l4.getString(6), l4.getString(7)), false);
                    i4++;
                    i2 = 4;
                }
            }
        }

        @Override
        public Document generate() {
            Document uVar = new Document();
            DataDB.C0736a[] d = DataDB.m373d();
            if (d != null) {
                for (DataDB.C0736a aVar : d) {
                    if ((aVar.f2499d & 2) == 0) {
                        uVar.append(new Document(aVar.f2496a, aVar.f2497b, aVar.f2498c ? 1 : 0, aVar.f2499d & -241, aVar.f2500e, aVar.f2501f, aVar.f2502g, aVar.f2503h));
                    }
                }
            }
            return new Document(DataDB.m363n(3, 0), uVar);
        }
    }

    public static class LoadStandardBookmarksRequest extends IGenerateRequest {
        LoadStandardBookmarksRequest() {
            super(28019);
            this.isAuth = true;
        }

        @Override
        void getResult(int status, Document uVar) {
            if (status == 0) {
                DataDB.m361p();
                DataDB.m362o(5, uVar.getInt(0));
                Document l = uVar.getDocument(1);
                for (int i2 = 0; i2 < l.count(); i2++) {
                    Document l2 = l.getDocument(i2);
                    DataDB.m357t(new DataDB.C0737b(l2.getInt(0), l2.getInt(1), l2.getString(2), l2.getString(3), l2.getInt(4).intValue() != 0, l2.getInt(5).intValue()));
                }
            }
        }

        @Override
        public Document generate() {
            return new Document(DataDB.m363n(5, 0));
        }
    }

    private static class SendLoginIdRequest extends IGenerateRequest {
        int loginId;

        SendLoginIdRequest(boolean isLogin, int loginId) {
            super(isLogin ? 24933 : 25701);
            this.loginId = loginId;
            this.isAuth = true;
        }

        @Override
        Document generate() {
            return new Document("u" + this.loginId);
        }
    }

    private class AuthRequest extends IGenerateRequest {
        int memberId;
        String loginKey;
        boolean isHidden;

        AuthRequest(int memberId, String loginKey, boolean isHidden) {
            super(24941);
            this.memberId = memberId;
            this.loginKey = loginKey;
            this.isHidden = isHidden;
            this.isAuth = true;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (this.memberId != 0) {
                if (status == 0) {
                    DocumentManager.getResultRequest(new SendLoginIdRequest(true, DocumentManager.this.memberId));
                    DocumentManager.isMemberValid();
                    int i2 = Prefs.f1169g;
                    if (i2 == 4 || i2 == 5 || i2 == 6) {
                        MainActivity.m900f(DocumentManager.documentManager.context);
                    }
                    DocumentManager.this.loadServerData();
                    return;
                }
                DocumentManager vVar = DocumentManager.this;
                vVar.memberId = 0;
                vVar.messageHandler.sendEmptyMessage(8);
            }
        }

        @Override
        Document generate() {
            Document document = new Document(this.memberId, this.loginKey);
            if (this.isHidden) {
                document.append(1);
            }
            return document;
        }
    }

    public class SendHandshake extends IGenerateRequest {
        SendHandshake() {
            super(26721);
            this.isAuth = true;
        }


        @Override
        void prepareResult(int status, Document uVar) {
            Log.d("DocumnetManager", "send handshake");
            if (status == 3) {
                DocumentManager.this.messageHandler.sendEmptyMessage(7);
            } else if (status != 0) {
                if (1 == DocumentManager.this.errorStatusCode) {
                    Toast.makeText(DocumentManager.this.context, "Ошибка сервера!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException unused) {
                        unused.printStackTrace();
                    }
                }
                DocumentManager.closeConnection();
            } else if (DocumentManager.isLoggined()) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(DocumentManager.this.context);
                DocumentManager vVar = DocumentManager.this;
                DocumentManager.getResultRequest(new AuthRequest(vVar.memberId, defaultSharedPreferences.getString("login_key", ""), defaultSharedPreferences.getBoolean("member_hidden", false)));
            } else {
                DocumentManager.this.loadServerData();
            }
            String n = uVar.getString(2);
            if (!TextUtils.isEmpty(n)) {
                DocumentManager.f2745B.m655c(n);
            }
        }

        @Override
        Document generate() {
            int i;
            String installerPackageName = DocumentManager.this.context.getPackageManager().getInstallerPackageName(DocumentManager.this.context.getPackageName());
            @SuppressLint("DefaultLocale") String format = String.format("%d.%d.%d", 1, 9, 32);
            try {
                ZipFile zipFile = new ZipFile(DocumentManager.this.context.getPackageCodePath());
                i = (int) zipFile.getEntry("classes.dex").getCrc();
                try {
                    zipFile.close();
                } catch (Exception unused) {
                    unused.printStackTrace();
                }
            } catch (Exception unused2) {
                i = 0;
            }
            Object[] objArr = new Object[5];
            objArr[0] = format;
            String str = Prefs.f1183u;
            if (str == null) {
                str = "";
            }
            objArr[1] = "";
            if (installerPackageName == null) {
                installerPackageName = "";
            }
            objArr[2] = "";
            objArr[3] = 0;
            objArr[4] = DocumentManager.this.memberId;
            return new Document(objArr);
        }
    }

    public class SendLoginedData extends IGenerateRequest {
        SendLoginedData() {
            super(27749);
            this.isAuth = true;
        }

        @Override
        void prepareResult(int status, Document uVar) {
          //  Log.d("DocumentManager","doc: "+ uVar.toString());
            if (status == 0) {
                int intValue = uVar.getInt(0);
                if (intValue != DocumentManager.this.f2767w && 2 == DocumentManager.this.errorStatusCode) {
                    DocumentManager.getResultRequest(new SendHandshake());
                }
                DocumentManager.this.f2767w = intValue;
            }

        }

        @Override
        Document generate() {
            return new Document(DocumentManager.this.memberId);
        }
    }

    public class MemberSummaryRequest extends IGenerateRequest {
        MemberSummaryRequest() {
            super(29549);
            this.isAuth = true;
        }

        @Override
        void prepareResult(int status, Document document) {
            if (status == 0) {
                DocumentManager.this.memberInfoModel = new MemberInfoModel(document);
                MemberInfoModel.parseMemberSummaryDocument(DocumentManager.this.context, document);
                DocumentManager.this.messageHandler.sendEmptyMessage(9);
                if (unread2 == null) {
                    unread2 = new Unread2(DocumentManager.this.context);
                }
                DocumentManager.this.unread2.m695c();
            }
        }

        @Override
        Document generate() {
            return null;
        }
    }
}
