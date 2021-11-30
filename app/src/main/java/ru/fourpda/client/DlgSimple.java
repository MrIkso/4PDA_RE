package ru.fourpda.client;

import android.widget.EditText;
import android.widget.TextView;

public class DlgSimple extends CustomDialog {
    public TextView promtMessage;
    TextView promtDescription = this.rootView.findViewById(R.id.promtDescription);
    public EditText editText;
    public Widgets$CheckboxTextView checkboxTextView;

    public DlgSimple(MainActivity mainActivity, String str, boolean z, String str2, String str3) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_simple, null), str2, str3);
        TextView textView = this.rootView.findViewById(R.id.promtMessage);
        this.promtMessage = textView;
        textView.setText(str);
        EditText editText = this.rootView.findViewById(R.id.promtInput);
        this.editText = editText;
        if (z) {
            editText.setInputType(2);
        }
        this.checkboxTextView = this.rootView.findViewById(R.id.promtCheck);
    }
}
