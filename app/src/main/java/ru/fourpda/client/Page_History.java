package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Page_History extends Page {
    View$OnClickListenerC0417c f1613E = new View$OnClickListenerC0417c();
    View$OnClickListenerC0416b f1614F = new View$OnClickListenerC0416b();
    int f1615G;
    int f1616H;
    SparseArray<String> f1617I;

    class C0415a implements OptionPoupupMenuView.IClickListener {
        C0415a() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_History.this.tabReload();
            } else if (i3 == 22) {
                Page_History i0Var = Page_History.this;
                DataDB.m365l(i0Var.title, i0Var.getLink());
            }
        }
    }

    protected class View$OnClickListenerC0416b implements View.OnClickListener {
        public View$OnClickListenerC0416b() {
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) view.getTag();
            uVar.addInt(3, uVar.getInt(3) & -33);
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_History.this.mainActivity, 1, uVar.getInt(0));
            lVar.m824u(Page_History.this.tab);
            lVar.m823v(uVar.getString(1));
            DocumentManager.getResultRequest(lVar);
        }
    }

    protected class View$OnClickListenerC0417c implements View.OnClickListener, View.OnLongClickListener {

        class C0418a implements OptionPoupupMenuView.IClickListener {
            final Document f1621a;

            C0418a(Document uVar) {
                this.f1621a = uVar;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                int intValue = this.f1621a.getInt(0);
                Document uVar = this.f1621a;
                uVar.addInt(3, uVar.getInt(3) & -33);
                if (i3 == 1) {
                    Page_History.this.m819N(intValue, false, "");
                } else if (i3 == 2) {
                    Page_History.this.m819N(intValue, true, "");
                }
            }
        }

        public View$OnClickListenerC0417c() {
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) view.getTag();
            uVar.addInt(3, uVar.getInt(3) & -33);
            Page_History.this.m819N(uVar.getInt(0), false, uVar.getString(1));
        }

        @Override
        public boolean onLongClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_History.this.mainActivity, new C0418a((Document) view.getTag()), true);
            o1Var.addMenuItem(0, 0, 1, "Открыть");
            o1Var.addMenuItem(0, 0, 2, "Открыть в новой вкладке");
            o1Var.show(null);
            return true;
        }
    }

    public Page_History(MainActivity mainActivity, int page) {
        super(mainActivity, 26733, new Document(page, Prefs.f1181s));
        this.iconId = R.drawable.ic_nav_fav;
        this.title = "История";
        this.statusMessage = "Загрузка истории";
        this.f1615G = page;
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoadedForum() {
        this.f1616H = this.currentDocument.getInt(0);
        this.currentDocument = this.currentDocument.getDocument(1);
        this.f1617I = new SparseArray<>();
        String str = null;
        for (int i = 0; i < this.currentDocument.count(); i++) {
            Document l = this.currentDocument.getDocument(i);
            l.addString(1, Util.C0427h.UnEscapeString(l.getString(1)));
            l.addString(7, Util.C0427h.UnEscapeString(l.getString(7)));
            String n = Util.formatDate(l.getInt(8), true, false);
            if (!n.equals(str)) {
                SparseArray<String> sparseArray = this.f1617I;
                sparseArray.put(sparseArray.size() + i, n);
                str = n;
            }
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0415a());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    int mo141P() {
        int i = this.f1616H;
        int i2 = Prefs.f1181s;
        int i3 = (i / i2) + (i % i2 != 0 ? 1 : 0);
        return i3 == 0 ? i3 + 1 : i3;
    }

    @Override
    int mo140Q() {
        return (this.f1615G / Prefs.f1181s) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_History(this.mainActivity, (i - 1) * Prefs.f1181s);
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces() || this.currentDocument == null) {
            return 0;
        }
        int size = this.f1617I.size() + this.currentDocument.count();
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
        int count = getCount();
        int i2 = 0;
        if (m816T()) {
            if (i == 0) {
                return 0;
            }
            if (count - 1 == i) {
                return 3;
            }
            i2 = 1;
        }
        if (this.f1617I.indexOfKey(i - i2) >= 0) {
            return 1;
        }
        return 2;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        int i2 = i - (m816T() ? 1 : 0);
        boolean z = false;
        if (view == null) {
            if (itemViewType == 0) {
                view = m817S(viewGroup, false);
            } else if (itemViewType == 3) {
                view = m817S(viewGroup, true);
            } else if (itemViewType == 1) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
            } else if (itemViewType == 2) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_top, viewGroup, false);
            }
        }
        if (itemViewType == 1) {
            MainActivity mainActivity = this.mainActivity;
            float f = mainActivity.f731b;
            view.setBackgroundDrawable(mainActivity.skin.m736f(i2 > 0 ? R.drawable.card_sep : R.color.cardlist_bg));
            int i3 = (int) (16.0f * f);
            view.setPadding(i3, i3, i3, (int) (f * 8.0f));
            ((TextView) view).setText(this.f1617I.get(i2));
        } else if (itemViewType == 2) {
            int i4 = 0;
            int i5 = 0;
            while (i4 < this.currentDocument.count()) {
                if (this.f1617I.indexOfKey(i4 + i5) >= 0) {
                    i5++;
                }
                if (i2 == i4 + i5) {
                    break;
                }
                i4++;
            }
            Document l = this.currentDocument.getDocument(i4);
            ViewGroup viewGroup2 = (ViewGroup) view;
            TextView textView = (TextView) viewGroup2.findViewById(R.id.topicName);
            View findViewById = viewGroup2.findViewById(R.id.topicGoUnread);
            textView.setText(l.getString(1));
            ((TextView) viewGroup2.findViewById(R.id.topicLastDate)).setText(Util.formatDate(l.getInt(5)));
            ((TextView) viewGroup2.findViewById(R.id.topicLastUser)).setText(l.getString(7));
            viewGroup2.setTag(l);
            viewGroup2.setOnClickListener(this.f1613E);
            viewGroup2.setOnLongClickListener(this.f1613E);
            findViewById.setTag(l);
            findViewById.setVisibility((l.getInt(3) & 32) == 0 ? 8 : 0);
            findViewById.setOnClickListener(this.f1614F);
            if ((l.getInt(3) & 4) == 0) {
                z = true;
            }
            textView.setEnabled(z);
            int itemViewType2 = getItemViewType((m816T() ? 1 : 0) + i2 + 1);
            if ((l.getInt(3) & 2) > 0) {
                viewGroup2.setBackgroundColor(Skin.C0353a.f1366V);
            } else {
                viewGroup2.setBackgroundDrawable(itemViewType2 == 2 ? this.mainActivity.skin.m736f(R.drawable.border_bottom) : null);
            }
        }
        if (itemViewType == 3) {
            m815U();
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public String getLink() {
        return "forum/index.php?act=history";
    }

    @Override
    public void tabReload() {
        this.rootDocument = new Document(this.f1615G, Prefs.f1181s);
        super.tabReload();
    }
}
