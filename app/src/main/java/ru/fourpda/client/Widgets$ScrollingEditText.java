package ru.fourpda.client;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Widgets$ScrollingEditText extends EditText {
    private static Pattern f909d;
    protected FastScrollBar f910a;
    private BackgroundColorSpan f911b;
    private final Runnable f912c = new RunnableC0242c();

    public class C0240a implements FastScrollBar.AbstractC0546a {
        C0240a() {
        }

        @Override
        public void mo604a(View view, double d) {
            int lineCount;
            Layout layout = Widgets$ScrollingEditText.this.getLayout();
            if (layout != null && (lineCount = layout.getLineCount()) != 0) {
                Widgets$ScrollingEditText widgets$ScrollingEditText = Widgets$ScrollingEditText.this;
                double lineBottom = (double) (layout.getLineBottom(lineCount - 1) - Widgets$ScrollingEditText.this.getHeight());
                Double.isNaN(lineBottom);
                widgets$ScrollingEditText.scrollTo(0, (int) (lineBottom * d));
            }
        }
    }

    public class C0241b implements TextWatcher {
        C0241b() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Widgets$ScrollingEditText.this.m835e();
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class RunnableC0242c implements Runnable {
        RunnableC0242c() {
        }

        @Override
        public void run() {
            boolean z;
            int indexOf;
            try {
                int i = 2;
                if (Widgets$ScrollingEditText.f909d == null) {
                    Pattern unused = Widgets$ScrollingEditText.f909d = Pattern.compile("(?:\\[(/)?(attachment|background|b|center|code|color|cur|ex|font|hide|img|i|left|list|mergetime|mod|offtop|quote|right|size|snapback|spoiler|sub|sup|s|url|u|\\*))([^]\\[]*?)]", 2);
                }
                Editable text = Widgets$ScrollingEditText.this.getText();
                if (text.length() != 0) {
                    C0244e[] eVarArr = (C0244e[]) text.getSpans(0, text.length(), C0244e.class);
                    for (int length = eVarArr.length - 1; length >= 0; length--) {
                        text.removeSpan(eVarArr[length]);
                    }
                    Layout layout = Widgets$ScrollingEditText.this.getLayout();
                    if (layout != null) {
                        int length2 = text.length();
                        int lineCount = layout.getLineCount();
                        int scrollY = Widgets$ScrollingEditText.this.getScrollY();
                        int height = Widgets$ScrollingEditText.this.getHeight();
                        int i2 = length2;
                        int i3 = 0;
                        int i4 = -1;
                        while (i3 < lineCount) {
                            if (i4 == -1 && layout.getLineBottom(i3) >= scrollY) {
                                i4 = i3;
                            } else if (layout.getLineTop(i3) > scrollY + height) {
                                break;
                            }
                            i3++;
                            i2 = i3;
                        }
                        int lineStart = layout.getLineStart(Math.max(0, i4 - 5));
                        Matcher matcher = Widgets$ScrollingEditText.f909d.matcher(text.subSequence(lineStart, layout.getLineEnd(Math.min(lineCount - 1, i2 + 5))));
                        while (matcher.find()) {
                            String group = matcher.group(i);
                            text.setSpan(new C0244e(group, matcher.group(1) != null, false, Skin.C0353a.f1360P), matcher.start(0) + lineStart, matcher.end(0) + lineStart, 33);
                            String group2 = matcher.group(3);
                            if (group2 != null) {
                                int start = matcher.start(3);
                                int i5 = -1;
                                while (true) {
                                    int indexOf2 = group2.indexOf(61, i5 + 1);
                                    if (indexOf2 >= 0) {
                                        int i6 = indexOf2 + 1;
                                        char charAt = group2.charAt(i6);
                                        if (!('\"' == charAt || '\'' == charAt)) {
                                            z = false;
                                            if (z) {
                                                charAt = ' ';
                                            }
                                            indexOf = group2.indexOf(charAt, (!z ? 1 : 0) + i6);
                                            if (-1 != indexOf) {
                                                indexOf = group2.length();
                                            } else if (z) {
                                                i6++;
                                            }
                                            int i7 = Skin.C0353a.f1362R;
                                            if (!group.equalsIgnoreCase("color") || group.equalsIgnoreCase("background")) {
                                                i7 = Util.C0424f.m646c(group2.substring(i6, indexOf), Skin.C0353a.f1365U);
                                            }
                                            int i8 = lineStart + start;
                                            text.setSpan(new C0244e(null, false, true, i7), i6 + i8, i8 + indexOf, 33);
                                            i5 = indexOf;
                                        }
                                        z = true;
                                        if (z) {
                                        }
                                        indexOf = group2.indexOf(charAt, (!z ? 1 : 0) + i6);
                                        if (-1 != indexOf) {
                                        }
                                        int i72 = Skin.C0353a.f1362R;
                                        if (!group.equalsIgnoreCase("color")) {
                                        }
                                        i72 = Util.C0424f.m646c(group2.substring(i6, indexOf), Skin.C0353a.f1365U);
                                        int i82 = lineStart + start;
                                        text.setSpan(new C0244e(null, false, true, i72), i6 + i82, i82 + indexOf, 33);
                                        i5 = indexOf;
                                    }
                                }
                            }
                            i = 2;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C0243d implements Comparator<C0244e> {
        final Editable f916a;

        C0243d(Widgets$ScrollingEditText widgets$ScrollingEditText, Editable editable) {
            this.f916a = editable;
        }

        public int compare(C0244e eVar, C0244e eVar2) {
            return this.f916a.getSpanStart(eVar) - this.f916a.getSpanStart(eVar2);
        }
    }

    private static class C0244e extends CharacterStyle implements UpdateAppearance {
        String f917a;
        boolean f918b;
        int f919c;

        C0244e(String str, boolean z, boolean z2, int i) {
            this.f917a = str;
            this.f918b = z;
            this.f919c = i;
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.f919c);
        }
    }

    public Widgets$ScrollingEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m836d(context);
    }

    public void m835e() {
        removeCallbacks(this.f912c);
        postDelayed(this.f912c, 100);
    }

    protected void m836d(Context context) {
        FastScrollBar m1Var = new FastScrollBar(getContext(), this);
        this.f910a = m1Var;
        m1Var.m607m(new C0240a());
        addTextChangedListener(new C0241b());
    }

    public int getVisibleHeight() {
        return ((getBottom() - getTop()) - getExtendedPaddingBottom()) - getExtendedPaddingTop();
    }

    @Override
    public boolean isVerticalScrollBarEnabled() {
        FastScrollBar m1Var = this.f910a;
        return m1Var == null || !m1Var.m613g();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FastScrollBar m1Var = this.f910a;
        if (m1Var != null) {
            m1Var.m617c(canvas);
        }
    }

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        FastScrollBar m1Var = this.f910a;
        if (m1Var != null) {
            m1Var.m611i(this, i2, getVisibleHeight(), getLayout().getHeight());
        }
        m835e();
    }

    @Override
    protected void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        Editable text = getText();
        BackgroundColorSpan backgroundColorSpan = this.f911b;
        if (backgroundColorSpan != null) {
            text.removeSpan(backgroundColorSpan);
        }
        if (i2 == i) {
            C0244e[] eVarArr = (C0244e[]) text.getSpans(Math.max(0, i - 500), Math.min(text.length(), i2 + 500), C0244e.class);
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 24 && i3 <= 25) {
                Arrays.sort(eVarArr, new C0243d(this, text));
            }
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            int i7 = -1;
            for (int i8 = 0; i8 < eVarArr.length; i8++) {
                int spanStart = text.getSpanStart(eVarArr[i8]);
                int spanEnd = text.getSpanEnd(eVarArr[i8]);
                if (!TextUtils.isEmpty(eVarArr[i8].f917a) && !eVarArr[i8].f917a.equalsIgnoreCase("attachment") && !eVarArr[i8].f917a.equals("*") && ((!eVarArr[i8].f918b || spanEnd >= i) && (eVarArr[i8].f918b || spanStart <= i2))) {
                    int i9 = 1;
                    if (eVarArr[i8].f918b) {
                        if (!(eVarArr[i8].f918b && i4 == -1)) {
                            break;
                        }
                        int i10 = i8 - 1;
                        while (true) {
                            if (i10 < 0) {
                                break;
                            }
                            if (eVarArr[i8].f917a.equalsIgnoreCase(eVarArr[i10].f917a)) {
                                if (!eVarArr[i10].f918b) {
                                    i9--;
                                    if (i9 == 0) {
                                        int spanStart2 = text.getSpanStart(eVarArr[i10]);
                                        if (spanStart2 <= i) {
                                            i4 = spanStart2;
                                            i7 = spanEnd;
                                        }
                                    }
                                } else {
                                    i9++;
                                }
                            }
                            i10--;
                        }
                    } else {
                        int i11 = i8 + 1;
                        while (true) {
                            if (i11 >= eVarArr.length) {
                                break;
                            }
                            if (eVarArr[i8].f917a.equalsIgnoreCase(eVarArr[i11].f917a)) {
                                if (eVarArr[i11].f918b) {
                                    i9--;
                                    if (i9 == 0) {
                                        int spanEnd2 = text.getSpanEnd(eVarArr[i11]);
                                        if (spanEnd2 >= i2) {
                                            i5 = spanStart;
                                            i6 = spanEnd2;
                                        }
                                    }
                                } else {
                                    i9++;
                                }
                            }
                            i11++;
                        }
                    }
                }
            }
            if (i5 == -1 || (i4 != -1 && i7 - i4 < i6 - i5)) {
                i6 = i7;
            } else {
                i4 = i5;
            }
            if (i4 >= 0) {
                if (this.f911b == null) {
                    this.f911b = new BackgroundColorSpan(Skin.C0353a.f1361Q);
                }
                text.setSpan(this.f911b, i4, i6, 33);
            }
        }
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        FastScrollBar m1Var = this.f910a;
        if (m1Var != null) {
            m1Var.m610j(i, i2, i3, i4);
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        FastScrollBar m1Var = this.f910a;
        if (m1Var == null || (!m1Var.m612h(motionEvent) && !this.f910a.m609k(motionEvent))) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }
}
