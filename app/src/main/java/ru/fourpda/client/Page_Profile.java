package ru.fourpda.client;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class Page_Profile extends Page implements BBDisplay.IBBDisplayCallback, Form_Post.AbstractC0846i {
    public int userId;
    Widgets$AvatarView avatarView;
    BBString profileSignBBCode;
    BBString profileDeicesBBCode;
    BBString profileBioBBCode;
    BBString profileCurDataBBCode;
    boolean isAdvancedView;
    List<BBString> profileWarnsBBCode;
    List<String> profileWarnsDate;
    boolean isCancelPunishment;
    int f1900O;
    EditProfilePage editProfilePage;
    private Form_Post.ForumPostModel forumPostModel;
    private Form_Post formPost;
    boolean f1901P = false;
    Util.AbstractC0429j<Boolean, Document> f1905T = new C0573m();
    Util.AbstractC0429j<Boolean, Document> f1906U = new C0574n();
    Util.AbstractC0430k<Boolean, String, Integer> f1907V = new C0575o();

    class C0563c implements OptionPoupupMenuView.IClickListener {
        C0563c() {
//            Page_Profile.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Page_Profile.this.m577f0(i, i2, i3);
        }
    }

    public class C0564d implements TextWatcher {
        final CustomDialog f1913a;
        final TextView f1914b;
        final EditText f1915c;

        C0564d(Page_Profile n0Var, CustomDialog l1Var, TextView textView, EditText editText) {
            this.f1913a = l1Var;
            this.f1914b = textView;
            this.f1915c = editText;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1913a.m625a(!TextUtils.isEmpty(this.f1914b.getText().toString()) && !TextUtils.isEmpty(this.f1915c.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class C0566f implements TextWatcher {
        final CustomDialog f1919a;
        final TextView f1920b;
        final EditText f1921c;

        C0566f(Page_Profile n0Var, CustomDialog l1Var, TextView textView, EditText editText) {
            this.f1919a = l1Var;
            this.f1920b = textView;
            this.f1921c = editText;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1919a.m625a(!TextUtils.isEmpty(this.f1920b.getText().toString()) && !TextUtils.isEmpty(this.f1921c.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class C0568h implements TextWatcher {
        final CustomDialog f1925a;
        final EditText f1926b;
        final EditText f1927c;
        final EditText f1928d;

        C0568h(Page_Profile n0Var, CustomDialog l1Var, EditText editText, EditText editText2, EditText editText3) {
            this.f1925a = l1Var;
            this.f1926b = editText;
            this.f1927c = editText2;
            this.f1928d = editText3;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1925a.m625a(!TextUtils.isEmpty(this.f1926b.getText().toString()) && !TextUtils.isEmpty(this.f1927c.getText().toString()) && !TextUtils.isEmpty(this.f1928d.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class C0570j implements TextWatcher {
        final DlgSimple f1933a;

        C0570j(Page_Profile n0Var, DlgSimple q1Var) {
            this.f1933a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1933a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class View$OnClickListenerC0560a implements View.OnClickListener {
        View$OnClickListenerC0560a() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            Page_Profile n0Var = Page_Profile.this;
            Tab f1Var = n0Var.tab;
            MainActivity mainActivity = n0Var.mainActivity;
            int i = n0Var.userId;
            f1Var.addPage(new Page_Search(mainActivity, 524289, 0, 0, i, "", 0, "посты пользователя " + Page_Profile.this.title));
        }
    }

    class View$OnClickListenerC0562b implements View.OnClickListener {
        final String f1910a;

        View$OnClickListenerC0562b(String str) {
//            Page_Profile.this
            this.f1910a = str;
        }

        @Override
        public void onClick(View view) {
            Page b = Urls2.openPage(Page_Profile.this.mainActivity, this.f1910a, false, 1);
            if (b != null) {
                Page_Profile.this.tab.addPage(b);
            }
        }
    }

    public class View$OnClickListenerC0565e implements View.OnClickListener {
        final TextView f1916a;
        final EditText f1917b;

        View$OnClickListenerC0565e(TextView textView, EditText editText) {
//            Page_Profile.this
            this.f1916a = textView;
            this.f1917b = editText;
        }

        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.f1916a.getText().toString()) || TextUtils.isEmpty(this.f1917b.getText().toString())) {
                Toast.makeText(Page_Profile.this.mainActivity, "Введите логин", 0).show();
            } else {
                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(Page_Profile.this.mainActivity, 7, 0, 0, this.f1917b.getText().toString(), this.f1916a.getText().toString()));
            }
        }
    }

    public class View$OnClickListenerC0567g implements View.OnClickListener {
        final TextView f1922a;
        final EditText f1923b;

        View$OnClickListenerC0567g(TextView textView, EditText editText) {
//            Page_Profile.this
            this.f1922a = textView;
            this.f1923b = editText;
        }

        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.f1922a.getText().toString()) || TextUtils.isEmpty(this.f1923b.getText().toString())) {
                Toast.makeText(Page_Profile.this.mainActivity, "Введите e-mail", 0).show();
            } else if (!Urls2.isEmailAddress(this.f1922a.getText().toString())) {
                Toast.makeText(Page_Profile.this.mainActivity, "Некорректный email", 0).show();
                this.f1922a.requestFocus();
            } else {
                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(Page_Profile.this.mainActivity, 2, 0, 0, this.f1923b.getText().toString(), this.f1922a.getText().toString()));
            }
        }
    }

    public class View$OnClickListenerC0569i implements View.OnClickListener {
        final EditText f1929a;
        final EditText f1930b;
        final EditText f1931c;

        View$OnClickListenerC0569i(EditText editText, EditText editText2, EditText editText3) {
//            Page_Profile.this
            this.f1929a = editText;
            this.f1930b = editText2;
            this.f1931c = editText3;
        }

        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.f1929a.getText().toString()) || TextUtils.isEmpty(this.f1930b.getText().toString()) || TextUtils.isEmpty(this.f1931c.getText().toString())) {
                Toast.makeText(Page_Profile.this.mainActivity, "Введите пароль", 0).show();
            } else if (this.f1930b.getText().toString().equals(this.f1931c.getText().toString())) {
                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(Page_Profile.this.mainActivity, 4, 0, 0, this.f1929a.getText().toString(), this.f1930b.getText().toString()));
            } else {
                Toast.makeText(Page_Profile.this.mainActivity, "Новые пароли не совпадают", 0).show();
            }
        }
    }

    class View$OnClickListenerC0571k implements View.OnClickListener {
        final String f1934a;

        View$OnClickListenerC0571k(String str) {
//            Page_Profile.this
            this.f1934a = str;
        }

        @Override
        public void onClick(View view) {
            new ImageDialog(Page_Profile.this.mainActivity).m917b(this.f1934a);
        }
    }

    public class View$OnClickListenerC0572l implements View.OnClickListener {
        final DlgSimple f1936a;

        View$OnClickListenerC0572l(DlgSimple q1Var) {
//            Page_Profile.this
            this.f1936a = q1Var;
        }

        @Override
        public void onClick(View view) {
            Page_Profile n0Var = Page_Profile.this;
            DocumentManager.getResultRequest(new MemberPunishmentRequest(n0Var.mainActivity, n0Var.userId, this.f1936a.editText.getText().toString()));
        }
    }

    class View$OnLongClickListenerC0576p implements View.OnLongClickListener {
        View$OnLongClickListenerC0576p() {
//            Page_Profile.this
        }

        @Override
        public boolean onLongClick(View view) {
            Util.copyToClipboard(Page_Profile.this.mainActivity, ((TextView) view).getText().toString(), "Ник скопирован в буфер обмена");
            return true;
        }
    }

    class View$OnClickListenerC0577q implements View.OnClickListener {
        View$OnClickListenerC0577q() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            Util.copyToClipboard(Page_Profile.this.mainActivity, ((TextView) view).getText().toString(), "Адрес почты скопирован в буфер обмена");
        }
    }

    class View$OnClickListenerC0578r implements View.OnClickListener {
        View$OnClickListenerC0578r() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            Page_Profile n0Var = Page_Profile.this;
            n0Var.tab.addPage(new Page_QMS_List(n0Var.mainActivity, n0Var.userId));
        }
    }

    class View$OnClickListenerC0579s implements View.OnClickListener {
        final String f1944a;

        View$OnClickListenerC0579s(String str) {
//            Page_Profile.this
            this.f1944a = str;
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Profile.this.mainActivity;
            String str = this.f1944a;
            Util.sendMail(mainActivity, str, "4PDA " + Page_Profile.this.title);
        }
    }

    public class View$OnClickListenerC0580t implements View.OnClickListener {

        class C0581a implements TextWatcher {
            final DlgSimple f1947a;

            C0581a(View$OnClickListenerC0580t tVar, DlgSimple q1Var) {
                this.f1947a = q1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                this.f1947a.m625a(editable.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0582b implements View.OnClickListener {
            final DlgSimple f1948a;

            View$OnClickListenerC0582b(DlgSimple q1Var) {
//                View$OnClickListenerC0580t.this
                this.f1948a = q1Var;
            }

            @Override
            public void onClick(View view) {
                Page_Profile n0Var = Page_Profile.this;
                DocumentManager.getResultRequest(new MemberChangeReputationRequest(n0Var.mainActivity, n0Var.userId, 0, 0, this.f1948a.editText.getText().toString()));
            }
        }

        View$OnClickListenerC0580t() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            DlgSimple q1Var = new DlgSimple(Page_Profile.this.mainActivity, "Укажите причину понижения репутации", false, "ПОНИЗИТЬ", null);
            q1Var.m625a(false);
            q1Var.editText.addTextChangedListener(new C0581a(this, q1Var));
            q1Var.m620f(new View$OnClickListenerC0582b(q1Var), true);
            q1Var.show(true, true, true);
            Page_Profile.this.mainActivity.mainLayout.hideKeyboard(q1Var.editText);
        }
    }

    class View$OnClickListenerC0583u implements View.OnClickListener {
        View$OnClickListenerC0583u() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            Page_Profile n0Var = Page_Profile.this;
            n0Var.tab.addPage(new Page_Reputation(n0Var.mainActivity, n0Var.userId));
        }
    }

    public class View$OnClickListenerC0584v implements View.OnClickListener {

        class C0585a implements TextWatcher {
            final DlgSimple f1952a;

            C0585a(View$OnClickListenerC0584v vVar, DlgSimple q1Var) {
                this.f1952a = q1Var;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                this.f1952a.m625a(editable.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        }

        class View$OnClickListenerC0586b implements View.OnClickListener {
            final DlgSimple f1953a;

            View$OnClickListenerC0586b(DlgSimple q1Var) {
//                View$OnClickListenerC0584v.this
                this.f1953a = q1Var;
            }

            @Override
            public void onClick(View view) {
                Page_Profile n0Var = Page_Profile.this;
                DocumentManager.getResultRequest(new MemberChangeReputationRequest(n0Var.mainActivity, n0Var.userId, 1, 0, this.f1953a.editText.getText().toString()));
            }
        }

        View$OnClickListenerC0584v() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            DlgSimple q1Var = new DlgSimple(Page_Profile.this.mainActivity, "Укажите причину повышения репутации", false, "ПОДНЯТЬ", null);
            q1Var.m625a(false);
            q1Var.editText.addTextChangedListener(new C0585a(this, q1Var));
            q1Var.m620f(new View$OnClickListenerC0586b(q1Var), true);
            q1Var.show(true, true, true);
            Page_Profile.this.mainActivity.mainLayout.hideKeyboard(q1Var.editText);
        }
    }

    class View$OnClickListenerC0587w implements View.OnClickListener {
        View$OnClickListenerC0587w() {
//            Page_Profile.this
        }

        @Override
        public void onClick(View view) {
            Page_Profile n0Var = Page_Profile.this;
            Tab f1Var = n0Var.tab;
            MainActivity mainActivity = n0Var.mainActivity;
            int i = n0Var.userId;
            f1Var.addPage(new Page_Search(mainActivity, 589826, 0, 0, i, "", 0, "темы пользователя " + Page_Profile.this.title));
        }
    }

    public class C0573m implements Util.AbstractC0429j<Boolean, Document> {
        C0573m() {
//            Page_Profile.this
        }

        public Boolean mo222a(Document uVar) {
            DocumentManager.getResultRequest(new SaveMemberProfileRequest(uVar));
            Page_Profile.this.editProfilePage.m570a();
            Page_Profile n0Var = Page_Profile.this;
            n0Var.editProfilePage = null;
            n0Var.tabReload();
            return Boolean.TRUE;
        }
    }

    public class C0574n implements Util.AbstractC0429j<Boolean, Document> {
        C0574n() {
//            Page_Profile.this
        }

        public Boolean mo222a(Document uVar) {
            DocumentManager.getResultRequest(new ChangeMemberAvatarRequest(uVar));
            return Boolean.TRUE;
        }
    }

    public class C0575o implements Util.AbstractC0430k<Boolean, String, Integer> {
        C0575o() {
//            Page_Profile.this
        }

        public Boolean mo103a(String str, Integer num) {
            Page_Profile n0Var = Page_Profile.this;
            DocumentManager.getResultRequest(new ChangeMemberDeviceRequest(n0Var.userId, num.intValue(), str, num.intValue() == 2));
            return Boolean.TRUE;
        }
    }

    public class ChangeMemberAvatarRequest extends MemberAvatarRequest {
        ChangeMemberAvatarRequest(Document uVar) {
            super(uVar);
//            Page_Profile.this
        }

        @Override
        public void prepareResult(int status, Document document) {
            Page_Profile n0Var = Page_Profile.this;
            if (!n0Var.isLoading) {
                if (status == 0) {
                    String n = document.getString(0);
                    if (n != null) {
                        Page_Profile.this.currentDocument.addString(2, n);
                    }
                    if (!TextUtils.isEmpty(n)) {
                        Toast.makeText(Page_Profile.this.mainActivity, "Аватар изменен", 0).show();
                        PicoImgRequest l = PicoImg.loadUrl(Page_Profile.this.mainActivity, n);
                        l.to(Page_Profile.this.avatarView);
                        l.disableAnimation(!Prefs.animAvatars);
                        l.runAsync();
                    } else {
                        Toast.makeText(Page_Profile.this.mainActivity, "Аватар удален", 0).show();
                        Page_Profile n0Var2 = Page_Profile.this;
                        n0Var2.avatarView.setImageDrawable(n0Var2.mainActivity.skin.getSkinDrawable(R.drawable.ic_avatar));
                    }
                    DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
                    if (L != null && Page_Profile.this.userId == L.memberId) {
                        DocumentManager.isMemberValid();
                    }
                    EditProfilePage zVar = Page_Profile.this.editProfilePage;
                    if (zVar != null) {
                        zVar.m568c(n);
                        return;
                    }
                    return;
                }
                Toast.makeText(n0Var.mainActivity, "Ошибка изменения аватара", 0).show();
            }
        }
    }

    public class ChangeMemberDeviceRequest extends MemberDeviceRequest {
        ChangeMemberDeviceRequest(int i, int i2, String str, boolean z) {
            super(i, i2, str, z);
//            Page_Profile.this
            this.statusMessage = "Изменение статуса устройства";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Profile n0Var = Page_Profile.this;
            if (!n0Var.isLoading) {
                if (status == 0) {
                    n0Var.f1901P = true;
                }
                Toast.makeText(n0Var.mainActivity, status == 0 ? "Действие выполнено" : "Действие завершилось ошибкой", 0).show();
            }
        }
    }

    public class SaveMemberProfileRequest extends MemberProfileRequest {
        SaveMemberProfileRequest(Document uVar) {
            super(uVar);
//            Page_Profile.this
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Profile n0Var = Page_Profile.this;
            if (!n0Var.isLoading) {
                if (status == 0) {
                    Toast.makeText(n0Var.mainActivity, "Профиль сохранен", 0).show();
                    Page_Profile.this.tabReload();
                    return;
                }
                Toast.makeText(n0Var.mainActivity, "Ошибка изменения профиля", 0).show();
            }
        }
    }

    public static class EditProfilePage implements MainActivity.AbstractC0193g0 {
        Util.AbstractC0429j<Boolean, Document> f1958a;
        Util.AbstractC0429j<Boolean, Document> f1959b;
        Util.AbstractC0430k<Boolean, String, Integer> f1960c;
        MainActivity activity;
        Tab tab1;
        RelativeLayout relativeLayout;
        BBEditor f1964g;
        BBEditor f1965h;
        DatePickerDialog pickerDialog;
        int memberId;
        String f1968k;
        List<C0598g> f1969l;

        public class View$OnClickListenerC0591a implements View.OnClickListener {
            View$OnClickListenerC0591a() {
//                EditProfilePage.this
            }

            @Override
            public void onClick(View view) {
                EditProfilePage.this.f1959b.mo222a(new Document(EditProfilePage.this.memberId, ""));
            }
        }

        public class View$OnClickListenerC0592b implements View.OnClickListener {
            View$OnClickListenerC0592b() {
//                EditProfilePage.this
            }

            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType("*/*");
                    EditProfilePage.this.activity.m904b(Intent.createChooser(intent, "Файл-менеджер"));
                } catch (Exception unused) {
                    Toast.makeText(EditProfilePage.this.activity, "Не найден файл-менеджер", 1).show();
                }
            }
        }

        public class View$OnClickListenerC0593c implements View.OnClickListener {
            final int f1972a;
            final int f1973b;
            final int f1974c;

            class C0594a implements DatePickerDialog.OnDateSetListener {
                C0594a() {
//                    View$OnClickListenerC0593c.this
                }

                @Override
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    String str = i3 + "." + (i2 + 1) + "." + i;
                    EditProfilePage.this.f1968k = str;
                    ((TextView) EditProfilePage.this.relativeLayout.findViewById(R.id.prf_edt_date)).setText(str);
                }
            }

            View$OnClickListenerC0593c(int i, int i2, int i3) {
//                EditProfilePage.this
                this.f1972a = i;
                this.f1973b = i2;
                this.f1974c = i3;
            }

            @Override
            public void onClick(View view) {
                EditProfilePage.this.pickerDialog = new DatePickerDialog(EditProfilePage.this.activity, new C0594a(), this.f1972a, this.f1973b, this.f1974c);
                EditProfilePage.this.pickerDialog.show();
            }
        }

        public class View$OnClickListenerC0595d implements View.OnClickListener {
            View$OnClickListenerC0595d() {
//                EditProfilePage.this
            }

            @Override
            public void onClick(View view) {
                if (EditProfilePage.this.f1969l != null) {
                    for (int i = 0; i < EditProfilePage.this.f1969l.size(); i++) {
                        C0598g gVar = EditProfilePage.this.f1969l.get(i);
                        View view2 = gVar.f1983d;
                        boolean z = true;
                        view2.setSelected(view2 == view);
                        if (!gVar.f1982c && gVar.f1983d == view) {
                            EditProfilePage.this.f1960c.mo103a(gVar.f1981b, 2);
                        }
                        if (gVar.f1983d != view) {
                            z = false;
                        }
                        gVar.f1982c = z;
                    }
                }
            }
        }

        public class View$OnClickListenerC0596e implements View.OnClickListener {
            View$OnClickListenerC0596e() {
//                EditProfilePage.this
            }

            @Override
            public void onClick(View view) {
                if (EditProfilePage.this.f1969l != null) {
                    for (int i = 0; i < EditProfilePage.this.f1969l.size(); i++) {
                        C0598g gVar = EditProfilePage.this.f1969l.get(i);
                        if (gVar.f1984e == view) {
                            EditProfilePage.this.f1960c.mo103a(gVar.f1981b, 0);
                            ((LinearLayout) EditProfilePage.this.relativeLayout.findViewById(R.id.prf_edt_devices)).removeView(gVar.f1985f);
                            EditProfilePage.this.f1969l.remove(i);
                            return;
                        }
                    }
                }
            }
        }

        public class View$OnClickListenerC0597f implements View.OnClickListener {
            View$OnClickListenerC0597f() {
//                EditProfilePage.this
            }

            @Override
            public void onClick(View view) {
                String obj = ((EditText) EditProfilePage.this.relativeLayout.findViewById(R.id.prf_edt_title)).getText().toString();
                RadioGroup radioGroup = (RadioGroup) EditProfilePage.this.relativeLayout.findViewById(R.id.prf_edt_gender_radio);
                int indexOfChild = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
                EditProfilePage.this.f1958a.mo222a(new Document(Integer.valueOf(EditProfilePage.this.memberId), obj, EditProfilePage.this.f1964g.f2030d.getText().toString(), EditProfilePage.this.f1965h.f2030d.getText().toString(), EditProfilePage.this.f1968k, Integer.valueOf(indexOfChild), ((EditText) EditProfilePage.this.relativeLayout.findViewById(R.id.prf_edt_location)).getText().toString()));
            }
        }

        public class C0598g {
            public String f1980a;
            public String f1981b;
            public boolean f1982c;
            public View f1983d;
            public View f1984e;
            public View f1985f;

            C0598g(EditProfilePage zVar) {
            }
        }

        public EditProfilePage(Tab f1Var, Document uVar, Util.AbstractC0429j<Boolean, Document> jVar, Util.AbstractC0429j<Boolean, Document> jVar2, Util.AbstractC0430k<Boolean, String, Integer> kVar) {
            this.f1968k = "";
            int memberId = DocumentManager.getMemberInfoModel().memberId;
            String n = uVar.getString(1);
            this.memberId = uVar.getInt(0);
            MainActivity mainActivity = f1Var.f1443a;
            this.activity = mainActivity;
            this.tab1 = f1Var;
            this.f1958a = jVar;
            this.f1959b = jVar2;
            this.f1960c = kVar;
            float f = mainActivity.f731b;
            this.relativeLayout = new RelativeLayout(this.activity);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) "Редактирование профиля ");
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 33);
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) n);
            spannableStringBuilder.setSpan(new StyleSpan(3), length, n.length() + length, 33);
            TextView textView = new TextView(this.activity);
            textView.setId(R.id.titleId);
            textView.setTextColor(Skin.SkinColorModel.mainTextColor);
            textView.setText(spannableStringBuilder);
            textView.setTextSize(18.0f);
            textView.setBackgroundDrawable(this.tab1.f1443a.skin.getSkinDrawable(R.drawable.border_bottom));
            int i2 = (int) (24.0f * f);
            int i3 = (int) (8.0f * f);
            textView.setPadding(i2, i3, i2, (int) (10.0f * f));
            this.relativeLayout.addView(textView);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(10);
            ScrollView scrollView = new ScrollView(this.activity);
            scrollView.setId(R.id.captionID);
            this.relativeLayout.addView(scrollView);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -2;
            layoutParams2.addRule(3, R.id.titleId);
            View inflate = this.activity.getLayoutInflater().inflate(R.layout.profileeditor, (ViewGroup) scrollView, false);
            TextView textView2 = (TextView) inflate.findViewById(R.id.prf_edt_ava_delete);
            textView2.setOnClickListener(new View$OnClickListenerC0591a());
            String n2 = uVar.getString(2);
            if (!TextUtils.isEmpty(n2)) {
                PicoImgRequest l = PicoImg.loadUrl(this.activity, n2);
                l.to((Widgets$AvatarView) inflate.findViewById(R.id.prf_edt_ava));
                l.disableAnimation(!Prefs.animAvatars);
                l.runAsync();
            } else {
                textView2.setEnabled(false);
            }
            this.tab1.f1443a.m898h(this);
            if (this.memberId == memberId) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) ((TextView) inflate.findViewById(R.id.prf_edt_ava_lbl)).getLayoutParams();
                layoutParams3.height = -2;
                layoutParams3.topMargin = i3;
                TextView textView3 = (TextView) inflate.findViewById(R.id.prf_edt_ava_change);
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
                layoutParams4.height = (int) (f * 36.0f);
                layoutParams4.bottomMargin = i3;
                textView3.setOnClickListener(new View$OnClickListenerC0592b());
            }
            ((EditText) inflate.findViewById(R.id.prf_edt_title)).setText(uVar.getString(4));
            View findViewById = inflate.findViewById(R.id.prf_edt_sign_tags);
            this.f1964g = new BBEditor(this.activity, findViewById, (EditText) inflate.findViewById(R.id.prf_edt_sign), true);
            findViewById.findViewById(R.id.editor_Spoiler).setVisibility(8);
            findViewById.findViewById(R.id.editor_SpoilerNamed).setVisibility(8);
            findViewById.findViewById(R.id.editor_Code).setVisibility(8);
            findViewById.findViewById(R.id.editor_Quote).setVisibility(8);
            findViewById.findViewById(R.id.editor_List).setVisibility(8);
            findViewById.findViewById(R.id.editor_ListNumder).setVisibility(8);
            findViewById.findViewById(R.id.editor_Hide).setVisibility(8);
            findViewById.findViewById(R.id.editor_Size).setVisibility(8);
            this.f1964g.f2030d.setText(uVar.getString(5));
            View findViewById2 = inflate.findViewById(R.id.prf_edt_bio_tags);
            this.f1965h = new BBEditor(this.activity, findViewById2, (EditText) inflate.findViewById(R.id.prf_edt_bio), true);
            findViewById2.findViewById(R.id.editor_Spoiler).setVisibility(8);
            findViewById2.findViewById(R.id.editor_SpoilerNamed).setVisibility(8);
            findViewById2.findViewById(R.id.editor_Code).setVisibility(8);
            findViewById2.findViewById(R.id.editor_Quote).setVisibility(8);
            findViewById2.findViewById(R.id.editor_List).setVisibility(8);
            findViewById2.findViewById(R.id.editor_ListNumder).setVisibility(8);
            findViewById2.findViewById(R.id.editor_Hide).setVisibility(8);
            findViewById2.findViewById(R.id.editor_Size).setVisibility(8);
            this.f1965h.f2030d.setText(uVar.getString(6));
            String n3 = uVar.getString(9);
            this.f1968k = n3;
            int i4 = 1941;
            int i5 = 11;
            int i6 = 7;
            if (!TextUtils.isEmpty(n3)) {
                Matcher matcher = Pattern.compile("(?i:^(\\d{1,2}).(\\d{1,2}).(\\d{1,4}))").matcher(n3);
                if (matcher.matches()) {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    int parseInt3 = Integer.parseInt(matcher.group(3));
                    if (parseInt >= 1 && parseInt <= 31) {
                        i6 = parseInt;
                    }
                    if (parseInt2 >= 1 && parseInt2 <= 12) {
                        i5 = parseInt2;
                    }
                    if (parseInt3 >= 1900 && parseInt3 <= 2100) {
                        i4 = parseInt3;
                    }
                }
            } else {
                n3 = "не задано";
            }
            TextView textView4 = (TextView) inflate.findViewById(R.id.prf_edt_date);
            textView4.setText(n3);
            textView4.setOnClickListener(new View$OnClickListenerC0593c(i4, i5, i6));
            int intValue = uVar.getInt(10).intValue();
            if (intValue == 1) {
                ((RadioButton) inflate.findViewById(R.id.prf_edt_gender_male)).setChecked(true);
            } else if (intValue == 2) {
                ((RadioButton) inflate.findViewById(R.id.prf_edt_gender_female)).setChecked(true);
            } else {
                ((RadioButton) inflate.findViewById(R.id.prf_edt_gender_none)).setChecked(true);
            }
            ((EditText) inflate.findViewById(R.id.prf_edt_location)).setText(uVar.getString(12));
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.prf_edt_devices);
            Document l2 = uVar.getDocument(13);
            if (this.memberId == memberId && l2 != null && l2.count() > 0) {
                RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) ((TextView) inflate.findViewById(R.id.prf_edt_devices_lbl)).getLayoutParams();
                layoutParams5.height = -2;
                layoutParams5.topMargin = (int) (this.tab1.f1443a.f731b * 16.0f);
                this.f1969l = new Vector(l2.count());
                for (int i7 = 0; i7 < l2.count(); i7++) {
                    Document l3 = l2.getDocument(i7);
                    C0598g gVar = new C0598g(this);
                    this.f1969l.add(gVar);
                    gVar.f1980a = l3.getString(1);
                    gVar.f1981b = l3.getString(0);
                    gVar.f1982c = l3.getInt(2).intValue() > 0;
                    View inflate2 = this.activity.getLayoutInflater().inflate(R.layout.profile_editor_device, (ViewGroup) linearLayout, false);
                    gVar.f1985f = inflate2;
                    ((TextView) inflate2.findViewById(R.id.prf_edt_dev)).setText(gVar.f1980a);
                    View findViewById3 = inflate2.findViewById(R.id.prf_edt_primary);
                    gVar.f1983d = findViewById3;
                    findViewById3.setSelected(gVar.f1982c);
                    gVar.f1983d.setOnClickListener(new View$OnClickListenerC0595d());
                    View findViewById4 = inflate2.findViewById(R.id.prf_edt_delete);
                    gVar.f1984e = findViewById4;
                    findViewById4.setOnClickListener(new View$OnClickListenerC0596e());
                    linearLayout.addView(inflate2);
                }
            }
            inflate.findViewById(R.id.prf_edt_save).setOnClickListener(new View$OnClickListenerC0597f());
            scrollView.addView(inflate);
            this.relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            m569b();
        }

        public void m570a() {
            this.tab1.m714l(null, false);
            this.tab1.mainLayout.hideKeyboard(null);
            this.tab1.mainLayout.m868n(true);
        }

        public void m569b() {
            this.tab1.m714l(this.relativeLayout, true);
            this.tab1.mainLayout.m868n(false);
        }

        public void m568c(String avatarUrl) {
            if (this.relativeLayout != null) {
                if (!TextUtils.isEmpty(avatarUrl)) {
                    PicoImgRequest l = PicoImg.loadUrl(this.activity, avatarUrl);
                    l.to((Widgets$AvatarView) this.relativeLayout.findViewById(R.id.prf_edt_ava));
                    l.disableAnimation(!Prefs.animAvatars);
                    l.runAsync();
                } else {
                    ((Widgets$AvatarView) this.relativeLayout.findViewById(R.id.prf_edt_ava)).setImageDrawable(this.tab1.f1443a.skin.getSkinDrawable(R.drawable.ic_avatar));
                }
                TextView textView = (TextView) this.relativeLayout.findViewById(R.id.prf_edt_ava_delete);
                textView.setEnabled(TextUtils.isEmpty(avatarUrl) | textView.isEnabled());
            }
        }

        @Override
        public void mo426e(Uri uri, String str) {
            boolean z;
            long j = 0;
            try {
                InputStream openInputStream = this.activity.getContentResolver().openInputStream(uri);
                if (openInputStream != null) {
                    byte[] buffer = new byte[4096];
                    while (true) {
                        int read = openInputStream.read(buffer, 0, 4096);
                        if (read <= 0) {
                            break;
                        }
                        j += (long) read;
                    }
                    openInputStream.close();
                    openInputStream = this.activity.getContentResolver().openInputStream(uri);
                }
                if (!TextUtils.isEmpty(str) && openInputStream != null) {
                    if (j > 32768) {
                        Toast.makeText(this.activity, "Размер файла превышает ограничение 32kb!", 1).show();
                        openInputStream.close();
                        return;
                    }
                    int lastIndexOf = str.lastIndexOf(46);
                    if (lastIndexOf >= 0) {
                        String substring = str.substring(lastIndexOf + 1);
                        if (substring.equalsIgnoreCase("gif") || substring.equalsIgnoreCase("jpg") || substring.equalsIgnoreCase("jpeg") || substring.equalsIgnoreCase("jpe") || substring.equalsIgnoreCase("png")) {
                            z = true;
                            if (z) {
                                MainActivity mainActivity = this.activity;
                                Toast.makeText(mainActivity, "Неподходящее расширение файла!\n" + str, 1).show();
                                openInputStream.close();
                                return;
                            }
                            byte[] bArr2 = new byte[32768];
                            int read2 = openInputStream.read(bArr2, 0, 32768);
                            openInputStream.close();
                            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, read2);
                            if (decodeByteArray == null || this.relativeLayout == null) {
                                Toast.makeText(this.activity, "Ошибка загрузки изображения!", 1).show();
                                return;
                            }
                            if (decodeByteArray.getWidth() <= 100 && decodeByteArray.getHeight() <= 100) {
                                ((Widgets$AvatarView) this.relativeLayout.findViewById(R.id.prf_edt_ava)).setImageBitmap(decodeByteArray);
                                this.f1959b.mo222a(new Document(this.memberId, new String(Base64.encode(bArr2, 0, read2, 2))));
                                return;
                            }
                            Toast.makeText(this.activity, "Размер изображения должен быть максимум 100x100", 1).show();
                            return;
                        }
                    }
                    z = false;
                    if (z) {
                    }
                }
                Toast.makeText(this.activity, "Не удалось открыть файл, используйте другой файл-менеджер!", 1).show();
                if (openInputStream != null) {
                    openInputStream.close();
                }
            } catch (Exception unused) {
                Toast.makeText(this.activity, "Ошибка файла", 1).show();
            }
        }

        @Override
        public boolean mo423g(Intent intent) {
            return false;
        }
    }

    public Page_Profile(MainActivity mainActivity, int userId, int i2) {
        super(mainActivity, 26989, new Document(userId));
        this.f1900O = 0;
        this.iconId = R.drawable.ic_nav_profile;
        this.userId = userId;
        this.title = "профиль " + userId;
        this.statusMessage = "Загрузка профиля " + this.userId;
        this.f1900O = i2;
    }

    @Override
    public boolean mo145B() {
        EditProfilePage zVar = this.editProfilePage;
        if (zVar == null) {
            return false;
        }
        zVar.m570a();
        this.editProfilePage = null;
        if (!this.f1901P) {
            return true;
        }
        tabReload();
        return true;
    }

    @Override
    public void onSearchBar() {
        Form_Post wVar = this.formPost;
        if (wVar != null && wVar.m201t()) {
            this.formPost.m200u();
        }
        EditProfilePage zVar = this.editProfilePage;
        if (zVar != null) {
            zVar.m570a();
        }
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoaded() {
        int i = 0;
        if (this.isLoading) {
            return false;
        }
        Log.d("ProfilePage", currentDocument.toString());
        String title = Util.C0427h.UnEscapeString(currentDocument.getString(1));
        this.title = title;
        currentDocument.addString(1, title);
        currentDocument.addString(4, Util.C0427h.UnEscapeString(currentDocument.getString(4)));
        String profileSignDate = this.currentDocument.getString(5);
        BBString.C0670a[] aVarArr = null;
        if (!TextUtils.isEmpty(profileSignDate)) {
            BBString x = BBString.getBBString(profileSignDate, null);
            this.profileSignBBCode = x;
            BBString.C0674e eVar = x.f2246z;
            int i2 = (int) (this.mainActivity.f731b * 16.0f);
            eVar.f2266j = i2;
            eVar.f2265i = i2;
            this.currentDocument.addString(5, x.m481h());
        }
        Document devicesDocument = this.currentDocument.getDocument(13);
        int i3 = 2;
        if (devicesDocument != null && devicesDocument.count() > 0) {
            StringBuilder sb = new StringBuilder("[list]");
            int i4 = 0;
            while (i4 < devicesDocument.count()) {
                Document l2 = devicesDocument.getDocument(i4);
                String url = l2.getString(i);
                if (!Urls2.is4pdaHost(url)) {
                    url = url.indexOf(47) < 0 ? "https://4pda.ru/devdb/" + url : "";
                }
                sb.append("[*]");
                if (l2.getInt(2) == 1) {
                    sb.append("[b]");
                }
                if (TextUtils.isEmpty(url)) {
                    sb.append(l2.getString(1));
                } else {
                    sb.append("[url=" + url + "]");
                    sb.append(l2.getString(1));
                    sb.append("[/url]");
                }
                if (l2.getInt(2) == 1) {
                    sb.append("[/b]    [offtop]основное[/offtop]");
                }
                i4++;
                i = 0;
            }
            sb.append("[/list]");
            BBString x2 = BBString.getBBString(sb.toString(), null);
            this.profileDeicesBBCode = x2;
            BBString.C0674e eVar2 = x2.f2246z;
            int i5 = (int) (this.mainActivity.f731b * 16.0f);
            eVar2.f2266j = i5;
            eVar2.f2265i = i5;
            eVar2.f2263g = 0.0f;
        }
        String n3 = this.currentDocument.getString(6);
        if (!TextUtils.isEmpty(n3)) {
            BBString devicesBBCode = BBString.getBBString(n3, null);
            this.profileBioBBCode = devicesBBCode;
            BBString.C0674e eVar3 = devicesBBCode.f2246z;
            int i6 = (int) (this.mainActivity.f731b * 16.0f);
            eVar3.f2266j = i6;
            eVar3.f2265i = i6;
            eVar3.f2263g = 0.0f;
            this.currentDocument.addString(6, devicesBBCode.m481h());
        }
        Document devicesListDocument = this.currentDocument.getDocument(21);
        if (devicesListDocument != null && devicesListDocument.count() > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[list]");
            for (int i7 = 0; i7 < devicesListDocument.count(); i7++) {
                sb2.append("[*][url=https://4pda.ru/forum/index.php?showtopic=");
                sb2.append(devicesListDocument.getDocument(i7).getInt(0));
                sb2.append("]");
                sb2.append(devicesListDocument.getDocument(i7).getString(1).replace("[", "&#91;").replace("]", "&#93;"));
                sb2.append("[/url]\n");
            }
            sb2.append("[/list]");
            BBString devicesBBCode = BBString.getBBString(sb2.toString(), null);
            this.profileCurDataBBCode = devicesBBCode;
            BBString.C0674e eVar4 = devicesBBCode.f2246z;
            int i8 = (int) (this.mainActivity.f731b * 16.0f);
            eVar4.f2266j = i8;
            eVar4.f2265i = i8;
            eVar4.f2263g = 0.0f;
        }
        Document warnsDocument = this.currentDocument.getDocument(22);
        boolean isHaveWarns = warnsDocument != null && warnsDocument.count() > 0;
        this.isAdvancedView = isHaveWarns || !TextUtils.isEmpty(this.currentDocument.getString(24)) || !TextUtils.isEmpty(this.currentDocument.getString(25));
        this.profileWarnsBBCode = null;
        this.isCancelPunishment = false;
        if (isHaveWarns) {
            Vector vector = new Vector(warnsDocument.count());
            this.profileWarnsDate = new Vector(warnsDocument.count());
            StringBuilder sb3 = new StringBuilder();
            this.mainActivity.mainLayout.getWidth();
            int i9 = 0;
            while (i9 < warnsDocument.count()) {
                Document l5 = warnsDocument.getDocument(i9);
                sb3.setLength(0);
                sb3.append("[url=https://4pda.ru/forum/index.php?showuser=" + l5.getInt(i3) + "][b][color=main_text]" + l5.getString(3).replace("[", "&#91;").replace("]", "&#93;") + "[/color][/b][/url]\n");
                int intValue = l5.getInt(1);
                if (intValue == i3) {
                    sb3.append("[color=green][size=1]Понижен уровень предупреждений[/size][/color]\n");
                } else if (intValue == 1) {
                    sb3.append("[color=red][size=1]Повышен уровень предупреждений[/size][/color]\n");
                } else if (intValue == 0) {
                    sb3.append("[size=1]Добавлена заметка[/size]\n");
                }
                sb3.append(l5.getString(4));
                int intValue2 = l5.getInt(5);
                if (intValue2 > 0) {
                    sb3.append("\n[color=blue][url=https://4pda.ru/forum/index.php?act=findpost&pid=" + intValue2 + "]Перейти к посту[/url][/color]\n");
                }
                if (l5.getInt(6) > 0) {
                    this.isCancelPunishment = true;
                    sb3.append("\n\n[size=1][color=red](доступна отмена)[/color][/size]\n");
                }
                BBString x5 = BBString.getBBString(sb3.toString(), aVarArr);
                BBString.C0674e eVar5 = x5.f2246z;
                float f = this.mainActivity.f731b;
                int i10 = (int) (f * 16.0f);
                eVar5.f2266j = i10;
                eVar5.f2265i = i10;
                eVar5.f2263g = (float) ((int) (f * 9.0f));
                vector.add(x5);
                this.profileWarnsDate.add(Util.formatDate(l5.getInt(0)));
                i9++;
                aVarArr = null;
                i3 = 2;
            }
            this.profileWarnsBBCode = vector;
        }
        int[] iArr = new int[(isHaveWarns ? this.profileWarnsBBCode.size() : 0) + 1 + (this.isAdvancedView ? 1 : 0)];
        this.f1071B = iArr;
        MainActivity mainActivity = this.mainActivity;
        iArr[0] = (int) (mainActivity.f731b * 500.0f);
        if (isHaveWarns) {
            int width = mainActivity.mainLayout.getWidth();
            int i11 = this.f1071B[0];
            int i12 = 0;
            while (i12 < this.profileWarnsBBCode.size()) {
                int[] iArr2 = this.f1071B;
                int i13 = i12 + 1;
                MainActivity mainActivity2 = this.mainActivity;
                i11 += ((int) (mainActivity2.f731b * 8.0f)) + Util.m663k(mainActivity2, this.profileWarnsBBCode.get(i12), width);
                iArr2[i13] = i11;
                i12 = i13;
            }
        }
        if (this.isAdvancedView) {
            int[] iArr3 = this.f1071B;
            iArr3[iArr3.length - 1] = iArr3[iArr3.length - 2] + ((int) (this.mainActivity.f731b * 32.0f));
        }
        return true;
    }

    @Override
    public void mo142J(boolean z) {
        super.mo142J(z);
        if (this.f1900O != 0 && isCurrentTab()) {
            m577f0(0, 0, this.f1900O);
            this.f1900O = 0;
        }
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        int i;
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0563c());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 1, "Копировать ссылку");
        o1Var.addMenuItem(0, 0, 2, "Открыть в браузере");
        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
        if (L != null) {
            if (this.userId == L.memberId || (i = L.memberGroup) == 4 || i == 9 || i == 10) {
                o1Var.addMenuItem(0, 0, 3, "Редактировать профиль");
            }
            int i2 = L.memberGroup;
            if (i2 == 4 || i2 == 10 || i2 == 9 || i2 == 11 || i2 == 19) {
                o1Var.addMenuItem(0, 0, 6, "Наказать", false, true);
            }
            int i3 = L.memberGroup;
            if (i3 == 4 || i3 == 10 || i3 == 9 || i3 == 11 || i3 == 19) {
                o1Var.addMenuItem(0, 0, 9, "Добавить заметку", false, true);
            }
            if (this.isCancelPunishment) {
                o1Var.addMenuItem(0, 0, 7, "Отменить наказание", false, true);
            }
        }
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        int i;
        super.mo54M(f1Var, z);
        if (this.f1901P) {
            tabReload();
        }
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
            EditProfilePage zVar = this.editProfilePage;
            if (zVar != null) {
                zVar.m569b();
            }
            Form_Post wVar = this.formPost;
            if (wVar != null && wVar.m201t()) {
                this.formPost.m199v();
            }
            if (isUnsucces() && (i = this.f1900O) != 0) {
                m577f0(0, 0, i);
                this.f1900O = 0;
            }
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public void mo135b(Form_Post.ForumPostModel kVar, boolean z) {
        if (!TextUtils.isEmpty(kVar.postMessage)) {
            DocumentManager.getResultRequest(new API_Member(this.mainActivity, this.userId, kVar.postMessage));
        }
        this.forumPostModel = null;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
    }

    boolean addTextColored(String str, View view, Integer num, int i) {
        return addTextColored(str, view, num, i, null);
    }

    boolean addTextColored(String str, View view, Integer num, int i, ColorStateList colorStateList) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (num != null) {
            TextView textView = (TextView) view.findViewById(num.intValue());
            textView.setClickable(false);
            textView.setTextColor(Skin.SkinColorModel.mainTextColor);
            textView.getLayoutParams().height = -2;
        }
        TextView textView2 = (TextView) view.findViewById(i);
        if (colorStateList == null) {
            textView2.setTextColor(Skin.SkinColorModel.mainTextColor);
        } else {
            textView2.setTextColor(colorStateList);
        }
        textView2.getLayoutParams().height = -2;
        textView2.setText(str);
        return true;
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
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
        }
    }

    public void m577f0(int i, int i2, int statusCode) {
        if (statusCode == 21) {
            tabReload();
        } else if (statusCode == 22) {
            DataDB.m365l(this.title, getLink());
        } else if (statusCode == 1) {
            MainActivity mainActivity = this.mainActivity;
            Util.copyToClipboard(mainActivity, "https://4pda.ru/" + getLink(), "Ссылка скопирована");
        } else if (statusCode == 2) {
            MainActivity mainActivity2 = this.mainActivity;
            Urls2.visitPage(mainActivity2, "https://4pda.ru/" + getLink());
        } else if (statusCode == 3) {
            if (this.editProfilePage == null) {
                this.editProfilePage = new EditProfilePage(this.tab, this.currentDocument, this.f1905T, this.f1906U, this.f1907V);
            }
        } else if (statusCode == 8) {
            EditProfilePage zVar = this.editProfilePage;
            if (zVar != null) {
                zVar.m570a();
                this.editProfilePage = null;
            }
            View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_edit_login, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.edit_login_current);
            textView.setText(textView.getText() + "  " + this.currentDocument.getString(1));
            EditText editText = (EditText) inflate.findViewById(R.id.edit_login_new);
            EditText editText2 = (EditText) inflate.findViewById(R.id.edit_login_pass);
            CustomDialog l1Var = new CustomDialog(this.mainActivity, inflate, "ИЗМЕНИТЬ", null);
            l1Var.m625a(false);
            C0564d dVar = new C0564d(this, l1Var, editText, editText2);
            editText.addTextChangedListener(dVar);
            editText2.addTextChangedListener(dVar);
            l1Var.m620f(new View$OnClickListenerC0565e(editText, editText2), true);
            l1Var.show(true, true, true);
        } else if (statusCode == 4) {
            EditProfilePage zVar2 = this.editProfilePage;
            if (zVar2 != null) {
                zVar2.m570a();
                this.editProfilePage = null;
            }
            View inflate2 = this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_edit_email, (ViewGroup) null);
            TextView textView2 = (TextView) inflate2.findViewById(R.id.edit_email_current);
            textView2.setText(((Object) textView2.getText()) + "  " + this.currentDocument.getString(23));
            EditText editText3 = (EditText) inflate2.findViewById(R.id.edit_email_new);
            EditText editText4 = (EditText) inflate2.findViewById(R.id.edit_email_pass);
            CustomDialog l1Var2 = new CustomDialog(this.mainActivity, inflate2, "ИЗМЕНИТЬ", null);
            l1Var2.m625a(false);
            C0566f fVar = new C0566f(this, l1Var2, editText3, editText4);
            editText3.addTextChangedListener(fVar);
            editText4.addTextChangedListener(fVar);
            l1Var2.m620f(new View$OnClickListenerC0567g(editText3, editText4), true);
            l1Var2.show(true, true, true);
        } else if (statusCode == 5) {
            EditProfilePage zVar3 = this.editProfilePage;
            if (zVar3 != null) {
                zVar3.m570a();
                this.editProfilePage = null;
            }
            View inflate3 = this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_edit_pass, (ViewGroup) null);
            EditText editText5 = (EditText) inflate3.findViewById(R.id.edit_pass_curr);
            EditText editText6 = (EditText) inflate3.findViewById(R.id.edit_pass_new);
            EditText editText7 = (EditText) inflate3.findViewById(R.id.edit_pass_new2);
            CustomDialog l1Var3 = new CustomDialog(this.mainActivity, inflate3, "СОХРАНИТЬ", null);
            l1Var3.m625a(false);
            C0568h hVar = new C0568h(this, l1Var3, editText5, editText6, editText7);
            editText5.addTextChangedListener(hVar);
            editText6.addTextChangedListener(hVar);
            editText7.addTextChangedListener(hVar);
            l1Var3.m620f(new View$OnClickListenerC0569i(editText5, editText6, editText7), true);
            l1Var3.show(true, true, true);
        } else if (6 == statusCode) {
            DlgWarn.m320A(this.mainActivity, this.userId, 0);
        } else if (7 == statusCode) {
            DlgSimple q1Var = new DlgSimple(this.mainActivity, "Введите причину отмены", false, null, null);
            q1Var.m625a(false);
            q1Var.editText.addTextChangedListener(new C0570j(this, q1Var));
            q1Var.m620f(new View$OnClickListenerC0572l(q1Var), true);
            q1Var.show(true, true, true);
        } else if (9 == statusCode) {
            if (this.forumPostModel == null) {
                this.forumPostModel = new Form_Post.ForumPostModel(0, "Заметка: " + this.title, this.userId, 0, false, false, false, false, false, "", "", null);
            }
            if (this.formPost == null) {
                this.formPost = new Form_Post(this.mainActivity, this);
            }
            this.formPost.m196y(this.forumPostModel, this);
        }
    }

    public void m576g0(int i) {
        this.f1900O = i;
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces()) {
            return 0;
        }
        List<BBString> list = this.profileWarnsBBCode;
        if (list != null) {
            i = list.size();
        }
        return i + 1 + (this.isAdvancedView ? 1 : 0);
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
        return i == getCount() - 1 ? 2 : 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("ProfilePage", "getView Called");
        View relativeLayout = null;
        boolean d0;
        DocumentManager.MemberInfoModel memberInfoModel;
        boolean z;
        String n;

        boolean d02 = false;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 2) {
                RelativeLayout relativeLayout2 = new RelativeLayout(this.mainActivity);
                View view2 = new View(this.mainActivity);
                view2.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.card_sep));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f));
                layoutParams.topMargin = (int) (this.mainActivity.f731b * 16.0f);
                view2.setLayoutParams(layoutParams);
                relativeLayout2.addView(view2);
                relativeLayout = relativeLayout2;
            } else {
                int i2 = 0;
                if (itemViewType == 1) {
                    View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.profile_warn, viewGroup, false);
                    ((BBDisplay) inflate.findViewById(R.id.profileWarnCode)).setCallback(this);
                    relativeLayout = inflate;
                } else if (itemViewType == 0) {
                    Log.d("ProfilePage", "getStandartView Called");
                    View inflate2 = this.mainActivity.getLayoutInflater().inflate(R.layout.profile_page, viewGroup, false);
                    this.avatarView = (Widgets$AvatarView) inflate2.findViewById(R.id.profileAvatar);
                    String n2 = this.currentDocument.getString(2);
                    if (this.userId == 17927) {
                        this.avatarView.setImageDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.ic_launcher));
                    } else if (!TextUtils.isEmpty(n2)) {
                        PicoImgRequest picoImgRequest = PicoImg.loadUrl(this.mainActivity, n2);
                        picoImgRequest.to(this.avatarView);
                        picoImgRequest.disableAnimation(!Prefs.animAvatars);
                        picoImgRequest.sizeToView();
                        picoImgRequest.runAsync();
                        this.avatarView.setOnClickListener(new View$OnClickListenerC0571k(n2));
                    }
                    TextView textView = (TextView) inflate2.findViewById(R.id.profileName);
                    textView.setText(this.title);
                    textView.setOnLongClickListener(new View$OnLongClickListenerC0576p());
                    int intValue = this.currentDocument.getInt(8);
                    textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable((System.currentTimeMillis() / 1000) - ((long) intValue) < 900 ? R.drawable.ic_online : R.drawable.ic_offline), (Drawable) null, (Drawable) null, (Drawable) null);
                    if (intValue > 0) {
                        addTextColored(Util.formatDate(intValue), inflate2, (int) R.id.profileLastLabel, R.id.profileLast);
                    }
                    ((TextView) inflate2.findViewById(R.id.profileGroup)).
                            setText(API.userGroups.get(this.currentDocument.getInt(3)));
                    ((TextView) inflate2.findViewById(R.id.profileGroup)).
                            setTextColor(Util.C0424f.m646c(Skin.SkinColorModel.f1398n0[this.currentDocument.getInt(3)],
                                    Skin.SkinColorModel.f1370Z));
                    addTextView(4, inflate2, (int) R.id.profileTitleLabel, R.id.profileTitle);
                    addTextColored(Util.formatDate(this.currentDocument.getInt(7), true, false),
                            inflate2, (int) R.id.profileRegLabel, R.id.profileReg);
                    int warningLevel = this.currentDocument.getInt(26);
                    if (warningLevel >= 0) {
                        addTextColored((warningLevel * 20) + "%", inflate2, (int) R.id.profileWarnLevelLabel, R.id.profileWarnLevel);
                    }
                    int premod = this.currentDocument.getInt(27);
                    if (premod == 1) {
                        addTextColored("постоянно", inflate2, (int) R.id.profilePremodLabel, R.id.profilePremod);
                    } else if (premod != 0) {
                        addTextColored("до " + Util.formatDate(premod), inflate2, (int) R.id.profilePremodLabel, R.id.profilePremod);
                    }
                    int readOnly = this.currentDocument.getInt(28);
                    if (readOnly != 0) {
                        addTextColored("до " + Util.formatDate(readOnly), inflate2, (int) R.id.profileReadOnlyLabel, R.id.profileReadOnly);
                    }
                    int banReason = this.currentDocument.getInt(29);
                    if (banReason == 1) {
                        addTextColored("последний шанс", inflate2, (int) R.id.profileBanLabel, R.id.profileBan);
                    } else if (banReason == 2) {
                        addTextColored("перманентный", inflate2, (int) R.id.profileBanLabel, R.id.profileBan);
                    } else if (banReason == 3) {
                        addTextColored("security block", inflate2, (int) R.id.profileBanLabel, R.id.profileBan);
                    }
                    if (this.profileSignBBCode != null) {
                        BBDisplay bBDisplay = (BBDisplay) inflate2.findViewById(R.id.profileSign);
                        bBDisplay.setOverlay((BBOverlay) inflate2.findViewById(R.id.profileSignOver));
                        inflate2.findViewById(R.id.profileSignLabel).setVisibility(0);
                        bBDisplay.setBBString(this.profileSignBBCode);
                        bBDisplay.setCallback(this);
                    }
                    inflate2.findViewById(R.id.profileMail).setOnClickListener(new View$OnClickListenerC0577q());
                    boolean h0 = addTextView(23, inflate2, (int) R.id.profileMailLabel,
                            R.id.profileMail) | addTextView(24, inflate2, (int) R.id.profileRegIPLabel, R.id.profileRegIP);
                    int gender = this.currentDocument.getInt(10);
                    if (gender == 1) {
                        d02 = addTextColored("Мужчина", inflate2, null, R.id.profileGender);
                    } else {
                        if (gender == 2) {
                            d02 = addTextColored("Женщина", inflate2, null, R.id.profileGender);
                        }
                        boolean i0 = h0 | addTextView(12, inflate2, (int) R.id.profileLocationLabel, R.id.profileLocation, true)
                                | addTextView(9, inflate2, (int) R.id.profileBirthLabel, R.id.profileBirth);
                        Time time = new Time("UTC");
                        time.set(System.currentTimeMillis() + (((long) this.currentDocument.getInt(11)) * 1000));
                        d0 = i0 | addTextColored(time.format("%d.%m.%Y %H:%M"), inflate2, (int) R.id.profileUserTimeLabel, R.id.profileUserTime);
                        memberInfoModel = DocumentManager.getMemberInfoModel();
                        if (memberInfoModel != null || this.userId == memberInfoModel.memberId) {
                            z = false;
                        } else {
                            TextView textView2 = (TextView) inflate2.findViewById(R.id.profileQms);
                            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).height = (int) (this.mainActivity.f731b * 36.0f);
                            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).topMargin = (int) (this.mainActivity.f731b * 8.0f);
                            int qmlMessages = this.currentDocument.getInt(20);
                            if (qmlMessages > 0) {
                                textView2.setText("QMS (" + qmlMessages + ")");
                            }
                            textView2.setOnClickListener(new View$OnClickListenerC0578r());
                            d0 = true;
                            z = true;
                        }
                        n = this.currentDocument.getString(23);
                        if (!TextUtils.isEmpty(n)) {
                            TextView textView3 = (TextView) inflate2.findViewById(R.id.profileEmail);
                            ((RelativeLayout.LayoutParams) textView3.getLayoutParams()).height = (int) (this.mainActivity.f731b * 36.0f);
                            ((RelativeLayout.LayoutParams) textView3.getLayoutParams()).topMargin = (int) (this.mainActivity.f731b * 8.0f);
                            textView3.setOnClickListener(new View$OnClickListenerC0579s(n));
                            d0 = true;
                            z = true;
                        }
                        if (z) {
                            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) ((TextView) inflate2.findViewById(R.id.profileContactsLabel)).getLayoutParams();
                            layoutParams2.height = -2;
                            layoutParams2.topMargin = (int) (this.mainActivity.f731b * 8.0f);
                        }
                        if (this.profileDeicesBBCode != null) {
                            BBDisplay bBDisplay2 = (BBDisplay) inflate2.findViewById(R.id.profileDevices);
                            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) ((TextView) inflate2.findViewById(R.id.profileDevicesLabel)).getLayoutParams();
                            layoutParams3.height = -2;
                            layoutParams3.topMargin = (int) (this.mainActivity.f731b * 8.0f);
                            bBDisplay2.setBBString(this.profileDeicesBBCode);
                            bBDisplay2.setCallback(this);
                            d0 = true;
                        }
                        if (d0) {
                            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) ((TextView) inflate2.findViewById(R.id.profilePersonalsLabel)).getLayoutParams();
                            layoutParams4.height = -2;
                            float f = this.mainActivity.f731b;
                            layoutParams4.topMargin = (int) (f * 12.0f);
                            layoutParams4.bottomMargin = (int) (f * 6.0f);
                            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) inflate2.findViewById(R.id.profilePersonalsSep).getLayoutParams();
                            float f2 = this.mainActivity.f731b;
                            layoutParams5.height = (int) (f2 * 16.0f);
                            layoutParams5.topMargin = (int) (f2 * 16.0f);
                        }
                        if (this.profileBioBBCode != null) {
                            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) ((TextView) inflate2.findViewById(R.id.profileBioLabel)).getLayoutParams();
                            layoutParams6.height = -2;
                            float f3 = this.mainActivity.f731b;
                            layoutParams6.topMargin = (int) (f3 * 12.0f);
                            layoutParams6.bottomMargin = (int) (f3 * 6.0f);
                            BBDisplay bBDisplay3 = (BBDisplay) inflate2.findViewById(R.id.profileBio);
                            bBDisplay3.setOverlay((BBOverlay) inflate2.findViewById(R.id.profileBioOver));
                            bBDisplay3.setBBString(this.profileBioBBCode);
                            bBDisplay3.setCallback(this);
                            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) inflate2.findViewById(R.id.profileBioSep).getLayoutParams();
                            float f4 = this.mainActivity.f731b;
                            layoutParams7.height = (int) (f4 * 16.0f);
                            layoutParams7.topMargin = (int) (f4 * 16.0f);
                        }
                        double karma = (double) ((float) this.currentDocument.getInt(14));
                        Double.isNaN(karma);
                        addTextColored(String.format("%.02f", karma / 100.0d), inflate2, (int) R.id.profileKarmaLabel, R.id.profileKarma);
                        addTextColored(this.currentDocument.getInt(15).toString(), inflate2, (int) R.id.profileSitePostsLabel, R.id.profileSitePosts);
                        addTextColored(this.currentDocument.getInt(16).toString(), inflate2, (int) R.id.profileCommentsLabel, R.id.profileComments);
                        if (memberInfoModel != null || this.userId == memberInfoModel.memberId) {
                            inflate2.findViewById(R.id.profileReputationDown).setVisibility(8);
                        } else {
                            inflate2.findViewById(R.id.profileReputationDown).setOnClickListener(new View$OnClickListenerC0580t());
                        }
                        inflate2.findViewById(R.id.profileReputation).setOnClickListener(new View$OnClickListenerC0583u());
                        if (memberInfoModel != null || this.userId == memberInfoModel.memberId) {
                            inflate2.findViewById(R.id.profileReputationUp).setVisibility(8);
                        } else {
                            inflate2.findViewById(R.id.profileReputationUp).setOnClickListener(new View$OnClickListenerC0584v());
                        }
                        ((TextView) inflate2.findViewById(R.id.profileTopics)).setText(this.currentDocument.getInt(18).toString());
                        inflate2.findViewById(R.id.profileTopics).setOnClickListener(new View$OnClickListenerC0587w());
                        ((TextView) inflate2.findViewById(R.id.profileForumPosts)).setText(this.currentDocument.getInt(19).toString());
                        inflate2.findViewById(R.id.profileForumPosts).setOnClickListener(new View$OnClickListenerC0560a());
                        if (this.profileCurDataBBCode == null) {
                            BBDisplay bBDisplay4 = (BBDisplay) inflate2.findViewById(R.id.profileCurData);
                            bBDisplay4.setBBString(this.profileCurDataBBCode);
                            bBDisplay4.setCallback(this);
                        } else {
                            inflate2.findViewById(R.id.profileCurLabel).setVisibility(8);
                            inflate2.findViewById(R.id.profileCurSep).setVisibility(8);
                        }
                        Document rewardDocumentList = this.currentDocument.getDocument(30);
                        if (rewardDocumentList != null || rewardDocumentList.count() <= 0) {
                            inflate2.findViewById(R.id.profileRewardLabel).setVisibility(8);
                            inflate2.findViewById(R.id.profileRewardSep).setVisibility(8);
                        } else {
                            LinearLayout linearLayout = (LinearLayout) inflate2.findViewById(R.id.profileRewardData);
                            for (int i3 = 0; i3 < rewardDocumentList.count(); i3++) {
                                Document rewardDocument = rewardDocumentList.getDocument(i3);
                                View inflate3 = this.mainActivity.getLayoutInflater().inflate(R.layout.profile_reward, (ViewGroup) linearLayout, false);
                                PicoImgRequest l4 = PicoImg.loadUrl(this.mainActivity, rewardDocument.getString(0));
                                l4.to((ImageView) inflate3.findViewById(R.id.reward_image));
                                l4.disableAnimation(!Prefs.animImages);
                                l4.runAsync();
                                ((TextView) inflate3.findViewById(R.id.reward_name)).setText(rewardDocument.getString(1));
                                ((TextView) inflate3.findViewById(R.id.reward_date)).setText(Util.formatDate(rewardDocument.getInt(4), true, false));
                                String rewardDesc = rewardDocument.getString(2);
                                if (!TextUtils.isEmpty(rewardDesc)) {
                                    ((TextView) inflate3.findViewById(R.id.reward_desc)).setText(rewardDesc);
                                } else {
                                    inflate3.findViewById(R.id.reward_desc).setVisibility(8);
                                }
                                int rewardCount = rewardDocument.getInt(3);
                                TextView textView4 = (TextView) inflate3.findViewById(R.id.reward_count);
                                if (rewardCount > 1) {
                                    textView4.setText(String.valueOf(rewardCount));
                                } else {
                                    textView4.setVisibility(8);
                                }
                                String n4 = rewardDocument.getString(5);
                                if (!TextUtils.isEmpty(n4)) {
                                    inflate3.setOnClickListener(new View$OnClickListenerC0562b(n4));
                                }
                                linearLayout.addView(inflate3);
                            }
                        }
                        if (this.isAdvancedView) {
                            RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) inflate2.findViewById(R.id.profileLogLabel).getLayoutParams();
                            layoutParams8.height = -2;
                            float f5 = this.mainActivity.f731b;
                            layoutParams8.topMargin = (int) (f5 * 12.0f);
                            layoutParams8.bottomMargin = (int) (f5 * 6.0f);
                            boolean h02 = addTextView(24, inflate2, (int) R.id.profileLogRegIPLabel, R.id.profileLogRegIP) | addTextView(25, inflate2, (int) R.id.profileLogSessIPLabel, R.id.profileLogSessIP);
                            if (this.profileWarnsBBCode != null) {
                                RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) inflate2.findViewById(R.id.profileWarnLabel).getLayoutParams();
                                layoutParams9.height = -2;
                                float f6 = this.mainActivity.f731b;
                                if (h02) {
                                    i2 = 8;
                                }
                                layoutParams9.topMargin = (int) (f6 * ((float) i2));
                            }
                        }
                    }
                    /*h0 |= d02;
                    boolean i02 = h0 | addTextView(12, inflate2, (int) R.id.profileLocationLabel, R.id.profileLocation, true) | addTextView(9, inflate2, (int) R.id.profileBirthLabel, R.id.profileBirth);
                    Time time2 = new Time("UTC");
                    time2.set(System.currentTimeMillis() + (((long) this.currentDocument.getInt(11)) * 1000));
                    d0 = i02 | addTextColored(time2.format("%d.%m.%Y %H:%M"), inflate2, (int) R.id.profileUserTimeLabel, R.id.profileUserTime);
                    memberInfoModel = DocumentManager.getMemberInfoModel();
                    z = false;
                    n = this.currentDocument.getString(23);
                    double intValue82 = (double) ((float) this.currentDocument.getInt(14));
                    Double.isNaN(intValue82);
                    addTextColored(String.format("%.02f", intValue82 / 100.0d), inflate2, (int) R.id.profileKarmaLabel, R.id.profileKarma);
                    addTextColored(this.currentDocument.getInt(15).toString(), inflate2, (int) R.id.profileSitePostsLabel, R.id.profileSitePosts);
                    addTextColored(this.currentDocument.getInt(16).toString(), inflate2, (int) R.id.profileCommentsLabel, R.id.profileComments);

                    inflate2.findViewById(R.id.profileReputationDown).setVisibility(8);
                    inflate2.findViewById(R.id.profileReputation).setOnClickListener(new View$OnClickListenerC0583u());

                    inflate2.findViewById(R.id.profileReputationUp).setVisibility(8);
                    ((TextView) inflate2.findViewById(R.id.profileTopics)).setText(this.currentDocument.getInt(18).toString());
                    inflate2.findViewById(R.id.profileTopics).setOnClickListener(new View$OnClickListenerC0587w());
                    ((TextView) inflate2.findViewById(R.id.profileForumPosts)).setText(this.currentDocument.getInt(19).toString());
                    inflate2.findViewById(R.id.profileForumPosts).setOnClickListener(new View$OnClickListenerC0560a());

                    memberDocument = this.currentDocument.getDocument(30);

                    inflate2.findViewById(R.id.profileRewardLabel).setVisibility(8);
                    inflate2.findViewById(R.id.profileRewardSep).setVisibility(8);
                    relativeLayout = inflate2;*/
                    relativeLayout = inflate2;
                }
            }
          /*  if (itemViewType == 0) {
               // ((TextView) relativeLayout.findViewById(R.id.profileReputation)).setText(this.currentDocument.getInt(17).toString());
            }
            if (itemViewType == 1) {
                int i4 = i - 1;
              //  ((BBDisplay) relativeLayout.findViewById(R.id.profileWarnCode)).setBBString(this.profileWarnsBBCode.get(i4));
               // ((TextView) relativeLayout.findViewById(R.id.profileWarnDate)).setText(this.profileWarnsDate.get(i4));
            }*/
            return relativeLayout;
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    boolean addTextView(int i, View view, Integer num, int i2) {
        return addTextView(i, view, num, i2, false);
    }

    boolean addTextView(int i, View view, Integer num, int i2, boolean z) {
        try {
            String n = this.currentDocument.getString(i);
            if (TextUtils.isEmpty(n)) {
                return false;
            }
            if (num != null) {
                view.findViewById(num).getLayoutParams().height = -2;
                ((TextView) view.findViewById(num)).setTextColor(Skin.SkinColorModel.mainTextColor);
            }
            if (z) {
                n = Util.C0427h.UnEscapeString(n);
            }
            TextView textView = (TextView) view.findViewById(i2);
            textView.setTextColor(Skin.SkinColorModel.mainTextColor);
            textView.getLayoutParams().height = -2;
            textView.setText(n);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.avatarView = null;
        this.profileSignBBCode = null;
        this.profileDeicesBBCode = null;
        this.profileBioBBCode = null;
        if (this.editProfilePage != null) {
            this.editProfilePage = null;
        }
        this.isAdvancedView = false;
        List<BBString> list = this.profileWarnsBBCode;
        if (list != null) {
            list.clear();
            this.profileWarnsBBCode = null;
        }
    }

    @Override
    public String getLink() {
        return "forum/index.php?showuser=" + this.userId;
    }

    @Override
    public void tabReload() {
        this.f1901P = false;
        super.tabReload();
    }
}
