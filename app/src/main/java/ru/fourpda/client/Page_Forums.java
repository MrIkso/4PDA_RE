package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Page_Forums extends Page {
    Document forumsDocument;
    View$OnClickListenerC0403b f1578F = new View$OnClickListenerC0403b(this);
    SparseBooleanArray f1579G = new SparseBooleanArray();
    boolean f1580H;

    class C0401a implements OptionPoupupMenuView.IClickListener {

        class RunnableC0402a implements Runnable {
            RunnableC0402a(C0401a aVar) {
            }

            @Override
            public void run() {
                DocumentManager.isMemberValid();
            }
        }

        C0401a() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (1 == i3) {
                Page_Forums.this.tabReload();
            } else if (2 == i3) {
                Page_Forums h0Var = Page_Forums.this;
                DataDB.m365l(h0Var.title, h0Var.getLink());
            } else if (3 == i3) {
                API.ForumModifyRequest.m821p(-1, null, 21, 32, 0, Page_Forums.this, "отметка прочтения", "", new RunnableC0402a(this));
            } else if (4 == i3) {
                Page_Forums h0Var2 = Page_Forums.this;
                h0Var2.tab.addPage(new Page_Announcement(h0Var2.mainActivity, 0));
            }
        }
    }

    protected class View$OnClickListenerC0403b implements View.OnClickListener, View.OnLongClickListener {
        Page_Forums f1582a;

        class C0404a implements OptionPoupupMenuView.IClickListener {

            class RunnableC0405a implements Runnable {
                final Document document;
                final boolean f1586b;

                RunnableC0405a(C0404a aVar, Document uVar, boolean z) {
                    this.document = uVar;
                    this.f1586b = z;
                }

                @Override
                public void run() {
                    int intValue = this.document.getInt(2) | 8;
                    if (!this.f1586b) {
                        intValue ^= 8;
                    }
                    this.document.addInt(2, intValue);
                }
            }

            class RunnableC0406b implements Runnable {
                final Document document;

                RunnableC0406b(Document uVar) {
                    this.document = uVar;
                }

                @Override
                public void run() {
                    this.document.addInt(2, (this.document.getInt(2) | 32) ^ 32);
                    Page_Forums.this.tabLoaded(true);
                }
            }

            C0404a() {
            }

            @SuppressLint("WrongConstant")
            @Override
            public void mo49a(int i, int forumId, int i3) {
                Document document = Page_Forums.this.m684d0(forumId);
                boolean z = true;
                int i4 = 0;
                if (i3 == 21) {
                    Page b = document.count() > 3 ? Urls2.openPage(Page_Forums.this.mainActivity, document.getString(3), false, 2) : new Page_Forum(Page_Forums.this.mainActivity, document.getInt(0), 0, document.getString(1));
                    if (b != null) {
                        Tab f1Var = new Tab(Page_Forums.this.mainActivity);
                        f1Var.addPage(b);
                        Page_Forums.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    }
                } else if (i3 == 1 || i3 == 2) {
                    if (i3 != 1) {
                        z = false;
                    }
                    if (z) {
                        i4 = 8;
                    }
                    API.ForumModifyRequest.m821p(forumId, null, 21, 8, i4, Page_Forums.this, "обновление подписки", "", new RunnableC0405a(this, document, z));
                } else if (i3 == 3) {
                    MainActivity mainActivity = Page_Forums.this.mainActivity;
                    Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showforum=" + forumId, "Ссылка скопирована");
                } else if (i3 == 33) {
                    API.ForumModifyRequest.m821p(forumId, null, 21, 32, 0, Page_Forums.this, "отметка прочтения", "", new RunnableC0406b(document));
                } else if (i3 == 4) {
                    DocumentManager.getResultRequest(new API.ForumGetTopicsRequest(document.getInt(0), Page_Forums.this.mainActivity));
                } else {
                    Toast.makeText(Page_Forums.this.mainActivity, "не работает(", 0).show();
                }
            }
        }

        public View$OnClickListenerC0403b(Page_Forums h0Var) {
            this.f1582a = h0Var;
        }

        @Override
        public void onClick(View view) {
            int intValue = (Integer) view.getTag();
            if (intValue > 0) {
                Document document = this.f1582a.m684d0(intValue);
                if (document.count() > 3) {
                    Page b = Urls2.openPage(Page_Forums.this.mainActivity, document.getString(3), false, 1);
                    if (b != null) {
                        Page_Forums.this.tab.addPage(b);
                        return;
                    }
                    return;
                }
                this.f1582a.tab.addPage(new Page_Forum(this.f1582a.mainActivity, document.getInt(0),
                        0, document.getString(1)));
                return;
            }
            int catalog = -intValue;
            SparseBooleanArray sparseBooleanArray = Page_Forums.this.f1579G;
            sparseBooleanArray.put(catalog, !sparseBooleanArray.get(catalog));
            this.f1582a.tabLoaded(true);
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f1582a.mainActivity).edit();
            edit.putBoolean("open_catalog_" + catalog, Page_Forums.this.f1579G.get(catalog));
            edit.commit();
        }

        @Override
        public boolean onLongClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Forums.this.mainActivity, new C0404a(), true);
            int intValue = (Integer) view.getTag();
            int intValue2 = Page_Forums.this.m684d0(intValue).getInt(2);
            boolean z = (intValue2 & 8) != 0;
            o1Var.addMenuItem(0, intValue, 21, "Открыть в новой вкладке", false, false, false, false);
            o1Var.addMenuItem(0, intValue, z ? 2 : 1, "В избранное", false, false, true, z);
            o1Var.addMenuItem(0, intValue, 4, "О форуме", false, false, false, false);
            if ((intValue2 & 32) != 0) {
                o1Var.addMenuItem(0, intValue, 33, "Отметить прочитанным", false, false, false, false);
            }
            o1Var.addMenuItem(0, intValue, 3, "Копировать ссылку", false, false, false, false);
            o1Var.show(null);
            return true;
        }
    }

    public Page_Forums(MainActivity mainActivity) {
        super(mainActivity, 26982, null);
        this.iconId = R.drawable.ic_nav_forum;
        this.title = "Форумы";
        this.statusMessage = "Загрузка списка форумов";
    }

    @Override
    protected boolean onPageLoadedForum() {
        this.forumsDocument = this.currentDocument.getDocument(0);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mainActivity);
        for (int i = 0; i < this.forumsDocument.count(); i++) {
            Document l = this.forumsDocument.getDocument(i);
            if (!((l.getInt(2) & 16) <= 0)) {
                int intValue = l.getInt(0);
                if (this.f1579G.indexOfKey(intValue) < 0) {
                    SparseBooleanArray sparseBooleanArray = this.f1579G;
                    sparseBooleanArray.put(intValue, defaultSharedPreferences.getBoolean("open_catalog_" + intValue, true));
                }
            }
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0401a());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 1, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 2, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 4, "Правила форума");
        o1Var.addMenuItem(0, 0, 3, "Отметить прочитанным всё");
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f1580H) {
            tabReload();
        }
    }

    @Override
    public boolean mo136a0() {
        return super.mo136a0();
    }

    @Override
    public boolean mo134b0() {
        return super.mo134b0();
    }

    Document m684d0(int i) {
        if (i < 0) {
            i = -i;
        }
        for (int i2 = 0; i2 < this.forumsDocument.count(); i2++) {
            Document l = this.forumsDocument.getDocument(i2);
            if (l.getInt(0) == i) {
                return l;
            }
        }
        return null;
    }

    Document getCurrentDocument(int i) {
        boolean z = true;
        int i2 = 0;
        for (int i3 = 0; i3 < this.forumsDocument.count(); i3++) {
            Document l = this.forumsDocument.getDocument(i3);
            int intValue = l.getInt(0);
            boolean z2 = (l.getInt(2) & 16) <= 0;
            if (!z2) {
                z = this.f1579G.get(intValue);
            }
            if (!z2 || z) {
                if (i2 == i) {
                    return l;
                }
                i2++;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        if (!isUnsucces()) {
            return 0;
        }
        int i = 0;
        boolean z = true;
        for (int i2 = 0; i2 < this.forumsDocument.count(); i2++) {
            Document l = this.forumsDocument.getDocument(i2);
            int intValue = l.getInt(0);
            boolean z2 = (l.getInt(2) & 16) <= 0;
            if (!z2) {
                z = this.f1579G.get(intValue);
            }
            if (!z2 || z) {
                i++;
            }
        }
        return i;
    }

    @Override
    public long getItemId(int i) {
        return (long) getCurrentDocument(i).getInt(0);
    }

    @Override
    public int getItemViewType(int i) {
        return (getCurrentDocument(i).getInt(2) & 16) > 0 ? 0 : 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Document document = getCurrentDocument(i);
        int intValue = document.getInt(0);
        int unreadTopic = document.getInt(2);
        boolean z = (unreadTopic & 16) <= 0;
        if (view == null) {
            if (z) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.index_forum, viewGroup, false);
            } else {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.index_cat, viewGroup, false);
            }
        }
        if (z) {
            view.setTag(intValue);
            view.setOnClickListener(this.f1578F);
            view.setOnLongClickListener(this.f1578F);
            TextView forumName = (TextView) ((ViewGroup) view).findViewById(R.id.forumName);
            forumName.setText(document.getString(1));
            forumName.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (unreadTopic & 32) > 0 ? this.mainActivity.skin.m736f(R.drawable.ic_unread) : null, (Drawable) null);
            Document e02 = getCurrentDocument(i + 1);
            if (e02 == null || (e02.getInt(2) & 16) <= 0) {
                forumName.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.border_bottom));
                float f = this.mainActivity.f731b;
                forumName.setPadding(0, (int) (f * 12.0f), (int) (16.0f * f), (int) (f * 12.0f));
                view.setPadding(0, 0, 0, 0);
            } else {
                forumName.setBackgroundResource(0);
                float f2 = this.mainActivity.f731b;
                forumName.setPadding(0, (int) (f2 * 12.0f), (int) (f2 * 16.0f), (int) (f2 * 12.0f));
                view.setPadding(0, 0, 0, (int) (this.mainActivity.f731b * 16.0f));
            }
        } else {
            view.setTag(-intValue);
            boolean z2 = this.f1579G.get(intValue);
            TextView textView2 = (TextView) view;
            textView2.setText(document.getString(1));
            view.setOnClickListener(this.f1578F);
            textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(z2 ? R.drawable.ic_expand_open : R.drawable.ic_expand_close), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, "forum/index.php?act=idx", "Форум", 0, true, true)};
    }

    @Override
    public String getLink() {
        return "forum/index.php?act=idx";
    }

    @Override
    public void tabReload() {
        this.f1580H = false;
        super.tabReload();
    }
}
