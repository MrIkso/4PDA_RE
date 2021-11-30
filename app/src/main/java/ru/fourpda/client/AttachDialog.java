package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Vector;

public class AttachDialog implements MainActivity.AbstractC0193g0 {
    private Dialog f1853a;
    private MainActivity f1854b;
    private String[] f1855c;
    private int f1856d;
    private LinearLayout f1857e;
    private AbstractC0559i f1858f;
    private Vector<C0553e> f1859g = new Vector<>();

    public class View$OnClickListenerC0548a implements View.OnClickListener {
        final C0553e f1860a;

        View$OnClickListenerC0548a(C0553e eVar) {
            this.f1860a = eVar;
        }

        @Override
        public void onClick(View view) {
            AttachDialog.this.f1853a.cancel();
            AbstractC0559i iVar = AttachDialog.this.f1858f;
            AttachDialog nVar = AttachDialog.this;
            C0553e eVar = this.f1860a;
            iVar.mo153a(nVar, eVar.f1875g, eVar.f1869a);
        }
    }

    public class View$OnClickListenerC0549b implements View.OnClickListener {
        final C0553e f1862a;
        final RelativeLayout f1863b;
        final View f1864c;

        class View$OnClickListenerC0550a implements View.OnClickListener {
            View$OnClickListenerC0550a() {
            }

            @Override
            public void onClick(View view) {
                View$OnClickListenerC0549b bVar = View$OnClickListenerC0549b.this;
                bVar.f1862a.f1876h = true;
                AttachDialog.this.m586r(bVar.f1863b, bVar.f1864c);
                AbstractC0559i iVar = AttachDialog.this.f1858f;
                View$OnClickListenerC0549b bVar2 = View$OnClickListenerC0549b.this;
                iVar.mo152b(AttachDialog.this, bVar2.f1862a.f1875g);
                C0553e eVar = View$OnClickListenerC0549b.this.f1862a;
                eVar.f1875g = -eVar.f1875g;
            }
        }

        View$OnClickListenerC0549b(C0553e eVar, RelativeLayout relativeLayout, View view) {
            this.f1862a = eVar;
            this.f1863b = relativeLayout;
            this.f1864c = view;
        }

        @Override
        public void onClick(View view) {
            synchronized (this.f1862a) {
                C0553e eVar = this.f1862a;
                if (eVar.f1875g != 0) {
                    DlgSimple q1Var = new DlgSimple(AttachDialog.this.f1854b, "Подтвердите удаление", false, null, null);
                    q1Var.editText.setVisibility(8);
                    q1Var.m620f(new View$OnClickListenerC0550a(), true);
                    q1Var.show(true, true, true);
                } else {
                    eVar.f1876h = true;
                    AttachDialog.this.m586r(this.f1863b, this.f1864c);
                    AttachDialog.this.f1859g.remove(this.f1862a);
                }
            }
        }
    }

