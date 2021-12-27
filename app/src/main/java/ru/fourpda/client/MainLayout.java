package ru.fourpda.client;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;


public class MainLayout extends ViewGroup implements Animation.AnimationListener {
    public List<Tab> f793A;
    View$OnTouchListenerC0234n f794B;
    TopLayout f795C;
    MainActivity f796D;
    int f804L;
    float f805M;
    DecelerateInterpolator f806N;
    Widgets$CircleImageView f807O;
    protected int f808P;
    protected int f809Q;
    AnimateDrawable f810R;
    float f811S;
    int f812T;
    boolean f813U;
    C0233m f814V;
    C0232l f815W;
    View f816a;
    C0230j f817a0;
    TextView f818b;
    C0231k f819b0;
    Widgets$CircleImageView f820c;
    Widgets$CircleImageView f821d;
    Widgets$CircleImageView f822e;
    private DrawerLayout f823f;
    private View f824g;
    LinearLayout f825h;
    TextView f826i;
    private View f827j;
    private View f828k;
    View f829l;
    int f832o;
    public float f833p;
    public float f834q;
    boolean f835r;
    private float f837t;
    private float f838u;
    private Animation f841x;
    Tab tab;
    public List<Tab> tabList;
    boolean f830m = false;
    public boolean f831n = true;
    private int f836s = 0;
    private int f839v = 0;
    private boolean f840w = false;
    private boolean f797E = false;
    public boolean f798F = true;
    public boolean f799G = true;
    public boolean f800H = true;
    public boolean f801I = true;
    int f802J = -328966;
    boolean f803K = false;

    public static class DrawerLayout extends ViewGroup {
        private float f844a;
        private int f845b;
        private View f846c;
        private View f847d;
        private boolean f848e;
        private int f849f;
        private int f850g;
        private float f851h;
        private float f852i;
        private boolean f853j;
        private boolean f854k;
        private Paint f855l;

        public class C0219a implements ValueAnimator.AnimatorUpdateListener {
            C0219a() {
             //   DrawerLayout.this = r1;
            }

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DrawerLayout.this.invalidate();
            }
        }

        public class C0220b implements TimeInterpolator {
            C0220b(DrawerLayout drawerLayout) {
            }

            @Override
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        public DrawerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        private boolean m856b(MotionEvent motionEvent) {
            return (Math.abs(motionEvent.getX() - this.f851h) * 1000.0f) / (((float) (motionEvent.getEventTime() - motionEvent.getDownTime())) * this.f844a) > 400.0f;
        }

        boolean m857a() {
            return this.f848e;
        }

        @SuppressLint({"NewApi"})
        void m855c(boolean z) {
            int i;
            int i2 = this.f850g;
            this.f848e = z;
            if (z) {
                i = 0;
            } else {
                i = -this.f849f;
            }
            this.f850g = i;
            View view = this.f847d;
            view.layout(i, 0, this.f849f + i, view.getMeasuredHeight());
            if (!this.f848e) {
                i2 += this.f849f;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 11 && i2 != 0) {
                this.f847d.setTranslationX((float) i2);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f847d, "translationX", 0.0f);
                ofFloat.addUpdateListener(new C0219a());
                ofFloat.setInterpolator(new C0220b(this));
                ofFloat.setDuration(((Math.abs(i2) * 344) / this.f849f) + 256);
                ofFloat.start();
            } else if (i3 < 11) {
                invalidate();
            }
            if (z) {
                ((MainActivity) getContext()).mainLayout.m859w(null);
            }
        }

        @Override
        @SuppressLint({"NewApi"})
        protected boolean drawChild(Canvas canvas, View view, long j) {
            boolean drawChild = super.drawChild(canvas, view, j);
            if (view == this.f846c) {
                int floor = Build.VERSION.SDK_INT >= 11 ? (int) Math.floor(this.f847d.getTranslationX()) : 0;
                int i = this.f850g;
                int i2 = i + floor;
                int i3 = this.f849f;
                if (i2 > (-i3)) {
                    this.f855l.setColor((((((i + i3) + floor) * 153) / i3) & 255) << 24);
                    canvas.drawRect((float) Math.max(((this.f850g + this.f849f) + floor) - ((int) (this.f844a * 10.0f)), 0), 0.0f, (float) getWidth(), (float) getHeight(), this.f855l);
                }
            }
            return drawChild;
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            this.f844a = getContext().getResources().getDisplayMetrics().density;
            this.f845b = ViewConfiguration.get(getContext()).getScaledTouchSlop();
            this.f846c = getChildAt(0);
            this.f847d = getChildAt(1);
            this.f855l = new Paint();
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            boolean z = true;
            if (action == 0) {
                this.f851h = motionEvent.getX();
                this.f852i = motionEvent.getY();
                boolean z2 = this.f848e;
                this.f853j = z2 || this.f851h <= this.f844a * 36.0f;
                boolean z3 = z2 && this.f851h > ((float) this.f849f);
                this.f854k = z3;
                return z3;
            } else if (action == 2 && this.f853j) {
                float x = motionEvent.getX();
                float abs = Math.abs(x - this.f851h);
                float abs2 = Math.abs(motionEvent.getY() - this.f852i);
                int i = this.f845b;
                if (abs2 > ((float) i)) {
                    this.f853j = false;
                } else if (abs > ((float) i)) {
                    boolean z4 = this.f848e;
                    if (z4 && x > this.f851h) {
                        this.f853j = false;
                    } else if (!z4 && x < this.f851h) {
                        this.f853j = false;
                    }
                    return this.f853j;
                }
            } else if (action == 1 && this.f853j && m856b(motionEvent)) {
                if (motionEvent.getX() <= this.f851h) {
                    z = false;
                }
                m855c(z);
            }
            return false;
        }

