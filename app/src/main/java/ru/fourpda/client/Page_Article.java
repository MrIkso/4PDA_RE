package ru.fourpda.client;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;

public class Page_Article extends Page implements BBDisplay.IBBDisplayCallback, Form_Post.AbstractC0846i {
    public int f1190E;
    boolean f1191F;
    int f1192G;
    C0316h f1193H;
    View$OnClickListenerC0328p f1194I;
    View$OnClickListenerC0319j f1195J;
    Document f1196K;
    int f1197L;
    List<C0316h> f1198M;
    int f1199N;
    List<C0318i> f1200O;
    List<C0325n> f1201P;
    boolean f1202Q;
    boolean f1203R;
    private Form_Post.ForumPostModel forumPostModel;
    private Form_Post forumPost;
    private String f1206U;

    class View$OnClickListenerC0307a implements View.OnClickListener {
        final ArticleLayout f1207a;

        View$OnClickListenerC0307a(ArticleLayout articleLayout) {
//           // Page_Article.this = r1;
            this.f1207a = articleLayout;
        }

        @Override
        public void onClick(View view) {
            Tab.ForumsListView gVar = Page_Article.this.tab.forumsListView;
            int measuredHeight = this.f1207a.getMeasuredHeight() + this.f1207a.getTop();
            MainLayout mainLayout = Page_Article.this.tab.mainLayout;
            gVar.smoothScrollBy(measuredHeight - (mainLayout.f831n ? mainLayout.getActionBarHeight() : 0), 800);
        }
    }

