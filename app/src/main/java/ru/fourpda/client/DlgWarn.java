package ru.fourpda.client;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DlgWarn extends CustomDialog {
    private TextView f2616A;
    private Widgets$CheckboxTextView f2617B;
    private Widgets$CheckboxTextView f2618C;
    private EditText f2619D;
    private EditText f2620E;
    private BBEditor f2621F;
    private BBEditor f2622G;
    MainActivity f2623H;
    private boolean f2624I;
    private C0787i f2625J;
    private C0787i.C0788a f2626K;
    private int f2627L;
    private int f2628M;
    private View.OnClickListener f2629N;
    private int f2630j;
    private int f2631k;
    private int f2632l;
    private int f2633m;
    private int f2634n;
    private C0787i[] f2635o;
    private TextView f2636p;
    private LinearLayout f2637q;
    private TextView f2638r;
    private LinearLayout f2639s;
    private TextView f2640t;
    private TextView f2641u;
    private TextView f2642v;
    private EditText f2643w;
    private TextView f2644x;
    private EditText f2645y;
    private TextView f2646z;

    class View$OnClickListenerC0773a implements View.OnClickListener {
        final MainActivity f2647a;

        class C0774a implements OptionPoupupMenuView.IClickListener {
            C0774a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DlgWarn tVar = DlgWarn.this;
                tVar.m319B(tVar.f2635o[i3]);
            }
        }

        View$OnClickListenerC0773a(MainActivity mainActivity) {
            this.f2647a = mainActivity;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.f2647a, new C0774a());
            for (int i = 0; i < DlgWarn.this.f2635o.length; i++) {
                o1Var.addMenuItem(0, 0, i, DlgWarn.this.f2635o[i].f2678b);
            }
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0775b implements View.OnClickListener {
        final MainActivity f2650a;

        class C0776a implements OptionPoupupMenuView.IClickListener {
            C0776a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DlgWarn tVar = DlgWarn.this;
                tVar.m318C(tVar.f2625J.f2680d[i3]);
            }
        }

        View$OnClickListenerC0775b(MainActivity mainActivity) {
            this.f2650a = mainActivity;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.f2650a, new C0776a());
            for (int i = 0; i < DlgWarn.this.f2625J.f2680d.length; i++) {
                o1Var.addMenuItem(0, 0, i, DlgWarn.this.f2625J.f2680d[i].f2682b);
            }
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0777c implements View.OnClickListener {
        final MainActivity f2653a;

        class C0778a implements OptionPoupupMenuView.IClickListener {
            C0778a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DlgWarn.this.m316E(i3 > 0 ? "дней" : "часов");
            }
        }

        View$OnClickListenerC0777c(MainActivity mainActivity) {
            this.f2653a = mainActivity;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.f2653a, new C0778a());
            o1Var.addMenuItem(0, 0, 0, "часов");
            o1Var.addMenuItem(0, 0, 1, "дней");
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0779d implements View.OnClickListener {
        final MainActivity f2656a;

        class C0780a implements OptionPoupupMenuView.IClickListener {
            C0780a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DlgWarn.this.m315F(i3 > 0 ? "дней" : "часов");
            }
        }

        View$OnClickListenerC0779d(MainActivity mainActivity) {
            this.f2656a = mainActivity;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.f2656a, new C0780a());
            o1Var.addMenuItem(0, 0, 0, "часов");
            o1Var.addMenuItem(0, 0, 1, "дней");
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0781e implements View.OnClickListener {
        final MainActivity f2659a;

        class C0782a implements OptionPoupupMenuView.IClickListener {
            C0782a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                DlgWarn.this.m317D(2 == i3 ? "полный запрет" : 1 == i3 ? "последний шанс" : "нет");
            }
        }

        View$OnClickListenerC0781e(MainActivity mainActivity) {
            this.f2659a = mainActivity;
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.f2659a, new C0782a());
            o1Var.addMenuItem(0, 0, 0, "нет");
            o1Var.addMenuItem(0, 0, 1, "последний шанс");
            if (DlgWarn.this.f2624I) {
                o1Var.addMenuItem(0, 0, 2, "полный запрет");
            }
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0783f implements View.OnClickListener {
        View$OnClickListenerC0783f() {
        }

        @Override
        public void onClick(View view) {
            DlgWarn tVar = DlgWarn.this;
            tVar.m316E(tVar.f2644x.getText().toString());
            DlgWarn tVar2 = DlgWarn.this;
            tVar2.m315F(tVar2.f2646z.getText().toString());
            int i = (DlgWarn.this.f2626K.f2685e & 66) | 1;
            if (DlgWarn.this.f2617B.getChecked()) {
                i |= 8;
            }
            if (DlgWarn.this.f2618C.getChecked()) {
                i |= 4;
            }
            String charSequence = DlgWarn.this.f2616A.getText().toString();
            if (charSequence.equals("полный запрет") && DlgWarn.this.f2624I) {
                i |= 48;
            } else if (charSequence.equals("последний шанс")) {
                i |= 16;
            }
            DlgWarn tVar3 = DlgWarn.this;
            DocumentManager.getResultRequest(new WarnOutRequest(tVar3.f2630j, DlgWarn.this.f2631k, DlgWarn.this.f2625J.f2677a, DlgWarn.this.f2626K.f2681a, i, DlgWarn.this.f2627L, DlgWarn.this.f2628M, DlgWarn.this.f2621F.m524a(), DlgWarn.this.f2622G.m524a()));
        }
    }

    private class WarnOutRequest extends DocumentManager.IGenerateRequest {
        int f2663g;
        int f2664h;
        String f2665i;
        String f2666j;
        int f2667k;
        int f2668l;
        int f2669m;
        String f2670n;
        String f2671o;

        class View$OnClickListenerC0785a implements View.OnClickListener {
            View$OnClickListenerC0785a() {
            }

            @Override
            public void onClick(View view) {
                WarnOutRequest gVar = WarnOutRequest.this;
                gVar.f2667k |= 512;
                DocumentManager.getResultRequest(gVar);
            }
        }

        WarnOutRequest(int i, int i2, String str, String str2, int i3, int i4, int i5, String str3, String str4) {
            super(30573);
            this.f2663g = i;
            this.f2664h = i2;
            this.f2665i = str;
            this.f2666j = str2;
            this.f2667k = i3;
            this.f2668l = i4;
            this.f2669m = i5;
            this.f2670n = str3;
            this.f2671o = str4;
            this.statusMessage = "Вынесение наказания";
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (status == 0) {
                DlgWarn.this.dialog.dismiss();
                Toast.makeText(DlgWarn.this.f2623H, "Наказание вынесено", 1).show();
                DlgWarn.this.f2623H.mainLayout.tab.page.tabReload();
            } else if (status == 5) {
                Toast.makeText(DlgWarn.this.f2623H, "Не указана причина", 1).show();
            } else if (status == 6) {
                Toast.makeText(DlgWarn.this.f2623H, "Не указано сообщение", 1).show();
            } else if (status == 7) {
                Toast.makeText(DlgWarn.this.f2623H, "УП уже максимален", 1).show();
            } else if (status == 8) {
                DlgSimple q1Var = new DlgSimple(DlgWarn.this.f2623H, "УП этого пользователя уже повышали за последние 5 часов. Подтвердите повышение.", false, null, null);
                q1Var.promtMessage.setTextSize(2, 16.0f);
                q1Var.editText.setVisibility(8);
                q1Var.m620f(new View$OnClickListenerC0785a(), true);
                q1Var.show(true, true, true);
            } else {
                Toast.makeText(DlgWarn.this.f2623H, "Нет доступа", 1).show();
            }
        }

        @Override
        public Document generate() {
            return new Document(this.f2663g, 1, this.f2664h, this.f2670n, this.f2665i, this.f2666j, Integer.valueOf(this.f2667k), this.f2671o, Integer.valueOf(this.f2668l), Integer.valueOf(this.f2669m));
        }
    }

    public static class LoadWarnTemplates extends DocumentManager.IGenerateRequest {
        MainActivity f2674g;
        int f2675h;
        int f2676i;

        LoadWarnTemplates(MainActivity mainActivity, int i, int i2) {
            super(30573);
            this.f2674g = mainActivity;
            this.f2675h = i;
            this.f2676i = i2;
            this.statusMessage = "Загрузка шаблонов наказания";
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (status == 0) {
                Document l = uVar.getDocument(3);
                int d = l.count();
                C0787i[] iVarArr = new C0787i[d];
                for (int i2 = 0; i2 < d; i2++) {
                    Document l2 = l.getDocument(i2);
                    iVarArr[i2] = new C0787i(null);
                    iVarArr[i2].f2677a = l2.getString(0);
                    iVarArr[i2].f2678b = l2.getString(1);
                    iVarArr[i2].f2679c = l2.getInt(2).intValue();
                    Document l3 = l2.getDocument(3);
                    iVarArr[i2].f2680d = new C0787i.C0788a[l3.count()];
                    for (int i3 = 0; i3 < iVarArr[i2].f2680d.length; i3++) {
                        Document l4 = l3.getDocument(i3);
                        iVarArr[i2].f2680d[i3] = new C0787i.C0788a(null);
                        iVarArr[i2].f2680d[i3].f2681a = l4.getString(0);
                        iVarArr[i2].f2680d[i3].f2682b = l4.getString(1);
                        iVarArr[i2].f2680d[i3].f2683c = l4.getString(2);
                        iVarArr[i2].f2680d[i3].f2684d = l4.getString(3);
                        iVarArr[i2].f2680d[i3].f2685e = l4.getInt(4).intValue();
                        if ((iVarArr[i2].f2679c & 1) != 0) {
                            iVarArr[i2].f2680d[i3].f2686f = l4.getInt(5).intValue();
                            iVarArr[i2].f2680d[i3].f2687g = l4.getInt(6).intValue();
                        } else {
                            iVarArr[i2].f2680d[i3].f2686f = 0;
                            iVarArr[i2].f2680d[i3].f2687g = 0;
                        }
                    }
                }
                new DlgWarn(this.f2674g, this.f2675h, this.f2676i, uVar.getInt(2).intValue(), uVar.getInt(0).intValue(), uVar.getInt(1).intValue(), iVarArr, null).show(true, true, true);
                return;
            }
            Toast.makeText(this.f2674g, "Нет доступа", 1).show();
        }

        @Override
        public Document generate() {
            return new Document(Integer.valueOf(this.f2675h), 0, Integer.valueOf(this.f2676i));
        }
    }

    public static class C0787i {
        String f2677a;
        String f2678b;
        int f2679c;
        C0788a[] f2680d;

        public static class C0788a {
            String f2681a;
            String f2682b;
            String f2683c;
            String f2684d;
            int f2685e;
            int f2686f;
            int f2687g;

            private C0788a() {
            }

            C0788a(View$OnClickListenerC0773a aVar) {
                this();
            }
        }

        private C0787i() {
        }

        C0787i(View$OnClickListenerC0773a aVar) {
            this();
        }
    }

    DlgWarn(MainActivity mainActivity, int i, int i2, int i3, int i4, int i5, C0787i[] iVarArr, View$OnClickListenerC0773a aVar) {
        this(mainActivity, i, i2, i3, i4, i5, iVarArr);
    }

    public static void m320A(MainActivity mainActivity, int i, int i2) {
        DocumentManager.getResultRequest(new LoadWarnTemplates(mainActivity, i, i2));
    }

    public void m319B(C0787i iVar) {
        this.f2625J = iVar;
        this.f2641u.setText(iVar.f2678b);
        int i = 0;
        m318C(this.f2625J.f2680d[0]);
        if ((this.f2625J.f2679c & 1) == 0) {
            i = 8;
        }
        this.f2636p.setVisibility(i);
        this.f2637q.setVisibility(i);
        this.f2638r.setVisibility(i);
        this.f2639s.setVisibility(i);
        this.f2640t.setVisibility(i);
        this.f2616A.setVisibility(i);
    }

    public void m318C(C0787i.C0788a aVar) {
        this.f2626K = aVar;
        this.f2642v.setText(aVar.f2682b);
        C0787i.C0788a aVar2 = this.f2626K;
        boolean z = false;
        int i = aVar2.f2686f + ((aVar2.f2685e & 128) != 0 ? this.f2633m : 0);
        this.f2627L = i;
        EditText editText = this.f2643w;
        if (i > 72) {
            i /= 24;
        }
        editText.setText(Integer.valueOf(i).toString());
        String str = "дней";
        this.f2644x.setText(this.f2627L > 72 ? str : "часов");
        C0787i.C0788a aVar3 = this.f2626K;
        int i2 = aVar3.f2687g + ((aVar3.f2685e & 256) != 0 ? this.f2634n : 0);
        this.f2628M = i2;
        EditText editText2 = this.f2645y;
        if (i2 > 72) {
            i2 /= 24;
        }
        editText2.setText(Integer.valueOf(i2).toString());
        TextView textView = this.f2646z;
        if (this.f2628M <= 72) {
            str = "часов";
        }
        textView.setText(str);
        int i3 = this.f2632l;
        int i4 = this.f2626K.f2685e;
        if (((i3 | i4) & 32) != 0 && this.f2624I) {
            this.f2616A.setText("полный запрет");
        } else if (((i3 | i4) & 16) != 0) {
            this.f2616A.setText("последний шанс");
        } else {
            this.f2616A.setText("нет");
        }
        this.f2617B.setChecked(((this.f2632l | this.f2626K.f2685e) & 8) != 0);
        Widgets$CheckboxTextView widgets$CheckboxTextView = this.f2618C;
        if ((this.f2626K.f2685e & 4) != 0) {
            z = true;
        }
        widgets$CheckboxTextView.setChecked(z);
        C0787i.C0788a aVar4 = this.f2626K;
        if ((aVar4.f2685e & 1024) == 0) {
            this.f2619D.setText(aVar4.f2683c);
            this.f2620E.setText(this.f2626K.f2684d);
        }
    }

    public void m317D(String str) {
        this.f2616A.setText(str);
    }

    public void m316E(String str) {
        try {
            this.f2627L = Integer.parseInt(this.f2643w.getText().toString());
            if (this.f2644x.getText().toString().equals("дней")) {
                this.f2627L *= 24;
            }
        } catch (Exception unused) {
            C0787i.C0788a aVar = this.f2626K;
            this.f2627L = aVar.f2686f + ((aVar.f2685e & 128) != 0 ? this.f2633m : 0);
        }
        this.f2644x.setText(str);
        this.f2643w.setText(Integer.valueOf(str.equals("дней") ? this.f2627L / 24 : this.f2627L).toString());
    }

    public void m315F(String str) {
        try {
            this.f2628M = Integer.parseInt(this.f2645y.getText().toString());
            if (this.f2646z.getText().toString().equals("дней")) {
                this.f2628M *= 24;
            }
        } catch (Exception unused) {
            C0787i.C0788a aVar = this.f2626K;
            this.f2628M = aVar.f2687g + ((aVar.f2685e & 256) != 0 ? this.f2634n : 0);
        }
        this.f2646z.setText(str);
        this.f2645y.setText(Integer.valueOf(str.equals("дней") ? this.f2628M / 24 : this.f2628M).toString());
    }

    private DlgWarn(MainActivity mainActivity, int i, int i2, int i3, int i4, int i5, C0787i[] iVarArr) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_warn, (ViewGroup) null), null, null);
        int i6;
        this.f2629N = new View$OnClickListenerC0783f();
        this.f2623H = mainActivity;
        this.f2630j = i;
        this.f2631k = i2;
        this.f2632l = i3;
        this.f2633m = i4;
        this.f2634n = i5;
        this.f2635o = iVarArr;
        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
        this.f2624I = L != null && ((i6 = L.memberGroup) == 4 || i6 == 10 || i6 == 16);
        this.f2636p = (TextView) this.rootView.findViewById(R.id.dlg_warn_premod_label);
        this.f2637q = (LinearLayout) this.rootView.findViewById(R.id.dlg_warn_premod);
        this.f2638r = (TextView) this.rootView.findViewById(R.id.dlg_warn_ro_label);
        this.f2639s = (LinearLayout) this.rootView.findViewById(R.id.dlg_warn_ro);
        this.f2640t = (TextView) this.rootView.findViewById(R.id.dlg_warn_ban_label);
        this.f2643w = (EditText) this.rootView.findViewById(R.id.dlg_warn_premod_time);
        this.f2645y = (EditText) this.rootView.findViewById(R.id.dlg_warn_ro_time);
        this.f2641u = (TextView) this.rootView.findViewById(R.id.dlg_warn_level);
        this.f2642v = (TextView) this.rootView.findViewById(R.id.dlg_warn_template);
        this.f2644x = (TextView) this.rootView.findViewById(R.id.dlg_warn_premod_unit);
        this.f2646z = (TextView) this.rootView.findViewById(R.id.dlg_warn_ro_unit);
        this.f2616A = (TextView) this.rootView.findViewById(R.id.dlg_warn_ban);
        this.f2617B = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_warn_premod_always);
        Widgets$CheckboxTextView widgets$CheckboxTextView = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_warn_delete);
        this.f2618C = widgets$CheckboxTextView;
        if (i2 <= 0) {
            widgets$CheckboxTextView.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) this.rootView.findViewById(R.id.dlg_warn_reason);
        EditText editText = (EditText) linearLayout.findViewById(R.id.editor);
        this.f2619D = editText;
        editText.setMinLines(4);
        this.f2621F = new BBEditor(mainActivity, linearLayout, this.f2619D, true);
        LinearLayout linearLayout2 = (LinearLayout) this.rootView.findViewById(R.id.dlg_warn_message);
        EditText editText2 = (EditText) linearLayout2.findViewById(R.id.editor);
        this.f2620E = editText2;
        editText2.setMinLines(4);
        this.f2622G = new BBEditor(mainActivity, linearLayout2, this.f2620E, true);
        this.f2641u.setOnClickListener(new View$OnClickListenerC0773a(mainActivity));
        this.f2642v.setOnClickListener(new View$OnClickListenerC0775b(mainActivity));
        this.f2644x.setOnClickListener(new View$OnClickListenerC0777c(mainActivity));
        this.f2646z.setOnClickListener(new View$OnClickListenerC0779d(mainActivity));
        this.f2616A.setOnClickListener(new View$OnClickListenerC0781e(mainActivity));
        m620f(this.f2629N, false);
        m319B(this.f2635o[0]);
    }
}
