package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class Widgets$CircleImageView extends ImageView {
    int f887a = 503316480;
    Animation.AnimationListener f888b;
    int f889c;
    C0237c f890d;
    C0236b f891e;
    int f892f;
    float f893g;

    public class C0235a extends OvalShape {
        RadialGradient f894a;
        int f896c;
        int f897d;
        int f898e = 1023410176;
        Paint f895b = new Paint(7);

        public C0235a(Widgets$CircleImageView widgets$CircleImageView, int i, int i2) {
            this.f897d = i;
            this.f896c = i2;
            int i3 = this.f896c;
            RadialGradient radialGradient = new RadialGradient((float) (i3 / 2), (float) (i3 / 2), (float) this.f897d, new int[]{this.f898e, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.f894a = radialGradient;
            this.f895b.setShader(radialGradient);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            float width = (float) (canvas.getWidth() / 2);
            float height = (float) (canvas.getHeight() / 2);
            canvas.drawCircle(width, height, (float) ((this.f896c / 2) + this.f897d), this.f895b);
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawCircle(width, height, (float) (this.f896c / 2), paint);
        }
    }

    public class C0236b extends Animation {
        Widgets$CircleImageView f899a;

        public C0236b(Widgets$CircleImageView widgets$CircleImageView, Widgets$CircleImageView widgets$CircleImageView2) {
            this.f899a = widgets$CircleImageView2;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            Widgets$CircleImageView widgets$CircleImageView = this.f899a;
            float f2 = 1.0f - f;
            float f3 = widgets$CircleImageView.f893g;
            if (f2 > f3) {
                f2 = f3;
            }
            widgets$CircleImageView.m847c(f2);
        }
    }

    public class C0237c extends Animation {
        Widgets$CircleImageView f900a;

        public C0237c(Widgets$CircleImageView widgets$CircleImageView, Widgets$CircleImageView widgets$CircleImageView2) {
            this.f900a = widgets$CircleImageView2;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            Widgets$CircleImageView widgets$CircleImageView = this.f900a;
            widgets$CircleImageView.f893g = f;
            widgets$CircleImageView.m847c(f);
        }
    }

    public Widgets$CircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i = 20;
        if (!(context == null || attributeSet == null)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0329c1.f1269b);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == 0) {
                    i = obtainStyledAttributes.getInt(index, i);
                }
            }
            obtainStyledAttributes.recycle();
        }
        m848b(context, Skin.C0353a.f1374b0, (float) i);
    }

    public void m849a(boolean z) {
        this.f892f = 2;
        if (this.f891e == null) {
            this.f891e = new C0236b(this, this);
        }
        this.f891e.setDuration(200);
        this.f891e.setStartOffset(z ? 800 : 0);
        clearAnimation();
        startAnimation(this.f891e);
    }

    @SuppressLint("WrongConstant")
    void m848b(Context context, int i, float f) {
        float f2 = context.getResources().getDisplayMetrics().density;
        int i2 = (int) (1.75f * f2);
        int i3 = (int) (0.0f * f2);
        this.f889c = (int) (f2 * 3.5f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new C0235a(this, this.f889c, (int) (f * f2 * 2.0f)));
        shapeDrawable.setFilterBitmap(true);
        shapeDrawable.setDither(true);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setFilterBitmap(true);
        shapeDrawable.getPaint().setDither(true);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, shapeDrawable.getPaint());
        }
        shapeDrawable.setFilterBitmap(true);
        shapeDrawable.setDither(true);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setFilterBitmap(true);
        shapeDrawable.getPaint().setDither(true);
        shapeDrawable.getPaint().setShadowLayer((float) this.f889c, (float) i3, (float) i2, this.f887a);
        int i4 = this.f889c;
        setPadding(i4, i4, i4, i4);
        shapeDrawable.getPaint().setColor(i);
        setBackgroundDrawable(shapeDrawable);
    }

    void m847c(float f) {
        if (Build.VERSION.SDK_INT < 11) {
            getBackground().setAlpha((int) (f * 255.0f));
            return;
        }
        setScaleX(f);
        setScaleY(f);
    }

    public void m846d() {
        this.f892f = 1;
        if (this.f890d == null) {
            this.f890d = new C0237c(this, this);
        }
        this.f890d.setDuration(200);
        clearAnimation();
        startAnimation(this.f890d);
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.f892f == 2) {
            this.f892f = 0;
            if (Build.VERSION.SDK_INT >= 11) {
                setScaleX(1.0f);
                setScaleY(1.0f);
            } else {
                getBackground().setAlpha(255);
            }
            layout(0, 0, 0, 0);
        }
        Animation.AnimationListener animationListener = this.f888b;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f888b;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth() + (this.f889c * 2), getMeasuredHeight() + (this.f889c * 2));
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.f888b = animationListener;
    }

    @Override
    public void setBackgroundColor(int i) {
        ShapeDrawable shapeDrawable = (ShapeDrawable) getBackground();
        if (shapeDrawable != null) {
            shapeDrawable.getPaint().setAntiAlias(true);
            shapeDrawable.getPaint().setFilterBitmap(true);
            shapeDrawable.getPaint().setDither(true);
            shapeDrawable.getPaint().setColor(i);
        }
    }

    public Widgets$CircleImageView(Context context, int i, float f) {
        super(context);
        m848b(context, i, f);
    }
}
