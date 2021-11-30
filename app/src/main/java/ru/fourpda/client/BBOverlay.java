package ru.fourpda.client;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoDrawable;


public class BBOverlay extends View implements Drawable.Callback {
    static int f550j;
    static int f552l;
    BBDisplay f554a;
    float f557d;
    float f558e;
    boolean f559f;
    boolean f560g;
    static int f549i;
    static int f551k = f549i;
    static char[] f553m = {' ', ',', '.', '?', '(', ')', '!'};
    C0149e f555b = new C0149e();
    char[] f556c = f553m;
    int f561h = -1;

    public class View$OnClickListenerC0145a implements View.OnClickListener {
        View$OnClickListenerC0145a() {
          //  BBOverlay.this = r1;
        }

        @Override
        public void onClick(View view) {
            BBOverlay bBOverlay = BBOverlay.this;
            BBDisplay bBDisplay = bBOverlay.f554a;
            bBDisplay.f538o.showBBNotify(bBDisplay, bBDisplay.f527d, 0, bBOverlay.m959a());
        }
    }

    public class View$OnClickListenerC0146b implements View.OnClickListener {
        View$OnClickListenerC0146b() {
           // BBOverlay.this = r1;
        }

        @Override
        public void onClick(View view) {
            BBOverlay bBOverlay = BBOverlay.this;
            BBDisplay bBDisplay = bBOverlay.f554a;
            bBDisplay.f538o.showBBNotify(bBDisplay, bBDisplay.f527d, 1, bBOverlay.m959a());
        }
    }

    public class View$OnClickListenerC0147c implements View.OnClickListener {
        View$OnClickListenerC0147c() {
          //  BBOverlay.this = r1;
        }

        @Override
        public void onClick(View view) {
            BBOverlay bBOverlay = BBOverlay.this;
            BBDisplay bBDisplay = bBOverlay.f554a;
            bBDisplay.f538o.showBBNotify(bBDisplay, bBDisplay.f527d, 0, bBOverlay.m959a());
        }
    }

    public class C0148d {
        float f565a;
        float f566b;
        float f567c;
        float f568d;
        float f569e;
        float f570f;

        C0148d(BBOverlay bBOverlay) {
        }
    }

    public class C0149e {
        public C0151g f571a;
        public C0151g f572b;
        boolean f573c;
        public boolean f574d;
        public boolean f575e;
        public boolean f576f;

        C0149e() {
        //    BBOverlay.this = r1;
        }

        public C0151g m947a(int i, int i2, int i3) {
            C0151g gVar = this.f571a;
            while (gVar.f588c != i) {
                gVar = gVar.f587b;
                if (gVar == this.f571a) {
                    C0151g gVar2 = new C0151g(i, i3, i2);
                    C0151g gVar3 = this.f571a;
                    gVar2.f587b = gVar3;
                    C0151g gVar4 = this.f572b;
                    gVar2.f586a = gVar4;
                    gVar3.f586a = gVar2;
                    this.f571a = gVar2;
                    gVar4.f587b = gVar2;
                    return gVar2;
                }
            }
            return gVar;
        }

        public C0151g m946b(int i, int i2, int i3) {
            C0151g gVar = this.f572b;
            while (gVar.f588c != i) {
                gVar = gVar.f586a;
                if (gVar == this.f572b) {
                    C0151g gVar2 = new C0151g(i, i3, i2);
                    C0151g gVar3 = this.f572b;
                    gVar2.f586a = gVar3;
                    C0151g gVar4 = this.f571a;
                    gVar2.f587b = gVar4;
                    gVar3.f587b = gVar2;
                    this.f572b = gVar2;
                    gVar4.f586a = gVar2;
                    return gVar2;
                }
            }
            return gVar;
        }

        public boolean m945c(C0148d dVar) {
            if (this.f574d) {
                int i = m939i();
                if (i < 0) {
                    m937k(false);
                    dVar.f570f = 0.0f;
                    dVar.f569e = 0.0f;
                    dVar.f568d = 0.0f;
                    dVar.f567c = 0.0f;
                    dVar.f566b = 0.0f;
                    dVar.f565a = 0.0f;
                    return false;
                }
                C0150f[] fVarArr = this.f571a.f591f;
                dVar.f568d = fVarArr[i].f579b;
                dVar.f569e = fVarArr[i].f582e;
                int j = m938j();
                if (j < 0) {
                    m937k(false);
                    dVar.f570f = 0.0f;
                    dVar.f569e = 0.0f;
                    dVar.f568d = 0.0f;
                    dVar.f567c = 0.0f;
                    dVar.f566b = 0.0f;
                    dVar.f565a = 0.0f;
                    return false;
                }
                C0150f[] fVarArr2 = this.f572b.f591f;
                dVar.f565a = fVarArr2[j].f580c;
                dVar.f566b = fVarArr2[j].f582e;
                return false;
            }
            int i2 = m939i();
            if (i2 < 0) {
                m937k(false);
                dVar.f570f = 0.0f;
                dVar.f569e = 0.0f;
                dVar.f568d = 0.0f;
                dVar.f567c = 0.0f;
                dVar.f566b = 0.0f;
                dVar.f565a = 0.0f;
                return false;
            }
            C0150f[] fVarArr3 = this.f571a.f591f;
            dVar.f565a = fVarArr3[i2].f579b;
            dVar.f566b = fVarArr3[i2].f582e;
            int j2 = m938j();
            if (j2 < 0) {
                m937k(false);
                dVar.f570f = 0.0f;
                dVar.f569e = 0.0f;
                dVar.f568d = 0.0f;
                dVar.f567c = 0.0f;
                dVar.f566b = 0.0f;
                dVar.f565a = 0.0f;
                return false;
            }
            C0150f[] fVarArr4 = this.f572b.f591f;
            dVar.f568d = fVarArr4[j2].f580c;
            dVar.f569e = fVarArr4[j2].f582e;
            return true;
        }

        public boolean m944d(C0148d dVar) {
            int i = m939i();
            C0150f[] fVarArr = this.f571a.f591f;
            dVar.f565a = fVarArr[i].f579b;
            dVar.f566b = fVarArr[i].f581d;
            dVar.f567c = fVarArr[i].f582e;
            int j = m938j();
            C0150f[] fVarArr2 = this.f572b.f591f;
            dVar.f568d = fVarArr2[j].f580c;
            dVar.f569e = fVarArr2[j].f581d;
            dVar.f570f = fVarArr2[j].f582e;
            return this.f573c && !this.f574d;
        }

        public boolean m943e(C0151g gVar, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4) {
            C0150f[] fVarArr = gVar.f591f;
            if (fVarArr[i] == null) {
                fVarArr[i] = new C0150f();
            }
            fVarArr[i].f585h = true;
            fVarArr[i].f583f = i2;
            fVarArr[i].f584g = i3;
            fVarArr[i].f578a = i4;
            fVarArr[i].f579b = f;
            fVarArr[i].f580c = f2;
            fVarArr[i].f581d = f3;
            fVarArr[i].f582e = f4;
            if (i3 == -1 || i3 - i2 >= 0) {
                return true;
            }
            return false;
        }

        public boolean m942f(C0151g gVar, int i, int i2, float f) {
            C0150f[] fVarArr = gVar.f591f;
            fVarArr[i].f583f = i2;
            fVarArr[i].f579b = f;
            return fVarArr[i].f584g == -1 || fVarArr[i].f584g - i2 >= 0;
        }

        public boolean m941g(C0151g gVar, int i, int i2, float f) {
            C0150f[] fVarArr = gVar.f591f;
            fVarArr[i].f584g = i2;
            fVarArr[i].f580c = f;
            return i2 == -1 || i2 - fVarArr[i].f583f >= 0;
        }

        public void m940h(int i, int i2, int i3) {
            C0151g gVar = new C0151g(i, i3, i2);
            this.f571a = gVar;
            this.f572b = gVar;
            gVar.f587b = gVar;
            gVar.f586a = gVar;
            m937k(true);
            this.f574d = false;
        }

        public int m939i() {
            int i = 0;
            while (true) {
                C0151g gVar = this.f571a;
                if (i >= gVar.f590e) {
                    return -1;
                }
                C0150f[] fVarArr = gVar.f591f;
                if (fVarArr[i] != null && fVarArr[i].f585h) {
                    return i;
                }
                i++;
            }
        }

        public int m938j() {
            for (int i = this.f572b.f590e - 1; i >= 0; i--) {
                C0150f[] fVarArr = this.f572b.f591f;
                if (fVarArr[i] != null && fVarArr[i].f585h) {
                    return i;
                }
            }
            return -1;
        }

        public void m937k(boolean z) {
            if (!z) {
                this.f573c = false;
                this.f571a = null;
                this.f572b = null;
                this.f574d = false;
            } else {
                this.f573c = true;
            }
            BBOverlay.this.invalidate();
        }
    }

    public static class C0150f {
        public int f578a;
        public float f579b;
        public float f580c;
        public float f581d;
        public float f582e;
        public int f583f;
        public int f584g;
        public boolean f585h;

        protected C0150f() {
        }
    }

    public static class C0151g {
        public C0151g f586a;
        public C0151g f587b;
        public int f588c;
        public int f589d;
        public int f590e;
        public C0150f[] f591f;
        public boolean f592g = false;

        public C0151g(int i, int i2, int i3) {
            this.f588c = i;
            this.f589d = i2;
            this.f590e = i3;
            if (i3 > 0) {
                this.f591f = new C0150f[i3];
            }
        }
    }

    public static class C0152h {
        public static C0152h f593s;
        public static C0152h f594t;
        public static C0153a f595u;
        Drawable f596a;
        int f597b;
        int f598c;
        int f599d;
        int f600e;
        int f601f;
        int f602g;
        int f603h;
        int f604i;
        int f605j;
        int f606k;
        int f607l;
        int f608m;
        float f609n;
        float f610o;
        int f611p;
        public boolean f612q;
        public boolean f613r;

        public static class C0153a {
            List<C0154a> f614a = new Vector(2);
            int f615b;
            int f616c;
            int f617d;
            int f618e;
            int f619f;
            Paint f620g;
            NinePatchDrawable f621h;
            Rect f622i;
            int f623j;
            int f624k;
            public boolean f625l;

