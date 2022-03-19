package ru.fourpda.client;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class AnimView extends View {
    float f2545a;
    Paint f2546b;
    int f2547c;
    C0748b f2548d;
    int f2549e;

    public class animationAnimation$AnimationListenerC0747a implements Animation.AnimationListener {
        animationAnimation$AnimationListenerC0747a() {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            AnimView r1Var = AnimView.this;
            r1Var.f2547c = (r1Var.f2547c + 1) % 16;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }

    public class C0748b extends Animation {
        private C0748b() {
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            AnimView.this.invalidate();
        }
    }

    public AnimView(Context context) {
        super(context);
        m341b();
    }

    private void m341b() {
        this.f2545a = getContext().getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        this.f2546b = paint;
        paint.setColor(Skin.SkinColorModel.f1372a0);
        this.f2546b.setStrokeWidth(this.f2545a);
        C0748b bVar = new C0748b();
        this.f2548d = bVar;
        bVar.setDuration(30);
        this.f2548d.setRepeatCount(-1);
        this.f2548d.setInterpolator(new LinearInterpolator());
        this.f2548d.setAnimationListener(new animationAnimation$AnimationListenerC0747a());
    }

    public void m342a(boolean z) {
        if (z) {
            startAnimation(this.f2548d);
        } else {
            clearAnimation();
        }
    }

    public void m340c(int i) {
        this.f2549e = i;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f2549e == 1) {
            float f = (float) (this.f2547c / 4);
            for (int i = 0; i < 6; i++) {
                float f2 = ((float) (i * 4)) + f;
                float f3 = f2 - 4.0f;
                float f4 = this.f2545a;
                float f5 = (f2 + 3.0f) - 4.0f;
                canvas.drawLine(f3 * f4, f4 * 0.0f, f5 * f4, f4 * 3.0f, this.f2546b);
                float f6 = this.f2545a;
                canvas.drawLine(f3 * f6, f6 * 6.0f, f5 * f6, f6 * 3.0f, this.f2546b);
            }
            if (f <= 3.0f) {
                float f7 = 20.0f + f;
                float f8 = this.f2545a;
                canvas.drawLine(f7 * f8, f8 * 0.0f, f8 * 23.0f, (3.0f - f) * f8, this.f2546b);
                float f9 = this.f2545a;
                canvas.drawLine(f7 * f9, f9 * 6.0f, f9 * 23.0f, (f + 3.0f) * f9, this.f2546b);
                return;
            }
            return;
        }
        int i2 = this.f2547c;
        int i3 = i2 / 4;
        int i4 = i2 % 4;
        for (int i5 = 0; i5 < 3; i5++) {
            if (i5 != i3) {
                i4 = 0;
            }
            float f10 = this.f2545a;
            canvas.drawCircle(((float) ((i5 * 8) + 5)) * f10, f10 * 3.0f, (1 == i4 ? 3.0f : i4 == 0 ? 2.0f : 2.5f) * f10, this.f2546b);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        float f = this.f2545a;
        setMeasuredDimension((int) (33.0f * f), (int) (f * 11.0f));
    }
}
