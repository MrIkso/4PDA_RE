package ru.fourpda.client;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class BBString {
    protected static C0692u f2157A0;
    protected static C0672c f2158B0;
    static C0676g f2159C0;
    protected static C0675f f2160D0;
    protected static C0684n f2161E0;
    protected static C0673d f2162F0;
    protected static C0683m f2163G0;
    static int f2177j0;
    static int f2178k0;
    private static boolean f2179l0;
    protected static float f2188u0;
    protected static float f2189v0;
    protected static C0691t f2190w0;
    protected static C0671b f2191x0;
    protected static C0674e f2192y0;
    protected static C0688q f2193z0;
    protected C0688q f2194A;
    protected C0692u f2195B;
    protected C0672c f2196C;
    C0676g f2197D;
    protected C0675f f2198E;
    protected C0684n f2199F;
    protected C0673d f2200G;
    protected C0683m f2201H;
    int f2203J;
    int f2204K;
    String[] f2205L;
    int f2206M;
    int f2207N;
    C0677h[] f2208O;
    protected C0670a[] f2212S;
    int f2213T;
    float f2214U;
    float f2215V;
    public int f2216W;
    public int f2217X;
    public int f2218Y;
    public int f2219Z;
    C0685o[] f2220a;
    public Object f2221a0;
    int f2222b;
    int f2223c;
    boolean f2225e;
    int f2226f;
    int f2227g;
    int f2228h;
    int f2229i;
    int f2230j;
    char[] f2231k;
    SparseBooleanArray f2232l;
    int f2233m;
    int f2234n;
    int f2235o;
    int f2236p;
    int f2237q;
    int f2238r;
    int f2239s;
    protected C0671b f2245y;
    public C0674e f2246z;
    static int[] f2169b0 = new int[1000];
    static int[] f2170c0 = new int[1000];
    static int[] f2171d0 = new int[1000];
    static int[] f2172e0 = new int[1000];
    static int[] f2173f0 = new int[1000];
    static int[] f2174g0 = new int[1000];
    static int[] f2175h0 = new int[1000];
    static int[] f2176i0 = new int[1000];
    private static Stack<Boolean> f2180m0 = new Stack<>();
    static final char[] f2181n0 = {']', '=', ' ', ':'};
    protected static int f2182o0 = -16777216;
    protected static int f2183p0 = 0;
    protected static int f2184q0 = Color.argb(255, 0, 0, 139);
    protected static int f2185r0 = Color.argb(255, 255, 255, 64);
    protected static int f2186s0 = Color.argb(80, 80, 80, 128);
    protected static float f2187t0 = 0.0f;
    static int f2164H0 = 0;
    static int f2165I0 = 0;
    public static float f2166J0 = 30.0f;
    public static float f2167K0 = 30.0f;
    public static int f2168L0 = -1;
    C0686p f2224d = new C0686p();
    Stack<Integer> f2240t = new Stack<>();
    Stack<Integer> f2241u = new Stack<>();
    Stack<Integer> f2242v = new Stack<>();
    Stack<Integer> f2243w = new Stack<>();
    Stack<Integer> f2244x = new Stack<>();
    List<C0681k> f2202I = new Vector(50);
    int f2209P = 0;
    int f2210Q = 0;
    C0679j f2211R = new C0679j();

    public static class C0670a {
        public int f2247a;
        public int f2248b;
        public boolean f2249c;
        public String f2250d;
        public int f2251e;
        public int f2252f;
        public int f2253g;
        public String f2254h;
        public Object f2255i;
        public int f2256j;
    }

    public static class C0671b {
        public int f2258b;
        public float f2259c;
        public int f2260d;
        public float f2261e;
        public float f2262f;
        public float f2263g;
        public float f2264h;
        public int f2265i;
        public int f2266j;
        public float f2267k;
        public float f2268l;
        public int f2269m;
        public int f2270n;
        public String f2257a = " ";
        public int f2271o = 7;
        public int f2272p = 0;
        public int f2273q = 0;
        public int f2274r = 0;
        public int f2275s = 0;
        public int f2276t = 0;

        public C0671b() {
            this.f2258b = 200;
            this.f2259c = 200.0f;
            this.f2260d = 10;
            this.f2261e = 20.0f;
            this.f2262f = 20.0f;
            this.f2263g = 10.0f;
            this.f2264h = 10.0f;
            this.f2265i = 10;
            this.f2266j = 10;
            this.f2267k = 20.0f;
            this.f2268l = 20.0f;
            this.f2269m = 10;
            this.f2270n = 5;
            float f = BBString.f2188u0;
            this.f2258b = (int) (2.0f * f);
            this.f2259c = (float) ((int) (f * 3.0f));
            float f2 = BBString.f2189v0;
            this.f2260d = (int) (8.0f * f2);
            this.f2261e = (float) ((int) (f2 * 9.0f));
            this.f2262f = (float) ((int) (9.0f * f2));
            this.f2263g = (float) ((int) (f2 * 5.0f));
            this.f2264h = (float) ((int) (f2 * 5.0f));
            this.f2265i = (int) (f2 * 5.0f);
            this.f2266j = (int) (f2 * 5.0f);
            this.f2267k = (float) ((int) (f2 * 6.0f));
            this.f2268l = (float) ((int) (6.0f * f2));
            this.f2269m = (int) (5.0f * f2);
            this.f2270n = (int) (f2 * 2.5f);
        }
    }

    public static class C0672c extends C0671b {
        public C0672c() {
            this.f2257a = "Код";
            this.f2272p = Skin.C0353a.f1406v;
            this.f2273q = Skin.C0353a.f1407w;
            this.f2276t = Skin.C0353a.f1408x;
            this.f2274r = Skin.C0353a.f1409y;
            this.f2275s = Skin.C0353a.f1410z;
        }
    }

    public static class C0673d extends C0671b {
        public C0673d() {
            float f = BBString.f2188u0;
            this.f2260d = (int) (3.4f * f);
            this.f2271o = (int) (f * 1.0f);
            this.f2273q = Skin.C0353a.f1355K;
            this.f2276t = Skin.C0353a.f1356L;
            this.f2274r = Skin.C0353a.f1357M;
        }
    }

    public static class C0674e extends C0671b implements Cloneable {
        public C0674e() {
            this.f2260d = 0;
            float f = BBString.f2189v0;
            this.f2263g = (float) ((int) (24.0f * f));
            this.f2264h = (float) ((int) (f * 16.0f));
            this.f2265i = (int) (f * 16.0f);
            this.f2266j = (int) (f * 16.0f);
            this.f2273q = Skin.C0353a.f1393l;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class C0675f extends C0671b {
        public C0675f() {
            float f = BBString.f2188u0;
            this.f2260d = (int) (3.4f * f);
            this.f2271o = (int) (f * 1.4f);
            this.f2273q = Skin.C0353a.f1349E;
            this.f2276t = Skin.C0353a.f1350F;
            this.f2274r = Skin.C0353a.f1351G;
        }
    }

    public static class C0676g extends C0671b {
        public C0676g() {
            this.f2257a = "Скрытый текст";
            this.f2272p = Skin.C0353a.f1345A;
            this.f2273q = Skin.C0353a.f1346B;
            this.f2276t = Skin.C0353a.f1347C;
            this.f2274r = Skin.C0353a.f1348D;
        }
    }

    public class C0677h {
        public int f2277a;
        public int f2278b;
        public String f2279c;
        public int f2280d;
        public float f2281e;
        public float f2282f;
        public float f2283g;
        public float f2284h;
        public float f2285i;
        public float f2286j;
        public float f2287k;
        public float f2288l;
        boolean f2289m;
        public boolean f2290n;
        public boolean f2291o;
        public boolean f2292p;
        public boolean f2293q;

        C0677h(BBString pVar) {
        }
    }

    public static class C0678i {
        public int f2294a;
        public int f2295b;
        public int f2296c;
        public float f2297d;
        public float f2298e;
        public float f2299f;
        public float f2300g;
        public float f2301h;
    }

    public static class C0679j {
        public static long f2302g;
        public static long f2303h;
        public static long f2304i;
        public static long f2305j;
        public static long f2306k;
        public C0680a[] f2307a;
        public int f2308b;
        int f2309c;
        C0693v[] f2310d;
        public int f2311e = 50;
        int f2312f = 1;

        public static class C0680a {
            public C0678i[] f2313a;
            public int f2314b;
            public int f2315c;
        }

        C0679j() {
        }

        public boolean m462a(int i, int i2, float f, float f2) {
            f2306k++;
            int i3 = this.f2309c;
            C0693v[] vVarArr = this.f2310d;
            C0680a[] aVarArr = this.f2307a;
            int i4 = aVarArr[i3].f2315c;
            int i5 = aVarArr[i3].f2314b;
            C0678i[] iVarArr = aVarArr[i3].f2313a;
            if (i5 <= i4) {
                i5 += 20;
                C0678i[] iVarArr2 = new C0678i[i5];
                if (iVarArr != null) {
                    System.arraycopy(iVarArr, 0, iVarArr2, 0, i4);
                }
                iVarArr = iVarArr2;
            }
            if (iVarArr[i4] == null) {
                iVarArr[i4] = new C0678i();
            }
            iVarArr[i4].f2294a = i2;
            iVarArr[i4].f2295b = 0;
            iVarArr[i4].f2296c = 0;
            iVarArr[i4].f2297d = f;
            iVarArr[i4].f2299f = f2;
            if (vVarArr[i].f2355c == 0) {
                vVarArr[i].f2356d = i4;
            }
            int i6 = i4 + 1;
            this.f2312f++;
            vVarArr[i].f2355c++;
            if (vVarArr[i].f2357e > f) {
                vVarArr[i].f2357e = f;
            }
            if (vVarArr[i].f2359g < f2) {
                vVarArr[i].f2359g = f2;
            }
            C0680a[] aVarArr2 = this.f2307a;
            aVarArr2[i3].f2315c = i6;
            aVarArr2[i3].f2314b = i5;
            aVarArr2[i3].f2313a = iVarArr;
            return true;
        }

        public boolean m461b(int i, int i2, int i3, int i4, float f, float f2) {
            if (i4 < 1) {
                return false;
            }
            f2306k++;
            int i5 = this.f2309c;
            C0693v[] vVarArr = this.f2310d;
            C0680a[] aVarArr = this.f2307a;
            int i6 = aVarArr[i5].f2315c;
            int i7 = aVarArr[i5].f2314b;
            C0678i[] iVarArr = aVarArr[i5].f2313a;
            if (i7 <= i6) {
                i7 += 20;
                C0678i[] iVarArr2 = new C0678i[i7];
                if (iVarArr != null) {
                    System.arraycopy(iVarArr, 0, iVarArr2, 0, i6);
                }
                iVarArr = iVarArr2;
            }
            if (iVarArr[i6] == null) {
                iVarArr[i6] = new C0678i();
            }
            iVarArr[i6].f2294a = i2;
            iVarArr[i6].f2295b = i3;
            iVarArr[i6].f2296c = i4;
            iVarArr[i6].f2297d = f;
            iVarArr[i6].f2299f = f2;
            if (vVarArr[i].f2355c == 0) {
                vVarArr[i].f2356d = i6;
            }
            int i8 = i6 + 1;
            this.f2312f++;
            vVarArr[i].f2355c++;
            if (vVarArr[i].f2357e > f) {
                vVarArr[i].f2357e = f;
            }
            if (vVarArr[i].f2359g < f2) {
                vVarArr[i].f2359g = f2;
            }
            C0680a[] aVarArr2 = this.f2307a;
            aVarArr2[i5].f2315c = i8;
            aVarArr2[i5].f2314b = i7;
            aVarArr2[i5].f2313a = iVarArr;
            return true;
        }

        public void m460c() {
            this.f2307a = new C0680a[this.f2308b];
        }

        public void m459d(int i) {
            C0680a[] aVarArr = this.f2307a;
            int i2 = aVarArr[i].f2315c;
            int i3 = aVarArr[i].f2314b;
            if (i2 == 0) {
                aVarArr[i].f2314b = 0;
                aVarArr[i].f2313a = null;
            } else if (i3 - i2 > 8) {
                f2303h++;
                C0678i[] iVarArr = aVarArr[i].f2313a;
                int i4 = i2 + 3;
                C0678i[] iVarArr2 = new C0678i[i4];
                if (iVarArr != null) {
                    System.arraycopy(iVarArr, 0, iVarArr2, 0, i2);
                }
                C0680a[] aVarArr2 = this.f2307a;
                aVarArr2[i].f2314b = i4;
                aVarArr2[i].f2313a = iVarArr2;
            }
        }

        public void m458e(int i, C0693v[] vVarArr, int i2) {
            f2305j += (long) i2;
            this.f2309c = i;
            this.f2310d = vVarArr;
            C0680a[] aVarArr = this.f2307a;
            if (aVarArr[i] == null) {
                aVarArr[i] = new C0680a();
            }
            aVarArr[i].f2315c = 0;
            m457f();
        }

        public void m457f() {
            if (f2305j > 9223372036853775807L) {
                f2306k = 0;
                f2305j = 0;
                f2303h = 0;
                f2304i = 0;
                f2302g = 0;
            }
            int i = this.f2312f;
            if (i > 100) {
                this.f2311e = (this.f2311e * 20) / i;
                this.f2312f = 20;
            }
        }

        public void m456g(int i, int i2) {
            C0680a[] aVarArr = this.f2307a;
            int i3 = aVarArr[i].f2315c;
            int i4 = aVarArr[i].f2314b;
            if (i4 <= i3) {
                if (i3 > 0) {
                    f2304i++;
                }
                f2302g++;
                C0678i[] iVarArr = aVarArr[i].f2313a;
                if (i2 < 0) {
                    i2 = 0;
                }
                int i5 = this.f2311e / this.f2312f;
                float f = (float) i2;
                int i6 = ((int) (f + ((((float) i5) * 5.0f) - (0.09f * f)))) / i5;
                if (i6 < 0) {
                    i6 = 8;
                }
                int i7 = i4 + i6;
                C0678i[] iVarArr2 = new C0678i[i7];
                if (iVarArr != null) {
                    System.arraycopy(iVarArr, 0, iVarArr2, 0, i3);
                }
                C0680a[] aVarArr2 = this.f2307a;
                aVarArr2[i].f2314b = i7;
                aVarArr2[i].f2313a = iVarArr2;
            }
        }
    }

    public static class C0681k {
        public String link;
        public int statusCode;
        int f2319d = -1;
        int f2318c = -1;

        public C0681k(int statusCode, String link) {
            this.statusCode = statusCode;
            this.link = link;
        }
    }

    public static class C0682l {
        static char[] f2320a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        static char[] f2321b = new char[32];

        public static String m455a(int i) {
            int i2 = 32;
            do {
                i2--;
                f2321b[i2] = f2320a[i % 26];
                i /= 26;
            } while (i > 0);
            int i3 = 32 - i2;
            if (i3 > 0) {
                return new String(f2321b, i2, i3);
            }
            return null;
        }

        public static String m454b(int i) {
            if (i >= 4000) {
                return "!!!";
            }
            if (i >= 1000) {
                return "M" + m454b(i - 1000);
            } else if (i >= 900) {
                return "CM" + m454b(i - 900);
            } else if (i >= 500) {
                return "D" + m454b(i - 500);
            } else if (i >= 400) {
                return "CD" + m454b(i - 400);
            } else if (i >= 100) {
                return "C" + m454b(i - 100);
            } else if (i >= 90) {
                return "XC" + m454b(i - 90);
            } else if (i >= 50) {
                return "L" + m454b(i - 50);
            } else if (i >= 40) {
                return "XL" + m454b(i - 40);
            } else if (i >= 10) {
                return "X" + m454b(i - 10);
            } else if (i >= 9) {
                return "IX" + m454b(i - 9);
            } else if (i >= 5) {
                return "V" + m454b(i - 5);
            } else if (i >= 4) {
                return "IV" + m454b(i - 4);
            } else if (i < 1) {
                return "";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("I");
                sb.append(m454b(i - 1));
                return sb.toString();
            }
        }
    }

    public static class C0683m extends C0671b {
        public float f2322u;
        public float f2323v;

        public C0683m() {
            this.f2322u = 150.0f;
            this.f2323v = 18.0f;
            float f = BBString.f2188u0;
            this.f2322u = (float) ((int) (5.0f * f));
            this.f2323v = (float) ((int) (f * 0.6f));
        }
    }

    public static class C0684n extends C0671b {
        public C0684n() {
            float f = BBString.f2188u0;
            this.f2260d = (int) (3.4f * f);
            this.f2271o = (int) (f * 0.85f);
            this.f2273q = Skin.C0353a.f1352H;
            this.f2276t = Skin.C0353a.f1353I;
            this.f2274r = Skin.C0353a.f1354J;
        }
    }

    public static class C0685o {
        public int f2324a;
        public int f2325b;
        public int f2326c;
        public int f2327d;
        public int f2328e;
        public int f2329f;
        public int f2330g;
        public int f2331h;
        public float f2332i;
        public float f2333j;
        public float f2334k;
        public float f2335l;

        C0685o() {
        }

        public void m453a(int i, int i2) {
            this.f2324a = i | i2;
        }

        public boolean m452b() {
            return (this.f2324a & 33554432) > 0;
        }

        public boolean m451c() {
            return (this.f2324a & 268435456) > 0;
        }

        public boolean m450d() {
            return (this.f2324a & 67108864) > 0;
        }

        public boolean m449e() {
            return (this.f2324a & 1073741824) > 0;
        }

        public boolean m448f() {
            return (this.f2324a & 536870912) > 0;
        }

        public boolean m447g() {
            return (this.f2324a & 134217728) > 0;
        }

        public boolean m446h() {
            return (this.f2324a & Integer.MIN_VALUE) != 0;
        }

        public int m445i() {
            return this.f2324a & 65280;
        }

        public int m444j() {
            return this.f2324a & 255;
        }

        public boolean m443k(int i) {
            return (i & this.f2324a) > 0;
        }

        public boolean m442l() {
            return (this.f2324a & 65280) > 32768;
        }

        public void m441m(boolean z) {
            int i = this.f2324a | 67108864;
            this.f2324a = i;
            if (!z) {
                this.f2324a = i ^ 67108864;
            }
        }

        public void m440n(boolean z) {
            int i = this.f2324a | 1073741824;
            this.f2324a = i;
            if (!z) {
                this.f2324a = i ^ 1073741824;
            }
        }
    }

    public static class C0686p {
        int f2336a;
        int f2337b;
        int f2338c;
        C0687a[] f2339d;

        public static class C0687a {
            public int f2340a;
            public boolean f2341b;
            public int f2342c;
            public int f2343d;
            public int f2344e;
        }

        C0686p() {
        }

        void m439a() {
            int i = this.f2337b + 10;
            this.f2337b = i;
            C0687a[] aVarArr = new C0687a[i];
            C0687a[] aVarArr2 = this.f2339d;
            if (aVarArr2 != null) {
                System.arraycopy(aVarArr2, 0, aVarArr, 0, this.f2336a + 1);
            }
            this.f2339d = aVarArr;
        }

        public void m438b() {
            int i = this.f2336a;
            if (i > 0) {
                this.f2336a = i - 1;
                this.f2338c--;
            }
        }

        public void m437c(int i, boolean z, int i2) {
            if (this.f2336a >= this.f2337b - 1) {
                m439a();
            }
            int i3 = this.f2336a;
            if (i3 > 0) {
                C0687a[] aVarArr = this.f2339d;
                int i4 = this.f2338c;
                if (aVarArr[i4].f2344e < 0) {
                    aVarArr[i4].f2344e = i;
                }
            }
            this.f2336a = i3 + 1;
            C0687a[] aVarArr2 = this.f2339d;
            int i5 = this.f2338c + 1;
            this.f2338c = i5;
            aVarArr2[i5] = new C0687a();
            aVarArr2[i5].f2340a = i;
            aVarArr2[i5].f2341b = z;
            aVarArr2[i5].f2342c = i2;
            aVarArr2[i5].f2343d = 0;
            aVarArr2[i5].f2344e = -1;
        }

        public int m436d() {
            return this.f2339d[this.f2338c].f2344e;
        }

        public int m435e() {
            if (this.f2336a > 0) {
                C0687a[] aVarArr = this.f2339d;
                int i = this.f2338c;
                if (aVarArr[i].f2344e >= 0) {
                    return aVarArr[i + 1].f2340a;
                }
            }
            return -1;
        }

        public int m434f() {
            return this.f2339d[this.f2338c].f2342c;
        }

        public int m433g() {
            if (this.f2336a > 0) {
                return this.f2339d[this.f2338c].f2340a;
            }
            return -1;
        }

        public int m432h(int i) {
            if (i > 0) {
                int i2 = this.f2338c;
                if (i2 - i > 0) {
                    return this.f2339d[i2 - i].f2340a;
                }
            }
            return -1;
        }

        public int m431i() {
            C0687a aVar = this.f2339d[this.f2338c];
            int i = aVar.f2343d + 1;
            aVar.f2343d = i;
            return i;
        }

        public boolean m430j() {
            return this.f2336a == 0;
        }

        public boolean m429k() {
            return this.f2336a > 0 && this.f2339d[this.f2338c].f2341b;
        }
    }

    public static class C0688q extends C0671b {
        public C0688q() {
            this.f2257a = "Цитата";
            this.f2272p = Skin.C0353a.f1395m;
            this.f2273q = Skin.C0353a.f1397n;
            this.f2276t = Skin.C0353a.f1399o;
            this.f2274r = Skin.C0353a.f1400p;
        }
    }

    public static class C0689r {
        int f2345a;
        int f2346b;
        int f2347c;
        int f2348d;

        public C0689r(int i, int i2, int i3, int i4) {
            this.f2345a = i;
            this.f2346b = i2;
            this.f2347c = i3;
            this.f2348d = i4;
        }
    }

    public class C0690s {
        public int f2349a;
        public int f2350b;
        public int f2351c;
        public boolean f2352d;

        public C0690s(BBString pVar, int i, int i2, int i3, boolean z) {
            this.f2349a = i;
            this.f2350b = i2;
            this.f2351c = i3;
            this.f2352d = z;
        }
    }

    public static class C0691t {
        public C0691t() {
            float f = BBString.f2188u0;
            float f2 = BBString.f2189v0;
        }
    }

    public static class C0692u extends C0671b {
        public C0692u() {
            this.f2257a = "Спойлер";
            this.f2272p = Skin.C0353a.f1401q;
            this.f2273q = Skin.C0353a.f1402r;
            this.f2276t = Skin.C0353a.f1403s;
            this.f2274r = Skin.C0353a.f1404t;
            this.f2275s = Skin.C0353a.f1405u;
        }
    }

    public static class C0693v {
        public boolean f2353a;
        public float f2354b;
        public int f2355c;
        public int f2356d;
        public float f2357e;
        public float f2358f;
        public float f2359g;
        public float f2360h;
    }

    static {
        Color.argb(176, 176, 176, 128);
    }

    BBString() {
    }

    public static void m477l(float f, float f2) {
        f2188u0 = f;
        f2189v0 = f2;
        f2190w0 = new C0691t();
        f2191x0 = new C0671b();
        f2192y0 = new C0674e();
        f2193z0 = new C0688q();
        f2157A0 = new C0692u();
        f2158B0 = new C0672c();
        f2159C0 = new C0676g();
        f2160D0 = new C0675f();
        f2161E0 = new C0684n();
        f2162F0 = new C0673d();
        f2163G0 = new C0683m();
        float f3 = f2188u0;
        f2166J0 = f3;
        f2167K0 = f3;
        f2187t0 = f2 * 0.0f;
        f2182o0 = Skin.C0353a.f1371a;
        f2183p0 = Skin.C0353a.f1375c;
        f2184q0 = Skin.C0353a.f1377d;
        f2185r0 = Skin.C0353a.f1379e;
        f2186s0 = Skin.C0353a.f1381f;
        int i = Skin.C0353a.f1383g;
        BBOverlay.f550j = Skin.C0353a.f1387i;
        BBOverlay.f549i = Skin.C0353a.f1385h;
        int i2 = Skin.C0353a.f1391k;
        if (i2 == 0) {
            i2 = Skin.C0353a.f1387i;
        }
        BBOverlay.f552l = i2;
        int i3 = Skin.C0353a.f1389j;
        if (i3 == 0) {
            i3 = Skin.C0353a.f1385h;
        }
        BBOverlay.f551k = i3;
    }

    public static synchronized BBString getBBString(char[] bbChars, C0670a[] aVarArr, SparseBooleanArray sparseBooleanArray) {
         {
            if (bbChars == null) {
                return null;
            }
            BBString bbString = new BBString();
            bbString.f2212S = aVarArr;
            if (sparseBooleanArray == null) {
                sparseBooleanArray = new SparseBooleanArray();
            }
            bbString.f2232l = sparseBooleanArray;
            bbString.m469t();
            f2177j0 = 0;
            f2178k0 = -1;
            int[] iArr = f2171d0;
            C0674e eVar = bbString.f2246z;
            iArr[0] = eVar.f2273q;
            f2176i0[0] = eVar.f2260d;
            int[] iArr2 = f2169b0;
            f2177j0 = 1;
            iArr2[0] = 0x4000081;
            bbString.m486c();
            bbString.f2231k = bbChars;
            if (!bbString.m476m()) {
                Log.e("BBString", "parse error");
                return null;
            }
            int[] iArr3 = f2169b0;
            int i = f2177j0;
            f2177j0 = i + 1;
            iArr3[i] = 0x4000082;
            bbString.m486c();
            bbString.f2211R.m460c();
            Log.e("BBString", "parse ended");
            return bbString;
        }
    }

    public static BBString getBBString(String str, C0670a[] aVarArr) {
        if (str == null) {
            return null;
        }
        return getBBString(str.toCharArray(), aVarArr, null);
    }

    public boolean m495A(int i) {
        return (i & 0x20000000) > 0;
    }

    public int m494B(int i) {
        return i & 0xff00;
    }

    public int m493C(int i) {
        return i & 255;
    }

    public boolean m492D(int i) {
        return (i & 0xff00) > 0x8000;
    }

    public int m491E(int i, boolean z) {
        int i2 = i | Integer.MIN_VALUE;
        return !z ? i2 ^ Integer.MIN_VALUE : i2;
    }

    void m490F(int i) {
        int[] iArr = new int[i];
        System.arraycopy(f2169b0, 0, iArr, 0, f2177j0);
        f2169b0 = iArr;
        int[] iArr2 = new int[i];
        System.arraycopy(f2170c0, 0, iArr2, 0, f2177j0);
        f2170c0 = iArr2;
        int[] iArr3 = new int[i];
        System.arraycopy(f2171d0, 0, iArr3, 0, f2177j0);
        f2171d0 = iArr3;
        int[] iArr4 = new int[i];
        System.arraycopy(f2172e0, 0, iArr4, 0, f2177j0);
        f2172e0 = iArr4;
        int[] iArr5 = new int[i];
        System.arraycopy(f2173f0, 0, iArr5, 0, f2177j0);
        f2173f0 = iArr5;
        int[] iArr6 = new int[i];
        System.arraycopy(f2174g0, 0, iArr6, 0, f2177j0);
        f2174g0 = iArr6;
        int[] iArr7 = new int[i];
        System.arraycopy(f2175h0, 0, iArr7, 0, f2177j0);
        f2175h0 = iArr7;
        int[] iArr8 = new int[i];
        System.arraycopy(f2176i0, 0, iArr8, 0, f2177j0);
        f2176i0 = iArr8;
    }

    void m489G(int i) {
        int i2 = this.f2229i;
        C0670a[] aVarArr = this.f2212S;
        if (aVarArr[i].f2249c) {
            if (i2 < 0) {
                this.f2229i = -2;
            }
            int[] iArr = f2169b0;
            int i3 = f2177j0;
            iArr[i3] = 0x2000007;
            f2173f0[i3] = m488a(aVarArr[i].f2250d, aVarArr[i].f2251e, aVarArr[i].f2252f);
            f2174g0[f2177j0] = i;
            m470s();
            this.f2229i = i2;
            return;
        }
        this.f2229i = -2;
        int[] iArr2 = f2169b0;
        int i4 = f2177j0;
        iArr2[i4] = 33554437;
        f2173f0[i4] = m485d(aVarArr[i].f2250d);
        f2174g0[f2177j0] = i;
        m470s();
        this.f2229i = i2;
        int[] iArr3 = f2169b0;
        int i5 = f2177j0;
        iArr3[i5] = 5;
        f2173f0[i5] = m485d(" (" + Util.formatFileSize((long) this.f2212S[i].f2253g) + ")");
        f2174g0[f2177j0] = -1;
        m470s();
        if (this.f2212S[i].f2256j >= 0) {
            this.f2239s++;
            int[] iArr4 = f2169b0;
            int i6 = f2177j0;
            iArr4[i6] = 5;
            f2173f0[i6] = m485d(" Кол-во скачиваний: " + this.f2212S[i].f2256j);
            f2174g0[f2177j0] = -1;
            m470s();
            this.f2239s = this.f2239s + -1;
        }
        int[] iArr5 = f2169b0;
        int i7 = f2177j0;
        iArr5[i7] = 5;
        f2173f0[i7] = m485d("\n");
        f2174g0[f2177j0] = -1;
        m470s();
    }

    int m488a(String str, int i, int i2) {
        int i3 = this.f2209P;
        int i4 = this.f2210Q;
        if (i3 <= i4) {
            int i5 = i3 + 5;
            this.f2209P = i5;
            C0677h[] hVarArr = new C0677h[i5];
            C0677h[] hVarArr2 = this.f2208O;
            if (hVarArr2 != null) {
                System.arraycopy(hVarArr2, 0, hVarArr, 0, i4);
            }
            this.f2208O = hVarArr;
        }
        C0677h[] hVarArr3 = this.f2208O;
        int i6 = this.f2210Q;
        hVarArr3[i6] = new C0677h(this);
        hVarArr3[i6].f2277a = -1;
        hVarArr3[i6].f2283g = 1.0f;
        if (i > 0) {
            hVarArr3[i6].f2286j = ((float) i) * f2189v0;
        }
        if (i2 > 0) {
            hVarArr3[i6].f2287k = ((float) i2) * f2189v0;
        }
        hVarArr3[i6].f2279c = str;
        hVarArr3[i6].f2280d = 0;
        hVarArr3[i6].f2291o = true;
        hVarArr3[i6].f2292p = false;
        hVarArr3[i6].f2293q = true;
        this.f2210Q = i6 + 1;
        return i6;
    }

    void m487b(C0685o oVar) {
        int i = this.f2223c;
        int i2 = this.f2222b;
        if (i <= i2) {
            int i3 = i + 30;
            this.f2223c = i3;
            C0685o[] oVarArr = new C0685o[i3];
            C0685o[] oVarArr2 = this.f2220a;
            if (oVarArr2 != null) {
                System.arraycopy(oVarArr2, 0, oVarArr, 0, i2);
            }
            this.f2220a = oVarArr;
        }
        oVar.f2324a |= 1073741824;
        C0685o[] oVarArr3 = this.f2220a;
        int i4 = this.f2222b;
        this.f2222b = i4 + 1;
        oVarArr3[i4] = oVar;
    }

    void m486c() {
        int i;
        if (!f2179l0) {
            for (int i2 = 0; i2 < f2177j0; i2++) {
                int C = m493C(f2169b0[i2]);
                if (C == 129) {
                    m484e();
                    if (!m492D(f2169b0[i2])) {
                        int e = this.f2224d.m435e();
                        if (e >= 0) {
                            C0685o[] oVarArr = this.f2220a;
                            oVarArr[oVarArr[e].f2328e].f2330g = this.f2222b;
                        }
                        f2172e0[i2] = this.f2224d.m433g();
                        int B = m494B(f2169b0[i2]);
                        if (B == 7424) {
                            if (m495A(f2169b0[i2])) {
                                boolean z = this.f2232l.get(this.f2222b);
                                int[] iArr = f2170c0;
                                C0692u uVar = this.f2195B;
                                iArr[i2] = z ? uVar.f2274r : uVar.f2275s;
                                this.f2225e = !z;
                            } else if (m463z(f2169b0[i2])) {
                                int[] iArr2 = f2169b0;
                                iArr2[i2] = m491E(iArr2[i2], this.f2225e);
                            }
                        }
                        this.f2224d.m437c(this.f2222b, B == 6400, f2174g0[i2]);
                        if (C != 0) {
                            int i3 = this.f2223c;
                            int i4 = this.f2222b;
                            if (i3 <= i4) {
                                int i5 = i3 + (30 - i2) + f2177j0;
                                this.f2223c = i5;
                                C0685o[] oVarArr2 = new C0685o[i5];
                                C0685o[] oVarArr3 = this.f2220a;
                                if (oVarArr3 != null) {
                                    System.arraycopy(oVarArr3, 0, oVarArr2, 0, i4);
                                }
                                this.f2220a = oVarArr2;
                            }
                            int[] iArr3 = f2169b0;
                            iArr3[i2] = iArr3[i2] | 1073741824;
                            C0685o[] oVarArr4 = this.f2220a;
                            int i6 = this.f2222b;
                            if (oVarArr4[i6] == null) {
                                oVarArr4[i6] = new C0685o();
                            }
                            oVarArr4[i6].f2324a = iArr3[i2];
                            oVarArr4[i6].f2325b = f2170c0[i2];
                            oVarArr4[i6].f2326c = f2171d0[i2];
                            oVarArr4[i6].f2327d = f2172e0[i2];
                            oVarArr4[i6].f2328e = f2173f0[i2];
                            oVarArr4[i6].f2329f = f2174g0[i2];
                            oVarArr4[i6].f2330g = f2175h0[i2];
                            oVarArr4[i6].f2331h = f2176i0[i2];
                            if (3 == m493C(iArr3[i2])) {
                                int[] iArr4 = f2176i0;
                                if (-1 < iArr4[i2]) {
                                    C0681k kVar = this.f2202I.get(iArr4[i2]);
                                    if (-1 == kVar.f2318c) {
                                        kVar.f2318c = this.f2222b;
                                    }
                                    kVar.f2319d = this.f2222b;
                                }
                            }
                            this.f2222b++;
                        }
                    }
                    C = 0;
                    if (C != 0) {
                    }
                } else if (C == 130) {
                    m484e();
                    if (!m492D(f2169b0[i2])) {
                        int e2 = this.f2224d.m435e();
                        if (e2 >= 0) {
                            C0685o[] oVarArr5 = this.f2220a;
                            oVarArr5[oVarArr5[e2].f2328e].f2330g = this.f2222b;
                        }
                        int g = this.f2224d.m433g();
                        this.f2220a[g].f2330g = this.f2224d.m436d();
                        C0685o[] oVarArr6 = this.f2220a;
                        oVarArr6[g].f2328e = this.f2222b;
                        f2173f0[i2] = g;
                        f2172e0[i2] = oVarArr6[g].f2327d;
                        f2170c0[i2] = oVarArr6[g].f2325b;
                        f2171d0[i2] = oVarArr6[g].f2326c;
                        f2176i0[i2] = oVarArr6[g].f2331h;
                        int[] iArr5 = f2169b0;
                        iArr5[i2] = (oVarArr6[g].f2324a & -2013265920) | iArr5[i2];
                        this.f2224d.m438b();
                        if (C != 0) {
                        }
                    }
                    C = 0;
                    if (C != 0) {
                    }
                } else {
                    if (f2178k0 == -1 && C == 3) {
                        int i7 = f2173f0[i2];
                        int i8 = f2174g0[i2];
                        while (i8 > 0 && this.f2231k[i7] == 0) {
                            i7++;
                            i8--;
                        }
                        if (i2 > 0) {
                            i = f2169b0[i2 - 1];
                        } else {
                            int i9 = this.f2222b;
                            i = i9 > 0 ? this.f2220a[i9 - 1].f2324a : 0;
                        }
                        if (i8 > (130 == m493C(i) ? 1 : 0) && '\n' == this.f2231k[i7]) {
                            i7++;
                            i8--;
                        }
                        if (i8 > 0) {
                            m483f();
                            f2173f0[i2] = i7;
                            f2174g0[i2] = i8;
                        }
                        C = 0;
                    } else {
                        m483f();
                        if (C == 7) {
                            C0677h[] hVarArr = this.f2208O;
                            int[] iArr6 = f2173f0;
                            hVarArr[iArr6[i2]].f2277a = this.f2222b;
                            hVarArr[iArr6[i2]].f2278b = f2178k0;
                        }
                    }
                    if (C != 0) {
                    }
                }
            }
        }
        f2177j0 = 0;
    }

    int m485d(String str) {
        int i = this.f2206M;
        int i2 = this.f2207N;
        if (i <= i2) {
            int i3 = i + 30;
            this.f2206M = i3;
            String[] strArr = new String[i3];
            String[] strArr2 = this.f2205L;
            if (strArr2 != null) {
                System.arraycopy(strArr2, 0, strArr, 0, i2);
            }
            this.f2205L = strArr;
        }
        String[] strArr3 = this.f2205L;
        int i4 = this.f2207N;
        strArr3[i4] = str;
        this.f2207N = i4 + 1;
        return i4;
    }

    void m484e() {
        if (f2178k0 >= 0) {
            this.f2220a[this.f2222b - 1].m441m(true);
            C0685o[] oVarArr = this.f2220a;
            int i = f2178k0;
            oVarArr[i].f2328e = this.f2222b;
            C0685o oVar = new C0685o();
            oVar.f2328e = i;
            oVar.m453a(130, oVarArr[i].m445i());
            C0685o[] oVarArr2 = this.f2220a;
            int i2 = f2178k0;
            oVar.f2327d = oVarArr2[i2].f2327d;
            oVar.f2330g = oVarArr2[i2].f2330g;
            m487b(oVar);
            f2178k0 = -1;
        }
    }

    void m483f() {
        if (f2178k0 == -1) {
            f2178k0 = this.f2222b;
            C0685o oVar = new C0685o();
            oVar.m453a(129, this.f2230j);
            oVar.f2327d = this.f2224d.m433g();
            C0679j jVar = this.f2211R;
            int i = jVar.f2308b;
            jVar.f2308b = i + 1;
            oVar.f2330g = i;
            m487b(oVar);
        }
    }

    int m482g(int i) {
        if (this.f2212S == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            C0670a[] aVarArr = this.f2212S;
            if (i2 >= aVarArr.length) {
                return -1;
            }
            if (i == aVarArr[i2].f2247a) {
                C0670a aVar = aVarArr[i2];
                int i3 = this.f2213T + 1;
                this.f2213T = i3;
                aVar.f2248b = i3;
                return i2;
            }
            i2++;
        }
    }

    public String m481h() {
        StringBuilder sb = new StringBuilder(this.f2231k.length);
        int length = this.f2231k.length;
        for (int i = 0; i < length; i++) {
            char c = this.f2231k[i];
            if (!(c == 0 || c == 8203)) {
                sb.append(c);
            }
        }
        String sb2 = sb.toString();
        int lastIndexOf = sb2.lastIndexOf("[break]");
        return lastIndexOf >= 0 ? sb2.substring(0, lastIndexOf) : sb2;
    }

    int m480i(int i, int i2) {
        while (i < this.f2231k.length) {
            int i3 = 0;
            while (true) {
                char[] cArr = f2181n0;
                if (i3 < cArr.length) {
                    if (cArr[i3] == this.f2231k[i]) {
                        return i;
                    }
                    i3++;
                }
            }
        }
        return i2;
    }

    int m479j(int i, int i2) {
        while (i < i2) {
            while (i < i2 && this.f2231k[i] != '\"') {
                i++;
            }
            if (i >= i2) {
                return i2;
            }
            if (i == 0 || (i > 0 && this.f2231k[i - 1] != '\\')) {
                return i;
            }
            int i3 = i - 1;
            while (i3 >= 0 && this.f2231k[i3] == '\\') {
                i3--;
            }
            int i4 = i3 + 1;
            while (i4 < i) {
                i4 += 2;
            }
            if (i4 == i) {
                return i;
            }
            i++;
        }
        return i2;
    }

    int m478k(char c, int i, int i2) {
        while (i < i2 && this.f2231k[i] != c) {
            i++;
        }
        return i;
    }

    boolean m476m() {
        int i;
        int o;
        int k;
        m471r();
        C0690s sVar = new C0690s(this, 0, 0, 0, false);
        int length = this.f2231k.length - 1;
        while (length >= 0 && this.f2231k[length] == '\n') {
            length--;
        }
        int i2 = length + 1;
        boolean z = false;
        int i3 = 0;
        while (i3 < i2) {
            int k2 = m478k('[', i3, i2);
            if (z && (k = m478k(']', i3, i2)) < k2) {
                if (!f2179l0) {
                    if (this.f2224d.m430j()) {
                        return false;
                    }
                    sVar.f2350b = this.f2220a[this.f2224d.m433g()].m445i();
                }
                i3 = m472q(i3, k - i3, 0, sVar.f2351c, false) + 1;
                int i4 = sVar.f2350b;
                if (i4 == 7168 || i4 == 7424) {
                    int[] iArr = f2169b0;
                    int i5 = f2177j0;
                    int i6 = i5 + 1;
                    f2177j0 = i6;
                    iArr[i5] = i4 | 0x82 | 0x20000000;
                    f2170c0[i6] = (i4 == 7424 ? this.f2195B : this.f2194A).f2274r;
                    f2171d0[i6] = (i4 == 7424 ? this.f2195B : this.f2194A).f2273q;
                    f2176i0[i6] = (i4 == 7424 ? this.f2195B : this.f2194A).f2260d;
                    f2177j0 = i6 + 1;
                    iArr[i6] = i4 | 129 | 268435456;
                    this.f2226f = 2;
                }
                z = false;
            }
            m468u(i3, i2);
            m472q(i3, k2 - i3, sVar.f2350b, sVar.f2351c, sVar.f2352d);
            m486c();
            if (k2 >= i2 || (i = (sVar = m473p(k2 + 1, m480i(k2, i2))).f2349a) >= i2 || (o = m474o(i, i2, sVar.f2350b, sVar.f2351c, sVar.f2352d)) >= i2) {
                break;
            }
            if (']' == this.f2231k[o]) {
                i3 = o + 1;
            } else {
                i3 = o;
                z = true;
            }
            m468u(i3, i2);
            m486c();
        }
        return true;
    }

    C0689r m475n(int i, int i2) {
        int i3;
        int i4;
        char lowerCase = Character.toLowerCase(this.f2231k[i]);
        int k = m478k('=', i, i2) + 1;
        if ('\"' == this.f2231k[k]) {
            k++;
            int j = m479j(k, i2);
            i4 = j - k;
            if (i4 < 0) {
                i4 = 0;
            }
            i3 = j + 1;
            int i5 = i4 + i4;
            int i6 = i4;
            for (int i7 = i4 + 1; i7 < i5; i7++) {
                char[] cArr = this.f2231k;
                if (cArr[i6] == '\\' && (cArr[i7] == '\"' || cArr[i7] == '\\')) {
                    cArr[i6] = 8203;
                }
                i6++;
            }
        } else {
            i3 = m480i(k, i2);
            i4 = i3 - k;
        }
        return new C0689r(i3, lowerCase, k, i4);
    }

    int m474o(int i, int i2, int i3, int i4, boolean z) {
        boolean z2 = false;
        int i5 =0;
        int i6= 0;
        int i7;
        int i8;
        int i9;
        int h;
        int parseInt;
        boolean z3 = false;
        boolean z4 = false;
        int i10;
        int i11;
        String str;
        int i12;
        int i13 = 0;
        int k = 0;
        int i14 = 1;
        boolean z5 = true;
        int i15 = 1;
        int i16 = z ? -1 : 1;
        char[] cArr = this.f2231k;
        char c = cArr[i];
        int i17 = 0;
        if ('=' == c) {
            i6 = i + 1;
            if ('\"' == cArr[i6]) {
                i6++;
                int j = m479j(i6, i2);
                i5 = j - i6;
                if (i5 < 0) {
                    i5 = 0;
                }
                k = j + 1;
                z2 = true;
            } else if (i3 == 7168 || i3 == 7424) {
                i7 = i6;
            } else {
                k = m478k(']', i6, i2);
                i5 = k - i6;
                z2 = false;
            }
            i6 = k;
            i7 = i6;
            switch (i3) {
                case 256:
                    if (z) {
                        if (this.f2240t.size() > 0) {
                            this.f2240t.pop();
                        }
                        i8 = this.f2240t.size() > 0 ? this.f2240t.peek() : 0;
                    } else {
                        i8 = i5 > 0 ? Util.C0425g.m644b(new String(this.f2231k, i7, i5)) : 0;
                        this.f2240t.push(i8);
                    }
                    int i18 = this.f2226f & 65535;
                    this.f2226f = i18;
                    this.f2226f = i18 | i8;
                    break;
                case 512:
                    if (z) {
                        if (this.f2241u.size() > 0) {
                            this.f2241u.pop();
                        }
                        i9 = this.f2241u.size() > 0 ? this.f2241u.peek() : 2;
                    } else {
                        try {
                            i9 = Integer.parseInt(new String(this.f2231k, i7, i5));
                        } catch (NumberFormatException unused) {
                            i9 = 2;
                        }
                        this.f2241u.push(i9);
                    }
                    int i19 = this.f2226f & -65536;
                    this.f2226f = i19;
                    this.f2226f = i19 | i9;
                    break;
                case 768:
                    if (z) {
                        if (this.f2242v.size() > 0) {
                            this.f2242v.pop();
                        }
                        this.f2227g = this.f2242v.size() > 0 ? this.f2242v.peek().intValue() : f2182o0;
                        break;
                    } else {
                        int c2 = Util.C0424f.m646c(new String(this.f2231k, i7, i5), f2182o0);
                        this.f2227g = c2;
                        this.f2242v.push(Integer.valueOf(c2));
                        break;
                    }
                case 1024:
                    if (z) {
                        if (this.f2243w.size() > 0) {
                            this.f2243w.pop();
                        }
                        this.f2228h = this.f2243w.size() > 0 ? this.f2243w.peek().intValue() : f2183p0;
                        break;
                    } else {
                        int c3 = Util.C0424f.m646c(new String(this.f2231k, i7, i5), f2183p0);
                        this.f2228h = c3;
                        this.f2243w.push(Integer.valueOf(c3));
                        break;
                    }
                case 1280:
                    this.f2233m += i16;
                    break;
                case 1536:
                    this.f2234n += i16;
                    break;
                case 1792:
                    this.f2235o += i16;
                    break;
                case 2048:
                    this.f2236p += i16;
                    break;
                case 2304:
                    this.f2237q += i16;
                    break;
                case 2560:
                    this.f2238r += i16;
                    break;
                case 2816:
                    this.f2239s += i16;
                    break;
                case 3072:
                case 3328:
                    if (z) {
                        if (this.f2242v.size() > 0) {
                            this.f2242v.pop();
                        }
                        this.f2227g = this.f2242v.size() > 0 ? this.f2242v.peek().intValue() : f2182o0;
                        this.f2229i = -1;
                        break;
                    } else {
                        int i20 = Skin.C0353a.f1373b;
                        this.f2227g = i20;
                        this.f2242v.push(Integer.valueOf(i20));
                        this.f2229i = this.f2202I.size();
                        List<C0681k> list = this.f2202I;
                        if (i3 != 3072) {
                            i14 = 2;
                        }
                        list.add(new C0681k(i14, new String(this.f2231k, i7, i5)));
                        break;
                    }
                case 5376:
                    if (z) {
                        int[] iArr = f2169b0;
                        int i21 = f2177j0;
                        f2177j0 = i21 + 1;
                        iArr[i21] = i3 | 130;
                        break;
                    } else {
                        int[] iArr2 = f2170c0;
                        int i22 = f2177j0;
                        C0675f fVar = this.f2198E;
                        iArr2[i22] = fVar.f2274r;
                        f2171d0[i22] = fVar.f2273q;
                        f2176i0[i22] = fVar.f2260d;
                        int[] iArr3 = f2169b0;
                        f2177j0 = i22 + 1;
                        iArr3[i22] = i3 | 129;
                        this.f2229i = -1;
                        this.f2226f = 2;
                        break;
                    }
                case 0x1600:
                    if (z) {
                        int[] iArr4 = f2169b0;
                        int i23 = f2177j0;
                        f2177j0 = i23 + 1;
                        iArr4[i23] = i3 | 130;
                        break;
                    } else {
                        int[] iArr5 = f2170c0;
                        int i24 = f2177j0;
                        C0684n nVar = this.f2199F;
                        iArr5[i24] = nVar.f2274r;
                        f2171d0[i24] = nVar.f2273q;
                        f2176i0[i24] = nVar.f2260d;
                        int[] iArr6 = f2169b0;
                        f2177j0 = i24 + 1;
                        iArr6[i24] = i3 | 129;
                        this.f2229i = -1;
                        this.f2226f = 2;
                        break;
                    }
                case 5888:
                    if (z) {
                        int[] iArr7 = f2169b0;
                        int i25 = f2177j0;
                        f2177j0 = i25 + 1;
                        iArr7[i25] = i3 | 130;
                        break;
                    } else {
                        int[] iArr8 = f2170c0;
                        int i26 = f2177j0;
                        C0673d dVar = this.f2200G;
                        iArr8[i26] = dVar.f2274r;
                        f2171d0[i26] = dVar.f2273q;
                        f2176i0[i26] = dVar.f2260d;
                        int[] iArr9 = f2169b0;
                        f2177j0 = i26 + 1;
                        iArr9[i26] = i3 | 129;
                        this.f2229i = -1;
                        this.f2226f = 2;
                        break;
                    }
                case 6400:
                    if (z) {
                        int[] iArr10 = f2169b0;
                        int i27 = f2177j0;
                        f2177j0 = i27 + 1;
                        iArr10[i27] = i3 | 130;
                        break;
                    } else {
                        if (i5 == 1) {
                            char c4 = this.f2231k[i7];
                            if (c4 == 'A') {
                                i15 = 4;
                            } else if (c4 == 'I') {
                                i15 = 2;
                            } else if (c4 == 'a') {
                                i15 = 5;
                            } else if (c4 == 'i') {
                                i15 = 3;
                            }
                        } else {
                            i15 = 0;
                        }
                        int i28 = 0;
                        while (true) {
                            int i29 = i28 + 1;
                            h = this.f2224d.m432h(i28);
                            if (h >= 0 && this.f2220a[h].m445i() == 6400) {
                                i28 = i29;
                            }else
                                break;
                        }
                        int[] iArr11 = f2170c0;
                        int i30 = f2177j0;
                        int[] iArr12 = f2171d0;
                        int i31 = h >= 0 ? this.f2220a[h].f2326c : this.f2246z.f2273q;
                        iArr12[i30] = i31;
                        iArr11[i30] = i31;
                        f2176i0[i30] = 0;
                        f2169b0[i30] = i3 | 129;
                        f2173f0[i30] = 0;
                        int[] iArr13 = f2174g0;
                        f2177j0 = i30 + 1;
                        iArr13[i30] = i15;
                        break;
                    }
                case 6656:
                    if (z) {
                        if (!f2179l0) {
                            int[] iArr14 = f2169b0;
                            int i32 = f2177j0;
                            f2177j0 = i32 + 1;
                            iArr14[i32] = i3 | 130 | 268435456;
                        }
                        Boolean pop = f2180m0.pop();
                        if (pop == null || !pop.booleanValue()) {
                            z5 = false;
                        }
                        f2179l0 = z5;
                        break;
                    } else {
                        if (i5 > 0) {
                            parseInt = Integer.parseInt(new String(this.f2231k, i7, i5));
                            f2180m0.push(Boolean.valueOf(f2179l0));
                            z3 = !f2179l0 || f2168L0 < parseInt;
                            f2179l0 = z3;
                            if (!z3) {
                                int[] iArr15 = f2170c0;
                                int i33 = f2177j0;
                                C0676g gVar = this.f2197D;
                                iArr15[i33] = gVar.f2274r;
                                f2171d0[i33] = gVar.f2272p;
                                f2176i0[i33] = gVar.f2260d;
                                int[] iArr16 = f2169b0;
                                int i34 = i33 + 1;
                                f2177j0 = i34;
                                int i35 = i3 | 129;
                                iArr16[i33] = i35 | 536870912;
                                this.f2229i = -1;
                                this.f2226f = 1;
                                iArr16[i34] = 5;
                                f2173f0[i34] = m485d(gVar.f2257a);
                                f2174g0[f2177j0] = -1;
                                m470s();
                                this.f2226f = 2;
                                int[] iArr17 = f2169b0;
                                int i36 = f2177j0;
                                int i37 = i36 + 1;
                                f2177j0 = i37;
                                iArr17[i36] = i3 | 130 | 536870912;
                                int[] iArr18 = f2170c0;
                                C0676g gVar2 = this.f2197D;
                                iArr18[i37] = gVar2.f2274r;
                                f2171d0[i37] = gVar2.f2273q;
                                f2176i0[i37] = gVar2.f2260d;
                                f2177j0 = i37 + 1;
                                iArr17[i37] = i35 | 268435456;
                                break;
                            }
                        }
                        parseInt = 0;
                        f2180m0.push(Boolean.valueOf(f2179l0));
                        if (!f2179l0) {
                        }
                        f2179l0 = z3;
                        if (!z3) {
                        }
                    }
                    break;
                case 6912:
                    if (z) {
                        int[] iArr19 = f2169b0;
                        int i38 = f2177j0;
                        f2177j0 = i38 + 1;
                        iArr19[i38] = i3 | 130 | 268435456;
                        this.f2226f = 2;
                        break;
                    } else {
                        int[] iArr20 = f2170c0;
                        int i39 = f2177j0;
                        C0672c cVar = this.f2196C;
                        iArr20[i39] = i4 == 2 ? cVar.f2274r : cVar.f2275s;
                        int[] iArr21 = f2171d0;
                        C0672c cVar2 = this.f2196C;
                        iArr21[i39] = cVar2.f2272p;
                        f2176i0[i39] = cVar2.f2260d;
                        int[] iArr22 = f2169b0;
                        int i40 = i39 + 1;
                        f2177j0 = i40;
                        int i41 = i3 | 129;
                        iArr22[i39] = i41 | 536870912;
                        this.f2229i = -1;
                        this.f2226f = 1;
                        String str2 = cVar2.f2257a;
                        if (i5 > 0) {
                            String str3 = str2 + ": ";
                            int[] iArr23 = f2169b0;
                            int i42 = f2177j0;
                            iArr23[i42] = 5;
                            f2173f0[i42] = m485d(str3);
                            f2174g0[f2177j0] = -1;
                            m470s();
                            m472q(i7, i5, 32768, 0, false);
                        } else {
                            iArr22[i40] = 5;
                            f2173f0[i40] = m485d(str2);
                            f2174g0[f2177j0] = -1;
                            m470s();
                        }
                        int[] iArr24 = f2169b0;
                        int i43 = f2177j0;
                        int i44 = i43 + 1;
                        f2177j0 = i44;
                        iArr24[i43] = i3 | 130 | 536870912;
                        int[] iArr25 = f2170c0;
                        C0672c cVar3 = this.f2196C;
                        iArr25[i44] = i4 == 2 ? cVar3.f2274r : cVar3.f2275s;
                        int[] iArr26 = f2171d0;
                        C0672c cVar4 = this.f2196C;
                        iArr26[i44] = cVar4.f2273q;
                        f2176i0[i44] = cVar4.f2260d;
                        this.f2226f = 65536;
                        f2177j0 = i44 + 1;
                        iArr24[i44] = i41 | 268435456 | (i4 == 2 ? 0 : 134217728);
                        break;
                    }
                case 7168:
                    if (z) {
                        int[] iArr27 = f2169b0;
                        int i45 = f2177j0;
                        f2177j0 = i45 + 1;
                        iArr27[i45] = i3 | 130 | 268435456;
                        break;
                    } else {
                        int[] iArr28 = f2170c0;
                        int i46 = f2177j0;
                        C0688q qVar = this.f2194A;
                        iArr28[i46] = qVar.f2274r;
                        f2171d0[i46] = qVar.f2272p;
                        f2176i0[i46] = qVar.f2260d;
                        int[] iArr29 = f2169b0;
                        f2177j0 = i46 + 1;
                        int i47 = i3 | 129;
                        iArr29[i46] = i47 | 536870912;
                        this.f2229i = -1;
                        this.f2226f = 1;
                        String str4 = qVar.f2257a;
                        if (z2 && i5 > 0) {
                            String str5 = str4 + ": ";
                            int[] iArr30 = f2169b0;
                            int i48 = f2177j0;
                            iArr30[i48] = 5;
                            f2173f0[i48] = m485d(str5);
                            f2174g0[f2177j0] = -1;
                            m470s();
                            m472q(i7, i5, 32768, 0, false);
                        } else if (' ' == c) {
                            int[] iArr31 = f2169b0;
                            int i49 = f2177j0;
                            iArr31[i49] = 5;
                            f2173f0[i49] = m485d(str4 + ": ");
                            f2174g0[f2177j0] = -1;
                            m470s();
                            do {
                                C0689r n = m475n(i6 + 1, i2);
                                i6 = n.f2345a;
                                int i50 = n.f2347c;
                                int i51 = n.f2348d;
                                if (n.f2346b == 100) {
                                    int[] iArr32 = f2169b0;
                                    int i52 = f2177j0;
                                    iArr32[i52] = 5;
                                    f2173f0[i52] = m485d(" @ ");
                                    f2174g0[f2177j0] = -1;
                                    m470s();
                                }
                                int i53 = n.f2346b;
                                if (i53 == 110) {
                                    z4 = false;
                                } else if (i53 == 100) {
                                    z4 = false;
                                } else if (i53 == 112) {
                                    try {
                                        int parseInt2 = Integer.parseInt(new String(this.f2231k, i50, i51));
                                        int[] iArr33 = f2169b0;
                                        int i54 = f2177j0;
                                        iArr33[i54] = 5;
                                        f2173f0[i54] = m485d(" ");
                                        f2174g0[f2177j0] = -1;
                                        m470s();
                                        int i55 = this.f2229i;
                                        this.f2229i = this.f2202I.size();
                                        this.f2202I.add(new C0681k(1, "https://4pda.ru/forum/index.php?act=findpost&pid=" + parseInt2));
                                        float f = f2189v0;
                                        try {
                                            int a = m488a(null, (int) (f * 18.0f), (int) (f * 18.0f));
                                            C0677h[] hVarArr = this.f2208O;
                                            hVarArr[a].f2280d = R.drawable.ic_snapback;
                                            hVarArr[a].f2291o = false;
                                            int[] iArr34 = f2169b0;
                                            int i56 = f2177j0;
                                            try {
                                                iArr34[i56] = 7;
                                                f2173f0[i56] = a;
                                                f2174g0[i56] = -1;
                                                m470s();
                                                this.f2229i = i55;
                                            } catch (NumberFormatException unused3) {
                                            }
                                        } catch (NumberFormatException unused4) {
                                        }
                                    } catch (NumberFormatException unused5) {
                                    }
                                }
                                m467v(i50, i51, z4);
                                int[] iArr35 = f2169b0;
                                int i57 = f2177j0;
                                iArr35[i57] = 3;
                                f2173f0[i57] = i50;
                                f2174g0[i57] = i51;
                                m470s();
                            } while (']' != this.f2231k[i6]);
                        } else {
                            if (']' != this.f2231k[i6]) {
                                str4 = str4 + ": ";
                            }
                            int[] iArr36 = f2169b0;
                            int i58 = f2177j0;
                            iArr36[i58] = 5;
                            f2173f0[i58] = m485d(str4);
                            f2174g0[f2177j0] = -1;
                            m470s();
                        }
                        this.f2226f = 2;
                        if (']' == this.f2231k[i6]) {
                            int[] iArr37 = f2169b0;
                            int i59 = f2177j0;
                            int i60 = i59 + 1;
                            f2177j0 = i60;
                            iArr37[i59] = i3 | 130 | 536870912;
                            int[] iArr38 = f2170c0;
                            C0688q qVar2 = this.f2194A;
                            iArr38[i60] = qVar2.f2274r;
                            f2171d0[i60] = qVar2.f2273q;
                            f2176i0[i60] = qVar2.f2260d;
                            f2177j0 = i60 + 1;
                            iArr37[i60] = i47 | 268435456;
                            break;
                        }
                    }
                    break;
                case 7424:
                    if (z) {
                        int[] iArr39 = f2169b0;
                        int i61 = f2177j0;
                        f2177j0 = i61 + 1;
                        iArr39[i61] = i3 | 130 | 268435456;
                        break;
                    } else {
                        int[] iArr40 = f2170c0;
                        int i62 = f2177j0;
                        iArr40[i62] = i4 == 4 ? this.f2195B.f2274r : this.f2195B.f2275s;
                        int[] iArr41 = f2171d0;
                        C0692u uVar = this.f2195B;
                        iArr41[i62] = uVar.f2272p;
                        f2176i0[i62] = uVar.f2260d;
                        int i63 = i3 | 129;
                        f2169b0[i62] = i63 | 536870912;
                        int[] iArr42 = f2174g0;
                        f2177j0 = i62 + 1;
                        iArr42[i62] = i4 == 4 ? 0 : 1;
                        this.f2229i = -1;
                        this.f2226f = 1;
                        String str6 = uVar.f2257a;
                        if (!z2 || i5 <= 0) {
                            i11 = i63;
                            i10 = 2;
                            if (']' != this.f2231k[i6]) {
                                str6 = str6 + ": ";
                            }
                            int[] iArr43 = f2169b0;
                            int i64 = f2177j0;
                            iArr43[i64] = 5;
                            f2173f0[i64] = m485d(str6);
                            f2174g0[f2177j0] = -1;
                            m470s();
                        } else {
                            int[] iArr44 = f2169b0;
                            int i65 = f2177j0;
                            iArr44[i65] = 5;
                            f2173f0[i65] = m485d(str6 + ": ");
                            f2174g0[f2177j0] = -1;
                            m470s();
                            i11 = i63;
                            i10 = 2;
                            m472q(i7, i5, 32768, 0, false);
                        }
                        this.f2226f = i10;
                        if (']' == this.f2231k[i6]) {
                            int[] iArr45 = f2169b0;
                            int i66 = f2177j0;
                            int i67 = i66 + 1;
                            f2177j0 = i67;
                            iArr45[i66] = i3 | 130 | 536870912;
                            int[] iArr46 = f2170c0;
                            C0692u uVar2 = this.f2195B;
                            iArr46[i67] = uVar2.f2274r;
                            f2171d0[i67] = uVar2.f2273q;
                            f2176i0[i67] = uVar2.f2260d;
                            f2177j0 = i67 + 1;
                            iArr45[i67] = i11 | 268435456;
                            break;
                        }
                    }
                    break;
                case 7680:
                    int i68 = i7;
                    while (true) {
                        char[] cArr2 = this.f2231k;
                        if (i68 < cArr2.length && cArr2[i68] != ':') {
                            i68++;
                        }else {
                            break;
                        }
                    }
                    if (i68 < 0 || i68 > i7 + i5) {
                        i68 = i7 + i5;
                    }
                    try {
                        int g = m482g(Integer.parseInt(new String(this.f2231k, i7, i68 - i7)));
                        if (g >= 0) {
                            m489G(g);
                            break;
                        } else {
                            int[] iArr47 = f2169b0;
                            int i69 = f2177j0;
                            iArr47[i69] = 5;
                            f2173f0[i69] = m485d("[attachment=\"" + new String(this.f2231k, i7, i5) + "\"]\n");
                            f2174g0[f2177j0] = -1;
                            m470s();
                            break;
                        }
                    } catch (NumberFormatException unused6) {
                        break;
                    }
                case 7936:
                    f2169b0[f2177j0] = 8;
                    if (this.f2224d.m429k()) {
                        int i70 = this.f2224d.m431i();
                        int f2 = this.f2224d.m434f();
                        if (f2 == 1) {
                            str = Integer.valueOf(i70).toString();
                        } else if (f2 == 2) {
                            str = C0682l.m454b(i70);
                        } else if (f2 == 3) {
                            str = C0682l.m454b(i70);
                            if (str != null) {
                                str = str.toLowerCase();
                            }
                        } else if (f2 == 4) {
                            str = C0682l.m455a(i70 - 1);
                            if (str != null) {
                                str = str.toUpperCase();
                            }
                        } else if (f2 != 5) {
                            str = null;
                        } else {
                            str = C0682l.m455a(i70 - 1);
                        }
                        f2173f0[f2177j0] = m485d(str != null ? str + "." : "•");
                    } else {
                        f2173f0[f2177j0] = -1;
                    }
                    int i71 = this.f2226f;
                    int i72 = this.f2227g;
                    int i73 = this.f2228h;
                    int i74 = this.f2229i;
                    int i75 = this.f2233m;
                    int i76 = this.f2234n;
                    int i77 = this.f2236p;
                    int i78 = this.f2235o;
                    int i79 = this.f2237q;
                    int i80 = this.f2238r;
                    int i81 = this.f2239s;
                    this.f2226f = 2;
                    this.f2227g = f2184q0;
                    this.f2228h = f2183p0;
                    this.f2229i = -1;
                    this.f2239s = 0;
                    this.f2238r = 0;
                    this.f2237q = 0;
                    this.f2235o = 0;
                    this.f2236p = 0;
                    this.f2234n = 0;
                    this.f2233m = 0;
                    m470s();
                    this.f2226f = i71;
                    this.f2227g = i72;
                    this.f2228h = i73;
                    this.f2229i = i74;
                    this.f2233m = i75;
                    this.f2234n = i76;
                    this.f2236p = i77;
                    this.f2235o = i78;
                    this.f2237q = i79;
                    this.f2238r = i80;
                    this.f2239s = i81;
                    break;
                case 8448:
                case 8704:
                    String str7 = new String(this.f2231k, i7, i5);
                    int indexOf = str7.indexOf(58);
                    if (indexOf > 0) {
                        str7 = str7.substring(0, indexOf);
                    }
                    if (i3 == 8704) {
                        this.f2202I.add(new C0681k(1, "http://vimeo.com/" + str7));
                        i12 = m488a("https://vimeo.com/api/oembed.json?url=https://vimeo.com/" + str7, 640, 360);
                    } else {
                        this.f2202I.add(new C0681k(1, "http://www.youtube.com/watch?v=" + str7));
                        i12 = m488a("http://img.youtube.com/vi/" + str7 + "/hqdefault.jpg", 480, 360);
                    }
                    this.f2208O[i12].f2290n = true;
                    int i82 = this.f2229i;
                    this.f2229i = this.f2202I.size() - 1;
                    int[] iArr48 = f2169b0;
                    int i83 = f2177j0;
                    iArr48[i83] = 7;
                    f2173f0[i83] = i12;
                    f2174g0[i83] = -1;
                    m470s();
                    this.f2229i = i82;
                    break;
                case 8960:
                    if (z) {
                        int[] iArr49 = f2169b0;
                        int i84 = f2177j0;
                        f2177j0 = i84 + 1;
                        iArr49[i84] = i3 | 130;
                        break;
                    } else {
                        int[] iArr50 = f2170c0;
                        int i85 = f2177j0;
                        iArr50[i85] = 0;
                        f2171d0[i85] = Util.C0424f.m646c(new String(this.f2231k, i7, i5), f2183p0);
                        int[] iArr51 = f2176i0;
                        int i86 = f2177j0;
                        iArr51[i86] = 0;
                        int[] iArr52 = f2169b0;
                        f2177j0 = i86 + 1;
                        iArr52[i86] = i3 | 129;
                        this.f2229i = -1;
                        this.f2226f = 2;
                        break;
                    }
                case 9216:
                case 9472:
                case 9728:
                    if (z) {
                        int[] iArr53 = f2169b0;
                        int i87 = f2177j0;
                        f2177j0 = i87 + 1;
                        iArr53[i87] = i3 | 130;
                        break;
                    } else {
                        if (9728 == i3 && i5 > 0) {
                            String[] split = new String(this.f2231k, i7, i5).split(":", -1);
                            if (split.length >= 2) {
                                try {
                                    i13 = (Integer.parseInt(split[1]) << 8) | Integer.parseInt(split[0]);
                                } catch (NumberFormatException unused7) {
                                }
                                int[] iArr54 = f2170c0;
                                int i88 = f2177j0;
                                iArr54[i88] = i13;
                                f2171d0[i88] = 0;
                                f2176i0[i88] = 0;
                                int[] iArr55 = f2169b0;
                                f2177j0 = i88 + 1;
                                iArr55[i88] = i3 | 129;
                                this.f2229i = -1;
                                this.f2226f = 2;
                                break;
                            }
                        }
                        i13 = 0;
                        int[] iArr542 = f2170c0;
                        int i882 = f2177j0;
                        iArr542[i882] = i13;
                        f2171d0[i882] = 0;
                        f2176i0[i882] = 0;
                        int[] iArr552 = f2169b0;
                        f2177j0 = i882 + 1;
                        iArr552[i882] = i3 | 129;
                        this.f2229i = -1;
                        this.f2226f = 2;
                    }
                case 16384:
                    f2169b0[f2177j0] = 9;
                    m470s();
                    break;
                case 16640:
                    C0670a[] aVarArr = this.f2212S;
                    if (aVarArr != null) {
                        int i89 = 0;
                        int i90 = 0;
                        for (C0670a aVar : aVarArr) {
                            if (aVar.f2248b <= 0) {
                                if (aVar.f2249c) {
                                    i90++;
                                } else {
                                    i89++;
                                }
                            }
                        }
                        if (!(i89 == 0 && i90 == 0)) {
                            m471r();
                            int i91 = i90 + 5 + (i89 * 4) + 1;
                            if (f2169b0.length < i91) {
                                m490F(i91);
                            }
                            if (i90 > 0) {
                                if (i5 > 0 && i90 > 0) {
                                    int[] iArr56 = f2169b0;
                                    int i92 = f2177j0;
                                    iArr56[i92] = 5;
                                    f2173f0[i92] = m485d("\n\n");
                                    f2174g0[f2177j0] = -1;
                                    m470s();
                                    int[] iArr57 = f2170c0;
                                    int i93 = f2177j0;
                                    C0692u uVar3 = this.f2195B;
                                    iArr57[i93] = uVar3.f2275s;
                                    f2171d0[i93] = uVar3.f2272p;
                                    f2176i0[i93] = uVar3.f2260d;
                                    int[] iArr58 = f2169b0;
                                    iArr58[i93] = 536878465;
                                    int[] iArr59 = f2174g0;
                                    int i94 = i93 + 1;
                                    f2177j0 = i94;
                                    iArr59[i93] = 1;
                                    this.f2229i = -1;
                                    this.f2226f = 1;
                                    iArr58[i94] = 5;
                                    f2173f0[i94] = m485d(this.f2195B.f2257a + ": Прикрепленные изображения");
                                    f2174g0[f2177j0] = -1;
                                    m470s();
                                    this.f2226f = 2;
                                    int[] iArr60 = f2169b0;
                                    int i95 = f2177j0;
                                    int i96 = i95 + 1;
                                    f2177j0 = i96;
                                    iArr60[i95] = 536878466;
                                    int[] iArr61 = f2170c0;
                                    C0692u uVar4 = this.f2195B;
                                    iArr61[i96] = uVar4.f2274r;
                                    f2171d0[i96] = uVar4.f2273q;
                                    f2176i0[i96] = uVar4.f2260d;
                                    f2177j0 = i96 + 1;
                                    iArr60[i96] = 268443009;
                                } else if (i5 > 0) {
                                    int[] iArr62 = f2169b0;
                                    int i97 = f2177j0;
                                    iArr62[i97] = 5;
                                    f2173f0[i97] = m485d("\n\nПрикрепленные изображения:\n");
                                    f2174g0[f2177j0] = -1;
                                    this.f2226f = 1;
                                    this.f2233m = 1;
                                    m470s();
                                    this.f2233m = 0;
                                    this.f2226f = 2;
                                }
                                int i98 = 0;
                                while (true) {
                                    C0670a[] aVarArr2 = this.f2212S;
                                    if (i98 < aVarArr2.length) {
                                        if (aVarArr2[i98].f2248b <= 0 && aVarArr2[i98].f2249c) {
                                            m489G(i98);
                                        }
                                        i98++;
                                    } else if (i5 > 0 && i90 > 0) {
                                        int[] iArr63 = f2169b0;
                                        int i99 = f2177j0;
                                        f2177j0 = i99 + 1;
                                        iArr63[i99] = 268443010;
                                    }
                                }
                            }
                            if (i89 > 0) {
                                if (i5 > 0) {
                                    int[] iArr64 = f2169b0;
                                    int i100 = f2177j0;
                                    iArr64[i100] = 5;
                                    f2173f0[i100] = m485d("\n\nПрикрепленные файлы:\n");
                                    f2174g0[f2177j0] = -1;
                                    this.f2226f = 1;
                                    this.f2233m = 1;
                                    m470s();
                                    this.f2233m = 0;
                                    this.f2226f = 2;
                                }
                                while (true) {
                                    C0670a[] aVarArr3 = this.f2212S;
                                    if (i17 >= aVarArr3.length) {
                                        break;
                                    } else {
                                        if (aVarArr3[i17].f2248b <= 0 && !aVarArr3[i17].f2249c) {
                                            m489G(i17);
                                        }
                                        i17++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 33024:
                case 33280:
                case 33536:
                case 33792:
                    if (z) {
                        if (this.f2244x.size() > 0) {
                            this.f2244x.pop();
                        }
                        this.f2230j = this.f2244x.size() > 0 ? this.f2244x.peek().intValue() : 33024;
                        int[] iArr65 = f2169b0;
                        int i101 = f2177j0;
                        f2177j0 = i101 + 1;
                        iArr65[i101] = i3 | 130;
                        break;
                    } else {
                        this.f2230j = i3;
                        this.f2244x.push(Integer.valueOf(i3));
                        int[] iArr66 = f2169b0;
                        int i102 = f2177j0;
                        f2177j0 = i102 + 1;
                        iArr66[i102] = i3 | 129;
                        break;
                    }
            }
            return i6;
        }
        i6 = i;
        i7 = 0;
        i5 = 0;
        z2 = false;
        switch (i3) {
            case 256:
                break;
            case 512:
                break;
            case 768:
                break;
            case 1024:
                break;
            case 1280:
                break;
            case 1536:
                break;
            case 1792:
                break;
            case 2048:
                break;
            case 2304:
                break;
            case 2560:
                break;
            case 2816:
                break;
            case 3072:
            case 3328:
                break;
            case 5376:
                break;
            case 5632:
                break;
            case 5888:
                break;
            case 6400:
                break;
            case 6656:
                break;
            case 6912:
                break;
            case 7168:
                break;
            case 7424:
                break;
            case 7680:
                break;
            case 7936:
                break;
            case 8448:
            case 8704:
                break;
            case 8960:
                break;
            case 9216:
            case 9472:
            case 9728:
                break;
            case 16384:
                break;
            case 16640:
                break;
            case 33024:
            case 33280:
            case 33536:
            case 33792:
                break;
        }
        return i6;
    }

    C0690s m473p(int i, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        int i7 = i + 1;
        char lowerCase = Character.toLowerCase(this.f2231k[i]);
        int i8 = 0;
        if ('/' == lowerCase) {
            lowerCase = Character.toLowerCase(this.f2231k[i7]);
            i7++;
            z = true;
        } else {
            z = false;
        }
        int i9 = i7 + 1;
        char lowerCase2 = Character.toLowerCase(this.f2231k[i7]);
        if (lowerCase == '*') {
            i3 = 7936;
        } else if (lowerCase != 'y') {
            if (lowerCase == 'e') {
                i6 = 'm' == lowerCase2 ? 3328 : 5376;
            } else if (lowerCase != 'f') {
                switch (lowerCase) {
                    case 'a':
                        if ('n' != lowerCase2) {
                            i6 = 7680;
                            break;
                        } else {
                            i6 = 4096;
                            break;
                        }
                    case 'b':
                        if ('a' != lowerCase2) {
                            if ('l' != lowerCase2) {
                                if (']' == lowerCase2) {
                                    i3 = 1280;
                                    break;
                                }
                                i3 = 0;
                                break;
                            } else {
                                i3 = 8960;
                                break;
                            }
                        } else {
                            i3 = 1024;
                            break;
                        }
                    case 'c':
                        if ('e' != lowerCase2) {
                            if ('u' != lowerCase2) {
                                if ('d' != this.f2231k[i9]) {
                                    i3 = 768;
                                    break;
                                } else {
                                    i3 = 6912;
                                    break;
                                }
                            } else {
                                i3 = 5888;
                                break;
                            }
                        } else {
                            i3 = 33536;
                            break;
                        }
                    default:
                        switch (lowerCase) {
                            case 'h':
                                i3 = 6656;
                                break;
                            case 'i':
                                if ('m' != lowerCase2) {
                                    i6 = 1536;
                                    break;
                                } else {
                                    i6 = 6144;
                                    break;
                                }
                            case 'j':
                                i3 = 33792;
                                break;
                            default:
                                switch (lowerCase) {
                                    case 'l':
                                        if ('e' != lowerCase2) {
                                            i6 = 6400;
                                            break;
                                        } else {
                                            i6 = 33024;
                                            break;
                                        }
                                    case 'm':
                                        if ('e' != lowerCase2) {
                                            if ('o' == lowerCase2) {
                                                i3 = 5632;
                                                break;
                                            }
                                            i3 = 0;
                                            break;
                                        } else {
                                            i3 = 3840;
                                            break;
                                        }
                                    case 'n':
                                        if ('a' == lowerCase2) {
                                            i3 = 16640;
                                            break;
                                        }
                                        i3 = 0;
                                        break;
                                    case 'o':
                                        i3 = 2816;
                                        break;
                                    default:
                                        switch (lowerCase) {
                                            case 'q':
                                                i3 = 7168;
                                                break;
                                            case 'r':
                                                i3 = 33280;
                                                break;
                                            case 's':
                                                if ('i' != lowerCase2) {
                                                    if ('n' != lowerCase2) {
                                                        if ('p' != lowerCase2) {
                                                            if ('u' != lowerCase2 || 'b' != this.f2231k[i9]) {
                                                                if ('u' != lowerCase2 || 'p' != this.f2231k[i9]) {
                                                                    if ('e' != lowerCase2) {
                                                                        i3 = 2048;
                                                                        break;
                                                                    } else {
                                                                        i3 = 16384;
                                                                        break;
                                                                    }
                                                                } else {
                                                                    i3 = 2560;
                                                                    break;
                                                                }
                                                            } else {
                                                                i3 = 2304;
                                                                break;
                                                            }
                                                        } else {
                                                            i3 = 7424;
                                                            break;
                                                        }
                                                    } else {
                                                        i3 = 3584;
                                                        break;
                                                    }
                                                } else {
                                                    i3 = 512;
                                                    break;
                                                }
                                            case 't':
                                                if ('d' != lowerCase2) {
                                                    if ('r' != lowerCase2) {
                                                        if ('a' == lowerCase2) {
                                                            i3 = 9216;
                                                            break;
                                                        }
                                                        i3 = 0;
                                                        break;
                                                    } else {
                                                        i3 = 9472;
                                                        break;
                                                    }
                                                } else {
                                                    i3 = 9728;
                                                    break;
                                                }
                                            case 'u':
                                                if ('r' != lowerCase2) {
                                                    i6 = 1792;
                                                    break;
                                                } else {
                                                    i6 = 3072;
                                                    break;
                                                }
                                            case 'v':
                                                i3 = 8704;
                                                break;
                                            default:
                                                i3 = 0;
                                                break;
                                        }
                                }
                        }
                }
            } else {
                i3 = 256;
            }
            i3 = i6;
        } else {
            i3 = 8448;
        }
        char[] cArr = this.f2231k;
        if (':' == cArr[i2]) {
            int i10 = i2 + 1;
            char c = cArr[i10];
            if ('b' == c) {
                i8 = 1;
            } else if ('u' == c) {
                i8 = 2;
            } else if ('c' == c) {
                i8 = 3;
            } else if ('o' == c) {
                i8 = 4;
            } else if ('f' == c) {
                i8 = 5;
            } else if ('n' == c) {
                i8 = 6;
            }
            i5 = m480i(i10, cArr.length);
            i4 = i8;
        } else {
            i5 = i2;
            i4 = 0;
        }
        return new C0690s(this, i5, i3, i4, z);
    }

    int m472q(int i, int i2, int i3, int i4, boolean z) {
        boolean z2;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z3;
        int i9;
        boolean z4= false;
        boolean z5 = false;
        char c;
        int i10;
        int i11 = i;
        int i12 = i2;
        if (!z) {
            if (i3 == 3584) {
                int i13 = this.f2229i;
                this.f2229i = this.f2202I.size();
                this.f2202I.add(new C0681k(1, "https://4pda.ru/forum/index.php?act=findpost&pid=" + new String(this.f2231k, i11, i12)));
                float f = f2189v0;
                int a = m488a(null, (int) (f * 18.0f), (int) (f * 18.0f));
                C0677h[] hVarArr = this.f2208O;
                hVarArr[a].f2280d = R.drawable.ic_snapback;
                hVarArr[a].f2291o = false;
                int[] iArr = f2169b0;
                int i14 = f2177j0;
                iArr[i14] = 7;
                f2173f0[i14] = a;
                f2174g0[i14] = -1;
                m470s();
                this.f2229i = i13;
            } else if (i3 == 3840) {
                try {
                    int parseInt = Integer.parseInt(new String(this.f2231k, i11, i12));
                    int[] iArr2 = f2169b0;
                    int i15 = f2177j0;
                    iArr2[i15] = 5;
                    f2173f0[i15] = m485d(Util.formatDate(parseInt));
                    f2174g0[f2177j0] = -1;
                    m470s();
                } catch (NumberFormatException unused) {
                }
            } else if (i3 == 4096) {
                int[] iArr3 = f2169b0;
                int i16 = f2177j0;
                iArr3[i16] = -2147483642;
                f2173f0[i16] = i11;
                f2174g0[i16] = i12;
                m470s();
                int i17 = this.f2229i;
                this.f2229i = this.f2202I.size();
                this.f2202I.add(new C0681k(1, "#" + new String(this.f2231k, i11, i12)));
                int[] iArr4 = f2169b0;
                int i18 = f2177j0;
                iArr4[i18] = 5;
                f2173f0[i18] = m485d("⚓");
                f2174g0[f2177j0] = -1;
                m470s();
                int[] iArr5 = f2169b0;
                int i19 = f2177j0 - 1;
                iArr5[i19] = iArr5[i19] & -262145;
                this.f2229i = i17;
            } else if (i3 == 6144) {
                int a2 = m488a(new String(this.f2231k, i11, i12), 0, 0);
                if (i4 == 5) {
                    this.f2208O[a2].f2292p = true;
                }
                if (i4 == 6) {
                    this.f2208O[a2].f2293q = false;
                }
                int[] iArr6 = f2169b0;
                int i20 = f2177j0;
                iArr6[i20] = 7;
                f2173f0[i20] = a2;
                f2174g0[i20] = -1;
                m470s();
            }
            z2 = true;
            if (!z2 || i12 <= 0) {
                i5 = 0;
            } else {
                char[] cArr = this.f2231k;
                char c2 = cArr[i11];
                char c3 = cArr[(i11 + i12) - 1];
                if (c2 == 2 && c3 == 3) {
                    i11++;
                    i12 -= 2;
                    i6 = 2;
                } else {
                    i6 = 0;
                }
                int i21 = i6 > 0 ? 16777216 : 0;
                if (i3 != 6912 || z) {
                    int i22 = i11;
                    while (true) {
                        i7 = i11 + i12;
                        if (i22 >= i7) {
                            break;
                        }
                        char[] cArr2 = this.f2231k;
                        if ('\t' == cArr2[i22]) {
                            cArr2[i22] = ' ';
                            while (true) {
                                i22++;
                                if (i22 < i7) {
                                    char[] cArr3 = this.f2231k;
                                    if (' ' == cArr3[i22]) {
                                        cArr3[i22] = 8203;
                                    }
                                }
                            }
                        }
                        i22++;
                    }
                    int i23 = i11;
                    int i24 = i23;
                    while (i23 < i7 - 1) {
                        char[] cArr4 = this.f2231k;
                        char c4 = cArr4[i23];
                        int i25 = i23 + 1;
                        char c5 = cArr4[i25];
                        if (c4 < '&') {
                            i8 = i6;
                        } else if (c4 > '}' || c5 < '#' || c5 > '}' || !((c4 == '&' && (c5 == 'l' || c5 == '#')) || ((c4 == '(' && (c5 == '-' || c5 == ';')) || ((c4 == ')' && c5 == '-') || ((c4 == '*' && c5 == '-') || ((c4 == '-' && c5 == '_') || ((c4 == '8' && c5 == '-') || ((c4 == ':' && (c5 == 'b' || c5 == 'h' || c5 == 'P' || c5 == 't' || c5 == 'D' || c5 == '-' || c5 == 'l' || c5 == 'r' || c5 == 's' || c5 == 'k' || c5 == 'g' || c5 == 'm' || c5 == 'o' || c5 == 'O' || c5 == 'w' || c5 == 'a' || c5 == 'u' || c5 == 'p' || c5 == 'c' || c5 == 'd' || c5 == 'f' || c5 == 'i' || c5 == '4' || c5 == 'n' || c5 == 'v' || c5 == 'y' || c5 == '&' || c5 == '*' || c5 == '\'' || c5 == '(' || c5 == ')')) || ((c4 == ';' && (c5 == ')' || c5 == '-')) || ((c4 == '=' && c5 == '^') || ((c4 == '@' && c5 == '}') || ((c4 == 'B' && (c5 == ')' || c5 == '-')) || ((c4 == ']' && c5 == '-') || ((c4 == 'o' && (c5 == '_' || c5 == '.')) || (c4 == '}' && c5 == '-'))))))))))))))) {
                            i8 = i6;
                        } else {
                            int size = Util.SmileClass.smilesList.size();
                            int i26 = 0;
                            int i27 = 0;
                            String str = null;
                            Util.SmileClass mVar = null;
                            while (true) {
                                if (i26 >= size) {
                                    i8 = i6;
                                    z3 = false;
                                    break;
                                }
                                mVar = Util.SmileClass.smilesList.get(i26);
                                if (mVar.f1662i == c4 && mVar.f1663j == c5) {
                                    i27 = mVar.f1654a.length();
                                    str = mVar.f1654a;
                                    int i28 = i23 + i27;
                                    int i29 = i25 + 1;
                                    i8 = i6;
                                    i10 = size;
                                    int i30 = 2;
                                    boolean z6 = true;
                                    while (z6 && i29 < i28 && i29 < i7) {
                                        z6 = this.f2231k[i29] == str.charAt(i30);
                                        i30++;
                                        i29++;
                                        c5 = c5;
                                        i28 = i28;
                                    }
                                    c = c5;
                                    if (z6 && i30 == i27) {
                                        i25 = i28;
                                        z3 = true;
                                        break;
                                    }
                                } else {
                                    i8 = i6;
                                    i10 = size;
                                    c = c5;
                                }
                                i26++;
                                i6 = i8;
                                size = i10;
                                c5 = c;
                            }
                            if (!z3) {
                                i23 = i25;
                                i6 = i8;
                            } else {
                                if (c4 != ':' || str.charAt(i27 - 1) != ':') {
                                    int i31 = i23 - 1;
                                    while (true) {
                                        if (i31 < i11) {
                                            break;
                                        }
                                        char[] cArr5 = this.f2231k;
                                        if (cArr5[i31] == 0 || cArr5[i31] == 8203 || cArr5[i31] == '\r') {
                                            i31--;
                                        } else if (cArr5[i31] != ' ' && cArr5[i31] != '\n') {
                                            z4 = false;
                                        }
                                    }
                                    int i32 = i27 + i23;
                                    while (true) {
                                        if (i32 >= i7) {
                                            break;
                                        }
                                        char[] cArr6 = this.f2231k;
                                        if (!(cArr6[i32] == 0 || cArr6[i32] == 8203 || cArr6[i32] == '\r')) {
                                            if (cArr6[i32] != ' ' && cArr6[i32] != '\n') {
                                                z5 = false;
                                            }
                                        }
                                        i32++;
                                    }
                                    z3 = z4 && z5;
                                }
                                if (z3) {
                                    int[] iArr7 = f2169b0;
                                    if (iArr7.length - 5 < f2177j0) {
                                        m490F(iArr7.length + 1000);
                                    }
                                    int i33 = i23 - i24;
                                    if (i33 > 0) {
                                        int[] iArr8 = f2169b0;
                                        int i34 = f2177j0;
                                        iArr8[i34] = i21 | 3;
                                        f2173f0[i34] = i24;
                                        f2174g0[i34] = i33;
                                        m470s();
                                    }
                                    if (TextUtils.isEmpty(mVar.f1658e) || !Prefs.f1147H) {
                                        i9 = m488a(null, mVar.f1656c, mVar.f1657d);
                                        this.f2208O[i9].f2280d = mVar.f1655b;
                                    } else {
                                        i9 = m488a("http://s.4pda.to/img/emot_hd/" + mVar.f1658e + ".png", mVar.f1659f, mVar.f1660g);
                                    }
                                    this.f2208O[i9].f2289m = true;
                                    int[] iArr9 = f2169b0;
                                    int i35 = f2177j0;
                                    iArr9[i35] = i21 | 7 | 8388608;
                                    f2173f0[i35] = i9;
                                    f2174g0[i35] = i26;
                                    m470s();
                                    i24 = i25;
                                }
                                i23 = i25;
                                i6 = i8;
                            }
                        }
                        i23 = i25;
                        i6 = i8;
                    }
                    if (i24 < i7) {
                        int[] iArr10 = f2169b0;
                        if (iArr10.length - 5 < f2177j0) {
                            m490F(iArr10.length + 10);
                        }
                        int[] iArr11 = f2169b0;
                        int i36 = f2177j0;
                        iArr11[i36] = 3 | i21;
                        f2173f0[i36] = i24;
                        f2174g0[i36] = i12 - (i24 - i11);
                        m470s();
                    }
                    m467v(i11, i12, (this.f2226f & -65536) == 65536);
                    i5 = i6;
                } else {
                    m467v(i11, i12, true);
                    int[] iArr12 = f2169b0;
                    int i37 = f2177j0;
                    iArr12[i37] = i21 | 3;
                    f2173f0[i37] = i11;
                    f2174g0[i37] = i12;
                    m470s();
                    i5 = i6;
                }
            }
            return i11 + i12 + i5;
        }
        z2 = false;
        if (!z2) {
        }
        i5 = 0;
        return i11 + i12 + i5;
    }

    void m471r() {
        this.f2229i = -1;
        this.f2226f = 2;
        this.f2227g = f2182o0;
        this.f2228h = f2183p0;
        this.f2230j = 33024;
        this.f2239s = 0;
        this.f2238r = 0;
        this.f2237q = 0;
        this.f2236p = 0;
        this.f2235o = 0;
        this.f2234n = 0;
        this.f2233m = 0;
        this.f2240t.clear();
        this.f2241u.clear();
        this.f2242v.clear();
        this.f2243w.clear();
        this.f2244x.clear();
        f2179l0 = false;
        f2180m0.clear();
    }

    void m470s() {
        int i = this.f2233m > 0 ? 65536 : 0;
        if (this.f2234n > 0) {
            i |= 131072;
        }
        if (this.f2235o > 0 || this.f2229i != -1) {
            i |= 262144;
        }
        if (this.f2236p > 0) {
            i |= 524288;
        }
        if (this.f2237q > 0) {
            i |= 1048576;
        }
        if (this.f2238r > 0) {
            i |= 2097152;
        }
        int[] iArr = f2172e0;
        int i2 = f2177j0;
        iArr[i2] = this.f2226f;
        if (this.f2239s > 0) {
            i |= 4194304;
            f2170c0[i2] = Skin.C0353a.f1358N;
        } else {
            f2170c0[i2] = this.f2227g;
        }
        int[] iArr2 = f2171d0;
        int i3 = f2177j0;
        iArr2[i3] = this.f2228h;
        f2176i0[i3] = this.f2229i;
        int[] iArr3 = f2169b0;
        iArr3[i3] = i | iArr3[i3];
        f2177j0 = i3 + 1;
    }

    protected void m469t() {
        this.f2245y = f2191x0;
        this.f2246z = (C0674e) f2192y0.clone();
        this.f2194A = f2193z0;
        this.f2195B = f2157A0;
        this.f2196C = f2158B0;
        this.f2197D = f2159C0;
        this.f2198E = f2160D0;
        this.f2199F = f2161E0;
        this.f2200G = f2162F0;
        this.f2201H = f2163G0;
    }

    void m468u(int i, int i2) {
        int i3 = f2164H0 + (i - this.f2203J);
        f2164H0 = i3;
        this.f2203J = i;
        int i4 = f2165I0;
        int i5 = this.f2222b;
        int i6 = i4 + (i5 - this.f2204K);
        f2165I0 = i6;
        this.f2204K = i5;
        int i7 = this.f2223c;
        if (i7 <= i5) {
            if (i3 > 2000000) {
                if (i6 <= 0) {
                    f2165I0 = i6 + 1;
                }
                f2164H0 = i3 / f2165I0;
                f2165I0 = 1;
            }
            int i8 = f2164H0 / (f2165I0 + 1);
            if (i8 < 30) {
                i8 = 30;
            }
            int i9 = i / (i5 + 1);
            if (i8 < i9) {
                i8 = i9;
            }
            float f = (float) (i2 - i);
            float f2 = (((float) i8) * 30.0f) - f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            int i10 = i7 + (((int) ((f * 1.05f) + f2)) / i8) + f2177j0;
            this.f2223c = i10;
            C0685o[] oVarArr = new C0685o[i10];
            C0685o[] oVarArr2 = this.f2220a;
            if (oVarArr2 != null) {
                System.arraycopy(oVarArr2, 0, oVarArr, 0, i5);
            }
            this.f2220a = oVarArr;
        }
    }

    int m467v(int i, int i2, boolean z) {
        StringBuilder sb = Util.C0427h.f1649b;
        int i3 = i;
        while (true) {
            int i4 = i + i2;
            if (i3 >= i4) {
                return i2;
            }
            char[] cArr = this.f2231k;
            char c = cArr[i3];
            char c2 = 8203;
            if ('\r' == c) {
                if (z) {
                    c2 = 0;
                }
                cArr[i3] = c2;
            } else if ('&' == c) {
                sb.setLength(0);
                int i5 = i3 + 1;
                int i6 = i5 + 8;
                if (i6 > i4) {
                    i6 = i4;
                }
                if (i5 < i6) {
                    boolean z2 = this.f2231k[i5] == '#';
                    int i7 = z2 ? i5 + 1 : i5;
                    while (i7 < i6) {
                        char[] cArr2 = this.f2231k;
                        if (cArr2[i7] == ';') {
                            break;
                        }
                        sb.append(cArr2[i7]);
                        i7++;
                    }
                    if (i7 < i4 && this.f2231k[i7] == ';') {
                        int i8 = i7 - i3;
                        int i9 = i8 - 1;
                        int i10 = -1;
                        if (i9 != 0) {
                            if (z2) {
                                int i11 = i9 - 1;
                                if (i11 != 0) {
                                    if (sb.charAt(0) == 'x' || sb.charAt(0) == 'X') {
                                        i10 = Integer.parseInt(sb.substring(1, (i11 - 1) + 1), 16);
                                    } else {
                                        boolean z3 = sb.length() == 2;
                                        if (z3 && sb.charAt(0) == '9' && sb.charAt(1) == '1') {
                                            i10 = 91;
                                        } else if (z3 && sb.charAt(0) == '9' && sb.charAt(1) == '3') {
                                            i10 = 93;
                                        } else if (z3 && sb.charAt(0) == '3' && sb.charAt(1) == '3') {
                                            i10 = 33;
                                        } else {
                                            try {
                                                i10 = Integer.parseInt(sb.toString());
                                            } catch (NumberFormatException unused) {
                                            }
                                        }
                                    }
                                }
                            } else {
                                i10 = Util.C0427h.m642b(sb.toString());
                            }
                        }
                        if (i10 > 0) {
                            if (i10 <= 65535) {
                                this.f2231k[i3] = (char) i10;
                            } else if (i10 <= 1114111) {
                                char[] cArr3 = this.f2231k;
                                cArr3[i3] = (char) ((i10 >> 10) + 55232);
                                cArr3[i5] = (char) ((i10 & 1023) + 56320);
                                i8--;
                                i3 = i5;
                            } else {
                                this.f2231k[i3] = ' ';
                            }
                            int i12 = i3 + 1;
                            int i13 = i8 + i12;
                            while (i12 < i13) {
                                this.f2231k[i12] = z ? (char) 0 : 8203;
                                i12++;
                            }
                            i3 = i12 - 1;
                        }
                    }
                }
            }
            i3++;
        }
    }

    public SparseArray<String> m464y() {
        C0670a[] aVarArr = this.f2212S;
        if (aVarArr == null || aVarArr.length <= 0) {
            return null;
        }
        SparseArray<String> sparseArray = new SparseArray<>(this.f2212S.length);
        int i = 0;
        while (true) {
            C0670a[] aVarArr2 = this.f2212S;
            if (i >= aVarArr2.length) {
                return sparseArray;
            }
            sparseArray.put(aVarArr2[i].f2247a, aVarArr2[i].f2254h);
            i++;
        }
    }

    public boolean m463z(int i) {
        return (i & 268435456) > 0;
    }
}
