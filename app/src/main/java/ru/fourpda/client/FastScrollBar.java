package ru.fourpda.client;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

public class FastScrollBar {
    private static int f1834p = 2;
    private Drawable f1835a;
    private int f1836b;
    private int f1837c;
    private int f1838d;
    private View f1839e;
    private int f1840f;
    private Paint f1841g;
    private boolean f1843i;
    private RunnableC0547b f1844j;
    private int f1845k;
    private boolean f1847m;
    private AbstractC0546a f1849o;
    private int f1842h = -1;
    private Handler f1846l = new Handler();
    private long f1848n = 0;

    public interface AbstractC0546a {
        void mo604a(View view, double d);
    }

    public class RunnableC0547b implements Runnable {
        long f1850a;
        long f1851b;

        public RunnableC0547b() {
        }

        int m603a() {
            if (FastScrollBar.this.m616d() != 4) {
                return 208;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            long j = this.f1850a;
            long j2 = this.f1851b;
            if (uptimeMillis > j + j2) {
                return 0;
            }
            return (int) (208 - (((uptimeMillis - j) * 208) / j2));
        }

        void m602b() {
            this.f1851b = 200;
            this.f1850a = SystemClock.uptimeMillis();
            FastScrollBar.this.m606n(4);
        }

        @Override
        public void run() {
            if (FastScrollBar.this.m616d() != 4) {
                m602b();
            } else if (m603a() > 0) {
                FastScrollBar.this.f1839e.invalidate();
            } else {
                FastScrollBar.this.m606n(0);
            }
        }
    }

    public FastScrollBar(Context context, View view) {
        this.f1839e = view;
        m615e(context);
    }

    private void m618b() {
        MotionEvent obtain = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        this.f1839e.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void m615e(Context context) {
        m605o(context, context instanceof MainActivity ? ((MainActivity) context).skin.m736f(R.drawable.scrollbar_handle_accelerated_anim2) : context.getResources().getDrawable(R.drawable.scrollbar_handle_accelerated_anim2));
        this.f1844j = new RunnableC0547b();
        Paint paint = new Paint();
        this.f1841g = paint;
        paint.setAntiAlias(true);
        this.f1841g.setTextAlign(Paint.Align.CENTER);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16842806});
        this.f1841g.setColor(obtainStyledAttributes.getColorStateList(obtainStyledAttributes.getIndex(0)).getDefaultColor());
        this.f1841g.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f1845k = 0;
    }

    private void m608l() {
        int width = this.f1839e.getWidth();
        this.f1835a.setBounds(width - this.f1837c, 0, width, this.f1836b);
        this.f1835a.setAlpha(208);
    }

    private void m605o(Context context, Drawable drawable) {
        this.f1835a = drawable;
        this.f1837c = context.getResources().getDimensionPixelSize(R.dimen.fastscroll_thumb_width);
        this.f1836b = context.getResources().getDimensionPixelSize(R.dimen.fastscroll_thumb_height);
        this.f1847m = true;
    }

    public void m617c(Canvas canvas) {
        if (this.f1845k != 0) {
            int scrollY = this.f1838d + this.f1839e.getScrollY();
            int width = this.f1839e.getWidth();
            RunnableC0547b bVar = this.f1844j;
            int scrollX = this.f1839e.getScrollX();
            int i = -1;
            if (this.f1845k == 4) {
                i = bVar.m603a();
                if (i < 104) {
                    this.f1835a.setAlpha(i * 2);
                }
                this.f1835a.setBounds(width - ((this.f1837c * i) / 208), 0, width, this.f1836b);
                this.f1847m = true;
            }
            canvas.translate((float) scrollX, (float) scrollY);
            this.f1835a.draw(canvas);
            canvas.translate((float) (-scrollX), (float) (-scrollY));
            if (i == 0) {
                m606n(0);
            } else {
                this.f1839e.invalidate(width - this.f1837c, scrollY, width, this.f1836b + scrollY);
            }
        }
    }

    public int m616d() {
        return this.f1845k;
    }

    boolean m614f(float f, float f2) {
        if (f > ((float) (this.f1839e.getWidth() - this.f1837c))) {
            int i = this.f1838d;
            if (f2 >= ((float) i) && f2 <= ((float) (i + this.f1836b))) {
                return true;
            }
        }
        return false;
    }

    public boolean m613g() {
        return this.f1845k != 0;
    }

    public boolean m612h(MotionEvent motionEvent) {
        if (this.f1845k <= 0 || motionEvent.getAction() != 0 || !m614f(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        m606n(3);
        return true;
    }

    public void m611i(View view, int i, int i2, int i3) {
        if (this.f1842h != i3 && i2 > 0) {
            this.f1842h = i3;
            this.f1843i = i3 / i2 >= f1834p;
        }
        if (this.f1843i) {
            int i4 = i3 - i2;
            if (i4 > 0 && this.f1845k != 3) {
                this.f1838d = ((this.f1839e.getHeight() - this.f1836b) * i) / i4;
                if (this.f1847m) {
                    m608l();
                    this.f1847m = false;
                }
            }
            if (i != this.f1840f) {
                this.f1840f = i;
                if (this.f1845k != 3) {
                    m606n(2);
                    this.f1846l.postDelayed(this.f1844j, 1500);
                }
            }
        } else if (this.f1845k != 0) {
            m606n(0);
        }
    }

    public void m610j(int i, int i2, int i3, int i4) {
        Drawable drawable = this.f1835a;
        if (drawable != null) {
            drawable.setBounds(i - this.f1837c, 0, i, this.f1836b);
        }
    }

    public boolean m609k(MotionEvent motionEvent) {
        int i = 0;
        if (this.f1845k == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (m614f(motionEvent.getX(), motionEvent.getY())) {
                m606n(3);
                m618b();
                return true;
            }
        } else if (action == 1) {
            if (this.f1845k == 3) {
                m606n(2);
                Handler handler = this.f1846l;
                handler.removeCallbacks(this.f1844j);
                handler.postDelayed(this.f1844j, 1000);
                return true;
            }
        } else if (action == 2 && this.f1845k == 3) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f1848n > 30) {
                this.f1848n = currentTimeMillis;
                int height = this.f1839e.getHeight();
                int i2 = this.f1836b;
                int y = ((int) motionEvent.getY()) - (i2 / 2);
                if (y >= 0) {
                    i = y + i2 > height ? height - i2 : y;
                }
                if (Math.abs(this.f1838d - i) < 2) {
                    return true;
                }
                this.f1838d = i;
                AbstractC0546a aVar = this.f1849o;
                if (aVar != null) {
                    View view = this.f1839e;
                    double d = (double) i;
                    double d2 = (double) (height - this.f1836b);
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    aVar.mo604a(view, d / d2);
                }
            }
            return true;
        }
        return false;
    }

    public void m607m(AbstractC0546a aVar) {
        this.f1849o = aVar;
    }

    public void m606n(int i) {
        if (i != 0) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        int width = this.f1839e.getWidth();
                        int i2 = this.f1838d;
                        this.f1839e.invalidate(width - this.f1837c, i2, width, this.f1836b + i2);
                    }
                }
            } else if (this.f1845k != 2) {
                m608l();
            }
            this.f1846l.removeCallbacks(this.f1844j);
        } else {
            this.f1846l.removeCallbacks(this.f1844j);
            this.f1839e.invalidate();
        }
        this.f1845k = i;
    }
}
