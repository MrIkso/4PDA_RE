package ru.fourpda.client;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;


public class Page_Reputation extends Page implements BBDisplay.IBBDisplayCallback, Form_Post.AbstractC0846i {
    public int userId;
    ProfileClickListener profileClickListener;
    OnTopicClickListenr onTopicClickListenr;
    List<BBString> votesList;
    int allVotesCount;
    int f2528J;
    String memberIdString;
    boolean f2530L;
    int mode;
    int f2532N;
    private Form_Post.ForumPostModel forumPostModel;
    private Form_Post formPost;

    public class C0739a implements OptionPoupupMenuView.IClickListener {
        final Document f2535a;

        class View$OnClickListenerC0740a implements View.OnClickListener {
            View$OnClickListenerC0740a() {
//                C0739a.this
            }

            @Override
            public void onClick(View view) {
                C0739a aVar = C0739a.this;
                DocumentManager.getResultRequest(new MemberRestoreOrDeleteReputationRequest(aVar.f2535a.getInt(0), false));
            }
        }

        C0739a(Document uVar) {
//            Page_Reputation.this
            this.f2535a = uVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            int i4 = 3;
            if (i3 == 1) {
                Page_Reputation r0Var = Page_Reputation.this;
                Tab f1Var = r0Var.tab;
                MainActivity mainActivity = r0Var.mainActivity;
                Document uVar = this.f2535a;
                if (r0Var.mode != 3) {
                    i4 = 2;
                }
                f1Var.addPage(new Page_Profile(mainActivity, uVar.getInt(i4).intValue(), 0));
            } else if (i3 == 2) {
                Page_Reputation.this.m343e0(this.f2535a.getInt(0).intValue());
            } else if (i3 == 3) {
                if (Prefs.confirmAction) {
                    DlgSimple q1Var = new DlgSimple(Page_Reputation.this.mainActivity, "Подтвердите удаление репутации", false, "УДАЛИТЬ", null);
                    q1Var.editText.setVisibility(8);
                    q1Var.m620f(new View$OnClickListenerC0740a(), true);
                    q1Var.show(true, true, true);
                    return;
                }
                DocumentManager.getResultRequest(new MemberRestoreOrDeleteReputationRequest(this.f2535a.getInt(0), false));
            } else if (i3 == 4) {
                DocumentManager.getResultRequest(new MemberRestoreOrDeleteReputationRequest(this.f2535a.getInt(0), true));
            } else if (i3 == 5) {
                DlgWarn.m320A(Page_Reputation.this.mainActivity, this.f2535a.getInt(2), -this.f2535a.getInt(0));
            }
        }
    }

