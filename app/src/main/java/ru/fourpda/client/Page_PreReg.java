package ru.fourpda.client;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Page_PreReg extends Page implements BBDisplay.IBBDisplayCallback {
    ViewGroup f1816E;
    BBDisplay f1817F;
    BBString f1818G;
    RelativeLayout f1819H;
    TextView f1820I;
    TextView f1821J;
    Widgets$CheckboxTextView f1822K;
    Widgets$CheckboxTextView f1823L;

    class View$OnClickListenerC0536a implements View.OnClickListener {
        View$OnClickListenerC0536a() {
        }

        @Override
        public void onClick(View view) {
            Page_PreReg m0Var = Page_PreReg.this;
            m0Var.tab.addPage(new Page_Login(m0Var.mainActivity, false));
        }
    }

    class View$OnClickListenerC0537b implements View.OnClickListener {
        View$OnClickListenerC0537b() {
        }

        @Override
        public void onClick(View view) {
            Page_PreReg m0Var = Page_PreReg.this;
            if (m0Var.f1818G != null) {
                m0Var.f1819H.setVisibility(8);
                Page_PreReg m0Var2 = Page_PreReg.this;
                m0Var2.f1817F.setBBString(m0Var2.f1818G);
            } else if (DocumentManager.getResultRequest(new LoadForumRulesRequest()) != 0) {
                view.setVisibility(4);
            }
        }
    }

    class View$OnClickListenerC0538c implements View.OnClickListener {

        class C0539a implements TextWatcher {
            final CustomDialog f1827a;

            C0539a(View$OnClickListenerC0538c cVar, CustomDialog l1Var) {
                this.f1827a = l1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                this.f1827a.m625a(editable.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0540b implements View.OnClickListener {
            final EditText f1828a;

            View$OnClickListenerC0540b(EditText editText) {
                this.f1828a = editText;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f1828a.getText().toString())) {
                    DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(Page_PreReg.this.mainActivity, 5, 0, 0, "", this.f1828a.getText().toString()));
                } else {
                    Toast.makeText(Page_PreReg.this.mainActivity, "Введите логин", 0).show();
                }
            }
        }

        View$OnClickListenerC0538c() {
        }

        @Override
        public void onClick(View view) {
            View inflate = Page_PreReg.this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_forgot_pass, (ViewGroup) null);
            EditText editText = (EditText) inflate.findViewById(R.id.forgotpassMsg);
            CustomDialog l1Var = new CustomDialog(Page_PreReg.this.mainActivity, inflate, "СБРОСИТЬ", null);
            l1Var.m625a(false);
            editText.addTextChangedListener(new C0539a(this, l1Var));
            l1Var.m620f(new View$OnClickListenerC0540b(editText), true);
            l1Var.show(true, true, true);
        }
    }

    class View$OnClickListenerC0541d implements View.OnClickListener {
        View$OnClickListenerC0541d() {
        }

        @Override
        public void onClick(View view) {
            Page_PreReg.this.f1823L.setChecked(!((Widgets$CheckboxTextView) view).getChecked());
            Page_PreReg.this.f1821J.setBackgroundColor(-11617546);
        }
    }

    class View$OnClickListenerC0542e implements View.OnClickListener {
        View$OnClickListenerC0542e() {
        }

        @Override
        public void onClick(View view) {
            Page_PreReg.this.f1822K.setChecked(!((Widgets$CheckboxTextView) view).getChecked());
            Page_PreReg.this.f1821J.setBackgroundColor(-11617546);
        }
    }

    class View$OnClickListenerC0543f implements View.OnClickListener {
        View$OnClickListenerC0543f() {
        }

        @Override
        public void onClick(View view) {
            if (Page_PreReg.this.f1822K.getChecked() || Page_PreReg.this.f1823L.getChecked()) {
                Page_PreReg m0Var = Page_PreReg.this;
                m0Var.tab.addPage(new Page_Reg(m0Var.mainActivity));
                return;
            }
            Toast.makeText(Page_PreReg.this.mainActivity, "Выберите один из вариантов.", 0).show();
        }
    }

    class LoadForumRulesRequest extends API.ForumAnnouncementRequest {
        LoadForumRulesRequest() {
            super(0);
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_PreReg m0Var = Page_PreReg.this;
            if (!m0Var.isLoading) {
                if (status == 0) {
                    m0Var.f1818G = BBString.getBBString(uVar.getString(1), null);
                    Page_PreReg m0Var2 = Page_PreReg.this;
                    BBString pVar = m0Var2.f1818G;
                    if (pVar != null) {
                        int i2 = (int) (m0Var2.mainActivity.f731b * 16.0f);
                        BBString.C0674e eVar = pVar.f2246z;
                        float f = (float) i2;
                        eVar.f2264h = f;
                        eVar.f2263g = f;
                        eVar.f2266j = i2;
                        eVar.f2265i = i2;
                        m0Var2.f1817F.setBBString(pVar);
                        Page_PreReg.this.f1819H.setVisibility(8);
                        Page_PreReg.this.f1817F.setVisibility(0);
                    }
                } else {
                    Toast.makeText(m0Var.mainActivity, "Ошибка загрузки правил.", 1).show();
                }
                Page_PreReg.this.f1820I.setVisibility(0);
            }
        }
    }

    public Page_PreReg(MainActivity mainActivity) {
        super(mainActivity);
        ViewGroup viewGroup = (ViewGroup) this.mainActivity.getLayoutInflater().inflate(R.layout.preregform, (ViewGroup) this.mainActivity.mainLayout, false);
        this.f1816E = viewGroup;
        this.f1819H = (RelativeLayout) viewGroup.findViewById(R.id.preregLayout);
        BBDisplay bBDisplay = (BBDisplay) this.f1816E.findViewById(R.id.preregRules);
        this.f1817F = bBDisplay;
        bBDisplay.setBackgroundColor(Skin.SkinColorModel.mainBgColor);
        this.f1817F.setCallback(this);
        this.f1816E.findViewById(R.id.preregLogin).setOnClickListener(new View$OnClickListenerC0536a());
        TextView textView = (TextView) this.f1816E.findViewById(R.id.preregRulesBtn);
        this.f1820I = textView;
        textView.setOnClickListener(new View$OnClickListenerC0537b());
        this.f1816E.findViewById(R.id.preregForgot).setOnClickListener(new View$OnClickListenerC0538c());
        this.f1821J = (TextView) this.f1816E.findViewById(R.id.preregNextBtn);
        this.f1822K = (Widgets$CheckboxTextView) this.f1816E.findViewById(R.id.preregRulesReaded);
        this.f1823L = (Widgets$CheckboxTextView) this.f1816E.findViewById(R.id.preregRulesAgree);
        this.f1822K.setTextColor(-1);
        this.f1823L.setTextColor(-1);
        this.f1822K.setOnClickListener(new View$OnClickListenerC0541d());
        this.f1823L.setOnClickListener(new View$OnClickListenerC0542e());
        this.f1821J.setOnClickListener(new View$OnClickListenerC0543f());
    }

    @Override
    public boolean mo145B() {
        if (this.f1818G == null || this.f1817F.getVisibility() != 0) {
            return false;
        }
        this.f1817F.setVisibility(8);
        this.f1819H.setVisibility(0);
        return true;
    }

    @Override
    public void onSearchBar() {
        this.tab.m714l(null, false);
        this.tab.mainLayout.f798F = true;
        super.onSearchBar();
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.mainActivity.mainLayout.m880b();
            this.tab.m722d();
            this.tab.m714l(this.f1816E, true);
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f798F = false;
            mainLayout.m868n(false);
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            if (i2 == 1) {
                Urls2.visitPage(this.mainActivity, kVar.link);
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }

    @Override
    public String getLink() {
        return "forum/index.php?act=auth&action=registration";
    }

    @Override
    public void tabReload() {
        tabLoaded(true);
    }
}
