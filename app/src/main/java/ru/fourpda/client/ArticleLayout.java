package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArticleLayout extends RelativeLayout {
    private static int f490A;
    public static int f491p;
    public static int f492q;
    public static int f493r;
    public static float f494s;
    public static int f495t;
    public static int f496u;
    public static int f497v;
    public static int f498w;
    public static int f499x;
    public static float f500y;
    private static Drawable f501z;
    public ImageView f502a;
    public TextView f503b;
    public TextView f504c;
    public TextView f505d;
    public TextView f506e;
    public BBDisplay f507f;
    public HorizontalScrollView f508g;
    public LinearLayout f509h;
    public View f510i;
    public boolean f511j;
    public BBOverlay f512k;
    int f514m;
    public boolean f515n;
    boolean f513l = false;
    private int f516o = -1;

    public ArticleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static int m980a(Context context, String str, BBString pVar, boolean z, int i) {
        int b = m979b(i);
        boolean c = m978c(i);
        int i2 = (b * 3) / 4;
        int k = Util.m663k(context, pVar, c ? i - b : i);
        int i3 = 0;
        if (z) {
            if (!c) {
                int i4 = f495t;
                i3 = (i4 * 2) + Util.m672b(str, i - (i4 * 2), f494s, true) + f498w + f497v;
            }
        } else if (!c) {
            int i5 = f495t;
            i3 = i5 + Util.m672b(str, i - (i5 * 2), f494s, true) + f496u;
        } else {
            k = 0;
        }
        return i2 + i3 + k + f493r;
    }

    public static int m979b(int i) {
        if (!m978c(i)) {
            return i;
        }
        int i2 = f492q;
        return ((float) i2) * 2.55f <= ((float) i) ? i2 : f491p;
    }

    public static boolean m978c(int i) {
        return ((float) f491p) * 2.55f <= ((float) i);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f502a = (ImageView) findViewById(R.id.articlePicture);
        this.f503b = (TextView) findViewById(R.id.articleDate);
        this.f504c = (TextView) findViewById(R.id.articleAuthorName);
        this.f505d = (TextView) findViewById(R.id.articleCommentsCount);
        this.f506e = (TextView) findViewById(R.id.articleTitle);
        this.f507f = (BBDisplay) findViewById(R.id.articleCode);
        this.f508g = (HorizontalScrollView) findViewById(R.id.articleTagsScroll);
        this.f509h = (LinearLayout) findViewById(R.id.articleTags);
        this.f510i = findViewById(R.id.articleSepBottom);
        findViewById(R.id.articleBackground);
        BBOverlay bBOverlay = (BBOverlay) findViewById(R.id.articleOverlay);
        this.f512k = bBOverlay;
        this.f507f.setOverlay(bBOverlay);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onMeasure(int i, int i2) {
        int i3 = this.f516o;
        this.f516o = i;
        if (i != i3) {
            requestLayout();
        }
        int size = View.MeasureSpec.getSize(i);
        int b = m979b(size);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f502a.getLayoutParams();
        layoutParams.width = b;
        layoutParams.height = (b * 3) / 4;
        if (m978c(size)) {
            layoutParams.addRule(11, 0);
            if (!this.f515n) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f510i.getLayoutParams();
                boolean z = this.f511j;
                layoutParams2.height = z ? 0 : f493r;
                this.f510i.setVisibility(z ? 4 : 0);
            }
            if (!this.f513l) {
                if (this.f515n) {
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.f506e.getLayoutParams();
                    layoutParams3.addRule(3, 0);
                    layoutParams3.addRule(9, 0);
                    layoutParams3.addRule(1, R.id.articlePicture);
                    layoutParams3.addRule(10);
                    ((RelativeLayout.LayoutParams) this.f507f.getLayoutParams()).addRule(3, R.id.articlePicture);
                    RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.f508g.getLayoutParams();
                    layoutParams4.addRule(3, 0);
                    layoutParams4.addRule(2, R.id.articleCode);
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.f503b.getLayoutParams();
                    layoutParams5.addRule(3, 0);
                    layoutParams5.addRule(2, R.id.articleTagsScroll);
                } else {
                    RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.f506e.getLayoutParams();
                    layoutParams6.addRule(3, 0);
                    layoutParams6.addRule(9, 0);
                    layoutParams6.addRule(1, R.id.articlePicture);
                    layoutParams6.addRule(10);
                    this.f514m = this.f506e.getPaddingTop();
                    TextView textView = this.f506e;
                    textView.setPadding(textView.getPaddingLeft(), 0, this.f506e.getPaddingRight(), this.f506e.getPaddingBottom());
                    ((RelativeLayout.LayoutParams) this.f507f.getLayoutParams()).addRule(8, R.id.articlePicture);
                    RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.f508g.getLayoutParams();
                    layoutParams7.addRule(3, 0);
                    layoutParams7.addRule(8, R.id.articlePicture);
                    layoutParams7.addRule(5, 0);
                    ((RelativeLayout.LayoutParams) this.f510i.getLayoutParams()).addRule(3, R.id.articlePicture);
                }
                this.f513l = true;
            }
        } else {
            layoutParams.addRule(11);
            if (!this.f515n) {
                RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) this.f510i.getLayoutParams();
                boolean z2 = this.f511j;
                layoutParams8.height = z2 ? 0 : f493r;
                this.f510i.setVisibility(z2 ? 4 : 0);
            }
            if (this.f513l) {
                if (this.f515n) {
                    RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) this.f506e.getLayoutParams();
                    layoutParams9.addRule(1, 0);
                    layoutParams9.addRule(10, 0);
                    layoutParams9.addRule(3, R.id.articlePicture);
                    layoutParams9.addRule(9);
                    ((RelativeLayout.LayoutParams) this.f507f.getLayoutParams()).addRule(3, R.id.articleTagsScroll);
                    RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) this.f508g.getLayoutParams();
                    layoutParams10.addRule(2, 0);
                    layoutParams10.addRule(3, R.id.articleDate);
                    RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) this.f503b.getLayoutParams();
                    layoutParams11.addRule(2, 0);
                    layoutParams11.addRule(3, R.id.articleTitle);
                } else {
                    RelativeLayout.LayoutParams layoutParams12 = (RelativeLayout.LayoutParams) this.f506e.getLayoutParams();
                    layoutParams12.addRule(1, 0);
                    layoutParams12.addRule(10, 0);
                    layoutParams12.addRule(3, R.id.articlePicture);
                    layoutParams12.addRule(9);
                    TextView textView2 = this.f506e;
                    textView2.setPadding(textView2.getPaddingLeft(), this.f514m, this.f506e.getPaddingRight(), this.f506e.getPaddingBottom());
                    ((RelativeLayout.LayoutParams) this.f507f.getLayoutParams()).addRule(8, 0);
                    RelativeLayout.LayoutParams layoutParams13 = (RelativeLayout.LayoutParams) this.f508g.getLayoutParams();
                    layoutParams13.addRule(8, 0);
                    layoutParams13.addRule(5, R.id.articleTitle);
                    layoutParams13.addRule(1, 0);
                    layoutParams13.addRule(3, R.id.articleCode);
                    ((RelativeLayout.LayoutParams) this.f510i.getLayoutParams()).addRule(3, R.id.articleTagsScroll);
                }
                this.f513l = false;
            }
        }
        if (!this.f515n) {
            ((RelativeLayout.LayoutParams) this.f507f.getLayoutParams()).height = this.f513l ? -1 : (size * 160) / 400;
            if (f501z == null || f490A != Skin.SkinColorModel.mainBgColor) {
                f501z = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Skin.SkinColorModel.f1364T, Skin.SkinColorModel.mainBgColor});
                f490A = Skin.SkinColorModel.mainBgColor;
            }
            View findViewById = findViewById(R.id.articleGradient);
            findViewById.setBackgroundDrawable(f501z);
            ((RelativeLayout.LayoutParams) findViewById.getLayoutParams()).height = (int) (BBString.f2188u0 * 2.0f);
        }
        super.onMeasure(i, i2);
    }
}
