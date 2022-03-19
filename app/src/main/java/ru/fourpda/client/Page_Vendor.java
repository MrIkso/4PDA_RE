package ru.fourpda.client;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;

public class Page_Vendor extends Page {
    String f3190E;
    String f3191F;
    List<C0955d> f3192G;
    List<C0955d> f3193H;
    boolean f3194I;

    class View$OnClickListenerC0952a implements View.OnClickListener {
        View$OnClickListenerC0952a() {
        }

        @Override
        public void onClick(View view) {
            Page_Vendor y0Var = Page_Vendor.this;
            boolean z = !y0Var.f3194I;
            y0Var.f3194I = z;
            ((TextView) view).setText(z ? "ПОКАЗАТЬ ВСЕ" : "ПОКАЗАТЬ АКТИВНЫЕ");
            Page_Vendor.this.tabLoaded(true);
        }
    }

    class View$OnClickListenerC0953b implements View.OnClickListener {
        View$OnClickListenerC0953b() {
        }

        @Override
        public void onClick(View view) {
            Page_Vendor y0Var = Page_Vendor.this;
           // y0Var.f1074h.m715k(Page_Device.m742d0(y0Var.f1073g, (String) view.getTag()));
        }
    }

    class C0954c implements OptionPoupupMenuView.IClickListener {
        C0954c() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Vendor.this.tabReload();
            } else if (i3 == 22) {
                Page_Vendor y0Var = Page_Vendor.this;
                DataDB.m365l(y0Var.title, y0Var.getLink());
            } else if (i3 == 1) {
                MainActivity mainActivity = Page_Vendor.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/" + Page_Vendor.this.getLink(), "Ссылка скопирована");
            }
        }
    }

    class C0955d {
        public Document f3198a;
        public BBString f3199b;

        C0955d(Page_Vendor y0Var) {
        }
    }

    public Page_Vendor(MainActivity mainActivity, String str, String str2, boolean z) {
        super(mainActivity, 30308, new Document(str, str2));
        this.f3190E = str;
        this.f3191F = str2;
        this.title = str + ":" + str2;
        StringBuilder sb = new StringBuilder();
        sb.append("Загрузка ");
        sb.append(this.title);
        this.statusMessage = sb.toString();
        this.f3194I = !z;
    }

    public static Page_Vendor m53d0(MainActivity mainActivity, String str) {
        int indexOf = str.indexOf(":");
        String str2 = "null";
        if (indexOf >= 0) {
            String substring = str.substring(0, indexOf);
            int i = indexOf + 1;
            if (i < str.length()) {
                str2 = str.substring(i);
            }
            str = substring;
        }
        return new Page_Vendor(mainActivity, str, str2, false);
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoaded() {
        if (this.isLoading) {
            return false;
        }
        Document uVar = this.currentDocument;
        String c = Util.C0427h.UnEscapeString(uVar.getString(1));
        uVar.addString(1, c);
        Document uVar2 = this.currentDocument;
        String c2 = Util.C0427h.UnEscapeString(uVar2.getString(3));
        this.title = c2;
        uVar2.addString(3, c2);
        if (!TextUtils.isEmpty(c)) {
            this.title += " (" + c + ")";
        }
        Document l = this.currentDocument.getDocument(4);
        this.f3193H = null;
        this.f3192G = null;
        if (l != null && l.count() > 0) {
            Vector vector = new Vector(l.count());
            Vector vector2 = new Vector(l.count());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l.count(); i++) {
                C0955d dVar = new C0955d(this);
                Document l2 = l.getDocument(i);
                l2.addString(1, Util.C0427h.UnEscapeString(l2.getString(1)));
                dVar.f3198a = l2;
                vector.add(dVar);
                if (l2.getInt(3).intValue() != 0) {
                    vector2.add(dVar);
                }
                Document l3 = l2.getDocument(4);
                if (l3 != null && l3.count() > 0) {
                    sb.setLength(0);
                    for (int i2 = 0; i2 < l3.count(); i2++) {
                        Document l4 = l3.getDocument(i2);
                        sb.append(l4.getString(2));
                        sb.append("  [b]");
                        sb.append(l4.getString(4));
                        sb.append("[/b]\n");
                    }
                    BBString x = BBString.getBBString(sb.toString(), null);
                    dVar.f3199b = x;
                    if (x != null) {
                        BBString.C0674e eVar = x.f2246z;
                        int i3 = (int) (this.mainActivity.f731b * 16.0f);
                        eVar.f2266j = i3;
                        eVar.f2265i = i3;
                    }
                }
            }
            this.f3193H = vector2;
            this.f3192G = vector;
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0954c());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 1, "Копировать ссылку");
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (!z) {
            this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = 0;
        }
    }

    @Override
    public int getCount() {
        int i = 0;
        if (!isUnsucces()) {
            return 0;
        }
        if (this.f3194I) {
            List<C0955d> list = this.f3193H;
            if (list != null) {
                i = list.size();
            }
            return i + 1;
        }
        List<C0955d> list2 = this.f3192G;
        if (list2 != null) {
            i = list2.size();
        }
        return i + 1;
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
        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 0) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.vendor_main, viewGroup, false);
                ((TextView) view.findViewById(R.id.vendorTitle)).setText(this.currentDocument.getString(3));
                ((TextView) view.findViewById(R.id.vendorActual)).setText("Актуальные: " + this.f3193H.size());
                ((TextView) view.findViewById(R.id.vendorAll)).setText("Всего: " + this.f3192G.size());
                TextView textView = (TextView) view.findViewById(R.id.vendorButton);
                textView.setText(this.f3194I ? "ПОКАЗАТЬ ВСЕ" : "ПОКАЗАТЬ АКТУАЛЬНЫЕ");
                textView.setOnClickListener(new View$OnClickListenerC0952a());
            } else if (itemViewType == 1) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.vendor_device, viewGroup, false);
                view.findViewById(R.id.title).setOnClickListener(new View$OnClickListenerC0953b());
            }
        }
        if (itemViewType == 1) {
            C0955d dVar = (this.f3194I ? this.f3193H : this.f3192G).get(i - 1);
            TextView textView2 = (TextView) view.findViewById(R.id.title);
            textView2.setText(dVar.f3198a.getString(1));
            textView2.setTag(dVar.f3198a.getString(0));
            ((BBDisplay) view.findViewById(R.id.deviceBrief)).setBBString(dVar.f3199b);
            ImageView imageView = (ImageView) view.findViewById(R.id.deviceImage);
            String n = dVar.f3198a.getString(2);
            if (!TextUtils.isEmpty(n)) {
                PicoImgRequest l = PicoImg.loadUrl(this.mainActivity, n);
                l.to(imageView);
                l.sizeToView();
                l.runAsync();
            } else {
                imageView.setImageDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.ic_launcher));
            }
        }
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        List<C0955d> list = this.f3192G;
        if (list != null) {
            list.clear();
        }
        this.f3192G = null;
        List<C0955d> list2 = this.f3193H;
        if (list2 != null) {
            list2.clear();
        }
        this.f3193H = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        if (isUnsucces()) {
            return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, "devdb", "Устройства", 0, false, false), new Breadcrumb.C0725a(2, getLink(), this.title, 0, true, true)};
        }
        return null;
    }

    @Override
    public String getLink() {
        StringBuilder sb = new StringBuilder();
        sb.append("devdb/");
        sb.append(this.f3190E);
        sb.append("/");
        sb.append(this.f3191F);
        sb.append(this.f3194I ? "" : "/all");
        return sb.toString();
    }
}
