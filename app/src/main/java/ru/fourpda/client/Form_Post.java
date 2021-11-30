package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Form_Post implements AttachDialog.AbstractC0559i {
    private ViewGroup f2846a;
    private Button f2847b;
    private Button f2848c;
    private Button f2849d;
    private TextView f2850e;
    private EditText f2851f;
    private ToggleButton f2852g;
    private ScrollView f2853h;
    private BBDisplay bbDisplay;
    private MainActivity mainActivity;
    private Page page;
    private AttachDialog attachDialog;
    private BBEditor bbEditor;
    private AbstractC0846i f2859n;
    private ForumPostModel forumPostModel;

    public class View$OnClickListenerC0838a implements View.OnClickListener {
        View$OnClickListenerC0838a() {
          //  Form_Post.this = r1;
        }

        @Override
        public void onClick(View view) {
            Form_Post.this.mainActivity.mainLayout.m866p(true);
        }
    }

    public class View$OnClickListenerC0839b implements View.OnClickListener {
        View$OnClickListenerC0839b() {
        //    Form_Post.this = r1;
        }

        @Override
        public void onClick(View view) {
            Form_Post.this.m198w(false);
        }
    }

    public class View$OnLongClickListenerC0840c implements View.OnLongClickListener {
        View$OnLongClickListenerC0840c() {
           // Form_Post.this = r1;
        }

        @Override
        public boolean onLongClick(View view) {
            Form_Post.this.m198w(true);
            view.setPressed(false);
            return true;
        }
    }

    public class View$OnClickListenerC0841d implements View.OnClickListener {
        View$OnClickListenerC0841d() {
          //  Form_Post.this = r1;
        }

        @Override
        public void onClick(View view) {
            Form_Post.this.attachDialog.m600b();
            Form_Post.this.forumPostModel.f2884m = true;
        }
    }

    public class C0842e implements TextWatcher {
        C0842e() {
         //   Form_Post.this = r1;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean z = true;
            Form_Post.this.f2847b.setEnabled(editable.length() > 0);
            ToggleButton toggleButton = Form_Post.this.f2852g;
            if (editable.length() <= 0) {
                z = false;
            }
            toggleButton.setEnabled(z);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class View$OnClickListenerC0843f implements View.OnClickListener {
        View$OnClickListenerC0843f() {

        }

        @Override
        public void onClick(View view) {
            Form_Post.this.bbEditor.m523b();
        }
    }

    public class View$OnClickListenerC0844g implements View.OnClickListener {
        View$OnClickListenerC0844g() {
        //    Form_Post.this = r1;
        }

        @Override
        public void onClick(View view) {
            if (Form_Post.this.f2853h.getVisibility() != 0) {
                DocumentManager.getResultRequest(new PreviewPostRequest());
            } else {
                Form_Post.this.m218A(null, true);
            }
        }
    }

    public class View$OnClickListenerC0845h implements View.OnClickListener {
        final DlgSimple f2868a;
        final boolean f2869b;

        View$OnClickListenerC0845h(DlgSimple q1Var, boolean z) {
            this.f2868a = q1Var;
            this.f2869b = z;
        }

        @Override
        public void onClick(View view) {
            if (Form_Post.this.forumPostModel != null) {
                Form_Post.this.forumPostModel.reason = this.f2868a.editText.getText().toString();
                Form_Post.this.forumPostModel.showMark = this.f2868a.checkboxTextView.getChecked();
                Form_Post.this.f2859n.mo135b(Form_Post.this.forumPostModel, this.f2869b);
                Form_Post.this.m202s();
            }
        }
    }

    public interface AbstractC0846i {
        void mo135b(ForumPostModel kVar, boolean z);
    }

    private class PreviewPostRequest extends API.ForumPostRequest {
        PreviewPostRequest() {
            super(Form_Post.this.forumPostModel.topicId,
                    Form_Post.this.forumPostModel.postId > 0 ? -Form_Post.this.forumPostModel.postId : -1,
                    Form_Post.this.f2851f.getText().toString(),
                    Form_Post.this.forumPostModel.getPostAttaches(), "");
            this.statusMessage = "Загрузка препросмотра";
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (status == 0) {
                Form_Post.this.m218A(BBString.getBBString(uVar.getString(0), Page_Topic.PostModel.m97c(uVar.getDocument(1))), true);
            } else {
                Toast.makeText(Form_Post.this.mainActivity, "Ошибка препросмотра!", 0).show();
            }
        }
    }

    public Form_Post(MainActivity mainActivity, AbstractC0846i iVar) {
        this.mainActivity = mainActivity;
        this.f2859n = iVar;
        ViewGroup viewGroup = (ViewGroup) mainActivity.getLayoutInflater().inflate(Prefs.f1176n ? R.layout.posteditor_tags_below : R.layout.posteditor, (ViewGroup) null);
        this.f2846a = viewGroup;
        viewGroup.findViewById(R.id.editorPanel).setOnClickListener(new View$OnClickListenerC0838a());
        Button button = (Button) this.f2846a.findViewById(R.id.editorSend);
        this.f2847b = button;
        button.setOnClickListener(new View$OnClickListenerC0839b());
        this.f2847b.setOnLongClickListener(new View$OnLongClickListenerC0840c());
        this.f2851f = (EditText) this.f2846a.findViewById(R.id.editorEdit);
        View findViewById = this.f2846a.findViewById(R.id.tagsBarScroll);
        if (findViewById != null) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 16) {
                findViewById.setScrollBarDefaultDelayBeforeFade(2000);
            }
            if (i >= 29) {
                findViewById.setHorizontalScrollbarThumbDrawable(this.mainActivity.skin.m736f(R.drawable.scroll_horz_thumb));
            } else {
                try {
                    Field declaredField = View.class.getDeclaredField("mScrollCache");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(findViewById);
                    Field declaredField2 = obj.getClass().getDeclaredField("scrollBar");
                    declaredField2.setAccessible(true);
                    Object obj2 = declaredField2.get(obj);
                    Method declaredMethod = obj2.getClass().getDeclaredMethod("setHorizontalThumbDrawable", Drawable.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(obj2, this.mainActivity.skin.m736f(R.drawable.scroll_horz_thumb));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.f2848c = (Button) this.f2846a.findViewById(R.id.editorAttach);
        this.f2850e = (TextView) this.f2846a.findViewById(R.id.editorAttachCount);
        this.attachDialog = new AttachDialog(this.mainActivity, this);
        this.f2848c.setOnClickListener(new View$OnClickListenerC0841d());
        this.bbEditor = new BBEditor(this.mainActivity, this.f2846a, this.f2851f, true);
        this.f2851f.addTextChangedListener(new C0842e());
        Button button2 = (Button) this.f2846a.findViewById(R.id.editorSmiles);
        this.f2849d = button2;
        button2.setOnClickListener(new View$OnClickListenerC0843f());
        ToggleButton toggleButton = (ToggleButton) this.f2846a.findViewById(R.id.editorPreview);
        this.f2852g = toggleButton;
        toggleButton.setOnClickListener(new View$OnClickListenerC0844g());
        ScrollView scrollView = (ScrollView) this.f2846a.findViewById(R.id.previewScroll);
        this.f2853h = scrollView;
        BBDisplay bBDisplay = (BBDisplay) scrollView.findViewById(R.id.previewCode);
        this.bbDisplay = bBDisplay;
        bBDisplay.setOverlay((BBOverlay) this.f2853h.findViewById(R.id.previewOverlay));
    }

    public void m218A(BBString pVar, boolean z) {
        if (pVar != null) {
            if (z) {
                this.mainActivity.mainLayout.m859w(null);
            }
            m195z(false);
            this.f2852g.setChecked(true);
            this.f2853h.setVisibility(0);
            this.bbDisplay.setBBString(pVar);
            return;
        }
        if (z) {
            this.mainActivity.mainLayout.m859w(this.f2851f);
        }
        m195z(this.forumPostModel.f2882k);
        this.f2852g.setChecked(false);
        this.f2853h.setVisibility(8);
    }

    @SuppressLint("SetTextI18n")
    private void m217B() {
        int i;
        int i2 = 0;
        if (this.forumPostModel.attaches != null) {
            i = 0;
            for (int i3 = 0; i3 < this.forumPostModel.attaches.size(); i3++) {
                if (this.forumPostModel.attaches.keyAt(i3) > 0) {
                    i++;
                }
            }
        } else {
            i = 0;
        }
        this.f2850e.setText(Integer.valueOf(i).toString());
        TextView textView = this.f2850e;
        if (i <= 0) {
            i2 = 4;
        }
        textView.setVisibility(i2);
    }

    public void m198w(boolean z) {
        int i;
        this.forumPostModel.postMessage = this.f2851f.getText().toString();
        ForumPostModel forumPostModel = this.forumPostModel;
        if (forumPostModel.f2878g) {
            DlgSimple q1Var = new DlgSimple(this.mainActivity, "Причина редактирования", false, null, null);
            if (!TextUtils.isEmpty(this.forumPostModel.reason)) {
                q1Var.editText.setText(this.forumPostModel.reason);
                q1Var.editText.setSelection(this.forumPostModel.reason.length());
            }
            q1Var.checkboxTextView.setChecked(true);
            DocumentManager.MemberInfoModel memberInfoModel = DocumentManager.getMemberInfoModel();
            if (memberInfoModel != null && ((i = memberInfoModel.memberGroup) == 4 || i == 10 || i == 16)) {
                q1Var.checkboxTextView.setText("Показывать отметку");
                q1Var.checkboxTextView.setVisibility(0);
            }
            q1Var.m620f(new View$OnClickListenerC0845h(q1Var, z), true);
            q1Var.show(true, true, true);
            return;
        }
        this.f2859n.mo135b(forumPostModel, z);
        m202s();
    }

    private void m195z(boolean z) {
        int i = 0;
        this.f2849d.setVisibility(z ? 0 : 8);
        this.f2848c.setVisibility(z ? 0 : 8);
        TextView textView = this.f2850e;
        if (!z) {
            i = 8;
        } else if (Integer.parseInt(textView.getText().toString()) <= 0) {
            i = 4;
        }
        textView.setVisibility(i);
    }

    @Override
    public void mo153a(AttachDialog nVar, int i, String str) {
        Editable text = this.f2851f.getText();
        int max = Math.max(this.f2851f.getSelectionStart(), this.f2851f.getSelectionEnd());
        if (max < 0) {
            max = text.length();
        }
        String str2 = "[attachment=\"" + i + ":" + str + "\"]";
        text.insert(max, str2);
        this.f2851f.setSelection(max, str2.length() + max);
    }

    @Override
    public void mo152b(AttachDialog nVar, int i) {
        SparseArray<String> sparseArray = this.forumPostModel.attaches;
        sparseArray.put(-i, sparseArray.get(i));
        this.forumPostModel.attaches.remove(i);
        m217B();
    }

    @Override
    public void mo151c(AttachDialog nVar) {
        this.forumPostModel.f2884m = false;
    }

    @Override
    public void mo150d(AttachDialog nVar, int i, String str) {
        ForumPostModel kVar = this.forumPostModel;
        if (kVar.attaches == null) {
            kVar.attaches = new SparseArray<>();
        }
        this.forumPostModel.attaches.put(i, str);
        m217B();
    }

    public boolean m205p() {
        if (this.f2853h.getVisibility() != 0) {
            return false;
        }
        m218A(null, true);
        return true;
    }

    public void m204q() {
        this.forumPostModel.postMessage = this.f2851f.getText().toString();
    }

    public ForumPostModel m203r() {
        return this.forumPostModel;
    }

    public void m202s() {
        if (this.forumPostModel != null) {
            if (this.f2853h.getVisibility() == 0) {
                m218A(null, false);
            }
            m200u();
            m204q();
            this.f2851f.setEnabled(false);
            this.f2851f.setText("");
            this.attachDialog.m599c();
            this.forumPostModel = null;
            this.page.tab.m714l(null, false);
            Page a0Var = this.page;
            a0Var.f1086t = "";
            a0Var.changeTitleTabError();
            this.page = null;
        }
    }

    public boolean m201t() {
        return this.forumPostModel != null;
    }

    public void m200u() {
        this.mainActivity.mainLayout.m859w(null);
        this.mainActivity.mainLayout.m868n(true);
    }

    public void m199v() {
        this.mainActivity.mainLayout.m868n(false);
        this.mainActivity.mainLayout.m859w(this.f2851f);
    }

    public void m197x(int i, int i2) {
        this.f2851f.setSelection(i, i2);
    }

    public void m196y(ForumPostModel kVar, Page a0Var) {
        this.forumPostModel = kVar;
        if (kVar != null) {
            this.page = a0Var;
            a0Var.f1086t = kVar.topicTitle;
            a0Var.changeTitleTabError();
            this.page.tab.m714l(this.f2846a, true);
            ((TextView) this.f2846a.findViewById(R.id.editorTitle)).setText(this.forumPostModel.topicTitle);
            BBEditor oVar = this.bbEditor;
            ViewGroup viewGroup = this.f2846a;
            ForumPostModel kVar2 = this.forumPostModel;
            oVar.m514k(viewGroup, kVar2.f2877f, kVar2.f2876e);
            m195z(this.forumPostModel.f2882k);
            this.attachDialog.m584t(this.forumPostModel.attaches);
            m217B();
            this.f2851f.setEnabled(true);
            this.f2851f.setText(this.forumPostModel.postMessage);
            EditText editText = this.f2851f;
            editText.setSelection(editText.getText().length());
            this.f2847b.setLongClickable(this.forumPostModel.f2879h);
            m199v();
            if (this.forumPostModel.f2884m) {
                this.attachDialog.m600b();
            }
        }
    }

    public static class ForumPostModel {
        int status;
        String topicTitle;
        int topicId;
        int postId;
        boolean f2876e;
        boolean f2877f;
        boolean f2878g;
        boolean f2879h;
        String reason;
        String postMessage;
        boolean f2882k;
        SparseArray<String> attaches;
        boolean f2884m;
        boolean showMark;

        public ForumPostModel(int i, String str, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str2, String str3, SparseArray<String> sparseArray) {
            this.status = i;
            this.topicTitle = str;
            this.topicId = i2;
            this.postId = i3;
            this.f2876e = z;
            this.f2877f = z2;
            this.f2878g = z3;
            this.f2879h = z5;
            this.reason = str2;
            this.postMessage = str3;
            this.f2882k = z4;
            this.attaches = sparseArray;
        }

        public int[] getPostAttaches() {
            SparseArray<String> sparseArray = this.attaches;
            if (sparseArray == null || sparseArray.size() <= 0) {
                return null;
            }
            int[] iArr = new int[this.attaches.size()];
            for (int i = 0; i < this.attaches.size(); i++) {
                iArr[i] = this.attaches.keyAt(i);
            }
            return iArr;
        }

        public void m193b(Bundle bundle, String str) {
            bundle.putInt(str + "_t", this.status);
            bundle.putString(str + "_h", this.topicTitle);
            bundle.putInt(str + "_tid", this.topicId);
            bundle.putInt(str + "_pid", this.postId);
            bundle.putBoolean(str + "_ac", this.f2876e);
            bundle.putBoolean(str + "_am", this.f2877f);
            bundle.putBoolean(str + "_ar", this.f2878g);
            bundle.putBoolean(str + "_aa", this.f2882k);
            bundle.putBoolean(str + "_as", this.f2879h);
            bundle.putString(str + "_r", this.reason);
            bundle.putString(str + "_p", this.postMessage);
            if (this.attaches != null) {
                bundle.putInt(str + "_att", this.attaches.size());
                for (int i = 0; i < this.attaches.size(); i++) {
                    bundle.putInt(str + "_atti" + i, this.attaches.keyAt(i));
                    bundle.putString(str + "_attn" + i, this.attaches.valueAt(i));
                }
            } else {
                bundle.putInt(str + "att", 0);
            }
            bundle.putBoolean(str + "_ado", this.f2884m);
        }

        public ForumPostModel(Bundle bundle, String str) {

            int i = bundle.getInt(str + "_t");
            String string = bundle.getString(str + "_h");
            int i2 = bundle.getInt(str + "_tid");
            int i3 = bundle.getInt(str + "_pid");
            boolean z = bundle.getBoolean(str + "_ac");
            boolean z2 = bundle.getBoolean(str + "_am");
            boolean z3 = bundle.getBoolean(str + "_ar");
            boolean z4 = bundle.getBoolean(str + "_aa");
            boolean z5 = bundle.getBoolean(str + "_as");
            String string2 = bundle.getString(str + "_r");
            int i4 = bundle.getInt(str + "_att");
            for (int i5 = 0; i5 < i4; i5++) {
                SparseArray<String> sparseArray = this.attaches;
                int i6 = bundle.getInt(str + "_atti" + i5);
                sparseArray.append(i6, bundle.getString(str + "_attn" + i5));
            }
            this.f2884m = bundle.getBoolean(str + "_ado");
            new ForumPostModel(i,string, i2, i3, z, z2, z3, z4, z5, string2, bundle.getString(str + "_p"), new SparseArray<>());
        }
    }
}
