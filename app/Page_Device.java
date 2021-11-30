package ru.fourpda.client;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;
import p012b.p011a.p012a.PicoImg;
import p012b.p011a.p012a.PicoImgRequest;

public class Page_Device extends Page {
    String f1322E;
    String f1323F;
    List<C0351c> f1324G;
    LinearLayout f1325H;
    Document f1326I;
    View$OnClickListenerC0350b f1327J;
    private String f1328K;

    class C0349a implements C0667o1.AbstractC0669b {
        C0349a() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            String str;
            if (i3 == 21) {
                Page_Device.this.mo107z();
            } else if (i3 == 22) {
                Page_Device e0Var = Page_Device.this;
                DataDB.m365l(e0Var.f1088v, e0Var.mo50u());
            } else {
                int i4 = 1;
                if (i3 == 1) {
                    Util.m673a(Page_Device.this.f1073g, "https://4pda.ru/" + Page_Device.this.mo50u(), "Ссылка скопирована");
                } else if (i3 == 2) {
                    if (Page_Device.this.f1077k.m282m(9).intValue() > 0) {
                        i4 = 0;
                    }
                    Page_Device e0Var2 = Page_Device.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Page_Device.this.f1322E);
                    if (Page_Device.this.f1323F == null) {
                        str = "";
                    } else {
                        str = ":" + Page_Device.this.f1323F;
                    }
                    sb.append(str);
                    DocumentManager.m250g0(new C0352d(e0Var2, i4, sb.toString()));
                }
            }
        }
    }

    class View$OnClickListenerC0350b implements View.OnClickListener {
        Page_Device f1330a;

        public View$OnClickListenerC0350b(Page_Device e0Var) {
            this.f1330a = e0Var;
        }

        @Override
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag(R.id.setId)).intValue();
            if (intValue >= 1000) {
                Vector vector = new Vector(this.f1330a.f1324G.size());
                for (int i = 0; i < this.f1330a.f1324G.size(); i++) {
                    vector.add(new ImageDialog.C0174m(0, this.f1330a.f1324G.get(i).f1334c, this.f1330a.f1324G.get(i).f1334c));
                }
                new ImageDialog(Page_Device.this.f1073g).m916c(vector, intValue - 1000);
                return;
            }
            Document l = this.f1330a.f1077k.m283l(6).m283l(intValue);
            Page_Device e0Var = this.f1330a;
            e0Var.f1074h.m715k(new Page_Device(e0Var.f1073g, e0Var.f1322E, l.m281n(0)));
        }
    }

    class C0351c {
        public ImageView f1332a;
        public String f1333b;
        public String f1334c;

        public C0351c(Page_Device e0Var, Object obj, Object obj2) {
            this.f1333b = (String) obj;
            this.f1334c = (String) obj2;
        }
    }

    class C0352d extends C0348e {
        Page_Device f1335k;

        C0352d(Page_Device e0Var, int i, String str) {
            super(DocumentManager.m271L().f2776a, i, str, false);
            this.f1335k = e0Var;
            this.f2799f = "Изменение статуса устройства";
        }

        @Override
        public void mo96i(int i, Document uVar) {
            if (Page_Device.this.f1075i || !this.f1335k.m805w()) {
                return;
            }
            if (i == 0) {
                if (this.f1319h == 1) {
                    this.f1335k.f1077k.m280o(9, 1);
                    Toast.makeText(this.f1335k.f1073g, "Устройство добавлено", 0).show();
                } else {
                    this.f1335k.f1077k.m280o(9, 0);
                    Toast.makeText(this.f1335k.f1073g, "Устройство удалено", 0).show();
                }
                Page_Device.this.mo107z();
            } else if (i == 4) {
                Toast.makeText(this.f1335k.f1073g, "Нельзя добавить более 5 устройств", 0).show();
            } else {
                Toast.makeText(this.f1335k.f1073g, "Действие завершилось ошибкой", 0).show();
            }
        }
    }

    Page_Device(MainActivity mainActivity, String str, String str2) {
        super(mainActivity, 25956, new Document(r1));
        String str3;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str4 = "";
        if (TextUtils.isEmpty(str2)) {
            str3 = str4;
        } else {
            str3 = ":" + str2;
        }
        sb.append(str3);
        objArr[0] = sb.toString();
        this.f1322E = str;
        this.f1323F = TextUtils.isEmpty(str2) ? null : str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f1322E);
        if (this.f1323F == null) {
            str4 = ":" + str2;
        }
        sb2.append(str4);
        this.f1088v = sb2.toString();
        this.f1327J = new View$OnClickListenerC0350b(this);
        this.f2799f = "Загрузка устройства " + str;
    }

    public static Page_Device m742d0(MainActivity mainActivity, String str) {
        int indexOf = str.indexOf(":");
        String str2 = null;
        if (indexOf >= 0) {
            String substring = str.substring(0, indexOf);
            int i = indexOf + 1;
            if (i < str.length()) {
                str2 = str.substring(i);
            }
            str = substring;
        }
        return new Page_Device(mainActivity, str, str2);
    }

    @Override
    public void mo57F() {
        this.f1074h.f1451i.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.f1073g.f731b * 42.0f);
        super.mo57F();
    }

    @Override
    protected boolean mo56H() {
        if (this.f1075i) {
            return false;
        }
        Document uVar = this.f1077k;
        uVar.m279p(1, Util.C0427h.m641c(uVar.m281n(1)));
        Document uVar2 = this.f1077k;
        String c = Util.C0427h.m641c(uVar2.m281n(4));
        this.f1088v = c;
        uVar2.m279p(4, c);
        Document uVar3 = this.f1077k;
        uVar3.m279p(3, Util.C0427h.m641c(uVar3.m281n(3)));
        Document uVar4 = this.f1077k;
        String c2 = Util.C0427h.m641c(uVar4.m281n(5));
        uVar4.m279p(5, c2);
        if (!TextUtils.isEmpty(c2)) {
            this.f1088v += " " + c2;
        }
        Document l = this.f1077k.m283l(6);
        if (l != null && l.m291d() > 0) {
            for (int i = 0; i < l.m291d(); i++) {
                Document l2 = l.m283l(i);
                l2.m279p(1, Util.C0427h.m641c(l2.m281n(1)));
            }
        }
        Document l3 = this.f1077k.m283l(8);
        this.f1326I = l3;
        if (l3 != null && l3.m291d() > 0) {
            for (int i2 = 0; i2 < this.f1326I.m291d(); i2++) {
                Document l4 = this.f1326I.m283l(i2);
                String c3 = Util.C0427h.m641c(l4.m281n(2));
                l4.m279p(2, c3);
                if (l4.m282m(0).intValue() != 0) {
                    String str = c3 + "   " + Util.C0427h.m641c(l4.m281n(4));
                    SpannableString spannableString = new SpannableString(str);
                    spannableString.setSpan(new StyleSpan(1), c3.length() + 3, str.length(), 33);
                    l4.m287h(4);
                    l4.m293b(spannableString);
                }
            }
        }
        Document l5 = this.f1077k.m283l(7);
        if (l5 != null && l5.m291d() > 0) {
            this.f1324G = new Vector(l5.m291d());
            C0351c cVar = null;
            for (int i3 = 0; i3 < l5.m291d(); i3++) {
                Document l6 = l5.m283l(i3);
                C0351c cVar2 = new C0351c(this, l6.m281n(1), l6.m281n(2));
                this.f1324G.add(cVar2);
                if (cVar == null || l6.m282m(0).intValue() == 1) {
                    cVar = cVar2;
                }
            }
            this.f1324G.remove(cVar);
            this.f1324G.add(0, cVar);
        }
        return true;
    }

    @Override
    public void mo55K(View view) {
        C0667o1 o1Var = new C0667o1(this.f1073g, new C0349a());
        if (Prefs.f1144E) {
            o1Var.m500a(0, 0, 21, "Обновить");
        }
        if (this.f1077k != null) {
            o1Var.m499b(0, 0, 22, "В закладки", DataDB.m366k(mo50u()));
            o1Var.m500a(0, 0, 1, "Копировать ссылку");
            if (DocumentManager.m257Z()) {
                o1Var.m499b(0, 0, 2, "Моё устройство", this.f1077k.m282m(9).intValue() > 0);
            }
        }
        o1Var.m496e(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.f1074h.f1451i.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!m805w()) {
            return 0;
        }
        Document uVar = this.f1326I;
        if (uVar != null) {
            i = uVar.m291d();
        }
        return i + 1 + 1;
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
        if (i == getCount() - 1) {
            return 3;
        }
        if (this.f1326I.m283l(i - 1).m282m(0).intValue() == 0) {
            return 1;
        }
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 3) {
                RelativeLayout  r1 = new RelativeLayout(this.f1073g);
                View view2 = new View(this.f1073g);
                view2.setBackgroundDrawable(this.f1073g.f736g.m736f(R.drawable.card_sep));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (this.f1073g.f731b * 16.0f));
                layoutParams.topMargin = (int) (this.f1073g.f731b * 16.0f);
                view2.setLayoutParams(layoutParams);
                r1.addView(view2);
            } else if (itemViewType == 0) {
                View r1 = this.f1073g.getLayoutInflater().inflate(R.layout.device_main, viewGroup, false);
                this.f1325H = (LinearLayout) r1.findViewById(R.id.deviceImages);
                int i2 = 0;
                while (true) {
                    List<C0351c> list = this.f1324G;
                    if (list == null || i2 >= list.size()) {
                        break;
                    }
                    ImageView imageView = new ImageView(this.f1073g);
                    imageView.setTag(R.id.setId, Integer.valueOf(i2 + 1000));
                    this.f1325H.addView(imageView);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                    layoutParams2.width = -2;
                    float f = this.f1073g.f731b;
                    layoutParams2.height = (int) (200.0f * f);
                    layoutParams2.rightMargin = (int) (f * 16.0f);
                    this.f1324G.get(i2).f1332a = imageView;
                    PicoImgRequest l = PicoImg.m1013l(this.f1073g, this.f1324G.get(i2).f1333b);
                    l.m993r(imageView);
                    l.m1001j();
                    View$OnClickListenerC0350b bVar = this.f1327J;
                    if (bVar != null) {
                        imageView.setOnClickListener(bVar);
                    }
                    i2++;
                }
                ((TextView) r1.findViewById(R.id.deviceTitle)).setText(this.f1077k.m281n(3) + " " + this.f1077k.m281n(4) + " " + this.f1077k.m281n(5));
                Document l2 = this.f1077k.m283l(6);
                if (l2 != null && l2.m291d() > 0) {
                    r1.findViewById(R.id.deviceEditionsScroll).setVisibility(0);
                    LinearLayout linearLayout = (LinearLayout) r1.findViewById(R.id.deviceEditions);
                    for (int i3 = 0; i3 < l2.m291d(); i3++) {
                        Document l3 = l2.m283l(i3);
                        TextView textView = new TextView(this.f1073g);
                        textView.setText(l3.m281n(1));
                        textView.setTag(R.id.setId, Integer.valueOf(i3));
                        textView.setSingleLine(true);
                        textView.setTextSize(16.0f);
                        textView.setTextColor(Skin.C0353a.f1386h0);
                        textView.setGravity(16);
                        textView.setPadding(0, 0, (int) (this.f1073g.f731b * 8.0f), 0);
                        linearLayout.addView(textView);
                        ((LinearLayout.LayoutParams) textView.getLayoutParams()).height = -1;
                        View$OnClickListenerC0350b bVar2 = this.f1327J;
                        if (bVar2 != null) {
                            textView.setOnClickListener(bVar2);
                        }
                    }
                }
            } else if (itemViewType == 1) {
                r1 = this.f1073g.getLayoutInflater().inflate(R.layout.device_field_title, viewGroup, false);
            } else if (itemViewType == 2) {
                r1 = this.f1073g.getLayoutInflater().inflate(R.layout.device_field, viewGroup, false);
            }
            if (itemViewType != 1) {
                ((TextView) r1.findViewById(R.id.title)).setText(this.f1326I.m283l(i - 1).m281n(2));
            } else if (itemViewType == 2) {
                ((TextView) r1.findViewById(R.id.title)).setText((SpannableString) this.f1326I.m283l(i - 1).m289f(4));
            }
            return r1;
        }
        r1 = view;
        if (itemViewType != 1) {
        }
        return r1;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public void mo52r() {
        super.mo52r();
        List<C0351c> list = this.f1324G;
        if (list != null) {
            list.clear();
        }
        this.f1324G = null;
        this.f1325H = null;
        this.f1326I = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        if (!m805w()) {
            return null;
        }
        return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, "devdb", "Устройства", 0, false, false), new Breadcrumb.C0725a(2, "devdb/" + this.f1077k.m281n(0) + "/" + this.f1077k.m281n(2), this.f1077k.m281n(3), 0, false, false), new Breadcrumb.C0725a(3, mo50u(), this.f1088v, 0, true, true)};
    }

    @Override
    public String mo50u() {
        String str;
        if (this.f1328K == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("devdb/");
            sb.append(this.f1322E);
            if (this.f1323F != null) {
                str = ":" + this.f1323F;
            } else {
                str = "";
            }
            sb.append(str);
            this.f1328K = sb.toString();
        }
        return this.f1328K;
    }
}
