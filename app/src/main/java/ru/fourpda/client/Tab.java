package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

public class Tab extends ViewGroup {
    static int f1441v;
    static boolean f1442w;
    MainActivity f1443a;
    Page page;
    private String f1445c;
    private int f1446d;
    private List<Page> pageList;
    boolean f1448f;
    private boolean f1449g;
    boolean f1450h;
    MainLayout mainLayout;
    ForumsListView forumsListView;
    private TextView f1453k;
    private TextView f1454l;
    private View f1455m;
    private AnimView f1456n;
    private animationAnimation$AnimationListenerC0371e f1457o;
    private animationAnimation$AnimationListenerC0373f f1458p;
    private Animation f1459q;
    private Animation f1460r;
    private Animation f1461s;
    private Animation f1462t;
    Exception exception;

    public class View$OnClickListenerC0367a implements View.OnClickListener {
        View$OnClickListenerC0367a() {
         //   Tab.this = r1;
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = (Tab) view.getTag();
            if (!f1Var.f1448f) {
                Tab.this.mainLayout.m866p(false);
                MainLayout mainLayout = Tab.this.mainLayout;
                if (mainLayout.tab != f1Var) {
                    mainLayout.setCurrentTab(f1Var);
                }
            }
        }
    }

    public class RunnableC0368b implements Runnable {
        RunnableC0368b() {
         //   Tab.this = r1;
        }

        @Override
        public void run() {
            Tab.this.mainLayout.f816a.findViewById(R.id.bar_caption).requestLayout();
        }
    }

    public class RunnableC0369c implements Runnable {
        RunnableC0369c() {
         //   Tab.this = r1;
        }

        @Override
        public void run() {
            Tab.this.f1443a.moveTaskToBack(false);
        }
    }

    public class RunnableC0370d implements Runnable {
        RunnableC0370d() {
        //    Tab.this = r1;
        }

        @Override
        public void run() {
            Tab.this.f1443a.moveTaskToBack(false);
        }
    }

    public class animationAnimation$AnimationListenerC0371e implements Animation.AnimationListener {
        int f1468a;

        class RunnableC0372a implements Runnable {
            RunnableC0372a() {
               // animationAnimation$AnimationListenerC0371e.this = r1;
            }

            @Override
            public void run() {
                Tab.this.forumsListView.setAdapter((ListAdapter) null);
                Tab f1Var = Tab.this;
                if (f1Var.f1448f) {
                    f1Var.removeTab();
                }
            }
        }

