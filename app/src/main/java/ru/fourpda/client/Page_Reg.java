package ru.fourpda.client;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Page_Reg extends Page {
    ViewGroup viewGroup;
    TextView regBtn;
    TextView regName;
    TextView regPass;
    TextView regPassPass;
    TextView regEmail;
    Util.AbstractC0429j<Boolean, Integer> f2475K = new C0731d();

    class View$OnClickListenerC0726a implements View.OnClickListener {
        View$OnClickListenerC0726a() {
        }

        @Override
        public void onClick(View view) {
            Page_Reg q0Var = Page_Reg.this;
            q0Var.tab.addPage(new Page_Login(q0Var.mainActivity, false));
        }
    }

    class View$OnClickListenerC0727b implements View.OnClickListener {

        class C0728a implements TextWatcher {
            final CustomDialog f2478a;

            C0728a(View$OnClickListenerC0727b bVar, CustomDialog l1Var) {
                this.f2478a = l1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                this.f2478a.m625a(editable.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0729b implements View.OnClickListener {
            final EditText f2479a;

            View$OnClickListenerC0729b(EditText editText) {
                this.f2479a = editText;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f2479a.getText().toString())) {
                    DocumentManager.getResultRequest(new MainActivity.C0187e0(Page_Reg.this.mainActivity, 5, 0, 0, "", this.f2479a.getText().toString()));
                } else {
                    Toast.makeText(Page_Reg.this.mainActivity, "Введите логин", 0).show();
                }
            }
        }

        View$OnClickListenerC0727b() {
        }

        @Override
        public void onClick(View view) {
            View inflate = Page_Reg.this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_forgot_pass, (ViewGroup) null);
            EditText editText = (EditText) inflate.findViewById(R.id.forgotpassMsg);
            CustomDialog l1Var = new CustomDialog(Page_Reg.this.mainActivity, inflate, "СБРОСИТЬ", null);
            l1Var.m625a(false);
            editText.addTextChangedListener(new C0728a(this, l1Var));
            l1Var.m620f(new View$OnClickListenerC0729b(editText), true);
            l1Var.show(true, true, true);
        }
    }

    class View$OnClickListenerC0730c implements View.OnClickListener {
        View$OnClickListenerC0730c() {
        }

        @Override
        public void onClick(View view) {
            Page_Reg.this.m386d0();
        }
    }

    class C0731d implements Util.AbstractC0429j<Boolean, Integer> {
        C0731d() {
        }

        public Boolean mo222a(Integer num) {
            if (Page_Reg.this.isUnsucces()) {
                Page_Reg.this.regBtn.setBackgroundColor(DocumentManager.isConnected3() ? -11617546 : -8355712);
            }
            return Boolean.TRUE;
        }
    }

    public class MemberRegisterRequest extends MemberCreateRequest {

        class View$OnClickListenerC0733a implements View.OnClickListener {
            View$OnClickListenerC0733a() {
            }

            @Override
            public void onClick(View view) {
                Page_Reg.this.m385e0();
            }
        }

        class View$OnClickListenerC0734b implements View.OnClickListener {
            final Page_Login.CaptchaDialog f2485a;

            View$OnClickListenerC0734b(Page_Login.CaptchaDialog iVar) {
                this.f2485a = iVar;
            }

            @Override
            public void onClick(View view) {
                try {
                    MemberRegisterRequest.this.captcha = Integer.parseInt(this.f2485a.captchaEditText.getText().toString());
                } catch (Exception unused) {
                    MemberRegisterRequest.this.captcha = 0;
                }
                DocumentManager.getResultRequest(MemberRegisterRequest.this);
            }
        }

        MemberRegisterRequest(String regName, String regEmail, String regPassword) {
            super(regName, regEmail, regPassword);
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Reg q0Var = Page_Reg.this;
            if (!q0Var.isLoading) {
                if (status == 0) {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(q0Var.mainActivity).edit();
                    edit.putString("member_name", Page_Reg.this.regName.getText().toString());
                    edit.commit();
                    Page_Reg q0Var2 = Page_Reg.this;
                    Tab f1Var = q0Var2.tab;
                    f1Var.addPage(new Page_Start(q0Var2.mainActivity));
                    f1Var.m722d();
                    Page_Reg.this.m385e0();
                } else if (12 == status) {
                    Page_Login.CaptchaDialog iVar = new Page_Login.CaptchaDialog(Page_Reg.this.mainActivity, uVar.getString(0));
                    iVar.m621e(new View$OnClickListenerC0733a(), true);
                    iVar.m620f(new View$OnClickListenerC0734b(iVar), true);
                    iVar.show(true, true, false);
                } else {
                    Toast.makeText(q0Var.mainActivity, status == 3 ? "IP в черном списке" : status == 4 ? "Логин короче 3 символов" : status == 5 ? "Логин длиннее 255 символов" : status == 6 ? "Логин занят" : status == 7 ? "Логин в черном списке" : status == 8 ? "Неправильный email" : status == 9 ? "Email занят" : status == 10 ? "Email в черном списке" : status == 11 ? "Слишком короткий пароль" : "Ошибка регистрации", 1).show();
                    Page_Reg.this.m385e0();
                }
            }
        }
    }

    public Page_Reg(MainActivity mainActivity) {
        super(mainActivity);
        this.title = "";
        ViewGroup viewGroup = (ViewGroup) this.mainActivity.getLayoutInflater().inflate(R.layout.registrationform, (ViewGroup) this.mainActivity.mainLayout, false);
        this.viewGroup = viewGroup;
        viewGroup.findViewById(R.id.regLogin).setOnClickListener(new View$OnClickListenerC0726a());
        this.viewGroup.findViewById(R.id.regForgot).setOnClickListener(new View$OnClickListenerC0727b());
        this.regBtn = this.viewGroup.findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View$OnClickListenerC0730c());
        this.regName = (TextView) this.viewGroup.findViewById(R.id.regName);
        this.regPass = (TextView) this.viewGroup.findViewById(R.id.regPass);
        this.regPassPass = (TextView) this.viewGroup.findViewById(R.id.regPassPass);
        this.regEmail = (TextView) this.viewGroup.findViewById(R.id.regEmail);
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
            this.tab.m714l(this.viewGroup, true);
            this.f2475K.mo222a(null);
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f798F = false;
            mainLayout.m868n(false);
        }
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        DocumentManager.f2754y.m657a(this.f2475K);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        DocumentManager.f2754y.m656b(this.f2475K);
        return true;
    }

    void m386d0() {
        if (!DocumentManager.isConnected3()) {
            Toast.makeText(this.mainActivity, "Нет соединения", 0).show();
        } else if (this.regName.getText().length() < 1) {
            Toast.makeText(this.mainActivity, "Введите логин", 0).show();
            this.regName.requestFocus();
        } else if (this.regPass.getText().length() < 1) {
            Toast.makeText(this.mainActivity, "Введите пароль", 0).show();
            this.regPass.requestFocus();
        } else if (this.regPassPass.getText().length() < 1) {
            Toast.makeText(this.mainActivity, "Введите пароль ещё раз", 0).show();
            this.regPassPass.requestFocus();
        } else if (this.regEmail.getText().length() < 1) {
            Toast.makeText(this.mainActivity, "Введите email", 0).show();
            this.regEmail.requestFocus();
        } else if (!this.regPass.getText().toString().equals(this.regPassPass.getText().toString())) {
            Toast.makeText(this.mainActivity, "Введенные пароли не совпадают", 0).show();
            this.regPassPass.requestFocus();
        } else if (!Urls2.isEmailAddress(this.regEmail.getText().toString())) {
            Toast.makeText(this.mainActivity, "Некорректный email", 0).show();
            this.regEmail.requestFocus();
        } else if (DocumentManager.getResultRequest(new MemberRegisterRequest(this.regName.getText().toString(), this.regEmail.getText().toString(), this.regPass.getText().toString())) != 0) {
            this.regBtn.setVisibility(4);
            this.viewGroup.findViewById(R.id.regLogin).setVisibility(4);
            this.viewGroup.findViewById(R.id.regForgot).setVisibility(4);
            this.regName.setEnabled(false);
            this.regPass.setEnabled(false);
            this.regPassPass.setEnabled(false);
            this.regEmail.setEnabled(false);
        } else {
            Toast.makeText(this.mainActivity, "Ошибка запроса", 0).show();
        }
    }

    void m385e0() {
        this.regBtn.setVisibility(0);
        this.viewGroup.findViewById(R.id.regLogin).setVisibility(0);
        this.viewGroup.findViewById(R.id.regForgot).setVisibility(0);
        this.regName.setEnabled(true);
        this.regPass.setEnabled(true);
        this.regPassPass.setEnabled(true);
        this.regEmail.setEnabled(true);
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
        return "forum/index.php?act=auth&action=registration&app-step=2";
    }

    @Override
    public void tabReload() {
        tabLoaded(true);
    }
}
