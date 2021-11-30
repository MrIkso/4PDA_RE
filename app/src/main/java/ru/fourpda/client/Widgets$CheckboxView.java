package ru.fourpda.client;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class Widgets$CheckboxView extends View {
    static int[] f885b = {16842912};
    boolean f886a;

    public Widgets$CheckboxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m851a();
    }

    void m851a() {
        setClickable(true);
    }

    public boolean m850b() {
        return Build.VERSION.SDK_INT >= 15 ? callOnClick() : performClick();
    }

    @Override
    public boolean callOnClick() {
        setChecked(!this.f886a);
        return super.callOnClick();
    }

    public boolean getChecked() {
        return this.f886a;
    }

    @Override
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        return this.f886a ? View.mergeDrawableStates(onCreateDrawableState, f885b) : onCreateDrawableState;
    }

    @Override
    public boolean performClick() {
        setChecked(!this.f886a);
        return super.performClick();
    }

    public void setChecked(boolean z) {
        this.f886a = z;
        refreshDrawableState();
    }
}