        private animationAnimation$AnimationListenerC0371e() {
            //Tab.this = r1;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onAnimationEnd(Animation animation) {
            this.f1468a++;
            if (!Tab.this.f1449g) {
                Tab.this.clearAnimation();
                if (this.f1468a == 2) {
                    Tab.this.setVisibility(4);
                    Tab.this.mainLayout.postDelayed(new RunnableC0372a(), 50);
                    this.f1468a = 0;
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        animationAnimation$AnimationListenerC0371e(Tab f1Var, View$OnClickListenerC0367a aVar) {
            this();
        }
    }

    public class animationAnimation$AnimationListenerC0373f implements Animation.AnimationListener {
        int f1471a;

        private animationAnimation$AnimationListenerC0373f() {
           // Tab.this = r1;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.f1471a++;
            if (!Tab.this.f1449g) {
                Tab.this.clearAnimation();
                if (this.f1471a == 2) {
                    Tab.this.mainLayout.f835r = false;
                    this.f1471a = 0;
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        animationAnimation$AnimationListenerC0373f(Tab f1Var, View$OnClickListenerC0367a aVar) {
            this();
        }
    }

    public class ForumsListView extends ListView implements AbsListView.OnScrollListener {
        Page f1473a;
        View f1474b;
        Drawable f1475c;
        private int f1476d;
        private int f1477e;
        private Drawable f1481i;
        private int f1482j;
        private int f1483k;
        private int f1484l;
        private int f1488p;
        long f1492t;
        boolean f1478f = false;
        private Handler f1479g = new Handler();
        int f1489q = 0;
        int f1490r = 0;
        int f1491s = 0;
        View f1493u = null;
        int f1494v = 0;
        int f1495w = 0;
        float f1496x = 0.0f;
        float f1497y = 0.0f;
        private RunnableC0375a f1480h = new RunnableC0375a();
        private boolean f1487o = true;
        private boolean f1486n = true;
        private int f1485m = 0;

        public class RunnableC0375a implements Runnable {
            long f1499a;
            long f1500b;

            RunnableC0375a() {
              //  C0374g.this = r1;
            }

            int m707a() {
                if (ForumsListView.this.f1485m != 4) {
                    return 208;
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                long j = this.f1499a;
                long j2 = this.f1500b;
                if (uptimeMillis > j + j2) {
                    return 0;
                }
                return (int) (208 - (((uptimeMillis - j) * 208) / j2));
            }

            void m706b() {
                this.f1500b = 200;
                this.f1499a = SystemClock.uptimeMillis();
                ForumsListView.this.m708d(4);
            }

            @Override
            public void run() {
                if (ForumsListView.this.f1485m != 4) {
                    m706b();
                } else if (m707a() > 0) {
                    ForumsListView.this.invalidate();
                } else {
                    ForumsListView.this.m708d(0);
                }
            }
        }

        public class C0376b extends View {
            public C0376b(Context context) {
                super(context);
               // C0374g.this = r1;
            }

            @Override
            protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
                super.onLayout(z, i, i2, i3, i4);
                if (!Tab.this.f1449g) {
                    ForumsListView gVar = ForumsListView.this;
                    Tab f1Var = Tab.this;
                    MainLayout mainLayout = f1Var.mainLayout;
                    if (!mainLayout.f831n && !f1Var.f1450h && gVar.f1493u == null) {
                        mainLayout.m868n(true);
                    }
                }
            }
        }

        public ForumsListView(Context context) {
            super(context);
            Resources resources = Tab.this.f1443a.getResources();
            this.f1481i = Tab.this.f1443a.skin.getSkinDrawable(R.drawable.scrollbar_handle_accelerated_anim2);
            this.f1483k = resources.getDimensionPixelSize(R.dimen.fastscroll_thumb_width);
            this.f1482j = resources.getDimensionPixelSize(R.dimen.fastscroll_thumb_height);
            C0376b bVar = new C0376b(context);
            this.f1474b = bVar;
            bVar.setLayoutParams(new AbsListView.LayoutParams(-1, Tab.this.mainLayout.f816a.getMeasuredHeight()));
            this.f1474b.setBackgroundColor(Skin.SkinColorModel.f1367W);
            this.f1475c = this.f1474b.getBackground();
            addHeaderView(this.f1474b, null, false);
            setOnScrollListener(this);
        }

        private void m709c() {
            int width = getWidth();
            this.f1481i.setBounds(width - this.f1483k, 0, width, this.f1482j);
            this.f1481i.setAlpha(208);
        }

        public void m708d(int i) {
            if (i != 0) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            int width = getWidth();
                            int i2 = this.f1484l;
                            invalidate(width - this.f1483k, i2, width, this.f1482j + i2);
                        }
                    }
                } else if (this.f1485m != 2) {
                    m709c();
                }
                this.f1479g.removeCallbacks(this.f1480h);
            } else {
                this.f1479g.removeCallbacks(this.f1480h);
                invalidate();
            }
            this.f1485m = i;
        }

        @Override
        protected int computeVerticalScrollExtent() {
            return this.f1477e;
        }

        @Override
        protected int computeVerticalScrollOffset() {
            return this.f1476d;
        }

        @Override
        protected int computeVerticalScrollRange() {
            Exception e;
            int i;
            int i2;
            int i3;
            int i4;
            View childAt;
            int i5;
            int i6;
            int i7;
            int i8;
            Exception e2;
            int i9 = 1;
            if (Tab.this.mainLayout == null || this.f1473a == null || !this.f1478f) {
                return 1;
            }
            try {
                int height = getHeight();
                int bottom = Tab.this.mainLayout.f816a.getBottom();
                int firstVisiblePosition = getFirstVisiblePosition();
                int lastVisiblePosition = getLastVisiblePosition();
                int headerViewsCount = getHeaderViewsCount();
                int childCount = getChildCount();
                int count = this.f1473a.getCount();
                int[] iArr = this.f1473a.f1071B;
                if (iArr == null) {
                    i = bottom;
                    int i10 = count * 10000;
                    int i11 = childCount * 10000;
                    try {
                        if (childCount > 0) {
                            View childAt2 = getChildAt(0);
                            int height2 = childAt2.getHeight();
                            if (height2 > 0) {
                                int top = (childAt2.getTop() * 10000) / height2;
                                i11 += top;
                                if (firstVisiblePosition >= headerViewsCount) {
                                    i4 = Math.max(((firstVisiblePosition - headerViewsCount) * 10000) - top, 0);
                                    childAt = getChildAt(childCount - 1);
                                    if (count <= 2) {
                                        height2 = childAt.getHeight();
                                    }
                                    if (childAt.getHeight() > 0) {
                                        i11 -= ((childAt.getBottom() - height) * 10000) / height2;
                                    }
                                    i9 = i11;
                                    i2 = i10;
                                    i3 = i4;
                                }
                            }
                            i4 = 0;
                            childAt = getChildAt(childCount - 1);
                            if (count <= 2) {
                            }
                            if (childAt.getHeight() > 0) {
                            }
                            i9 = i11;
                            i2 = i10;
                            i3 = i4;
                        } else {
                            this.f1476d = 0;
                            this.f1477e = 0;
                            return 0;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        i9 = i10;
                        //ACRA.getErrorReporter().putCustomData("extra", this.f1473a.toString());
                        //ACRA.getErrorReporter().handleSilentException(e);
                        //ACRA.getErrorReporter().removeCustomData("extra");
                        return i9;
                    }
                } else if (iArr.length > 0) {
                    i9 = iArr[iArr.length - 1];
                    if (firstVisiblePosition >= headerViewsCount) {
                        int i12 = firstVisiblePosition;
                        int i13 = 0;
                        int i14 = 0;
                        int i15 = 0;
                        while (i14 < childCount) {
                            try {
                                View childAt3 = getChildAt(i14);
                                try {
                                    int height3 = childAt3.getHeight();
                                    if (i14 == 0) {
                                        i5 = childAt3.getTop();
                                        i6 = height3;
                                    } else {
                                        i5 = i15;
                                        i6 = i13;
                                    }
                                    try {
                                        int[] iArr2 = this.f1473a.f1071B;
                                        int i16 = i12 - headerViewsCount;
                                        int i17 = iArr2[i16] - (i12 == headerViewsCount ? 0 : iArr2[i16 - 1]);
                                        if (i17 != height3) {
                                            i7 = i6;
                                            int i18 = i16;
                                            while (i18 < count) {
                                                i8 = bottom;
                                                try {
                                                    int[] iArr3 = this.f1473a.f1071B;
                                                    iArr3[i18] = (iArr3[i18] - i17) + height3;
                                                    i18++;
                                                    bottom = i8;
                                                } catch (Exception e4) {
                                                    e2 = e4;
                                                    //ACRA.getErrorReporter().putCustomData("extra", this.f1473a.toString());
                                               //     ErrorReporter errorReporter = //ACRA.getErrorReporter();
                                                //    errorReporter.putCustomData("extra2", "" + i12 + " " + firstVisiblePosition + " " + lastVisiblePosition + " " + childCount + " " + this.f1473a.f1071B.length);
                                                    //ACRA.getErrorReporter().handleSilentException(e2);
                                                    //ACRA.getErrorReporter().removeCustomData("extra");
                                                    //ACRA.getErrorReporter().removeCustomData("extra2");
                                                    i14++;
                                                    i12++;
                                                    i15 = i5;
                                                    i9 = i9;
                                                    bottom = i8;
                                                    i13 = i7;
                                                }
                                            }
                                            i8 = bottom;
                                        } else {
                                            i8 = bottom;
                                            i7 = i6;
                                        }
                                    } catch (Exception e5) {
                                        e2 = e5;
                                        i8 = bottom;
                                        i7 = i6;
                                    }
                                    i14++;
                                    i12++;
                                    i15 = i5;
                                    i9 = i9;
                                    bottom = i8;
                                    i13 = i7;
                                } catch (Exception e6) {
                                    e = e6;
                                    i9 = i9;
                                    //ACRA.getErrorReporter().putCustomData("extra", this.f1473a.toString());
                                    //ACRA.getErrorReporter().handleSilentException(e);
                                    //ACRA.getErrorReporter().removeCustomData("extra");
                                    return i9;
                                }
                            } catch (Exception e7) {
                                e = e7;
                            }
                        }
                        i2 = i9;
                        i = bottom;
                        i3 = (this.f1473a.f1071B[firstVisiblePosition - headerViewsCount] - i13) - i15;
                    } else {
                        i2 = i9;
                        i = bottom;
                        i3 = 0;
                    }
                    i9 = height;
                } else {
                    i = bottom;
                    i3 = 0;
                    i2 = 1;
                }
                if (height == 0) {
                    this.f1476d = 0;
                    this.f1477e = 0;
                    return 0;
                }
                float f = ((float) (height - i)) / ((float) height);
                this.f1477e = (int) (((float) i9) * f);
                this.f1476d = ((i * i2) / height) + ((int) (((float) i3) * f));
                return i2;
            } catch (Exception e8) {
                e = e8;
            }
            return 0;
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            if (this.f1485m != 0) {
                this.f1484l = (int) (((long) (getScrollY() + this.f1488p)) + ((((((long) getHeight()) - ((long) this.f1488p)) - ((long) this.f1482j)) * ((long) this.f1491s)) / ((long) this.f1489q)));
                int width = getWidth();
                int scrollX = getScrollX();
                int i = -1;
                if (this.f1485m == 4) {
                    i = this.f1480h.m707a();
                    if (i < 104) {
                        this.f1481i.setAlpha(i * 2);
                    }
                    this.f1481i.setBounds(width - ((this.f1483k * i) / 208), 0, width, this.f1482j);
                    this.f1487o = true;
                }
                canvas.translate((float) scrollX, (float) this.f1484l);
                this.f1481i.draw(canvas);
                canvas.translate((float) (-scrollX), (float) (-this.f1484l));
                if (i == 0) {
                    m708d(0);
                    return;
                }
                int i2 = this.f1484l;
                invalidate(width - this.f1483k, i2, width, this.f1482j + i2);
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            View view;
            int i;
            int i2;
            int[] iArr;
            try {
                int action = motionEvent.getAction();
                int i3 = this.f1485m;
                boolean z = false;
                int i4 = 0;
                if (i3 == 0 || action != 0) {
                    if (i3 == 3) {
                        if (action == 1) {
                            m708d(2);
                            Handler handler = this.f1479g;
                            handler.removeCallbacks(this.f1480h);
                            handler.postDelayed(this.f1480h, 1000);
                            return true;
                        } else if (action == 3) {
                            m708d(0);
                            return true;
                        } else if (action == 2) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - this.f1492t > 30) {
                                this.f1492t = currentTimeMillis;
                                int height = getHeight();
                                int i5 = this.f1482j;
                                int y = ((int) motionEvent.getY()) - (i5 / 2);
                                int i6 = this.f1488p;
                                if (y < i6) {
                                    y = i6;
                                } else if (y + i5 > height) {
                                    y = height - i5;
                                } else if (Math.abs(this.f1484l - y) < 2) {
                                    return true;
                                }
                                if (this.f1484l == y) {
                                    return true;
                                }
                                this.f1484l = y;
                                if (this.f1486n) {
                                    int i7 = this.f1488p;
                                    int scrollY = (int) ((((((long) y) - ((long) getScrollY())) - ((long) i7)) * ((long) this.f1489q)) / ((long) ((height - i7) - this.f1482j)));
                                    this.f1491s = scrollY;
                                    if (this.f1473a.f1071B != null) {
                                        int measuredHeight = Tab.this.mainLayout.f816a.getMeasuredHeight();
                                        i2 = 0;
                                        while (true) {
                                            iArr = this.f1473a.f1071B;
                                            if (i2 >= iArr.length || iArr[i2] + measuredHeight >= this.f1491s) {
                                                break;
                                            }
                                            i2++;
                                        }
                                        if (i2 < iArr.length) {
                                            int i8 = this.f1491s - measuredHeight;
                                            if (i2 > 0) {
                                                i4 = iArr[i2 - 1];
                                            }
                                            i = i8 - i4;
                                        } else {
                                            i2 = -1;
                                            i = -1;
                                        }
                                    } else {
                                        i = scrollY % 10000;
                                        i2 = scrollY / 10000;
                                        if (i2 != 0 && i <= 0) {
                                            i2--;
                                        }
                                        i = (-(getChildAt(0).getHeight() * (10000 - i))) / 10000;
                                    }
                                    if (-1 != i2) {
                                        setSelectionFromTop(getHeaderViewsCount() + i2, -i);
                                    }
                                }
                            }
                            return true;
                        }
                    }
                } else if (motionEvent.getX() > ((float) (getWidth() - this.f1483k)) && motionEvent.getY() >= ((float) this.f1484l) && motionEvent.getY() <= ((float) (this.f1484l + this.f1482j))) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    m708d(3);
                    MotionEvent obtain = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
                    super.onTouchEvent(obtain);
                    obtain.recycle();
                    return true;
                }
                if (action == 0) {
                    float y2 = motionEvent.getY();
                    this.f1493u = null;
                    for (int i9 = 0; i9 < getChildCount(); i9++) {
                        View childAt = getChildAt(i9);
                        if (y2 >= ((float) childAt.getTop()) && y2 <= ((float) childAt.getBottom())) {
                            this.f1493u = childAt;
                            this.f1494v = childAt.getTop() - Tab.this.mainLayout.f832o;
                        }
                    }
                    this.f1495w = Tab.this.mainLayout.f816a.getMeasuredHeight();
                } else if (2 == action && (view = this.f1493u) != null) {
                    MainLayout mainLayout = Tab.this.mainLayout;
                    int i10 = mainLayout.f832o;
                    mainLayout.f832o = view.getTop() - this.f1494v;
                    if (getFirstVisiblePosition() == 0) {
                        int top = this.f1474b.getTop();
                        MainLayout mainLayout2 = Tab.this.mainLayout;
                        if (top > mainLayout2.f832o) {
                            mainLayout2.f832o = top;
                        }
                    }
                    MainLayout mainLayout3 = Tab.this.mainLayout;
                    if (mainLayout3.f832o > 0) {
                        mainLayout3.f831n = true;
                        mainLayout3.f832o = 0;
                        this.f1494v = this.f1493u.getTop() - Tab.this.mainLayout.f816a.getTop();
                    }
                    int i11 = this.f1495w;
                    int i12 = -i11;
                    MainLayout mainLayout4 = Tab.this.mainLayout;
                    if (i12 > mainLayout4.f832o) {
                        mainLayout4.f831n = false;
                        mainLayout4.f832o = -i11;
                    }
                    int i13 = mainLayout4.f832o;
                    if (i13 != i10) {
                        View view2 = mainLayout4.f816a;
                        view2.layout(0, i13, view2.getMeasuredWidth(), Tab.this.mainLayout.f832o + this.f1495w);
                    }
                } else if (1 == action && this.f1493u != null) {
                    this.f1493u = null;
                    int measuredHeight2 = Tab.this.mainLayout.f816a.getMeasuredHeight();
                    MainLayout mainLayout5 = Tab.this.mainLayout;
                    if ((-mainLayout5.f832o) < measuredHeight2 / 2) {
                        z = true;
                    }
                    mainLayout5.m868n(z);
                }
                if (action == 0) {
                    float y3 = motionEvent.getY();
                    this.f1497y = y3;
                    this.f1496x = y3;
                } else if (1 == action) {
                    float y4 = motionEvent.getY();
                    this.f1497y = y4;
                    if (((Math.abs(y4 - this.f1496x) * 1000.0f) / Tab.this.mainLayout.f833p) / ((float) (motionEvent.getEventTime() - motionEvent.getDownTime())) < 1100.0f) {
                        this.f1496x = 0.0f;
                        this.f1497y = 0.0f;
                    }
                }
                return super.dispatchTouchEvent(motionEvent);
            } catch (Exception e) {
                //ACRA.getErrorReporter().putCustomData("extra", this.f1473a.toString());
                //ACRA.getErrorReporter().handleSilentException(new Exception("ML MainListView DispatchTouchEvent", e));
                //ACRA.getErrorReporter().removeCustomData("extra");
                return true;
            }
        }

        @Override
        protected void layoutChildren() {
            try {
                super.layoutChildren();
            } catch (Exception e) {
                //ACRA.getErrorReporter().putCustomData("extra", this.f1473a.toString());
                //ACRA.getErrorReporter().handleSilentException(e);
                //ACRA.getErrorReporter().removeCustomData("extra");
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            boolean z;
            int i4;
            int i5;
            int i6;
            Page a0Var;
            MainLayout mainLayout = Tab.this.mainLayout;
            mainLayout.f830m = i + i2 < i3;
            if (Prefs.scrollMode == 0) {
                if ((i == 0) == mainLayout.f829l.isEnabled()) {
                    Tab.this.mainLayout.f829l.setEnabled(i != 0);
                }
            } else if (mainLayout.f829l.isEnabled()) {
                Tab.this.mainLayout.f829l.setEnabled(false);
            }
            if (2 == Prefs.scrollMode && (a0Var = this.f1473a) != null) {
                if (this.f1485m == 0) {
                    if (a0Var.f1071B != null) {
                        int height = getHeight();
                        int[] iArr = this.f1473a.f1071B;
                    }
                    if (this.f1473a.f1071B == null) {
                    }
                }
                z = true;
                this.f1478f = !z;
                if (i2 > 0 && i3 > i2 && z) {
                    if (this.f1488p == 0) {
                        this.f1488p = Tab.this.mainLayout.getActionBarHeight();
                    }
                    int headerViewsCount = getHeaderViewsCount();
                    View childAt = getChildAt(0);
                    if (this.f1473a.f1071B == null) {
                        this.f1490r = getHeight();
                        int i7 = this.f1488p;
                        int[] iArr2 = this.f1473a.f1071B;
                        this.f1489q = i7 + iArr2[iArr2.length - 1];
                    } else {
                        this.f1489q = i3 * 10000;
                        this.f1490r = i2 * 10000;
                        int height2 = childAt.getHeight();
                        if (height2 > 0) {
                            this.f1490r += (childAt.getTop() * 10000) / height2;
                        }
                        View childAt2 = getChildAt(getChildCount() - 1);
                        int height3 = childAt2.getHeight();
                        if (height3 > 0) {
                            this.f1490r -= ((childAt2.getBottom() - getHeight()) * 10000) / height3;
                        }
                    }
                    this.f1489q -= this.f1490r;
                    if (this.f1485m != 3) {
                        int[] iArr3 = this.f1473a.f1071B;
                        if (iArr3 != null) {
                            i6 = ((this.f1488p + (i >= headerViewsCount ? iArr3[i - headerViewsCount] : 0)) - childAt.getHeight()) - childAt.getTop();
                        } else {
                            i6 = (i * 10000) - ((childAt.getTop() * 10000) / childAt.getHeight());
                        }
                        this.f1491s = i6;
                        if (this.f1487o) {
                            m709c();
                            this.f1487o = false;
                        }
                    }
                }
                this.f1486n = true;
                if (z && (i4 = this.f1491s) > 0 && i4 < this.f1489q && (i5 = this.f1485m) != 3 && i5 != 2) {
                    m708d(2);
                    this.f1479g.postDelayed(this.f1480h, 1500);
                    return;
                }
                return;
            }
            z = false;
            this.f1478f = !z;
            if (i2 > 0) {
                if (this.f1488p == 0) {
                }
                int headerViewsCount2 = getHeaderViewsCount();
                View childAt3 = getChildAt(0);
                if (this.f1473a.f1071B == null) {
                }
                this.f1489q -= this.f1490r;
                if (this.f1485m != 3) {
                }
            }
            this.f1486n = true;
            if (z) {
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            boolean z = true;
            int i2 = 1;
            if (1 != Prefs.scrollMode) {
                return;
            }
            if (i == 2 && this.f1497y != this.f1496x) {
                int measuredHeight = getMeasuredHeight() / 2;
                MainLayout mainLayout = Tab.this.mainLayout;
                mainLayout.f822e.setImageDrawable(mainLayout.f796D.skin.getSkinDrawable(this.f1497y > this.f1496x ? R.drawable.ic_scroll_up : R.drawable.ic_scroll_down));
                Widgets$CircleImageView widgets$CircleImageView = Tab.this.mainLayout.f822e;
                if (this.f1497y > this.f1496x) {
                    i2 = 2;
                }
                widgets$CircleImageView.setTag(Integer.valueOf(i2));
                Widgets$CircleImageView widgets$CircleImageView2 = Tab.this.mainLayout.f822e;
                int width = getWidth();
                float f = Tab.this.mainLayout.f833p;
                int width2 = getWidth();
                float f2 = Tab.this.mainLayout.f833p;
                widgets$CircleImageView2.layout(width - ((int) (74.0f * f)), measuredHeight - ((int) (f * 34.0f)), width2 - ((int) (6.0f * f2)), measuredHeight + ((int) (f2 * 34.0f)));
                Tab.this.mainLayout.f822e.m846d();
            } else if (i == 0 && ((Integer) Tab.this.mainLayout.f822e.getTag()).intValue() != 0) {
                Widgets$CircleImageView widgets$CircleImageView3 = Tab.this.mainLayout.f822e;
                if (this.f1497y == this.f1496x) {
                    z = false;
                }
                widgets$CircleImageView3.m849a(z);
                Tab.this.mainLayout.f822e.setTag(0);
            }
        }

        @Override
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            this.f1488p = 0;
            this.f1481i.setBounds(i - this.f1483k, 0, i, this.f1482j);
            super.onSizeChanged(i, i2, i3, i4);
        }

        @Override
        public void requestDisallowInterceptTouchEvent(boolean z) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.f1493u = null;
            }
            super.requestDisallowInterceptTouchEvent(z);
        }

