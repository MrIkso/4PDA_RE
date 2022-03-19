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
    Handler handler;
    ProgressBar progressBar;
    public Util.AbstractC0429j<Boolean, Boolean> f903c;
    Integer memberId;

    public class C0238a extends MemberInfoRequest {
        Widgets$MemberView memberView;

        class RunnableC0239a implements Runnable {
            final int memberId;
            final String memberUserName;

            RunnableC0239a(int memberId, String memberUserName) {
               // C0238a.this = r1;
                this.memberId = memberId;
                this.memberUserName = memberUserName;
            }

            @Override
            public void run() {
                C0238a.this.memberView.setData(this.memberId, this.memberUserName, true);
            }
        }

        public C0238a(Widgets$MemberView memberView, Widgets$MemberView widgets$MemberView2, int memberId) {
            super(memberId);
            this.memberView = widgets$MemberView2;
        }

        @Override
        public void prepareResult(int status, Document document) {
            if (status == 0) {
                ((Activity) this.memberView.getContext()).runOnUiThread(new RunnableC0239a(document.getInt(0).intValue(), Util.C0427h.UnEscapeString(document.getString(1))));
            }
        }
    }

    public Widgets$MemberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void setData(int memberId, String userName, boolean z) {
        this.memberId = Integer.valueOf(memberId);
        int i2 = 0;
        if (z) {
            if (Build.VERSION.SDK_INT >= 17) {
                setText((CharSequence) userName, false);
            } else {
                setText(userName);
            }
        }
        View findViewById = ((View) getParent()).findViewById(R.id.memberLink);
        if (findViewById != null) {
            if (memberId == 0) {
                i2 = 8;
            }
            findViewById.setVisibility(i2);
        }
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.TRUE);
        }
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    void init(Context context) {
        this.handler = new Handler(context.getMainLooper(), this);
        FilterAdapter filterAdapter = new FilterAdapter(context, this);
        setAdapter(filterAdapter);
        setOnItemClickListener(filterAdapter);
        setThreshold(3);
    }

    public void resetData() {
        setText("");
        this.memberId = null;
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.FALSE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Integer num = this.memberId;
        if (num != null && num != 0 && 1 == motionEvent.getAction() && motionEvent.getX() > ((float) (getWidth() - getHeight()))) {
            MainActivity mainActivity = (MainActivity) getContext();
            mainActivity.mainLayout.tab.addPage(new Page_Profile(mainActivity, this.memberId, 0));
            CustomDialog.dismiss();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void m841e(int i) {
        this.memberId = i;
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
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        super.onFilterComplete(i);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void performFiltering(CharSequence charSequence, int i) {
        ProgressBar progressBar = (ProgressBar) ((View) getParent()).findViewById(R.id.memberProgress);
        this.progressBar = progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        View findViewById = ((View) getParent()).findViewById(R.id.memberLink);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        this.memberId = null;
        Util.AbstractC0429j<Boolean, Boolean> jVar = this.f903c;
        if (jVar != null) {
            jVar.mo222a(Boolean.FALSE);
        }
        this.handler.removeMessages(28018);
        String charSequence2 = charSequence.toString();
        Handler handler = this.handler;
        handler.sendMessageDelayed(handler.obtainMessage(28018, i, 0, charSequence2), 1200);
    }
}