    class C0741b implements OptionPoupupMenuView.IClickListener {
        C0741b() {
//            Page_Reputation.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Reputation.this.tabReload();
            } else if (i3 == 22) {
                Page_Reputation r0Var = Page_Reputation.this;
                int i4 = 3;
                if (3 == r0Var.mode) {
                    i4 = 2;
                }
                r0Var.mode = i4;
                r0Var.tabReload();
                Page_Reputation.this.m813W(0);
            }
        }
    }

    private class MemberRestoreOrDeleteReputationRequest extends MemberChangeReputationRequest {
        MemberRestoreOrDeleteReputationRequest(int postId, boolean isRestored) {
            super(Page_Reputation.this.mainActivity, 0, isRestored ? 5 : 4, postId, "");
        }

        @Override
        public void prepareResult(int status, Document document) {
            if (status == 0) {
                Toast.makeText(this.context, 4 == this.reputationCode ? "Репутация удалена" : "Репутация восстановлена", 0).show();
                Page_Reputation.this.tabReload();
                return;
            }
            super.prepareResult(status, document);
        }
    }

    static class LoadMemberReputationRequest extends MemberReputationRequest {
        Page_Reputation pageReputation;
        boolean f2541l;

        LoadMemberReputationRequest(Page_Reputation page, int votesCount) {
            super(page.userId, page.mode, votesCount, 200);
            this.pageReputation = page;
            page.f2530L = true;
            this.statusMessage = "загрузка репутации";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Reputation r0Var = this.pageReputation;
            if (!r0Var.isLoading) {
                r0Var.f2530L = false;
                r0Var.tabLoaded(this.f2541l);
                if (status != 0) {
                    Toast.makeText(this.pageReputation.mainActivity, "Ошибка загрузки", 0).show();
                }
            }
        }

        @Override
        public void getResult(int status, Document document) {
            Document reputationsDocument;
            Page_Reputation r0Var = this.pageReputation;
            if (!r0Var.isLoading && status == 0 && r0Var.isUnsucces() && (reputationsDocument = document.getDocument(1)) != null) {
                float f = this.pageReputation.mainActivity.f731b;
                Vector<BBString> reputationList = new Vector<>(reputationsDocument.count());
                this.pageReputation.mainActivity.mainLayout.getWidth();
                for (int i = 0; i < reputationsDocument.count(); i++) {
                    try {
                        Document reputationDocument = reputationsDocument.getDocument(i);
                        reputationDocument.addString(4, Util.C0427h.UnEscapeString(reputationDocument.getString(4)));
                        reputationDocument.addString(5, Util.C0427h.UnEscapeString(reputationDocument.getString(5)));
                        reputationDocument.addString(8, Util.C0427h.UnEscapeString(reputationDocument.getString(8)));
                        StringBuilder bbCodeReputation = new StringBuilder();
                        if (reputationDocument.getInt(11) != 0) {
                            bbCodeReputation.append("[backgroud=darkred][color=white](");
                            bbCodeReputation.append((reputationDocument.getInt(6) & 2) != 0 ? "отменено " : "восстановлено ");
                            bbCodeReputation.append(Util.formatDate(reputationDocument.getInt(11), false, true));
                            bbCodeReputation.append(" [url=https://4pda.ru/forum/index.php?showuser=");
                            bbCodeReputation.append(reputationDocument.getInt(12));
                            bbCodeReputation.append("][color=white]");
                            bbCodeReputation.append(reputationDocument.getString(13).replace("[", "&#91;").replace("]", "&#93;"));
                            bbCodeReputation.append("[/color][/url])[/color][/background]\n");
                        }
                        bbCodeReputation.append(reputationDocument.getString(10));
                        BBString bbReputation = BBString.getBBString(reputationDocument.getString(10), null);
                        if (bbReputation != null) {
                            BBString.C0674e eVar = bbReputation.f2246z;
                            int i3 = (int) (16.0f * f);
                            eVar.f2266j = i3;
                            eVar.f2265i = i3;
                            eVar.f2263g = (float) ((int) (8.0f * f));
                            eVar.f2264h = (float) i3;
                            bbReputation.f2221a0 = reputationDocument;
                            reputationList.add(bbReputation);
                            this.pageReputation.currentDocument.append(reputationDocument);
                        } else {
                            this.pageReputation.allVotesCount++;
                        }
                    } catch (Exception unused) {
                        this.pageReputation.allVotesCount++;
                    }
                }
                this.pageReputation.votesList.addAll(reputationList);
                this.f2541l = true;
            }
        }
    }

    public class ProfileClickListener implements View.OnClickListener {
        Page_Reputation f2542a;

        public ProfileClickListener(Page_Reputation r0Var) {
//            Page_Reputation.this
            this.f2542a = r0Var;
        }

        @Override
        public void onClick(View view) {
            Document l = this.f2542a.currentDocument.getDocument((Integer) view.getTag());
            Page_Reputation r0Var = this.f2542a;
            Tab f1Var = r0Var.tab;
            MainActivity mainActivity = r0Var.mainActivity;
            int i = 3;
            if (Page_Reputation.this.mode != 3) {
                i = 2;
            }
            f1Var.addPage(new Page_Profile(mainActivity, l.getInt(i), 0));
        }
    }

    public class OnTopicClickListenr implements View.OnClickListener {
        Page_Reputation f2544a;

        public OnTopicClickListenr(Page_Reputation r0Var, Page_Reputation r0Var2) {
            this.f2544a = r0Var2;
        }

        @Override
        public void onClick(View view) {
            Document l = this.f2544a.currentDocument.getDocument((Integer) view.getTag());
            if (l.getInt(7).intValue() != 0) {
                int intValue = l.getInt(9);
                if (intValue != 0) {
                    API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.f2544a.mainActivity, 3, intValue);
                    lVar.m824u(this.f2544a.tab);
                    DocumentManager.getResultRequest(lVar);
                    return;
                }
                this.f2544a.tab.addPage(new Page_Topic(this.f2544a.mainActivity, l.getInt(7), 0));
            }
        }
    }

    public Page_Reputation(MainActivity mainActivity, int i) {
        this(mainActivity, i, 2, 0);
    }

    public void m343e0(int i) {
        Form_Post.ForumPostModel kVar = this.forumPostModel;
        if (kVar == null || kVar.postId != i) {
            this.forumPostModel = new Form_Post.ForumPostModel(0, "Жалоба: " + this.title, this.userId, i, false, false, false, false, false, "", "", null);
        }
        if (this.formPost == null) {
            this.formPost = new Form_Post(this.mainActivity, this);
        }
        this.formPost.m196y(this.forumPostModel, this);
    }

    @Override
    public boolean mo145B() {
        Form_Post wVar = this.formPost;
        if (wVar == null || !wVar.m201t()) {
            return false;
        }
        if (this.formPost.m205p()) {
            return true;
        }
        this.formPost.m202s();
        return true;
    }

    @Override
    public void onSearchBar() {
        Form_Post wVar = this.formPost;
        if (wVar != null && wVar.m201t()) {
            this.formPost.m200u();
        }
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoaded() {
        this.allVotesCount = 0;
        try {
            this.f2528J = this.currentDocument.getInt(0);
            Document l = this.currentDocument.getDocument(1);
            this.currentDocument = l;
            if (l != null) {
                Vector<BBString> vector = new Vector<>(this.currentDocument.count());
                this.mainActivity.mainLayout.getWidth();
                for (int i = 0; i < this.currentDocument.count(); i++) {
                    try {
                        Document l2 = this.currentDocument.getDocument(i);
                        int i2 = 4;
                        l2.addString(4, Util.C0427h.UnEscapeString(l2.getString(4)));
                        l2.addString(5, Util.C0427h.UnEscapeString(l2.getString(5)));
                        l2.addString(8, Util.C0427h.UnEscapeString(l2.getString(8)));
                        if (i == 0) {
                            if (this.mode != 3) {
                                i2 = 5;
                            }
                            this.memberIdString = Util.C0427h.UnEscapeString(l2.getString(i2));
                            StringBuilder sb = new StringBuilder();
                            sb.append(this.mode == 3 ? "Как голосовал " : "История репутации ");
                            sb.append(this.memberIdString);
                            this.title = sb.toString();
                        }
                        StringBuilder sb2 = new StringBuilder();
                        if (l2.getInt(11) != 0) {
                            sb2.append("[backgroud=darkred][color=white](");
                            sb2.append((l2.getInt(6) & 2) != 0 ? "отменено " : "восстановлено ");
                            sb2.append(Util.formatDate(l2.getInt(11), false, true));
                            sb2.append(" [url=https://4pda.ru/forum/index.php?showuser=");
                            sb2.append(l2.getInt(12));
                            sb2.append("][color=white]");
                            sb2.append(l2.getString(13).replace("[", "&#91;").replace("]", "&#93;"));
                            sb2.append("[/color][/url])[/color][/background]\n");
                        }
                        sb2.append(l2.getString(10));
                        BBString x = BBString.getBBString(sb2.toString(), null);
                        if (x != null) {
                            BBString.C0674e eVar = x.f2246z;
                            float f = this.mainActivity.f731b;
                            int i3 = (int) (f * 16.0f);
                            eVar.f2266j = i3;
                            eVar.f2265i = i3;
                            eVar.f2263g = (float) ((int) (8.0f * f));
                            eVar.f2264h = (float) ((int) (f * 16.0f));
                            x.f2221a0 = l2;
                            vector.add(x);
                        } else {
                            this.allVotesCount++;
                        }
                    } catch (Exception unused) {
                        this.allVotesCount++;
                    }
                }
                this.votesList = vector;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //ACRA.getErrorReporter().handleSilentException(new Exception("ReputationPage.onLoadAsync", e));
        }
        return false;
    }

    @Override
    public void mo142J(boolean z) {
        this.currentDocument = this.currentDocument.getDocument(1);
        super.mo142J(z);
        if (this.f2532N > 0) {
            int i = 0;
            while (true) {
                if (i >= this.currentDocument.count()) {
                    break;
                } else if (this.f2532N == this.currentDocument.getDocument(i).getInt(0)) {
                    m813W(i + 1);
                    break;
                } else {
                    i++;
                }
            }
            this.f2532N = 0;
        }
        if (isCurrentTab() && this.tab.m717i()) {
            this.tab.mainLayout.f801I = false;
        }
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0741b());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "Как голосовал", 3 == this.mode);
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
            this.tab.mainLayout.f801I = false;
            Form_Post wVar = this.formPost;
            if (wVar != null && wVar.m201t()) {
                this.formPost.m199v();
            }
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public void mo135b(Form_Post.ForumPostModel kVar, boolean z) {
        if (!TextUtils.isEmpty(kVar.postMessage)) {
            DocumentManager.getResultRequest(new API.ReportRequest(this.mainActivity, 2, kVar.postId, kVar.postMessage));
        }
        this.forumPostModel = null;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i;
        Document uVar = (Document) pVar.f2221a0;
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0739a(uVar), true);
        o1Var.addMenuItem(0, 0, 1, "Профиль");
        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
        if (DocumentManager.isLoggined() && L != null) {
            if (L.memberId == uVar.getInt(3) || (i = L.memberGroup) == 9 || i == 10 || i == 4) {
                o1Var.addMenuItem(0, 0, 2, "Пожаловаться");
            }
            int i2 = L.memberGroup;
            if (i2 == 4 || i2 == 10 || i2 == 9) {
                if ((uVar.getInt(6) & 2) == 0) {
                    o1Var.addMenuItem(0, 0, 3, "Отменить", false, true);
                    if (uVar.getInt(11) == 0) {
                        o1Var.addMenuItem(0, 0, 5, "... и наказать", false, true);
                    }
                } else {
                    o1Var.addMenuItem(0, 0, 4, "Восстановить", false, true);
                }
            }
        }
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
        }
    }

    @Override
    public int getCount() {
        List<BBString> list;
        if (!isUnsucces() || (list = this.votesList) == null || list.size() == 0) {
            return 0;
        }
        return this.votesList.size() + 1 + 1;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return (getCount() - 1) == i ? 2 : 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 0) {
                view = new View(this.mainActivity);
                view.setBackgroundDrawable(Skin.SkinColorModel.f1388i0.getConstantState().newDrawable());
                view.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
            } else if (itemViewType == 2) {
                view = new View(this.mainActivity);
                view.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.card_sep));
                view.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
            } else if (itemViewType == 1) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.vote, viewGroup, false);
                view.findViewById(R.id.voteUser).setOnClickListener(this.profileClickListener);
                view.findViewById(R.id.voteWhere).setOnClickListener(this.onTopicClickListenr);
                BBDisplay bBDisplay = (BBDisplay) view.findViewById(R.id.voteCode);
                bBDisplay.setOverlay((BBOverlay) view.findViewById(R.id.voteOverlay));
                bBDisplay.setCallback(this);
            }
        }
        if (itemViewType == 1) {
            int i2 = i - 1;
            Document l = this.currentDocument.getDocument(i2);
            ((BBDisplay) view.findViewById(R.id.voteCode)).setBBString(this.votesList.get(i2));
            TextView textView = (TextView) view.findViewById(R.id.voteUser);
            textView.setTag(i2);
            textView.setText(l.getString(this.mode == 3 ? 5 : 4));
            textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable((l.getInt(6) & 1) == 0 ? R.drawable.ic_thumb_c_down : R.drawable.ic_thumb_c_up), (Drawable) null, (Drawable) null, (Drawable) null);
            TextView textView2 = (TextView) view.findViewById(R.id.voteWhere);
            textView2.setTag(i2);
            if (l.getInt(7) == 0) {
                textView2.setClickable(false);
                textView2.setText("Из профиля");
            } else {
                textView2.setClickable(true);
                textView2.setText(l.getString(8));
            }
            ((TextView) view.findViewById(R.id.voteDate)).setText(Util.formatDate(l.getInt(1)));
            if ((i + 1) - 1 == this.votesList.size()) {
                view.setBackgroundResource(0);
            } else {
                view.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.border_bottom));
            }
            if (!this.f2530L && i > this.votesList.size() - 100 && this.votesList.size() + this.allVotesCount < this.f2528J) {
                DocumentManager.getResultRequest(new LoadMemberReputationRequest(this, this.votesList.size() + this.allVotesCount));
            }
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        List<BBString> list = this.votesList;
        if (list != null) {
            list.clear();
            this.votesList = null;
        }
    }

    @Override
    public String getLink() {
        StringBuilder sb = new StringBuilder();
        sb.append("forum/index.php?act=rep&view=history&mid=");
        sb.append(this.userId);
        sb.append("&mode=");
        sb.append(this.mode == 0 ? "to" : "from");
        return sb.toString();
    }

    @Override
    public void tabReload() {
        this.rootDocument = new Document(this.userId, this.mode, 0, 200);
        super.tabReload();
    }

    public Page_Reputation(MainActivity mainActivity, int userId, int mode, int i3) {
        super(mainActivity, 29293, null);
        this.allVotesCount = 0;
        this.mode = 0;
        this.f2532N = 0;
        this.iconId = R.drawable.ic_nav_profile;
        this.userId = userId;
        this.memberIdString = Integer.valueOf(userId).toString();
        this.title = "Репутация " + userId;
        this.statusMessage = "Загрузка репутации " + userId;
        this.mode = mode;
        this.profileClickListener = new ProfileClickListener(this);
        this.onTopicClickListenr = new OnTopicClickListenr(this, this);
        this.f2532N = i3;
    }
}
