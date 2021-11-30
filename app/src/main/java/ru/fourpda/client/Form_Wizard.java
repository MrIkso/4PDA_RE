package ru.fourpda.client;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.List;
import java.util.Vector;

public class Form_Wizard {
    private static BBString.C0674e f2949t;
    private static BBString.C0674e f2950u;
    private MainActivity mainActivity;
    private Page f2952b;
    private int forumNumber;
    private int topicId;
    private String f2955e;
    private C0883n f2956f;
    private BBDisplay.IBBDisplayCallback f2957g;
    private Object[] f2958h;
    private TextView[] f2959i;
    private int f2960j;
    private RelativeLayout wizardPanel;
    private ToggleButton wizardPreview;
    private Button wizardSend;
    private Button wizardAttach;
    private TextView wizardAttachCount;
    private Button wizardSmiles;
    private ScrollView previewScroll;
    private TextView previewTitle;
    private BBDisplay previewCode;

    public class View$OnClickListenerC0869a implements View.OnClickListener {
        final AttachDialog f2970a;
        final C0883n.C0884a f2971b;

        View$OnClickListenerC0869a(Form_Wizard xVar, AttachDialog nVar, C0883n.C0884a aVar) {
            this.f2970a = nVar;
            this.f2971b = aVar;
        }

        @Override
        public void onClick(View view) {
            this.f2970a.m600b();
            this.f2971b.f3003m = true;
        }
    }

    class View$OnClickListenerC0870b implements View.OnClickListener {
        View$OnClickListenerC0870b() {
        }

        @Override
        public void onClick(View view) {
            Form_Wizard.this.wizardPreview.setChecked(false);
            if (Form_Wizard.this.previewScroll.getVisibility() != 0) {
                Form_Wizard.this.m161s();
                DocumentManager.getResultRequest(new SendWizardRequest(1));
                return;
            }
            Form_Wizard.this.m180F(null);
        }
    }

    class View$OnClickListenerC0871c implements View.OnClickListener {
        View$OnClickListenerC0871c() {
        }

        @Override
        public void onClick(View view) {
            Form_Wizard.this.mainActivity.mainLayout.m866p(true);
        }
    }

    class WizardSendOnClickListener implements View.OnClickListener {
        WizardSendOnClickListener() {
        }

        @Override
        public void onClick(View view) {
            Form_Wizard.this.m161s();
            DocumentManager.getResultRequest(new SendWizardRequest(2));
        }
    }

    class wizardSmilesOnClickListener implements View.OnClickListener {
        wizardSmilesOnClickListener() {
        }

        @Override
        public void onClick(View view) {
            if (-1 < Form_Wizard.this.f2960j) {
                ((BBEditor) Form_Wizard.this.f2958h[Form_Wizard.this.f2960j]).m523b();
            }
        }
    }

    public class View$OnFocusChangeListenerC0874f implements View.OnFocusChangeListener {
        final C0883n.C0884a f2976a;
        final int f2977b;

        View$OnFocusChangeListenerC0874f(C0883n.C0884a aVar, int i) {
            this.f2976a = aVar;
            this.f2977b = i;
        }

        @Override
        public void onFocusChange(View view, boolean z) {
            boolean z2 = true;
            int i = 0;
            if (z && this.f2976a.f2996f) {
                for (int i2 = this.f2977b + 1; i2 < Form_Wizard.this.f2956f.f2990a.length; i2++) {
                    C0883n.C0884a aVar = Form_Wizard.this.f2956f.f2990a[i2];
                    if (aVar.f2996f && aVar.f2991a.equals("upload_box")) {
                        Form_Wizard.this.wizardAttachCount.setText(Integer.valueOf(((AttachDialog) Form_Wizard.this.f2958h[i2]).m589o()).toString());
                        break;
                    }
                }
            }
            z2 = false;
            Form_Wizard.this.wizardAttach.setVisibility(z2 ? 0 : 8);
            Form_Wizard.this.wizardAttachCount.setVisibility(z2 ? 0 : 8);
            Button button = Form_Wizard.this.wizardSmiles;
            if (!z) {
                i = 8;
            }
            button.setVisibility(i);
            Form_Wizard.this.f2960j = z ? this.f2977b : -1;
        }
    }

    public class View$OnClickListenerC0875g implements View.OnClickListener {
        final TextView f2979a;
        final C0883n.C0884a f2980b;

