package ru.fourpda.client;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

public class FilterAdapter extends BaseAdapter implements Filterable, AdapterView.OnItemClickListener {
    List<String> usersName = new Vector();
    List<Integer> usersId = new Vector();
    Context f1709c;
    Widgets$MemberView f1710d;

    public class C0458a extends Filter {
        FilterAdapter f1711a;

        public C0458a(FilterAdapter k1Var) {
            this.f1711a = k1Var;
        }

        @Override
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.count = 0;
            if (charSequence != null) {
                String charSequence2 = charSequence.toString();
                if (!TextUtils.isEmpty(charSequence2)) {
                    SearchUsersRequest bVar = new SearchUsersRequest(FilterAdapter.this, this.f1711a, charSequence2);
                    if (DocumentManager.getResultRequest(bVar) > 0) {
                        bVar.f1714l.m658b(20000);
                        filterResults.count = this.f1711a.usersId.size();
                    }
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            if (filterResults == null || filterResults.count <= 0) {
                this.f1711a.notifyDataSetInvalidated();
                return;
            }
            String obj = this.f1711a.f1710d.getText().toString();
            if (this.f1711a.usersName.contains(obj)) {
                FilterAdapter k1Var = this.f1711a;
                k1Var.f1710d.m845a(k1Var.usersId.get(k1Var.usersName.indexOf(obj)), obj, false);
            }
            this.f1711a.notifyDataSetChanged();
        }
    }

    class SearchUsersRequest extends API.SearchGetUsersRequest {
        FilterAdapter filterAdapter;
        public Util.C0419a f1714l = new Util.C0419a(false);

        public SearchUsersRequest(FilterAdapter k1Var, FilterAdapter filterAdapter, String term) {
            super(0, term, 0, 30);
            this.filterAdapter = filterAdapter;
        }

        @Override
        public void getResult(int status, Document uVar) {
            this.filterAdapter.usersId.clear();
            this.filterAdapter.usersName.clear();
            if (status == 0) {
                Document l = uVar.getDocument(1);
                if (uVar.getInt(0) > 0 && l != null) {
                    for (int i2 = 0; i2 < l.count(); i2++) {
                        Document l2 = l.getDocument(i2);
                        this.filterAdapter.usersId.add(l2.getInt(0));
                        this.filterAdapter.usersName.add(Util.C0427h.UnEscapeString(l2.getString(1)));
                    }
                }
            }
            this.f1714l.m659a();
        }
    }

    public FilterAdapter(Context context, Widgets$MemberView widgets$MemberView) {
        this.f1709c = context;
        this.f1710d = widgets$MemberView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public int getCount() {
        return this.usersId.size();
    }

    @Override
    public Filter getFilter() {
        return new C0458a(this);
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f1709c).inflate(R.layout.spinner_item, viewGroup, false);
        }
        TextView textView = (TextView) view;
        textView.setText(this.usersName.get(i));
        textView.setTag(this.usersId.get(i));
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.usersId.size() > 0;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f1710d.m845a(this.usersId.get(i), this.usersName.get(i), true);
    }
}
