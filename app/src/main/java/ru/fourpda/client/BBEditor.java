package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;

public class BBEditor {
    C0626i0 f2027a;
    int f2028b;
    int f2029c;
    public EditText f2030d;
    MainActivity f2031e;
    private int f2032f;
    private int f2033g;

    class View$OnClickListenerC0605a implements View.OnClickListener {
        View$OnClickListenerC0605a() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("SUB");
        }
    }

    class View$OnClickListenerC0606a0 implements View.OnClickListener {
        final DlgSimple f2035a;

        View$OnClickListenerC0606a0(DlgSimple q1Var) {
            this.f2035a = q1Var;
        }

        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.f2035a.editText.getText().toString())) {
                BBEditor oVar = BBEditor.this;
                String obj = this.f2035a.editText.getText().toString();
                BBEditor oVar2 = BBEditor.this;
                oVar.m511n("URL", obj, oVar2.f2028b, oVar2.f2029c, false);
            }
        }
    }

    class View$OnClickListenerC0607b implements View.OnClickListener {
        View$OnClickListenerC0607b() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("SUP");
        }
    }

    class View$OnClickListenerC0608b0 implements View.OnClickListener {
        View$OnClickListenerC0608b0() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.f2030d.setText("");
        }
    }

    class View$OnClickListenerC0609c implements View.OnClickListener {
        View$OnClickListenerC0609c() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("Left");
        }
    }

    class View$OnClickListenerC0610c0 implements View.OnClickListener {
        View$OnClickListenerC0610c0() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("B");
        }
    }

    class View$OnClickListenerC0611d implements View.OnClickListener {
        View$OnClickListenerC0611d() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("Center");
        }
    }

    class View$OnClickListenerC0612d0 implements View.OnClickListener {
        View$OnClickListenerC0612d0() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("I");
        }
    }

    class View$OnClickListenerC0613e implements View.OnClickListener {
        View$OnClickListenerC0613e() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("Right");
        }
    }

    class View$OnClickListenerC0614e0 implements View.OnClickListener {
        View$OnClickListenerC0614e0() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("U");
        }
    }

    class View$OnClickListenerC0615f implements View.OnClickListener {
        final DlgSimple f2045a;

        View$OnClickListenerC0615f(DlgSimple q1Var) {
            this.f2045a = q1Var;
        }

        @Override
        public void onClick(View view) {
            if (!BBEditor.this.m511n("URL", null, -1, -1, true)) {
                BBEditor oVar = BBEditor.this;
                oVar.f2028b = oVar.f2030d.getSelectionStart();
                BBEditor oVar2 = BBEditor.this;
                oVar2.f2029c = oVar2.f2030d.getSelectionEnd();
                this.f2045a.editText.setText("");
                this.f2045a.show(true, true, true);
                BBEditor.this.f2031e.mainLayout.m859w(this.f2045a.editText);
            }
        }
    }

    class View$OnClickListenerC0616f0 implements View.OnClickListener {
        View$OnClickListenerC0616f0() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("S");
        }
    }

    class View$OnClickListenerC0617g implements View.OnClickListener {
        View$OnClickListenerC0617g() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("QUOTE");
        }
    }

    public static class C0618g0 {
        public static int f2049e;
        Dialog f2050a;
        BBEditor f2051b;
        int f2052c;
        int f2053d;

        class C0619a implements AdapterView.OnItemClickListener {
            final C0620b f2054a;

            C0619a(C0620b bVar) {
                this.f2054a = bVar;
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BBEditor oVar = C0618g0.this.f2051b;
                String str = this.f2054a.f2056a.get(i).f2060b;
                C0618g0 g0Var = C0618g0.this;
                oVar.m511n("COLOR", str, g0Var.f2052c, g0Var.f2053d, false);
                C0618g0.this.f2050a.cancel();
            }
        }

        public class C0620b extends BaseAdapter {
            public List<C0621a> f2056a;
            MainActivity f2057b;
            AbsListView.LayoutParams f2058c;

            public class C0621a {
                public int f2059a;
                public String f2060b;

                public C0621a(C0620b bVar, int i, String str) {
                    this.f2059a = i;
                    this.f2060b = str;
                }
            }

            public C0620b(C0618g0 g0Var, MainActivity mainActivity) {
                this.f2057b = mainActivity;
                int i = C0618g0.f2049e;
                this.f2058c = new AbsListView.LayoutParams(i, i);
                Vector vector = new Vector(30);
                this.f2056a = vector;
                vector.add(new C0621a(this, -16777216, "Black"));
                this.f2056a.add(new C0621a(this, -1, "White"));
                this.f2056a.add(new C0621a(this, -7876885, "SkyBlue"));
                this.f2056a.add(new C0621a(this, -12490271, "RoyalBlue"));
                this.f2056a.add(new C0621a(this, -16776961, "Blue"));
                this.f2056a.add(new C0621a(this, -16777077, "DarkBlue"));
                this.f2056a.add(new C0621a(this, -23296, "Orange"));
                this.f2056a.add(new C0621a(this, -47872, "OrangeRed"));
                this.f2056a.add(new C0621a(this, -2354116, "Crimson"));
                this.f2056a.add(new C0621a(this, -65536, "Red"));
                this.f2056a.add(new C0621a(this, -7667712, "DarkRed"));
                this.f2056a.add(new C0621a(this, -16711936, "Green"));
                this.f2056a.add(new C0621a(this, -13447886, "LimeGreen"));
                this.f2056a.add(new C0621a(this, -13726889, "SeaGreen"));
                this.f2056a.add(new C0621a(this, -60269, "DeepPink"));
                this.f2056a.add(new C0621a(this, -40121, "Tomato"));
                this.f2056a.add(new C0621a(this, -32944, "Coral"));
                this.f2056a.add(new C0621a(this, -8388480, "Purple"));
                this.f2056a.add(new C0621a(this, -11861886, "Indigo"));
                this.f2056a.add(new C0621a(this, -2180985, "BurlyWood"));
                this.f2056a.add(new C0621a(this, -5952982, "SandyBrown"));
                this.f2056a.add(new C0621a(this, -7852777, "Sienna"));
                this.f2056a.add(new C0621a(this, -2987746, "Chocolate"));
                this.f2056a.add(new C0621a(this, -16744320, "Teal"));
                this.f2056a.add(new C0621a(this, -4144960, "Silver"));
            }

            @Override
            public int getCount() {
                return this.f2056a.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return (long) i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = new View(this.f2057b);
                    view.setLayoutParams(this.f2058c);
                }
                view.setBackgroundColor(this.f2056a.get(i).f2059a);
                return view;
            }
        }

        @SuppressLint("WrongConstant")
        public C0618g0(BBEditor oVar) {
            this.f2051b = oVar;
            LinearLayout linearLayout = new LinearLayout(this.f2051b.f2031e);
            linearLayout.setOrientation(1);
            float f = this.f2051b.f2031e.f731b;
            int i = (int) (24.0f * f);
            linearLayout.setPadding(i, i, i, i);
            TextView textView = new TextView(this.f2051b.f2031e);
            textView.setText("Выберите цвет");
            textView.setTextColor(Skin.C0353a.f1365U);
            textView.setPadding(0, 0, 0, (int) (20.0f * f));
            textView.setTextSize(22.0f);
            textView.setTypeface(null, 1);
            linearLayout.addView(textView);
            GridView gridView = new GridView(this.f2051b.f2031e);
            C0620b bVar = new C0620b(this, this.f2051b.f2031e);
            gridView.setAdapter((ListAdapter) bVar);
            gridView.setNumColumns(-1);
            gridView.setColumnWidth(f2049e);
            int i2 = (int) (f * 8.0f);
            gridView.setHorizontalSpacing(i2);
            gridView.setVerticalSpacing(i2);
            gridView.setOnItemClickListener(new C0619a(bVar));
            linearLayout.addView(gridView);
            Dialog dialog = new Dialog(this.f2051b.f2031e, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            this.f2050a = dialog;
            dialog.setContentView(linearLayout);
            this.f2050a.setCanceledOnTouchOutside(true);
            this.f2050a.getWindow().setLayout(-1, -2);
            this.f2050a.getWindow().setBackgroundDrawable(this.f2051b.f2031e.skin.m736f(R.drawable.np_dialog));
        }

        public void m510a() {
            this.f2052c = this.f2051b.f2030d.getSelectionStart();
            this.f2053d = this.f2051b.f2030d.getSelectionEnd();
            this.f2050a.getWindow().getAttributes().gravity = 17;
            this.f2050a.show();
            CustomDialog.m623c(this.f2050a);
        }
    }

    class View$OnClickListenerC0622h implements View.OnClickListener {
        View$OnClickListenerC0622h() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("OFFTOP");
        }
    }

    public class C0623h0 {
        Dialog f2062a;
        BBEditor f2063b;
        int f2064c;
        int f2065d;

        class View$OnClickListenerC0624a implements View.OnClickListener {
            final int f2066a;

            View$OnClickListenerC0624a(BBEditor oVar, int i) {
                this.f2066a = i;
            }

            @Override
            public void onClick(View view) {
                BBEditor oVar = C0623h0.this.f2063b;
                String num = Integer.valueOf(this.f2066a).toString();
                C0623h0 h0Var = C0623h0.this;
                oVar.m511n("SIZE", num, h0Var.f2064c, h0Var.f2065d, false);
                C0623h0.this.f2062a.cancel();
            }
        }

        @SuppressLint("WrongConstant")
        public C0623h0(BBEditor oVar, BBEditor oVar2) {
            this.f2063b = oVar2;
            LinearLayout linearLayout = new LinearLayout(this.f2063b.f2031e);
            linearLayout.setOrientation(1);
            float f = this.f2063b.f2031e.f731b;
            int i = (int) (24.0f * f);
            linearLayout.setPadding(i, i, i, i);
            TextView textView = new TextView(this.f2063b.f2031e);
            textView.setText("Выберите размер текста");
            textView.setTextSize(22.0f);
            textView.setTypeface(null, 1);
            textView.setTextColor(Skin.C0353a.f1365U);
            textView.setPadding(0, 0, 0, i);
            linearLayout.addView(textView);
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).width = -1;
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.f2063b.f2031e);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);
            linearLayout.addView(horizontalScrollView);
            LinearLayout linearLayout2 = new LinearLayout(this.f2063b.f2031e);
            horizontalScrollView.addView(linearLayout2);
            for (int i2 = 1; i2 <= 7; i2++) {
                TextView textView2 = new TextView(this.f2063b.f2031e);
                textView2.setClickable(true);
                textView2.setTypeface(null, 1);
                textView2.setTextColor(Skin.C0353a.f1382f0);
                textView2.setTextSize(16.0f);
                textView2.setGravity(17);
                textView2.setBackgroundDrawable(oVar.f2031e.skin.m736f(R.drawable.button_flat));
                textView2.setText(Integer.valueOf(i2).toString());
                textView2.setOnClickListener(new View$OnClickListenerC0624a(oVar, i2));
                linearLayout2.addView(textView2);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
                int i3 = (int) (36.0f * f);
                layoutParams.width = i3;
                layoutParams.height = i3;
                if (i2 > 1) {
                    layoutParams.leftMargin = (int) (8.0f * f);
                }
            }
            Dialog dialog = new Dialog(this.f2063b.f2031e, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            this.f2062a = dialog;
            dialog.setContentView(linearLayout);
            this.f2062a.setCanceledOnTouchOutside(true);
            this.f2062a.getWindow().setLayout(-1, -2);
            this.f2062a.getWindow().setBackgroundDrawable(oVar.f2031e.skin.m736f(R.drawable.np_dialog));
        }

        public void m509a() {
            this.f2064c = this.f2063b.f2030d.getSelectionStart();
            this.f2065d = this.f2063b.f2030d.getSelectionEnd();
            this.f2062a.getWindow().getAttributes().gravity = 17;
            this.f2062a.show();
            CustomDialog.m623c(this.f2062a);
        }
    }

    class View$OnClickListenerC0625i implements View.OnClickListener {
        View$OnClickListenerC0625i() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("CODE");
        }
    }

    private static class C0626i0 {
        public static int f2069d;
        Dialog f2070a;
        BBEditor f2071b;
        int f2072c;

        class C0627a implements AdapterView.OnItemClickListener {
            final C0628b f2073a;

            C0627a(C0628b bVar) {
                this.f2073a = bVar;
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                C0626i0 i0Var = C0626i0.this;
                if (i0Var.f2072c >= 0) {
                    Editable text = i0Var.f2071b.f2030d.getText();
                    int i2 = C0626i0.this.f2072c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(C0626i0.this.f2072c > 0 ? " " : "");
                    sb.append(this.f2073a.f2075a.get(i).f1654a);
                    sb.append(" ");
                    text.insert(i2, sb.toString());
                }
                BBEditor oVar = C0626i0.this.f2071b;
                oVar.f2031e.mainLayout.m859w(oVar.f2030d);
                C0626i0.this.f2070a.cancel();
            }
        }

        public class C0628b extends BaseAdapter {
            public List<Util.SmileClass> f2075a = new Vector();
            MainActivity f2076b;
            AbsListView.LayoutParams f2077c;

            public C0628b(C0626i0 i0Var, MainActivity mainActivity) {
                this.f2076b = mainActivity;
                int i = C0626i0.f2069d;
                this.f2077c = new AbsListView.LayoutParams(i, i);
                for (int i2 = 0; i2 < Util.SmileClass.smilesList.size(); i2++) {
                    Util.SmileClass mVar = Util.SmileClass.smilesList.get(i2);
                    if (!mVar.f1661h) {
                        this.f2075a.add(mVar);
                    }
                }
            }

            @Override
            public int getCount() {
                return this.f2075a.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return (long) i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ImageView imageView = null;
                if (view == null) {
                    ImageView imageView2 = new ImageView(this.f2076b);
                    imageView2.setLayoutParams(this.f2077c);
                    imageView2.setScaleType(ImageView.ScaleType.CENTER);
                    imageView = imageView2;
                }
                Util.SmileClass mVar = this.f2075a.get(i);
                PicoImgRequest k = PicoImg.loadResource(this.f2076b, mVar.f1655b);
                k.to((ImageView) imageView);
                float f = this.f2076b.f731b;
                k.size((int) (((float) mVar.f1656c) * f), (int) (((float) mVar.f1657d) * f));
                k.scale(32);
                k.disableAnimation(!Prefs.f1147H);
                k.runAsync();
                return imageView;
            }
        }

        @SuppressLint("WrongConstant")
        C0626i0(BBEditor oVar) {
            this.f2071b = oVar;
            LinearLayout linearLayout = new LinearLayout(this.f2071b.f2031e);
            linearLayout.setOrientation(1);
            float f = this.f2071b.f2031e.f731b;
            int i = (int) (24.0f * f);
            linearLayout.setPadding(i, i, i, i);
            f2069d = (int) (50.0f * f);
            TextView textView = new TextView(this.f2071b.f2031e);
            textView.setText("Смайлики");
            textView.setPadding(0, 0, 0, (int) (20.0f * f));
            textView.setTextSize(22.0f);
            textView.setTypeface(null, 1);
            textView.setTextColor(Skin.C0353a.f1365U);
            linearLayout.addView(textView);
            GridView gridView = new GridView(this.f2071b.f2031e);
            C0628b bVar = new C0628b(this, this.f2071b.f2031e);
            gridView.setAdapter((ListAdapter) bVar);
            gridView.setNumColumns(-1);
            gridView.setColumnWidth(f2069d);
            int i2 = (int) (f * 0.0f);
            gridView.setHorizontalSpacing(i2);
            gridView.setVerticalSpacing(i2);
            gridView.setOnItemClickListener(new C0627a(bVar));
            linearLayout.addView(gridView);
            Dialog dialog = new Dialog(this.f2071b.f2031e, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            this.f2070a = dialog;
            dialog.setContentView(linearLayout);
            this.f2070a.setCanceledOnTouchOutside(true);
            this.f2070a.getWindow().setLayout(-1, -2);
            this.f2070a.getWindow().setBackgroundDrawable(this.f2071b.f2031e.skin.m736f(R.drawable.np_dialog));
        }

        public void m508a() {
            this.f2072c = Math.max(this.f2071b.f2030d.getSelectionStart(), this.f2071b.f2030d.getSelectionEnd());
            this.f2070a.getWindow().getAttributes().gravity = 17;
            this.f2070a.show();
            CustomDialog.m623c(this.f2070a);
        }
    }

    class View$OnClickListenerC0629j implements View.OnClickListener {
        View$OnClickListenerC0629j() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("SPOILER");
        }
    }

    class C0630k implements TextWatcher {
        final DlgSimple f2079a;

        C0630k(BBEditor oVar, DlgSimple q1Var) {
            this.f2079a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2079a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class View$OnClickListenerC0631l implements View.OnClickListener {
        final DlgSimple f2080a;

        View$OnClickListenerC0631l(DlgSimple q1Var) {
            this.f2080a = q1Var;
        }

        @Override
        public void onClick(View view) {
            if (!BBEditor.this.m511n("SPOILER", null, -1, -1, true)) {
                BBEditor oVar = BBEditor.this;
                oVar.f2028b = oVar.f2030d.getSelectionStart();
                BBEditor oVar2 = BBEditor.this;
                oVar2.f2029c = oVar2.f2030d.getSelectionEnd();
                this.f2080a.editText.setText("");
                this.f2080a.show(true, true, true);
                BBEditor.this.f2031e.mainLayout.m859w(this.f2080a.editText);
            }
        }
    }

    class View$OnClickListenerC0632m implements View.OnClickListener {
        View$OnClickListenerC0632m() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("HIDE");
        }
    }

    class View$OnLongClickListenerC0633n implements View.OnLongClickListener {
        View$OnLongClickListenerC0633n() {
        }

        @Override
        public boolean onLongClick(View view) {
            BBEditor.this.m511n("HIDE", "1000000", -1, -1, false);
            return true;
        }
    }

    class View$OnClickListenerC0634o implements View.OnClickListener {
        View$OnClickListenerC0634o() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m515j(false);
        }
    }

    class View$OnClickListenerC0635p implements View.OnClickListener {
        View$OnClickListenerC0635p() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m515j(true);
        }
    }

    class View$OnClickListenerC0636q implements View.OnClickListener {
        View$OnClickListenerC0636q() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("CUR");
        }
    }

    class View$OnClickListenerC0637r implements View.OnClickListener {
        View$OnClickListenerC0637r() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("MOD");
        }
    }

    class View$OnClickListenerC0638s implements View.OnClickListener {
        View$OnClickListenerC0638s() {
        }

        @Override
        public void onClick(View view) {
            BBEditor.this.m512m("EX");
        }
    }

    class View$OnClickListenerC0639t implements View.OnClickListener {
        final C0623h0 f2089a;

        View$OnClickListenerC0639t(C0623h0 h0Var) {
            this.f2089a = h0Var;
        }

        @Override
        public void onClick(View view) {
            if (!BBEditor.this.m511n("SIZE", null, -1, -1, true)) {
                this.f2089a.m509a();
            }
        }
    }

    class View$OnClickListenerC0640u implements View.OnClickListener {
        final C0618g0 f2091a;

        View$OnClickListenerC0640u(C0618g0 g0Var) {
            this.f2091a = g0Var;
        }

        @Override
        public void onClick(View view) {
            if (!BBEditor.this.m511n("COLOR", null, -1, -1, true)) {
                this.f2091a.m510a();
            }
        }
    }

    class View$OnClickListenerC0641v implements View.OnClickListener {
        final DlgSimple f2093a;

        View$OnClickListenerC0641v(DlgSimple q1Var) {
            this.f2093a = q1Var;
        }

        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.f2093a.editText.getText().toString())) {
                BBEditor oVar = BBEditor.this;
                String obj = this.f2093a.editText.getText().toString();
                BBEditor oVar2 = BBEditor.this;
                oVar.m511n("SPOILER", obj, oVar2.f2028b, oVar2.f2029c, false);
            }
        }
    }

    class View$OnClickListenerC0642w implements View.OnClickListener {
        final DlgSimple f2095a;

        View$OnClickListenerC0642w(BBEditor oVar, DlgSimple q1Var) {
            this.f2095a = q1Var;
        }

        @Override
        public void onClick(View view) {
            this.f2095a.show(true, true, true);
        }
    }

    public class C0643x implements TextWatcher {
        final DlgSimple f2096a;

        C0643x(BBEditor oVar, DlgSimple q1Var) {
            this.f2096a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2096a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class View$OnClickListenerC0644y implements View.OnClickListener {
        final DlgSimple f2097a;
        final Editable f2098b;

        class RunnableC0645a implements Runnable {
            RunnableC0645a() {
            }

            @Override
            public void run() {
                View$OnClickListenerC0644y.this.f2097a.show(true, true, true);
                View$OnClickListenerC0644y yVar = View$OnClickListenerC0644y.this;
                BBEditor.this.f2031e.mainLayout.m859w(yVar.f2097a.editText);
            }
        }

        View$OnClickListenerC0644y(DlgSimple q1Var, Editable editable) {
            this.f2097a = q1Var;
            this.f2098b = editable;
        }

        @Override
        public void onClick(View view) {
            String obj = this.f2097a.editText.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                StringBuilder sb = new StringBuilder();
                sb.append(BBEditor.this.f2032f == 1 ? "[*]" : "\n[*]");
                sb.append(obj);
                String sb2 = sb.toString();
                this.f2098b.insert(BBEditor.this.f2033g, sb2);
                BBEditor.this.f2033g += sb2.length();
            }
            this.f2097a.editText.setText("");
            this.f2097a.promtMessage.setText(String.format("Введите содержание %d пункта списка", Integer.valueOf(BBEditor.m518g(BBEditor.this))));
            if (this.f2097a.f1804b == view) {
                BBEditor.this.f2031e.mainLayout.postDelayed(new RunnableC0645a(), 300);
            }
        }
    }

    class C0646z implements TextWatcher {
        final DlgSimple f2101a;

        C0646z(BBEditor oVar, DlgSimple q1Var) {
            this.f2101a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f2101a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    @SuppressLint("WrongConstant")
    public BBEditor(MainActivity mainActivity, View view, EditText editText, boolean z) {
        this.f2030d = editText;
        this.f2031e = mainActivity;
        C0623h0 h0Var = z ? new C0623h0(this, this) : null;
        C0618g0 g0Var = z ? new C0618g0(this) : null;
        this.f2027a = new C0626i0(this);
        DlgSimple q1Var = z ? new DlgSimple(this.f2031e, "Введите заголовок спойлера", false, null, null) : null;
        if (q1Var != null) {
            q1Var.editText.addTextChangedListener(new C0630k(this, q1Var));
            q1Var.m620f(new View$OnClickListenerC0641v(q1Var), true);
        }
        DlgSimple q1Var2 = z ? new DlgSimple(this.f2031e, "Введите полный URL адрес", false, null, null) : null;
        if (q1Var2 != null) {
            q1Var2.editText.addTextChangedListener(new C0646z(this, q1Var2));
            q1Var2.m620f(new View$OnClickListenerC0606a0(q1Var2), true);
        }
        DlgSimple q1Var3 = new DlgSimple(this.f2031e, "Будет очищен весь текст.", false, null, null);
        q1Var3.editText.setVisibility(8);
        q1Var3.m620f(new View$OnClickListenerC0608b0(), true);
        m513l(view, R.id.editor_B, new View$OnClickListenerC0610c0(), null);
        m513l(view, R.id.editor_I, new View$OnClickListenerC0612d0(), null);
        m513l(view, R.id.editor_U, new View$OnClickListenerC0614e0(), null);
        m513l(view, R.id.editor_Strike, new View$OnClickListenerC0616f0(), null);
        m513l(view, R.id.editor_Sub, new View$OnClickListenerC0605a(), null);
        m513l(view, R.id.editor_Sup, new View$OnClickListenerC0607b(), null);
        m513l(view, R.id.editor_Left, new View$OnClickListenerC0609c(), null);
        m513l(view, R.id.editor_Center, new View$OnClickListenerC0611d(), null);
        m513l(view, R.id.editor_Right, new View$OnClickListenerC0613e(), null);
        m513l(view, R.id.editor_Link, new View$OnClickListenerC0615f(q1Var2), null);
        m513l(view, R.id.editor_Quote, new View$OnClickListenerC0617g(), null);
        m513l(view, R.id.editor_Offtop, new View$OnClickListenerC0622h(), null);
        m513l(view, R.id.editor_Code, new View$OnClickListenerC0625i(), null);
        m513l(view, R.id.editor_Spoiler, new View$OnClickListenerC0629j(), null);
        m513l(view, R.id.editor_SpoilerNamed, new View$OnClickListenerC0631l(q1Var), null);
        m513l(view, R.id.editor_Hide, new View$OnClickListenerC0632m(), new View$OnLongClickListenerC0633n());
        m513l(view, R.id.editor_List, new View$OnClickListenerC0634o(), null);
        m513l(view, R.id.editor_ListNumder, new View$OnClickListenerC0635p(), null);
        m513l(view, R.id.editor_ModKur, new View$OnClickListenerC0636q(), null);
        m513l(view, R.id.editor_ModMod, new View$OnClickListenerC0637r(), null);
        m513l(view, R.id.editor_ModAdmin, new View$OnClickListenerC0638s(), null);
        m513l(view, R.id.editor_Size, new View$OnClickListenerC0639t(h0Var), null);
        m513l(view, R.id.editor_Color, new View$OnClickListenerC0640u(g0Var), null);
        m513l(view, R.id.editor_Clear, new View$OnClickListenerC0642w(this, q1Var3), null);
    }

    static int m518g(BBEditor oVar) {
        int i = oVar.f2032f + 1;
        oVar.f2032f = i;
        return i;
    }

    public void m515j(boolean z) {
        int selectionStart = this.f2030d.getSelectionStart();
        int selectionEnd = this.f2030d.getSelectionEnd();
        Editable text = this.f2030d.getText();
        text.insert(selectionEnd, "[/LIST]");
        text.insert(selectionStart, z ? "[LIST=1]" : "[LIST]");
        this.f2033g = (z ? 8 : 6) + selectionStart;
        this.f2030d.setSelection(selectionStart, (z ? 15 : 13) + selectionEnd);
        if (selectionEnd == selectionStart) {
            this.f2032f = 1;
            DlgSimple q1Var = new DlgSimple(this.f2031e, "Введите содержание 1 пункта списка", false, "СЛЕДУЮЩИЙ", "ЗАВЕРШИТЬ");
            q1Var.m625a(false);
            q1Var.editText.addTextChangedListener(new C0643x(this, q1Var));
            this.f2031e.mainLayout.m859w(q1Var.editText);
            View$OnClickListenerC0644y yVar = new View$OnClickListenerC0644y(q1Var, text);
            q1Var.m620f(yVar, true);
            q1Var.m621e(yVar, true);
            q1Var.show(true, true, true);
            return;
        }
        int i = selectionEnd + (this.f2033g - selectionStart);
        while (true) {
            int i2 = this.f2033g;
            if (i2 < i) {
                text.insert(i2, "[*]");
                this.f2033g += 3;
                i += 3;
                while (this.f2033g < text.length() && '\n' != text.charAt(this.f2033g - 1)) {
                    this.f2033g++;
                }
            } else {
                return;
            }
        }
    }

    public void m512m(String str) {
        m511n(str, null, -1, -1, false);
    }

    public boolean m511n(String str, String str2, int i, int i2, boolean z) {
        String str3;
        int indexOf;
        if (i == -1) {
            i = this.f2030d.getSelectionStart();
        }
        if (i2 == -1) {
            i2 = this.f2030d.getSelectionEnd();
        }
        if (i > i2) {
            int i3 = i + i2;
            i2 = i3 - i2;
            i = i3 - i2;
        }
        if (i >= 0) {
            Editable text = this.f2030d.getText();
            String lowerCase = ("[" + str).toLowerCase();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(str);
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = "=" + str2;
            }
            sb.append(str3);
            sb.append("]");
            String sb2 = sb.toString();
            String str4 = "[/" + str + "]";
            if (i > lowerCase.length() + 1 && text.charAt(i - 1) == ']' && text.subSequence(i2, Math.min(str4.length() + i2, text.length())).toString().equalsIgnoreCase(str4)) {
                int max = Math.max(0, i - 100);
                int lastIndexOf = text.subSequence(max, i).toString().toLowerCase().lastIndexOf(lowerCase);
                if (lastIndexOf >= 0) {
                    text.delete(i2, str4.length() + i2);
                    int i4 = lastIndexOf + max;
                    text.delete(i4, i);
                    this.f2030d.setSelection(i4, (i2 + i4) - i);
                    return true;
                }
            }
            String charSequence = text.subSequence(i, i2).toString();
            if (charSequence.length() > lowerCase.length() + str4.length() && charSequence.substring(0, lowerCase.length()).equalsIgnoreCase(lowerCase) && charSequence.substring(charSequence.length() - str4.length()).equalsIgnoreCase(str4) && (indexOf = charSequence.indexOf(93)) > 0 && indexOf < charSequence.length() - str4.length()) {
                text.delete(i2 - str4.length(), i2);
                text.delete(i, i + indexOf + 1);
                this.f2030d.setSelection(i, (i2 - str4.length()) - (indexOf + 1));
                return true;
            } else if (!z) {
                text.insert(i2, str4);
                text.insert(i, sb2);
                this.f2030d.setSelection(i + sb2.length(), i2 + sb2.length());
                return true;
            } else {
                this.f2031e.mainLayout.m859w(this.f2030d);
            }
        }
        return false;
    }

    public String m524a() {
        return this.f2030d.getText().toString();
    }

    public void m523b() {
        this.f2027a.m508a();
    }

    @SuppressLint("WrongConstant")
    public void m514k(View view, boolean z, boolean z2) {
        View findViewById = view.findViewById(R.id.editor_ModKur);
        int i = 0;
        if (findViewById != null) {
            findViewById.setVisibility(z2 | z ? 0 : 8);
        }
        View findViewById2 = view.findViewById(R.id.editor_ModMod);
        if (findViewById2 != null) {
            findViewById2.setVisibility(z ? 0 : 8);
        }
        View findViewById3 = view.findViewById(R.id.editor_ModAdmin);
        if (findViewById3 != null) {
            if (!z) {
                i = 8;
            }
            findViewById3.setVisibility(i);
        }
    }

    void m513l(View view, int i, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        View findViewById = view.findViewById(i);
        if (findViewById != null) {
            findViewById.setOnClickListener(onClickListener);
            findViewById.setOnLongClickListener(onLongClickListener);
        }
    }
}
