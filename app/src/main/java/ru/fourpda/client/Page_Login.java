package ru.fourpda.client;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_Login extends Page {
    boolean resetPassword;
    ViewGroup viewGroup;
    Util.AbstractC0429j<Boolean, Integer> f1670G = new C0440g();

    public class View$OnClickListenerC0434a implements View.OnClickListener {
        View$OnClickListenerC0434a() {
//            Page_Login.this
        }

        @Override
        public void onClick(View view) {
            Page_Login j0Var = Page_Login.this;
            j0Var.tab.addPage(new Page_PreReg(j0Var.mainActivity));
        }
    }

    public class View$OnClickListenerC0435b implements View.OnClickListener {
        View$OnClickListenerC0435b() {
//            Page_Login.this
        }

        @Override
        public void onClick(View view) {
            Page_Login.this.m635f0();
        }
    }

    public class View$OnClickListenerC0436c implements View.OnClickListener {
        View$OnClickListenerC0436c() {
//            Page_Login.this
        }

        @Override
        public void onClick(View view) {
            Page_Login.this.m637d0();
        }
    }

    public class View$OnClickListenerC0437d implements View.OnClickListener {
        View$OnClickListenerC0437d() {
//            Page_Login.this
        }

        @Override
        public void onClick(View view) {
            Page_Login.this.m636e0();
        }
    }

    public class C0438e implements TextWatcher {
        final CustomDialog f1675a;

        C0438e(Page_Login j0Var, CustomDialog l1Var) {
            this.f1675a = l1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1675a.f1804b.setEnabled(!TextUtils.isEmpty(editable.toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class View$OnClickListenerC0439f implements View.OnClickListener {
        final EditText f1676a;

        View$OnClickListenerC0439f(EditText editText) {
//            Page_Login.this
            this.f1676a = editText;
        }

        @Override
        public void onClick(View view) {
            String login = this.f1676a.getText().toString();
            if (!TextUtils.isEmpty(login)) {
                DocumentManager.getResultRequest(new MainActivity.C0187e0(Page_Login.this.mainActivity, 5, 0, 0, login, ""));
            } else {
                Toast.makeText(Page_Login.this.mainActivity, "Введите логин", 0).show();
            }
        }
    }

    public class C0440g implements Util.AbstractC0429j<Boolean, Integer> {
        C0440g() {
//            Page_Login.this
        }

        public Boolean mo222a(Integer num) {
            if (Page_Login.this.isUnsucces()) {
                TextView textView = (TextView) Page_Login.this.viewGroup.findViewById(R.id.loginBtn);
                TextView textView2 = (TextView) Page_Login.this.viewGroup.findViewById(R.id.loginBtnFree);
                boolean z = DocumentManager.isLoggined() || DocumentManager.isConnected3();
                boolean Y = DocumentManager.isConnected3();
                textView.setEnabled(z);
                textView2.setEnabled(Y);
                int i = 0xff4ebaf6;
                textView.setBackgroundColor(z ? 0xff4ebaf6 : 0xff808080);
                if (!Y) {
                    i = 0xff808080;
                }
                textView2.setBackgroundColor(i);
                Page_Login.this.initLoginData(false);
            }
            return Boolean.TRUE;
        }
    }

    public class View$OnClickListenerC0441h implements View.OnClickListener {
        View$OnClickListenerC0441h() {
//            Page_Login.this
        }

        @Override
        public void onClick(View view) {
            DocumentManager.initAuthenticate();
            Page_Login.this.tab.mainLayout.m880b();
            Page_Login.this.tab.m722d();
            new Page_Start(Page_Login.this.mainActivity);
            Page_Login.this.initLoginData(true);
        }
    }

    public static class CaptchaDialog extends CustomDialog {
        EditText captchaEditText = (EditText) this.rootView.findViewById(R.id.promtInput);

        public CaptchaDialog(MainActivity mainActivity, String str) {
            super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_captcha, (ViewGroup) null), null, null);
            PicoImgRequest l = PicoImg.loadUrl(mainActivity, str);
            l.to((ImageView) this.rootView.findViewById(R.id.promtImage));
            l.runAsync();
        }
    }

    public class CaptchaRequest extends MemberLoginRequest {

        class View$OnClickListenerC0444a implements View.OnClickListener {
            View$OnClickListenerC0444a() {
//                CaptchaRequest.this
            }

            @Override
            public void onClick(View view) {
                Page_Login.this.reset();
            }
        }

        class View$OnClickListenerC0445b implements View.OnClickListener {
            final CaptchaDialog f1683a;

            View$OnClickListenerC0445b(CaptchaDialog iVar) {
//                CaptchaRequest.this
                this.f1683a = iVar;
            }

            @Override
            public void onClick(View view) {
                try {
                    CaptchaRequest.this.captcha = Integer.parseInt(this.f1683a.captchaEditText.getText().toString());
                } catch (Exception unused) {
                    CaptchaRequest.this.captcha = 0;
                }
                DocumentManager.getResultRequest(CaptchaRequest.this);
            }
        }

        CaptchaRequest(String memberName, String memberPassword, boolean isHidden) {
            super(memberName, memberPassword, isHidden);
//            Page_Login.this
        }

        @Override
        void prepareResult(int status, Document document) {
            Page_Login pageLogin = Page_Login.this;
            if (!pageLogin.isLoading) {
                if (status == 0) {
                    pageLogin.initLoginData(false);
                    Page_Login.this.reset();
                    Toast.makeText(Page_Login.this.mainActivity, "Вход выполнен", 0).show();
                    Page_Login j0Var2 = Page_Login.this;
                    Tab f1Var = j0Var2.tab;
                    f1Var.addPage(new Page_Start(j0Var2.mainActivity));
                    f1Var.m722d();
                } else if (4 == status) {
                    CaptchaDialog iVar = new CaptchaDialog(Page_Login.this.mainActivity, document.getString(0));
                    iVar.m621e(new View$OnClickListenerC0444a(), true);
                    iVar.m620f(new View$OnClickListenerC0445b(iVar), true);
                    iVar.show(true, true, false);
                } else {
                    Toast.makeText(pageLogin.mainActivity, "Неверный логин или пароль.", 1).show();
                    Page_Login.this.reset();
                }
            }
        }
    }

    public Page_Login(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        this.title = "";
        ViewGroup viewGroup = (ViewGroup) this.mainActivity.getLayoutInflater().inflate(R.layout.loginform, (ViewGroup) this.mainActivity.mainLayout, false);
        this.viewGroup = viewGroup;
        viewGroup.findViewById(R.id.loginReg).setOnClickListener(new View$OnClickListenerC0434a());
        this.viewGroup.findViewById(R.id.loginForgot).setOnClickListener(new View$OnClickListenerC0435b());
        this.viewGroup.findViewById(R.id.loginBtn).setOnClickListener(new View$OnClickListenerC0436c());
        this.viewGroup.findViewById(R.id.loginBtnFree).setOnClickListener(new View$OnClickListenerC0437d());
        ((TextView) this.viewGroup.findViewById(R.id.loginHide)).setTextColor(-1);
        this.resetPassword = z;
    }

    @Override
    public void onSearchBar() {
        if (this.viewGroup != null) {
            this.tab.m714l(null, false);
        }
        this.tab.mainLayout.f798F = true;
        super.onSearchBar();
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.mainActivity.mainLayout.m880b();
            this.tab.m722d();
            this.tab.m714l(this.viewGroup, true);
            MainLayout mainLayout = this.tab.mainLayout;
            mainLayout.f798F = false;
            mainLayout.m868n(false);
            if (this.resetPassword) {
                m635f0();
            }
        }
        this.f1670G.mo222a(null);
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        DocumentManager.f2754y.m657a(this.f1670G);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        DocumentManager.f2754y.m656b(this.f1670G);
        return true;
    }

    void m637d0() {
        if (DocumentManager.isLoggined()) {
            DlgSimple q1Var = new DlgSimple(this.mainActivity, "Будут закрыты все вкладки.", false, null, null);
            q1Var.editText.setVisibility(8);
            q1Var.m620f(new View$OnClickListenerC0441h(), true);
            q1Var.show(true, true, true);
            return;
        }
        String memberName = ((EditText) this.viewGroup.findViewById(R.id.loginName)).getText().toString();
        String memberPassword = ((EditText) this.viewGroup.findViewById(R.id.loginPass)).getText().toString();
        boolean isHidden = ((Widgets$CheckboxTextView) this.viewGroup.findViewById(R.id.loginHide)).getChecked();
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.mainActivity).edit();
        edit.putString("member_name", memberName);
        edit.putBoolean("member_hidden", isHidden);
        edit.commit();
        if (TextUtils.isEmpty(memberName)) {
            Toast.makeText(this.mainActivity, "Введите логин", 0).show();
            this.viewGroup.findViewById(R.id.loginName).requestFocus();
        } else if (TextUtils.isEmpty(memberPassword)) {
            Toast.makeText(this.mainActivity, "Введите пароль", 0).show();
            this.viewGroup.findViewById(R.id.loginPass).requestFocus();
        } else if (DocumentManager.getResultRequest(new CaptchaRequest(memberName, memberPassword, isHidden)) > 0) {
            this.viewGroup.findViewById(R.id.loginBtn).setVisibility(4);
            this.viewGroup.findViewById(R.id.loginBtnFree).setVisibility(4);
            this.viewGroup.findViewById(R.id.loginForgot).setVisibility(4);
            this.viewGroup.findViewById(R.id.loginReg).setVisibility(4);
            this.viewGroup.findViewById(R.id.loginName).setEnabled(false);
            this.viewGroup.findViewById(R.id.loginPass).setEnabled(false);
        }
    }

    void m636e0() {
        Tab f1Var = this.tab;
        f1Var.addPage(new Page_Start(this.mainActivity));
        f1Var.m722d();
    }

    public void m635f0() {
        this.resetPassword = false;
        View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_forgot_pass, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.forgotpassMsg);
        CustomDialog l1Var = new CustomDialog(this.mainActivity, inflate, "СБРОСИТЬ", null);
        l1Var.m625a(false);
        editText.addTextChangedListener(new C0438e(this, l1Var));
        l1Var.m620f(new View$OnClickListenerC0439f(editText), true);
        l1Var.show(true, true, true);
    }

    void initLoginData(boolean z) {
        try {
            EditText loginEdit = (EditText) this.viewGroup.findViewById(R.id.loginName);
            EditText passEdit = (EditText) this.viewGroup.findViewById(R.id.loginPass);
            Widgets$CheckboxTextView hiddenCb = (Widgets$CheckboxTextView) this.viewGroup.findViewById(R.id.loginHide);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mainActivity);
            if (TextUtils.isEmpty(loginEdit.getText().toString())) {
                loginEdit.setText(defaultSharedPreferences.getString("member_name", ""));
            }
            hiddenCb.setChecked(defaultSharedPreferences.getBoolean("member_hidden", false));
            if (DocumentManager.isLoggined()) {
                loginEdit.setEnabled(false);
                passEdit.setText("*****");
                passEdit.setEnabled(false);
                return;
            }
            loginEdit.setEnabled(true);
            if (z) {
                passEdit.setText("");
            }
            passEdit.setEnabled(true);
        } catch (Exception unused) {
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

    void reset() {
        this.viewGroup.findViewById(R.id.loginBtn).setVisibility(0);
        this.viewGroup.findViewById(R.id.loginBtnFree).setVisibility(0);
        this.viewGroup.findViewById(R.id.loginForgot).setVisibility(0);
        this.viewGroup.findViewById(R.id.loginReg).setVisibility(0);
        this.viewGroup.findViewById(R.id.loginName).setEnabled(true);
        this.viewGroup.findViewById(R.id.loginPass).setEnabled(true);
    }

    @Override
    public String getLink() {
        StringBuilder sb = new StringBuilder();
        sb.append("forum/index.php?act=auth&action=");
        sb.append(this.resetPassword ? "lostpass" : "login");
        return sb.toString();
    }

    @Override
    public void tabReload() {
        tabLoaded(true);
    }
}
