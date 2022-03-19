package ru.fourpda.client;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;

public class Page_Ticket extends Page implements BBDisplay.IBBDisplayCallback {
    private boolean f2886E = false;
    private int f2887F;
    private int f2888G;
    private String f2889H;
    int f2890I;
    int f2891J;
    int f2892K;
    String[] ticketStatus;
    int[] f2894M;
    int[] f2895N;
    List<C0867o> f2896O;
    int f2897P;
    Object f2898Q;

    class View$OnClickListenerC0849a implements View.OnClickListener {

        class View$OnClickListenerC0850a implements View.OnClickListener {
            final DlgSimple f2900a;
            final C0867o f2901b;

            View$OnClickListenerC0850a(DlgSimple q1Var, C0867o oVar) {
                this.f2900a = q1Var;
                this.f2901b = oVar;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f2900a.editText.getText().toString())) {
                    Page_Ticket w0Var = Page_Ticket.this;
                    DocumentManager.getResultRequest(new TicketEditCommentRequest(w0Var, w0Var.mainActivity, this.f2901b, this.f2900a.editText.getText().toString(), 0));
                }
            }
        }

        View$OnClickListenerC0849a() {
        }

        @Override
        public void onClick(View view) {
            DlgSimple q1Var = new DlgSimple(Page_Ticket.this.mainActivity, "Введите комментарий", false, "ОТПРАВИТЬ", null);
            q1Var.editText.setSingleLine(false);
            q1Var.editText.setMinLines(1);
            q1Var.editText.setMaxLines(7);
            q1Var.m620f(new View$OnClickListenerC0850a(q1Var, Page_Ticket.this.f2896O.get(((Integer) view.getTag()).intValue())), true);
            q1Var.show(true, true, true);
        }
    }

    public class View$OnClickListenerC0851b implements View.OnClickListener {
        final RelativeLayout f2903a;

        View$OnClickListenerC0851b(RelativeLayout relativeLayout) {
            this.f2903a = relativeLayout;
        }

        @Override
        public void onClick(View view) {
            Prefs.ticketPerPage = Math.min(100, Prefs.ticketPerPage + 1);
            Page_Ticket.this.m191d0(true, this.f2903a);
        }
    }

    class View$OnClickListenerC0852c implements View.OnClickListener {
        View$OnClickListenerC0852c() {
        }

        @Override
        public void onClick(View view) {
            C0867o oVar = Page_Ticket.this.f2896O.get(((Integer) view.getTag()).intValue());
            if (oVar.f2937a) {
                oVar.f2937a = false;
                Page_Ticket.this.m190e0();
                Page_Ticket.this.tabLoaded(true);
            } else if (oVar.f2939c) {
                oVar.f2937a = true;
                Page_Ticket.this.m190e0();
                Page_Ticket.this.tabLoaded(true);
            } else if (!oVar.f2938b) {
                oVar.f2938b = true;
                Page_Ticket w0Var = Page_Ticket.this;
                DocumentManager.getResultRequest(new TicketLoadCommentsRequest(w0Var, w0Var.mainActivity, oVar));
            }
        }
    }

    class View$OnLongClickListenerC0853d implements View.OnLongClickListener {
        View$OnLongClickListenerC0853d() {
        }

        @Override
        public boolean onLongClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            MainActivity mainActivity = Page_Ticket.this.mainActivity;
            Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?act=ticket&s=thread&t_id=" + Page_Ticket.this.f2896O.get(intValue).document.getInt(0), "Ссылка на тикет скопирована");
            return true;
        }
    }

    class View$OnClickListenerC0854e implements View.OnClickListener {
        View$OnClickListenerC0854e() {
        }

        @Override
        public void onClick(View view) {
            Page_Ticket w0Var = Page_Ticket.this;
            DocumentManager.getResultRequest(new TicketHistoryRequest(w0Var, w0Var.mainActivity, Page_Ticket.this.f2896O.get(((Integer) view.getTag()).intValue())));
        }
    }

    class View$OnClickListenerC0855f implements View.OnClickListener {

        class C0856a implements OptionPoupupMenuView.IClickListener {
            final View f2909a;

            C0856a(View view) {
                this.f2909a = view;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                Page_Ticket w0Var = Page_Ticket.this;
                DocumentManager.getResultRequest(new TicketChangeStatusRequest(w0Var, w0Var.mainActivity, w0Var.f2896O.get((Integer) this.f2909a.getTag()), i3));
            }
        }

        View$OnClickListenerC0855f() {
        }

        @Override
        public void onClick(View view) {
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Ticket.this.mainActivity, new C0856a(view));
            int i = 0;
            while (true) {
                String[] ticketStatus = Page_Ticket.this.ticketStatus;
                if (i < ticketStatus.length) {
                    o1Var.addMenuItem(0, 0, i, ticketStatus[i]);
                    i++;
                } else {
                    o1Var.show(null);
                    return;
                }
            }
        }
    }

    class View$OnClickListenerC0857g implements View.OnClickListener {
        View$OnClickListenerC0857g() {
        }

        @Override
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (intValue > 0) {
                Tab f1Var = new Tab(Page_Ticket.this.mainActivity);
                f1Var.addPage(new Page_Profile(Page_Ticket.this.mainActivity, intValue, 0));
                Page_Ticket.this.tab.mainLayout.setCurrentTab(f1Var);
            }
        }
    }

    class C0858h implements OptionPoupupMenuView.IClickListener {

        class View$OnClickListenerC0859a implements View.OnClickListener {
            final DlgSimple f2913a;
            final C0867o f2914b;
            final Document f2915c;

            View$OnClickListenerC0859a(DlgSimple q1Var, C0867o oVar, Document uVar) {
                this.f2913a = q1Var;
                this.f2914b = oVar;
                this.f2915c = uVar;
            }

            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.f2913a.editText.getText().toString())) {
                    Page_Ticket w0Var = Page_Ticket.this;
                    DocumentManager.getResultRequest(new TicketEditCommentRequest(w0Var, w0Var.mainActivity, this.f2914b, this.f2913a.editText.getText().toString(), this.f2915c.getInt(0).intValue()));
                }
            }
        }

        C0858h() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (Page_Ticket.this.isUnsucces()) {
                C0867o oVar = Page_Ticket.this.f2896O.get(i);
                Document uVar = (Document) oVar.f2943g.get(i2).f2221a0;
                String n = uVar.getString(4);
                int indexOf = n.indexOf("[na]");
                if (indexOf >= 0) {
                    n = n.substring(indexOf + 4);
                }
                if (i3 == 0) {
                    Page_Ticket w0Var = Page_Ticket.this;
                    DocumentManager.getResultRequest(new TicketDeleteCommentRequest(w0Var, w0Var.mainActivity, oVar, uVar.getInt(0).intValue()));
                } else if (i3 == 1) {
                    DlgSimple q1Var = new DlgSimple(Page_Ticket.this.mainActivity, "Редактирование комментария", false, "ОТПРАВИТЬ", null);
                    q1Var.editText.setSingleLine(false);
                    q1Var.editText.setMinLines(1);
                    q1Var.editText.setMaxLines(7);
                    q1Var.editText.setText(n);
                    q1Var.m620f(new View$OnClickListenerC0859a(q1Var, oVar, uVar), true);
                    q1Var.show(true, true, true);
                } else if (i3 == 2) {
                    Util.copyToClipboard(Page_Ticket.this.mainActivity, n, "Комментарий скопирован в буфер обмена");
                }
            }
        }
    }

    class C0860i implements OptionPoupupMenuView.IClickListener {
        C0860i() {
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            Page_Ticket.this.m186i0(i, i2, i3);
        }
    }

    public class View$OnClickListenerC0861j implements View.OnClickListener {
        final RelativeLayout f2918a;

        View$OnClickListenerC0861j(RelativeLayout relativeLayout) {
            this.f2918a = relativeLayout;
        }

        @Override
        public void onClick(View view) {
            Prefs.ticketPerPage = Math.max(10, Prefs.ticketPerPage - 1);
            Page_Ticket.this.m191d0(true, this.f2918a);
        }
    }

    class TicketDeleteCommentRequest extends API.TicketDeleteRequest {
        Page_Ticket f2920i;
        MainActivity f2921j;
        C0867o f2922k;

        TicketDeleteCommentRequest(Page_Ticket w0Var, MainActivity mainActivity, C0867o oVar, int i) {
            super(oVar.document.getInt(0), i);
            this.f2920i = w0Var;
            this.f2921j = mainActivity;
            this.f2922k = oVar;
            this.statusMessage = "Удаление комментария";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Ticket w0Var = Page_Ticket.this;
            if (!w0Var.isLoading) {
                if (status == 0) {
                    C0867o oVar = this.f2922k;
                    oVar.f2938b = true;
                    DocumentManager.getResultRequest(new TicketLoadCommentsRequest(this.f2920i, this.f2921j, oVar));
                    return;
                }
                Toast.makeText(this.f2921j, "Ошибка удаления", 0).show();
            }
        }
    }

    class TicketLoadCommentsRequest extends API.TicketContentRequest {
        Page_Ticket f2924h;
        MainActivity f2925i;
        C0867o f2926j;

        TicketLoadCommentsRequest(Page_Ticket w0Var, MainActivity mainActivity, C0867o oVar) {
            super(oVar.document.getInt(0).intValue());
            this.f2924h = w0Var;
            this.f2925i = mainActivity;
            this.f2926j = oVar;
            this.statusMessage = "загрузка комментариев";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (!Page_Ticket.this.isLoading) {
                if (status == 0) {
                    Document l = uVar.getDocument(11);
                    C0867o oVar = this.f2926j;
                    if (oVar.f2943g == null) {
                        oVar.f2943g = new Vector(l.count());
                    }
                    C0867o oVar2 = this.f2926j;
                    if (oVar2.f2939c) {
                        oVar2.f2943g.clear();
                    }
                    for (int i2 = 0; i2 < l.count(); i2++) {
                        Document l2 = l.getDocument(i2);
                        StringBuilder sb = new StringBuilder();
                        sb.append(!this.f2926j.document.getInt(7).equals(l2.getInt(2)) ? "[url=https://4pda.ru/forum/index.php?showuser=" + l2.getInt(2) + "]" + Util.C0427h.UnEscapeString(l2.getString(3)) + "[/url]\n" : "");
                        sb.append(l2.getString(4));
                        sb.append("\n[right][sub]");
                        sb.append(Util.formatDate(l2.getInt(1).intValue()));
                        sb.append("[/sub][/right]");
                        BBString x = BBString.getBBString(sb.toString(), null);
                        if (x != null) {
                            BBString.C0674e eVar = x.f2246z;
                            int i3 = (int) (this.f2925i.f731b * 16.0f);
                            eVar.f2266j = i3;
                            eVar.f2265i = i3;
                            float f = (float) i3;
                            eVar.f2264h = f;
                            eVar.f2263g = f;
                            x.f2221a0 = l2;
                            x.f2218Y = this.f2924h.f2896O.indexOf(this.f2926j);
                            x.f2219Z = this.f2926j.f2943g.size();
                            this.f2926j.f2943g.add(x);
                        }
                    }
                    C0867o oVar3 = this.f2926j;
                    oVar3.f2939c = true;
                    oVar3.f2937a = true;
                    this.f2924h.m190e0();
                    this.f2924h.tabLoaded(true);
                    return;
                }
                Toast.makeText(this.f2925i, "Ошибка получения коментариев", 0).show();
            }
        }
    }

    class TicketHistoryRequest extends API.TicketHistoryRequest {
        Page_Ticket f2928h;
        MainActivity f2929i;

        class View$OnClickListenerC0865a implements View.OnClickListener {
            final Dialog f2931a;

            View$OnClickListenerC0865a(Dialog dialog) {
                this.f2931a = dialog;
            }

            @Override
            public void onClick(View view) {
                this.f2931a.dismiss();
                Tab f1Var = new Tab(TicketHistoryRequest.this.f2929i);
                f1Var.addPage(new Page_Profile(TicketHistoryRequest.this.f2929i, (Integer) view.getTag(), 0));
                TicketHistoryRequest.this.f2929i.mainLayout.setCurrentTab(f1Var);
            }
        }

        TicketHistoryRequest(Page_Ticket w0Var, MainActivity mainActivity, C0867o oVar) {
            super(oVar.document.getInt(0));
            this.f2928h = w0Var;
            this.f2929i = mainActivity;
            this.statusMessage = "История тикета";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (!Page_Ticket.this.isLoading) {
                if (status == 0) {
                    Dialog dialog = new Dialog(this.f2929i, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
                    dialog.requestWindowFeature(1);
                    dialog.setContentView(R.layout.dlg_ticket_history);
                    dialog.getWindow().setBackgroundDrawable(this.f2929i.skin.getSkinDrawable(R.drawable.np_dialog));
                    dialog.getWindow().setLayout(-1, -2);
                    dialog.setCanceledOnTouchOutside(true);
                    TableLayout tableLayout = (TableLayout) dialog.findViewById(R.id.ticket_history_table);
                    Document l = uVar.getDocument(0);
                    float f = this.f2929i.f731b;
                    for (int i2 = 0; i2 < l.count(); i2++) {
                        Document l2 = l.getDocument(i2);
                        TableRow tableRow = new TableRow(this.f2929i);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                        if (i2 < l.count() - 1) {
                            tableRow.setBackgroundDrawable(this.f2929i.skin.getSkinDrawable(R.drawable.border_bottom));
                        }
                        TextView textView = new TextView(this.f2929i);
                        textView.setTextSize(14.0f);
                        textView.setText(Util.formatDate(l2.getInt(1).intValue()));
                        int i3 = (int) (8.0f * f);
                        textView.setPadding(i3, i3, i3, i3);
                        tableRow.addView(textView);
                        TextView textView2 = new TextView(this.f2929i);
                        textView2.setTextSize(16.0f);
                        textView2.setText(this.f2928h.ticketStatus[l2.getInt(0)]);
                        textView2.setPadding(i3, i3, i3, i3);
                        tableRow.addView(textView2);
                        TextView textView3 = new TextView(this.f2929i);
                        textView3.setTextSize(16.0f);
                        textView3.setText(Util.C0427h.UnEscapeString(l2.getString(3)));
                        textView3.setTextColor(Skin.SkinColorModel.linkColor);
                        textView3.setTag(l2.getInt(2));
                        textView3.setOnClickListener(new View$OnClickListenerC0865a(dialog));
                        textView3.setPadding(i3, i3, i3, i3);
                        tableRow.addView(textView3);
                        tableLayout.addView(tableRow, i2);
                    }
                    dialog.show();
                    CustomDialog.m623c(dialog);
                    return;
                }
                Toast.makeText(this.f2929i, "Ошибка получения истории тикета", 0).show();
            }
        }
    }

    class TicketChangeStatusRequest extends API.TicketStatusRequest {
        MainActivity f2933j;
        C0867o f2934k;
        int ticketStatusCode;

        public TicketChangeStatusRequest(Page_Ticket w0Var, MainActivity mainActivity, C0867o oVar, int ticketStatusCode) {
            super(oVar.document.getInt(0), ticketStatusCode, oVar.document.getInt(9));
            this.f2933j = mainActivity;
            this.f2934k = oVar;
            this.ticketStatusCode = ticketStatusCode;
            this.statusMessage = "Изменение статуса";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (!Page_Ticket.this.isLoading) {
                if (status == 0 || status == 4) {
                    this.f2934k.f2942f = uVar.getInt(0);
                    if (status == 4) {
                        this.f2934k.document.addInt(9, uVar.getInt(1));
                        this.f2934k.document.addString(10, uVar.getString(2));
                        Toast.makeText(this.f2933j, "У тикета изменился ответственный, попробуйте еще раз", 0).show();
                    } else {
                        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
                        this.f2934k.document.addInt(9, L.memberId);
                        this.f2934k.document.addString(10, L.memberInfoName);
                        if (this.ticketStatusCode == 2) {
                            this.f2934k.document.addInt(6, (int) (System.currentTimeMillis() / 1000));
                            C0867o oVar = this.f2934k;
                            oVar.f2941e = Util.formatDate(oVar.document.getInt(6));
                        }
                        if (this.ticketStatusCode == 0) {
                            this.f2934k.document.addInt(9, 0);
                        }
                        Toast.makeText(this.f2933j, "Статус изменен", 0).show();
                    }
                    Page_Ticket.this.tabLoaded(true);
                    return;
                }
                Toast.makeText(this.f2933j, "Невозможно сменить статус тикета", 0).show();
            }
        }
    }

    public class C0867o {
        public boolean f2937a;
        public boolean f2938b;
        public boolean f2939c;
        public String f2940d;
        public String f2941e;
        public int f2942f;
        public List<BBString> f2943g;
        public Document document;

        C0867o(Page_Ticket w0Var) {
        }
    }

    class TicketEditCommentRequest extends API.TicketMessageRequest {
        Page_Ticket f2945j;
        MainActivity f2946k;
        C0867o f2947l;

        TicketEditCommentRequest(Page_Ticket w0Var, MainActivity mainActivity, C0867o oVar, String str, int i) {
            super(oVar.document.getInt(0), str, i);
            this.f2945j = w0Var;
            this.f2946k = mainActivity;
            this.f2947l = oVar;
            this.statusMessage = "Отправка комментария";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Ticket w0Var = Page_Ticket.this;
            if (!w0Var.isLoading) {
                if (status == 0) {
                    C0867o oVar = this.f2947l;
                    oVar.f2938b = true;
                    DocumentManager.getResultRequest(new TicketLoadCommentsRequest(this.f2945j, this.f2946k, oVar));
                    return;
                }
                Toast.makeText(this.f2946k, "Ошибка редактирования коментария", 0).show();
            }
        }
    }

    public Page_Ticket(MainActivity mainActivity, int i, int i2, String str) {
        super(mainActivity, 27764, m188g0(false, i, i2));
        m187h0(0, i, i2, str);
    }

    private static Document m188g0(boolean z, int i, int i2) {
        int i3 = 4;
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(Prefs.ticketPerPage);
        if (!z) {
            i3 = 0;
        }
        objArr[2] = Integer.valueOf((Prefs.ticketForumSort ? 1 : 0) | i3);
        objArr[3] = new Document(Integer.valueOf(i2));
        return new Document(objArr);
    }

    private void m187h0(int i, int i2, int i3, String str) {
        String str2;
        String str3;
        if (i > 0) {
            this.f2887F = i;
            this.title = "Тикет " + this.f2887F;
            this.f2890I = 0;
            this.statusMessage = "Загрузка тикета";
        } else {
            this.f2887F = 0;
            this.f2888G = i3;
            this.f2889H = str;
            this.f2890I = i2;
            if (i3 <= 0) {
                str2 = "Тикеты";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Тикеты темы");
                if (TextUtils.isEmpty(str)) {
                    str3 = "";
                } else {
                    str3 = " \"" + str + "\"";
                }
                sb.append(str3);
                str2 = sb.toString();
            }
            this.title = str2;
            this.statusMessage = "Загрузка тикетов";
        }
        Resources resources = this.mainActivity.getResources();
        String[] strArr = new String[3];
        this.ticketStatus = strArr;
        strArr[0] = resources.getString(R.string.ticket_status_not_processed);
        this.ticketStatus[1] = resources.getString(R.string.ticket_status_processing);
        this.ticketStatus[2] = resources.getString(R.string.ticket_status_processed);
        int[] iArr = new int[3];
        this.f2894M = iArr;
        iArr[0] = this.mainActivity.skin.getSkinColor(R.color.ticket_new);
        this.f2894M[1] = this.mainActivity.skin.getSkinColor(R.color.ticket_work);
        this.f2894M[2] = this.mainActivity.skin.getSkinColor(R.color.ticket_done);
        int[] iArr2 = new int[3];
        this.f2895N = iArr2;
        iArr2[0] = this.mainActivity.skin.getSkinColor(R.color.ticket_new_text);
        this.f2895N[1] = this.mainActivity.skin.getSkinColor(R.color.ticket_work_text);
        this.f2895N[2] = this.mainActivity.skin.getSkinColor(R.color.ticket_done_text);
        this.f2892K = this.mainActivity.skin.getSkinColor(R.color.ticket_count);
    }

    @Override
    public void mo192E() {
        if (this.f2887F == 0) {
            this.f2886E = false;
            this.rootDocument = m188g0(false, this.f2890I, this.f2888G);
        }
        super.mo192E();
    }

    @Override
    public void onSearchBar() {
        this.tab.mainLayout.findViewById(R.id.bar_search).getLayoutParams().width = (int) (this.mainActivity.f731b * 42.0f);
        super.onSearchBar();
    }

    @Override
    protected boolean onPageLoadedForum() {
        String str;
        int i = 0;
        if (this.isLoading) {
            return false;
        }
        Vector vector = new Vector(this.currentDocument.count());
        if (this.f2887F > 0) {
            this.f2891J = 1;
            C0867o oVar = new C0867o(this);
            Document uVar = this.currentDocument;
            oVar.document = uVar;
            uVar.addString(1, Util.C0427h.UnEscapeString(uVar.getString(1)));
            Document uVar2 = oVar.document;
            uVar2.addString(3, Util.C0427h.UnEscapeString(uVar2.getString(3)));
            Document uVar3 = oVar.document;
            uVar3.addString(7, Util.C0427h.UnEscapeString(uVar3.getString(7)));
            Document uVar4 = oVar.document;
            uVar4.addString(9, Util.C0427h.UnEscapeString(uVar4.getString(9)));
            oVar.f2940d = Util.formatDate(oVar.document.getInt(4).intValue());
            oVar.f2941e = Util.formatDate(oVar.document.getInt(5).intValue());
            oVar.f2942f = oVar.document.getInt(0).intValue();
            vector.add(oVar);
            Document l = this.currentDocument.getDocument(11);
            oVar.f2943g = new Vector(l.count());
            while (i < l.count()) {
                Document l2 = l.getDocument(i);
                StringBuilder sb = new StringBuilder();
                if (!oVar.document.getInt(6).equals(l2.getInt(2))) {
                    str = "[url=https://4pda.ru/forum/index.php?showuser=" + l2.getInt(2) + "]" + Util.C0427h.UnEscapeString(l2.getString(3)) + "[/url]\n";
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(l2.getString(4));
                sb.append("\n[right][sub]");
                sb.append(Util.formatDate(l2.getInt(1).intValue()));
                sb.append("[/sub][/right]");
                BBString x = BBString.getBBString(sb.toString(), null);
                if (x != null) {
                    BBString.C0674e eVar = x.f2246z;
                    int i2 = (int) (this.mainActivity.f731b * 16.0f);
                    eVar.f2266j = i2;
                    eVar.f2265i = i2;
                    float f = (float) i2;
                    eVar.f2264h = f;
                    eVar.f2263g = f;
                    x.f2221a0 = l2;
                    x.f2218Y = vector.indexOf(oVar);
                    x.f2219Z = oVar.f2943g.size();
                    oVar.f2943g.add(x);
                }
                i++;
            }
            this.currentDocument.prepend(Integer.valueOf(this.f2887F));
            oVar.f2939c = true;
            oVar.f2937a = true;
        } else {
            this.f2891J = this.currentDocument.getInt(0).intValue();
            this.currentDocument = this.currentDocument.getDocument(1);
            while (i < this.currentDocument.count()) {
                C0867o oVar2 = new C0867o(this);
                Document l3 = this.currentDocument.getDocument(i);
                oVar2.document = l3;
                l3.addString(2, Util.C0427h.UnEscapeString(l3.getString(2)));
                Document uVar5 = oVar2.document;
                uVar5.addString(4, Util.C0427h.UnEscapeString(uVar5.getString(4)));
                Document uVar6 = oVar2.document;
                uVar6.addString(8, Util.C0427h.UnEscapeString(uVar6.getString(8)));
                Document uVar7 = oVar2.document;
                uVar7.addString(10, Util.C0427h.UnEscapeString(uVar7.getString(10)));
                oVar2.f2940d = Util.formatDate(oVar2.document.getInt(5).intValue());
                oVar2.f2941e = Util.formatDate(oVar2.document.getInt(6).intValue());
                oVar2.f2942f = oVar2.document.getInt(1).intValue();
                vector.add(oVar2);
                i++;
            }
        }
        this.f2896O = vector;
        m190e0();
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0860i());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 1, "Обновить");
        }
        o1Var.addMenuItem(0, 0, 2, "В закладки", DataDB.m366k(getLink()));
        o1Var.addMenuItem(0, 0, 5, "Копировать ссылку");
        if (this.f2887F <= 0) {
            o1Var.addMenuItem(0, 0, 6, "Только мои", this.f2886E);
            o1Var.addMenuItem(0, 0, 3, "Сортировка по форумам", Prefs.ticketForumSort);
            o1Var.addMenuItem(0, 0, 4, "Тикетов на странице: " + Prefs.ticketPerPage);
        }
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
    int mo141P() {
        return ((this.f2891J - 1) / Prefs.ticketPerPage) + 1;
    }

    @Override
    int mo140Q() {
        return (this.f2890I / Prefs.ticketPerPage) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_Ticket(this.mainActivity, (i - 1) * Prefs.ticketPerPage, this.f2888G, this.f2889H);
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        Util.copyToClipboard(this.mainActivity, str, "Текст скопирован в буфер");
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = pVar.f2218Y;
        int i2 = pVar.f2219Z;
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0858h(), true);
        Document uVar = (Document) this.f2896O.get(i).f2943g.get(i2).f2221a0;
        DocumentManager.MemberInfoModel L = DocumentManager.getMemberInfoModel();
        o1Var.addMenuItem(i, i2, 2, "Копировать");
        if (L != null && uVar.getInt(2).intValue() == L.memberId) {
            o1Var.addMenuItem(i, i2, 0, "Удалить");
            o1Var.addMenuItem(i, i2, 1, "Редактировать");
        }
        o1Var.show(null);
    }

    void m191d0(boolean z, RelativeLayout relativeLayout) {
        ((TextView) relativeLayout.findViewById(R.id.option_value)).setText(Integer.valueOf(Prefs.ticketPerPage).toString());
        boolean z2 = true;
        relativeLayout.findViewById(R.id.option_down).setEnabled(Prefs.ticketPerPage > 10);
        View findViewById = relativeLayout.findViewById(R.id.option_up);
        if (Prefs.ticketPerPage >= 100) {
            z2 = false;
        }
        findViewById.setEnabled(z2);
        if (z) {
            Prefs.saveInt(this.mainActivity, "ticket_per_page", Prefs.ticketPerPage);
        }
    }

    void m190e0() {
        int i;
        float f = 0;
        int f0 = m189f0(0, true);
        this.f2898Q = null;
        this.f1071B = new int[f0];
        MainActivity mainActivity = this.mainActivity;
        float f2 = mainActivity.f731b;
        float f3 = mainActivity.f732c;
        int width = mainActivity.mainLayout.getWidth() - ((int) (32.0f * f2));
        int i2 = 0;
        for (int i3 = 0; i3 < f0; i3++) {
            int f02 = m189f0(i3, false);
            Object obj = this.f2898Q;
            this.f2898Q = null;
            if (f02 == 0 || f02 == 6) {
                i = Page.f1069D;
            } else if (f02 == 1 || f02 == 4) {
                i = (int) (16.0f * f2);
            } else {
                if (f02 == 2) {
                    C0867o oVar = (C0867o) obj;
                    f = ((float) (Util.m672b(oVar.document.getString(2), width, 16.0f * f3, false) + (Util.m672b(oVar.document.getString(4), width, 14.0f * f3, false) * 2))) + (42.0f * f2);
                } else if (f02 == 3) {
                    MainActivity mainActivity2 = this.mainActivity;
                    i = Util.m663k(mainActivity2, (BBString) obj, mainActivity2.mainLayout.getWidth());
                } else if (f02 == 5) {
                    f = 84.0f * f2;
                } else {
                    i = 0;
                }
                i = (int) f;
            }
            i2 += i;
            this.f1071B[i3] = i2;
        }
    }

    @Override
    public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        int i = cVar.f543a;
        if (i >= 0) {
            BBString.C0681k kVar = pVar.f2202I.get(i);
            int i2 = kVar.statusCode;
            if (i2 == 1) {
                Page b = Urls2.openPage(this.mainActivity, kVar.link, false, 2);
                if (b != null) {
                    Tab f1Var = new Tab(this.mainActivity);
                    f1Var.addPage(b);
                    this.mainActivity.mainLayout.setCurrentTab(f1Var);
                }
            } else if (i2 == 2) {
                Util.sendMail(this.mainActivity, kVar.link, this.title);
            }
        }
    }

    int m189f0(int i, boolean z) {
        int i2;
        this.f2898Q = null;
        this.f2897P = -1;
        boolean T = m816T();
        if (!T) {
            i2 = 0;
        } else if (i == 0 && !z) {
            return 0;
        } else {
            i2 = 1;
        }
        int i3 = i2 + 1;
        if (i == i2 && !z) {
            return 1;
        }
        int size = this.f2896O.size();
        for (int i4 = 0; i4 < size; i4++) {
            C0867o oVar = this.f2896O.get(i4);
            if (!oVar.f2937a) {
                int i5 = i3 + 1;
                if (i != i3 || z) {
                    i3 = i5 + 1;
                    if (i == i5 && !z) {
                        return 4;
                    }
                } else {
                    this.f2898Q = oVar;
                    this.f2897P = i4;
                    return 2;
                }
            } else {
                int i6 = i3 + 1;
                if (i != i3 || z) {
                    if (oVar.f2939c) {
                        int size2 = oVar.f2943g.size() + i6;
                        if (size2 <= i || z) {
                            i6 = size2;
                        } else {
                            int i7 = i - i6;
                            this.f2897P = i7;
                            this.f2898Q = oVar.f2943g.get(i7);
                            return 3;
                        }
                    }
                    i3 = i6 + 1;
                    if (i == i6 && !z) {
                        this.f2898Q = oVar;
                        this.f2897P = i4;
                        return 5;
                    }
                } else {
                    this.f2898Q = oVar;
                    this.f2897P = i4;
                    return 2;
                }
            }
        }
        if (!T) {
            return i3;
        }
        int i8 = i3 + 1;
        if (i != i3 || z) {
            return i8;
        }
        return 6;
    }

    @Override
    public int getCount() {
        Document uVar;
        if (!isUnsucces() || (uVar = this.currentDocument) == null || uVar.count() < 1) {
            return 0;
        }
        int f0 = m189f0(0, true);
        this.f2898Q = null;
        return f0;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        int f0 = m189f0(i, false);
        this.f2898Q = null;
        return f0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BBDisplay bBDisplay = null;
        int f0 = m189f0(i, false);
        Object obj = this.f2898Q;
        this.f2898Q = null;
        if (view == null) {
            if (f0 == 1) {
                View view2 = new View(this.mainActivity);
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
                view2.setBackgroundDrawable(Skin.SkinColorModel.f1388i0.getConstantState().newDrawable());
                bBDisplay = (BBDisplay) view2;
            } else if (f0 == 4) {
                View view3 = new View(this.mainActivity);
                view3.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (this.mainActivity.f731b * 16.0f)));
                view3.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.card_sep));
                bBDisplay = (BBDisplay) view3;
            } else if (f0 == 5) {
                View inflate = this.mainActivity.getLayoutInflater().inflate(R.layout.ticket_button_sep, viewGroup, false);
                inflate.findViewById(R.id.ticket_comment_button).setOnClickListener(new View$OnClickListenerC0849a());
                bBDisplay = (BBDisplay) inflate;
            } else if (f0 == 0) {
                bBDisplay = (BBDisplay) m817S(viewGroup, false);
            } else if (f0 == 6) {
                bBDisplay = (BBDisplay) m817S(viewGroup, true);
            } else if (f0 == 2) {
                View inflate2 = this.mainActivity.getLayoutInflater().inflate(R.layout.ticket, viewGroup, false);
                inflate2.findViewById(R.id.ticket_title).setOnClickListener(new View$OnClickListenerC0852c());
                inflate2.findViewById(R.id.ticket_title).setOnLongClickListener(new View$OnLongClickListenerC0853d());
                inflate2.findViewById(R.id.ticket_date).setOnClickListener(new View$OnClickListenerC0854e());
                inflate2.findViewById(R.id.ticket_handler).setOnClickListener(new View$OnClickListenerC0855f());
                inflate2.findViewById(R.id.ticket_member).setOnClickListener(new View$OnClickListenerC0857g());
                bBDisplay = (BBDisplay) inflate2;
            } else if (f0 == 3) {
                BBDisplay bBDisplay2 = new BBDisplay(this.mainActivity);
                bBDisplay2.setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.border_top));
                bBDisplay2.setCallback(this);
                bBDisplay = bBDisplay2;
            }
            if (f0 != 2) {
                C0867o oVar = (C0867o) obj;
                TextView textView = (TextView) bBDisplay.findViewById(R.id.ticket_title);
                int intValue = oVar.document.getInt(14).intValue();
                if (intValue > 1) {
                    String str = "(" + intValue + ")";
                    SpannableString spannableString = new SpannableString(str + " " + oVar.document.getString(2));
                    spannableString.setSpan(new ForegroundColorSpan(this.f2892K), 0, str.length(), 0);
                    textView.setText(spannableString);
                } else {
                    textView.setText(oVar.document.getString(2));
                }
                textView.setTag(this.f2897P);
                bBDisplay.findViewById(R.id.ticket_date).setTag(this.f2897P);
                bBDisplay.findViewById(R.id.ticket_handler).setTag(this.f2897P);
                bBDisplay.findViewById(R.id.ticket_member).setTag(oVar.document.getInt(7));
                ((TextView) bBDisplay.findViewById(R.id.ticket_date)).setText(oVar.f2942f == 2 ? oVar.f2941e : oVar.f2940d);
                ((TextView) bBDisplay.findViewById(R.id.ticket_forum)).setText(oVar.document.getString(4));
                TextView textView2 = (TextView) bBDisplay.findViewById(R.id.ticket_handler);
                if (oVar.document.getInt(9) > 0) {
                    textView2.setText(oVar.document.getString(10));
                    textView2.setCompoundDrawablesWithIntrinsicBounds(this.mainActivity.skin.getSkinDrawable(R.drawable.ic_user), (Drawable) null, (Drawable) null, (Drawable) null);
                } else {
                    textView2.setText("новый");
                    textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                textView2.setBackgroundColor(this.f2894M[oVar.f2942f]);
                textView2.setTextColor(this.f2895N[oVar.f2942f]);
                ((TextView) bBDisplay.findViewById(R.id.ticket_member)).setText(oVar.document.getString(8));
            } else if (f0 == 3) {
                ((BBDisplay) bBDisplay).setBBString((BBString) obj);
            } else if (f0 == 5) {
                bBDisplay.findViewById(R.id.ticket_comment_button).setTag(this.f2897P);
            } else if (f0 == 6) {
                m815U();
            }
            return bBDisplay;
        }
        bBDisplay = (BBDisplay) view;
        if (f0 != 2) {
        }
        return bBDisplay;
    }

    @Override
    public int getViewTypeCount() {
        return 7;
    }

    public void m186i0(int i, int i2, int i3) {
        if (i3 == 1) {
            tabReload();
        } else if (i3 == 2) {
            DataDB.m365l(this.title, getLink());
        } else if (i3 == 5) {
            MainActivity mainActivity = this.mainActivity;
            Util.copyToClipboard(mainActivity, "https://4pda.ru/" + getLink(), "Ссылка скопирована");
        } else if (i3 == 6) {
            boolean z = true ^ this.f2886E;
            this.f2886E = z;
            this.rootDocument = m188g0(z, this.f2890I, this.f2888G);
            tabReload();
        } else if (i3 == 3) {
            MainActivity mainActivity2 = this.mainActivity;
            boolean z2 = true ^ Prefs.ticketForumSort;
            Prefs.ticketForumSort = z2;
            Prefs.saveBoolean(mainActivity2, "ticket_forum_sort", z2);
            this.rootDocument = m188g0(this.f2886E, this.f2890I, this.f2888G);
            tabReload();
        } else if (i3 == 4) {
            Dialog dialog = new Dialog(this.mainActivity, Skin.SkinColorModel.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
            RelativeLayout relativeLayout = (RelativeLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.option_up_down, (ViewGroup) this.mainActivity.mainLayout, false);
            relativeLayout.findViewById(R.id.option_down).setOnClickListener(new View$OnClickListenerC0861j(relativeLayout));
            relativeLayout.findViewById(R.id.option_up).setOnClickListener(new View$OnClickListenerC0851b(relativeLayout));
            ((TextView) relativeLayout.findViewById(R.id.option_caption)).setText("Тикетов на странице");
            ((TextView) relativeLayout.findViewById(R.id.option_label)).setText("Выбор количества тикетов на странице");
            relativeLayout.findViewById(R.id.option_button).setVisibility(8);
            m191d0(false, relativeLayout);
            dialog.requestWindowFeature(1);
            dialog.setContentView(relativeLayout);
            dialog.getWindow().setBackgroundDrawable(this.mainActivity.skin.getSkinDrawable(R.drawable.np_dialog));
            dialog.getWindow().setLayout((this.mainActivity.getResources().getDisplayMetrics().widthPixels * 6) / 7, -2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            CustomDialog.m623c(dialog);
        }
    }

    @Override
    public void destroyData() {
        super.destroyData();
        List<C0867o> list = this.f2896O;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (this.f2896O.get(i).f2939c) {
                    this.f2896O.get(i).f2943g.clear();
                }
            }
            this.f2896O.clear();
            this.f2896O = null;
        }
    }

    @Override
    public String getLink() {
        StringBuilder sb;
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("forum/index.php?act=ticket");
        if (this.f2887F > 0) {
            sb = new StringBuilder();
            sb.append("&s=thread&t_id=");
            sb.append(this.f2887F);
        } else {
            sb = new StringBuilder();
            String str2 = "";
            if (this.f2890I > 0) {
                str = "&st=" + this.f2890I;
            } else {
                str = str2;
            }
            sb.append(str);
            if (this.f2888G > 0) {
                str2 = "&only-topic=" + this.f2888G;
            }
            sb.append(str2);
        }
        sb2.append(sb.toString());
        return sb2.toString();
    }

    public Page_Ticket(MainActivity mainActivity, int i) {
        super(mainActivity, 25460, new Document(i));
        m187h0(i, 0, 0, null);
    }
}
