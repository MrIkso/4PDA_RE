package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;

public class Page_Site_List extends Page implements BBDisplay.IBBDisplayCallback {
    static int f2688K = 30;
    int f2689E;
    int f2690F;
    int f2691G;
    List<Page_Article.C0316h> f2692H;
    View$OnClickListenerC0794f f2693I = new View$OnClickListenerC0794f(this);
    boolean f2694J;

    class View$OnClickListenerC0789a implements View.OnClickListener {
        View$OnClickListenerC0789a() {
        }

        @Override
        public void onClick(View view) {
            Page_Site_List t0Var = Page_Site_List.this;
            t0Var.tab.addPage(new Page_Article(t0Var.mainActivity, ((Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag()).f1226a, true));
        }
    }

    class View$OnClickListenerC0790b implements View.OnClickListener {
        View$OnClickListenerC0790b() {
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Site_List.this.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Site_List.this.mainActivity, ((Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag()).f1231f, 0));
            Page_Site_List.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    class View$OnClickListenerC0791c implements View.OnClickListener {
        View$OnClickListenerC0791c() {
        }

        @Override
        public void onClick(View view) {
            Page_Site_List t0Var = Page_Site_List.this;
            t0Var.tab.addPage(new Page_Article(t0Var.mainActivity, (Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag(), t0Var.f2692H));
        }
    }

    class C0792d implements OptionPoupupMenuView.IClickListener {
        final BBDisplay f2698a;
        final BBString f2699b;
        final BBDisplay.C0143c f2700c;

        C0792d(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
            this.f2698a = bBDisplay;
            this.f2699b = pVar;
            this.f2700c = cVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Site_List t0Var = Page_Site_List.this;
                t0Var.f2694J = true;
                t0Var.mo129f(this.f2698a, this.f2699b, this.f2700c);
            } else if (i3 == 24) {
                this.f2698a.m977d(this.f2700c.f545c, true);
            } else if (i3 == 1) {
                Tab f1Var = new Tab(Page_Site_List.this.mainActivity);
                Page_Site_List t0Var2 = Page_Site_List.this;
                f1Var.addPage(new Page_Article(t0Var2.mainActivity, (Page_Article.C0316h) this.f2699b.f2221a0, t0Var2.f2692H));
                Page_Site_List.this.mainActivity.mainLayout.setCurrentTab(f1Var);
            }
        }
    }

    class C0793e implements OptionPoupupMenuView.IClickListener {
        C0793e() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Site_List.this.tabReload();
            } else if (i3 == 22) {
                Page_Site_List t0Var = Page_Site_List.this;
                DataDB.m365l(t0Var.title, t0Var.getLink());
            }
        }
    }

    static class View$OnClickListenerC0794f implements View.OnClickListener {
        Page_Site_List f2703a;

        public View$OnClickListenerC0794f(Page_Site_List t0Var) {
            this.f2703a = t0Var;
        }

        @Override
        public void onClick(View view) {
            this.f2703a.tab.addPage(new Page_Site_List(this.f2703a.mainActivity, ((Integer) view.getTag()).intValue(), 0));
        }
    }

