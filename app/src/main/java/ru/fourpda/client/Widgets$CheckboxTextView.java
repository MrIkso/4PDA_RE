package ru.fourpda.client;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class Widgets$CheckboxTextView extends TextView {
    static int[] f880e = {16842912};
    boolean f881a;
    private int f882b = 0;
    String f883c = null;
    boolean f884d = false;

    public Widgets$CheckboxTextView(Context context) {
        super(context);
        m852a(context, null);
    }

    void m852a(Context context, AttributeSet attributeSet) {
        super.setClickable(true);
        if (!(context == null || attributeSet == null)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0329c1.f1268a);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 0) {
                    this.f882b = obtainStyledAttributes.getInt(index, 1);
                } else if (index == 1) {
                    setSubText(obtainStyledAttributes.getString(index));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public int getBoxAlign() {
        return this.f882b;
    }

    public boolean getChecked() {
        return this.f881a;
    }

    public String getSubText() {
        return this.f883c;
    }

    @Override
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        return this.f881a ? TextView.mergeDrawableStates(onCreateDrawableState, f880e) : onCreateDrawableState;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        if (!this.f884d) {
            int i3 = this.f882b;
            if (i3 == 1) {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() + ((int) (getContext().getResources().getDisplayMetrics().density * 40.0f)), getPaddingBottom());
            } else if (i3 == 0) {
                setPadding(getPaddingLeft() + ((int) (getContext().getResources().getDisplayMetrics().density * 24.0f)), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
        }
        this.f884d = true;
        super.onMeasure(i, i2);
    }

    @Override
    public boolean performClick() {
        setChecked(!this.f881a);
        return super.performClick();
    }

    public void setBoxAlign(int i) {
        this.f882b = i;
        requestLayout();
    }

    public void setChecked(boolean z) {
        this.f881a = z;
        refreshDrawableState();
    }

    public void setSubText(String str) {
        this.f883c = str;
        if (str.length() > 0) {
            int length = getText().length();
            SpannableString spannableString = new SpannableString(((Object) getText()) + "\n" + this.f883c);
            spannableString.setSpan(new ForegroundColorSpan(Skin.C0353a.f1370Z), length, spannableString.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan((int) (getContext().getResources().getDisplayMetrics().scaledDensity * 14.0f)), length, spannableString.length(), 33);
            setText(spannableString);
            return;
        }
        setText(new SpannableString(getText()));
    }

    public Widgets$CheckboxTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m852a(context, attributeSet);
    }
}
