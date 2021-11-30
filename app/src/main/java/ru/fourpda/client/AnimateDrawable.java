package ru.fourpda.client;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;

public class AnimateDrawable extends Drawable implements Animatable, Drawable.Callback {
    static final LinearInterpolator f1986l = new LinearInterpolator();
    static final C0601b f1987m = new C0601b();
    static final C0604e f1988n = new C0604e();
    static final AccelerateDecelerateInterpolator f1989o = new AccelerateDecelerateInterpolator();
    private static float f1990p = 2.5f;
    private final int[] f1991a;
    private final ArrayList<Animation> f1992b = new ArrayList<>();
    private final C0603d f1993c;
    private float f1994d;
    private Resources f1995e;
    private View f1996f;
    private Animation f1997g;
    private float f1998h;
    private double f1999i;
    private double f2000j;
    private Animation f2001k;

    public static class animationAnimation$AnimationListenerC0600a extends Animation implements Animation.AnimationListener {
        AnimateDrawable f2002a;

        public animationAnimation$AnimationListenerC0600a(AnimateDrawable n1Var) {
            this.f2002a = n1Var;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            double j = (double) this.f2002a.f1993c.m541j();
            double d = this.f2002a.f1993c.m547d();
            Double.isNaN(j);
            float f2 = (float) ((j / d) / 360.0d);
            float g = this.f2002a.f1993c.m544g();
            float i = this.f2002a.f1993c.m542i();
            float h = this.f2002a.f1993c.m543h();
            this.f2002a.f1993c.m529v(g + ((0.8f - f2) * AnimateDrawable.f1988n.getInterpolation(f)));
            this.f2002a.f1993c.m525z(i + (AnimateDrawable.f1987m.getInterpolation(f) * 0.8f));
            this.f2002a.f1993c.m527x(h + (0.25f * f));
            this.f2002a.m558j((f * 144.0f) + ((this.f2002a.f1998h / 5.0f) * 720.0f));
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            this.f2002a.f1993c.m551B();
            this.f2002a.f1993c.m540k();
            this.f2002a.f1993c.m525z(this.f2002a.f1993c.m546e());
            AnimateDrawable n1Var = this.f2002a;
            n1Var.f1998h = (n1Var.f1998h + 1.0f) % 5.0f;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }

    protected static class C0601b extends AccelerateDecelerateInterpolator {
        protected C0601b() {
        }

        @Override
        public float getInterpolation(float f) {
            return super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
        }
    }

    public static class animationAnimation$AnimationListenerC0602c extends Animation implements Animation.AnimationListener {
        AnimateDrawable f2003a;

        public animationAnimation$AnimationListenerC0602c(AnimateDrawable n1Var) {
            this.f2003a = n1Var;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            float floor = (float) (Math.floor((double) (this.f2003a.f1993c.m543h() / 0.8f)) + 1.0d);
            this.f2003a.f1993c.m525z(this.f2003a.f1993c.m542i() + ((this.f2003a.f1993c.m544g() - this.f2003a.f1993c.m542i()) * f));
            this.f2003a.f1993c.m527x(this.f2003a.f1993c.m543h() + ((floor - this.f2003a.f1993c.m543h()) * f));
            this.f2003a.f1993c.m535p(1.0f - f);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.f2003a.f1993c.m540k();
            this.f2003a.f1993c.m551B();
            this.f2003a.f1993c.m526y(false);
            this.f2003a.f1996f.startAnimation(this.f2003a.f1997g);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }

    public static class C0603d {
        private final Paint f2005b;
        private final Paint f2006c;
        private final Drawable.Callback f2007d;
        private int[] f2013j;
        private int f2014k;
        private float f2015l;
        private float f2016m;
        private float f2017n;
        private boolean f2018o;
        private Path f2019p;
        private float f2020q;
        private double f2021r;
        private int f2022s;
        private int f2023t;
        private int f2024u;
        private int f2026w;
        private final RectF f2004a = new RectF();
        private float f2008e = 0.0f;
        private float f2009f = 0.0f;
        private float f2010g = 0.0f;
        private float f2011h = 5.0f;
        private float f2012i = 2.5f;
        private Paint f2025v = new Paint();

        public C0603d(Drawable.Callback callback) {
            Paint paint = new Paint();
            this.f2005b = paint;
            Paint paint2 = new Paint();
            this.f2006c = paint2;
            this.f2007d = callback;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint2.setFilterBitmap(true);
            paint2.setDither(true);
            this.f2025v.setAntiAlias(true);
            this.f2025v.setFilterBitmap(true);
            this.f2025v.setDither(true);
        }

