package ru.fourpda.client;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Widgets$WrappingLinearLayout extends LinearLayout {
    private int f940a = 0;
    private int f941b = 0;
    private View f942c = null;

    class View$OnClickListenerC0245a implements View.OnClickListener {
        View$OnClickListenerC0245a() {
        }

        @Override
        public void onClick(View view) {
            Widgets$WrappingLinearLayout widgets$WrappingLinearLayout = Widgets$WrappingLinearLayout.this;
            widgets$WrappingLinearLayout.removeView(widgets$WrappingLinearLayout.f942c);
            Widgets$WrappingLinearLayout.this.m832b(0, null);
        }
    }

    public Widgets$WrappingLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void m832b(int i, View view) {
        this.f940a = i;
        this.f942c = view;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view;
        int paddingLeft = getPaddingLeft();
        int measuredWidth = (getMeasuredWidth() - paddingLeft) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            int i8 = this.f941b;
            if (i8 <= 0 || i8 == i7 || childAt != this.f942c) {
                if (i8 > 0 && i8 == i7) {
                    childAt.layout(0, 0, 0, 0);
                    childAt = this.f942c;
                }
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                int measuredWidth2 = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                int measuredHeight = childAt.getMeasuredHeight();
                int i9 = layoutParams.topMargin;
                int i10 = layoutParams.bottomMargin;
                int i11 = measuredHeight + i9 + i10;
                int i12 = this.f941b;
                if (i12 <= 0 || i7 <= i12) {
                    if (i5 <= 0 || i5 + measuredWidth2 <= measuredWidth) {
                        int i13 = paddingLeft + i5;
                        int i14 = paddingTop - i6;
                        int i15 = i14 + i11;
                        childAt.layout(layoutParams.leftMargin + i13, i14 + i9, (i13 + measuredWidth2) - layoutParams.rightMargin, i15 - i10);
                        if (i11 > i6) {
                            i6 = i11;
                            paddingTop = i15;
                        }
                        i5 += measuredWidth2;
                    } else {
                        paddingTop += i11;
                        childAt.layout(layoutParams.leftMargin + paddingLeft, i9 + paddingTop, (paddingLeft + measuredWidth2) - layoutParams.rightMargin, paddingTop - i10);
                        i5 = measuredWidth2;
                        i6 = i11;
                    }
                    if (this.f941b == 0 && (view = this.f942c) != null) {
                        view.layout(0, 0, 0, 0);
                    }
                } else {
                    childAt.layout(0, 0, 0, 0);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        int resolveSize = (LinearLayout.resolveSize(getSuggestedMinimumWidth(), i) - getPaddingLeft()) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int i3 = 0;
        this.f941b = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (true) {
            if (i3 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt != this.f942c) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                int measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                if (i4 <= 0 || i4 + measuredWidth <= resolveSize) {
                    if (measuredHeight > i5) {
                        paddingTop = (paddingTop - i5) + measuredHeight;
                        i5 = measuredHeight;
                    }
                    i4 += measuredWidth;
                } else {
                    int i7 = this.f940a;
                    if (i7 <= 0 || (i6 = i6 + 1) <= i7) {
                        paddingTop += measuredHeight;
                        i5 = measuredHeight;
                        i4 = measuredWidth;
                    } else {
                        this.f941b = i3 - 1;
                        View view = this.f942c;
                        if (view != null) {
                            ((TextView) view).setText("Еще " + (getChildCount() - i3) + "...");
                            this.f942c.setOnClickListener(new View$OnClickListenerC0245a());
                        }
                    }
                }
            }
            i3++;
        }
        setMeasuredDimension(LinearLayout.resolveSize(getSuggestedMinimumWidth(), i), paddingTop + getPaddingBottom());
    }
}