    class View$OnClickListenerC0551c implements View.OnClickListener {
        View$OnClickListenerC0551c() {
        }

        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT < 23 || AttachDialog.this.f1854b.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                AttachDialog.m585s(AttachDialog.this.f1854b);
            } else {
                AttachDialog.this.f1854b.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
            }
        }
    }

    class DialogInterface$OnDismissListenerC0552d implements DialogInterface.OnDismissListener {
        DialogInterface$OnDismissListenerC0552d() {
        }

        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            AttachDialog.this.f1858f.mo151c(AttachDialog.this);
        }
    }

    public class C0554f extends DocumentManager.IGenerateRequest {
        C0553e f1878g;

        C0554f(C0553e eVar) {
            super(30054);
            this.f1878g = eVar;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            C0553e eVar = this.f1878g;
            if (!eVar.f1876h) {
                if (status == 0) {
                    AttachDialog.this.m587q(eVar, uVar.getInt(0).intValue());
                } else if (4 == status) {
                    DocumentManager.getResultRequest(new C0556h(eVar));
                } else {
                    AttachDialog.this.m588p(eVar, status);
                }
            }
        }

        @Override
        public Document generate() {
            C0553e eVar = this.f1878g;
            return new Document(eVar.f1869a, Integer.valueOf((int) eVar.f1873e), this.f1878g.f1874f);
        }
    }

    private class AsyncTaskC0555g extends AsyncTask<C0553e, Void, C0553e> {
        private AsyncTaskC0555g() {
        }

        public C0553e doInBackground(C0553e... eVarArr) {
            if (eVarArr.length != 1) {
                return null;
            }
            C0553e eVar = eVarArr[0];
            try {
                InputStream openInputStream = AttachDialog.this.f1854b.getContentResolver().openInputStream(eVar.f1872d);
                if (openInputStream != null) {
                    eVar.f1873e = 0;
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = openInputStream.read(bArr, 0, 4096);
                        if (read <= 0) {
                            break;
                        }
                        eVar.f1873e += (long) read;
                        instance.update(bArr, 0, read);
                    }
                    openInputStream.close();
                    if (AttachDialog.this.f1856d > 0 && eVar.f1873e > ((long) AttachDialog.this.f1856d)) {
                        return eVar;
                    }
                    byte[] digest = instance.digest();
                    eVar.f1874f = String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X", Integer.valueOf(digest[0] & 255), Integer.valueOf(digest[1] & 255), Integer.valueOf(digest[2] & 255), Integer.valueOf(digest[3] & 255), Integer.valueOf(digest[4] & 255), Integer.valueOf(digest[5] & 255), Integer.valueOf(digest[6] & 255), Integer.valueOf(digest[7] & 255), Integer.valueOf(digest[8] & 255), Integer.valueOf(digest[9] & 255), Integer.valueOf(digest[10] & 255), Integer.valueOf(digest[11] & 255), Integer.valueOf(digest[12] & 255), Integer.valueOf(digest[13] & 255), Integer.valueOf(digest[14] & 255), Integer.valueOf(digest[15] & 255));
                }
            } catch (Exception e) {
                e.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(e);
            }
            return eVar;
        }

        public void onPostExecute(C0553e eVar) {
            if (eVar.f1874f != null) {
                DocumentManager.getResultRequest(new C0554f(eVar));
                ((TextView) eVar.f1870b.findViewById(R.id.captionID)).setText(eVar.f1869a);
                AttachDialog.this.m582v(eVar.f1870b, 0);
                return;
            }
            AttachDialog.this.f1859g.remove(eVar);
            AttachDialog.this.m586r(eVar.f1870b, eVar.f1871c);
            Toast.makeText(AttachDialog.this.f1854b, (AttachDialog.this.f1856d <= 0 || eVar.f1873e <= ((long) AttachDialog.this.f1856d)) ? "Ошибка открытия файла. Попробуйте использовать другой файловый менеджер." : "Размер файла превышает ограничение!", 1).show();
        }

        AsyncTaskC0555g(AttachDialog nVar, View$OnClickListenerC0548a aVar) {
            this();
        }
    }

    private class C0556h extends DocumentManager.IGenerateRequest {
        C0553e f1881g;
        InputStream f1883i;
        int f1884j;
        int f1882h = 0;
        String f1885k = "att-" + DocumentManager.getMemberId() + "-" + SystemClock.elapsedRealtime();

        class RunnableC0557a implements Runnable {
            final long f1887a;

            RunnableC0557a(long j) {
                this.f1887a = j;
            }

            @Override
            public void run() {
                C0556h hVar = C0556h.this;
                AttachDialog.this.m582v(hVar.f1881g.f1870b, this.f1887a);
            }
        }

        class RunnableC0558b implements Runnable {
            RunnableC0558b() {
            }

            @Override
            public void run() {
                AttachDialog.this.f1859g.remove(C0556h.this.f1881g);
                C0556h hVar = C0556h.this;
                AttachDialog nVar = AttachDialog.this;
                C0553e eVar = hVar.f1881g;
                nVar.m586r(eVar.f1870b, eVar.f1871c);
                Toast.makeText(AttachDialog.this.f1854b, "Ошибка чтения файла", 1).show();
            }
        }

        C0556h(C0553e eVar) {
            super(30067);
            this.f1881g = eVar;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            C0553e eVar = this.f1881g;
            if (!eVar.f1876h) {
                if (status == 0) {
                    int i2 = this.f1882h;
                    if (i2 == 0 || 1 == i2) {
                        int intValue = uVar.getInt(0);
                        this.f1884j = intValue;
                        int i3 = this.f1882h;
                        if (i3 == 0) {
                            this.f1882h = 1;
                        } else if (1 == i3 && ((long) intValue) >= this.f1881g.f1873e) {
                            this.f1882h = 2;
                        }
                        DocumentManager.getResultRequest(this);
                        return;
                    }
                    AttachDialog.this.m587q(eVar, uVar.getInt(0));
                    return;
                }
                AttachDialog.this.m588p(eVar, status);
            }
        }

        @Override
        public void onErrorUploadFile() {
            super.onErrorUploadFile();
            if (this.f1882h == 1) {
                this.f1882h = 0;
                try {
                    this.f1883i.close();
                } catch (Exception unused) {
                }
                this.f1883i = null;
            }
        }

        @Override
        public int onUploadFile(byte[] bytes, int len) {
            int i2 = 0;
            Exception e;
            int i3 = 0;
            try {
                if (this.f1883i == null) {
                    InputStream openInputStream = AttachDialog.this.f1854b.getContentResolver().openInputStream(this.f1881g.f1872d);
                    this.f1883i = openInputStream;
                    openInputStream.skip((long) this.f1884j);
                }
                i2 = this.f1883i.read(bytes, 0, len);
                if (i2 > 0) {
                    try {
                        C0553e eVar = this.f1881g;
                        long j = (((long) this.f1884j) * 500) / eVar.f1873e;
                        if (j / 5 != eVar.f1877i / 5) {
                            AttachDialog.this.f1854b.runOnUiThread(new RunnableC0557a(j));
                            this.f1881g.f1877i = j;
                        }
                        this.f1884j += i2;
                        if (this.f1881g.f1876h) {
                            this.f1882h = 0;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        i3 = i2;
                        //ACRA.getErrorReporter().handleSilentException(e);
                        AttachDialog.this.f1854b.runOnUiThread(new RunnableC0558b());
                        i2 = i3;
                        if (((long) this.f1884j) >= this.f1881g.f1873e) {
                        }
                        return i2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
            if (((long) this.f1884j) >= this.f1881g.f1873e) {
                try {
                    this.f1883i.close();
                } catch (Exception unused) {
                }
                this.f1883i = null;
            }
            return i2;
        }

        @Override
        public int isFileSize() {
            if (1 == this.f1882h) {
                return ((int) this.f1881g.f1873e) - this.f1884j;
            }
            return 0;
        }

        @Override
        public Document generate() {
            int i = this.f1882h;
            if (i == 0 || 1 == i) {
                return new Document(this.f1885k);
            }
            C0553e eVar = this.f1881g;
            return new Document(this.f1885k, "fu", eVar.f1869a, Integer.valueOf((int) eVar.f1873e), this.f1881g.f1874f);
        }
    }

    public interface AbstractC0559i {
        void mo153a(AttachDialog nVar, int i, String str);

        void mo152b(AttachDialog nVar, int i);

        void mo151c(AttachDialog nVar);

        void mo150d(AttachDialog nVar, int i, String str);
    }

    @SuppressLint({"NewApi"})
    public AttachDialog(MainActivity mainActivity, AbstractC0559i iVar) {
        this.f1854b = mainActivity;
        this.f1858f = iVar;
        LinearLayout linearLayout = new LinearLayout(this.f1854b);
        linearLayout.setOrientation(1);
        float f = this.f1854b.f731b;
        int i = (int) (24.0f * f);
        int i2 = (int) (12.0f * f);
        linearLayout.setPadding(i, i, i2, (int) (16.0f * f));
        RelativeLayout relativeLayout = new RelativeLayout(this.f1854b);
        relativeLayout.setPadding(0, 0, 0, i2);
        linearLayout.addView(relativeLayout);
        TextView textView = new TextView(this.f1854b);
        textView.setId(R.id.captionID);
        textView.setText("Вложения");
        textView.setTextColor(Skin.C0353a.f1365U);
        textView.setSingleLine(true);
        textView.setTextSize(22.0f);
        textView.setTypeface(null, 1);
        textView.setPadding(0, 0, 0, (int) (8.0f * f));
        relativeLayout.addView(textView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        View view = new View(this.f1854b);
        view.setClickable(true);
        view.setBackgroundDrawable(this.f1854b.skin.m736f(R.drawable.add_attach));
        view.setOnClickListener(new View$OnClickListenerC0551c());
        relativeLayout.addView(view);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams2.width = (int) (f * 40.0f);
        layoutParams2.height = -1;
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        layoutParams2.addRule(8, R.id.captionID);
        ScrollView scrollView = new ScrollView(this.f1854b);
        linearLayout.addView(scrollView);
        LinearLayout linearLayout2 = new LinearLayout(this.f1854b);
        this.f1857e = linearLayout2;
        linearLayout2.setOrientation(1);
        scrollView.addView(this.f1857e);
        Dialog dialog = new Dialog(this.f1854b, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
        this.f1853a = dialog;
        dialog.setContentView(linearLayout);
        this.f1853a.setCanceledOnTouchOutside(true);
        this.f1853a.getWindow().setLayout(-1, -2);
        this.f1853a.getWindow().setBackgroundDrawable(this.f1854b.skin.m736f(R.drawable.np_dialog));
        this.f1853a.setOnDismissListener(new DialogInterface$OnDismissListenerC0552d());
    }

    private void m591m(View view) {
        TextView textView = (TextView) view.findViewById(R.id.captionID);
        textView.setTextColor(Skin.C0353a.f1384g0);
        textView.setClickable(true);
    }

    public void m586r(View view, View view2) {
        this.f1857e.removeView(view);
        if (view2 != null) {
            this.f1857e.removeView(view2);
        }
    }

    public static void m585s(MainActivity mainActivity) {
        if (!Prefs.f1153N) {
            try {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                mainActivity.m904b(Intent.createChooser(intent, "Файл-менеджер"));
            } catch (Exception unused) {
                Toast.makeText(mainActivity, "Не найден файл-менеджер", 1).show();
            }
        } else {
            Vector vector = new Vector();
            Vector vector2 = new Vector();
            if (Build.VERSION.SDK_INT >= 19) {
                Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent2.addCategory("android.intent.category.OPENABLE");
                intent2.setType("*/*");
                for (ResolveInfo resolveInfo : mainActivity.getPackageManager().queryIntentActivities(intent2, 0)) {
                    String str = resolveInfo.activityInfo.packageName;
                    if (!vector.contains(str)) {
                        vector.add(str);
                        Intent intent3 = new Intent("android.intent.action.OPEN_DOCUMENT");
                        intent3.addCategory("android.intent.category.OPENABLE");
                        intent3.setType("*/*");
                        intent3.setPackage(str);
                        vector2.add(intent3);
                    }
                }
            }
            Intent intent4 = new Intent("android.intent.action.GET_CONTENT");
            intent4.addCategory("android.intent.category.OPENABLE");
            intent4.setType("*/*");
            for (ResolveInfo resolveInfo2 : mainActivity.getPackageManager().queryIntentActivities(intent4, 0)) {
                String str2 = resolveInfo2.activityInfo.packageName;
                if (!vector.contains(str2)) {
                    Intent intent5 = new Intent("android.intent.action.GET_CONTENT");
                    intent5.addCategory("android.intent.category.OPENABLE");
                    intent5.setType("*/*");
                    intent5.setPackage(str2);
                    vector2.add(intent5);
                }
            }
            if (vector2.size() > 1) {
                Intent createChooser = Intent.createChooser((Intent) vector2.get(0), "Файл-менеджер");
                vector2.remove(0);
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) vector2.toArray(new Parcelable[0]));
                mainActivity.m904b(createChooser);
            } else if (vector2.size() == 1) {
                mainActivity.m904b((Intent) vector2.get(0));
            } else {
                Toast.makeText(mainActivity, "Не найден файл-менеджер", 1).show();
            }
        }
    }

    public void m582v(View view, long j) {
        View findViewById = view.findViewById(R.id.progressBlueID);
        View findViewById2 = view.findViewById(R.id.progressWhiteID);
        if (j == 0) {
            view.findViewById(R.id.progressBarID).setVisibility(0);
        }
        if (j > 500) {
            view.findViewById(R.id.progressBarID).setVisibility(4);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.weight = (float) (500 - j);
        findViewById.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) findViewById2.getLayoutParams();
        layoutParams2.weight = (float) j;
        findViewById2.setLayoutParams(layoutParams2);
    }

    Util.C0428i<View, View> m601a(C0553e eVar, int i, boolean z) {
        View view = i > 0 ? new View(this.f1854b) : null;
        if (view != null) {
            view.setBackgroundColor(this.f1854b.skin.m738d(R.color.border_line));
            this.f1857e.addView(view);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = -1;
            float f = this.f1854b.f731b;
            layoutParams.height = (int) (1.0f * f);
            layoutParams.rightMargin = (int) (f * 12.0f);
        }
        RelativeLayout relativeLayout = new RelativeLayout(this.f1854b);
        this.f1857e.addView(relativeLayout);
        TextView textView = new TextView(this.f1854b);
        textView.setText(z ? eVar.f1869a : "загрузка...");
        if (z) {
            textView.setTextColor(Skin.C0353a.f1384g0);
        } else {
            textView.setTextColor(Skin.C0353a.f1386h0);
        }
        textView.setTextSize(16.0f);
        textView.setOnClickListener(new View$OnClickListenerC0548a(eVar));
        textView.setClickable(z);
        textView.setId(R.id.captionID);
        textView.setGravity(16);
        textView.setMinimumHeight((int) (this.f1854b.f731b * 40.0f));
        float f2 = this.f1854b.f731b;
        textView.setPadding(0, (int) (f2 * 8.0f), 0, (int) (f2 * 8.0f));
        relativeLayout.addView(textView);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.addRule(10);
        layoutParams2.addRule(9);
        layoutParams2.addRule(0, R.id.removeID);
        View view2 = new View(this.f1854b);
        view2.setClickable(true);
        view2.setBackgroundDrawable(this.f1854b.skin.m736f(R.drawable.button_remove));
        view2.setId(R.id.removeID);
        view2.setOnClickListener(new View$OnClickListenerC0549b(eVar, relativeLayout, view));
        view2.setEnabled(true);
        relativeLayout.addView(view2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
        layoutParams3.width = (int) (this.f1854b.f731b * 40.0f);
        layoutParams3.height = -2;
        layoutParams3.addRule(11);
        layoutParams3.addRule(6, R.id.captionID);
        layoutParams3.addRule(8, R.id.progressBarID);
        LinearLayout linearLayout = new LinearLayout(this.f1854b);
        linearLayout.setId(R.id.progressBarID);
        linearLayout.setVisibility(4);
        relativeLayout.addView(linearLayout);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams4.width = -1;
        layoutParams4.height = -2;
        layoutParams4.addRule(12);
        layoutParams4.addRule(9);
        layoutParams4.addRule(3, R.id.captionID);
        layoutParams4.rightMargin = (int) (this.f1854b.f731b * 12.0f);
        View view3 = new View(this.f1854b);
        view3.setId(R.id.progressBlueID);
        view3.setBackgroundColor(this.f1854b.skin.m738d(R.color.progress_done));
        linearLayout.addView(view3);
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) view3.getLayoutParams();
        layoutParams5.width = -1;
        layoutParams5.height = (int) (this.f1854b.f731b * 3.0f);
        View view4 = new View(this.f1854b);
        view4.setId(R.id.progressWhiteID);
        view4.setBackgroundColor(this.f1854b.skin.m738d(R.color.progress_all));
        linearLayout.addView(view4);
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) view4.getLayoutParams();
        layoutParams6.width = -1;
        layoutParams6.height = (int) (this.f1854b.f731b * 3.0f);
        return new Util.C0428i<>(relativeLayout, view);
    }

    public void m600b() {
        this.f1857e.removeAllViews();
        int i = 0;
        for (int i2 = 0; i2 < this.f1859g.size(); i2++) {
            C0553e eVar = this.f1859g.get(i2);
            int i3 = eVar.f1875g;
            if (i3 >= 0) {
                Util.C0428i<View, View> a = m601a(eVar, i, i3 > 0);
                View view = a.f1651a;
                eVar.f1870b = view;
                eVar.f1871c = a.f1652b;
                if (eVar.f1875g == 0) {
                    ((TextView) view.findViewById(R.id.captionID)).setText(eVar.f1869a);
                    eVar.f1870b.findViewById(R.id.progressBarID).setVisibility(0);
                    m582v(eVar.f1870b, eVar.f1877i);
                } else {
                    i++;
                }
            }
        }
        this.f1853a.getWindow().getAttributes().gravity = 17;
        this.f1853a.show();
        CustomDialog.m623c(this.f1853a);
        this.f1854b.m898h(this);
    }

    public void m599c() {
        Vector vector = new Vector();
        for (int i = 0; i < this.f1859g.size(); i++) {
            C0553e eVar = this.f1859g.get(i);
            synchronized (eVar) {
                eVar.f1876h = true;
                if (eVar.f1875g == 0) {
                    vector.add(eVar);
                }
            }
        }
        for (int i2 = 0; i2 < vector.size(); i2++) {
            this.f1859g.remove(vector.get(i2));
        }
    }

    @Override
    public void mo426e(Uri uri, String str) {
        boolean z;
        try {
            String[] strArr = this.f1855c;
            if (strArr != null && strArr.length > 0) {
                int lastIndexOf = str.lastIndexOf(46);
                if (lastIndexOf >= 0) {
                    String substring = str.substring(lastIndexOf + 1);
                    int i = 0;
                    while (true) {
                        String[] strArr2 = this.f1855c;
                        if (i >= strArr2.length) {
                            break;
                        } else if (strArr2[i].equalsIgnoreCase(substring)) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                z = false;
                if (!z) {
                    MainActivity mainActivity = this.f1854b;
                    Toast.makeText(mainActivity, "Неподходящее расширение файла!\n" + str, 1).show();
                    return;
                }
            }
            C0553e eVar = new C0553e(uri, str);
            this.f1859g.add(eVar);
            Util.C0428i<View, View> a = m601a(eVar, this.f1859g.size(), false);
            View view = a.f1651a;
            eVar.f1870b = view;
            eVar.f1871c = a.f1652b;
            ((TextView) view.findViewById(R.id.captionID)).setText("Подготовка " + eVar.f1869a);
            new AsyncTaskC0555g(this, null).execute(eVar);
        } catch (Exception unused) {
            Toast.makeText(this.f1854b, "Ошибка файла", 1).show();
        }
    }

    @Override
    public boolean mo423g(Intent intent) {
        return false;
    }

    public SparseArray<String> m590n() {
        if (this.f1859g.size() <= 0) {
            return null;
        }
        SparseArray<String> sparseArray = new SparseArray<>(this.f1859g.size());
        for (int i = 0; i < this.f1859g.size(); i++) {
            sparseArray.put(this.f1859g.get(i).f1875g, this.f1859g.get(i).f1869a);
        }
        return sparseArray;
    }

    public int m589o() {
        int i = 0;
        for (int i2 = 0; i2 < this.f1859g.size(); i2++) {
            if (this.f1859g.get(i2).f1875g > 0) {
                i++;
            }
        }
        return i;
    }

    void m588p(C0553e eVar, int i) {
        this.f1859g.remove(eVar);
        m586r(eVar.f1870b, eVar.f1871c);
        Toast.makeText(this.f1854b, 5 == i ? "Сервер отказал в загрузке файла." : 6 == i ? "Файл слишком велик для загрузки." : 7 == i ? "Этот тип файла не разрешен для загрузки." : 8 == i ? "Этот файл не разрешен для загрузки." : String.format("Статус %d при загрузке.", Integer.valueOf(i)), 1).show();
    }

    void m587q(C0553e eVar, int i) {
        eVar.f1875g = i;
        m591m(eVar.f1870b);
        m582v(eVar.f1870b, 510);
        this.f1858f.mo150d(this, i, eVar.f1869a);
    }

    public void m584t(SparseArray<String> sparseArray) {
        this.f1859g = new Vector<>();
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                this.f1859g.add(new C0553e(sparseArray.keyAt(i), sparseArray.valueAt(i)));
            }
        }
    }

    public void m583u(String[] strArr, int i) {
        this.f1855c = strArr;
        this.f1856d = i * 1024 * 1024;
    }

    public static class C0553e {
        String f1869a;
        View f1870b;
        View f1871c;
        Uri f1872d;
        long f1873e;
        String f1874f;
        public int f1875g;
        boolean f1876h;
        long f1877i;

        C0553e(int i, String str) {
            this.f1875g = i;
            this.f1869a = str;
        }

        public C0553e(Uri uri, String str) {
            this.f1872d = uri;
            this.f1869a = str;
        }
    }
}
