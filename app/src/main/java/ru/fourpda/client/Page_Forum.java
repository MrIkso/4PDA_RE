package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Vector;

public class Page_Forum extends Page implements BBDisplay.IBBDisplayCallback {
    private static Vector f1507b0;
    public static int f1508c0;
    public static int f1509d0;
    public static float f1510e0;
    public static float f1511f0;
    int forumNumber;
    int pageNumber;
    int f1514G;
    Document f1515H;
    Document f1516I;
    Document f1517J;
    int f1518K;
    int f1519L;
    int f1520M;
    int f1521N;
    BBString f1522O;
    View$OnClickListenerC0383e f1523P;
    View$OnClickListenerC0384f f1524Q;
    View$OnClickListenerC0390i f1525R;
    View$OnClickListenerC0388g f1526S;
    boolean f1527T;
    Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> f1528U = new C0379b();
    Util.AbstractC0431l<Boolean, Integer, Integer, Object> f1529V = new C0380c();
    private String f1530W;
    private String f1531X;
    private Form_Wizard.C0883n f1532Y;
    Form_Wizard f1533Z;
    boolean f1534a0;

    class View$OnClickListenerC0378a implements View.OnClickListener {
        View$OnClickListenerC0378a() {
            //Page_Forum.this = r1;
        }