        private void m549b(Canvas canvas, float f, float f2, Rect rect) {
            if (this.f2018o) {
                Path path = this.f2019p;
                if (path == null) {
                    Path path2 = new Path();
                    this.f2019p = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float f3 = ((float) (((int) this.f2012i) / 2)) * this.f2020q;
                double cos = this.f2021r * Math.cos(0.0d);
                double exactCenterX = (double) rect.exactCenterX();
                Double.isNaN(exactCenterX);
                double sin = this.f2021r * Math.sin(0.0d);
                double exactCenterY = (double) rect.exactCenterY();
                Double.isNaN(exactCenterY);
                float f4 = (float) (sin + exactCenterY);
                this.f2019p.moveTo(0.0f, 0.0f);
                this.f2019p.lineTo(((float) this.f2022s) * this.f2020q, 0.0f);
                Path path3 = this.f2019p;
                float f5 = this.f2020q;
                path3.lineTo((((float) this.f2022s) * f5) / 2.0f, ((float) this.f2023t) * f5);
                this.f2019p.offset(((float) (cos + exactCenterX)) - f3, f4);
                this.f2019p.close();
                this.f2006c.setColor(this.f2013j[this.f2014k]);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.f2019p, this.f2006c);
            }
        }

        private void m539l() {
            this.f2007d.invalidateDrawable(null);
        }

        public void m552A(float f) {
            this.f2011h = f;
            this.f2005b.setStrokeWidth(f);
            m539l();
        }

        public void m551B() {
            this.f2015l = this.f2008e;
            this.f2016m = this.f2009f;
            this.f2017n = this.f2010g;
        }

        public void m550a(Canvas canvas, Rect rect) {
            RectF rectF = this.f2004a;
            rectF.set(rect);
            float f = this.f2012i;
            rectF.inset(f, f);
            float f2 = this.f2008e;
            float f3 = this.f2010g;
            float f4 = (f2 + f3) * 360.0f;
            float f5 = ((this.f2009f + f3) * 360.0f) - f4;
            this.f2005b.setColor(this.f2013j[this.f2014k]);
            canvas.drawArc(rectF, f4, f5, false, this.f2005b);
            m549b(canvas, f4, f5, rect);
            if (this.f2024u < 255) {
                this.f2025v.setColor(this.f2026w);
                this.f2025v.setAlpha(255 - this.f2024u);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.f2025v);
            }
        }

        public int m548c() {
            return this.f2024u;
        }

        public double m547d() {
            return this.f2021r;
        }

        public float m546e() {
            return this.f2009f;
        }

        public float m545f() {
            return this.f2008e;
        }

        public float m544g() {
            return this.f2016m;
        }

        public float m543h() {
            return this.f2017n;
        }

        public float m542i() {
            return this.f2015l;
        }

        public float m541j() {
            return this.f2011h;
        }

        public void m540k() {
            this.f2014k = (this.f2014k + 1) % this.f2013j.length;
        }

        public void m538m() {
            this.f2015l = 0.0f;
            this.f2016m = 0.0f;
            this.f2017n = 0.0f;
            m525z(0.0f);
            m529v(0.0f);
            m527x(0.0f);
        }

        public void m537n(int i) {
            this.f2024u = i;
        }

        public void m536o(float f, float f2) {
            this.f2022s = (int) f;
            this.f2023t = (int) f2;
        }

        public void m535p(float f) {
            if (f != this.f2020q) {
                this.f2020q = f;
                m539l();
            }
        }

        public void m534q(int i) {
            this.f2026w = i;
        }

        public void m533r(double d) {
            this.f2021r = d;
        }

        public void m532s(ColorFilter colorFilter) {
            this.f2005b.setColorFilter(colorFilter);
            m539l();
        }

        public void m531t(int i) {
            this.f2014k = i;
        }

        public void m530u(int[] iArr) {
            this.f2013j = iArr;
            m531t(0);
        }

        public void m529v(float f) {
            this.f2009f = f;
            m539l();
        }