        @Override
        public void setAdapter(ListAdapter listAdapter) {
            this.f1473a = (Page) listAdapter;
            super.setAdapter(listAdapter);
        }
    }

    @SuppressLint("WrongConstant")
    public Tab(MainActivity mainActivity, Bundle bundle, String str) {
        super(mainActivity);
        this.f1445c = "No title";
        this.f1446d = R.drawable.ic_nav_home;
        this.pageList = new Vector(10);
        this.f1449g = false;
        this.f1443a = mainActivity;
        this.mainLayout = mainActivity.mainLayout;
        TextView textView = new TextView(mainActivity);
        this.f1454l = textView;
        textView.setText("Тут ничего нет");
        this.f1454l.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f1454l.setGravity(17);
        this.f1454l.setTextColor(Skin.SkinColorModel.labelTextCsl);
        this.f1454l.setBackgroundDrawable(Skin.SkinColorModel.f1388i0.getConstantState().newDrawable());
        this.f1454l.setFocusable(true);
        this.f1454l.setFocusableInTouchMode(true);
        addView(this.f1454l);
        ForumsListView gVar = new ForumsListView(mainActivity);
        this.forumsListView = gVar;
        gVar.setDividerHeight(0);
        this.forumsListView.setFadingEdgeLength(0);
        this.forumsListView.setSelector(R.color.list_selector);
        this.forumsListView.setScrollingCacheEnabled(false);
        this.forumsListView.setCacheColorHint(0);
        this.forumsListView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.forumsListView.setEmptyView(this.f1454l);
        addView(this.forumsListView);
        AnimView r1Var = new AnimView(mainActivity);
        this.f1456n = r1Var;
        r1Var.setVisibility(4);
        addView(this.f1456n);
        TextView textView2 = new TextView(this.f1443a);
        this.f1453k = textView2;
        textView2.setTextColor(Skin.SkinColorModel.mainTextColor);
        this.f1453k.setTextSize(2, 16.0f);
        this.f1453k.setSingleLine();
        this.f1453k.setEllipsize(TextUtils.TruncateAt.END);
        this.f1453k.setLineSpacing(this.mainLayout.f834q * 3.0f, 1.0f);
        this.f1453k.setClickable(false);
        this.f1453k.setBackgroundDrawable(this.mainLayout.f796D.skin.getSkinDrawable(R.drawable.nav_item));
        this.f1453k.setPadding((int) (this.mainLayout.f833p * 8.0f), 0, 0, 0);
        this.f1453k.setCompoundDrawablePadding((int) (this.mainLayout.f833p * 8.0f));
        this.f1453k.setGravity(19);
        this.f1453k.setTag(this);
        this.f1453k.setOnClickListener(new View$OnClickListenerC0367a());
        this.f1453k.setOnTouchListener(this.mainLayout.f794B);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mainLayout.addView(this, 0);
        setVisibility(4);
        this.mainLayout.f825h.addView(this.f1453k);
        ViewGroup.LayoutParams layoutParams = this.f1453k.getLayoutParams();
        MainLayout mainLayout = this.mainLayout;
        layoutParams.height = (int) (mainLayout.f833p * 40.0f);
        mainLayout.tabList.add(this);
        this.mainLayout.f793A.add(this);
        this.f1457o = new animationAnimation$AnimationListenerC0371e(this, null);
        this.f1458p = new animationAnimation$AnimationListenerC0373f(this, null);
        this.f1459q = AnimationUtils.loadAnimation(this.f1443a, R.anim.tab_hide);
        this.f1460r = AnimationUtils.loadAnimation(this.f1443a, R.anim.tab_show);
        this.f1459q.setAnimationListener(this.f1457o);
        this.f1460r.setAnimationListener(this.f1458p);
        this.f1461s = AnimationUtils.loadAnimation(this.f1443a, R.anim.tab_hide_back);
        this.f1462t = AnimationUtils.loadAnimation(this.f1443a, R.anim.tab_show_back);
        this.f1461s.setAnimationListener(this.f1457o);
        this.f1462t.setAnimationListener(this.f1458p);
        if (bundle != null) {
            int i = bundle.getInt(str + "_n");
            for (int i2 = 0; i2 < i; i2++) {
                String str2 = str + "_" + i2;
                Page b = Urls2.openPage(mainActivity, "https://4pda.ru/" + bundle.getString(str2), true, 0);
                if (b == null) {
                    b = new Page_Start(mainActivity);
                }
                b.mo147A(bundle, str2);
                this.pageList.add(b);
            }
            List<Page> list = this.pageList;
            addPage(list.remove(list.size() - 1));
            m713m(bundle.getString(str + "_t"), bundle.getInt(str + "_i"));
        }
    }

