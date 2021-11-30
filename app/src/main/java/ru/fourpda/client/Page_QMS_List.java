package ru.fourpda.client;



import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_QMS_List extends Page {
    View$OnClickListenerC0662n f2102E;
    List<Integer> f2103F;
    int f2104G;
    SparseArray<C0666p> f2105H;
    String searchText;
    boolean f2107J;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f2108K;
    private String f2109L;

    public class View$OnClickListenerC0647a implements View.OnClickListener {
        final int f2110a;

        View$OnClickListenerC0647a(int i) {
//            Page_QMS_List.this
            this.f2110a = i;
        }

        @Override
        public void onClick(View view) {
            Page_QMS_List.m505f0(Page_QMS_List.this.mainActivity, this.f2110a, "").show(true, true, true);
        }
    }

    class C0648b implements TextWatcher {
        final DlgSimple f2112a;

        C0648b(Page_QMS_List o0Var, DlgSimple q1Var) {
            this.f2112a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2112a.m625a(editable.length() >= 3);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class View$OnClickListenerC0649c implements View.OnClickListener {
        final DlgSimple f2113a;

        View$OnClickListenerC0649c(DlgSimple q1Var) {
//            Page_QMS_List.this
            this.f2113a = q1Var;
        }

        @Override
        public void onClick(View view) {
            Page_QMS_List o0Var = Page_QMS_List.this;
            o0Var.tab.addPage(new Page_QMS_List(o0Var.mainActivity, this.f2113a.editText.getText().toString()));
        }
    }

    class View$OnClickListenerC0650d implements View.OnClickListener {
        View$OnClickListenerC0650d() {
//            Page_QMS_List.this
        }

        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(Page_QMS_List.this.searchText)) {
                Page_QMS_List o0Var = Page_QMS_List.this;
                o0Var.tab.addPage(new Page_QMS_List(o0Var.mainActivity, -1));
            }
        }
    }

    public class C0651e implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0651e() {
//            Page_QMS_List.this
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            if (1 != cVar2.f2511a || (cVar != null && cVar.f2513c == cVar2.f2513c)) {
                return Boolean.FALSE;
            }
            Page_QMS_List o0Var = Page_QMS_List.this;
            o0Var.f2107J = true;
            if (o0Var.isCurrentTab() && Page_QMS_List.this.tab.m717i()) {
                Page_QMS_List o0Var2 = Page_QMS_List.this;
                if (!o0Var2.mainActivity.f733d) {
                    o0Var2.tabReload();
                }
            }
            return Boolean.TRUE;
        }
    }

    class C0652f implements OptionPoupupMenuView.IClickListener {
        C0652f() {
//            Page_QMS_List.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            String str;
            if (i3 == 21) {
                Page_QMS_List.this.tabReload();
            } else if (i3 == 1) {
                Page_QMS_List.m505f0(Page_QMS_List.this.mainActivity, 0, "").show(true, true, true);
            } else if (i3 == 2) {
                DocumentManager.getResultRequest(new QmsAddOrRemoveFromBlackList(Page_QMS_List.this));
            } else if (i3 == 23) {
                Page_QMS_List o0Var = Page_QMS_List.this;
                if (o0Var.searchText != null) {
                    str = "Поиск \"" + Page_QMS_List.this.searchText + "\" в сообщениях";
                } else {
                    str = o0Var.title;
                }
                DataDB.m365l(str, Page_QMS_List.this.getLink());
            }
        }
    }

    public static class C0653g implements TextWatcher {
        final CustomDialog f2118a;
        final Widgets$MemberView f2119b;

        C0653g(CustomDialog l1Var, Widgets$MemberView widgets$MemberView) {
            this.f2118a = l1Var;
            this.f2119b = widgets$MemberView;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2118a.m625a(this.f2119b.m844b() != null && editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public static class C0654h implements Util.AbstractC0429j<Boolean, Boolean> {
        final CustomDialog f2120a;
        final EditText f2121b;

        C0654h(CustomDialog l1Var, EditText editText) {
            this.f2120a = l1Var;
            this.f2121b = editText;
        }

        public Boolean mo222a(Boolean bool) {
            this.f2120a.m625a(bool.booleanValue() && !TextUtils.isEmpty(this.f2121b.getText().toString()));
            return Boolean.TRUE;
        }
    }

    public static class View$OnClickListenerC0655i implements View.OnClickListener {
        final EditText f2122a;
        final Widgets$MemberView f2123b;
        final MainActivity activity;
        final EditText f2125d;

        View$OnClickListenerC0655i(EditText editText, Widgets$MemberView widgets$MemberView, MainActivity mainActivity, EditText editText2) {
            this.f2122a = editText;
            this.f2123b = widgets$MemberView;
            this.activity = mainActivity;
            this.f2125d = editText2;
        }

        @Override
        public void onClick(View view) {
            String dialogTheme = this.f2122a.getText().toString();
            if (TextUtils.isEmpty(dialogTheme)) {
                DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
                if (L != null) {
                    dialogTheme = L.memberInfoName + " / " + this.f2123b.getText().toString();
                } else {
                    Toast.makeText(this.activity, "Введите тему диалога", 1).show();
                    return;
                }
            }
            Integer userId = this.f2123b.m844b();
            if (this.f2125d.getText().length() <= 0 || userId == null) {
                Toast.makeText(this.activity, "Введите сообщение", 1).show();
            } else {
                DocumentManager.getResultRequest(new CreateDialogQmsRequest(this.activity, userId, dialogTheme, this.f2125d.getText().toString()));
            }
        }
    }

    private class LoadDialogsRequest extends API.QmsListRequest {
        LoadDialogsRequest(int opponentId) {
            super(opponentId);
//            Page_QMS_List.this
            this.statusMessage = "Загрузка диалогов";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Document l;
            if (!Page_QMS_List.this.isLoading && status == 0 && (l = uVar.getDocument(0)) != null) {
                C0666p pVar = new C0666p(Page_QMS_List.this, l.getDocument(0));
                pVar.f2146g = true;
                pVar.f2148i = Page_QMS_List.this.f2105H.get(this.opponentId).f2148i;
                Page_QMS_List.this.f2105H.put(this.opponentId, pVar);
                Page_QMS_List.this.tabLoaded(true);
            }
        }
    }

    public static class DialogC0657k extends Dialog {

        public class View$OnClickListenerC0658a implements View.OnClickListener {
            final Util.AbstractC0429j f2127a;
            final Document f2128b;

            View$OnClickListenerC0658a(Util.AbstractC0429j jVar, Document uVar) {
//                DialogC0657k.this
                this.f2127a = jVar;
                this.f2128b = uVar;
            }

            @Override
            public void onClick(View view) {
                this.f2127a.mo222a(this.f2128b.getInt(0));
                DialogC0657k.this.cancel();
            }
        }

        DialogC0657k(MainActivity mainActivity, Document uVar, Util.AbstractC0429j<Boolean, Integer> jVar) {
            super(mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            ScrollView scrollView = new ScrollView(mainActivity);
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            int i = 1;
            linearLayout.setOrientation(1);
            float f = mainActivity.f731b;
            int i2 = (int) (24.0f * f);
            int i3 = (int) (12.0f * f);
            float f2 = 16.0f;
            linearLayout.setPadding(i2, i2, i3, (int) (f * 16.0f));
            scrollView.addView(linearLayout);
            TextView textView = new TextView(mainActivity);
            textView.setText("Черный список");
            textView.setTextColor(Skin.C0353a.f1365U);
            textView.setSingleLine(true);
            textView.setTextSize(22.0f);
            textView.setTypeface(null, 1);
            int i4 = 0;
            textView.setPadding(0, 0, 0, i3);
            linearLayout.addView(textView);
            int i5 = 0;
            while (i5 < uVar.count()) {
                Document l = uVar.getDocument(i5);
                if (i5 > 0) {
                    View view = new View(mainActivity);
                    view.setBackgroundDrawable(mainActivity.skin.m736f(R.color.border_line));
                    linearLayout.addView(view);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = (int) (1.0f * f);
                    layoutParams.rightMargin = i3;
                }
                RelativeLayout relativeLayout = new RelativeLayout(mainActivity);
                linearLayout.addView(relativeLayout);
                TextView textView2 = new TextView(mainActivity);
                textView2.setText(Util.C0427h.UnEscapeString(l.getString(i)));
                textView2.setTextSize(f2);
                textView2.setTextColor(Skin.C0353a.f1365U);
                textView2.setId(R.id.captionID);
                textView2.setGravity(16);
                int i6 = (int) (40.0f * f);
                textView2.setMinimumHeight(i6);
                textView2.setPadding(i4, (int) (f * 9.0f), i4, (int) (f * 8.0f));
                relativeLayout.addView(textView2);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
                layoutParams2.width = -1;
                layoutParams2.height = -1;
                layoutParams2.addRule(10);
                layoutParams2.addRule(9);
                layoutParams2.addRule(i4, R.id.removeID);
                View view2 = new View(mainActivity);
                view2.setClickable(true);
                view2.setBackgroundDrawable(mainActivity.skin.m736f(R.drawable.button_remove));
                view2.setId(R.id.removeID);
                view2.setOnClickListener(new View$OnClickListenerC0658a(jVar, l));
                view2.setEnabled(true);
                relativeLayout.addView(view2);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
                layoutParams3.width = i6;
                layoutParams3.height = -2;
                layoutParams3.addRule(11);
                layoutParams3.addRule(6, R.id.captionID);
                layoutParams3.addRule(8, R.id.captionID);
                i5++;
                i = 1;
                f2 = 16.0f;
                i4 = 0;
            }
            setContentView(scrollView);
            getWindow().setBackgroundDrawable(mainActivity.skin.m736f(R.drawable.np_dialog));
            getWindow().setLayout(-1, -2);
            setCanceledOnTouchOutside(true);
            getWindow().getAttributes().gravity = 17;
            super.show();
            CustomDialog.m623c(this);
        }

        public static void m502a(MainActivity mainActivity, Document uVar, Util.AbstractC0429j<Boolean, Integer> jVar) {
            new DialogC0657k(mainActivity, uVar, jVar);
        }
    }

    public class QmsAddOrRemoveFromBlackList extends API.QmsBlacklistRequest {

        class C0660a implements Util.AbstractC0429j<Boolean, Integer> {
            C0660a() {
//                QmsAddOrRemoveFromBlackList.this
            }

            public Boolean mo222a(Integer num) {
                DocumentManager.getResultRequest(new QmsAddOrRemoveFromBlackList(num, false));
                return Boolean.TRUE;
            }
        }

        QmsAddOrRemoveFromBlackList(int opponentId, boolean add) {
            super(opponentId, add);
//            Page_QMS_List.this
            this.statusMessage = opponentId > 0 ? add ? "Добавление пользователя" : "Удаление пользователя" : "Запрос списка";
        }

        @Override
        void prepareResult(int status, Document uVar) {
            Page_QMS_List o0Var = Page_QMS_List.this;
            if (!o0Var.isLoading) {
                if (status != 0) {
                    Toast.makeText(o0Var.mainActivity, "Действие завершилось ошибкой", 0).show();
                } else if (uVar.count() > 0) {
                    Document l = uVar.getDocument(0);
                    if (l == null || l.count() <= 0) {
                        Toast.makeText(Page_QMS_List.this.mainActivity, "Черный список пуст", 0).show();
                    } else {
                        DialogC0657k.m502a(Page_QMS_List.this.mainActivity, l, new C0660a());
                    }
                } else {
                    Toast.makeText(Page_QMS_List.this.mainActivity, "Действие выполнено", 0).show();
                    Page_QMS_List.this.tabReload();
                }
            }
        }

        QmsAddOrRemoveFromBlackList(Page_QMS_List o0Var) {
            this(0, false);
        }
    }

    static class QmsDeleteMessage extends API.QmsDeleteRequest {
        Page f2132j;

        public QmsDeleteMessage(Page a0Var, int dialogId, int messageId, boolean isOnlyMe, String statusMessage) {
            super(dialogId, messageId, isOnlyMe);
            this.f2132j = a0Var;
            this.statusMessage = statusMessage;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            Page a0Var = this.f2132j;
            if (!a0Var.isLoading) {
                Toast.makeText(a0Var.mainActivity, status == 0 ? "Удалено успешно" : "Действие завершилось ошибкой", 0).show();
                if (status == 0) {
                    this.f2132j.tabReload();
                }
            }
        }
    }

    public class View$OnClickListenerC0662n implements View.OnClickListener, View.OnLongClickListener {

        class C0663a implements OptionPoupupMenuView.IClickListener {
            final C0666p f2134a;

            C0663a(C0666p pVar) {
//                View$OnClickListenerC0662n.this
                this.f2134a = pVar;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 1) {
                    MainActivity mainActivity = Page_QMS_List.this.mainActivity;
                    C0666p pVar = this.f2134a;
                    Page_QMS_List.m505f0(mainActivity, pVar.userId, pVar.f2142c).show(true, true, true);
                } else if (i3 == 2) {
                    Tab f1Var = new Tab(Page_QMS_List.this.mainActivity);
                    f1Var.addPage(new Page_Profile(Page_QMS_List.this.mainActivity, this.f2134a.userId, 0));
                    Page_QMS_List.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                } else if (i3 == 3) {
                    MainActivity mainActivity2 = Page_QMS_List.this.mainActivity;
                    Util.copyToClipboard(mainActivity2, "https://4pda.ru/forum/index.php?showuser=" + this.f2134a.userId, "Ссылка скопирована");
                } else if (i3 == 4) {
                    DocumentManager.getResultRequest(new QmsAddOrRemoveFromBlackList(this.f2134a.userId, true));
                } else if (i3 == 5) {
                    DocumentManager.getResultRequest(new QmsAddOrRemoveFromBlackList(this.f2134a.userId, false));
                } else if (i3 == 6) {
                    Page_QMS_List o0Var = Page_QMS_List.this;
                    int i4 = this.f2134a.userId;
                    DocumentManager.getResultRequest(new QmsDeleteMessage(o0Var, 0, i4, false, "Удаление пользователя " + this.f2134a.f2142c));
                }
            }
        }

        class C0664b implements OptionPoupupMenuView.IClickListener {
            final Document qmsDocument;

            C0664b(Document uVar) {
//                View$OnClickListenerC0662n.this
                this.qmsDocument = uVar;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 7) {
                    DocumentManager.getResultRequest(new API.QmsThreadRequest(this.qmsDocument.getInt(0), 0, -this.qmsDocument.getInt(6)));
                } else if (i3 == 8) {
                    Page_QMS_List o0Var = Page_QMS_List.this;
                    int dialogId = this.qmsDocument.getInt(0);
                    DocumentManager.getResultRequest(new QmsDeleteMessage(o0Var, dialogId, 0, false, "Удаление диалога " + this.qmsDocument.getString(3)));
                }
            }
        }

        private View$OnClickListenerC0662n() {
//            Page_QMS_List.this
        }

        @Override
        public void onClick(View view) {
            Object tag = view.getTag();
            if (tag instanceof C0666p) {
                C0666p pVar = (C0666p) tag;
                if (pVar.f2149j != null) {
                    pVar.f2146g = !pVar.f2146g;
                    Page_QMS_List.this.tabLoaded(true);
                    return;
                }
                DocumentManager.getResultRequest(new LoadDialogsRequest(pVar.userId));
            } else if (tag instanceof Document) {
                int intValue = ((Document) tag).getInt(0);
                if (TextUtils.isEmpty(Page_QMS_List.this.searchText) || Page_QMS_List.this.searchText.length() < 3) {
                    Page_QMS_List o0Var = Page_QMS_List.this;
                    o0Var.tab.addPage(new Page_QMS_Talk(o0Var.mainActivity, intValue));
                    return;
                }
                Page_QMS_List o0Var2 = Page_QMS_List.this;
                o0Var2.tab.addPage(new Page_QMS_Talk(o0Var2.mainActivity, intValue, o0Var2.searchText));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            Object tag = view.getTag();
            if (tag instanceof C0666p) {
                C0666p pVar = (C0666p) tag;
                if (pVar.userId > 0) {
                    OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_QMS_List.this.mainActivity, new C0663a(pVar), true);
                    o1Var.addMenuItem(0, 0, 1, "Создать диалог");
                    o1Var.addMenuItem(0, 0, 2, "Профиль пользователя");
                    o1Var.addMenuItem(0, 0, 3, "Ссылка на профиль");
                    if (pVar.f2147h) {
                        o1Var.addMenuItem(0, 0, 5, "В черный список", true);
                    } else {
                        o1Var.addMenuItem(0, 0, 4, "В черный список", false);
                    }
                    o1Var.addMenuItem(0, 0, 6, "Удалить все диалоги");
                    o1Var.show(null);
                }
            } else if (tag instanceof Document) {
                OptionPoupupMenuView o1Var2 = new OptionPoupupMenuView(Page_QMS_List.this.mainActivity, new C0664b((Document) tag), true);
                o1Var2.addMenuItem(0, 0, 7, "Отметить прочитанным");
                o1Var2.addMenuItem(0, 0, 8, "Удалить диалог");
                o1Var2.show(null);
            }
            return true;
        }

        View$OnClickListenerC0662n(Page_QMS_List o0Var, View$OnClickListenerC0647a aVar) {
            this();
        }
    }

    private static class CreateDialogQmsRequest extends API.QmsPostRequest {
        MainActivity f2138l;
        MainLayout f2139m;

        CreateDialogQmsRequest(MainActivity mainActivity, int userId, String dialogTheme, String message) {
            super(userId, dialogTheme, message);
            this.f2138l = mainActivity;
            this.f2139m = mainActivity.mainLayout;
            this.statusMessage = "Создание диалога";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (status == 0) {
                this.f2139m.tab.addPage(new Page_QMS_Talk(this.f2138l, uVar.getInt(0)));
            } else {
                Toast.makeText(this.f2138l, "Ошибка при создании диалога", 0).show();
            }
        }
    }

    public class C0666p {
        public int userId;
        public int f2141b;
        public String f2142c;
        public String f2143d;
        public int f2144e;
        public boolean f2145f;
        public boolean f2146g;
        public boolean f2147h;
        public String f2148i;
        public List<Document> f2149j;

        public C0666p(Page_QMS_List o0Var, Document uVar) {
            this.userId = uVar.getInt(0);
            this.f2141b = uVar.getInt(6);
            int i = this.userId;
            boolean z = true;
            this.f2147h = i < 0;
            this.userId = Math.abs(i);
            this.f2142c = Util.C0427h.UnEscapeString(uVar.getString(1));
            this.f2143d = API.userGroups.get(uVar.getInt(2));
            this.f2144e = Util.C0424f.m646c(Skin.C0353a.f1398n0[uVar.getInt(2)], Skin.C0353a.f1370Z);
            this.f2148i = uVar.getString(3);
            this.f2145f = ((int) (System.currentTimeMillis() / 1000)) - uVar.getInt(4) < 900 && z;
            Document l = uVar.getDocument(7);
            if (l.count() > 0) {
                this.f2149j = new Vector(l.count());
                for (int i2 = 0; i2 < l.count(); i2++) {
                    Document l2 = l.getDocument(i2);
                    l2.addString(3, Util.C0427h.UnEscapeString(l2.getString(3)));
                    this.f2149j.add(l2);
                }
            }
        }
    }

    public Page_QMS_List(MainActivity mainActivity) {
        this(mainActivity, -1);
    }

    private void m506e0(int i, String str) {
        this.iconId = R.drawable.ic_nav_qms;
        if (!TextUtils.isEmpty(str)) {
            this.title = "Поиск сообщений";
            this.statusMessage = "Поиск сообщений";
        } else {
            this.title = "Контакты";
            this.statusMessage = "Загрузка контактов";
        }
        this.f2102E = new View$OnClickListenerC0662n(this, null);
        this.f2104G = i;
        this.searchText = str;
    }

    public static CustomDialog m505f0(MainActivity mainActivity, int i, String str) {
        View inflate = mainActivity.getLayoutInflater().inflate(R.layout.dlg_new_talk, (ViewGroup) null);
        Widgets$MemberView widgets$MemberView = inflate.findViewById(R.id.memberEdit);
        EditText editText = inflate.findViewById(R.id.dlg_new_talk_title);
        EditText editText2 = inflate.findViewById(R.id.dlg_new_talk_msg);
        CustomDialog l1Var = new CustomDialog(mainActivity, inflate, "ОТПРАВИТЬ", null);
        l1Var.m625a(false);
        editText2.addTextChangedListener(new C0653g(l1Var, widgets$MemberView));
        widgets$MemberView.f903c = new C0654h(l1Var, editText2);
        if (!TextUtils.isEmpty(str) || i != 0) {
            editText.requestFocus();
            if (i != 0) {
                widgets$MemberView.m841e(i);
            } else {
                widgets$MemberView.m840f(str);
            }
        } else {
            widgets$MemberView.requestFocus();
        }
        l1Var.m620f(new View$OnClickListenerC0655i(editText, widgets$MemberView, mainActivity, editText2), true);
        return l1Var;
    }

    @Override
    protected boolean onPageLoadedForum() {
        Document l;
        if (this.isLoading || (l = this.currentDocument.getDocument(0)) == null) {
            return false;
        }
        this.f2103F = new Vector();
        SparseArray<C0666p> sparseArray = new SparseArray<>();
        for (int i = 0; i < l.count(); i++) {
            C0666p pVar = new C0666p(this, l.getDocument(i));
            sparseArray.put(pVar.userId, pVar);
            this.f2103F.add(pVar.userId);
            SparseArray<C0666p> sparseArray2 = this.f2105H;
            if (sparseArray2 != null) {
                C0666p pVar2 = sparseArray2.get(pVar.userId);
                if (pVar2 != null) {
                    pVar.f2148i = pVar2.f2148i;
                    if (pVar2.f2146g || pVar.userId == this.f2104G) {
                        if (pVar.f2149j == null) {
                            pVar.f2149j = pVar2.f2149j;
                            int i2 = pVar.userId;
                            if (i2 != 0) {
                                DocumentManager.getResultRequest(new LoadDialogsRequest(i2));
                            }
                        }
                        if (pVar.f2149j != null) {
                            pVar.f2146g = true;
                        }
                    }
                }
            } else {
                int i3 = pVar.userId;
                if (i3 == this.f2104G) {
                    if (pVar.f2149j != null) {
                        pVar.f2146g = true;
                    } else {
                        DocumentManager.getResultRequest(new LoadDialogsRequest(i3));
                    }
                }
                if (!(this.searchText == null || pVar.f2149j == null)) {
                    pVar.f2146g = true;
                }
            }
        }
        this.f2105H = sparseArray;
        return true;
    }

    @Override
    public void mo142J(boolean z) {
        int i = this.f2104G;
        this.f2104G = -1;
        if (z && i >= 0) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.f2103F.size()) {
                int intValue = this.f2103F.get(i2).intValue();
                if (intValue == i) {
                    m813W(i3);
                    return;
                }
                C0666p pVar = this.f2105H.get(intValue);
                i2++;
                i3 = i3 + (pVar.f2146g ? pVar.f2149j.size() : 0) + 1;
            }
            DlgSimple q1Var = new DlgSimple(this.mainActivity, "У вас нет диалогов с этим пользователем. Хотите создать?", false, "ДА", "НЕТ");
            q1Var.promtMessage.setTextSize(16.0f);
            q1Var.editText.setVisibility(8);
            q1Var.m620f(new View$OnClickListenerC0647a(i), true);
            q1Var.show(true, true, true);
        }
        super.mo142J(z);
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0652f());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            o1Var.addMenuItem(0, 0, 1, "Создать диалог");
            o1Var.addMenuItem(0, 0, 23, "В закладки", DataDB.m366k(getLink()));
            o1Var.addMenuItem(0, 0, 2, "Черный список");
        }
        o1Var.show(view);
    }

    @Override
    public void showSearchDialog(View view) {
        DlgSimple q1Var = new DlgSimple(this.mainActivity, "Поиск по сообщениям", false, "ПОИСК", "СБРОС");
        q1Var.editText.setText(this.searchText);
        q1Var.m625a(!TextUtils.isEmpty(this.searchText) && this.searchText.length() >= 3);
        q1Var.editText.addTextChangedListener(new C0648b(this, q1Var));
        this.mainActivity.mainLayout.m859w(q1Var.editText);
        q1Var.m620f(new View$OnClickListenerC0649c(q1Var), true);
        q1Var.m621e(new View$OnClickListenerC0650d(), true);
        q1Var.show(true, true, true);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f2107J) {
            tabReload();
        }
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        if (this.searchText != null) {
            return true;
        }
        Unread2.f1568b.m654a(this.f2108K);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        if (this.searchText != null) {
            return true;
        }
        Unread2.f1568b.m653b(this.f2108K);
        return true;
    }

    @Override
    public int getCount() {
        if (!isUnsucces()) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f2103F.size(); i2++) {
            C0666p pVar = this.f2105H.get(this.f2103F.get(i2).intValue());
            int i3 = 1;
            if (pVar.f2146g) {
                i3 = 1 + pVar.f2149j.size();
            }
            i += i3;
        }
        return i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f2103F.size()) {
            if (i3 == i) {
                return 0;
            }
            C0666p pVar = this.f2105H.get(this.f2103F.get(i2).intValue());
            int size = i3 + (pVar.f2146g ? pVar.f2149j.size() : 0);
            if (i <= size) {
                return 1;
            }
            i2++;
            i3 = size + 1;
        }
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        char c;
        Document uVar;
        C0666p pVar;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= this.f2103F.size()) {
                c = 65535;
                uVar = null;
                pVar = null;
                break;
            } else if (i4 == i) {
                pVar = this.f2105H.get(this.f2103F.get(i3).intValue());
                uVar = null;
                c = 0;
                break;
            } else {
                int i5 = i4 + 1;
                pVar = this.f2105H.get(this.f2103F.get(i3).intValue());
                int size = i4 + (pVar.f2146g ? pVar.f2149j.size() : 0);
                if (i <= size) {
                    uVar = pVar.f2149j.get(i - i5);
                    c = 1;
                    break;
                }
                i3++;
                i4 = size + 1;
            }
        }
        if (view == null) {
            if (c == 0) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.qmslistuser, viewGroup, false);
            } else if (c == 1) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.qmslisttalk, viewGroup, false);
            }
        }
        int i6 = 8;
        if (c == 0) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            TextView textView = (TextView) viewGroup2.findViewById(R.id.userName);
            textView.setText(pVar.f2142c);
            ((TextView) viewGroup2.findViewById(R.id.userGroup)).setText(pVar.f2143d);
            ((TextView) viewGroup2.findViewById(R.id.userGroup)).setTextColor(pVar.f2144e);
            View findViewById = viewGroup2.findViewById(R.id.userUnread);
            findViewById.setEnabled(false);
            view.setTag(pVar);
            view.setOnClickListener(this.f2102E);
            view.setOnLongClickListener(this.f2102E);
            if (pVar.f2141b > 0) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.m736f(pVar.f2145f ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
            Widgets$AvatarView avatarView = (Widgets$AvatarView) viewGroup2.findViewById(R.id.userImage);
            boolean isEmpty = TextUtils.isEmpty(pVar.f2148i);
            int i7 = R.drawable.ic_avatar;
            if (isEmpty || (i2 = pVar.userId) == 0 || i2 == 17927) {
                PicoImg.cancel(avatarView);
                Skin e1Var = this.mainActivity.skin;
                int i8 = pVar.userId;
                if (i8 == 0 || i8 == 17927) {
                    i7 = R.drawable.ic_launcher;
                }
                avatarView.setImageDrawable(e1Var.m736f(i7));
            } else {
                PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, pVar.f2148i);
                l.to(avatarView);
                l.disableAnimation(!Prefs.f1146G);
                l.placeholder(this.mainActivity.skin.m736f(R.drawable.ic_avatar));
                l.fade(4, 200, false);
                l.sizeToView();
                l.runAsync();
            }
            viewGroup2.findViewById(R.id.userDropdown).setSelected(pVar.f2146g);
        } else if (c == 1) {
            ViewGroup viewGroup3 = (ViewGroup) view;
            TextView textView2 = (TextView) viewGroup3.findViewById(R.id.talkName);
            TextView textView3 = (TextView) viewGroup3.findViewById(R.id.talkDate);
            View findViewById2 = viewGroup3.findViewById(R.id.talkUnread);
            findViewById2.setEnabled(false);
            if (this.searchText != null) {
                textView2.setText(Page_Search.m321g0(uVar.getString(3)));
            } else {
                textView2.setText(uVar.getString(3));
            }
            if (uVar.getInt(5) > 0) {
                i6 = 0;
            }
            findViewById2.setVisibility(i6);
            if (this.searchText == null || uVar.getInt(4) <= 0) {
                textView3.setText(Util.formatDate(uVar.getInt(2)));
            } else {
                textView3.setText(Util.formatDate(uVar.getInt(2)) + " | сообщений: " + uVar.getInt(4));
            }
            viewGroup3.setTag(uVar);
            viewGroup3.setOnClickListener(this.f2102E);
            viewGroup3.setOnLongClickListener(this.f2102E);
            List<Document> list = pVar.f2149j;
            view.setPadding(0, 0, 0, list.get(list.size() - 1) == uVar ? (int) (this.mainActivity.f731b * 15.0f) : 0);
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, getLink(), "QMS", 0, true, true)};
    }

    @Override
    public String getLink() {
        if (this.f2109L == null) {
            this.f2109L = "forum/index.php?act=qms";
            if (this.searchText != null) {
                try {
                    this.f2109L += "&search=" + URLEncoder.encode(this.searchText, "windows-1251");
                } catch (UnsupportedEncodingException unused) {
                    this.f2109L += "&search=" + this.searchText.replace(' ', '+');
                }
            }
        }
        return this.f2109L;
    }

    @Override
    public void tabReload() {
        this.f2107J = false;
        super.tabReload();
    }

    public Page_QMS_List(MainActivity mainActivity, int i) {
        super(mainActivity, 27761, null);
        this.f2108K = new C0651e();
        m506e0(i, null);
    }

    public Page_QMS_List(MainActivity mainActivity, String str) {
        super(mainActivity, 27761, new Document(0, 0, str));
        this.f2108K = new C0651e();
        m506e0(-1, str);
    }
}
