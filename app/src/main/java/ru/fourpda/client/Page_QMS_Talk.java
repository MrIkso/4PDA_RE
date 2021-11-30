package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_QMS_Talk extends Page implements BBDisplay.IBBDisplayCallback, MainActivity.AbstractC0193g0 {
    String f2361E;
    int f2362F;
    View f2363G;
    EditText f2364H;
    View f2365I;
    View f2366J;
    View f2367K;
    int dialogId;
    BBEditor bbEditor;
    ViewGroup f2370N;
    SparseArray<QmsMessage> qmsMessageSparseArray;
    int myId;
    String opponentTitle;
    String avatarUrl;
    String f2375S;
    boolean f2376T;
    boolean f2377U;
    boolean f2378V;
    boolean f2379W;
    boolean f2380X;
    private int f2381Y;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f2382Z;
    private final Util.AbstractC0429j<Boolean, DataDB.C0738c> f2383a0;
    private final Util.AbstractC0430k<Boolean, Integer, Integer> f2384b0;
    private final Util.AbstractC0430k<Boolean, Integer, Integer> f2385c0;
    private final Runnable f2386d0;
    boolean f2387e0;
    private int f2388f0;
    private long f2389g0;
    private String f2390h0;
    String f2391i0;
    int f2392j0;
    Runnable f2393k0;

    class C0694a implements Util.AbstractC0429j<Boolean, DataDB.C0738c> {
        C0694a() {
        }

        public Boolean mo222a(DataDB.C0738c cVar) {
            boolean z = true;
            if (cVar.f2511a == 1) {
                int i = cVar.f2512b;
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                if (i == p0Var.dialogId) {
                    if (p0Var.isCurrentTab()) {
                        if (Page_QMS_Talk.this.tab.m717i()) {
                        }
                    }
                }
            }
            z = false;
            return Boolean.valueOf(z);
        }
    }

    class C0695b implements Util.AbstractC0430k<Boolean, Integer, Integer> {
        C0695b() {
        }

        public Boolean mo103a(Integer num, Integer num2) {
            if (Page_QMS_Talk.this.dialogId != num) {
                return Boolean.FALSE;
            }
            Page_QMS_Talk.this.m413p0(0, 0);
            if (Page_QMS_Talk.this.isCurrentTab() && Page_QMS_Talk.this.tab.m717i()) {
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                if (!p0Var.mainActivity.f733d) {
                    p0Var.tabLoaded(true);
                }
            }
            return Boolean.TRUE;
        }
    }

    class C0696c implements Util.AbstractC0430k<Boolean, Integer, Integer> {
        C0696c() {
        }

        public Boolean mo103a(Integer num, Integer num2) {
            if (Page_QMS_Talk.this.dialogId != num.intValue() || !Page_QMS_Talk.this.isCurrentTab() || !Page_QMS_Talk.this.tab.m717i()) {
                return Boolean.FALSE;
            }
            Page_QMS_Talk.this.tab.m721e(true, num2.intValue());
            Page_QMS_Talk p0Var = Page_QMS_Talk.this;
            p0Var.tab.removeCallbacks(p0Var.f2386d0);
            Page_QMS_Talk p0Var2 = Page_QMS_Talk.this;
            p0Var2.tab.postDelayed(p0Var2.f2386d0, 5500);
            return Boolean.TRUE;
        }
    }

    class RunnableC0697d implements Runnable {
        RunnableC0697d() {
        }

        @Override
        public void run() {
            Tab f1Var = Page_QMS_Talk.this.tab;
            if (f1Var != null) {
                f1Var.m721e(false, 0);
            }
        }
    }

    class DeleteMessageClickListener implements OptionPoupupMenuView.IClickListener {
        final BBDisplay f2398a;
        final BBString f2399b;
        final BBDisplay.C0143c f2400c;

        class View$OnClickListenerC0699a implements View.OnClickListener {
            final QmsMessage f2402a;
            final DlgSimple f2403b;

            View$OnClickListenerC0699a(QmsMessage uVar, DlgSimple q1Var) {
                this.f2402a = uVar;
                this.f2403b = q1Var;
            }

            @Override
            public void onClick(View view) {
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                int dialogId = p0Var.dialogId;
                int i2 = this.f2402a.messageId;
                boolean isOnlyMe = this.f2403b.checkboxTextView.getChecked();
                DocumentManager.getResultRequest(new Page_QMS_List.QmsDeleteMessage(p0Var, dialogId, i2, isOnlyMe, "Удаление сообщения " + this.f2402a.messageId));
            }
        }

        DeleteMessageClickListener(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
            this.f2398a = bBDisplay;
            this.f2399b = pVar;
            this.f2400c = cVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            boolean z = true;
            if (i3 == 21) {
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                p0Var.f2387e0 = true;
                p0Var.mo129f(this.f2398a, this.f2399b, this.f2400c);
            } else if (i3 == 26) {
                MainActivity mainActivity = Page_QMS_Talk.this.mainActivity;
                BBDisplay.C0143c cVar = this.f2400c;
                Util.copyToClipboard(mainActivity, cVar.f544b >= 0 ? "https://4pda.ru/forum/dl/post/" + this.f2399b.f2212S[this.f2400c.f544b].f2247a + "/" + this.f2399b.f2212S[this.f2400c.f544b].f2250d : this.f2399b.f2202I.get(cVar.f543a).link, "Ссылка скопирована");
            } else if (i3 == 3 || i3 == 4) {
                for (int i4 = 0; i4 < Page_QMS_Talk.this.tab.forumsListView.getChildCount(); i4++) {
                    BBOverlay bBOverlay = Page_QMS_Talk.this.tab.forumsListView.getChildAt(i4).findViewById(R.id.messageOverlay);
                    if (bBOverlay != null) {
                        bBOverlay.m949k();
                    }
                }
                BBOverlay bBOverlay2 = this.f2398a.f528e;
                if (i3 != 3) {
                    z = false;
                }
                bBOverlay2.m950j(z);
            } else if (i3 == 27) {
                Urls2.m676g(Page_QMS_Talk.this.mainActivity, this.f2399b.f2202I.get(this.f2400c.f543a).link);
            } else if (i3 == 24) {
                this.f2398a.m977d(this.f2400c.f545c, true);
            } else if (i3 == 1) {
                QmsMessage valueAt = Page_QMS_Talk.this.qmsMessageSparseArray.valueAt(i2);
                DlgSimple q1Var = new DlgSimple(Page_QMS_Talk.this.mainActivity, "Удалить сообщение?", false, null, null);
                q1Var.editText.setVisibility(8);
                if (valueAt.opponentId != Page_QMS_Talk.this.myId && ((long) valueAt.f2443d) > (System.currentTimeMillis() / 1000) - 172800) {
                    q1Var.checkboxTextView.setVisibility(0);
                    q1Var.checkboxTextView.setText("Удалить и для " + Page_QMS_Talk.this.opponentTitle);
                }
                q1Var.m620f(new View$OnClickListenerC0699a(valueAt, q1Var), true);
                q1Var.show(true, true, true);
            } else if (i3 == 2) {
                Page_QMS_Talk p0Var2 = Page_QMS_Talk.this;
                Util.copyToClipboard(p0Var2.mainActivity, p0Var2.qmsMessageSparseArray.valueAt(i2).f2446g.m481h(), "Сообщение скопировано в буфер");
            } else if (i3 == 32) {
                Page_QMS_Talk.this.mo129f(this.f2398a, this.f2399b, this.f2400c);
            } else if (i3 == 30) {
                Page_QMS_Talk.this.mo129f(this.f2398a, this.f2399b, this.f2400c);
            } else if (i3 == 31) {
                BBDisplay.C0143c cVar2 = this.f2400c;
                int i5 = cVar2.f544b;
                if (i5 >= 0) {
                    BBString.C0670a[] aVarArr = this.f2399b.f2212S;
                    if (aVarArr[i5].f2249c && aVarArr[i5].f2253g > 0) {
                        DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVarArr[i5].f2247a, Page_QMS_Talk.this.mainActivity, null));
                        return;
                    }
                }
                new API.LoadForumAttachRequest(cVar2.f545c, Page_QMS_Talk.this.mainActivity, null).prepareResult(0, new Document(this.f2399b.f2208O[this.f2400c.f545c].f2279c));
            }
        }
    }

    class C0700f implements OptionPoupupMenuView.IClickListener {
        C0700f() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 1) {
                Page_QMS_Talk.this.tabReload();
            } else if (i3 == 2) {
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                DataDB.m365l(p0Var.f2361E != null ? "Поиск \"" + Page_QMS_Talk.this.f2361E + "\" в \"" + Page_QMS_Talk.this.title + "\"" : p0Var.title, Page_QMS_Talk.this.getLink());
            } else {
                if (i3 == 3) {
                    Tab f1Var = new Tab(Page_QMS_Talk.this.mainActivity);
                    Page_QMS_Talk p0Var2 = Page_QMS_Talk.this;
                    f1Var.addPage(new Page_Profile(p0Var2.mainActivity, p0Var2.myId, 0));
                    Page_QMS_Talk.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                } else if (i3 == 4) {
                    DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Разговор между \"[url=https://4pda.ru/forum/index.php?showuser=");
                    sb.append(L.memberId);
                    sb.append("]");
                    sb.append(L.memberInfoName);
                    sb.append("[/url]\" и \"[url=https://4pda.ru/forum/index.php?showuser=");
                    sb.append(Page_QMS_Talk.this.myId);
                    sb.append("]");
                    sb.append(Page_QMS_Talk.this.opponentTitle);
                    sb.append("[/url]\" на тему \"");
                    sb.append(Page_QMS_Talk.this.title);
                    sb.append("\"\n");
                    for (int i4 = 0; i4 < Page_QMS_Talk.this.qmsMessageSparseArray.size(); i4++) {
                        QmsMessage valueAt = Page_QMS_Talk.this.qmsMessageSparseArray.valueAt(i4);
                        sb.append("[quote name=\"");
                        int i5 = valueAt.opponentId;
                        Page_QMS_Talk p0Var3 = Page_QMS_Talk.this;
                        sb.append(i5 == p0Var3.myId ? p0Var3.opponentTitle : L.memberInfoName);
                        sb.append("\" date=\"");
                        sb.append(valueAt.f2444e);
                        sb.append("\"]\n");
                        sb.append(valueAt.f2446g.m481h());
                        sb.append("\n[/quote]\n");
                    }
                    Util.copyToClipboard(Page_QMS_Talk.this.mainActivity, sb.toString(), "Диалог скопирован в буфер");
                }
            }
        }
    }

    class RunnableC0701g implements Runnable {
        RunnableC0701g() {
        }

        @Override
        public void run() {
            Page_QMS_Talk p0Var = Page_QMS_Talk.this;
            Page_QMS_Talk p0Var2 = Page_QMS_Talk.this;
            Page_QMS_Talk.this.f2366J.getLayoutParams().width = (int) ((p0Var.mainActivity.f731b * 5.0f) + ((((float) p0Var.f2392j0) * (((float) p0Var.f2370N.getWidth()) - (p0Var2.mainActivity.f731b * 10.0f))) / 100.0f));
            p0Var2.f2370N.requestLayout();
        }
    }

    public class View$OnClickListenerC0702h implements View.OnClickListener {
        View$OnClickListenerC0702h() {
        }

        @Override
        public void onClick(View view) {
            Page_QMS_Talk.this.m417l0();
        }
    }

    public class View$OnClickListenerC0703i implements View.OnClickListener {
        View$OnClickListenerC0703i() {
        }

        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(Page_QMS_Talk.this.bbEditor.f2030d.getText().toString())) {
                Page_QMS_Talk.this.bbEditor.f2030d.setEnabled(false);
                Page_QMS_Talk.this.f2370N.findViewById(R.id.qmsSend).setEnabled(false);
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                DocumentManager.getResultRequest(new QmsSendPostRequest(p0Var, p0Var.bbEditor.f2030d.getText().toString(), null));
            }
        }
    }

    public class C0704j implements TextWatcher {
        C0704j() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            int length = editable.length();
            Page_QMS_Talk.this.f2365I.setVisibility(length > 0 ? 8 : 0);
            Page_QMS_Talk p0Var = Page_QMS_Talk.this;
            p0Var.f2367K.setEnabled(length > 0 && p0Var.f2364H.isEnabled());
            if (length >= 3) {
                Page_QMS_Talk.this.m412q0(0);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class View$OnClickListenerC0705k implements View.OnClickListener {
        View$OnClickListenerC0705k() {
        }

        @Override
        public void onClick(View view) {
            Page_QMS_Talk.this.bbEditor.m523b();
        }
    }

    class RunnableC0706l implements Runnable {
        RunnableC0706l() {
        }

        @Override
        public void run() {
            Page_QMS_Talk.this.m813W(0);
        }
    }

    class C0707m implements TextWatcher {
        final DlgSimple f2412a;

        C0707m(Page_QMS_Talk p0Var, DlgSimple q1Var) {
            this.f2412a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2412a.m625a(editable.length() >= 3);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class View$OnClickListenerC0708n implements View.OnClickListener {
        final DlgSimple f2413a;

        View$OnClickListenerC0708n(DlgSimple q1Var) {
            this.f2413a = q1Var;
        }

        @Override
        public void onClick(View view) {
            Page_QMS_Talk p0Var = Page_QMS_Talk.this;
            p0Var.tab.addPage(new Page_QMS_Talk(p0Var.mainActivity, p0Var.dialogId, this.f2413a.editText.getText().toString()));
        }
    }

    class View$OnClickListenerC0709o implements View.OnClickListener {
        View$OnClickListenerC0709o() {
        }

        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(Page_QMS_Talk.this.f2361E)) {
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                p0Var.tab.addPage(new Page_QMS_Talk(p0Var.mainActivity, p0Var.dialogId));
            }
        }
    }

    class C0710p implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0710p() {
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            int messageId;
            if (1 == cVar2.f2511a) {
                int i2 = cVar2.f2512b;
                Page_QMS_Talk p0Var = Page_QMS_Talk.this;
                if (i2 == p0Var.dialogId) {
                    if (p0Var.qmsMessageSparseArray.size() > 0) {
                        Page_QMS_Talk p0Var2 = Page_QMS_Talk.this;
                        int i3 = p0Var2.f2381Y;
                        SparseArray<QmsMessage> sparseArray = Page_QMS_Talk.this.qmsMessageSparseArray;
                        p0Var2.f2381Y = Math.max(i3, sparseArray.keyAt(sparseArray.size() - 1));
                    }
                    if (cVar2.f2517g > Page_QMS_Talk.this.f2381Y) {
                        Page_QMS_Talk.this.f2381Y = cVar2.f2517g;
                        if (Page_QMS_Talk.this.isCurrentTab() && Page_QMS_Talk.this.tab.m717i()) {
                            Page_QMS_Talk p0Var3 = Page_QMS_Talk.this;
                            if (!p0Var3.mainActivity.f733d) {
                                p0Var3.tab.m721e(false, 0);
                                if (Page_QMS_Talk.this.qmsMessageSparseArray.size() > 0) {
                                    SparseArray<QmsMessage> sparseArray2 = Page_QMS_Talk.this.qmsMessageSparseArray;
                                    messageId = sparseArray2.valueAt(sparseArray2.size() - 1).messageId;
                                } else {
                                    messageId = 0;
                                }
                                DocumentManager.getResultRequest(new QmsLoadMessages(Page_QMS_Talk.this, messageId, 0, true));
                            }
                        }
                        Page_QMS_Talk.this.f2380X = true;
                    }
                }
            }
            return Boolean.TRUE;
        }
    }

    public class QmsUploadFile extends DocumentManager.IGenerateRequest {
        AttachDialog.C0553e f2417g;

        QmsUploadFile(AttachDialog.C0553e eVar) {
            super(30054);
            this.f2417g = eVar;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            AttachDialog.C0553e eVar = this.f2417g;
            if (!eVar.f1876h) {
                if (status == 0) {
                    Page_QMS_Talk.this.m415n0(eVar, uVar.getInt(0));
                } else if (4 == status) {
                    Page_QMS_Talk.this.m412q0(1);
                    DocumentManager.getResultRequest(new QmsUploadFileRequest(this.f2417g));
                } else {
                    Page_QMS_Talk.this.m416m0(eVar, status);
                }
            }
        }

        @Override
        public Document generate() {
            AttachDialog.C0553e eVar = this.f2417g;
            return new Document(eVar.f1869a, (int) eVar.f1873e, this.f2417g.f1874f, 1);
        }
    }

    private class AsyncTaskC0712r extends AsyncTask<AttachDialog.C0553e, Void, AttachDialog.C0553e> {
        private AsyncTaskC0712r() {
        }

        public AttachDialog.C0553e doInBackground(AttachDialog.C0553e... eVarArr) {
            if (eVarArr.length != 1) {
                return null;
            }
            AttachDialog.C0553e eVar = eVarArr[0];
            try {
                InputStream openInputStream = Page_QMS_Talk.this.mainActivity.getContentResolver().openInputStream(eVar.f1872d);
                if (openInputStream != null) {
                    Page_QMS_Talk.this.m412q0(1);
                    eVar.f1873e = 0;
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = openInputStream.read(bArr, 0, 4096);
                        if (read <= 0) {
                            break;
                        }
                        eVar.f1873e += read;
                        instance.update(bArr, 0, read);
                    }
                    openInputStream.close();
                    byte[] digest = instance.digest();
                    eVar.f1874f = String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X", Integer.valueOf(digest[0] & 255), Integer.valueOf(digest[1] & 255), Integer.valueOf(digest[2] & 255), Integer.valueOf(digest[3] & 255), Integer.valueOf(digest[4] & 255), Integer.valueOf(digest[5] & 255), Integer.valueOf(digest[6] & 255), Integer.valueOf(digest[7] & 255), Integer.valueOf(digest[8] & 255), Integer.valueOf(digest[9] & 255), Integer.valueOf(digest[10] & 255), Integer.valueOf(digest[11] & 255), Integer.valueOf(digest[12] & 255), Integer.valueOf(digest[13] & 255), Integer.valueOf(digest[14] & 255), Integer.valueOf(digest[15] & 255));
                }
            } catch (Exception e) {
                e.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(e);
            }
            return eVar;
        }

        public void onPostExecute(AttachDialog.C0553e eVar) {
            if (eVar.f1874f != null) {
                DocumentManager.getResultRequest(new QmsUploadFile(eVar));
                Page_QMS_Talk.this.f2364H.setText(eVar.f1869a);
                return;
            }
            Page_QMS_Talk.this.m419j0();
            Toast.makeText(Page_QMS_Talk.this.mainActivity, "Ошибка открытия файла. Попробуйте использовать другой файловый менеджер.", 1).show();
        }

        AsyncTaskC0712r(Page_QMS_Talk p0Var, View$OnClickListenerC0702h hVar) {
            this();
        }
    }

    private class QmsUploadFileRequest extends DocumentManager.IGenerateRequest {
        AttachDialog.C0553e f2420g;
        InputStream f2422i;
        int f2423j;
        int f2421h = 0;
        String attachmentName = "att-" + DocumentManager.getMemberId() + "-" + SystemClock.elapsedRealtime();

        class RunnableC0714a implements Runnable {
            final long f2426a;

            RunnableC0714a(long j) {
                this.f2426a = j;
            }

            @Override
            public void run() {
                Page_QMS_Talk.this.m418k0((int) this.f2426a);
            }
        }

        class RunnableC0715b implements Runnable {
            final MainActivity f2428a;

            RunnableC0715b(MainActivity mainActivity) {
                this.f2428a = mainActivity;
            }

            @Override
            public void run() {
                QmsUploadFileRequest sVar = QmsUploadFileRequest.this;
                sVar.f2421h = 0;
                Page_QMS_Talk.this.m419j0();
                Toast.makeText(this.f2428a, "Ошибка чтения файла", 1).show();
            }
        }

        QmsUploadFileRequest(AttachDialog.C0553e eVar) {
            super(30067);
            this.f2420g = eVar;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            AttachDialog.C0553e eVar = this.f2420g;
            if (!eVar.f1876h) {
                if (status == 0) {
                    int i2 = this.f2421h;
                    if (i2 == 0 || 1 == i2) {
                        int intValue = uVar.getInt(0);
                        this.f2423j = intValue;
                        int i3 = this.f2421h;
                        if (i3 == 0) {
                            this.f2421h = 1;
                        } else if (1 == i3 && ((long) intValue) >= this.f2420g.f1873e) {
                            this.f2421h = 2;
                        }
                        DocumentManager.getResultRequest(this);
                        return;
                    }
                    Page_QMS_Talk.this.m415n0(eVar, uVar.getInt(0));
                    return;
                }
                Page_QMS_Talk.this.m416m0(eVar, status);
            }
        }

        @Override
        public void onErrorUploadFile() {
            super.onErrorUploadFile();
            if (this.f2421h == 1) {
                this.f2421h = 0;
                try {
                    this.f2422i.close();
                } catch (Exception unused) {
                }
                this.f2422i = null;
            }
        }

        @Override
        public int onUploadFile(byte[] bytes, int len) {
            Exception e;
            MainActivity mainActivity = Page_QMS_Talk.this.mainActivity;
            int i2 = 0;
            if (mainActivity != null) {
                try {
                    if (this.f2422i == null) {
                        InputStream openInputStream = mainActivity.getContentResolver().openInputStream(this.f2420g.f1872d);
                        this.f2422i = openInputStream;
                        openInputStream.skip(this.f2423j);
                    }
                    int read = this.f2422i.read(bytes, 0, len);
                    if (read > 0) {
                        try {
                            AttachDialog.C0553e eVar = this.f2420g;
                            long j = (((long) this.f2423j) * 100) / eVar.f1873e;
                            if (j / 5 != eVar.f1877i / 5) {
                                mainActivity.runOnUiThread(new RunnableC0714a(j));
                                this.f2420g.f1877i = j;
                            }
                            this.f2423j += read;
                            if (this.f2420g.f1876h) {
                                this.f2421h = 0;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            i2 = read;
                            //ACRA.getErrorReporter().handleSilentException(e);
                            mainActivity.runOnUiThread(new RunnableC0715b(mainActivity));
                            if (this.f2421h != 0) {
                            }
                            try {
                                this.f2422i.close();
                            } catch (Exception unused) {
                            }
                            this.f2422i = null;
                            return i2;
                        }
                    }
                    i2 = read;
                } catch (Exception e3) {
                    e = e3;
                }
            } else {
                this.f2421h = 0;
            }
            if (this.f2421h != 0 || ((long) this.f2423j) >= this.f2420g.f1873e) {
                try {
                    this.f2422i.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                this.f2422i = null;
            }
            return i2;
        }

        @Override
        public int isFileSize() {
            if (1 == this.f2421h) {
                return ((int) this.f2420g.f1873e) - this.f2423j;
            }
            return 0;
        }

        @Override
        public Document generate() {
            int i = this.f2421h;
            if (i == 0 || 1 == i) {
                return new Document(this.attachmentName);
            }
            AttachDialog.C0553e eVar = this.f2420g;
            return new Document(this.attachmentName, "fu", eVar.f1869a, (int) eVar.f1873e, this.f2420g.f1874f, 1);
        }
    }

    public static class QmsLoadMessages extends API.QmsThreadRequest {
        Page_QMS_Talk pageQmsTalk;
        int qmsLoadSize;
        boolean reloadAll;
        List<QmsMessage> qmsMessages;
        int[] f2434n;

        class RunnableC0717a implements Runnable {
            RunnableC0717a() {
            }

            @Override
            public void run() {
                QmsLoadMessages.this.pageQmsTalk.tab.forumsListView.setTranscriptMode(2);
            }
        }

        QmsLoadMessages(Page_QMS_Talk p0Var, int messageId, int loadSize, boolean needReload) {
            super(p0Var.dialogId, messageId, loadSize);
            this.pageQmsTalk = p0Var;
            this.qmsLoadSize = loadSize;
            if (loadSize < 0) {
                p0Var.f2378V = true;
            } else if (loadSize > 0) {
                p0Var.f2379W = true;
            }
            this.reloadAll = needReload;
            this.statusMessage = "Загрузка сообщений";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_QMS_Talk p0Var = this.pageQmsTalk;
            if (!p0Var.isLoading) {
                if (status == 0) {
                    if (this.qmsMessages != null) {
                        p0Var.currentDocument = uVar;
                        for (int i2 = 0; i2 < this.qmsMessages.size(); i2++) {
                            QmsMessage uVar2 = this.qmsMessages.get(i2);
                            this.pageQmsTalk.qmsMessageSparseArray.put(uVar2.messageId, uVar2);
                        }
                        this.pageQmsTalk.m413p0(uVar.getInt(12), this.qmsMessages.size());
                        this.pageQmsTalk.m411r0();
                    }
                    if (this.pageQmsTalk.isCurrentTab()) {
                        if (this.reloadAll) {
                            this.pageQmsTalk.tabLoaded(true);
                        } else {
                            this.pageQmsTalk.tab.forumsListView.setTranscriptMode(0);
                            this.pageQmsTalk.m814V();
                            this.pageQmsTalk.tabLoaded(true);
                            Page_QMS_Talk p0Var2 = this.pageQmsTalk;
                            int size = this.qmsLoadSize < 0 ? this.qmsMessages.size() : 0;
                            Page_QMS_Talk p0Var3 = this.pageQmsTalk;
                            p0Var2.m812X(size + p0Var3.f1090x, p0Var3.f1089w);
                            this.pageQmsTalk.tab.mainLayout.postDelayed(new RunnableC0717a(), 1);
                        }
                    }
                } else {
                    Toast.makeText(p0Var.mainActivity, "Ошибка загрузки сообщений", 0).show();
                }
                int i3 = this.qmsLoadSize;
                if (i3 < 0) {
                    this.pageQmsTalk.f2378V = false;
                } else if (i3 > 0) {
                    this.pageQmsTalk.f2379W = false;
                }
            }
        }

        @Override
        public void getResult(int status, Document uVar) {
            Document l;
            Page_QMS_Talk p0Var = this.pageQmsTalk;
            if (!p0Var.isLoading && status == 0 && p0Var.isUnsucces() && (l = uVar.getDocument(13)) != null) {
                if (l.count() < Math.abs(this.qmsLoadSize)) {
                    int i2 = this.qmsLoadSize;
                    if (i2 < 0) {
                        this.pageQmsTalk.f2376T = true;
                    } else if (i2 > 0) {
                        this.pageQmsTalk.f2377U = true;
                    }
                }
                this.qmsMessages = new Vector(Math.abs(this.limit));
                int width = (this.pageQmsTalk.mainActivity.mainLayout.getWidth() - (QmsMessage.f2438k * 2)) - QmsMessage.f2439l;
                for (int i3 = 0; i3 < l.count(); i3++) {
                    Document l2 = l.getDocument(i3);
                    try {
                        QmsMessage a = QmsMessage.parseQmsMessage(l2);
                        if (a != null) {
                            a.f2440a = Util.m663k(this.pageQmsTalk.mainActivity, a.f2446g, width) + QmsMessage.f2437j;
                            this.qmsMessages.add(a);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                       /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Lost Message add ");
                        sb.append(l2 != null ? l2.m282m(0).intValue() : 0);
                        errorReporter.handleSilentException(new Exception(sb.toString(), e));*/
                    }
                }
                this.f2434n = new int[this.qmsMessages.size()];
                int i4 = QmsMessage.f2437j / 2;
                int width2 = (this.pageQmsTalk.mainActivity.mainLayout.getWidth() - (QmsMessage.f2438k * 2)) - QmsMessage.f2439l;
                for (int i5 = 0; i5 < this.qmsMessages.size(); i5++) {
                    int[] iArr = this.f2434n;
                    i4 += Util.m663k(this.pageQmsTalk.mainActivity, this.qmsMessages.get(i5).f2446g, width2) + QmsMessage.f2437j;
                    iArr[i5] = i4;
                }
            }
        }
    }

    public static class QmsMessage {
        static BBString.C0674e f2436i;
        public static int f2437j;
        public static int f2438k;
        public static int f2439l;
        int f2440a;
        public int messageId;
        public int opponentId;
        int f2443d;
        public String f2444e;
        String f2445f;
        public BBString f2446g;
        public boolean f2447h;

        public static class C0719a extends BBString.C0674e {
            public C0719a() {
                float f = BBString.f2189v0;
                this.f2263g = (float) ((int) (f * 12.0f));
                this.f2264h = (float) (((int) (f * 12.0f * 2.0f)) + Util.m672b("W", 240, 13.0f, false));
                float f2 = BBString.f2189v0;
                this.f2265i = (int) (f2 * 12.0f);
                this.f2266j = (int) (f2 * 12.0f);
            }
        }

        QmsMessage() {
        }

        public static QmsMessage parseQmsMessage(Document uVar) {
            String n = uVar.getString(3);
            if (n == null) {
                return null;
            }
            QmsMessage uVar2 = new QmsMessage();
            BBString.C0670a[] c = Page_Topic.PostModel.m97c(uVar.getDocument(4));
            if (c != null && c.length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(n);
                sb.append(n.length() > 0 ? "\n[NA]" : "[NA]");
                n = sb.toString();
            }
            uVar2.messageId = uVar.getInt(0);
            uVar2.opponentId = uVar.getInt(1);
            int intValue = uVar.getInt(2);
            uVar2.f2443d = intValue;
            uVar2.f2444e = Util.formatDate(intValue);
            uVar2.f2445f = Util.formatDate(uVar2.f2443d, false, true);
            BBString x = BBString.getBBString(n, c);
            uVar2.f2446g = x;
            if (x == null) {
                return null;
            }
            if (f2436i == null) {
                f2436i = new C0719a();
            }
            BBString pVar = uVar2.f2446g;
            pVar.f2221a0 = uVar2;
            pVar.f2246z = f2436i;
            return uVar2;
        }
    }

    public static class QmsSendPostRequest extends API.QmsPostRequest {
        Page_QMS_Talk pageQmsTalk;

        public QmsSendPostRequest(Page_QMS_Talk p0Var, String message, List<Integer> list) {
            super(p0Var.dialogId, message, list);
            this.pageQmsTalk = p0Var;
            this.statusMessage = "Отправка поста";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            int messageId;
            Page_QMS_Talk p0Var = this.pageQmsTalk;
            if (!p0Var.isLoading) {
                if (status != 0 || !p0Var.isUnsucces()) {
                    Toast.makeText(this.pageQmsTalk.mainActivity, "Ошибка при отправке сообщения", 0).show();
                    return;
                }
                EditText editText = this.pageQmsTalk.f2370N.findViewById(R.id.qmsMsg);
                editText.setText("");
                editText.setEnabled(true);
                if (this.pageQmsTalk.qmsMessageSparseArray.size() > 0) {
                    SparseArray<QmsMessage> sparseArray = this.pageQmsTalk.qmsMessageSparseArray;
                    messageId = sparseArray.valueAt(sparseArray.size() - 1).messageId;
                } else {
                    messageId = 0;
                }
                DocumentManager.getResultRequest(new QmsLoadMessages(this.pageQmsTalk, messageId, 0, true));
            }
        }
    }

    public static class QmsSearchRequest extends API.QmsThreadRequest {
        Page_QMS_Talk f2449j;

        QmsSearchRequest(Page_QMS_Talk p0Var, int i) {
            super(p0Var.dialogId, i, -1);
            this.f2449j = p0Var;
            this.statusMessage = "Поиск сообщения";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_QMS_Talk p0Var = this.f2449j;
            if (!p0Var.isLoading && p0Var.isCurrentTab()) {
                int i2 = 0;
                if (status == 0) {
                    Document l = uVar.getDocument(13);
                    if (l != null && l.count() > 0) {
                        i2 = l.getDocument(0).getInt(0);
                    }
                    Page_QMS_Talk p0Var2 = this.f2449j;
                    p0Var2.tab.addPage(new Page_QMS_Talk(p0Var2.mainActivity, p0Var2.dialogId, i2));
                    return;
                }
                Toast.makeText(this.f2449j.mainActivity, "Ошибка поиска сообщения", 0).show();
            }
        }
    }

    public Page_QMS_Talk(MainActivity mainActivity, int i) {
        this(mainActivity, i, -1);
    }

    @SuppressLint({"NewApi"})
    public void m417l0() {
        if (Build.VERSION.SDK_INT < 23 || this.mainActivity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
            AttachDialog.m585s(this.mainActivity);
        } else {
            this.mainActivity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        }
    }

    private void m414o0(int i, int i2, String str) {
        this.iconId = R.drawable.ic_nav_qms;
        this.dialogId = i;
        if (!TextUtils.isEmpty(str)) {
            this.f2361E = str;
            this.title = "Поиск в диалоге " + Integer.valueOf(i).toString();
            this.statusMessage = "Поиск в диалоге";
            this.f2362F = -1;
            this.f2376T = true;
            return;
        }
        this.title = "Диалог " + Integer.valueOf(i).toString();
        this.statusMessage = "Загрузка диалога";
        this.f2362F = i2;
        if (-1 == i2) {
            this.f2377U = true;
        }
        ViewGroup viewGroup = (ViewGroup) this.mainActivity.getLayoutInflater().inflate(R.layout.qmsreplyform, this.mainActivity.mainLayout, false);
        this.f2370N = viewGroup;
        this.f2363G = viewGroup.findViewById(R.id.qmsSmiles);
        View findViewById = this.f2370N.findViewById(R.id.qmsAttach);
        this.f2365I = findViewById;
        findViewById.setClickable(true);
        this.f2365I.setOnClickListener(new View$OnClickListenerC0702h());
        View findViewById2 = this.f2370N.findViewById(R.id.qmsSend);
        this.f2367K = findViewById2;
        findViewById2.setClickable(true);
        this.f2367K.setEnabled(false);
        this.f2367K.setOnClickListener(new View$OnClickListenerC0703i());
        EditText editText = this.f2370N.findViewById(R.id.qmsMsg);
        this.f2364H = editText;
        editText.setImeOptions(1);
        this.f2364H.addTextChangedListener(new C0704j());
        this.bbEditor = new BBEditor(this.mainActivity, this.f2370N, this.f2364H, false);
        this.f2363G.setOnClickListener(new View$OnClickListenerC0705k());
        this.f2366J = this.f2370N.findViewById(R.id.qmsProgress);
    }

    public void m413p0(int i, int i2) {
        int size = this.qmsMessageSparseArray.size() - 1;
        for (int i3 = size; i3 >= 0; i3--) {
            QmsMessage valueAt = this.qmsMessageSparseArray.valueAt(i3);
            if (valueAt.opponentId != this.myId) {
                if (i > 0) {
                    valueAt.f2447h = true;
                    i--;
                } else if (valueAt.f2447h) {
                    valueAt.f2447h = false;
                } else if (i3 < (size - i2) - i3) {
                    return;
                }
            }
        }
    }

    public void m411r0() {
        this.f1071B = new int[this.qmsMessageSparseArray.size()];
        int i = QmsMessage.f2437j / 2;
        for (int i2 = 0; i2 < this.qmsMessageSparseArray.size(); i2++) {
            int[] iArr = this.f1071B;
            i += this.qmsMessageSparseArray.valueAt(i2).f2440a;
            iArr[i2] = i;
        }
        if (this.qmsMessageSparseArray.size() > 0) {
            this.f1071B[this.qmsMessageSparseArray.size() - 1] = i + (QmsMessage.f2437j / 2);
        }
    }

    @Override
    public void onSearchBar() {
        this.tab.m714l(null, false);
        Tab.ForumsListView gVar = this.tab.forumsListView;
        gVar.f1474b.setBackgroundDrawable(gVar.f1475c);
        this.tab.forumsListView.setOverscrollHeader(null);
        this.tab.m721e(false, 0);
        this.mainActivity.m898h(null);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoaded() {
        if (this.isLoading) {
            return false;
        }
        this.myId = this.currentDocument.getInt(4);
        this.opponentTitle = Util.C0427h.UnEscapeString(this.currentDocument.getString(5));
        this.title = Util.C0427h.UnEscapeString(this.currentDocument.getString(3));
        this.avatarUrl = DocumentManager.getMemberInfoModel().memberAvatar;
        this.f2375S = this.currentDocument.getString(7);
        Document l = this.currentDocument.getDocument(13);
        if (l == null) {
            return false;
        }
        SparseArray<QmsMessage> sparseArray = new SparseArray<>(l.count());
        int measuredWidth = (this.mainActivity.mainLayout.getMeasuredWidth() - (QmsMessage.f2438k * 2)) - QmsMessage.f2439l;
        for (int i = 0; i < l.count(); i++) {
            Document uVar = null;
            try {
                uVar = l.getDocument(i);
                QmsMessage qmsMessage = QmsMessage.parseQmsMessage(uVar);
                if (qmsMessage != null) {
                    qmsMessage.f2440a = Util.m663k(this.mainActivity, qmsMessage.f2446g, measuredWidth) + QmsMessage.f2437j;
                    sparseArray.put(qmsMessage.messageId, qmsMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
               /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                StringBuilder sb = new StringBuilder();
                sb.append("Lost Message ");
                sb.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                errorReporter.handleSilentException(new Exception(sb.toString(), e));*/
            }
        }
        if (sparseArray.size() < 60) {
            if (this.f2361E != null || this.f2362F >= 0) {
                this.f2377U = true;
            } else {
                this.f2376T = true;
            }
        }
        this.qmsMessageSparseArray = sparseArray;
        m413p0(this.currentDocument.getInt(12), 0);
        m411r0();
        return true;
    }

    @Override
    public void mo142J(boolean z) {
        ViewGroup viewGroup;
        if (isCurrentTab() && this.myId > 0 && (viewGroup = this.f2370N) != null) {
            this.tab.m714l(viewGroup, false);
        }
        super.mo142J(z);
        if (isCurrentTab()) {
            this.tab.forumsListView.f1474b.setBackgroundDrawable(Skin.C0353a.f1390j0.getConstantState().newDrawable());
            if (Build.VERSION.SDK_INT > 9) {
                this.tab.forumsListView.setOverscrollHeader(Skin.C0353a.f1390j0);
            }
            if (this.f2362F >= 0) {
                this.tab.forumsListView.post(new RunnableC0706l());
                this.f2362F = -1;
            }
        }
        if (isCurrentTab() && this.tab.m717i()) {
            this.tab.mainLayout.f801I = false;
        }
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0700f());
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 2, "В закладки", DataDB.m366k(getLink()));
            if (this.myId > 0) {
                o1Var.addMenuItem(0, 0, 3, "Профиль собеседника");
            }
            o1Var.addMenuItem(0, 0, 4, "Скопировать сообщения");
        }
        o1Var.show(view);
    }

    @Override
    public void showSearchDialog(View view) {
        DlgSimple q1Var = new DlgSimple(this.mainActivity, "Поиск по сообщениям", false, "ПОИСК", "СБРОС");
        q1Var.editText.setText(this.f2361E);
        q1Var.m625a(!TextUtils.isEmpty(this.f2361E) && this.f2361E.length() >= 3);
        q1Var.editText.addTextChangedListener(new C0707m(this, q1Var));
        this.mainActivity.mainLayout.m859w(q1Var.editText);
        q1Var.m620f(new View$OnClickListenerC0708n(q1Var), true);
        q1Var.m621e(new View$OnClickListenerC0709o(), true);
        q1Var.show(true, true, true);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        ViewGroup viewGroup;
        super.mo54M(f1Var, z);
        if (this.f2380X) {
            tabReload();
        }
        if (!z) {
            if (isUnsucces() && this.myId > 0 && (viewGroup = this.f2370N) != null) {
                this.tab.m714l(viewGroup, false);
            }
            this.tab.forumsListView.setTranscriptMode(2);
            this.tab.forumsListView.setStackFromBottom(true);
            if (isUnsucces()) {
                this.tab.forumsListView.f1474b.setBackgroundDrawable(Skin.C0353a.f1390j0.getConstantState().newDrawable());
                this.tab.forumsListView.setOverscrollHeader(Skin.C0353a.f1390j0);
            } else {
                this.tab.forumsListView.f1474b.setBackgroundDrawable(null);
            }
            if (this.f2370N != null) {
                ((RelativeLayout.LayoutParams) this.f2364H.getLayoutParams()).topMargin = Prefs.f1177o ? 0 : (int) (this.mainActivity.f731b * 16.0f);
                this.f2370N.findViewById(R.id.qmsTagsBar).setVisibility(Prefs.f1177o ? 0 : 8);
            }
            this.tab.mainLayout.f801I = false;
            this.mainActivity.m898h(this);
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        if (i > 0) {
            DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
            QmsMessage uVar = (QmsMessage) pVar.f2221a0;
            StringBuilder sb = new StringBuilder();
            sb.append("[quote name=\"");
            sb.append((uVar.opponentId == this.myId ? this.opponentTitle : L.memberInfoName).replace("\\", "\\\\").replace("\"", "\\\""));
            sb.append("\" date=\"");
            sb.append(uVar.f2445f);
            sb.append("\"]");
            sb.append(str);
            sb.append("[/quote]\r\n");
            String sb2 = sb.toString();
            EditText editText = this.f2364H;
            editText.setText(this.f2364H.getText().toString() + sb2);
            EditText editText2 = this.f2364H;
            editText2.setSelection(editText2.getText().length());
            return;
        }
        Util.copyToClipboard(this.mainActivity, str, "Текст скопирован в буфер");
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        if (this.f2361E != null) {
            return true;
        }
        Unread2.f1568b.m654a(this.f2382Z);
        Unread2.f1569c.m657a(this.f2383a0);
        DocumentManager.f2750G.m654a(this.f2385c0);
        DocumentManager.f2751H.m654a(this.f2384b0);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        if (this.f2361E != null) {
            return true;
        }
        DocumentManager.f2751H.m653b(this.f2384b0);
        DocumentManager.f2750G.m653b(this.f2385c0);
        Unread2.f1569c.m656b(this.f2383a0);
        Unread2.f1568b.m653b(this.f2382Z);
        return true;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i;
        int i2;
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new DeleteMessageClickListener(bBDisplay, pVar, cVar), true);
        int intValue = (Integer) bBDisplay.getTag();
        int i3 = cVar.f543a;
        if (-1 != i3) {
            String str = pVar.f2202I.get(i3).link;
            i = 0;
            o1Var.addMenuItem(0, intValue, 0, Util.C0427h.m640d(str), true, false);
            o1Var.addMenuItem(0, intValue, 26, "Копировать ссылку");
            if (Urls2.is4pdaHost(str)) {
                o1Var.addMenuItem(0, intValue, 21, "Открыть в новой вкладке");
            }
            o1Var.addMenuItem(0, intValue, 27, "Открыть с помощью");
        } else {
            i = 0;
        }
        int i4 = cVar.f544b;
        if (i4 >= 0) {
            BBString.C0670a aVar = pVar.f2212S[i4];
            if (!aVar.f2249c) {
                o1Var.addMenuItem(0, 0, 0, "https://4pda.ru/forum/dl/post/" + aVar.f2247a + "/" + aVar.f2254h, true, false);
                o1Var.addMenuItem(i, i, 26, "Копировать ссылку");
                o1Var.addMenuItem(i, i, 32, "Загрузить файл");
            } else if (aVar.f2253g > 0) {
                o1Var.addMenuItem(i, i, 30, "Открыть изображение");
            }
        }
        int i5 = cVar.f545c;
        if (i5 < 0 || (!TextUtils.isEmpty(pVar.f2208O[i5].f2279c) && !pVar.f2208O[cVar.f545c].f2289m)) {
            i2 = -1;
        } else {
            i2 = -1;
            cVar.f545c = -1;
        }
        int i6 = cVar.f545c;
        if (i6 >= 0) {
            if (bBDisplay.f531h[i6] == null) {
                o1Var.addMenuItem(i, intValue, 24, "Загрузить изображение");
            }
            o1Var.addMenuItem(i, i, 31, "Сохранить изображение");
        }
        if (cVar.f543a == i2 && cVar.f544b == i2 && cVar.f545c == i2 && cVar.f546d == i2) {
            if (bBDisplay.f528e.m948l()) {
                if (this.f2370N != null) {
                    o1Var.addMenuItem(i, intValue, 3, "Копировать/Цитировать");
                } else {
                    o1Var.addMenuItem(i, intValue, 4, "Копировать");
                }
            }
            if (this.myId > 0) {
                o1Var.addMenuItem(i, intValue, 1, "Удалить сообщение");
            }
            o1Var.addMenuItem(i, intValue, 2, "Копировать сообщение");
        }
        o1Var.show(null);
    }

    @Override
    public void mo426e(Uri uri, String str) {
        try {
            AttachDialog.C0553e eVar = new AttachDialog.C0553e(uri, str);
            this.f2363G.setEnabled(false);
            this.f2365I.setEnabled(false);
            this.f2367K.setEnabled(false);
            this.f2391i0 = this.f2364H.getText().toString();
            this.f2364H.setEnabled(false);
            EditText editText = this.f2364H;
            editText.setText("? " + eVar.f1869a);
            m418k0(0);
            this.f2366J.setVisibility(0);
            new AsyncTaskC0712r(this, null).execute(eVar);
        } catch (Exception unused) {
            Toast.makeText(this.mainActivity, "Ошибка файла", 1).show();
        }
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f544b;
        if (i >= 0) {
            BBString.C0670a aVar = pVar.f2212S[i];
            if (!aVar.f2249c) {
                DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVar.f2247a, this.mainActivity, aVar.f2254h));
            } else if (aVar.f2253g > 0) {
                new ImageDialog(this.mainActivity).m916c(Collections.singletonList(new ImageDialog.C0174m(aVar.f2247a, null, aVar.f2254h)), 0);
            } else {
                new ImageDialog(this.mainActivity).m917b(aVar.f2250d);
            }
        } else {
            int i2 = cVar.f543a;
            if (i2 >= 0) {
                BBString.C0681k kVar = pVar.f2202I.get(i2);
                int i3 = kVar.statusCode;
                int i4 = 2;
                if (i3 == 1) {
                    MainActivity mainActivity = this.mainActivity;
                    String str = kVar.link;
                    if (!this.f2387e0) {
                        i4 = 1;
                    }
                    Page b = Urls2.openPage(mainActivity, str, false, i4);
                    if (b != null) {
                        if (this.f2387e0) {
                            Tab f1Var = new Tab(this.mainActivity);
                            f1Var.addPage(b);
                            this.mainActivity.mainLayout.setCurrentTab(f1Var);
                        } else {
                            this.tab.addPage(b);
                        }
                    }
                    this.f2387e0 = false;
                } else if (i3 == 2) {
                    Util.sendMail(this.mainActivity, kVar.link, this.title);
                }
            } else if (this.f2361E != null) {
                DocumentManager.getResultRequest(new QmsSearchRequest(this, this.qmsMessageSparseArray.valueAt((Integer) bBDisplay.getTag()).messageId));
            }
        }
    }

    @Override
    public boolean mo423g(Intent intent) {
        return false;
    }

    @Override
    public int getCount() {
        SparseArray<QmsMessage> sparseArray;
        if (!isUnsucces() || (sparseArray = this.qmsMessageSparseArray) == null) {
            return 0;
        }
        return sparseArray.size();
    }

    @Override
    public long getItemId(int i) {
        return this.qmsMessageSparseArray.keyAt(i);
    }

    @Override
    public int getItemViewType(int i) {
        return this.myId == this.qmsMessageSparseArray.valueAt(i).opponentId ? 1 : 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        QmsMessage valueAt = null;
        Widgets$AvatarView avatarView = null;
        int i2 = 0;
        try {
            valueAt = this.qmsMessageSparseArray.valueAt(i);
            if (view == null) {
                view = this.mainActivity.getLayoutInflater().inflate(valueAt.opponentId == this.myId ? R.layout.qmsoppomessage : R.layout.qmsmymessage, viewGroup, false);
                BBDisplay bBDisplay = view.findViewById(R.id.messageCode);
                BBOverlay bBOverlay = view.findViewById(R.id.messageOverlay);
                bBDisplay.setBackgroundDrawable(this.mainActivity.skin.m736f(valueAt.opponentId == this.myId ? R.drawable.bubble_oppo : R.drawable.bubble_my));
                bBDisplay.setOverlay(bBOverlay);
                bBDisplay.setCallback(this);
            }
            BBDisplay bBDisplay2 = view.findViewById(R.id.messageCode);
            bBDisplay2.setTag(i);
            bBDisplay2.setBBString(valueAt.f2446g);
            avatarView = view.findViewById(R.id.messageAvatar);
            i2 = valueAt.opponentId;
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(new Exception("QmsTaskPage.GetView", e));
        }
        if (!(i2 == 0 || i2 == 17927)) {
            String str = i2 == this.myId ? this.f2375S : this.avatarUrl;
            if (!TextUtils.isEmpty(str)) {
                PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, str);
                l.to(avatarView);
                l.disableAnimation(!Prefs.f1146G);
                l.placeholder(this.mainActivity.skin.m736f(R.drawable.ic_avatar));
                l.fade(4, 200, false);
                l.runAsync();
            }
            TextView textView = view.findViewById(R.id.messageDate);
            textView.setText(valueAt.f2444e);
            textView.setCompoundDrawablesWithIntrinsicBounds(!valueAt.f2447h ? this.mainActivity.skin.m736f(R.drawable.ic_qms_unread) : null, null, null, null);
            view.setPadding(QmsMessage.f2438k, i != 0 ? QmsMessage.f2437j : QmsMessage.f2437j / 2, QmsMessage.f2438k, i != this.qmsMessageSparseArray.size() - 1 ? QmsMessage.f2437j : QmsMessage.f2437j / 2);
            if (i >= 30 && !this.f2376T && !this.f2378V) {
                DocumentManager.getResultRequest(new QmsLoadMessages(this, this.qmsMessageSparseArray.valueAt(0).messageId, -60, false));
            } else if (i > this.qmsMessageSparseArray.size() - 30 && !this.f2377U && !this.f2379W) {
                SparseArray<QmsMessage> sparseArray = this.qmsMessageSparseArray;
                DocumentManager.getResultRequest(new QmsLoadMessages(this, sparseArray.valueAt(sparseArray.size() - 1).messageId, 60, false));
            }
            return view;
        }
        avatarView.setImageDrawable(this.mainActivity.skin.m736f(R.drawable.ic_launcher));
        TextView textView2 = view.findViewById(R.id.messageDate);
        textView2.setText(valueAt.f2444e);
        textView2.setCompoundDrawablesWithIntrinsicBounds(!valueAt.f2447h ? this.mainActivity.skin.m736f(R.drawable.ic_qms_unread) : null, null, null, null);
        view.setPadding(QmsMessage.f2438k, i != 0 ? QmsMessage.f2437j : QmsMessage.f2437j / 2, QmsMessage.f2438k, i != this.qmsMessageSparseArray.size() - 1 ? QmsMessage.f2437j : QmsMessage.f2437j / 2);
        if (i >= 30) {
        }
        if (i > this.qmsMessageSparseArray.size() - 30) {
            SparseArray<QmsMessage> sparseArray2 = this.qmsMessageSparseArray;
            DocumentManager.getResultRequest(new QmsLoadMessages(this, sparseArray2.valueAt(sparseArray2.size() - 1).messageId, 60, false));
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    void m419j0() {
        this.f2364H.setText(this.f2391i0);
        boolean z = true;
        this.f2364H.setEnabled(true);
        this.f2363G.setEnabled(true);
        this.f2365I.setEnabled(true);
        View view = this.f2367K;
        if (this.f2391i0.length() <= 0) {
            z = false;
        }
        view.setEnabled(z);
        this.f2366J.setVisibility(4);
    }

    void m418k0(int i) {
        this.f2392j0 = i;
        this.f2370N.post(this.f2393k0);
    }

    void m416m0(AttachDialog.C0553e eVar, int status) {
        m419j0();
        Toast.makeText(this.mainActivity, 5 == status ? "Сервер отказал в загрузке файла." : 6 == status ? "Файл слишком велик для загрузки." : 7 == status ? "Этот тип файла не разрешен для загрузки." : 8 == status ? "Этот файл не разрешен для загрузки." : String.format("Статус %d при загрузке.", status), 1).show();
    }

    void m415n0(AttachDialog.C0553e eVar, int i) {
        m419j0();
        DocumentManager.getResultRequest(new QmsSendPostRequest(this, "", Collections.singletonList(i)));
    }

    void m412q0(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        int myId = this.myId;
        if (myId <= 0) {
            return;
        }
        if (currentTimeMillis - this.f2389g0 > 5000 || this.f2388f0 != i) {
            DocumentManager.getResultRequest(new API.EventPushRequest(String.format("u%d", myId), String.format("q%d", this.dialogId), 101, i));
            this.f2389g0 = currentTimeMillis;
            this.f2388f0 = i;
        }
    }

    @Override
    public void destroyData() {
        if (isCurrentTab() && this.myId > 0) {
            this.tab.m714l(null, false);
        }
        super.destroyData();
        this.f2377U = false;
        this.f2376T = false;
        this.qmsMessageSparseArray = null;
        if (isCurrentTab()) {
            Tab.ForumsListView gVar = this.tab.forumsListView;
            gVar.f1474b.setBackgroundDrawable(gVar.f1475c);
            this.tab.forumsListView.setOverscrollHeader(null);
        }
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, "forum/index.php?act=qms", "QMS", 0, false, false), new Breadcrumb.C0725a(2, getLink(), this.title, 0, true, true)};
    }

    @Override
    public String getLink() {
        if (this.f2390h0 == null) {
            this.f2390h0 = "forum/index.php?act=qms&t=" + this.dialogId;
            if (this.f2361E != null) {
                try {
                    this.f2390h0 += "&search=" + URLEncoder.encode(this.f2361E, "windows-1251");
                } catch (UnsupportedEncodingException unused) {
                    this.f2390h0 += "&search=" + this.f2361E.replace(' ', '+');
                }
            }
        }
        return this.f2390h0;
    }

    @Override
    public boolean mo109y(Object... objArr) {
        return this.f2361E == null && (Integer) objArr[0] == this.dialogId;
    }

    @Override
    public void tabReload() {
        this.f2380X = false;
        super.tabReload();
    }

    Page_QMS_Talk(MainActivity mainActivity, int i, int i2) {
        super(mainActivity, 29809, new Document(i, i2));
        Document uVar;
        Object[] objArr = new Object[3];
        int valueOf = i;
        if (i2 >= 0) {
            objArr[0] = valueOf;
            objArr[1] = i2;
            objArr[2] = 60;
            uVar = new Document(objArr);
        } else {
            objArr[0] = valueOf;
            objArr[1] = 0;
            objArr[2] = -60;
            uVar = new Document(objArr);
        }
        this.f2381Y = 0;
        this.f2382Z = new C0710p();
        this.f2383a0 = new C0694a();
        this.f2384b0 = new C0695b();
        this.f2385c0 = new C0696c();
        this.f2386d0 = new RunnableC0697d();
        this.f2387e0 = false;
        this.f2388f0 = 0;
        this.f2389g0 = 0;
        this.f2392j0 = 0;
        this.f2393k0 = new RunnableC0701g();
        m414o0(i, i2, null);
    }

    public Page_QMS_Talk(MainActivity mainActivity, int i, String str) {
        super(mainActivity, 29809, new Document(i, 0, 60, str));
        this.f2381Y = 0;
        this.f2382Z = new C0710p();
        this.f2383a0 = new C0694a();
        this.f2384b0 = new C0695b();
        this.f2385c0 = new C0696c();
        this.f2386d0 = new RunnableC0697d();
        this.f2387e0 = false;
        this.f2388f0 = 0;
        this.f2389g0 = 0;
        this.f2392j0 = 0;
        this.f2393k0 = new RunnableC0701g();
        m414o0(i, -1, str);
    }
}