            public static class C0154a {
                C0153a f626a;
                View.OnClickListener f627b;
                String f628c;
                public int f629d;
                public int f630e;
                public int f631f;
                public int f632g;
                public int f633h;
                public int f634i;

                public C0154a(String str, View.OnClickListener onClickListener) {
                    this.f627b = onClickListener;
                    this.f628c = str;
                }

                public boolean m924a(int i, int i2) {
                    View.OnClickListener onClickListener = this.f627b;
                    if (onClickListener == null || i < this.f629d || i > this.f631f || i2 < this.f630e || i2 > this.f632g) {
                        return false;
                    }
                    onClickListener.onClick(null);
                    return true;
                }

                public void m923b(Canvas canvas, int i, int i2) {
                    this.f626a.f620g.setColor(Skin.C0353a.f1365U);
                    String str = this.f628c;
                    int i3 = i + this.f629d;
                    C0153a aVar = this.f626a;
                    int i4 = aVar.f615b;
                    canvas.drawText(str, (float) (i3 + i4), (float) (((i2 + this.f632g) - i4) - aVar.f616c), aVar.f620g);
                }

                public void m922c(C0153a aVar) {
                    this.f626a = aVar;
                    Rect rect = new Rect();
                    Paint paint = this.f626a.f620g;
                    String str = this.f628c;
                    paint.getTextBounds(str, 0, str.length(), rect);
                    int length = this.f628c.length();
                    float[] fArr = new float[length];
                    this.f626a.f620g.getTextWidths(this.f628c, fArr);
                    float f = 0.5f;
                    for (int i = 0; i < length; i++) {
                        f += fArr[i];
                    }
                    C0153a aVar2 = this.f626a;
                    this.f633h = ((int) f) + (aVar2.f615b * 2);
                    this.f634i = ((int) aVar2.f620g.getFontSpacing()) + (this.f626a.f615b * 2);
                }
            }

            public C0153a(Drawable drawable, float f, float f2) {
                this.f621h = (NinePatchDrawable) drawable;
                Paint paint = new Paint(1);
                this.f620g = paint;
                paint.setTextSize(f);
                this.f620g.setStrokeJoin(Paint.Join.ROUND);
                this.f616c = (int) ((this.f620g.getFontSpacing() * 0.2f) + 0.5f);
                int i = (int) (f2 + 0.5f);
                this.f619f = i;
                this.f615b = i * 8;
                Rect rect = new Rect();
                this.f622i = rect;
                this.f621h.getPadding(rect);
            }

            public void m930a(C0154a... aVarArr) {
                int i;
                this.f614a.clear();
                for (C0154a aVar : aVarArr) {
                    aVar.m922c(this);
                    if (this.f614a.size() > 0) {
                        List<C0154a> list = this.f614a;
                        i = list.get(list.size() - 1).f631f + this.f619f;
                    } else {
                        i = this.f622i.left;
                    }
                    aVar.f629d = i;
                    int i2 = i + aVar.f633h;
                    aVar.f631f = i2;
                    Rect rect = this.f622i;
                    this.f617d = i2 + rect.right;
                    int i3 = rect.top;
                    aVar.f630e = i3;
                    int i4 = i3 + aVar.f634i;
                    aVar.f632g = i4;
                    this.f618e = i4 + rect.bottom;
                    this.f614a.add(aVar);
                }
            }

            public void m929b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9;
                float f = C0152h.f593s.f610o;
                C0152h hVar = C0152h.f594t;
                int i10 = ((int) (f + hVar.f610o)) / 2;
                int i11 = 0;
                boolean z = true;
                if (this.f625l) {
                    int i12 = this.f618e;
                    if (i2 >= i12) {
                        i9 = i2 - i12;
                    } else {
                        int i13 = hVar.f602g;
                        int i14 = C0152h.f593s.f602g;
                        if ((i13 - i14) - (i10 * 2) >= i12) {
                            i9 = i14 + i10;
                        } else {
                            int i15 = C0152h.f594t.f602g;
                            if ((i8 - i15) - i10 >= i12) {
                                i9 = i15 + i10;
                            } else {
                                i9 = 0;
                                z = false;
                            }
                        }
                    }
                    if (z) {
                        int i16 = this.f617d;
                        if (i > i7 - (i16 / 2)) {
                            i11 = i7 - i16;
                        } else if (i > i16 / 2) {
                            i11 = i - (i16 / 2);
                        }
                    }
                } else {
                    int i17 = hVar.f602g;
                    int i18 = (i8 - i17) - i10;
                    int i19 = this.f618e;
                    if (i18 >= i19) {
                        i9 = i17 + i10;
                    } else if ((i17 - C0152h.f593s.f602g) - (i10 * 2) >= i19) {
                        i9 = i6 - i19;
                    } else if (i2 >= i19) {
                        i9 = i2 - i19;
                    } else {
                        i9 = 0;
                        z = false;
                    }
                    if (z) {
                        int i20 = this.f617d;
                        if (i4 >= i7 - (i20 / 2)) {
                            i11 = i7 - i20;
                        } else if (i4 > i20 / 2) {
                            i11 = i4 - (i20 / 2);
                        }
                    }
                }
                if (!z) {
                    Point f2 = m925f(i7 - this.f617d, i8 - this.f618e);
                    i11 = f2.x;
                    i9 = f2.y;
                }
                this.f623j = i11;
                this.f624k = i9;
            }

            int m928c(int i, int i2, int i3, int i4) {
                int i5 = this.f617d + i;
                int i6 = this.f618e + i2;
                C0152h hVar = C0152h.f593s;
                int i7 = 1;
                int i8 = 0;
                while (true) {
                    int i9 = hVar.f601f;
                    int i10 = i9 - i3;
                    int i11 = i9 + i3;
                    int i12 = hVar.f602g;
                    int i13 = i12 - i4;
                    int i14 = i12 + i4;
                    int i15 = (i < i10 || i5 > i11) ? (i > i10 || i5 < i11) ? (i11 <= i || i5 <= i11) ? (i5 <= i10 || i10 <= i) ? 0 : i5 - i10 : i11 - i : i11 - i10 : i5 - i;
                    int i16 = (i2 < i13 || i6 > i14) ? (i2 > i13 || i6 < i14) ? (i14 <= i2 || i6 <= i14) ? (i6 <= i13 || i13 <= i2) ? 0 : i6 - i13 : i14 - i2 : i14 - i13 : i6 - i2;
                    i8 += i15 * i16;
                    if (i15 < 0 || i16 < 0) {
                        break;
                    } else if (i7 == 2) {
                        return i8;
                    } else {
                        hVar = C0152h.f594t;
                        i7++;
                    }
                }
                return i7;
            }

            public boolean m927d(int i, int i2) {
                for (int i3 = 0; i3 < this.f614a.size(); i3++) {
                    if (this.f614a.get(i3).m924a(i - this.f623j, i2 - this.f624k)) {
                        return true;
                    }
                }
                return false;
            }

