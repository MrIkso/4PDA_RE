package ru.fourpda.client;

import android.content.Context;
import android.net.Uri;
import java.util.Locale;

public class Unread2 {
    static Util.C0421c<DataDB.C0738c, DataDB.C0738c> f1568b = new Util.C0421c<>();
    static Util.C0420b<DataDB.C0738c> f1569c = new Util.C0420b<>();
    private Context f1570a;

    public Unread2(Context context) {
        this.f1570a = context;
    }

    private void m687k(DataDB.C0738c cVar, int i) {
        DataDB.C0738c cVar2 = new DataDB.C0738c(cVar);
        int i2 = cVar2.f2518h;
        if (i == 0) {
            i = cVar2.f2517g;
        }
        cVar2.f2518h = Math.max(i2, i);
        if (3 == cVar2.f2511a) {
            cVar2.f2521k &= -2;
        }
        m686l(cVar, cVar2);
    }

    public void m686l(DataDB.C0738c cVar, DataDB.C0738c cVar2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z5 = cVar != null && cVar.f2517g > cVar.f2518h;
        boolean z6 = cVar != null && 3 == cVar.f2511a && (cVar.f2521k & 1) > 0;
        boolean z7 = cVar2.f2517g > cVar2.f2518h;
        boolean z8 = 3 == cVar2.f2511a && (cVar2.f2521k & 1) > 0;
        cVar2.f2513c = z7;
        if (z7 || z8) {
            DataDB.m378F(cVar2);
        } else if (cVar != null) {
            DataDB.m382B(cVar.f2511a, cVar.f2512b);
        }
        try {
            f1568b.m652c(cVar, cVar2);
        } catch (Throwable th) {
            //ACRA.getErrorReporter().handleSilentException(th);
        }
        if (z7) {
            if (cVar2.f2517g > (cVar != null ? cVar.f2517g : 0)) {
                int i5 = cVar2.f2511a;
                if ((1 == i5 && cVar2.f2515e == 0 && Prefs.f1164b) || ((1 == i5 && cVar2.f2515e != 0 && Prefs.f1163a) || ((3 == i5 || 2 == i5) && Prefs.f1165c && (!Prefs.f1166d || cVar2.f2520j != 0) ? (i4 = cVar2.f2519i & 3) == 0 || (1 == i4 && !z5) : (4 == i5 || 5 == i5) && Prefs.f1167e))) {
                    z2 = true;
                    z = false;
                    if (z8) {
                        if (cVar2.f2521k > (cVar != null ? cVar.f2521k : 0)) {
                            if ((cVar2.f2519i & 4) != 0) {
                                z4 = true;
                                z3 = false;
                                if ((!z2 || z4) && f1569c.m655c(cVar2)) {
                                    z4 = false;
                                    z2 = false;
                                }
                                i = cVar2.f2511a;
                                if ((3 != i || 2 == i) && z2 && cVar2.f2515e == DocumentManager.getMemberId()) {
                                    z2 = false;
                                }
                                if ((!z2 || z) && Prefs.f1168f) {
                                    i2 = cVar2.f2511a;
                                    if (1 != i2) {
                                        int f = m692f();
                                        if (f > 0 && z2) {
                                            Notify.m46c(this.f1570a, -1, "4pda-qms-group", true, true, "4PDA QMS", f + " " + Util.m662l(f, "новое сообщение", "новых сообщения", "новых сообщений"), Uri.parse("https://4pda.ru/forum/index.php?act=qms"));
                                            return;
                                        } else if (f == 0 && z) {
                                            Notify.m48a(this.f1570a, -1, "4pda-qms-group");
                                            return;
                                        } else {
                                            return;
                                        }
                                    } else if (2 == i2 || 3 == i2) {
                                        if (Prefs.f1166d) {
                                            DataDB.C0738c[] D = DataDB.m380D(2);
                                            if (D != null) {
                                                i3 = 0;
                                                for (DataDB.C0738c cVar3 : D) {
                                                    if (cVar3.f2513c && cVar3.f2520j > 0) {
                                                        i3++;
                                                    }
                                                }
                                            } else {
                                                i3 = 0;
                                            }
                                            DataDB.C0738c[] D2 = DataDB.m380D(3);
                                            if (D2 != null) {
                                                for (DataDB.C0738c cVar4 : D2) {
                                                    if (cVar4.f2513c && cVar4.f2520j > 0) {
                                                        i3++;
                                                    }
                                                }
                                            }
                                        } else {
                                            i3 = m694d();
                                        }
                                        if (i3 > 0 && z2) {
                                            Context context = this.f1570a;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append(Prefs.f1166d ? "Непрочитанных важных: " : "Непрочитанных в избранном: ");
                                            sb.append(i3);
                                            Notify.m46c(context, -2, "4pda-fav-group", true, true, "4PDA избранное", sb.toString(), Uri.parse("https://4pda.ru/forum/index.php?act=fav"));
                                            return;
                                        } else if (i3 == 0 && z) {
                                            Notify.m48a(this.f1570a, -2, "4pda-fav-group");
                                            return;
                                        } else {
                                            return;
                                        }
                                    } else if (4 == i2 || 5 == i2) {
                                        int e = m693e();
                                        if (e > 0 && z2) {
                                            Notify.m46c(this.f1570a, -3, "4pda-mention-group", true, true, "4PDA упоминания", "Ссылаются на форуме: " + e, Uri.parse("https://4pda.ru/forum/index.php?act=mentions"));
                                            return;
                                        } else if (e == 0 && z) {
                                            Notify.m48a(this.f1570a, -3, "4pda-mention-group");
                                            return;
                                        } else {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } else if (z2) {
                                    int i6 = cVar2.f2511a;
                                    if (1 == i6) {
                                        Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-qms", true, false, cVar2.f2516f, String.format(Locale.getDefault(), "%s: %d %s", cVar2.f2514d, Integer.valueOf(cVar2.f2519i), Util.m662l(cVar2.f2519i, "новое сообщение", "новых сообщения", "новых сообщений")), Uri.parse("https://4pda.ru/forum/index.php?act=qms&t=" + cVar2.f2512b));
                                        return;
                                    } else if (2 == i6) {
                                        Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-forum", true, false, "Новое на форуме ", cVar2.f2514d, Uri.parse("https://4pda.ru/forum/index.php?showforum=" + cVar2.f2512b));
                                        return;
                                    } else if (3 == i6) {
                                        Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-topic", true, false, cVar2.f2516f + " в топике ", cVar2.f2514d, Uri.parse("https://4pda.ru/forum/index.php?showtopic=" + cVar2.f2512b + "&view=getnewpost"));
                                        return;
                                    } else if (4 == i6) {
                                        Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-mention-forum", true, false, "Упоминание в топике " + cVar2.f2514d, cVar2.f2516f + " ссылается на вас", Uri.parse("https://4pda.ru/forum/index.php?showtopic=" + cVar2.f2517g + "&view=findpost&p=" + cVar2.f2512b));
                                        return;
                                    } else if (5 == i6) {
                                        Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-mention-site", true, false, "Упоминание в новости " + cVar2.f2514d, cVar2.f2516f + " ссылается на вас", Uri.parse("https://4pda.ru/?p=" + cVar2.f2517g + "&c=" + cVar2.f2512b));
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (z) {
                                    int i7 = cVar2.f2511a;
                                    if (1 == i7) {
                                        Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-qms");
                                        return;
                                    } else if (2 == i7) {
                                        Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-forum");
                                        return;
                                    } else if (3 == i7) {
                                        Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-topic");
                                        return;
                                    } else if (4 == i7) {
                                        Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-mention-forum");
                                        return;
                                    } else if (5 == i7) {
                                        Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-mention-site");
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (z4) {
                                    Notify.m46c(this.f1570a, cVar2.f2512b, "4pda-pinupd", true, false, "Обновилась шапка ", cVar2.f2514d, Uri.parse("https://4pda.ru/forum/index.php?showtopic=" + cVar2.f2512b));
                                    return;
                                } else if (z3) {
                                    Notify.m48a(this.f1570a, cVar2.f2512b, "4pda-pinupd");
                                    return;
                                } else {
                                    return;
                                }
                            }
                            z4 = false;
                            z3 = false;
                            if (!z2) {
                            }
                            z4 = false;
                            z2 = false;
                            i = cVar2.f2511a;
                            if (3 != i) {
                            }
                            z2 = false;
                            if (!z2) {
                            }
                            i2 = cVar2.f2511a;
                            if (1 != i2) {
                            }
                        }
                    }
                    if (z6 && !z8) {
                        z4 = false;
                        z3 = true;
                        if (!z2) {
                        }
                        z4 = false;
                        z2 = false;
                        i = cVar2.f2511a;
                        if (3 != i) {
                        }
                        z2 = false;
                        if (!z2) {
                        }
                        i2 = cVar2.f2511a;
                        if (1 != i2) {
                        }
                    }
                    z4 = false;
                    z3 = false;
                    if (!z2) {
                    }
                    z4 = false;
                    z2 = false;
                    i = cVar2.f2511a;
                    if (3 != i) {
                    }
                    z2 = false;
                    if (!z2) {
                    }
                    i2 = cVar2.f2511a;
                    if (1 != i2) {
                    }
                }
                z2 = false;
                z = false;
                if (z8) {
                }
                if (z6) {
                    z4 = false;
                    z3 = true;
                    if (!z2) {
                    }
                    z4 = false;
                    z2 = false;
                    i = cVar2.f2511a;
                    if (3 != i) {
                    }
                    z2 = false;
                    if (!z2) {
                    }
                    i2 = cVar2.f2511a;
                    if (1 != i2) {
                    }
                }
                z4 = false;
                z3 = false;
                if (!z2) {
                }
                z4 = false;
                z2 = false;
                i = cVar2.f2511a;
                if (3 != i) {
                }
                z2 = false;
                if (!z2) {
                }
                i2 = cVar2.f2511a;
                if (1 != i2) {
                }
            }
        }
        if (z5 && !z7) {
            z2 = false;
            z = true;
            if (z8) {
            }
            if (z6) {
            }
            z4 = false;
            z3 = false;
            if (!z2) {
            }
            z4 = false;
            z2 = false;
            i = cVar2.f2511a;
            if (3 != i) {
            }
            z2 = false;
            if (!z2) {
            }
            i2 = cVar2.f2511a;
            if (1 != i2) {
            }
        }
        z2 = false;
        z = false;
        if (z8) {
        }
        if (z6) {
        }
        z4 = false;
        z3 = false;
        if (!z2) {
        }
        z4 = false;
        z2 = false;
        i = cVar2.f2511a;
        if (3 != i) {
        }
        z2 = false;
        if (!z2) {
        }
        i2 = cVar2.f2511a;
        if (1 != i2) {
        }
    }

    public void m696b() {
        DataDB.m351z();
        DataDB.m362o(2, 0);
    }

    public void m695c() {
        DocumentManager.getResultRequest(new UnreadSyncRequest());
    }

    public int m694d() {
        return DataDB.m383A(2) + DataDB.m383A(3);
    }

    public int m693e() {
        return DataDB.m383A(4) + DataDB.m383A(5);
    }

    public int m692f() {
        return DataDB.m377G(1, "uext1");
    }

    public void m691g(int i, int i2, int i3) {
        if (i2 == 0) {
            DataDB.C0738c[] D = DataDB.m380D(i);
            if (D != null) {
                for (DataDB.C0738c cVar : D) {
                    m687k(cVar, i3);
                }
                return;
            }
            return;
        }
        DataDB.C0738c C = DataDB.m381C(i, i2);
        if (C != null) {
            m687k(C, i3);
        }
    }

    public void m690h(int i, int[] iArr, int i2) {
        DataDB.C0738c[] E = DataDB.m379E(i, iArr);
        if (E != null) {
            for (DataDB.C0738c cVar : E) {
                m687k(cVar, i2);
            }
        }
    }

    public void m689i(int i, int i2, int i3) {
        DocumentManager.getResultRequest(new UnreadSyncRequest(i, i2));
    }

    public void m688j(int i, int i2, String str, int i3, String str2, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        int i10;
        int i11;
        DataDB.C0738c C = DataDB.m381C(i, i2);
        if (z || C == null) {
            i11 = i4;
            i10 = i5;
        } else {
            int max = Math.max(C.f2517g, i4);
            i10 = Math.max(C.f2518h, i5);
            i11 = max;
        }
        m686l(C, new DataDB.C0738c(i, i2, C != null && C.f2513c, str, i3, str2, i11, i10, i6, i7, i8, i9));
    }

    public class UnreadSyncRequest extends DocumentManager.IGenerateRequest {
        private boolean f1571g;
        private int f1572h;
        private int f1573i;
        private int f1574j;

        class C0399a implements Util.AbstractC0429j<Boolean, DataDB.C0738c> {
            C0399a(UnreadSyncRequest aVar) {
            }

            public Boolean mo222a(DataDB.C0738c cVar) {
                return Boolean.TRUE;
            }
        }

        UnreadSyncRequest() {
            super(28269);
            this.f1571g = false;
            this.f1572h = DataDB.m363n(2, 0);
            this.f1573i = 0;
            this.f1574j = 0;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            DataDB.C0738c[] y;
            if (status == 0) {
                C0399a aVar = null;
                if (this.f1573i == 0 && !this.f1571g && this.f1572h == 0) {
                    aVar = new C0399a(this);
                    Unread2.f1569c.m657a(aVar);
                }
                if (this.f1573i == 0 && this.f1571g) {
                    DataDB.m353x();
                }
                int i2 = 4;
                try {
                    Document l = uVar.getDocument(4);
                    int i3 = 0;
                    while (i3 < l.count()) {
                        Document l2 = l.getDocument(i3);
                        Unread2.this.m688j(l2.getInt(0).intValue(), l2.getInt(1).intValue(), l2.getString(2), l2.getInt(3).intValue(), l2.getString(i2), l2.getInt(5).intValue(), l2.getInt(6).intValue(), l2.getInt(7).intValue(), l2.getInt(8).intValue(), l2.getInt(9).intValue(), l2.getInt(10).intValue(), true);
                        i3++;
                        i2 = 4;
                    }
                    if (this.f1573i == 0) {
                        DataDB.m362o(2, uVar.getInt(0).intValue());
                    }
                } catch (Throwable th) {
                    //ACRA.getErrorReporter().handleSilentException(th);
                }
                if (aVar != null) {
                    Unread2.f1569c.m656b(aVar);
                }
                if (this.f1573i == 0) {
                    if (this.f1571g && (y = DataDB.m352y()) != null) {
                        for (DataDB.C0738c cVar : y) {
                            DataDB.C0738c cVar2 = new DataDB.C0738c(cVar);
                            cVar2.f2518h = cVar2.f2517g;
                            if (3 == cVar2.f2511a) {
                                cVar2.f2521k &= -2;
                            }
                            Unread2.this.m686l(cVar, cVar2);
                        }
                    }
                    int intValue = uVar.getInt(1).intValue();
                    int intValue2 = uVar.getInt(2).intValue();
                    int intValue3 = uVar.getInt(3).intValue();
                    int f = Unread2.this.m692f();
                    int d = Unread2.this.m694d();
                    int e = Unread2.this.m693e();
                    if (!(f == intValue && d == intValue2 && e == intValue3)) {
                        if (this.f1571g || this.f1572h <= 0) {
                            //ACRA.getErrorReporter().putCustomData("unread", "q" + f + ":" + intValue + " f" + d + ":" + intValue2 + " m" + e + ":" + intValue3);
                            //ACRA.getErrorReporter().handleSilentException(new Exception("FullSync failed"));
                            //ACRA.getErrorReporter().removeCustomData("unread");
                            return;
                        }
                        this.f1571g = true;
                        this.f1572h = 0;
                        DocumentManager.getResultRequest(this);
                    }
                }
            }
        }

        @Override
        Document generate() {
            return new Document(this.f1572h, this.f1573i, this.f1574j);
        }

        UnreadSyncRequest(int i, int i2) {
            super(28269);
            this.f1571g = false;
            this.f1572h = 0;
            this.f1573i = i;
            this.f1574j = i2;
        }
    }
}