        @Override
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            View view = this.f847d;
            int i5 = this.f850g;
            view.layout(i5, 0, this.f849f + i5, getMeasuredHeight());
            this.f846c.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }

        @Override
        protected void onMeasure(int i, int i2) {
            measureChildren(i, i2);
            View view = this.f847d;
            double size = MeasureSpec.getSize(i);
            Double.isNaN(size);
            view.measure(View.MeasureSpec.makeMeasureSpec((int) Math.min(size * 0.865d, this.f844a * 300.0f), View.MeasureSpec.getMode(i)), i2);
            int measuredWidth = this.f847d.getMeasuredWidth();
            this.f849f = measuredWidth;
            this.f850g = this.f848e ? 0 : -measuredWidth;
            setMeasuredDimension(ViewGroup.resolveSize(getSuggestedMinimumWidth(), i), ViewGroup.resolveSize(getSuggestedMinimumHeight(), i2));
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int i = 2;
            boolean z = false;
            if (action == 1 || action == 3) {
                if (this.f854k) {
                    m855c(false);
                } else if (this.f853j) {
                    if (m856b(motionEvent)) {
                        if (motionEvent.getX() > this.f851h) {
                            z = true;
                        }
                        m855c(z);
                    } else {
                        int i2 = this.f850g;
                        int i3 = this.f849f;
                        if (this.f848e) {
                            i = 1;
                        }
                        if (i2 > (-((i3 * i) / 3))) {
                            z = true;
                        }
                        m855c(z);
                    }
                }
            } else if (action == 2) {
                if (this.f854k && Math.max(Math.abs(motionEvent.getX() - this.f851h), Math.abs(motionEvent.getY() - this.f852i)) > ((float) this.f845b)) {
                    this.f854k = false;
                }
                int i4 = this.f849f;
                int max = Math.max(-i4, Math.min(0, (int) ((((float) (this.f848e ? 0 : -i4)) + motionEvent.getX()) - this.f851h)));
                this.f850g = max;
                View view = this.f847d;
                view.layout(max, 0, this.f849f + max, view.getMeasuredHeight());
                this.f846c.invalidate();
            }
            return true;
        }
    }

    public static class TopLayout extends LinearLayout {
        public TopLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void m854a(View view) {
            addViewInLayout(view, -1, view.getLayoutParams(), true);
        }

        public void m853b(View view) {
            removeViewInLayout(view);
        }
    }

    public class View$OnClickListenerC0221a implements View.OnClickListener {
        View$OnClickListenerC0221a() {
         //   MainLayout.this = r1;
        }

        @Override
        public void onClick(View view) {
            boolean checked = ((Widgets$CheckboxView) view).getChecked();
            int i = 8;
            MainLayout.this.f825h.setVisibility(checked ? 8 : 0);
            MainLayout.this.f824g.findViewById(R.id.nav_bottom).setVisibility(checked ? 8 : 0);
            View findViewById = MainLayout.this.f824g.findViewById(R.id.nav_user_menu);
            if (checked) {
                i = 0;
            }
            findViewById.setVisibility(i);
        }
    }

    public class View$OnClickListenerC0222b implements View.OnClickListener {
        View$OnClickListenerC0222b() {
           // MainLayout.this = r1;
        }

        @Override
        public void onClick(View view) {
            ((Widgets$CheckboxView) MainLayout.this.f824g.findViewById(R.id.nav_expander)).m850b();
        }
    }

    class View$OnClickListenerC0223c implements View.OnClickListener {
        View$OnClickListenerC0223c() {
            //MainLayout.this = r1;
        }

        @Override
        public void onClick(View view) {
            MainLayout.this.m866p(true);
        }
    }

    class View$OnClickListenerC0224d implements View.OnClickListener {
        View$OnClickListenerC0224d() {
         //   MainLayout.this = r1;
        }

        @Override
        public void onClick(View view) {
            Tab f1Var = MainLayout.this.tab;
            if (f1Var != null) {
                f1Var.forumsListView.setSelection(0);
            }
        }
    }

    class View$OnClickListenerC0225e implements View.OnClickListener {
        View$OnClickListenerC0225e() {
         //   MainLayout.this = r1;
        }

        @Override
        public void onClick(View view) {
            int intValue;
            Tab f1Var = MainLayout.this.tab;
            if (!(f1Var == null || f1Var.forumsListView == null || (intValue = ((Integer) view.getTag()).intValue()) <= 0)) {
                int i = 1;
                if (intValue > 1) {
                    MainLayout.this.tab.forumsListView.setSelectionFromTop(0, 0);
                } else {
                    int count = MainLayout.this.tab.page.getCount();
                    if (count > 1) {
                        MainLayout mainLayout = MainLayout.this;
                        int[] iArr = mainLayout.tab.page.f1071B;
                        if (iArr == null || iArr[iArr.length - 1] <= mainLayout.getHeight()) {
                            MainLayout.this.tab.forumsListView.setSelection(count);
                        } else {
                            MainLayout mainLayout2 = MainLayout.this;
                            Tab.ForumsListView gVar = mainLayout2.tab.forumsListView;
                            int height = mainLayout2.getHeight();
                            int[] iArr2 = MainLayout.this.tab.page.f1071B;
                            gVar.setSelectionFromTop(count, height - iArr2[iArr2.length - 1]);
                        }
                    } else {
                        View childAt = MainLayout.this.tab.forumsListView.getChildAt(0);
                        Tab.ForumsListView gVar2 = MainLayout.this.tab.forumsListView;
                        if (childAt != gVar2.f1474b) {
                            i = 0;
                        }
                        int height2 = (gVar2.getChildAt(i).getHeight() + (MainLayout.this.tab.forumsListView.f1474b.getHeight() / 2)) - MainLayout.this.tab.forumsListView.getHeight();
                        if (height2 > 0) {
                            MainLayout.this.tab.forumsListView.setSelectionFromTop(0, -height2);
                        }
                    }
                }
            }
            MainLayout.this.f822e.m849a(false);
            view.setTag(0);
        }
    }

    public class RunnableC0226f implements Runnable {
        final View f862a;

        RunnableC0226f(MainLayout mainLayout, View view) {
            this.f862a = view;
        }

        @Override
        public void run() {
            this.f862a.requestFocus();
        }
    }

    public class RunnableC0227g implements Runnable {
        final InputMethodManager f863a;
        final View f864b;

        RunnableC0227g(MainLayout mainLayout, InputMethodManager inputMethodManager, View view) {
            this.f863a = inputMethodManager;
            this.f864b = view;
        }

        @Override
        public void run() {
            this.f863a.showSoftInput(this.f864b, 1);
        }
    }

    public class RunnableC0228h implements Runnable {
        RunnableC0228h() {
        //    MainLayout.this = r1;
        }

        @Override
        public void run() {
            if (4 == MainLayout.this.f818b.getVisibility() && MainLayout.this.f818b.getTag() != null) {
                MainLayout.this.f818b.setVisibility(0);
                MainLayout mainLayout = MainLayout.this;
                mainLayout.f818b.startAnimation(mainLayout.f841x);
            }
        }
    }

    public static class C0229i extends Animation {
        MainLayout f866a;
        int f867b;
        int f868c;

        public C0229i(MainLayout mainLayout, int i, int i2) {
            this.f866a = mainLayout;
            this.f867b = i;
            this.f868c = i2;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            AnimateDrawable n1Var = this.f866a.f810R;
            int i = this.f867b;
            n1Var.setAlpha((int) (((float) i) + (((float) (this.f868c - i)) * f)));
        }
    }

    public static class C0230j extends Animation {
        MainLayout f869a;
        public int f870b;

        public C0230j(MainLayout mainLayout) {
            this.f869a = mainLayout;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            int i;
            MainLayout mainLayout = this.f869a;
            int height = ((int) mainLayout.f811S) + mainLayout.f816a.getHeight();
            if (this.f870b == 11) {
                MainLayout mainLayout2 = this.f869a;
                int i2 = mainLayout2.f808P;
                int height2 = mainLayout2.getHeight();
                MainLayout mainLayout3 = this.f869a;
                i = i2 + ((int) ((((float) (height2 - mainLayout3.f808P)) - (mainLayout3.f811S - ((float) mainLayout3.f809Q))) * f));
            } else {
                int i3 = this.f869a.f808P;
                i = ((int) (((float) (height - i3)) * f)) + i3;
            }
            this.f869a.m871k(i - this.f869a.f807O.getTop(), false);
            this.f869a.f810R.m562f(1.0f - f);
        }
    }

    public static class C0231k extends Animation {
        MainLayout f871a;

        public C0231k(MainLayout mainLayout) {
            this.f871a = mainLayout;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            int height = this.f871a.getHeight();
            MainLayout mainLayout = this.f871a;
            int i = mainLayout.f808P;
            float f2 = mainLayout.f811S;
            int i2 = mainLayout.f809Q;
            this.f871a.m871k((i + ((int) ((((float) (height - i)) < f2 - ((float) i2) ? (float) (mainLayout.getHeight() - this.f871a.f808P) : (float) (i2 - i)) * f))) - this.f871a.f807O.getTop(), false);
        }
    }

    public static class C0232l extends Animation {
        MainLayout f872a;

        public C0232l(MainLayout mainLayout) {
            this.f872a = mainLayout;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            this.f872a.m872j(1.0f - f);
        }
    }

    public static class C0233m extends Animation {
        MainLayout f873a;

        public C0233m(MainLayout mainLayout) {
            this.f873a = mainLayout;
        }

        @Override
        protected void applyTransformation(float f, Transformation transformation) {
            this.f873a.m872j(f);
        }
    }

    public class View$OnTouchListenerC0234n implements View.OnTouchListener {
        boolean f874a;
        float f875b;
        ImageView f876c;
        int f877d;
        int f878e;

        private View$OnTouchListenerC0234n() {
          //  MainLayout.this = r1;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                view.setPressed(true);
                this.f875b = motionEvent.getX();
            } else if (2 == action) {
                float x = motionEvent.getX();
                if (this.f874a) {
                    int i = this.f877d + ((int) ((x - this.f875b) + 0.5f));
                    if (i < 0) {
                        i = 0;
                    }
                    this.f876c.layout(i, this.f878e, view.getWidth() + i, this.f878e + view.getHeight());
                } else if (x - this.f875b > ((float) MainLayout.this.f839v)) {
                    this.f874a = true;
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    view.setDrawingCacheEnabled(true);
                    ImageView imageView = new ImageView(view.getContext());
                    this.f876c = imageView;
                    imageView.setPadding(0, 0, 0, 0);
                    this.f876c.setImageBitmap(Bitmap.createBitmap(view.getDrawingCache(false)));
                    this.f876c.setLayoutParams(new LinearLayout.LayoutParams(view.getWidth(), view.getHeight()));
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    view.getLocationInWindow(iArr);
                    MainLayout.this.f795C.getLocationInWindow(iArr2);
                    this.f877d = iArr[0] - iArr2[0];
                    this.f878e = iArr[1] - iArr2[1];
                    this.f876c.setScaleType(ImageView.ScaleType.FIT_START);
                    MainLayout.this.f795C.m854a(this.f876c);
                    ImageView imageView2 = this.f876c;
                    int i2 = this.f877d;
                    imageView2.layout(i2, this.f878e, view.getWidth() + i2, this.f878e + view.getHeight());
                    view.setDrawingCacheEnabled(false);
                    view.setVisibility(4);
                }
            } else if (1 == action || 3 == action) {
                view.setPressed(false);
                if (this.f874a) {
                    MainLayout.this.f795C.m853b(this.f876c);
                    if (1 != action || motionEvent.getX() - this.f875b <= ((float) (view.getWidth() / 3))) {
                        view.setVisibility(0);
                    } else {
                        MainLayout.this.removeTab((Tab) view.getTag());
                    }
                    this.f876c = null;
                    this.f874a = false;
                } else if (1 == action) {
                    if (Build.VERSION.SDK_INT >= 15) {
                        view.callOnClick();
                    } else {
                        view.performClick();
                    }
                }
            }
            return false;
        }

        View$OnTouchListenerC0234n(MainLayout mainLayout, View$OnClickListenerC0221a aVar) {
            this();
        }
    }

    public MainLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m879c(context);
    }

    public void removeTab(Tab f1Var) {
        if (this.tab != f1Var) {
            f1Var.removeTab();
        } else if (this.tabList.size() >= 2) {
            this.tabList.remove(this.tab);
            this.f793A.remove(this.tab);
            f1Var.f1448f = true;
            Tab.f1442w = true;
            setCurrentTab(this.f793A.get(0));
            Tab.f1442w = false;
        } else {
            f1Var.removeTab();
            this.tab = null;
            f1Var.f1443a.finish();
        }
    }

    public void m881a() {
        Vector vector = new Vector(this.tabList);
        for (int i = 0; i < vector.size(); i++) {
            ((Tab) vector.get(i)).removeTab();
        }
        this.tab = null;
    }

    public void m880b() {
        Vector vector = new Vector(this.tabList);
        Tab f1Var = this.tab;
        if (f1Var != null) {
            vector.remove(f1Var);
        }
        for (int i = 0; i < vector.size(); i++) {
            ((Tab) vector.get(i)).removeTab();
        }
    }

    void m879c(Context context) {
        this.f833p = context.getResources().getDisplayMetrics().density;
        this.f834q = context.getResources().getDisplayMetrics().scaledDensity;
        this.tabList = new Vector();
        this.f793A = new Vector();
        this.f794B = new View$OnTouchListenerC0234n(this, null);
        this.f839v = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f841x = AnimationUtils.loadAnimation(context, R.anim.loading_text);
        setWillNotDraw(false);
        m877e();
    }

    void m878d(View view) {
        view.clearAnimation();
        int i = Build.VERSION.SDK_INT;
        if (i >= 14) {
            view.animate().cancel();
        }
        if (view.getAnimation() != null && i >= 11) {
            view.getAnimation().cancel();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        int repeatCount = keyEvent.getRepeatCount();
        if (4 == keyCode) {
            if (1 == action && repeatCount == 0 && !this.f835r && this.tab != null) {
                if (this.f823f.m857a()) {
                    m866p(false);
                } else if (!this.tab.m724b()) {
                    removeTab(this.tab);
                }
            }
            return true;
        } else if (82 != keyCode) {
            return super.dispatchKeyEvent(keyEvent);
        } else {
            if (action == 0 && repeatCount == 0 && this.f798F) {
                m866p(!this.f823f.m857a());
            }
            return true;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        Tab f1Var;
        Tab.ForumsListView gVar;
        Page a0Var;
        Tab f1Var2;
        Tab f1Var3;
        int i = Build.VERSION.SDK_INT;
        int action = motionEvent.getAction() & -65281;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z2 = false;
        if (action == 0) {
            this.f840w = false;
            this.f836s = 1;
            this.f837t = x;
            this.f838u = y;
        } else if (5 == action) {
            if (1 < motionEvent.getPointerCount() && 1 == this.f836s) {
                this.f836s = 0;
            }
        } else if (1 == action || 6 == action || 3 == action) {
            this.f797E = false;
            int i2 = this.f836s;
            if (8 == i2) {
                float f = this.f837t - x;
                float abs = Math.abs(this.f838u - y);
                Tab f1Var4 = this.tab;
                if (f1Var4 != null && f > this.f833p * 120.0f && abs / f < 0.6f) {
                    f1Var4.page.mo763C();
                }
                this.f821d.setVisibility(8);
            } else if (9 == i2) {
                float f2 = x - this.f837t;
                float abs2 = Math.abs(this.f838u - y);
                Tab f1Var5 = this.tab;
                if (f1Var5 != null && f2 > this.f833p * 120.0f && abs2 / f2 < 0.6f) {
                    f1Var5.page.mo762D();
                }
                this.f820c.setVisibility(8);
            } else if (10 == i2 || 11 == i2) {
                boolean z3 = Math.abs((y - this.f838u) * 0.5f) >= this.f811S;
                this.f803K = z3;
                if (z3) {
                    this.tab.page.mo192E();
                } else {
                    this.f810R.m556l(0.0f, 0.0f);
                    this.f810R.m554n(false);
                }
                boolean z4 = this.f803K;
                Animation animation = z4 ? this.f817a0 : this.f819b0;
                if (z4) {
                    ((C0230j) animation).f870b = this.f836s;
                }
                this.f808P = this.f804L;
                animation.reset();
                animation.setDuration(200);
                animation.setInterpolator(this.f806N);
                this.f807O.setAnimationListener(this);
                this.f807O.clearAnimation();
                this.f807O.startAnimation(animation);
            }
            this.f836s = 0;
            boolean z5 = this.f840w;
            this.f840w = false;
            z2 = z5;
        } else if (2 == action) {
            if (this.f797E) {
                this.f836s = 0;
            }
            float abs3 = Math.abs(x - this.f837t);
            float abs4 = Math.abs(y - this.f838u);
            int i3 = this.f836s;
            if (1 == i3) {
                if (abs3 > abs4 && abs3 > ((float) this.f839v)) {
                    float f3 = this.f837t;
                    if (x < f3 && Prefs.f1140A && (f1Var3 = this.tab) != null && !f1Var3.f1450h && this.f800H) {
                        this.f836s = 8;
                        if (i >= 11) {
                            this.f821d.setAlpha(0.0f);
                        }
                        int height = (getHeight() - this.f821d.getMeasuredHeight()) / 2;
                        this.f821d.layout(getWidth(), height, getWidth() + this.f821d.getMeasuredWidth(), this.f821d.getMeasuredHeight() + height);
                        this.f821d.setVisibility(0);
                    } else if (x > f3 && Prefs.f1140A && (f1Var2 = this.tab) != null && !f1Var2.f1450h && this.f799G) {
                        this.f836s = 9;
                        if (i >= 11) {
                            this.f820c.setAlpha(0.0f);
                        }
                        int height2 = (getHeight() - this.f820c.getMeasuredHeight()) / 2;
                        Widgets$CircleImageView widgets$CircleImageView = this.f820c;
                        widgets$CircleImageView.layout(-widgets$CircleImageView.getMeasuredWidth(), height2, 0, this.f820c.getMeasuredHeight() + height2);
                        this.f820c.setVisibility(0);
                    }
                    z = true;
                    z2 = true;
                } else if (abs4 > abs3 && abs4 > ((float) this.f839v) && this.f801I && !this.f803K && !this.f813U && (f1Var = this.tab) != null && !f1Var.f1450h && (gVar = f1Var.forumsListView) != null && gVar.getChildCount() > 0 && (a0Var = this.tab.page) != null && a0Var.isUnsucces()) {
                    if (y > this.f838u && Prefs.f1142C && this.tab.forumsListView.getFirstVisiblePosition() == 0 && this.tab.forumsListView.getChildAt(0).getTop() == 0) {
                        this.f813U = true;
                        m878d(this);
                        m878d(this.f807O);
                        this.f838u = y;
                        this.f836s = 10;
                        this.f805M = 0.0f;
                    } else if (y < this.f838u && Prefs.f1143D && this.tab.forumsListView.getLastVisiblePosition() == this.tab.page.getCount() && this.tab.page.getCount() > 0) {
                        Tab.ForumsListView gVar2 = this.tab.forumsListView;
                        if (gVar2.getChildAt(gVar2.getChildCount() - 1).getBottom() == this.tab.forumsListView.getHeight()) {
                            this.f813U = true;
                            m878d(this);
                            m878d(this.f807O);
                            this.f838u = y;
                            this.f836s = 11;
                            this.f805M = 0.0f;
                        }
                    }
                    z = true;
                    z2 = true;
                }
                if (z2) {
                    try {
                        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, 0.0f, 0.0f, 0);
                        super.dispatchTouchEvent(obtain);
                        obtain.recycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                        //ACRA.getErrorReporter().handleSilentException(new Exception("ML.DispatchTouchEvent cancelEvent", e));
                    }
                    this.f840w = true;
                }
                z2 = z;
            } else if (9 == i3) {
                int i4 = (int) (x - this.f837t);
                float f4 = this.f833p * 120.0f;
                if (((float) i4) >= f4) {
                    i4 = (int) f4;
                    if (i >= 11) {
                        this.f820c.setAlpha(1.0f);
                    }
                } else if (i >= 11) {
                    this.f820c.setAlpha(Math.min(1.0f, Math.max(0.0f, i4 < this.f820c.getMeasuredWidth() ? 0.0f : (((float) (i4 - this.f820c.getMeasuredWidth())) * 1.5f) / f4)));
                }
                int height3 = (getHeight() - this.f820c.getMeasuredHeight()) / 2;
                Widgets$CircleImageView widgets$CircleImageView2 = this.f820c;
                widgets$CircleImageView2.layout(i4 - widgets$CircleImageView2.getMeasuredWidth(), height3, i4, this.f820c.getMeasuredHeight() + height3);
            } else if (8 == i3) {
                int i5 = (int) (this.f837t - x);
                float f5 = this.f833p * 120.0f;
                if (((float) i5) >= f5) {
                    i5 = (int) f5;
                    if (i >= 11) {
                        this.f821d.setAlpha(1.0f);
                    }
                } else if (i >= 11) {
                    this.f821d.setAlpha(Math.min(1.0f, Math.max(0.0f, i5 < this.f821d.getMeasuredWidth() ? 0.0f : (((float) (i5 - this.f821d.getMeasuredWidth())) * 1.5f) / f5)));
                }
                int height4 = (getHeight() - this.f821d.getMeasuredHeight()) / 2;
                this.f821d.layout(getWidth() - i5, height4, (getWidth() - i5) + this.f821d.getMeasuredWidth(), this.f821d.getMeasuredHeight() + height4);
            } else if (10 == i3) {
                float f6 = (y - this.f838u) * 0.5f;
                if (f6 > 0.0f) {
                    m876f(f6);
                }
            } else if (11 == i3) {
                float f7 = (y - this.f838u) * 0.5f;
                if (f7 < 0.0f) {
                    m876f(f7);
                }
            }
            z = false;
            if (z2) {
            }
            z2 = z;
        }
        if (this.f840w) {
            z2 = true;
        }
        if (!z2 && !z2) {
            try {
                if (!super.dispatchTouchEvent(motionEvent)) {
                    this.f840w = true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(new Exception("ML.DispatchTouchEvent", e2));
            }
        }
        return true;
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Tab f1Var;
        Tab f1Var2;
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        if (2 == action) {
            if (y < 0.0f && (f1Var2 = this.tab) != null && !f1Var2.f1450h) {
                m868n(true);
            } else if (y > 0.0f && this.f830m && (f1Var = this.tab) != null && f1Var.forumsListView.getFirstVisiblePosition() > 0) {
                m868n(false);
            }
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    void m877e() {
        this.f806N = new DecelerateInterpolator(2.0f);
        float f = this.f833p;
        this.f812T = (int) (40.0f * f);
        this.f811S = f * 64.0f;
        this.f802J = Skin.C0353a.f1374b0;
        this.f807O = new Widgets$CircleImageView(getContext(), this.f802J, 20.0f);
        AnimateDrawable n1Var = new AnimateDrawable(getContext(), this);
        this.f810R = n1Var;
        int i = Skin.C0353a.f1376c0;
        int i2 = Skin.C0353a.f1378d0;
        int i3 = Skin.C0353a.f1380e0;
        int i4 = 1;
        int[] iArr = new int[(i != 0 ? 1 : 0) + (i2 != 0 ? 1 : 0) + (i3 != 0 ? 1 : 0)];
        if (i != 0) {
            iArr[0] = i;
        } else {
            i4 = 0;
        }
        if (i2 != 0) {
            iArr[i4] = i2;
            i4++;
        }
        if (i3 != 0) {
            iArr[i4] = i3;
        }
        n1Var.m560h(iArr);
        this.f810R.m561g(this.f802J);
        this.f807O.setImageDrawable(this.f810R);
        this.f807O.setVisibility(8);
        addView(this.f807O, 0);
        this.f817a0 = new C0230j(this);
        this.f819b0 = new C0231k(this);
        this.f814V = new C0233m(this);
        this.f815W = new C0232l(this);
    }

    void m876f(float f) {
        int i;
        int i2 = 0;
        if (this.f807O.getVisibility() != 0) {
            this.f807O.setVisibility(0);
            this.f810R.setAlpha(76);
        }
        this.f810R.m554n(true);
        float min = Math.min(1.0f, Math.abs(Math.abs(f) / this.f811S));
        float abs = Math.abs(f);
        float f2 = this.f811S;
        float f3 = abs - f2;
        float f4 = f2 - ((float) this.f809Q);
        double max = Math.max(0.0f, Math.min(f3, f4 * 2.0f) / f4) / 4.0f;
        double pow = Math.pow(max, 2.0d);
        Double.isNaN(max);
        float f5 = ((float) (max - pow)) * 2.0f;
        float f6 = f4 * f5 * 2.0f;
        if (f > 0.0f) {
            int i3 = this.f809Q;
            if (this.f836s != 11) {
                i2 = this.f816a.getHeight();
            }
            i = i3 + i2 + ((int) ((f4 * min) + f6));
        } else {
            int height = getHeight();
            if (this.f836s != 11) {
                i2 = this.f816a.getHeight();
            }
            i = (height - i2) - ((int) ((f4 * min) + f6));
        }
        m871k(i - this.f804L, true);
        double d = min;
        Double.isNaN(d);
        float max2 = (((float) Math.max(d - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        this.f810R.m556l(0.0f, Math.min(0.8f, max2 * 0.8f));
        this.f810R.m562f(Math.min(1.0f, max2));
        this.f810R.m559i((((max2 * 0.4f) - 16.0f) + (f5 * 2.0f)) * 0.5f);
        if (Math.abs(f) >= this.f811S && Math.abs(this.f805M) < this.f811S) {
            m869m(this.f810R.getAlpha(), 255);
        } else if (Math.abs(f) < this.f811S && Math.abs(this.f805M) >= this.f811S) {
            m869m(this.f810R.getAlpha(), 76);
        }
        this.f805M = f;
    }

    void m875g(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        getMeasuredHeight();
        if (getChildCount() != 0) {
            int measuredWidth2 = this.f807O.getMeasuredWidth();
            int measuredHeight = this.f807O.getMeasuredHeight();
            int i5 = measuredWidth / 2;
            int i6 = measuredWidth2 / 2;
            int i7 = this.f804L;
            this.f807O.layout(i5 - i6, i7, i5 + i6, measuredHeight + i7);
        }
    }

    public int getActionBarHeight() {
        return this.f816a.getMeasuredHeight();
    }

    void m874h(int i, int i2) {
        this.f807O.measure(View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824));
        if (this.f809Q == 0) {
            int i3 = -this.f807O.getMeasuredHeight();
            this.f809Q = i3;
            this.f804L = i3;
        }
    }

    void m873i(int i) {
        this.f807O.getBackground().setAlpha(i);
        this.f810R.setAlpha(i);
    }

    void m872j(float f) {
        if (Build.VERSION.SDK_INT < 11) {
            m873i((int) (f * 255.0f));
            return;
        }
        this.f807O.setScaleX(f);
        this.f807O.setScaleY(f);
    }

    void m871k(int i, boolean z) {
        this.f807O.offsetTopAndBottom(i);
        this.f804L = this.f807O.getTop();
        if (z && Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    public void m870l(boolean z) {
        if (this.f803K != z) {
            this.f803K = z;
            if (z) {
                m871k((((int) this.f811S) + this.f816a.getHeight()) - this.f804L, true);
                this.f807O.setVisibility(0);
            }
            Animation animation = this.f803K ? this.f814V : this.f815W;
            animation.setDuration(150);
            this.f807O.setAnimationListener(this);
            this.f807O.clearAnimation();
            this.f807O.startAnimation(animation);
        }
    }

    Animation m869m(int i, int i2) {
        C0229i iVar = new C0229i(this, i, i2);
        iVar.setDuration(300);
        this.f807O.setAnimationListener(null);
        this.f807O.clearAnimation();
        this.f807O.startAnimation(iVar);
        return iVar;
    }

    public void m868n(boolean z) {
        Tab f1Var;
        try {
            this.f831n = z;
            int measuredHeight = this.f816a.getMeasuredHeight();
            int i = z ? 0 : -measuredHeight;
            this.f832o = i;
            View view = this.f816a;
            view.layout(0, i, view.getMeasuredWidth(), this.f832o + measuredHeight);
            if (!this.f831n && (f1Var = this.tab) != null) {
                Tab.ForumsListView gVar = f1Var.forumsListView;
                if (gVar.f1474b == gVar.getChildAt(0)) {
                    Tab.ForumsListView gVar2 = this.tab.forumsListView;
                    gVar2.setSelection(gVar2.getHeaderViewsCount());
                }
            }
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
        }
    }

    public void m867o(boolean z) {
        this.f816a.findViewById(R.id.bar_caption).setBackgroundDrawable(z ? null : this.f796D.skin.m736f(R.drawable.button_bg));
        int i = 0;
        this.f827j.setVisibility(z ? 0 : 8);
        TextView textView = this.f826i;
        if (z) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (this.f803K) {
            this.f810R.start();
            this.f804L = this.f807O.getTop();
        } else {
            this.f807O.clearAnimation();
            this.f810R.stop();
            this.f807O.setVisibility(8);
            m871k(this.f809Q - this.f804L, true);
            this.f804L = this.f807O.getTop();
            if (Build.VERSION.SDK_INT >= 11) {
                this.f807O.setScaleX(1.0f);
                this.f807O.setScaleY(1.0f);
            }
            this.f813U = false;
        }
        m873i(255);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Toast.makeText(getContext(), "repeat", 0).show();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    @SuppressLint({"NewApi"})
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f816a = findViewById(R.id.actionBar);
        this.f818b = findViewById(R.id.loadingText);
        this.f820c = findViewById(R.id.prev);
        this.f821d = findViewById(R.id.next);
        this.f822e = findViewById(R.id.fling);
        View view = this.f816a;
        if (view != null) {
            View findViewById = view.findViewById(R.id.bar_panel);
            this.f828k = findViewById;
            findViewById.setOnClickListener(new View$OnClickListenerC0223c());
            View findViewById2 = this.f816a.findViewById(R.id.bar_up);
            this.f829l = findViewById2;
            findViewById2.setOnClickListener(new View$OnClickListenerC0224d());
            this.f829l.setEnabled(false);
            this.f826i = this.f816a.findViewById(R.id.bar_title);
            this.f827j = this.f816a.findViewById(R.id.bar_logo);
        }
        Widgets$CircleImageView widgets$CircleImageView = this.f822e;
        if (widgets$CircleImageView != null) {
            widgets$CircleImageView.setOnClickListener(new View$OnClickListenerC0225e());
            this.f822e.setTag(0);
        }
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.f816a;
        view.layout(0, this.f832o, view.getMeasuredWidth(), this.f832o + this.f816a.getMeasuredHeight());
        Tab f1Var = this.tab;
        if (f1Var != null) {
            f1Var.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
        this.f818b.layout(0, getMeasuredHeight() - this.f818b.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
        m875g(z, i, i2, i3, i4);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        this.f832o = this.f831n ? 0 : -this.f816a.getMeasuredHeight();
        setMeasuredDimension(ViewGroup.resolveSize(getSuggestedMinimumWidth(), i), ViewGroup.resolveSize(getSuggestedMinimumHeight(), i2));
        Tab f1Var = this.tab;
        if (f1Var != null) {
            if (f1Var.forumsListView == null) {
                //ACRA.getErrorReporter().handleSilentException(new Exception("closed tab is current", this.f842y.f1463u));
            }
            this.tab.forumsListView.f1474b.getLayoutParams().height = this.f816a.getMeasuredHeight();
        }
        m874h(i, i2);
        this.f820c.measure(View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824));
        this.f821d.measure(View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f812T, 1073741824));
        this.f822e.measure(i, i2);
    }

    public void m866p(boolean z) {
        this.f823f.m855c(z);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.f797E = z;
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setCurrentTab(Tab f1Var) {
        Tab f1Var2 = this.tab;
        if (f1Var2 != f1Var) {
            boolean z = f1Var2 != null;
            if (z) {
                f1Var2.m718h();
            }
            this.tab = f1Var;
            if (f1Var != null) {
                this.f793A.remove(f1Var);
                this.f793A.add(0, this.tab);
                this.tab.m712n(z);
            }
        }
        showBageRunningStatus();
    }

    public void setTitleBarClickListener(View.OnClickListener onClickListener) {
        this.f816a.findViewById(R.id.bar_caption).setOnClickListener(onClickListener);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void m860v(MainActivity mainActivity) {
        this.f796D = mainActivity;
        TopLayout topLayout = mainActivity.findViewById(R.id.top_layout);
        this.f795C = topLayout;
        DrawerLayout drawerLayout = topLayout.findViewById(R.id.drawer_layout);
        this.f823f = drawerLayout;
        View findViewById = drawerLayout.findViewById(R.id.navScroll);
        this.f824g = findViewById;
        this.f825h = findViewById.findViewById(R.id.nav_tabs);
        this.f824g.findViewById(R.id.nav_expander).setOnClickListener(new View$OnClickListenerC0221a());
        this.f824g.findViewById(R.id.nav_top).setOnClickListener(new View$OnClickListenerC0222b());
    }

    public void m859w(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.f796D.getSystemService("input_method");
        if (view != null) {
            view.post(new RunnableC0226f(this, view));
            view.postDelayed(new RunnableC0227g(this, inputMethodManager, view), 400);
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(this.f795C.getWindowToken(), 0);
    }

    public void showBageRunningStatus() {
        boolean z;
        Page a0Var;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Tab currentTab = this.tab;
        if (currentTab == null || (a0Var = currentTab.page) == null || TextUtils.isEmpty(a0Var.f1092z)) {
            z = false;
        } else {
            spannableStringBuilder.append(this.tab.page.f1092z);
            z = this.tab.page.f1070A;
        }
        DocumentManager currentDocumentManager = DocumentManager.documentManager;
        if (currentDocumentManager != null) {
            synchronized (currentDocumentManager.requestSparseArray) {
                SparseArray<DocumentManager.IGenerateRequest> sparseArray = DocumentManager.documentManager.requestSparseArray;
                int i = 0;
                for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                    try {
                        if (!TextUtils.isEmpty(sparseArray.valueAt(i2).statusMessage)) {
                            i++;
                        }
                    } catch (Exception unused) {
                        unused.printStackTrace();
                        DocumentManager.IGenerateRequest valueAt = sparseArray.valueAt(i2);
                        unused.printStackTrace();
                      //  ErrorReporter errorReporter = //ACRA.getErrorReporter();
                      //  errorReporter.handleSilentException(new Exception("Got " + valueAt.getClass().getCanonicalName() + " at " + i2 + "/" + sparseArray.size()));
                        this.f818b.clearAnimation();
                        this.f818b.setTag(null);
                        this.f818b.setVisibility(4);
                        return;
                    }
                }
                if (i > 0) {
                    int U = DocumentManager.getErrorStatusCode();
                    if (DocumentManager.documentManager.isWebSocketConnected()) {
                        U |= 4;
                    }
                    if (DocumentManager.documentManager.isConnected()) {
                        U |= 8;
                    }
                    if (DocumentManager.documentManager.isAltPath()) {
                        U |= 16;
                    }
                    int length = spannableStringBuilder.length();
                    spannableStringBuilder.append(String.format("[%02X:%d] ",
                            U, DocumentManager.documentManager.f2766v));
                    int status = DocumentManager.documentManager.conStatus;
                    int i4 = 0xff808000;
                    if (status == 0) {
                        spannableStringBuilder.append("Не подключен");
                        i4 = 0xff800000;
                    } else if (1 == status) {
                        spannableStringBuilder.append("Подключаюсь");
                    } else if (2 == status) {
                        spannableStringBuilder.append("Настраиваюсь");
                    } else if (3 == status) {
                        spannableStringBuilder.append("Подключен");
                        i4 = 0xff008000;
                    } else if (4 == status) {
                        spannableStringBuilder.append("Проверяю");
                    } else {
                        i4 = 0;
                    }
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(i4), length, spannableStringBuilder.length(), 33);
                    for (int i5 = 0; i5 < sparseArray.size(); i5++) {
                        DocumentManager.IGenerateRequest valueAt2 = sparseArray.valueAt(i5);
                        if (!TextUtils.isEmpty(valueAt2.statusMessage)) {
                            if (spannableStringBuilder.length() > 0) {
                                spannableStringBuilder.append("\n");
                            }
                            spannableStringBuilder.append(valueAt2.statusMessage);
                            if (!valueAt2.isAuth) {
                                z = true;
                            }
                        }
                    }
                }
            }
        }
        int i6 = this.f836s;
        if (!(10 == i6 || 11 == i6)) {
            m870l(z);
        }
        this.f818b.setText(spannableStringBuilder);
        if (spannableStringBuilder.length() <= 0) {
            this.f818b.clearAnimation();
            this.f818b.setTag(null);
            this.f818b.setVisibility(4);
        } else if (this.f818b.getTag() == null) {
            this.f818b.setTag(1);
            postDelayed(new RunnableC0228h(), z ? 3000 : 100);
        }
    }
}
