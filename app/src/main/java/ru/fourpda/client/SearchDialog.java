package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;

public class SearchDialog extends CustomDialog {
    EditText f1293j;
    private View f1294k;
    Widgets$MemberView f1295l;
    TextView f1296m;
    TextView f1297n;
    TextView f1299p;
    Page f1300q;
    MainActivity f1301r;
    String[] f1304u = {"по соответствию", "по убыванию даты", "по возрастанию даты"};
    String[] f1305v = {"везде", "только в заголовках тем", "только в сообщениях"};
    View.OnClickListener f1306w = new View$OnClickListenerC0347h();
    Widgets$CheckboxTextView f1298o = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_search_themes);
    List<String> f1302s = new Vector();
    List<Integer> f1303t = new Vector();

    class C0337a implements TextWatcher {
        C0337a() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            SearchDialog.this.m625a(editable.length() > 0 || (SearchDialog.this.f1294k.getVisibility() == 0 && SearchDialog.this.f1295l.getMemberId() != null));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class View$OnClickListenerC0338b implements View.OnClickListener {
        View$OnClickListenerC0338b() {
        }

        @Override
        public void onClick(View view) {
            SearchDialog.this.f1293j.setText("");
        }
    }

    class C0339c implements Util.AbstractC0429j<Boolean, Boolean> {
        C0339c() {
        }

        public Boolean mo222a(Boolean bool) {
            SearchDialog.this.m625a(bool.booleanValue() || !TextUtils.isEmpty(SearchDialog.this.f1293j.getText().toString()));
            return Boolean.TRUE;
        }
    }

    class View$OnClickListenerC0340d implements View.OnClickListener {

        class C0341a implements OptionPoupupMenuView.IClickListener {
            C0341a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                SearchDialog.this.f1296m.setTag(Integer.valueOf(i3));
                SearchDialog d1Var = SearchDialog.this;
                d1Var.f1296m.setText(d1Var.f1302s.get(i3));
            }
        }

        View$OnClickListenerC0340d() {
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(SearchDialog.this.f1301r, new C0341a());
            for (int i = 0; i < SearchDialog.this.f1302s.size(); i++) {
                o1Var.addMenuItem(0, 0, i, SearchDialog.this.f1302s.get(i));
            }
            o1Var.show(null);
        }
    }

    class View$OnClickListenerC0342e implements View.OnClickListener {

        class C0343a implements OptionPoupupMenuView.IClickListener {
            C0343a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                SearchDialog.this.f1297n.setTag(Integer.valueOf(i3));
                SearchDialog d1Var = SearchDialog.this;
                d1Var.f1297n.setText(d1Var.f1304u[i3]);
            }
        }

        View$OnClickListenerC0342e() {
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(SearchDialog.this.f1301r, new C0343a());
            int i = 0;
            while (true) {
                String[] strArr = SearchDialog.this.f1304u;
                if (i < strArr.length) {
                    o1Var.addMenuItem(0, 0, i, strArr[i]);
                    i++;
                } else {
                    o1Var.show(null);
                    return;
                }
            }
        }
    }

    class View$OnClickListenerC0344f implements View.OnClickListener {

        class C0345a implements OptionPoupupMenuView.IClickListener {
            C0345a() {
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                SearchDialog.this.f1299p.setTag(Integer.valueOf(i3));
                SearchDialog d1Var = SearchDialog.this;
                d1Var.f1299p.setText(d1Var.f1305v[i3]);
            }
        }

        View$OnClickListenerC0344f() {
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(SearchDialog.this.f1301r, new C0345a());
            int i = 0;
            while (true) {
                String[] strArr = SearchDialog.this.f1305v;
                if (i < strArr.length) {
                    o1Var.addMenuItem(0, 0, i, strArr[i]);
                    i++;
                } else {
                    o1Var.show(null);
                    return;
                }
            }
        }
    }

    class View$OnClickListenerC0346g implements View.OnClickListener {
        View$OnClickListenerC0346g() {
        }

        @Override
        public void onClick(View view) {
            SearchDialog d1Var = SearchDialog.this;
            d1Var.m744l(d1Var.f1297n.getVisibility() != 0);
        }
    }

    class View$OnClickListenerC0347h implements View.OnClickListener {
        View$OnClickListenerC0347h() {
        }

        @Override
        public void onClick(View view) {
            int i;
            String str;
            String str2;
            @SuppressLint("WrongConstant") boolean z = SearchDialog.this.f1297n.getVisibility() == 0;
            SearchDialog d1Var = SearchDialog.this;
            Tab f1Var = d1Var.f1301r.mainLayout.tab;
            int intValue = d1Var.f1303t.get(((Integer) d1Var.f1296m.getTag()).intValue()).intValue();
            int i2 = 131072;
            if (z && ((Integer) SearchDialog.this.f1297n.getTag()).intValue() == 1) {
                i2 = 655360;
            } else if (z && ((Integer) SearchDialog.this.f1297n.getTag()).intValue() == 2) {
                i2 = 1703936;
            }
            if (z && SearchDialog.this.f1298o.getChecked()) {
                i2 |= 65536;
            }
            if (!z || ((Integer) SearchDialog.this.f1299p.getTag()).intValue() != 1) {
                i = (!z || ((Integer) SearchDialog.this.f1299p.getTag()).intValue() != 2) ? i2 | 3 : 1 | i2;
            } else {
                i = i2 | 2;
            }
            Page a0Var = null;
            Integer b = z ? SearchDialog.this.f1295l.getMemberId() : null;
            if (b == null) {
                b = 0;
            }
            String obj = SearchDialog.this.f1293j.getText().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(obj);
            if (b.intValue() > 0) {
                str = " в постах " + SearchDialog.this.f1295l.getText().toString();
            } else {
                str = "";
            }
            sb.append(str);
            String sb2 = sb.toString();
            SearchDialog d1Var2 = SearchDialog.this;
            Page a0Var2 = d1Var2.f1300q;
            if (a0Var2 instanceof Page_Topic) {
                d1Var2.m745k("topic");
                if (intValue == 67108864) {
                    SearchDialog d1Var3 = SearchDialog.this;
                    a0Var = ((Page_Topic) d1Var3.f1300q).m124j0(d1Var3.f1293j.getText().toString(), b.intValue());
                } else if ((intValue & 134217728) > 0) {
                    a0Var = new Page_Search(SearchDialog.this.f1301r, i | 1, 0, 134217728 ^ intValue, b.intValue(), obj, 0, sb2);
                } else if ((intValue & 268435456) > 0) {
                    str2 = obj;
                    a0Var = new Page_Search(SearchDialog.this.f1301r, i, intValue ^ 268435456, 0, b.intValue(), str2, 0, sb2);
                }
                str2 = obj;
            } else {
                str2 = obj;
                if (a0Var2 instanceof Page_Forum) {
                    d1Var2.m745k("forum");
                    if (intValue != 67108864 && (intValue & 268435456) > 0) {
                        a0Var = new Page_Search(SearchDialog.this.f1301r, i, intValue ^ 268435456, 0, b.intValue(), str2, 0, sb2);
                    }
                } else if (a0Var2 instanceof Page_Search) {
                    if ((intValue & 134217728) != 0) {
                        a0Var = new Page_Search(d1Var2.f1301r, i | 1, 0, 134217728 ^ intValue, b.intValue(), str2, 0, sb2);
                    } else if ((intValue & 268435456) != 0) {
                        a0Var = new Page_Search(d1Var2.f1301r, i, intValue ^ 268435456, 0, b.intValue(), str2, 0, sb2);
                    }
                } else if (a0Var2 instanceof Page_Forums) {
                    d1Var2.m745k("forums");
                } else if (a0Var2 instanceof Page_Site_List) {
                    d1Var2.m745k("site");
                } else if (a0Var2 instanceof Page_Article) {
                    d1Var2.m745k("article");
                } else if (a0Var2 instanceof Page_Start) {
                    d1Var2.m745k("start");
                }
            }
            if (intValue == 1073741824) {
                a0Var = new Page_Search(SearchDialog.this.f1301r, (i ^ 3) | 4, 0, 0, b, str2, 0, sb2);
            } else if (intValue == 536870912) {
                a0Var = new Page_Search(SearchDialog.this.f1301r, i, 0, 0, b, str2, 0, sb2);
            } else if (intValue == Integer.MIN_VALUE) {
                a0Var = new Page_Search(SearchDialog.this.f1301r, i | 4, 0, 0, b, str2, 0, sb2);
            }
            if (a0Var != null) {
                a0Var.searchDialog = SearchDialog.this;
                f1Var.addPage(a0Var);
                return;
            }
            Toast.makeText(SearchDialog.this.f1301r, "Нет результатов поиска.", Toast.LENGTH_SHORT).show();
        }
    }

    public SearchDialog(MainActivity mainActivity, Page a0Var) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_search2, (ViewGroup) null), "ПОИСК", "ОПЦИИ");
        boolean z;
        Integer m;
        int i;
        Integer forumNumber;
        Integer m3;
        int i2 = 0;
        char c = 2;
        this.f1300q = a0Var;
        this.f1301r = mainActivity;
        EditText editText = (EditText) this.rootView.findViewById(R.id.dlg_search_term);
        this.f1293j = editText;
        editText.addTextChangedListener(new C0337a());
        this.rootView.findViewById(R.id.dlg_search_term_clear).setOnClickListener(new View$OnClickListenerC0338b());
        View findViewById = this.rootView.findViewById(R.id.memberLayout);
        this.f1294k = findViewById;
        Widgets$MemberView widgets$MemberView = (Widgets$MemberView) findViewById.findViewById(R.id.memberEdit);
        this.f1295l = widgets$MemberView;
        widgets$MemberView.resetData();
        this.f1295l.f903c = new C0339c();
        TextView textView = (TextView) this.rootView.findViewById(R.id.dlg_search_where);
        this.f1296m = textView;
        textView.setOnClickListener(new View$OnClickListenerC0340d());
        this.f1304u[0] = mainActivity.getResources().getString(R.string.dlg_search_sort_rev);
        this.f1304u[1] = mainActivity.getResources().getString(R.string.dlg_search_sort_date_down);
        this.f1304u[2] = mainActivity.getResources().getString(R.string.dlg_search_sort_date_up);
        TextView textView2 = (TextView) this.rootView.findViewById(R.id.dlg_search_sort);
        this.f1297n = textView2;
        textView2.setTag(0);
        this.f1297n.setText(this.f1304u[0]);
        this.f1297n.setOnClickListener(new View$OnClickListenerC0342e());
        this.f1305v[0] = mainActivity.getResources().getString(R.string.dlg_search_forum_all);
        this.f1305v[1] = mainActivity.getResources().getString(R.string.dlg_search_forum_titles);
        this.f1305v[2] = mainActivity.getResources().getString(R.string.dlg_search_forum_posts);
        TextView textView3 = (TextView) this.rootView.findViewById(R.id.dlg_search_forum);
        this.f1299p = textView3;
        textView3.setTag(0);
        this.f1299p.setText(this.f1305v[0]);
        this.f1299p.setOnClickListener(new View$OnClickListenerC0344f());
        m620f(this.f1306w, true);
        m621e(new View$OnClickListenerC0346g(), false);
        Page a0Var2 = this.f1300q;
        if (a0Var2 instanceof Page_Topic) {
            this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_page));
            this.f1303t.add(67108864);
            this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_topic));
            this.f1303t.add(((Page_Topic) a0Var2).topicId | 134217728);
            Document uVar = this.f1300q.currentDocument;
            if (uVar != null) {
                Document l = uVar.getDocument(0);
                if (l.count() > 0) {
                    Document l2 = l.getDocument(0);
                    List<String> list = this.f1302s;
                    list.add(mainActivity.getResources().getString(R.string.dlg_search_where_the_forum) + " " + l2.getString(2));
                    this.f1303t.add(l2.getInt(1).intValue() | 268435456);
                }
            }
            this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_forum));
            this.f1303t.add(536870912);
            z = false;
            i2 = 1;
        } else {
            if (a0Var2 instanceof Page_Forum) {
                Page_Forum g0Var = (Page_Forum) a0Var2;
                List<String> list2 = this.f1302s;
                list2.add(mainActivity.getResources().getString(R.string.dlg_search_where_the_forum) + " " + g0Var.title);
                this.f1303t.add(g0Var.forumNumber | 268435456);
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_forum));
                this.f1303t.add(536870912);
            } else if (a0Var2 instanceof Page_Forums) {
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_forum));
                this.f1303t.add(536870912);
            } else if (a0Var2 instanceof Page_Site_List) {
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_site));
                this.f1303t.add(1073741824);
            } else if (a0Var2 instanceof Page_Article) {
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_site));
                this.f1303t.add(1073741824);
            } else if (a0Var2 instanceof Page_Start) {
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_forum));
                this.f1303t.add(536870912);
                this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_site));
                this.f1303t.add(1073741824);
                z = false;
                i2 = 2;
            } else if (a0Var2 instanceof Page_Search) {
                Page_Search s0Var = (Page_Search) a0Var2;
                if (!TextUtils.isEmpty(s0Var.f2579I)) {
                    this.f1293j.setText(s0Var.f2579I);
                    this.f1293j.setSelection(s0Var.f2579I.length());
                } else {
                    this.f1293j.setText("");
                }
                int i3 = s0Var.searchFlags & 7;
                if (i3 == 4) {
                    this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_site));
                    this.f1303t.add(1073741824);
                } else if (i3 != 7) {
                    Document uVar2 = s0Var.f2577G;
                    if (uVar2 != null && 1 == uVar2.count() && (m3 = s0Var.f2577G.getInt(0)) != null && m3.intValue() > 0) {
                        this.f1303t.add(m3 | 134217728);
                        this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_topic));
                    }
                    Document uVar3 = s0Var.f2576F;
                    if (uVar3 != null && 1 == uVar3.count() && (forumNumber = s0Var.f2576F.getInt(0)) != null && forumNumber.intValue() > 0) {
                        this.f1303t.add(forumNumber | 268435456);
                        List<String> list3 = this.f1302s;
                        list3.add("в форуме #" + forumNumber);
                    }
                    this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_forum));
                    this.f1303t.add(536870912);
                    if (2 == i3) {
                        this.f1299p.setTag(1);
                        this.f1299p.setText(this.f1305v[1]);
                    } else if (1 == i3) {
                        this.f1299p.setTag(2);
                        this.f1299p.setText(this.f1305v[2]);
                    }
                    z = true;
                    Document uVar4 = s0Var.f2578H;
                    m = uVar4 == null ? uVar4.getInt(0) : null;
                    if (m != null || m.intValue() == 0) {
                        if (!TextUtils.isEmpty(s0Var.f2578H.getString(0))) {
                            this.f1295l.m840f(s0Var.f2578H.getString(0));
                        }
                        i = s0Var.searchFlags;
                        if ((524288 & i) != 0) {
                            this.f1297n.setTag(Integer.valueOf((i & 1048576) != 0 ? 2 : 1));
                            this.f1297n.setText(this.f1304u[(1048576 & s0Var.searchFlags) == 0 ? 1 : c]);
                            z = true;
                        }
                        if ((s0Var.searchFlags & 65536) != 0) {
                            this.f1298o.setChecked(true);
                            z = true;
                        }
                    } else {
                        this.f1295l.m841e(m.intValue());
                    }
                    z = true;
                    i = s0Var.searchFlags;
                    if ((524288 & i) != 0) {
                    }
                    if ((s0Var.searchFlags & 65536) != 0) {
                    }
                }
                z = false;
                Document uVar42 = s0Var.f2578H;
                
                if (!TextUtils.isEmpty(s0Var.f2578H.getString(0))) {
                }
                i = s0Var.searchFlags;
                if ((524288 & i) != 0) {
                }
                if ((s0Var.searchFlags & 65536) != 0) {
                }
            }
            z = false;
        }
        this.f1302s.add(mainActivity.getResources().getString(R.string.dlg_search_where_all));
        this.f1303t.add(Integer.MIN_VALUE);
        this.f1296m.setTag(Integer.valueOf(i2));
        this.f1296m.setText(this.f1302s.get(i2));
        m744l(z);
        Page a0Var3 = this.f1300q;
        if (a0Var3 instanceof Page_Topic) {
            m746j("topic");
        } else if (a0Var3 instanceof Page_Forum) {
            m746j("forum");
        } else if (a0Var3 instanceof Page_Forums) {
            m746j("forums");
        } else if (a0Var3 instanceof Page_Site_List) {
            m746j("site");
        } else if (a0Var3 instanceof Page_Article) {
            m746j("article");
        } else if (a0Var3 instanceof Page_Start) {
            m746j("start");
        }
    }

    @SuppressLint("WrongConstant")
    public void m744l(boolean z) {
        if (z) {
            this.rootView.findViewById(R.id.dlg_search_member_label).setVisibility(0);
            this.f1294k.setVisibility(0);
            this.rootView.findViewById(R.id.dlg_search_sort_label).setVisibility(0);
            this.f1297n.setVisibility(0);
            this.f1298o.setVisibility(0);
            this.rootView.findViewById(R.id.dlg_search_forum_label).setVisibility(0);
            this.f1299p.setVisibility(0);
            this.f1805c.setText("КРАТКО");
            ((InputMethodManager) this.f1301r.getSystemService("input_method")).hideSoftInputFromWindow(this.rootView.getWindowToken(), 0);
            return;
        }
        this.rootView.findViewById(R.id.dlg_search_member_label).setVisibility(8);
        this.f1294k.setVisibility(8);
        this.rootView.findViewById(R.id.dlg_search_sort_label).setVisibility(8);
        this.f1297n.setVisibility(8);
        this.f1298o.setVisibility(8);
        this.rootView.findViewById(R.id.dlg_search_forum_label).setVisibility(8);
        this.f1299p.setVisibility(8);
        this.f1805c.setText("ОПЦИИ");
    }

    @SuppressLint("WrongConstant")
    public void m749g() {
        boolean z = true;
        super.show(true, true, true);
        if (TextUtils.isEmpty(this.f1293j.getText().toString()) && (this.f1294k.getVisibility() != 0 || this.f1295l.getMemberId() == null)) {
            z = false;
        }
        m625a(z);
        this.f1805c.setText(this.f1297n.getVisibility() == 0 ? "КРАТКО" : "ОПЦИИ");
    }

    void m746j(String str) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f1301r);
        int i = defaultSharedPreferences.getInt("s-w-" + str, -1);
        if (-1 != i) {
            this.f1296m.setTag(Integer.valueOf(i));
            this.f1296m.setText(this.f1302s.get(i));
            int i2 = defaultSharedPreferences.getInt("s-s-" + str, 0);
            this.f1297n.setTag(Integer.valueOf(i2));
            this.f1297n.setText(this.f1304u[i2]);
            Widgets$CheckboxTextView widgets$CheckboxTextView = this.f1298o;
            widgets$CheckboxTextView.setChecked(defaultSharedPreferences.getBoolean("s-v-" + str, false));
            int i3 = defaultSharedPreferences.getInt("s-i-" + str, 0);
            this.f1299p.setTag(Integer.valueOf(i3));
            this.f1299p.setText(this.f1305v[i3]);
            m744l(defaultSharedPreferences.getBoolean("s-o-" + str, false));
        }
    }

    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    void m745k(String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f1301r).edit();
        edit.putInt("s-w-" + str, ((Integer) this.f1296m.getTag()).intValue());
        edit.putInt("s-s-" + str, ((Integer) this.f1297n.getTag()).intValue());
        edit.putBoolean("s-v-" + str, this.f1298o.getChecked());
        edit.putInt("s-i-" + str, ((Integer) this.f1299p.getTag()).intValue());
        edit.putBoolean("s-o-" + str, this.f1297n.getVisibility() == 0);
        edit.apply();
    }
}