        class C0876a implements OptionPoupupMenuView.IClickListener {
            C0876a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                View$OnClickListenerC0875g.this.f2979a.setTag(Integer.valueOf(i3));
                View$OnClickListenerC0875g gVar = View$OnClickListenerC0875g.this;
                gVar.f2979a.setText(gVar.f2980b.f2998h[i3]);
            }
        }

        View$OnClickListenerC0875g(TextView textView, C0883n.C0884a aVar) {
            this.f2979a = textView;
            this.f2980b = aVar;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Form_Wizard.this.mainActivity, new C0876a());
            int i = 0;
            while (true) {
                String[] strArr = this.f2980b.f2998h;
                if (i < strArr.length) {
                    o1Var.addMenuItem(0, 0, i, strArr[i]);
                    i++;
                } else {
                    o1Var.show(null);
                    return;
                }
            }
        }
    }

    public class C0877h implements AttachDialog.AbstractC0559i {
        final C0883n.C0884a f2983a;
        final int f2984b;

        C0877h(C0883n.C0884a aVar, int i) {
            this.f2983a = aVar;
            this.f2984b = i;
        }

        @Override
        public void mo153a(AttachDialog nVar, int i, String str) {
            for (int i2 = this.f2984b - 1; i2 >= 0; i2--) {
                C0883n.C0884a aVar = Form_Wizard.this.f2956f.f2990a[i2];
                if (aVar.f2996f && aVar.f2991a.equals("editor")) {
                    EditText editText = ((BBEditor) Form_Wizard.this.f2958h[i2]).f2030d;
                    editText.setText(editText.getText().toString() + "[attachment=\"" + i + ":" + str + "\"]");
                    return;
                }
            }
        }

        @Override
        public void mo152b(AttachDialog nVar, int i) {
            if (this.f2983a.f3003m) {
                Form_Wizard.this.wizardAttachCount.setText(Integer.valueOf(nVar.m589o()).toString());
            }
        }

        @Override
        public void mo151c(AttachDialog nVar) {
            this.f2983a.f3003m = false;
        }

        @Override
        public void mo150d(AttachDialog nVar, int i, String str) {
            if (this.f2983a.f3003m) {
                Form_Wizard.this.wizardAttachCount.setText(Integer.valueOf(nVar.m589o()).toString());
            }
        }
    }

    public class View$OnClickListenerC0878i implements View.OnClickListener {
        View$OnClickListenerC0878i() {
        }

        @Override
        public void onClick(View view) {
            if (Form_Wizard.this.f2960j >= 0) {
                for (int i = Form_Wizard.this.f2960j + 1; i < Form_Wizard.this.f2956f.f2990a.length; i++) {
                    C0883n.C0884a aVar = Form_Wizard.this.f2956f.f2990a[i];
                    if (aVar.f2996f && aVar.f2991a.equals("upload_box")) {
                        ((AttachDialog) Form_Wizard.this.f2958h[i]).m600b();
                        aVar.f3003m = true;
                        return;
                    }
                }
            }
        }
    }

    public class C0879j implements AttachDialog.AbstractC0559i {
        final TextView f2987a;
        final C0883n.C0884a f2988b;

        C0879j(Form_Wizard xVar, TextView textView, C0883n.C0884a aVar) {
            this.f2987a = textView;
            this.f2988b = aVar;
        }

        @Override
        public void mo153a(AttachDialog nVar, int i, String str) {
        }

        @Override
        public void mo152b(AttachDialog nVar, int i) {
            TextView textView = this.f2987a;
            textView.setText("ФАЙЛЫ (" + nVar.m589o() + ")");
        }

        @Override
        public void mo151c(AttachDialog nVar) {
            this.f2988b.f3003m = false;
        }

        @Override
        public void mo150d(AttachDialog nVar, int i, String str) {
            TextView textView = this.f2987a;
            textView.setText("ФАЙЛЫ (" + nVar.m589o() + ")");
        }
    }

    private static class C0880k extends BBString.C0674e {
        C0880k() {
            this.f2263g = (float) ((int) (BBString.f2189v0 * 12.0f));
            this.f2265i = 0;
            this.f2266j = 0;
        }
    }

    private static class C0881l extends BBString.C0674e {
        C0881l() {
            this.f2263g = (float) ((int) (BBString.f2189v0 * 2.0f));
            this.f2265i = 0;
            this.f2266j = 0;
        }
    }

    private class SendWizardRequest extends API.ForumGetFromRequest {
        SendWizardRequest(int sendCode) {
            super(Form_Wizard.this.forumNumber, Form_Wizard.this.topicId, sendCode, Form_Wizard.this.f2956f.m149a());
            this.statusMessage = "Отправка формы";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (Form_Wizard.this.wizardPanel != null) {
                boolean z = true;
                if (status == 0) {
                    int i2 = this.sendCode;
                    if (i2 == 1) {
                        Document l = uVar.getDocument(0);
                        String n = l.getString(1);
                        TextView textView = Form_Wizard.this.previewTitle;
                        StringBuilder sb = new StringBuilder();
                        sb.append(l.getString(0));
                        sb.append(TextUtils.isEmpty(n) ? "" : ", " + n);
                        textView.setText(Util.C0427h.UnEscapeString(sb.toString()));
                        Form_Wizard.this.m180F(BBString.getBBString(l.getString(2), Page_Topic.PostModel.m97c(l.getDocument(3))));
                    } else if (i2 == 2) {
                        Page a0Var = Form_Wizard.this.f2952b;
                        Form_Wizard.this.m184B();
                        if (a0Var instanceof Page_Forum) {
                            ((Page_Forum) a0Var).f1533Z = null;
                        } else if (a0Var instanceof Page_Topic) {
                            ((Page_Topic) a0Var).f3035h0 = null;
                        }
                        if (Form_Wizard.this.topicId != 0) {
                            Tab f1Var = a0Var.tab;
                            MainActivity mainActivity = Form_Wizard.this.mainActivity;
                            int intValue = uVar.getInt(0).intValue();
                            int intValue2 = uVar.getInt(1).intValue();
                            int i3 = Prefs.f1179q;
                            f1Var.addPage(new Page_Topic(mainActivity, intValue, (intValue2 / i3) * i3, 0, uVar.getInt(2).intValue(), null, null));
                            return;
                        }
                        a0Var.tab.addPage(new Page_Topic(Form_Wizard.this.mainActivity, uVar.getInt(0), 0));
                    }
                } else if (3 == status) {
                    Toast.makeText(Form_Wizard.this.mainActivity, "Сервер отказал в создании темы (неверный параметр)", 0).show();
                } else if (4 == status) {
                    Toast.makeText(Form_Wizard.this.mainActivity, "Тема отправлена на премодерацию", 0).show();
                } else if (5 == status) {
                    Document l2 = uVar.getDocument(0);
                    for (int i4 = 0; i4 < l2.count(); i4++) {
                        String n2 = l2.getString(i4);
                        if (Form_Wizard.this.f2959i[i4] != null) {
                            Form_Wizard.this.f2959i[i4].setText(n2);
                        } else if (!TextUtils.isEmpty(n2)) {
                            Toast.makeText(Form_Wizard.this.mainActivity, n2, 0).show();
                            z = false;
                        }
                    }
                    if (z) {
                        Toast.makeText(Form_Wizard.this.mainActivity, "При заполнении формы допущены ошибки", 0).show();
                    }
                }
            }
        }
    }

    public Form_Wizard(MainActivity mainActivity, int forumNumber, int topicId, String str, C0883n nVar, BBDisplay.IBBDisplayCallback bVar) {
        this.mainActivity = mainActivity;
        this.forumNumber = forumNumber;
        this.topicId = topicId;
        this.f2955e = str;
        this.f2956f = nVar;
        this.f2957g = bVar;
        if (f2949t == null) {
            f2949t = new C0880k();
            f2950u = new C0881l();
        }
        RelativeLayout relativeLayout = (RelativeLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.topicwizard, (ViewGroup) null);
        this.wizardPanel = relativeLayout;
        this.wizardSend = (Button) relativeLayout.findViewById(R.id.wizardSend);
        this.wizardAttach = (Button) this.wizardPanel.findViewById(R.id.wizardAttach);
        this.wizardAttachCount = (TextView) this.wizardPanel.findViewById(R.id.wizardAttachCount);
        this.wizardSmiles = (Button) this.wizardPanel.findViewById(R.id.wizardSmiles);

        this.wizardPreview =(ToggleButton) this.wizardPanel.findViewById(R.id.wizardPreview);
        wizardPreview.setOnClickListener(new View$OnClickListenerC0870b());
        ScrollView scrollView = (ScrollView) this.wizardPanel.findViewById(R.id.previewScroll);
        this.previewScroll = scrollView;
        this.previewTitle = (TextView) scrollView.findViewById(R.id.previewTitle);
        BBDisplay bBDisplay = (BBDisplay) this.previewScroll.findViewById(R.id.previewCode);
        this.previewCode = bBDisplay;
        bBDisplay.setOverlay((BBOverlay) this.previewScroll.findViewById(R.id.previewOverlay));
        previewTitle.setText(str);
        this.wizardPanel.findViewById(R.id.wizardPanel).setOnClickListener(new View$OnClickListenerC0871c());
        this.wizardSend.setOnClickListener(new WizardSendOnClickListener());
        this.wizardSmiles.setOnClickListener(new wizardSmilesOnClickListener());
        this.wizardAttach.setVisibility(8);
        this.wizardAttachCount.setVisibility(8);
        this.wizardSmiles.setVisibility(8);
        C0883n.C0884a[] aVarArr = this.f2956f.f2990a;
        this.f2958h = new Object[aVarArr.length];
        this.f2959i = new TextView[aVarArr.length];
        LinearLayout wizardContent = (LinearLayout) this.wizardPanel.findViewById(R.id.wizardContent);
        int i3 = 0;
        while (true) {
            C0883n.C0884a[] aVarArr2 = this.f2956f.f2990a;
            if (i3 < aVarArr2.length) {
                C0883n.C0884a aVar = aVarArr2[i3];
                if (aVar.f2991a.equalsIgnoreCase("text")) {
                    m155y(i3, aVar, wizardContent);
                } else if (aVar.f2991a.equalsIgnoreCase("editor")) {
                    m158v(i3, aVar, wizardContent);
                } else if (aVar.f2991a.equalsIgnoreCase("dropdown")) {
                    m159u(i3, aVar, wizardContent);
                } else if (aVar.f2991a.equalsIgnoreCase("checkbox_list")) {
                    m160t(i3, aVar, wizardContent);
                } else if (aVar.f2991a.equalsIgnoreCase("upload_box")) {
                    m185A(i3, aVar, wizardContent);
                } else if (aVar.f2991a.equalsIgnoreCase("title")) {
                    m154z(i3, aVar, wizardContent);
                }
                i3++;
            } else {
                this.wizardPanel.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                return;
            }
        }
    }

    public void m180F(BBString pVar) {
        if (pVar != null) {
            this.mainActivity.mainLayout.m859w(null);
            this.wizardSmiles.setVisibility(8);
            this.wizardAttach.setVisibility(8);
            this.wizardAttachCount.setVisibility(8);
            this.wizardPreview.setChecked(true);
            this.previewScroll.setVisibility(0);
            this.previewCode.setBBString(pVar);
            return;
        }
        this.wizardPreview.setChecked(false);
        this.previewScroll.setVisibility(8);
    }

    void m185A(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        if (aVar.f2996f) {
            AttachDialog nVar = new AttachDialog(this.mainActivity, new C0877h(aVar, i));
            SparseArray<String> sparseArray = aVar.f3002l;
            if (sparseArray != null) {
                nVar.m584t(sparseArray);
                this.wizardAttachCount.setText(Integer.valueOf(nVar.m589o()).toString());
            }
            nVar.m583u(aVar.f2998h, aVar.f2997g);
            this.wizardAttach.setOnClickListener(new View$OnClickListenerC0878i());
            this.f2958h[i] = nVar;
            return;
        }
        m156x(i, aVar, linearLayout);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (aVar.f2997g > 0) {
            spannableStringBuilder.append((CharSequence) "Макс. размер файла: ");
            int length = spannableStringBuilder.length();
            String str = aVar.f2997g + " МБ";
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.setSpan(new StyleSpan(1), length, str.length() + length, 33);
        }
        String[] strArr = aVar.f2998h;
        if (strArr != null && strArr.length > 0) {
            if (aVar.f2997g > 0) {
                spannableStringBuilder.append((CharSequence) "\n");
            }
            spannableStringBuilder.append((CharSequence) "Расширения: ");
            int length2 = spannableStringBuilder.length();
            for (int i2 = 0; i2 < aVar.f2998h.length; i2++) {
                spannableStringBuilder.append((CharSequence) (aVar.f2998h[i2] + " "));
            }
            spannableStringBuilder.setSpan(new StyleSpan(1), length2, spannableStringBuilder.length() - 1, 33);
        }
        if (spannableStringBuilder.length() > 0) {
            TextView textView = new TextView(this.mainActivity);
            textView.setTextColor(Skin.C0353a.f1365U);
            textView.setText(spannableStringBuilder);
            textView.setTextSize(10.0f);
            textView.setPadding(((int) this.mainActivity.f731b) * 2, 0, 0, 0);
            linearLayout.addView(textView);
        }
        TextView textView2 = new TextView(this.mainActivity);
        textView2.setClickable(true);
        textView2.setTextColor(this.mainActivity.skin.m738d(R.color.button_flat_text));
        textView2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.button_flat));
        textView2.setTextSize(16.0f);
        textView2.setTypeface(null, 1);
        textView2.setText("ФАЙЛЫ...");
        textView2.setGravity(17);
        float f = this.mainActivity.f731b;
        textView2.setPadding((int) (f * 8.0f), 0, (int) (f * 8.0f), 0);
        linearLayout.addView(textView2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
        layoutParams.width = -2;
        MainActivity mainActivity = this.mainActivity;
        float f2 = mainActivity.f731b;
        layoutParams.height = (int) (36.0f * f2);
        layoutParams.topMargin = (int) (f2 * 8.0f);
        layoutParams.gravity = 1;
        AttachDialog nVar2 = new AttachDialog(mainActivity, new C0879j(this, textView2, aVar));
        SparseArray<String> sparseArray2 = aVar.f3002l;
        if (sparseArray2 != null) {
            nVar2.m584t(sparseArray2);
            textView2.setText("ФАЙЛЫ (" + nVar2.m589o() + ")");
        }
        nVar2.m583u(aVar.f2998h, aVar.f2997g);
        textView2.setOnClickListener(new View$OnClickListenerC0869a(this, nVar2, aVar));
        this.f2958h[i] = nVar2;
        m157w(i, aVar, linearLayout);
    }

    public void m184B() {
        m183C();
        m161s();
        Page a0Var = this.f2952b;
        a0Var.f1086t = null;
        a0Var.changeTitleTabError();
        this.f2952b.tab.m714l(null, false);
        this.f2952b = null;
    }

    public void m183C() {
        this.mainActivity.mainLayout.m868n(true);
    }

    public void m182D() {
        this.mainActivity.mainLayout.m868n(false);
    }

    public void m181E(Page a0Var) {
        this.f2952b = a0Var;
        a0Var.f1086t = this.f2955e;
        a0Var.changeTitleTabError();
        this.f2952b.tab.m714l(this.wizardPanel, true);
        m182D();
        int i = 0;
        while (true) {
            C0883n.C0884a[] aVarArr = this.f2956f.f2990a;
            if (i < aVarArr.length) {
                if (aVarArr[i].f3003m) {
                    ((AttachDialog) this.f2958h[i]).m600b();
                }
                i++;
            } else {
                return;
            }
        }
    }

    public boolean m162r() {
        if (this.previewScroll.getVisibility() != 0) {
            return false;
        }
        m180F(null);
        return true;
    }

    public void m161s() {
        int i = 0;
        while (true) {
            C0883n.C0884a[] aVarArr = this.f2956f.f2990a;
            if (i < aVarArr.length) {
                C0883n.C0884a aVar = aVarArr[i];
                if (aVar.f2991a.equalsIgnoreCase("text")) {
                    aVar.f3001k = ((EditText) this.f2958h[i]).getText().toString();
                } else if (aVar.f2991a.equalsIgnoreCase("editor")) {
                    aVar.f3001k = ((BBEditor) this.f2958h[i]).m524a();
                } else if (aVar.f2991a.equalsIgnoreCase("dropdown")) {
                    aVar.f2999i = ((Integer) ((TextView) this.f2958h[i]).getTag()).intValue();
                } else if (aVar.f2991a.equalsIgnoreCase("checkbox_list")) {
                    aVar.f3000j = 0;
                    List list = (List) this.f2958h[i];
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (((Widgets$CheckboxTextView) list.get(i2)).getChecked()) {
                            aVar.f3000j |= 1 << i2;
                        }
                    }
                } else if (aVar.f2991a.equalsIgnoreCase("upload_box")) {
                    aVar.f3002l = ((AttachDialog) this.f2958h[i]).m590n();
                }
                i++;
            } else {
                return;
            }
        }
    }

    void m160t(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        m156x(i, aVar, linearLayout);
        Vector vector = new Vector();
        for (int i2 = 0; i2 < aVar.f2998h.length; i2++) {
            Widgets$CheckboxTextView widgets$CheckboxTextView = new Widgets$CheckboxTextView(this.mainActivity);
            widgets$CheckboxTextView.setClickable(true);
            widgets$CheckboxTextView.setText(aVar.f2998h[i2]);
            widgets$CheckboxTextView.setTextColor(Skin.C0353a.f1365U);
            widgets$CheckboxTextView.setTextSize(18.0f);
            widgets$CheckboxTextView.setGravity(16);
            widgets$CheckboxTextView.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.checkbox_left));
            float f = this.mainActivity.f731b;
            widgets$CheckboxTextView.setPadding(0, (int) (4.0f * f), 0, (int) (f * 6.0f));
            if (0 != (aVar.f3000j & (1 << i2))) {
                widgets$CheckboxTextView.setChecked(true);
            }
            vector.add(widgets$CheckboxTextView);
            linearLayout.addView(widgets$CheckboxTextView);
        }
        this.f2958h[i] = vector;
        m157w(i, aVar, linearLayout);
    }

    void m159u(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        m156x(i, aVar, linearLayout);
        TextView textView = new TextView(this.mainActivity);
        textView.setSingleLine(true);
        textView.setTextSize(18.0f);
        textView.setCompoundDrawablePadding((int) (this.mainActivity.f731b * 6.0f));
        textView.setTextColor(Skin.C0353a.f1365U);
        textView.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.edit_text_material));
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.mainActivity.skin.m736f(R.drawable.ic_spinner_drop_down), (Drawable) null);
        textView.setTag(Integer.valueOf(aVar.f2999i));
        textView.setText(aVar.f2998h[aVar.f2999i]);
        textView.setOnClickListener(new View$OnClickListenerC0875g(textView, aVar));
        this.f2958h[i] = textView;
        linearLayout.addView(textView);
        m157w(i, aVar, linearLayout);
    }

    void m158v(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        m156x(i, aVar, linearLayout);
        ViewGroup viewGroup = (ViewGroup) this.mainActivity.getLayoutInflater().inflate(R.layout.bb_editor, (ViewGroup) linearLayout, false);
        EditText editText = (EditText) viewGroup.findViewById(R.id.editor);
        editText.setMinLines(4);
        editText.setOnFocusChangeListener(new View$OnFocusChangeListenerC0874f(aVar, i));
        editText.setText(aVar.f3001k);
        this.f2958h[i] = new BBEditor(this.mainActivity, viewGroup, editText, true);
        linearLayout.addView(viewGroup);
        m157w(i, aVar, linearLayout);
    }

    void m157w(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        if (!TextUtils.isEmpty(aVar.f2994d)) {
            BBString x = BBString.getBBString("[size=1][color=gray]" + aVar.f2994d + "[/color][/size]", null);
            if (x != null) {
                x.f2246z = f2950u;
                BBDisplay bBDisplay = new BBDisplay(this.mainActivity);
                bBDisplay.setCallback(this.f2957g);
                bBDisplay.setBBString(x);
                linearLayout.addView(bBDisplay);
            }
        }
        this.f2959i[i] = new TextView(this.mainActivity);
        this.f2959i[i].setTextColor(this.mainActivity.skin.m738d(R.color.moderator_text));
        linearLayout.addView(this.f2959i[i]);
        View view = new View(this.mainActivity);
        linearLayout.addView(view);
        view.getLayoutParams().height = (int) (this.mainActivity.f731b * 12.0f);
    }

    void m156x(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        String str = "[size=3]" + aVar.f2992b + "[/size]";
        if (aVar.f2995e) {
            str = "[color=red]" + str + "[/color]";
        }
        if (!TextUtils.isEmpty(aVar.f2993c)) {
            str = str + "\n[size=1]" + aVar.f2993c + "[/size]";
        }
        BBString x = BBString.getBBString(str, null);
        if (x != null) {
            x.f2246z = f2949t;
            BBDisplay bBDisplay = new BBDisplay(this.mainActivity);
            bBDisplay.setCallback(this.f2957g);
            bBDisplay.setBBString(x);
            linearLayout.addView(bBDisplay);
        }
    }

    void m155y(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        m156x(i, aVar, linearLayout);
        EditText editText = new EditText(this.mainActivity);
        editText.setTextColor(Skin.C0353a.f1365U);
        editText.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.edit_text_material));
        if (aVar.f2999i > 1) {
            editText.setSingleLine(false);
            editText.setImeOptions(1);
            editText.setMinLines(aVar.f2999i);
            editText.setMaxLines(aVar.f2999i);
            editText.setGravity(48);
        } else {
            editText.setSingleLine(true);
        }
        if (aVar.f2997g > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(aVar.f2997g)});
        }
        editText.setText(aVar.f3001k);
        linearLayout.addView(editText);
        this.f2958h[i] = editText;
        m157w(i, aVar, linearLayout);
    }

    void m154z(int i, C0883n.C0884a aVar, LinearLayout linearLayout) {
        m156x(i, aVar, linearLayout);
        BBString x = BBString.getBBString(aVar.f2994d, null);
        if (x != null) {
            x.f2246z.f2264h = (float) ((int) (this.mainActivity.f731b * 8.0f));
            BBDisplay bBDisplay = new BBDisplay(this.mainActivity);
            bBDisplay.setCallback(this.f2957g);
            bBDisplay.setBBString(x);
            linearLayout.addView(bBDisplay);
        }
        this.f2958h[i] = x;
    }

    public static class C0883n {
        C0884a[] f2990a;

        public class C0884a {
            String f2991a;
            String f2992b;
            String f2993c;
            String f2994d;
            boolean f2995e;
            boolean f2996f;
            int f2997g;
            String[] f2998h;
            int f2999i;
            long f3000j;
            String f3001k;
            SparseArray<String> f3002l;
            boolean f3003m;

            private C0884a(C0883n nVar) {
            }

            C0884a(C0883n nVar, View$OnClickListenerC0870b bVar) {
                this(nVar);
            }
        }

        public C0883n(Document uVar) {
            this.f2990a = new C0884a[uVar.count()];
            for (int i = 0; i < this.f2990a.length; i++) {
                Document l = uVar.getDocument(i);
                C0884a[] aVarArr = this.f2990a;
                C0884a aVar = new C0884a(this, null);
                aVarArr[i] = aVar;
                aVar.f2991a = l.getString(0);
                boolean z = true;
                aVar.f2992b = l.getString(1);
                aVar.f2993c = l.getString(2);
                aVar.f2994d = l.getString(3);
                int intValue = l.getInt(4).intValue();
                aVar.f2995e = (intValue & 1) != 0;
                aVar.f2996f = (intValue & 2) == 0 ? false : z;
                aVar.f3001k = l.getString(5);
                if (aVar.f2991a.equalsIgnoreCase("text") || aVar.f2991a.equalsIgnoreCase("editor")) {
                    Integer m = l.getInt(6);
                    aVar.f2999i = m != null ? m.intValue() : 0;
                    Integer m2 = l.getInt(7);
                    aVar.f2997g = m2 != null ? m2.intValue() : 0;
                } else if (aVar.f2991a.equalsIgnoreCase("dropdown") || aVar.f2991a.equalsIgnoreCase("checkbox_list")) {
                    Document l2 = l.getDocument(6);
                    aVar.f2998h = new String[l2.count()];
                    for (int i2 = 0; i2 < l2.count(); i2++) {
                        aVar.f2998h[i2] = l2.getString(i2);
                    }
                } else if (aVar.f2991a.equalsIgnoreCase("upload_box")) {
                    Integer m3 = l.getInt(6);
                    aVar.f2997g = m3 != null ? m3.intValue() : 0;
                    Document l3 = l.getDocument(7);
                    aVar.f2998h = new String[l3 != null ? l3.count() : 0];
                    int i3 = 0;
                    while (true) {
                        String[] strArr = aVar.f2998h;
                        if (i3 < strArr.length) {
                            strArr[i3] = l3.getString(i3);
                            i3++;
                        }
                    }
                }
            }
        }

        Document m149a() {
            Document uVar = new Document();
            C0884a[] aVarArr = this.f2990a;
            for (C0884a aVar : aVarArr) {
                if (aVar.f2991a.equalsIgnoreCase("text") || aVar.f2991a.equalsIgnoreCase("editor")) {
                    uVar.append(aVar.f3001k);
                } else if (aVar.f2991a.equalsIgnoreCase("dropdown")) {
                    uVar.append(aVar.f2999i + 1);
                } else if (aVar.f2991a.equalsIgnoreCase("checkbox_list")) {
                    Document uVar2 = new Document();
                    for (int i = 0; i < 64; i++) {
                        if (0 != (aVar.f3000j & (1 << i))) {
                            uVar2.append(i + 1);
                        }
                    }
                    uVar.append(uVar2);
                } else if (aVar.f2991a.equalsIgnoreCase("upload_box")) {
                    Document uVar3 = new Document();
                    if (aVar.f3002l != null) {
                        for (int i2 = 0; i2 < aVar.f3002l.size(); i2++) {
                            int keyAt = aVar.f3002l.keyAt(i2);
                            if (keyAt > 0) {
                                uVar3.append(keyAt);
                            }
                        }
                    }
                    uVar.append(uVar3);
                } else {
                    uVar.append(0);
                }
            }
            return uVar;
        }

        public void m148b(Bundle bundle, String str) {
            bundle.putInt(str + "_n", this.f2990a.length);
            int i = 0;
            while (true) {
                C0884a[] aVarArr = this.f2990a;
                if (i < aVarArr.length) {
                    C0884a aVar = aVarArr[i];
                    bundle.putString(str + "_ft" + i, aVar.f2991a);
                    bundle.putString(str + "_fh" + i, aVar.f2992b);
                    bundle.putString(str + "_fd" + i, aVar.f2993c);
                    bundle.putString(str + "_fe" + i, aVar.f2994d);
                    bundle.putBoolean(str + "_fm" + i, aVar.f2995e);
                    bundle.putBoolean(str + "_fg" + i, aVar.f2996f);
                    bundle.putInt(str + "_fs" + i, aVar.f2997g);
                    bundle.putInt(str + "_fi" + i, aVar.f2999i);
                    bundle.putLong(str + "_fl" + i, aVar.f3000j);
                    bundle.putString(str + "_fv" + i, aVar.f3001k);
                    bundle.putBoolean(str + "_fa" + i, aVar.f3003m);
                    if (aVar.f2998h != null) {
                        bundle.putInt(str + "_fo" + i, aVar.f2998h.length);
                        for (int i2 = 0; i2 < aVar.f2998h.length; i2++) {
                            bundle.putString(str + "_fo" + i + "_" + i2, aVar.f2998h[i2]);
                        }
                    }
                    if (aVar.f3002l != null) {
                        bundle.putInt(str + "_ff" + i, aVar.f3002l.size());
                        for (int i3 = 0; i3 < aVar.f3002l.size(); i3++) {
                            bundle.putInt(str + "_ff" + i + "_k" + i3, aVar.f3002l.keyAt(i3));
                            bundle.putString(str + "_ff" + i + "_v" + i3, aVar.f3002l.valueAt(i3));
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }

        public C0883n(Bundle bundle, String str) {
            this.f2990a = new C0884a[bundle.getInt(str + "_n")];
            int i = 0;
            while (true) {
                C0884a[] aVarArr = this.f2990a;
                if (i < aVarArr.length) {
                    C0884a aVar = new C0884a(this, null);
                    aVarArr[i] = aVar;
                    aVar.f2991a = bundle.getString(str + "_ft" + i);
                    aVar.f2992b = bundle.getString(str + "_fh" + i);
                    aVar.f2993c = bundle.getString(str + "_fd" + i);
                    aVar.f2994d = bundle.getString(str + "_fe" + i);
                    aVar.f2995e = bundle.getBoolean(str + "_fm" + i);
                    aVar.f2996f = bundle.getBoolean(str + "_fg" + i);
                    aVar.f2997g = bundle.getInt(str + "_fs" + i);
                    aVar.f2999i = bundle.getInt(str + "_fi" + i);
                    aVar.f3000j = bundle.getLong(str + "_fl" + i);
                    aVar.f3001k = bundle.getString(str + "_fv" + i);
                    aVar.f3003m = bundle.getBoolean(str + "_fa" + i);
                    int i2 = bundle.getInt(str + "_fo" + i);
                    if (i2 > 0) {
                        aVar.f2998h = new String[i2];
                        for (int i3 = 0; i3 < i2; i3++) {
                            String[] strArr = aVar.f2998h;
                            strArr[i3] = bundle.getString(str + "_fo" + i + "_" + i3);
                        }
                    }
                    int i4 = bundle.getInt(str + "_ff" + i);
                    if (i4 > 0) {
                        aVar.f3002l = new SparseArray<>(i4);
                        for (int i5 = 0; i5 < i4; i5++) {
                            SparseArray<String> sparseArray = aVar.f3002l;
                            int i6 = bundle.getInt(str + "_ff" + i + "_k" + i5);
                            sparseArray.put(i6, bundle.getString(str + "_ff" + i + "_v" + i5));
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
