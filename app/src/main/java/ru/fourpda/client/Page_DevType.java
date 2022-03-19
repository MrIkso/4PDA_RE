package ru.fourpda.client;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;

public class Page_DevType extends Page {
    C0336f f1274E = new C0336f(this, "phones", "Телефоны");
    C0336f f1275F = new C0336f(this, "ebook", "Эл.книги");
    C0336f f1276G = new C0336f(this, "pad", "Планшеты");
    C0336f f1277H = new C0336f(this, "smartwatch", "Смарт часы");
    Object f1278I;
    boolean f1279J;

    class View$OnClickListenerC0331a implements View.OnClickListener {
        View$OnClickListenerC0331a() {
        }

        @Override
        public void onClick(View view) {
            C0336f fVar = (C0336f) view.getTag();
            if (fVar.f1287a != 0) {
                fVar.f1287a = 0;
                Page_DevType.this.tabLoaded(true);
            } else if (fVar.f1288b.size() > 0) {
                fVar.f1287a = 2;
                Page_DevType.this.tabLoaded(true);
            } else if (!fVar.f1292f) {
                Page_DevType d0Var = Page_DevType.this;
                DocumentManager.getResultRequest(new C0335e(fVar, d0Var));
            }
        }
    }

    class View$OnClickListenerC0332b implements View.OnClickListener {
        View$OnClickListenerC0332b() {
        }

        @Override
        public void onClick(View view) {
            C0336f fVar = (C0336f) view.getTag();
            if (fVar.f1287a == 2) {
                fVar.f1287a = 1;
            } else {
                fVar.f1287a = 2;
            }
            Page_DevType.this.tabLoaded(true);
        }
    }

    class View$OnClickListenerC0333c implements View.OnClickListener {
        View$OnClickListenerC0333c() {
        }

        @Override
        public void onClick(View view) {
            Page_DevType d0Var = Page_DevType.this;
            d0Var.tab.addPage(Page_Vendor.m53d0(d0Var.mainActivity, (String) view.getTag()));
        }
    }