    class View$OnClickListenerC0308b implements View.OnClickListener {
        View$OnClickListenerC0308b() {
//            Page_Article.this = r1;
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Article.this.mainActivity);
            Page_Article c0Var = Page_Article.this;
            f1Var.addPage(new Page_Profile(c0Var.mainActivity, c0Var.f1193H.f1231f, 0));
            Page_Article.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    class View$OnClickListenerC0309c implements View.OnClickListener {
        View$OnClickListenerC0309c() {
//            Page_Article.this = r1;
        }

        @Override
        public void onClick(View view) {
            Page_Article.this.mainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Page_Article.this.f1196K.getString(4))));
        }
    }

    public class View$OnClickListenerC0310d implements View.OnClickListener {
        final C0325n f1211a;

        View$OnClickListenerC0310d(C0325n nVar) {
//            Page_Article.this = r1;
            this.f1211a = nVar;
        }

        @Override
        public void onClick(View view) {
            Document uVar = new Document();
            int i = 0;
            while (true) {
                C0325n nVar = this.f1211a;
                C0325n.C0326a[] aVarArr = nVar.f1262e;
                if (i >= aVarArr.length) {
                    break;
                }
                if (aVarArr[i].f1266d != null) {
                    if ((nVar.f1260c & 2) != 0) {
                        if (!((Widgets$CheckboxTextView) aVarArr[i].f1266d).getChecked()) {
                        }
                        uVar.append(Integer.valueOf(this.f1211a.f1262e[i].f1263a));
                    } else {
                        if (!((RadioButton) aVarArr[i].f1266d).isChecked()) {
                        }
                        uVar.append(Integer.valueOf(this.f1211a.f1262e[i].f1263a));
                    }
                }
                i++;
            }
            if (uVar.count() > 0) {
                Page_Article c0Var = Page_Article.this;
                c0Var.f1202Q = true;
                this.f1211a.f1260c |= 4;
                c0Var.tabLoaded(true);
                DocumentManager.getResultRequest(new API.C0249b0(this.f1211a.f1258a, uVar));
                return;
            }
            Toast.makeText(Page_Article.this.mainActivity, "Выберите вариант ответа", 1).show();
        }
    }

    class C0311e implements OptionPoupupMenuView.IClickListener {
        final BBDisplay f1213a;
        final BBString f1214b;
        final BBDisplay.C0143c f1215c;

        C0311e(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
//            Page_Article.this = r1;
            this.f1213a = bBDisplay;
            this.f1214b = pVar;
            this.f1215c = cVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Article c0Var = Page_Article.this;
                c0Var.f1203R = true;
                c0Var.mo129f(this.f1213a, this.f1214b, this.f1215c);
            } else if (i3 == 22) {
                Page_Article.this.mo129f(this.f1213a, this.f1214b, this.f1215c);
            } else if (i3 == 3) {
                this.f1213a.f528e.m950j(false);
            } else if (i3 == 26) {
                Urls2.visitPage(Page_Article.this.mainActivity, this.f1214b.f2202I.get(this.f1215c.f543a).link);
            } else if (i3 == 27) {
                Util.copyToClipboard(Page_Article.this.mainActivity, this.f1214b.f2202I.get(this.f1215c.f543a).link, "Ссылка скопирована в буфер");
            } else if (i3 == 24) {
                this.f1213a.m977d(this.f1215c.f545c, true);
            }
        }
    }

    public class C0312f implements OptionPoupupMenuView.IClickListener {
        final C0318i f1217a;

        class C0313a implements TextWatcher {
            final DlgSimple f1219a;
            final String f1220b;

            C0313a(C0312f fVar, DlgSimple q1Var, String str) {
                this.f1219a = q1Var;
                this.f1220b = str;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean z = false;
                this.f1219a.promtDescription.setVisibility(editable.toString().equals(this.f1220b) ? 0 : 8);
                DlgSimple q1Var = this.f1219a;
                if (editable.length() > 0) {
                    z = true;
                }
                q1Var.m625a(z);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0314b implements View.OnClickListener {
            final DlgSimple f1221a;
            final int f1222b;

            View$OnClickListenerC0314b(DlgSimple q1Var, int i) {
//                C0312f.this = r1;
                this.f1221a = q1Var;
                this.f1222b = i;
            }

            @Override
            public void onClick(View view) {
                if (this.f1221a.editText.getText().length() > 0) {
                    Page_Article c0Var = Page_Article.this;
                    DocumentManager.getResultRequest(new SiteCommentReqest(c0Var.f1190E, this.f1222b, this.f1221a.editText.getText().toString(), "Отправка нового комментария"));
                    return;
                }
                Toast.makeText(Page_Article.this.mainActivity, "Введите комментарий", 1).show();
            }
        }

        C0312f(C0318i iVar) {
//            Page_Article.this = r1;
            this.f1217a = iVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 1) {
                String str = Page_Article.this.f1200O.get(i2).f1244f + ",\n";
                DlgSimple q1Var = new DlgSimple(Page_Article.this.mainActivity, "Введите комментарий", false, "ОСТАВИТЬ", null);
                q1Var.m625a(false);
                q1Var.promtDescription.setText(R.string.dlg_simple_comment_warning);
                q1Var.editText.addTextChangedListener(new C0313a(this, q1Var, str));
                q1Var.editText.setSingleLine(false);
                q1Var.editText.setMinLines(2);
                q1Var.editText.setMaxLines(6);
                int i4 = Page_Article.this.f1200O.get(i2).f1239a;
                q1Var.editText.setText(str);
                q1Var.m620f(new View$OnClickListenerC0314b(q1Var, i4), true);
                q1Var.show(true, true, true);
                EditText editText = q1Var.editText;
                editText.setSelection(editText.getText().length());
                Page_Article.this.mainActivity.mainLayout.hideKeyboard(q1Var.editText);
            } else if (i3 == 2) {
                Tab f1Var = new Tab(Page_Article.this.mainActivity);
                Page_Article c0Var = Page_Article.this;
                f1Var.addPage(new Page_Profile(c0Var.mainActivity, c0Var.f1200O.get(i2).f1243e, 0));
                Page_Article.this.mainActivity.mainLayout.setCurrentTab(f1Var);
            } else if (i3 == 3) {
                C0318i iVar = this.f1217a;
                iVar.f1249k++;
                iVar.f1241c |= 16;
                Page_Article.this.tabLoaded(true);
                DocumentManager.getResultRequest(new API.C0247a0(Page_Article.this.f1193H.f1226a, this.f1217a.f1239a, 1));
            } else if (i3 == 4) {
                this.f1217a.f1241c |= 20;
                Page_Article.this.tabLoaded(true);
                DocumentManager.getResultRequest(new API.C0247a0(Page_Article.this.f1193H.f1226a, this.f1217a.f1239a, -1));
            } else if (i3 == 5) {
                Page_Article.this.m756i0(this.f1217a.f1239a);
            }
        }
    }

    class C0315g implements OptionPoupupMenuView.IClickListener {
        C0315g() {
//            Page_Article.this = r1;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Article.this.tabReload();
            } else if (i3 == 22) {
                Page_Article c0Var = Page_Article.this;
                DataDB.m365l(c0Var.title, c0Var.getLink());
            } else if (i3 == 1) {
                MainActivity mainActivity = Page_Article.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/" + Page_Article.this.getLink(), "Ссылка скопирована");
            } else if (i3 == 2) {
                MainActivity mainActivity2 = Page_Article.this.mainActivity;
                Urls2.visitPage(mainActivity2, "https://4pda.ru/" + Page_Article.this.getLink());
            }
        }
    }

    public static class C0316h {
        static BBString.C0674e f1225n;
        int f1226a;
        String f1227b;
        int f1228c;
        boolean f1229d;
        SpannableString f1230e;
        int f1231f;
        String f1232g;
        String f1233h;
        int f1234i;
        SparseArray<String> f1235j;
        String f1236k;
        BBString f1237l;
        List<ImageDialog.C0174m> f1238m;

        public static class C0317a extends BBString.C0674e {
            C0317a() {
                float f = BBString.f2189v0;
                this.f2263g = (float) ((int) (f * 1.0f));
                this.f2264h = (float) ((int) (f * 1.0f));
            }
        }

        C0316h() {
        }

        public static C0316h m755a(Document uVar, int i) {
            C0316h hVar = new C0316h();
            String n = uVar.getString(10);
            BBString.C0670a[] aVarArr = null;
            if (n == null) {
                return null;
            }
            int i2 = 0;
            while (true) {
                int indexOf = n.indexOf("[markets=", i2);
                if (indexOf < 0) {
                    break;
                }
                int indexOf2 = n.indexOf("\"]", indexOf) + 2;
                n = n.substring(0, indexOf) + "[center]" + n.substring(indexOf, indexOf2) + "[/center]" + n.substring(indexOf2);
                i2 = indexOf2 + 8 + 9;
            }
            Document l = uVar.getDocument(11);
            if (l != null && l.count() > 0) {
                int d = l.count();
                BBString.C0670a[] aVarArr2 = new BBString.C0670a[d];
                for (int i3 = 0; i3 < d; i3++) {
                    Document l2 = l.getDocument(i3);
                    aVarArr2[i3] = new BBString.C0670a();
                    aVarArr2[i3].f2247a = l2.getInt(0).intValue();
                    aVarArr2[i3].f2249c = true;
                    aVarArr2[i3].f2250d = l2.getString(1);
                    aVarArr2[i3].f2254h = Util.C0427h.UnEscapeString(l2.getString(4));
                    aVarArr2[i3].f2251e = l2.getInt(2).intValue();
                    aVarArr2[i3].f2252f = l2.getInt(3).intValue();
                    BBString.C0670a aVar = aVarArr2[i3];
                    String n2 = l2.getString(5);
                    aVar.f2255i = n2;
                    aVarArr2[i3].f2253g = !TextUtils.isEmpty(n2) ? 1 : 0;
                    aVarArr2[i3].f2248b = 0;
                }
                aVarArr = aVarArr2;
            }
            hVar.f1226a = uVar.getInt(0).intValue();
            hVar.f1230e = Page_Search.m321g0(Util.C0427h.UnEscapeString(uVar.getString(9)));
            hVar.f1231f = uVar.getInt(5).intValue();
            hVar.f1232g = Util.C0427h.UnEscapeString(uVar.getString(6));
            hVar.f1233h = uVar.getString(8);
            hVar.f1227b = Util.formatDate(uVar.getInt(1).intValue(), false, false);
            int intValue = uVar.getInt(4).intValue();
            hVar.f1228c = intValue;
            hVar.f1229d = (intValue & 64) > 0;
            hVar.f1234i = uVar.getInt(7).intValue();
            Document l3 = uVar.getDocument(12);
            if (l3 != null && l3.count() > 0) {
                hVar.f1235j = new SparseArray<>(l3.count());
                int d2 = l3.count() - 1;
                for (int i4 = 0; i4 <= d2; i4++) {
                    Document l4 = l3.getDocument(i4);
                    int intValue2 = l4.getInt(0).intValue();
                    String c = Util.C0427h.UnEscapeString(l4.getString(1));
                    hVar.f1235j.put(intValue2, c);
                    if (i == intValue2) {
                        hVar.f1236k = c;
                    }
                }
            }
            BBString x = BBString.getBBString(n, aVarArr);
            hVar.f1237l = x;
            x.f2221a0 = hVar;
            if (f1225n == null) {
                f1225n = new C0317a();
            }
            hVar.f1237l.f2246z = f1225n;
            if (aVarArr != null) {
                for (int i5 = 1; i5 <= aVarArr.length + 1; i5++) {
                    for (int i6 = 0; i6 < aVarArr.length; i6++) {
                        if (aVarArr[i6].f2249c && aVarArr[i6].f2253g != 0 && aVarArr[i6].f2248b == i5) {
                            if (hVar.f1238m == null) {
                                hVar.f1238m = new Vector(aVarArr.length);
                            }
                            hVar.f1238m.add(new ImageDialog.C0174m(aVarArr[i6].f2247a, (String) aVarArr[i6].f2255i, aVarArr[i6].f2254h));
                        }
                    }
                }
            }
            return hVar;
        }
    }

    public class C0318i {
        public int f1239a;
        public String f1240b;
        public int f1241c;
        public boolean f1242d;
        public int f1243e;
        public String f1244f;
        public int f1245g;
        public String f1246h;
        public int f1247i;
        public int f1248j;
        int f1249k;

        C0318i(Page_Article c0Var) {
        }
    }

    public class View$OnClickListenerC0319j implements View.OnClickListener, View.OnLongClickListener {
        Page_Article f1250a;

        class C0320a implements TextWatcher {
            final DlgSimple f1252a;

            C0320a(View$OnClickListenerC0319j jVar, DlgSimple q1Var) {
                this.f1252a = q1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean z = false;
                this.f1252a.promtDescription.setVisibility(editable.length() > 0 ? 8 : 0);
                DlgSimple q1Var = this.f1252a;
                if (editable.length() > 0) {
                    z = true;
                }
                q1Var.m625a(z);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0321b implements View.OnClickListener {
            final DlgSimple f1253a;

            View$OnClickListenerC0321b(DlgSimple q1Var) {
//                View$OnClickListenerC0319j.this = r1;
                this.f1253a = q1Var;
            }

            @Override
            public void onClick(View view) {
                if (this.f1253a.editText.length() > 0) {
                    View$OnClickListenerC0319j jVar = View$OnClickListenerC0319j.this;
                    DocumentManager.getResultRequest(new SiteCommentReqest(jVar.f1250a.f1190E, 0, this.f1253a.editText.getText().toString(), "Отправка нового комментария"));
                    return;
                }
                Toast.makeText(View$OnClickListenerC0319j.this.f1250a.mainActivity, "Введите комментарий", 1).show();
            }
        }

        public View$OnClickListenerC0319j(Page_Article c0Var) {
//            Page_Article.this = r1;
            this.f1250a = c0Var;
        }

        @Override
        public void onClick(View view) {
            if (((Integer) view.getTag()).intValue() == -1) {
                DlgSimple q1Var = new DlgSimple(this.f1250a.mainActivity, "Введите комментарий", false, "ОСТАВИТЬ", null);
                q1Var.m625a(false);
                q1Var.promtDescription.setText(R.string.dlg_simple_comment_warning);
                q1Var.editText.addTextChangedListener(new C0320a(this, q1Var));
                q1Var.editText.setSingleLine(false);
                q1Var.editText.setMinLines(2);
                q1Var.editText.setMaxLines(6);
                q1Var.m620f(new View$OnClickListenerC0321b(q1Var), true);
                q1Var.show(true, true, true);
                EditText editText = q1Var.editText;
                editText.setSelection(editText.getText().length());
                Page_Article.this.mainActivity.mainLayout.hideKeyboard(q1Var.editText);
                return;
            }
            C0318i iVar = this.f1250a.f1200O.get(((Integer) view.getTag()).intValue());
            int i = iVar.f1241c;
            if ((i & 4) != 0) {
                iVar.f1241c = i & -5;
                Page_Article.this.tabLoaded(true);
                return;
            }
            Tab f1Var = new Tab(this.f1250a.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Article.this.mainActivity, iVar.f1243e, 0));
            this.f1250a.mainActivity.mainLayout.setCurrentTab(f1Var);
        }

        @Override
        public boolean onLongClick(View view) {
            this.f1250a.m757h0(3, ((Integer) view.getTag()).intValue());
            return true;
        }
    }

    class SiteCommentReqest extends API.SiteCommentRequest {
        SiteCommentReqest(int articleId, int parentId, String msg, String statusMessage) {
            super(articleId, parentId, msg);
//            Page_Article.this = r1;
            this.statusMessage = statusMessage;
        }

        @Override
        void prepareResult(int status, Document document) {
            Page_Article c0Var = Page_Article.this;
            if (!c0Var.isLoading) {
                String message = "Комментарий отправлен на премодерацию";
                if (status == 0) {
                    message = "Комментарий добавлен";
                } else if (status == 4) {
                    message = "Ошибка: Вам запрещено комментировать";
                } else if (status == 5) {
                    message = "Ошибка: Нет текста комментария";
                } else if (status == 6) {
                    message = "Ошибка: Комментирование этого поста окончено";
                } else if (status == 7) {
                    message = "Ошибка: Вы не можете ответить на свернутый вами комментарий";
                } else if (status == 8) {
                    message = "Ошибка: Вы достигли кармического лимита комментирования";
                } else if (status == 9) {
                    message = "Ошибка: Повторный комментарий";
                } else if (status == 10) {
                    message = "Ошибка: Вы достигли часового лимита комментирования";
                } else if (status == 11) {
                    message = "Ошибка: Вы достигли дневного лимита комментирования";
                } else if (!(status == 12 || status == 13)) {
                    message = "Ошибка при добавлении комментария";
                }
                Toast.makeText(c0Var.mainActivity, message, 0).show();
                if (status == 0) {
                    Page_Article.this.tabReload();
                }
            }
        }
    }

    class SiteJumpRequest extends API.SiteJumpRequest {
        SiteJumpRequest(int time, boolean next, int category, String statusMessage) {
            super(time, next, category);
//            Page_Article.this = r1;
            this.statusMessage = statusMessage;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            Page_Article c0Var = Page_Article.this;
            if (!c0Var.isLoading && status == 0) {
                c0Var.tab.addPage(new Page_Article(c0Var.mainActivity, uVar.getInt(0), false));
            }
        }
    }

    class C0324m extends API.C0248b {
        C0324m() {
            super(1);
//            Page_Article.this = r1;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            Page_Article c0Var = Page_Article.this;
            if (!c0Var.isLoading && status == 0) {
                PicoImg.loadUrl(c0Var.mainActivity, uVar.getString(2)).runAsync();
                Page_Article c0Var2 = Page_Article.this;
                c0Var2.f1196K = uVar;
                if (c0Var2.isUnsucces()) {
                    Page_Article.this.tabLoaded(true);
                }
            }
        }
    }

    public static class C0325n {
        int f1258a;
        String f1259b;
        int f1260c;
        int f1261d;
        C0326a[] f1262e;

        public static class C0326a {
            int f1263a;
            String f1264b;
            int f1265c;
            View f1266d;

            C0326a() {
            }
        }

        C0325n() {
        }
    }

    private class AsyncTaskC0327o extends AsyncTask<String, Void, Void> {
        private AsyncTaskC0327o(Page_Article c0Var) {
        }

        public Void doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.getResponseCode();
                httpURLConnection.disconnect();
                return null;
            } catch (Exception e) {
               // //ACRA.getErrorReporter().handleSilentException(e);
                return null;
            }
        }

        AsyncTaskC0327o(Page_Article c0Var, View$OnClickListenerC0307a aVar) {
            this(c0Var);
        }
    }

    public class View$OnClickListenerC0328p implements View.OnClickListener {
        View$OnClickListenerC0328p() {
////            Page_Article.this = r1;
        }

        @Override
        public void onClick(View view) {
            Page_Article c0Var = Page_Article.this;
            if (!c0Var.isLoading && c0Var.currentDocument != null) {
                c0Var.tab.addPage(new Page_Site_List(Page_Article.this.mainActivity, ((Integer) view.getTag()).intValue(), 0));
            }
        }
    }

    public Page_Article(MainActivity mainActivity, int i, boolean z) {
        super(mainActivity, 24947, new Document(i));
        this.f1196K = null;
        this.f1197L = 0;
        this.f1202Q = false;
        this.f1203R = false;
        this.iconId = R.drawable.ic_nav_site;
        this.f1190E = i;
        this.f1194I = new View$OnClickListenerC0328p();
        this.f1195J = new View$OnClickListenerC0319j(this);
        this.title = "статья " + this.f1190E;
        this.statusMessage = "Загрузка статьи " + this.f1190E;
        this.f1191F = z;
    }

    public static int m761d0(List<Breadcrumb.C0725a> list, int i, int i2, int i3) {
        DataDB.BookMarkModel[] q = DataDB.m360q(i);
        if (q == null) {
            return 0;
        }
        int i4 = 0;
        for (DataDB.BookMarkModel bVar : q) {
            if (!bVar.f2509e || bVar.f2505a == i2) {
                i4++;
                list.add(new Breadcrumb.C0725a(bVar.f2505a, bVar.f2506b > 0 ? "tag/" + bVar.f2506b : null, bVar.f2508d, bVar.f2510f, true, bVar.f2505a == i3));
                m761d0(list, bVar.f2505a, i2, i3);
            }
        }
        return i4;
    }

    private boolean m758g0() {
        List<C0318i> list = this.f1200O;
        return list != null && list.size() > 0;
    }

    public void m756i0(int i) {
        Form_Post.ForumPostModel kVar = this.forumPostModel;
        if (kVar == null || kVar.postId != i) {
            this.forumPostModel = new Form_Post.ForumPostModel(0, "Жалоба: " + this.title, this.f1190E, i, false, false, false, false, false, "", "", null);
        }
        if (this.forumPost == null) {
            this.forumPost = new Form_Post(this.mainActivity, this);
        }
        this.forumPost.m196y(this.forumPostModel, this);
    }

    @Override
    public boolean mo145B() {
        Form_Post wVar = this.forumPost;
        if (wVar == null || !wVar.m201t()) {
            return false;
        }
        if (this.forumPost.m205p()) {
            return true;
        }
        this.forumPost.m202s();
        return true;
    }

    @Override
    public void mo763C() {
        if (isUnsucces()) {
            List<C0316h> list = this.f1198M;
            if (list == null || this.f1199N >= list.size() - 1) {
                DocumentManager.getResultRequest(new SiteJumpRequest(this.currentDocument.getInt(1).intValue(), false, -1, "К предыдущему..."));
            } else {
                this.tab.addPage(new Page_Article(this.mainActivity, this.f1198M.get(this.f1199N + 1), this.f1198M));
            }
        }
    }

    @Override
    public void mo762D() {
        int i;
        if (isUnsucces()) {
            List<C0316h> list = this.f1198M;
            if (list == null || (i = this.f1199N) <= 0) {
                DocumentManager.getResultRequest(new SiteJumpRequest(this.currentDocument.getInt(1).intValue(), true, -1, "К следующему..."));
            } else {
                this.tab.addPage(new Page_Article(this.mainActivity, list.get(i - 1), this.f1198M));
            }
        }
    }

    @Override
    public void onSearchBar() {
        Form_Post wVar = this.forumPost;
        if (wVar != null && wVar.m201t()) {
            this.forumPost.m200u();
        }
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoaded() {
        boolean z;
        boolean z2;
        Vector vector;
        Exception e;
        int i = 0;
        if (this.isLoading) {
            return false;
        }
        boolean z3 = true;
        try {
            C0316h a = C0316h.m755a(this.currentDocument, 0);
            if (a != null) {
                SpannableString spannableString = a.f1230e;
                this.title = spannableString.subSequence(0, spannableString.length()).toString();
                if (this.isLoading) {
                    return false;
                }
                Document l = this.currentDocument.getDocument(13);
                Vector vector2 = new Vector(l != null ? l.count() : 0);
                int i2 = 4;
                int i3 = 3;
                int i4 = 2;
                if (l != null) {
                    try {
                        if (l.count() > 0) {
                            int i5 = 0;
                            while (i5 < l.count()) {
                                Document l2 = l.getDocument(i5);
                                int intValue = l2.getInt(i4).intValue();
                                C0318i iVar = new C0318i(this);
                                iVar.f1239a = l2.getInt(0).intValue();
                                iVar.f1241c = intValue;
                                iVar.f1243e = l2.getInt(i3).intValue();
                                int intValue2 = l2.getInt(5).intValue();
                                iVar.f1245g = intValue2;
                                if ((intValue & 2) == 0) {
                                    iVar.f1242d = false;
                                    iVar.f1244f = Util.C0427h.UnEscapeString(l2.getString(i2));
                                    iVar.f1240b = Util.formatDate(l2.getInt(1).intValue());
                                    iVar.f1246h = Util.C0427h.UnEscapeString(l2.getString(6));
                                } else {
                                    iVar.f1242d = true;
                                }
                                iVar.f1249k = l2.getInt(7).intValue();
                                int i6 = 1;
                                boolean z4 = false;
                                while (intValue2 > 0) {
                                    int i7 = 0;
                                    while (true) {
                                        if (i7 >= vector2.size()) {
                                            z2 = false;
                                            break;
                                        } else if (((C0318i) vector2.get(i7)).f1239a == intValue2) {
                                            C0318i iVar2 = (C0318i) vector2.get(i7);
                                            int i8 = iVar2.f1248j + 1;
                                            iVar2.f1248j = i8;
                                            int i9 = i8 + i7;
                                            if (!z4) {
                                                vector2.add(i9, iVar);
                                                z4 = true;
                                            }
                                            intValue2 = ((C0318i) vector2.get(i7)).f1245g;
                                            i6++;
                                            z2 = true;
                                        } else {
                                            i7++;
                                        }
                                    }
                                    if (!z2) {
                                        break;
                                    }
                                }
                                iVar.f1247i = i6;
                                if (!z4) {
                                    vector2.add(iVar);
                                }
                                i5++;
                                i2 = 4;
                                i3 = 3;
                                i4 = 2;
                            }
                            do {
                                int i10 = 0;
                                z = false;
                                while (i10 < vector2.size()) {
                                    if (((C0318i) vector2.get(i10)).f1242d && ((C0318i) vector2.get(i10)).f1248j == 0) {
                                        vector2.remove(i10);
                                        i10--;
                                        z = true;
                                    }
                                    i10++;
                                }
                            } while (z);
                        }
                    } catch (Exception e2) {
                        /*ErrorReporter errorReporter = ACRA.getErrorReporter();
                        StringBuilder sb = new StringBuilder();
                        sb.append("id=");
                        sb.append(this.f1190E);
                        sb.append(" in=");
                        sb.append(this.f1077k == null);
                        errorReporter.putCustomData("extra", sb.toString());
                        ACRA.getErrorReporter().handleSilentException(e2);
                        ACRA.getErrorReporter().removeCustomData("extra");*/
                        e2.printStackTrace();
                    }
                }
                if (this.isLoading) {
                    return false;
                }
                try {
                    Document l3 = this.currentDocument.getDocument(14);
                    if (l3 != null) {
                        int i11 = 0;
                        vector = null;
                        while (i11 < l3.count()) {
                            try {
                                if (vector == null) {
                                    vector = new Vector(l3.count());
                                }
                                Document l4 = l3.getDocument(i11);
                                C0325n nVar = new C0325n();
                                nVar.f1258a = l4.getInt(i).intValue();
                                nVar.f1259b = l4.getString(1);
                                nVar.f1260c = l4.getInt(2).intValue();
                                nVar.f1261d = l4.getInt(3).intValue();
                                Document l5 = l4.getDocument(4);
                                nVar.f1262e = new C0325n.C0326a[l5.count()];
                                int i12 = 0;
                                while (i12 < l5.count()) {
                                    Document l6 = l5.getDocument(i12);
                                    C0325n.C0326a[] aVarArr = nVar.f1262e;
                                    aVarArr[i12] = new C0325n.C0326a();
                                    aVarArr[i12].f1263a = l6.getInt(i).intValue();
                                    nVar.f1262e[i12].f1264b = l6.getString(1);
                                    nVar.f1262e[i12].f1265c = l6.getInt(2).intValue();
                                    i12++;
                                    i = 0;
                                }
                                vector.add(nVar);
                                i11++;
                                i = 0;
                            } catch (Exception e3) {
                                /*e = e3;
                                ErrorReporter errorReporter2 = ACRA.getErrorReporter();
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("id=");
                                sb2.append(this.f1190E);
                                sb2.append(" in=");
                                sb2.append(this.f1077k == null);
                                errorReporter2.putCustomData("extra", sb2.toString());
                                //ACRA.getErrorReporter().handleSilentException(e);
                                ACRA.getErrorReporter().removeCustomData("extra");
                                MainActivity mainActivity = this.f1073g;
                                MainLayout mainLayout = mainActivity.f730a;
                                if (!this.f1075i) {
                                }*/
                                e3.printStackTrace();
                            }
                        }
                    } else {
                        vector = null;
                    }
                } catch (Exception e4) {
                    e = e4;
                    vector = null;
                }
                MainActivity mainActivity2 = this.mainActivity;
                MainLayout mainLayout2 = mainActivity2.mainLayout;
                if (!this.isLoading) {
                    return false;
                }
                int size = vector2.size() + 2 + 1;
                int[] iArr = new int[size];
                try {
                    SpannableString spannableString2 = a.f1230e;
                    iArr[0] = ArticleLayout.m980a(mainActivity2, spannableString2.subSequence(0, spannableString2.length()).toString(), a.f1237l, true, mainLayout2.getWidth());
                    int i13 = iArr[0] + ArticleLayout.f499x;
                    iArr[1] = i13;
                    int size2 = vector2.size() + 2;
                    int i14 = (int) (mainActivity2.f731b * 125.0f);
                    for (int i15 = 2; i15 < size2; i15++) {
                        i13 += i14;
                        iArr[i15] = i13;
                    }
                    iArr[size - 1] = i13 + ArticleLayout.f499x + ArticleLayout.f493r;
                } catch (Exception e5) {
                    /*ErrorReporter errorReporter3 = ACRA.getErrorReporter();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("id=");
                    sb3.append(this.f1190E);
                    sb3.append(" in=");
                    sb3.append(this.f1077k == null);
                    errorReporter3.putCustomData("extra", sb3.toString());
                    ACRA.getErrorReporter().handleSilentException(e5);
                    ACRA.getErrorReporter().removeCustomData("extra");*/
                    e5.printStackTrace();
                }
                if (this.isLoading) {
                    return false;
                }
                this.f1193H = a;
                this.f1201P = vector;
                this.f1200O = vector2;
                this.f1071B = iArr;
                if (vector != null) {
                    this.f1202Q = true;
                }
                this.currentDocument = null;
                return true;
            }
            throw new Exception("Article parsing failed");
        } catch (Exception e6) {
            /*ErrorReporter errorReporter4 = ACRA.getErrorReporter();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("id=");
            sb4.append(this.f1190E);
            sb4.append(" in=");
            if (this.f1077k != null) {
                z3 = false;
            }
            sb4.append(z3);
            errorReporter4.putCustomData("extra", sb4.toString());
            ACRA.getErrorReporter().handleSilentException(e6);
            ACRA.getErrorReporter().removeCustomData("extra");*/
            e6.printStackTrace();
            return false;
        }
    }

    @Override
    public void mo142J(boolean z) {
        if (z && this.f1191F) {
            m813W(1);
        } else if (!z || this.f1192G <= 0) {
            super.mo142J(z);
        } else {
            int i = 0;
            while (true) {
                if (i >= this.f1200O.size()) {
                    break;
                } else if (this.f1200O.get(i).f1239a == this.f1192G) {
                    m813W(i + 1);
                    break;
                } else {
                    i++;
                }
            }
        }
        this.f1191F = false;
        this.f1192G = 0;
        if (isCurrentTab() && this.tab.m717i()) {
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f800H = true;
            mainLayout.f799G = true;
        }
        this.f1196K = null;
        DocumentManager.getResultRequest(new C0324m());
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0315g());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        }
        o1Var.addMenuItem(0, 0, 1, "Копировать ссылку");
        o1Var.addMenuItem(0, 0, 2, "Открыть в браузере");
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            if (isUnsucces()) {
                MainLayout mainLayout = this.tab.mainLayout;
                mainLayout.f800H = true;
                mainLayout.f799G = true;
            }
            Form_Post wVar = this.forumPost;
            if (wVar != null && wVar.m201t()) {
                this.forumPost.m199v();
            }
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        Util.copyToClipboard(this.mainActivity, str, "Текст скопирован в буфер");
    }

    @Override
    public void mo135b(Form_Post.ForumPostModel kVar, boolean z) {
        if (!TextUtils.isEmpty(kVar.postMessage)) {
            DocumentManager.getResultRequest(new API.ReportRequest(this.mainActivity, 1, kVar.postId, kVar.postMessage));
        }
        this.forumPostModel = null;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0311e(bBDisplay, pVar, cVar), true);
        int i = cVar.f543a;
        if (i >= 0) {
            o1Var.addMenuItem(0, 0, 0, Util.C0427h.urlDecode(pVar.f2202I.get(i).link), true, false);
            o1Var.addMenuItem(0, 0, 27, "Копировать ссылку");
            if (Urls2.is4pdaHost(pVar.f2202I.get(cVar.f543a).link)) {
                o1Var.addMenuItem(0, 0, 21, "Открыть в новой вкладке");
            }
            o1Var.addMenuItem(0, 0, 26, "Открыть с помощью");
        }
        int i2 = cVar.f544b;
        if (i2 >= 0 && cVar.f545c >= 0 && pVar.f2212S[i2].f2253g > 0) {
            o1Var.addMenuItem(0, 0, 22, "Открыть изображение");
        }
        int i3 = cVar.f545c;
        if (i3 >= 0 && bBDisplay.f531h[i3] == null) {
            o1Var.addMenuItem(0, 0, 24, "Загрузить изображение");
        }
        if (bBDisplay.f528e.m948l()) {
            o1Var.addMenuItem(0, 0, 3, "Копировать");
        }
        o1Var.show(null);
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f544b;
        if (i >= 0) {
            BBString.C0670a[] aVarArr = pVar.f2212S;
            if (aVarArr[i].f2249c) {
                if (aVarArr[i].f2253g <= 0) {
                    new ImageDialog(this.mainActivity).m917b(pVar.f2212S[cVar.f544b].f2250d);
                    return;
                } else if (((C0316h) pVar.f2221a0).f1238m != null) {
                    for (int i2 = 0; i2 < ((C0316h) pVar.f2221a0).f1238m.size(); i2++) {
                        if (((C0316h) pVar.f2221a0).f1238m.get(i2).f723a == pVar.f2212S[cVar.f544b].f2247a) {
                            new ImageDialog(this.mainActivity).m916c(((C0316h) pVar.f2221a0).f1238m, i2);
                            return;
                        }
                    }
                    return;
                } else {
                    new ImageDialog(this.mainActivity).m917b((String) pVar.f2212S[cVar.f544b].f2255i);
                    return;
                }
            }
        }
        int i3 = cVar.f543a;
        if (i3 >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i3);
            int i4 = kVar.statusCode;
            int i5 = 2;
            if (i4 == 1) {
                MainActivity mainActivity = this.mainActivity;
                String str = kVar.link;
                if (!this.f1203R) {
                    i5 = 1;
                }
                Page b = Urls2.openPage(mainActivity, str, false, i5);
                if (b != null) {
                    if (this.f1203R) {
                        Tab f1Var = new Tab(this.mainActivity);
                        f1Var.addPage(b);
                        this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    } else {
                        this.tab.addPage(b);
                    }
                }
                this.f1203R = false;
            } else if (i4 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        }
    }

    void m759f0(RelativeLayout relativeLayout) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f = this.mainActivity.f731b;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < this.f1201P.size()) {
            C0325n nVar = this.f1201P.get(i7);
            TextView textView = new TextView(this.mainActivity);
            textView.setText(nVar.f1259b);
            textView.setTextSize(20.0f);
            textView.setTextColor(Skin.SkinColorModel.mainTextColor);
            int i9 = i8 + 1;
            textView.setId(i9);
            int i10 = (int) (f * 16.0f);
            int i11 = (int) (8.0f * f);
            textView.setPadding(i10, i6, i10, i11);
            relativeLayout.addView(textView);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            int i12 = 9;
            layoutParams.addRule(9);
            if (1 == i9) {
                layoutParams.addRule(10);
            } else {
                layoutParams.addRule(3, i9 - 1);
            }
            int i13 = nVar.f1260c;
            float f2 = 0.0f;
            float f3 = 15.0f;
            int i14 = 16;
            if ((i13 & 4) != 0) {
                int i15 = nVar.f1261d;
                int i16 = 0;
                while (i16 < nVar.f1262e.length) {
                    TextView textView2 = new TextView(this.mainActivity);
                    textView2.setText(nVar.f1262e[i16].f1264b);
                    textView2.setTextSize(f3);
                    textView2.setTextColor(Skin.SkinColorModel.mainTextColor);
                    int i17 = i9 + 1;
                    textView2.setId(i17);
                    textView2.setGravity(i14);
                    int i18 = (int) (f * 4.0f);
                    int i19 = (int) (f * f2);
                    textView2.setPadding(i10, i18, i10, i19);
                    relativeLayout.addView(textView2);
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
                    layoutParams2.addRule(i12);
                    layoutParams2.addRule(3, i17 - 1);
                    Integer valueOf = Integer.valueOf(nVar.f1262e[i16].f1265c);
                    TextView textView3 = new TextView(this.mainActivity);
                    textView3.setText(valueOf.toString());
                    textView3.setTextSize(16.0f);
                    textView3.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView3.setTypeface(null, 1);
                    i9 = i17 + 1;
                    textView3.setId(i9);
                    textView3.setGravity(16);
                    textView3.setPadding(i19, 0, i19, i18);
                    relativeLayout.addView(textView3);
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
                    layoutParams3.addRule(9);
                    layoutParams3.addRule(3, i9 - 1);
                    layoutParams3.width = (int) (50.0f * f);
                    layoutParams3.leftMargin = i10;
                    View view = new View(this.mainActivity);
                    view.setBackgroundColor(Skin.SkinColorModel.mainTextColor);
                    relativeLayout.addView(view);
                    RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams4.addRule(8, i9);
                    layoutParams4.addRule(1, i9);
                    if (i15 > 0) {
                        i4 = i7;
                        i5 = (valueOf.intValue() * (this.mainActivity.mainLayout.getWidth() - ((int) (f * 142.0f)))) / i15;
                    } else {
                        i4 = i7;
                        i5 = 0;
                    }
                    layoutParams4.width = i5;
                    layoutParams4.height = i11;
                    layoutParams4.bottomMargin = (int) (10.0f * f);
                    TextView textView4 = new TextView(this.mainActivity);
                    textView4.setSingleLine();
                    Object[] objArr = new Object[1];
                    objArr[0] = Float.valueOf(i15 > 0 ? (((float) valueOf.intValue()) * 100.0f) / ((float) i15) : 0.0f);
                    textView4.setText(String.format("%.2f%%", objArr));
                    textView4.setTextSize(16.0f);
                    textView4.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView4.setGravity(21);
                    textView4.setPadding(i19, 0, i19, i18);
                    relativeLayout.addView(textView4);
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) textView4.getLayoutParams();
                    layoutParams5.addRule(11);
                    layoutParams5.addRule(8, i9);
                    layoutParams5.width = (int) (55.0f * f);
                    layoutParams5.rightMargin = i10;
                    i16++;
                    i7 = i4;
                    f3 = 15.0f;
                    i14 = 16;
                    i12 = 9;
                    f2 = 0.0f;
                }
                i = i7;
                TextView textView5 = new TextView(this.mainActivity);
                textView5.setText("Всего голосов: " + i15);
                textView5.setTextSize(16.0f);
                i2 = i9 + 1;
                textView5.setId(i2);
                textView5.setPadding(0, (int) (f * 4.0f), 0, i11);
                textView5.setTextColor(Skin.SkinColorModel.labelTextCsl);
                relativeLayout.addView(textView5);
                RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) textView5.getLayoutParams();
                layoutParams6.addRule(14);
                layoutParams6.addRule(3, i2 - 1);
            } else {
                i = i7;
                boolean z = (i13 & 1) != 0;
                if ((i13 & 2) != 0) {
                    i3 = 0;
                    int i20 = 0;
                    while (i20 < nVar.f1262e.length) {
                        Widgets$CheckboxTextView widgets$CheckboxTextView = new Widgets$CheckboxTextView(this.mainActivity);
                        widgets$CheckboxTextView.setClickable(true);
                        widgets$CheckboxTextView.setText(nVar.f1262e[i20].f1264b);
                        widgets$CheckboxTextView.setTextSize(15.0f);
                        int i21 = i9 + 1;
                        widgets$CheckboxTextView.setId(i21);
                        widgets$CheckboxTextView.setTextColor(Skin.SkinColorModel.mainTextColor);
                        widgets$CheckboxTextView.setGravity(16);
                        widgets$CheckboxTextView.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.checkbox_left));
                        int i22 = (int) (f * 4.0f);
                        widgets$CheckboxTextView.setPadding(i10, i22, 0, i22);
                        nVar.f1262e[i20].f1266d = widgets$CheckboxTextView;
                        relativeLayout.addView(widgets$CheckboxTextView);
                        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) widgets$CheckboxTextView.getLayoutParams();
                        layoutParams7.width = -1;
                        layoutParams7.addRule(9);
                        layoutParams7.addRule(3, i21 - 1);
                        layoutParams7.leftMargin = i10;
                        layoutParams7.rightMargin = i10;
                        i20++;
                        i3 = i21;
                        i9 = i3;
                    }
                } else {
                    RadioGroup radioGroup = new RadioGroup(this.mainActivity);
                    radioGroup.setOrientation(1);
                    int i23 = i9 + 1;
                    radioGroup.setId(i23);
                    relativeLayout.addView(radioGroup);
                    RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) radioGroup.getLayoutParams();
                    layoutParams8.width = -1;
                    layoutParams8.addRule(9);
                    layoutParams8.addRule(3, i23 - 1);
                    int i24 = i23;
                    for (int i25 = 0; i25 < nVar.f1262e.length; i25++) {
                        RadioButton radioButton = new RadioButton(this.mainActivity);
                        radioButton.setButtonDrawable((Drawable) null);
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable(R.drawable.radio_button), (Drawable) null, (Drawable) null, (Drawable) null);
                        radioButton.setTextColor(Skin.SkinColorModel.mainTextColor);
                        radioButton.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
                        radioButton.setText(nVar.f1262e[i25].f1264b);
                        radioButton.setTextSize(15.0f);
                        i24++;
                        radioButton.setId(i24);
                        radioButton.setGravity(16);
                        int i26 = (int) (f * 0.0f);
                        int i27 = (int) (f * 4.0f);
                        radioButton.setPadding(i26, i27, i26, i27);
                        radioButton.setEnabled(z);
                        nVar.f1262e[i25].f1266d = radioButton;
                        radioGroup.addView(radioButton);
                        ((RadioGroup.LayoutParams) radioButton.getLayoutParams()).width = -1;
                        ((RadioGroup.LayoutParams) radioButton.getLayoutParams()).leftMargin = i10;
                        ((RadioGroup.LayoutParams) radioButton.getLayoutParams()).rightMargin = i10;
                    }
                    i3 = i23;
                    i9 = i24;
                }
                TextView textView6 = new TextView(this.mainActivity);
                textView6.setText("ГОЛОСОВАТЬ");
                textView6.setOnClickListener(new View$OnClickListenerC0310d(nVar));
                textView6.setTextSize(16.0f);
                textView6.setTypeface(null, 1);
                textView6.setGravity(17);
                textView6.setTextColor(Skin.SkinColorModel.btnTextColor);
                i2 = i9 + 1;
                textView6.setId(i2);
                textView6.setPadding(i10, 0, i10, 0);
                textView6.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
                relativeLayout.addView(textView6);
                RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) textView6.getLayoutParams();
                layoutParams9.addRule(9);
                layoutParams9.addRule(3, i3);
                layoutParams9.height = (int) (36.0f * f);
                layoutParams9.leftMargin = i10;
                layoutParams9.topMargin = (int) (12.0f * f);
            }
            View view2 = new View(this.mainActivity);
            i8 = i2 + 1;
            view2.setId(i8);
            relativeLayout.addView(view2);
            RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
            layoutParams10.addRule(9);
            layoutParams10.addRule(3, i8 - 1);
            layoutParams10.width = -1;
            layoutParams10.height = (int) (32.0f * f);
            i7 = i + 1;
            i6 = 0;
        }
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces()) {
            return 0;
        }
        int i2 = (this.f1196K != null ? 1 : 0) + 1;
        List<C0318i> list = this.f1200O;
        if (list != null) {
            i = list.size() + 1 + (m758g0() ? 1 : 0);
        }
        return i + i2;
    }

    @Override
    public long getItemId(int i) {
        if (i == 1) {
            return (long) this.f1190E;
        }
        return -1;
    }

    @Override
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (m758g0() && i == getCount() - 1) {
            return 4;
        }
        if (this.f1196K == null || i - 1 != 0) {
            return i == 1 ? 2 : 3;
        }
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        int i2;
        TextView textView;
        int itemViewType = getItemViewType(i);
        int width = this.mainActivity.mainLayout.getWidth();
        if (width == this.f1197L || itemViewType != 1) {
            view2 = view;
        } else {
            this.f1197L = width;
            view2 = null;
        }
        int i3 = 0;
        View articleLayout = view2;
        if (view2 == null) {
            MainActivity mainActivity = this.mainActivity;
            float f = mainActivity.f731b;
            if (itemViewType == 0) {
                ArticleLayout articleLayout2 = (ArticleLayout) mainActivity.getLayoutInflater().inflate(R.layout.article, viewGroup, false);
                articleLayout2.f515n = true;
                articleLayout2.f505d.setOnClickListener(new View$OnClickListenerC0307a(articleLayout2));
                articleLayout2.f504c.setOnClickListener(new View$OnClickListenerC0308b());
                articleLayout2.f507f.setCallback(this);
                articleLayout = articleLayout2;
                if (this.f1201P != null) {
                    m759f0((RelativeLayout) articleLayout2.findViewById(R.id.articlePolls));
                    this.f1202Q = false;
                    articleLayout = articleLayout2;
                }
            } else if (itemViewType == 1) {
                LinearLayout linearLayout = (LinearLayout) mainActivity.getLayoutInflater().inflate(R.layout.imagelayout, viewGroup, false);
                ImageView imageView = (ImageView) linearLayout.findViewById(R.id.imagesView);
                ((LinearLayout.LayoutParams) imageView.getLayoutParams()).width = Math.min(width, (int) (((float) this.f1196K.getInt(0).intValue()) * this.mainActivity.f731b));
                imageView.setOnClickListener(new View$OnClickListenerC0309c());
                PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, this.f1196K.getString(2));
                l.to(imageView);
                l.runAsync();
                new AsyncTaskC0327o(this, null).execute(this.f1196K.getString(3));
                articleLayout = linearLayout;
            } else if (itemViewType == 2) {
                articleLayout = mainActivity.getLayoutInflater().inflate(R.layout.commentheader_top, viewGroup, false);
            } else if (itemViewType == 4) {
                articleLayout = mainActivity.getLayoutInflater().inflate(R.layout.commentheader_bottom, viewGroup, false);
            } else {
                articleLayout = mainActivity.getLayoutInflater().inflate(R.layout.comment, viewGroup, false);
            }
        }
        if (itemViewType == 0) {
            ArticleLayout articleLayout3 = (ArticleLayout) articleLayout;
            PicoImgRequest l2 = PicoImg.loadUrl(this.mainActivity, this.f1193H.f1233h);
            l2.to(articleLayout3.f502a);
            l2.runAsync();
            articleLayout3.f503b.setText(this.f1193H.f1227b);
            articleLayout3.f504c.setText(this.f1193H.f1232g);
            articleLayout3.f505d.setText(Integer.valueOf(this.f1193H.f1234i).toString());
            articleLayout3.f506e.setText(this.f1193H.f1230e);
            articleLayout3.f507f.setBBString(this.f1193H.f1237l);
            if (this.f1193H.f1235j != null) {
                i2 = 0;
                for (int i4 = 0; i4 < this.f1193H.f1235j.size(); i4++) {
                    int keyAt = this.f1193H.f1235j.keyAt(i4);
                    String valueAt = this.f1193H.f1235j.valueAt(i4);
                    if (!valueAt.startsWith("!!")) {
                        if (i2 >= articleLayout3.f509h.getChildCount()) {
                            textView = new TextView(this.mainActivity);
                            textView.setSingleLine(true);
                            textView.setTextAppearance(this.mainActivity, 16973894);
                            textView.setTextColor(Skin.SkinColorModel.labelTextCsl);
                            textView.setGravity(16);
                            articleLayout3.f509h.addView(textView);
                            ((LinearLayout.LayoutParams) textView.getLayoutParams()).height = -1;
                            textView.setOnClickListener(this.f1194I);
                        } else {
                            textView = (TextView) articleLayout3.f509h.getChildAt(i2);
                            textView.setVisibility(0);
                        }
                        i2++;
                        StringBuilder sb = new StringBuilder();
                        sb.append(1 == i2 ? "# " : ", ");
                        sb.append(valueAt);
                        textView.setText(sb.toString());
                        textView.setTag(Integer.valueOf(keyAt));
                    }
                }
            } else {
                i2 = 0;
            }
            while (i2 < articleLayout3.f509h.getChildCount()) {
                articleLayout3.f509h.getChildAt(i2).setVisibility(8);
                i2++;
            }
            if (this.f1202Q) {
                RelativeLayout relativeLayout = (RelativeLayout) articleLayout3.findViewById(R.id.articlePolls);
                relativeLayout.removeAllViews();
                m759f0(relativeLayout);
                this.f1202Q = false;
            }
        } else if (itemViewType == 2 || itemViewType == 4) {
            int i5 = this.f1193H.f1234i;
            String str = i5 + " ";
            int i6 = i5 % 10;
            if (i6 == 0 || ((i5 >= 11 && i5 <= 19) || (i6 >= 5 && i6 <= 9))) {
                str = str + "комментариев";
            } else if (i6 == 1) {
                str = str + "комментарий";
            } else if (i6 >= 2 && i6 <= 4) {
                str = str + "комментария";
            }
            ((TextView) articleLayout.findViewById(R.id.commentsHeaderCount)).setText(str);
            TextView textView2 = (TextView) articleLayout.findViewById(R.id.commentsHeaderBtn);
            if (!this.f1193H.f1229d || !DocumentManager.isLoggined()) {
                textView2.setVisibility(4);
            } else {
                textView2.setVisibility(0);
                textView2.setTag(-1);
                textView2.setOnClickListener(this.f1195J);
            }
        } else if (itemViewType == 3) {
            int i7 = i - (this.f1196K != null ? 3 : 2);
            TextView textView3 = (TextView) articleLayout.findViewById(R.id.commentAuthor);
            TextView textView4 = (TextView) articleLayout.findViewById(R.id.commentKarma);
            TextView textView5 = (TextView) articleLayout.findViewById(R.id.commentDate);
            View findViewById = articleLayout.findViewById(R.id.commentSeparator);
            TextView textView6 = (TextView) articleLayout.findViewById(R.id.commentText);
            if (this.f1200O.get(i7).f1242d) {
                textView6.setText("(Комментарий удален)");
                textView6.setOnLongClickListener(null);
                textView6.setEnabled(false);
                textView3.setVisibility(8);
                textView4.setVisibility(8);
                textView5.setVisibility(8);
                findViewById.setVisibility(8);
                textView6.setVisibility(0);
            } else {
                C0318i iVar = this.f1200O.get(i7);
                textView6.setText(iVar.f1246h);
                textView6.setEnabled(true);
                textView5.setVisibility(0);
                textView5.setText(iVar.f1240b);
                textView3.setVisibility(0);
                textView3.setText(iVar.f1244f);
                textView3.setTag(Integer.valueOf(i7));
                textView3.setOnClickListener(this.f1195J);
                findViewById.setVisibility(0);
                textView4.setVisibility(0);
                String str2 = "";
                if (iVar.f1249k != 0) {
                    StringBuilder sb2 = new StringBuilder();
                    if (iVar.f1249k > 0) {
                        str2 = "+";
                    }
                    sb2.append(str2);
                    sb2.append(Integer.valueOf(iVar.f1249k).toString());
                    str2 = sb2.toString();
                }
                textView4.setText(str2);
                textView6.setVisibility((iVar.f1241c & 4) != 0 ? 8 : 0);
                textView6.setTag(Integer.valueOf(i7));
                if (iVar.f1247i <= 8 && this.f1193H.f1229d) {
                    textView6.setOnLongClickListener(this.f1195J);
                    textView3.setOnLongClickListener(this.f1195J);
                }
            }
            float f2 = this.mainActivity.f731b;
            int i8 = (int) ((5.0f * f2) + 0.5f);
            int i9 = (int) ((f2 * 4.0f) + 0.5f);
            int i10 = (int) ((((float) ((!this.f1193H.f1229d || this.f1200O.get(i7).f1247i > 8 || this.f1200O.get(i7).f1242d) ? 12 : 5)) * this.mainActivity.f731b) + 0.5f);
            int i11 = i7 == 0 ? i8 * 3 : 0;
            if (i == getCount() - 2) {
                i3 = i8 * 3;
            }
            int i12 = i10 + i3;
            if (this.f1200O.get(i7).f1242d) {
                i11 += i9;
            }
            articleLayout.setPadding(this.f1200O.get(i7).f1247i * i9 * 3, i11, i8 * 2, i12);
        }
        return articleLayout;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    public void m757h0(int i, int i2) {
        if (i2 >= 0) {
            C0318i iVar = this.f1200O.get(i2);
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0312f(iVar), true);
            if (DocumentManager.isLoggined() && (iVar.f1241c & 4) == 0) {
                o1Var.addMenuItem(i, i2, 1, "Ответить");
            }
            o1Var.addMenuItem(i, i2, 2, "Профиль пользователя");
            if ((this.f1193H.f1228c & 16) != 0 && (iVar.f1241c & 16) == 0) {
                o1Var.addMenuItem(i, i2, 3, "Плюс в карму");
                o1Var.addMenuItem(i, i2, 4, "Скрыть");
            }
            if (DocumentManager.isLoggined()) {
                o1Var.addMenuItem(i, i2, 5, "Пожаловаться");
            }
            o1Var.show(null);
        }
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f1193H = null;
        this.f1200O = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        int i;
        int i2;
        int i3;
        if (!isUnsucces()) {
            return null;
        }
        if (this.f1193H.f1235j != null) {
            i3 = 0;
            i2 = 0;
            i = 0;
            for (int i4 = 0; i4 < this.f1193H.f1235j.size(); i4++) {
                DataDB.BookMarkModel s = DataDB.m358s(this.f1193H.f1235j.keyAt(i4));
                if (s != null) {
                    i3 = s.f2505a;
                    i = s.f2510f;
                    i2 = i3;
                    if (i != 0) {
                        break;
                    }
                }
            }
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        while (i2 != 0 && i != 0) {
            DataDB.BookMarkModel r = DataDB.m359r(i);
            if (r == null) {
                break;
            }
            i2 = r.f2505a;
            i = r.f2510f;
        }
        Vector vector = new Vector(50);
        vector.add(new Breadcrumb.C0725a(100000, "page/1", "Главная", 0, false, false));
        m761d0(vector, 0, i2, i3);
        return (Breadcrumb.C0725a[]) vector.toArray(new Breadcrumb.C0725a[0]);
    }

    @Override
    public String getLink() {
        if (this.f1206U == null) {
            if (this.currentDocument == null) {
                return "?p=" + this.f1190E;
            }
            Time time = new Time("UTC");
            time.set(((long) this.currentDocument.getInt(1).intValue()) * 1000);
            this.f1206U = time.format("%Y/%m/%d/") + this.f1190E + "/";
        }
        return this.f1206U;
    }

    public Page_Article(MainActivity mainActivity, int i, int i2) {
        this(mainActivity, i, false);
        this.f1192G = i2;
    }

    public Page_Article(MainActivity mainActivity, C0316h hVar, List<C0316h> list) {
        this(mainActivity, hVar.f1226a, false);
        this.f1198M = list;
        if (list != null) {
            this.f1199N = list.indexOf(hVar);
        }
        this.f1193H = hVar;
        this.title = hVar.f1230e.toString();
        tabLoaded(true);
        DocumentManager.getResultRequest(this);
    }
}
