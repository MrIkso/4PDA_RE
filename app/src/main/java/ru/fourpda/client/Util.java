package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.text.ClipboardManager;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.Toast;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Util {
    static Typeface fontelloTypeface;
    static Typeface robotoTypeface;
    static AtomicInteger f1625c = new AtomicInteger(((int) (Math.random() * 8192.0d)) + 65536);
    private static BBDisplay bbDisplay;

    static class C0419a {
        private final Object f1627a = new Object();
        private volatile boolean f1628b;

        public C0419a(boolean z) {
            this.f1628b = false;
            this.f1628b = z;
        }

        public void m659a() {
            synchronized (this.f1627a) {
                this.f1628b = true;
                this.f1627a.notify();
            }
        }

        public void m658b(long j) {
            synchronized (this.f1627a) {
                long currentTimeMillis = System.currentTimeMillis();
                while (!this.f1628b) {
                    try {
                        this.f1627a.wait(j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (System.currentTimeMillis() - currentTimeMillis >= j) {
                        break;
                    }
                }
                this.f1628b = false;
            }
        }
    }

    public static class C0420b<T1> {
        List<AbstractC0429j<Boolean, T1>> f1629a = new Vector(5);

        public void m657a(AbstractC0429j<Boolean, T1> jVar) {
            this.f1629a.add(jVar);
        }

        public void m656b(AbstractC0429j<Boolean, T1> jVar) {
            this.f1629a.remove(jVar);
        }

        public boolean m655c(T1 t1) {
            boolean z = false;
            for (int i = 0; i < this.f1629a.size(); i++) {
                Boolean a = this.f1629a.get(i).mo222a(t1);
                if (a != null && a) {
                    z = true;
                }
            }
            return z;
        }
    }

    static class C0421c<T1, T2> {
        List<AbstractC0430k<Boolean, T1, T2>> f1630a = new Vector(5);

        public void m654a(AbstractC0430k<Boolean, T1, T2> kVar) {
            this.f1630a.add(kVar);
        }

        public void m653b(AbstractC0430k<Boolean, T1, T2> kVar) {
            this.f1630a.remove(kVar);
        }

        public boolean m652c(T1 t1, T2 t2) {
            boolean z = false;
            for (int i = 0; i < this.f1630a.size(); i++) {
                Boolean a = this.f1630a.get(i).mo103a(t1, t2);
                if (a != null && a.booleanValue()) {
                    z = true;
                }
            }
            return z;
        }
    }

    static class C0422d<T1, T2, T3> {
        List<AbstractC0431l<Boolean, T1, T2, T3>> f1631a = new Vector(5);

        public void m651a(AbstractC0431l<Boolean, T1, T2, T3> lVar) {
            this.f1631a.add(lVar);
        }

        public void m650b(AbstractC0431l<Boolean, T1, T2, T3> lVar) {
            this.f1631a.remove(lVar);
        }

        public boolean m649c(T1 t1, T2 t2, T3 t3) {
            boolean z = false;
            for (int i = 0; i < this.f1631a.size(); i++) {
                Boolean a = this.f1631a.get(i).mo101a(t1, t2, t3);
                if (a != null && a.booleanValue()) {
                    z = true;
                }
            }
            return z;
        }
    }

    static class C0423e implements BBDisplay.IBBDisplayCallback {
        private BBDisplay.IBBDisplayCallback f1632a;
        private Dialog f1633b;

        public C0423e(BBDisplay.IBBDisplayCallback bVar, Dialog dialog) {
            this.f1632a = bVar;
            this.f1633b = dialog;
        }

        @Override
        public void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str) {
        }

        @Override
        public void mo133c(BBDisplay bBDisplay, BBString pVar, int i) {
        }

        @Override
        public void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
        }

        @Override
        public void mo129f(BBDisplay bBDisplay, BBString pVar, BBDisplay.C0143c cVar) {
            Dialog dialog = this.f1633b;
            if (dialog != null && cVar.f543a >= 0) {
                dialog.dismiss();
            }
            this.f1632a.mo129f(bBDisplay, pVar, cVar);
        }
    }

    static class C0424f {
        static Map<String, Integer> f1634a = new HashMap(200);

        public static void m648a() {
            f1634a.clear();
        }

        public static void m647b(String str, int i) {
            f1634a.put(str, Integer.valueOf(i));
        }

        public static int m646c(String str, int i) {
            Integer num = null;
            Integer num2 = null;
            if ('#' == str.charAt(0)) {
                try {
                    int parseInt = Integer.parseInt(str.substring(1), 16);
                    if (str.length() == 7) {
                        parseInt &= 16777215;
                    } else if (str.length() == 4) {
                        int i2 = parseInt << 8;
                        int i3 = ((parseInt << 12) & 15728640) | (983040 & i2) | (i2 & 61440);
                        int i4 = parseInt << 4;
                        parseInt = (parseInt & 15) | i3 | (i4 & 3840) | (i4 & 240);
                    }
                    i = parseInt | -16777216;
                    if (-16777216 == i) {
                        num = f1634a.get("black");
                    } else if (-1 == i) {
                        num = f1634a.get("white");
                    }
                    num2 = num;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                num2 = f1634a.get(str.toLowerCase());
            }
            return num2 != null ? num2.intValue() : i;
        }
    }

    static class C0425g {
        static C0426a f1636b;
        static C0426a f1637c;
        @SuppressLint("WrongConstant")
        static C0426a f1635a = new C0426a(Typeface.defaultFromStyle(0), Typeface.defaultFromStyle(1), Typeface.defaultFromStyle(2), Typeface.defaultFromStyle(3));
        static Map<String, Integer> f1638d = new HashMap(30);
        static Map<Integer, C0426a> f1639e = new HashMap(30);

        static class C0426a {
            Typeface f1640a;
            Typeface f1641b;
            Typeface f1642c;
            Typeface f1643d;
            boolean f1644e;
            boolean f1645f;
            boolean f1646g;
            boolean f1647h;

            C0426a(Typeface typeface, Typeface typeface2, Typeface typeface3, Typeface typeface4) {
                this.f1640a = typeface;
                this.f1641b = typeface2;
                this.f1642c = typeface3;
                this.f1643d = typeface4;
                int style = typeface2.getStyle();
                boolean z = true;
                this.f1644e = (style & 1) == 0;
                this.f1645f = (this.f1642c.getStyle() & 2) == 0;
                this.f1646g = (this.f1643d.getStyle() & 1) == 0;
                this.f1647h = (this.f1643d.getStyle() & 2) != 0 ? false : z;
            }
        }

        static {
            Typeface typeface = Util.robotoTypeface;
            f1636b = new C0426a(typeface, typeface, typeface, typeface);
            Typeface typeface2 = Util.fontelloTypeface;
            f1637c = new C0426a(typeface2, typeface2, typeface2, typeface2);
            f1638d.put("monospace", 65536);
            f1639e.put(65536, f1636b);
            f1638d.put("fontello", 131072);
            f1639e.put(131072, f1637c);
        }

        public static C0426a m645a(int i) {
            C0426a aVar;
            if (i <= 0 || (aVar = f1639e.get(Integer.valueOf(i & -65536))) == null) {
                return f1635a;
            }
            return aVar;
        }

        @SuppressLint("WrongConstant")
        public static int m644b(String str) {
            Integer num = f1638d.get(str);
            if (num == null) {
                num = Integer.valueOf(((f1639e.size() + 1) << 16) & -65536);
                f1638d.put(str, num);
                f1639e.put(num, new C0426a(Typeface.create(str, 0), Typeface.create(str, 1), Typeface.create(str, 2), Typeface.create(str, 3)));
            }
            return num.intValue();
        }
    }

    public static class C0427h {
        static Map<String, Integer> f1648a;
        static StringBuilder f1649b = new StringBuilder(8);
        static StringBuilder f1650c = new StringBuilder(1024);

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(252);
            f1648a = hashMap;
            hashMap.put("quot", 34);
            f1648a.put("amp", 38);
            f1648a.put("lt", 60);
            f1648a.put("gt", 62);
            f1648a.put("nbsp", 160);
            f1648a.put("iexcl", 161);
            f1648a.put("cent", 162);
            f1648a.put("pound", 163);
            f1648a.put("curren", 164);
            f1648a.put("yen", 165);
            f1648a.put("brvbar", 166);
            f1648a.put("sect", 167);
            f1648a.put("uml", 168);
            f1648a.put("copy", 169);
            f1648a.put("ordf", 170);
            f1648a.put("laquo", 171);
            f1648a.put("not", 172);
            f1648a.put("shy", 173);
            f1648a.put("reg", 174);
            f1648a.put("macr", 175);
            f1648a.put("deg", 176);
            f1648a.put("plusmn", 177);
            f1648a.put("sup2", 178);
            f1648a.put("sup3", 179);
            f1648a.put("acute", 180);
            f1648a.put("micro", 181);
            f1648a.put("para", 182);
            f1648a.put("middot", 183);
            f1648a.put("cedil", 184);
            f1648a.put("sup1", 185);
            f1648a.put("ordm", 186);
            f1648a.put("raquo", 187);
            f1648a.put("frac14", 188);
            f1648a.put("frac12", 189);
            f1648a.put("frac34", 190);
            f1648a.put("iquest", 191);
            f1648a.put("Agrave", 192);
            f1648a.put("Aacute", 193);
            f1648a.put("Acirc", 194);
            f1648a.put("Atilde", 195);
            f1648a.put("Auml", 196);
            f1648a.put("Aring", 197);
            f1648a.put("AElig", 198);
            f1648a.put("Ccedil", 199);
            f1648a.put("Egrave", 200);
            f1648a.put("Eacute", 201);
            f1648a.put("Ecirc", 202);
            f1648a.put("Euml", 203);
            f1648a.put("Igrave", 204);
            f1648a.put("Iacute", 205);
            f1648a.put("Icirc", 206);
            f1648a.put("Iuml", 207);
            f1648a.put("ETH", 208);
            f1648a.put("Ntilde", 209);
            f1648a.put("Ograve", 210);
            f1648a.put("Oacute", 211);
            f1648a.put("Ocirc", 212);
            f1648a.put("Otilde", 213);
            f1648a.put("Ouml", 214);
            f1648a.put("times", 215);
            f1648a.put("Oslash", 216);
            f1648a.put("Ugrave", 217);
            f1648a.put("Uacute", 218);
            f1648a.put("Ucirc", 219);
            f1648a.put("Uuml", 220);
            f1648a.put("Yacute", 221);
            f1648a.put("THORN", 222);
            f1648a.put("szlig", 223);
            f1648a.put("agrave", 224);
            f1648a.put("aacute", 225);
            f1648a.put("acirc", 226);
            f1648a.put("atilde", 227);
            f1648a.put("auml", 228);
            f1648a.put("aring", 229);
            f1648a.put("aelig", 230);
            f1648a.put("ccedil", 231);
            f1648a.put("egrave", 232);
            f1648a.put("eacute", 233);
            f1648a.put("ecirc", 234);
            f1648a.put("euml", 235);
            f1648a.put("igrave", 236);
            f1648a.put("iacute", 237);
            f1648a.put("icirc", 238);
            f1648a.put("iuml", 239);
            f1648a.put("eth", 240);
            f1648a.put("ntilde", 241);
            f1648a.put("ograve", 242);
            f1648a.put("oacute", 243);
            f1648a.put("ocirc", 244);
            f1648a.put("otilde", 245);
            f1648a.put("ouml", 246);
            f1648a.put("divide", 247);
            f1648a.put("oslash", 248);
            f1648a.put("ugrave", 249);
            f1648a.put("uacute", 250);
            f1648a.put("ucirc", 251);
            f1648a.put("uuml", 252);
            f1648a.put("yacute", 253);
            f1648a.put("thorn", 254);
            f1648a.put("yuml", 255);
            f1648a.put("fnof", 402);
            f1648a.put("Alpha", 913);
            f1648a.put("Beta", 914);
            f1648a.put("Gamma", 915);
            f1648a.put("Delta", 916);
            f1648a.put("Epsilon", 917);
            f1648a.put("Zeta", 918);
            f1648a.put("Eta", 919);
            f1648a.put("Theta", 920);
            f1648a.put("Iota", 921);
            f1648a.put("Kappa", 922);
            f1648a.put("Lambda", 923);
            f1648a.put("Mu", 924);
            f1648a.put("Nu", 925);
            f1648a.put("Xi", 926);
            f1648a.put("Omicron", 927);
            f1648a.put("Pi", 928);
            f1648a.put("Rho", 929);
            f1648a.put("Sigma", 931);
            f1648a.put("Tau", 932);
            f1648a.put("Upsilon", 933);
            f1648a.put("Phi", 934);
            f1648a.put("Chi", 935);
            f1648a.put("Psi", 936);
            f1648a.put("Omega", 937);
            f1648a.put("alpha", 945);
            f1648a.put("beta", 946);
            f1648a.put("gamma", 947);
            f1648a.put("delta", 948);
            f1648a.put("epsilon", 949);
            f1648a.put("zeta", 950);
            f1648a.put("eta", 951);
            f1648a.put("theta", 952);
            f1648a.put("iota", 953);
            f1648a.put("kappa", 954);
            f1648a.put("lambda", 955);
            f1648a.put("mu", 956);
            f1648a.put("nu", 957);
            f1648a.put("xi", 958);
            f1648a.put("omicron", 959);
            f1648a.put("pi", 960);
            f1648a.put("rho", 961);
            f1648a.put("sigmaf", 962);
            f1648a.put("sigma", 963);
            f1648a.put("tau", 964);
            f1648a.put("upsilon", 965);
            f1648a.put("phi", 966);
            f1648a.put("chi", 967);
            f1648a.put("psi", 968);
            f1648a.put("omega", 969);
            f1648a.put("thetasym", 977);
            f1648a.put("upsih", 978);
            f1648a.put("piv", 982);
            f1648a.put("bull", 8226);
            f1648a.put("hellip", 8230);
            f1648a.put("prime", 8242);
            f1648a.put("Prime", 8243);
            f1648a.put("oline", 8254);
            f1648a.put("frasl", 8260);
            f1648a.put("weierp", 8472);
            f1648a.put("image", 8465);
            f1648a.put("real", 8476);
            f1648a.put("trade", 8482);
            f1648a.put("alefsym", 8501);
            f1648a.put("larr", 8592);
            f1648a.put("uarr", 8593);
            f1648a.put("rarr", 8594);
            f1648a.put("darr", 8595);
            f1648a.put("harr", 8596);
            f1648a.put("crarr", 8629);
            f1648a.put("lArr", 8656);
            f1648a.put("uArr", 8657);
            f1648a.put("rArr", 8658);
            f1648a.put("dArr", 8659);
            f1648a.put("hArr", 8660);
            f1648a.put("forall", 8704);
            f1648a.put("part", 8706);
            f1648a.put("exist", 8707);
            f1648a.put("empty", 8709);
            f1648a.put("nabla", 8711);
            f1648a.put("isin", 8712);
            f1648a.put("notin", 8713);
            f1648a.put("ni", 8715);
            f1648a.put("prod", 8719);
            f1648a.put("sum", 8721);
            f1648a.put("minus", 8722);
            f1648a.put("lowast", 8727);
            f1648a.put("radic", 8730);
            f1648a.put("prop", 8733);
            f1648a.put("infin", 8734);
            f1648a.put("ang", 8736);
            f1648a.put("and", 8743);
            f1648a.put("or", 8744);
            f1648a.put("cap", 8745);
            f1648a.put("cup", 8746);
            f1648a.put("int", 8747);
            f1648a.put("there4", 8756);
            f1648a.put("sim", 8764);
            f1648a.put("cong", 8773);
            f1648a.put("asymp", 8776);
            f1648a.put("ne", 8800);
            f1648a.put("equiv", 8801);
            f1648a.put("le", 8804);
            f1648a.put("ge", 8805);
            f1648a.put("sub", 8834);
            f1648a.put("sup", 8835);
            f1648a.put("sube", 8838);
            f1648a.put("supe", 8839);
            f1648a.put("oplus", 8853);
            f1648a.put("otimes", 8855);
            f1648a.put("perp", 8869);
            f1648a.put("sdot", 8901);
            f1648a.put("lceil", 8968);
            f1648a.put("rceil", 8969);
            f1648a.put("lfloor", 8970);
            f1648a.put("rfloor", 8971);
            f1648a.put("lang", 9001);
            f1648a.put("rang", 9002);
            f1648a.put("loz", 9674);
            f1648a.put("spades", 9824);
            f1648a.put("clubs", 9827);
            f1648a.put("hearts", 9829);
            f1648a.put("diams", 9830);
            f1648a.put("OElig", 338);
            f1648a.put("oelig", 339);
            f1648a.put("Scaron", 352);
            f1648a.put("scaron", 353);
            f1648a.put("Yuml", 376);
            f1648a.put("circ", 710);
            f1648a.put("tilde", 732);
            f1648a.put("ensp", 8194);
            f1648a.put("emsp", 8195);
            f1648a.put("thinsp", 8201);
            f1648a.put("zwnj", 8204);
            f1648a.put("zwj", 8205);
            f1648a.put("lrm", 8206);
            f1648a.put("rlm", 8207);
            f1648a.put("ndash", 8211);
            f1648a.put("mdash", 8212);
            f1648a.put("lsquo", 8216);
            f1648a.put("rsquo", 8217);
            f1648a.put("sbquo", 8218);
            f1648a.put("ldquo", 8220);
            f1648a.put("rdquo", 8221);
            f1648a.put("bdquo", 8222);
            f1648a.put("dagger", 8224);
            f1648a.put("Dagger", 8225);
            f1648a.put("permil", 8240);
            f1648a.put("lsaquo", 8249);
            f1648a.put("rsaquo", 8250);
            f1648a.put("euro", 8364);
        }

        static int m643a() {
            Integer num = f1648a.get(f1649b.toString());
            if (num != null) {
                return num;
            }
            return -1;
        }

        public static int m642b(String str) {
            Integer num = f1648a.get(str);
            if (num != null) {
                return num;
            }
            return -1;
        }

        public static String UnEscapeString(String str) {
            char charAt = 0;
            int i = 0;
            int i2 = 0;
            try {
                f1650c.setLength(0);
                int length = str.length();
                int i3 = 0;
                boolean z = false;
                while (i3 < length) {
                    charAt = str.charAt(i3);
                    if ('&' == charAt) {
                        f1649b.setLength(0);
                        int i4 = i3 + 1;
                        if (i4 < length) {
                            int i5 = i4 + 8;
                            if (i5 > length) {
                                i5 = length;
                            }
                            boolean z2 = str.charAt(i4) == '#';
                        } else if (z) {
                            f1650c.append((char) charAt);
                        }
                    } else if (z) {
                        f1650c.append((char) charAt);
                    }
                    i3++;
                }
                //return z ? f1650c.toString() : str;
                if (i2 > 0) {
                    if (i2 <= 65535) {
                        f1650c.append((char) i2);
                    } else if (i2 <= 1114111) {
                        f1650c.append(new char[]{(char) ((i2 >> 10) + 55232), (char) ((i2 & 1023) + 56320)});
                    } else {
                        f1650c.append(' ');
                    }
                    i3 += i;
                } else if (z) {
                    f1650c.append((char) charAt);
                }
                i3++;
                return z ? f1650c.toString() : str;
            } catch (Exception e) {
                e.printStackTrace();
               // ACRA.getErrorReporter().handleSilentException(new Exception("UnEscape", e));
                return str;
            }
        }

        public static String m640d(String str) {
            try {
                String decode = URLDecoder.decode(str, "windows-1251");
                if (-1 != decode.indexOf(65533)) {
                    String decode2 = URLDecoder.decode(str, "utf-8");
                    if (-1 == decode2.indexOf(65533)) {
                        decode = decode2;
                    }
                }
                str = decode;
            } catch (Exception e) {
               /* ACRA.getErrorReporter().putCustomData("extra", str);
                ACRA.getErrorReporter().handleSilentException(e);
                ACRA.getErrorReporter().removeCustomData("extra");*/
                e.printStackTrace();
            }
            return str.replace("&amp;", "&");
        }
    }

    static class C0428i<First, Second> {
        final First f1651a;
        final Second f1652b;

        public C0428i(First first, Second second) {
            this.f1651a = first;
            this.f1652b = second;
        }
    }

    public interface AbstractC0429j<T1, T2> {
        T1 mo222a(T2 t2);
    }

    public interface AbstractC0430k<T1, T2, T3> {
        T1 mo103a(T2 t2, T3 t3);
    }

    interface AbstractC0431l<T1, T2, T3, T4> {
        T1 mo101a(T2 t2, T3 t3, T4 t4);
    }

    @SuppressLint("WrongConstant")
    public static void copyToClipboard(Activity activity, String str, String str2) {
        if (Build.VERSION.SDK_INT < 11) {
            ((ClipboardManager) activity.getSystemService("clipboard")).setText(str);
        } else {
            ((android.content.ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied data", str));
        }
        if (str2 != null) {
            Toast.makeText(activity, str2, Toast.LENGTH_LONG).show();
        }
    }

    public static int m672b(String str, int i, float f, boolean z) {
        if (i <= 0) {
            return 0;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        textPaint.setFakeBoldText(z);
        if (z) {
            textPaint.setTypeface(C0425g.f1635a.f1641b);
        }
        return new StaticLayout(str, textPaint, i, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getHeight();
    }

    public static int m671c() {
        return f1625c.getAndIncrement();
    }

    @SuppressLint("DefaultLocale")
    public static String formatFileSize(long j) {
        if (j > 1048576) {
            double d = (double) j;
            Double.isNaN(d);
            return String.format("%.2f МБ", d / 1048576.0d);
        } else if (j > 1024) {
            double d2 = (double) j;
            Double.isNaN(d2);
            return String.format("%.2f КБ", d2 / 1024.0d);
        } else {
            return j + " Б";
        }
    }

    public static int m669e(String str, char[] cArr, int i, int i2) {
        if (!TextUtils.isEmpty(str) && !isEmpty(cArr)) {
            int max = Math.max(0, Math.min(i, str.length()));
            for (int i3 = max; i3 < Math.min(max + i2, str.length()); i3++) {
                char charAt = str.charAt(i3);
                for (char c : cArr) {
                    if (c == charAt) {
                        return i3;
                    }
                }
            }
        }
        return -1;
    }

    public static int m668f(char[] cArr, char[] cArr2, int i, int i2) {
        if (!isEmpty(cArr) && !isEmpty(cArr2)) {
            int max = Math.max(0, Math.min(i, cArr.length));
            for (int i3 = max; i3 < Math.min(max + i2, cArr.length); i3++) {
                char c = cArr[i3];
                for (char c2 : cArr2) {
                    if (c2 == c) {
                        return i3;
                    }
                }
            }
        }
        return -1;
    }

    static boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    public static int m666h(String str, char[] cArr, int i, int i2) {
        if (!TextUtils.isEmpty(str) && !isEmpty(cArr)) {
            int max = Math.max(0, Math.min(i, str.length()));
            for (int i3 = max; i3 > Math.max(0, max - i2); i3--) {
                char charAt = str.charAt(i3);
                for (char c : cArr) {
                    if (c == charAt) {
                        return i3;
                    }
                }
            }
        }
        return -1;
    }

    public static int m665i(char[] cArr, char[] cArr2, int i, int i2) {
        if (!isEmpty(cArr) && !isEmpty(cArr2)) {
            int max = Math.max(0, Math.min(i, cArr.length));
            for (int i3 = max; i3 > Math.max(0, max - i2); i3--) {
                char c = cArr[i3];
                for (char c2 : cArr2) {
                    if (c2 == c) {
                        return i3;
                    }
                }
            }
        }
        return -1;
    }

    public static void sendMail(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", str, null));
        intent.putExtra("android.intent.extra.EMAIL", str);
        intent.putExtra("android.intent.extra.SUBJECT", URLEncoder.encode("4PDA " + str2));
        context.startActivity(Intent.createChooser(intent, "Отправить " + str));
    }

    public static synchronized int m663k(Context context, BBString pVar, int i) {
        int k;
        synchronized (Util.class) {
            if (bbDisplay == null) {
                bbDisplay = new BBDisplay(context);
            }
            bbDisplay.setBBString(pVar);
            k = bbDisplay.m970k(i);
            bbDisplay.setBBString(null);
        }
        return k;
    }

    public static String m662l(int i, String str, String str2, String str3) {
        int i2 = i % 100;
        if (i2 < 5 || i2 > 20) {
            int i3 = i2 % 10;
            if (1 == i3) {
                return str;
            }
            if (i3 >= 2 && i3 <= 4) {
                return str2;
            }
        }
        return str3;
    }

    public static String formatDate(int i) {
        return formatDate(i, true, true);
    }

    public static String formatDate(int i, boolean z, boolean z2) {
        String str;
        Calendar instance = Calendar.getInstance();
        String str2 = "";
        if (z) {
            instance.setTimeInMillis(System.currentTimeMillis());
            str2 = String.format("%d.%02d.%02d", Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(1) % 100));
            instance.setTimeInMillis(System.currentTimeMillis() - 86400000);
            str = String.format("%d.%02d.%02d", Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(1) % 100));
        } else {
            str = str2;
        }
        instance.setTimeInMillis(((long) i) * 1000);
        String format = String.format("%d.%02d.%02d", Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(1) % 100));
        if (z) {
            if (format.equals(str2)) {
                format = "Сегодня";
            } else if (format.equals(str)) {
                format = "Вчера";
            }
        }
        return z2 ? String.format("%s, %d:%02d", format, Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12))) : format;
    }

    static class SmileClass {
        static List<SmileClass> smilesList = new Vector(200);
        String f1654a;
        int f1655b;
        int f1656c;
        int f1657d;
        String f1658e;
        int f1659f;
        int f1660g;
        boolean f1661h;
        char f1662i;
        char f1663j;

        SmileClass(String str, int i, int i2, int i3, String str2, int i4, int i5, boolean z) {
            this.f1654a = str;
            this.f1655b = i;
            this.f1656c = i2;
            this.f1657d = i3;
            this.f1658e = str2;
            this.f1659f = i4;
            this.f1660g = i5;
            this.f1661h = z;
            this.f1662i = str.charAt(0);
            this.f1663j = str.charAt(1);
        }

        static void addSmile(SmileClass mVar) {
            smilesList.add(mVar);
        }

        public static void initSmiles() {
            smilesList.clear();
            addSmile(new SmileClass(":boo!:", R.drawable.st_vampire_035, 53, 33, "vampire", 53, 36, true));
            addSmile(new SmileClass(":happy:", R.drawable.st_happy, 18, 18));
            addSmile(new SmileClass(":P", R.drawable.st_blum2_000, 18, 18, "blum2", 18, 18, false));
            addSmile(new SmileClass(":-P", R.drawable.st_blum2_000, 18, 18, "blum2", 18, 18, true));
            addSmile(new SmileClass(":tongue3:", R.drawable.st_blum2_000, 18, 18, "blum2", 18, 18, true));
            addSmile(new SmileClass(":D", R.drawable.st_biggrin_000, 18, 18, "biggrin", 18, 19, false));
            addSmile(new SmileClass(":-D", R.drawable.st_biggrin_000, 18, 18, "biggrin", 18, 19, true));
            addSmile(new SmileClass(":lol:", R.drawable.st_lol, 18, 18));
            addSmile(new SmileClass(":laugh:", R.drawable.st_lol, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass(":roll:", R.drawable.st_lol, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass(":rolleyes:", R.drawable.st_rolleyes, 18, 18));
            addSmile(new SmileClass(":beee:", R.drawable.st_beee_003, 18, 18, "beee", 19, 18, false));
            addSmile(new SmileClass(":rofl:", R.drawable.st_rofl_104, 26, 18, "rofl", 27, 23, false));
            addSmile(new SmileClass(":sveta:", R.drawable.st_sveta, 17, 19));
            addSmile(new SmileClass(":thank_you:", R.drawable.st_thank_you2_008, 27, 25, "thank_you2", 35, 27, false));
            addSmile(new SmileClass("}-)", R.drawable.st_diablo_funny, 29, 21));
            addSmile(new SmileClass(":kindness:", R.drawable.st_kindness, 57, 41));
            addSmile(new SmileClass(":blush:", R.drawable.st_blush_017, 22, 22, "blush", 22, 22, false));
            addSmile(new SmileClass(":mellow:", R.drawable.st_mellow, 18, 18));
            addSmile(new SmileClass(":offtopic:", R.drawable.st_offtopic, 48, 39));
            addSmile(new SmileClass(":ok:", R.drawable.st_ok_009, 33, 18, "ok", 36, 23, false));
            addSmile(new SmileClass(":ohmy:", R.drawable.st__o, 18, 18));
            addSmile(new SmileClass(":o", R.drawable.st__o, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass(":O", R.drawable.st__o, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass("B)", R.drawable.st_b_, 18, 18));
            addSmile(new SmileClass("B-)", R.drawable.st_b_, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass("-_-", R.drawable.st_sleep, 17, 17));
            addSmile(new SmileClass("&lt;_&lt;", R.drawable.st_dry, 18, 18));
            addSmile(new SmileClass(":wub:", R.drawable.st_wub, 20, 26));
            addSmile(new SmileClass(":angry:", R.drawable.st_angry, 18, 26));
            addSmile(new SmileClass(":unsure:", R.drawable.st_unknown_000, 28, 18, "unknown", 31, 18, false));
            addSmile(new SmileClass(":wacko:", R.drawable.st_wacko2_000, 18, 22, "wacko2", 18, 22, false));
            addSmile(new SmileClass(":blink:", R.drawable.st_blink, 18, 18));
            addSmile(new SmileClass("o_O", R.drawable.st_blink, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass("o.O", R.drawable.st_blink, 18, 18, null, 0, 0, true));
            addSmile(new SmileClass(":ph34r:", R.drawable.st_ph34r, 18, 18));
            addSmile(new SmileClass(":banned:", R.drawable.st_banned, 52, 47));
            addSmile(new SmileClass(":antifeminism:", R.drawable.st_antifeminism, 57, 49));
            addSmile(new SmileClass(":beta:", R.drawable.st_beta, 32, 20));
            addSmile(new SmileClass(":boy_girl:", R.drawable.st_boy_girl, 34, 23));
            addSmile(new SmileClass(":butcher:", R.drawable.st_butcher, 40, 25));
            addSmile(new SmileClass(":bubble:", R.drawable.st_bubble, 22, 38));
            addSmile(new SmileClass(":censored:", R.drawable.st_censored, 34, 18));
            addSmile(new SmileClass(":clap:", R.drawable.st_clap, 27, 27));
            addSmile(new SmileClass(":close_tema:", R.drawable.st_close_tema, 39, 26));
            addSmile(new SmileClass(":clapping:", R.drawable.st_clapping_001, 18, 25, "clapping", 39, 26, false));
            addSmile(new SmileClass(":coldly:", R.drawable.st_coldly, 25, 31));
            addSmile(new SmileClass(":comando:", R.drawable.st_comando, 33, 25));
            addSmile(new SmileClass(":congratulate:", R.drawable.st_congratulate, 245, 44, null, 0, 0, true));
            addSmile(new SmileClass(":dance:", R.drawable.st_dance_035, 26, 19));
            addSmile(new SmileClass(":daisy:", R.drawable.st_daisy, 30, 33));
            addSmile(new SmileClass(":dancer:", R.drawable.st_dance4_001, 33, 22, "dance4", 35, 22, false));
            addSmile(new SmileClass(":derisive:", R.drawable.st_derisive, 17, 21));
            addSmile(new SmileClass(":dinamo:", R.drawable.st_dinamo, 44, 24));
            addSmile(new SmileClass(":dirol:", R.drawable.st_dirol_000, 18, 18, "dirol", 18, 18, false));
            addSmile(new SmileClass(":diver:", R.drawable.st_diver, 34, 28));
            addSmile(new SmileClass(":drag:", R.drawable.st_drag, 28, 22));
            addSmile(new SmileClass(":download:", R.drawable.st_download_000, 26, 26));
            addSmile(new SmileClass(":drinks:", R.drawable.st_drinks_000, 45, 26, "drinks", 50, 27, false));
            addSmile(new SmileClass(":-*", R.drawable.st_kiss3_003, 44, 22, "kiss3", 45, 22, false));
            addSmile(new SmileClass(":first_move:", R.drawable.st_kiss3_003, 44, 22, "kiss3", 45, 22, true));
            addSmile(new SmileClass(":feminist:", R.drawable.st_feminist_000, 46, 44, "feminist", 52, 47, false));
            addSmile(new SmileClass(":flood:", R.drawable.st_flood, 18, 18));
            addSmile(new SmileClass(":fool:", R.drawable.st_fool_028, 27, 18, "fool", 28, 21, false));
            addSmile(new SmileClass(":friends:", R.drawable.st_friends_000, 43, 25, "friends", 52, 27, false));
            addSmile(new SmileClass(":foto:", R.drawable.st_foto, 24, 29));
            addSmile(new SmileClass(":girl_blum:", R.drawable.st_girl_blum_004, 32, 21));
            addSmile(new SmileClass(":girl_crazy:", R.drawable.st_girl_crazy_000, 34, 22, "girl_crazy", 35, 23, false));
            addSmile(new SmileClass(":girl_curtsey:", R.drawable.st_curtsey_008, 35, 21, "curtsey", 37, 22, false));
            addSmile(new SmileClass(":girl_dance:", R.drawable.st_girl_dance_013, 32, 20));
            addSmile(new SmileClass(":girl_flirt:", R.drawable.st_flirt_005, 33, 22, "flirt", 34, 22, false));
            addSmile(new SmileClass(":girl_hospital:", R.drawable.st_girl_hospital_012, 33, 23, "girl_hospital", 40, 23, false));
            addSmile(new SmileClass(":girl_hysterics:", R.drawable.st_girl_cray2_001, 35, 21, "girl_cray2", 36, 22, false));
            addSmile(new SmileClass(":girl_cray:", R.drawable.st_girl_cray, 32, 20));
            addSmile(new SmileClass(":girl_in_love:", R.drawable.st_girl_in_love_005, 33, 21, "girl_in_love", 36, 25, false));
            addSmile(new SmileClass("*-:", R.drawable.st_girl_kiss, 32, 19, "kiss2", 33, 21, false));
            addSmile(new SmileClass(":girl_kiss:", R.drawable.st_girl_kiss, 32, 19, "ok", 33, 21, true));
            addSmile(new SmileClass(":girl_pinkglassesf:", R.drawable.st_girl_pinkglasses_005, 33, 21, "girl_pinkglasses", 34, 36, false));
            addSmile(new SmileClass(":girl_parting:", R.drawable.st_girl_parting, 32, 22));
            addSmile(new SmileClass(":girl_prepare_fish:", R.drawable.st_girl_prepare_fish, 43, 22));
            addSmile(new SmileClass(":good:", R.drawable.st_good_006, 23, 20, "good", 24, 21, false));
            addSmile(new SmileClass(":girl_spruce_up:", R.drawable.st_spruce_up_010, 33, 20, "spruce_up", 35, 23, false));
            addSmile(new SmileClass(":girl_tear:", R.drawable.st_girl_tear, 32, 21));
            addSmile(new SmileClass(":girl_tender:", R.drawable.st_tender_007, 33, 19, "tender", 35, 21, false));
            addSmile(new SmileClass(":girl_teddy:", R.drawable.st_girl_teddy, 33, 22));
            addSmile(new SmileClass(":girl_to_babruysk:", R.drawable.st_girl_to_babruysk, 49, 28));
            addSmile(new SmileClass(":girl_to_take_umbrage:", R.drawable.st_girl_to_take_umbrage, 32, 21));
            addSmile(new SmileClass(":girl_triniti:", R.drawable.st_girl_triniti, 33, 21));
            addSmile(new SmileClass(":girl_wacko:", R.drawable.st_girl_wacko, 34, 23));
            addSmile(new SmileClass(":girl_werewolf:", R.drawable.st_girl_werewolf, 44, 25));
            addSmile(new SmileClass(":girl_witch:", R.drawable.st_girl_witch, 45, 26));
            addSmile(new SmileClass(":grabli:", R.drawable.st_grabli, 28, 26));
            addSmile(new SmileClass(":good_luck:", R.drawable.st_good_luck, 29, 22));
            addSmile(new SmileClass(":guess:", R.drawable.st_guess, 27, 35));
            addSmile(new SmileClass(":hang:", R.drawable.st_hang2_000, 23, 35, "hang2", 25, 35, false));
            addSmile(new SmileClass(":heart:", R.drawable.st_heart_000, 16, 15, "heart", 20, 20, false));
            addSmile(new SmileClass(":help:", R.drawable.st_help_028, 28, 31, "help", 28, 31, false));
            addSmile(new SmileClass(":helpsmilie:", R.drawable.st_helpsmilie, 25, 18));
            addSmile(new SmileClass(":hemp:", R.drawable.st_hemp, 57, 21));
            addSmile(new SmileClass(":heppy_dancing:", R.drawable.st_heppy_dancing, 37, 22));
            addSmile(new SmileClass(":hysterics:", R.drawable.st_hysterics, 35, 22));
            addSmile(new SmileClass(":indeec:", R.drawable.st_indeec, 37, 27));
            addSmile(new SmileClass(":i-m_so_happy:", R.drawable.st_i_am_so_happy_001, 18, 20, "i-m_so_happy", 24, 22, false));
            addSmile(new SmileClass(":king:", R.drawable.st_king_000, 27, 28, "king", 30, 32, false));
            addSmile(new SmileClass(":laugh_wild:", R.drawable.st_laugh_wild, 18, 23));
            addSmile(new SmileClass(":4PDA:", R.drawable.st_love_4pda, 58, 43));
            addSmile(new SmileClass(":nea:", R.drawable.st_nea_006, 18, 21, "nea", 18, 22, false));
            addSmile(new SmileClass(":moil:", R.drawable.st_moil_000, 37, 30, "moil", 40, 35, false));
            addSmile(new SmileClass(":nono:", R.drawable.st_nono, 23, 25));
            addSmile(new SmileClass(":no:", R.drawable.st_no, 30, 33));
            addSmile(new SmileClass(":papuas:", R.drawable.st_mamba_000, 19, 23, "mamba", 27, 23, false));
            addSmile(new SmileClass(":party:", R.drawable.st_party2_004, 56, 22, "party2", 56, 25, false));
            addSmile(new SmileClass(":pioneer_smoke:", R.drawable.st_pioneer_smoke, 26, 30));
            addSmile(new SmileClass(":pipiska:", R.drawable.st_pipiska, 36, 34, "censored", 39, 30, false));
            addSmile(new SmileClass(":protest:", R.drawable.st_protest, 49, 30));
            addSmile(new SmileClass(":popcorm:", R.drawable.st_popcorn1_000, 34, 26, "popcorn1", 35, 26, false));
            addSmile(new SmileClass(":rabbi:", R.drawable.st_rabbi, 27, 28));
            addSmile(new SmileClass(":resent:", R.drawable.st_resent, 17, 21));
            addSmile(new SmileClass(":rtfm:", R.drawable.st_rtfm, 24, 24));
            addSmile(new SmileClass(":russian_garmoshka:", R.drawable.st_russian_garmoshka, 31, 36));
            addSmile(new SmileClass(":russian:", R.drawable.st_russian, 38, 38));
            addSmile(new SmileClass(":russian_ru:", R.drawable.st_russian_ru, 27, 24));
            addSmile(new SmileClass(":scratch_one-s_head:", R.drawable.st_scratch_one_s_head_012, 18, 22, "scratch_one-s_head", 25, 22, false));
            addSmile(new SmileClass(":scare:", R.drawable.st_scare_005, 40, 24, "scare", 40, 30, false));
            addSmile(new SmileClass(":search:", R.drawable.st_search_003, 19, 18, "search", 37, 23, false));
            addSmile(new SmileClass(":secret:", R.drawable.st_secret_008, 20, 18, "secret", 20, 24, false));
            addSmile(new SmileClass(":skull:", R.drawable.st_skull, 26, 22));
            addSmile(new SmileClass(":shok:", R.drawable.st_shock_000, 18, 18, "shock", 18, 18, false));
            addSmile(new SmileClass(":sorry:", R.drawable.st_sorry2_000, 18, 20, "sorry2", 25, 23, false));
            addSmile(new SmileClass(":smoke:", R.drawable.st_smoke_000, 30, 22, "smoke", 37, 27, false));
            addSmile(new SmileClass(":spiteful:", R.drawable.st_spiteful_000, 18, 18, "spiteful", 18, 18, false));
            addSmile(new SmileClass(":stop_flood:", R.drawable.st_stop_flood, 43, 50));
            addSmile(new SmileClass(":suicide:", R.drawable.st_suicide, 30, 23));
            addSmile(new SmileClass(":stop_holywar:", R.drawable.st_stop_holywar, 41, 42));
            addSmile(new SmileClass(":superman:", R.drawable.st_superman, 31, 21));
            addSmile(new SmileClass(":superstition:", R.drawable.st_superstition, 29, 19));
            addSmile(new SmileClass(":tablet_za:", R.drawable.st_tablet_za, 43, 50));
            addSmile(new SmileClass(":tablet_protiv:", R.drawable.st_tablet_protiv, 43, 50));
            addSmile(new SmileClass(":this:", R.drawable.st_this_016, 20, 23, "this", 22, 23, false));
            addSmile(new SmileClass(":tomato:", R.drawable.st_tomato, 38, 21));
            addSmile(new SmileClass(":to_clue:", R.drawable.st_to_clue, 28, 21));
            addSmile(new SmileClass(":tommy:", R.drawable.st_tommy, 30, 24));
            addSmile(new SmileClass(":umnik:", R.drawable.st_umnik, 20, 19));
            addSmile(new SmileClass(":victory:", R.drawable.st_victory_004, 26, 20, "victory", 27, 20, false));
            addSmile(new SmileClass(":vinsent:", R.drawable.st_vinsent, 34, 28));
            addSmile(new SmileClass(":wallbash:", R.drawable.st_dash1_000, 27, 23, "dash1", 49, 25, false));
            addSmile(new SmileClass(":whistle:", R.drawable.st_whistle, 24, 21));
            addSmile(new SmileClass(":yahoo:", R.drawable.st_yahoo_001, 23, 22, "yahoo", 38, 25, false));
            addSmile(new SmileClass(":yes:", R.drawable.st_yes3_007, 18, 20, "yes3", 19, 23, false));
            addSmile(new SmileClass(":yes2:", R.drawable.st_yes3_007, 18, 20, "yes3", 19, 23, true));
            addSmile(new SmileClass("]-:{", R.drawable.st_girl_devil_000, 28, 20));
            addSmile(new SmileClass("&#93;-:{", R.drawable.st_girl_devil_000, 28, 20, null, 0, 0, true));
            addSmile(new SmileClass(":-{", R.drawable.st_mad, 18, 18));
            addSmile(new SmileClass("=^.^=", R.drawable.st_kitten, 22, 18));
            addSmile(new SmileClass("(-=", R.drawable.st_girl_hide, 32, 21));
            addSmile(new SmileClass("(-;", R.drawable.st_girl_wink, 32, 21));
            addSmile(new SmileClass(")-:{", R.drawable.st_girl_angry, 32, 21));
            addSmile(new SmileClass(")-:", R.drawable.st_girl_sad, 32, 21));
            addSmile(new SmileClass(":'(", R.drawable.st_cry, 28, 18, "cray", 29, 21, false));
            addSmile(new SmileClass(":&#39;(", R.drawable.st_cry, 28, 18, "cray", 29, 21, true));
            addSmile(new SmileClass(":'-(", R.drawable.st_cry, 28, 18, "cray", 29, 21, true));
            addSmile(new SmileClass(":&#39;-(", R.drawable.st_cry, 28, 18, "cray", 29, 21, true));
            addSmile(new SmileClass(":girl_mad:", R.drawable.st_girl_mad, 32, 21));
            addSmile(new SmileClass("(-:", R.drawable.st_girl_sigh_000, 33, 21));
            addSmile(new SmileClass(":acute:", R.drawable.st_acute_014, 24, 21, "acute", 25, 22, false));
            addSmile(new SmileClass(":aggressive:", R.drawable.st_aggressive_008, 35, 20, "aggressive", 35, 23, false));
            addSmile(new SmileClass(":air_kiss:", R.drawable.st_air_kiss_005, 18, 21, "air_kiss", 22, 25, false));
            addSmile(new SmileClass(":-&#91;", R.drawable.st_confusion, 18, 18));
            addSmile(new SmileClass(":lol_girl:", R.drawable.st_girl_haha_000, 33, 20, "girl_haha", 34, 20, false));
            addSmile(new SmileClass(":(", R.drawable.st_sad_000, 18, 22, "sad", 18, 22, false));
            addSmile(new SmileClass(":-(", R.drawable.st_sad_000, 18, 22, "sad", 18, 22, true));
            addSmile(new SmileClass(":)", R.drawable.st_smile_000, 18, 22, "smile", 18, 22, false));
            addSmile(new SmileClass(":-)", R.drawable.st_smile_000, 18, 22, "smile", 18, 22, true));
            addSmile(new SmileClass(":smile:", R.drawable.st_smile_000, 18, 22, "smile", 18, 22, true));
            addSmile(new SmileClass(";)", R.drawable.st_wink_004, 18, 18, "wink", 18, 18, false));
            addSmile(new SmileClass(";-)", R.drawable.st_wink_004, 18, 18, "wink", 18, 18, true));
            addSmile(new SmileClass(":wink_kind:", R.drawable.st_wink_004, 18, 18, "wink", 18, 18, true));
            addSmile(new SmileClass("@}-'-,-", R.drawable.st_give_rose_012, 25, 24, "give_rose", 27, 25, false));
            addSmile(new SmileClass("@}-&#39;-,-", R.drawable.st_give_rose_012, 25, 24, "give_rose", 27, 25, true));
            addSmile(new SmileClass("&#64;}-'-,-", R.drawable.st_give_rose_012, 25, 24, "give_rose", 27, 25, true));
            addSmile(new SmileClass("&#64;}-&#39;-,-", R.drawable.st_give_rose_012, 25, 24, "give_rose", 27, 25, true));
        }

        SmileClass(String str, int i, int i2, int i3) {
            this(str, i, i2, i3, null, 0, 0, false);
        }
    }
}