        @Override
        public void onClick(View view) {
            Page_Forum.f1507b0.add(Integer.valueOf(Page_Forum.this.forumNumber));
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Page_Forum.this.mainActivity).edit();
            edit.putInt("forum_rules_count", Page_Forum.f1507b0.size());
            for (int i = 0; i < Page_Forum.f1507b0.size(); i++) {
                edit.putInt("forum_rule_" + i, (Integer) Page_Forum.f1507b0.get(i));
            }
            edit.commit();
            Page_Forum g0Var = Page_Forum.this;
            g0Var.f1522O = null;
            g0Var.tabLoaded(true);
        }
    }

    public class C0379b implements Util.AbstractC0430k<Boolean, DataDB.C0738c, DataDB.C0738c> {
        C0379b() {
           // Page_Forum.this = r1;
        }

        public Boolean mo103a(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
            int i = cVar2.f2511a;
            if (2 == i) {
                int i2 = cVar2.f2512b;
                Page_Forum g0Var = Page_Forum.this;
                if (i2 == g0Var.forumNumber && g0Var.pageNumber == 0) {
                    if (Prefs.f1141B) {
                        g0Var.f1527T = true;
                        if (g0Var.isCurrentTab() && Page_Forum.this.tab.m717i()) {
                            Page_Forum g0Var2 = Page_Forum.this;
                            if (!g0Var2.mainActivity.f733d) {
                                g0Var2.tabReload();
                            }
                        }
                    } else if (cVar2.f2513c) {
                        g0Var.m810c0("есть обновления", false);
                    }
                    return Boolean.FALSE;
                }
            }
            if (3 == i) {
                int i3 = 0;
                while (true) {
                    Page_Forum g0Var3 = Page_Forum.this;
                    if (i3 >= g0Var3.f1519L) {
                        break;
                    }
                    if (g0Var3.f1516I.getDocument(i3).getInt(0).intValue() == cVar2.f2512b) {
                        if (Prefs.f1141B) {
                            Page_Forum g0Var4 = Page_Forum.this;
                            g0Var4.f1527T = true;
                            if (g0Var4.isCurrentTab() && Page_Forum.this.tab.m717i()) {
                                Page_Forum g0Var5 = Page_Forum.this;
                                if (!g0Var5.mainActivity.f733d) {
                                    g0Var5.tabReload();
                                }
                            }
                        } else if (cVar2.f2513c) {
                            Page_Forum.this.m810c0("есть обновления", false);
                        }
                    }
                    i3++;
                }
            }
            return Boolean.FALSE;
        }
    }

    public class C0380c implements Util.AbstractC0431l<Boolean, Integer, Integer, Object> {
        C0380c() {
          //  Page_Forum.this = r1;
        }

        public Boolean mo101a(Integer num, Integer num2, Object obj) {
            if (obj == this) {
                return Boolean.FALSE;
            }
            if (num.intValue() == 0) {
                if (num2.intValue() == Page_Forum.this.forumNumber || num2.intValue() == -1) {
                    Page_Forum.this.f1527T = true;
                } else {
                    int i = 0;
                    while (true) {
                        Page_Forum g0Var = Page_Forum.this;
                        if (i >= g0Var.f1518K) {
                            break;
                        } else if (g0Var.f1515H.getDocument(i).getInt(0) == num2) {
                            Page_Forum.this.f1527T = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            } else if (num.intValue() == 1) {
                int i2 = 0;
                while (true) {
                    Page_Forum g0Var2 = Page_Forum.this;
                    if (i2 >= g0Var2.f1519L) {
                        break;
                    } else if (g0Var2.f1516I.getDocument(i2).getInt(0) == num2) {
                        Page_Forum.this.f1527T = true;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            return Boolean.TRUE;
        }
    }

    public class C0381d implements OptionPoupupMenuView.IClickListener {

        class RunnableC0382a implements Runnable {
            final boolean f1539a;

            RunnableC0382a(boolean z) {
              //  C0381d.this = r1;
                this.f1539a = z;
            }

            @Override
            public void run() {
                int intValue = Page_Forum.this.currentDocument.getInt(3) | 8;
                if (!this.f1539a) {
                    intValue ^= 8;
                }
                Page_Forum.this.currentDocument.addInt(3, intValue);
            }
        }

        C0381d() {
           // Page_Forum.this = r1;
        }

        @Override
        public void mo49a(int i, int i2, int i3) {
            if (i3 == 21) {
                Page_Forum.this.tabReload();
            } else if (i3 == 5) {
                MainActivity mainActivity = Page_Forum.this.mainActivity;
                Urls2.m676g(mainActivity, "https://4pda.ru/" + Page_Forum.this.getLink());
            } else if (i3 == 4) {
                DocumentManager.getResultRequest(new CreateForumTopicRequest());
            } else if (i3 == 3) {
                MainActivity mainActivity2 = Page_Forum.this.mainActivity;
                Util.copyToClipboard(mainActivity2, "https://4pda.ru/" + Page_Forum.this.getLink(), "Ссылка скопирована");
            } else if (i3 == 22) {
                Page_Forum g0Var = Page_Forum.this;
                DataDB.m365l(g0Var.title, g0Var.getLink());
            } else {
                boolean z = true;
                if (i3 == 1 || i3 == 2) {
                    if (i3 != 1) {
                        z = false;
                    }
                    Page_Forum g0Var2 = Page_Forum.this;
                    API.ForumModifyRequest.m821p(g0Var2.forumNumber, null, 21, 8, z ? 8 : 0, g0Var2, "обновление подписки", "", new RunnableC0382a(z));
                } else if (i3 == 6) {
                    Page_Forum.f1507b0.remove(Integer.valueOf(Page_Forum.this.forumNumber));
                    Page_Forum.this.m704e0();
                    Page_Forum.this.tabLoaded(true);
                } else if (i3 == 7) {
                    Page_Forum g0Var3 = Page_Forum.this;
                    DocumentManager.getResultRequest(new API.ForumGetTopicsRequest(g0Var3.forumNumber, g0Var3.mainActivity));
                }
            }
        }
    }

    public class View$OnClickListenerC0383e implements View.OnClickListener {
        Page_Forum f1541a;

        public View$OnClickListenerC0383e(Page_Forum g0Var, Page_Forum g0Var2) {
            this.f1541a = g0Var2;
        }

        @Override
        public void onClick(View view) {
            int intValue = (Integer) view.getTag();
            Page_Forum g0Var = this.f1541a;
            g0Var.tab.addPage(new Page_Announcement(g0Var.mainActivity, intValue));
        }
    }

    public class View$OnClickListenerC0384f implements View.OnClickListener, View.OnLongClickListener {
        Page_Forum f1542a;

        public class C0385a implements OptionPoupupMenuView.IClickListener {

            class RunnableC0386a implements Runnable {
                final Document f1545a;
                final boolean f1546b;

                RunnableC0386a(C0385a aVar, Document uVar, boolean z) {
                    this.f1545a = uVar;
                    this.f1546b = z;
                }

                @Override
                public void run() {
                    int intValue = this.f1545a.getInt(2) | 8;
                    if (!this.f1546b) {
                        intValue ^= 8;
                    }
                    this.f1545a.addInt(2, intValue);
                }
            }

            class RunnableC0387b implements Runnable {
                final Document f1547a;

                RunnableC0387b(Document uVar) {
           //         C0385a.this = r1;
                    this.f1547a = uVar;
                }

                @Override
                public void run() {
                    this.f1547a.addInt(2, (this.f1547a.getInt(2) | 32) ^ 32);
                    Page_Forum.this.tabLoaded(true);
                }
            }

            C0385a() {
              //  View$OnClickListenerC0384f.this = r1;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                Document uVar = (Document) Page_Forum.this.getItem(i2);
                int intValue = uVar.getInt(0);
                boolean z = true;
                if (i3 == 21) {
                    Page b = uVar.count() > 3 ? Urls2.openPage(Page_Forum.this.mainActivity, uVar.getString(3), false, 2) : new Page_Forum(Page_Forum.this.mainActivity, uVar.getInt(0).intValue(), 0, uVar.getString(1));
                    if (b != null) {
                        Tab f1Var = new Tab(Page_Forum.this.mainActivity);
                        f1Var.addPage(b);
                        Page_Forum.this.tab.mainLayout.setCurrentTab(f1Var);
                    }
                } else if (i3 == 1 || i3 == 2) {
                    if (i3 != 1) {
                        z = false;
                    }
                    API.ForumModifyRequest.m821p(intValue, null, 21, 8, z ? 8 : 0, Page_Forum.this, "обновление подписки", "", new RunnableC0386a(this, uVar, z));
                } else if (i3 == 34) {
                    DocumentManager.getResultRequest(new API.ForumGetTopicsRequest(intValue, Page_Forum.this.mainActivity));
                } else if (i3 == 8) {
                    MainActivity mainActivity = Page_Forum.this.mainActivity;
                    Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showforum=" + intValue, "Ссылка скопирована");
                } else if (i3 == 33) {
                    API.ForumModifyRequest.m821p(intValue, null, 21, 32, 0, Page_Forum.this, "отметка прочтения", "", new RunnableC0387b(uVar));
                }
            }
        }

        public View$OnClickListenerC0384f(Page_Forum g0Var) {
           // Page_Forum.this = r1;
            this.f1542a = g0Var;
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) this.f1542a.getItem(((Integer) view.getTag()).intValue());
            if (uVar.count() > 3) {
                Page b = Urls2.openPage(Page_Forum.this.mainActivity, uVar.getString(3), false, 1);
                if (b != null) {
                    Page_Forum.this.tab.addPage(b);
                    return;
                }
                return;
            }
            this.f1542a.tab.addPage(new Page_Forum(this.f1542a.mainActivity, uVar.getInt(0).intValue(), 0, uVar.getString(1)));
        }

        @Override
        public boolean onLongClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            Document uVar = (Document) Page_Forum.this.getItem(intValue);
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Forum.this.mainActivity, new C0385a(), true);
            o1Var.addMenuItem(2, intValue, 21, "Открыть в новой вкладке", false, false, false, false);
            if (uVar.count() <= 3) {
                int intValue2 = uVar.getInt(2).intValue();
                if ((intValue2 & 8) == 0) {
                    o1Var.addMenuItem(2, intValue, 1, "В избранное", false, false, true, false);
                } else {
                    o1Var.addMenuItem(2, intValue, 2, "В избранное", false, false, true, true);
                }
                o1Var.addMenuItem(2, intValue, 34, "О форуме", false, false, false, false);
                o1Var.addMenuItem(2, intValue, 8, "Копировать ссылку", false, false, false, false);
                if ((intValue2 & 32) != 0) {
                    o1Var.addMenuItem(2, intValue, 33, "Отметить прочитанным", false, false, false, false);
                }
            }
            o1Var.show(null);
            return true;
        }
    }

    public class View$OnClickListenerC0388g implements View.OnClickListener {
        Page_Forum f1549a;

        public View$OnClickListenerC0388g(Page_Forum g0Var, Page_Forum g0Var2) {
            this.f1549a = g0Var2;
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) this.f1549a.getItem(((Integer) view.getTag()).intValue());
            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(this.f1549a.mainActivity, 1, uVar.getInt((uVar.getInt(3).intValue() & 16) > 0 ? 4 : 0).intValue());
            lVar.m824u(this.f1549a.tab);
            lVar.m823v(uVar.getString(1));
            DocumentManager.getResultRequest(lVar);
        }
    }

    class CreateForumTopicRequest extends API.ForumGetFromRequest {
        CreateForumTopicRequest() {
            super(0, 0, 0, null);
          //  Page_Forum.this = r3;
            this.statusMessage = "Получение формы (" + this.forumNumber + ")";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Document l;
            if (!Page_Forum.this.isLoading) {
                if (status != 0 || uVar == null || (l = uVar.getDocument(0)) == null) {
                    Toast.makeText(Page_Forum.this.mainActivity, "Ошибка создания темы", 1).show();
                    return;
                }
                Page_Forum.this.f1532Y = new Form_Wizard.C0883n(l);
                Page_Forum.this.m700i0().m181E(Page_Forum.this);
            }
        }
    }

    public class View$OnClickListenerC0390i implements View.OnClickListener, View.OnLongClickListener {
        Page_Forum f1551a;

        public class C0391a implements OptionPoupupMenuView.IClickListener {

            class RunnableC0392a implements Runnable {
                final Document f1554a;
                final boolean f1555b;

                RunnableC0392a(C0391a aVar, Document uVar, boolean z) {
                    this.f1554a = uVar;
                    this.f1555b = z;
                }

                @Override
                public void run() {
                    int intValue = this.f1554a.getInt(3).intValue() | 8;
                    if (!this.f1555b) {
                        intValue ^= 8;
                    }
                    this.f1554a.addInt(3, intValue);
                }
            }

            class RunnableC0393b implements Runnable {
                final Document f1556a;
                final boolean f1557b;

                RunnableC0393b(Document uVar, boolean z) {
                //    C0391a.this = r1;
                    this.f1556a = uVar;
                    this.f1557b = z;
                }

                @Override
                public void run() {
                    int intValue = this.f1556a.getInt(3).intValue() | 4;
                    if (!this.f1557b) {
                        intValue ^= 4;
                    }
                    this.f1556a.addInt(3, intValue);
                    Page_Forum.this.tabLoaded(true);
                }
            }

            class RunnableC0394c implements Runnable {
                final Document f1559a;
                final boolean f1560b;

                RunnableC0394c(Document uVar, boolean z) {
                  //  C0391a.this = r1;
                    this.f1559a = uVar;
                    this.f1560b = z;
                }

                @Override
                public void run() {
                    int intValue = this.f1559a.getInt(3).intValue() | 2;
                    if (!this.f1560b) {
                        intValue ^= 2;
                    }
                    this.f1559a.addInt(3, intValue);
                    Page_Forum.this.tabLoaded(true);
                }
            }

            class C0395d implements TextWatcher {
                final DlgSimple f1562a;

                C0395d(C0391a aVar, DlgSimple q1Var) {
                    this.f1562a = q1Var;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    DlgSimple q1Var = this.f1562a;
                    q1Var.f1804b.setEnabled(!TextUtils.isEmpty(q1Var.editText.getText().toString()));
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }
            }

            class View$OnClickListenerC0396e implements View.OnClickListener {
                final DlgSimple f1563a;
                final int f1564b;

                View$OnClickListenerC0396e(DlgSimple q1Var, int i) {
              //      C0391a.this = r1;
                    this.f1563a = q1Var;
                    this.f1564b = i;
                }

                @Override
                public void onClick(View view) {
                    String obj = this.f1563a.editText.getText().toString();
                    int i = Urls2.m674i(obj);
                    if (i == 0) {
                        Page b = Urls2.openPage(Page_Forum.this.mainActivity, obj, true, 0);
                        if (b instanceof Page_Forum) {
                            i = ((Page_Forum) b).forumNumber;
                        }
                    }
                    if (i != 0) {
                        int i2 = this.f1564b;
                        boolean checked = this.f1563a.checkboxTextView.getChecked();
                        API.ForumModifyRequest.m821p(i2, null, 12, i, checked ? 1 : 0, Page_Forum.this, "перемещение темы", "", null);
                        return;
                    }
                    Toast.makeText(Page_Forum.this.mainActivity, "Неправильная ссылка", 1).show();
                }
            }

            class RunnableC0397f implements Runnable {
                final Document f1566a;

                RunnableC0397f(Document uVar) {
                //    C0391a.this = r1;
                    this.f1566a = uVar;
                }

                @Override
                public void run() {
                    this.f1566a.addInt(3, (this.f1566a.getInt(3).intValue() | 32) ^ 32);
                    Page_Forum.this.tabLoaded(true);
                }
            }

            C0391a() {
             //   View$OnClickListenerC0390i.this = r1;
            }

            @Override
            public void mo49a(int i, int i2, int i3) {
                Document uVar = (Document) Page_Forum.this.getItem(i2);
                boolean z = false;
                int intValue = uVar.getInt(0).intValue();
                boolean z2 = true;
                if (i3 == 21) {
                    if (i == 7) {
                        Page_Forum.this.m819N(((uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0)).intValue(), true, uVar.getString(1));
                    }
                } else if (i3 == 31) {
                    Page_Forum.this.tab.addPage(new Page_Topic(Page_Forum.this.mainActivity, ((uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0)).intValue(), 0, Prefs.showAllPost ? 3 : 0, 0, null, uVar.getString(1)));
                } else if (i3 == 32) {
                    API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(Page_Forum.this.mainActivity, 1, ((uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0)).intValue());
                    lVar.m824u(Page_Forum.this.tab);
                    lVar.m823v(uVar.getString(1));
                    DocumentManager.getResultRequest(lVar);
                } else if (i3 == 1 || i3 == 2) {
                    if (i3 != 1) {
                        z2 = false;
                    }
                    API.ForumModifyRequest.m821p(intValue, null, 11, 8, z2 ? 8 : 0, Page_Forum.this, "обновление подписки", "", new RunnableC0392a(this, uVar, z2));
                } else if (i3 == 13 || i3 == 14) {
                    if (i3 != 14) {
                        z2 = false;
                    }
                    API.ForumModifyRequest.m821p(intValue, null, 11, 4, z2 ? 4 : 0, Page_Forum.this, z2 ? "закрытие темы" : "открытие темы", "ВЫПОЛНИТЬ", new RunnableC0393b(uVar, z2));
                } else if (i3 == 16 || i3 == 15) {
                    if (i3 != 16) {
                        z2 = false;
                    }
                    API.ForumModifyRequest.m821p(intValue, null, 11, 2, z2 ? 2 : 0, Page_Forum.this, z2 ? "скрытие темы" : "отображение темы", "ВЫПОЛНИТЬ", new RunnableC0394c(uVar, z2));
                } else if (i3 == 11 || i3 == 12) {
                    int i4 = i3 == 11 ? 1 : 0;
                    API.ForumModifyRequest.m821p(intValue, null, 11, 1, i4, Page_Forum.this, i4 != 0 ? "прикрепление темы" : "открепление темы", "ВЫПОЛНИТЬ", null);
                } else if (i3 == 4) {
                    API.ForumModifyRequest.m821p(intValue, null, 14, 0, 0, Page_Forum.this, "удаление темы", "УДАЛИТЬ", null);
                } else if (i3 == 17) {
                    DlgSimple q1Var = new DlgSimple(Page_Forum.this.mainActivity, "Введите ссылку на форум", false, null, null);
                    q1Var.checkboxTextView.setText("Оставить ссылку");
                    q1Var.checkboxTextView.setChecked(true);
                    q1Var.checkboxTextView.setVisibility(0);
                    q1Var.m625a(false);
                    q1Var.editText.addTextChangedListener(new C0395d(this, q1Var));
                    q1Var.m620f(new View$OnClickListenerC0396e(q1Var, intValue), true);
                    q1Var.show(true, true, true);
                } else if (i3 == 6) {
                    int intValue2 = ((uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0)).intValue();
                    MainActivity mainActivity = Page_Forum.this.mainActivity;
                    Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showtopic=" + intValue2, "Адрес темы скопирован в буфер");
                } else if (i3 == 3) {
                    MainActivity mainActivity2 = Page_Forum.this.mainActivity;
                    String n = uVar.getString(1);
                    String c = Util.C0427h.UnEscapeString(uVar.getString(2));
                    if ((uVar.getInt(3).intValue() & 512) > 0) {
                        z = true;
                    }
                    new DlgEditTopic(mainActivity2, intValue, n, c, z, false, false, null, null).show(true, true, true);
                } else if (i3 == 33) {
                    API.ForumModifyRequest.m821p(intValue, null, 11, 32, 0, Page_Forum.this, "отметка прочтения", "", new RunnableC0397f(uVar));
                }
            }
        }

        public View$OnClickListenerC0390i(Page_Forum g0Var) {
       //     Page_Forum.this = r1;
            this.f1551a = g0Var;
        }

        @Override
        public void onClick(View view) {
            Document uVar = (Document) this.f1551a.getItem(((Integer) view.getTag()).intValue());
            this.f1551a.m819N(((uVar.getInt(3).intValue() & 16) > 0 ? uVar.getInt(4) : uVar.getInt(0)).intValue(), false, uVar.getString(1));
        }

        @Override
        public boolean onLongClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            Document uVar = (Document) Page_Forum.this.getItem(intValue);
            OptionPoupupMenuView o1Var = new OptionPoupupMenuView(Page_Forum.this.mainActivity, new C0391a(), true);
            o1Var.addMenuItem(7, intValue, 21, "Открыть в новой вкладке");
            int i = Prefs.f1187y;
            if (i == 0) {
                o1Var.addMenuItem(7, intValue, 32, "Перейти в конец");
            } else if (i == 1) {
                o1Var.addMenuItem(7, intValue, 31, "Перейти в начало");
            }
            int intValue2 = uVar.getInt(3).intValue();
            if ((intValue2 & 8) == 0) {
                o1Var.addMenuItem(7, intValue, 1, "В избранное", false);
            } else {
                o1Var.addMenuItem(7, intValue, 2, "В избранное", true);
            }
            if ((intValue2 & 128) > 0) {
                o1Var.addMenuItem(7, intValue, 3, "Редактировать", false, true);
            }
            if ((intValue2 & 256) > 0) {
                o1Var.addMenuItem(7, intValue, 4, "Удалить", false, true);
            }
            o1Var.addMenuItem(7, intValue, 6, "Копировать ссылку");
            if ((intValue2 & 32) != 0) {
                o1Var.addMenuItem(7, intValue, 33, "Отметить прочитанным");
            }
            if ((intValue2 & 512) > 0) {
                if ((intValue2 & 1) > 0) {
                    o1Var.addMenuItem(7, intValue, 12, "Закрепить тему", false, true, true, true);
                } else {
                    o1Var.addMenuItem(7, intValue, 11, "Закрепить тему", false, true, true, false);
                }
                if ((intValue2 & 2) > 0) {
                    o1Var.addMenuItem(7, intValue, 15, "Скрыть тему", false, true, true, true);
                } else {
                    o1Var.addMenuItem(7, intValue, 16, "Скрыть тему", false, true, true, false);
                }
                if ((intValue2 & 4) > 0) {
                    o1Var.addMenuItem(7, intValue, 13, "Закрыть тему", false, true, true, true);
                } else {
                    o1Var.addMenuItem(7, intValue, 14, "Закрыть тему", false, true, true, false);
                }
                o1Var.addMenuItem(7, intValue, 17, "Переместить", false, true);
            }
            o1Var.show(null);
            return true;
        }
    }

    @SuppressLint("DefaultLocale")
    public Page_Forum(MainActivity mainActivity, int forumNumber, int pageNumber, String str) {
        super(mainActivity, 29542, new Document(forumNumber, pageNumber, Prefs.f1181s));
        this.iconId = R.drawable.ic_nav_forum;
        this.forumNumber = forumNumber;
        this.pageNumber = pageNumber;
        this.title = TextUtils.isEmpty(str) ? String.format("форум %d: %d(%d)", forumNumber, pageNumber, Prefs.f1181s) : str;
        this.statusMessage = String.format("Загрузка форума %d", this.forumNumber);
        this.f1523P = new View$OnClickListenerC0383e(this, this);
        this.f1524Q = new View$OnClickListenerC0384f(this);
        this.f1525R = new View$OnClickListenerC0390i(this);
        this.f1526S = new View$OnClickListenerC0388g(this, this);
        if (f1507b0 == null) {
            f1507b0 = new Vector(10);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            int i3 = defaultSharedPreferences.getInt("forum_rules_count", 0);
            for (int i4 = 0; i4 < i3; i4++) {
                List<Integer> list = f1507b0;
                list.add(defaultSharedPreferences.getInt("forum_rule_" + i4, 0));
            }
        }
    }

    public Form_Wizard m700i0() {
        if (this.f1533Z == null) {
            this.f1533Z = new Form_Wizard(this.mainActivity, this.forumNumber, 0, this.f1531X, this.f1532Y, this);
        }
        return this.f1533Z;
    }

    @Override
    public void mo147A(Bundle bundle, String str) {
        super.mo147A(bundle, str);
        if (bundle.getBoolean(str + "_ws")) {
            this.f1531X = bundle.getString(str + "_wt");
            this.f1532Y = new Form_Wizard.C0883n(bundle, str + "_ws");
            this.f1534a0 = bundle.getBoolean(str + "_wo");
        }
    }

    @Override
    public boolean mo145B() {
        Form_Wizard xVar = this.f1533Z;
        if (xVar == null) {
            return false;
        }
        if (xVar.m162r()) {
            return true;
        }
        this.f1533Z.m184B();
        this.f1533Z = null;
        return true;
    }

    @Override
    protected boolean onPageLoadedForum() {
        this.title = Util.C0427h.UnEscapeString(this.currentDocument.getString(2));
        this.f1531X = "Создание: " + this.title;
        this.f1514G = this.currentDocument.getInt(7);
        if (!f1507b0.contains(this.forumNumber)) {
            m704e0();
        }
        this.f1515H = this.currentDocument.getDocument(6);
        this.f1516I = this.currentDocument.getDocument(8);
        this.f1517J = this.currentDocument.getDocument(5);
        this.f1518K = this.f1515H.count();
        this.f1519L = this.f1516I.count();
        this.f1521N = this.f1517J.count();
        for (int i = 0; i < this.f1518K; i++) {
            Document l = this.f1515H.getDocument(i);
            l.addString(1, Util.C0427h.UnEscapeString(l.getString(1)));
        }
        this.f1520M = 0;
        for (int i2 = 0; i2 < this.f1519L; i2++) {
            Document l2 = this.f1516I.getDocument(i2);
            l2.addString(1, Util.C0427h.UnEscapeString(l2.getString(1)));
            l2.addString(7, Util.C0427h.UnEscapeString(l2.getString(7)));
            if ((l2.getInt(3) & 1) > 0) {
                this.f1520M++;
            }
        }
        for (int i3 = 0; i3 < this.f1521N; i3++) {
            Document l3 = this.f1517J.getDocument(i3);
            l3.addString(1, Util.C0427h.UnEscapeString(l3.getString(1)));
        }
        return true;
    }

    @Override
    public void setOptionMenuToolBar(View view) {
        OptionPoupupMenuView o1Var = new OptionPoupupMenuView(this.mainActivity, new C0381d());
        if (Prefs.showReloadButton) {
            o1Var.addMenuItem(0, 0, 21, "Обновить");
        }
        if (isUnsucces()) {
            int intValue = this.currentDocument.getInt(3);
            o1Var.addMenuItem(0, 0, 22, "В закладки", DataDB.m366k(getLink()));
            if ((intValue & 8) == 0) {
                o1Var.addMenuItem(0, 0, 1, "В избранное", false);
            } else {
                o1Var.addMenuItem(0, 0, 2, "В избранное", true);
            }
            o1Var.addMenuItem(0, 0, 7, "О форуме");
            o1Var.addMenuItem(0, 0, 3, "Ссылка на страницу");
            if ((intValue & 64) > 0) {
                o1Var.addMenuItem(0, 0, 4, "Создать тему");
            }
            o1Var.addMenuItem(0, 0, 5, "Открыть в браузере");
            if (f1507b0.contains(this.forumNumber)) {
                o1Var.addMenuItem(0, 0, 6, "Отображать правила форума");
            }
        }
        o1Var.show(view);
    }

    @Override
    public void mo54M(Tab f1Var, boolean z) {
        super.mo54M(f1Var, z);
        if (this.f1527T) {
            tabReload();
        }
        if (z) {
            return;
        }
        if (this.f1534a0) {
            m700i0().m181E(this);
            this.f1534a0 = false;
            return;
        }
        Form_Wizard xVar = this.f1533Z;
        if (xVar != null) {
            xVar.m182D();
        }
    }

    @Override
    int mo141P() {
        return ((this.f1514G - 1) / Prefs.f1181s) + 1;
    }

    @Override
    int mo140Q() {
        return (this.pageNumber / Prefs.f1181s) + 1;
    }

    @Override
    Page mo139R(int i) {
        return new Page_Forum(this.mainActivity, this.forumNumber, (i - 1) * Prefs.f1181s, this.title);
    }

    @Override
    public void mo138Y(Bundle bundle, String str) {
        super.mo138Y(bundle, str);
        Form_Wizard xVar = this.f1533Z;
        if (xVar != null) {
            xVar.m161s();
            bundle.putBoolean(str + "_wo", true);
            bundle.putString(str + "_wt", this.f1531X);
        }
        if (this.f1532Y != null) {
            bundle.putBoolean(str + "_ws", true);
            Form_Wizard.C0883n nVar = this.f1532Y;
            nVar.m148b(bundle, str + "_ws");
        }
    }

    @Override
    public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
    }

    @Override
    public boolean mo136a0() {
        if (!super.mo136a0()) {
            return false;
        }
        Unread2.f1568b.m654a(this.f1528U);
        DocumentManager.f2752I.m651a(this.f1529V);
        return true;
    }

    @Override
    public boolean mo134b0() {
        if (!super.mo134b0()) {
            return false;
        }
        Unread2.f1568b.m653b(this.f1528U);
        DocumentManager.f2752I.m650b(this.f1529V);
        return true;
    }

    @Override
    public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
    }

    @Override
    public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
    }

    Util.C0428i<Integer, Document> m705d0(int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        if (!m816T()) {
            i2 = 0;
        } else if (i == 0) {
            return new Util.C0428i<>(0, null);
        } else {
            if (getCount() - 1 == i) {
                return new Util.C0428i<>(9, null);
            }
            i2 = 1;
        }
        if (this.f1522O != null) {
            i2++;
            if (i == i2 - 1) {
                return new Util.C0428i<>(10, null);
            }
        }
        int i6 = this.f1518K;
        if (i6 <= 0) {
            i3 = 0;
        } else if (i == i2) {
            return new Util.C0428i<>(1, null);
        } else {
            if (i < i2 + 1 + i6) {
                return new Util.C0428i<>(2, this.f1515H.getDocument((i - i2) - 1));
            }
            i3 = 1;
        }
        int i7 = this.f1521N;
        if (i7 > 0) {
            int i8 = i2 + i3 + i6;
            if (i == i8) {
                return new Util.C0428i<>(3, null);
            }
            int i9 = (i - i8) - 1;
            if (i9 < i7) {
                return new Util.C0428i<>(4, this.f1517J.getDocument(i9));
            }
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i10 = this.f1520M;
        if (i10 > 0) {
            int i11 = i2 + i3 + i6 + i4 + i7;
            if (i == i11) {
                return new Util.C0428i<>(5, null);
            }
            int i12 = (i - i11) - 1;
            if (i12 < i10) {
                return new Util.C0428i<>(7, this.f1516I.getDocument(i12));
            }
            i5 = 1;
        }
        int i13 = this.f1519L;
        if (i13 - i10 > 0) {
            int i14 = i2 + i3 + i6 + i4 + i7 + i5 + i10;
            if (i == i14) {
                return new Util.C0428i<>(6, null);
            }
            int i15 = ((i - i14) - 1) + i10;
            if (i15 < i13) {
                return new Util.C0428i<>(7, this.f1516I.getDocument(i15));
            }
        }
        return new Util.C0428i<>(8, null);
    }

    void m704e0() {
        String n = this.currentDocument.getString(4);
        if (!TextUtils.isEmpty(n)) {
            BBString x = BBString.getBBString(n, null);
            this.f1522O = x;
            if (x != null) {
                BBString.C0674e eVar = x.f2246z;
                float f = this.mainActivity.f731b;
                int i = (int) (f * 16.0f);
                eVar.f2266j = i;
                eVar.f2265i = i;
                float f2 = (float) ((int) (f * 16.0f));
                eVar.f2264h = f2;
                eVar.f2263g = f2;
            }
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

    @Override
    public int getCount() {
        int i;
        int i2 = 0;
        if (!isUnsucces() || ((i = this.f1518K) == 0 && this.f1519L == 0 && this.f1521N == 0)) {
            return 0;
        }
        int i3 = this.f1522O != null ? 1 : 0;
        int i4 = this.f1521N;
        int i5 = i3 + (i4 > 0 ? i4 + 1 : 0) + (i > 0 ? i + 1 : 0);
        int i6 = this.f1520M;
        int i7 = i5 + (i6 > 0 ? i6 + 1 : 0);
        int i8 = this.f1519L;
        int i9 = i7 + (i8 - i6 > 0 ? (i8 - i6) + 1 : 0) + 1;
        if (m816T()) {
            i2 = 2;
        }
        return i9 + i2;
    }

    @Override
    public Object getItem(int i) {
        return m705d0(i).f1652b;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public int getItemViewType(int i) {
        return m705d0(i).f1651a;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    //    View view2 = null;
        Util.C0428i<Integer, Document> d0 = m705d0(i);
        int intValue = d0.f1651a;
        Document uVar = d0.f1652b;
        MainActivity mainActivity = this.mainActivity;
        float f = mainActivity.f731b;
        int i2 = 8;
        boolean z = false;
        if (view == null) {
            if (intValue == 0) {
                view = m817S(viewGroup, false);
            } else if (intValue == 9) {
                view = m817S(viewGroup, true);
            } else if (intValue == 7) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_top, viewGroup, false);
            } else if (intValue == 2 || intValue == 4) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_for, viewGroup, false);
            } else if (intValue == 10) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.startglobal, viewGroup, false);
                view.findViewById(R.id.sepBottom).setVisibility(8);
                ((BBDisplay) view.findViewById(R.id.globalMsg)).setCallback(this);
                view.findViewById(R.id.globalClose).setOnClickListener(new View$OnClickListenerC0378a());
            } else if (intValue == 1) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                if (this.f1522O != null) {
                    view.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                } else {
                    view.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                }
                int i3 = (int) (f * 16.0f);
                view.setPadding(i3, i3, i3, (int) (8.0f * f));
                ((TextView) view).setText("Форумы");
            } else if (intValue == 3) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                if (this.f1518K > 0 || this.f1522O != null) {
                    view.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                } else {
                    view.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                }
                int i4 = (int) (f * 16.0f);
                view.setPadding(i4, i4, i4, (int) (8.0f * f));
                ((TextView) view).setText("Объявления");
            } else if (intValue == 5) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                if (this.f1518K > 0 || this.f1522O != null) {
                    view.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                } else {
                    view.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                }
                int i5 = (int) (f * 16.0f);
                view.setPadding(i5, i5, i5, (int) (8.0f * f));
                ((TextView) view).setText("Закреплённые темы");
            } else if (intValue == 6) {
                view = mainActivity.getLayoutInflater().inflate(R.layout.forum_list_sep, viewGroup, false);
                if (this.f1518K > 0 || this.f1520M > 0 || this.f1522O != null) {
                    view.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                } else {
                    view.setBackgroundDrawable(Skin.C0353a.f1388i0.getConstantState().newDrawable());
                }
                int i6 = (int) (f * 16.0f);
                view.setPadding(i6, i6, i6, (int) (8.0f * f));
                ((TextView) view).setText("Темы");
            } else if (intValue == 8) {
                view = new View(this.mainActivity);
                view.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.card_sep));
                view.setLayoutParams(new AbsListView.LayoutParams(-1, (int) (f * 16.0f)));
            }
            if (intValue != 10) {
                ((BBDisplay) view.findViewById(R.id.globalMsg)).setBBString(this.f1522O);
            } if (intValue == 2) {
                TextView textView = (TextView) view;
                textView.setText(uVar.getString(1));
                textView.setTag(i);
                textView.setOnClickListener(this.f1524Q);
                textView.setOnLongClickListener(this.f1524Q);
                textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (uVar.getInt(2) & 32) > 0 ? this.mainActivity.skin.m736f(R.drawable.ic_unread) : null, (Drawable) null);
                if (getItemViewType(i + 1) == 2) {
                    textView.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.border_bottom));
                } else {
                    textView.setBackgroundResource(0);
                }
                int i7 = (int) (f * 16.0f);
                textView.setPadding(i7, i7, i7, i7);
            } if (intValue == 4) {
                TextView textView2 = (TextView) view;
                textView2.setText(uVar.getString(1));
                textView2.setTag(uVar.getInt(0));
                textView2.setOnClickListener(this.f1523P);
                if (getItemViewType(i + 1) == 4) {
                    textView2.setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.border_bottom));
                } else {
                    textView2.setBackgroundResource(0);
                }
                int i8 = (int) (f * 16.0f);
                textView2.setPadding(i8, i8, i8, i8);
            } if (intValue == 7) {
                String str = (uVar.getInt(3) & 16) > 0 ? "Перемещено: " : "";
                ViewGroup viewGroup2 = (ViewGroup) view;
                TextView textView3 = (TextView) viewGroup2.findViewById(R.id.topicName);
                View findViewById = viewGroup2.findViewById(R.id.topicGoUnread);
                textView3.setText(str + uVar.getString(1));
                ((TextView) viewGroup2.findViewById(R.id.topicLastDate)).setText(Util.formatDate(uVar.getInt(5)));
                ((TextView) viewGroup2.findViewById(R.id.topicLastUser)).setText(uVar.getString(7));
                viewGroup2.setTag(i);
                viewGroup2.setOnClickListener(this.f1525R);
                viewGroup2.setOnLongClickListener(this.f1525R);
                findViewById.setTag(i);
                if ((uVar.getInt(3) & 32) != 0) {
                    i2 = 0;
                }
                findViewById.setVisibility(i2);
                findViewById.setOnClickListener(this.f1526S);
                if ((uVar.getInt(3) & 4) == 0) {
                    z = true;
                }
                textView3.setEnabled(z);
                int itemViewType = getItemViewType(i + 1);
                if ((uVar.getInt(3) & 2) > 0) {
                    viewGroup2.setBackgroundColor(Skin.C0353a.f1366V);
                } else {
                    viewGroup2.setBackgroundDrawable(itemViewType == 7 ? this.mainActivity.skin.m736f(R.drawable.border_bottom) : null);
                }
            }
            if (intValue == 9) {
                m815U();
            }
            return view;
        }
       // view2 = view;
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 11;
    }

    @Override
    public void destroyData() {
        super.destroyData();
        this.f1521N = 0;
        this.f1520M = 0;
        this.f1519L = 0;
        this.f1518K = 0;
        this.f1515H = null;
        this.f1516I = null;
        this.f1533Z = null;
        this.f1517J = null;
        this.f1522O = null;
    }

    @Override
    public Breadcrumb.C0725a[] mo51t() {
        if (!isUnsucces()) {
            return null;
        }
        Document l = this.currentDocument.getDocument(0);
        Breadcrumb.C0725a[] aVarArr = new Breadcrumb.C0725a[l.count() + 2];
        aVarArr[0] = new Breadcrumb.C0725a(1, "forum/index.php?act=idx", "Форум", 0, false, false);
        for (int i = 0; i < l.count(); i++) {
            Document l2 = l.getDocument(i);
            aVarArr[l.count() - i] = new Breadcrumb.C0725a(i + 2, "forum/index.php?showforum=" + l2.getInt(1), l2.getString(2), 0, false, false);
        }
        aVarArr[l.count() + 1] = new Breadcrumb.C0725a(100, "forum/index.php?showforum=" + this.forumNumber, this.title, 0, true, true);
        return aVarArr;
    }

    @Override
    public String getLink() {
        String str;
        if (this.f1530W == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("forum/index.php?showforum=");
            sb.append(this.forumNumber);
            if (this.pageNumber > 0) {
                str = "&st=" + this.pageNumber;
            } else {
                str = "";
            }
            sb.append(str);
            this.f1530W = sb.toString();
        }
        return this.f1530W;
    }

    @Override
    public boolean mo109y(Object... objArr) {
        if (objArr.length >= 2) {
            if (((Integer) objArr[0]).intValue() == this.forumNumber && ((Integer) objArr[1]).intValue() == this.pageNumber) {
                return true;
            }
            return false;
        } else if (objArr.length < 1 || ((Integer) objArr[0]).intValue() != this.forumNumber) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void tabReload() {
        this.f1527T = false;
        m810c0("", false);
        super.tabReload();
    }
}
