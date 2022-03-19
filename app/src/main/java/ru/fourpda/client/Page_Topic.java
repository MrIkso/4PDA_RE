package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_Topic extends Page implements BBDisplay.IBBDisplayCallback, Form_Post.AbstractC0846i {
    static SparseArray<SparseArray<C0888b0>> f3004k0 = new SparseArray<>();
    public static int f3005l0 = 140;
    int topicId;
    int topicPage;
    int f3008G;
    int f3009H;
    List<PostModel> f3010I;
    int f3011J;
    int f3012K;
    String f3013L;
    SparseArray<SparseBooleanArray> f3014M;
    boolean f3015N;
    boolean f3016O;
    int f3017P;
    boolean f3018Q;
    boolean f3019R;
    int f3020S;
    Document f3021T;
    Stack<Integer> f3022U;
    SparseArray<C0888b0> f3023V;
    boolean f3024W;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f3025X;
    Util.AbstractC0431l<Boolean, Integer, Integer, Object> f3026Y;
    boolean f3027Z;
    private SparseArray<Form_Post.ForumPostModel> f3028a0;
    private Form_Post.ForumPostModel f3029b0;
    private Form_Post.ForumPostModel f3030c0;
    Form_Post f3031d0;
    private String f3032e0;
    private String f3033f0;
    private Form_Wizard.C0883n f3034g0;
    Form_Wizard f3035h0;
    boolean f3036i0;
    OptionPoupupMenuView.IClickListener f3037j0;

    class View$OnClickListenerC0885a implements View.OnClickListener {
        View$OnClickListenerC0885a() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            DocumentManager.getResultRequest(new LoadKarmaHistoryRequest(((PostModel) ((ViewGroup) view.getParent()).getTag()).postId));
        }
    }

    private class CreateTopicRequest extends API.ForumGetFromRequest {
        CreateTopicRequest() {
            super(0, Page_Topic.this.topicId, 0, null);
            this.statusMessage = "Получение формы (" + Page_Topic.this.f3032e0 + ")";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Document l;
            if (!Page_Topic.this.isLoading) {
                if (status != 0 || uVar == null || (l = uVar.getDocument(0)) == null) {
                    Toast.makeText(Page_Topic.this.mainActivity, "Ошибка создания темы", 1).show();
                    return;
                }
                Page_Topic.this.f3034g0 = new Form_Wizard.C0883n(l);
                Page_Topic.this.m112v0().m181E(Page_Topic.this);
            }
        }
    }

    class View$OnClickListenerC0887b implements View.OnClickListener {
        View$OnClickListenerC0887b() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = new Tab(Page_Topic.this.mainActivity);
            f1Var.addPage(new Page_Profile(Page_Topic.this.mainActivity, ((PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b, 0));
            Page_Topic.this.mainActivity.mainLayout.setCurrentTab(f1Var);
        }
    }

    public static class C0888b0 {
        int f3041a;
        int f3042b;
        int f3043c;
        int f3044d;
        String f3045e;

        public C0888b0(int i, int i2, int i3, int i4, String str) {
            this.f3041a = i;
            this.f3042b = i2;
            this.f3043c = i3;
            this.f3044d = i4;
            this.f3045e = str;
        }
    }

    class RunnableC0889c implements Runnable {
        final int f3046a;
        final int f3047b;

        RunnableC0889c(int i, int i2) {
//            Page_Topic.this
            this.f3046a = i;
            this.f3047b = i2;
        }

        @Override
        public void run() {
            Tab.ForumsListView gVar = Page_Topic.this.tab.forumsListView;
            gVar.setSelectionFromTop(gVar.getHeaderViewsCount() + this.f3046a, (Page_Topic.this.tab.mainLayout.getActionBarHeight() - (PostModel.f3145x + this.f3047b)) + ((int) (Page_Topic.this.mainActivity.f731b * 3.0f)));
        }
    }

    public static class C0890c0 extends MemberInfoRequest {
        private final MainActivity mainActivity;
        private final DlgSimple dlgSimple;

        public class View$OnTouchListenerC0891a implements View.OnTouchListener {
            final Document f3051a;

            class C0892a implements OptionPoupupMenuView.IClickListener {
                C0892a() {
//                    View$OnTouchListenerC0891a.this
                }

                @Override
                public void mo49a(int i, int i2, int i3) {
                    C0890c0.this.dlgSimple.editText.setText(Integer.valueOf(i2).toString());
                }
            }

            View$OnTouchListenerC0891a(Document uVar) {
//                C0890c0.this
                this.f3051a = uVar;
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getX() <= ((float) (view.getWidth() - view.getHeight()))) {
                    return false;
                }
                if (motionEvent.getActionMasked() == 0) {
                    OptionPoupupMenuView o1Var = new OptionPoupupMenuView(C0890c0.this.mainActivity, new C0892a());
                    for (int i = 0; i < this.f3051a.count(); i++) {
                        o1Var.addMenuItem(0, this.f3051a.getDocument(i).getInt(0), 0, this.f3051a.getDocument(i).getString(1));
                    }
                    o1Var.show(null);
                }
                return true;
            }
        }

        C0890c0(MainActivity mainActivity, DlgSimple q1Var) {
            super(DocumentManager.getMemberId());
            this.mainActivity = mainActivity;
            this.dlgSimple = q1Var;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            Document l;
            if (status == 0 && (l = uVar.getDocument(21)) != null && l.count() > 0) {
                this.dlgSimple.editText.setCompoundDrawablesWithIntrinsicBounds(null, null, this.mainActivity.skin.getSkinDrawable(R.drawable.ic_spinner_drop_down), null);
                this.dlgSimple.editText.setCompoundDrawablePadding((int) (this.mainActivity.f731b * 6.0f));
                this.dlgSimple.editText.setOnTouchListener(new View$OnTouchListenerC0891a(l));
            }
        }
    }

    public class VoteOnClickListenerC0893d implements View.OnClickListener {
        final Document f3054a;
        final RelativeLayout f3055b;

        VoteOnClickListenerC0893d(Document uVar, RelativeLayout relativeLayout) {
//            Page_Topic.this
            this.f3054a = uVar;
            this.f3055b = relativeLayout;
        }

        @Override
        public void onClick(View view) {
            Document voteDocument = new Document();
            for (int i = 0; i < this.f3054a.count(); i++) {
                Document l = this.f3054a.getDocument(i);
                Document l2 = l.getDocument(4);
                Document uVar2 = new Document();
                if (l.getInt(1) == 0) {
                    RadioGroup radioGroup = this.f3055b.findViewById(l2.getInt(0));
                    int indexOfChild = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
                    if (indexOfChild > -1) {
                        uVar2.append(indexOfChild);
                    }
                } else {
                    for (int i2 = 0; i2 < l2.count(); i2++) {
                        if (((Widgets$CheckboxTextView) this.f3055b.findViewById(l2.getInt(i2))).getChecked()) {
                            uVar2.append(i2);
                        }
                    }
                }
                voteDocument.append(uVar2);
            }
            Page_Topic x0Var = Page_Topic.this;
            DocumentManager.getResultRequest(new VoteRequest(x0Var.topicId, voteDocument));
        }
    }

    private class TopicInfoSesionsRequest extends MemberSessionsRequest {
        BBDisplay bbDisplay;

        TopicInfoSesionsRequest(BBDisplay bBDisplay) {
            super(0, Page_Topic.this.topicId);
            this.bbDisplay = bBDisplay;
        }

        @Override
        public void prepareResult(int status, Document document) {
            int memberGroup;
            if (!Page_Topic.this.isLoading && status == 0) {
                DocumentManager.MemberInfoModel memberModel = DocumentManager.getMemberInfoModel();
                boolean isAdmins = memberModel != null && ((memberGroup = memberModel.memberGroup) == 9 || memberGroup == 10 || memberGroup == 4);
                Document usersListDocument = document.getDocument(2);
                StringBuilder sb = new StringBuilder();
                int guestUsers = document.getInt(0).intValue();
                int hiddenUsers = document.getInt(1).intValue();
                sb.append(guestUsers + "[/b], скрытых пользователей: [b]");
                sb.append(hiddenUsers + "[/b]\n[/size]");
                int allUsersCount = usersListDocument.count() + guestUsers + hiddenUsers;
                for (int i = 0; i < usersListDocument.count(); i++) {
                    Document userDocument = usersListDocument.getDocument(i);
                    boolean isInActivate = userDocument.getInt(3).intValue() == 1;
                    if (!isInActivate || (isInActivate && isAdmins)) {
                        sb.append("[url=https://4pda.ru/forum/index.php?showuser=" + userDocument.getInt(0) + "]");
                        sb.append("[color=" + Skin.SkinColorModel.f1398n0[userDocument.getInt(2).intValue()] + "]");
                        sb.append(userDocument.getString(1).replace("[", "&#91;").replace("]", "&#93;"));
                        if (isInActivate) {
                            allUsersCount--;
                            sb.append("*");
                        }
                        sb.append("[/color][/url] ");
                    }
                }
                sb.insert(0, "[size=1][b]" + allUsersCount + "[/b] чел. читают эту тему\nгостей: [b]");
                BBString x = BBString.getBBString(sb.toString(), null);
                BBString.C0674e eVar = (BBString.C0674e) x.f2246z.clone();
                x.f2246z = eVar;
                eVar.f2266j = 0;
                eVar.f2265i = 0;
                this.bbDisplay.setBBString(x);
            }
        }
    }

    public class View$OnClickListenerC0895e implements View.OnClickListener {
        View$OnClickListenerC0895e() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            Page_Topic x0Var = Page_Topic.this;
            x0Var.f3019R = true;
            x0Var.tabLoaded(true);
        }
    }

    class VoteRequest extends API.ForumVoteRequest {
        VoteRequest(int i, Document uVar) {
            super(i, uVar);
//            Page_Topic.this
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Topic x0Var = Page_Topic.this;
            if (!x0Var.isLoading) {
                Toast.makeText(x0Var.mainActivity, status == 0 ? "Голос засчитан" : "Ошибка голосования", 0).show();
                if (status == 0) {
                    Page_Topic.this.tabReload();
                }
            }
        }
    }

    public class View$OnClickListenerC0897f implements View.OnClickListener {
        View$OnClickListenerC0897f() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            Page_Topic x0Var = Page_Topic.this;
            x0Var.f3019R = false;
            x0Var.tabLoaded(true);
        }
    }

    public class C0898f0 extends CustomDialog {
        PostModel f3062j;

        public class View$OnClickListenerC0899a implements View.OnClickListener {
            final TextView f3064a;

            class C0900a implements OptionPoupupMenuView.IClickListener {
                C0900a() {
//                    View$OnClickListenerC0899a.this
                }

                @Override
                public void mo49a(int i, int i2, int i3) {
                    View$OnClickListenerC0899a aVar = View$OnClickListenerC0899a.this;
                    TextView textView = aVar.f3064a;
                    C0898f0 f0Var = C0898f0.this;
                    PostModel yVar = f0Var.f3062j;
                    textView.setTag(Integer.valueOf((yVar == null || yVar.postId != i3) ? Page_Topic.this.f3023V.get(i3).f3044d : yVar.f3149b));
                    View$OnClickListenerC0899a aVar2 = View$OnClickListenerC0899a.this;
                    TextView textView2 = aVar2.f3064a;
                    C0898f0 f0Var2 = C0898f0.this;
                    PostModel yVar2 = f0Var2.f3062j;
                    textView2.setText((yVar2 == null || yVar2.postId != i3) ? Page_Topic.this.f3023V.get(i3).f3045e : yVar2.f3150c);
                }
            }

            View$OnClickListenerC0899a(Page_Topic x0Var, TextView textView) {
//                C0898f0.this
                this.f3064a = textView;
            }

            @Override
            public void onClick(View view) {
                OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Topic.this.mainActivity, new C0900a());
                Vector vector = new Vector();
                PostModel yVar = C0898f0.this.f3062j;
                if (yVar != null) {
                    o1Var.addMenuItem(0, 0, yVar.postId, yVar.f3150c);
                    vector.add(Integer.valueOf(C0898f0.this.f3062j.f3149b));
                }
                for (int i = 0; i < Page_Topic.this.f3023V.size(); i++) {
                    C0888b0 valueAt = Page_Topic.this.f3023V.valueAt(i);
                    if (!vector.contains(Integer.valueOf(valueAt.f3044d))) {
                        o1Var.addMenuItem(0, 0, valueAt.f3041a, valueAt.f3045e);
                        vector.add(Integer.valueOf(valueAt.f3044d));
                    }
                }
                o1Var.show(null);
            }
        }

        public class View$OnClickListenerC0901b implements View.OnClickListener {
            final TextView f3067a;

            class C0902a implements OptionPoupupMenuView.IClickListener {
                C0902a() {
//                    View$OnClickListenerC0901b.this
                }

                @Override
                public void mo49a(int i, int i2, int i3) {
                    View$OnClickListenerC0901b.this.f3067a.setTag(Integer.valueOf(i3));
                    View$OnClickListenerC0901b.this.f3067a.setText(Util.formatDate(i3));
                }
            }

            View$OnClickListenerC0901b(Page_Topic x0Var, TextView textView) {
//                C0898f0.this
                this.f3067a = textView;
            }

            @Override
            public void onClick(View view) {
                OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Topic.this.mainActivity, new C0902a());
                PostModel yVar = C0898f0.this.f3062j;
                if (yVar != null) {
                    o1Var.addMenuItem(0, 0, yVar.f3156i, yVar.f3157j);
                }
                for (int i = 0; i < Page_Topic.this.f3023V.size(); i++) {
                    C0888b0 valueAt = Page_Topic.this.f3023V.valueAt(i);
                    PostModel yVar2 = C0898f0.this.f3062j;
                    if (yVar2 == null || valueAt.f3042b != yVar2.f3156i) {
                        int i2 = valueAt.f3042b;
                        o1Var.addMenuItem(0, 0, i2, Util.formatDate(i2));
                    }
                }
                o1Var.show(null);
            }
        }

        public class View$OnClickListenerC0903c implements View.OnClickListener {
            final TextView f3070a;
            final TextView f3071b;

            View$OnClickListenerC0903c(Page_Topic x0Var, TextView textView, TextView textView2) {
//                C0898f0.this
                this.f3070a = textView;
                this.f3071b = textView2;
            }

            @Override
            public void onClick(View view) {
                C0898f0 f0Var = C0898f0.this;
                PostModel yVar = f0Var.f3062j;
                API.ForumModifyRequest.modifyForum(yVar != null ? yVar.postId : 0, Page_Topic.this.f3023V, 3, ((Integer) this.f3070a.getTag()).intValue(), ((Integer) this.f3071b.getTag()).intValue(), Page_Topic.this, "объединение постов", "", null);
            }
        }

        C0898f0(PostModel yVar) {
            super(Page_Topic.this.mainActivity, Page_Topic.this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_join, null), "ОБЪЕДИНИТЬ", "ОТМЕНА");
            this.f3062j = yVar;
            TextView textView = this.rootView.findViewById(R.id.dlg_join_author);
            TextView textView2 = this.rootView.findViewById(R.id.dlg_join_date);
            PostModel yVar2 = this.f3062j;
            if (yVar2 != null) {
                textView.setText(yVar2.f3150c);
                textView.setTag(Integer.valueOf(this.f3062j.f3149b));
                textView2.setText(this.f3062j.f3157j);
                textView2.setTag(Integer.valueOf(this.f3062j.f3156i));
            } else {
                textView.setText(Page_Topic.this.f3023V.valueAt(0).f3045e);
                textView.setTag(Integer.valueOf(Page_Topic.this.f3023V.valueAt(0).f3044d));
                textView2.setText(Util.formatDate(Page_Topic.this.f3023V.valueAt(0).f3042b));
                textView2.setTag(Integer.valueOf(Page_Topic.this.f3023V.valueAt(0).f3042b));
            }
            textView.setOnClickListener(new View$OnClickListenerC0899a(Page_Topic.this, textView));
            textView2.setOnClickListener(new View$OnClickListenerC0901b(Page_Topic.this, textView2));
            m620f(new View$OnClickListenerC0903c(Page_Topic.this, textView, textView2), true);
        }
    }

    public class C0904g implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0904g() {
//            Page_Topic.this
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            if (3 == cVar2.f2511a) {
                Page_Topic x0Var = Page_Topic.this;
                if (x0Var.topicId == cVar2.f2512b && x0Var.mo140Q() >= Page_Topic.this.mo141P()) {
                    if (Page_Topic.this.f3010I.size() > 0) {
                        int i = cVar2.f2517g;
                        List<PostModel> list = Page_Topic.this.f3010I;
                    }
                }
            }
            return Boolean.FALSE;
        }
    }

    public class C0905h implements Util.AbstractC0431l<Boolean, Integer, Integer, Object> {
        C0905h() {
//            Page_Topic.this
        }

        public Boolean mo101a(Integer num, Integer num2, Object obj) {
            if (obj == this) {
                return Boolean.FALSE;
            }
            if (num.intValue() == 1) {
                int intValue = num2.intValue();
                Page_Topic x0Var = Page_Topic.this;
                if (intValue == x0Var.topicId) {
                    x0Var.f3024W = true;
                }
            }
            return Boolean.TRUE;
        }
    }

    public class C0906i implements OptionPoupupMenuView.IClickListener {
        final BBDisplay f3075a;
        final BBString f3076b;
        final BBDisplay.C0143c f3077c;
        final PostModel f3078d;

        class C0907a implements TextWatcher {
            final TextView f3080a;
            final EditText f3081b;
            final TextView f3082c;

            C0907a(C0906i iVar, TextView textView, EditText editText, TextView textView2) {
                this.f3080a = textView;
                this.f3081b = editText;
                this.f3082c = textView2;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean z = true;
                this.f3080a.setEnabled(this.f3081b.getText().length() > 0);
                TextView textView = this.f3082c;
                if (this.f3081b.getText().length() <= 0) {
                    z = false;
                }
                textView.setEnabled(z);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0908b implements View.OnClickListener {
            final EditText f3083a;
            final Dialog f3084b;

            View$OnClickListenerC0908b(EditText editText, Dialog dialog) {
//                C0906i.this
                this.f3083a = editText;
                this.f3084b = dialog;
            }

            @Override
            public void onClick(View view) {
                C0906i iVar = C0906i.this;
                MainActivity mainActivity = Page_Topic.this.mainActivity;
                PostModel yVar = iVar.f3078d;
                DocumentManager.getResultRequest(new MemberChangeReputationRequest(mainActivity, yVar.f3149b, 0, yVar.postId, this.f3083a.getText().toString()));
                this.f3084b.dismiss();
            }
        }

        class View$OnClickListenerC0909c implements View.OnClickListener {
            final EditText f3086a;
            final Dialog f3087b;

            View$OnClickListenerC0909c(EditText editText, Dialog dialog) {
//                C0906i.this
                this.f3086a = editText;
                this.f3087b = dialog;
            }

            @Override
            public void onClick(View view) {
                C0906i iVar = C0906i.this;
                MainActivity mainActivity = Page_Topic.this.mainActivity;
                PostModel yVar = iVar.f3078d;
                DocumentManager.getResultRequest(new MemberChangeReputationRequest(mainActivity, yVar.f3149b, 1, yVar.postId, this.f3086a.getText().toString()));
                this.f3087b.dismiss();
            }
        }

        class View$OnClickListenerC0910d implements View.OnClickListener {
            final DlgSimple f3089a;
            final String f3090b;

            View$OnClickListenerC0910d(C0906i iVar, DlgSimple q1Var, String str) {
                this.f3089a = q1Var;
                this.f3090b = str;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f3089a.editText.getText().toString())) {
                    DataDB.m368i(this.f3089a.editText.getText().toString(), this.f3090b);
                }
            }
        }

        class KarmaDownOnClickListenerC0911e implements View.OnClickListener {
            final Dialog f3091a;

            KarmaDownOnClickListenerC0911e(Dialog dialog) {
//                C0906i.this
                this.f3091a = dialog;
            }

            @Override
            public void onClick(View view) {
                C0906i iVar = C0906i.this;
                Page_Topic x0Var = Page_Topic.this;
                DocumentManager.getResultRequest(new ChangePostKarmaRequest(x0Var, iVar.f3078d, 1));
                this.f3091a.dismiss();
            }
        }

        class KarmaUpOnClickListener implements View.OnClickListener {
            final Dialog f3093a;

            KarmaUpOnClickListener(Dialog dialog) {
//                C0906i.this
                this.f3093a = dialog;
            }

            @Override
            public void onClick(View view) {
                C0906i iVar = C0906i.this;
                Page_Topic x0Var = Page_Topic.this;
                DocumentManager.getResultRequest(new ChangePostKarmaRequest(x0Var, iVar.f3078d, 2));
                this.f3093a.dismiss();
            }
        }

        C0906i(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar, PostModel yVar) {
//            Page_Topic.this
            this.f3075a = bBDisplay;
            this.f3076b = pVar;
            this.f3077c = cVar;
            this.f3078d = yVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Exception e;
            boolean z = false;
            if (Page_Topic.this.isUnsucces()) {
                int i4 = 3;
                try {
                    Page_Topic.this.m128f0();
                    z = false;
                } catch (Exception e2) {
                    e = e2;
                    i4 = 2;
                }
                try {
                    if (i3 == 2 || i3 == 3) {
                        for (int i5 = 0; i5 < Page_Topic.this.tab.forumsListView.getChildCount(); i5++) {
                            BBOverlay bBOverlay = Page_Topic.this.tab.forumsListView.getChildAt(i5).findViewById(R.id.PostCodeOverlay);
                            if (bBOverlay != null) {
                                bBOverlay.m949k();
                            }
                        }
                        BBOverlay bBOverlay2 = this.f3075a.f528e;
                        if (i3 == 2) {
                            z = true;
                        }
                        bBOverlay2.m950j(z);
                    } else if (i3 == 21) {
                        Page_Topic x0Var = Page_Topic.this;
                        x0Var.f3027Z = true;
                        x0Var.mo129f(this.f3075a, this.f3076b, this.f3077c);
                    } else if (i3 == 31) {
                        Page_Topic.this.mo129f(this.f3075a, this.f3076b, this.f3077c);
                    } else if (i3 == 30) {
                        Urls2.visitPage(Page_Topic.this.mainActivity, this.f3076b.f2202I.get(this.f3077c.f543a).link);
                    } else if (i3 == 22) {
                        Page_Topic.this.mo129f(this.f3075a, this.f3076b, this.f3077c);
                    } else if (i3 == 28) {
                        BBDisplay.C0143c cVar = this.f3077c;
                        int i6 = cVar.f544b;
                        if (i6 >= 0) {
                            BBString.C0670a[] aVarArr = this.f3076b.f2212S;
                            if (aVarArr[i6].f2249c && aVarArr[i6].f2253g > 0) {
                                DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVarArr[i6].f2247a, Page_Topic.this.mainActivity, null));
                                return;
                            }
                        }
                        new API.LoadForumAttachRequest(cVar.f545c, Page_Topic.this.mainActivity, null).prepareResult(0, new Document(this.f3076b.f2208O[this.f3077c.f545c].f2279c));
                    } else if (i3 == 27) {
                        new ImageDialog(Page_Topic.this.mainActivity).m917b(this.f3076b.f2208O[this.f3077c.f545c].f2279c);
                    } else if (i3 == 26) {
                        MainActivity mainActivity = Page_Topic.this.mainActivity;
                        int i7 = this.f3077c.f543a;
                        Util.copyToClipboard(mainActivity, i7 >= 0 ? this.f3076b.f2202I.get(i7).link : "https://4pda.ru/forum/dl/post/" + this.f3076b.f2212S[this.f3077c.f544b].f2247a + "/" + this.f3076b.f2212S[this.f3077c.f544b].f2250d, "Ссылка скопирована");
                    } else {
                        int i8 = R.style.Dialog_Light;
                        if (i3 == 18) {
                            MainActivity mainActivity2 = Page_Topic.this.mainActivity;
                            if (!Skin.SkinColorModel.f1392k0) {
                                i8 = R.style.Dialog_Dark;
                            }
                            Dialog dialog = new Dialog(mainActivity2, i8);
                            dialog.requestWindowFeature(1);
                            dialog.setContentView(Page_Topic.this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_reputation2, null));
                            dialog.getWindow().setBackgroundDrawable(Page_Topic.this.mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
                            dialog.getWindow().setLayout(-1, -2);
                            dialog.setCanceledOnTouchOutside(true);
                            ((TextView) dialog.findViewById(R.id.repTitle)).setText("Изменение репутации " + this.f3078d.f3150c);
                            TextView textView = dialog.findViewById(R.id.repDown);
                            TextView textView2 = dialog.findViewById(R.id.repUp);
                            EditText editText = dialog.findViewById(R.id.repReason);
                            editText.addTextChangedListener(new C0907a(this, textView, editText, textView2));
                            textView.setOnClickListener(new View$OnClickListenerC0908b(editText, dialog));
                            textView2.setOnClickListener(new View$OnClickListenerC0909c(editText, dialog));
                            dialog.show();
                            CustomDialog.m623c(dialog);
                            Page_Topic.this.mainActivity.mainLayout.hideKeyboard(editText);
                        } else if (i3 == 24) {
                            this.f3075a.m977d(this.f3077c.f545c, true);
                        } else if (i3 == 32) {
                            this.f3075a.m965p(this.f3077c.f546d);
                        } else if (i3 == 0) {
                            Page_Topic.this.m111w0("[SNAPBACK]" + this.f3078d.postId + "[/SNAPBACK] [B]" + this.f3078d.f3150c + "[/B], ");
                        } else if (i3 == 1) {
                            Tab f1Var = new Tab(Page_Topic.this.mainActivity);
                            f1Var.addPage(new Page_Profile(Page_Topic.this.mainActivity, this.f3078d.f3149b, 0));
                            Page_Topic.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                        } else if (i3 == 5) {
                            Page_Topic.this.m128f0().m196y(Page_Topic.this.m110x0(this.f3078d), Page_Topic.this);
                        } else if (i3 == 16) {
                            Tab f1Var2 = new Tab(Page_Topic.this.mainActivity);
                            Page_Topic x0Var2 = Page_Topic.this;
                            f1Var2.addPage(new Page_Search(x0Var2.mainActivity, 786433, 0, x0Var2.topicId, this.f3078d.f3149b, "", 0, this.f3078d.f3150c + " в теме " + Page_Topic.this.title));
                            Page_Topic.this.mainActivity.mainLayout.setCurrentTab(f1Var2);
                        } else if (i3 == 40) {
                            Tab f1Var3 = new Tab(Page_Topic.this.mainActivity);
                            Page_Topic x0Var3 = Page_Topic.this;
                            f1Var3.addPage(new Page_Search(x0Var3.mainActivity, 786433, 0, x0Var3.topicId, 0, String.valueOf(this.f3078d.postId), 0, "Упоминания поста " + this.f3078d.postId));
                            Page_Topic.this.mainActivity.mainLayout.setCurrentTab(f1Var3);
                        } else if (i3 == 20) {
                            String y0 = Page_Topic.this.m108y0(this.f3078d.postId, i2);
                            if (!DataDB.m366k(y0)) {
                                DlgSimple q1Var = new DlgSimple(Page_Topic.this.mainActivity, "Пост в закладки", false, "СОЗДАТЬ", null);
                                EditText editText2 = q1Var.editText;
                                StringBuilder sb = new StringBuilder();
                                sb.append(Page_Topic.this.title);
                                sb.append(" (Пост ");
                                sb.append(this.f3078d.f3150c);
                                sb.append(" #");
                                sb.append(this.f3078d.postId);
                                sb.append(i2 > 0 ? " спойлер №" + i2 : "");
                                sb.append(")");
                                editText2.setText(sb.toString());
                                q1Var.m620f(new View$OnClickListenerC0910d(this, q1Var, y0), true);
                                q1Var.show(true, true, true);
                                return;
                            }
                            DataDB.m367j(y0);
                        } else if (i3 == 4) {
                            if (Page_Topic.this.f3029b0 == null) {
                                Page_Topic.this.f3029b0 = new Form_Post.ForumPostModel(1, "Жалоба на пост " + this.f3078d.postId, Page_Topic.this.topicId, this.f3078d.postId, false, false, false, false, false, "", "", null);
                            }
                            Page_Topic.this.m128f0().m196y(Page_Topic.this.f3029b0, Page_Topic.this);
                        } else if (i3 == 7) {
                            API.ForumModifyRequest.modifyForum(this.f3078d.postId, null, 1, 1, 1, Page_Topic.this, "закрепление поста", "ЗАКРЕПИТЬ", null);
                        } else if (i3 == 8) {
                            API.ForumModifyRequest.modifyForum(this.f3078d.postId, null, 1, 1, 0, Page_Topic.this, "открепление поста", "ОТКРЕПИТЬ", null);
                        } else if (i3 == 12) {
                            int i9 = this.f3078d.postId;
                            Page_Topic x0Var4 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i9, x0Var4.f3023V, 4, 0, 0, x0Var4, "удаление постов", "УДАЛИТЬ", null);
                        } else if (i3 == 35) {
                            int i10 = this.f3078d.postId;
                            Page_Topic x0Var5 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i10, x0Var5.f3023V, 5, 0, 0, x0Var5, "восстановление постов", "ВОССТАНОВИТЬ", null);
                        } else if (i3 == 13) {
                            new C0898f0(this.f3078d).show(true, true, true);
                        } else if (i3 == 15) {
                            int i11 = this.f3078d.postId;
                            Page_Topic x0Var6 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i11, x0Var6.f3023V, 1, 2, 2, x0Var6, "скрытие постов", "СКРЫТЬ", null);
                        } else if (i3 == 14) {
                            int i12 = this.f3078d.postId;
                            Page_Topic x0Var7 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i12, x0Var7.f3023V, 1, 2, 0, x0Var7, "отображение постов", "ПОКАЗАТЬ", null);
                        } else if (i3 == 6) {
                            Page_Topic x0Var8 = Page_Topic.this;
                            Page_Topic.movePosts(x0Var8, x0Var8.f3023V, this.f3078d.postId);
                        } else if (i3 == 38) {
                            int i13 = this.f3078d.postId;
                            Page_Topic x0Var9 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i13, x0Var9.f3023V, 1, 2048, 2048, x0Var9, "защиту поста", "ЗАЩИТИТЬ", null);
                        } else if (i3 == 39) {
                            int i14 = this.f3078d.postId;
                            Page_Topic x0Var10 = Page_Topic.this;
                            API.ForumModifyRequest.modifyForum(i14, x0Var10.f3023V, 1, 2048, 0, x0Var10, "снятие защиты поста", "СНЯТЬ", null);
                        } else if (i3 == 17) {
                            Util.copyToClipboard(Page_Topic.this.mainActivity, "https://4pda.ru/" + Page_Topic.this.m108y0(this.f3078d.postId, i2), "Ссылка скопирована в буфер обмена");
                        } else if (i3 == 19) {
                            MainActivity mainActivity3 = Page_Topic.this.mainActivity;
                            if (!Skin.SkinColorModel.f1392k0) {
                                i8 = R.style.Dialog_Dark;
                            }
                            Dialog dialog2 = new Dialog(mainActivity3, i8);
                            dialog2.requestWindowFeature(1);
                            dialog2.setContentView(R.layout.dlg_karma);
                            dialog2.getWindow().setBackgroundDrawable(Page_Topic.this.mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
                            dialog2.getWindow().setLayout(-1, -2);
                            dialog2.setCanceledOnTouchOutside(true);
                            dialog2.findViewById(R.id.karma_down).setOnClickListener(new KarmaDownOnClickListenerC0911e(dialog2));
                            dialog2.findViewById(R.id.karma_up).setOnClickListener(new KarmaUpOnClickListener(dialog2));
                            dialog2.show();
                            CustomDialog.m623c(dialog2);
                        } else if (i3 == 34) {
                            MainActivity mainActivity4 = Page_Topic.this.mainActivity;
                            PostModel yVar = this.f3078d;
                            DlgWarn.m320A(mainActivity4, yVar.f3149b, yVar.postId);
                        } else if (i3 == 36) {
                            Util.copyToClipboard(Page_Topic.this.mainActivity, "https://4pda.ru/forum/index.php?showtopic=" + Page_Topic.this.topicId + "&view=findpost&p=" + this.f3078d.postId, null);
                            Page_Topic.this.m128f0().m196y(Page_Topic.this.m110x0(Page_Topic.this.f3010I.get(0)), Page_Topic.this);
                        } else if (i3 == 37) {
                            Form_Post.ForumPostModel x0 = Page_Topic.this.m110x0(Page_Topic.this.f3010I.get(0));
                            Page_Topic.this.m128f0().m196y(x0, Page_Topic.this);
                            int indexOf = x0.postMessage.indexOf("=" + this.f3078d.postId);
                            if (indexOf > 0) {
                                indexOf = x0.postMessage.lastIndexOf("[url", indexOf);
                            }
                            if (indexOf >= 0) {
                                Page_Topic.this.f3031d0.m197x(indexOf, x0.postMessage.indexOf("[/url]", indexOf) + 6);
                            }
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    //ACRA.getErrorReporter().handleSilentException(new Exception("Topic.OnContextMenuItemClick " + i4, e));
                }
            }
        }
    }

    public static class C0913j implements TextWatcher {
        final DlgSimple f3095a;

        C0913j(DlgSimple q1Var) {
            this.f3095a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f3095a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class RunnableC0914k implements Runnable {
        final int f3096a;

        RunnableC0914k(int i) {
//            Page_Topic.this
            this.f3096a = i;
        }

        @Override
        public void run() {
            Page_Topic.this.m813W(this.f3096a);
        }
    }

    public static class View$OnClickListenerC0915l implements View.OnClickListener {
        final DlgSimple f3098a;
        final Page f3099b;
        final int f3100c;
        final SparseArray f3101d;

        View$OnClickListenerC0915l(DlgSimple q1Var, Page a0Var, int i, SparseArray sparseArray) {
            this.f3098a = q1Var;
            this.f3099b = a0Var;
            this.f3100c = i;
            this.f3101d = sparseArray;
        }

        @Override
        public void onClick(View view) {
            String obj = this.f3098a.editText.getText().toString();
            int i = Urls2.m674i(obj);
            if (i == 0) {
                Page b = Urls2.openPage(this.f3099b.mainActivity, obj, true, 0);
                if (b instanceof Page_Topic) {
                    i = ((Page_Topic) b).topicId;
                }
            }
            if (i != 0) {
                API.ForumModifyRequest.modifyForum(this.f3100c, this.f3101d, 2, i, 0, this.f3099b, "перемещение постов", "", null);
            } else {
                Toast.makeText(this.f3099b.mainActivity, "Неправильная ссылка", 1).show();
            }
        }
    }

    class C0916m implements OptionPoupupMenuView.IClickListener {
        final Form_Post.ForumPostModel f3102a;

        C0916m(Form_Post.ForumPostModel kVar) {
//            Page_Topic.this
            this.f3102a = kVar;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Page_Topic x0Var = Page_Topic.this;
            int topicId = x0Var.topicId;
            Form_Post.ForumPostModel kVar = this.f3102a;
            int postId = kVar.postId;
            String postMessage = kVar.postMessage;
            int[] attaches = kVar.getPostAttaches();
            Form_Post.ForumPostModel kVar2 = this.f3102a;
            DocumentManager.getResultRequest(new SendPostRequest(topicId, postId, postMessage, attaches, kVar2.reason, kVar2.showMark, i3 != 0));
        }
    }

    public class C0917n implements OptionPoupupMenuView.IClickListener {

        class View$OnClickListenerC0918a implements View.OnClickListener {
            final DlgSimple f3105a;

            View$OnClickListenerC0918a(DlgSimple q1Var) {
//                C0917n.this
                this.f3105a = q1Var;
            }

            @Override
            public void onClick(View view) {
                String obj = this.f3105a.editText.getText().toString();
                int i = Urls2.m674i(obj);
                if (i == 0) {
                    Page b = Urls2.openPage(Page_Topic.this.mainActivity, obj, true, 0);
                    if (b instanceof Page_Forum) {
                        i = ((Page_Forum) b).forumNumber;
                    }
                }
                if (i != 0) {
                    int i2 = Page_Topic.this.topicId;
                    boolean checked = this.f3105a.checkboxTextView.getChecked();
                    API.ForumModifyRequest.modifyForum(i2, null, 12, i, checked ? 1 : 0, Page_Topic.this, "перемещение темы", "", null);
                    return;
                }
                Toast.makeText(Page_Topic.this.mainActivity, "Неправильная ссылка", 1).show();
            }
        }

        class View$OnClickListenerC0919b implements View.OnClickListener {
            final DlgSimple f3107a;
            final String f3108b;

            View$OnClickListenerC0919b(DlgSimple q1Var, String str) {
//                C0917n.this
                this.f3107a = q1Var;
                this.f3108b = str;
            }

            @Override
            public void onClick(View view) {
                String obj = this.f3107a.editText.getText().toString();
                boolean checked = this.f3107a.checkboxTextView.getChecked();
                if (checked && obj.equals(this.f3108b)) {
                    this.f3107a.editText.setText(Page_Topic.this.title);
                } else if (!checked && obj.equals(Page_Topic.this.title)) {
                    this.f3107a.editText.setText(this.f3108b);
                }
            }
        }

        class View$OnClickListenerC0920c implements View.OnClickListener {
            final DlgSimple f3110a;
            final String f3111b;
            final String f3112c;

            View$OnClickListenerC0920c(C0917n nVar, DlgSimple q1Var, String str, String str2) {
                this.f3110a = q1Var;
                this.f3111b = str;
                this.f3112c = str2;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f3110a.editText.getText().toString())) {
                    DataDB.m368i(this.f3110a.editText.getText().toString(), this.f3110a.checkboxTextView.getChecked() ? this.f3111b : this.f3112c);
                }
            }
        }

        class C0921d implements OptionPoupupMenuView.IClickListener {
            C0921d() {
//                C0917n.this
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                List<PostModel> list = Page_Topic.this.f3010I;
                int i4 = 0;
                if (list != null && list.size() > 0) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= Page_Topic.this.tab.forumsListView.getChildCount()) {
                            break;
                        }
                        View childAt = Page_Topic.this.tab.forumsListView.getChildAt(i5);
                        if (childAt.getBottom() > Page_Topic.this.tab.forumsListView.f1474b.getMeasuredHeight() * 2) {
                            Object tag = childAt.getTag();
                            if (tag instanceof PostModel) {
                                i4 = ((PostModel) tag).postId;
                                break;
                            }
                        }
                        i5++;
                    }
                }
                if (i4 != 0) {
                    API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Topic.this.mainActivity, 3, i4);
                    lVar.m822w(i3);
                    lVar.m824u(Page_Topic.this.tab);
                    lVar.m823v(Page_Topic.this.title);
                    DocumentManager.getResultRequest(lVar);
                    return;
                }
                Tab f1Var = Page_Topic.this.tab;
                Page_Topic x0Var = Page_Topic.this;
                f1Var.addPage(new Page_Topic(x0Var.mainActivity, x0Var.topicId, 0, i3, 0, null, x0Var.title));
            }
        }

        class View$OnClickListenerC0922e implements View.OnClickListener {
            final DlgMemberEditor f3114a;

            View$OnClickListenerC0922e(DlgMemberEditor p1Var) {
//                C0917n.this
                this.f3114a = p1Var;
            }

            @Override
            public void onClick(View view) {
                Integer b = this.f3114a.f2451k.getMemberId();
                API.ForumModifyRequest.modifyForum(Page_Topic.this.topicId, null, 16, b != null ? b.intValue() : 0, 0, Page_Topic.this, "сохранение куратора", "", null);
            }
        }

        class C0923f implements OptionPoupupMenuView.IClickListener {
            C0923f() {
//                C0917n.this
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 12) {
                    Page_Topic x0Var = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var.f3023V, 4, 0, 0, x0Var, "удаление постов", "УДАЛИТЬ", null);
                } else if (i3 == 35) {
                    Page_Topic x0Var2 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var2.f3023V, 5, 0, 0, x0Var2, "восстановление постов", "ВОССТАНОВИТЬ", null);
                } else if (i3 == 13) {
                    new C0898f0(null).show(true, true, true);
                } else if (i3 == 15) {
                    Page_Topic x0Var3 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var3.f3023V, 1, 2, 2, x0Var3, "скрытие постов", "СКРЫТЬ", null);
                } else if (i3 == 14) {
                    Page_Topic x0Var4 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var4.f3023V, 1, 2, 0, x0Var4, "отображение постов", "ПОКАЗАТЬ", null);
                } else if (i3 == 6) {
                    Page_Topic x0Var5 = Page_Topic.this;
                    Page_Topic.movePosts(x0Var5, x0Var5.f3023V, 0);
                } else if (i3 == 38) {
                    Page_Topic x0Var6 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var6.f3023V, 1, 2048, 2048, x0Var6, "защиту постов", "ЗАЩИТИТЬ", null);
                } else if (i3 == 39) {
                    Page_Topic x0Var7 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(0, x0Var7.f3023V, 1, 2048, 0, x0Var7, "снятие защиты постов", "СНЯТЬ", null);
                }
            }
        }

        class View$OnClickListenerC0924g implements View.OnClickListener {
            View$OnClickListenerC0924g() {
//                C0917n.this
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Topic.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showtopic=" + Page_Topic.this.topicId, "Ссылка скопирована");
            }
        }

        class View$OnClickListenerC0925h implements View.OnClickListener {
            View$OnClickListenerC0925h() {
//                C0917n.this
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Topic.this.mainActivity;
                Urls2.visitPage(mainActivity, "https://4pda.ru/" + Page_Topic.this.getLink());
            }
        }

        class View$OnClickListenerC0926i implements View.OnClickListener {
            final Dialog f3119a;

            View$OnClickListenerC0926i(Dialog dialog) {
//                C0917n.this
                this.f3119a = dialog;
            }

            @Override
            public void onClick(View view) {
                this.f3119a.dismiss();
                Tab f1Var = new Tab(Page_Topic.this.mainActivity);
                Page_Topic x0Var = Page_Topic.this;
                f1Var.addPage(new Page_Profile(x0Var.mainActivity, x0Var.currentDocument.getInt(6).intValue(), 0));
                Page_Topic.this.tab.mainLayout.setCurrentTab(f1Var);
            }
        }

        class View$OnClickListenerC0927j implements View.OnClickListener {
            final Dialog f3121a;

            View$OnClickListenerC0927j(Dialog dialog) {
//                C0917n.this
                this.f3121a = dialog;
            }

            @Override
            public void onClick(View view) {
                this.f3121a.dismiss();
                Tab f1Var = new Tab(Page_Topic.this.mainActivity);
                Page_Topic x0Var = Page_Topic.this;
                f1Var.addPage(new Page_Profile(x0Var.mainActivity, x0Var.currentDocument.getInt(8).intValue(), 0));
                Page_Topic.this.tab.mainLayout.setCurrentTab(f1Var);
            }
        }

        class View$OnClickListenerC0928k implements View.OnClickListener {
            final BBDisplay f3123a;

            View$OnClickListenerC0928k(BBDisplay bBDisplay) {
//                C0917n.this
                this.f3123a = bBDisplay;
            }

            @Override
            public void onClick(View view) {
                view.setVisibility(8);
                this.f3123a.setBBString(BBString.getBBString("Загрузка истории...", null));
                DocumentManager.getResultRequest(new API.PostHistoryRequest(Page_Topic.this.topicId, this.f3123a));
            }
        }

        class RunnableC0929l implements Runnable {
            final boolean f3125a;

            RunnableC0929l(boolean z) {
//                C0917n.this
                this.f3125a = z;
            }

            @Override
            public void run() {
                Page_Topic x0Var = Page_Topic.this;
                int i = x0Var.f3017P | 8;
                x0Var.f3017P = i;
                if (!this.f3125a) {
                    x0Var.f3017P = i ^ 8;
                }
            }
        }

        class RunnableC0930m implements Runnable {
            final boolean f3127a;

            RunnableC0930m(boolean z) {
//                C0917n.this
                this.f3127a = z;
            }

            @Override
            public void run() {
                Page_Topic x0Var = Page_Topic.this;
                int i = x0Var.f3017P | 4;
                x0Var.f3017P = i;
                if (!this.f3127a) {
                    x0Var.f3017P = i ^ 4;
                }
            }
        }

        class RunnableC0931n implements Runnable {
            final boolean f3129a;

            RunnableC0931n(boolean z) {
//                C0917n.this
                this.f3129a = z;
            }

            @Override
            public void run() {
                Page_Topic x0Var = Page_Topic.this;
                int i = x0Var.f3017P | 2;
                x0Var.f3017P = i;
                if (!this.f3129a) {
                    x0Var.f3017P = i ^ 2;
                }
            }
        }

        class C0932o implements TextWatcher {
            final DlgSimple f3131a;

            C0932o(C0917n nVar, DlgSimple q1Var) {
                this.f3131a = q1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                this.f3131a.m625a(editable.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        C0917n() {
//            Page_Topic.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            boolean z = false;
            boolean z2 = true;
            if (i3 == 6) {
                Dialog dialog = new Dialog(Page_Topic.this.mainActivity, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.dlg_topicinfo);
                dialog.getWindow().setBackgroundDrawable(Page_Topic.this.mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
                dialog.getWindow().setLayout(-1, -2);
                dialog.setCanceledOnTouchOutside(true);
                ((TextView) dialog.findViewById(R.id.topicName)).setText(Page_Topic.this.title);
                ((TextView) dialog.findViewById(R.id.topicDesc)).setText(Util.C0427h.UnEscapeString(Page_Topic.this.currentDocument.getString(3)));
                dialog.findViewById(R.id.topicShare).setOnClickListener(new View$OnClickListenerC0924g());
                TextView textView = dialog.findViewById(R.id.topicLink);
                textView.setPaintFlags(textView.getPaintFlags() | 8);
                textView.setText("https://4pda.ru/" + Page_Topic.this.getLink());
                textView.setOnClickListener(new View$OnClickListenerC0925h());
                TextView textView2 = dialog.findViewById(R.id.topicAuthor);
                textView2.setText(Util.C0427h.UnEscapeString(Page_Topic.this.currentDocument.getString(7)));
                textView2.setClickable(true);
                textView2.setOnClickListener(new View$OnClickListenerC0926i(dialog));
                ((TextView) dialog.findViewById(R.id.topicCreated)).setText(Util.formatDate(Page_Topic.this.currentDocument.getInt(5).intValue()));
                TextView textView3 = dialog.findViewById(R.id.topicCurator);
                String n = Page_Topic.this.currentDocument.getString(9);
                if (!TextUtils.isEmpty(n)) {
                    textView3.setText(Util.C0427h.UnEscapeString(n));
                    textView3.setOnClickListener(new View$OnClickListenerC0927j(dialog));
                } else {
                    textView3.setText("нет");
                }
                if ((Page_Topic.this.f3017P & 4) == 0) {
                    z = true;
                }
                ((TextView) dialog.findViewById(R.id.topicStatus)).setText(z ? "открыта" : "закрыта");
                BBDisplay bBDisplay = dialog.findViewById(R.id.topic_info_sessions);
                Util.C0423e eVar = new Util.C0423e(Page_Topic.this, dialog);
                bBDisplay.setCallback(eVar);
                DocumentManager.getResultRequest(new TopicInfoSesionsRequest(bBDisplay));
                if ((Page_Topic.this.f3017P & 512) != 0) {
                    BBDisplay bBDisplay2 = dialog.findViewById(R.id.topic_info_history);
                    bBDisplay2.setCallback(eVar);
                    dialog.findViewById(R.id.topic_info_load_history).setOnClickListener(new View$OnClickListenerC0928k(bBDisplay2));
                } else {
                    dialog.findViewById(R.id.topic_info_load_history).setVisibility(8);
                }
                dialog.show();
                CustomDialog.m623c(dialog);
            } else if (i3 == 21) {
                Page_Topic.this.tabReload();
            } else {
                Document uVar = null;
                if (i3 == 22) {
                    Page_Topic.this.m128f0().m196y(Page_Topic.this.m110x0(null), Page_Topic.this);
                } else if (i3 == 25) {
                    if (Page_Topic.this.f3034g0 != null) {
                        Page_Topic.this.m112v0().m181E(Page_Topic.this);
                    } else {
                        DocumentManager.getResultRequest(new CreateTopicRequest());
                    }
                } else if (i3 == 1 || i3 == 2) {
                    if (i3 != 1) {
                        z2 = false;
                    }
                    Page_Topic x0Var = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(x0Var.topicId, null, 11, 8, z2 ? 8 : 0, x0Var, z2 ? "подписка на тему" : "отписка от темы", "", new RunnableC0929l(z2));
                } else if (i3 == 13 || i3 == 14) {
                    if (i3 != 14) {
                        z2 = false;
                    }
                    Page_Topic x0Var2 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(x0Var2.topicId, null, 11, 4, z2 ? 4 : 0, x0Var2, z2 ? "закрытие темы" : "открытие темы", "СОГЛАСЕН", new RunnableC0930m(z2));
                } else if (i3 == 16 || i3 == 15) {
                    if (i3 != 16) {
                        z2 = false;
                    }
                    Page_Topic x0Var3 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(x0Var3.topicId, null, 11, 2, z2 ? 2 : 0, x0Var3, z2 ? "скрытие темы" : "отображение темы", "СОГЛАСЕН", new RunnableC0931n(z2));
                } else if (i3 == 4) {
                    Page_Topic x0Var4 = Page_Topic.this;
                    API.ForumModifyRequest.modifyForum(x0Var4.topicId, null, 14, 0, 0, x0Var4, "удаление темы", "УДАЛИТЬ", null);
                } else if (i3 == 17) {
                    DlgSimple q1Var = new DlgSimple(Page_Topic.this.mainActivity, "Введите ссылку на форум", false, null, null);
                    q1Var.checkboxTextView.setText("Оставить ссылку");
                    q1Var.checkboxTextView.setChecked(true);
                    q1Var.checkboxTextView.setVisibility(0);
                    q1Var.m625a(false);
                    q1Var.editText.addTextChangedListener(new C0932o(this, q1Var));
                    q1Var.m620f(new View$OnClickListenerC0918a(q1Var), true);
                    q1Var.show(true, true, true);
                } else if (i3 == 7) {
                    Tab f1Var = new Tab(Page_Topic.this.mainActivity);
                    Page_Topic x0Var5 = Page_Topic.this;
                    f1Var.addPage(new Page_Ticket(x0Var5.mainActivity, 0, x0Var5.topicId, x0Var5.title));
                    Page_Topic.this.tab.mainLayout.setCurrentTab(f1Var);
                } else if (i3 == 5) {
                    Util.copyToClipboard(Page_Topic.this.mainActivity, "https://4pda.ru/" + Page_Topic.this.getLink(), "Ссылка скопирована");
                } else if (i3 == 3) {
                    Page_Topic x0Var6 = Page_Topic.this;
                    MainActivity mainActivity = x0Var6.mainActivity;
                    int topicId = x0Var6.topicId;
                    String c = Util.C0427h.UnEscapeString(x0Var6.currentDocument.getString(2));
                    String c2 = Util.C0427h.UnEscapeString(Page_Topic.this.currentDocument.getString(3));
                    Page_Topic x0Var7 = Page_Topic.this;
                    boolean z3 = (x0Var7.f3017P & 512) > 0;
                    Document uVar2 = x0Var7.f3021T;
                    boolean z4 = uVar2 != null;
                    String n2 = uVar2 != null ? uVar2.getString(0) : null;
                    Document uVar3 = Page_Topic.this.f3021T;
                    if (uVar3 != null) {
                        uVar = uVar3.getDocument(3);
                    }
                    new DlgEditTopic(mainActivity, topicId, c, c2, z3, true, z4, n2, uVar).show(true, true, true);
                } else if (i3 == 23) {
                    String u = Page_Topic.this.getLink();
                    String z0 = Page_Topic.this.m106z0();
                    String str = Page_Topic.this.title + " (стр. " + Page_Topic.this.mo140Q() + ")";
                    boolean k = DataDB.m366k(u);
                    boolean k2 = DataDB.m366k(z0);
                    if (!k && !k2) {
                        DlgSimple q1Var2 = new DlgSimple(Page_Topic.this.mainActivity, "Тему в закладки", false, "СОЗДАТЬ", null);
                        q1Var2.editText.setText(str);
                        q1Var2.checkboxTextView.setText("Переходить к непрочитанным");
                        q1Var2.checkboxTextView.setVisibility(0);
                        q1Var2.checkboxTextView.setOnClickListener(new View$OnClickListenerC0919b(q1Var2, str));
                        q1Var2.m620f(new View$OnClickListenerC0920c(this, q1Var2, z0, u), true);
                        q1Var2.show(true, true, true);
                    } else if (k) {
                        DataDB.m367j(u);
                    } else {
                        DataDB.m367j(z0);
                    }
                } else if (i3 == 18) {
                    OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Topic.this.mainActivity, new C0921d());
                    o1Var.addMenuItem(0, 0, 0, "Кроме удаленных", false, false, true, Page_Topic.this.f3008G == 0);
                    o1Var.addMenuItem(0, 0, 4, "Только обычные", false, false, true, Page_Topic.this.f3008G == 4);
                    o1Var.addMenuItem(0, 0, 1, "Только скрытые", false, false, true, Page_Topic.this.f3008G == 1);
                    o1Var.addMenuItem(0, 0, 2, "Только удаленные", false, false, true, Page_Topic.this.f3008G == 2);
                    o1Var.addMenuItem(0, 0, 3, "Все посты", false, false, true, Page_Topic.this.f3008G == 3);
                    o1Var.show(null);
                } else if (i3 == 19) {
                    DlgMemberEditor p1Var = new DlgMemberEditor(Page_Topic.this.mainActivity, "Укажите куратора", null, null);
                    int intValue = Page_Topic.this.currentDocument.getInt(8).intValue();
                    if (intValue > 0) {
                        String c3 = Util.C0427h.UnEscapeString(Page_Topic.this.currentDocument.getString(9));
                        p1Var.f2451k.setData(intValue, c3, true);
                        p1Var.f2451k.setSelection(0, c3.length());
                    }
                    p1Var.m620f(new View$OnClickListenerC0922e(p1Var), true);
                    p1Var.show(true, true, true);
                } else if (20 == i3) {
                    OptionPoupupMenuView o1Var2 = new OptionPoupupMenuView(Page_Topic.this.mainActivity, new C0923f(), true);
                    Page_Topic x0Var8 = Page_Topic.this;
                    if ((x0Var8.f3017P & 512) > 0) {
                        boolean z5 = false;
                        boolean z6 = false;
                        boolean z7 = false;
                        boolean z8 = false;
                        boolean z9 = false;
                        for (int i5 = 0; i5 < Page_Topic.this.f3023V.size(); i5++) {
                            z6 = z6 || (Page_Topic.this.f3023V.valueAt(i5).f3043c & 2) == 0;
                            z7 = z7 || (Page_Topic.this.f3023V.valueAt(i5).f3043c & 2) != 0;
                            z8 = z8 || (Page_Topic.this.f3023V.valueAt(i5).f3043c & 2048) == 0;
                            z9 = z9 || (Page_Topic.this.f3023V.valueAt(i5).f3043c & 2048) != 0;
                            z5 = z5 || (Page_Topic.this.f3023V.valueAt(i5).f3043c & 4) > 0;
                        }
                        o1Var2.addMenuItem(0, 0, 12, "Удалить", false, true);
                        if (z5) {
                            o1Var2.addMenuItem(0, 0, 35, "Восстановить", false, true);
                        }
                        if (z6) {
                            o1Var2.addMenuItem(0, 0, 15, "Скрыть", false, true);
                        }
                        if (z7) {
                            o1Var2.addMenuItem(0, 0, 14, "Показать", false, true);
                        }
                        if (z8) {
                            o1Var2.addMenuItem(0, 0, 38, "Защитить", false, true);
                        }
                        if (z9) {
                            o1Var2.addMenuItem(0, 0, 39, "Снять защиту", false, true);
                        }
                        o1Var2.addMenuItem(0, 0, 6, "Переместить", false, true);
                        if (Page_Topic.this.f3023V.size() > 1) {
                            o1Var2.addMenuItem(0, 0, 13, "Объединить", false, true);
                        }
                    } else if (x0Var8.f3018Q) {
                        o1Var2.addMenuItem(0, 0, 12, "Удалить", false, true);
                        o1Var2.addMenuItem(0, 0, 6, "Переместить", false, true);
                        if (Page_Topic.this.f3023V.size() > 1) {
                            o1Var2.addMenuItem(0, 0, 13, "Объединить", false, true);
                        }
                    }
                    o1Var2.show(null);
                }
            }
        }
    }

    class View$OnClickListenerC0933o implements View.OnClickListener {
        View$OnClickListenerC0933o() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            Page_Topic x0Var = Page_Topic.this;
            x0Var.f3016O = false;
            x0Var.m131d0();
            Page_Topic.this.tabLoaded(true);
        }
    }

    class View$OnClickListenerC0934p implements View.OnClickListener {
        View$OnClickListenerC0934p() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            int count = Page_Topic.this.getCount();
            Page_Topic x0Var = Page_Topic.this;
            x0Var.f3015N = false;
            if (count != x0Var.getCount()) {
                Page_Topic.this.m131d0();
            }
            Page_Topic.this.tabLoaded(true);
        }
    }

    class View$OnLongClickListenerC0935q implements View.OnLongClickListener {
        View$OnLongClickListenerC0935q() {
//            Page_Topic.this
        }

        @Override
        public boolean onLongClick(View view) {
            BBDisplay bBDisplay = view.findViewById(R.id.PostCode);
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
            Page_Topic.this.showBBOptionMenu(bBDisplay, bBDisplay.f527d, new BBDisplay.C0143c());
            return true;
        }
    }

    class View$OnClickListenerC0936r implements View.OnClickListener {
        View$OnClickListenerC0936r() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            PostModel yVar = (PostModel) ((ViewGroup) view.getParent()).getTag();
            Page_Topic x0Var = Page_Topic.this;
            x0Var.m111w0("[SNAPBACK]" + yVar.postId + "[/SNAPBACK] [B]" + yVar.f3150c.replace("[", "&#91;").replace("]", "&#93;") + "[/B], ");
        }
    }

    class View$OnLongClickListenerC0937s implements View.OnLongClickListener {
        View$OnLongClickListenerC0937s() {
//            Page_Topic.this
        }

        @Override
        public boolean onLongClick(View view) {
            Util.copyToClipboard(Page_Topic.this.mainActivity, ((TextView) view).getText().toString(), "Ник скопирован в буфер обмена");
            return true;
        }
    }

    class View$OnClickListenerC0938t implements View.OnClickListener {
        View$OnClickListenerC0938t() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            Page_Topic x0Var = Page_Topic.this;
            x0Var.tab.addPage(new Page_Reputation(x0Var.mainActivity, ((PostModel) ((ViewGroup) view.getParent()).getTag()).f3149b));
        }
    }

    class View$OnClickListenerC0939u implements View.OnClickListener {
        View$OnClickListenerC0939u() {
//            Page_Topic.this
        }

        @Override
        public void onClick(View view) {
            boolean checked = ((Widgets$CheckboxView) view).getChecked();
            PostModel yVar = (PostModel) ((ViewGroup) view.getParent()).getTag();
            if (checked) {
                SparseArray<C0888b0> sparseArray = Page_Topic.this.f3023V;
                int i = yVar.postId;
                sparseArray.put(i, new C0888b0(i, yVar.f3156i, yVar.f3159l, yVar.f3149b, yVar.f3150c));
                return;
            }
            Page_Topic.this.f3023V.delete(yVar.postId);
        }
    }

    class View$OnLongClickListenerC0940v implements View.OnLongClickListener {
        View$OnLongClickListenerC0940v() {
//            Page_Topic.this
        }

        @Override
        public boolean onLongClick(View view) {
            boolean checked = ((Widgets$CheckboxView) view).getChecked();
            for (int i = 0; i < Page_Topic.this.f3010I.size(); i++) {
                PostModel yVar = Page_Topic.this.f3010I.get(i);
                int i2 = yVar.f3159l;
                if ((i2 & 1) <= 0) {
                    if (!checked) {
                        SparseArray<C0888b0> sparseArray = Page_Topic.this.f3023V;
                        int i3 = yVar.postId;
                        sparseArray.put(i3, new C0888b0(i3, yVar.f3156i, i2, yVar.f3149b, yVar.f3150c));
                    } else {
                        Page_Topic.this.f3023V.delete(yVar.postId);
                    }
                }
            }
            for (int i4 = 0; i4 < Page_Topic.this.tab.forumsListView.getChildCount(); i4++) {
                View childAt = Page_Topic.this.tab.forumsListView.getChildAt(i4);
                Widgets$CheckboxView widgets$CheckboxView = childAt.findViewById(R.id.postCheckbox);
                if (widgets$CheckboxView != null && (((PostModel) childAt.getTag()).f3159l & 1) == 0) {
                    widgets$CheckboxView.setChecked(!checked);
                }
            }
            return true;
        }
    }

    class ChangePostKarmaRequest extends API.ForumKarmaRequest {
        Page_Topic f3140i;

        ChangePostKarmaRequest(Page_Topic x0Var, PostModel yVar, int i) {
            super(yVar.postId, i);
//            Page_Topic.this
            this.f3140i = x0Var;
        }

        @Override
        public void prepareResult(int status, Document document) {
            if (!Page_Topic.this.isLoading) {
                if (status == 0) {
                    this.f3140i.tabReload();
                    Toast.makeText(this.f3140i.mainActivity, "Голос засчитан.", 0).show();
                    return;
                }
                Toast.makeText(this.f3140i.mainActivity, "Ошибка изменения репутации!", 0).show();
            }
        }
    }

    private class LoadKarmaHistoryRequest extends API.ForumKarmaRequest {
        LoadKarmaHistoryRequest(int postId) {
            super(postId, 0);
//            Page_Topic.this
            this.statusMessage = "Загрузка истории кармы";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Topic x0Var = Page_Topic.this;
            if (!x0Var.isLoading) {
                if (status == 0) {
                    Document l = uVar.getDocument(0);
                    if (l.count() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int i2 = 0; i2 < l.count(); i2++) {
                            Document l2 = l.getDocument(i2);
                            sb.append(Util.formatDate(l2.getInt(0).intValue()));
                            sb.append(" [color=");
                            sb.append(l2.getInt(3).intValue() > 0 ? "green]+1" : "red]-1");
                            sb.append("[/color] [url=https://4pda.ru/forum/index.php?showuser=");
                            sb.append(l2.getInt(1));
                            sb.append("]");
                            sb.append(l2.getString(2).replace("[", "&#91;").replace("]", "&#93;"));
                            sb.append("[/url]\n");
                        }
                        BBString x = BBString.getBBString("[size=4]История кармы[/size]\n\n" + sb.toString(), null);
                        ScrollView scrollView = new ScrollView(Page_Topic.this.mainActivity);
                        BBDisplay bBDisplay = new BBDisplay(Page_Topic.this.mainActivity);
                        scrollView.addView(bBDisplay);
                        bBDisplay.setBBString(x);
                        Dialog dialog = new Dialog(Page_Topic.this.mainActivity, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
                        bBDisplay.setCallback(new Util.C0423e(Page_Topic.this, dialog));
                        dialog.requestWindowFeature(1);
                        dialog.setContentView(scrollView);
                        dialog.getWindow().setBackgroundDrawable(Page_Topic.this.mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
                        dialog.getWindow().setLayout(-1, -2);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                        CustomDialog.m623c(dialog);
                        return;
                    }
                    Toast.makeText(Page_Topic.this.mainActivity, "Никто не голосовал за этот пост", 1).show();
                    return;
                }
                Toast.makeText(x0Var.mainActivity, "Действие завершилось ошибкой", 0).show();
            }
        }
    }

    public static class PostModel {
        public static int f3143A;
        public static int f3144w;
        public static int f3145x;
        public static float f3146y;
        public static int f3147z;
        public int postId;
        public int f3149b;
        public String f3150c;
        public int f3151d;
        public int f3152e;
        public String f3153f;
        public String f3154g;
        public int f3155h;
        int f3156i;
        public String f3157j;
        public String f3158k;
        public int f3159l;
        public String f3160m;
        public boolean f3161n;
        public BBString f3162o;
        public String f3163p;
        public List<ImageDialog.C0174m> f3164q;
        public SpannableString f3165r;
        public int f3166s;
        public int f3167t;
        public int f3168u;
        public boolean f3169v;

        PostModel() {
        }

        public static PostModel m99a(Document uVar) {
            return m98b(uVar, null);
        }

        public static PostModel m98b(Document document, SparseArray<SparseBooleanArray> sparseArray) {
            PostModel yVar = new PostModel();
            String n = document.getString(8);
            if (n == null) {
                return null;
            }
            BBString.C0670a[] c = m97c(document.getDocument(11));
            yVar.postId = document.getInt(0);
            yVar.f3149b = document.getInt(2);
            yVar.f3150c = Util.C0427h.UnEscapeString(document.getString(3));
            int intValue = document.getInt(4);
            yVar.f3151d = intValue;
            yVar.f3152e = Util.C0424f.m646c(Skin.SkinColorModel.f1398n0[intValue], Skin.SkinColorModel.f1370Z);
            yVar.f3153f = document.getInt(6).toString();
            yVar.f3154g = document.getString(9);
            yVar.f3155h = document.getInt(5);
            int intValue2 = document.getInt(7);
            yVar.f3156i = intValue2;
            yVar.f3157j = Util.formatDate(intValue2);
            yVar.f3158k = Util.formatDate(yVar.f3156i, false, true);
            yVar.f3159l = document.getInt(1);
            int intValue3 = document.getInt(12);
            boolean z = (intValue3 & 1) > 0;
            yVar.f3161n = z;
            if (z) {
                int i = intValue3 >> 3;
                String str = "";
                if (i > 0) {
                    str = "+" + i;
                } else if (i < 0) {
                    str = str + i;
                }
                yVar.f3160m = str;
                if ((intValue3 & 2) > 0) {
                    yVar.f3161n = false;
                    int i2 = 4 & intValue3;
                    if (i == 0) {
                        yVar.f3160m = "0";
                    }
                }
            }
            String str2 = n + "[break][NA=1]";
            Integer m = document.getInt(13);
            if (m != null) {
                String replace = Util.C0427h.UnEscapeString(document.getString(14)).replace("[", "&#91;").replace("]", "&#93;");
                int intValue4 = document.getInt(16).intValue();
                if (intValue4 != 0) {
                    replace = "[url=https://4pda.ru/forum/index.php?showuser=" + intValue4 + "]" + replace + "[/url]";
                }
                String str3 = "[left][sub][color=label_text]\n\nОтредактировал [/color][b]" + replace + "[/b][color=gray] - " + Util.formatDate(m.intValue());
                if ((yVar.f3159l & 16) == 0) {
                    str3 = str3 + " [color=red](Скрыто)[/color]";
                }
                String n2 = document.getString(15);
                if (n2 != null && !TextUtils.isEmpty(n2)) {
                    yVar.f3163p = n2;
                    str3 = str3 + "\nПричина: " + n2.replace("[", "&#91;").replace("]", "&#93;");
                }
                str2 = str2 + str3 + "[/color][/sub][/left]";
            }
            String n3 = document.getString(10);
            if (Prefs.postShowSign && !TextUtils.isEmpty(n3)) {
                str2 = str2 + "[left][size=1][color=border_line][seporator][/color]\n[/size][/left][size=1]" + n3 + "[/size]";
            }
            DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
            if (L == null || L.memberId <= 0) {
                BBString.f2168L0 = -1;
            } else if ((yVar.f3159l & 128) != 0) {
                BBString.f2168L0 = Integer.MAX_VALUE;
            } else {
                BBString.f2168L0 = L.memberPostsCount;
            }
            SparseBooleanArray sparseBooleanArray = sparseArray != null ? sparseArray.get(yVar.postId) : null;
            BBString w = BBString.getBBString(str2.toCharArray(), c, sparseBooleanArray);
            yVar.f3162o = w;
            w.f2221a0 = yVar;
            if (sparseArray != null && sparseBooleanArray == null) {
                sparseArray.put(yVar.postId, w.f2232l);
            }
            for (BBString.C0681k kVar : yVar.f3162o.f2202I) {
                if (kVar.link.startsWith("#")) {
                    kVar.link = "https://4pda.ru/forum/index.php?act=findpost&pid=" + yVar.postId + "&anchor=" + kVar.link.substring(1);
                }
            }
            if (c != null) {
                boolean z2 = false;
                for (int i3 = 1; i3 <= c.length + 1; i3++) {
                    for (int i4 = 0; i4 < c.length; i4++) {
                        if (c[i4].f2249c && c[i4].f2253g != 0 && c[i4].f2248b == 0) {
                            z2 = true;
                        }
                        if (c[i4].f2249c && c[i4].f2253g != 0 && c[i4].f2248b == i3) {
                            if (yVar.f3164q == null) {
                                yVar.f3164q = new Vector(c.length);
                            }
                            yVar.f3164q.add(new ImageDialog.C0174m(c[i4].f2247a, null, c[i4].f2254h));
                        }
                    }
                }
                if (z2) {
                    for (int i5 = 0; i5 < c.length; i5++) {
                        if (c[i5].f2249c && c[i5].f2253g != 0 && c[i5].f2248b == 0) {
                            if (yVar.f3164q == null) {
                                yVar.f3164q = new Vector(c.length);
                            }
                            yVar.f3164q.add(new ImageDialog.C0174m(c[i5].f2247a, null, c[i5].f2254h));
                        }
                    }
                }
            }
            return yVar;
        }

        public static BBString.C0670a[] m97c(Document uVar) {
            if (uVar == null || uVar.count() <= 0) {
                return null;
            }
            int d = uVar.count();
            BBString.C0670a[] aVarArr = new BBString.C0670a[d];
            for (int i = 0; i < d; i++) {
                Document l = uVar.getDocument(i);
                aVarArr[i] = new BBString.C0670a();
                aVarArr[i].f2247a = l.getInt(0).intValue();
                aVarArr[i].f2253g = l.getInt(3).intValue();
                BBString.C0670a aVar = aVarArr[i];
                boolean z = true;
                if (l.getInt(1).intValue() <= 0) {
                    z = false;
                }
                aVar.f2249c = z;
                if (z) {
                    aVarArr[i].f2250d = l.getString(4);
                    aVarArr[i].f2254h = l.getString(2);
                    aVarArr[i].f2251e = l.getInt(5).intValue();
                    aVarArr[i].f2252f = l.getInt(6).intValue();
                } else {
                    aVarArr[i].f2250d = l.getString(2);
                    aVarArr[i].f2254h = aVarArr[i].f2250d;
                    aVarArr[i].f2256j = -1;
                    if (l.count() > 4 && l.getInt(4) != null) {
                        aVarArr[i].f2256j = l.getInt(4).intValue();
                    }
                }
                aVarArr[i].f2248b = 0;
            }
            return aVarArr;
        }
    }

    public class SendPostRequest extends API.ForumPostRequest {

        class View$OnClickListenerC0945a implements View.OnClickListener {
            View$OnClickListenerC0945a() {
//                SendPostRequest.this
            }

            @Override
            public void onClick(View view) {
                SendPostRequest zVar = SendPostRequest.this;
                zVar.postFlags |= 1;
                DocumentManager.getResultRequest(zVar);
            }
        }

        class View$OnClickListenerC0946b implements View.OnClickListener {
            View$OnClickListenerC0946b() {
//                SendPostRequest.this
            }

            @Override
            public void onClick(View view) {
                SendPostRequest zVar = SendPostRequest.this;
                zVar.postFlags |= 3;
                DocumentManager.getResultRequest(zVar);
            }
        }

        SendPostRequest(int topicId, int postId, String postMessage, int[] attaches, String reason, boolean showMark, boolean hidePost) {
            super(topicId, postId, postMessage, attaches, reason);
            if (showMark) {
                this.postFlags |= 4;
            }
            if (hidePost) {
                this.postFlags |= 8;
            }
            this.statusMessage = "Отправка поста";
        }

        @Override
        public void prepareResult(int status, Document document) {
            String message;
            Page_Topic pageTopic = Page_Topic.this;
            if (!pageTopic.isLoading) {
                if (status == 0) {
                    pageTopic.f3028a0.remove(this.postId);
                    if (this.postId == 0 && (this.postFlags & 1) != 0) {
                        Page_Topic.this.f3028a0.remove(document.getInt(2));
                    }
                    Page_Topic x0Var2 = Page_Topic.this;
                    if (x0Var2.f3008G <= 0) {
                        int intValue = document.getInt(1);
                        int i2 = Prefs.memberPostsPerPage;
                        int topicPage = (intValue / i2) * i2;
                        int intValue2 = document.getInt(0);
                        Page_Topic x0Var3 = Page_Topic.this;
                        if (intValue2 == x0Var3.topicId && topicPage == x0Var3.topicPage) {
                            x0Var3.f3012K = document.getInt(2).intValue();
                            Page_Topic.this.tabReload();
                        } else {
                            x0Var3.tab.addPage(new Page_Topic(Page_Topic.this.mainActivity, document.getInt(0).intValue(), topicPage, 0, document.getInt(2).intValue(), null, Page_Topic.this.title));
                        }
                    } else if (this.postId != 0) {
                        x0Var2.f3012K = document.getInt(2).intValue();
                        Page_Topic.this.tabReload();
                    } else {
                        API.ForumTopicPostRequest postReqest = new API.ForumTopicPostRequest(x0Var2.mainActivity, 3, document.getInt(2).intValue());
                        postReqest.m825t(document.getInt(0).intValue());
                        postReqest.m823v(Page_Topic.this.title);
                        postReqest.m824u(Page_Topic.this.tab);
                        postReqest.m822w(Page_Topic.this.f3008G);
                        postReqest.m827r(true);
                        DocumentManager.getResultRequest(postReqest);
                    }
                    message = "Пост отправлен";
                } else if (status == 4) {
                    pageTopic.f3028a0.remove(this.postId);
                    message = "Пост отправлен на премодерацию";
                } else if (status == 5) {
                    message = "Слишком много букв";
                } else if (status == 6) {
                    message = "Этот пост уже был отправлен";
                } else if (status == 7) {
                    DlgSimple q1Var = new DlgSimple(Page_Topic.this.mainActivity, "Прикрепить этот пост к предыдущему?", false, "ДА", "НЕТ");
                    q1Var.editText.setVisibility(8);
                    q1Var.m620f(new View$OnClickListenerC0945a(), true);
                    q1Var.m621e(new View$OnClickListenerC0946b(), true);
                    q1Var.show(true, true, true);
                    message = null;
                } else {
                    message = "Ошибка отправки поста";
                }
                if (message != null) {
                    Toast.makeText(Page_Topic.this.mainActivity, message, 0).show();
                }
            }
        }
    }

    public Page_Topic(MainActivity mainActivity, int topicId, int i2) {
        this(mainActivity, topicId, i2, Prefs.showAllPost ? 3 : 0, 0, null, "");
    }

    private void m146A0(int topicId, int topicPage, int i3) {
        this.topicId = topicId;
        this.topicPage = topicPage;
        this.f3008G = i3;
        this.iconId = R.drawable.ic_nav_forum;
        this.title = String.format("тема %d: %d(%d)", topicId, topicPage, Prefs.memberPostsPerPage);
        this.statusMessage = "Загрузка темы " + this.topicId;
        if (this.topicPage == 0) {
            this.f3015N = false;
        } else {
            this.f3015N = Prefs.topicHideHeader;
        }
        this.f3016O = Prefs.topicHidePoll;
        SparseArray<C0888b0> sparseArray = f3004k0.get(this.topicId);
        this.f3023V = sparseArray;
        if (sparseArray == null) {
            SparseArray<C0888b0> sparseArray2 = new SparseArray<>();
            this.f3023V = sparseArray2;
            f3004k0.put(topicId, sparseArray2);
        }
        this.f3014M = new SparseArray<>();
    }

    public static void movePosts(Page a0Var, SparseArray<C0888b0> posts, int i) {
        DlgSimple q1Var = new DlgSimple(a0Var.mainActivity, "Введите ссылку на тему", false, "ПЕРЕМЕСТИТЬ", null);
        DocumentManager.getResultRequest(new C0890c0(a0Var.mainActivity, q1Var));
        q1Var.m625a(false);
        q1Var.editText.addTextChangedListener(new C0913j(q1Var));
        q1Var.m620f(new View$OnClickListenerC0915l(q1Var, a0Var, i, posts), true);
        q1Var.show(true, true, true);
    }

    public Form_Wizard m112v0() {
        if (this.f3035h0 == null) {
            this.f3035h0 = new Form_Wizard(this.mainActivity, 0, this.topicId, this.f3033f0, this.f3034g0, new Util.C0423e(this, null));
        }
        return this.f3035h0;
    }

    public void m111w0(String str) {
        Form_Post.ForumPostModel x0 = m110x0(null);
        x0.postMessage += str;
        m128f0().m196y(x0, this);
    }

    public Form_Post.ForumPostModel m110x0(PostModel yVar) {
        StringBuilder sb;
        if (this.f3028a0 == null) {
            this.f3028a0 = new SparseArray<>();
        }
        int i = yVar != null ? yVar.postId : 0;
        Form_Post.ForumPostModel kVar = this.f3028a0.get(i);
        if (kVar != null) {
            return kVar;
        }
        boolean z = (this.f3017P & 512) != 0;
        if (i > 0) {
            sb = new StringBuilder();
            sb.append("Редактирование поста ");
            sb.append(i);
        } else {
            sb = new StringBuilder();
            sb.append("Ответ: ");
            sb.append(this.title);
        }
        String sb2 = sb.toString();
        int i2 = this.topicId;
        boolean z2 = this.f3018Q;
        boolean z3 = i > 0;
        boolean z4 = z && i == 0;
        String str = "";
        String str2 = yVar != null ? yVar.f3163p : str;
        if (yVar != null) {
            str = yVar.f3162o.m481h();
        }
        Form_Post.ForumPostModel kVar2 = new Form_Post.ForumPostModel(0, sb2, i2, i, z2, z, z3, true, z4, str2, str, yVar != null ? yVar.f3162o.m464y() : null);
        this.f3028a0.put(i, kVar2);
        return kVar2;
    }

    public String m108y0(int i, int i2) {
        String str = "forum/index.php?showtopic=" + this.topicId + "&view=findpost&p=" + i;
        if (i2 <= 0) {
            return str;
        }
        return str + "&anchor=Spoil-" + i + "-" + i2;
    }

    @Override
    public void mo147A(Bundle bundle, String str) {
        super.mo147A(bundle, str);
        if (bundle.getBoolean(str + "_ec")) {
            this.f3029b0 = new Form_Post.ForumPostModel(bundle, str + "_ec");
        }
        int i = bundle.getInt(str + "_ps");
        if (i > 0) {
            this.f3028a0 = new SparseArray<>(i);
            for (int i2 = 0; i2 < i; i2++) {
                Form_Post.ForumPostModel kVar = new Form_Post.ForumPostModel(bundle, str + "_ps" + i2);
                this.f3028a0.put(kVar.postId, kVar);
            }
        }
        int i3 = bundle.getInt(str + "_ea", -2);
        if (i3 == -1) {
            this.f3030c0 = this.f3029b0;
        } else if (i3 >= 0) {
            this.f3030c0 = this.f3028a0.get(i3);
        }
        if (bundle.getBoolean(str + "_ws")) {
            this.f3033f0 = bundle.getString(str + "_wt");
            this.f3034g0 = new Form_Wizard.C0883n(bundle, str + "_ws");
            this.f3036i0 = bundle.getBoolean(str + "_wo");
        }
    }

    @Override
    public boolean mo145B() {
        Form_Post wVar = this.f3031d0;
        if (wVar == null || !wVar.m201t()) {
            Form_Wizard xVar = this.f3035h0;
            if (xVar != null) {
                if (!xVar.m162r()) {
                    this.f3035h0.m184B();
                    this.f3035h0 = null;
                }
                return true;
            } else if (this.f3022U.size() <= 0) {
                return false;
            } else {
                m812X(this.f3022U.pop().intValue(), this.f3022U.pop().intValue());
                return true;
            }
        } else {
            if (!this.f3031d0.m205p()) {
                this.f3031d0.m202s();
            }
            return true;
        }
    }

    @Override
    public void onSearchBar() {
        Form_Post wVar = this.f3031d0;
        if (wVar == null || !wVar.m201t()) {
            Form_Wizard xVar = this.f3035h0;
            if (xVar != null) {
                xVar.m183C();
            }
        } else {
            this.f3031d0.m200u();
        }
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoadedForum() {
        return true;
    }

    @Override
    protected boolean onPageLoaded() {
        return m125i0();
    }

    @Override
    public void mo142J(boolean z) {
        super.mo142J(z);
        if (this.f3012K > 0) {
            int i = -1;
            List<PostModel> list = this.f3010I;
            int size = list != null ? list.size() : 0;
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (this.f3010I.get(i2).postId == this.f3012K) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i >= 0) {
                m813W(m126h0() + i);
                if (this.f3015N && i < this.f3011J) {
                    int count = getCount();
                    this.f3015N = false;
                    if (count != getCount()) {
                        m131d0();
                    }
                    tabLoaded(true);
                }
                this.f3012K = 0;
            }
        }
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        if (isUnsucces()) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, this.f3037j0);
            int i = this.f3017P;
            if ((i & 64) > 0) {
                if ((i & 16) == 0) {
                    o1Var.addMenuItem(0, 0, 22, "Написать пост");
                }
                if (!TextUtils.isEmpty(this.f3032e0)) {
                    o1Var.addMenuItem(0, 0, 25, this.f3032e0);
                }
            }
            if (Prefs.showReloadButton) {
                o1Var.addMenuItem(0, 0, 21, "Обновить");
            }
            o1Var.addMenuItem(0, 0, 23, "В закладки", DataDB.m366k(getLink()) || DataDB.m366k(m106z0()));
            if ((this.f3017P & 8) == 0) {
                o1Var.addMenuItem(0, 0, 1, "В избранное", false);
            } else {
                o1Var.addMenuItem(0, 0, 2, "В избранное", true);
            }
            o1Var.addMenuItem(0, 0, 6, "О теме");
            if ((this.f3017P & 128) > 0) {
                o1Var.addMenuItem(0, 0, 3, "Редактировать", false, true);
            }
            if ((this.f3017P & 256) > 0) {
                o1Var.addMenuItem(0, 0, 4, "Удалить тему", false, true);
            }
            if ((this.f3017P & 512) > 0) {
                o1Var.addMenuItem(0, 0, 18, "Фильтр постов", false, true);
            }
            int i2 = this.f3017P;
            if ((i2 & 512) > 0) {
                if ((i2 & 2) > 0) {
                    o1Var.addMenuItem(0, 0, 15, "Скрыть тему", false, true, true, true);
                } else {
                    o1Var.addMenuItem(0, 0, 16, "Скрыть тему", false, true, true, false);
                }
                if ((this.f3017P & 4) > 0) {
                    o1Var.addMenuItem(0, 0, 13, "Закрыть тему", false, true, true, true);
                } else {
                    o1Var.addMenuItem(0, 0, 14, "Закрыть тему", false, true, true, false);
                }
                o1Var.addMenuItem(0, 0, 17, "Переместить тему", false, true);
                o1Var.addMenuItem(0, 0, 7, "Тикеты темы", false, true);
                String str = "Куратор";
                String n = this.currentDocument.getString(9);
                if (!TextUtils.isEmpty(n)) {
                    str = str + " (" + Util.C0427h.UnEscapeString(n) + ")";
                }
                o1Var.addMenuItem(0, 0, 19, str, false, true);
            }
            if (this.f3023V.size() > 0) {
                o1Var.addMenuItem(0, 0, 20, String.format("Выбранные (%d)", this.f3023V.size()), false, true);
            }
            o1Var.show(view);
        } else if (Prefs.showReloadButton) {
            OptionPoupupMenuView o1Var2 = new OptionPoupupMenuView(this.mainActivity, this.f3037j0);
            o1Var2.addMenuItem(0, 0, 21, "Обновить");
            o1Var2.show(view);
        }
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f3024W) {
            tabReload();
        }
        if (z) {
            return;
        }
        if (this.f3030c0 != null) {
            m128f0().m196y(this.f3030c0, this);
            this.f3030c0 = null;
        } else if (this.f3036i0) {
            m112v0().m181E(this);
            this.f3036i0 = false;
        } else {
            Form_Post wVar = this.f3031d0;
            if (wVar == null || !wVar.m201t()) {
                Form_Wizard xVar = this.f3035h0;
                if (xVar != null) {
                    xVar.m182D();
                    return;
                }
                return;
            }
            this.f3031d0.m199v();
        }
    }

    @Override
    int mo141P() {
        int i = this.f3009H;
        int i2 = Prefs.memberPostsPerPage;
        int i3 = ((i + i2) - 1) / i2;
        return i3 == 0 ? i3 + 1 : i3;
    }

    @Override
    int mo140Q() {
        return (this.topicPage / Prefs.memberPostsPerPage) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_Topic(this.mainActivity, this.topicId, (i - 1) * Prefs.memberPostsPerPage, this.f3008G, 0, null, this.title);
    }

    @Override
    public void mo138Y(Bundle bundle, String str) {
        super.mo138Y(bundle, str);
        Form_Post wVar = this.f3031d0;
        if (wVar != null && wVar.m201t()) {
            this.f3031d0.m204q();
            Form_Post.ForumPostModel r = this.f3031d0.m203r();
            bundle.putInt(str + "_ea", r == this.f3029b0 ? -1 : r.postId);
        }
        Form_Post.ForumPostModel kVar = this.f3029b0;
        if (kVar != null) {
            kVar.m193b(bundle, str + "_ec");
        }
        SparseArray<Form_Post.ForumPostModel> sparseArray = this.f3028a0;
        if (sparseArray != null && sparseArray.size() > 0) {
            bundle.putInt(str + "_ps", this.f3028a0.size());
            for (int i = 0; i < this.f3028a0.size(); i++) {
                this.f3028a0.valueAt(i).m193b(bundle, str + "_ps" + i);
            }
        }
        Form_Wizard xVar = this.f3035h0;
        if (xVar != null) {
            xVar.m161s();
            bundle.putBoolean(str + "_wo", true);
            bundle.putString(str + "_wt", this.f3033f0);
        }
        if (this.f3034g0 != null) {
            bundle.putBoolean(str + "_ws", true);
            Form_Wizard.C0883n nVar = this.f3034g0;
            nVar.m148b(bundle, str + "_ws");
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        if (i > 0) {
            PostModel yVar = (PostModel) pVar.f2221a0;
            m111w0("[quote name=\"" + yVar.f3150c.replace("\\", "\\\\").replace("\"", "\\\"") + "\" date=\"" + yVar.f3158k.replace("  ", ", ") + "\" post=\"" + yVar.postId + "\"]" + str + "[/quote]");
            return;
        }
        Util.copyToClipboard(this.mainActivity, str, "Текст скопирован в буфер");
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        Unread2.f1568b.m654a(this.f3025X);
        DocumentManager.f2752I.m651a(this.f3026Y);
        return true;
    }

    @Override
    public void mo135b(Form_Post.ForumPostModel kVar, boolean z) {
        if (1 == kVar.status) {
            if (!TextUtils.isEmpty(kVar.postMessage)) {
                DocumentManager.getResultRequest(new API.ReportRequest(this.mainActivity, 0, kVar.postId, kVar.postMessage));
            }
            this.f3029b0 = null;
        } else if (z) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0916m(kVar));
            o1Var.addMenuItem(0, 0, 0, "Отправить");
            o1Var.addMenuItem(0, 0, 1, "Отправить и скрыть");
            o1Var.show(null);
        } else {
            DocumentManager.getResultRequest(new SendPostRequest(this.topicId, kVar.postId, kVar.postMessage, kVar.getPostAttaches(), kVar.reason, kVar.showMark, false));
        }
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        Unread2.f1568b.m653b(this.f3025X);
        DocumentManager.f2752I.m650b(this.f3026Y);
        return true;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
        int i2;
        if (!this.isLoading) {
            boolean T = m816T();
            int measuredWidth = this.mainActivity.mainLayout.getMeasuredWidth();
            int i3 = this.f1071B[T ? 1 : 0];
            int i4 = 0;
            while (true) {
                List<PostModel> list = this.f3010I;
                if (i4 >= (list != null ? list.size() : 0)) {
                    break;
                }
                if (!this.f3015N || i4 >= this.f3011J) {
                    i2 = PostModel.f3145x + Util.m663k(this.mainActivity, this.f3010I.get(i4).f3162o, measuredWidth) + PostModel.f3144w;
                } else {
                    i2 = f3005l0;
                }
                i3 += i2;
                this.f1071B[(T ? 1 : 0) + 1 + i4] = i3;
                i4++;
            }
            if (m816T()) {
                m815U();
            }
        }
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        OptionPoupupMenuView o1Var;
        int i;
        String str;
        int i2;
        OptionPoupupMenuView o1Var2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z2;
        String str2;
        String str3;
        PostModel yVar = (PostModel) pVar.f2221a0;
        OptionPoupupMenuView o1Var3 = new OptionPoupupMenuView(this.mainActivity, new C0906i(bBDisplay, pVar, cVar, yVar), true);
        int i7 = cVar.f543a;
        if (i7 >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i7);
            o1Var = o1Var3;
            o1Var3.addMenuItem(0, 0, 0, Util.C0427h.urlDecode(kVar.link), true, false);
            o1Var.addMenuItem(0, 0, 26, "Копировать ссылку");
            if (Urls2.is4pdaHost(kVar.link)) {
                o1Var.addMenuItem(0, 0, 21, "Открыть в новой вкладке");
            }
            o1Var.addMenuItem(0, 0, 30, "Открыть с помощью");
        } else {
            o1Var = o1Var3;
        }
        int i8 = cVar.f544b;
        if (i8 >= 0) {
            BBString.C0670a aVar = pVar.f2212S[i8];
            if (!aVar.f2249c) {
                o1Var.addMenuItem(0, 0, 0, "https://4pda.ru/forum/dl/post/" + aVar.f2247a + "/" + aVar.f2254h, true, false);
                o1Var.addMenuItem(0, 0, 26, "Копировать ссылку");
                o1Var.addMenuItem(0, 0, 31, "Загрузить файл");
            } else if (aVar.f2253g > 0) {
                o1Var.addMenuItem(0, 0, 22, "Открыть изображение");
            }
        }
        int i9 = cVar.f545c;
        if (i9 >= 0 && (TextUtils.isEmpty(pVar.f2208O[i9].f2279c) || pVar.f2208O[cVar.f545c].f2289m)) {
            cVar.f545c = -1;
        }
        int i10 = cVar.f545c;
        if (i10 >= 0) {
            if (bBDisplay.f531h[i10] == null) {
                o1Var.addMenuItem(0, 0, 24, "Загрузить изображение");
            } else {
                int i11 = cVar.f544b;
                if (i11 < 0 || pVar.f2212S[i11].f2253g <= 0) {
                    o1Var.addMenuItem(0, 0, 27, "Увеличить изображение");
                }
            }
            o1Var.addMenuItem(0, 0, 28, "Сохранить изображение");
        }
        int i12 = cVar.f546d;
        if (i12 >= 0) {
            int i13 = bBDisplay.m972i(i12);
            o1Var.addMenuItem(0, i13, 17, "Ссылка на спойлер");
            o1Var2 = o1Var;
            i2 = 0;
            str = "Копировать ссылку";
            i = 1;
            o1Var.addMenuItem(0, i13, 20, "Спойлер в закладки", DataDB.m366k(m108y0(yVar.postId, i13)));
            o1Var2.addMenuItem(0, 0, 32, "Открыть вложенные");
        } else {
            o1Var2 = o1Var;
            str = "Копировать ссылку";
            i2 = 0;
            i = 1;
        }
        if (bBDisplay.f528e.m948l()) {
            int i14 = this.f3017P;
            if ((i14 & 64) == 0 || (i14 & 16) != 0) {
                o1Var2.addMenuItem(i2, i2, 3, "Копировать");
            } else {
                o1Var2.addMenuItem(i2, i2, 2, "Копировать/Цитировать");
            }
        }
        if (cVar.f543a == -1 && cVar.f544b == -1 && cVar.f545c == -1 && cVar.f546d == -1) {
            DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
            int i15 = this.f3017P;
            if ((i15 & 64) != 0 && (i15 & 16) == 0) {
                o1Var2.addMenuItem(i2, i2, i2, "Ответить");
            }
            o1Var2.addMenuItem(i2, i2, 17, str);
            if (!(L == null || !yVar.f3161n || yVar.f3149b == L.memberId)) {
                o1Var2.addMenuItem(i2, i2, 19, "Оценить пост");
            }
            if (L != null) {
                o1Var2.addMenuItem(i2, i2, 4, "Жалоба");
            }
            if ((yVar.f3159l & 128) > 0) {
                o1Var2.addMenuItem(i2, i2, 5, "Редактировать");
            }
            o1Var2.addMenuItem(0, 0, 20, "В закладки", DataDB.m366k(m108y0(yVar.postId, i2)));
            int size = this.f3023V.size();
            if (this.f3023V.indexOfKey(yVar.postId) < 0) {
                size++;
            }
            if ((yVar.f3159l & 256) <= 0 || size > i) {
                z = false;
            } else {
                o1Var2.addMenuItem(i2, i2, 12, "Удалить");
                z = true;
            }
            if ((yVar.f3159l & 512) == 0) {
                o1Var2.addMenuItem(i2, i2, i, "Профиль");
            }
            if (!(L == null || yVar.f3149b == L.memberId)) {
                o1Var2.addMenuItem(i2, i2, 18, "Репутация (+/-)");
            }
            o1Var2.addMenuItem(i2, i2, 16, "Поиск постов " + yVar.f3150c);
            o1Var2.addMenuItem(i2, i2, 40, "Упоминания поста");
            if (z || (yVar.f3159l & 256) <= 0) {
                i6 = 4;
                i5 = 2;
                i4 = 1;
                i3 = 0;
            } else if (size > i) {
                i3 = 0;
                i6 = 4;
                i5 = 2;
                i4 = 1;
                o1Var2.addMenuItem(0, 0, 12, "Удалить (" + size + ")", false, true);
            } else {
                i6 = 4;
                i5 = 2;
                i4 = 1;
                i3 = 0;
                o1Var2.addMenuItem(0, 0, 12, "Удалить", false, true);
            }
            int i16 = yVar.f3159l;
            if ((i16 & 512) > 0) {
                boolean z3 = (i16 & 2) == 0;
                boolean z4 = (i16 & 2) > 0;
                boolean z5 = (i16 & i6) > 0;
                for (int i17 = 0; i17 < this.f3023V.size(); i17++) {
                    z3 = z3 || (this.f3023V.valueAt(i17).f3043c & i5) == 0;
                    z4 = z4 || (this.f3023V.valueAt(i17).f3043c & i5) > 0;
                    z5 = z5 || (this.f3023V.valueAt(i17).f3043c & i6) > 0;
                }
                if (z5) {
                    str2 = "Переместить (";
                    str3 = "Объединить (";
                    o1Var2.addMenuItem(0, 0, 35, size > i4 ? "Восстановить (" + size + ")" : "Восстановить", false, true);
                } else {
                    str2 = "Переместить (";
                    str3 = "Объединить (";
                }
                o1Var2.addMenuItem(0, 0, 34, "Наказать", false, true);
                if ((yVar.f3159l & i4) > 0) {
                    o1Var2.addMenuItem(0, 0, 8, "Закрепить пост", false, true, true, true);
                } else if ((this.f3010I.get(i3).f3159l & i4) == 0) {
                    o1Var2.addMenuItem(0, 0, 7, "Закрепить пост", false, true, true, false);
                }
                if (z3) {
                    o1Var2.addMenuItem(0, 0, 15, "Скрыть (" + size + ")", false, true);
                }
                if (z4) {
                    o1Var2.addMenuItem(0, 0, 14, "Показать (" + size + ")", false, true);
                }
                if ((yVar.f3159l & 2048) != 0) {
                    o1Var2.addMenuItem(0, 0, 39, "Защитить (" + size + ")", false, true, true, true);
                } else {
                    o1Var2.addMenuItem(0, 0, 38, "Защитить (" + size + ")", false, true, true, false);
                }
                o1Var2.addMenuItem(0, 0, 6, str2 + size + ")", false, true);
                if (size > i4) {
                    o1Var2.addMenuItem(0, 0, 13, str3 + size + ")", false, true);
                }
            } else if (this.f3018Q) {
                o1Var2.addMenuItem(0, 0, 6, "Переместить (" + size + ")", false, true);
                if (size > i4) {
                    o1Var2.addMenuItem(0, 0, 13, "Объединить (" + size + ")", false, true);
                }
            }
            PostModel yVar2 = this.f3010I.get(i3);
            if ((yVar2.f3159l & 129) == 129) {
                int i18 = 0;
                while (true) {
                    if (i18 >= yVar2.f3162o.f2202I.size()) {
                        z2 = false;
                        break;
                    }
                    String str4 = yVar2.f3162o.f2202I.get(i18).link;
                    if (str4 != null) {
                        if (str4.contains("=" + yVar.postId)) {
                            z2 = true;
                            break;
                        }
                    }
                    i18++;
                }
                if (z2) {
                    o1Var2.addMenuItem(0, 0, 37, "В шапку", false, true, true, true);
                } else {
                    o1Var2.addMenuItem(0, 0, 36, "В шапку", false, true, true, false);
                }
            }
        }
        o1Var2.show(null);
    }

    void m131d0() {
        int i;
        boolean T = m816T();
        int i2 = 1;
        int i3 = 0;
        int i4 = (this.f3015N || this.f3016O) ? 1 : 0;
        int i5 = (this.f3021T == null || this.f3016O) ? 0 : 1;
        int i6 = 2;
        int i7 = (T ? 2 : 0) + 1 + i4 + i5;
        List<PostModel> list = this.f3010I;
        int[] iArr = new int[i7 + (list != null ? list.size() - (this.f3015N ? this.f3011J : 0) : 0)];
        this.f1071B = iArr;
        if (T) {
            i = Page.f1069D + 0;
            iArr[0] = i;
        } else {
            i2 = 0;
            i = 0;
        }
        int i8 = i2 + 1;
        int i9 = i + PostModel.f3144w;
        iArr[i2] = i9;
        if (i4 != 0) {
            i9 += f3005l0;
            iArr[i8] = i9;
            i8++;
        }
        if (i5 != 0) {
            MainActivity mainActivity = this.mainActivity;
            float f = mainActivity.f731b;
            float f2 = mainActivity.f732c;
            int width = (int) (((float) mainActivity.mainLayout.getWidth()) - (32.0f * f));
            String c = Util.C0427h.UnEscapeString(this.f3021T.getString(0));
            this.f3021T.addString(0, c);
            float b = ((float) PostModel.f3144w) + ((float) Util.m672b(c, width, 20.0f * f2, false)) + (24.0f * f);
            float f3 = 16.0f;
            if (this.f3021T.getInt(2).intValue() == 0 && !this.f3019R && (this.f3017P & 4) == 0) {
                Document l = this.f3021T.getDocument(3);
                int i10 = 0;
                while (i10 < l.count()) {
                    Document l2 = l.getDocument(i10);
                    String c2 = Util.C0427h.UnEscapeString(l2.getString(0));
                    l2.addString(0, c2);
                    if (!TextUtils.isEmpty(c2)) {
                        b += ((float) Util.m672b(c2, width, f2 * f3, false)) + (f * f3);
                        i6 = 2;
                    }
                    Document l3 = l2.getDocument(i6);
                    int i11 = 0;
                    while (i11 < l3.count()) {
                        String c3 = Util.C0427h.UnEscapeString(l3.getString(i11));
                        l3.addString(i11, c3);
                        b += ((float) Util.m672b(c3, (int) (((float) width) - (40.0f * f)), f2 * 15.0f, false)) + (8.0f * f);
                        i11++;
                        l = l;
                    }
                    i10++;
                    i6 = 2;
                    f3 = 16.0f;
                }
            } else {
                Document l4 = this.f3021T.getDocument(3);
                for (int i12 = 0; i12 < l4.count(); i12++) {
                    Document l5 = l4.getDocument(i12);
                    String c4 = Util.C0427h.UnEscapeString(l5.getString(0));
                    l5.addString(0, c4);
                    if (!TextUtils.isEmpty(c4)) {
                        b += ((float) Util.m672b(c4, width, f2 * 16.0f, false)) + (f * 16.0f);
                    }
                    Document l6 = l5.getDocument(2);
                    int i13 = 0;
                    while (i13 < l6.count()) {
                        String c5 = Util.C0427h.UnEscapeString(l6.getString(i13));
                        l6.addString(i13, c5);
                        float f4 = 4.0f * f;
                        b = b + ((float) Util.m672b(c5, width, f2 * 15.0f, false)) + f4 + ((float) Util.m672b("1", width, f2 * 16.0f, false)) + f4;
                        i13++;
                        l4 = l4;
                    }
                }
            }
            i8++;
            i9 = (int) (((float) i9) + b + ((float) Util.m672b("1", width, f2 * 16.0f, false)) + (f * 76.0f));
            this.f1071B[i8] = i9;
        }
        int measuredWidth = this.mainActivity.mainLayout.getMeasuredWidth();
        if (this.f3015N) {
            i3 = this.f3011J;
        }
        while (i3 < this.f3010I.size()) {
            int[] iArr2 = this.f1071B;
            i8++;
            i9 += PostModel.f3145x + Util.m663k(this.mainActivity, this.f3010I.get(i3).f3162o, measuredWidth) + PostModel.f3144w;
            iArr2[i8] = i9;
            i3++;
        }
        if (T) {
            this.f1071B[i8] = i9 + Page.f1069D;
        }
    }

    @SuppressLint("ResourceType")
    View m130e0(View view, int i) {
        int i2;
        int i3;
        String str;
        int i4;
        RadioGroup radioGroup;
        int i5;
        Document uVar;
        boolean z;
        RadioGroup radioGroup2;
        float f = this.mainActivity.f731b;
        int i6 = 1;
        int intValue = this.f3021T.getInt(1).intValue();
        RelativeLayout relativeLayout = view == null ? new RelativeLayout(this.mainActivity) : (RelativeLayout) view;
        if (view != null) {
            relativeLayout.removeAllViews();
        }
        TextView textView = new TextView(this.mainActivity);
        int i7 = 0;
        textView.setText(this.f3021T.getString(0));
        textView.setTextSize(20.0f);
        textView.setTextColor(Skin.SkinColorModel.mainTextColor);
        textView.setId(1);
        float f2 = 16.0f;
        int i8 = (int) (f * 16.0f);
        int i9 = (int) (8.0f * f);
        textView.setPadding(i8, i8, i8, i9);
        relativeLayout.addView(textView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        layoutParams.addRule(3, 0);
        String str2 = "Всего голосов: ";
        Typeface typeface = null;
        if (i == 4) {
            Document l = this.f3021T.getDocument(3);
            int i10 = 1;
            int i11 = 0;
            int i12 = 1;
            while (i11 < l.count()) {
                Document l2 = l.getDocument(i11);
                String n = l2.getString(i7);
                if (!TextUtils.isEmpty(n)) {
                    TextView textView2 = new TextView(this.mainActivity);
                    textView2.setText(n);
                    textView2.setTextSize(f2);
                    textView2.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView2.setTypeface(typeface, 1);
                    int i13 = i12 + 1;
                    textView2.setId(i13);
                    textView2.setPadding(i8, (int) (f * 12.0f), i8, (int) (f * 4.0f));
                    relativeLayout.addView(textView2);
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
                    layoutParams2.addRule(9);
                    layoutParams2.addRule(3, i10);
                    i12 = i13;
                    i6 = 1;
                }
                boolean z2 = l2.getInt(i6).intValue() != 0;
                if (!z2) {
                    radioGroup = new RadioGroup(this.mainActivity);
                    radioGroup.setOrientation(i6);
                    i10 = i12 + 1;
                    radioGroup.setId(i10);
                    relativeLayout.addView(radioGroup);
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) radioGroup.getLayoutParams();
                    layoutParams3.width = -1;
                    layoutParams3.addRule(9);
                    layoutParams3.addRule(3, i10 - 1);
                    if (l2.count() <= 4) {
                        l2.append(new Document(Integer.valueOf(i10)));
                    }
                    i12 = i10;
                    uVar = null;
                    i5 = 2;
                } else {
                    if (l2.count() <= 4) {
                        uVar = new Document();
                        l2.append(uVar);
                    } else {
                        uVar = null;
                    }
                    i5 = 2;
                    radioGroup = null;
                }
                Document l3 = l2.getDocument(i5);
                l2.getDocument(3);
                int i14 = 0;
                while (i14 < l3.count()) {
                    if (!z2) {
                        z = z2;
                        RadioButton radioButton = new RadioButton(this.mainActivity);
                        radioButton.setButtonDrawable(null);
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable(R.drawable.radio_button), null, null, null);
                        radioButton.setTextColor(Skin.SkinColorModel.mainTextColor);
                        radioButton.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
                        radioButton.setText(l3.getString(i14));
                        radioButton.setTextSize(15.0f);
                        int i15 = i12 + 1;
                        radioButton.setId(i15);
                        radioButton.setGravity(16);
                        i12 = i15;
                        int i16 = (int) (f * 0.0f);
                        int i17 = (int) (f * 4.0f);
                        radioButton.setPadding(i16, i17, i16, i17);
                        radioGroup.addView(radioButton);
                        radioButton.getLayoutParams().width = -1;
                        ((RadioGroup.LayoutParams) radioButton.getLayoutParams()).leftMargin = i8;
                        ((RadioGroup.LayoutParams) radioButton.getLayoutParams()).rightMargin = i8;
                        radioGroup2 = radioGroup;
                        i10 = i10;
                    } else {
                        z = z2;
                        Widgets$CheckboxTextView widgets$CheckboxTextView = new Widgets$CheckboxTextView(this.mainActivity);
                        widgets$CheckboxTextView.setClickable(true);
                        widgets$CheckboxTextView.setText(l3.getString(i14));
                        widgets$CheckboxTextView.setTextSize(15.0f);
                        i10 = i12 + 1;
                        widgets$CheckboxTextView.setId(i10);
                        widgets$CheckboxTextView.setTextColor(Skin.SkinColorModel.mainTextColor);
                        widgets$CheckboxTextView.setGravity(16);
                        radioGroup2 = radioGroup;
                        widgets$CheckboxTextView.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.checkbox_left));
                        int i18 = (int) (f * 4.0f);
                        widgets$CheckboxTextView.setPadding(i8, i18, 0, i18);
                        relativeLayout.addView(widgets$CheckboxTextView);
                        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) widgets$CheckboxTextView.getLayoutParams();
                        layoutParams4.width = -1;
                        layoutParams4.addRule(9);
                        layoutParams4.addRule(3, i10 - 1);
                        layoutParams4.leftMargin = i8;
                        layoutParams4.rightMargin = i8;
                        if (uVar != null) {
                            uVar.append(Integer.valueOf(i10));
                        }
                        i12 = i10;
                    }
                    i14++;
                    z2 = z;
                    radioGroup = radioGroup2;
                }
                i11++;
                i6 = 1;
                typeface = null;
                f2 = 16.0f;
                i7 = 0;
            }
            TextView textView3 = new TextView(this.mainActivity);
            textView3.setText(str2 + intValue);
            textView3.setTextSize(16.0f);
            int i19 = i12 + 1;
            textView3.setId(i19);
            textView3.setPadding(0, (int) (f * 4.0f), 0, i9);
            textView3.setTextColor(Skin.SkinColorModel.labelTextCsl);
            relativeLayout.addView(textView3);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
            layoutParams5.addRule(14);
            layoutParams5.addRule(3, i10);
            TextView textView4 = new TextView(this.mainActivity);
            textView4.setText("ГОЛОСОВАТЬ");
            textView4.setOnClickListener(new VoteOnClickListenerC0893d(l, relativeLayout));
            textView4.setTextSize(16.0f);
            textView4.setTypeface(null, 1);
            textView4.setGravity(17);
            textView4.setTextColor(Skin.SkinColorModel.btnTextColor);
            i2 = i19 + 1;
            textView4.setId(i2);
            textView4.setPadding(i8, 0, i8, 0);
            textView4.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
            relativeLayout.addView(textView4);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) textView4.getLayoutParams();
            layoutParams6.addRule(9);
            int i20 = i2 - 1;
            layoutParams6.addRule(3, i20);
            int i21 = (int) (f * 36.0f);
            layoutParams6.height = i21;
            layoutParams6.leftMargin = i8;
            int i22 = (int) (f * 12.0f);
            layoutParams6.topMargin = i22;
            TextView textView5 = new TextView(this.mainActivity);
            textView5.setOnClickListener(new View$OnClickListenerC0895e());
            textView5.setText("РЕЗУЛЬТАТЫ");
            textView5.setTextSize(16.0f);
            textView5.setTypeface(null, 1);
            textView5.setGravity(17);
            textView5.setTextColor(Skin.SkinColorModel.btnTextColor);
            textView5.setPadding(i8, 0, i8, 0);
            textView5.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
            relativeLayout.addView(textView5);
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) textView5.getLayoutParams();
            layoutParams7.addRule(11);
            layoutParams7.addRule(3, i20);
            layoutParams7.height = i21;
            layoutParams7.rightMargin = i8;
            layoutParams7.topMargin = i22;
        } else {
            Document l4 = this.f3021T.getDocument(3);
            int i23 = 1;
            for (int i24 = 0; i24 < l4.count(); i24++) {
                Document l5 = l4.getDocument(i24);
                String n2 = l5.getString(0);
                if (!TextUtils.isEmpty(n2)) {
                    TextView textView6 = new TextView(this.mainActivity);
                    textView6.setText(n2);
                    textView6.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView6.setTextSize(16.0f);
                    textView6.setTypeface(null, 1);
                    i23++;
                    textView6.setId(i23);
                    textView6.setPadding(i8, (int) (f * 12.0f), i8, (int) (f * 4.0f));
                    relativeLayout.addView(textView6);
                    RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) textView6.getLayoutParams();
                    layoutParams8.addRule(9);
                    i3 = 3;
                    layoutParams8.addRule(3, i23 - 1);
                } else {
                    i3 = 3;
                }
                Document l6 = l5.getDocument(2);
                Document l7 = l5.getDocument(i3);
                int i25 = 0;
                while (i25 < l6.count()) {
                    TextView textView7 = new TextView(this.mainActivity);
                    textView7.setText(l6.getString(i25));
                    textView7.setTextSize(15.0f);
                    textView7.setTextColor(Skin.SkinColorModel.mainTextColor);
                    int i26 = i23 + 1;
                    textView7.setId(i26);
                    textView7.setGravity(16);
                    int i27 = (int) (f * 4.0f);
                    int i28 = (int) (f * 0.0f);
                    textView7.setPadding(i8, i27, i8, i28);
                    relativeLayout.addView(textView7);
                    RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) textView7.getLayoutParams();
                    layoutParams9.addRule(9);
                    layoutParams9.addRule(3, i26 - 1);
                    Integer m = l7.getInt(i25);
                    TextView textView8 = new TextView(this.mainActivity);
                    textView8.setText(m.toString());
                    textView8.setTextSize(16.0f);
                    textView8.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView8.setTypeface(null, 1);
                    i23 = i26 + 1;
                    textView8.setId(i23);
                    textView8.setGravity(16);
                    textView8.setPadding(i28, 0, i28, i27);
                    relativeLayout.addView(textView8);
                    RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) textView8.getLayoutParams();
                    layoutParams10.addRule(9);
                    layoutParams10.addRule(3, i23 - 1);
                    layoutParams10.width = (int) (50.0f * f);
                    layoutParams10.leftMargin = i8;
                    View view2 = new View(this.mainActivity);
                    view2.setBackgroundColor(Skin.SkinColorModel.mainTextColor);
                    relativeLayout.addView(view2);
                    RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
                    layoutParams11.addRule(8, i23);
                    layoutParams11.addRule(1, i23);
                    if (intValue > 0) {
                        str = str2;
                        i4 = (m.intValue() * (this.mainActivity.mainLayout.getWidth() - ((int) (f * 142.0f)))) / intValue;
                    } else {
                        str = str2;
                        i4 = 0;
                    }
                    layoutParams11.width = i4;
                    layoutParams11.height = i9;
                    layoutParams11.bottomMargin = (int) (10.0f * f);
                    TextView textView9 = new TextView(this.mainActivity);
                    textView9.setSingleLine();
                    Object[] objArr = new Object[1];
                    objArr[0] = Float.valueOf(intValue > 0 ? (((float) m.intValue()) * 100.0f) / ((float) intValue) : 0.0f);
                    textView9.setText(String.format("%.2f%%", objArr));
                    textView9.setTextSize(16.0f);
                    textView9.setTextColor(Skin.SkinColorModel.mainTextColor);
                    textView9.setGravity(21);
                    textView9.setPadding(i28, 0, i28, i27);
                    relativeLayout.addView(textView9);
                    RelativeLayout.LayoutParams layoutParams12 = (RelativeLayout.LayoutParams) textView9.getLayoutParams();
                    layoutParams12.addRule(11);
                    layoutParams12.addRule(8, i23);
                    layoutParams12.width = (int) (55.0f * f);
                    layoutParams12.rightMargin = i8;
                    i25++;
                    l4 = l4;
                    l6 = l6;
                    l7 = l7;
                    str2 = str;
                }
            }
            TextView textView10 = new TextView(this.mainActivity);
            textView10.setText(str2 + intValue);
            textView10.setTextSize(16.0f);
            i2 = i23 + 1;
            textView10.setId(i2);
            textView10.setPadding(0, (int) (f * 4.0f), 0, i9);
            textView10.setTextColor(Skin.SkinColorModel.labelTextCsl);
            relativeLayout.addView(textView10);
            RelativeLayout.LayoutParams layoutParams13 = (RelativeLayout.LayoutParams) textView10.getLayoutParams();
            layoutParams13.addRule(14);
            layoutParams13.addRule(3, i2 - 1);
            if (this.f3021T.getInt(2).intValue() == 0 && (this.f3017P & 4) == 0) {
                TextView textView11 = new TextView(this.mainActivity);
                textView11.setOnClickListener(new View$OnClickListenerC0897f());
                textView11.setText("ПРОГОЛОСОВАТЬ");
                textView11.setTextSize(16.0f);
                textView11.setTypeface(null, 1);
                textView11.setGravity(17);
                textView11.setTextColor(Skin.SkinColorModel.btnTextColor);
                i2++;
                textView11.setId(i2);
                textView11.setPadding(i8, 0, i8, 0);
                textView11.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
                relativeLayout.addView(textView11);
                RelativeLayout.LayoutParams layoutParams14 = (RelativeLayout.LayoutParams) textView11.getLayoutParams();
                layoutParams14.addRule(14);
                layoutParams14.addRule(3, i2 - 1);
                layoutParams14.height = (int) (f * 36.0f);
                layoutParams14.leftMargin = i8;
                layoutParams14.topMargin = (int) (f * 12.0f);
            }
        }
        View view3 = new View(this.mainActivity);
        view3.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.card_sep));
        view3.setLayoutParams(new AbsListView.LayoutParams(-1, i8));
        int i29 = i2 + 1;
        view3.setId(i29);
        relativeLayout.addView(view3);
        RelativeLayout.LayoutParams layoutParams15 = (RelativeLayout.LayoutParams) view3.getLayoutParams();
        layoutParams15.addRule(3, i29 - 1);
        layoutParams15.topMargin = i8;
        return relativeLayout;
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        PostModel yVar = (PostModel) pVar.f2221a0;
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            int i3 = 2;
            if (i2 == 1) {
                MainActivity mainActivity = this.mainActivity;
                String str = kVar.link;
                if (!this.f3027Z) {
                    i3 = 1;
                }
                Page b = Urls2.openPage(mainActivity, str, false, i3);
                if (b != null) {
                    if (this.f3027Z) {
                        Tab f1Var = new Tab(this.mainActivity);
                        f1Var.addPage(b);
                        this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    } else {
                        this.tab.addPage(b);
                    }
                }
                this.f3027Z = false;
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        } else {
            int i4 = cVar.f544b;
            if (i4 >= 0) {
                BBString.C0670a aVar = pVar.f2212S[i4];
                if (!aVar.f2249c) {
                    DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVar.f2247a, this.mainActivity, aVar.f2254h));
                } else if (aVar.f2253g <= 0) {
                    new ImageDialog(this.mainActivity).m917b(aVar.f2250d);
                } else if (yVar.f3164q != null) {
                    for (int i5 = 0; i5 < yVar.f3164q.size(); i5++) {
                        if (yVar.f3164q.get(i5).f723a == aVar.f2247a) {
                            new ImageDialog(this.mainActivity).m916c(yVar.f3164q, i5);
                            return;
                        }
                    }
                } else {
                    DocumentManager.getResultRequest(new API.LoadForumAttachRequest(aVar.f2247a, this.mainActivity, ""));
                }
            }
        }
    }

    Form_Post m128f0() {
        if (this.f3031d0 == null) {
            this.f3031d0 = new Form_Post(this.mainActivity, this);
        }
        return this.f3031d0;
    }

    public void m127g0(Document uVar) {
        this.currentDocument = uVar;
        m125i0();
        tabLoaded(true);
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces()) {
            return 0;
        }
        int i2 = 1;
        int i3 = (m816T() ? 2 : 0) + 1 + ((this.f3015N || this.f3016O) ? 1 : 0);
        if (this.f3021T == null || this.f3016O) {
            i2 = 0;
        }
        int i4 = i3 + i2;
        List<PostModel> list = this.f3010I;
        if (list != null) {
            int size = list.size();
            if (this.f3015N) {
                i = this.f3011J;
            }
            i = size - i;
        }
        return i4 + i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int i) {
        Document uVar;
        int i2 = 0;
        if (m816T()) {
            if (i == 0) {
                return 0;
            }
            if (getCount() - 1 == i) {
                return 8;
            }
            i2 = 1;
        }
        if (i == i2) {
            return 1;
        }
        if ((this.f3015N || this.f3016O) && i == (i2 = i2 + 1)) {
            return 2;
        }
        if (i != i2 + 1 || this.f3016O || (uVar = this.f3021T) == null) {
            if (this.f3010I.size() <= 0) {
                return 5;
            }
            int i3 = this.f3010I.get(i - m126h0()).f3159l;
            if ((i3 & 2) != 0) {
                return 6;
            }
            if ((i3 & 1) == 0 || (this.f3017P & 2) == 0) {
                return (i3 & 4) != 0 ? 7 : 5;
            }
            return 6;
        } else if (uVar.getInt(2).intValue() == 0 && !this.f3019R && (this.f3017P & 4) == 0) {
            return 4;
        } else {
            return 3;
        }
    }

    @Override
    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        boolean z;
        int i2;
        int itemViewType = getItemViewType(i);
        if (3 == itemViewType) {
            int measuredWidth = this.mainActivity.mainLayout.getMeasuredWidth();
            view2 = measuredWidth != this.f3020S ? null : view;
            this.f3020S = measuredWidth;
        } else {
            view2 = view;
        }
        if (view2 == null) {
            if (itemViewType == 0) {
                view2 = m817S(viewGroup, false);
            } else if (itemViewType == 8) {
                view2 = m817S(viewGroup, true);
            } else if (itemViewType == 1) {
                view2 = new View(this.mainActivity);
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
                view2.setBackgroundDrawable(Skin.SkinColorModel.f1388i0.getConstantState().newDrawable());
            } else if (itemViewType == 2) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.topicbuttons, viewGroup, false);
                view2.findViewById(R.id.topicButtonsPoll).setOnClickListener(new View$OnClickListenerC0933o());
                view2.findViewById(R.id.topicButtonsHat).setOnClickListener(new View$OnClickListenerC0934p());
            } else if (itemViewType == 4) {
                view2 = m130e0(view2, itemViewType);
            } else if (itemViewType == 3) {
                view2 = m130e0(view2, itemViewType);
            } else if (itemViewType == 5 || itemViewType == 6 || itemViewType == 7) {
                view2 = this.mainActivity.getLayoutInflater().inflate(R.layout.post, viewGroup, false);
                if (itemViewType == 6) {
                    view2.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.post_hidden));
                } else if (itemViewType == 7) {
                    view2.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.post_deleted));
                } else {
                    view2.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.post_normal));
                }
                view2.setOnLongClickListener(new View$OnLongClickListenerC0935q());
                BBDisplay bBDisplay = view2.findViewById(R.id.PostCode);
                bBDisplay.setCallback(this);
                bBDisplay.setOverlay(view2.findViewById(R.id.PostCodeOverlay));
                TextView textView = view2.findViewById(R.id.authorName);
                if ((this.f3017P & 16) == 0) {
                    textView.setOnClickListener(new View$OnClickListenerC0936r());
                }
                textView.setOnLongClickListener(new View$OnLongClickListenerC0937s());
                TextView textView2 = view2.findViewById(R.id.postAuthorRep);
                textView2.setOnClickListener(new View$OnClickListenerC0938t());
                Widgets$CheckboxView widgets$CheckboxView = view2.findViewById(R.id.postCheckbox);
                if ((this.f3017P & 512) > 0 || this.f3018Q) {
                    widgets$CheckboxView.getLayoutParams().width = (int) (this.mainActivity.f731b * 36.0f);
                    widgets$CheckboxView.setOnClickListener(new View$OnClickListenerC0939u());
                    widgets$CheckboxView.setOnLongClickListener(new View$OnLongClickListenerC0940v());
                    ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).rightMargin = (int) (this.mainActivity.f731b * 44.0f);
                } else {
                    widgets$CheckboxView.getLayoutParams().width = 0;
                    ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).rightMargin = (int) (this.mainActivity.f731b * 8.0f);
                }
                TextView textView3 = view2.findViewById(R.id.postKarma);
                if ((this.f3017P & 512) != 0) {
                    textView3.setOnClickListener(new View$OnClickListenerC0885a());
                }
                view2.findViewById(R.id.postTitle).setVisibility(8);
                view2.findViewById(R.id.authorImage).setOnClickListener(new View$OnClickListenerC0887b());
            }
        }
        if (itemViewType == 5 || itemViewType == 6 || itemViewType == 7) {
            PostModel yVar = this.f3010I.get(i - m126h0());
            view2.setTag(yVar);
            TextView textView4 = view2.findViewById(R.id.authorName);
            textView4.setText(yVar.f3150c);
            textView4.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable((System.currentTimeMillis() / 1000) - ((long) yVar.f3155h) < 900 ? R.drawable.ic_online : R.drawable.ic_offline), null, null, null);
            TextView textView5 = view2.findViewById(R.id.authorGroup);
            if (this.currentDocument.getInt(8).intValue() != yVar.f3149b) {
                textView5.setText(API.userGroups.get(yVar.f3151d));
                textView5.setTextColor(yVar.f3152e);
            } else if (yVar.f3151d != 20) {
                SpannableString spannableString = new SpannableString("[K] " + API.userGroups.get(yVar.f3151d));
                spannableString.setSpan(new ForegroundColorSpan(Util.C0424f.m646c(Skin.SkinColorModel.f1398n0[20], Skin.SkinColorModel.f1370Z)), 0, 3, 0);
                spannableString.setSpan(new ForegroundColorSpan(Util.C0424f.m646c(Skin.SkinColorModel.f1398n0[yVar.f3151d], Skin.SkinColorModel.f1370Z)), 4, spannableString.length(), 0);
                textView5.setText(spannableString);
            } else {
                textView5.setText("Куратор темы");
                textView5.setTextColor(Util.C0424f.m646c(Skin.SkinColorModel.f1398n0[20], Skin.SkinColorModel.f1370Z));
            }
            Widgets$AvatarView avatarView = view2.findViewById(R.id.authorImage);
            boolean isEmpty = TextUtils.isEmpty(yVar.f3154g);
            int i3 = R.drawable.ic_avatar;
            if (isEmpty || (i2 = yVar.f3149b) == 0 || i2 == 17927) {
                z = true;
                PicoImg.cancel(avatarView);
                Skin e1Var = this.mainActivity.skin;
                int i4 = yVar.f3149b;
                if (i4 == 0 || i4 == 17927) {
                    i3 = R.drawable.ic_launcher;
                }
                avatarView.setImageDrawable(e1Var.getSkinDrawable(i3));
            } else {
                PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, yVar.f3154g);
                l.to(avatarView);
                z = true;
                l.disableAnimation(!Prefs.animAvatars);
                l.placeholder(this.mainActivity.skin.getSkinDrawable(R.drawable.ic_avatar));
                l.fade(4, 200, false);
                l.sizeToView();
                l.runAsync();
            }
            TextView textView6 = view2.findViewById(R.id.postDate);
            textView6.setText(yVar.f3157j);
            if ((this.f3017P & 512) != 0) {
                textView6.setCompoundDrawablesWithIntrinsicBounds((yVar.f3159l & 2048) != 0 ? this.mainActivity.skin.getSkinDrawable(R.drawable.ic_protected) : null, null, null, null);
                textView6.setCompoundDrawablePadding((int) (this.mainActivity.f731b * 4.0f));
            }
            TextView textView7 = view2.findViewById(R.id.postKarma);
            if (yVar.f3160m == null) {
                textView7.setVisibility(8);
            } else {
                textView7.setVisibility(0);
                textView7.setText(yVar.f3160m);
            }
            ((TextView) view2.findViewById(R.id.postAuthorRep)).setText(yVar.f3153f);
            Widgets$CheckboxView widgets$CheckboxView2 = view2.findViewById(R.id.postCheckbox);
            if (this.f3023V.indexOfKey(yVar.postId) < 0) {
                z = false;
            }
            widgets$CheckboxView2.setChecked(z);
            BBDisplay bBDisplay2 = view2.findViewById(R.id.PostCode);
            bBDisplay2.setBBString(yVar.f3162o);
            if (!TextUtils.isEmpty(this.f3013L)) {
                if (this.f3013L.startsWith("Spoil-")) {
                    String str = this.f3013L;
                }
                int h = bBDisplay2.m973h(this.f3013L);
                if (h > 0) {
                    this.tab.forumsListView.post(new RunnableC0889c(i, h));
                    this.f3013L = null;
                }
            }
        } else if (itemViewType == 2) {
            view2.findViewById(R.id.topicButtonsPoll).setVisibility(this.f3016O ? 0 : 8);
            view2.findViewById(R.id.topicButtonsHat).setVisibility(this.f3015N ? 0 : 8);
        } else if (itemViewType == 8) {
            m815U();
        }
        return view2;
    }

    @Override
    public int getViewTypeCount() {
        return 9;
    }

    int m126h0() {
        int i = 1;
        int i2 = (m816T() ? 1 : 0) + 1;
        boolean z = this.f3015N;
        int i3 = 0;
        int i4 = i2 + ((z || this.f3016O) ? 1 : 0);
        if (this.f3021T == null || this.f3016O) {
            i = 0;
        }
        int i5 = i4 + i;
        if (z) {
            i3 = this.f3011J;
        }
        return i5 - i3;
    }

    boolean m125i0() {
        Exception e;
        Document uVar;
        if (this.isLoading) {
            return false;
        }
        try {
            this.title = Util.C0427h.UnEscapeString(this.currentDocument.getString(2));
            this.f3009H = this.currentDocument.getInt(11).intValue();
            this.f3017P = this.currentDocument.getInt(4).intValue();
            DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
            this.f3018Q = L != null && this.currentDocument.getInt(8).intValue() == L.memberId;
            this.f3010I = null;
            Document l = this.currentDocument.getDocument(12);
            if (l != null) {
                this.f3010I = new Vector(l.count());
                for (int i = 0; i < l.count(); i++) {
                    try {
                        uVar = l.getDocument(i);
                        try {
                            PostModel b = PostModel.m98b(uVar, this.f3014M);
                            if (b != null) {
                                this.f3010I.add(b);
                                if ((b.f3159l & 1) > 0) {
                                    this.f3011J++;
                                }
                            } else {
                              /*  ErrorReporter errorReporter = //ACRA.getErrorReporter();
                                StringBuilder sb = new StringBuilder();
                                sb.append("Lost Post");
                                sb.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                                errorReporter.handleSilentException(new Exception(sb.toString()));*/
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Log.e("Page_Topic", "ecx", e);
                          /*  ErrorReporter errorReporter2 = //ACRA.getErrorReporter();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Lost Post common ");
                            sb2.append(uVar != null ? uVar.m282m(0).intValue() : 0);
                            errorReporter2.handleSilentException(new Exception(sb2.toString(), e));*/
                        }
                    } catch (Exception e3) {
                        e = e3;
                        uVar = null;
                    }
                }
                if (this.f3011J == 0) {
                    this.f3015N = false;
                }
                if (this.isLoading) {
                    return false;
                }
                Document l2 = this.currentDocument.getDocument(10);
                this.f3021T = l2;
                if (l2.count() == 0) {
                    this.f3021T = null;
                    this.f3016O = false;
                }
                m131d0();
            }
            Document l3 = this.currentDocument.getDocument(13);
            if (l3 != null && l3.count() > 0) {
                this.f3032e0 = l3.getString(0);
                this.f3033f0 = this.f3032e0 + " в " + this.title;
            }
            return true;
        } catch (Exception e4) {
            //ACRA.getErrorReporter().handleSilentException(new Exception("Parse", e4));
            return false;
        }
    }

    public Page_Topic m124j0(String str, int i) {
        if (!isUnsucces()) {
            return null;
        }
        Document c = this.currentDocument.cloneDocument();
        Document l = c.getDocument(12);
        Vector vector = new Vector();
        boolean z = false;
        for (int i2 = 0; i2 < l.count(); i2++) {
            Document l2 = l.getDocument(i2);
            String n = l2.getString(8);
            int i3 = 0;
            boolean z2 = false;
            while (true) {
                if ((TextUtils.isEmpty(str) || (i3 = n.toLowerCase().indexOf(str.toLowerCase(), i3)) >= 0) && (i == 0 || l2.getInt(2).intValue() == i)) {
                    if (!TextUtils.isEmpty(str)) {
                        n = n.substring(0, i3) + "[color=red][background=yellow]" + n.substring(i3, str.length() + i3) + "[/color][/background]" + n.substring(str.length() + i3);
                    }
                    i3 += 30 + str.length();
                    z = true;
                    z2 = true;
                }
                else{
                    break;
                }
            }
            if (z2) {
                l2.addString(8, n);
            } else {
                vector.add(Integer.valueOf(i2));
            }
        }
        if (!z) {
            return null;
        }
        for (int size = vector.size() - 1; size >= 0; size--) {
            l.remove(((Integer) vector.get(size)).intValue());
        }
        Page_Topic x0Var = new Page_Topic(this.mainActivity);
        x0Var.topicId = this.topicId;
        x0Var.topicPage = this.topicPage;
        x0Var.m127g0(c);
        return x0Var;
    }

    public void m123k0(int i, String str) {
        this.f3012K = i;
        this.f3013L = str;
        if (isUnsucces() && this.f3012K > 0) {
            int i2 = -1;
            List<PostModel> list = this.f3010I;
            int size = list != null ? list.size() : 0;
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    break;
                } else if (this.f3010I.get(i3).postId == this.f3012K) {
                    i2 = i3;
                    break;
                } else {
                    i3++;
                }
            }
            if (isCurrentTab() && this.tab.m717i() && i2 >= 0) {
                this.f3012K = 0;
                int h0 = m126h0() + i2;
                m814V();
                this.f3022U.push(Integer.valueOf(this.f1090x));
                this.f3022U.push(Integer.valueOf(this.f1089w));
                tabLoaded(true);
                this.mainActivity.mainLayout.post(new RunnableC0914k(h0));
            } else if (!isCurrentTab() || !this.tab.m717i()) {
                this.f3024W = true;
            } else {
                tabReload();
            }
        }
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f3011J = 0;
        this.f3021T = null;
        List<PostModel> list = this.f3010I;
        if (list != null) {
            list.clear();
        }
        this.f3010I = null;
        this.f3035h0 = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        if (!isUnsucces()) {
            return null;
        }
        Document l = this.currentDocument.getDocument(0);
        Breadcrumb.C0725a[] aVarArr = new Breadcrumb.C0725a[l.count() + 2];
        aVarArr[0] = new Breadcrumb.C0725a(1, "forum/index.php?act=idx", "Форум", 0, false, false);
        for (int i = 0; i < l.count(); i++) {
            Document l2 = l.getDocument(i);
            aVarArr[l.count() - i] = new Breadcrumb.C0725a(i + 2, "forum/index.php?showforum=" + l2.getInt(1), l2.getString(2), 0, false, false);
        }
        aVarArr[l.count() + 1] = new Breadcrumb.C0725a(100, "forum/index.php?showtopic=" + this.topicId, this.title, 0, true, true);
        return aVarArr;
    }

    @Override
    public String getLink() {
        int i = this.f3008G;
        String str = "";
        String str2 = 1 == i ? "&modfilter=invisible-posts" : 2 == i ? "&modfilter=deleted-posts" : 3 == i ? "&modfilter=all-posts" : 4 == i ? "&modfilter=regular-posts" : str;
        StringBuilder sb = new StringBuilder();
        sb.append("forum/index.php?showtopic=");
        sb.append(this.topicId);
        if (this.topicPage > 0) {
            str = "&st=" + this.topicPage;
        }
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    @Override
    public boolean mo109y(Object... objArr) {
        Form_Post wVar = this.f3031d0;
        if ((wVar != null && wVar.m201t()) || this.f3035h0 != null) {
            return false;
        }
        if (objArr.length >= 3) {
            return ((Integer) objArr[0]).intValue() == this.topicId && ((Integer) objArr[1]).intValue() == this.topicPage && ((Integer) objArr[2]).intValue() == this.f3008G;
        } else if (objArr.length >= 2) {
            return ((Integer) objArr[0]).intValue() == this.topicId && ((Integer) objArr[1]).intValue() == this.topicPage;
        } else return objArr.length >= 1 && ((Integer) objArr[0]).intValue() == this.topicId;
    }

    @Override
    public void tabReload() {
        this.f3024W = false;
        m810c0("", false);
        if (isUnsuccesCode()) {
            super.tabReload();
        } else {
            tabLoaded(true);
        }
    }

    String m106z0() {
        return "forum/index.php?showtopic=" + this.topicId + "&view=getnewpost";
    }

    public Page_Topic(MainActivity mainActivity, int topicId, int topicPage, int showPostMode, int i4, String str, String str2) {
        super(mainActivity, 29286, new Document(topicId, topicPage, Prefs.memberPostsPerPage, showPostMode));
        this.f3008G = 0;
        this.f3022U = new Stack<>();
        this.f3025X = new C0904g();
        this.f3026Y = new C0905h();
        this.f3037j0 = new C0917n();
        m146A0(topicId, topicPage, showPostMode);
        this.f3012K = i4;
        this.f3013L = str;
        if (!TextUtils.isEmpty(str2)) {
            this.title = str2;
        }
    }

    Page_Topic(MainActivity mainActivity) {
        super(mainActivity);
        this.f3008G = 0;
        this.f3022U = new Stack<>();
        this.f3025X = new C0904g();
        this.f3026Y = new C0905h();
        this.f3037j0 = new C0917n();
        m146A0(0, 0, 0);
        this.title = "пустая страница топика";
    }
}
