package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import in.cpp.picoimg.PicoDrawable;
import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class ImageDialog extends Dialog {
    MainActivity f644a;
    ImageDialogLayout f645b;
    List<C0174m> f646c;
    int f647d;
    private Runnable f648e;
    private Runnable f649f;
    private Runnable f650g;
    private Runnable f651h;
    String f652i;
    String f653j;
    int f654k;
    int f655l;
    Runnable f656m;

    public static class ImageDialogLayout extends RelativeLayout implements View.OnLongClickListener {
        int f658B;
        int f659C;
        float f661E;
        float f662F;
        float f663G;
        float f664H;
        float f665I;
        float f666J;
        float f667K;
        private float f670N;
        private float f671O;
        long f672P;
        boolean f673Q;
        RunnableC0159b f674R;
        float f675a;
        float f676b;
        float f677c;
        float f678d;
        float f679e;
        float f680f;
        float f681g;
        float f682h;
        float f683i;
        float f684j;
        public Runnable f685k;
        public Runnable f686l;
        public Runnable f687m;
        public ImageView f688n;
        public TextView f689o;
        public ProgressBar f690p;
        public View f691q;
        int f692r;
        int f693s;
        Widgets$CircleImageView f694t;
        Widgets$CircleImageView f696v;
        String f698x;
        int f699y;
        int f700z;
        public boolean f695u = true;
        public boolean f697w = true;
        private boolean f657A = false;
        int f660D = 0;
        int f668L = 0;
        boolean f669M = false;

        class C0158a implements OptionPoupupMenuView.IClickListener {
            final MainActivity f701a;

            C0158a(MainActivity mainActivity) {
                this.f701a = mainActivity;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (i3 == 1) {
                    new API.LoadForumAttachRequest(0, this.f701a, null).prepareResult(0, new Document(ImageDialogLayout.this.f698x));
                }
            }
        }

        public class RunnableC0159b implements Runnable {
            ImageDialogLayout f703a;

            public RunnableC0159b(ImageDialogLayout imageDialogLayout, ImageDialogLayout imageDialogLayout2) {
                this.f703a = imageDialogLayout2;
            }

            @Override
            public void run() {
                TextView textView = this.f703a.f689o;
                if (textView != null) {
                    textView.setVisibility(textView.getVisibility() == 0 ? 8 : 0);
                }
            }
        }

        public ImageDialogLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            m911a(context);
        }

        private void m910b() {
            float f = this.f677c;
            int i = this.f699y;
            float f2 = (((float) i) * f) / 2.0f;
            float f3 = (((float) this.f700z) * f) / 2.0f;
            if (f * ((float) i) <= ((float) getWidth())) {
                this.f675a = (float) (getWidth() / 2);
            } else if (this.f675a + f2 < ((float) getWidth())) {
                this.f675a = ((float) getWidth()) - f2;
            } else if (this.f675a - f2 > 0.0f) {
                this.f675a = f2;
            }
            if (this.f677c * ((float) this.f700z) <= ((float) getHeight())) {
                this.f676b = (float) (getHeight() / 2);
            } else if (this.f676b + f3 < ((float) getHeight())) {
                this.f676b = ((float) getHeight()) - f3;
            } else if (this.f676b - f3 > 0.0f) {
                this.f676b = f3;
            }
            Matrix matrix = new Matrix();
            matrix.reset();
            float f4 = this.f677c;
            matrix.postScale(f4, f4);
            matrix.postTranslate(this.f675a - f2, this.f676b - f3);
            this.f688n.setImageMatrix(matrix);
        }

        void m911a(Context context) {
            setWillNotDraw(false);
            this.f668L = ViewConfiguration.get(context).getScaledTouchSlop();
            float f = context.getResources().getDisplayMetrics().density;
            this.f679e = f;
            this.f674R = new RunnableC0159b(this, this);
            this.f693s = (int) (f * 44.0f);
            setOnLongClickListener(this);
        }

        public void m909c(String str, int i, int i2) {
            this.f698x = str;
            this.f699y = i;
            this.f700z = i2;
            this.f680f = ((float) getWidth()) / ((float) this.f699y);
            float height = ((float) getHeight()) / ((float) this.f700z);
            this.f677c = height;
            if (this.f680f > height) {
                this.f680f = height;
            }
            float f = this.f680f;
            float f2 = this.f679e;
            if (f > f2) {
                this.f680f = f2;
            }
            this.f681g = Math.min(this.f680f, f2 * 0.1f);
            float f3 = this.f679e;
            this.f682h = 10.0f * f3;
            this.f677c = Math.min(this.f680f, f3);
            this.f675a = ((float) getWidth()) / 2.0f;
            this.f676b = ((float) getHeight()) / 2.0f;
            Matrix matrix = new Matrix();
            matrix.reset();
            float f4 = this.f677c;
            matrix.postScale(f4, f4);
            float f5 = this.f675a;
            float f6 = this.f677c;
            matrix.postTranslate(f5 - ((((float) this.f699y) * f6) / 2.0f), this.f676b - ((((float) this.f700z) * f6) / 2.0f));
            this.f688n.setImageMatrix(matrix);
        }

        @Override
        @SuppressLint({"NewApi"})
        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (Exception e) {
                if (this.f657A || Build.VERSION.SDK_INT < 11) {
                    Context context = getContext();
                    Toast.makeText(context, e.getClass().getCanonicalName() + ": " + e.getMessage(), 1).show();
                    return;
                }
                this.f657A = true;
                this.f688n.setLayerType(1, null);
                this.f688n.postInvalidate();
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            boolean z = false;
            boolean z2 = false;
            boolean z3;
            boolean z4;
            int i;
            int i2 = Build.VERSION.SDK_INT;
            int action = motionEvent.getAction() & -65281;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.f670N += Math.abs(x - this.f662F);
            this.f671O += Math.abs(y - this.f664H);
            boolean z5 = false;
            if (action == 0) {
                this.f669M = false;
                this.f660D = 1;
                this.f661E = x;
                this.f662F = x;
                this.f663G = y;
                this.f664H = y;
                this.f671O = 0.0f;
                this.f670N = 0.0f;
                this.f678d = this.f677c;
            } else {
                if (5 == action) {
                    if (2 == motionEvent.getPointerCount() && ((i = this.f660D) == 0 || 1 == i)) {
                        this.f665I = motionEvent.getX(1);
                        this.f666J = motionEvent.getY(1);
                        float x2 = motionEvent.getX(0) - this.f665I;
                        float y2 = motionEvent.getY(0) - this.f666J;
                        this.f660D = 2;
                        this.f667K = (float) Math.sqrt((double) ((x2 * x2) + (y2 * y2)));
                        this.f683i = this.f675a;
                        this.f684j = this.f676b;
                        z = true;
                        z5 = true;
                    }
                } else if (1 == action || 6 == action || 3 == action) {
                    int i3 = this.f660D;
                    if (8 == i3) {
                        float f = this.f661E - x;
                        float abs = Math.abs(this.f663G - y);
                        if (f > this.f679e * 120.0f && abs / f < 0.6f) {
                            this.f686l.run();
                        }
                        this.f696v.setVisibility(8);
                    } else if (9 == i3) {
                        float f2 = x - this.f661E;
                        float abs2 = Math.abs(this.f663G - y);
                        if (f2 > this.f679e * 120.0f && abs2 / f2 < 0.6f) {
                            this.f687m.run();
                        }
                        this.f694t.setVisibility(8);
                    } else if (10 == i3) {
                        float abs3 = Math.abs(x - this.f661E);
                        float f3 = y - this.f663G;
                        if (motionEvent.getEventTime() - motionEvent.getDownTime() < 600 && this.f671O - f3 < ((float) (this.f668L * 2))) {
                            float f4 = this.f670N;
                            float f5 = this.f679e;
                            if (f4 < (80.0f * f5) + abs3 && f3 > f5 * 150.0f && abs3 / f3 < 0.6f) {
                                this.f685k.run();
                            }
                        }
                    } else if (1 == i3) {
                        float f6 = this.f677c;
                        float f7 = (((float) this.f699y) * f6) / 2.0f;
                        float f8 = (f6 * ((float) this.f700z)) / 2.0f;
                        float f9 = this.f675a;
                        if (x >= f9 - f7 && x <= f9 + f7) {
                            float f10 = this.f676b;
                            if (y >= f10 - f8) {
                            }
                        }
                        if (y < ((float) getHeight()) - (this.f679e * 50.0f)) {
                            this.f685k.run();
                        }
                        if (y >= ((float) getHeight()) - (this.f679e * 50.0f) || motionEvent.getEventTime() - motionEvent.getDownTime() >= 150) {
                            this.f672P = 0;
                        } else if (motionEvent.getEventTime() - this.f672P < 500) {
                            removeCallbacks(this.f674R);
                            if (this.f673Q) {
                                this.f677c = Math.max(this.f681g, this.f677c / 3.0f);
                                this.f673Q = false;
                            } else {
                                this.f677c = Math.min(this.f682h, this.f677c * 3.0f);
                                this.f673Q = true;
                            }
                            float f11 = this.f661E;
                            float f12 = this.f677c;
                            float f13 = this.f678d;
                            this.f675a = f11 - (((f11 - this.f675a) * f12) / f13);
                            float f14 = this.f663G;
                            this.f676b = f14 - (((f14 - this.f676b) * f12) / f13);
                            m910b();
                            this.f672P = 0;
                            z3 = true;
                            if (!z3) {
                                float f15 = this.f675a;
                                if (x >= f15 - f7 && x <= f15 + f7) {
                                    float f16 = this.f676b;
                                    if (y >= f16 - f8 && y <= f16 + f8) {
                                        postDelayed(this.f674R, 520);
                                    }
                                }
                            }
                        } else {
                            this.f672P = motionEvent.getEventTime();
                        }
                        z3 = false;
                        if (!z3) {
                        }
                    } else if (7 == i3) {
                        this.f673Q = false;
                    }
                    this.f660D = 0;
                    z = this.f669M;
                    this.f669M = false;
                } else if (2 == action) {
                    float abs4 = Math.abs(x - this.f661E);
                    float abs5 = Math.abs(y - this.f663G);
                    int i4 = this.f660D;
                    if (1 == i4) {
                        boolean z6 = abs4 > abs5 && abs4 > ((float) this.f668L) * 2.0f;
                        if (z6 && x < this.f661E && this.f675a + ((((float) this.f699y) * this.f677c) / 2.0f) <= ((float) getWidth()) && this.f697w) {
                            this.f660D = 8;
                            if (i2 >= 11) {
                                this.f696v.setAlpha(0.0f);
                            }
                            Widgets$CircleImageView widgets$CircleImageView = this.f696v;
                            int width = getWidth();
                            int i5 = this.f692r;
                            int measuredWidth = getMeasuredWidth();
                            int i6 = this.f693s;
                            widgets$CircleImageView.layout(width, i5, measuredWidth + i6, this.f692r + i6);
                            this.f696v.setVisibility(0);
                        } else if (z6 && x > this.f661E && this.f675a - ((((float) this.f699y) * this.f677c) / 2.0f) >= 0.0f && this.f695u) {
                            this.f660D = 9;
                            if (i2 >= 11) {
                                this.f694t.setAlpha(0.0f);
                            }
                            Widgets$CircleImageView widgets$CircleImageView2 = this.f694t;
                            int i7 = this.f693s;
                            int i8 = this.f692r;
                            widgets$CircleImageView2.layout(-i7, i8, 0, i7 + i8);
                            this.f694t.setVisibility(0);
                        } else if (this.f677c > this.f680f || y <= this.f663G || abs5 <= ((float) this.f668L) * 2.0f) {
                            int i9 = this.f668L;
                            if (abs4 > ((float) i9) * 2.0f || abs5 > ((float) i9) * 2.0f) {
                                this.f660D = 3;
                            }
                            z4 = false;
                        } else {
                            this.f660D = 10;
                        }
                        z4 = true;
                        z5 = true;
                    } else {
                        if (2 == i4) {
                            int i10 = this.f668L;
                            if (abs4 >= ((float) i10) * 3.0f || abs5 >= ((float) i10) * 3.0f) {
                                this.f660D = 0;
                            } else {
                                float x3 = x - motionEvent.getX(1);
                                float y3 = y - motionEvent.getY(1);
                                if (Math.abs(this.f667K - ((float) Math.sqrt((double) ((x3 * x3) + (y3 * y3))))) > ((float) this.f668L) * 0.1f) {
                                    this.f660D = 7;
                                }
                            }
                        } else if (3 == i4) {
                            if (this.f677c > this.f680f) {
                                this.f675a += x - this.f662F;
                                this.f676b += y - this.f664H;
                                m910b();
                            }
                        } else if (7 == i4) {
                            if (2 == motionEvent.getPointerCount()) {
                                float x4 = x - motionEvent.getX(1);
                                float y4 = y - motionEvent.getY(1);
                                float sqrt = this.f678d * (((float) Math.sqrt((double) ((x4 * x4) + (y4 * y4)))) / this.f667K);
                                this.f677c = sqrt;
                                if (sqrt < Math.min(this.f680f, this.f679e * 0.1f)) {
                                    this.f677c = Math.min(this.f680f, this.f679e * 0.1f);
                                } else {
                                    float f17 = this.f677c;
                                    float f18 = this.f679e;
                                    if (f17 > f18 * 10.0f) {
                                        this.f677c = f18 * 10.0f;
                                    }
                                }
                                float f19 = (float) ((int) ((this.f661E + this.f665I) / 2.0f));
                                float f20 = this.f677c;
                                float f21 = this.f678d;
                                this.f675a = f19 - (((f19 - this.f683i) * f20) / f21);
                                float f22 = (float) ((int) ((this.f663G + this.f666J) / 2.0f));
                                this.f676b = f22 - (((f22 - this.f684j) * f20) / f21);
                                m910b();
                            }
                            z4 = false;
                            z5 = true;
                        } else if (9 == i4) {
                            int i11 = (int) (x - this.f661E);
                            float f23 = this.f679e * 120.0f;
                            if (((float) i11) >= f23) {
                                i11 = (int) f23;
                                if (i2 >= 11) {
                                    this.f694t.setAlpha(1.0f);
                                }
                            } else if (i2 >= 11) {
                                int i12 = this.f693s;
                                this.f694t.setAlpha(Math.min(1.0f, Math.max(0.0f, i11 < i12 ? 0.0f : (((float) (i11 - i12)) * 1.5f) / f23)));
                            }
                            Widgets$CircleImageView widgets$CircleImageView3 = this.f694t;
                            int i13 = this.f693s;
                            int i14 = this.f692r;
                            widgets$CircleImageView3.layout(i11 - i13, i14, i11, i13 + i14);
                        } else if (8 == i4) {
                            int i15 = (int) (this.f661E - x);
                            float f24 = this.f679e * 120.0f;
                            if (((float) i15) >= f24) {
                                i15 = (int) f24;
                                if (i2 >= 11) {
                                    this.f696v.setAlpha(1.0f);
                                }
                            } else if (i2 >= 11) {
                                int i16 = this.f693s;
                                this.f696v.setAlpha(Math.min(1.0f, Math.max(0.0f, i15 < i16 ? 0.0f : (((float) (i15 - i16)) * 1.5f) / f24)));
                            }
                            Widgets$CircleImageView widgets$CircleImageView4 = this.f696v;
                            int width2 = getWidth() - i15;
                            int i17 = this.f692r;
                            int measuredWidth2 = getMeasuredWidth() - i15;
                            int i18 = this.f693s;
                            widgets$CircleImageView4.layout(width2, i17, measuredWidth2 + i18, this.f692r + i18);
                        }
                        z4 = false;
                    }
                    this.f662F = x;
                    this.f664H = y;
                    z5 = z4;
                    z = z5;
                }
                if (!z5) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, 0.0f, 0.0f, 0);
                    super.dispatchTouchEvent(obtain);
                    obtain.recycle();
                    z2 = true;
                    this.f669M = true;
                } else {
                    z2 = true;
                }
                if (this.f669M) {
                    z = true;
                }
                if (!z && !z && !super.dispatchTouchEvent(motionEvent)) {
                    this.f669M = z2;
                }
                return z2;
            }
            z = false;
            if (!z5) {
            }
            if (this.f669M) {
            }
            if (!z) {
                this.f669M = z2;
            }
            return z2;
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            this.f688n = (ImageView) findViewById(R.id.imagesView);
            this.f689o = (TextView) findViewById(R.id.imagesCaption);
            this.f690p = (ProgressBar) findViewById(R.id.imagesLoad);
            this.f694t = (Widgets$CircleImageView) findViewById(R.id.imagesPrev);
            this.f696v = (Widgets$CircleImageView) findViewById(R.id.imagesNext);
            this.f691q = findViewById(R.id.imagesProgress);
        }

        @Override
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            this.f692r = (i4 - this.f693s) / 2;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int i7 = this.f658B;
            if (!(i7 == 0 || i5 == i7)) {
                this.f675a += (float) ((i5 - i7) / 2);
                this.f676b += (float) ((i6 - this.f659C) / 2);
            }
            this.f658B = i5;
            this.f659C = i6;
            m910b();
            super.onLayout(z, i, i2, i3, i4);
        }

        @Override
        public boolean onLongClick(View view) {
            if (this.f698x != null) {
                MainActivity mainActivity = (MainActivity) getContext();
                OptionPoupupMenuView o1Var = new OptionPoupupMenuView(mainActivity, new C0158a(mainActivity));
                o1Var.addMenuItem(0, 0, 1, "Сохранить");
                o1Var.show(null);
            }
            return true;
        }
    }

    public class C0160a implements PicoImgRequest.TargetCallback {

        class RunnableC0161a implements Runnable {
            final PicoDrawable f705a;

            RunnableC0161a(PicoDrawable eVar) {
                this.f705a = eVar;
            }

            @Override
            public void run() {
                ImageDialog.this.f645b.m909c(null, this.f705a.getIntrinsicWidth(), this.f705a.getIntrinsicHeight());
            }
        }

        C0160a() {
        }

        @Override
        public void onPicoImgResult(PicoImgRequest gVar, PicoDrawable eVar) {
            ImageDialog.this.f645b.post(new RunnableC0161a(eVar));
        }

        @Override
        public void onPicoImgProgress(PicoImgRequest gVar, int i, int i2) {
        }

        @Override
        public void onPicoImgError(PicoImgRequest gVar, Throwable th) {
            ImageDialog.this.f645b.f690p.setVisibility(8);
            Toast.makeText(ImageDialog.this.f644a, "Ошибка загрузки изображения!\n", 0).show();
        }
    }

    class RunnableC0162b implements Runnable {
        RunnableC0162b() {
        }

        @Override
        public void run() {
            ImageDialog imageDialog = ImageDialog.this;
            ImageDialogLayout imageDialogLayout = imageDialog.f645b;
            ImageDialogLayout imageDialogLayout2 = ImageDialog.this.f645b;
            ((RelativeLayout.LayoutParams) ImageDialog.this.f645b.f691q.getLayoutParams()).width = (int) ((imageDialogLayout.f679e * 5.0f) + ((((float) imageDialog.f655l) * (((float) imageDialogLayout.getWidth()) - (imageDialogLayout2.f679e * 10.0f))) / 100.0f));
            imageDialogLayout2.requestLayout();
        }
    }

    public class C0163c implements PicoImgRequest.TargetCallback {
        boolean f708a = false;
        final String f709b;
        final String f710c;

        class RunnableC0164a implements Runnable {
            final PicoDrawable f712a;

            RunnableC0164a(PicoDrawable eVar) {
                this.f712a = eVar;
            }

            @Override
            public void run() {
                C0163c cVar = C0163c.this;
                ImageDialog.this.f645b.m909c(cVar.f709b, this.f712a.getIntrinsicWidth(), this.f712a.getIntrinsicHeight());
            }
        }

        C0163c(String str, String str2) {
            this.f709b = str;
            this.f710c = str2;
        }

        @Override
        @SuppressLint({"NewApi"})
        public void onPicoImgResult(PicoImgRequest gVar, PicoDrawable eVar) {
            if (this.f708a) {
                Toast.makeText(ImageDialog.this.f644a, "Качество изображения ухудшено из-за нехватки памяти.", 0).show();
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 11 && i <= 28 && eVar.isAnimated()) {
                ImageDialog.this.f645b.f688n.setLayerType(1, null);
            }
            ImageDialog.this.f645b.f690p.setVisibility(8);
            ImageDialog.this.f645b.f691q.setVisibility(4);
            ImageDialog.this.f645b.post(new RunnableC0164a(eVar));
        }

        @Override
        public void onPicoImgProgress(PicoImgRequest gVar, int i, int i2) {
            int i3 = (i * 100) / i2;
            ImageDialog imageDialog = ImageDialog.this;
            if (i3 != imageDialog.f655l) {
                imageDialog.f655l = i3;
                imageDialog.f645b.f689o.post(imageDialog.f656m);
            }
        }

        @Override
        public void onPicoImgError(PicoImgRequest gVar, Throwable th) {
            if (!this.f708a) {
                this.f708a = true;
                PicoImgRequest m = PicoImg.loadUrl(ImageDialog.this.f644a, this.f709b, this.f710c);
                m.to(ImageDialog.this.f645b.f688n);
                m.callback(this);
                m.skipCache(false, true, false, false);
                m.sizeToScreen();
                m.runAsync();
                return;
            }
            ImageDialog.this.f645b.f690p.setVisibility(8);
            ImageDialog.this.f645b.f691q.setVisibility(4);
            String str = "Ошибка загрузки изображения!";
            if (th != null) {
                str = str + "\n" + th.getClass().getCanonicalName() + ": " + th.getMessage();
            }
            Toast.makeText(ImageDialog.this.f644a, str, 1).show();
        }
    }

    class RunnableC0165d implements Runnable {
        RunnableC0165d() {
        }

        @Override
        public void run() {
            ImageDialog.this.dismiss();
        }
    }

    class RunnableC0166e implements Runnable {
        RunnableC0166e() {
        }

        @Override
        public void run() {
            ImageDialog.this.dismiss();
        }
    }

    class View$OnClickListenerC0167f implements View.OnClickListener {
        View$OnClickListenerC0167f() {
        }

        @Override
        public void onClick(View view) {
            ImageDialog.this.f650g.run();
        }
    }

    class View$OnClickListenerC0168g implements View.OnClickListener {
        View$OnClickListenerC0168g() {
        }

        @Override
        public void onClick(View view) {
            ImageDialog.this.f651h.run();
        }
    }

    class View$OnClickListenerC0169h implements View.OnClickListener {
        View$OnClickListenerC0169h() {
        }

        @Override
        public void onClick(View view) {
            ImageDialog.this.dismiss();
        }
    }

    class RunnableC0170i implements Runnable {
        RunnableC0170i() {
        }

        @SuppressLint("ResourceType")
        @Override
        public void run() {
            ImageDialog imageDialog = ImageDialog.this;
            int i = imageDialog.f647d;
            if (i != 0) {
                boolean z = true;
                int i2 = i - 1;
                imageDialog.f647d = i2;
                ImageDialogLayout imageDialogLayout = imageDialog.f645b;
                imageDialogLayout.f695u = i2 > 0;
                if (i2 >= imageDialog.f646c.size() - 1) {
                    z = false;
                }
                imageDialogLayout.f697w = z;
                ImageDialog.this.m915d();
                ImageDialog.this.f645b.f688n.setImageResource(17170445);
                ImageDialog imageDialog2 = ImageDialog.this;
                List<C0174m> list = imageDialog2.f646c;
                if (list == null) {
                    return;
                }
                if (list.get(imageDialog2.f647d).f724b == null) {
                    ImageDialog imageDialog3 = ImageDialog.this;
                    DocumentManager.getResultRequest(new LoadImageAttachRequest(imageDialog3.f646c.get(imageDialog3.f647d).f723a));
                    return;
                }
                ImageDialog imageDialog4 = ImageDialog.this;
                imageDialog4.m917b(imageDialog4.f646c.get(imageDialog4.f647d).f724b);
            }
        }
    }

    class RunnableC0171j implements Runnable {
        RunnableC0171j() {
        }

        @SuppressLint("ResourceType")
        @Override
        public void run() {
            ImageDialog imageDialog = ImageDialog.this;
            boolean z = true;
            if (imageDialog.f647d != imageDialog.f646c.size() - 1) {
                ImageDialog imageDialog2 = ImageDialog.this;
                int i = imageDialog2.f647d + 1;
                imageDialog2.f647d = i;
                ImageDialogLayout imageDialogLayout = imageDialog2.f645b;
                imageDialogLayout.f695u = i > 0;
                if (i >= imageDialog2.f646c.size() - 1) {
                    z = false;
                }
                imageDialogLayout.f697w = z;
                ImageDialog.this.m915d();
                ImageDialog.this.f645b.f688n.setImageResource(17170445);
                ImageDialog imageDialog3 = ImageDialog.this;
                if (imageDialog3.f646c.get(imageDialog3.f647d).f724b == null) {
                    ImageDialog imageDialog4 = ImageDialog.this;
                    DocumentManager.getResultRequest(new LoadImageAttachRequest(imageDialog4.f646c.get(imageDialog4.f647d).f723a));
                    return;
                }
                ImageDialog imageDialog5 = ImageDialog.this;
                imageDialog5.m917b(imageDialog5.f646c.get(imageDialog5.f647d).f724b);
            }
        }
    }

    class RunnableC0172k implements Runnable {
        RunnableC0172k() {
        }

        @Override
        public void run() {
            ImageDialog imageDialog = ImageDialog.this;
            int i = imageDialog.f654k - 1;
            imageDialog.f654k = i;
            if (i <= 0) {
                imageDialog.f654k = 5;
            }
            imageDialog.m914e();
        }
    }

    class RunnableC0173l implements Runnable {
        RunnableC0173l() {
        }

        @Override
        public void run() {
            ImageDialog imageDialog = ImageDialog.this;
            int i = imageDialog.f654k + 1;
            imageDialog.f654k = i;
            if (i > 5) {
                imageDialog.dismiss();
            } else {
                imageDialog.m914e();
            }
        }
    }

    public static class C0174m {
        public int f723a;
        public String f724b;
        public String f725c;

        public C0174m(int i, String str, String str2) {
            this.f723a = i;
            this.f724b = str;
            this.f725c = str2;
        }
    }

    class LoadImageAttachRequest extends API.ForumAttachRequest {

        class RunnableC0176a implements Runnable {
            RunnableC0176a() {
            }

            @Override
            public void run() {
                ImageDialog.this.f645b.f690p.setVisibility(0);
            }
        }

        LoadImageAttachRequest(int attachId) {
            super(attachId);
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (ImageDialog.this.isShowing()) {
                ImageDialog.this.f645b.f690p.setVisibility(8);
                if (status == 0) {
                    ImageDialog.this.m917b(uVar.getString(0));
                } else {
                    Toast.makeText(ImageDialog.this.f644a, "Ошибка при запросе изображения", 0).show();
                }
            }
        }

        @Override
        public Document generate() {
            ImageDialog.this.f644a.runOnUiThread(new RunnableC0176a());
            return super.generate();
        }
    }

    public ImageDialog(MainActivity mainActivity) {
        super(mainActivity, R.style.ImageViewer);
        this.f648e = new RunnableC0170i();
        this.f649f = new RunnableC0171j();
        this.f650g = new RunnableC0172k();
        this.f651h = new RunnableC0173l();
        this.f652i = Util.C0427h.UnEscapeString("&#x25CF;");
        this.f653j = Util.C0427h.UnEscapeString("&#x25CB;");
        this.f654k = 0;
        this.f655l = 0;
        this.f656m = new RunnableC0162b();
        this.f644a = mainActivity;
        this.f645b = (ImageDialogLayout) mainActivity.getLayoutInflater().inflate(R.layout.image_gallary, (ViewGroup) null, false);
        getWindow().setBackgroundDrawable(mainActivity.skin.m736f(R.color.image_gallary_bg));
        ImageDialogLayout imageDialogLayout = this.f645b;
        imageDialogLayout.f685k = new RunnableC0165d();
        imageDialogLayout.f687m = this.f648e;
        imageDialogLayout.f686l = this.f649f;
        setContentView(imageDialogLayout);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(-1, -1);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f645b.setSystemUiVisibility(1792);
        }
        getWindow().getAttributes().gravity = 17;
    }

    public static void m918a(MainActivity mainActivity) {
        ImageDialog imageDialog = new ImageDialog(mainActivity, true);
        imageDialog.show();
        CustomDialog.m623c(imageDialog);
    }

    public boolean m917b(String str) {
        if (!isShowing()) {
            ImageDialogLayout imageDialogLayout = this.f645b;
            List<C0174m> list = this.f646c;
            imageDialogLayout.f695u = list != null && list.size() > 1 && this.f647d > 0;
            ImageDialogLayout imageDialogLayout2 = this.f645b;
            List<C0174m> list2 = this.f646c;
            imageDialogLayout2.f697w = list2 != null && list2.size() > 1 && this.f647d < this.f646c.size() - 1;
            show();
            CustomDialog.m623c(this);
        }
        m915d();
        this.f645b.f690p.setVisibility(0);
        this.f655l = 0;
        this.f656m.run();
        this.f645b.f691q.setVisibility(0);
        String substring = str.contains("?") ? str.substring(0, str.indexOf("?")) : null;
        PicoImgRequest m = PicoImg.loadUrl(this.f644a, str, substring);
        m.to(this.f645b.f688n);
        m.callback(new C0163c(str, substring));
        m.skipCache(false, true, false, false);
        m.runAsync();
        return true;
    }

    public boolean m916c(List<C0174m> list, int i) {
        this.f646c = list;
        this.f647d = i;
        if (list.get(i).f724b != null) {
            return m917b(list.get(i).f724b);
        }
        if (!isShowing()) {
            ImageDialogLayout imageDialogLayout = this.f645b;
            List<C0174m> list2 = this.f646c;
            imageDialogLayout.f695u = list2 != null && list2.size() > 1 && this.f647d > 0;
            ImageDialogLayout imageDialogLayout2 = this.f645b;
            List<C0174m> list3 = this.f646c;
            imageDialogLayout2.f697w = list3 != null && list3.size() > 1 && this.f647d < this.f646c.size() - 1;
            show();
            CustomDialog.m623c(this);
        }
        m915d();
        if (DocumentManager.getResultRequest(new LoadImageAttachRequest(list.get(i).f723a)) != 0) {
            return true;
        }
        return false;
    }

    void m915d() {
        List<C0174m> list = this.f646c;
        if (list == null || list.size() <= 1) {
            this.f645b.f689o.setVisibility(8);
            this.f645b.f689o.setText("Изображение");
            return;
        }
        this.f645b.f689o.setVisibility(0);
        String str = "Изображение " + (this.f647d + 1) + " из " + this.f646c.size();
        SpannableString spannableString = new SpannableString(str + "\n" + this.f646c.get(this.f647d).f725c);
        spannableString.setSpan(new AbsoluteSizeSpan((int) (this.f644a.f732c * 14.0f)), 0, str.length(), 33);
        this.f645b.f689o.setText(spannableString);
    }

    @SuppressLint("ResourceType")
    @Override
    public void dismiss() {
        this.f646c = null;
        this.f645b.f688n.setImageResource(17170445);
        super.dismiss();
    }

    @SuppressLint("ResourceType")
    void m914e() {
        String str = "";
        int i = 1;
        while (i <= 5) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(i == this.f654k ? this.f652i : this.f653j);
            str = sb.toString();
            i++;
        }
        ((TextView) this.f645b.findViewById(R.id.guide_beats)).setText(str);
        this.f645b.f688n.setImageResource(0x106000d);
        int i2 = this.f654k;
        PicoImgRequest k = PicoImg.loadResource(this.f644a, i2 == 1 ? R.drawable.guide_main : i2 == 2 ? R.drawable.guide_sidebar : i2 == 3 ? R.drawable.guide_forum : i2 == 4 ? R.drawable.guide_favorite : i2 == 5 ? R.drawable.guide_qms : 0);
        k.to(this.f645b.f688n);
        k.skipCache(false, true, false, false);
        k.callback(new C0160a());
        k.runAsync();
    }

    @Override
    @SuppressLint({"NewApi"})
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.f654k == 0 && Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public ImageDialog(MainActivity mainActivity, boolean z) {
        super(mainActivity, R.style.ImageViewer);
        this.f648e = new RunnableC0170i();
        this.f649f = new RunnableC0171j();
        this.f650g = new RunnableC0172k();
        this.f651h = new RunnableC0173l();
        this.f652i = Util.C0427h.UnEscapeString("&#x25CF;");
        this.f653j = Util.C0427h.UnEscapeString("&#x25CB;");
        this.f654k = 0;
        this.f655l = 0;
        this.f656m = new RunnableC0162b();
        this.f644a = mainActivity;
        this.f645b = (ImageDialogLayout) mainActivity.getLayoutInflater().inflate(R.layout.dlg_guide, (ViewGroup) null, false);
        getWindow().setBackgroundDrawable(mainActivity.skin.m736f(R.color.guide_gallary_bg));
        ImageDialogLayout imageDialogLayout = this.f645b;
        imageDialogLayout.f685k = new RunnableC0166e();
        imageDialogLayout.f687m = this.f650g;
        imageDialogLayout.f686l = this.f651h;
        setContentView(imageDialogLayout);
        findViewById(R.id.guide_prev).setOnClickListener(new View$OnClickListenerC0167f());
        findViewById(R.id.guide_next).setOnClickListener(new View$OnClickListenerC0168g());
        findViewById(R.id.guide_close).setOnClickListener(new View$OnClickListenerC0169h());
        this.f654k = 1;
        m914e();
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(-1, -1);
        getWindow().getAttributes().gravity = 17;
    }
}
