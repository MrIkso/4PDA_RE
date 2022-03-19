package ru.fourpda.client;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class Page_Start extends Page implements BBDisplay.IBBDisplayCallback {
    private static List<Integer> f2801N;
    private int f2805H;
    private List<DataDB.UserBookMarkModel> f2806I;
    Widgets$StartButtonsLayout f2802E = null;
    List<BBString> f2803F = new Vector(5);
    List<BBString> f2804G = new Vector(5);
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f2807J = new C0821b();
  //  Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> f2808K = new C0822c();
    Util.AbstractC0429j<Boolean, Object> f2809L = new C0823d();
    private View$OnTouchListenerC0834n f2810M = new View$OnTouchListenerC0834n(this, null);

    class C0820a implements OptionPoupupMenuView.IClickListener {
        C0820a() {
//            Page_Start.this
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 1) {
                Page_Start.f2801N.clear();
               // Page_Start.this.f2808K.mo222a(DocumentManager.getMemberInfoModel());
                Prefs.saveInt(Page_Start.this.mainActivity, "globals_norm_count", 0);
            } else if (i3 == 2) {
                MainActivity mainActivity = Page_Start.this.mainActivity;
                boolean z = true ^ Prefs.notifySilence;
                Prefs.notifySilence = z;
                Prefs.saveBoolean(mainActivity, "notify_silence", z);
            } else if (i3 == 3) {
              //  ImageDialog.m918a(Page_Start.this.mainActivity);
            }
        }
    }

    public class C0821b implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0821b() {
//            Page_Start.this
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            Page_Start v0Var;
            Widgets$StartButtonsLayout widgets$StartButtonsLayout;
            if (Page_Start.this.isCurrentTab() && (widgets$StartButtonsLayout = (v0Var = Page_Start.this).f2802E) != null) {
                int i = cVar2.f2511a;
                if (1 == i) {
                    v0Var.m225q0(widgets$StartButtonsLayout.f925f, DocumentManager.documentManager.unread2.m692f());
                } else if (2 == i || 3 == i) {
                    v0Var.m225q0(widgets$StartButtonsLayout.f927h, DocumentManager.documentManager.unread2.m694d());
                } else if (4 == i || 5 == i) {
                    v0Var.m225q0(widgets$StartButtonsLayout.f929j, DocumentManager.documentManager.unread2.m693e());
                }
            }
            return Boolean.TRUE;
        }
    }

    public class C0822c implements Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> {
        C0822c() {
//            Page_Start.this
        }

        public Boolean mo222a(DocumentManager.MemberInfoModel hVar) {
            BBString pVar;
            String str = null;
            BBString pVar2;
            String str2;
            if (hVar == null) {
                return Boolean.FALSE;
            }
            try {
                Widgets$StartButtonsLayout widgets$StartButtonsLayout = Page_Start.this.f2802E;
                if (widgets$StartButtonsLayout != null) {
                    if (hVar == null || hVar.memberGroup != 1) {
                        if (!(hVar == null || (hVar.memberPremod == 0 && hVar.memberReadonly == 0 && hVar.memberBan == 0))) {
                            if (hVar.memberBan != 0) {
                                widgets$StartButtonsLayout.f920a.setVisibility(0);
                                if (hVar.memberBan == 2) {
                                    Page_Start.this.f2802E.f920a.setText("Перманентный бан");
                                } else {
                                    Page_Start.this.f2802E.f920a.setText("Бан (последний шанс)");
                                }
                            } else if (hVar.memberReadonly != 0) {
                                widgets$StartButtonsLayout.f920a.setVisibility(0);
                                Calendar instance = Calendar.getInstance();
                                instance.setTimeInMillis(System.currentTimeMillis() + ((long) (hVar.memberReadonly * 1000)));
                                Page_Start.this.f2802E.f920a.setText(String.format("Только чтение до %d.%02d.%02d %d:%02d", Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(1) % 100), Integer.valueOf(instance.get(10)), Integer.valueOf(instance.get(12))));
                            }
                        }
                        widgets$StartButtonsLayout.f920a.setVisibility(8);
                    } else {
                        widgets$StartButtonsLayout.f920a.setVisibility(0);
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                        spannableStringBuilder.append((CharSequence) "Активируйте аккаунт, - проверьте почту.\n");
                        int length = spannableStringBuilder.length();
                        spannableStringBuilder.append((CharSequence) "Повторно выслать письмо активации.");
                        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length() - 1, 0);
                        Page_Start.this.f2802E.f920a.setText(spannableStringBuilder);
                    }
                    Page_Start.this.f2802E.f920a.requestLayout();
                }
                Page_Start.this.f2803F.clear();
                if (hVar.memberDeletedMessageModels != null) {
                    for (int i = 0; i < hVar.memberDeletedMessageModels.size(); i++) {
                        try {
                            pVar2 = BBString.getBBString(hVar.memberDeletedMessageModels.get(i).memberMsgText, null);
                        } catch (Exception e) {
                            ////ACRA.getErrorReporter().putCustomData("extra", str2);
                            ////ACRA.getErrorReporter().handleSilentException(e);
                            ////ACRA.getErrorReporter().removeCustomData("extra");
                            pVar2 = null;
                        }
                        if (pVar2 != null) {
                            BBString.C0674e eVar = pVar2.f2246z;
                            float f = Page_Start.this.mainActivity.f731b;
                            int i2 = (int) (f * 16.0f);
                            eVar.f2266j = i2;
                            eVar.f2265i = i2;
                            eVar.f2263g = (float) ((int) (f * 14.0f));
                            eVar.f2264h = (float) ((int) (f * 13.0f));
                            pVar2.f2221a0 = hVar.memberDeletedMessageModels.get(i);
                            Page_Start.this.f2803F.add(pVar2);
                        }
                    }
                }
                Page_Start.this.f2805H = 0;
                Page_Start.this.f2804G.clear();
                if (hVar.memberMessageModels != null) {
                    for (int i3 = 0; i3 < hVar.memberMessageModels.size(); i3++) {
                        if (Page_Start.f2801N.contains(hVar.memberMessageModels.get(i3).memberMsgCreated)) {
                            Page_Start.m236f0(Page_Start.this);
                        } else {
                            try {
                                pVar = BBString.getBBString(hVar.memberMessageModels.get(i3).memberMsgText, null);
                            } catch (Exception e2) {
                                //ACRA.getErrorReporter().putCustomData("extra", str);
                                //ACRA.getErrorReporter().handleSilentException(e2);
                                //ACRA.getErrorReporter().removeCustomData("extra");
                                pVar = null;
                            }
                            if (pVar != null) {
                                BBString.C0674e eVar2 = pVar.f2246z;
                                float f2 = Page_Start.this.mainActivity.f731b;
                                int i4 = (int) (f2 * 16.0f);
                                eVar2.f2265i = i4;
                                eVar2.f2266j = i4;
                                eVar2.f2263g = (float) ((int) (f2 * 14.0f));
                                eVar2.f2264h = (float) ((int) (f2 * 13.0f));
                                pVar.f2221a0 = hVar.memberMessageModels.get(i3);
                                Page_Start.this.f2804G.add(pVar);
                            }
                        }
                    }
                }
                Page_Start.this.m227o0();
                if (Page_Start.this.isUnsucces()) {
                    Page_Start.this.tabLoaded(true);
                }
            } catch (Exception e3) {
                //ACRA.getErrorReporter().handleSilentException(e3);
            }
            return Boolean.TRUE;
        }
    }

    public class C0823d implements Util.AbstractC0429j<Boolean, Object> {
        C0823d() {
//            Page_Start.this
        }

        public Boolean mo222a(Object obj) {
            Page_Start.this.m229m0(false);
            return Boolean.TRUE;
        }
    }

    public class View$OnClickListenerC0824e implements View.OnClickListener {
        View$OnClickListenerC0824e() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                Page_Start.this.tab.addPage(new Page_Site_List(Page_Start.this.mainActivity, 0, 0));
            }
        }
    }

    class OpenForumList implements View.OnClickListener {
        OpenForumList() {
//           // Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                Page_Start v0Var = Page_Start.this;
                v0Var.tab.addPage(new Page_Forums(v0Var.mainActivity));
            }
        }
    }

    class View$OnClickListenerC0826g implements View.OnClickListener {
        View$OnClickListenerC0826g() {
//          //  Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                if (DocumentManager.isLoggined()) {
                    Page_Start v0Var = Page_Start.this;
                    v0Var.tab.addPage(new Page_History(v0Var.mainActivity, 0));
                    return;
                }
                Toast.makeText(Page_Start.this.mainActivity, "Выполните вход", 1).show();
            }
        }
    }

    class View$OnClickListenerC0827h implements View.OnClickListener {
        View$OnClickListenerC0827h() {
//           // Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                if (DocumentManager.isLoggined()) {
                    Page_Start v0Var = Page_Start.this;
                    v0Var.tab.addPage(new Page_QMS_List(v0Var.mainActivity));
                    return;
                }
                Toast.makeText(Page_Start.this.mainActivity, "Выполните вход", 1).show();
            }
        }
    }

    class View$OnClickListenerC0828i implements View.OnClickListener {
        View$OnClickListenerC0828i() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                if (DocumentManager.isLoggined()) {
                    Page_Start v0Var = Page_Start.this;
                    v0Var.tab.addPage(new Page_Favorites(v0Var.mainActivity, 0));
                    return;
                }
                Toast.makeText(Page_Start.this.mainActivity, "Выполните вход", 1).show();
            }
        }
    }

    class View$OnClickListenerC0829j implements View.OnClickListener {
        View$OnClickListenerC0829j() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            if (Page_Start.this.isCurrentTab()) {
                if (DocumentManager.isLoggined()) {
                    Page_Start v0Var = Page_Start.this;
                    v0Var.tab.addPage(new Page_Mention(v0Var.mainActivity, 0));
                    return;
                }
                Toast.makeText(Page_Start.this.mainActivity, "Выполните вход", 1).show();
            }
        }
    }

    class View$OnClickListenerC0830k implements View.OnClickListener {
        View$OnClickListenerC0830k() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
            if (L != null && L.memberGroup == 1) {
                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(Page_Start.this.mainActivity, 0, 0, 0, "", ""));
            }
        }
    }

    class View$OnClickListenerC0831l implements View.OnClickListener {
        View$OnClickListenerC0831l() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            int intValue = (Integer) view.getTag();
            List<BBString> list = Page_Start.this.f2804G;
            if (list != null && intValue < list.size()) {
                Page_Start.f2801N.add(((DocumentManager.MemberInfoModel.MemberMessageModel) Page_Start.this.f2804G.get(intValue).f2221a0).memberMsgCreated);
                Page_Start.m236f0(Page_Start.this);
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Page_Start.this.mainActivity).edit();
                edit.putInt("globals_norm_count", Page_Start.f2801N.size());
                for (int i = 0; i < Page_Start.f2801N.size(); i++) {
                    edit.putInt("globals_norm_" + i, (Integer) Page_Start.f2801N.get(i));
                }
                edit.commit();
                Page_Start.this.f2804G.remove(intValue);
                Page_Start.this.tabLoaded(true);
            }
        }
    }

    class View$OnClickListenerC0832m implements View.OnClickListener {

        class View$OnClickListenerC0833a implements View.OnClickListener {
            final DlgSimple f2824a;

            View$OnClickListenerC0833a(View$OnClickListenerC0832m mVar, DlgSimple q1Var) {
                this.f2824a = q1Var;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f2824a.editText.getText().toString())) {
                    DataDB.m368i(this.f2824a.editText.getText().toString(), "folder");
                }
            }
        }

        View$OnClickListenerC0832m() {
//            Page_Start.this
        }

        @Override
        public void onClick(View view) {
            DlgSimple q1Var = new DlgSimple(Page_Start.this.mainActivity, "Создание папки", false, "СОЗДАТЬ", null);
            q1Var.editText.setText("Новая папка");
            q1Var.m620f(new View$OnClickListenerC0833a(this, q1Var), true);
            q1Var.show(true, true, true);
        }
    }

    public class View$OnTouchListenerC0834n implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
        private boolean f2825a;
        private ImageView f2826b;
        private int f2827c;
        private int f2828d;
        private int f2829e;
        private float f2830f;
        private View f2831g;
        private View f2832h;
        private boolean f2833i;
        private DataDB.UserBookMarkModel f2834j;
        private int f2835k;

        public class C0835a implements OptionPoupupMenuView.IClickListener {
            final int f2837a;
            final int f2838b;
            final View f2839c;

            class View$OnClickListenerC0836a implements View.OnClickListener {
                final DlgSimple f2841a;
                final int f2842b;

                View$OnClickListenerC0836a(DlgSimple q1Var, int i) {
//                    C0835a.this
                    this.f2841a = q1Var;
                    this.f2842b = i;
                }

                @Override
                public void onClick(View view) {
                    DataDB.UserBookMarkModel aVar;
                    int i;
                    int i2;
                    int i3;
                    C0835a aVar2 = C0835a.this;
                    if (aVar2.f2837a >= Page_Start.this.f2806I.size() || (aVar = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(C0835a.this.f2837a)) == null || aVar.f2503h.hashCode() != C0835a.this.f2838b) {
                        Toast.makeText(Page_Start.this.mainActivity, "Вмешалась синхронизация и ничего не вышло. Повторите действие, пожалуйста.", 1).show();
                        return;
                    }
                    String obj = this.f2841a.editText.getText().toString();
                    if (!TextUtils.isEmpty(obj)) {
                        if (this.f2842b == 0) {
                            int i4 = aVar.f2500e;
                            int i5 = aVar.f2501f;
                            for (int i6 = C0835a.this.f2837a; i6 < Page_Start.this.f2806I.size(); i6++) {
                                DataDB.UserBookMarkModel aVar3 = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(i6);
                                if (aVar3.f2500e == i4 && (i3 = aVar3.f2501f) >= i5) {
                                    aVar3.f2501f = i3 + 1;
                                    DataDB.m370g(aVar3, true);
                                }
                            }
                            i = i5;
                            i2 = i4;
                        } else {
                            int i7 = aVar.f2496a;
                            i = DataDB.m371f(i7) + 1;
                            i2 = i7;
                        }
                        int n = DataDB.getPropsId(4, 0) + 1;
                        DataDB.makeProps(4, n);
                        DataDB.m370g(new DataDB.UserBookMarkModel(n, 0, false, 1, i2, i, obj, "folder"), true);
                        DocumentManager.f2748E.m655c(null);
                    }
                }
            }

            class View$OnClickListenerC0837b implements View.OnClickListener {
                final DlgSimple f2844a;

                View$OnClickListenerC0837b(DlgSimple q1Var) {
//                    C0835a.this
                    this.f2844a = q1Var;
                }

                @Override
                public void onClick(View view) {
                    DataDB.UserBookMarkModel aVar;
                    String obj = this.f2844a.editText.getText().toString();
                    if (!TextUtils.isEmpty(obj)) {
                        C0835a aVar2 = C0835a.this;
                        if (aVar2.f2837a >= Page_Start.this.f2806I.size() || (aVar = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(C0835a.this.f2837a)) == null || aVar.f2503h.hashCode() != C0835a.this.f2838b) {
                            Toast.makeText(Page_Start.this.mainActivity, "Вмешалась синхронизация и ничего не вышло. Повторите действие, пожалуйста.", 1).show();
                            return;
                        }
                        aVar.f2502g = obj;
                        DataDB.m370g(aVar, true);
                        ((TextView) C0835a.this.f2839c.findViewById(R.id.startEntryTitle)).setText(obj);
                    }
                }
            }

            C0835a(int i, int i2, View view) {
//                View$OnTouchListenerC0834n.this
                this.f2837a = i;
                this.f2838b = i2;
                this.f2839c = view;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DataDB.UserBookMarkModel aVar;
                int i4;
                DataDB.UserBookMarkModel aVar2;
                if (i3 == 0 || 1 == i3) {
                    DlgSimple q1Var = new DlgSimple(Page_Start.this.mainActivity, "Создание папки", false, "СОЗДАТЬ", null);
                    q1Var.editText.setText("Новая папка");
                    q1Var.m620f(new View$OnClickListenerC0836a(q1Var, i3), true);
                    q1Var.show(true, true, true);
                } else if (2 == i3) {
                    if (this.f2837a >= Page_Start.this.f2806I.size() || (aVar2 = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(this.f2837a)) == null || aVar2.f2503h.hashCode() != this.f2838b) {
                        Toast.makeText(Page_Start.this.mainActivity, "Вмешалась синхронизация и ничего не вышло. Повторите действие, пожалуйста.", 1).show();
                        return;
                    }
                    DlgSimple q1Var2 = new DlgSimple(Page_Start.this.mainActivity, "Переименование закладки", false, "ГОТОВО", null);
                    q1Var2.editText.setText(aVar2.f2502g);
                    q1Var2.m620f(new View$OnClickListenerC0837b(q1Var2), true);
                    q1Var2.show(true, true, true);
                } else if (3 != i3) {
                } else {
                    if (this.f2837a >= Page_Start.this.f2806I.size() || (aVar = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(this.f2837a)) == null || aVar.f2503h.hashCode() != this.f2838b) {
                        Toast.makeText(Page_Start.this.mainActivity, "Вмешалась синхронизация и ничего не вышло. Повторите действие, пожалуйста.", 1).show();
                        return;
                    }
                    DataDB.m374c(aVar);
                    if ((aVar.f2499d & 17) == 17 && Page_Start.this.f2806I.size() > this.f2837a + 1 && ((DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(this.f2837a + 1)).f2500e == (i4 = aVar.f2496a)) {
                        Page_Start.this.m226p0(i4, this.f2837a + 1);
                    }
                    Page_Start.this.f2806I.remove(this.f2837a);
                    Page_Start.this.m227o0();
                    Page_Start.this.tabLoaded(true);
                }
            }
        }

        private View$OnTouchListenerC0834n() {
//            Page_Start.this
        }

        void m220a(View view) {
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            view.setOnTouchListener(this);
        }

        void m219b(View view) {
            if (Build.VERSION.SDK_INT >= 11 && this.f2834j == view.getTag()) {
                view.setTranslationY((float) this.f2835k);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 0.0f);
                ofFloat.setDuration(50L);
                ofFloat.start();
            }
            this.f2834j = null;
            this.f2835k = 0;
        }

        @Override
        public void onClick(View view) {
            DataDB.UserBookMarkModel aVar = (DataDB.UserBookMarkModel) view.getTag();
            int i = aVar.f2499d;
            if ((i & 1) != 0) {
                if ((i & 16) != 0) {
                    Page_Start v0Var = Page_Start.this;
                    v0Var.m226p0(aVar.f2496a, v0Var.f2806I.indexOf(aVar) + 1);
                    aVar.f2499d &= -17;
                } else {
                    Page_Start v0Var2 = Page_Start.this;
                    v0Var2.m228n0(aVar.f2496a, v0Var2.f2806I.indexOf(aVar) + 1, aVar.f2504i + 1);
                    aVar.f2499d |= 16;
                }
                DataDB.m370g(aVar, false);
                Page_Start.this.m227o0();
                Page_Start.this.tabLoaded(true);
                return;
            }
            Page b = Urls2.openPage(Page_Start.this.mainActivity, "https://4pda.ru/" + aVar.f2503h, false, 1);
            if (b != null) {
                b.title = aVar.f2502g;
                Page_Start.this.tab.addPage(b);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            this.f2825a = true;
            return true;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean z;
            int i;
            int i2;
            boolean z2;
            int indexOf;
            int i3;
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f2830f = motionEvent.getY();
            } else if (this.f2825a) {
                DataDB.UserBookMarkModel aVar = (DataDB.UserBookMarkModel) view.getTag();
                int indexOf2 = Page_Start.this.f2806I.indexOf(aVar);
                int hashCode = aVar.f2503h.hashCode();
                int i4 = 3;
                if (2 == action) {
                    float y = motionEvent.getY();
                    if (this.f2826b != null || Math.abs(y - this.f2830f) * 2.0f <= ((float) view.getHeight())) {
                        ImageView imageView = this.f2826b;
                        if (imageView != null) {
                            int i5 = this.f2828d + ((int) ((y - this.f2830f) + 0.5f));
                            if (i5 < 0) {
                                i5 = 0;
                            }
                            int i6 = this.f2827c;
                            imageView.layout(i6, i5, view.getWidth() + i6, view.getHeight() + i5);
                            int height = (i5 - this.f2829e) + (this.f2826b.getHeight() / 2);
                            this.f2831g = null;
                            for (int i7 = 0; i7 < Page_Start.this.tab.forumsListView.getChildCount(); i7++) {
                                View childAt = Page_Start.this.tab.forumsListView.getChildAt(i7);
                                if (height > childAt.getTop() && height < childAt.getBottom()) {
                                    this.f2831g = childAt;
                                }
                            }
                            View view2 = this.f2831g;
                            if (!(view2 == null || view2 == view)) {
                                Object tag = view2.getTag();
                                if (tag instanceof DataDB.UserBookMarkModel) {
                                    int top = this.f2831g.getTop();
                                    int bottom = this.f2831g.getBottom();
                                    int top2 = view.getTop();
                                    int bottom2 = view.getBottom();
                                    int i8 = bottom - top;
                                    if ((((DataDB.UserBookMarkModel) tag).f2499d & 1) != 0) {
                                        i4 = 2;
                                    }
                                    int i9 = i8 / i4;
                                    if (top > top2 && height > top + i9) {
                                        this.f2833i = true;
                                    } else if (top >= top2 || height >= bottom - i9) {
                                        z2 = false;
                                        if (z2) {
                                            this.f2832h = this.f2831g;
                                            int i10 = top - top2;
                                            this.f2828d += i10;
                                            view.layout(view.getLeft(), top, view.getRight(), bottom);
                                            View view3 = this.f2831g;
                                            view3.layout(view3.getLeft(), top2, this.f2831g.getRight(), bottom2);
                                            if (Build.VERSION.SDK_INT >= 11) {
                                                this.f2831g.setTranslationY((float) i10);
                                                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f2831g, "translationY", 0.0f);
                                                ofFloat.setDuration(100L);
                                                ofFloat.start();
                                            }
                                            this.f2831g = view;
                                        }
                                    } else {
                                        this.f2833i = false;
                                    }
                                    z2 = true;
                                    if (z2) {
                                    }
                                }
                            }
                        }
                    } else if ((aVar.f2499d & 17) == 17 && Page_Start.this.f2806I.size() > (indexOf = Page_Start.this.f2806I.indexOf(aVar) + 1) && ((DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(indexOf)).f2500e == (i3 = aVar.f2496a)) {
                        Page_Start.this.m226p0(i3, indexOf);
                        aVar.f2499d &= -17;
                        DataDB.m370g(aVar, false);
                        Page_Start.this.m227o0();
                        Page_Start.this.tabLoaded(true);
                        return true;
                    } else {
                        TextView textView = (TextView) view.findViewById(R.id.startEntryTitle);
                        Drawable background = textView.getBackground();
                        view.setDrawingCacheEnabled(true);
                        textView.setBackgroundDrawable(Page_Start.this.mainActivity.skin.getSkinDrawable(R.drawable.main_background));
                        ImageView imageView2 = new ImageView(view.getContext());
                        this.f2826b = imageView2;
                        imageView2.setPadding(0, 0, 0, 0);
                        this.f2826b.setImageBitmap(Bitmap.createBitmap(view.getDrawingCache(false)));
                        this.f2826b.setLayoutParams(new LinearLayout.LayoutParams(view.getWidth(), view.getHeight()));
                        int[] iArr = new int[2];
                        int[] iArr2 = new int[2];
                        int[] iArr3 = new int[2];
                        view.getLocationInWindow(iArr);
                        Page_Start.this.tab.mainLayout.f795C.getLocationInWindow(iArr2);
                        Page_Start.this.tab.forumsListView.getLocationInWindow(iArr3);
                        this.f2827c = iArr[0] - iArr2[0];
                        this.f2828d = iArr[1] - iArr2[1];
                        int i11 = iArr3[0];
                        int i12 = iArr2[0];
                        this.f2829e = iArr3[1] - iArr2[1];
                        this.f2826b.setScaleType(ImageView.ScaleType.FIT_START);
                        Page_Start.this.tab.mainLayout.f795C.m854a(this.f2826b);
                        ImageView imageView3 = this.f2826b;
                        int i13 = this.f2827c;
                        imageView3.layout(i13, this.f2828d, view.getWidth() + i13, this.f2828d + view.getHeight());
                        view.setDrawingCacheEnabled(false);
                        textView.setText("");
                        textView.setBackgroundDrawable(background);
                        ((ImageView) view.findViewById(R.id.startEntryStatus)).setImageDrawable(null);
                    }
                    return true;
                } else if (3 == action || 1 == action) {
                    this.f2825a = false;
                    ImageView imageView4 = this.f2826b;
                    if (imageView4 != null) {
                        this.f2834j = aVar;
                        this.f2835k = imageView4.getTop() - this.f2828d;
                        Page_Start.this.tab.mainLayout.f795C.m853b(this.f2826b);
                        this.f2826b = null;
                        ((TextView) view.findViewById(R.id.startEntryTitle)).setText(aVar.f2502g);
                        if ((aVar.f2499d & 1) != 0) {
                            ((ImageView) view.findViewById(R.id.startEntryStatus)).setImageDrawable(Page_Start.this.mainActivity.skin.getSkinDrawable(R.drawable.ic_expand_close));
                        }
                        if (1 == action) {
                            int i14 = aVar.f2500e;
                            int i15 = aVar.f2501f;
                            View view4 = this.f2831g;
                            if (view4 == view) {
                                View view5 = this.f2832h;
                                if (view5 != null) {
                                    DataDB.UserBookMarkModel aVar2 = (DataDB.UserBookMarkModel) view5.getTag();
                                    if (this.f2833i) {
                                        if (17 == (aVar2.f2499d & 17)) {
                                            i2 = aVar2.f2496a;
                                            i = 0;
                                        } else {
                                            int i16 = aVar2.f2500e;
                                            i = aVar2.f2501f + 1;
                                            i2 = i16;
                                        }
                                        i15 = i;
                                        i14 = i2;
                                    } else {
                                        i14 = aVar2.f2500e;
                                        i15 = aVar2.f2501f;
                                    }
                                }
                            } else {
                                if (view4 != null) {
                                    Object tag2 = view4.getTag();
                                    if (tag2 instanceof DataDB.UserBookMarkModel) {
                                        DataDB.UserBookMarkModel aVar3 = (DataDB.UserBookMarkModel) tag2;
                                        if ((aVar3.f2499d & 1) != 0) {
                                            i14 = aVar3.f2496a;
                                            i15 = Integer.MAX_VALUE;
                                        }
                                    } else if (this.f2831g.getId() != R.id.startBottom) {
                                        i15 = 0;
                                    }
                                }
                                i14 = 0;
                                i15 = Integer.MAX_VALUE;
                            }
                            if (Integer.MAX_VALUE == i15) {
                                i15 = DataDB.m371f(i14);
                                if (!(i14 == aVar.f2500e && i15 == aVar.f2501f)) {
                                    i15++;
                                }
                            }
                            if (!(i14 == aVar.f2500e && i15 == aVar.f2501f)) {
                                Page_Start.this.f2806I.remove(aVar);
                                if (i14 == 0) {
                                    aVar.f2504i = 0;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                int i17 = 0;
                                int i18 = 0;
                                for (int i19 = 0; i19 < Page_Start.this.f2806I.size(); i19++) {
                                    DataDB.UserBookMarkModel aVar4 = (DataDB.UserBookMarkModel) Page_Start.this.f2806I.get(i19);
                                    if (aVar4.f2496a == i14) {
                                        aVar.f2504i = aVar4.f2504i + 1;
                                        if ((aVar4.f2499d & 16) != 0) {
                                            z = true;
                                        } else {
                                            i17 = -1;
                                        }
                                    } else if (z && (aVar4.f2504i < aVar.f2504i || (aVar4.f2500e == i14 && aVar4.f2501f >= i15))) {
                                        z = false;
                                    }
                                    if (z) {
                                        i17 = i19 + 1;
                                    }
                                    int i20 = aVar4.f2501f;
                                    int i21 = aVar4.f2500e;
                                    int i22 = (i21 != aVar.f2500e || i20 <= aVar.f2501f) ? i20 : i20 - 1;
                                    if (i21 == i14 && i20 >= i15) {
                                        i22++;
                                    }
                                    if (i21 == i14) {
                                        if (i22 <= i18) {
                                            i22 = i18 + 1;
                                        }
                                        if (i22 == i15) {
                                            i22++;
                                        }
                                        i18 = i22;
                                        i22 = i18;
                                    }
                                    if (i22 != i20) {
                                        aVar4.f2501f = i22;
                                        DataDB.m370g(aVar4, true);
                                    }
                                }
                                if (i14 == aVar.f2500e && i15 > aVar.f2501f) {
                                    i15--;
                                }
                                if (i17 >= 0) {
                                    Page_Start.this.f2806I.add(i17, aVar);
                                }
                                aVar.f2500e = i14;
                                aVar.f2501f = i15;
                                DataDB.m370g(aVar, true);
                            }
                        }
                        this.f2831g = null;
                        this.f2832h = null;
                        Page_Start.this.tabLoaded(true);
                    } else if (1 == action) {
                        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Start.this.mainActivity, new C0835a(indexOf2, hashCode, view), true);
                        o1Var.addMenuItem(0, 0, 0, "Создать папку");
                        if ((aVar.f2499d & 1) != 0) {
                            o1Var.addMenuItem(0, 0, 1, "Создать подпапку");
                        }
                        o1Var.addMenuItem(0, 0, 2, "Переименовать");
                        o1Var.addMenuItem(0, 0, 3, "Удалить");
                        o1Var.show(null);
                    }
                }
            }
            return false;
        }

        View$OnTouchListenerC0834n(Page_Start v0Var, View$OnClickListenerC0824e eVar) {
            this();
        }
    }

    public Page_Start(MainActivity mainActivity) {
        super(mainActivity);
        this.title = "Домашняя";
        if (f2801N == null) {
            f2801N = new Vector(10);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            int i = defaultSharedPreferences.getInt("globals_norm_count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                List<Integer> list = f2801N;
                list.add(defaultSharedPreferences.getInt("globals_norm_" + i2, 0));
            }
        }
    }

    static int m236f0(Page_Start v0Var) {
        int i = v0Var.f2805H;
        v0Var.f2805H = i + 1;
        return i;
    }

    public void m229m0(boolean z) {
        boolean w = isUnsucces();
        if (w || z) {
            this.f2806I = new Vector(100);
            m228n0(0, 0, 0);
            m227o0();
        }
        if (w) {
            tabLoaded(true);
        }
    }

    public int m228n0(int i, int i2, int i3) {
        DataDB.UserBookMarkModel[] e = DataDB.m372e(i);
        if (e != null) {
            for (DataDB.UserBookMarkModel aVar : e) {
                aVar.f2504i = i3;
                int i4 = i2 + 1;
                this.f2806I.add(i2, aVar);
                i2 = (aVar.f2499d & 17) == 17 ? m228n0(aVar.f2496a, i4, i3 + 1) : i4;
            }
        }
        return i2;
    }

    public void m227o0() {
        int size = this.f2803F.size() + 1 + this.f2804G.size() + 1;
        List<DataDB.UserBookMarkModel> list = this.f2806I;
        int size2 = size + (list != null ? list.size() : 0) + 1;
        int[] iArr = this.f1071B;
        if (iArr == null || iArr.length != size2) {
            int[] iArr2 = new int[size2];
            this.f1071B = iArr2;
            iArr2[0] = (int) (this.mainActivity.f731b * 208.0f);
            int i = 1;
            for (int i2 = 0; i2 < this.f2803F.size(); i2++) {
                int[] iArr3 = this.f1071B;
                iArr3[i] = iArr3[i - 1] + ((int) (this.mainActivity.f731b * 60.0f));
                i++;
            }
            for (int i3 = 0; i3 < this.f2804G.size(); i3++) {
                int[] iArr4 = this.f1071B;
                iArr4[i] = iArr4[i - 1] + ((int) (this.mainActivity.f731b * 60.0f));
                i++;
            }
            int[] iArr5 = this.f1071B;
            iArr5[i] = iArr5[i - 1] + ((int) (this.mainActivity.f731b * 40.0f));
            int i4 = i + 1;
            for (int i5 = 0; i5 < this.f2806I.size(); i5++) {
                int[] iArr6 = this.f1071B;
                iArr6[i4] = iArr6[i4 - 1] + ((int) (this.mainActivity.f731b * 40.0f));
                i4++;
            }
            int[] iArr7 = this.f1071B;
            iArr7[i4] = iArr7[i4 - 1] + ((int) (this.mainActivity.f731b * 16.0f));
        }
    }

    public void m226p0(int i, int i2) {
        while (i2 < this.f2806I.size()) {
            DataDB.UserBookMarkModel aVar = this.f2806I.get(i2);
            if (aVar.f2500e == i) {
                this.f2806I.remove(i2);
                if ((aVar.f2499d & 17) == 17) {
                    m226p0(aVar.f2496a, i2);
                }
            } else {
                return;
            }
        }
    }

    public void m225q0(TextView textView, int i) {
        textView.setText(i > 0 ? String.valueOf(i) : "");
        int i2 = i > 0 ? 0 : 4;
        if (textView.getVisibility() != i2) {
            textView.setVisibility(i2);
            textView.requestLayout();
        }
    }

    @Override
    public void onSearchBar() {
        this.f2802E = null;
        this.tab.mainLayout.m867o(false);
        super.onSearchBar();
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0820a());
        if (this.f2805H > 0) {
            o1Var.addMenuItem(0, 0, 1, "Показать обьявления");
        }
        if (Build.VERSION.SDK_INT < 26) {
            o1Var.addMenuItem(0, 0, 2, "В тихий режим", false, false, true, Prefs.notifySilence);
        }
        o1Var.addMenuItem(0, 0, 3, "Мини-гид", false, false, false, false);
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.m867o(true);
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        Unread2.f1568b.m654a(this.f2807J);
     //   DocumentManager.f2747D.m657a(this.f2808K);
        DocumentManager.f2748E.m657a(this.f2809L);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        Unread2.f1568b.m653b(this.f2807J);
     //   DocumentManager.f2747D.m656b(this.f2808K);
        DocumentManager.f2748E.m656b(this.f2809L);
        return true;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
       /* int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            if (i2 == 1) {
                Page b = Urls2.openPage(this.mainActivity, kVar.link, false, 2);
                if (b != null) {
                    Tab f1Var = new Tab(this.mainActivity);
                    f1Var.addPage(b);
                    this.mainActivity.mainLayout.setCurrentTab(f1Var);
                }
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        }*/
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces()) {
            return 0;
        }
        int size = this.f2803F.size() + 1 + this.f2804G.size() + 1;
        List<DataDB.UserBookMarkModel> list = this.f2806I;
        if (list != null) {
            i = list.size();
        }
        return size + i + 1;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        int size = this.f2803F.size();
        if (i >= 1 && i <= size) {
            return 1;
        }
        int size2 = this.f2804G.size() + size;
        if (i >= size + 1 && i <= size2) {
            return 2;
        }
        if (i == size2 + 1) {
            return 3;
        }
        return i == getCount() - 1 ? 5 : 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Exception e;
        char c;
     //   ?? r0 = 16;
      //  r0 = 16;
      // r0 = 16;
        try {
            int itemViewType = getItemViewType(i);
            if (view == null) {
                if (itemViewType == 0) {
                    try {
                        view = this.mainActivity.getLayoutInflater().inflate(R.layout.startbuttons, viewGroup, false);
                        Widgets$StartButtonsLayout widgets$StartButtonsLayout = (Widgets$StartButtonsLayout) view;
                        this.f2802E = widgets$StartButtonsLayout;
                        widgets$StartButtonsLayout.f921b.setOnClickListener(new View$OnClickListenerC0824e());
                        this.f2802E.f922c.setOnClickListener(new OpenForumList());
                        this.f2802E.f923d.setOnClickListener(new View$OnClickListenerC0826g());
                        this.f2802E.f924e.setOnClickListener(new View$OnClickListenerC0827h());
                        this.f2802E.f926g.setOnClickListener(new View$OnClickListenerC0828i());
                        this.f2802E.f928i.setOnClickListener(new View$OnClickListenerC0829j());
                        this.f2802E.f920a.setOnClickListener(new View$OnClickListenerC0830k());
                    } catch (Exception e2) {
                        e = e2;
                        c = 3;
                        //r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                        //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                        return view;
                    }
                } else if (itemViewType == 1) {
                    try {
                        view = this.mainActivity.getLayoutInflater().inflate(R.layout.startglobal, viewGroup, false);
                        ((BBDisplay) view.findViewById(R.id.globalMsg)).setCallback(this);
                        view.findViewById(R.id.globalClose).setVisibility(8);
                    } catch (Exception e3) {
                        e = e3;
                        c = 5;
                       // r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                        //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                        return view;
                    }
                } else if (itemViewType == 2) {
                    try {
                        view = this.mainActivity.getLayoutInflater().inflate(R.layout.startglobal, viewGroup, false);
                        ((BBDisplay) view.findViewById(R.id.globalMsg)).setCallback(this);
                        view.findViewById(R.id.globalClose).setOnClickListener(new View$OnClickListenerC0831l());
                    } catch (Exception e4) {
                        e = e4;
                        c = 7;
                      //  r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                        //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                        return view;
                    }
                } else {
                    try {
                        if (itemViewType == 3) {
                            view = this.mainActivity.getLayoutInflater().inflate(R.layout.start_bm_title, viewGroup, false);
                            view.findViewById(R.id.startAddFolder).setOnClickListener(new View$OnClickListenerC0832m());
                        } else if (itemViewType == 5) {
                            view = this.mainActivity.getLayoutInflater().inflate(R.layout.start_bm_bottom, viewGroup, false);
                        } else if (itemViewType == 4) {
                            view = this.mainActivity.getLayoutInflater().inflate(R.layout.start_bm_entry, viewGroup, false);
                            this.f2810M.m220a(view);
                        }
                    } catch (Exception e5) {
                        e = e5;
                        c = 2;
                     //   r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                        //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                        return view;
                    }
                }
            }
            try {
                try {
                    if (itemViewType == 0) {
                        if (this.f2802E == null) {
                            this.f2802E = (Widgets$StartButtonsLayout) view;
                        }
                        TextView textView = this.f2802E.f925f;
                        Unread2 g1Var = DocumentManager.documentManager.unread2;
                        m225q0(textView, g1Var == null ? 0 : g1Var.m692f());
                        TextView textView2 = this.f2802E.f927h;
                        Unread2 g1Var2 = DocumentManager.documentManager.unread2;
                        m225q0(textView2, g1Var2 == null ? 0 : g1Var2.m694d());
                        TextView textView3 = this.f2802E.f929j;
                        Unread2 g1Var3 = DocumentManager.documentManager.unread2;
                        m225q0(textView3, g1Var3 == null ? 0 : g1Var3.m693e());
                        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
                        if (L == null || L.memberGroup != 1) {
                            if (!(L == null || (L.memberPremod == 0 && L.memberReadonly == 0 && L.memberBan == 0))) {
                                if (L.memberBan != 0) {
                              //      r0 = 18;
                              //      r0 = 18;
                                    this.f2802E.f920a.setVisibility(0);
                                    if (L.memberBan == 2) {
                                        this.f2802E.f920a.setText("Перманентный бан");
                                    } else {
                                        this.f2802E.f920a.setText("Бан (последний шанс)");
                                    }
                                } else if (L.memberReadonly != 0) {
                                    this.f2802E.f920a.setVisibility(0);
                                    this.f2802E.f920a.setText("Только чтение до " + Util.formatDate(L.memberReadonly));
                                }
                            }
                         //   r0 = 14;
                            this.f2802E.f920a.setVisibility(8);
                        } else {
                         //   r0 = -14;
                            this.f2802E.f920a.setVisibility(0);
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                            spannableStringBuilder.append((CharSequence) "Активируйте аккаунт - проверьте почту.\n");
                            int length = spannableStringBuilder.length();
                            spannableStringBuilder.append((CharSequence) "Повторно выслать письмо активации.");
                            spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length() - 1, 0);
                            this.f2802E.f920a.setText(spannableStringBuilder);
                        }
                    } else if (itemViewType == 1) {
                      //  r0 = 20;
                     /*   BBDisplay bBDisplay = (BBDisplay) view.findViewById(R.id.globalMsg);
                        BBString pVar = this.f2803F.get(i - 1);
                        bBDisplay.setBackgroundColor(((DocumentManager.MemberInfoModel.MemberMessageModel) pVar.f2221a0).memberMsgColor);
                        bBDisplay.setBBString(pVar);*/
                    } else if (itemViewType == 2) {
                      //  r0 = 22;
                      /*  BBDisplay bBDisplay2 = (BBDisplay) view.findViewById(R.id.globalMsg);
                        int size = i - (this.f2803F.size() + 1);
                        BBString pVar2 = this.f2804G.get(size);
                        bBDisplay2.setBackgroundColor(((DocumentManager.MemberInfoModel.MemberMessageModel) pVar2.f2221a0).memberMsgColor);
                        bBDisplay2.setBBString(pVar2);
                        view.findViewById(R.id.globalClose).setTag(Integer.valueOf(size));*/
                    } else if (4 == itemViewType) {
                        DataDB.UserBookMarkModel aVar = this.f2806I.get((((i - 1) - this.f2803F.size()) - this.f2804G.size()) - 1);
                        TextView textView4 = (TextView) view.findViewById(R.id.startEntryTitle);
                        if (i + 2 < getCount()) {
                            textView4.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.border_bottom));
                        } else {
                            textView4.setBackgroundResource(0);
                        }
                        textView4.setText(aVar.f2502g);
                        view.setPadding((int) (this.mainActivity.f731b * 8.0f * ((float) aVar.f2504i)), 0, 0, 0);
                        ImageView r13 = (ImageView) view.findViewById(R.id.startEntryStatus);
                        int i2 = aVar.f2499d;
                        Drawable r0 = null;
                        if ((i2 & 1) != 0) {
                            r0 = this.mainActivity.skin.getSkinDrawable((16 & i2) != 0 ? R.drawable.ic_expand_open : R.drawable.ic_expand_close);
                        
                            //r0 = 0;

                            r13.setImageDrawable(r0);
                        }
                        view.setTag(aVar);
                        this.f2810M.m219b(view);
                    }
                } catch (Exception e6) {
                    e = e6;
                   // c = r0;
                    //r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                    //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                    return view;
                }
            } catch (Exception e7) {
                e = e7;
                c = 11;
                //r0 = "StartPage.GetView " + (c == 1 ? 1 : 0);
                //ACRA.getErrorReporter().handleSilentException(new Exception(r0, e));
                return view;
            }
        } catch (Exception e8) {
            e = e8;
            c = 0;
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f2803F.clear();
        this.f2804G.clear();
        this.f2802E = null;
    }

    @Override
    public String getLink() {
        return "";
    }

    @Override
    public void tabReload() {
        m229m0(true);
      //  this.f2808K.mo222a(DocumentManager.getMemberInfoModel());
        tabLoaded(true);
    }
}
