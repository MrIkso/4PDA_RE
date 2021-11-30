package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

public class Widgets$MemberView extends AutoCompleteTextView implements Handler.Callback {
    Handler f901a;
    ProgressBar f902b;
    public Util.AbstractC0429j<Boolean, Boolean> f903c;
    Integer f904d;

    public class C0238a extends MemberInfoRequest {
        Widgets$MemberView f905h;

        class RunnableC0239a implements Runnable {
            final int f906a;
            final String f907b;

            RunnableC0239a(int i, String str) {
               // C0238a.this = r1;
                this.f906a = i;
                this.f907b = str;
            }

            @Override
            public void run() {
                C0238a.this.f905h.m845a(this.f906a, this.f907b, true);
            }
        }

        public C0238a(Widgets$MemberView widgets$MemberView, Widgets$MemberView widgets$MemberView2, int memberId) {
            super(memberId);
            this.f905h = widgets$MemberView2;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (status == 0) {
                ((Activity) this.f905h.getContext()).runOnUiThread(new RunnableC0239a(uVar.getInt(0).intValue(), Util.C0427h.UnEscapeString(uVar.getString(1))));
            }
        }
    }

    public Widgets$MemberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m843c(context);
    }

    public void m845a(int i, String str, boolean z) {
        this.f904d = Integer.valueOf(i);
        int i2 = 0;
        if (z) {
            if (Build.VERSION.SDK_INT >= 17) {
                setText((CharSequence) str, false);
            } else {
                setText(str);
            }
        }
        View findViewById = ((View) getParent()).findViewById(R.id.memberLink);
        if (findViewById != null) {
            if (i == 0) {
                i2 = 8;
            }
            findViewById.setVisibility(i2);
        }
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.TRUE);
        }
    }

    public Integer m844b() {
        return this.f904d;
    }

    void m843c(Context context) {
        this.f901a = new Handler(context.getMainLooper(), this);
        FilterAdapter k1Var = new FilterAdapter(context, this);
        setAdapter(k1Var);
        setOnItemClickListener(k1Var);
        setThreshold(3);
    }

    public void m842d() {
        setText("");
        this.f904d = null;
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.FALSE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Integer num = this.f904d;
        if (num != null && num != 0 && 1 == motionEvent.getAction() && motionEvent.getX() > ((float) (getWidth() - getHeight()))) {
            MainActivity mainActivity = (MainActivity) getContext();
            mainActivity.mainLayout.tab.addPage(new Page_Profile(mainActivity, this.f904d, 0));
            CustomDialog.dismiss();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void m841e(int i) {
        this.f904d = i;
        DocumentManager.getResultRequest(new C0238a(this, this, i));
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.TRUE);
        }
    }

    public void m840f(String str) {
        setText(str);
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.FALSE);
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        super.performFiltering((CharSequence) message.obj, message.arg1);
        return false;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onFilterComplete(int i) {
        ProgressBar progressBar = this.f902b;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        super.onFilterComplete(i);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void performFiltering(CharSequence charSequence, int i) {
        ProgressBar progressBar = (ProgressBar) ((View) getParent()).findViewById(R.id.memberProgress);
        this.f902b = progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        View findViewById = ((View) getParent()).findViewById(R.id.memberLink);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        this.f904d = null;
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.FALSE);
        }
        this.f901a.removeMessages(28018);
        String charSequence2 = charSequence.toString();
        Handler handler = this.f901a;
        handler.sendMessageDelayed(handler.obtainMessage(28018, i, 0, charSequence2), 1200);
    }
}
