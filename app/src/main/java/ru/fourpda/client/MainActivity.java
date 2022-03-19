package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private static PicoFCM.C0962g f728q;
    private static String token;
    public MainLayout mainLayout;
    public float f731b;
    public float f732c;
    boolean f733d;
    int f734e;
    int f735f;
    Skin skin;
    private int f737h;
    WeakReference<AbstractC0193g0> f738i;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f739j = new C0206o();
    Util.AbstractC0429j<Boolean, Object> f740k = new C0207p();
    Util.AbstractC0429j<Boolean, String> f741l = new C0209q();
    Runnable runnablePageStatus = new ShowRunnablePageStatus();
    Util.AbstractC0429j<Boolean, Integer> f743n = new C0211s();
    Util.AbstractC0429j<Boolean, Object> f744o = new C0212t();
    Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> f745p = new C0213u();

    public class View$OnClickListenerC0177a implements View.OnClickListener {
        View$OnClickListenerC0177a() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.OnProfileClick(view);
        }
    }

    public class View$OnClickListenerC0178a0 implements View.OnClickListener {
        View$OnClickListenerC0178a0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Site_List(MainActivity.this, 0, 0));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class HistoryOnClickListenerC0179b implements View.OnClickListener {
        HistoryOnClickListenerC0179b() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            ((Widgets$CheckboxView) MainActivity.this.findViewById(R.id.nav_expander)).m850b();
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_History(MainActivity.this, 0));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class View$OnClickListenerC0180b0 implements View.OnClickListener {
        View$OnClickListenerC0180b0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.tab.page.setOptionMenuToolBar(view);
        }
    }

    public class View$OnClickListenerC0181c implements View.OnClickListener {
        View$OnClickListenerC0181c() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.OnProfileClick(view);
        }
    }

    public class View$OnClickListenerC0182c0 implements View.OnClickListener {
        View$OnClickListenerC0182c0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.tab.page.showSearchDialog(view);
        }
    }

    public class View$OnClickListenerC0183d implements View.OnClickListener {
        View$OnClickListenerC0183d() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.OnProfileClick(view);
        }
    }

    public static class C0184d0 {
        private final View f753a;
        private int f754b;
        private final int f755c;
        private final FrameLayout.LayoutParams f756d;

        class ViewTreeObserver$OnGlobalLayoutListenerC0185a implements ViewTreeObserver.OnGlobalLayoutListener {
            ViewTreeObserver$OnGlobalLayoutListenerC0185a() {
            }

            @Override
            public void onGlobalLayout() {
                C0184d0.this.m892d();
            }
        }

        private C0184d0(MainActivity mainActivity) {
            this.f755c = mainActivity.f734e;
            @SuppressLint("ResourceType") 
            View childAt = ((FrameLayout) mainActivity.findViewById(0x1020002)).getChildAt(0);
            this.f753a = childAt;
            childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC0185a());
            this.f756d = (FrameLayout.LayoutParams) this.f753a.getLayoutParams();
        }

        static void m894b(MainActivity mainActivity) {
            new C0184d0(mainActivity);
        }

        private int m893c() {
            Rect rect = new Rect();
            this.f753a.getWindowVisibleDisplayFrame(rect);
            return rect.bottom - rect.top;
        }

        public void m892d() {
            int c = m893c() + this.f755c;
            if (c != this.f754b) {
                int height = this.f753a.getRootView().getHeight();
                int i = height - c;
                if (i > height / 4) {
                    this.f756d.height = height - i;
                } else {
                    this.f756d.height = height;
                }
                this.f753a.requestLayout();
                this.f754b = c;
            }
        }
    }

    public class View$OnClickListenerC0186e implements View.OnClickListener {
        View$OnClickListenerC0186e() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.OnProfileClick(view);
        }
    }

    public static class MemberAuthorizationRequest extends MemberSecurityRequest {
        MainActivity activity;

        public MemberAuthorizationRequest(MainActivity mainActivity, int action, int mid, int ts, String sig, String value) {
            super(action, mid, ts, sig, value);
            this.activity = mainActivity;
            this.statusMessage = "Авторизация";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            String messageText1 = null;
            String messageText2;
            String messageText = String.format("Статус %d", Integer.valueOf(status));
            int action = this.action;
            if (action == 1) {
                messageText = status == 0 ? "Успешная авторизация" : "Ошибка авторизации";
                DocumentManager.isMemberValid();
            } else {
                if (action == 3) {
                    messageText2 = status == 0 ? "Успешная смена e-mail" : "Ошибка смены e-mail";
                } else {
                    messageText2 = "Письмо отправлено";
                    if (action != 0) {
                        messageText = "Успешная смена пароля";
                        messageText = "Ошибка смены пароля";
                        if (action != 6) {
                            if (action == 2) {
                                if (status != 0) {
                                    messageText2 = "Ошибка смена электронной почты";
                                }
                            } else if (action != 4) {
                                if (action == 5) {
                                    if (status != 0) {
                                        messageText2 = "Неверный логин или e-mail";
                                    }
                                } else if (action == 7) {
                                    messageText2 = status == 0 ? "Логин успешно изменен" : "Ошибка при смене логина";
                                }
                            }
                        }
                    } else if (status != 0) {
                        messageText2 = "Ошибка отправки письма";
                    }
                }
                messageText = messageText2;
            }
            if (status == 3) {
                messageText = "Неправильный пользователь";
            } else if (status == 4) {
                messageText = "Ссылка устарела";
            } else {
                if (status == 5) {
                    messageText1 = this.action == 7 ? "Некорректный логин" : "Неправильный адрес почты";
                } else if (status == 6) {
                    messageText1 = this.action == 7 ? "Логин уже используется" : "Адрес почты уже используется";
                } else if (status == 7) {
                    messageText1 = "Неверный пароль";
                } else if (status == 8) {
                    messageText1 = this.action == 7 ? "Запрещенный логин" : "Запрещенный адрес почты";
                } else if (status == 9) {
                    messageText1 = "С предыдущей смены логина прошло слишком мало времени";
                }
                messageText = messageText1;
            }
            Toast.makeText(this.activity, messageText, 0).show();
        }
    }

    public class View$OnClickListenerC0188f implements View.OnClickListener {
        View$OnClickListenerC0188f() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.OnProfileClick(view);
        }
    }

    public static class AsyncTaskC0189f0 extends AsyncTask<Void, Void, Void> {
        private final Context f761a;
        private final PicoFCM f762b;

        public class C0190a implements PicoFCM.AbstractC0961f {

            class RunnableC0191a implements Runnable {
                final Bundle f764a;

                RunnableC0191a(Bundle bundle) {
                    this.f764a = bundle;
                }

                @Override
                public void run() {
                    String str;
                    Context context = AsyncTaskC0189f0.this.f761a;
                    if (this.f764a.containsKey("error")) {
                        str = "Ошибка FCM:\n" + this.f764a.getString("error");
                    } else {
                        str = "Произошла неизвестная ошибка FCM";
                    }
                    Toast.makeText(context, str, 1).show();
                    Context context2 = AsyncTaskC0189f0.this.f761a;
                    Prefs.backgroundMode = 3;
                    Prefs.saveInt(context2, "background_mode", 3);
                }
            }

            C0190a() {
            }

            @Override
            public void mo8a(int i, Bundle bundle) {
                if (bundle == null || !bundle.containsKey("registration_id") || TextUtils.isEmpty(bundle.getString("registration_id"))) {
                    new Handler(Looper.getMainLooper()).post(new RunnableC0191a(bundle));
                } else {
                    MainActivity.initNotify(AsyncTaskC0189f0.this.f761a);
                }
            }
        }

        AsyncTaskC0189f0(Context context, PicoFCM z0Var) {
            this.f761a = context;
            this.f762b = z0Var;
        }

        public Void doInBackground(Void... voidArr) {
            this.f762b.m44A(new C0190a());
            return null;
        }
    }

    public class View$OnClickListenerC0192g implements View.OnClickListener {
        View$OnClickListenerC0192g() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            ((Widgets$CheckboxView) MainActivity.this.findViewById(R.id.nav_expander)).m850b();
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Ticket(MainActivity.this, 0, 0, null));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public interface AbstractC0193g0 {
        void mo426e(Uri uri, String str);

        boolean mo423g(Intent intent);
    }

    public class View$OnClickListenerC0194h implements View.OnClickListener {
        View$OnClickListenerC0194h() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Options(MainActivity.this));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public static class AsyncTaskC0195h0 extends AsyncTask<Void, Void, Void> {
        private final Context f768a;

        public class C0196a implements PicoHCM.AbstractC0298c {

            class RunnableC0197a implements Runnable {
                final int f770a;

                RunnableC0197a(int i) {
                    this.f770a = i;
                }

                @Override
                public void run() {
                    Context context = AsyncTaskC0195h0.this.f768a;
                    Toast.makeText(context, "Ошибка HCM: код " + this.f770a, 1).show();
                    Context context2 = AsyncTaskC0195h0.this.f768a;
                    Prefs.backgroundMode = 3;
                    Prefs.saveInt(context2, "background_mode", 3);
                }
            }

            C0196a() {
            }

            @Override
            public void mo772a(int i, Bundle bundle) {
                if (i != 0 || bundle == null) {
                    new Handler(Looper.getMainLooper()).post(new RunnableC0197a(i));
                    return;
                }
                String string = bundle.getString("token");
                if (!TextUtils.isEmpty(string)) {
                    MainActivity.m897i(AsyncTaskC0195h0.this.f768a, string);
                }
            }
        }

        AsyncTaskC0195h0(Context context) {
            this.f768a = context;
        }

        public Void doInBackground(Void... voidArr) {
            new PicoHCM(this.f768a, "101200861").m780w(new C0196a());
            return null;
        }
    }

    public class View$OnClickListenerC0198i implements View.OnClickListener {
        View$OnClickListenerC0198i() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(MainActivity.this, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.dlg_about);
            dialog.getWindow().setBackgroundDrawable(MainActivity.this.skin.getSkinDrawable(R.drawable.np_dialog));
            Window window = dialog.getWindow();
            MainActivity mainActivity = MainActivity.this;
            window.setLayout(Math.min((int) (mainActivity.f731b * 400.0f), mainActivity.getResources().getDisplayMetrics().widthPixels), -2);
            dialog.setCanceledOnTouchOutside(true);
            ((TextView) dialog.findViewById(R.id.aboutDate)).setText("Дата сборки: " + Util.formatDate(1610150122, false, false));
            ((TextView) dialog.findViewById(R.id.aboutVersion)).setText(MainActivity.this.getResources().getString(R.string.dlg_about_version) + " 1.9.32_p1");
            ((TextView) dialog.findViewById(R.id.aboutLibs)).setMovementMethod(LinkMovementMethod.getInstance());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    public class LogOutClickListenerC0199j implements View.OnClickListener {

        class View$OnClickListenerC0200a implements View.OnClickListener {
            View$OnClickListenerC0200a() {
            }

            @Override
            public void onClick(View view) {
                DocumentManager.initAuthenticate();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.mainLayout.tab.addPage(new Page_Login(mainActivity, false));
            }
        }

        LogOutClickListenerC0199j() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            if (DocumentManager.isLoggined()) {
                DlgSimple q1Var = new DlgSimple(MainActivity.this, "Будут закрыты все вкладки.", false, null, null);
                q1Var.editText.setVisibility(8);
                q1Var.m620f(new View$OnClickListenerC0200a(), true);
                q1Var.show(true, true, true);
                return;
            }
            MainActivity mainActivity = MainActivity.this;
            mainActivity.mainLayout.tab.addPage(new Page_Login(mainActivity, false));
        }
    }

    public class LayoutInflater$FactoryC0201k implements LayoutInflater.Factory {
        LayoutInflater$FactoryC0201k() {
        }

        @Override
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return MainActivity.this.skin.m732j(str, context, attributeSet);
        }
    }

    public class View$OnClickListenerC0202l implements View.OnClickListener {

        class View$OnClickListenerC0203a implements View.OnClickListener {
            View$OnClickListenerC0203a() {
            }

            @Override
            public void onClick(View view) {
                MainActivity.this.mainLayout.m881a();
                MainActivity.this.finish();
            }
        }

        View$OnClickListenerC0202l() {
        }

        @Override
        public void onClick(View view) {
            if (Prefs.confirmAction) {
                DlgSimple q1Var = new DlgSimple(MainActivity.this, "Закрыть приложение?", false, "ДА", null);
                q1Var.editText.setVisibility(8);
                q1Var.m620f(new View$OnClickListenerC0203a(), true);
                q1Var.show(true, true, true);
                return;
            }
            MainActivity.this.mainLayout.m881a();
            MainActivity.this.finish();
        }
    }

    public class View$OnClickListenerC0204m implements View.OnClickListener {
        View$OnClickListenerC0204m() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = MainActivity.this;
            int i = 3;
            if (Prefs.nightMode == 3) {
                i = 2;
            }
            Prefs.nightMode = i;
            Prefs.saveInt(mainActivity, "nightMode", i);
            MainActivity.this.m899g(null);
        }
    }

    public class View$OnClickListenerC0205n implements View.OnClickListener {
        View$OnClickListenerC0205n() {
        }

        @Override
        public void onClick(View view) {
            Page a0Var = MainActivity.this.mainLayout.tab.page;
            if (!(a0Var instanceof Page_Login) && !(a0Var instanceof Page_Start)) {
                MainActivity mainActivity = MainActivity.this;
                new Breadcrumb(mainActivity, mainActivity.mainLayout.tab).show();
            }
        }
    }

    class C0206o implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0206o() {
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            int i = cVar2.f2511a;
            if (1 == i) {
                MainActivity.this.m896j(R.id.nav_unread_qms, DocumentManager.documentManager.unread2.m692f());
            } else if (2 == i || 3 == i) {
                MainActivity.this.m896j(R.id.nav_unread_fav, DocumentManager.documentManager.unread2.m694d());
            } else if (4 == i || 5 == i) {
                MainActivity.this.m896j(R.id.nav_unread_men, DocumentManager.documentManager.unread2.m693e());
            }
            return Boolean.TRUE;
        }
    }

    class C0207p implements Util.AbstractC0429j<Boolean, Object> {

        public class View$OnClickListenerC0208a implements View.OnClickListener {
            View$OnClickListenerC0208a() {
            }

            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        }

        C0207p() {
        }

        public Boolean mo222a(Object obj) {
            DlgSimple q1Var = new DlgSimple(MainActivity.this, "Версия приложения не поддерживается!", false, null, null);
            q1Var.editText.setVisibility(8);
            q1Var.m620f(new View$OnClickListenerC0208a(), true);
            q1Var.show(true, false, false);
            return Boolean.TRUE;
        }
    }

    class C0209q implements Util.AbstractC0429j<Boolean, String> {
        C0209q() {
        }

        public Boolean mo222a(String str) {
            SharedPreferences defaultSharedPreferences = null;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int hashCode = str.hashCode();
            try {
                defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                i = defaultSharedPreferences.getInt("update_msg", 0);
                i2 = 1;
                i3 = defaultSharedPreferences.getInt("update_msg_count", 1);
            } catch (Exception e) {
                e.printStackTrace();
                //ACRA.getErrorReporter().handleSilentException(new Exception("MainActivity - UpdateMsg", e));
            }
            if (i == hashCode && (i2 = 1 + i3) > 2) {
                return Boolean.FALSE;
            }
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.putInt("update_msg", hashCode);
            edit.putInt("update_msg_count", i2);
            edit.commit();
            Notify.createNotify(MainActivity.this, hashCode, "4pda-update", true, true, "Обновление 4PDA", str, Uri.parse("https://4pda.ru/forum/index.php?showtopic=673755&view=getlastpost"));
            return Boolean.TRUE;
        }
    }

    class ShowRunnablePageStatus implements Runnable {
        ShowRunnablePageStatus() {
        }

        @Override
        public void run() {
            MainActivity.this.mainLayout.showBageRunningStatus();
        }
    }

    class C0211s implements Util.AbstractC0429j<Boolean, Integer> {
        C0211s() {
        }

        public Boolean mo222a(Integer num) {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.mainLayout.post(mainActivity.runnablePageStatus);
            return Boolean.TRUE;
        }
    }

    class C0212t implements Util.AbstractC0429j<Boolean, Object> {
        C0212t() {
        }

        public Boolean mo222a(Object obj) {
            Toast.makeText(MainActivity.this, "Ошибка авторизации! Введите логин и пароль.", 1).show();
            DocumentManager.initAuthenticate();
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Login(MainActivity.this, false));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
            return Boolean.TRUE;
        }
    }

    class C0213u implements Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> {
        C0213u() {
        }

        public Boolean mo222a(DocumentManager.MemberInfoModel hVar) {
            int i = 8;
            if (hVar != null) {
                if (Prefs.topicPppServer) {
                    Prefs.memberPostsPerPage = hVar.memberPostsPerPage;
                }
                if (Prefs.forumTppServer) {
                    Prefs.memberTopicsPerPage = hVar.memberTopicsPerPage;
                }
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_name)).setText(hVar.memberInfoName);
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_group)).setText(hVar.memberGroupName);
                MainActivity.this.findViewById(R.id.nav_new_qms).setClickable(true);
                if (!TextUtils.isEmpty(hVar.memberAvatar)) {
                    PicoImgRequest l = PicoImg.loadUrl(MainActivity.this, hVar.memberAvatar);
                    l.to((ImageView)MainActivity.this.findViewById(R.id.nav_avatar));
                    l.disableAnimation(!Prefs.animAvatars);
                    l.runAsync();
                } else {
                    ((Widgets$AvatarView) MainActivity.this.findViewById(R.id.nav_avatar)).setImageDrawable(MainActivity.this.skin.getSkinDrawable(R.drawable.ic_avatar));
                }
                MainActivity.this.findViewById(R.id.nav_user_profile).setVisibility(0);
                MainActivity.this.findViewById(R.id.nav_user_history).setVisibility(0);
                MainActivity.this.findViewById(R.id.nav_user_edit).setVisibility(0);
                MainActivity.this.findViewById(R.id.nav_user_login).setVisibility(0);
                MainActivity.this.findViewById(R.id.nav_user_email).setVisibility(0);
                MainActivity.this.findViewById(R.id.nav_user_password).setVisibility(0);
                View findViewById = MainActivity.this.findViewById(R.id.nav_user_tickets);
                if (hVar.memberTickets) {
                    i = 0;
                }
                findViewById.setVisibility(i);
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_logout)).setText("Выйти из аккаунта");
            } else {
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_name)).setText("");
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_group)).setText("");
                ((Widgets$AvatarView) MainActivity.this.findViewById(R.id.nav_avatar)).setImageDrawable(MainActivity.this.skin.getSkinDrawable(R.drawable.ic_avatar));
                MainActivity.this.findViewById(R.id.nav_user_profile).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_history).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_edit).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_login).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_email).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_password).setVisibility(8);
                MainActivity.this.findViewById(R.id.nav_user_tickets).setVisibility(8);
                ((TextView) MainActivity.this.findViewById(R.id.nav_user_logout)).setText("Вход");
            }
            return Boolean.TRUE;
        }
    }

    public class View$OnClickListenerC0214v implements View.OnClickListener {
        View$OnClickListenerC0214v() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Start(MainActivity.this));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class View$OnClickListenerC0215w implements View.OnClickListener {
        View$OnClickListenerC0215w() {
        }

        @Override
        public void onClick(View view) {
            MainActivity.this.mainLayout.m866p(false);
            Tab f1Var = new Tab(MainActivity.this);
            f1Var.addPage(new Page_Forums(MainActivity.this));
            MainActivity.this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public class View$OnClickListenerC0216x implements View.OnClickListener {
        View$OnClickListenerC0216x() {
        }

        @Override
        public void onClick(View view) {
            if (DocumentManager.isLoggined()) {
                MainActivity.this.mainLayout.m866p(false);
                Tab f1Var = new Tab(MainActivity.this);
                f1Var.addPage(new Page_QMS_List(MainActivity.this));
                MainActivity.this.mainLayout.setCurrentTab(f1Var);
                return;
            }
            Toast.makeText(MainActivity.this, "Выполните вход", 1).show();
        }
    }

    public class View$OnClickListenerC0217y implements View.OnClickListener {
        View$OnClickListenerC0217y() {
        }

        @Override
        public void onClick(View view) {
            if (DocumentManager.isLoggined()) {
                MainActivity.this.mainLayout.m866p(false);
                Tab f1Var = new Tab(MainActivity.this);
                f1Var.addPage(new Page_Favorites(MainActivity.this, 0));
                MainActivity.this.mainLayout.setCurrentTab(f1Var);
                return;
            }
            Toast.makeText(MainActivity.this, "Выполните вход", 1).show();
        }
    }

    public class View$OnClickListenerC0218z implements View.OnClickListener {
        View$OnClickListenerC0218z() {
        }

        @Override
        public void onClick(View view) {
            if (DocumentManager.isLoggined()) {
                MainActivity.this.mainLayout.m866p(false);
                Tab f1Var = new Tab(MainActivity.this);
                f1Var.addPage(new Page_Mention(MainActivity.this, 0));
                MainActivity.this.mainLayout.setCurrentTab(f1Var);
                return;
            }
            Toast.makeText(MainActivity.this, "Выполните вход", 1).show();
        }
    }

    private void m903c(Bundle bundle) {
        int size = this.mainLayout.tabList.size();
        bundle.putInt("t_n", size);
        MainLayout mainLayout = this.mainLayout;
        bundle.putInt("t_c", mainLayout.tabList.indexOf(mainLayout.tab));
        for (int i = 0; i < size; i++) {
            this.mainLayout.tabList.get(i).m716j(bundle, "t" + i);
        }
        bundle.putInt("request_code", this.f737h);
        bundle.putString("attach_name", API.LoadForumAttachRequest.f977j);
        bundle.putString("attach_loc", API.LoadForumAttachRequest.f978k);
    }

    private void m902d(AbstractC0193g0 g0Var, Uri uri) {
        int lastIndexOf;
        Cursor query;
        if (uri == null) {
            Toast.makeText(this, "Файл-менеджер вернул пустой URI", 1).show();
            return;
        }
        String str = null;
        if ("content".equals(uri.getScheme()) && (query = getContentResolver().query(uri, null, null, null, null)) != null) {
            try {
                if (query.moveToFirst()) {
                    str = query.getString(query.getColumnIndex("_display_name"));
                }
            } finally {
                query.close();
            }
        }
        if (str == null && (lastIndexOf = (str = uri.getPath()).lastIndexOf(47)) != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        g0Var.mo426e(uri, str.replace(':', '_').replace('\"', '_').replace('\\', '_'));
    }

    public static void initNotify(Context context) {
        int i;
        int i2;
        PicoFCM.C0962g gVar;
        if (DocumentManager.isLoggined()) {
            boolean z = Prefs.qmsNotify;
            int i3 = z ? 1 :0;
            if (Prefs.notifyQmsSystem) {
                i3 = (z ? 1 : 0) | 2;
            }
            int i4 = i3;
            if (Prefs.favNotify) {
                int i5 = i3 == 1 ? 1 : 0;
                boolean z2 = i3 == 1;
                i4 = i5 | 4;
            }
            int i6 = i4;
            if (Prefs.favImportantNotify) {
                int i7 = i4 == 1 ? 1 : 0;
            //    boolean z3 = i4 == 1 ? 1 : 0;
                i6 = i7 | 8;
            }
            i = i6;
            if (Prefs.menNotify) {
                int i8 = i6 == 1 ? 1 : 0;
              //  boolean z4 = i6 == 1 ? 1 : 0;
                i = i8 | 16;
            }
        } else {
            i = 0;
        }
        int i9 = Prefs.backgroundMode;
        if (i9 == 4) {
            int i10 = i == 1 ? 1 : 0;
           // boolean z5 = i == 1 ? 1 : 0;
            i2 = i10;
        } else {
            i2 = 0;
        }
        int i11 = i;
        if (i9 != 5) {
            i11 = 0;
        }
        if (i2 != 0 && ((gVar = f728q) == null || PicoFCM.m13v(gVar))) {
            PicoFCM z0Var = new PicoFCM(context, "1:1043483203481:android:43c96e036dc3fe54");
            PicoFCM.C0962g r = z0Var.m17r();
            f728q = r;
            if (!z0Var.m12w(r)) {
                new AsyncTaskC0189f0(context, z0Var).execute();
                return;
            }
        }
        if (i11 == 0 || !TextUtils.isEmpty(token)) {
            PicoFCM.C0962g gVar2 = f728q;
            if (gVar2 != null) {
                DocumentManager.getResultRequest(new API.NotifyRequest(gVar2.m5c(), i2, 0));
            }
            if (!TextUtils.isEmpty(token)) {
                String str = token;
                int i12 = i11 == 1 ? 1 : 0;
                int i13 = i11 == 1 ? 1 : 0;
                DocumentManager.getResultRequest(new API.NotifyRequest(str, i12, 1));
            }
            if (i2 == 0) {
                f728q = null;
            }
            if (i11 == 0) {
                token = null;
                return;
            }
            return;
        }
        new AsyncTaskC0195h0(context).execute();
    }

    public static void m897i(Context context, String str) {
        token = str;
        if (!TextUtils.isEmpty(str)) {
            initNotify(context);
        }
    }

    public void m896j(int i, int i2) {
        TextView textView = findViewById(i);
        if (textView == null) {
            return;
        }
        if (i2 > 0) {
            textView.setVisibility(0);
            textView.setText(String.valueOf(i2));
            return;
        }
        textView.setVisibility(8);
    }

    public void OnProfileClick(View view) {
        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
        if (L != null) {
            this.mainLayout.m866p(false);
            ((Widgets$CheckboxView) findViewById(R.id.nav_expander)).m850b();
            int size = this.mainLayout.tabList.size() - 1;
            Page_Profile n0Var = null;
            Tab f1Var = null;
            while (true) {
                if (size < 0) {
                    break;
                }
                f1Var = this.mainLayout.tabList.get(size);
                Page a0Var = f1Var.page;
                if (a0Var instanceof Page_Profile) {
                    Page_Profile n0Var2 = (Page_Profile) a0Var;
                    if (n0Var2.userId == L.memberId) {
                        n0Var = n0Var2;
                        break;
                    }
                }
                size--;
            }
            if (n0Var == null) {
                f1Var = new Tab(this);
                n0Var = new Page_Profile(this, L.memberId, 0);
            }
            if (view == findViewById(R.id.nav_user_edit)) {
                n0Var.m576g0(3);
            } else if (view == findViewById(R.id.nav_user_login)) {
                n0Var.m576g0(8);
            } else if (view == findViewById(R.id.nav_user_email)) {
                n0Var.m576g0(4);
            } else if (view == findViewById(R.id.nav_user_password)) {
                n0Var.m576g0(5);
            }
            f1Var.addPage(n0Var);
            this.mainLayout.setCurrentTab(f1Var);
        }
    }

    public void m904b(Intent intent) {
        int c = Util.getAndIncrement();
        this.f737h = c;
        startActivityForResult(intent, c);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.mainLayout.dispatchKeyEvent(keyEvent);
    }

    public void m901e() {
        float applyDimension = TypedValue.applyDimension(2, (float) Prefs.textSize, getResources().getDisplayMetrics());
        BBString.m477l(applyDimension, this.f731b);
        try {
            BBOverlay.C0152h hVar = new BBOverlay.C0152h(this.skin.getSkinDrawable(R.drawable.text_select_handle_left));
            BBOverlay.C0152h.f593s = hVar;
            hVar.m936a(0.73f, 0.05f, 0.52f, 0.5f, applyDimension / 3.0f);
        } catch (Exception e) {
            //ACRA.getErrorReporter().handleSilentException(e);
        }
        try {
            BBOverlay.C0152h hVar2 = new BBOverlay.C0152h(this.skin.getSkinDrawable(R.drawable.text_select_handle_right));
            BBOverlay.C0152h.f594t = hVar2;
            hVar2.m936a(0.26f, 0.05f, 0.48f, 0.5f, applyDimension / 3.0f);
        } catch (Exception e2) {
            //ACRA.getErrorReporter().handleSilentException(e2);
        }
        try {
            BBOverlay.C0152h.f595u = new BBOverlay.C0152h.C0153a(this.skin.getSkinDrawable(R.drawable.np_float_panel), applyDimension * 1.1f, this.f731b);
        } catch (Exception e3) {
            //ACRA.getErrorReporter().handleSilentException(e3);
        }
    }

    public void m899g(Bundle bundle) {
        if (bundle == null && this.mainLayout != null) {
            bundle = new Bundle();
            m903c(bundle);
            CustomDialog.dismiss();
        }
        Skin e1Var = new Skin(this);
        this.skin = e1Var;
        Skin.SkinColorModel.initColors(e1Var);
        if (this.mainLayout == null) {
           // ((LayoutInflater) getSystemService("layout_inflater")).setFactory(new LayoutInflater$FactoryC0201k());
        }
        Window window = getWindow();
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (this.f735f == 0) {
                this.f735f = getWindow().getNavigationBarColor();
            }
            window.setStatusBarColor(Skin.SkinColorModel.f1368X);
            int i2 = Skin.SkinColorModel.f1369Y;
            if (i2 == 0) {
                i2 = this.f735f;
            }
            window.setNavigationBarColor(i2);
            if (i >= 23) {
                int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
                int i3 = (((systemUiVisibility & -8193) | (Skin.SkinColorModel.f1394l0 ? 8192 : 0)) & -17) | ((i < 26 || !Skin.SkinColorModel.f1396m0) ? 0 : 16);
                if (i3 != systemUiVisibility) {
                    window.getDecorView().setSystemUiVisibility(i3);
                }
            }
        }
        if (-16777216 != (Skin.SkinColorModel.mainBgColor & -16777216)) {
            window.addFlags(1048576);
        } else {
            window.clearFlags(1048576);
        }
        setTheme(Skin.SkinColorModel.f1392k0 ? R.style.Activity_Light : R.style.Activity_Dark);
        setContentView(R.layout.mainactivity);
        window.setBackgroundDrawable(this.skin.getSkinDrawable(R.drawable.main_background));
        MainLayout mainLayout = findViewById(R.id.main_layout);
        this.mainLayout = mainLayout;
        mainLayout.m860v(this);
        if (i >= 19 && i < 21) {
            C0184d0.m894b(this);
        }
        if (this.f734e > 0) {
            View findViewById = findViewById(R.id.top_status_spacer);
            findViewById.setBackgroundColor(Skin.SkinColorModel.f1368X);
            findViewById.getLayoutParams().height = this.f734e;
            findViewById.requestLayout();
        }
        findViewById(R.id.nav_new_home).setOnClickListener(new View$OnClickListenerC0214v());
        findViewById(R.id.nav_new_forum).setOnClickListener(new View$OnClickListenerC0215w());
        findViewById(R.id.nav_new_qms).setOnClickListener(new View$OnClickListenerC0216x());
        findViewById(R.id.nav_new_fav).setOnClickListener(new View$OnClickListenerC0217y());
        findViewById(R.id.nav_new_men).setOnClickListener(new View$OnClickListenerC0218z());
        findViewById(R.id.nav_new_site).setOnClickListener(new View$OnClickListenerC0178a0());
        findViewById(R.id.bar_menu).setOnClickListener(new View$OnClickListenerC0180b0());
        findViewById(R.id.bar_search).setOnClickListener(new View$OnClickListenerC0182c0());
        findViewById(R.id.nav_user_profile).setOnClickListener(new View$OnClickListenerC0177a());
        findViewById(R.id.nav_user_history).setOnClickListener(new HistoryOnClickListenerC0179b());
        findViewById(R.id.nav_user_edit).setOnClickListener(new View$OnClickListenerC0181c());
        findViewById(R.id.nav_user_login).setOnClickListener(new View$OnClickListenerC0183d());
        findViewById(R.id.nav_user_email).setOnClickListener(new View$OnClickListenerC0186e());
        findViewById(R.id.nav_user_password).setOnClickListener(new View$OnClickListenerC0188f());
        findViewById(R.id.nav_user_tickets).setOnClickListener(new View$OnClickListenerC0192g());
        findViewById(R.id.nav_options).setOnClickListener(new View$OnClickListenerC0194h());
        findViewById(R.id.nav_about).setOnClickListener(new View$OnClickListenerC0198i());
        findViewById(R.id.nav_user_logout).setOnClickListener(new LogOutClickListenerC0199j());
        findViewById(R.id.nav_exit).setOnClickListener(new View$OnClickListenerC0202l());
        View findViewById2 = findViewById(R.id.nav_night);
        int i4 = Prefs.nightMode;
        if (i4 == 2 || i4 == 3) {
            findViewById2.setVisibility(0);
            findViewById2.setOnClickListener(new View$OnClickListenerC0204m());
        } else {
            findViewById2.setVisibility(8);
        }
        this.mainLayout.setTitleBarClickListener(new View$OnClickListenerC0205n());
        this.f745p.mo222a(DocumentManager.getMemberInfoModel());
        Unread2 g1Var = DocumentManager.documentManager.unread2;
        m896j(R.id.nav_unread_qms, g1Var != null ? g1Var.m692f() : 0);
        Unread2 g1Var2 = DocumentManager.documentManager.unread2;
        m896j(R.id.nav_unread_fav, g1Var2 != null ? g1Var2.m694d() : 0);
        Unread2 g1Var3 = DocumentManager.documentManager.unread2;
        m896j(R.id.nav_unread_men, g1Var3 != null ? g1Var3.m693e() : 0);
        m901e();
        Tab f1Var = null;
        if (bundle != null) {
            int i5 = bundle.getInt("t_n", 0);
            for (int i6 = 0; i6 < i5; i6++) {
                new Tab(this, bundle, "t" + i6);
            }
            f1Var = this.mainLayout.tabList.get(bundle.getInt("t_c"));
            API.LoadForumAttachRequest.f977j = bundle.getString("attach_name");
            API.LoadForumAttachRequest.f978k = bundle.getString("attach_loc");
            this.f737h = bundle.getInt("request_code", 0);
        }
        if (f1Var == null) {
            f1Var = new Tab(this);
            if (Prefs.firstStart) {
                Prefs.firstStart = false;
                Prefs.saveBoolean(this, "first_start", false);
                if (!DocumentManager.isLoggined()) {
                    f1Var.addPage(new Page_Login(this, false));
                }
                ImageDialog.m918a(this);
            }
            if (f1Var.page == null) {
                f1Var.addPage(new Page_Start(this));
            }
        }
        this.mainLayout.setCurrentTab(f1Var);
    }

    public void m898h(AbstractC0193g0 g0Var) {
        this.f738i = g0Var != null ? new WeakReference<>(g0Var) : null;
    }

    @Override
    @SuppressLint({"NewApi"})
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i != this.f737h) {
           /* ErrorReporter errorReporter = //ACRA.getErrorReporter();
            errorReporter.handleSilentException(new Exception("Request code: " + this.f737h + " vs " + i));*/
            Toast.makeText(this, "Неправильный код запроса", 1).show();
        } else if (intent == null) {
            Toast.makeText(this, "Файл-менеджер вернул пустой результат", 1).show();
        } else {
            WeakReference<AbstractC0193g0> weakReference = this.f738i;
            AbstractC0193g0 g0Var = weakReference != null ? weakReference.get() : null;
            if (g0Var != null && !g0Var.mo423g(intent)) {
                if (intent.getData() != null) {
                    m902d(g0Var, intent.getData());
                } else if (Build.VERSION.SDK_INT < 16 || intent.getClipData() == null) {
                    Toast.makeText(this, "Файл-менеджер вернул пустой список файлов", 1).show();
                } else {
                    for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                        m902d(g0Var, intent.getClipData().getItemAt(i3).getUri());
                    }
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.skin.m733i(configuration) && Prefs.nightMode == 0) {
            m899g(null);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            Window window = getWindow();
            if (i >= 21) {
                window.addFlags(Integer.MIN_VALUE);
                window.clearFlags(67108864);
            } else {
                int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (identifier > 0) {
                    window.addFlags(67108864);
                    this.f734e = getResources().getDimensionPixelSize(identifier);
                }
            }
        }
        super.onCreate(bundle);
		
		Prefs.initPreference(this);
        DataDB.mkdirDB(this);
        DocumentManager.syncBookmarks(this);
        Notify.createNotificationChannel(this);
		
        in.cpp.picoimg.PicoImg.init(this, null, ((long) Prefs.cacheSize) * 1024 * 1024);
        Resources resources = getResources();
        this.f731b = resources.getDisplayMetrics().density;
        this.f732c = resources.getDisplayMetrics().scaledDensity;
        Page_Topic.PostModel.f3145x = (int) resources.getDimension(R.dimen.post_header_height);
        Page_Topic.PostModel.f3147z = (int) resources.getDimension(R.dimen.post_title_padding_height);
        Page_Topic.PostModel.f3146y = resources.getDimension(R.dimen.post_title_textsize);
        Page_Topic.PostModel.f3143A = (int) resources.getDimension(R.dimen.post_title_padding_width);
        Page_Topic.f3005l0 = (int) resources.getDimension(R.dimen.topic_hat_height);
        Page_Forum.f1508c0 = (int) resources.getDimension(R.dimen.topic_padding_width);
        Page_Forum.f1509d0 = (int) resources.getDimension(R.dimen.topic_padding_height);
        Page_Forum.f1510e0 = resources.getDimension(R.dimen.topic_name_textsize);
        Page_Forum.f1511f0 = resources.getDimension(R.dimen.topic_lastuser_textsize);
        ArticleLayout.f494s = resources.getDimension(R.dimen.article_title_textsize);
        ArticleLayout.f495t = (int) resources.getDimension(R.dimen.article_title_padding);
        ArticleLayout.f491p = (int) resources.getDimension(R.dimen.article_picture_250);
        ArticleLayout.f492q = (int) resources.getDimension(R.dimen.article_picture_400);
        ArticleLayout.f498w = (int) resources.getDimension(R.dimen.article_date_height);
        ArticleLayout.f497v = (int) resources.getDimension(R.dimen.article_tags_height);
        ArticleLayout.f496u = (int) resources.getDimension(R.dimen.article_more_height);
        ArticleLayout.f499x = (int) resources.getDimension(R.dimen.article_commentsheader_height);
        ArticleLayout.f500y = this.f731b;
        Page_QMS_Talk.QmsMessage.f2438k = (int) resources.getDimension(R.dimen.message_side_padding);
        Page_QMS_Talk.QmsMessage.f2439l = (int) resources.getDimension(R.dimen.message_avatar_width);
        int dimension = (int) resources.getDimension(R.dimen.separator_height);
        Page_Topic.PostModel.f3144w = dimension;
        ArticleLayout.f493r = dimension;
        Page_QMS_Talk.QmsMessage.f2437j = dimension;
        Page.f1069D = (int) resources.getDimension(R.dimen.pager_height);
        BBEditor.C0618g0.f2049e = (int) resources.getDimension(R.dimen.color_dialog_button_side);
        Util.fontelloTypeface = Typeface.createFromAsset(getAssets(), "fontello.ttf");
        Util.robotoTypeface = Typeface.createFromAsset(getAssets(), "RobotoMono_Regular.ttf");
        Util.SmileClass.initSmiles();
        setContentView(R.layout.forum_list_sep);
        m899g(bundle);
        DocumentManager.f2749F.m657a(this.f740k);
        DocumentManager.f2745B.m657a(this.f741l);
        DocumentManager.f2744A.m657a(this.f744o);
        DocumentManager.f2747D.m657a(this.f745p);
        DocumentManager.f2755z.m657a(this.f743n);
        DocumentManager.f2754y.m657a(this.f743n);
        Unread2.f1568b.m654a(this.f739j);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mainLayout.m881a();
        DocumentManager.f2749F.m656b(this.f740k);
        DocumentManager.f2745B.m656b(this.f741l);
        DocumentManager.f2744A.m656b(this.f744o);
        DocumentManager.f2747D.m656b(this.f745p);
        DocumentManager.f2755z.m656b(this.f743n);
        DocumentManager.f2754y.m656b(this.f743n);
        Unread2.f1568b.m653b(this.f739j);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (iArr != null && 1 <= iArr.length && iArr[0] == 0) {
            if (1 == i) {
                API.LoadForumAttachRequest.downloadFile(this);
            } else if (2 == i) {
                Prefs.exportSettings(this);
            } else if (3 == i) {
                Prefs.importSettings(this);
                initNotify(this);
                m899g(null);
            } else if (4 == i) {
                AttachDialog.m585s(this);
            }
        }
    }

    @Override
    protected void onResume() {
        Tab f1Var;
        Uri data;
        super.onResume();
        boolean z = this.f733d;
        this.f733d = false;
        DocumentManager.restartConnection(1);
        Intent intent = getIntent();
        Page a0Var = null;
        setIntent(null);
        if (!(intent == null || !"android.intent.action.VIEW".equals(intent.getAction()) || (data = intent.getData()) == null)) {
            Page a = Urls2.openPage(this, data, false, z ? 4 : 0);
            if (a != null) {
                a.f1079m = z;
                for (int i = 0; i < this.mainLayout.tabList.size(); i++) {
                    Tab f1Var2 = this.mainLayout.tabList.get(i);
                    Page a0Var2 = f1Var2.page;
                    Class<?> cls = a0Var2.getClass();
                    if (cls.equals(a.getClass()) && (cls.equals(Page_QMS_List.class) || cls.equals(Page_Favorites.class) || cls.equals(Page_Mention.class) || (cls.equals(Page_QMS_Talk.class) && ((Page_QMS_Talk) a0Var2).dialogId == ((Page_QMS_Talk) a).dialogId))) {
                        a0Var2.f1079m = z;
                        MainLayout mainLayout = this.mainLayout;
                        if (mainLayout.tab != f1Var2) {
                            mainLayout.setCurrentTab(f1Var2);
                            z = false;
                        }
                        if (a0Var != null) {
                            Tab f1Var3 = new Tab(this);
                            f1Var3.addPage(a0Var);
                            this.mainLayout.setCurrentTab(f1Var3);
                            z = false;
                        }
                    }
                }
            }
            a0Var = a;
            if (a0Var != null) {
            }
        }
        if (z && (f1Var = this.mainLayout.tab) != null) {
            f1Var.m720f();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        m903c(bundle);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.f733d = true;
        DocumentManager.restartConnection(0);
        Tab f1Var = this.mainLayout.tab;
        if (f1Var != null) {
            f1Var.m719g();
        }
    }
}
