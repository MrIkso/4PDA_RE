package ru.fourpda.client;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;

public class Page_Search extends Page implements BBDisplay.IBBDisplayCallback {
    int searchFlags;
    Document f2576F;
    Document f2577G;
    Document f2578H;
    String f2579I;
    String f2580J;
    int f2581K;
    int f2582L;
    List<C0768m> f2583M;
    SparseArray<Page_Topic.C0888b0> f2584N;
    View$OnClickListenerC0772p f2585O;
    View$OnClickListenerC0769n f2586P;
    View$OnClickListenerC0770o f2587Q;
    boolean f2588R;
    private String f2589S;

    class View$OnClickListenerC0755a implements View.OnClickListener {
        View$OnClickListenerC0755a() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Page_Search s0Var = Page_Search.this;
            s0Var.tab.addPage(new Page_Article(s0Var.mainActivity, ((Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag()).f1226a, false));
        }
    }

    public class C0756b implements OptionPoupupMenuView.IClickListener {
        final BBDisplay f2591a;
        final BBString f2592b;
        final BBDisplay.C0143c f2593c;

        C0756b(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
//            Page_Search.this
            this.f2591a = bBDisplay;
            this.f2592b = pVar;
            this.f2593c = cVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Search s0Var = Page_Search.this;
                s0Var.f2588R = true;
                s0Var.mo129f(this.f2591a, this.f2592b, this.f2593c);
            } else if (i3 == 22) {
                this.f2591a.m977d(this.f2593c.f545c, true);
            } else if (i3 == 26) {
                Urls2.m676g(Page_Search.this.mainActivity, this.f2592b.f2202I.get(this.f2593c.f543a).link);
            } else if (i3 == 1) {
                Object obj = this.f2592b.f2221a0;
                if (obj instanceof Page_Topic.PostModel) {
                    API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Search.this.mainActivity, 3, ((Page_Topic.PostModel) obj).postId);
                    lVar.m826s(true);
                    DocumentManager.getResultRequest(lVar);
                } else if (obj instanceof Page_Article.C0316h) {
                    Tab f1Var = new Tab(Page_Search.this.mainActivity);
                    f1Var.addPage(new Page_Article(Page_Search.this.mainActivity, ((Page_Article.C0316h) obj).f1226a, false));
                    Page_Search.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                }
            } else if (i3 == 5) {
                API.ForumModifyRequest.m821p(((Page_Topic.PostModel) this.f2592b.f2221a0).postId, null, 4, 0, 0, Page_Search.this, "удаление поста", "УДАЛИТЬ", null);
            } else if (i3 == 6) {
                API.ForumModifyRequest.m821p(((Page_Topic.PostModel) this.f2592b.f2221a0).postId, null, 5, 0, 0, Page_Search.this, "восстановление поста", "ВОССТАНОВИТЬ", null);
            } else if (i3 == 3 || i3 == 4) {
                API.ForumModifyRequest.m821p(((Page_Topic.PostModel) this.f2592b.f2221a0).postId, null, 1, 2, 3 == i3 ? 2 : 0, Page_Search.this, 3 == i3 ? "удаление поста" : "отображение поста", "ВЫПОЛНИТЬ", null);
            } else if (i3 == 7 || i3 == 8) {
                API.ForumModifyRequest.m821p(((Page_Topic.PostModel) this.f2592b.f2221a0).postId, null, 1, 2048, 7 == i3 ? 2048 : 0, Page_Search.this, 7 == i3 ? "защиту поста" : "снятие защиты поста", "ВЫПОЛНИТЬ", null);
            } else if (i3 == 9) {
                Page_Topic.movePosts(Page_Search.this, null, ((Page_Topic.PostModel) this.f2592b.f2221a0).postId);
            }
        }
    }

    public class C0757c implements OptionPoupupMenuView.IClickListener {

        class C0758a implements OptionPoupupMenuView.IClickListener {
            C0758a() {
//                C0757c.this
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 5) {
                    Page_Search s0Var = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var.f2584N, 4, 0, 0, s0Var, "удаление постов", "УДАЛИТЬ", null);
                } else if (i3 == 6) {
                    Page_Search s0Var2 = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var2.f2584N, 5, 0, 0, s0Var2, "восстановление постов", "ВОССТАНОВИТЬ", null);
                } else if (i3 == 3) {
                    Page_Search s0Var3 = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var3.f2584N, 1, 2, 2, s0Var3, "скрытие постов", "СКРЫТЬ", null);
                } else if (i3 == 4) {
                    Page_Search s0Var4 = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var4.f2584N, 1, 2, 0, s0Var4, "отображение постов", "ПОКАЗАТЬ", null);
                } else if (i3 == 7) {
                    Page_Search s0Var5 = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var5.f2584N, 1, 2048, 2048, s0Var5, "защиту постов", "ЗАЩИТИТЬ", null);
                } else if (i3 == 8) {
                    Page_Search s0Var6 = Page_Search.this;
                    API.ForumModifyRequest.m821p(0, s0Var6.f2584N, 1, 2048, 0, s0Var6, "снятие защиты постов", "СНЯТЬ", null);
                } else if (i3 == 9) {
                    Page_Search s0Var7 = Page_Search.this;
                    Page_Topic.movePosts(s0Var7, s0Var7.f2584N, 0);
                }
            }
        }

        C0757c() {
//            Page_Search.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Search.this.tabReload();
            } else if (i3 == 22) {
                MainActivity mainActivity = Page_Search.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/" + Page_Search.this.getLink(), "Ссылка скопирована");
            } else if (i3 == 23) {
                Page_Search s0Var = Page_Search.this;
                DataDB.m365l(s0Var.title, s0Var.getLink());
            } else if (24 == i3) {
                OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Search.this.mainActivity, new C0758a(), true);
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                boolean z5 = false;
                for (int i4 = 0; i4 < Page_Search.this.f2584N.size(); i4++) {
                    z2 = z2 || (Page_Search.this.f2584N.valueAt(i4).f3043c & 2) == 0;
                    z3 = z3 || (Page_Search.this.f2584N.valueAt(i4).f3043c & 2) != 0;
                    z4 = z4 || (Page_Search.this.f2584N.valueAt(i4).f3043c & 2048) == 0;
                    z5 = z5 || (Page_Search.this.f2584N.valueAt(i4).f3043c & 2048) != 0;
                    z = z || (Page_Search.this.f2584N.valueAt(i4).f3043c & 4) > 0;
                }
                o1Var.addMenuItem(0, 0, 5, "Удалить", false, true);
                if (z) {
                    o1Var.addMenuItem(0, 0, 6, "Восстановить", false, true);
                }
                if (z2) {
                    o1Var.addMenuItem(0, 0, 3, "Скрыть", false, true);
                }
                if (z3) {
                    o1Var.addMenuItem(0, 0, 4, "Показать", false, true);
                }
                if (z4) {
                    o1Var.addMenuItem(0, 0, 7, "Защитить", false, true);
                }
                if (z5) {
                    o1Var.addMenuItem(0, 0, 8, "Снять защиту", false, true);
                }
                o1Var.addMenuItem(0, 0, 9, "Переместить", false, true);
                o1Var.show(null);
            }
        }
    }

    class View$OnLongClickListenerC0759d implements View.OnLongClickListener {
        View$OnLongClickListenerC0759d() {
//            Page_Search.this
        }

        @Override
        public boolean onLongClick(View view) {
            BBDisplay bBDisplay = (BBDisplay) view.findViewById(R.id.PostCode);
            if (bBDisplay == null) {
                return true;
            }
            bBDisplay.f537n = 0.0f;
            bBDisplay.f536m = 0.0f;
            BBOverlay bBOverlay = bBDisplay.f528e;
            if (bBOverlay != null) {
                bBOverlay.f558e = 0.0f;
                bBOverlay.f557d = 0.0f;
            }
            Page_Search.this.showBBOptionMenu(bBDisplay, bBDisplay.f527d, new BBDisplay.C0143c());
            return true;
        }
    }

    class View$OnClickListenerC0760e implements View.OnClickListener {
        View$OnClickListenerC0760e() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Page_Topic.PostModel yVar = (Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag();
            if (yVar.f3167t != 0) {
                Tab f1Var = new Tab(Page_Search.this.mainActivity);
                f1Var.addPage(new Page_Topic(Page_Search.this.mainActivity, yVar.f3167t, 0));
                Page_Search.this.tab.mainLayout.setCurrentTab(f1Var);
            }
        }
    }

    class View$OnLongClickListenerC0761f implements View.OnLongClickListener {
        View$OnLongClickListenerC0761f() {
//            Page_Search.this
        }

        @Override
        public boolean onLongClick(View view) {
            Util.copyToClipboard(Page_Search.this.mainActivity, ((TextView) view).getText().toString(), "Ник скопирован в буфер обмена");
            return true;
        }
    }

    class View$OnClickListenerC0762g implements View.OnClickListener {
        View$OnClickListenerC0762g() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Page_Search s0Var = Page_Search.this;
            s0Var.tab.addPage(new Page_Reputation(s0Var.mainActivity, ((Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b));
        }
    }

    class View$OnClickListenerC0763h implements View.OnClickListener {
        View$OnClickListenerC0763h() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            boolean checked = ((Widgets$CheckboxView) view).getChecked();
            Page_Topic.PostModel yVar = (Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag();
            if (checked) {
                SparseArray<Page_Topic.C0888b0> sparseArray = Page_Search.this.f2584N;
                int i = yVar.postId;
                sparseArray.put(i, new Page_Topic.C0888b0(i, yVar.f3156i, yVar.f3159l, yVar.f3149b, yVar.f3150c));
                return;
            }
            Page_Search.this.f2584N.delete(yVar.postId);
        }
    }

    class View$OnLongClickListenerC0764i implements View.OnLongClickListener {
        View$OnLongClickListenerC0764i() {
//            Page_Search.this
        }

        @Override
        public boolean onLongClick(View view) {
            boolean checked = ((Widgets$CheckboxView) view).getChecked();
            for (int i = 0; i < Page_Search.this.f2583M.size(); i++) {
                Page_Topic.PostModel yVar = Page_Search.this.f2583M.get(i).f2607b;
                if (yVar != null) {
                    int i2 = yVar.f3159l;
                    if ((i2 & 512) != 0) {
                        if (!checked) {
                            SparseArray<Page_Topic.C0888b0> sparseArray = Page_Search.this.f2584N;
                            int i3 = yVar.postId;
                            sparseArray.put(i3, new Page_Topic.C0888b0(i3, yVar.f3156i, i2, yVar.f3149b, yVar.f3150c));
                        } else {
                            Page_Search.this.f2584N.delete(yVar.postId);
                        }
                    }
                }
            }
            for (int i4 = 0; i4 < Page_Search.this.tab.forumsListView.getChildCount(); i4++) {
                View childAt = Page_Search.this.tab.forumsListView.getChildAt(i4);
                Widgets$CheckboxView widgets$CheckboxView = (Widgets$CheckboxView) childAt.findViewById(R.id.postCheckbox);
                if (!(widgets$CheckboxView == null || (((Page_Topic.PostModel) childAt.getTag()).f3159l & 512) == 0)) {
                    widgets$CheckboxView.setChecked(!checked);
                }
            }
            return true;
        }
    }

    class View$OnClickListenerC0765j implements View.OnClickListener {
        View$OnClickListenerC0765j() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Search.this.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Search.this.mainActivity, ((Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b, 0));
            Page_Search.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    class View$OnClickListenerC0766k implements View.OnClickListener {
        View$OnClickListenerC0766k() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Page_Search s0Var = Page_Search.this;
            s0Var.tab.addPage(new Page_Article(s0Var.mainActivity, ((Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag()).f1226a, true));
        }
    }

    class View$OnClickListenerC0767l implements View.OnClickListener {
        View$OnClickListenerC0767l() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Search.this.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Search.this.mainActivity, ((Page_Article.C0316h) ((ViewGroup) view.getParent()).getTag()).f1231f, 0));
            Page_Search.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class C0768m {
        public int f2606a;
        public Page_Topic.PostModel f2607b;
        public Page_Article.C0316h f2608c;
        public Document f2609d;
        public int f2610e;

        C0768m(Page_Search s0Var) {
        }
    }

    public class View$OnClickListenerC0769n implements View.OnClickListener {
        Page_Search f2611a;

        public View$OnClickListenerC0769n(Page_Search s0Var, Page_Search s0Var2) {
            this.f2611a = s0Var2;
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(this.f2611a.mainActivity);
            f1Var.addPage(new Page_Site_List(this.f2611a.mainActivity, ((Integer) view.getTag()).intValue(), 0));
            this.f2611a.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class View$OnClickListenerC0770o implements View.OnClickListener, View.OnLongClickListener {
        Page_Search f2612a;

        class C0771a implements OptionPoupupMenuView.IClickListener {
            C0771a() {
//                View$OnClickListenerC0770o.this
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 1) {
                    Document uVar = Page_Search.this.f2583M.get(i2).f2609d;
                    Tab f1Var = new Tab(Page_Search.this.mainActivity);
                    f1Var.addPage(new Page_Topic(Page_Search.this.mainActivity, (uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0), 0));
                    Page_Search.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                } else if (i3 == 2) {
                    int intValue = Page_Search.this.f2583M.get(i2).f2609d.getInt(0);
                    MainActivity mainActivity = Page_Search.this.mainActivity;
                    Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showtopic=" + intValue, "Ссылка на тему скопирована в буфер");
                }
            }
        }

        public View$OnClickListenerC0770o(Page_Search s0Var) {
//            Page_Search.this
            this.f2612a = s0Var;
        }

        @Override
        public void onClick(View view) {
            Document uVar = this.f2612a.f2583M.get((Integer) view.getTag()).f2609d;
            Tab f1Var = new Tab(this.f2612a.mainActivity);
            f1Var.addPage(new Page_Topic(Page_Search.this.mainActivity, (uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0), 0));
            this.f2612a.mainActivity.mainLayout.setCurrentTab(f1Var);
        }

        @Override
        public boolean onLongClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Search.this.mainActivity, new C0771a(), true);
            o1Var.addMenuItem(6, (Integer) view.getTag(), 1, "Открыть в новой вкладке");
            o1Var.addMenuItem(6, (Integer) view.getTag(), 2, "Копировать ссылку");
            o1Var.show(null);
            return true;
        }
    }

    public class View$OnClickListenerC0772p implements View.OnClickListener {
        public View$OnClickListenerC0772p() {
//            Page_Search.this
        }

        @Override
        public void onClick(View view) {
            Document uVar = Page_Search.this.f2583M.get(((Integer) view.getTag()).intValue()).f2609d;
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Search.this.mainActivity, 1, uVar.getInt((uVar.getInt(3).intValue() & 16) > 0 ? 4 : 0).intValue());
            lVar.m824u(Page_Search.this.tab);
            lVar.m823v(uVar.getString(1));
            DocumentManager.getResultRequest(lVar);
        }
    }

    public Page_Search(MainActivity mainActivity, int i, int i2, int i3, int i4, String str, int i5, String str2) {
        this(mainActivity, i, new Document(i2), new Document(i3), new Document(i4), str, i5, str2);
    }

    public static SpannableString m321g0(String str) {
        int indexOf = str.indexOf(2);
        if (indexOf < 0) {
            return new SpannableString(str);
        }
        int indexOf2 = str.indexOf(3);
        StringBuilder sb = new StringBuilder();
        int i = indexOf - 29;
        sb.append(str.substring(0, i));
        sb.append(str.substring(indexOf + 1, indexOf2));
        sb.append(str.substring(indexOf2 + 22));
        SpannableString g0 = m321g0(sb.toString());
        int i2 = indexOf2 - 30;
        g0.setSpan(new ForegroundColorSpan(-65536), i, i2, 33);
        g0.setSpan(new BackgroundColorSpan(-256), i, i2, 33);
        return g0;
    }

    @Override
    protected boolean onPageLoaded() {
        Exception e;
        Document uVar;
        C0768m mVar;
        if (this.isLoading) {
            return false;
        }
        this.f2582L = this.currentDocument.getInt(0).intValue();
        this.f2583M = null;
        Document l = this.currentDocument.getDocument(1);
        if (l != null) {
            Vector vector = new Vector(l.count());
            int width = this.mainActivity.mainLayout.getWidth();
            for (int i = 0; i < l.count(); i++) {
                if (this.isLoading) {
                    return false;
                }
                try {
                    uVar = l.getDocument(i);
                } catch (Exception e2) {
                    e = e2;
                    uVar = null;
                }
                try {
                    int intValue = uVar.getInt(0).intValue();
                    uVar.remove(0);
                    if ((intValue & 1) > 0) {
                        mVar = m324d0(uVar, width);
                    } else if ((intValue & 2) > 0) {
                        mVar = m323e0(uVar, width);
                    } else {
                        mVar = (intValue & 4) > 0 ? m322f0(uVar, width) : null;
                    }
                    if (mVar != null) {
                        vector.add(mVar);
                    }
                } catch (Exception e3) {
                    e = e3;
                   /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Lost Search Item ");
                    sb.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                    errorReporter.handleSilentException(new Exception(sb.toString(), e));*/
                    e.printStackTrace();
                }
            }
            boolean T = m816T();
            int i2 = (T == true ? 1 : 0) + 1;
            int[] iArr = new int[vector.size() + i2 + (T? 1 :0)];
            this.f1071B = iArr;
            iArr[0] = m816T() ? Page.f1069D : 0;
            int[] iArr2 = this.f1071B;
            iArr2[T == true ? 1 : 0] = iArr2[0] + Page_Topic.PostModel.f3144w;
            int i3 = iArr2[T? 1:0];
            for (int i4 = 0; i4 < vector.size(); i4++) {
                i3 += ((C0768m) vector.get(i4)).f2610e;
                this.f1071B[i2 + i4] = i3;
            }
            if (m816T()) {
                int[] iArr3 = this.f1071B;
                iArr3[iArr3.length - 1] = iArr3[iArr3.length - 2] + Page.f1069D;
            }
            this.f2583M = vector;
            this.currentDocument = null;
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0757c());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "Копировать ссылку");
        o1Var.addMenuItem(0, 0, 23, "В закладки", DataDB.m366k(getLink()));
        if (this.f2584N.size() > 0) {
            o1Var.addMenuItem(0, 0, 24, String.format("Выбранные (%d)", Integer.valueOf(this.f2584N.size())), false, true);
        }
        o1Var.show(view);
    }

    @Override
    int mo141P() {
        int i = this.f2582L;
        int i2 = Prefs.f1179q;
        int i3 = (i / i2) + (i % i2 != 0 ? 1 : 0);
        return i3 == 0 ? i3 + 1 : i3;
    }

    @Override
    int mo140Q() {
        return (this.f2581K / Prefs.f1179q) + 1;
    }

    @Override
    Page mo139R(int i) {
        Page_Search s0Var = new Page_Search(this.mainActivity, this.searchFlags, this.f2576F, this.f2577G, this.f2578H, this.f2579I, (i - 1) * Prefs.f1179q, this.f2580J);
        s0Var.searchDialog = this.searchDialog;
        s0Var.f2584N = this.f2584N;
        return s0Var;
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
        if (!this.isLoading) {
            boolean T = m816T();
            int width = this.mainActivity.mainLayout.getWidth();
            int i2 = this.f1071B[T ? 1 : 0];
            for (int i3 = 0; i3 < this.f2583M.size(); i3++) {
                if (this.f2583M.get(i3).f2606a == 2) {
                    Page_Topic.PostModel yVar = this.f2583M.get(i3).f2607b;
                    int i4 = Page_Topic.PostModel.f3147z;
                    SpannableString spannableString = yVar.f3165r;
                    i2 += i4 + Util.m672b(spannableString.subSequence(0, spannableString.length()).toString(), width - Page_Topic.PostModel.f3143A, Page_Topic.PostModel.f3146y, false) + Page_Topic.PostModel.f3145x + Util.m663k(this.mainActivity, yVar.f3162o, width) + Page_Topic.PostModel.f3144w;
                } else {
                    i2 += this.f2583M.get(i3).f2610e;
                }
                this.f1071B[(T ? 1 : 0) + 1 + i3] = i2;
            }
            if (m816T()) {
                m815U();
            }
        }
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0756b(bBDisplay, pVar, cVar), true);
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            o1Var.addMenuItem(0, 0, 0, Util.C0427h.m640d(kVar.link), true, false);
            o1Var.addMenuItem(0, 0, 2, "Копировать ссылку");
            if (Urls2.is4pdaHost(kVar.link)) {
                o1Var.addMenuItem(0, 0, 21, "Открыть ссылку в новой вкладке");
            }
            o1Var.addMenuItem(0, 0, 26, "Открыть с помощью");
        }
        int i2 = cVar.f545c;
        if (i2 >= 0 && bBDisplay.f531h[i2] == null) {
            o1Var.addMenuItem(0, 0, 22, "Загрузить изображение");
        }
        Object obj = pVar.f2221a0;
        if (!(obj instanceof Page_Topic.PostModel) || ((Page_Topic.PostModel) obj).f3167t > 0) {
            o1Var.addMenuItem(0, 0, 1, "Открыть в новой вкладке");
        }
        if (cVar.f543a < 0) {
            Object obj2 = pVar.f2221a0;
            if (obj2 instanceof Page_Topic.PostModel) {
                Page_Topic.PostModel yVar = (Page_Topic.PostModel) obj2;
                int i3 = yVar.f3159l;
                if ((i3 & 512) > 0) {
                    if ((i3 & 2) > 0) {
                        o1Var.addMenuItem(0, 0, 4, "Показать", false, true);
                    } else {
                        o1Var.addMenuItem(0, 0, 3, "Скрыть", false, true);
                    }
                    if ((yVar.f3159l & 2048) != 0) {
                        o1Var.addMenuItem(0, 0, 8, "Защитить", false, true, true, true);
                    } else {
                        o1Var.addMenuItem(0, 0, 7, "Защитить", false, true, true, false);
                    }
                    o1Var.addMenuItem(0, 0, 9, "Переместить", false, true);
                    if ((yVar.f3159l & 4) > 0) {
                        o1Var.addMenuItem(0, 0, 6, "Восстановить", false, true);
                    }
                }
                if ((yVar.f3159l & 256) > 0) {
                    o1Var.addMenuItem(0, 0, 5, "Удалить");
                }
            }
        }
        o1Var.show(null);
    }

    C0768m m324d0(Document uVar, int i) {
        Document c = uVar.cloneDocument();
        c.removeRange(0, 3);
        Page_Topic.PostModel a = Page_Topic.PostModel.m99a(c);
        if (a == null) {
            return null;
        }
        a.f3167t = uVar.getInt(1).intValue();
        a.f3165r = m321g0(Util.C0427h.UnEscapeString(uVar.getString(2)));
        C0768m mVar = new C0768m(this);
        mVar.f2606a = 2;
        mVar.f2607b = a;
        uVar.removeRange(3, 10);
        mVar.f2609d = uVar;
        int i2 = Page_Topic.PostModel.f3147z;
        SpannableString spannableString = a.f3165r;
        mVar.f2610e = i2 + Util.m672b(spannableString.subSequence(0, spannableString.length()).toString(), i - Page_Topic.PostModel.f3143A, Page_Topic.PostModel.f3146y, false) + Page_Topic.PostModel.f3145x + Util.m663k(this.mainActivity, a.f3162o, i) + Page_Topic.PostModel.f3144w;
        return mVar;
    }

    C0768m m323e0(Document uVar, int i) {
        C0768m mVar = new C0768m(this);
        mVar.f2606a = 6;
        mVar.f2609d = uVar;
        SpannableString g0 = m321g0(Util.C0427h.UnEscapeString(uVar.getString(1)));
        String charSequence = g0.subSequence(0, g0.length()).toString();
        uVar.appendExtra(g0);
        uVar.addString(1, charSequence);
        uVar.addString(7, Util.C0427h.UnEscapeString(uVar.getString(7)));
        mVar.f2610e = Page_Forum.f1509d0 + Util.m672b(charSequence, i - Page_Forum.f1508c0, Page_Forum.f1510e0, false) + Util.m672b("8", i, Page_Forum.f1511f0, false);
        return mVar;
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            int i3 = 2;
            if (i2 == 1) {
                MainActivity mainActivity = this.mainActivity;
                String str = kVar.link;
                if (!this.f2588R) {
                    i3 = 1;
                }
                Page b = Urls2.openPage(mainActivity, str, false, i3);
                if (b != null) {
                    if (this.f2588R) {
                        Tab f1Var = new Tab(this.mainActivity);
                        f1Var.addPage(b);
                        this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    } else {
                        this.tab.addPage(b);
                    }
                }
                this.f2588R = false;
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        } else {
            Object obj = pVar.f2221a0;
            if (obj instanceof Page_Topic.PostModel) {
                API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.mainActivity, 3, ((Page_Topic.PostModel) obj).postId);
                lVar.m824u(this.tab);
                DocumentManager.getResultRequest(lVar);
            }
        }
    }

    C0768m m322f0(Document uVar, int i) {
        Page_Article.C0316h a = Page_Article.C0316h.m755a(uVar, 0);
        if (a == null) {
            return null;
        }
        C0768m mVar = new C0768m(this);
        mVar.f2606a = 5;
        mVar.f2608c = a;
        MainActivity mainActivity = this.mainActivity;
        SpannableString spannableString = a.f1230e;
        mVar.f2610e = ArticleLayout.m980a(mainActivity, spannableString.subSequence(0, spannableString.length()).toString(), a.f1237l, false, i);
        return mVar;
    }

    @Override
    public int getCount() {
        List<C0768m> list;
        int i = 0;
        if (!isUnsucces() || (list = this.f2583M) == null || list.size() == 0) {
            return 0;
        }
        int size = this.f2583M.size() + 1;
        if (m816T()) {
            i = 2;
        }
        return size + i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        try {
            int count = getCount();
            int i2 = 0;
            if (m816T()) {
                if (i == 0) {
                    return 0;
                }
                if (count - 1 == i) {
                    return 7;
                }
                i2 = 1;
            }
            if (i != i2 && i < count) {
                C0768m mVar = this.f2583M.get((i - i2) - 1);
                int i3 = mVar.f2606a;
                if (i3 == 2) {
                    int i4 = mVar.f2607b.f3159l;
                    if ((i4 & 2) != 0) {
                        return 3;
                    }
                    if ((i4 & 4) != 0) {
                        return 4;
                    }
                }
                return i3;
            }
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(new Exception("SearchPage.GetItemViewType", e));
        }
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View articleLayout = null;
        Page_Topic.PostModel yVar;
        Widgets$AvatarView avatarView;
        boolean isEmpty;
        int i2;
        boolean z = false;
        int i3;
        int i4;
        TextView textView;
        int itemViewType = getItemViewType(i);
        int i5 = m816T() ? 2 : 1;
        int i6 = 0;
        if (view == null) {
            if (itemViewType == 0) {
                articleLayout = m817S(viewGroup, false);
            } else if (itemViewType == 7) {
                articleLayout = m817S(viewGroup, true);
            } else if (itemViewType == 1) {
                View view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
                articleLayout = view2;
            } else if (itemViewType == 2 || itemViewType == 3 || itemViewType == 4) {
                View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.post, viewGroup, false);
                if (itemViewType == 3) {
                    inflate.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_hidden));
                } else if (itemViewType == 4) {
                    inflate.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_deleted));
                } else {
                    inflate.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_normal));
                }
                inflate.setOnLongClickListener(new View$OnLongClickListenerC0759d());
                BBDisplay bBDisplay = (BBDisplay) inflate.findViewById(R.id.PostCode);
                bBDisplay.setCallback(this);
                bBDisplay.setOverlay((BBOverlay) inflate.findViewById(R.id.PostCodeOverlay));
                ((TextView) inflate.findViewById(R.id.postTitle)).setOnClickListener(new View$OnClickListenerC0760e());
                ((TextView) inflate.findViewById(R.id.authorName)).setOnLongClickListener(new View$OnLongClickListenerC0761f());
                ((TextView) inflate.findViewById(R.id.postAuthorRep)).setOnClickListener(new View$OnClickListenerC0762g());
                Widgets$CheckboxView widgets$CheckboxView = (Widgets$CheckboxView) inflate.findViewById(R.id.postCheckbox);
                widgets$CheckboxView.setOnClickListener(new View$OnClickListenerC0763h());
                widgets$CheckboxView.setOnLongClickListener(new View$OnLongClickListenerC0764i());
                inflate.findViewById(R.id.authorImage).setOnClickListener(new View$OnClickListenerC0765j());
                articleLayout = inflate;
            } else if (itemViewType == 5) {
                ArticleLayout articleLayout2 = (ArticleLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.sitepost, viewGroup, false);
                articleLayout2.f515n = false;
                articleLayout2.f505d.setOnClickListener(new View$OnClickListenerC0766k());
                articleLayout2.f504c.setOnClickListener(new View$OnClickListenerC0767l());
                View$OnClickListenerC0755a aVar = new View$OnClickListenerC0755a();
                articleLayout2.f502a.setOnClickListener(aVar);
                articleLayout2.f506e.setOnClickListener(aVar);
                articleLayout2.f507f.setCallback(this);
                articleLayout = articleLayout2;
            } else if (itemViewType == 6) {
                articleLayout = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_top, viewGroup, false);
            }
            if (itemViewType != 2 || itemViewType == 3 || itemViewType == 4) {
                yVar = this.f2583M.get(i - i5).f2607b;
                articleLayout.setTag(yVar);
                ((TextView) articleLayout.findViewById(R.id.postTitle)).setText(!TextUtils.isEmpty(yVar.f3165r) ? "<Нет доступа>" : yVar.f3165r);
                TextView textView2 = (TextView) articleLayout.findViewById(R.id.authorName);
                textView2.setText(yVar.f3150c);
                textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f((System.currentTimeMillis() / 1000) - ((long) yVar.f3155h) >= 900 ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
                TextView textView3 = (TextView) articleLayout.findViewById(R.id.authorGroup);
                textView3.setText(API.userGroups.get(yVar.f3151d));
                textView3.setTextColor(yVar.f3152e);
                avatarView = (Widgets$AvatarView) articleLayout.findViewById(R.id.authorImage);
                isEmpty = TextUtils.isEmpty(yVar.f3154g);
                int i7 = R.drawable.ic_avatar;
                if (!isEmpty || (i4 = yVar.f3149b) == 0 || i4 == 17927) {
                    PicoImg.cancel(avatarView);
                    Skin e1Var = this.mainActivity.skin;
                    i3 = yVar.f3149b;
                    if (i3 != 0 || i3 == 17927) {
                        i7 = R.drawable.ic_launcher;
                    }
                    avatarView.setImageDrawable(e1Var.m736f(i7));
                } else {
                    PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, yVar.f3154g);
                    l.to(avatarView);
                    l.disableAnimation(!Prefs.f1146G);
                    l.placeholder(this.mainActivity.skin.m736f(R.drawable.ic_avatar));
                    l.fade(4, 200, false);
                    l.sizeToView();
                    l.runAsync();
                }
                TextView textView4 = (TextView) articleLayout.findViewById(R.id.postDate);
                textView4.setText(yVar.f3157j);
                i2 = yVar.f3159l;
                if ((i2 & 512) != 0) {
                    textView4.setCompoundDrawablesWithIntrinsicBounds((i2 & 2048) != 0 ? this.mainActivity.skin.m736f(R.drawable.ic_protected) : null, (Drawable) null, (Drawable) null, (Drawable) null);
                    textView4.setCompoundDrawablePadding((int) (this.mainActivity.f731b * 4.0f));
                }
                TextView textView5 = (TextView) articleLayout.findViewById(R.id.postKarma);
                if (yVar.f3160m != null) {
                    textView5.setVisibility(8);
                } else {
                    textView5.setVisibility(0);
                    textView5.setText(yVar.f3160m);
                }
                TextView textView6 = (TextView) articleLayout.findViewById(R.id.postAuthorRep);
                textView6.setText(yVar.f3153f);
                Widgets$CheckboxView widgets$CheckboxView2 = (Widgets$CheckboxView) articleLayout.findViewById(R.id.postCheckbox);
                if ((yVar.f3159l & 512) <= 0) {
                    widgets$CheckboxView2.getLayoutParams().width = (int) (this.mainActivity.f731b * 36.0f);
                    ((RelativeLayout.LayoutParams) textView6.getLayoutParams()).rightMargin = (int) (this.mainActivity.f731b * 44.0f);
                    z = false;
                } else {
                    z = false;
                    widgets$CheckboxView2.getLayoutParams().width = 0;
                    ((RelativeLayout.LayoutParams) textView6.getLayoutParams()).rightMargin = (int) (this.mainActivity.f731b * 8.0f);
                }
                if (this.f2584N.indexOfKey(yVar.postId) >= 0) {
                    z = true;
                }
                widgets$CheckboxView2.setChecked(z);
                ((BBDisplay) articleLayout.findViewById(R.id.PostCode)).setBBString(yVar.f3162o);
            } else if (itemViewType == 5) {
                Page_Article.C0316h hVar = this.f2583M.get(i - i5).f2608c;
                ArticleLayout articleLayout3 = (ArticleLayout) articleLayout;
                articleLayout3.setTag(hVar);
                PicoImgRequest l2 = PicoImg.loadUrl(this.mainActivity, hVar.f1233h);
                l2.to(articleLayout3.f502a);
                l2.runAsync();
                articleLayout3.f503b.setText(hVar.f1227b);
                articleLayout3.f504c.setText(hVar.f1232g);
                articleLayout3.f505d.setText(Integer.valueOf(hVar.f1234i).toString());
                articleLayout3.f506e.setText(hVar.f1230e);
                articleLayout3.f507f.setBBString(hVar.f1237l);
                if (hVar.f1235j != null) {
                    int i8 = 0;
                    for (int i9 = 0; i9 < hVar.f1235j.size(); i9++) {
                        int keyAt = hVar.f1235j.keyAt(i9);
                        String valueAt = hVar.f1235j.valueAt(i9);
                        if (!valueAt.startsWith("!!")) {
                            if (i8 >= articleLayout3.f509h.getChildCount()) {
                                textView = new TextView(this.mainActivity);
                                textView.setSingleLine(true);
                                textView.setTextAppearance(this.mainActivity, 16973894);
                                textView.setTextColor(Skin.C0353a.f1386h0);
                                textView.setGravity(16);
                                articleLayout3.f509h.addView(textView);
                                ((LinearLayout.LayoutParams) textView.getLayoutParams()).height = -1;
                                textView.setOnClickListener(this.f2586P);
                            } else {
                                textView = (TextView) articleLayout3.f509h.getChildAt(i8);
                                textView.setVisibility(0);
                            }
                            i8++;
                            StringBuilder sb = new StringBuilder();
                            sb.append(1 == i8 ? "# " : ", ");
                            sb.append(valueAt);
                            textView.setText(sb.toString());
                            textView.setTag(Integer.valueOf(keyAt));
                        }
                    }
                    i6 = i8;
                }
                while (i6 < articleLayout3.f509h.getChildCount()) {
                    articleLayout3.f509h.getChildAt(i6).setVisibility(8);
                    i6++;
                }
            } else if (itemViewType == 6) {
                ViewGroup viewGroup2 = (ViewGroup) articleLayout;
                TextView textView7 = (TextView) viewGroup2.findViewById(R.id.topicName);
                TextView textView8 = (TextView) viewGroup2.findViewById(R.id.topicLastDate);
                TextView textView9 = (TextView) viewGroup2.findViewById(R.id.topicLastUser);
                View findViewById = viewGroup2.findViewById(R.id.topicGoUnread);
                int i10 = i - i5;
                Document uVar = this.f2583M.get(i10).f2609d;
                if (uVar.count() > 8) {
                    textView7.setText((SpannableString) uVar.getObject(8));
                } else {
                    textView7.setText(uVar.getString(1));
                }
                textView8.setText(Util.formatDate(uVar.getInt(5).intValue()));
                textView9.setText(uVar.getString(7));
                viewGroup2.setTag(Integer.valueOf(i10));
                viewGroup2.setOnClickListener(this.f2587Q);
                viewGroup2.setOnLongClickListener(this.f2587Q);
                findViewById.setTag(Integer.valueOf(i10));
                findViewById.setVisibility((uVar.getInt(3).intValue() & 32) == 0 ? 8 : 0);
                findViewById.setOnClickListener(this.f2585O);
                textView7.setEnabled((uVar.getInt(3).intValue() & 4) == 0);
                if (getItemViewType(i + 1) == 6) {
                    if ((uVar.getInt(3).intValue() & 2) != 0) {
                        viewGroup2.setBackgroundColor(Skin.C0353a.f1366V);
                    } else {
                        viewGroup2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.border_bottom));
                    }
                    View findViewById2 = viewGroup2.findViewById(R.id.postSepBottom);
                    if (findViewById2 != null) {
                        viewGroup2.removeView(findViewById2);
                    }
                } else {
                    if ((uVar.getInt(3).intValue() & 2) != 0) {
                        viewGroup2.setBackgroundColor(Skin.C0353a.f1366V);
                    } else {
                        viewGroup2.setBackgroundResource(0);
                    }
                    View view3 = new View(this.mainActivity);
                    view3.setId(R.id.postSepBottom);
                    view3.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f));
                    layoutParams.addRule(3, R.id.topicLastDate);
                    view3.setLayoutParams(layoutParams);
                    viewGroup2.addView(view3);
                }
            }
            if (itemViewType == 7) {
                m815U();
            }
            return articleLayout;
        }
        articleLayout = view;
        if (itemViewType != 2) {
        }
        yVar = this.f2583M.get(i - i5).f2607b;
        articleLayout.setTag(yVar);
        ((TextView) articleLayout.findViewById(R.id.postTitle)).setText(!TextUtils.isEmpty(yVar.f3165r) ? "<Нет доступа>" : yVar.f3165r);
        TextView textView22 = (TextView) articleLayout.findViewById(R.id.authorName);
        textView22.setText(yVar.f3150c);
        textView22.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f((System.currentTimeMillis() / 1000) - ((long) yVar.f3155h) >= 900 ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView32 = (TextView) articleLayout.findViewById(R.id.authorGroup);
        textView32.setText(API.userGroups.get(yVar.f3151d));
        textView32.setTextColor(yVar.f3152e);
        avatarView = (Widgets$AvatarView) articleLayout.findViewById(R.id.authorImage);
        isEmpty = TextUtils.isEmpty(yVar.f3154g);
        int i72 = R.drawable.ic_avatar;
        if (!isEmpty) {
        }
        PicoImg.cancel(avatarView);
        Skin e1Var2 = this.mainActivity.skin;
        i3 = yVar.f3149b;
        if (i3 != 0) {
        }
        i72 = R.drawable.ic_launcher;
        avatarView.setImageDrawable(e1Var2.m736f(i72));
        TextView textView42 = (TextView) articleLayout.findViewById(R.id.postDate);
        textView42.setText(yVar.f3157j);
        i2 = yVar.f3159l;
        if ((i2 & 512) != 0) {
        }
        TextView textView52 = (TextView) articleLayout.findViewById(R.id.postKarma);
        if (yVar.f3160m != null) {
        }
        TextView textView62 = (TextView) articleLayout.findViewById(R.id.postAuthorRep);
        textView62.setText(yVar.f3153f);
        Widgets$CheckboxView widgets$CheckboxView22 = (Widgets$CheckboxView) articleLayout.findViewById(R.id.postCheckbox);
        if ((yVar.f3159l & 512) <= 0) {
        }
        if (this.f2584N.indexOfKey(yVar.postId) >= 0) {
        }
        widgets$CheckboxView22.setChecked(z);
        ((BBDisplay) articleLayout.findViewById(R.id.PostCode)).setBBString(yVar.f3162o);
        if (itemViewType == 7) {
        }
        return articleLayout;
    }

    @Override
    public int getViewTypeCount() {
        return 8;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        List<C0768m> list = this.f2583M;
        if (list != null) {
            list.clear();
            this.f2583M = null;
        }
    }

    @Override
    public String getLink() {
        if (this.f2589S == null) {
            this.f2589S = "forum/index.php?act=search";
            if (!TextUtils.isEmpty(this.f2579I)) {
                try {
                    this.f2589S += "&query=" + URLEncoder.encode(this.f2579I, "windows-1251");
                } catch (UnsupportedEncodingException unused) {
                    this.f2589S += "&query=" + this.f2579I.replace(' ', '+');
                }
            }
            int i = this.searchFlags;
            if (3 == (i & 3)) {
                this.f2589S += "&source=all";
            } else if ((i & 2) != 0) {
                this.f2589S += "&source=top";
            } else if ((i & 1) != 0) {
                this.f2589S += "&source=pst";
            }
            if ((this.searchFlags & 4) != 0) {
                this.f2589S += "&site=1";
            }
            if ((this.searchFlags & 65536) != 0) {
                this.f2589S += "&result=topics";
            }
            if ((this.searchFlags & 524288) != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f2589S);
                sb.append((this.searchFlags & 1048576) != 0 ? "&sort=da" : "&sort=dd");
                this.f2589S = sb.toString();
            }
            if ((this.searchFlags & 131072) != 0) {
                this.f2589S += "&subforums=1";
            }
            if ((this.searchFlags & 262144) != 0) {
                this.f2589S += "&nohl=1";
            }
            Document uVar = this.f2578H;
            if (uVar != null && uVar.count() > 0) {
                if (this.f2578H.getInt(0) != null && this.f2578H.getInt(0).intValue() != 0) {
                    this.f2589S += "&username-id=" + this.f2578H.getInt(0);
                } else if (!TextUtils.isEmpty(this.f2578H.getString(0))) {
                    this.f2589S += "&username=" + this.f2578H.getString(0);
                }
            }
            Document uVar2 = this.f2576F;
            if (!(uVar2 == null || uVar2.count() <= 0 || this.f2576F.getInt(0).intValue() == 0)) {
                this.f2589S += "&forums=" + this.f2576F.getInt(0);
                for (int i2 = 1; i2 < this.f2576F.count(); i2++) {
                    this.f2589S += "," + this.f2576F.getInt(i2);
                }
            }
            Document uVar3 = this.f2577G;
            if (!(uVar3 == null || uVar3.count() <= 0 || this.f2577G.getInt(0).intValue() == 0)) {
                this.f2589S += "&topics=" + this.f2577G.getInt(0);
                for (int i3 = 1; i3 < this.f2577G.count(); i3++) {
                    this.f2589S += "," + this.f2577G.getInt(i3);
                }
            }
        }
        return this.f2589S;
    }

    public Page_Search(MainActivity mainActivity, int i, Document uVar, Document uVar2, Document uVar3, String str, int i2, String str2) {
        super(mainActivity, 25458, new Document(i2));
        Object[] objArr = new Object[7];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = uVar != null ? uVar : new Document(0);
        objArr[2] = uVar2 != null ? uVar2 : new Document(0);
        objArr[3] = uVar3 != null ? uVar3 : new Document(0);
        objArr[4] = str != null ? str : "";
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = Integer.valueOf(Prefs.f1179q);
        this.f2580J = null;
        this.iconId = R.drawable.ic_nav_forum;
        this.f2581K = i2;
        this.f2580J = str2;
        if (!TextUtils.isEmpty(str2)) {
            this.title = "Поиск: " + str2;
        } else if (!TextUtils.isEmpty(str)) {
            this.title = "Поиск: " + str;
        } else if (uVar3 == null || TextUtils.isEmpty(uVar3.getString(0))) {
            this.title = "Поиск";
        } else {
            this.title = "Поиск по постам " + uVar3.getString(0);
        }
        this.statusMessage = "поиск...";
        this.searchFlags = i;
        this.f2576F = uVar;
        this.f2577G = uVar2;
        this.f2578H = uVar3;
        this.f2579I = str;
        this.f2586P = new View$OnClickListenerC0769n(this, this);
        this.f2587Q = new View$OnClickListenerC0770o(this);
        this.f2584N = new SparseArray<>();
        this.f2585O = new View$OnClickListenerC0772p();
    }
}