    class C0334d implements OptionPoupupMenuView.IClickListener {
        C0334d() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_DevType.this.tabReload();
            } else if (i3 == 22) {
                Page_DevType d0Var = Page_DevType.this;
                DataDB.m365l(d0Var.title, d0Var.getLink());
            } else if (i3 == 1) {
                MainActivity mainActivity = Page_DevType.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/" + Page_DevType.this.getLink(), "Ссылка скопирована");
            }
        }
    }

    class C0335e extends API.DeviceTypeRequest {
        C0336f f1284h;
        Page_DevType f1285i;

        C0335e(C0336f fVar, Page_DevType d0Var) {
            super(fVar.f1290d);
            this.f1284h = fVar;
            fVar.f1292f = true;
            this.f1285i = d0Var;
            this.statusMessage = "загрузка " + fVar.f1291e;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (!Page_DevType.this.isLoading) {
                if (status == 0) {
                    this.f1284h.f1287a = 2;
                    this.f1285i.tabLoaded(true);
                } else {
                    MainActivity mainActivity = this.f1285i.mainActivity;
                    Toast.makeText(mainActivity, "Ошибка загрузки " + this.f1284h.f1291e, 0).show();
                }
                this.f1284h.f1292f = false;
            }
        }

        @Override
        public void getResult(int status, Document uVar) {
            Document l;
            if (status != 0 || (l = uVar.getDocument(2)) == null || l.count() <= 0) {
                this.f1284h.m752a();
                return;
            }
            Vector vector = new Vector(l.count());
            Vector vector2 = new Vector(l.count());
            Document uVar2 = null;
            Document uVar3 = null;
            for (int i2 = 0; i2 < l.count(); i2++) {
                uVar3 = l.getDocument(i2);
                uVar3.addString(1, Util.C0427h.UnEscapeString(uVar3.getString(1)));
                uVar3.append(this.f1284h.f1290d);
                vector.add(uVar3);
                if (uVar3.getInt(3).intValue() > 2) {
                    vector2.add(uVar3);
                    uVar2 = uVar3;
                }
            }
            if (!(uVar2 == null || uVar3 == uVar2)) {
                uVar2.append(0);
            }
            uVar3.append(1);
            C0336f fVar = this.f1284h;
            fVar.f1289c = vector2;
            fVar.f1288b = vector;
        }
    }

    public class C0336f {
        public int f1287a = 0;
        public List<Document> f1288b = new Vector(100);
        public List<Document> f1289c = new Vector(100);
        public String f1290d;
        public String f1291e;
        public boolean f1292f;

        public C0336f(Page_DevType d0Var, String str, String str2) {
            this.f1290d = str;
            this.f1291e = str2;
        }

        public void m752a() {
            this.f1288b.clear();
            this.f1289c.clear();
            this.f1287a = 0;
        }

        public Document m751b(int i) {
            return (this.f1287a == 1 ? this.f1288b : this.f1289c).get(i);
        }

        public int m750c() {
            int i = this.f1287a;
            if (i == 0) {
                return 0;
            }
            return (i == 1 ? this.f1288b : this.f1289c).size();
        }
    }

    public Page_DevType(MainActivity mainActivity, String str, boolean z) {
        super(mainActivity);
        this.title = "Устройства";
        this.statusMessage = "Загрузка устройств";
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0334d());
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

    int m753d0(int i, boolean z) {
        this.f1278I = null;
        boolean z2 = false;
        this.f1279J = false;
        if (i == 0) {
            if (z) {
                this.f1278I = this.f1274E;
            }
            return 0;
        }
        int c = this.f1274E.m750c() + 1;
        if (i < c) {
            if (z) {
                this.f1278I = this.f1274E.m751b(i - 1);
                if (this.f1274E.f1287a == 1) {
                    z2 = true;
                }
                this.f1279J = z2;
            }
            return 1;
        } else if (i == c) {
            if (z) {
                this.f1278I = this.f1275F;
            }
            return 0;
        } else {
            int i2 = c + 1;
            int c2 = this.f1275F.m750c() + i2;
            if (i < c2) {
                if (z) {
                    this.f1278I = this.f1275F.m751b(i - i2);
                    if (this.f1275F.f1287a == 1) {
                        z2 = true;
                    }
                    this.f1279J = z2;
                }
                return 1;
            } else if (i == c2) {
                if (z) {
                    this.f1278I = this.f1276G;
                }
                return 0;
            } else {
                int i3 = c2 + 1;
                int c3 = this.f1276G.m750c() + i3;
                if (i < c3) {
                    if (z) {
                        this.f1278I = this.f1276G.m751b(i - i3);
                        if (this.f1276G.f1287a == 1) {
                            z2 = true;
                        }
                        this.f1279J = z2;
                    }
                    return 1;
                } else if (i == c3) {
                    if (z) {
                        this.f1278I = this.f1277H;
                    }
                    return 0;
                } else {
                    int i4 = c3 + 1;
                    this.f1277H.m750c();
                    if (z) {
                        this.f1278I = this.f1277H.m751b(i - i4);
                        if (this.f1277H.f1287a == 1) {
                            z2 = true;
                        }
                        this.f1279J = z2;
                    }
                    return 1;
                }
            }
        }
    }

    @Override
    public int getCount() {
        if (!isUnsucces()) {
            return 0;
        }
        return this.f1274E.m750c() + 4 + this.f1275F.m750c() + this.f1276G.m750c() + this.f1277H.m750c();
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        int d0 = m753d0(i, false);
        this.f1278I = null;
        return d0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int d0 = m753d0(i, true);
        Object obj = this.f1278I;
        this.f1278I = null;
        int i2 = 0;
        if (view == null) {
            if (d0 == 0) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.devtype_main, viewGroup, false);
                view.findViewById(R.id.devtypeTitle).setOnClickListener(new View$OnClickListenerC0331a());
                view.findViewById(R.id.devtypeButton).setOnClickListener(new View$OnClickListenerC0332b());
            } else if (d0 == 1) {
                view = this.mainActivity.getLayoutInflater().inflate(R.layout.devtype_vendor, viewGroup, false);
                view.setOnClickListener(new View$OnClickListenerC0333c());
            }
        }
        if (d0 == 0) {
            C0336f fVar = (C0336f) obj;
            TextView textView = (TextView) view.findViewById(R.id.devtypeTitle);
            textView.setText(fVar.f1291e);
            textView.setTag(fVar);
            textView.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable(fVar.f1287a == 0 ? R.drawable.ic_expand_close : R.drawable.ic_expand_open), (Drawable) null, (Drawable) null, (Drawable) null);
            TextView textView2 = (TextView) view.findViewById(R.id.devtypeActual);
            textView2.setText("Актуальные: " + fVar.f1289c.size());
            textView2.setVisibility(fVar.f1287a == 0 ? 8 : 0);
            TextView textView3 = (TextView) view.findViewById(R.id.devtypeAll);
            textView3.setText("Всего: " + fVar.f1288b.size());
            textView3.setVisibility(fVar.f1287a == 0 ? 8 : 0);
            TextView textView4 = (TextView) view.findViewById(R.id.devtypeButton);
            textView4.setText(fVar.f1287a == 1 ? "ПОКАЗАТЬ АКТУАЛЬНЫЕ" : "ПОКАЗАТЬ ВСЕ");
            textView4.setTag(fVar);
            textView4.setVisibility(fVar.f1287a == 0 ? 8 : 0);
            View findViewById = view.findViewById(R.id.sepBottom);
            if (fVar.f1287a == 0) {
                i2 = 8;
            }
            findViewById.setVisibility(i2);
        } else if (d0 == 1) {
            Document uVar = (Document) obj;
            TextView textView5 = (TextView) view.findViewById(R.id.vendorName);
            if (this.f1279J) {
                textView5.setText(uVar.getString(1) + " (" + uVar.getInt(2) + ")");
            } else {
                textView5.setText(uVar.getString(1) + " (" + uVar.getInt(3) + ")");
            }
            view.setTag(uVar.getString(4) + ":" + uVar.getString(0));
            if (uVar.count() > 5) {
                int intValue = uVar.getInt(5).intValue();
                boolean z = this.f1279J;
                if ((z && intValue == 1) || !z) {
                    textView5.setBackgroundResource(0);
                    float f = this.mainActivity.f731b;
                    textView5.setPadding(0, (int) (f * 12.0f), (int) (f * 16.0f), (int) (f * 12.0f));
                    view.setPadding(0, 0, 0, (int) (this.mainActivity.f731b * 16.0f));
                }
            } else {
                textView5.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.border_bottom));
                float f2 = this.mainActivity.f731b;
                textView5.setPadding(0, (int) (f2 * 12.0f), (int) (16.0f * f2), (int) (f2 * 12.0f));
                view.setPadding(0, 0, 0, 0);
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
        this.f1274E.m752a();
        this.f1275F.m752a();
        this.f1276G.m752a();
        this.f1277H.m752a();
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        return new Breadcrumb.C0725a[]{new Breadcrumb.C0725a(1, getLink(), this.title, 0, true, true)};
    }

    @Override
    public String getLink() {
        return "devdb/";
    }

    @Override
    public void tabReload() {
        tabLoaded(true);
    }
}
