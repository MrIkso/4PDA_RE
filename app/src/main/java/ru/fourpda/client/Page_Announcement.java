package ru.fourpda.client;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class Page_Announcement extends Page implements BBDisplay.IBBDisplayCallback {
    public int linkId;
    BBString bbString;
    private boolean f1134G;

    class C0304a implements OptionPoupupMenuView.IClickListener {
        final BBDisplay bbDisplay;
        final BBString bbString1;
        final BBDisplay.C0143c f1137c;

        C0304a(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
            //Page_Announcement.this = r1;
            this.bbDisplay = bBDisplay;
            this.bbString1 = pVar;
            this.f1137c = cVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            String str;
            if (i3 == 4) {
                this.bbDisplay.f528e.m950j(false);
            } else if (i3 == 1) {
                Page_Announcement.this.f1134G = true;
                Page_Announcement.this.mo129f(this.bbDisplay, this.bbString1, this.f1137c);
            } else if (i3 == 2) {
                Urls2.visitPage(Page_Announcement.this.mainActivity, this.bbString1.f2202I.get(this.f1137c.f543a).link);
            } else if (i3 == 3) {
                BBDisplay.C0143c cVar = this.f1137c;
                int i4 = cVar.f544b;
                if (i4 >= 0) {
                    BBString.C0670a[] aVarArr = this.bbString1.f2212S;
                    if (aVarArr[i4].f2249c && aVarArr[i4].f2253g > 0) {
                        DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVarArr[i4].f2247a, Page_Announcement.this.mainActivity, null));
                        return;
                    }
                }
                new API.LoadForumAttachRequest(cVar.f545c, Page_Announcement.this.mainActivity, null).prepareResult(0, new Document(this.bbString1.f2208O[this.f1137c.f545c].f2279c));
            } else if (i3 == 0) {
                MainActivity mainActivity = Page_Announcement.this.mainActivity;
                BBDisplay.C0143c cVar2 = this.f1137c;
                if (cVar2.f544b >= 0) {
                    str = "https://4pda.ru/forum/dl/post/" + this.bbString1.f2212S[this.f1137c.f544b].f2247a + "/" + this.bbString1.f2212S[this.f1137c.f544b].f2250d;
                } else {
                    str = this.bbString1.f2202I.get(cVar2.f543a).link;
                }
                Util.copyToClipboard(mainActivity, str, "Ссылка скопирована");
            }
        }
    }

    class C0305b implements OptionPoupupMenuView.IClickListener {
        C0305b() {
          //  Page_Announcement.this = r1;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Announcement.this.tabReload();
            } else if (i3 == 22) {
                Page_Announcement b0Var = Page_Announcement.this;
                DataDB.m365l(b0Var.title, b0Var.getLink());
            } else if (i3 == 1) {
                MainActivity mainActivity = Page_Announcement.this.mainActivity;
                Urls2.visitPage(mainActivity, "https://4pda.ru/" + Page_Announcement.this.getLink());
            } else if (i3 == 2) {
                MainActivity mainActivity2 = Page_Announcement.this.mainActivity;
                Util.copyToClipboard(mainActivity2, "https://4pda.ru/" + Page_Announcement.this.getLink(), "Ссылка скопирована");
            }
        }
    }

    public Page_Announcement(MainActivity mainActivity, int linkId) {
        super(mainActivity, 28262, new Document(linkId));
        String str;
        this.iconId = R.drawable.ic_nav_forum;
        this.linkId = linkId;
        if (linkId == 0) {
            str = "Правила форума";
        } else {
            str = "объявление " + linkId;
        }
        this.title = str;
        this.statusMessage = "Загрузка объявления " + this.linkId;
    }

    @Override
    protected boolean onPageLoaded() {
        Log.d("Page_Announcement", "onPageLoaded called");
       /* if (!this.isLoadig) {
            return false;
        }*/
        this.bbString = null;
        try {
           /* Log.d("Page_Announcement", "BBString called");
        //    BBString string = BBString.getBBString(this.currentDocument.getString(1), null);
            this.bbString = BBString.getBBString(this.currentDocument.getString(1), null);
            Log.d("Page_Announcement", "BBString called end");
            BBString.C0674e eVar = bbString.f2246z;
            float f = this.mainActivity.f731b;
            float f2 = (float) ((int) (f * 16.0f));
            eVar.f2264h = f2;
            eVar.f2263g = f2;
            int i = (int) (f * 16.0f);
            eVar.f2266j = i;
            eVar.f2265i = i;
            this.title = Util.C0427h.UnEscapeString(this.currentDocument.getString(0));*/
            Log.d("Page_Announcement", "loaded");
            Log.d("Page_Announcement", title);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
          //  ACRA.getErrorReporter().handleSilentException(new Exception("Lost Announcement", e));
            return false;
        }
    }

    @Override
    public void mo142J(boolean z) {
        super.mo142J(z);
        if (isCurrentTab() && this.tab.m717i()) {
            this.tab.mainLayout.f801I = false;
        }
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0305b());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        }
        o1Var.addMenuItem(0, 0, 2, "Копировать ссылку");
        o1Var.addMenuItem(0, 0, 1, "Открыть в браузере");
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.f801I = false;
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        Util.copyToClipboard(this.mainActivity, str, "Текст скопирован в буфер");
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0304a(bBDisplay, pVar, cVar), true);
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            o1Var.addMenuItem(0, 0, 0, Util.C0427h.urlDecode(kVar.link), true, false);
            o1Var.addMenuItem(0, 0, 0, "Копировать ссылку");
            if (Urls2.is4pdaHost(kVar.link)) {
                o1Var.addMenuItem(0, 0, 1, "Открыть в новой вкладке");
            }
            o1Var.addMenuItem(0, 0, 2, "Открыть с помощью");
        }
        int i2 = cVar.f545c;
        if (i2 >= 0 && (TextUtils.isEmpty(pVar.f2208O[i2].f2279c) || pVar.f2208O[cVar.f545c].f2289m)) {
            cVar.f545c = -1;
        }
        if (cVar.f545c >= 0) {
            o1Var.addMenuItem(0, 0, 3, "Сохранить изображение");
        }
        if (bBDisplay.f528e.m948l()) {
            o1Var.addMenuItem(0, 0, 4, "Копировать");
        }
        o1Var.show(null);
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString bbString, BBDisplay.C0143c cVar) {
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = bbString.f2202I.get(i);
            int statusCode = kVar.statusCode;
            int i3 = 2;
            if (statusCode == 1) {
                MainActivity mainActivity = this.mainActivity;
                String link = kVar.link;
                if (!this.f1134G) {
                    i3 = 1;
                }
                Page page = Urls2.openPage(mainActivity, link, false, i3);
                if (page != null) {
                    if (this.f1134G) {
                        Tab f1Var = new Tab(this.mainActivity);
                        f1Var.addPage(page);
                        this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    } else {
                        this.tab.addPage(page);
                    }
                }
                this.f1134G = false;
            } else if (statusCode == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        }
    }

    @Override
    public int getCount() {
        return isUnsucces() ? 1 : 0;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("ANN", "getViewCalled");
        if (view != null) {
            return view;
        }
        View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.announcement, viewGroup, false);
        BBDisplay bBDisplay = (BBDisplay) inflate.findViewById(R.id.bbCode);
        bBDisplay.setCallback(this);
        bBDisplay.setOverlay((BBOverlay) inflate.findViewById(R.id.bbOverlay));
        bBDisplay.setBBString(new ru.fourpda.client.BBString());
        return inflate;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.bbString = null;
    }

    @Override
    public String getLink() {
        if (this.linkId <= 0) {
            return "forum/index.php?act=boardrules";
        }
        return "forum/index.php?act=announce&st=" + this.linkId;
    }
}
