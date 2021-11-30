package ru.fourpda.client;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class Breadcrumb extends Dialog implements View.OnClickListener {
    private Tab f2454a;
    private MainActivity f2455b;
    private LinearLayout f2456c;
    private C0725a[] f2457d;
    private int f2458e;
    private long f2459f;

    public static class C0725a {
        private int f2460a;
        private String f2461b;
        private String f2462c;
        int f2463d;
        boolean f2464e;
        boolean f2465f;
        private boolean f2466g;
        private boolean f2467h;
        private int f2468i;

        public C0725a(int i, String str, String str2, int i2, boolean z, boolean z2) {
            this.f2460a = i;
            this.f2461b = str;
            this.f2462c = str2;
            this.f2463d = i2;
            this.f2464e = z;
            this.f2465f = z2;
        }
    }

    public Breadcrumb(MainActivity mainActivity, Tab f1Var) {
        super(mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
        boolean z;
        this.f2455b = mainActivity;
        this.f2454a = f1Var;
        C0725a[] t = f1Var.page.mo51t();
        this.f2457d = t;
        if (t == null) {
            C0725a[] aVarArr = {new C0725a(1, "", "Домашняя", 0, false, false), new C0725a(2, f1Var.page.getLink(), f1Var.page.title, 0, true, true)};
            this.f2457d = aVarArr;
            z = true;
        } else {
            z = false;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            C0725a[] aVarArr2 = this.f2457d;
            if (i >= aVarArr2.length) {
                break;
            }
            if (i > 0) {
                int i4 = i - 1;
                if (aVarArr2[i].f2463d == aVarArr2[i4].f2460a) {
                    this.f2457d[i4].f2467h = true;
                }
            }
            C0725a[] aVarArr3 = this.f2457d;
            if (aVarArr3[i].f2465f) {
                i2 = aVarArr3[i].f2463d;
                i3 = i;
            }
            i++;
        }
        if (i2 != 0) {
            for (int i5 = i3 - 1; i5 >= 0 && i2 != 0; i5--) {
                if (this.f2457d[i5].f2460a == i2) {
                    this.f2457d[i5].f2466g = true;
                    i2 = this.f2457d[i5].f2463d;
                }
            }
        }
        setContentView(R.layout.bc_root);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bc_list);
        this.f2456c = linearLayout;
        m398e(linearLayout.getChildCount() - 1, 0, 0, z);
        m399d(this.f2456c.getChildCount() - 1, 0, 0);
        getWindow().setBackgroundDrawable(this.f2455b.skin.m736f(R.drawable.np_dialog));
        getWindow().setLayout(-1, -2);
        setCanceledOnTouchOutside(true);
    }

    private void m402a(View view, C0725a aVar, boolean z, boolean z2) {
        int i;
        if (z) {
            i = !z2 ? aVar.f2465f ? R.drawable.bc_tree_tsc : R.drawable.bc_tree_t : R.drawable.bc_tree_c;
        } else if (z2) {
            i = aVar.f2465f ? R.drawable.bc_tree_b : R.drawable.bc_tree_bs;
        } else {
            i = aVar.f2465f ? R.drawable.bc_tree_msc : aVar.f2464e ? R.drawable.bc_tree_ms : R.drawable.bc_tree_m;
        }
        ((ImageView) view.findViewById(R.id.bc_line_img)).setImageDrawable(this.f2455b.skin.m736f(i));
        if (!z) {
            view.findViewById(R.id.bc_line_text).setBackgroundDrawable(z2 ? null : this.f2455b.skin.m736f(R.drawable.border_bottom));
        }
    }

    private int m401b(int i, int i2, int i3) {
        DataDB.C0736a[] e = DataDB.m372e(i2);
        if (e != null) {
            if (i2 == 0) {
                this.f2455b.getLayoutInflater().inflate(R.layout.bc_bm_sep, this.f2456c);
                i++;
            } else {
                ((DataDB.C0736a) this.f2456c.getChildAt(i).getTag()).f2499d |= 16;
                ((ImageView) this.f2456c.getChildAt(i).findViewById(R.id.bc_line_img)).setImageDrawable(this.f2455b.skin.m736f(R.drawable.ic_expand_open));
            }
            for (DataDB.C0736a aVar : e) {
                View inflate = this.f2455b.getLayoutInflater().inflate(R.layout.bc_bm_line, (ViewGroup) this.f2456c, false);
                inflate.setTag(aVar);
                aVar.f2504i = i3;
                inflate.setPadding((int) (this.f2455b.f731b * 8.0f * ((float) i3)), 0, 0, 0);
                int i4 = aVar.f2499d;
                if ((i4 & 1) != 0 && (i4 & 16) == 0) {
                    ((ImageView) inflate.findViewById(R.id.bc_line_img)).setImageDrawable(this.f2455b.skin.m736f(R.drawable.ic_expand_close));
                }
                TextView textView = (TextView) inflate.findViewById(R.id.bc_line_text);
                textView.setText(aVar.f2502g);
                textView.setOnClickListener(this);
                i++;
                this.f2456c.addView(inflate, i);
                if ((aVar.f2499d & 16) != 0) {
                    i = m401b(i, aVar.f2496a, i3 + 1);
                }
            }
        }
        return i;
    }

    private int m400c(int i, int i2, int i3, boolean z) {
        int i4 = 0;
        while (true) {
            C0725a[] aVarArr = this.f2457d;
            if (i4 >= aVarArr.length) {
                return i;
            }
            C0725a aVar = aVarArr[i4];
            boolean z2 = true;
            if (aVar.f2460a == i2) {
                aVar.f2466g = true;
                aVar.f2464e = false;
                m402a(this.f2456c.getChildAt(i), aVar, this.f2458e == 1, false);
            }
            if (aVar.f2463d == i2) {
                View inflate = this.f2455b.getLayoutInflater().inflate((i4 == 0 && i2 == 0 && !z) ? R.layout.bc_home : R.layout.bc_line, (ViewGroup) this.f2456c, false);
                inflate.setTag(aVar);
                TextView textView = (TextView) inflate.findViewById(R.id.bc_line_text);
                textView.setText(aVar.f2462c);
                textView.setOnClickListener(this);
                int paddingLeft = textView.getPaddingLeft();
                aVar.f2468i = i3;
                textView.setPadding(paddingLeft + ((int) (((float) (i3 * 8)) * this.f2455b.f731b)), 0, 0, 0);
                if (i4 == 0 && i2 == 0 && !z) {
                    inflate.findViewById(R.id.bc_line_home).setOnClickListener(this);
                }
                if (i4 != 0) {
                    z2 = false;
                }
                m402a(inflate, aVar, z2, false);
                i++;
                this.f2456c.addView(inflate, i);
                if (aVar.f2466g) {
                    i = m400c(i, aVar.f2460a, i3 + 1, z);
                }
            }
            i4++;
        }
    }

    private void m399d(int i, int i2, int i3) {
        int childCount = this.f2456c.getChildCount() - 1;
        boolean z = i2 == 0 || i == childCount;
        if (z) {
            View childAt = this.f2456c.getChildAt(childCount);
            if (childAt.getTag() instanceof DataDB.C0736a) {
                childAt.findViewById(R.id.bc_line_text).setBackgroundDrawable(this.f2455b.skin.m736f(R.drawable.border_bottom));
            }
        }
        m401b(i, i2, i3);
        if (z) {
            LinearLayout linearLayout = this.f2456c;
            View childAt2 = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
            if (childAt2.getTag() instanceof DataDB.C0736a) {
                childAt2.findViewById(R.id.bc_line_text).setBackgroundDrawable(null);
            }
        }
    }

    private void m398e(int i, int i2, int i3, boolean z) {
        int i4 = this.f2458e;
        boolean z2 = false;
        if (i4 > 0) {
            m402a(this.f2456c.getChildAt(i4 - 1), (C0725a) this.f2456c.getChildAt(this.f2458e - 1).getTag(), this.f2458e == 1, false);
        }
        int c = this.f2458e + (m400c(i, i2, i3, z) - i);
        this.f2458e = c;
        if (c > 0) {
            View childAt = this.f2456c.getChildAt(c - 1);
            C0725a aVar = (C0725a) this.f2456c.getChildAt(this.f2458e - 1).getTag();
            if (this.f2458e == 1) {
                z2 = true;
            }
            m402a(childAt, aVar, z2, true);
        }
    }

    private void m397f(int i, int i2) {
        ((DataDB.C0736a) this.f2456c.getChildAt(i).getTag()).f2499d &= -17;
        ((ImageView) this.f2456c.getChildAt(i).findViewById(R.id.bc_line_img)).setImageDrawable(this.f2455b.skin.m736f(R.drawable.ic_expand_close));
        int i3 = i + 1;
        while (i3 < this.f2456c.getChildCount()) {
            DataDB.C0736a aVar = (DataDB.C0736a) this.f2456c.getChildAt(i3).getTag();
            if (aVar.f2500e == i2) {
                if ((aVar.f2499d & 16) != 0) {
                    m397f(i3, aVar.f2496a);
                }
                this.f2456c.removeViewAt(i3);
            } else {
                return;
            }
        }
    }

    private void m396g(int i, int i2) {
        View childAt = this.f2456c.getChildAt(i);
        C0725a aVar = (C0725a) childAt.getTag();
        boolean z = false;
        aVar.f2466g = false;
        aVar.f2464e = true;
        m402a(childAt, aVar, this.f2458e == 1, false);
        int i3 = i + 1;
        while (i3 < this.f2458e && ((C0725a) this.f2456c.getChildAt(i3).getTag()).f2468i > aVar.f2468i) {
            this.f2456c.removeViewAt(i3);
            this.f2458e--;
        }
        int i4 = this.f2458e;
        if (i4 > 0) {
            View childAt2 = this.f2456c.getChildAt(i4 - 1);
            C0725a aVar2 = (C0725a) this.f2456c.getChildAt(this.f2458e - 1).getTag();
            if (this.f2458e == 1) {
                z = true;
            }
            m402a(childAt2, aVar2, z, true);
        }
    }

    @Override
    public void onClick(View view) {
        if (R.id.bc_line_home == view.getId()) {
            this.f2454a.addPage(new Page_Start(this.f2455b));
            dismiss();
            return;
        }
        View view2 = (View) view.getParent();
        Object tag = view2.getTag();
        if (tag instanceof C0725a) {
            C0725a aVar = (C0725a) tag;
            long currentTimeMillis = System.currentTimeMillis();
            if (!aVar.f2467h || currentTimeMillis - this.f2459f <= 500) {
                if (aVar.f2461b != null) {
                    MainActivity mainActivity = this.f2455b;
                    Page b = Urls2.openPage(mainActivity, "https://4pda.ru/" + aVar.f2461b, true, 1);
                    if (b != null) {
                        b.title = aVar.f2462c;
                        this.f2454a.addPage(b);
                    }
                    dismiss();
                }
            } else if (aVar.f2466g) {
                m396g(this.f2456c.indexOfChild(view2), aVar.f2460a);
            } else {
                m398e(this.f2456c.indexOfChild(view2), aVar.f2460a, aVar.f2468i + 1, false);
            }
            this.f2459f = currentTimeMillis;
        } else if (tag instanceof DataDB.C0736a) {
            DataDB.C0736a aVar2 = (DataDB.C0736a) tag;
            int i = aVar2.f2499d;
            if ((i & 1) == 0) {
                MainActivity mainActivity2 = this.f2455b;
                Page b2 = Urls2.openPage(mainActivity2, "https://4pda.ru/" + aVar2.f2503h, false, 1);
                if (b2 != null) {
                    b2.title = aVar2.f2502g;
                    this.f2454a.addPage(b2);
                }
                dismiss();
            } else if ((i & 16) != 0) {
                m397f(this.f2456c.indexOfChild(view2), aVar2.f2496a);
            } else {
                m399d(this.f2456c.indexOfChild(view2), aVar2.f2496a, aVar2.f2504i + 1);
            }
        }
    }

    @Override
    public void show() {
        super.show();
        CustomDialog.m623c(this);
    }
}
