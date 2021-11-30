package ru.fourpda.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import p012b.p011a.p012a.PicoImg;
import p012b.p011a.p012a.PicoImgRequest;

public class Page_Skins extends Page {
    private C0805j f2712H;
    boolean f2713I;
    List<C0804i> f2709E = new Vector(25);
    List<C0806k> f2710F = null;
    List<C0806k> f2711G = null;
    View.OnClickListener f2714J = new View$OnClickListenerC0802g();

    class RunnableC0796a implements Runnable {
        final String f2715a;
        final Throwable f2716b;

        RunnableC0796a(String str, Throwable th) {
            this.f2715a = str;
            this.f2716b = th;
        }

        @Override
        public void run() {
            MainActivity mainActivity = Page_Skins.this.f1073g;
            Toast.makeText(mainActivity, "Не удалось загрузить изображение для скина " + this.f2715a + ":\n" + this.f2716b.getClass().getCanonicalName() + ": " + this.f2716b.getMessage(), 1).show();
        }
    }

    class View$OnClickListenerC0797b implements View.OnClickListener {
        View$OnClickListenerC0797b() {
        }

        @Override
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag(R.id.setId)).intValue();
            if (intValue != 0) {
                Page_Skins.this.f1074h.m715k(new Page_Topic(Page_Skins.this.f1073g, intValue, 0));
            }
        }
    }

    class View$OnClickListenerC0798c implements View.OnClickListener {
        View$OnClickListenerC0798c() {
        }

        @Override
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (intValue != 0) {
                Page_Skins.this.f1074h.m715k(new Page_Topic(Page_Skins.this.f1073g, intValue, 0));
            }
        }
    }

    class View$OnClickListenerC0799d implements View.OnClickListener {
        View$OnClickListenerC0799d() {
        }

        @Override
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (intValue != 0) {
                Page_Skins u0Var = Page_Skins.this;
                u0Var.f1074h.m715k(new Page_Profile(u0Var.f1073g, intValue, 0));
            }
        }
    }

    class View$OnClickListenerC0800e implements View.OnClickListener {
        View$OnClickListenerC0800e() {
        }

        @Override
        public void onClick(View view) {
            C0806k kVar = (C0806k) view.getTag();
            MainActivity mainActivity = Page_Skins.this.f1073g;
            String str = kVar.f2739k;
            Prefs.f1183u = str;
            Prefs.m765f(mainActivity, "skin", str);
            if (!kVar.f2743o) {
                MainActivity mainActivity2 = Page_Skins.this.f1073g;
                Prefs.f1184v = 1;
                Prefs.m766e(mainActivity2, "nightMode", 1);
            } else if (1 == Prefs.f1184v) {
                MainActivity mainActivity3 = Page_Skins.this.f1073g;
                Prefs.f1184v = 0;
                Prefs.m766e(mainActivity3, "nightMode", 0);
            }
            Page_Skins.this.f1073g.m899g(null);
        }
    }

    class View$OnClickListenerC0801f implements View.OnClickListener {
        View$OnClickListenerC0801f() {
        }

        @Override
        public void onClick(View view) {
            C0806k kVar = (C0806k) view.getTag();
            int i = kVar.f2740l;
            if (i != 0) {
                MainActivity mainActivity = Page_Skins.this.f1073g;
                DocumentManager.m250g0(new API.C0262i(i, mainActivity, kVar.f2739k + ".apk"));
            }
        }
    }

    class View$OnClickListenerC0802g implements View.OnClickListener {
        View$OnClickListenerC0802g() {
        }

        @Override
        public void onClick(View view) {
            ((C0804i) view.getTag()).f2727c = ((Widgets$CheckboxTextView) view).getChecked();
            Page_Skins u0Var = Page_Skins.this;
            u0Var.f2711G = u0Var.m276d0(u0Var.f2710F, u0Var.f2709E);
            Page_Skins.this.m811Z(true);
        }
    }

    class C0803h implements C0667o1.AbstractC0669b {
        C0803h() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Skins.this.mo107z();
            } else if (i3 == 23) {
                Page_Skins u0Var = Page_Skins.this;
                DataDB.m365l(u0Var.f1088v, u0Var.mo50u());
            }
        }
    }

    public static class C0804i implements Comparable<C0804i> {
        String f2725a;
        int f2726b = 1;
        boolean f2727c = false;

        C0804i(String str) {
            this.f2725a = str;
        }

        private int m274b() {
            int i;
            int i2 = this.f2726b;
            if (m273c(this.f2725a).equals(m273c("установлен"))) {
                i = 50000;
            } else if (m273c(this.f2725a).equals(m273c("обновлён"))) {
                i = 40000;
            } else if (m273c(this.f2725a).equals(m273c("отладка"))) {
                return i2 + 30000;
            } else {
                if (m273c(this.f2725a).equals(m273c("светлый"))) {
                    return i2 + ACRAConstants.DEFAULT_SOCKET_TIMEOUT;
                }
                return m273c(this.f2725a).equals(m273c("тёмный")) ? i2 + 10000 : i2;
            }
            return i2 + i;
        }

        static String m273c(String str) {
            return str.toLowerCase().replace((char) 1105, (char) 1077);
        }

        public int compareTo(C0804i iVar) {
            return iVar.m274b() - m274b();
        }
    }

    private class C0805j extends BroadcastReceiver {
        private C0805j() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String dataString = intent.getDataString();
            if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && !TextUtils.isEmpty(dataString) && dataString.contains("ru.fourpda.skins")) {
                if (Page_Skins.this.m806v()) {
                    Page_Skins.this.mo107z();
                } else {
                    Page_Skins.this.f2713I = true;
                }
            }
        }

        C0805j(Page_Skins u0Var, RunnableC0796a aVar) {
            this();
        }
    }

    public static class C0806k {
        String f2729a;
        Bitmap f2730b;
        String f2731c;
        String f2732d;
        String f2733e;
        int f2734f;
        String f2735g;
        int f2736h;
        String f2737i;
        String f2738j;
        String f2739k;
        int f2740l;
        int f2741m;
        int f2742n;
        boolean f2743o;

        C0806k() {
        }
    }

    public Page_Skins(MainActivity mainActivity) {
        super(mainActivity, 29537, null);
        this.f1080n = R.drawable.ic_nav_options;
        this.f1088v = "Галерея скинов";
        this.f2799f = "Загрузка скинов";
    }

    @Override
    public void mo147A(Bundle bundle, String str) {
        super.mo147A(bundle, str);
        int i = bundle.getInt(str + "_kc");
        for (int i2 = 0; i2 < i; i2++) {
            C0804i iVar = new C0804i(bundle.getString(str + "_k" + i2));
            iVar.f2726b = 0;
            iVar.f2727c = true;
            this.f2709E.add(iVar);
        }
    }

    @Override
    public void mo57F() {
        this.f1074h.f1451i.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.f1073g.f731b * 42.0f);
        super.mo57F();
    }

    @Override
    protected boolean mo56H() {
        Exception e;
        PackageManager packageManager;
        boolean z;
        int i = 0;
        if (this.f1075i) {
            return false;
        }
        try {
            Vector vector = new Vector(250);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            C0806k kVar = new C0806k();
            kVar.f2731c = "Оригинальный скин";
            kVar.f2732d = "Базовый вид приложения #установлен";
            kVar.f2733e = "Базовый вид приложения #установлен";
            kVar.f2730b = this.f1073g.f736g.m739c(R.drawable.overview);
            kVar.f2739k = "";
            kVar.f2742n = 180;
            kVar.f2738j = "orig";
            kVar.f2743o = true;
            vector.add(kVar);
            Document l = this.f1077k.m283l(1);
            if (l != null) {
                for (int i2 = 0; i2 < l.m291d(); i2++) {
                    C0806k kVar2 = new C0806k();
                    Document l2 = l.m283l(i2);
                    kVar2.f2731c = Util.C0427h.m641c(l2.m281n(1));
                    String c = Util.C0427h.m641c(l2.m281n(2));
                    kVar2.f2732d = c;
                    kVar2.f2733e = c;
                    kVar2.f2735g = Util.C0427h.m641c(l2.m281n(4));
                    kVar2.f2734f = l2.m282m(3).intValue();
                    kVar2.f2736h = l2.m282m(0).intValue();
                    kVar2.f2729a = l2.m281n(8);
                    kVar2.f2737i = l2.m281n(6);
                    kVar2.f2741m = l2.m282m(7).intValue();
                    String trim = l2.m281n(5).trim();
                    kVar2.f2739k = trim;
                    if (trim.endsWith(".apk")) {
                        String str = kVar2.f2739k;
                        kVar2.f2739k = str.substring(0, str.length() - 4);
                    }
                    kVar2.f2740l = l2.m282m(9).intValue();
                    vector.add(kVar2);
                }
            }
            List<PackageInfo> list = null;
            try {
                packageManager = this.f1073g.getPackageManager();
                try {
                    list = packageManager.getInstalledPackages(0);
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                packageManager = null;
            }
            if (list != null) {
                int i3 = 0;
                while (i3 < list.size()) {
                    String str2 = list.get(i3).packageName;
                    if (str2.startsWith("ru.fourpda.skins")) {
                        Resources resourcesForApplication = packageManager.getResourcesForApplication(str2);
                        int i4 = 0;
                        while (true) {
                            if (i4 >= vector.size()) {
                                z = false;
                                break;
                            }
                            C0806k kVar3 = vector.get(i4);
                            if (kVar3.f2739k.equalsIgnoreCase(str2)) {
                                kVar3.f2738j = list.get(i3).versionName;
                                kVar3.f2739k = str2;
                                String str3 = kVar3.f2732d + " #установлен";
                                kVar3.f2732d = str3;
                                kVar3.f2733e = str3;
                                if (kVar3.f2737i.equals(kVar3.f2738j)) {
                                    kVar3.f2740l = i;
                                }
                                int i5 = list.get(i3).versionCode;
                                kVar3.f2742n = i5;
                                if (kVar3.f2741m > i5) {
                                    String str4 = kVar3.f2732d + " #обновлён";
                                    kVar3.f2732d = str4;
                                    kVar3.f2733e = str4;
                                }
                                kVar3.f2743o = resourcesForApplication.getIdentifier("zn_main_text", "color", str2) != 0;
                                z = true;
                            } else {
                                i4++;
                            }
                        }
                        if (!z) {
                            C0806k kVar4 = new C0806k();
                            kVar4.f2739k = str2;
                            kVar4.f2742n = list.get(i3).versionCode;
                            kVar4.f2738j = list.get(i3).versionName;
                            kVar4.f2731c = list.get(i3).applicationInfo.loadLabel(packageManager).toString();
                            int identifier = resourcesForApplication.getIdentifier("description", "string", str2);
                            if (identifier > 0) {
                                String str5 = resourcesForApplication.getString(identifier) + " #отладка #установлен";
                                kVar4.f2732d = str5;
                                kVar4.f2733e = str5;
                                int identifier2 = resourcesForApplication.getIdentifier("overview", "drawable", str2);
                                if (identifier2 > 0) {
                                    kVar4.f2730b = BitmapFactory.decodeResource(resourcesForApplication, identifier2, options);
                                    kVar4.f2743o = resourcesForApplication.getIdentifier("zn_main_text", "color", str2) != 0;
                                    vector.add(kVar4);
                                }
                            }
                        }
                    }
                    i3++;
                    i = 0;
                }
            }
            int i6 = 0;
            while (i6 < vector.size()) {
                C0806k kVar5 = vector.get(i6);
                if (kVar5.f2742n == 0 && kVar5.f2741m < 180) {
                    vector.remove(i6);
                    i6--;
                }
                if (kVar5.f2739k.equalsIgnoreCase("ru.fourpda.skins.dark")) {
                    vector.remove(i6);
                    i6--;
                }
                i6++;
            }
            Vector vector2 = new Vector(25);
            for (int i7 = 0; i7 < vector.size(); i7++) {
                String str6 = vector.get(i7).f2733e;
                int length = str6.length();
                while (length > 0) {
                    int lastIndexOf = str6.lastIndexOf(35, length);
                    if (lastIndexOf >= 0) {
                        int i8 = lastIndexOf - 1;
                        int i9 = lastIndexOf + 1;
                        int indexOf = str6.indexOf(32, i9);
                        if (indexOf < 0) {
                            indexOf = str6.length();
                        }
                        String substring = str6.substring(i9, indexOf);
                        String c2 = C0804i.m273c(substring);
                        int i10 = 0;
                        while (true) {
                            if (i10 >= vector2.size()) {
                                break;
                            }
                            C0804i iVar = vector2.get(i10);
                            if (c2.equals(C0804i.m273c(iVar.f2725a))) {
                                iVar.f2726b++;
                                break;
                            }
                            i10++;
                        }
                        if (i10 == vector2.size()) {
                            C0804i iVar2 = new C0804i(substring);
                            if (this.f2709E != null) {
                                for (int i11 = 0; i11 < this.f2709E.size(); i11++) {
                                    if (this.f2709E.get(i11).f2725a.equalsIgnoreCase(substring)) {
                                        iVar2.f2727c = this.f2709E.get(i11).f2727c;
                                    }
                                }
                            }
                            vector2.add(iVar2);
                        }
                        if (indexOf == str6.length()) {
                            try {
                                str6 = str6.substring(0, lastIndexOf).trim();
                            } catch (Exception e2) {
                                e = e2;
                                ACRA.getErrorReporter().handleSilentException(e);
                                return false;
                            }
                        }
                        length = i8;
                    }
                }
                vector.get(i7).f2732d = str6;
            }
            Collections.sort(vector2);
            this.f2711G = m276d0(vector, vector2);
            this.f2710F = vector;
            this.f2709E = vector2;
            return true;
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override
    public void mo142J(boolean z) {
        super.mo142J(z);
        this.f2712H = new C0805j(this, null);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.f1073g.registerReceiver(this.f2712H, intentFilter);
    }

    @Override
    public void mo55K(View view) {
        if (m805w()) {
            C0667o1 o1Var = new C0667o1(this.f1073g, new C0803h());
            if (Prefs.f1144E) {
                o1Var.m500a(0, 0, 21, "Обновить");
            }
            o1Var.m499b(0, 0, 23, "В закладки", DataDB.m366k(mo50u()));
            o1Var.m496e(view);
        }
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f2713I) {
            mo107z();
        }
        if (!z) {
            this.f1074h.f1451i.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    public void mo138Y(Bundle bundle, String str) {
        super.mo138Y(bundle, str);
        int i = 0;
        for (int i2 = 0; i2 < this.f2709E.size(); i2++) {
            C0804i iVar = this.f2709E.get(i2);
            if (iVar.f2727c) {
                bundle.putString(str + "_k" + i, iVar.f2725a);
                i++;
            }
        }
        bundle.putInt(str + "_kc", i);
    }

    List<C0806k> m276d0(List<C0806k> list, List<C0804i> list2) {
        Vector vector = new Vector(25);
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).f2727c) {
                vector.add(list2.get(i));
            }
        }
        if (vector.size() <= 0) {
            return list;
        }
        Vector vector2 = new Vector(250);
        for (int i2 = 0; i2 < list.size(); i2++) {
            String c = C0804i.m273c(list.get(i2).f2733e);
            int i3 = 0;
            while (i3 < vector.size()) {
                String c2 = C0804i.m273c(((C0804i) vector.get(i3)).f2725a);
                if (!c.endsWith("#" + c2)) {
                    if (!c.contains("#" + c2 + " ")) {
                        break;
                    }
                }
                i3++;
            }
            if (i3 == vector.size()) {
                vector2.add(list.get(i2));
            }
        }
        return vector2;
    }

    @Override
    public int getCount() {
        List<C0806k> list;
        if (!m805w() || (list = this.f2711G) == null) {
            return 0;
        }
        return list.size() + 1;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        if (view != null) {
            view2 = view;
        } else if (i == 0) {
            view2 = this.f1073g.getLayoutInflater().inflate(R.layout.skin_keywords, viewGroup, false);
            Widgets$WrappingLinearLayout widgets$WrappingLinearLayout = (Widgets$WrappingLinearLayout) view2.findViewById(R.id.skinKeywordsWrapper);
            for (int i2 = 0; i2 < this.f2709E.size(); i2++) {
                C0804i iVar = this.f2709E.get(i2);
                Widgets$CheckboxTextView widgets$CheckboxTextView = new Widgets$CheckboxTextView(this.f1073g);
                widgets$CheckboxTextView.setTag(iVar);
                widgets$CheckboxTextView.setText(iVar.f2725a + " (" + iVar.f2726b + ")");
                widgets$CheckboxTextView.setTextColor(Skin.C0353a.f1365U);
                widgets$CheckboxTextView.setBoxAlign(2);
                widgets$CheckboxTextView.setBackgroundDrawable(this.f1073g.f736g.m736f(R.drawable.skin_keyword_bg));
                widgets$CheckboxTextView.setChecked(iVar.f2727c);
                widgets$CheckboxTextView.setOnClickListener(this.f2714J);
                widgets$WrappingLinearLayout.addView(widgets$CheckboxTextView);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) widgets$CheckboxTextView.getLayoutParams();
                int i3 = (int) (this.f1073g.f731b * 8.0f);
                layoutParams.bottomMargin = i3;
                layoutParams.rightMargin = i3;
            }
            if (this.f2711G.size() == this.f2710F.size()) {
                TextView textView = new TextView(this.f1073g);
                textView.setTextColor(Skin.C0353a.f1365U);
                textView.setSingleLine();
                textView.setText("еще 99...");
                textView.setGravity(1);
                textView.setBackgroundDrawable(this.f1073g.f736g.m736f(R.drawable.skin_keyword_bg));
                widgets$WrappingLinearLayout.addView(textView);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView.getLayoutParams();
                int i4 = (int) (this.f1073g.f731b * 8.0f);
                layoutParams2.bottomMargin = i4;
                layoutParams2.rightMargin = i4;
                widgets$WrappingLinearLayout.m832b(3, textView);
            }
        } else {
            view2 = this.f1073g.getLayoutInflater().inflate(R.layout.skin, viewGroup, false);
            view2.findViewById(R.id.skinImage).setOnClickListener(new View$OnClickListenerC0797b());
            view2.findViewById(R.id.skinTitle).setOnClickListener(new View$OnClickListenerC0798c());
            view2.findViewById(R.id.skinAuthor).setOnClickListener(new View$OnClickListenerC0799d());
            ((TextView) view2.findViewById(R.id.skinEnable)).setOnClickListener(new View$OnClickListenerC0800e());
            ((TextView) view2.findViewById(R.id.skinDownload)).setOnClickListener(new View$OnClickListenerC0801f());
        }
        if (i > 0) {
            C0806k kVar = this.f2711G.get(i - 1);
            TextView textView2 = (TextView) view2.findViewById(R.id.skinTitle);
            textView2.setText(kVar.f2731c);
            textView2.setTag(Integer.valueOf(kVar.f2736h));
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            TextView textView3 = (TextView) view2.findViewById(R.id.skinAuthor);
            textView3.setTag(Integer.valueOf(kVar.f2734f));
            textView3.setText(kVar.f2735g);
            textView3.setVisibility(TextUtils.isEmpty(kVar.f2735g) ? 8 : 0);
            view2.findViewById(R.id.skinImage).setTag(R.id.setId, Integer.valueOf(kVar.f2736h));
            ((TextView) view2.findViewById(R.id.skinDesc)).setText(kVar.f2732d);
            TextView textView4 = (TextView) view2.findViewById(R.id.skinEnable);
            textView4.setTag(kVar);
            TextView textView5 = (TextView) view2.findViewById(R.id.skinDownload);
            textView5.setTag(kVar);
            TextView textView6 = (TextView) view2.findViewById(R.id.skinVersion);
            textView6.setTextColor(Skin.C0353a.f1365U);
            if (TextUtils.isEmpty(kVar.f2737i)) {
                textView6.setVisibility(8);
                textView5.setVisibility(8);
            } else {
                textView6.setVisibility(0);
                textView6.setText("Доступная версия: " + kVar.f2737i);
                textView5.setVisibility(kVar.f2740l == 0 ? 8 : 0);
            }
            int i5 = kVar.f2741m;
            if (i5 > 0 && i5 < 180) {
                textView5.setVisibility(8);
                textView6.setVisibility(0);
                textView6.setText("Доступная версия " + kVar.f2737i + " не поддерживается");
                textView6.setTextColor(-65536);
            }
            TextView textView7 = (TextView) view2.findViewById(R.id.skinInstalledVersion);
            textView7.setTextColor(Skin.C0353a.f1365U);
            if (TextUtils.isEmpty(kVar.f2738j)) {
                textView7.setVisibility(8);
                textView4.setVisibility(8);
            } else {
                textView7.setText("Установленная версия: " + kVar.f2738j);
                textView7.setVisibility(kVar.f2738j.equalsIgnoreCase("orig") ? 8 : 0);
                textView4.setVisibility(0);
            }
            int i6 = kVar.f2742n;
            if (i6 > 0 && i6 < 180) {
                textView4.setVisibility(8);
                textView7.setVisibility(0);
                textView7.setText("Установленная версия " + kVar.f2738j + " не поддерживается");
                textView7.setTextColor(-65536);
            }
            String str = kVar.f2739k;
            if (str != null && str.equalsIgnoreCase(Prefs.f1183u)) {
                textView4.setVisibility(8);
                ((TextView) view2.findViewById(R.id.skinTitle)).setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.f1073g.f736g.m736f(R.drawable.ic_menu_checkbox_on), (Drawable) null);
            }
            Widgets$AvatarView widgets$AvatarView = (Widgets$AvatarView) view2.findViewById(R.id.skinImage);
            Bitmap bitmap = kVar.f2730b;
            if (bitmap != null) {
                widgets$AvatarView.setImageBitmap(bitmap);
            } else {
                PicoImgRequest l = PicoImg.m1013l(this.f1073g, kVar.f2729a);
                l.m993r(widgets$AvatarView);
                l.m1001j();
            }
        }
        return view2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public void mo52r() {
        super.mo52r();
        C0805j jVar = this.f2712H;
        if (jVar != null) {
            this.f1073g.unregisterReceiver(jVar);
            this.f2712H = null;
        }
    }

    @Override
    public String mo50u() {
        return "forum/index.php?act=app-skins";
    }
}
