package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Widgets$StartButtonsLayout extends RelativeLayout {
    public TextView f920a;
    public TextView f921b;
    public TextView f922c;
    public TextView f923d;
    public TextView f924e;
    public TextView f925f;
    public TextView f926g;
    public TextView f927h;
    public TextView f928i;
    public TextView f929j;
    int f931l;
    int f932m;
    int f933n;
    int f934o;
    int f935p;
    int f936q;
    int f937r;
    int f938s;
    int f930k = 0;
    private int f939t = -1;

    public Widgets$StartButtonsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f920a = (TextView) findViewById(R.id.blockDetention);
        this.f921b = (TextView) findViewById(R.id.siteBtn);
        this.f922c = (TextView) findViewById(R.id.forumBtn);
        this.f923d = (TextView) findViewById(R.id.historyBtn);
        this.f924e = (TextView) findViewById(R.id.qmsBtn);
        this.f925f = (TextView) findViewById(R.id.qmsUnread);
        this.f926g = (TextView) findViewById(R.id.favBtn);
        this.f927h = (TextView) findViewById(R.id.favUnread);
        this.f928i = (TextView) findViewById(R.id.menBtn);
        this.f929j = (TextView) findViewById(R.id.menUnread);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = this.f939t;
        this.f939t = i;
        if (i != i4) {
            requestLayout();
        }
        if (this.f931l == 0) {
            measureChildren(i, i2);
            this.f931l = this.f925f.getMeasuredWidth();
            this.f932m = ((RelativeLayout.LayoutParams) this.f925f.getLayoutParams()).rightMargin;
            this.f933n = this.f921b.getMeasuredWidth();
            this.f934o = this.f922c.getMeasuredWidth();
            this.f935p = this.f923d.getMeasuredWidth();
            this.f936q = this.f924e.getMeasuredWidth();
            this.f937r = this.f926g.getMeasuredWidth();
            this.f938s = this.f928i.getMeasuredWidth();
        }
        this.f930k = View.MeasureSpec.getSize(i) - (findViewById(R.id.leftEdge).getMeasuredWidth() * 2);
        int max = Math.max(Math.max(this.f936q, this.f937r), this.f938s) + this.f931l;
        int i5 = this.f932m;
        int i6 = max + (i5 / 2) + (i5 * 2);
        int max2 = Math.max(Math.max(this.f933n, this.f934o), this.f935p) + (this.f932m * 2);
        int i7 = i6 * 6;
        int i8 = this.f930k;
        if (i7 <= i8) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f922c.getLayoutParams();
            layoutParams.addRule(3, 0);
            layoutParams.addRule(6, R.id.siteBtn);
            layoutParams.addRule(1, R.id.siteBtn);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f923d.getLayoutParams();
            layoutParams2.addRule(3, 0);
            layoutParams2.addRule(6, R.id.forumBtn);
            layoutParams2.addRule(1, R.id.forumBtn);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.f924e.getLayoutParams();
            layoutParams3.addRule(3, 0);
            layoutParams3.addRule(6, R.id.historyBtn);
            layoutParams3.addRule(1, R.id.historyBtn);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.f926g.getLayoutParams();
            layoutParams4.addRule(6, R.id.qmsBtn);
            layoutParams4.addRule(1, R.id.qmsBtn);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.f928i.getLayoutParams();
            layoutParams5.addRule(6, R.id.favBtn);
            layoutParams5.addRule(1, R.id.favBtn);
            i3 = Math.max(i6, this.f930k / 6);
            max2 = (this.f930k - (i3 * 3)) / 3;
        } else if (i6 * 3 <= i8) {
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.f922c.getLayoutParams();
            layoutParams6.addRule(3, 0);
            layoutParams6.addRule(6, R.id.siteBtn);
            layoutParams6.addRule(1, R.id.siteBtn);
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.f923d.getLayoutParams();
            layoutParams7.addRule(3, 0);
            layoutParams7.addRule(6, R.id.forumBtn);
            layoutParams7.addRule(1, R.id.forumBtn);
            RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) this.f924e.getLayoutParams();
            layoutParams8.addRule(6, 0);
            layoutParams8.addRule(3, R.id.siteBtn);
            layoutParams8.addRule(1, R.id.leftEdge);
            RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) this.f926g.getLayoutParams();
            layoutParams9.addRule(6, R.id.qmsBtn);
            layoutParams9.addRule(1, R.id.qmsBtn);
            RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) this.f928i.getLayoutParams();
            layoutParams10.addRule(6, R.id.favBtn);
            layoutParams10.addRule(1, R.id.favBtn);
            max2 = Math.max(i6, this.f930k / 3);
            i3 = max2;
        } else {
            RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) this.f922c.getLayoutParams();
            layoutParams11.addRule(6, 0);
            layoutParams11.addRule(3, R.id.siteBtn);
            layoutParams11.addRule(1, R.id.leftEdge);
            RelativeLayout.LayoutParams layoutParams12 = (RelativeLayout.LayoutParams) this.f923d.getLayoutParams();
            layoutParams12.addRule(6, 0);
            layoutParams12.addRule(3, R.id.forumBtn);
            layoutParams12.addRule(1, R.id.leftEdge);
            RelativeLayout.LayoutParams layoutParams13 = (RelativeLayout.LayoutParams) this.f924e.getLayoutParams();
            layoutParams13.addRule(3, 0);
            layoutParams13.addRule(6, R.id.siteBtn);
            layoutParams13.addRule(1, R.id.siteBtn);
            RelativeLayout.LayoutParams layoutParams14 = (RelativeLayout.LayoutParams) this.f926g.getLayoutParams();
            layoutParams14.addRule(6, R.id.forumBtn);
            layoutParams14.addRule(1, R.id.forumBtn);
            RelativeLayout.LayoutParams layoutParams15 = (RelativeLayout.LayoutParams) this.f928i.getLayoutParams();
            layoutParams15.addRule(6, R.id.historyBtn);
            layoutParams15.addRule(1, R.id.historyBtn);
            i3 = Math.max(i6, this.f930k / 2);
            int i9 = this.f930k;
            if (max2 > i9 - i3) {
                i3 = i9 - max2;
            } else {
                max2 = i9 - i3;
            }
        }
        int i10 = this.f933n;
        int i11 = (max2 - i10) / 2;
        this.f921b.setPadding(i11, 0, (max2 - i10) - i11, 0);
        int i12 = this.f934o;
        int i13 = (max2 - i12) / 2;
        this.f922c.setPadding(i13, 0, (max2 - i12) - i13, 0);
        int i14 = this.f935p;
        int i15 = (max2 - i14) / 2;
        this.f923d.setPadding(i15, 0, (max2 - i14) - i15, 0);
        this.f924e.getLayoutParams().width = i3;
        if (this.f936q + (this.f925f.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0) <= i3) {
            int i16 = ((i3 - this.f936q) - (this.f925f.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0)) / 2;
            this.f924e.setPadding(i16, 0, (i3 - this.f936q) - i16, 0);
        } else {
            this.f924e.setPadding(this.f932m, 0, (this.f925f.getVisibility() == 0 ? this.f931l : 0) + this.f932m, 0);
        }
        this.f926g.getLayoutParams().width = i3;
        if (this.f937r + (this.f927h.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0) <= i3) {
            int i17 = ((i3 - this.f937r) - (this.f927h.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0)) / 2;
            this.f926g.setPadding(i17, 0, (i3 - this.f937r) - i17, 0);
        } else {
            this.f926g.setPadding(this.f932m, 0, (this.f927h.getVisibility() == 0 ? this.f931l : 0) + this.f932m, 0);
        }
        this.f928i.getLayoutParams().width = i3;
        if (this.f938s + (this.f929j.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0) <= i3) {
            int i18 = ((i3 - this.f938s) - (this.f929j.getVisibility() == 0 ? this.f931l + (this.f932m / 2) : 0)) / 2;
            this.f928i.setPadding(i18, 0, (i3 - this.f938s) - i18, 0);
        } else {
            this.f928i.setPadding(this.f932m, 0, (this.f929j.getVisibility() == 0 ? this.f931l : 0) + this.f932m, 0);
        }
        super.onMeasure(i, i2);
    }
}