    public boolean m724b() {
        Page a0Var = this.page;
        if (a0Var != null) {
            if (a0Var.mo145B()) {
                return true;
            }
            if (this.page.hashCode() == f1441v) {
                f1441v = 0;
                this.mainLayout.postDelayed(new RunnableC0369c(), 300);
            }
            if (this.pageList.size() > 0) {
                if (Prefs.backButton && this.page.f1078l) {
                    while (this.pageList.size() > 0) {
                        List<Page> list = this.pageList;
                        Page a0Var2 = list.get(list.size() - 1);
                        if (a0Var2.hashCode() == f1441v) {
                            f1441v = 0;
                            this.mainLayout.postDelayed(new RunnableC0370d(), 300);
                        }
                        this.pageList.remove(a0Var2);
                        a0Var2.m807s();
                        if (!a0Var2.f1078l) {
                            break;
                        }
                    }
                }
                if (this.pageList.size() > 0) {
                    if (m717i()) {
                        this.page.onSearchBar();
                    }
                    this.page.m807s();
                    this.page = null;
                    List<Page> list2 = this.pageList;
                    addPage(list2.remove(list2.size() - 1));
                    return true;
                }
            }
        }
        return false;
    }

    public void removeTab() {
        this.f1449g = true;
        Page a0Var = this.page;
        if (a0Var != null) {
            a0Var.m807s();
            this.page = null;
        }
        for (int i = 0; i < this.pageList.size(); i++) {
            this.pageList.get(i).m807s();
        }
        this.pageList.clear();
        this.mainLayout.tabList.remove(this);
        this.mainLayout.f793A.remove(this);
        this.mainLayout.f825h.removeView(this.f1453k);
        this.mainLayout.removeView(this);
        this.f1453k = null;
        this.forumsListView = null;
        this.exception = new Exception();
    }

