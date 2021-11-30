package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;

import in.cpp.picoimg.PicoImg;


public class Page_Options extends Page implements MainActivity.AbstractC0193g0 {
    DocumentManager.MemberInfoModel memberInfoModel;
    Runnable runnable;
    Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> f1719G = new C0469b0();

    class View$OnClickListenerC0461a implements View.OnClickListener {

        class View$OnClickListenerC0462a implements View.OnClickListener {
            final RelativeLayout f1721a;

            View$OnClickListenerC0462a(RelativeLayout relativeLayout) {
                this.f1721a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                int i = Prefs.f1178p;
                if (i > 10) {
                    Prefs.f1178p = i - 1;
                    Page_Options.this.m628f0(true, this.f1721a);
                }
            }
        }

        class View$OnClickListenerC0463b implements View.OnClickListener {
            final RelativeLayout f1723a;

            View$OnClickListenerC0463b(RelativeLayout relativeLayout) {
                this.f1723a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                int i = Prefs.f1178p;
                if (i < 30) {
                    Prefs.f1178p = i + 1;
                    Page_Options.this.m628f0(true, this.f1723a);
                }
            }
        }

        class DialogInterface$OnDismissListenerC0464c implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0464c() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0461a() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_text_size, (ViewGroup) null);
            relativeLayout.findViewById(R.id.option_down).setOnClickListener(new View$OnClickListenerC0462a(relativeLayout));
            relativeLayout.findViewById(R.id.option_up).setOnClickListener(new View$OnClickListenerC0463b(relativeLayout));
            Page_Options.this.m628f0(false, relativeLayout);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0464c());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0465a0 implements View.OnClickListener {
        View$OnClickListenerC0465a0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1171i = checked;
            Prefs.saveBoolean(mainActivity, "notification_vibration", checked);
        }
    }

    class View$OnClickListenerC0466b implements View.OnClickListener {

        class C0467a implements RadioGroup.OnCheckedChangeListener {
            C0467a() {
            }

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = 3;
                if (i == R.id.option_radio_1) {
                    i2 = 0;
                } else if (i == R.id.option_radio_2) {
                    i2 = 1;
                } else if (i != R.id.option_radio_3) {
                    if (i == R.id.option_radio_4) {
                        i2 = 2;
                    } else if (i == R.id.option_radio_5) {
                        i2 = 4;
                    } else if (i == R.id.option_radio_6) {
                        i2 = 5;
                    } else if (i == R.id.option_radio_7) {
                        i2 = 6;
                    }
                }
                if (i2 != Prefs.f1169g) {
                    MainActivity mainActivity = Page_Options.this.mainActivity;
                    Prefs.f1169g = i2;
                    Prefs.saveInt(mainActivity, "background_mode", i2);
                    MainActivity.m900f(Page_Options.this.mainActivity);
                    SharedPreferences.Editor edit = Page_Options.this.mainActivity.getSharedPreferences("background", 0).edit();
                    edit.putInt("background_mode", Prefs.f1169g);
                    edit.commit();
                }
            }
        }

        class DialogInterface$OnDismissListenerC0468b implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0468b() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0466b() {
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_radio, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_sub));
            RadioButton radioButton = (RadioButton) relativeLayout.findViewById(R.id.option_radio_1);
            radioButton.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_off));
            boolean z = false;
            radioButton.setChecked(Prefs.f1169g == 0);
            RadioButton radioButton2 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_2);
            radioButton2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_seldom));
            radioButton2.setChecked(Prefs.f1169g == 1);
            RadioButton radioButton3 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_3);
            radioButton3.setVisibility(0);
            radioButton3.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_progressively));
            radioButton3.setChecked(Prefs.f1169g == 3);
            RadioButton radioButton4 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_4);
            radioButton4.setVisibility(0);
            radioButton4.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_frequently));
            radioButton4.setChecked(Prefs.f1169g == 2);
            if (PicoFCM.m14u(Page_Options.this.mainActivity)) {
                RadioButton radioButton5 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_5);
                radioButton5.setVisibility(0);
                radioButton5.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_fcm));
                radioButton5.setChecked(Prefs.f1169g == 4);
            }
            if (PicoHCM.m784s(Page_Options.this.mainActivity)) {
                RadioButton radioButton6 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_6);
                radioButton6.setVisibility(0);
                radioButton6.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_BackgroundMode_hcm));
                if (Prefs.f1169g == 5) {
                    z = true;
                }
                radioButton6.setChecked(z);
            }
            ((RadioGroup) relativeLayout.findViewById(R.id.option_radio_group)).setOnCheckedChangeListener(new C0467a());
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0468b());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class C0469b0 implements Util.AbstractC0429j<Boolean, DocumentManager.MemberInfoModel> {
        C0469b0() {
        }

        public Boolean mo222a(DocumentManager.MemberInfoModel hVar) {
            Page_Options.this.m810c0("", false);
            if (hVar == null || !Page_Options.this.isUnsucces()) {
                return Boolean.FALSE;
            }
            try {
                Runnable runnable = Page_Options.this.runnable;
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Exception unused) {
            }
            Page_Options.this.tabLoaded(true);
            return Boolean.TRUE;
        }
    }

    class View$OnClickListenerC0470c implements View.OnClickListener {

        class C0471a implements RadioGroup.OnCheckedChangeListener {
            C0471a() {
            }

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.option_radio_1) {
                    MainActivity mainActivity = Page_Options.this.mainActivity;
                    Prefs.f1165c = false;
                    Prefs.saveBoolean(mainActivity, "fav_notify", false);
                    MainActivity mainActivity2 = Page_Options.this.mainActivity;
                    Prefs.f1166d = false;
                    Prefs.saveBoolean(mainActivity2, "fav_important_notify", false);
                } else if (i == R.id.option_radio_2) {
                    MainActivity mainActivity3 = Page_Options.this.mainActivity;
                    Prefs.f1165c = true;
                    Prefs.saveBoolean(mainActivity3, "fav_notify", true);
                    MainActivity mainActivity4 = Page_Options.this.mainActivity;
                    Prefs.f1166d = true;
                    Prefs.saveBoolean(mainActivity4, "fav_important_notify", true);
                } else if (i == R.id.option_radio_3) {
                    MainActivity mainActivity5 = Page_Options.this.mainActivity;
                    Prefs.f1165c = true;
                    Prefs.saveBoolean(mainActivity5, "fav_notify", true);
                    MainActivity mainActivity6 = Page_Options.this.mainActivity;
                    Prefs.f1166d = false;
                    Prefs.saveBoolean(mainActivity6, "fav_important_notify", false);
                }
                if (4 == Prefs.f1169g) {
                    MainActivity.m900f(Page_Options.this.mainActivity);
                }
            }
        }

        class DialogInterface$OnDismissListenerC0472b implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0472b() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0470c() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_radio, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_FavNotify));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_FavNotify_sub));
            RadioButton radioButton = (RadioButton) relativeLayout.findViewById(R.id.option_radio_1);
            radioButton.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_FavNotify_off));
            radioButton.setChecked(!Prefs.f1165c);
            RadioButton radioButton2 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_2);
            radioButton2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_FavNotify_important));
            boolean z = false;
            radioButton2.setChecked(Prefs.f1165c && Prefs.f1166d);
            RadioButton radioButton3 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_3);
            radioButton3.setVisibility(0);
            radioButton3.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_FavNotify_all));
            if (Prefs.f1165c && !Prefs.f1166d) {
                z = true;
            }
            radioButton3.setChecked(z);
            ((RadioGroup) relativeLayout.findViewById(R.id.option_radio_group)).setOnCheckedChangeListener(new C0471a());
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0472b());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class C0473c0 implements OptionPoupupMenuView.IClickListener {
        C0473c0() {
        }

        @SuppressLint("WrongConstant")
        @Override
        public void mo49a(int i, int i2, int i3) {
            int i4 = Build.VERSION.SDK_INT;
            if (i3 == 21) {
                Prefs.resetSettings(Page_Options.this.mainActivity);
                Prefs.initPreference(Page_Options.this.mainActivity);
                DocumentManager.isMemberValid();
                Page_Options.this.mainActivity.m899g(null);
                MainActivity.m900f(Page_Options.this.mainActivity);
            } else if (i3 == 22) {
                Page_Options l0Var = Page_Options.this;
                DataDB.m365l(l0Var.title, l0Var.getLink());
            } else if (i3 == 5) {
                if (i4 < 23 || Page_Options.this.mainActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    Prefs.exportSettings(Page_Options.this.mainActivity);
                } else {
                    Page_Options.this.mainActivity.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
                }
            } else if (i3 != 6) {
            } else {
                if (i4 < 23 || Page_Options.this.mainActivity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                    Prefs.importSettings(Page_Options.this.mainActivity);
                    MainActivity.m900f(Page_Options.this.mainActivity);
                    Page_Options.this.mainActivity.m899g(null);
                    return;
                }
                Page_Options.this.mainActivity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 3);
            }
        }
    }

    class View$OnClickListenerC0474d implements View.OnClickListener {

        class View$OnClickListenerC0475a implements View.OnClickListener {
            View$OnClickListenerC0475a() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1141B = checked;
                Prefs.saveBoolean(mainActivity, "auto_refresh", checked);
            }
        }

        class View$OnClickListenerC0476b implements View.OnClickListener {
            View$OnClickListenerC0476b() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1142C = checked;
                Prefs.saveBoolean(mainActivity, "swipe_refresh_top", checked);
            }
        }

        class View$OnClickListenerC0477c implements View.OnClickListener {
            View$OnClickListenerC0477c() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1143D = checked;
                Prefs.saveBoolean(mainActivity, "swipe_refresh_bottom", checked);
            }
        }

        class View$OnClickListenerC0478d implements View.OnClickListener {
            View$OnClickListenerC0478d() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.showReloadButton = checked;
                Prefs.saveBoolean(mainActivity, "reload_button", checked);
            }
        }

        class DialogInterface$OnDismissListenerC0479e implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0479e() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0474d() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_checkbox, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh_sub));
            Widgets$CheckboxTextView widgets$CheckboxTextView = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_1);
            widgets$CheckboxTextView.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh_auto));
            widgets$CheckboxTextView.setChecked(Prefs.f1141B);
            widgets$CheckboxTextView.setOnClickListener(new View$OnClickListenerC0475a());
            Widgets$CheckboxTextView widgets$CheckboxTextView2 = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_2);
            widgets$CheckboxTextView2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh_swipe_top));
            widgets$CheckboxTextView2.setChecked(Prefs.f1142C);
            widgets$CheckboxTextView2.setOnClickListener(new View$OnClickListenerC0476b());
            Widgets$CheckboxTextView widgets$CheckboxTextView3 = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_3);
            widgets$CheckboxTextView3.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh_swipe_bottom));
            widgets$CheckboxTextView3.setChecked(Prefs.f1143D);
            widgets$CheckboxTextView3.setOnClickListener(new View$OnClickListenerC0477c());
            Widgets$CheckboxTextView widgets$CheckboxTextView4 = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_4);
            widgets$CheckboxTextView4.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PageRefresh_button));
            widgets$CheckboxTextView4.setChecked(Prefs.showReloadButton);
            widgets$CheckboxTextView4.setOnClickListener(new View$OnClickListenerC0478d());
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0479e());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0480d0 implements View.OnClickListener {

        class View$OnClickListenerC0481a implements View.OnClickListener {
            final RelativeLayout f1742a;

            View$OnClickListenerC0481a(RelativeLayout relativeLayout) {
                this.f1742a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                Prefs.f1181s = Math.max(10, Prefs.f1181s - 1);
                Prefs.f1182t = false;
                Page_Options.this.m627g0(true, this.f1742a);
            }
        }

        class View$OnClickListenerC0482b implements View.OnClickListener {
            final RelativeLayout f1744a;

            View$OnClickListenerC0482b(RelativeLayout relativeLayout) {
                this.f1744a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                Prefs.f1181s = Math.min(100, Prefs.f1181s + 1);
                Prefs.f1182t = false;
                Page_Options.this.m627g0(true, this.f1744a);
            }
        }

        class View$OnClickListenerC0483c implements View.OnClickListener {
            final RelativeLayout f1746a;

            class RunnableC0484a implements Runnable {
                RunnableC0484a() {
                }

                @Override
                public void run() {
                    View$OnClickListenerC0483c cVar = View$OnClickListenerC0483c.this;
                    Page_Options.this.m627g0(true, cVar.f1746a);
                }
            }

            View$OnClickListenerC0483c(RelativeLayout relativeLayout) {
                this.f1746a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                if (!Prefs.f1182t) {
                    Prefs.f1182t = true;
                    Page_Options.this.m627g0(true, this.f1746a);
                    Page_Options.this.runnable = new RunnableC0484a();
                    if (DocumentManager.isMemberValid()) {
                        Page_Options.this.m810c0("Запрос установок", true);
                    }
                }
            }
        }

        class DialogInterface$OnDismissListenerC0485d implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0485d() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options l0Var = Page_Options.this;
                l0Var.runnable = null;
                l0Var.tabLoaded(true);
            }
        }

        View$OnClickListenerC0480d0() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_up_down, (ViewGroup) null);
            relativeLayout.findViewById(R.id.option_down).setOnClickListener(new View$OnClickListenerC0481a(relativeLayout));
            relativeLayout.findViewById(R.id.option_up).setOnClickListener(new View$OnClickListenerC0482b(relativeLayout));
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_TopicsPerPage));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_TopicsPerPage_sub));
            ((TextView) relativeLayout.findViewById(R.id.option_button)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PerPageServer));
            relativeLayout.findViewById(R.id.option_button).setOnClickListener(new View$OnClickListenerC0483c(relativeLayout));
            Page_Options.this.m627g0(false, relativeLayout);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0485d());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0486e implements View.OnClickListener {
        View$OnClickListenerC0486e() {
        }

        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT >= 26) {
                Page_Options.this.mainActivity.startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", Page_Options.this.mainActivity.getPackageName()));
                return;
            }
            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
            intent.putExtra("android.intent.extra.ringtone.TITLE", Page_Options.this.mainActivity.getResources().getString(R.string.option_NotificationSound));
            intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
            intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);
            intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", Uri.parse(Prefs.f1170h));
            intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(2));
            Page_Options.this.mainActivity.m904b(intent);
        }
    }

    class View$OnClickListenerC0487e0 implements View.OnClickListener {

        class C0488a implements RadioGroup.OnCheckedChangeListener {
            C0488a() {
            }

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = Prefs.f1187y;
                if (i == R.id.option_radio_1) {
                    Prefs.f1187y = 0;
                } else if (i == R.id.option_radio_2) {
                    Prefs.f1187y = 1;
                }
                int i3 = Prefs.f1187y;
                if (i2 != i3) {
                    Prefs.saveInt(Page_Options.this.mainActivity, "topic_action", i3);
                }
            }
        }

        class DialogInterface$OnDismissListenerC0489b implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0489b() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0487e0() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_radio, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Topic));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Topic_sub));
            RadioButton radioButton = (RadioButton) relativeLayout.findViewById(R.id.option_radio_1);
            radioButton.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_toBegin));
            boolean z = false;
            radioButton.setChecked(Prefs.f1187y == 0);
            RadioButton radioButton2 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_2);
            radioButton2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_toEnd));
            if (Prefs.f1187y == 1) {
                z = true;
            }
            radioButton2.setChecked(z);
            ((RadioGroup) relativeLayout.findViewById(R.id.option_radio_group)).setOnCheckedChangeListener(new C0488a());
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0489b());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0490f implements View.OnClickListener {

        class View$OnClickListenerC0491a implements View.OnClickListener {
            View$OnClickListenerC0491a() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1146G = checked;
                Prefs.saveBoolean(mainActivity, "anim_avatars", checked);
            }
        }

        class View$OnClickListenerC0492b implements View.OnClickListener {
            View$OnClickListenerC0492b() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1147H = checked;
                Prefs.saveBoolean(mainActivity, "anim_smiles", checked);
            }
        }

        class View$OnClickListenerC0493c implements View.OnClickListener {
            View$OnClickListenerC0493c() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = Page_Options.this.mainActivity;
                boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
                Prefs.f1148I = checked;
                Prefs.saveBoolean(mainActivity, "anim_images", checked);
            }
        }

        class DialogInterface$OnDismissListenerC0494d implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0494d() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0490f() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_checkbox, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_AnimImages));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_AnimImages_sub));
            Widgets$CheckboxTextView widgets$CheckboxTextView = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_1);
            widgets$CheckboxTextView.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_AnimImages_avas));
            widgets$CheckboxTextView.setChecked(Prefs.f1146G);
            widgets$CheckboxTextView.setOnClickListener(new View$OnClickListenerC0491a());
            Widgets$CheckboxTextView widgets$CheckboxTextView2 = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_2);
            widgets$CheckboxTextView2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_AnimImages_emos));
            widgets$CheckboxTextView2.setChecked(Prefs.f1147H);
            widgets$CheckboxTextView2.setOnClickListener(new View$OnClickListenerC0492b());
            Widgets$CheckboxTextView widgets$CheckboxTextView3 = (Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_3);
            widgets$CheckboxTextView3.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_AnimImages_pics));
            widgets$CheckboxTextView3.setChecked(Prefs.f1148I);
            widgets$CheckboxTextView3.setOnClickListener(new View$OnClickListenerC0493c());
            ((Widgets$CheckboxTextView) relativeLayout.findViewById(R.id.option_checkbox_4)).setVisibility(8);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0494d());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0495f0 implements View.OnClickListener {
        View$OnClickListenerC0495f0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.showAllPost = checked;
            Prefs.saveBoolean(mainActivity, "show_all_posts", checked);
        }
    }

    class View$OnClickListenerC0496g implements View.OnClickListener {
        View$OnClickListenerC0496g() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1163a = checked;
            Prefs.saveBoolean(mainActivity, "qms_notify", checked);
            if (4 == Prefs.f1169g) {
                MainActivity.m900f(Page_Options.this.mainActivity);
            }
        }
    }

    class View$OnClickListenerC0497g0 implements View.OnClickListener {
        View$OnClickListenerC0497g0() {
        }

        @Override
        public void onClick(View view) {
            Page_Options l0Var = Page_Options.this;
           // l0Var.f1074h.m715k(new Page_Skins(l0Var.f1073g));
        }
    }

    class View$OnClickListenerC0498h implements View.OnClickListener {
        View$OnClickListenerC0498h() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1164b = checked;
            Prefs.saveBoolean(mainActivity, "notify_qms_system", checked);
            if (4 == Prefs.f1169g) {
                MainActivity.m900f(Page_Options.this.mainActivity);
            }
        }
    }

    class View$OnClickListenerC0499h0 implements View.OnClickListener {

        class C0500a implements OptionPoupupMenuView.IClickListener {
            C0500a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                if (Prefs.f1184v != i3) {
                    MainActivity mainActivity = Page_Options.this.mainActivity;
                    Prefs.f1184v = i3;
                    Prefs.saveInt(mainActivity, "nightMode", i3);
                    Page_Options.this.mainActivity.m899g(null);
                }
            }
        }

        View$OnClickListenerC0499h0() {
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Options.this.mainActivity, new C0500a());
            o1Var.addMenuItem(0, 0, 0, Page_Options.this.mainActivity.getResources().getString(R.string.option_Night_System), Prefs.f1184v == 0);
            o1Var.addMenuItem(0, 0, 2, Page_Options.this.mainActivity.getResources().getString(R.string.option_Night_Off), Prefs.f1184v == 2);
            o1Var.addMenuItem(0, 0, 3, Page_Options.this.mainActivity.getResources().getString(R.string.option_Night_On), Prefs.f1184v == 3);
            if (1 != Prefs.f1184v) {
                o1Var.show(null);
            }
        }
    }

    class View$OnClickListenerC0501i implements View.OnClickListener {
        View$OnClickListenerC0501i() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1173k = checked;
            Prefs.saveBoolean(mainActivity, "topic_hide_header", checked);
        }
    }

    class View$OnClickListenerC0502i0 implements View.OnClickListener {
        View$OnClickListenerC0502i0() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean z = !Prefs.f1162W;
            Prefs.f1162W = z;
            Prefs.saveBoolean(mainActivity, "options_extended", z);
            Page_Options.this.tabLoaded(true);
        }
    }

    class View$OnClickListenerC0503j implements View.OnClickListener {
        View$OnClickListenerC0503j() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1174l = checked;
            Prefs.saveBoolean(mainActivity, "topic_hide_poll", checked);
        }
    }

    class View$OnClickListenerC0504k implements View.OnClickListener {
        View$OnClickListenerC0504k() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1175m = checked;
            Prefs.saveBoolean(mainActivity, "post_show_sign", checked);
        }
    }

    class View$OnClickListenerC0505l implements View.OnClickListener {

        class View$OnClickListenerC0506a implements View.OnClickListener {
            final RelativeLayout f1770a;

            View$OnClickListenerC0506a(RelativeLayout relativeLayout) {
                this.f1770a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                int i = Prefs.f1150K;
                if (i > 128) {
                    Prefs.f1150K = i - 128;
                    Page_Options.this.m630d0(true, this.f1770a);
                }
            }
        }

        class View$OnClickListenerC0507b implements View.OnClickListener {
            final RelativeLayout f1772a;

            View$OnClickListenerC0507b(RelativeLayout relativeLayout) {
                this.f1772a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                int i = Prefs.f1150K;
                if (i < 10240) {
                    Prefs.f1150K = i + 128;
                    Page_Options.this.m630d0(true, this.f1772a);
                }
            }
        }

        class View$OnClickListenerC0508c implements View.OnClickListener {
            final RelativeLayout f1774a;

            class RunnableC0509a implements Runnable {
                RunnableC0509a() {
                }

                @Override
                public void run() {
                    View$OnClickListenerC0508c cVar = View$OnClickListenerC0508c.this;
                    Page_Options.this.m630d0(false, cVar.f1774a);
                }
            }

            View$OnClickListenerC0508c(RelativeLayout relativeLayout) {
                this.f1774a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                PicoImg.emptyCache(Page_Options.this.mainActivity, new RunnableC0509a());
            }
        }

        class DialogInterface$OnDismissListenerC0510d implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0510d() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0505l() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_up_down, (ViewGroup) null);
            relativeLayout.findViewById(R.id.option_down).setOnClickListener(new View$OnClickListenerC0506a(relativeLayout));
            relativeLayout.findViewById(R.id.option_up).setOnClickListener(new View$OnClickListenerC0507b(relativeLayout));
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_CacheSize));
            ((TextView) relativeLayout.findViewById(R.id.option_button)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_CacheClear));
            relativeLayout.findViewById(R.id.option_button).setOnClickListener(new View$OnClickListenerC0508c(relativeLayout));
            Page_Options.this.m630d0(false, relativeLayout);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0510d());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0511m implements View.OnClickListener {
        View$OnClickListenerC0511m() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1176n = checked;
            Prefs.saveBoolean(mainActivity, "post_editor_tags_below", checked);
        }
    }

    class View$OnClickListenerC0512n implements View.OnClickListener {
        View$OnClickListenerC0512n() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1177o = checked;
            Prefs.saveBoolean(mainActivity, "qms_hide_tags", !checked);
        }
    }

    class View$OnClickListenerC0513o implements View.OnClickListener {
        View$OnClickListenerC0513o() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1145F = checked;
            Prefs.saveBoolean(mainActivity, "load_images", checked);
        }
    }

    class View$OnClickListenerC0514p implements View.OnClickListener {
        View$OnClickListenerC0514p() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1149J = checked;
            Prefs.saveBoolean(mainActivity, "scale_images", checked);
            Toast.makeText(Page_Options.this.mainActivity, "Необходимо обновить страницы", 0).show();
        }
    }

    class View$OnClickListenerC0515q implements View.OnClickListener {
        View$OnClickListenerC0515q() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1151L = checked;
            Prefs.saveBoolean(mainActivity, "confirm_action", checked);
        }
    }

    class View$OnClickListenerC0516r implements View.OnClickListener {
        View$OnClickListenerC0516r() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1152M = checked;
            Prefs.saveBoolean(mainActivity, "attach_chooser", checked);
        }
    }

    class View$OnClickListenerC0517s implements View.OnClickListener {
        View$OnClickListenerC0517s() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1153N = checked;
            Prefs.saveBoolean(mainActivity, "upload_chooser", checked);
        }
    }

    class View$OnClickListenerC0518t implements View.OnClickListener {
        View$OnClickListenerC0518t() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1154O = checked;
           // Prefs.m764g(mainActivity, "send_report", checked);
            //ACRA.getErrorReporter().setEnabled(Prefs.f1154O);
        }
    }

    class View$OnClickListenerC0519u implements View.OnClickListener {

        class C0520a implements RadioGroup.OnCheckedChangeListener {
            C0520a() {
            }

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = Prefs.f1185w;
                if (i == R.id.option_radio_1) {
                    Prefs.f1185w = 0;
                } else if (i == R.id.option_radio_2) {
                    Prefs.f1185w = 1;
                } else if (i == R.id.option_radio_3) {
                    Prefs.f1185w = 2;
                }
                int i3 = Prefs.f1185w;
                if (i2 != i3) {
                    Prefs.saveInt(Page_Options.this.mainActivity, "scroll_mode", i3);
                }
            }
        }

        class DialogInterface$OnDismissListenerC0521b implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0521b() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options.this.tabLoaded(true);
            }
        }

        View$OnClickListenerC0519u() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_radio, (ViewGroup) null);
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Scroll));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Scroll_sub));
            RadioButton radioButton = (RadioButton) relativeLayout.findViewById(R.id.option_radio_1);
            radioButton.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Scroll_Regular));
            boolean z = false;
            radioButton.setChecked(Prefs.f1185w == 0);
            RadioButton radioButton2 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_2);
            radioButton2.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Scroll_Fling));
            radioButton2.setChecked(Prefs.f1185w == 1);
            RadioButton radioButton3 = (RadioButton) relativeLayout.findViewById(R.id.option_radio_3);
            radioButton3.setVisibility(0);
            radioButton3.setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_Scroll_Fast));
            if (Prefs.f1185w == 2) {
                z = true;
            }
            radioButton3.setChecked(z);
            ((RadioGroup) relativeLayout.findViewById(R.id.option_radio_group)).setOnCheckedChangeListener(new C0520a());
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0521b());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0522v implements View.OnClickListener {
        View$OnClickListenerC0522v() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1186x = checked;
            Prefs.saveBoolean(mainActivity, "back_button", checked);
        }
    }

    class View$OnClickListenerC0523w implements View.OnClickListener {

        class View$OnClickListenerC0524a implements View.OnClickListener {
            final RelativeLayout f1791a;

            View$OnClickListenerC0524a(RelativeLayout relativeLayout) {
                this.f1791a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                Prefs.f1179q = Math.max(10, Prefs.f1179q - 1);
                Prefs.f1180r = false;
                Page_Options.this.m629e0(true, this.f1791a);
            }
        }

        class View$OnClickListenerC0525b implements View.OnClickListener {
            final RelativeLayout f1793a;

            View$OnClickListenerC0525b(RelativeLayout relativeLayout) {
                this.f1793a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                Prefs.f1179q = Math.min(100, Prefs.f1179q + 1);
                Prefs.f1180r = false;
                Page_Options.this.m629e0(true, this.f1793a);
            }
        }

        class View$OnClickListenerC0526c implements View.OnClickListener {
            final RelativeLayout f1795a;

            class RunnableC0527a implements Runnable {
                RunnableC0527a() {
                }

                @Override
                public void run() {
                    View$OnClickListenerC0526c cVar = View$OnClickListenerC0526c.this;
                    Page_Options.this.m629e0(true, cVar.f1795a);
                }
            }

            View$OnClickListenerC0526c(RelativeLayout relativeLayout) {
                this.f1795a = relativeLayout;
            }

            @Override
            public void onClick(View view) {
                if (!Prefs.f1180r) {
                    Prefs.f1180r = true;
                    Page_Options.this.m629e0(true, this.f1795a);
                    Page_Options.this.runnable = new RunnableC0527a();
                    if (DocumentManager.isMemberValid()) {
                        Page_Options.this.m810c0("Запрос установок", true);
                    }
                }
            }
        }

        class DialogInterface$OnDismissListenerC0528d implements DialogInterface.OnDismissListener {
            DialogInterface$OnDismissListenerC0528d() {
            }

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Page_Options l0Var = Page_Options.this;
                l0Var.runnable = null;
                l0Var.tabLoaded(true);
            }
        }

        View$OnClickListenerC0523w() {
        }

        @Override
        public void onClick(View view) {
            Dialog dialog = new Dialog(Page_Options.this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) Page_Options.this.mainActivity.getLayoutInflater().inflate(R.layout.option_up_down, (ViewGroup) null);
            relativeLayout.findViewById(R.id.option_down).setOnClickListener(new View$OnClickListenerC0524a(relativeLayout));
            relativeLayout.findViewById(R.id.option_up).setOnClickListener(new View$OnClickListenerC0525b(relativeLayout));
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PostsPerPage));
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PostsPerPage_sub));
            ((TextView) relativeLayout.findViewById(R.id.option_button)).setText(Page_Options.this.mainActivity.getResources().getString(R.string.option_PerPageServer));
            relativeLayout.findViewById(R.id.option_button).setOnClickListener(new View$OnClickListenerC0526c(relativeLayout));
            Page_Options.this.m629e0(false, relativeLayout);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(Page_Options.this.mainActivity.skin.m736f(R.drawable.np_dialog));
            dialog.getWindow().setLayout((Page_Options.this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC0528d());
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    class View$OnClickListenerC0529x implements View.OnClickListener {
        View$OnClickListenerC0529x() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1140A = checked;
            Prefs.saveBoolean(mainActivity, "swipe_nextprev", checked);
        }
    }

    class View$OnClickListenerC0530y implements View.OnClickListener {
        View$OnClickListenerC0530y() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1168f = checked;
            Prefs.saveBoolean(mainActivity, "notify_group", checked);
        }
    }

    class View$OnClickListenerC0531z implements View.OnClickListener {
        View$OnClickListenerC0531z() {
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = Page_Options.this.mainActivity;
            boolean checked = ((Widgets$CheckboxTextView) view).getChecked();
            Prefs.f1167e = checked;
            Prefs.saveBoolean(mainActivity, "men_notify", checked);
            if (4 == Prefs.f1169g) {
                MainActivity.m900f(Page_Options.this.mainActivity);
            }
        }
    }

    public Page_Options(MainActivity mainActivity) {
        super(mainActivity);
        this.iconId = R.drawable.ic_nav_options;
        this.title = "Настройки";
        if (!DocumentManager.isLoggined()) {
            Prefs.f1162W = false;
        }
        this.memberInfoModel = DocumentManager.getMemberInfoModel();
    }

    @Override
    public void onSearchBar() {
        ViewGroup.LayoutParams layoutParams = this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams();
        MainActivity mainActivity = this.mainActivity;
        layoutParams.width = (int) (mainActivity.f731b * 42.0f);
        mainActivity.m898h(null);
        super.onSearchBar();
    }

    @Override
    @SuppressLint({"NewApi"})
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0473c0());
        o1Var.addMenuItem(0, 0, 21, "Сбросить настройки");
        o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 5, "Экспорт настроек");
        if (new File(Environment.getExternalStorageDirectory(), "4PDA.opt").exists()) {
            o1Var.addMenuItem(0, 0, 6, "Импорт настроек");
        }
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
            this.mainActivity.m898h(this);
        }
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        DocumentManager.f2747D.m657a(this.f1719G);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        DocumentManager.f2747D.m656b(this.f1719G);
        return true;
    }

    void m630d0(boolean z, RelativeLayout relativeLayout) {
        ((TextView) relativeLayout.findViewById(R.id.option_label)).setText("Используется: " + Util.formatFileSize(PicoImg.getCacheUsage()));
        boolean z2 = true;
        relativeLayout.findViewById(R.id.option_down).setEnabled(Prefs.f1150K > 128);
        View findViewById = relativeLayout.findViewById(R.id.option_up);
        if (Prefs.f1150K >= 10240) {
            z2 = false;
        }
        findViewById.setEnabled(z2);
        ((TextView) relativeLayout.findViewById(R.id.option_value)).setText(Integer.valueOf(Prefs.f1150K).toString());
        if (z) {
            Prefs.saveInt(this.mainActivity, "cache_size", Prefs.f1150K);
            PicoImg.setCacheSize(((long) Prefs.f1150K) * 1024 * 1024);
        }
    }

    @Override
    public void mo426e(Uri uri, String str) {
    }

    void m629e0(boolean z, RelativeLayout relativeLayout) {
        ((TextView) relativeLayout.findViewById(R.id.option_value)).setText(Integer.valueOf(Prefs.f1179q).toString());
        boolean z2 = true;
        int i = 0;
        relativeLayout.findViewById(R.id.option_down).setEnabled(Prefs.f1179q > 10);
        View findViewById = relativeLayout.findViewById(R.id.option_up);
        if (Prefs.f1179q >= 100) {
            z2 = false;
        }
        findViewById.setEnabled(z2);
        View findViewById2 = relativeLayout.findViewById(R.id.option_button);
        if (Prefs.f1180r) {
            i = 8;
        }
        findViewById2.setVisibility(i);
        if (z) {
            Prefs.saveInt(this.mainActivity, "topic_ppp", Prefs.f1179q);
            Prefs.saveBoolean(this.mainActivity, "topic_ppp_server", Prefs.f1180r);
        }
    }

    void m628f0(boolean z, RelativeLayout relativeLayout) {
        ((TextView) relativeLayout.findViewById(R.id.option_example)).setTextSize((float) Prefs.f1178p);
        ((TextView) relativeLayout.findViewById(R.id.option_value)).setText(Integer.valueOf(Prefs.f1178p).toString());
        boolean z2 = true;
        relativeLayout.findViewById(R.id.option_down).setEnabled(Prefs.f1178p > 10);
        View findViewById = relativeLayout.findViewById(R.id.option_up);
        if (Prefs.f1178p >= 30) {
            z2 = false;
        }
        findViewById.setEnabled(z2);
        if (z) {
            this.mainActivity.m901e();
            Prefs.saveInt(this.mainActivity, "text_size", Prefs.f1178p);
        }
    }

    @Override
    public boolean mo423g(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
        String uri2 = uri != null ? uri.toString() : "";
        Prefs.f1170h = uri2;
        Prefs.saveString(this.mainActivity, "notification_sound", uri2);
        tabLoaded(true);
        return true;
    }

    void m627g0(boolean z, RelativeLayout relativeLayout) {
        ((TextView) relativeLayout.findViewById(R.id.option_value)).setText(Integer.valueOf(Prefs.f1181s).toString());
        boolean z2 = true;
        int i = 0;
        relativeLayout.findViewById(R.id.option_down).setEnabled(Prefs.f1181s > 10);
        View findViewById = relativeLayout.findViewById(R.id.option_up);
        if (Prefs.f1181s >= 100) {
            z2 = false;
        }
        findViewById.setEnabled(z2);
        View findViewById2 = relativeLayout.findViewById(R.id.option_button);
        if (Prefs.f1182t) {
            i = 8;
        }
        findViewById2.setVisibility(i);
        if (z) {
            Prefs.saveInt(this.mainActivity, "forum_tpp", Prefs.f1181s);
            Prefs.saveBoolean(this.mainActivity, "forum_tpp_server", Prefs.f1182t);
        }
    }

    @Override
    public int getCount() {
        if (!isUnsucces()) {
            return 0;
        }
        if (Prefs.f1162W) {
            return 38;
        }
        return DocumentManager.isLoggined() ? 7 : 14;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        if (Prefs.f1162W) {
            if (i == 0 || i == 9 || i == 20 || i == 27 || i == 32) {
                return 2;
            }
            if (18 == i || 19 == i || 15 == i || 31 == i || 6 == i || 16 == i || 17 == i || 23 == i || 26 == i || 8 == i || 3 == i || 21 == i || 29 == i) {
                return 1;
            }
            if (i == 37) {
                return 3;
            }
            if (i == 7) {
                if (Build.VERSION.SDK_INT >= 26) {
                    return 4;
                }
                return 0;
            } else if (i != 24) {
                return 0;
            } else {
                DocumentManager.MemberInfoModel hVar = this.memberInfoModel;
                if (hVar == null) {
                    return 4;
                }
                int i2 = hVar.memberGroup;
                if (i2 == 4 || i2 == 10 || i2 == 9 || i2 == 11 || i2 == 19) {
                    return 0;
                }
                return 4;
            }
        } else if (!DocumentManager.isLoggined()) {
            return (i == 1 || i == 0 || i == 12 || i == 8) ? 1 : 0;
        } else {
            if (i == 5 || i == 3 || i == 4 || i == 1) {
                return 1;
            }
            return i == 6 ? 3 : 0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View widgets$CheckboxTextView;
        String str;
        View widgets$CheckboxTextView2;
        int i2 = i;
        int itemViewType = getItemViewType(i);
        float f = this.mainActivity.f731b;
        if (!Prefs.f1162W) {
            if (DocumentManager.isLoggined()) {
                if (i2 == 0) {
                    i2 = 1;
                } else if (i2 == 1) {
                    i2 = 3;
                } else {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 == 4) {
                                i2 = 6;
                            } else {
                                if (i2 != 5) {
                                    if (i2 == 6) {
                                        i2 = 37;
                                    }
                                }
                                i2 = 18;
                            }
                        }
                        i2 = 15;
                    }
                    i2 = 28;
                }
            } else if (i2 == 6) {
                i2 = 22;
            } else if (i2 == 12) {
                i2 = 31;
            } else if (i2 == 13) {
                i2 = 34;
            } else {
                if (i2 != 9) {
                    if (i2 == 10) {
                        i2 = 29;
                    } else if (i2 == 8) {
                        i2 = 26;
                    } else if (i2 == 11) {
                        i2 = 30;
                    } else if (i2 == 4) {
                        i2 = 12;
                    } else {
                        if (i2 != 1) {
                            if (i2 == 7) {
                                i2 = 25;
                            } else {
                                if (i2 != 0) {
                                    if (i2 == 2) {
                                        i2 = 10;
                                    } else if (i2 == 3) {
                                        i2 = 11;
                                    } else if (i2 == 5) {
                                        i2 = 21;
                                    }
                                }
                                i2 = 15;
                            }
                        }
                        i2 = 18;
                    }
                }
                i2 = 28;
            }
        }
        if (view != null) {
            widgets$CheckboxTextView = view;
        } else if (itemViewType == 1) {
            Widgets$CheckboxTextView widgets$CheckboxTextView3 = new Widgets$CheckboxTextView(this.mainActivity);
            widgets$CheckboxTextView3.setBoxAlign(2);
            widgets$CheckboxTextView3.setTextSize(18.0f);
            widgets$CheckboxTextView3.setTextColor(Skin.C0353a.f1365U);
            widgets$CheckboxTextView3.setGravity(16);
            widgets$CheckboxTextView = widgets$CheckboxTextView3;
        } else if (itemViewType == 3) {
            Widgets$CheckboxTextView widgets$CheckboxTextView4 = new Widgets$CheckboxTextView(this.mainActivity);
            widgets$CheckboxTextView4.setBoxAlign(2);
            widgets$CheckboxTextView4.setTextSize(18.0f);
            widgets$CheckboxTextView4.setTextColor(Skin.C0353a.f1365U);
            widgets$CheckboxTextView4.setGravity(16);
            widgets$CheckboxTextView4.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.button_bg_solid));
            int i3 = (int) (f * 16.0f);
            int i4 = (int) (f * 8.0f);
            widgets$CheckboxTextView4.setPadding(i3, i4, i3, i4);
            widgets$CheckboxTextView = widgets$CheckboxTextView4;
        } else {
            if (itemViewType == 2) {
                widgets$CheckboxTextView2 = this.mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
            } else if (itemViewType == 4) {
                return new View(this.mainActivity);
            } else {
                Widgets$CheckboxTextView widgets$CheckboxTextView5 = new Widgets$CheckboxTextView(this.mainActivity);
                widgets$CheckboxTextView5.setBoxAlign(1);
                widgets$CheckboxTextView5.setTextSize(18.0f);
                widgets$CheckboxTextView5.setTextColor(Skin.C0353a.f1365U);
                widgets$CheckboxTextView5.setGravity(16);
                widgets$CheckboxTextView5.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.option_checkbox));
                widgets$CheckboxTextView2 = widgets$CheckboxTextView5;
            }
            widgets$CheckboxTextView = widgets$CheckboxTextView2;
        }
        if (itemViewType == 2) {
            widgets$CheckboxTextView.setBackgroundDrawable(this.mainActivity.skin.m736f(i2 != 0 ? R.drawable.card_sep : R.color.cardlist_bg));
            int i5 = (int) (f * 16.0f);
            widgets$CheckboxTextView.setPadding(i5, i5, i5, (int) (f * 8.0f));
        } else if (itemViewType == 1) {
            widgets$CheckboxTextView.setBackgroundDrawable(this.mainActivity.skin.m736f(getItemViewType(i2 + 1) == 2 ? R.drawable.button_bg : R.drawable.button_bg_border));
            int i6 = (int) (f * 16.0f);
            int i7 = (int) (f * 8.0f);
            widgets$CheckboxTextView.setPadding(i6, i7, i6, i7);
        }
        if (i2 == 0) {
            ((TextView) widgets$CheckboxTextView).setText(this.mainActivity.getResources().getString(R.string.option_sep_notification));
        } else if (i2 == 9) {
            ((TextView) widgets$CheckboxTextView).setText(this.mainActivity.getResources().getString(R.string.option_sep_interface));
        } else if (i2 == 20) {
            ((TextView) widgets$CheckboxTextView).setText(this.mainActivity.getResources().getString(R.string.option_sep_behavior));
        } else if (i2 == 27) {
            ((TextView) widgets$CheckboxTextView).setText(this.mainActivity.getResources().getString(R.string.option_sep_images));
        } else if (i2 == 32) {
            ((TextView) widgets$CheckboxTextView).setText(this.mainActivity.getResources().getString(R.string.option_sep_other));
        } else if (i2 == 15) {
            Widgets$CheckboxTextView widgets$CheckboxTextView6 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView6.setText(this.mainActivity.getResources().getString(R.string.option_TextSize));
            widgets$CheckboxTextView6.setSubText(this.mainActivity.getResources().getString(R.string.option_TextSize_sub) + "\nРавен: " + Integer.valueOf(Prefs.f1178p).toString());
            widgets$CheckboxTextView6.setOnClickListener(new View$OnClickListenerC0461a());
        } else if (i2 == 31) {
            Widgets$CheckboxTextView widgets$CheckboxTextView7 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView7.setText(this.mainActivity.getResources().getString(R.string.option_CacheSize));
            widgets$CheckboxTextView7.setSubText("Используется " + Util.formatFileSize(PicoImg.getCacheUsage()) + " из " + Prefs.f1150K + " МБ");
            widgets$CheckboxTextView7.setOnClickListener(new View$OnClickListenerC0505l());
        } else if (i2 == 16) {
            Widgets$CheckboxTextView widgets$CheckboxTextView8 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView8.setText(this.mainActivity.getResources().getString(R.string.option_PostsPerPage));
            widgets$CheckboxTextView8.setSubText(this.mainActivity.getResources().getString(R.string.option_PostsPerPage_sub) + "\nКоличество: " + Integer.valueOf(Prefs.f1179q).toString());
            widgets$CheckboxTextView8.setOnClickListener(new View$OnClickListenerC0523w());
        } else if (i2 == 17) {
            Widgets$CheckboxTextView widgets$CheckboxTextView9 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView9.setText(this.mainActivity.getResources().getString(R.string.option_TopicsPerPage));
            widgets$CheckboxTextView9.setSubText(this.mainActivity.getResources().getString(R.string.option_TopicsPerPage_sub) + "\nКоличество: " + Integer.valueOf(Prefs.f1181s).toString());
            widgets$CheckboxTextView9.setOnClickListener(new View$OnClickListenerC0480d0());
        } else if (i2 == 23) {
            Widgets$CheckboxTextView widgets$CheckboxTextView10 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView10.setText(this.mainActivity.getResources().getString(R.string.option_Topic));
            StringBuilder sb = new StringBuilder();
            sb.append(this.mainActivity.getResources().getString(R.string.option_Topic_sub));
            sb.append("\nДействие: ");
            sb.append(this.mainActivity.getResources().getString(Prefs.f1187y == 0 ? R.string.option_toBegin : R.string.option_toEnd));
            widgets$CheckboxTextView10.setSubText(sb.toString());
            widgets$CheckboxTextView10.setOnClickListener(new View$OnClickListenerC0487e0());
        } else if (i2 == 24 && itemViewType == 0) {
            Widgets$CheckboxTextView widgets$CheckboxTextView11 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView11.setText(this.mainActivity.getResources().getString(R.string.option_ShowAllPosts));
            widgets$CheckboxTextView11.setSubText(this.mainActivity.getResources().getString(R.string.option_ShowAllPosts_sub));
            widgets$CheckboxTextView11.setChecked(Prefs.showAllPost);
            widgets$CheckboxTextView11.setOnClickListener(new View$OnClickListenerC0495f0());
        } else if (i2 == 18) {
            Widgets$CheckboxTextView widgets$CheckboxTextView12 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView12.setText(this.mainActivity.getResources().getString(R.string.option_Skin));
            widgets$CheckboxTextView12.setSubText((TextUtils.isEmpty(Prefs.f1183u) || TextUtils.isEmpty(this.mainActivity.skin.m735g())) ? "оригинальный" : this.mainActivity.skin.m735g());
            widgets$CheckboxTextView12.setOnClickListener(new View$OnClickListenerC0497g0());
        } else if (i2 == 19) {
            Widgets$CheckboxTextView widgets$CheckboxTextView13 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView13.setText(this.mainActivity.getResources().getString(R.string.option_Night_Title));
            int i8 = Prefs.f1184v;
            if (1 == i8) {
                widgets$CheckboxTextView13.setSubText(this.mainActivity.getResources().getString(R.string.option_Night_Unavail));
            } else if (i8 == 0) {
                widgets$CheckboxTextView13.setSubText(this.mainActivity.getResources().getString(R.string.option_Night_System));
            } else if (2 == i8) {
                widgets$CheckboxTextView13.setSubText(this.mainActivity.getResources().getString(R.string.option_Night_Off));
            } else if (3 == i8) {
                widgets$CheckboxTextView13.setSubText(this.mainActivity.getResources().getString(R.string.option_Night_On));
            }
            widgets$CheckboxTextView13.setOnClickListener(new View$OnClickListenerC0499h0());
        } else if (i2 == 37) {
            Widgets$CheckboxTextView widgets$CheckboxTextView14 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView14.setText(this.mainActivity.getResources().getString(Prefs.f1162W ? R.string.option_Simple : R.string.option_Extended));
            widgets$CheckboxTextView14.setSubText(this.mainActivity.getResources().getString(Prefs.f1162W ? R.string.option_Simple_sub : R.string.option_Extended_sub));
            widgets$CheckboxTextView14.setOnClickListener(new View$OnClickListenerC0502i0());
        } else if (i2 == 6) {
            Widgets$CheckboxTextView widgets$CheckboxTextView15 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView15.setText(this.mainActivity.getResources().getString(R.string.option_BackgroundMode));
            String str2 = this.mainActivity.getResources().getString(R.string.option_BackgroundMode_sub) + "\nУстановлено: ";
            int i9 = Prefs.f1169g;
            if (i9 == 0) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_off);
            } else if (i9 == 1) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_seldom);
            } else if (i9 == 2) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_frequently);
            } else if (i9 == 3) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_progressively);
            } else if (i9 == 4) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_fcm);
            } else if (i9 == 5) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_hcm);
            } else if (i9 == 6) {
                str2 = str2 + this.mainActivity.getResources().getString(R.string.option_BackgroundMode_xmpush);
            }
            widgets$CheckboxTextView15.setSubText(str2);
            widgets$CheckboxTextView15.setOnClickListener(new View$OnClickListenerC0466b());
        } else if (i2 == 3) {
            Widgets$CheckboxTextView widgets$CheckboxTextView16 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView16.setText(this.mainActivity.getResources().getString(R.string.option_FavNotify));
            String str3 = this.mainActivity.getResources().getString(R.string.option_FavNotify_sub) + "\nУстановлено: ";
            if (Prefs.f1165c && Prefs.f1166d) {
                str = str3 + this.mainActivity.getResources().getString(R.string.option_FavNotify_important);
            } else if (Prefs.f1165c) {
                str = str3 + this.mainActivity.getResources().getString(R.string.option_FavNotify_all);
            } else {
                str = str3 + this.mainActivity.getResources().getString(R.string.option_FavNotify_off);
            }
            widgets$CheckboxTextView16.setSubText(str);
            widgets$CheckboxTextView16.setOnClickListener(new View$OnClickListenerC0470c());
        } else if (i2 == 26) {
            Widgets$CheckboxTextView widgets$CheckboxTextView17 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView17.setText(this.mainActivity.getResources().getString(R.string.option_PageRefresh));
            widgets$CheckboxTextView17.setSubText(this.mainActivity.getResources().getString(R.string.option_PageRefresh_sub));
            widgets$CheckboxTextView17.setOnClickListener(new View$OnClickListenerC0474d());
        } else if (i2 == 8) {
            Widgets$CheckboxTextView widgets$CheckboxTextView18 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView18.setText(this.mainActivity.getResources().getString(R.string.option_NotificationSound));
            if (Build.VERSION.SDK_INT >= 26) {
                widgets$CheckboxTextView18.setSubText(this.mainActivity.getResources().getString(R.string.option_NotificationSound_select));
            } else if (TextUtils.isEmpty(Prefs.f1170h)) {
                widgets$CheckboxTextView18.setSubText(this.mainActivity.getResources().getString(R.string.option_NotificationSound_none));
            } else if (Prefs.f1170h.equals("default")) {
                widgets$CheckboxTextView18.setSubText(this.mainActivity.getResources().getString(R.string.option_NotificationSound_default));
            } else {
                try {
                    Ringtone ringtone = RingtoneManager.getRingtone(this.mainActivity, Uri.parse(Prefs.f1170h));
                    widgets$CheckboxTextView18.setSubText(ringtone != null ? ringtone.getTitle(this.mainActivity) : this.mainActivity.getResources().getString(R.string.option_NotificationSound_none));
                } catch (Exception e) {
                    //ACRA.getErrorReporter().handleSilentException(e);
                }
            }
            widgets$CheckboxTextView18.setOnClickListener(new View$OnClickListenerC0486e());
        } else if (i2 == 29) {
            Widgets$CheckboxTextView widgets$CheckboxTextView19 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            widgets$CheckboxTextView19.setText(this.mainActivity.getResources().getString(R.string.option_AnimImages));
            widgets$CheckboxTextView19.setSubText(this.mainActivity.getResources().getString(R.string.option_AnimImages_sub));
            widgets$CheckboxTextView19.setOnClickListener(new View$OnClickListenerC0490f());
        } else if (itemViewType != 4) {
            Widgets$CheckboxTextView widgets$CheckboxTextView20 = (Widgets$CheckboxTextView) widgets$CheckboxTextView;
            if (i2 == 1) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_QmsNotify));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_QmsNotify_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1163a);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0496g());
            } else if (i2 == 2) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_QmsSystemNotify));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_QmsSystemNotify_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1164b);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0498h());
            } else if (i2 == 10) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_TopicHeader));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_TopicHeader_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1173k);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0501i());
            } else if (i2 == 11) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_TopicPoll));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_TopicPoll_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1174l);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0503j());
            } else if (i2 == 12) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_Signature));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_Signature_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1175m);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0504k());
            } else if (i2 == 13) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_TagsPosition));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_TagsPosition_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1176n);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0511m());
            } else if (i2 == 14) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_QmsTags));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_QmsTags_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1177o);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0512n());
            } else if (i2 == 28) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_LoadImages));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_LoadImages_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1145F);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0513o());
            } else if (i2 == 30) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_ScaleImages));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_ScaleImages_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1149J);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0514p());
            } else if (i2 == 33) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_Confirm));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_Confirm_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1151L);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0515q());
            } else if (i2 == 34) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_Chooser));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_Chooser_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1152M);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0516r());
            } else if (i2 == 35) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_UploadChooser));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_UploadChooser_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1153N);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0517s());
            } else if (i2 == 36) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_SendReport));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_SendReport_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1154O);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0518t());
            } else if (i2 == 21) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_Scroll));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.mainActivity.getResources().getString(R.string.option_Scroll_sub));
                sb2.append("\nВыбран: ");
                Resources resources = this.mainActivity.getResources();
                int i10 = Prefs.f1185w;
                sb2.append(resources.getString(i10 == 2 ? R.string.option_Scroll_Fast : i10 == 1 ? R.string.option_Scroll_Fling : R.string.option_Scroll_Regular));
                widgets$CheckboxTextView20.setSubText(sb2.toString());
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0519u());
            } else if (i2 == 22) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_Back));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_Back_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1186x);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0522v());
            } else if (i2 == 25) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_SwipeNextPrev));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_SwipeNextPrev_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1140A);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0529x());
            } else if (i2 == 5) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_GroupNotify));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_GroupNotify_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1168f);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0530y());
            } else if (i2 == 4) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_MenNotify));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_MenNotify_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1167e);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0531z());
            } else if (i2 == 7) {
                widgets$CheckboxTextView20.setText(this.mainActivity.getResources().getString(R.string.option_NotificationVirbation));
                widgets$CheckboxTextView20.setSubText(this.mainActivity.getResources().getString(R.string.option_NotificationVirbation_sub));
                widgets$CheckboxTextView20.setChecked(Prefs.f1171i);
                widgets$CheckboxTextView20.setOnClickListener(new View$OnClickListenerC0465a0());
            }
        }
        return widgets$CheckboxTextView;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public String getLink() {
        return "forum/index.php?act=app-options";
    }

    @Override
    public void tabReload() {
        tabLoaded(true);
    }
}