            public void m926e(Canvas canvas) {
                NinePatchDrawable ninePatchDrawable = this.f621h;
                if (ninePatchDrawable != null) {
                    int i = this.f623j;
                    int i2 = this.f624k;
                    ninePatchDrawable.setBounds(i, i2, this.f617d + i, this.f618e + i2);
                    this.f621h.draw(canvas);
                    int size = this.f614a.size();
                    for (int i3 = 1; i3 <= size; i3++) {
                        C0154a aVar = this.f614a.get(i3 - 1);
                        aVar.m923b(canvas, this.f623j, this.f624k);
                        if (i3 != size) {
                            this.f620g.setColor(Skin.C0353a.f1365U);
                            int i4 = 1;
                            while (true) {
                                int i5 = this.f619f;
                                if (i4 <= i5) {
                                    int i6 = this.f623j;
                                    int i7 = aVar.f631f;
                                    int i8 = this.f624k;
                                    canvas.drawLine((float) (i6 + i7 + i4), (float) (aVar.f630e + i8 + (i5 * 6)), (float) (i6 + i7 + i4), (float) ((i8 + aVar.f632g) - (i5 * 6)), this.f620g);
                                    i4++;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                }
            }

            Point m925f(int i, int i2) {
                int i3;
                int i4;
                int i5 = this.f619f;
                int i6 = (i / i5) * i5;
                int i7 = (i2 / i5) * i5;
                int i8 = (int) ((C0152h.f593s.f610o + C0152h.f594t.f610o) / 2.0f);
                int i9 = (int) ((C0152h.f593s.f610o + C0152h.f594t.f610o) / 2.0f);
                int i10 = 0;
                if (this.f625l) {
                    i10 = i6;
                    i3 = i5;
                    i6 = 0;
                    i4 = i7;
                    i7 = 0;
                } else {
                    i3 = -i5;
                    i4 = 0;
                }
                int i11 = i7;
                int i12 = Integer.MAX_VALUE;
                while (i7 != i4) {
                    int i13 = i12;
                    int i14 = i11;
                    int i15 = i6;
                    while (i6 != i10) {
                        int c = m928c(i6, i7, i8, i9);
                        if (c < i13) {
                            if (c == 0) {
                                return new Point(i6, i7);
                            }
                            i15 = i6;
                            i14 = i7;
                            i13 = c;
                        }
                        i6 += i3;
                    }
                    i7 += i3;
                    i6 = i15;
                    i11 = i14;
                    i12 = i13;
                }
                return new Point(i6, i11);
            }
        }

        public C0152h(Drawable drawable) {
            this.f596a = drawable;
        }

        public static void m934c() {
            C0152h hVar = f593s;
            int i = hVar.f603h;
            int i2 = hVar.f604i;
            C0152h hVar2 = f594t;
            hVar.f603h = hVar2.f603h;
            hVar.f604i = hVar2.f604i;
            hVar2.f603h = i;
            hVar2.f604i = i2;
            int i3 = hVar.f601f;
            int i4 = hVar.f602g;
            hVar.f601f = hVar2.f601f;
            hVar.f602g = hVar2.f602g;
            hVar2.f601f = i3;
            hVar2.f602g = i4;
            hVar.f613r = !hVar.f613r;
            hVar2.f613r = !hVar2.f613r;
        }

        public void m936a(float f, float f2, float f3, float f4, float f5) {
            this.f611p = (int) f5;
            this.f607l = this.f596a.getMinimumWidth();
            int minimumHeight = this.f596a.getMinimumHeight();
            this.f608m = minimumHeight;
            float f6 = ((float) minimumHeight) / 1.5f;
            this.f610o = f6;
            this.f609n = (float) Math.pow((double) f6, 2.0d);
            int i = this.f607l;
            int i2 = (int) ((((float) i) * f) + 0.5f);
            this.f597b = i2;
            this.f598c = i - i2;
            int i3 = this.f608m;
            int i4 = (int) ((((float) i3) * f2) + 0.5f);
            this.f599d = i4;
            this.f600e = i3 - i4;
            this.f605j = (int) ((((float) i) * f3) + 0.5f);
            this.f606k = (int) ((((float) i3) * f4) + 0.5f);
        }

        public void m935b(Canvas canvas, int i, int i2) {
            if (!this.f613r || this.f612q) {
                this.f601f = (i - this.f597b) + this.f605j;
                this.f602g = (i2 - this.f599d) + this.f606k;
            }
            int i3 = this.f601f;
            int i4 = this.f597b;
            int i5 = (i3 + i4) - this.f605j;
            int i6 = this.f602g;
            int i7 = this.f599d;
            int i8 = (i6 + i7) - this.f606k;
            this.f596a.setBounds(i5 - i4, i8 - i7, i5 + this.f598c, i8 + this.f600e);
            this.f596a.draw(canvas);
        }

        public float m933d(int i, int i2) {
            int i3 = this.f601f - i;
            this.f603h = i3;
            int i4 = this.f602g;
            int i5 = i4 - i2;
            this.f604i = i5;
            float f = ((float) ((i3 * i3) + (i5 * i5))) / this.f609n;
            if (f <= 1.0f) {
                int i6 = i4 - this.f611p;
                this.f602g = i6;
                int i7 = i6 - i2;
                this.f604i = i7;
                if (i7 > 0) {
                    this.f604i = i7 / 2;
                }
            } else {
                this.f603h = 0;
                this.f604i = 0;
            }
            return f;
        }

        public boolean m932e(Point point) {
            boolean z = this.f613r;
            if (z) {
                this.f601f = point.x + this.f603h;
                this.f602g = point.y + this.f604i;
            }
            point.x = (this.f601f + this.f597b) - this.f605j;
            point.y = (this.f602g + this.f599d) - this.f606k;
            return z;
        }

        public void m931f() {
            this.f603h = 0;
            this.f604i = 0;
        }
    }

    static {
        int argb = Color.argb(255, 46, 171, 221);
        f550j = argb;
        f552l = argb;
    }

    public BBOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m951i(context);
    }

    public String m959a() {
        BBString pVar;
        String str;
        C0151g gVar = null;
        int i;
        C0151g gVar2;
        C0150f[] fVarArr;
        C0151g gVar3 = null;
        BBOverlay bBOverlay = this;
        BBDisplay bBDisplay = bBOverlay.f554a;
        String str2 = "";
        if (!(bBDisplay == null || (pVar = bBDisplay.f527d) == null)) {
            C0149e eVar = bBOverlay.f555b;
            if (eVar.f573c && !eVar.f574d) {
                BBString.C0685o[] oVarArr = pVar.f2220a;
                StringBuilder sb = new StringBuilder();
                C0151g gVar4 = bBOverlay.f555b.f571a;
                int i2 = 1;
                boolean z = true;
                while (true) {
                    if (gVar4 == null) {
                        str = str2;
                        break;
                    }
                    int i3 = gVar4.f588c;
                    int j = oVarArr[i3].m444j();
                    if (j != 7 || !oVarArr[i3].m443k(8388608)) {
                        C0149e eVar2 = bBOverlay.f555b;
                        C0151g gVar5 = eVar2.f571a;
                        String str3 = "[*]";
                        if (gVar4 == gVar5 || gVar4 == eVar2.f572b) {
                            boolean z2 = gVar4 == gVar5;
                            int i4 = z2 ? eVar2.m939i() : eVar2.m938j();
                            int i5 = z2 ? 1 : -1;
                            str = str2;
                            int i6 = 0;
                            int i7 = -1;
                            int i8 = -1;
                            int i9 = 0;
                            while (true) {
                                if (z2) {
                                    if (i4 >= gVar4.f590e) {
                                        break;
                                    }
                                    fVarArr = gVar4.f591f;
                                    if (fVarArr[i4] == null) {
                                        gVar3 = gVar4;
                                        if (fVarArr[i4].f585h) {
                                            if (fVarArr[i4].f584g == -1) {
                                                i9 = z2 ? i9 + 1 : i9 + 1;
                                            } else if (z2) {
                                                if (i7 == -1) {
                                                    i7 = fVarArr[i4].f583f;
                                                }
                                                i8 = fVarArr[i4].f584g;
                                                i6 = 0;
                                            } else {
                                                if (i8 == -1) {
                                                    i8 = fVarArr[i4].f584g;
                                                }
                                                i7 = fVarArr[i4].f583f;
                                                i9 = 0;
                                            }
                                        }
                                    } else {
                                        gVar3 = gVar4;
                                    }
                                    i4 += i5;
                                    str3 = str3;
                                    gVar4 = gVar3;
                                } else {
                                    if (i4 < 0) {
                                        break;
                                    }
                                    fVarArr = gVar4.f591f;
                                    if (fVarArr[i4] == null) {
                                    }
                                    i4 += i5;
                                    str3 = str3;
                                    gVar4 = gVar3;
                                }
                            }
                            if (i7 == -1 || i8 == -1) {
                                bBOverlay = this;
                                gVar = gVar4;
                            } else {
                                for (int i10 = 0; i10 < i9; i10++) {
                                    sb.append('\n');
                                }
                                if (j == 3) {
                                    sb.append(pVar.f2231k, i7, (i8 + 1) - i7);
                                } else if (j == 5) {
                                    sb.append((CharSequence) pVar.f2205L[oVarArr[i3].f2328e], i7, i8 + 1);
                                } else if (j == 8) {
                                    if (oVarArr[i3].f2328e != -1) {
                                        if (!z) {
                                            sb.append('\n');
                                        }
                                        sb.append((CharSequence) pVar.f2205L[oVarArr[i3].f2328e], i7, i8 + 1);
                                        bBOverlay = this;
                                        gVar = gVar4;
                                        if (gVar != bBOverlay.f555b.f572b) {
                                            sb.append(' ');
                                        }
                                    } else {
                                        bBOverlay = this;
                                        gVar = gVar4;
                                        sb.append(str3);
                                    }
                                    z = false;
                                    for (i = 0; i < i6; i++) {
                                        sb.append('\n');
                                    }
                                    if (z2 && oVarArr[i3].m450d() && (gVar2 = gVar.f587b) != null && gVar2 != bBOverlay.f555b.f571a) {
                                        sb.append('\n');
                                    }
                                }
                                bBOverlay = this;
                                gVar = gVar4;
                                if (z2) {
                                    sb.append('\n');
                                }
                            }
                            gVar4 = gVar.f587b;
                            if (gVar4 == bBOverlay.f555b.f571a) {
                                break;
                            }
                            str2 = str;
                            i2 = 1;
                        } else {
                            if (j == 3) {
                                sb.append(pVar.f2231k, oVarArr[i3].f2328e, oVarArr[i3].f2329f);
                            } else {
                                if (j == 5) {
                                    sb.append(pVar.f2205L[oVarArr[i3].f2328e]);
                                    String[] strArr = pVar.f2205L;
                                } else if (j == 8) {
                                    if (oVarArr[i3].f2328e != -1) {
                                        if (!z) {
                                            sb.append('\n');
                                        }
                                        sb.append(pVar.f2205L[oVarArr[i3].f2328e]);
                                        sb.append(' ');
                                    } else {
                                        sb.append(str3);
                                    }
                                }
                                z = false;
                            }
                            if (oVarArr[i3].m450d()) {
                                sb.append('\n');
                                str = str2;
                                gVar = gVar4;
                            }
                        }
                        z = true;
                        gVar4 = gVar.f587b;
                        if (gVar4 == bBOverlay.f555b.f571a) {
                        }
                    } else {
                        sb.append(Util.SmileClass.smilesList.get(oVarArr[i3].f2329f).f1654a);
                    }
                    str = str2;
                    gVar = gVar4;
                    gVar4 = gVar.f587b;
                    if (gVar4 == bBOverlay.f555b.f571a) {
                    }
                }
                return sb.toString().replace("\u0000", str).replace("\u200b", str);
            }
        }
        return str2;
    }

    protected void m958b(float f, float f2) {
        BBString pVar;
        BBDisplay bBDisplay = this.f554a;
        if (bBDisplay != null && (pVar = bBDisplay.f527d) != null) {
            C0149e eVar = this.f555b;
            if (eVar.f573c) {
                BBString.C0685o[] oVarArr = pVar.f2220a;
                C0151g gVar = eVar.f571a;
                int i = eVar.m939i();
                int i2 = gVar.f589d;
                C0150f[] fVarArr = gVar.f591f;
                int i3 = fVarArr[i].f578a;
                BBString.C0678i[] iVarArr = pVar.f2211R.f2307a[i2].f2313a;
                BBString.C0678i iVar = iVarArr[i3];
                if (iVar.f2295b <= fVarArr[i].f583f - 1) {
                    this.f555b.m940h(gVar.f588c, gVar.f590e, i2);
                    C0149e eVar2 = this.f555b;
                    C0151g gVar2 = eVar2.f571a;
                    int i4 = iVar.f2295b;
                    C0150f[] fVarArr2 = gVar.f591f;
                    if (!eVar2.m943e(gVar2, i, i4, fVarArr2[i].f583f - 1, i3, iVar.f2297d, fVarArr2[i].f579b, iVar.f2298e, iVar.f2300g)) {
                        this.f555b.m937k(false);
                        return;
                    }
                    return;
                }
                int i5 = gVar.f588c;
                while (i5 >= 0) {
                    int j = oVarArr[i5].m444j();
                    if (j == 130) {
                        if (oVarArr[i5].m442l()) {
                            i2 = oVarArr[i5].f2330g;
                            iVarArr = pVar.f2211R.f2307a[i2].f2313a;
                        }
                    } else if (j == 129 && oVarArr[i5].m442l()) {
                        iVarArr = null;
                    }
                    if (oVarArr[i5].m449e()) {
                        if (j == 130) {
                            i5 = oVarArr[i5].f2328e;
                        }
                    } else if (j == 3 || j == 5 || j == 8) {
                        if (iVarArr != null && oVarArr[i5].f2335l >= f2) {
                            int i6 = oVarArr[i5].f2330g;
                            int i7 = iVarArr[i6].f2294a;
                            int i8 = i5 == gVar.f588c ? i - 1 : i7 - i6;
                            int i9 = i6 + i8;
                            int i10 = i8;
                            while (i9 >= i6) {
                                BBString.C0678i iVar2 = iVarArr[i9];
                                float f3 = iVar2.f2298e;
                                if (f3 > f2 || (f3 <= f2 && iVar2.f2300g >= f2 && iVar2.f2299f > f)) {
                                    this.f555b.m940h(i5, (i7 - i6) + 1, i2);
                                    C0149e eVar3 = this.f555b;
                                    C0151g gVar3 = eVar3.f571a;
                                    int i11 = iVar2.f2295b;
                                    if (!eVar3.m943e(gVar3, i10, i11, (iVar2.f2296c + i11) - 1, i9, iVar2.f2297d, iVar2.f2299f, iVar2.f2298e, iVar2.f2300g)) {
                                        this.f555b.m937k(false);
                                        return;
                                    }
                                    return;
                                }
                                i9--;
                                i10--;
                            }
                            continue;
                        } else {
                            return;
                        }
                    }
                    i5--;
                }
            }
        }
    }

    protected void m957c(float f, float f2) {
        BBString pVar;
        BBDisplay bBDisplay = this.f554a;
        if (bBDisplay != null && (pVar = bBDisplay.f527d) != null) {
            C0149e eVar = this.f555b;
            if (eVar.f573c) {
                BBString.C0685o[] oVarArr = pVar.f2220a;
                C0151g gVar = eVar.f572b;
                int j = eVar.m938j();
                int i = gVar.f589d;
                C0150f[] fVarArr = gVar.f591f;
                int i2 = fVarArr[j].f578a;
                BBString.C0678i[] iVarArr = pVar.f2211R.f2307a[i].f2313a;
                BBString.C0678i iVar = iVarArr[i2];
                if (iVar.f2295b + iVar.f2296c > fVarArr[j].f584g + 1) {
                    this.f555b.m940h(gVar.f588c, gVar.f590e, i);
                    C0149e eVar2 = this.f555b;
                    C0151g gVar2 = eVar2.f571a;
                    C0150f[] fVarArr2 = gVar.f591f;
                    if (!eVar2.m943e(gVar2, j, fVarArr2[j].f584g + 1, (iVar.f2295b + iVar.f2296c) - 1, i2, fVarArr2[j].f580c, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                        this.f555b.m937k(false);
                        return;
                    }
                    return;
                }
                int i3 = gVar.f588c;
                while (i3 < pVar.f2222b) {
                    int j2 = oVarArr[i3].m444j();
                    if (j2 == 129) {
                        if (oVarArr[i3].m442l()) {
                            i = oVarArr[i3].f2330g;
                            iVarArr = pVar.f2211R.f2307a[i].f2313a;
                        }
                    } else if (j2 == 130 && oVarArr[i3].m442l()) {
                        iVarArr = null;
                    }
                    if (oVarArr[i3].m449e()) {
                        if (j2 == 129) {
                            i3 = oVarArr[i3].f2328e;
                        }
                    } else if (j2 == 3 || j2 == 5 || j2 == 8) {
                        if (iVarArr != null && oVarArr[i3].f2333j <= f2) {
                            int i4 = oVarArr[i3].f2330g;
                            int i5 = iVarArr[i4].f2294a;
                            int i6 = i3 == gVar.f588c ? j + 1 : 0;
                            int i7 = i4 + i6;
                            int i8 = i6;
                            while (i7 <= i5) {
                                BBString.C0678i iVar2 = iVarArr[i7];
                                float f3 = iVar2.f2300g;
                                if (f3 < f2 || (iVar2.f2298e <= f2 && f3 >= f2 && iVar2.f2297d <= f)) {
                                    this.f555b.m940h(i3, (i5 - i4) + 1, i);
                                    C0149e eVar3 = this.f555b;
                                    C0151g gVar3 = eVar3.f571a;
                                    int i9 = iVar2.f2295b;
                                    if (!eVar3.m943e(gVar3, i8, i9, (iVar2.f2296c + i9) - 1, i7, iVar2.f2297d, iVar2.f2299f, iVar2.f2298e, iVar2.f2300g)) {
                                        this.f555b.m937k(false);
                                        return;
                                    }
                                    return;
                                }
                                i7++;
                                i8++;
                            }
                            continue;
                        } else {
                            return;
                        }
                    }
                    i3++;
                }
            }
        }
    }

    boolean m956d(float f, float f2) {
        BBString pVar;
        float f3;
        int i;
        float f4;
        int i2;
        int f5;
        int i3;
        float f6;
        C0149e eVar;
        int i4;
        BBDisplay bBDisplay = this.f554a;
        boolean z = false;
        if (bBDisplay == null || (pVar = bBDisplay.f527d) == null) {
            return false;
        }
        BBString.C0685o[] oVarArr = pVar.f2220a;
        int i5 = -1;
        int i6 = 0;
        BBString.C0678i[] iVarArr = null;
        while (i6 < pVar.f2222b) {
            int j = oVarArr[i6].m444j();
            if (j == 129) {
                if (oVarArr[i6].m442l()) {
                    i5 = oVarArr[i6].f2330g;
                    iVarArr = pVar.f2211R.f2307a[i5].f2313a;
                }
            } else if (j == 130 && oVarArr[i6].m442l()) {
                iVarArr = null;
            }
            if (oVarArr[i6].m449e()) {
                if (j == 129) {
                    i6 = oVarArr[i6].f2328e;
                }
            } else if (oVarArr[i6].f2332i <= f && oVarArr[i6].f2334k > f && oVarArr[i6].f2333j <= f2 && oVarArr[i6].f2335l > f2) {
                int i7 = 3;
                if (j == 3 || j == 5 || j == 8) {
                    if (iVarArr == null) {
                        return false;
                    }
                    this.f554a.m963r(oVarArr[i6].f2327d, oVarArr[i6].f2324a, z);
                    int i8 = oVarArr[i6].f2330g;
                    int i9 = iVarArr[i8].f2294a;
                    int i10 = i8;
                    int i11 = 0;
                    while (i10 <= i9) {
                        BBString.C0678i iVar = iVarArr[i10];
                        int i12 = iVar.f2296c;
                        if (i12 <= 0 || iVar.f2297d > f || iVar.f2299f <= f || iVar.f2298e > f2 || iVar.f2300g <= f2) {
                            i10++;
                            i11++;
                            i7 = 3;
                        } else {
                            int i13 = iVar.f2295b;
                            float[] g = this.f554a.m974g(i12);
                            if (j == i7) {
                                this.f554a.f525b.getTextWidths(pVar.f2231k, i13, i12, g);
                            } else {
                                this.f554a.f525b.getTextWidths(pVar.f2205L[oVarArr[i6].f2328e], i13, i13 + i12, g);
                            }
                            float f7 = iVar.f2297d;
                            float f8 = f7;
                            int i14 = 0;
                            boolean z2 = false;
                            while (true) {
                                if (i14 >= i12) {
                                    break;
                                }
                                float f9 = g[i14];
                                f7 += f9;
                                boolean z3 = f7 >= f;
                                if (z3) {
                                    z2 = z3;
                                    break;
                                }
                                f8 += f9;
                                i14++;
                                i13++;
                                z2 = z3;
                            }
                            if (!z2) {
                                if (j == 8 && i13 == i12) {
                                    i13--;
                                } else {
                                    if (j == 3) {
                                        f3 = f7;
                                        i4 = Util.m668f(pVar.f2231k, this.f556c, i13, 1);
                                    } else {
                                        f3 = f7;
                                        i4 = Util.m669e(pVar.f2205L[oVarArr[i6].f2328e], this.f556c, i13, 1);
                                    }
                                    if (i13 == i4) {
                                        return false;
                                    }
                                    int i15 = j != 3 ? Util.m665i(pVar.f2231k, this.f556c, i13, i13 - iVar.f2295b) : Util.m666h(pVar.f2205L[oVarArr[i6].f2328e], this.f556c, i13, i13 - iVar.f2295b);
                                    int i16 = iVar.f2295b;
                                    i = i15 >= i16 ? i16 : i15 + 1;
                                    f4 = f3;
                                    for (i2 = i13; i2 >= i; i2--) {
                                        f4 -= g[i2 - iVar.f2295b];
                                    }
                                    f5 = j != 3 ? Util.m668f(pVar.f2231k, this.f556c, i13, iVar.f2296c - (i13 - iVar.f2295b)) : Util.m669e(pVar.f2205L[oVarArr[i6].f2328e], this.f556c, i13, iVar.f2296c - (i13 - iVar.f2295b));
                                    if (f5 < 0) {
                                        f5 = iVar.f2295b + iVar.f2296c;
                                    }
                                    i3 = f5 - 1;
                                    f6 = f8;
                                    while (i13 <= i3) {
                                        f6 += g[i13 - iVar.f2295b];
                                        i13++;
                                    }
                                    this.f555b.m940h(i6, (i9 - i8) + 1, i5);
                                    eVar = this.f555b;
                                    if (!eVar.m943e(eVar.f571a, i11, i, i3, i10, f4, f6, iVar.f2298e, iVar.f2300g)) {
                                        this.f555b.m937k(false);
                                    }
                                    return this.f555b.f573c;
                                }
                            }
                            this.f555b.m940h(i6, (i9 - i8) + 1, i5);
                            return this.f555b.f573c;
                        }
                    }
                    continue;
                }
            } else if (j == 129) {
                i6 = oVarArr[i6].f2328e;
            }
            i6++;
            z = false;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction() & -65281;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.f557d = x;
            this.f558e = y;
        }
        if (this.f555b.f573c && action == 0) {
            this.f559f = true;
        }
        if (!this.f559f) {
            if (action == 0) {
                for (int i = 0; i < this.f554a.f527d.f2202I.size(); i++) {
                    BBString.C0681k kVar = this.f554a.f527d.f2202I.get(i);
                    int i2 = kVar.f2318c;
                    if (i2 >= 0) {
                        BBString.C0678i[] iVarArr = null;
                        while (i2 <= kVar.f2319d) {
                            BBString.C0685o oVar = this.f554a.f527d.f2220a[i2];
                            if (3 == oVar.m444j() && !oVar.m449e()) {
                                float f = this.f557d;
                                if (f >= oVar.f2332i && f <= oVar.f2334k) {
                                    float f2 = this.f558e;
                                    if (f2 >= oVar.f2333j && f2 <= oVar.f2335l) {
                                        if (iVarArr == null) {
                                            int i3 = i2;
                                            while (true) {
                                                if (i3 < 0) {
                                                    break;
                                                }
                                                BBString.C0685o oVar2 = this.f554a.f527d.f2220a[i3];
                                                if (129 == oVar2.m444j() && oVar2.m442l()) {
                                                    iVarArr = this.f554a.f527d.f2211R.f2307a[oVar2.f2330g].f2313a;
                                                    break;
                                                }
                                                i3--;
                                            }
                                        }
                                        if (iVarArr != null) {
                                            int i4 = oVar.f2330g;
                                            int i5 = iVarArr[i4].f2294a;
                                            while (true) {
                                                if (i4 > i5) {
                                                    break;
                                                }
                                                BBString.C0678i iVar = iVarArr[i4];
                                                float f3 = this.f557d;
                                                if (f3 >= iVar.f2297d && f3 <= iVar.f2299f) {
                                                    float f4 = this.f558e;
                                                    if (f4 >= iVar.f2298e && f4 <= iVar.f2300g) {
                                                        this.f561h = i;
                                                        invalidate();
                                                        break;
                                                    }
                                                }
                                                i4++;
                                            }
                                            if (i4 <= i5) {
                                                break;
                                            }
                                        } else {
                                            continue;
                                        }
                                        i2++;
                                    }
                                }
                            }
                            iVarArr = null;
                            i2++;
                        }
                        if (i2 <= kVar.f2319d) {
                            break;
                        }
                    }
                }
            } else if (this.f561h > -1 && (1 == action || 3 == action)) {
                this.f561h = -1;
                invalidate();
            }
        }
        if (this.f559f) {
            if (action == 0) {
                int i6 = (int) x;
                int i7 = (int) y;
                float d = C0152h.f593s.m933d(i6, i7);
                float d2 = C0152h.f594t.m933d(i6, i7);
                if (d <= 1.0f || d2 <= 1.0f) {
                    if (d < d2) {
                        C0152h.f593s.f613r = true;
                    } else {
                        C0152h.f594t.f613r = true;
                    }
                    setClickable(false);
                    getParent().requestDisallowInterceptTouchEvent(true);
                    this.f560g = true;
                    z = true;
                }
                z = false;
            } else {
                boolean z2 = this.f560g;
                if (!z2 || 2 != action) {
                    if (z2 && 1 == action) {
                        C0152h.C0153a aVar = C0152h.f595u;
                        C0152h hVar = C0152h.f593s;
                        aVar.f625l = hVar.f613r;
                        hVar.f613r = false;
                        C0152h.f594t.f613r = false;
                        C0149e eVar = this.f555b;
                        if (eVar.f574d) {
                            eVar.m937k(false);
                        }
                        if (this.f555b.f573c) {
                            invalidate();
                        }
                        setClickable(true);
                        this.f560g = false;
                        z = true;
                    }
                    z = false;
                } else {
                    Point point = new Point();
                    point.x = (int) x;
                    point.y = (int) y;
                    C0152h hVar2 = C0152h.f593s;
                    if (hVar2.f613r) {
                        hVar2.m932e(point);
                        C0152h.f594t.m931f();
                        if (!(!this.f555b.f574d ? m953g((float) point.x, (float) point.y) : !m952h((float) point.x, (float) point.y))) {
                            C0149e eVar2 = this.f555b;
                            if (!eVar2.f574d && !eVar2.f576f) {
                                eVar2.f574d = true;
                                C0152h.m934c();
                            }
                        } else if (!this.f555b.f574d) {
                          //  C0152h.f593s.f612q = m955e((float) point.x, (float) point.y);
                        } else {
                            m958b((float) point.x, (float) point.y);
                            //C0152h.f593s.f612q = m955e((float) point.x, (float) point.y);
                            C0149e eVar3 = this.f555b;
                            if (!eVar3.f573c) {
                                eVar3.m937k(true);
                            }
                        }
                    } else {
                        C0152h hVar3 = C0152h.f594t;
                        if (hVar3.f613r) {
                            hVar3.m932e(point);
                            C0152h.f593s.m931f();
                            if (!(!this.f555b.f574d ? m952h((float) point.x, (float) point.y) : !m953g((float) point.x, (float) point.y))) {
                                C0149e eVar4 = this.f555b;
                                if (!eVar4.f574d && !eVar4.f575e) {
                                    eVar4.f574d = true;
                                    C0152h.m934c();
                                }
                            } else if (!this.f555b.f574d) {
                              //  C0152h.f594t.f612q = m954f((float) point.x, (float) point.y);
                            } else {
                                m957c((float) point.x, (float) point.y);
                             //   C0152h.f594t.f612q = m954f((float) point.x, (float) point.y);
                            }
                        }
                    }
                    if (this.f555b.f573c) {
                        invalidate();
                        z = true;
                    }
                    z = false;
                }
            }
            if (!this.f560g) {
                z = super.dispatchTouchEvent(motionEvent);
            }
        } else {
            z = this.f554a.dispatchTouchEvent(motionEvent);
        }
        if (!this.f555b.f573c && 1 == action) {
            this.f559f = false;
        }
        return z;
    }

   /* protected boolean m955e(float f, float f2) {
        BBString pVar;
        BBString.C0685o[] oVarArr;
        BBString.C0678i[] iVarArr;
        BBString pVar2;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        float[] fArr;
        C0151g gVar;
        BBDisplay bBDisplay = this.f554a;
        ?? r2 = 0;
        if (!(bBDisplay == null || (pVar = bBDisplay.f527d) == null)) {
            C0149e eVar = this.f555b;
            if (eVar.f573c) {
                BBString.C0685o[] oVarArr2 = pVar.f2220a;
                C0151g gVar2 = eVar.f572b;
                int i5 = gVar2.f589d;
                BBString.C0678i[] iVarArr2 = pVar.f2211R.f2307a[i5].f2313a;
                int i6 = gVar2.f588c;
                ?? r8 = 1;
                C0151g gVar3 = null;
                boolean z2 = true;
                loop0: while (true) {
                    if (i6 < 0) {
                        break;
                    }
                    int j = oVarArr2[i6].m444j();
                    if (j == 130) {
                        if (oVarArr2[i6].m442l()) {
                            i5 = oVarArr2[i6].f2330g;
                            iVarArr2 = pVar.f2211R.f2307a[i5].f2313a;
                        }
                    } else if (j == 129 && oVarArr2[i6].m442l()) {
                        iVarArr2 = null;
                    }
                    if (!oVarArr2[i6].m449e()) {
                        if (j == 7 && oVarArr2[i6].m443k(8388608) && oVarArr2[i6].f2335l > f2) {
                            if (oVarArr2[i6].f2333j > f2) {
                                gVar3 = this.f555b.m947a(i6, r8, i5);
                                gVar3.f592g = r8;
                                C0150f[] fVarArr = gVar3.f591f;
                                if (fVarArr[r2] == null) {
                                    fVarArr[r2] = new C0150f();
                                }
                                fVarArr[r2].f579b = oVarArr2[i6].f2332i;
                                fVarArr[r2].f580c = oVarArr2[i6].f2334k;
                                fVarArr[r2].f581d = oVarArr2[i6].f2333j;
                                fVarArr[r2].f582e = oVarArr2[i6].f2335l;
                                fVarArr[r2].f585h = r8;
                            } else if (oVarArr2[i6].f2334k > f) {
                                C0149e eVar2 = this.f555b;
                                int i7 = r8 == true ? 1 : 0;
                                int i8 = r8 == true ? 1 : 0;
                                int i9 = r8 == true ? 1 : 0;
                                int i10 = r8 == true ? 1 : 0;
                                int i11 = r8 == true ? 1 : 0;
                                int i12 = r8 == true ? 1 : 0;
                                int i13 = r8 == true ? 1 : 0;
                                int i14 = r8 == true ? 1 : 0;
                                int i15 = r8 == true ? 1 : 0;
                                int i16 = r8 == true ? 1 : 0;
                                gVar3 = eVar2.m947a(i6, i7, i5);
                                gVar3.f592g = r8;
                                C0150f[] fVarArr2 = gVar3.f591f;
                                char c = r2 == true ? 1 : 0;
                                char c2 = r2 == true ? 1 : 0;
                                char c3 = r2 == true ? 1 : 0;
                                char c4 = r2 == true ? 1 : 0;
                                if (fVarArr2[c] == null) {
                                    fVarArr2[r2] = new C0150f();
                                }
                                fVarArr2[r2].f579b = oVarArr2[i6].f2332i;
                                fVarArr2[r2].f580c = oVarArr2[i6].f2334k;
                                fVarArr2[r2].f581d = oVarArr2[i6].f2333j;
                                fVarArr2[r2].f582e = oVarArr2[i6].f2335l;
                                fVarArr2[r2].f585h = r8;
                            }
                        }
                        int i17 = 8;
                        if (j == 3 || j == 5 || j == 8) {
                            if (oVarArr2[i6].f2335l < f2) {
                                z2 = false;
                                break;
                            } else if (iVarArr2 == null) {
                                this.f555b.m937k(r2);
                                return r2;
                            } else {
                                int i18 = oVarArr2[i6].f2330g;
                                int i19 = iVarArr2[i18].f2294a;
                                int i20 = i19 - i18;
                                int i21 = i20;
                                boolean z3 = r2;
                                r8 = r8;
                                while (i19 >= i18) {
                                    BBString.C0678i iVar = iVarArr2[i19];
                                    float f3 = iVar.f2298e;
                                    if (f3 > f2 || (f3 <= f2 && iVar.f2300g > f2 && iVar.f2297d > f)) {
                                        oVarArr = oVarArr2;
                                        iVarArr = iVarArr2;
                                        i = i21;
                                        pVar2 = pVar;
                                        gVar3 = this.f555b.m947a(i6, i20 + 1, i5);
                                        C0149e eVar3 = this.f555b;
                                        if (gVar3 == eVar3.f572b) {
                                            int j2 = eVar3.m938j();
                                            if (j2 == -1) {
                                                this.f555b.m937k(false);
                                                return false;
                                            } else if (i == j2 && !this.f555b.m942f(gVar3, i, iVar.f2295b, iVar.f2297d)) {
                                                this.f555b.m937k(false);
                                                return false;
                                            } else if (i >= j2) {
                                                z = true;
                                                i2 = -1;
                                            }
                                        }
                                        C0149e eVar4 = this.f555b;
                                        int i22 = iVar.f2295b;
                                        z = true;
                                        if (!eVar4.m943e(gVar3, i, i22, (iVar.f2296c + i22) - 1, i19, iVar.f2297d, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                            this.f555b.m937k(false);
                                            return false;
                                        }
                                        i2 = -1;
                                    } else if (iVar.f2297d > f || iVar.f2299f <= f || f3 > f2 || iVar.f2300g <= f2) {
                                        oVarArr = oVarArr2;
                                        iVarArr = iVarArr2;
                                        i = i21;
                                        pVar2 = pVar;
                                        if (gVar3 == null) {
                                            this.f555b.m937k(z3);
                                            return z3;
                                        }
                                        if (gVar3.f588c == i6) {
                                            C0150f[] fVarArr3 = gVar3.f591f;
                                            if (fVarArr3[i] != null) {
                                                fVarArr3[i].f585h = z3;
                                            }
                                        }
                                        i2 = -1;
                                        z = true;
                                        z2 = false;
                                    } else {
                                        if (j != i17) {
                                            gVar3 = this.f555b.m947a(i6, i20 + 1, i5);
                                        }
                                        if (iVar.f2296c > 0) {
                                            this.f554a.m963r(oVarArr2[i6].f2327d, oVarArr2[i6].f2324a, z3);
                                            int i23 = iVar.f2295b;
                                            int i24 = iVar.f2296c;
                                            int i25 = (i23 + i24) - 1;
                                            if (j == 3) {
                                                fArr = this.f554a.m974g(i24);
                                                iVarArr = iVarArr2;
                                                i4 = i25;
                                                this.f554a.f525b.getTextWidths(pVar.f2231k, iVar.f2295b, i24, fArr);
                                                oVarArr = oVarArr2;
                                            } else {
                                                iVarArr = iVarArr2;
                                                i4 = i25;
                                                fArr = this.f554a.m974g(i24);
                                                Paint paint = this.f554a.f525b;
                                                String str = pVar.f2205L[oVarArr2[i6].f2328e];
                                                int i26 = iVar.f2295b;
                                                oVarArr = oVarArr2;
                                                paint.getTextWidths(str, i26, i26 + i24, fArr);
                                            }
                                            float f4 = iVar.f2299f;
                                            if (j == 8) {
                                                f4 -= pVar.f2201H.f2323v;
                                            }
                                            int i27 = i24 - 1;
                                            int i28 = i4;
                                            boolean z4 = false;
                                            float f5 = f4;
                                            while (true) {
                                                if (i27 < 0) {
                                                    break;
                                                }
                                                float f6 = fArr[i27];
                                                f4 -= f6;
                                                boolean z5 = f4 <= f && f5 >= f;
                                                if (z5) {
                                                    z4 = z5;
                                                    break;
                                                }
                                                f5 -= f6;
                                                i27--;
                                                i28--;
                                                z4 = z5;
                                            }
                                            if (z4) {
                                                if (j == 8) {
                                                    gVar3 = this.f555b.m947a(i6, i20 + 1, i5);
                                                }
                                                C0149e eVar5 = this.f555b;
                                                if (gVar3 == eVar5.f572b) {
                                                    int j3 = eVar5.m938j();
                                                    if (j3 == -1) {
                                                        this.f555b.m937k(false);
                                                        return false;
                                                    }
                                                    i3 = i21;
                                                    if (i3 == j3) {
                                                        if (!this.f555b.m942f(gVar3, i3, i28, f4)) {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                        pVar2 = pVar;
                                                    } else if (i3 < j3) {
                                                        pVar2 = pVar;
                                                        if (!this.f555b.m943e(gVar3, i3, i28, (iVar.f2295b + i24) - 1, i19, f4, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                    } else {
                                                        this.f555b.m937k(false);
                                                        return false;
                                                    }
                                                } else {
                                                    i3 = i21;
                                                    pVar2 = pVar;
                                                    if (!eVar5.m943e(gVar3, i3, i28, (iVar.f2295b + i24) - 1, i19, f4, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                                        this.f555b.m937k(false);
                                                        return false;
                                                    }
                                                }
                                                gVar = gVar3;
                                                if (i28 > iVar.f2295b) {
                                                    z2 = false;
                                                }
                                            } else if (j != 8 || i28 != -1) {
                                                break loop0;
                                            } else {
                                                gVar = this.f555b.f571a;
                                                if (gVar.f588c == i6) {
                                                    gVar = gVar.f587b;
                                                }
                                                i3 = i21;
                                                pVar2 = pVar;
                                            }
                                            gVar3 = gVar;
                                        } else {
                                            oVarArr = oVarArr2;
                                            iVarArr = iVarArr2;
                                            i3 = i21;
                                            pVar2 = pVar;
                                            if (!this.f555b.m943e(gVar3, i3, iVar.f2295b, -1, i19, iVar.f2297d, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                                this.f555b.m937k(false);
                                                return false;
                                            }
                                        }
                                        int i29 = i3 - 1;
                                        i19--;
                                        while (i19 >= i18) {
                                            C0150f[] fVarArr4 = gVar3.f591f;
                                            if (fVarArr4[i29] != null) {
                                                fVarArr4[i29].f585h = false;
                                            }
                                            i19--;
                                            i29--;
                                        }
                                        if (this.f555b.m938j() == -1) {
                                            this.f555b.m937k(false);
                                            return false;
                                        }
                                        i = i29;
                                        i2 = -1;
                                        z = true;
                                    }
                                    i19 += i2;
                                    i21 = i - 1;
                                    pVar = pVar2;
                                    iVarArr2 = iVarArr;
                                    oVarArr2 = oVarArr;
                                    i17 = 8;
                                    z3 = false;
                                    r8 = z;
                                }
                            }
                        }
                    } else if (j == 130) {
                        i6 = oVarArr2[i6].f2328e;
                    }
                    i6--;
                    pVar = pVar;
                    iVarArr2 = iVarArr2;
                    oVarArr2 = oVarArr2;
                    r2 = 0;
                    r8 = r8;
                }
                this.f555b.m937k(false);
                return false;
            }
        }
        return false;
    }

    protected boolean m954f(float f, float f2) {
        BBString pVar;
        BBString pVar2;
        BBString.C0685o[] oVarArr;
        BBString.C0678i[] iVarArr;
        int i;
        BBString pVar3;
        BBString.C0678i[] iVarArr2;
        BBString.C0685o[] oVarArr2;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        float[] fArr;
        BBDisplay bBDisplay = this.f554a;
        int r2 = 0;
        if (!(bBDisplay == null || (pVar = bBDisplay.f527d) == null)) {
            C0149e eVar = this.f555b;
            if (eVar.f573c) {
                BBString.C0685o[] oVarArr3 = pVar.f2220a;
                C0151g gVar = eVar.f571a;
                int i6 = gVar.f589d;
                BBString.C0678i[] iVarArr3 = pVar.f2211R.f2307a[i6].f2313a;
                int i7 = gVar.f588c;
                boolean r8 = true;
                C0151g gVar2 = null;
                boolean z2 = true;
                loop0: while (true) {
                    if (i7 >= pVar.f2222b) {
                        break;
                    }
                    int j = oVarArr3[i7].m444j();
                    if (j == 129) {
                        if (oVarArr3[i7].m442l()) {
                            i6 = oVarArr3[i7].f2330g;
                            iVarArr3 = pVar.f2211R.f2307a[i6].f2313a;
                        }
                    } else if (j == 130 && oVarArr3[i7].m442l()) {
                        iVarArr3 = null;
                    }
                    if (!oVarArr3[i7].m449e()) {
                        if (j == 7 && oVarArr3[i7].m443k(8388608) && oVarArr3[i7].f2333j <= f2) {
                            if (oVarArr3[i7].f2335l <= f2) {
                                gVar2 = this.f555b.m946b(i7, r8, i6);
                                gVar2.f592g = r8;
                                C0150f[] fVarArr = gVar2.f591f;
                                if (fVarArr[r2] == null) {
                                    fVarArr[r2] = new C0150f();
                                }
                                fVarArr[r2].f579b = oVarArr3[i7].f2332i;
                                fVarArr[r2].f580c = oVarArr3[i7].f2334k;
                                fVarArr[r2].f581d = oVarArr3[i7].f2333j;
                                fVarArr[r2].f582e = oVarArr3[i7].f2335l;
                                fVarArr[r2].f585h = r8;
                            } else if (oVarArr3[i7].f2332i <= f) {
                                C0149e eVar2 = this.f555b;
                                int i8 = r8 == true ? 1 : 0;
                                int i9 = r8 == true ? 1 : 0;
                                int i10 = r8 == true ? 1 : 0;
                                gVar2 = eVar2.m946b(i7, i8, i6);
                                gVar2.f592g = r8;
                                C0150f[] fVarArr2 = gVar2.f591f;
                                char c = r2 == true ? 1 : 0;
                                char c2 = r2 == true ? 1 : 0;
                                char c3 = r2 == true ? 1 : 0;
                                if (fVarArr2[c] == null) {
                                    fVarArr2[r2] = new C0150f();
                                }
                                fVarArr2[r2].f579b = oVarArr3[i7].f2332i;
                                fVarArr2[r2].f580c = oVarArr3[i7].f2334k;
                                fVarArr2[r2].f581d = oVarArr3[i7].f2333j;
                                fVarArr2[r2].f582e = oVarArr3[i7].f2335l;
                                fVarArr2[r2].f585h = r8;
                            }
                        }
                        if (j == 3 || j == 5 || j == 8) {
                            if (oVarArr3[i7].f2333j > f2) {
                                z2 = false;
                                break;
                            } else if (iVarArr3 == null) {
                                this.f555b.m937k(r2);
                                return r2;
                            } else {
                                int i11 = oVarArr3[i7].f2330g;
                                int i12 = iVarArr3[i11].f2294a;
                                int i13 = i11;
                                int i14 = 0;
                                while (i13 <= i12) {
                                    BBString.C0678i iVar = iVarArr3[i13];
                                    float f3 = iVar.f2300g;
                                    if (f3 > f2) {
                                        float f4 = iVar.f2298e;
                                        if (f4 > f2 || f3 <= f2 || iVar.f2299f > f) {
                                            if (iVar.f2297d > f || iVar.f2299f <= f || f4 > f2 || f3 <= f2) {
                                                pVar3 = pVar;
                                                iVarArr2 = iVarArr3;
                                                i = j;
                                                oVarArr2 = oVarArr3;
                                                i2 = i14;
                                                if (gVar2 == null) {
                                                    this.f555b.m937k(false);
                                                    return false;
                                                }
                                                if (gVar2.f588c == i7) {
                                                    C0150f[] fVarArr3 = gVar2.f591f;
                                                    if (fVarArr3[i2] != null) {
                                                        fVarArr3[i2].f585h = false;
                                                    }
                                                }
                                                i6 = i6;
                                                i3 = 1;
                                                z2 = false;
                                                i13 += i3;
                                                i14 = i2 + 1;
                                                oVarArr3 = oVarArr2;
                                                iVarArr3 = iVarArr2;
                                                pVar = pVar3;
                                                j = i;
                                            } else {
                                                gVar2 = this.f555b.m946b(i7, (i12 - i11) + 1, i6);
                                                if (iVar.f2296c > 0) {
                                                    iVarArr2 = iVarArr3;
                                                    this.f554a.m963r(oVarArr3[i7].f2327d, oVarArr3[i7].f2324a, false);
                                                    int i15 = iVar.f2295b;
                                                    int i16 = iVar.f2296c;
                                                    if (j == 3) {
                                                        fArr = this.f554a.m974g(i16);
                                                        z = z2;
                                                        this.f554a.f525b.getTextWidths(pVar.f2231k, i15, i16, fArr);
                                                        pVar3 = pVar;
                                                    } else {
                                                        z = z2;
                                                        fArr = this.f554a.m974g(i16);
                                                        pVar3 = pVar;
                                                        this.f554a.f525b.getTextWidths(pVar.f2205L[oVarArr3[i7].f2328e], i15, i15 + i16, fArr);
                                                    }
                                                    float f5 = iVar.f2297d;
                                                    int i17 = 0;
                                                    boolean z3 = false;
                                                    while (i17 < i16) {
                                                        f5 += fArr[i17];
                                                        z3 = f5 > f;
                                                        if (z3) {
                                                            break;
                                                        }
                                                        i17++;
                                                        i15++;
                                                    }
                                                    if (!z3) {
                                                        if (j != 8 || i15 != i16) {
                                                            break loop0;
                                                        }
                                                        i15--;
                                                    }
                                                    C0149e eVar3 = this.f555b;
                                                    if (gVar2 == eVar3.f571a) {
                                                        int i18 = eVar3.m939i();
                                                        if (i18 == -1) {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                        if (i14 == i18) {
                                                            if (!this.f555b.m941g(gVar2, i14, i15, f5)) {
                                                                this.f555b.m937k(false);
                                                                return false;
                                                            }
                                                            oVarArr2 = oVarArr3;
                                                            i4 = i6;
                                                            i = j;
                                                        } else if (i14 > i18) {
                                                            oVarArr2 = oVarArr3;
                                                            i = j;
                                                            i4 = i6;
                                                            if (!this.f555b.m943e(gVar2, i14, iVar.f2295b, i15, i13, iVar.f2297d, f5, iVar.f2298e, iVar.f2300g)) {
                                                                this.f555b.m937k(false);
                                                                return false;
                                                            }
                                                        } else {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                        i5 = i14;
                                                    } else {
                                                        i4 = i6;
                                                        i = j;
                                                        oVarArr2 = oVarArr3;
                                                        i5 = i14;
                                                        if (!eVar3.m943e(gVar2, i5, iVar.f2295b, i15, i13, iVar.f2297d, f5, iVar.f2298e, iVar.f2300g)) {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                    }
                                                    if (i15 - iVar.f2295b < i16 - 1) {
                                                        z2 = false;
                                                        int i19 = i5 + 1;
                                                        i13++;
                                                        while (i13 <= i12) {
                                                            C0150f[] fVarArr4 = gVar2.f591f;
                                                            if (fVarArr4[i19] != null) {
                                                                fVarArr4[i19].f585h = false;
                                                            }
                                                            i13++;
                                                            i19++;
                                                        }
                                                        if (this.f555b.m939i() != -1) {
                                                            this.f555b.m937k(false);
                                                            return false;
                                                        }
                                                        i2 = i19;
                                                        i6 = i4;
                                                        i3 = 1;
                                                        i13 += i3;
                                                        i14 = i2 + 1;
                                                        oVarArr3 = oVarArr2;
                                                        iVarArr3 = iVarArr2;
                                                        pVar = pVar3;
                                                        j = i;
                                                    }
                                                } else {
                                                    pVar3 = pVar;
                                                    i4 = i6;
                                                    iVarArr2 = iVarArr3;
                                                    z = z2;
                                                    i = j;
                                                    oVarArr2 = oVarArr3;
                                                    i5 = i14;
                                                    if (!this.f555b.m943e(gVar2, i5, iVar.f2295b, -1, i13, iVar.f2297d, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                                        this.f555b.m937k(false);
                                                        return false;
                                                    }
                                                }
                                                z2 = z;
                                                int i192 = i5 + 1;
                                                i13++;
                                                while (i13 <= i12) {
                                                }
                                                if (this.f555b.m939i() != -1) {
                                                }
                                            }
                                        }
                                    }
                                    pVar3 = pVar;
                                    iVarArr2 = iVarArr3;
                                    i = j;
                                    oVarArr2 = oVarArr3;
                                    i2 = i14;
                                    i6 = i6;
                                    gVar2 = this.f555b.m946b(i7, (i12 - i11) + 1, i6);
                                    C0149e eVar4 = this.f555b;
                                    if (gVar2 == eVar4.f571a) {
                                        int i20 = eVar4.m939i();
                                        if (i20 == -1) {
                                            this.f555b.m937k(false);
                                            return false;
                                        } else if (i2 == i20 && !this.f555b.m941g(gVar2, i2, (iVar.f2295b + iVar.f2296c) - 1, iVar.f2299f)) {
                                            this.f555b.m937k(false);
                                            return false;
                                        }
                                    }
                                    C0149e eVar5 = this.f555b;
                                    int i21 = iVar.f2295b;
                                    if (!eVar5.m943e(gVar2, i2, i21, (iVar.f2296c + i21) - 1, i13, iVar.f2297d, iVar.f2299f, iVar.f2298e, iVar.f2300g)) {
                                        this.f555b.m937k(false);
                                        return false;
                                    }
                                    z2 = z2;
                                    i3 = 1;
                                    i13 += i3;
                                    i14 = i2 + 1;
                                    oVarArr3 = oVarArr2;
                                    iVarArr3 = iVarArr2;
                                    pVar = pVar3;
                                    j = i;
                                }
                                pVar2 = pVar;
                                oVarArr = oVarArr3;
                                iVarArr = iVarArr3;
                                i7++;
                                oVarArr3 = oVarArr;
                                iVarArr3 = iVarArr;
                                pVar = pVar2;
                                r2 = 0;
                                r8 = 1;
                            }
                        }
                    } else if (j == 129) {
                        i7 = oVarArr3[i7].f2328e;
                    }
                    pVar2 = pVar;
                    oVarArr = oVarArr3;
                    iVarArr = iVarArr3;
                    i7++;
                    oVarArr3 = oVarArr;
                    iVarArr3 = iVarArr;
                    pVar = pVar2;
                    r2 = 0;
                    r8 = 1;
                }
            }
        }
        return false;
    }*/

    protected boolean m953g(float f, float f2) {
        C0149e eVar = this.f555b;
        if (!eVar.f573c) {
            return false;
        }
        C0150f fVar = this.f555b.f572b.f591f[eVar.m938j()];
        float f3 = fVar.f581d;
        if (f3 > f2 || (f3 <= f2 && fVar.f582e > f2 && fVar.f580c > f)) {
            return true;
        }
        return false;
    }

    protected boolean m952h(float f, float f2) {
        C0149e eVar = this.f555b;
        if (!eVar.f573c) {
            return false;
        }
        C0150f fVar = this.f555b.f571a.f591f[eVar.m939i()];
        float f3 = fVar.f582e;
        if (f3 <= f2 || (fVar.f581d <= f2 && f3 > f2 && fVar.f579b <= f)) {
            return true;
        }
        return false;
    }

    public void m951i(Context context) {
        setClickable(true);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.f554a.f531h;
            if (i < drawableArr.length) {
                if (drawableArr[i] == drawable) {
                    invalidate();
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void m950j(boolean z) {
        if (C0152h.f595u != null && C0152h.f593s != null && C0152h.f594t != null && m956d(this.f557d, this.f558e)) {
            if (z) {
                C0152h.f595u.m930a(new C0152h.C0153a.C0154a("Копировать", new View$OnClickListenerC0145a()), new C0152h.C0153a.C0154a("Цитировать", new View$OnClickListenerC0146b()));
            } else {
                C0152h.f595u.m930a(new C0152h.C0153a.C0154a("Копировать", new View$OnClickListenerC0147c()));
            }
            C0152h.f593s.m931f();
            C0152h.f594t.m931f();
        }
    }

    public void m949k() {
        this.f555b.m937k(false);
        invalidate();
    }

    public boolean m948l() {
        if (!m956d(this.f557d, this.f558e)) {
            return false;
        }
        this.f555b.m937k(false);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BBString pVar;
        int i;
        BBString.C0678i[] iVarArr;
        int i2;
        BBString.C0678i[] iVarArr2;
        int i3;
        int i4;
        BBDisplay bBDisplay = this.f554a;
        if (!(bBDisplay == null || bBDisplay.f527d == null)) {
            canvas.getClipBounds(bBDisplay.f526c);
            int i5 = 0;
            while (true) {
                BBDisplay bBDisplay2 = this.f554a;
                pVar = bBDisplay2.f527d;
                if (i5 >= pVar.f2210Q) {
                    break;
                }
                BBString.C0677h hVar = pVar.f2208O[i5];
                Drawable drawable = bBDisplay2.f531h[i5];
                if ((drawable instanceof PicoDrawable) && ((PicoDrawable) drawable).isAnimated()) {
                    int i6 = hVar.f2278b;
                    boolean e = this.f554a.f527d.f2220a[i6].m449e();
                    BBDisplay bBDisplay3 = this.f554a;
                    BBString.C0685o[] oVarArr = bBDisplay3.f527d.f2220a;
                    int i7 = oVarArr[i6].f2328e;
                    int i8 = hVar.f2277a;
                    if (!e) {
                        Rect rect = bBDisplay3.f526c;
                        if (((float) rect.top) <= oVarArr[i7].f2335l && ((float) rect.bottom) >= oVarArr[i6].f2333j) {
                            float f = oVarArr[i8].f2335l;
                            float f2 = hVar.f2284h;
                            bBDisplay3.f531h[i5].setBounds((int) oVarArr[i8].f2332i, (int) ((f - f2) - hVar.f2282f), (int) oVarArr[i8].f2334k, (int) (oVarArr[i8].f2335l - f2));
                            this.f554a.f531h[i5].draw(canvas);
                        }
                    }
                }
                i5++;
            }
            int i9 = this.f561h;
            int i10 = 100;
            boolean z = true;
            if (-1 < i9) {
                BBString.C0681k kVar = pVar.f2202I.get(i9);
                BBString.C0678i[] iVarArr3 = null;
                for (int i11 = kVar.f2318c; i11 <= kVar.f2319d; i11++) {
                    BBString.C0685o oVar = this.f554a.f527d.f2220a[i11];
                    if (!oVar.m449e()) {
                        if (3 == oVar.m444j()) {
                            if (iVarArr3 == null) {
                                for (int i12 = i11; i12 >= 0; i12--) {
                                    BBString.C0685o oVar2 = this.f554a.f527d.f2220a[i12];
                                    if (129 == oVar2.m444j() && oVar2.m442l()) {
                                        iVarArr2 = this.f554a.f527d.f2211R.f2307a[oVar2.f2330g].f2313a;
                                        break;
                                    }
                                }
                            }
                            iVarArr2 = iVarArr3;
                            if (iVarArr2 != null) {
                                float r = this.f554a.m963r(oVar.f2327d, oVar.f2324a, true);
                                int i13 = oVar.f2330g;
                                int i14 = iVarArr2[i13].f2294a;
                                int i15 = i13;
                                while (i15 <= i14) {
                                    BBString.C0678i iVar = iVarArr2[i15];
                                    float f3 = iVar.f2300g;
                                    BBDisplay bBDisplay4 = this.f554a;
                                    Rect rect2 = bBDisplay4.f526c;
                                    if (f3 < ((float) rect2.top) || iVar.f2298e >= ((float) rect2.bottom)) {
                                        i3 = i15;
                                    } else {
                                        bBDisplay4.f525b.setColor(f552l);
                                        i3 = i15;
                                        canvas.drawRect(iVar.f2297d, iVar.f2298e, iVar.f2299f, iVar.f2300g, this.f554a.f525b);
                                        if (iVar.f2296c > 0) {
                                            this.f554a.f525b.setColor(f551k);
                                            BBDisplay bBDisplay5 = this.f554a;
                                            i4 = i14;
                                            canvas.drawText(bBDisplay5.f527d.f2231k, iVar.f2295b, iVar.f2296c, iVar.f2297d, iVar.f2300g + iVar.f2301h + r, bBDisplay5.f525b);
                                            i15 = i3 + 1;
                                            i14 = i4;
                                        }
                                    }
                                    i4 = i14;
                                    i15 = i3 + 1;
                                    i14 = i4;
                                }
                            }
                            iVarArr3 = iVarArr2;
                        } else if (7 == oVar.m444j()) {
                            this.f554a.f525b.setColor(f552l);
                            this.f554a.f525b.setAlpha(100);
                            canvas.drawRect(oVar.f2332i, oVar.f2333j, oVar.f2334k, oVar.f2335l, this.f554a.f525b);
                        } else {
                            iVarArr3 = null;
                        }
                    }
                }
            }
            C0149e eVar = this.f555b;
            if (eVar.f573c) {
                C0148d dVar = new C0148d(this);
                if (eVar.m945c(dVar)) {
                    BBString pVar2 = this.f554a.f527d;
                    BBString.C0685o[] oVarArr2 = pVar2.f2220a;
                    C0151g gVar = this.f555b.f571a;
                    while (gVar != null) {
                        int i16 = gVar.f588c;
                        if (!oVarArr2[i16].m449e()) {
                            float f4 = oVarArr2[i16].f2335l;
                            BBDisplay bBDisplay6 = this.f554a;
                            Rect rect3 = bBDisplay6.f526c;
                            if (f4 >= ((float) rect3.top) && oVarArr2[i16].f2333j < ((float) rect3.bottom)) {
                                if (gVar.f592g) {
                                    bBDisplay6.f525b.setColor(f550j);
                                    this.f554a.f525b.setAlpha(i10);
                                    canvas.drawRect(oVarArr2[i16].f2332i, oVarArr2[i16].f2333j, oVarArr2[i16].f2334k, oVarArr2[i16].f2335l, this.f554a.f525b);
                                } else {
                                    BBString.C0678i[] iVarArr4 = pVar2.f2211R.f2307a[gVar.f589d].f2313a;
                                    float r2 = bBDisplay6.m963r(oVarArr2[i16].f2327d, oVarArr2[i16].f2324a, z);
                                    int i17 = 0;
                                    while (i17 < gVar.f590e) {
                                        C0150f fVar = gVar.f591f[i17];
                                        if (fVar != null && fVar.f585h) {
                                            float f5 = fVar.f582e;
                                            BBDisplay bBDisplay7 = this.f554a;
                                            Rect rect4 = bBDisplay7.f526c;
                                            if (f5 >= ((float) rect4.top) && fVar.f581d < ((float) rect4.bottom)) {
                                                bBDisplay7.f525b.setColor(f550j);
                                                i2 = i17;
                                                iVarArr = iVarArr4;
                                                canvas.drawRect(fVar.f579b, fVar.f581d, fVar.f580c, fVar.f582e, this.f554a.f525b);
                                                if (fVar.f584g >= 0) {
                                                    this.f554a.f525b.setColor(f549i);
                                                    if (oVarArr2[i16].m444j() == 3) {
                                                        char[] cArr = pVar2.f2231k;
                                                        int i18 = fVar.f583f;
                                                        i = i16;
                                                        canvas.drawText(cArr, i18, (fVar.f584g + 1) - i18, fVar.f579b, fVar.f582e + iVarArr[fVar.f578a].f2301h + r2, this.f554a.f525b);
                                                        i17 = i2 + 1;
                                                        iVarArr4 = iVarArr;
                                                        i16 = i;
                                                    } else {
                                                        i = i16;
                                                        canvas.drawText(pVar2.f2205L[oVarArr2[i].f2328e], fVar.f583f, fVar.f584g + 1, fVar.f579b, fVar.f582e + iVarArr[fVar.f578a].f2301h + r2, this.f554a.f525b);
                                                        i17 = i2 + 1;
                                                        iVarArr4 = iVarArr;
                                                        i16 = i;
                                                    }
                                                }
                                                i = i16;
                                                i17 = i2 + 1;
                                                iVarArr4 = iVarArr;
                                                i16 = i;
                                            }
                                        }
                                        i2 = i17;
                                        iVarArr = iVarArr4;
                                        i = i16;
                                        i17 = i2 + 1;
                                        iVarArr4 = iVarArr;
                                        i16 = i;
                                    }
                                }
                            }
                        }
                        gVar = gVar.f587b;
                        if (gVar == this.f555b.f571a) {
                            break;
                        }
                        i10 = 100;
                        z = true;
                    }
                }
                C0152h hVar2 = C0152h.f593s;
                if (!hVar2.f613r) {
                    hVar2.m935b(canvas, (int) dVar.f565a, (int) dVar.f566b);
                }
                C0152h.f594t.m935b(canvas, (int) dVar.f568d, (int) dVar.f569e);
                C0152h hVar3 = C0152h.f593s;
                if (hVar3.f613r) {
                    hVar3.m935b(canvas, (int) dVar.f565a, (int) dVar.f566b);
                }
                if (!(C0152h.f593s.f613r || C0152h.f594t.f613r || !this.f555b.m944d(dVar))) {
                    C0152h.f595u.m929b((int) dVar.f565a, (int) dVar.f566b, (int) dVar.f567c, (int) dVar.f568d, (int) dVar.f569e, (int) dVar.f570f, getWidth(), getHeight());
                    C0152h.f595u.m926e(canvas);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        BBDisplay bBDisplay = this.f554a;
        if (bBDisplay != null) {
            setMeasuredDimension(bBDisplay.getMeasuredWidth(), this.f554a.getMeasuredHeight());
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    @Override
    public boolean performClick() {
        C0152h.f595u.m927d((int) this.f557d, (int) this.f558e);
        this.f555b.m937k(false);
        this.f559f = false;
        return super.performClick();
    }

    @Override
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        getHandler().postAtTime(runnable, j);
    }

    @Override
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }
}