    public void m722d() {
        for (int i = 0; i < this.pageList.size(); i++) {
            this.pageList.get(i).m807s();
        }
        this.pageList.clear();
    }

    @SuppressLint("WrongConstant")
    public void m721e(boolean z, int i) {
        if (z && 4 == this.f1456n.getVisibility()) {
            this.f1456n.setVisibility(0);
            this.f1456n.m342a(true);
        } else if (!z && this.f1456n.getVisibility() == 0) {
            this.f1456n.m342a(false);
            this.f1456n.setVisibility(4);
        }
        if (z) {
            this.f1456n.m340c(i);
        }
    }

    public void m720f() {
        Page a0Var;
        if (m717i() && (a0Var = this.page) != null) {
            a0Var.mo54M(this, true);
        }
    }

    public void m719g() {
        f1441v = 0;
    }

    public void m718h() {
        if (!this.f1449g) {
            this.f1453k.setSelected(false);
            this.f1457o.f1468a = 0;
            startAnimation(f1442w ? this.f1461s : this.f1459q);
            Page a0Var = this.page;
            if (a0Var != null) {
                a0Var.onSearchBar();
            }
        }
    }

    public boolean m717i() {
        return this.page != null && this.mainLayout.tab == this;
    }

    public void m716j(Bundle bundle, String str) {
        bundle.putInt(str + "_n", this.pageList.size() + 1);
        bundle.putString(str + "_t", this.f1445c);
        bundle.putInt(str + "_i", this.f1446d);
        for (int i = 0; i < this.pageList.size(); i++) {
            Page a0Var = this.pageList.get(i);
            String str2 = str + "_" + i;
            bundle.putString(str2, a0Var.getLink());
            a0Var.mo138Y(bundle, str2);
        }
        if (m717i()) {
            this.page.m814V();
        }
        String str3 = str + "_" + this.pageList.size();
        bundle.putString(str3, this.page.getLink());
        this.page.mo138Y(bundle, str3);
    }

