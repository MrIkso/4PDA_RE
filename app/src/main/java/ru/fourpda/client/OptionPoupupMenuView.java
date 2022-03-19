package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class OptionPoupupMenuView {
    MainActivity mainActivity;
    IClickListener f2151b;
    LinearLayout rootLayout;
    Dialog dialog;
    View$OnClickListenerC0668a f2154e;
    int f2155f;

    public static class View$OnClickListenerC0668a implements View.OnClickListener {
        OptionPoupupMenuView f2156a;

        public View$OnClickListenerC0668a(OptionPoupupMenuView o1Var) {
            this.f2156a = o1Var;
        }

        @Override
        public void onClick(View view) {
            this.f2156a.dialog.dismiss();
            IClickListener bVar = this.f2156a.f2151b;
            if (bVar != null) {
                bVar.mo49a((Integer) view.getTag(R.string.menu_item_type), (Integer) view.getTag(R.string.menu_item_id), (Integer) view.getTag(R.string.menu_item_cmd));
                this.f2156a.f2151b = null;
            }
        }
    }

    public interface IClickListener {
        void mo49a(int i, int i2, int i3);
    }

    @SuppressLint("WrongConstant")
    public OptionPoupupMenuView(MainActivity mainActivity, IClickListener bVar, boolean z) {
        this.f2155f = 0;
        this.mainActivity = mainActivity;
        this.f2151b = bVar;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        this.rootLayout = linearLayout;
        linearLayout.setOrientation(1);
        boolean z2 = Skin.SkinColorModel.f1392k0;
        Dialog dialog = new Dialog(mainActivity, z ? z2 ? R.style.Dialog_Light : R.style.Dialog_Dark : z2 ? R.style.Menu_Light : R.style.Menu_Dark);
        this.dialog = dialog;
        dialog.setCanceledOnTouchOutside(true);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(this.rootLayout);
        this.dialog.setContentView(scrollView);
        this.dialog.getWindow().setBackgroundDrawable(mainActivity.skin.getSkinDrawable(z ? R.drawable.np_dialog : R.drawable.np_menu));
        this.f2154e = new View$OnClickListenerC0668a(this);
        View view = new View(this.mainActivity);
        this.rootLayout.addView(view);
        view.getLayoutParams().height = (int) (this.mainActivity.f731b * 8.0f);
    }

    public void addMenuItem(int i, int i2, int i3, String str) {
        addMenuItem(i, i2, i3, str, false, false, false, false);
    }

    public void addMenuItem(int i, int i2, int i3, String str, boolean z) {
        addMenuItem(i, i2, i3, str, false, false, true, z);
    }

    public void addMenuItem(int i, int i2, int i3, String str, boolean z, boolean z2) {
        addMenuItem(i, i2, i3, str, z, z2, false, false);
    }

    public void addMenuItem(int i, int i2, int i3, String str, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f2155f++;
        TextView textView = new TextView(this.mainActivity);
        textView.setTag(R.string.menu_item_type, Integer.valueOf(i));
        textView.setTag(R.string.menu_item_id, Integer.valueOf(i2));
        textView.setTag(R.string.menu_item_cmd, Integer.valueOf(i3));
        textView.setTextColor(Skin.SkinColorModel.mainTextColor);
        textView.setText(str);
        textView.setTextSize(16.0f);
        textView.setGravity(16);
        textView.setLineSpacing(this.mainActivity.f732c * 3.0f, 1.0f);
        if (z2) {
            textView.setTextColor(this.mainActivity.skin.getSkinColor(R.color.moderator_text));
        }
        textView.setClickable(!z);
        if (z) {
            textView.setSingleLine(true);
            float f = this.mainActivity.f731b;
            textView.setPadding(0, (int) (f * 8.0f), 0, (int) (f * 8.0f));
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.mainActivity);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);
            horizontalScrollView.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.border_bottom));
            float f2 = this.mainActivity.f731b;
            horizontalScrollView.setPadding((int) (f2 * 16.0f), 0, (int) (f2 * 16.0f), 0);
            horizontalScrollView.addView(textView);
            this.rootLayout.addView(horizontalScrollView);
            return;
        }
        textView.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.button_bg));
        if (z3) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.mainActivity.skin.getSkinDrawable(z4 ? R.drawable.ic_menu_checkbox_on : R.drawable.ic_zero), (Drawable) null);
            textView.setCompoundDrawablePadding((int) (this.mainActivity.f731b * 8.0f));
            float f3 = this.mainActivity.f731b;
            textView.setPadding((int) (16.0f * f3), (int) (f3 * 8.0f), (int) (12.0f * f3), (int) (f3 * 8.0f));
        } else {
            float f4 = this.mainActivity.f731b;
            textView.setPadding((int) (f4 * 16.0f), (int) (f4 * 8.0f), (int) (16.0f * f4), (int) (f4 * 8.0f));
        }
        textView.setOnClickListener(this.f2154e);
        this.rootLayout.addView(textView);
    }

    public boolean show(View view) {
        if (this.f2155f == 0) {
            return false;
        }
        View view2 = new View(this.mainActivity);
        this.rootLayout.addView(view2);
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        MainActivity mainActivity = this.mainActivity;
        layoutParams.height = (int) (mainActivity.f731b * 8.0f);
        int measuredWidth = (int) (((float) mainActivity.mainLayout.getMeasuredWidth()) * 0.7f);
        int applyDimension = (int) TypedValue.applyDimension(1, 200.0f, this.mainActivity.getResources().getDisplayMetrics());
        if (measuredWidth < applyDimension) {
            measuredWidth = applyDimension;
        }
        int applyDimension2 = (int) TypedValue.applyDimension(1, 350.0f, this.mainActivity.getResources().getDisplayMetrics());
        if (measuredWidth > applyDimension2) {
            measuredWidth = applyDimension2;
        }
        this.dialog.getWindow().setLayout(measuredWidth, -2);
        if (view != null) {
            this.dialog.getWindow().getAttributes().gravity = 53;
        } else {
            this.dialog.getWindow().getAttributes().gravity = 17;
        }
        this.dialog.show();
        CustomDialog.m623c(this.dialog);
        return true;
    }

    public OptionPoupupMenuView(MainActivity mainActivity, IClickListener bVar) {
        this(mainActivity, bVar, false);
    }
}
