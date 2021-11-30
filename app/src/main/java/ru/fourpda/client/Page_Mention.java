package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_Mention extends Page implements BBDisplay.IBBDisplayCallback {
    List<Page_Topic.PostModel> f1691F;
    int f1692G;
    int f1693H;
    boolean f1694I;
    boolean f1690E = true;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f1695J = new C0455i();

    class C0447a implements OptionPoupupMenuView.IClickListener {
        C0447a() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Mention.this.tabReload();
            } else if (i3 == 22) {
                Page_Mention k0Var = Page_Mention.this;
                DataDB.m365l(k0Var.title, k0Var.getLink());
            } else if (i3 == 1) {
                MainActivity mainActivity = Page_Mention.this.mainActivity;
                boolean z = true ^ Prefs.f1159T;
                Prefs.f1159T = z;
                Prefs.saveBoolean(mainActivity, "mention_show_posts", z);
                Page_Mention.this.tabReload();
            }
        }
    }

    class View$OnLongClickListenerC0448b implements View.OnLongClickListener {
        View$OnLongClickListenerC0448b() {
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
            Page_Mention.this.showBBOptionMenu(bBDisplay, bBDisplay.f527d, new BBDisplay.C0143c());
            return true;
        }
    }

    class View$OnClickListenerC0449c implements View.OnClickListener {
        View$OnClickListenerC0449c() {
        }

        @Override
        public void onClick(View view) {
            Page_Topic.PostModel yVar = (Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag();
            yVar.f3169v = true;
            Tab f1Var = new Tab(Page_Mention.this.mainActivity);
            f1Var.addPage(yVar.f3168u > 0 ? new Page_Article(Page_Mention.this.mainActivity, yVar.f3167t, false) : new Page_Topic(Page_Mention.this.mainActivity, yVar.f3167t, 0));
            Page_Mention.this.tab.mainLayout.setCurrentTab(f1Var);
        }
    }

    class View$OnLongClickListenerC0450d implements View.OnLongClickListener {
        View$OnLongClickListenerC0450d() {
        }

        @Override
        public boolean onLongClick(View view) {
            Util.copyToClipboard(Page_Mention.this.mainActivity, ((TextView) view).getText().toString(), "Ник скопирован в буфер обмена");
            return true;
        }
    }

    class View$OnClickListenerC0451e implements View.OnClickListener {
        View$OnClickListenerC0451e() {
        }

        @Override
        public void onClick(View view) {
            Page_Mention k0Var = Page_Mention.this;
            k0Var.tab.addPage(new Page_Reputation(k0Var.mainActivity, ((Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b));
        }
    }

    class View$OnClickListenerC0452f implements View.OnClickListener {
        View$OnClickListenerC0452f() {
        }

        @Override
        public void onClick(View view) {
            Page_Topic.PostModel yVar = (Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag();
            ((Widgets$CheckboxView) view).setChecked(!yVar.f3169v);
            yVar.f3169v = true;
            if (yVar.f3168u > 0) {
                Page_Mention k0Var = Page_Mention.this;
                k0Var.tab.addPage(new Page_Article(k0Var.mainActivity, yVar.f3167t, yVar.postId));
                return;
            }
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Mention.this.mainActivity, 3, yVar.postId);
            lVar.m824u(Page_Mention.this.tab);
            DocumentManager.getResultRequest(lVar);
        }
    }

    class View$OnClickListenerC0453g implements View.OnClickListener {
        View$OnClickListenerC0453g() {
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Mention.this.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Mention.this.mainActivity, ((Page_Topic.PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b, 0));
            Page_Mention.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    class View$OnClickListenerC0454h implements View.OnClickListener {
        View$OnClickListenerC0454h() {
        }

        @Override
        public void onClick(View view) {
            Page_Topic.PostModel yVar = (Page_Topic.PostModel) view.getTag();
            yVar.f3169v = true;
            if (yVar.f3168u > 0) {
                Page_Mention k0Var = Page_Mention.this;
                k0Var.tab.addPage(new Page_Article(k0Var.mainActivity, yVar.f3167t, yVar.postId));
                return;
            }
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Mention.this.mainActivity, 3, yVar.postId);
            lVar.m824u(Page_Mention.this.tab);
            DocumentManager.getResultRequest(lVar);
        }
    }

    class C0455i implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0455i() {
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            int i = cVar2.f2511a;
            if ((4 == i || 5 == i) && cVar2.f2513c) {
                Page_Mention.this.f1694I = true;
            }
            if (Page_Mention.this.isCurrentTab() && Page_Mention.this.tab.m717i()) {
                Page_Mention k0Var = Page_Mention.this;
                if (!k0Var.mainActivity.f733d && k0Var.f1694I) {
                    k0Var.tabReload();
                }
            }
            return Boolean.TRUE;
        }
    }

    public class C0456j implements OptionPoupupMenuView.IClickListener {
        final Page_Topic.PostModel f1705a;

        C0456j(Page_Topic.PostModel yVar) {
            this.f1705a = yVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Page_Topic.PostModel yVar = this.f1705a;
            yVar.f3169v = true;
            if (i3 == 1) {
                if (yVar.f3168u > 0) {
                    Page_Mention k0Var = Page_Mention.this;
                    k0Var.tab.addPage(new Page_Article(k0Var.mainActivity, yVar.f3167t, yVar.postId));
                    return;
                }
                API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Mention.this.mainActivity, 3, yVar.postId);
                lVar.m824u(Page_Mention.this.tab);
                DocumentManager.getResultRequest(lVar);
            } else if (i3 != 2) {
            } else {
                if (yVar.f3168u > 0) {
                    Tab f1Var = new Tab(Page_Mention.this.mainActivity);
                    MainActivity mainActivity = Page_Mention.this.mainActivity;
                    Page_Topic.PostModel yVar2 = this.f1705a;
                    f1Var.addPage(new Page_Article(mainActivity, yVar2.f3167t, yVar2.postId));
                    Page_Mention.this.tab.mainLayout.setCurrentTab(f1Var);
                    return;
                }
                API.ForumTopicPostRequest lVar2 = new API.ForumTopicPostRequest(Page_Mention.this.mainActivity, 3, yVar.postId);
                lVar2.m826s(true);
                DocumentManager.getResultRequest(lVar2);
            }
        }
    }

    public Page_Mention(MainActivity mainActivity, int i) {
        super(mainActivity, 28013, new Document(0, 0, Prefs.f1159T ? 1 : 0, i, Prefs.f1179q));
        this.iconId = R.drawable.ic_nav_fav;
        this.title = "Упоминания";
        this.statusMessage = "Загрузка списка упоминаний";
        this.f1692G = i;
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoadedForum() {
        this.currentDocument = null;
        return true;
    }

    @Override
    protected boolean onPageLoaded() {
        Exception e;
        Document uVar;
        if (this.isLoading) {
            return false;
        }
        this.f1691F = null;
        this.f1693H = this.currentDocument.getInt(1).intValue();
        Document l = this.currentDocument.getDocument(0);
        this.currentDocument = l;
        if (l != null) {
            Vector vector = new Vector(this.currentDocument.count());
            int width = this.mainActivity.mainLayout.getWidth();
            for (int i = 0; i < this.currentDocument.count(); i++) {
                try {
                    uVar = this.currentDocument.getDocument(i);
                } catch (Exception e2) {
                    e = e2;
                    uVar = null;
                }
                try {
                    Document c = uVar.cloneDocument();
                    c.removeRange(0, 6);
                    Page_Topic.PostModel a = Page_Topic.PostModel.m99a(c);
                    if (a != null) {
                        a.f3168u = uVar.getInt(0).intValue();
                        a.f3167t = uVar.getInt(4).intValue();
                        a.f3165r = new SpannableString(Util.C0427h.UnEscapeString(uVar.getString(5)));
                        a.f3169v = uVar.getInt(1).intValue() != 0;
                        if (this.f1690E) {
                            int i2 = Page_Topic.PostModel.f3147z;
                            SpannableString spannableString = a.f3165r;
                            a.f3166s = i2 + Util.m672b(spannableString.subSequence(0, spannableString.length()).toString(), width - Page_Topic.PostModel.f3143A, Page_Topic.PostModel.f3146y, false) + Page_Topic.PostModel.f3145x + Util.m663k(this.mainActivity, a.f3162o, width) + Page_Topic.PostModel.f3144w;
                        }
                        vector.add(a);
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                   /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Lost Mention Item ");
                    sb.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                    errorReporter.handleSilentException(new Exception(sb.toString(), e));*/
                }
            }
            if (this.f1690E) {
                boolean T = m816T();
                int i3 = (T == true ? 1 : 0) + 1;
                int[] iArr = new int[vector.size() + i3 + (T? 1: 0)];
                this.f1071B = iArr;
                iArr[0] = m816T() ? Page.f1069D : 0;
                int[] iArr2 = this.f1071B;
                iArr2[T == true ? 1 : 0] = iArr2[0] + Page_Topic.PostModel.f3144w;
                int i4 = iArr2[0];
                for (int i5 = 0; i5 < vector.size(); i5++) {
                    i4 += ((Page_Topic.PostModel) vector.get(i5)).f3166s;
                    this.f1071B[i3 + i5] = i4;
                }
                if (m816T()) {
                    int[] iArr3 = this.f1071B;
                    iArr3[iArr3.length - 1] = iArr3[iArr3.length - 2] + Page.f1069D;
                }
            } else {
                this.f1071B = null;
            }
            this.f1691F = vector;
            this.currentDocument = null;
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0447a());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 1, "Отображать посты", Prefs.f1159T);
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f1694I) {
            tabReload();
        }
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    int mo141P() {
        int i = this.f1693H;
        int i2 = Prefs.f1179q;
        int i3 = (i / i2) + (i % i2 != 0 ? 1 : 0);
        return i3 == 0 ? i3 + 1 : i3;
    }

    @Override
    int mo140Q() {
        return (this.f1692G / Prefs.f1179q) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_Mention(this.mainActivity, (i - 1) * Prefs.f1179q);
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        Unread2.f1568b.m654a(this.f1695J);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        Unread2.f1568b.m653b(this.f1695J);
        return true;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
        if (!this.isLoading) {
            boolean T = m816T();
            int width = this.mainActivity.mainLayout.getWidth();
            int i2 = this.f1071B[T ? 1 : 0];
            for (int i3 = 0; i3 < this.f1691F.size(); i3++) {
                int i4 = Page_Topic.PostModel.f3147z;
                SpannableString spannableString = this.f1691F.get(i3).f3165r;
                i2 += i4 + Util.m672b(spannableString.subSequence(0, spannableString.length()).toString(), width - Page_Topic.PostModel.f3143A, Page_Topic.PostModel.f3146y, false) + Page_Topic.PostModel.f3145x + Util.m663k(this.mainActivity, this.f1691F.get(i3).f3162o, width) + Page_Topic.PostModel.f3144w;
                this.f1071B[(T ? 1 : 0) + 1 + i3] = i2;
            }
            if (m816T()) {
                m815U();
            }
        }
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0456j((Page_Topic.PostModel) pVar.f2221a0), true);
        o1Var.addMenuItem(0, 0, 1, "Открыть");
        o1Var.addMenuItem(0, 0, 2, "Открыть в новой вкладке");
        o1Var.show(null);
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        Page_Topic.PostModel yVar = (Page_Topic.PostModel) pVar.f2221a0;
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            if (i2 == 1) {
                Page b = Urls2.openPage(this.mainActivity, kVar.link, false, 1);
                if (b != null) {
                    this.tab.addPage(b);
                }
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        } else {
            int i3 = cVar.f544b;
            if (i3 >= 0) {
                BBString.C0670a aVar = pVar.f2212S[i3];
                if (!aVar.f2249c) {
                    DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVar.f2247a, this.mainActivity, aVar.f2254h));
                } else if (aVar.f2253g <= 0) {
                    new ImageDialog(this.mainActivity).m917b(aVar.f2250d);
                } else if (yVar.f3164q != null) {
                    for (int i4 = 0; i4 < yVar.f3164q.size(); i4++) {
                        if (yVar.f3164q.get(i4).f723a == aVar.f2247a) {
                            new ImageDialog(this.mainActivity).m916c(yVar.f3164q, i4);
                            return;
                        }
                    }
                } else {
                    DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVar.f2247a, this.mainActivity, ""));
                }
            }
        }
    }

    @Override
    public int getCount() {
        List<Page_Topic.PostModel> list;
        int i = 0;
        if (!isUnsucces() || (list = this.f1691F) == null || list.size() == 0) {
            return 0;
        }
        int size = this.f1691F.size() + 1 + (!this.f1690E ? 1 : 0);
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
                return 7;
            }
            i2 = 1;
        }
        if (i == i2) {
            return 1;
        }
        boolean z = this.f1690E;
        if (!z && (count - i2) - 1 == i) {
            return 6;
        }
        if (!z) {
            return 5;
        }
        if (this.f1691F.size() <= 0) {
            return 2;
        }
        int i3 = this.f1691F.get((i - i2) - 1).f3159l;
        if ((i3 & 2) != 0) {
            return 3;
        }
        return (i3 & 4) != 0 ? 4 : 2;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = null;
        Page_Topic.PostModel yVar;
        Widgets$AvatarView avatarView;
        int i2;
        int itemViewType = getItemViewType(i);
        int i3 = i - (m816T() ? 2 : 1);
        int i4 = 0;
        if (view == null) {
            if (itemViewType == 0) {
                view2 = m817S(viewGroup, false);
            } else if (itemViewType == 7) {
                view2 = m817S(viewGroup, true);
            } else if (itemViewType == 1) {
                view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
            } else if (itemViewType == 6) {
                view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
            } else if (itemViewType == 2 || itemViewType == 3 || itemViewType == 4) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.post, viewGroup, false);
                if (itemViewType == 3) {
                    view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_hidden));
                } else if (itemViewType == 4) {
                    view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_deleted));
                } else {
                    view2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.post_normal));
                }
                view2.setOnLongClickListener(new View$OnLongClickListenerC0448b());
                BBDisplay bBDisplay = (BBDisplay) view2.findViewById(R.id.PostCode);
                bBDisplay.setCallback(this);
                bBDisplay.setOverlay((BBOverlay) view2.findViewById(R.id.PostCodeOverlay));
                ((TextView) view2.findViewById(R.id.postTitle)).setOnClickListener(new View$OnClickListenerC0449c());
                ((TextView) view2.findViewById(R.id.authorName)).setOnLongClickListener(new View$OnLongClickListenerC0450d());
                ((TextView) view2.findViewById(R.id.postAuthorRep)).setOnClickListener(new View$OnClickListenerC0451e());
                Widgets$CheckboxView widgets$CheckboxView = (Widgets$CheckboxView) view2.findViewById(R.id.postCheckbox);
                widgets$CheckboxView.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_unread));
                widgets$CheckboxView.setOnClickListener(new View$OnClickListenerC0452f());
                view2.findViewById(R.id.authorImage).setOnClickListener(new View$OnClickListenerC0453g());
            } else if (itemViewType == 5) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_top, viewGroup, false);
                view2.setOnClickListener(new View$OnClickListenerC0454h());
            }
            Drawable drawable = null;
            if (itemViewType != 2 || itemViewType == 3 || itemViewType == 4) {
                yVar = this.f1691F.get(i3);
                view2.setTag(yVar);
                ((TextView) view2.findViewById(R.id.postTitle)).setText(yVar.f3165r);
                TextView textView = (TextView) view2.findViewById(R.id.authorName);
                textView.setText(yVar.f3150c);
                textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f((System.currentTimeMillis() / 1000) - ((long) yVar.f3155h) >= 900 ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
                TextView textView2 = (TextView) view2.findViewById(R.id.authorGroup);
                textView2.setText(API.userGroups.get(yVar.f3151d));
                textView2.setTextColor(yVar.f3152e);
                avatarView = (Widgets$AvatarView) view2.findViewById(R.id.authorImage);
                if (!TextUtils.isEmpty(yVar.f3154g) || (i2 = yVar.f3149b) == 0 || i2 == 17927) {
                    PicoImg.cancel(avatarView);
                    Skin e1Var = this.mainActivity.skin;
                    int i5 = yVar.f3149b;
                    avatarView.setImageDrawable(e1Var.m736f((i5 != 0 || i5 == 17927) ? R.drawable.ic_launcher : R.drawable.ic_avatar));
                } else {
                    PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, yVar.f3154g);
                    l.to(avatarView);
                    l.disableAnimation(!Prefs.f1146G);
                    l.runAsync();
                }
                ((TextView) view2.findViewById(R.id.postDate)).setText(yVar.f3157j);
                TextView textView3 = (TextView) view2.findViewById(R.id.postKarma);
                if (yVar.f3160m != null) {
                    textView3.setVisibility(8);
                } else {
                    textView3.setVisibility(0);
                    textView3.setText(yVar.f3160m);
                }
                ((TextView) view2.findViewById(R.id.postAuthorRep)).setText(yVar.f3153f);
                ((BBDisplay) view2.findViewById(R.id.PostCode)).setBBString(yVar.f3162o);
                ((Widgets$CheckboxView) view2.findViewById(R.id.postCheckbox)).setChecked(!yVar.f3169v);
            } else if (itemViewType == 5) {
                Page_Topic.PostModel yVar2 = this.f1691F.get(i3);
                ViewGroup viewGroup2 = (ViewGroup) view2;
                View findViewById = viewGroup2.findViewById(R.id.topicGoUnread);
                ((TextView) viewGroup2.findViewById(R.id.topicName)).setText(yVar2.f3165r);
                ((TextView) viewGroup2.findViewById(R.id.topicLastDate)).setText(yVar2.f3157j);
                ((TextView) viewGroup2.findViewById(R.id.topicLastUser)).setText(yVar2.f3150c);
                viewGroup2.setTag(yVar2);
                if (yVar2.f3169v) {
                    i4 = 8;
                }
                findViewById.setVisibility(i4);
                if (i3 != this.f1691F.size() - 1) {
                    drawable = this.mainActivity.skin.m736f(R.drawable.border_bottom);
                }
                view2.setBackgroundDrawable(drawable);
            }
            if (itemViewType == 7) {
                m815U();
            }
            return view2;
        }
        view2 = view;
        Drawable drawable2 = null;
        if (itemViewType != 2) {
        }
        yVar = this.f1691F.get(i3);
        view2.setTag(yVar);
        ((TextView) view2.findViewById(R.id.postTitle)).setText(yVar.f3165r);
        TextView textView4 = (TextView) view2.findViewById(R.id.authorName);
        textView4.setText(yVar.f3150c);
        textView4.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f((System.currentTimeMillis() / 1000) - ((long) yVar.f3155h) >= 900 ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView22 = (TextView) view2.findViewById(R.id.authorGroup);
        textView22.setText(API.userGroups.get(yVar.f3151d));
        textView22.setTextColor(yVar.f3152e);
        avatarView = (Widgets$AvatarView) view2.findViewById(R.id.authorImage);
        if (!TextUtils.isEmpty(yVar.f3154g)) {
        }
        PicoImg.cancel(avatarView);
        Skin e1Var2 = this.mainActivity.skin;
        int i52 = yVar.f3149b;
        avatarView.setImageDrawable(e1Var2.m736f((i52 != 0 || i52 == 17927) ? R.drawable.ic_launcher : R.drawable.ic_avatar));
        ((TextView) view2.findViewById(R.id.postDate)).setText(yVar.f3157j);
        TextView textView32 = (TextView) view2.findViewById(R.id.postKarma);
        if (yVar.f3160m != null) {
        }
        ((TextView) view2.findViewById(R.id.postAuthorRep)).setText(yVar.f3153f);
        ((BBDisplay) view2.findViewById(R.id.PostCode)).setBBString(yVar.f3162o);
        ((Widgets$CheckboxView) view2.findViewById(R.id.postCheckbox)).setChecked(!yVar.f3169v);
        if (itemViewType == 7) {
        }
        return view2;
    }

    @Override
    public int getViewTypeCount() {
        return 8;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        List<Page_Topic.PostModel> list = this.f1691F;
        if (list != null) {
            list.clear();
        }
        this.f1691F = null;
    }

    @Override
    public String getLink() {
        return "forum/index.php?act=mentions";
    }

    @Override
    public void tabReload() {
        this.f1690E = Prefs.f1159T;
        this.rootDocument = new Document(0, 0, this.f1690E ? 1 : 0, this.f1692G, Prefs.f1179q);
        this.f1694I = false;
        super.tabReload();
    }
}
