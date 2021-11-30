package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public abstract class Page extends DocumentManager.IGenerateRequest implements ListAdapter {
    static int f1069D;
    boolean f1070A;
    int[] f1071B;
    private final DataSetObservable dataSetObservable;
    protected MainActivity mainActivity;
    protected Tab tab;
    boolean isLoading;
    Document rootDocument;
    Document currentDocument;
    boolean f1078l;
    boolean f1079m;
    protected int iconId;
    private boolean f1081o;
    private boolean f1082p;
    private boolean f1083q;
    private boolean isPageLoaded;
    private View rootView;
    String f1086t;
    private String errorMessage;
    String title;
    int f1089w;
    int f1090x;
    SearchDialog searchDialog;
    String f1092z;

    public class View$OnClickListenerC0289a implements View.OnClickListener {
        View$OnClickListenerC0289a() {
          //  Page.this = r1;
        }

        @Override
        public void onClick(View view) {
            Page.this.m818O(1);
        }
    }

    public class View$OnClickListenerC0290b implements View.OnClickListener {
        final int f1094a;

        View$OnClickListenerC0290b(int i) {
            //Page.this = r1;
            this.f1094a = i;
        }

        @Override
        public void onClick(View view) {
            Page.this.m818O(this.f1094a - 1);
        }
    }

    public class View$OnClickListenerC0291c implements View.OnClickListener {
        final int f1096a;

        class C0292a implements TextWatcher {
            final DlgSimple f1098a;

            C0292a(DlgSimple q1Var) {
              ///  View$OnClickListenerC0291c.this = r1;
                this.f1098a = q1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int parseInt = Integer.parseInt(this.f1098a.editText.getText().toString().trim());
                    DlgSimple q1Var = this.f1098a;
                    boolean z = true;
                    if (parseInt < 1 || parseInt > View$OnClickListenerC0291c.this.f1096a) {
                        z = false;
                    }
                    q1Var.m625a(z);
                } catch (NumberFormatException unused) {
                    this.f1098a.m625a(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0293b implements View.OnClickListener {
            final DlgSimple f1100a;

            View$OnClickListenerC0293b(DlgSimple q1Var) {
                //View$OnClickListenerC0291c.this = r1;
                this.f1100a = q1Var;
            }

            @Override
            public void onClick(View view) {
                try {
                    int parseInt = Integer.parseInt(this.f1100a.editText.getText().toString().trim());
                    if (parseInt >= 1) {
                        View$OnClickListenerC0291c cVar = View$OnClickListenerC0291c.this;
                        if (parseInt <= cVar.f1096a) {
                            Page.this.m818O(parseInt);
                            return;
                        }
                    }
                } catch (NumberFormatException unused) {
                }
                Toast.makeText(Page.this.mainActivity, "Неправильный номер страницы", 1).show();
            }
        }

        View$OnClickListenerC0291c(int i) {
           // Page.this = r1;
            this.f1096a = i;
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page.this.mainActivity;
            DlgSimple q1Var = new DlgSimple(mainActivity, "Введите номер страницы (1-" + this.f1096a + ")", true, null, null);
            q1Var.m625a(false);
            q1Var.editText.addTextChangedListener(new C0292a(q1Var));
            q1Var.m620f(new View$OnClickListenerC0293b(q1Var), true);
            q1Var.show(true, true, true);
            Page.this.mainActivity.mainLayout.m859w(q1Var.editText);
        }
    }

    public class View$OnClickListenerC0294d implements View.OnClickListener {
        final int f1102a;

        View$OnClickListenerC0294d(int i) {
          //  Page.this = r1;
            this.f1102a = i;
        }

        @Override
        public void onClick(View view) {
            Page.this.m818O(this.f1102a + 1);
        }
    }

    public class View$OnClickListenerC0295e implements View.OnClickListener {
        final int f1104a;

        View$OnClickListenerC0295e(int i) {
        //    Page.this = r1;
            this.f1104a = i;
        }

        @Override
        public void onClick(View view) {
            Page.this.m818O(this.f1104a);
        }
    }

    public Page(MainActivity mainActivity, int cmd, Document document) {
        super(cmd);
        this.isLoading = false;
        this.iconId = R.drawable.ic_nav_home;
        this.f1081o = true;
        this.f1092z = null;
        this.f1070A = false;
        this.dataSetObservable = new DataSetObservable();
        this.mainActivity = mainActivity;
        this.rootDocument = document;
        this.title = toString();
        if (cmd != 0) {
            this.statusMessage = "Загрузка " + this.title;
        }
    }

    public void m818O(int i) {
        Page R = mo139R(i);
        if (R != null) {
            R.f1078l = true;
            this.tab.addPage(R);
        }
    }

    public void mo147A(Bundle bundle, String str) {
        this.f1090x = bundle.getInt(str + "_posi");
        this.f1089w = bundle.getInt(str + "_poso");
    }

    public boolean mo145B() {
        return false;
    }

    public void mo763C() {
        if (isUnsucces() && mo140Q() < mo141P()) {
            m818O(mo140Q() + 1);
        }
    }

    public void mo762D() {
        if (isUnsucces() && mo140Q() > 1) {
            m818O(mo140Q() - 1);
        }
    }

    public void mo192E() {
        if (isUnsucces()) {
            tabReload();
        }
    }

    public void onSearchBar() {
        if (!this.isLoading && this.tab != null) {
            if (isUnsucces() && this.tab.forumsListView.getChildCount() > 0) {
                m814V();
            }
            this.tab.forumsListView.setOverscrollFooter(null);
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f800H = false;
            mainLayout.f799G = false;
            mainLayout.f801I = false;
        }
    }

    protected boolean onPageLoadedForum() {
        return true;
    }

    protected boolean onPageLoaded() {
        return true;
    }

    void sendErrorStatusCode(int statusCode) {
        if (!this.isLoading) {
            if (statusCode == -2) {
                this.errorMessage = "ошибка загрузки";
                Toast.makeText(this.mainActivity, "Ошибка загрузки страницы", 1).show();
            } else if (statusCode == -1) {
                this.errorMessage = "нет соединения";
                if (DocumentManager.getStartTime() > 30000) {
                    Toast.makeText(this.mainActivity, "Ошибка связи, убедитесь, что вы подключены к интернету", 1).show();
                }
            } else if (statusCode != 3) {
                this.errorMessage = "ошибка " + statusCode;
                MainActivity mainActivity = this.mainActivity;
                Toast.makeText(mainActivity, this.errorMessage + ", попробуйте еще раз позже", 1).show();
            } else {
                this.errorMessage = "не найдено";
                Toast.makeText(this.mainActivity, "Страница не существует или нет доступа", 1).show();
            }
            changeTitleTabError();
        }
    }

    @SuppressLint({"NewApi"})
    public void mo142J(boolean z) {
        if (isCurrentTab()) {
            if (this.f1090x > 0 || this.f1089w != 0) {
                Tab.ForumsListView gVar = this.tab.forumsListView;
                gVar.setSelectionFromTop(gVar.getHeaderViewsCount() + this.f1090x, this.f1089w);
            }
            if (this.tab.m717i()) {
                if (m816T()) {
                    boolean z2 = false;
                    this.tab.mainLayout.f800H = mo140Q() < mo141P();
                    MainLayout mainLayout = this.tab.mainLayout;
                    if (mo140Q() > 1) {
                        z2 = true;
                    }
                    mainLayout.f799G = z2;
                }
                this.tab.mainLayout.f801I = isUnsuccesCode();
            }
            this.tab.forumsListView.setOverscrollFooter(Skin.C0353a.f1388i0);
        }
        changeTitleTabError();
    }

    public void setOptionMenuToolBar(View view) {
    }

    public void showSearchDialog(View view) {
        if (!(this instanceof Page_QMS_List) && !(this instanceof Page_QMS_Talk) && !(this instanceof Page_Favorites) && !(this instanceof Page_Login)) {
            if (this.searchDialog == null) {
                this.searchDialog = new SearchDialog(this.mainActivity, this);
            }
            this.searchDialog.m749g();
        }
    }

    @SuppressLint({"NewApi"})
    public void mo54M(Tab f1Var, boolean z) {
        this.tab = f1Var;
        boolean z2 = false;
        if (this.f1079m) {
            Tab.f1441v = hashCode();
            this.f1079m = false;
        }
        if (!z) {
            this.tab.forumsListView.setTranscriptMode(0);
            this.tab.forumsListView.setStackFromBottom(false);
            changeTitleTabError();
            if ((this.f1090x > 0 || this.f1089w != 0) && isUnsucces()) {
                Tab.ForumsListView gVar = this.tab.forumsListView;
                gVar.setSelectionFromTop(gVar.getHeaderViewsCount() + this.f1090x, this.f1089w);
            }
            if (isUnsucces()) {
                this.tab.forumsListView.setOverscrollFooter(Skin.C0353a.f1388i0);
            }
            boolean z3 = isUnsucces() && m816T();
            this.tab.mainLayout.f800H = z3 && mo140Q() < mo141P();
            this.tab.mainLayout.f799G = z3 && mo140Q() > 1;
            MainLayout mainLayout = this.tab.mainLayout;
            if (isUnsucces() && isUnsuccesCode()) {
                z2 = true;
            }
            mainLayout.f801I = z2;
        }
    }

    public void m819N(int i, boolean z, String str) {
        int i2 = Prefs.f1187y;
        if (i2 == 0) {
            if (z) {
                Tab f1Var = new Tab(this.mainActivity);
                f1Var.addPage(new Page_Topic(this.mainActivity, i, 0, Prefs.showAllPost ? 3 : 0, 0, null, str));
                this.tab.mainLayout.setCurrentTab(f1Var);
                return;
            }
            this.tab.addPage(new Page_Topic(this.mainActivity, i, 0, Prefs.showAllPost ? 3 : 0, 0, null, str));
        } else if (i2 == 1) {
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.mainActivity, 1, i);
            lVar.m824u(this.tab);
            lVar.m826s(z);
            lVar.m823v(str);
            DocumentManager.getResultRequest(lVar);
        }
    }

    int mo141P() {
        return 1;
    }

    int mo140Q() {
        return 1;
    }

    Page mo139R(int i) {
        return null;
    }

    public View m817S(ViewGroup viewGroup, boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.listpages, viewGroup, false);
        int Q = mo140Q();
        int P = mo141P();
        if (z) {
            this.rootView = linearLayout;
        }
        TextView textView = (TextView) linearLayout.findViewById(R.id.pageFirst);
        if (Q == 1) {
            textView.setText("");
            textView.setBackgroundResource(0);
        } else {
            textView.setOnClickListener(new View$OnClickListenerC0289a());
        }
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.pagePrev);
        if (Q == 1) {
            textView2.setText("");
            textView2.setBackgroundResource(0);
        } else {
            textView2.setOnClickListener(new View$OnClickListenerC0290b(Q));
        }
        TextView textView3 = (TextView) linearLayout.findViewById(R.id.pageNumber);
        textView3.setText(String.format("%d/%d", Integer.valueOf(Q), Integer.valueOf(P)));
        if (P <= 1) {
            textView3.setEnabled(false);
        } else {
            textView3.setOnClickListener(new View$OnClickListenerC0291c(P));
        }
        TextView textView4 = (TextView) linearLayout.findViewById(R.id.pageNext);
        if (Q >= P) {
            textView4.setText("");
            textView4.setBackgroundResource(0);
        } else {
            textView4.setOnClickListener(new View$OnClickListenerC0294d(Q));
        }
        TextView textView5 = (TextView) linearLayout.findViewById(R.id.pageLast);
        if (Q >= P) {
            textView5.setText("");
            textView5.setBackgroundResource(0);
        } else {
            textView5.setOnClickListener(new View$OnClickListenerC0295e(P));
        }
        return linearLayout;
    }

    public boolean m816T() {
        return mo141P() > 1;
    }

    public void m815U() {
        int[] iArr;
        if (isCurrentTab() && this.rootView != null) {
            int i = (this.tab.forumsListView.getFirstVisiblePosition() <= 0 || ((iArr = this.f1071B) != null && iArr[iArr.length + -1] <= this.tab.forumsListView.getHeight())) ? 8 : 0;
            this.rootView.findViewById(R.id.pageFirst).setVisibility(i);
            this.rootView.findViewById(R.id.pagePrev).setVisibility(i);
            this.rootView.findViewById(R.id.pageNumber).setVisibility(i);
            this.rootView.findViewById(R.id.pageNext).setVisibility(i);
            this.rootView.findViewById(R.id.pageLast).setVisibility(i);
        }
    }

    public void m814V() {
        if (!this.isLoading && isCurrentTab() && isUnsucces() && this.tab.forumsListView.getChildCount() > 0) {
            int headerViewsCount = this.tab.forumsListView.getHeaderViewsCount();
            int firstVisiblePosition = this.tab.forumsListView.getFirstVisiblePosition();
            this.f1090x = firstVisiblePosition;
            if (firstVisiblePosition >= headerViewsCount) {
                this.f1090x = firstVisiblePosition - headerViewsCount;
                this.f1089w = this.tab.forumsListView.getChildAt(0).getTop();
                return;
            }
            View childAt = this.tab.forumsListView.getChildAt(headerViewsCount - firstVisiblePosition);
            this.f1090x = 0;
            this.f1089w = childAt != null ? childAt.getTop() : this.tab.mainLayout.getActionBarHeight();
        }
    }

    public void m813W(int i) {
        if (isCurrentTab()) {
            this.tab.mainLayout.m868n(true);
        }
        m812X(i, this.mainActivity.mainLayout.getActionBarHeight());
    }

    @SuppressLint({"NewApi"})
    public void m812X(int i, int i2) {
        this.f1090x = i;
        this.f1089w = i2;
        if (isCurrentTab() && isUnsucces()) {
            Tab.ForumsListView gVar = this.tab.forumsListView;
            gVar.setSelectionFromTop(gVar.getHeaderViewsCount() + this.f1090x, this.f1089w);
        }
    }

    public void mo138Y(Bundle bundle, String str) {
        bundle.putInt(str + "_posi", this.f1090x);
        bundle.putInt(str + "_poso", this.f1089w);
    }

    public void tabLoaded(boolean z) {
        if (this.f1083q && !z && isCurrentTab() && this.tab.forumsListView.getChildCount() > 0) {
            m814V();
        }
        boolean z2 = this.f1083q;
        this.f1083q = z;
        if (z) {
            mo136a0();
        }
        if (z2 && !z) {
            destroyData();
        }
        this.dataSetObservable.notifyChanged();
    }

    public boolean mo136a0() {
        if (this.f1082p) {
            return false;
        }
        this.f1082p = true;
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    public boolean mo134b0() {
        boolean z = this.f1082p;
        this.f1082p = false;
        return z;
    }

    public void m810c0(String str, boolean z) {
        this.f1092z = str;
        this.f1070A = z;
        if (isCurrentTab()) {
            this.tab.mainLayout.showBageRunningStatus();
        }
    }

    @Override
    public abstract int getCount();

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void prepareResult(int status, Document document) {
        if (!this.isLoading) {
            if (!this.isPageLoaded && status == 0) {
                if (this.currentDocument != document) {
                    this.currentDocument = document;
                }
                try {
                    this.isPageLoaded = !onPageLoadedForum();
                } catch (Exception e) {
                    this.isPageLoaded = true;
                    e.printStackTrace();
                    //ACRA.getErrorReporter().handleSilentException(new Exception("Page.RecieveBroadcast - load", e));
                }
                if (!this.isPageLoaded) {
                    this.errorMessage = null;
                    tabLoaded(true);
                    mo142J(this.f1081o);
                    this.f1081o = false;
                }
            }
            if (this.isPageLoaded || status != 0) {
                destroyData();
                if (this.isPageLoaded) {
                    status = -2;
                }
                sendErrorStatusCode(status);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return isUnsucces() && getCount() == 0;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void getResult(int status, Document document) {
        Log.d("PAGE", "getResultCalled");
        if (!this.isLoading && status == 0) {
            this.currentDocument = document;
            try {
                this.isPageLoaded = !onPageLoaded();
            } catch (Exception e) {
                this.isPageLoaded = true;
                e.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(new Exception("Page.RecieveReply - AsyncLoad", e));
            }
        }
    }

    @Override
    public Document generate() {
        return this.rootDocument;
    }

    public void changeTitleTabError() {
        String str;
        if (isCurrentTab()) {
            Tab f1Var = this.tab;
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            if (!TextUtils.isEmpty(this.f1086t)) {
                str = "(" + this.f1086t.substring(0, 3).toLowerCase() + ".) ";
            } else {
                str = str2;
            }
            sb.append(str);
            sb.append(this.title);
            if (!TextUtils.isEmpty(this.errorMessage)) {
                str2 = " [" + this.errorMessage + "]";
            }
            sb.append(str2);
            f1Var.m713m(sb.toString(), this.iconId);
        }
    }

    public void destroyData() {
        mo134b0();
        if (this.currentDocument != null && isUnsuccesCode()) {
            this.currentDocument = null;
        }
        if (isCurrentTab() && this.tab.m717i()) {
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f800H = false;
            mainLayout.f799G = false;
            mainLayout.f801I = false;
        }
        this.rootView = null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.dataSetObservable.registerObserver(dataSetObserver);
    }

    public void m807s() {
        if (!this.isLoading) {
            tabLoaded(false);
        }
    }

    public Breadcrumb.C0725a[] mo51t() {
        return null;
    }

    public abstract String getLink();

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.dataSetObservable.unregisterObserver(dataSetObserver);
    }

    public boolean isCurrentTab() {
        Tab f1Var = this.tab;
        return f1Var != null && f1Var.page == this;
    }

    public boolean isUnsucces() {
        return this.f1083q;
    }

    public boolean isUnsuccesCode() {
        return this.cmd != 0;
    }

    public boolean mo109y(Object... objArr) {
        return false;
    }

    public void tabReload() {
        this.errorMessage = null;
        if (isUnsuccesCode()) {
            this.isPageLoaded = false;
            if (isUnsucces()) {
                tabLoaded(false);
                this.tab.forumsListView.setAdapter((ListAdapter) this);
            }
            if (DocumentManager.getResultRequest(this) != 0) {
                this.mainActivity.mainLayout.showBageRunningStatus();
            } else {
                sendErrorStatusCode(-1);
            }
        }
    }

    public Page(MainActivity mainActivity) {
        this(mainActivity, 0, null);
    }
}