    public void addPage(Page page) {
        if (this.page != null) {
            if (this.pageList.size() > 0) {
                List<Page> list = this.pageList;
                Page a0Var2 = list.get(list.size() - 1);
                if (a0Var2.isUnsucces()) {
                    a0Var2.tabLoaded(false);
                }
            }
            Page newPage = this.page;
            if ((newPage instanceof Page_Forum) || (newPage instanceof Page_Favorites) || (newPage instanceof Page_QMS_List)) {
                newPage.tabLoaded(false);
            }
            if (m717i()) {
                this.page.onSearchBar();
            }
            this.pageList.add(this.page);
        }
        this.page = page;
        if (page == null) {
            return;
        }
        if (m717i()) {
            if (!this.page.isUnsucces()) {
                this.page.tabReload();
            }
            this.forumsListView.setAdapter((ListAdapter) this.page);
            this.mainLayout.m868n(true);
            this.page.mo54M(this, false);
            return;
        }
        this.page.changeTitleTabError();
    }

    public void m714l(View view, boolean z) {
        View view2 = this.f1455m;
        if (view != view2) {
            if (view2 != null) {
                removeView(view2);
            }
            this.f1455m = view;
            boolean z2 = true;
            if (view != null) {
                addView(view, 1);
            }
            if (view == null || !z) {
                z2 = false;
            }
            this.f1450h = z2;
        }
    }

