package ru.fourpda.client;

import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

public class Page_Favorites extends Page {
    int siteNum;
    int f1413F;
    Document f1414G;
    int f1415H;
    int f1416I;
    View$OnClickListenerC0360e f1417J;
    View$OnClickListenerC0362g f1418K;
    View$OnClickListenerC0361f f1419L;
    Object f1420M;
    boolean f1421N;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f1422O;
    Util.AbstractC0431l<Boolean, Integer, Integer, Object> f1423P;

    public class C0355a implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0355a() {
           // Page_Favorites.this = r1;
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            int i = cVar2.f2511a;
            if (2 == i || 3 == i) {
                if (Prefs.f1141B) {
                    Page_Favorites f0Var = Page_Favorites.this;
                    f0Var.f1421N = true;
                    if (f0Var.isCurrentTab() && Page_Favorites.this.tab.m717i()) {
                        Page_Favorites f0Var2 = Page_Favorites.this;
                        if (!f0Var2.mainActivity.f733d) {
                            f0Var2.tabReload();
                        }
                    }
                } else if (cVar2.f2513c) {
                    Page_Favorites.this.m810c0("есть обновления", false);
                }
            }
            return Boolean.FALSE;
        }
    }

    public class C0356b implements Util.AbstractC0431l<Boolean, Integer, Integer, Object> {
        C0356b() {
         //   Page_Favorites.this = r1;
        }

        public Boolean mo101a(Integer num, Integer num2, Object obj) {
            Boolean bool = Boolean.TRUE;
            Page_Favorites f0Var = Page_Favorites.this;
            if (obj == f0Var) {
                return bool;
            }
            f0Var.f1421N = true;
            if (f0Var.isCurrentTab() && Page_Favorites.this.tab.m717i()) {
                Page_Favorites f0Var2 = Page_Favorites.this;
                if (!f0Var2.mainActivity.f733d) {
                    f0Var2.tabReload();
                }
            }
            return bool;
        }
    }

    public class C0357c implements OptionPoupupMenuView.IClickListener {
        C0357c() {
           // Page_Favorites.this = r1;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Page_Favorites.this.m729e0(i, i2, i3);
        }
    }

    class C0358d implements OptionPoupupMenuView.IClickListener {

        class RunnableC0359a implements Runnable {
            RunnableC0359a(C0358d dVar) {
            }

            @Override
            public void run() {
                DocumentManager.isMemberValid();
            }
        }

        C0358d() {
          //  Page_Favorites.this = r1;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Favorites.this.tabReload();
            } else if (i3 == 22) {
                Page_Favorites f0Var = Page_Favorites.this;
                DataDB.m365l(f0Var.title, f0Var.getLink());
            } else if (i3 == 1) {
                new C0363h().show(true, true, true);
            } else if (i3 == 2) {
                API.ForumModifyRequest.m821p(-1, null, 21, 32, 8, Page_Favorites.this, "отметка прочтения", "", new RunnableC0359a(this));
            }
        }
    }

    public class View$OnClickListenerC0360e implements View.OnClickListener, View.OnLongClickListener {
        Page_Favorites f1428a;

        public View$OnClickListenerC0360e(Page_Favorites f0Var, Page_Favorites f0Var2) {
            this.f1428a = f0Var2;
        }

        @Override
        public void onClick(View view) {
            this.f1428a.tab.addPage(new Page_Forum(this.f1428a.mainActivity, ((Document) this.f1428a.getItem(((Integer) view.getTag()).intValue())).getInt(1).intValue(), 0, ""));
        }

        @Override
        public boolean onLongClick(View view) {
            this.f1428a.m728f0(3, ((Integer) view.getTag()).intValue());
            return true;
        }
    }

    public class View$OnClickListenerC0361f implements View.OnClickListener {
        Page_Favorites f1429a;

        public View$OnClickListenerC0361f(Page_Favorites f0Var, Page_Favorites f0Var2) {
            this.f1429a = f0Var2;
        }

        @Override
        public void onClick(View view) {
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.f1429a.mainActivity, 1, ((Document) this.f1429a.getItem(((Integer) view.getTag()).intValue())).getInt(1).intValue());
            lVar.m824u(this.f1429a.tab);
            DocumentManager.getResultRequest(lVar);
        }
    }

    public class View$OnClickListenerC0362g implements View.OnClickListener, View.OnLongClickListener {
        Page_Favorites f1430a;

        public View$OnClickListenerC0362g(Page_Favorites f0Var, Page_Favorites f0Var2) {
            this.f1430a = f0Var2;
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) this.f1430a.getItem(((Integer) view.getTag()).intValue());
            this.f1430a.m819N(uVar.getInt(1).intValue(), false, uVar.getString(2));
        }

        @Override
        public boolean onLongClick(View view) {
            this.f1430a.m728f0(4, ((Integer) view.getTag()).intValue());
            return true;
        }
    }

    public class C0363h extends CustomDialog {

        public class View$OnClickListenerC0364a implements View.OnClickListener {
            final TextView f1432a;
            final String f1433b;
            final String f1434c;

            class C0365a implements OptionPoupupMenuView.IClickListener {
                C0365a() {
               //     View$OnClickListenerC0364a.this = r1;
                }

                @Override
                public void mo49a(int i, int i2, int i3) {
                    View$OnClickListenerC0364a.this.f1432a.setTag(Integer.valueOf(i3));
                    View$OnClickListenerC0364a aVar = View$OnClickListenerC0364a.this;
                    aVar.f1432a.setText(i3 != 0 ? aVar.f1433b : aVar.f1434c);
                }
            }

            View$OnClickListenerC0364a(Page_Favorites f0Var, TextView textView, String str, String str2) {
              //  C0363h.this = r1;
                this.f1432a = textView;
                this.f1433b = str;
                this.f1434c = str2;
            }

            @Override
            public void onClick(View view) {
                OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Favorites.this.mainActivity, new C0365a());
                o1Var.addMenuItem(0, 0, 0, this.f1434c);
                o1Var.addMenuItem(0, 0, 1, this.f1433b);
                o1Var.show(null);
            }
        }

        public class View$OnClickListenerC0366b implements View.OnClickListener {
            final TextView f1437a;
            final Widgets$CheckboxTextView f1438b;
            final Widgets$CheckboxTextView f1439c;

            View$OnClickListenerC0366b(Page_Favorites f0Var, TextView textView, Widgets$CheckboxTextView widgets$CheckboxTextView, Widgets$CheckboxTextView widgets$CheckboxTextView2) {
             //   C0363h.this = r1;
                this.f1437a = textView;
                this.f1438b = widgets$CheckboxTextView;
                this.f1439c = widgets$CheckboxTextView2;
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Favorites.this.mainActivity;
                int i = 0;
                boolean z = ((Integer) this.f1437a.getTag()).intValue() > 0;
                Prefs.f1157R = z;
                Prefs.saveBoolean(mainActivity, "fav_sort_by_name", z);
                MainActivity mainActivity2 = Page_Favorites.this.mainActivity;
                boolean checked = this.f1438b.getChecked();
                Prefs.f1158S = checked;
                Prefs.saveBoolean(mainActivity2, "fav_sort_reverse", checked);
                MainActivity mainActivity3 = Page_Favorites.this.mainActivity;
                boolean checked2 = this.f1439c.getChecked();
                Prefs.f1156Q = checked2;
                Prefs.saveBoolean(mainActivity3, "fav_unread_first", checked2);
                Page_Favorites f0Var = Page_Favorites.this;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(Page_Favorites.this.siteNum);
                objArr[1] = Integer.valueOf(Prefs.f1181s);
                int i2 = (Prefs.f1156Q ? 1 : 0) | (Prefs.f1157R ? 4 : 0);
                if (Prefs.f1158S) {
                    i = 8;
                }
                objArr[2] = Integer.valueOf(i | i2);
                f0Var.rootDocument = new Document(objArr);
                Page_Favorites.this.tabReload();
            }
        }

        C0363h() {
            super(Page_Favorites.this.mainActivity, Page_Favorites.this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_fav_sort, (ViewGroup) null), "ОК", "ОТМЕНА");
            Page_Favorites r11 = Page_Favorites.this;
            TextView textView = (TextView) this.rootView.findViewById(R.id.dlg_fav_sort_key);
            Widgets$CheckboxTextView widgets$CheckboxTextView = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_fav_sort_reverse);
            Widgets$CheckboxTextView widgets$CheckboxTextView2 = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_fav_sort_unread_first);
            String string = r11.mainActivity.getResources().getString(R.string.dlg_fav_sort_key_date);
            String string2 = r11.mainActivity.getResources().getString(R.string.dlg_fav_sort_key_name);
            textView.setTag(Integer.valueOf(Prefs.f1157R ? 1 : 0));
            textView.setText(Prefs.f1157R ? string2 : string);
            widgets$CheckboxTextView.setChecked(Prefs.f1158S);
            widgets$CheckboxTextView2.setChecked(Prefs.f1156Q);
            textView.setOnClickListener(new View$OnClickListenerC0364a(r11, textView, string2, string));
            m620f(new View$OnClickListenerC0366b(r11, textView, widgets$CheckboxTextView, widgets$CheckboxTextView2), true);
        }
    }

    public Page_Favorites(MainActivity mainActivity, int i) {
        super(mainActivity, 26221, new Document(i));
        Object[] objArr = new Object[3];
        int i2 = 0;
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(Prefs.f1181s);
        objArr[2] = Integer.valueOf((Prefs.f1156Q ? 1 : 0) | (Prefs.f1157R ? 4 : 0) | (Prefs.f1158S ? 8 : i2));
        this.f1422O = new C0355a();
        this.f1423P = new C0356b();
        this.iconId = R.drawable.ic_nav_fav;
        this.siteNum = i;
        this.title = "Избранное";
        this.statusMessage = "Загрузка избранного";
        this.f1417J = new View$OnClickListenerC0360e(this, this);
        this.f1418K = new View$OnClickListenerC0362g(this, this);
        this.f1419L = new View$OnClickListenerC0361f(this, this);
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoadedForum() {
        this.f1413F = this.currentDocument.getInt(0).intValue();
        this.f1415H = 0;
        this.f1416I = 0;
        Document l = this.currentDocument.getDocument(1);
        this.f1414G = l;
        if (l == null) {
            return false;
        }
        this.f1415H = l.count();
        for (int i = 0; i < this.f1415H; i++) {
            Document l2 = this.f1414G.getDocument(i);
            l2.addString(2, Util.C0427h.UnEscapeString(l2.getString(2)));
            if (l2.getInt(0).intValue() == 1) {
                l2.addString(8, Util.C0427h.UnEscapeString(l2.getString(8)));
            }
            if ((l2.getInt(4).intValue() & 1) > 0) {
                this.f1416I++;
            }
        }
        return true;
    }

    @Override
    public void mo142J(boolean z) {
        super.mo142J(z);
        SystemClock.elapsedRealtime();
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0358d());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        }
        o1Var.addMenuItem(0, 0, 1, "Сортировка");
        o1Var.addMenuItem(0, 0, 2, "Отметить прочитанным всё");
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f1421N) {
            tabReload();
        }
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    protected int mo141P() {
        return ((this.f1413F - 1) / Prefs.f1181s) + 1;
    }

    @Override
    protected int mo140Q() {
        return (this.siteNum / Prefs.f1181s) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_Favorites(this.mainActivity, (i - 1) * Prefs.f1181s);
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        Unread2.f1568b.m654a(this.f1422O);
        DocumentManager.f2752I.m651a(this.f1423P);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        Unread2.f1568b.m653b(this.f1422O);
        DocumentManager.f2752I.m650b(this.f1423P);
        return true;
    }

    int m730d0(int i) {
        int i2;
        int i3;
        this.f1420M = null;
        if (!m816T()) {
            i2 = 0;
        } else if (i == 0) {
            return 0;
        } else {
            if (getCount() - 1 == i) {
                return 6;
            }
            i2 = 1;
        }
        int i4 = this.f1416I;
        if (i4 <= 0) {
            i3 = 0;
        } else if (i == i2) {
            return 1;
        } else {
            if (i < i2 + 1 + i4) {
                Document l = this.f1414G.getDocument((i - i2) - 1);
                this.f1420M = l;
                int intValue = l.getInt(0).intValue();
                if (intValue == 0) {
                    return 3;
                }
                if (intValue == 1) {
                    return 4;
                }
            }
            i3 = 1;
        }
        int i5 = this.f1415H;
        int i6 = this.f1416I;
        if (i5 - i6 <= 0) {
            return 5;
        }
        int i7 = i2 + i3 + i6;
        if (i == i7) {
            return 2;
        }
        int i8 = ((i - i7) - 1) + i6;
        if (i8 >= i5) {
            return 5;
        }
        Document l2 = this.f1414G.getDocument(i8);
        this.f1420M = l2;
        int intValue2 = l2.getInt(0).intValue();
        if (intValue2 == 0) {
            return 3;
        }
        if (intValue2 == 1) {
            return 4;
        }
        return 5;
    }

    void m729e0(int i, int i2, int i3) {
        int i4;
        int i5 = 0;
        Document uVar = (Document) getItem(i2);
        int i6 = 1;
        int intValue = uVar.getInt(1).intValue();
        int intValue2 = uVar.getInt(11).intValue();
        int i7 = 21;
        int i8 = 0;
        if (i3 == 21) {
            if (i == 4) {
                m819N(intValue, true, uVar.getString(2));
            } else if (i == 3) {
                Tab f1Var = new Tab(this.mainActivity);
                f1Var.addPage(new Page_Forum(this.mainActivity, intValue, 0, ""));
                this.tab.mainLayout.setCurrentTab(f1Var);
            }
        } else if (i3 == 31) {
            this.tab.addPage(new Page_Topic(this.mainActivity, intValue, 0));
        } else if (i3 == 32) {
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.mainActivity, 1, intValue);
            lVar.m824u(this.tab);
            DocumentManager.getResultRequest(lVar);
        } else if (i3 == 1) {
            if (i != 3) {
                i7 = 11;
            }
            API.ForumModifyRequest.m821p(intValue, null, i7, 8, 0, this, "удаление из избранного", "ОТПИСАТЬСЯ", null);
        } else if (i3 == 4 || i3 == 5) {
            if (i != 3) {
                i7 = 11;
            }
            if (i3 != 4) {
                i6 = 0;
            }
            API.ForumModifyRequest.m821p(intValue, null, i7, 1, i6 | 8, this, "закрепление в избранном", "", null);
        } else if (i3 == 33) {
            if (i != 3) {
                i7 = 11;
            }
            API.ForumModifyRequest.m821p(intValue, null, i7, 32, 0, this, "отметка прочтения", "", null);
        } else if (i3 == 2) {
            MainActivity mainActivity = this.mainActivity;
            Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showtopic=" + intValue, "Адрес темы скопирован в буфер");
        } else if (i3 == 3) {
            MainActivity mainActivity2 = this.mainActivity;
            Util.copyToClipboard(mainActivity2, "https://4pda.ru/forum/index.php?showforum=" + intValue, "Адрес форума скопирован в буфер");
        } else {
            if (i3 == 51) {
                i5 = (intValue2 & 4) | 0;
            } else if (i3 == 53) {
                i5 = (intValue2 & 4) | 1;
            } else if (i3 == 52) {
                i5 = (intValue2 & 4) | 2;
            } else if (i3 == 54) {
                int i9 = intValue2 & 3;
                if ((intValue2 & 4) <= 0) {
                    i8 = 4;
                }
                i5 = i9 | i8;
            }
            i4 = i5;
            if (i4 == intValue2) {
                API.ForumModifyRequest.m821p(intValue, null, 15, i4, 0, this, "", "", null);
                return;
            }
            return;
        }
        i4 = intValue2;
        if (i4 == intValue2) {
        }
    }

    public void m728f0(int i, int i2) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0357c(), true);
        o1Var.addMenuItem(i, i2, 21, "Открыть в новой вкладке");
        if (i == 4) {
            int i3 = Prefs.f1187y;
            if (i3 == 0) {
                o1Var.addMenuItem(i, i2, 32, "Перейти в конец");
            } else if (i3 == 1) {
                o1Var.addMenuItem(i, i2, 31, "Перейти в начало");
            }
        }
        Document uVar = (Document) getItem(i2);
        int intValue = uVar.getInt(4).intValue();
        if ((intValue & 1) > 0) {
            o1Var.addMenuItem(i, i2, 5, "Убрать из важных");
        } else {
            o1Var.addMenuItem(i, i2, 4, "Добавить в важные");
        }
        int intValue2 = uVar.getInt(11).intValue();
        int i4 = intValue2 & 3;
        if (i4 == 0) {
            o1Var.addMenuItem(i, i2, 52, "Не оповещать");
        } else if (i4 == 2) {
            o1Var.addMenuItem(i, i2, 53, "Оповещать один раз");
        } else {
            o1Var.addMenuItem(i, i2, 51, "Оповещать всегда");
        }
        if (i == 4) {
            o1Var.addMenuItem(i, i2, 54, "Оповещать о шапке", (intValue2 & 4) > 0);
        }
        if ((intValue & 32) != 0) {
            o1Var.addMenuItem(i, i2, 33, "Отметить прочитанным");
        }
        if (i == 3) {
            o1Var.addMenuItem(i, i2, 3, "Копировать ссылку");
        } else if (i == 4) {
            o1Var.addMenuItem(i, i2, 2, "Копировать ссылку");
        }
        o1Var.addMenuItem(i, i2, 1, "Удалить");
        o1Var.show(null);
    }

    @Override
    public int getCount() {
        int i;
        int i2 = 0;
        if (!isUnsucces() || (i = this.f1415H) == 0) {
            return 0;
        }
        int i3 = this.f1416I;
        int i4 = (i - i3 > 0 ? (i - i3) + 1 : 0) + (i3 > 0 ? i3 + 1 : 0) + 1;
        if (m816T()) {
            i2 = 2;
        }
        return i4 + i2;
    }

    @Override
    public Object getItem(int i) {
        m730d0(i);
        return this.f1420M;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        return m730d0(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = null;
        Drawable drawable;
        int d0 = m730d0(i);
        Document uVar = (Document) this.f1420M;
        if (view == null) {
            if (d0 == 0) {
                view2 = m817S(viewGroup, false);
            } else if (d0 == 6) {
                view2 = m817S(viewGroup, true);
            } else if (d0 == 3) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_for, viewGroup, false);
            } else if (d0 == 4) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_top, viewGroup, false);
                ((TextView) view2.findViewById(R.id.topicLastDate)).setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify), (Drawable) null, (Drawable) null, (Drawable) null);
            } else if (d0 == 1) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                view2.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                float f = this.mainActivity.f731b;
                view2.setPadding((int) (f * 16.0f), (int) (f * 16.0f), (int) (f * 16.0f), (int) (f * 8.0f));
                ((TextView) view2).setText("Важные");
            } else if (d0 == 2) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                if (this.f1416I > 0) {
                    view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                } else {
                    view2.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                }
                float f2 = this.mainActivity.f731b;
                view2.setPadding((int) (f2 * 16.0f), (int) (f2 * 16.0f), (int) (f2 * 16.0f), (int) (f2 * 8.0f));
                ((TextView) view2).setText("Темы / Форумы");
            } else if (d0 == 5) {
                view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
            }
            if (d0 != 3) {
                TextView textView = (TextView) view2;
                textView.setText(uVar.getString(2));
                textView.setTag(Integer.valueOf(i));
                textView.setOnClickListener(this.f1417J);
                textView.setOnLongClickListener(this.f1417J);
                int itemViewType = getItemViewType(i + 1);
                if (itemViewType == 4 || itemViewType == 3) {
                    textView.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.border_bottom));
                } else {
                    textView.setBackgroundResource(0);
                }
                float f3 = this.mainActivity.f731b;
                textView.setPadding((int) (f3 * 16.0f), (int) (f3 * 16.0f), (int) (f3 * 16.0f), (int) (f3 * 16.0f));
                boolean z = (uVar.getInt(4).intValue() & 32) > 0;
                int intValue = uVar.getInt(11).intValue();
                if (intValue == 2) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_not), (Drawable) null, z ? this.mainActivity.skin.m736f(R.drawable.ic_unread) : null, (Drawable) null);
                } else if (intValue == 1) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_once), (Drawable) null, z ? this.mainActivity.skin.m736f(R.drawable.ic_unread) : null, (Drawable) null);
                } else {
                    textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify), (Drawable) null, z ? this.mainActivity.skin.m736f(R.drawable.ic_unread) : null, (Drawable) null);
                }
            } else if (d0 == 4) {
                ViewGroup viewGroup2 = (ViewGroup) view2;
                TextView textView2 = (TextView) viewGroup2.findViewById(R.id.topicName);
                View findViewById = viewGroup2.findViewById(R.id.topicGoUnread);
                textView2.setText(uVar.getString(2));
                ((TextView) viewGroup2.findViewById(R.id.topicLastDate)).setText(Util.formatDate(uVar.getInt(9).intValue()));
                int i2 = 8;
                ((TextView) viewGroup2.findViewById(R.id.topicLastUser)).setText(uVar.getString(8));
                viewGroup2.setTag(Integer.valueOf(i));
                viewGroup2.setOnClickListener(this.f1418K);
                viewGroup2.setOnLongClickListener(this.f1418K);
                findViewById.setTag(Integer.valueOf(i));
                if ((uVar.getInt(4).intValue() & 32) != 0) {
                    i2 = 0;
                }
                findViewById.setVisibility(i2);
                findViewById.setOnClickListener(this.f1419L);
                textView2.setEnabled((uVar.getInt(4).intValue() & 4) == 0);
                int itemViewType2 = getItemViewType(i + 1);
                if ((uVar.getInt(4).intValue() & 2) > 0) {
                    viewGroup2.setBackgroundColor(Skin.C0353a.f1366V);
                } else {
                    if (itemViewType2 == 4 || itemViewType2 == 3) {
                        drawable = this.mainActivity.skin.m736f(R.drawable.border_bottom);
                    } else {
                        drawable = null;
                    }
                    viewGroup2.setBackgroundDrawable(drawable);
                }
                int intValue2 = uVar.getInt(11).intValue();
                int i3 = intValue2 & 4;
                if (i3 == 0 || (uVar.getInt(4).intValue() & 16) == 0) {
                    int i4 = intValue2 & 3;
                    if (i4 == 2) {
                        textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_not), (Drawable) null, (Drawable) null, (Drawable) null);
                    } else if (i4 == 1) {
                        textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_once), (Drawable) null, (Drawable) null, (Drawable) null);
                    } else if (i3 != 0) {
                        textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_pinned), (Drawable) null, (Drawable) null, (Drawable) null);
                    } else {
                        textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify), (Drawable) null, (Drawable) null, (Drawable) null);
                    }
                } else {
                    textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(R.drawable.ic_notify_pinned_unread), (Drawable) null, (Drawable) null, (Drawable) null);
                }
            }
            if (d0 == 6) {
                m815U();
            }
            return view2;
        }
        view2 = view;
        if (d0 != 3) {
        }
        if (d0 == 6) {
        }
        return view2;
    }

    @Override
    public int getViewTypeCount() {
        return 7;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f1416I = 0;
    }

    @Override
    public String getLink() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("forum/index.php?act=fav");
        if (this.siteNum > 0) {
            str = "&st=" + this.siteNum;
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override
    public void tabReload() {
        this.f1421N = false;
        m810c0("", false);
        super.tabReload();
    }
}