    public Page_Site_List(MainActivity mainActivity, int i, int i2) {
        super(mainActivity, 27763, new Document(Integer.valueOf(i2), Integer.valueOf(f2688K), -1, Integer.valueOf(i)));
        this.iconId = R.drawable.ic_nav_site;
        this.f2689E = i;
        this.f2690F = i2;
        this.title = String.format("Новости: %d %d(%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(f2688K));
        this.statusMessage = String.format("Загрузка сайта %d", Integer.valueOf(this.f2689E));
    }

    @Override
    protected boolean onPageLoaded() {
        Exception e;
        Document uVar;
        if (this.isLoading) {
            return false;
        }
        if (this.f2689E == 0) {
            this.title = "Новости";
        }
        this.f2691G = this.currentDocument.getInt(0).intValue();
        this.f2692H = null;
        Document l = this.currentDocument.getDocument(1);
        if (l == null) {
            return false;
        }
        Vector vector = new Vector(l.count());
        for (int i = 0; i < l.count(); i++) {
            try {
                uVar = l.getDocument(i);
            } catch (Exception e2) {
                e = e2;
                uVar = null;
            }
            try {
                Page_Article.C0316h a = Page_Article.C0316h.m755a(uVar, this.f2689E);
                if (a != null) {
                    vector.add(a);
                }
                if (!TextUtils.isEmpty(a.f1236k)) {
                    this.title = a.f1236k.startsWith("!!") ? a.f1236k.substring(2) : a.f1236k;
                }
            } catch (Exception e3) {
                e = e3;
               /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                StringBuilder sb = new StringBuilder();
                sb.append("Lost SitePost ");
                sb.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                errorReporter.handleSilentException(new Exception(sb.toString(), e));*/
                e.printStackTrace();
            }
        }
        boolean T = m816T();
        int i2 = (T == true ? 1 : 0) + 1;
        int[] iArr = new int[vector.size() + i2 + (T? 1:0)];
        this.f1071B = iArr;
        iArr[0] = m816T() ? Page.f1069D : 0;
        int[] iArr2 = this.f1071B;
        iArr2[T == true ? 1 : 0] = iArr2[0] + ArticleLayout.f493r;
        int width = this.mainActivity.mainLayout.getWidth();
        int i3 = this.f1071B[T? 1:0];
        for (int i4 = 0; i4 < vector.size() && !this.isLoading; i4++) {
            i3 += ArticleLayout.m980a(this.mainActivity, ((Page_Article.C0316h) vector.get(i4)).f1230e.subSequence(0, ((Page_Article.C0316h) vector.get(i4)).f1230e.length()).toString(), ((Page_Article.C0316h) vector.get(i4)).f1237l, false, width);
            this.f1071B[i2 + i4] = i3;
        }
        if (m816T()) {
            int[] iArr3 = this.f1071B;
            iArr3[iArr3.length - 1] = iArr3[iArr3.length - 2] + Page.f1069D;
        }
        this.f2692H = vector;
        this.currentDocument = null;
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0793e());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        }
        o1Var.show(view);
    }

    @Override
    int mo141P() {
        int i = this.f2691G;
        int i2 = f2688K;
        return (i / i2) + (i % i2 != 0 ? 1 : 0);
    }

    @Override
    int mo140Q() {
        return (this.f2690F / f2688K) + 1;
    }

    @Override
    Page mo139R(int i) {
        Page_Site_List t0Var = new Page_Site_List(this.mainActivity, this.f2689E, (i - 1) * f2688K);
        t0Var.title = this.title;
        return t0Var;
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0792d(bBDisplay, pVar, cVar), true);
        int i = cVar.f543a;
        if (i >= 0 && Urls2.is4pdaHost(pVar.f2202I.get(i).link)) {
            o1Var.addMenuItem(0, 0, 21, "Открыть ссылку в новой вкладке");
        }
        int i2 = cVar.f545c;
        if (i2 >= 0 && bBDisplay.f531h[i2] == null) {
            o1Var.addMenuItem(0, 0, 24, "Загрузить изображение");
        }
        o1Var.addMenuItem(0, 0, 1, "Открыть в новой вкладке");
        o1Var.show(null);
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            if (i2 == 1) {
                Page b = Urls2.openPage(this.mainActivity, kVar.link, false, 2);
                if (b != null) {
                    Tab f1Var = new Tab(this.mainActivity);
                    f1Var.addPage(b);
                    this.mainActivity.mainLayout.setCurrentTab(f1Var);
                }
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        } else {
            this.tab.addPage(new Page_Article(this.mainActivity, (Page_Article.C0316h) pVar.f2221a0, this.f2692H));
        }
    }

    @Override
    public int getCount() {
        List<Page_Article.C0316h> list;
        int i = 0;
        if (!isUnsucces() || (list = this.f2692H) == null || list.size() == 0) {
            return 0;
        }
        int size = this.f2692H.size() + 1;
        if (m816T()) {
            i = this.f2690F > 0 ? 2 : 1;
        }
        return size + i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        int i2 = 0;
        if (m816T()) {
            if (this.f2690F > 0) {
                if (i == 0) {
                    return 0;
                }
                i2 = 1;
            }
            if (getCount() - 1 == i) {
                return 3;
            }
        }
        if (i == i2) {
            return 1;
        }
        return 2;
    }

    @SuppressLint({"WrongConstant", "ResourceType"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        int itemViewType = getItemViewType(i);
        int i2 = 2;
        int i3 = 0;
        View articleLayout = view;
        articleLayout = view;
        if (view == null) {
            if (itemViewType == 0) {
                articleLayout = m817S(viewGroup, false);
            } else if (itemViewType == 3) {
                articleLayout = m817S(viewGroup, true);
            } else if (itemViewType == 1) {
                View view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
                articleLayout = view2;
            } else if (itemViewType == 2) {
                ArticleLayout articleLayout2 = (ArticleLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.sitepost, viewGroup, false);
                articleLayout2.f515n = false;
                articleLayout2.f505d.setOnClickListener(new View$OnClickListenerC0789a());
                articleLayout2.f504c.setOnClickListener(new View$OnClickListenerC0790b());
                View$OnClickListenerC0791c cVar = new View$OnClickListenerC0791c();
                articleLayout2.f502a.setOnClickListener(cVar);
                articleLayout2.f506e.setOnClickListener(cVar);
                articleLayout2.f507f.setCallback(this);
                articleLayout = articleLayout2;
            }
        }
        if (itemViewType == 2) {
            List<Page_Article.C0316h> list = this.f2692H;
            if (!m816T() || this.f2690F <= 0) {
                i2 = 1;
            }
            Page_Article.C0316h hVar = list.get(i - i2);
            ArticleLayout articleLayout3 = (ArticleLayout) articleLayout;
            articleLayout3.setTag(hVar);
            PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, hVar.f1233h);
            l.to(articleLayout3.f502a);
            l.sizeToView();
            l.runAsync();
            articleLayout3.f503b.setText(hVar.f1227b);
            articleLayout3.f504c.setText(hVar.f1232g);
            articleLayout3.f505d.setText(Integer.valueOf(hVar.f1234i).toString());
            articleLayout3.f506e.setText(hVar.f1230e);
            articleLayout3.f507f.setBBString(hVar.f1237l);
            if (hVar.f1235j != null) {
                int i4 = 0;
                for (int i5 = 0; i5 < hVar.f1235j.size(); i5++) {
                    int keyAt = hVar.f1235j.keyAt(i5);
                    String valueAt = hVar.f1235j.valueAt(i5);
                    if (!valueAt.startsWith("!!")) {
                        if (i4 >= articleLayout3.f509h.getChildCount()) {
                            textView = new TextView(this.mainActivity);
                            textView.setSingleLine(true);
                            textView.setTextAppearance(this.mainActivity, 0x1030046);
                            textView.setTextColor(Skin.C0353a.f1386h0);
                            textView.setGravity(16);
                            articleLayout3.f509h.addView(textView);
                            ((LinearLayout.LayoutParams) textView.getLayoutParams()).height = -1;
                            textView.setOnClickListener(this.f2693I);
                        } else {
                            textView = (TextView) articleLayout3.f509h.getChildAt(i4);
                            textView.setVisibility(0);
                        }
                        i4++;
                        StringBuilder sb = new StringBuilder();
                        sb.append(1 == i4 ? "# " : ", ");
                        sb.append(valueAt);
                        textView.setText(sb.toString());
                        textView.setTag(Integer.valueOf(keyAt));
                    }
                }
                i3 = i4;
            }
            while (i3 < articleLayout3.f509h.getChildCount()) {
                articleLayout3.f509h.getChildAt(i3).setVisibility(8);
                i3++;
            }
        } else if (itemViewType == 3) {
            m815U();
        }
        return articleLayout;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f2692H = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        int i;
        int i2;
        int i3;
        DataDB.C0737b s;
        if (!isUnsucces()) {
            return null;
        }
        int i4 = this.f2689E;
        if (i4 <= 0 || (s = DataDB.m358s(i4)) == null) {
            i3 = 0;
            i2 = 0;
            i = 0;
        } else {
            i2 = s.f2505a;
            i3 = s.f2510f;
            i = i2;
        }
        while (i2 != 0 && i3 != 0) {
            DataDB.C0737b r = DataDB.m359r(i3);
            if (r == null) {
                break;
            }
            i2 = r.f2505a;
            i3 = r.f2510f;
        }
        Vector vector = new Vector(50);
        vector.add(new Breadcrumb.C0725a(100000, "page/1", "Главная", 0, false, false));
        Page_Article.m761d0(vector, 0, i2, i);
        return (Breadcrumb.C0725a[]) vector.toArray(new Breadcrumb.C0725a[0]);
    }

    @Override
    public String getLink() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.f2689E != 0) {
            str = "tag/" + this.f2689E + "/";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("page/");
        sb.append((this.f2690F / f2688K) + 1);
        sb.append("/");
        return sb.toString();
    }
}
