package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Vector;

public class CustomDialog {
    private static List<WeakReference<Dialog>> f1802i = new Vector(10);
    protected Dialog dialog;
    protected TextView f1804b;
    protected TextView f1805c;
    protected View.OnClickListener f1806d;
    protected View.OnClickListener f1807e;
    protected boolean f1808f;
    protected boolean f1809g = true;
    protected View rootView;

    class View$OnClickListenerC0533a implements View.OnClickListener {
        View$OnClickListenerC0533a() {
        }

        @Override
        public void onClick(View view) {
            View.OnClickListener onClickListener = CustomDialog.this.f1807e;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            CustomDialog l1Var = CustomDialog.this;
            if (l1Var.f1809g) {
                l1Var.dialog.dismiss();
            }
        }
    }

    class View$OnClickListenerC0534b implements View.OnClickListener {
        View$OnClickListenerC0534b() {
        }

        @Override
        public void onClick(View view) {
            View.OnClickListener onClickListener = CustomDialog.this.f1806d;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            CustomDialog l1Var = CustomDialog.this;
            if (l1Var.f1808f) {
                l1Var.dialog.dismiss();
            }
        }
    }

    @SuppressLint("WrongConstant")
    public CustomDialog(MainActivity mainActivity, View view, String str, String str2) {
        this.rootView = view;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        float f = mainActivity.getResources().getDisplayMetrics().density;
        linearLayout.addView(view);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        int i = (int) (8.0f * f);
        linearLayout2.setPadding(i, i, i, i);
        linearLayout2.setGravity(117);
        linearLayout.addView(linearLayout2);
        TextView textView = new TextView(mainActivity);
        this.f1805c = textView;
        textView.setText(str2 == null ? "ОТМЕНА" : str2);
        this.f1805c.setClickable(true);
        this.f1805c.setPadding(i, 0, i, 0);
        this.f1805c.setSingleLine(true);
        this.f1805c.setTextSize(16.0f);
        this.f1805c.setTypeface(null, 1);
        this.f1805c.setTextColor(Skin.SkinColorModel.btnTextColor);
        this.f1805c.setBackgroundDrawable(mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
        this.f1805c.setGravity(17);
        this.f1805c.setOnClickListener(new View$OnClickListenerC0533a());
        linearLayout2.addView(this.f1805c);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f1805c.getLayoutParams();
        layoutParams.width = -2;
        int i2 = (int) (f * 36.0f);
        layoutParams.height = i2;
        layoutParams.rightMargin = i;
        TextView textView2 = new TextView(mainActivity);
        this.f1804b = textView2;
        textView2.setText(str == null ? "OK" : str);
        this.f1804b.setClickable(true);
        this.f1804b.setPadding(i, 0, i, 0);
        this.f1804b.setSingleLine(true);
        this.f1804b.setTextSize(16.0f);
        this.f1804b.setTypeface(null, 1);
        this.f1804b.setTextColor(Skin.SkinColorModel.btnTextColor);
        this.f1804b.setBackgroundDrawable(mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
        this.f1804b.setGravity(17);
        this.f1804b.setOnClickListener(new View$OnClickListenerC0534b());
        linearLayout2.addView(this.f1804b);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1804b.getLayoutParams();
        layoutParams2.width = -2;
        layoutParams2.height = i2;
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayout);
        Dialog dialog = new Dialog(mainActivity, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
        this.dialog = dialog;
        dialog.setContentView(scrollView);
        this.dialog.getWindow().setLayout(-1, -2);
        this.dialog.getWindow().setBackgroundDrawable(mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
    }

    public static void m623c(Dialog dialog) {
        f1802i.add(new WeakReference<>(dialog));
    }

    public static void dismiss() {
        for (int i = 0; i < f1802i.size(); i++) {
            Dialog dialog = f1802i.get(i).get();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void m625a(boolean z) {
        this.f1804b.setEnabled(z);
    }

    @SuppressLint("WrongConstant")
    public void show(boolean z, boolean z2, boolean z3) {
        int i = 0;
        this.f1804b.setVisibility(z ? 0 : 8);
        TextView textView = this.f1805c;
        if (!z2) {
            i = 8;
        }
        textView.setVisibility(i);
        this.dialog.setCanceledOnTouchOutside(z3);
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        this.dialog.getWindow().setSoftInputMode(16);
        attributes.gravity = 17;
        this.dialog.show();
        m623c(this.dialog);
    }

    public void m621e(View.OnClickListener onClickListener, boolean z) {
        this.f1807e = onClickListener;
        this.f1809g = z;
    }

    public void m620f(View.OnClickListener onClickListener, boolean z) {
        this.f1806d = onClickListener;
        this.f1808f = z;
    }
}