    public void m713m(String str, int i) {
        if (!this.f1449g) {
            this.f1445c = str;
            this.f1446d = i;
            this.f1453k.setText(str);
            this.f1453k.setCompoundDrawablesWithIntrinsicBounds(this.mainLayout.f796D.skin.getSkinDrawable(this.f1446d), (Drawable) null, (Drawable) null, (Drawable) null);
            MainLayout mainLayout = this.mainLayout;
            if (mainLayout.tab == this) {
                mainLayout.f826i.setText(str);
                this.mainLayout.post(new RunnableC0368b());
            }
        }
    }

    @SuppressLint("WrongConstant")
    public void m712n(boolean z) {
        f1441v = 0;
        if (!this.page.isUnsucces()) {
            this.page.tabReload();
        }
        this.forumsListView.setAdapter((ListAdapter) this.page);
        this.mainLayout.m868n(true);
        this.page.mo54M(this, false);
        this.f1453k.setSelected(true);
        setVisibility(0);
        if (z) {
            this.mainLayout.f835r = true;
            this.f1458p.f1471a = 0;
            startAnimation(f1442w ? this.f1462t : this.f1460r);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (Build.VERSION.SDK_INT < 11) {
            setBackgroundDrawable(((MainActivity) getContext()).skin.getSkinDrawable(R.drawable.main_background));
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.f1455m;
        if (view == null || view.getVisibility() != 0) {
            ForumsListView gVar = this.forumsListView;
            gVar.layout(0, 0, gVar.getMeasuredWidth(), this.forumsListView.getMeasuredHeight());
            TextView textView = this.f1454l;
            textView.layout(0, 0, textView.getMeasuredHeight(), this.f1454l.getMeasuredHeight());
        } else {
            int max = Math.max(0, getMeasuredHeight() - this.f1455m.getMeasuredHeight());
            ForumsListView gVar2 = this.forumsListView;
            gVar2.layout(0, 0, gVar2.getMeasuredWidth(), max);
            TextView textView2 = this.f1454l;
            textView2.layout(0, 0, textView2.getMeasuredWidth(), max);
            View view2 = this.f1455m;
            view2.layout(0, max, view2.getMeasuredWidth(), this.f1455m.getMeasuredHeight() + max);
        }
        this.f1456n.layout(this.forumsListView.getWidth() - this.f1456n.getMeasuredWidth(), this.forumsListView.getHeight() - this.f1456n.getMeasuredHeight(), this.forumsListView.getWidth(), this.forumsListView.getHeight());
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
        setMeasuredDimension(ViewGroup.resolveSize(getSuggestedMinimumWidth(), i), ViewGroup.resolveSize(getSuggestedMinimumHeight(), i2));
    }

    public Tab(MainActivity mainActivity) {
        this(mainActivity, null, null);
    }
}
