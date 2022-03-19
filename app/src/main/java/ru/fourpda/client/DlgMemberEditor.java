package ru.fourpda.client;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.TextView;

public class DlgMemberEditor extends CustomDialog {
    TextView f2450j;
    Widgets$MemberView f2451k;

    class C0723a implements Util.AbstractC0429j<Boolean, Boolean> {
        C0723a() {
        }

        public Boolean mo222a(Boolean bool) {
            DlgMemberEditor.this.m625a(bool || TextUtils.isEmpty(DlgMemberEditor.this.f2451k.getText().toString()));
            return Boolean.TRUE;
        }
    }

    class C0724b implements TextWatcher {
        C0724b() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 0) {
                DlgMemberEditor.this.f2451k.setData(0, null, false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public DlgMemberEditor(MainActivity mainActivity, String str, String str2, String str3) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_select_member, (ViewGroup) null), str2, str3);
        TextView textView = (TextView) this.rootView.findViewById(R.id.promtMessage);
        this.f2450j = textView;
        textView.setText(str);
        Widgets$MemberView widgets$MemberView = (Widgets$MemberView) this.rootView.findViewById(R.id.memberEdit);
        this.f2451k = widgets$MemberView;
        widgets$MemberView.resetData();
        Widgets$MemberView widgets$MemberView2 = this.f2451k;
        widgets$MemberView2.f903c = new C0723a();
        widgets$MemberView2.addTextChangedListener(new C0724b());
    }
}