        public void m528w(int i, int i2) {
            double d;
            float min = (float) Math.min(i, i2);
            double d2 = this.f2021r;
            if (d2 <= 0.0d || min < 0.0f) {
                d = Math.ceil((double) (this.f2011h / 2.0f));
            } else {
                double d3 = (double) (min / 2.0f);
                Double.isNaN(d3);
                d = d3 - d2;
            }
            this.f2012i = (float) d;
        }

        public void m527x(float f) {
            this.f2010g = f;
            m539l();
        }

        public void m526y(boolean z) {
            if (this.f2018o != z) {
                this.f2018o = z;
                m539l();
            }
        }

        public void m525z(float f) {
            this.f2008e = f;
            m539l();
        }
    }

    protected static class C0604e extends AccelerateDecelerateInterpolator {
        protected C0604e() {
        }

        @Override
        public float getInterpolation(float f) {
            return super.getInterpolation(Math.min(1.0f, f * 2.0f));
        }
    }

    public AnimateDrawable(Context context, View view) {
        int[] iArr = {-16777216};
        this.f1991a = iArr;
        this.f1996f = view;
        this.f1995e = context.getResources();
        C0603d dVar = new C0603d(this);
        this.f1993c = dVar;
        dVar.m530u(iArr);
        m553o(1);
        m555m();
    }

    private void m557k(double d, double d2, double d3, double d4, float f, float f2) {
        C0603d dVar = this.f1993c;
        float f3 = this.f1995e.getDisplayMetrics().density;
        double d5 = (double) f3;
        Double.isNaN(d5);
        this.f1999i = d * d5;
        Double.isNaN(d5);
        this.f2000j = d2 * d5;
        dVar.m552A(((float) d4) * f3);
        Double.isNaN(d5);
        dVar.m533r(d3 * d5);
        dVar.m531t(0);
        dVar.m536o(f * f3, f2 * f3);
        dVar.m528w((int) this.f1999i, (int) this.f2000j);
    }

    private void m555m() {
        animationAnimation$AnimationListenerC0602c cVar = new animationAnimation$AnimationListenerC0602c(this);
        cVar.setInterpolator(f1989o);
        cVar.setDuration(666);
        cVar.setAnimationListener(cVar);
        animationAnimation$AnimationListenerC0600a aVar = new animationAnimation$AnimationListenerC0600a(this);
        aVar.setRepeatCount(-1);
        aVar.setRepeatMode(1);
        aVar.setInterpolator(f1986l);
        aVar.setDuration(1333);
        aVar.setAnimationListener(aVar);
        this.f2001k = cVar;
        this.f1997g = aVar;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.f1994d, bounds.exactCenterX(), bounds.exactCenterY());
        this.f1993c.m550a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void m562f(float f) {
        this.f1993c.m535p(f);
    }

    public void m561g(int i) {
        this.f1993c.m534q(i);
    }

    @Override
    public int getAlpha() {
        return this.f1993c.m548c();
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) this.f2000j;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) this.f1999i;
    }

    @Override
    public int getOpacity() {
        return -3;
    }

    public void m560h(int[] iArr) {
        if (iArr.length > 0) {
            this.f1993c.m530u(iArr);
        }
        this.f1993c.m531t(0);
    }

    public void m559i(float f) {
        this.f1993c.m527x(f);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        ArrayList<Animation> arrayList = this.f1992b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    void m558j(float f) {
        this.f1994d = f;
        invalidateSelf();
    }

    public void m556l(float f, float f2) {
        this.f1993c.m525z(f);
        this.f1993c.m529v(f2);
    }

    public void m554n(boolean z) {
        this.f1993c.m526y(z);
    }

    public void m553o(int i) {
        if (i == 0) {
            m557k(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            m557k(40.0d, 40.0d, 8.75d, (double) f1990p, 10.0f, 5.0f);
        }
    }

    @Override
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override
    public void setAlpha(int i) {
        this.f1993c.m537n(i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1993c.m532s(colorFilter);
    }

    @Override
    public void start() {
        this.f1997g.reset();
        this.f1993c.m551B();
        if (this.f1993c.m546e() != this.f1993c.m545f()) {
            this.f1996f.startAnimation(this.f2001k);
            return;
        }
        this.f1993c.m531t(0);
        this.f1993c.m538m();
        this.f1996f.startAnimation(this.f1997g);
    }

    @Override
    public void stop() {
        this.f1996f.clearAnimation();
        m558j(0.0f);
        this.f1993c.m526y(false);
        this.f1993c.m531t(0);
        this.f1993c.m538m();
    }

    @Override
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
