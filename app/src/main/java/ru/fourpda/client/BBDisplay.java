package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import org.json.JSONObject;

import in.cpp.picoimg.PicoDrawable;
import in.cpp.picoimg.PicoImg;
import in.cpp.picoimg.PicoImgRequest;


public class BBDisplay extends View implements View.OnClickListener, View.OnLongClickListener, PicoImgRequest.TargetCallback {
    public static Bitmap f517p;
    public static Drawable iconSnapback;
    static float[] f519r = new float[100000];
    static int f520s;
    static int f521t;
    static int[] f522u;
    static float[][] f523v;
    private Context f524a;
    Paint f525b;
    Rect f526c;
    BBString f527d;
    BBOverlay f528e;
    private float f529f;
    private float[] f530g;
    Drawable[] f531h;
    boolean[] f532i;
    private int f533j;
    RunnableC0144d f534k;
    boolean f535l;
    float f536m;
    float f537n;
    IBBDisplayCallback f538o;

    public class AsyncTaskC0141a extends AsyncTask<String, Void, String> {
        final BBString.C0677h f539a;
        final int f540b;
        final boolean f541c;

        AsyncTaskC0141a(BBString.C0677h hVar, int i, boolean z) {
           // BBDisplay.this = r1;
            this.f539a = hVar;
            this.f540b = i;
            this.f541c = z;
        }

        public String doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setReadTimeout(3000);
                if (httpURLConnection.getResponseCode() != 200) {
                    return null;
                }
                byte[] bArr = new byte[4096];
                InputStream inputStream = httpURLConnection.getInputStream();
                int i = 0;
                while (true) {
                    int read = inputStream.read(bArr, i, 4096 - i);
                    if (read > 0) {
                        i += read;
                    } else {
                        inputStream.close();
                        return new JSONObject(new String(bArr, 0, i)).getString("thumbnail_url");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        public void onPostExecute(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f539a.f2279c = str;
                BBDisplay.this.m977d(this.f540b, this.f541c);
            }
        }
    }

    public interface IBBDisplayCallback {
        void showBBNotify(BBDisplay bBDisplay, BBString pVar, int i, String str);

        void mo133c(BBDisplay bBDisplay, BBString pVar, int i);

        void showBBOptionMenu(BBDisplay bBDisplay, BBString pVar, C0143c cVar);

        void mo129f(BBDisplay bBDisplay, BBString pVar, C0143c cVar);
    }

    public class RunnableC0144d implements Runnable {
        boolean f547a;

        RunnableC0144d() {
            //BBDisplay.this = r1;
        }

        @Override
        public void run() {
            if (this.f547a) {
                BBDisplay.this.requestLayout();
                BBOverlay bBOverlay = BBDisplay.this.f528e;
                if (bBOverlay != null) {
                    bBOverlay.requestLayout();
                }
            } else {
                BBDisplay.this.invalidate();
                BBOverlay bBOverlay2 = BBDisplay.this.f528e;
                if (bBOverlay2 != null) {
                    bBOverlay2.invalidate();
                }
            }
            this.f547a = false;
        }
    }

    public BBDisplay(Context context) {
        super(context);
        this.f525b = new Paint(1);
        this.f526c = new Rect();
        this.f530g = new float[48];
        this.f534k = new RunnableC0144d();
        this.f535l = false;
        m971j(context);
    }

    private void m976e() {
        int i = 0;
        while (true) {
            BBString pVar = this.f527d;
            if (i < pVar.f2210Q) {
                if (!this.f532i[i]) {
                    BBString.C0677h[] hVarArr = pVar.f2208O;
                    if (hVarArr[i].f2277a >= 0 && !pVar.f2220a[hVarArr[i].f2277a].m449e()) {
                        this.f532i[i] = true;
                        m977d(i, false);
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override
    public void onPicoImgResult(PicoImgRequest gVar, PicoDrawable eVar) {
        boolean z;
        float intrinsicWidth;
        BBString.C0677h[] hVarArr;
        int g = gVar.getAppId();
        BBString.C0677h hVar = (BBString.C0677h) gVar.getAppObj();
        BBString pVar = this.f527d;
        if (pVar != null && g < pVar.f2210Q && hVar == pVar.f2208O[g]) {
            this.f533j--;
            this.f531h[g] = eVar;
            if (eVar.isAnimated()) {
                eVar.setCallback(this.f528e);
            }
            BBString pVar2 = this.f527d;
            int i = pVar2.f2208O[g].f2278b;
            if (!pVar2.f2220a[i].m449e()) {
                BBString.C0677h[] hVarArr2 = this.f527d.f2208O;
                if (hVarArr2[g].f2286j == 0.0f || hVarArr2[g].f2287k == 0.0f) {
                    z = true;
                    intrinsicWidth = ((float) eVar.getIntrinsicWidth()) * this.f529f;
                    float intrinsicHeight = ((float) eVar.getIntrinsicHeight()) * this.f529f;
                    BBString pVar3 = this.f527d;
                    hVarArr = pVar3.f2208O;
                    if (hVarArr[g].f2286j == 0.0f && hVarArr[g].f2287k == 0.0f) {
                        hVarArr[g].f2286j = intrinsicWidth;
                    }
                    if (hVarArr[g].f2287k == 0.0f) {
                        hVarArr[g].f2287k = (hVarArr[g].f2286j * intrinsicHeight) / intrinsicWidth;
                    }
                    if (hVarArr[g].f2286j == 0.0f) {
                        hVarArr[g].f2286j = (hVarArr[g].f2287k * intrinsicWidth) / intrinsicHeight;
                    }
                    hVarArr[g].f2288l = hVarArr[g].f2286j / intrinsicWidth;
                    if (!z) {
                        BBString.C0685o[] oVarArr = pVar3.f2220a;
                        int i2 = oVarArr[i].f2328e;
                        float f = oVarArr[i2].f2335l;
                        m968m(oVarArr[i].f2332i, oVarArr[i].f2333j, i, i2, oVarArr[i].f2329f);
                        BBString pVar4 = this.f527d;
                        pVar4.f2217X = (int) m962s(pVar4.f2220a[i2].f2335l - f, i2 + 1, pVar4.f2222b - 1);
                        IBBDisplayCallback bVar = this.f538o;
                        if (bVar != null) {
                            BBString pVar5 = this.f527d;
                            bVar.mo133c(this, pVar5, pVar5.f2217X);
                        }
                        m964q(true);
                        return;
                    }
                    m964q(false);
                    return;
                }
            }
            z = false;
            intrinsicWidth = ((float) eVar.getIntrinsicWidth()) * this.f529f;
            float intrinsicHeight2 = ((float) eVar.getIntrinsicHeight()) * this.f529f;
            BBString pVar32 = this.f527d;
            hVarArr = pVar32.f2208O;
            if (hVarArr[g].f2286j == 0.0f) {
                hVarArr[g].f2286j = intrinsicWidth;
            }
            if (hVarArr[g].f2287k == 0.0f) {
            }
            if (hVarArr[g].f2286j == 0.0f) {
            }
            hVarArr[g].f2288l = hVarArr[g].f2286j / intrinsicWidth;
            if (!z) {
            }
        }
    }

    @Override
    public void onPicoImgProgress(PicoImgRequest gVar, int i, int i2) {
    }

    @Override
    public void onPicoImgError(PicoImgRequest gVar, Throwable th) {
        th.printStackTrace();
    }

    @SuppressLint({"StaticFieldLeak"})
    public void m977d(int i, boolean z) {
        Context context = getContext();
        BBString.C0677h hVar = this.f527d.f2208O[i];
        int i2 = hVar.f2280d;
        if (i2 == R.drawable.ic_snapback) {
            this.f531h[i] = iconSnapback.getConstantState().newDrawable();
        } else {
            int i3 = 0;
            if (i2 != 0) {
                PicoImgRequest k = PicoImg.loadResource(this.f524a, i2);
                k.callback(this);
                k.setAppId(i);
                k.setAppObj(hVar);
                k.disableAnimation(!Prefs.animSmiles);
                int i4 = hVar.f2286j > 0.0f ? (int) hVar.f2281e : 0;
                if (hVar.f2287k > 0.0f) {
                    i3 = (int) hVar.f2282f;
                }
                k.size(i4, i3);
                k.run();
            } else if (!TextUtils.isEmpty(hVar.f2279c)) {
                if (!hVar.f2290n || !hVar.f2279c.startsWith("https://vimeo.com/api/oembed.json")) {
                    PicoImgRequest l = PicoImg.loadUrl(context, hVar.f2279c);
                    l.callback(this);
                    l.setAppId(i);
                    l.setAppObj(hVar);
                    l.disableAnimation(!hVar.f2289m ? !Prefs.animImages : !Prefs.animSmiles);
                    l.cachedOnly(!Prefs.loadImages && !z);
                    int i5 = hVar.f2286j > 0.0f ? (int) hVar.f2281e : 0;
                    if (hVar.f2287k > 0.0f) {
                        i3 = (int) hVar.f2282f;
                    }
                    l.size(i5, i3);
                    l.run();
                } else {
                    new AsyncTaskC0141a(hVar, i, z).execute(hVar.f2279c);
                    return;
                }
            }
        }
        this.f533j++;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & -65281;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.f536m = x;
            this.f537n = y;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    void m975f(int i, int i2, Canvas canvas, boolean z, boolean z2) {
        int i3;
        if (z) {
            float[] fArr = this.f530g;
            BBString.C0685o[] oVarArr = this.f527d.f2220a;
            fArr[0] = oVarArr[i].f2332i;
            fArr[1] = oVarArr[i].f2333j;
            fArr[2] = oVarArr[i].f2334k - 1.0f;
            fArr[3] = oVarArr[i].f2333j;
            fArr[4] = oVarArr[i].f2332i;
            fArr[5] = oVarArr[i].f2333j;
            fArr[6] = oVarArr[i2].f2332i;
            i3 = 8;
            fArr[7] = oVarArr[i2].f2335l - 1.0f;
        } else {
            i3 = 0;
        }
        if (z2) {
            float[] fArr2 = this.f530g;
            int i4 = i3 + 1;
            BBString.C0685o[] oVarArr2 = this.f527d.f2220a;
            fArr2[i3] = oVarArr2[i].f2334k - 1.0f;
            int i5 = i4 + 1;
            fArr2[i4] = oVarArr2[i].f2333j;
            int i6 = i5 + 1;
            fArr2[i5] = oVarArr2[i2].f2334k - 1.0f;
            int i7 = i6 + 1;
            fArr2[i6] = oVarArr2[i2].f2335l - 1.0f;
            int i8 = i7 + 1;
            fArr2[i7] = oVarArr2[i2].f2332i;
            int i9 = i8 + 1;
            fArr2[i8] = oVarArr2[i2].f2335l - 1.0f;
            int i10 = i9 + 1;
            fArr2[i9] = oVarArr2[i2].f2334k - 1.0f;
            i3 = i10 + 1;
            fArr2[i10] = oVarArr2[i2].f2335l - 1.0f;
        }
        float f = this.f529f;
        if (f >= 2.0f) {
            if (z) {
                float[] fArr3 = this.f530g;
                int i11 = i3 + 1;
                BBString.C0685o[] oVarArr3 = this.f527d.f2220a;
                fArr3[i3] = oVarArr3[i].f2332i - 1.0f;
                int i12 = i11 + 1;
                fArr3[i11] = oVarArr3[i].f2333j - 1.0f;
                int i13 = i12 + 1;
                fArr3[i12] = oVarArr3[i].f2334k;
                int i14 = i13 + 1;
                fArr3[i13] = oVarArr3[i].f2333j - 1.0f;
                int i15 = i14 + 1;
                fArr3[i14] = oVarArr3[i].f2332i - 1.0f;
                int i16 = i15 + 1;
                fArr3[i15] = oVarArr3[i].f2333j - 1.0f;
                int i17 = i16 + 1;
                fArr3[i16] = oVarArr3[i2].f2332i - 1.0f;
                i3 = i17 + 1;
                fArr3[i17] = oVarArr3[i2].f2335l;
            }
            if (z2) {
                float[] fArr4 = this.f530g;
                int i18 = i3 + 1;
                BBString.C0685o[] oVarArr4 = this.f527d.f2220a;
                fArr4[i3] = oVarArr4[i].f2334k;
                int i19 = i18 + 1;
                fArr4[i18] = oVarArr4[i].f2333j - 1.0f;
                int i20 = i19 + 1;
                fArr4[i19] = oVarArr4[i2].f2334k;
                int i21 = i20 + 1;
                fArr4[i20] = oVarArr4[i2].f2335l;
                int i22 = i21 + 1;
                fArr4[i21] = oVarArr4[i2].f2332i - 1.0f;
                int i23 = i22 + 1;
                fArr4[i22] = oVarArr4[i2].f2335l;
                int i24 = i23 + 1;
                fArr4[i23] = oVarArr4[i2].f2334k;
                i3 = i24 + 1;
                fArr4[i24] = oVarArr4[i2].f2335l;
            }
            if (f >= 3.0f) {
                if (z) {
                    float[] fArr5 = this.f530g;
                    int i25 = i3 + 1;
                    BBString.C0685o[] oVarArr5 = this.f527d.f2220a;
                    fArr5[i3] = oVarArr5[i].f2332i - 2.0f;
                    int i26 = i25 + 1;
                    fArr5[i25] = oVarArr5[i].f2333j - 2.0f;
                    int i27 = i26 + 1;
                    fArr5[i26] = oVarArr5[i].f2334k + 1.0f;
                    int i28 = i27 + 1;
                    fArr5[i27] = oVarArr5[i].f2333j - 2.0f;
                    int i29 = i28 + 1;
                    fArr5[i28] = oVarArr5[i].f2332i - 2.0f;
                    int i30 = i29 + 1;
                    fArr5[i29] = oVarArr5[i].f2333j - 2.0f;
                    int i31 = i30 + 1;
                    fArr5[i30] = oVarArr5[i2].f2332i - 2.0f;
                    i3 = i31 + 1;
                    fArr5[i31] = oVarArr5[i2].f2335l + 1.0f;
                }
                if (z2) {
                    float[] fArr6 = this.f530g;
                    int i32 = i3 + 1;
                    BBString.C0685o[] oVarArr6 = this.f527d.f2220a;
                    fArr6[i3] = oVarArr6[i].f2334k + 1.0f;
                    int i33 = i32 + 1;
                    fArr6[i32] = oVarArr6[i].f2333j - 2.0f;
                    int i34 = i33 + 1;
                    fArr6[i33] = oVarArr6[i2].f2334k + 1.0f;
                    int i35 = i34 + 1;
                    fArr6[i34] = oVarArr6[i2].f2335l + 1.0f;
                    int i36 = i35 + 1;
                    fArr6[i35] = oVarArr6[i2].f2332i - 2.0f;
                    int i37 = i36 + 1;
                    fArr6[i36] = oVarArr6[i2].f2335l + 1.0f;
                    int i38 = i37 + 1;
                    fArr6[i37] = oVarArr6[i2].f2334k + 1.0f;
                    i3 = i38 + 1;
                    fArr6[i38] = oVarArr6[i2].f2335l + 1.0f;
                }
            }
        }
        canvas.drawLines(this.f530g, 0, i3, this.f525b);
    }

    public float[] m974g(int i) {
        int i2;
        int i3 = 500;
        if (i < 200) {
            i3 = 0;
        } else if (i < 300) {
            i3 = 1;
        } else if (i < 400) {
            i3 = 2;
        } else if (i < 500) {
            i3 = 3;
        } else if (i < 700) {
            i3 = 4;
        } else if (i < 1000) {
            i3 = 5;
        } else if (i < 2000) {
            i3 = 10;
        } else if (i < 3000) {
            i3 = 50;
        } else if (i < 5000) {
            i3 = 100;
        } else if (i >= 10000) {
            i3 = 1000;
        }
        int i4 = -1;
        int i5 = 0;
        while (true) {
            i2 = f520s;
            if (i5 < i2) {
                int i6 = f522u[i5];
                if (i6 >= i && i6 <= i + i3) {
                    i4 = i5;
                    break;
                }
                i5++;
            } else {
                break;
            }
        }
        if (i4 >= 0) {
            return f523v[i4];
        }
        if (i > 10000 && i <= 100000) {
            return f519r;
        }
        int i7 = f521t;
        if (i7 <= i2) {
            int i8 = i7 + 200;
            f521t = i8;
            float[][] fArr = new float[i8][];
            float[][] fArr2 = f523v;
            if (fArr2 != null) {
                System.arraycopy(fArr2, 0, fArr, 0, i2);
            }
            f523v = fArr;
            int[] iArr = new int[f521t];
            int[] iArr2 = f522u;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, f520s);
            }
            f522u = iArr;
        }
        float[] fArr3 = new float[i];
        float[][] fArr4 = f523v;
        int i9 = f520s;
        fArr4[i9] = fArr3;
        f522u[i9] = i;
        f520s = i9 + 1;
        return fArr3;
    }

    public int m973h(String str) {
        BBString pVar = this.f527d;
        int i = 0;
        if (pVar == null) {
            return 0;
        }
        int i2 = pVar.f2222b;
        if (!str.startsWith("Spoil-")) {
            i2 = 0;
            while (true) {
                BBString pVar2 = this.f527d;
                if (i2 >= pVar2.f2222b) {
                    break;
                }
                BBString.C0685o oVar = pVar2.f2220a[i2];
                if (6 == oVar.m444j() && str.equalsIgnoreCase(new String(this.f527d.f2231k, oVar.f2328e, oVar.f2329f))) {
                    break;
                }
                i2++;
            }
        } else {
            try {
                int parseInt = Integer.parseInt(str.substring(str.lastIndexOf(45) + 1));
                i2 = 0;
                int i3 = 0;
                while (true) {
                    BBString pVar3 = this.f527d;
                    if (i2 >= pVar3.f2222b) {
                        break;
                    }
                    BBString.C0685o oVar2 = pVar3.f2220a[i2];
                    if (7424 == oVar2.m445i() && 129 == oVar2.m444j() && oVar2.m451c() && parseInt == (i3 = i3 + 1)) {
                        break;
                    }
                    i2++;
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (i2 >= this.f527d.f2222b) {
            return 0;
        }
        boolean z = false;
        while (i < i2) {
            BBString.C0685o oVar3 = this.f527d.f2220a[i];
            if (129 == oVar3.m444j()) {
                if (7424 != oVar3.m445i()) {
                    int i4 = oVar3.f2328e;
                    if (i4 < i2) {
                        i = i4;
                    }
                } else if (oVar3.m448f()) {
                    int i5 = oVar3.f2328e + 1;
                    BBString.C0685o[] oVarArr = this.f527d.f2220a;
                    int i6 = oVarArr[i5].f2328e;
                    if (i6 < i2) {
                        i = i6;
                    } else if (oVarArr[i5].m446h()) {
                        m966o(i, true);
                        i = i5;
                        z = true;
                    }
                }
            }
            i++;
        }
        if (z) {
            IBBDisplayCallback bVar = this.f538o;
            if (bVar != null) {
                BBString pVar4 = this.f527d;
                bVar.mo133c(this, pVar4, pVar4.f2217X);
            }
            m964q(true);
        }
        if (this.f527d.f2220a[i2].m445i() == 7424) {
            i2 = this.f527d.f2220a[i2 - 1].f2328e;
        }
        if (this.f527d.f2220a[i2].m444j() == 6) {
            i2++;
        }
        return (int) this.f527d.f2220a[i2].f2333j;
    }

    public int m972i(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 <= i; i3++) {
            BBString.C0685o oVar = this.f527d.f2220a[i3];
            if (oVar.m444j() == 129 && oVar.m448f() && oVar.m445i() == 7424) {
                i2++;
            }
        }
        return i2;
    }

    public void m971j(Context context) {
        this.f524a = context;
        this.f529f = context.getResources().getDisplayMetrics().density;
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public int m970k(int i) {
        BBString pVar = this.f527d;
        if (pVar.f2216W != i) {
            BBString.C0674e eVar = pVar.f2246z;
            float f = (float) ((i - eVar.f2265i) - eVar.f2266j);
            pVar.f2215V = f;
            pVar.f2214U = f / 900.0f;
            pVar.f2217X = (int) m968m(0.0f, 0.0f, 0, pVar.f2222b - 1, i);
            this.f527d.f2216W = i;
        }
        return this.f527d.f2217X;
    }

    int m969l(int i, int i2) {
        int i3;
        if (i2 < i) {
            return 0;
        }
        float f = 0.0f;
        while (i <= i2) {
            BBString.C0685o[] oVarArr = this.f527d.f2220a;
            if (oVarArr[i].f2328e != -1 && 8 == oVarArr[i].m444j()) {
                BBString.C0685o[] oVarArr2 = this.f527d.f2220a;
                oVarArr2[i].f2333j = m963r(oVarArr2[i].f2327d, oVarArr2[i].f2324a, false);
                BBString pVar = this.f527d;
                int length = pVar.f2205L[pVar.f2220a[i].f2328e].length();
                float[] g = m974g(length);
                Paint paint = this.f525b;
                BBString pVar2 = this.f527d;
                paint.getTextWidths(pVar2.f2205L[pVar2.f2220a[i].f2328e], 0, length + 0, g);
                float f2 = this.f527d.f2201H.f2323v;
                for (int i4 = 0; i4 < length; i4++) {
                    f2 += g[i4];
                }
                if (f2 > f) {
                    if (f2 < this.f527d.f2201H.f2322u) {
                        f = f2;
                    } else {
                        i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            int i5 = i3 + 1;
                            f2 -= g[i3];
                            if (f2 < this.f527d.f2201H.f2322u) {
                                f = f2;
                                i3 = i5;
                                break;
                            }
                            i3 = i5;
                        }
                        BBString.C0685o[] oVarArr3 = this.f527d.f2220a;
                        oVarArr3[i].f2329f = i3;
                        oVarArr3[i].f2331h = length - i3;
                        oVarArr3[i].f2332i = -f2;
                    }
                }
                i3 = 0;
                BBString.C0685o[] oVarArr32 = this.f527d.f2220a;
                oVarArr32[i].f2329f = i3;
                oVarArr32[i].f2331h = length - i3;
                oVarArr32[i].f2332i = -f2;
            }
            i++;
        }
        return (int) (f + 0.5f);
    }

    float m968m(float f, float f2, int i, int i2, int i3) {
        boolean z = false;
        int i4 = 0;
        boolean z2;
        float f3 = 0;
        int i5 =0;
        float f4 = 0;
        int i6=0;
        int i7=0;
        int i8=0;
        int i9=0;
        int i10=0;
        int i11=0;
        float f5=0;
        int i12=0;
        int i13=0;
        int i14=0;
        float f6=0;
        BBString.C0685o[] oVarArr;
        float f7;
        Vector vector = null;
        float f8 = 0;
        int i15 = 0;
        BBString.C0685o oVar;
        int i16;
        float f9 = 0;
        int i17;
        float f10 = 0;
        float f11 = 0;
        boolean z3;
        int i18 = i2;
        float f12 = f;
        float f13 = f2;
        int i19 = i;
        int i20 = i3;
        int i21 = -1;
        boolean z4 = false;
        while (i19 <= i18) {
            boolean z5 = i21 >= 0 || this.f527d.f2220a[i19].m446h();
            BBString.C0685o[] oVarArr2 = this.f527d.f2220a;
            oVarArr2[i19].f2324a |= 1073741824;
            int i22 = oVarArr2[i19].m445i();
            int j = this.f527d.f2220a[i19].m444j();
            if (j != 6) {
                if (j == 129) {
                    i4 = i21;
                    BBString.C0685o[] oVarArr3 = this.f527d.f2220a;
                    int i23 = oVarArr3[i19].f2327d;
                    if (i23 >= 0 && !oVarArr3[i19].m442l() && this.f527d.f2220a[i23].m445i() == 6400) {
                        if (i19 != i) {
                            int i24 = this.f527d.f2220a[i23].f2331h;
                            f12 += (float) i24;
                            i20 -= i24;
                        }
                    }
                    BBString.C0685o[] oVarArr4 = this.f527d.f2220a;
                    oVarArr4[i19].f2332i = f12;
                    oVarArr4[i19].f2333j = f13;
                    oVarArr4[i19].f2329f = i20;
                    if (oVarArr4[i19].m446h() && i4 < 0) {
                        i21 = i19;
                    } else if (!z5) {
                        BBString pVar = this.f527d;
                        if (i20 < pVar.f2245y.f2258b) {
                            i21 = i19;
                            z = true;
                            if (z) {
                            }
                            i19++;
                            i18 = i2;
                        } else {
                            if (i19 >= 2 && pVar.f2220a[i19 - 2].m444j() == 8) {
                                BBString pVar2 = this.f527d;
                                BBString.C0679j.C0680a[] aVarArr = pVar2.f2211R.f2307a;
                                BBString.C0685o[] oVarArr5 = pVar2.f2220a;
                                BBString.C0679j.C0680a aVar = aVarArr[oVarArr5[i19 - 1].f2330g];
                                BBString.C0685o oVar2 = oVarArr5[i19];
                                f13 = aVar.f2313a[aVar.f2315c - 1].f2298e;
                                oVar2.f2333j = f13;
                            }
                            if (i19 >= 2) {
                                int i25 = i19 - 1;
                                if (this.f527d.f2220a[i25].m442l() && !this.f527d.f2220a[i25].m449e() && this.f527d.f2220a[i19 - 2].m444j() != 8) {
                                    z2 = true;
                                    if (!this.f527d.f2220a[i19].m448f()) {
                                        if (i22 == 7168) {
                                            BBString pVar3 = this.f527d;
                                            BBString.C0688q qVar = pVar3.f2194A;
                                            int i26 = qVar.f2260d;
                                            int i27 = qVar.f2269m;
                                            i20 -= (i26 + i27) + qVar.f2270n;
                                            f12 += (float) (i26 + i27);
                                            if (z2) {
                                                BBString.C0685o oVar3 = pVar3.f2220a[i19];
                                                f13 += qVar.f2261e;
                                                oVar3.f2333j = f13;
                                            }
                                            f3 = qVar.f2267k;
                                        } else if (i22 == 7424) {
                                            BBString pVar4 = this.f527d;
                                            BBString.C0692u uVar = pVar4.f2195B;
                                            int i28 = uVar.f2260d;
                                            int i29 = uVar.f2269m;
                                            i20 -= (i28 + i29) + uVar.f2270n;
                                            f12 += (float) (i28 + i29);
                                            if (z2) {
                                                BBString.C0685o oVar4 = pVar4.f2220a[i19];
                                                f13 += uVar.f2261e;
                                                oVar4.f2333j = f13;
                                            }
                                            f3 = uVar.f2267k;
                                        } else if (i22 == 6912) {
                                            BBString pVar5 = this.f527d;
                                            BBString.C0672c cVar = pVar5.f2196C;
                                            int i30 = cVar.f2260d;
                                            int i31 = cVar.f2269m;
                                            i20 -= (i30 + i31) + cVar.f2270n;
                                            f12 += (float) (i30 + i31);
                                            if (z2) {
                                                BBString.C0685o oVar5 = pVar5.f2220a[i19];
                                                f13 += cVar.f2261e;
                                                oVar5.f2333j = f13;
                                            }
                                            f3 = cVar.f2267k;
                                        } else if (i22 == 6656) {
                                            BBString pVar6 = this.f527d;
                                            BBString.C0676g gVar = pVar6.f2197D;
                                            int i32 = gVar.f2260d;
                                            int i33 = gVar.f2269m;
                                            i20 -= (i32 + i33) + gVar.f2270n;
                                            f12 += (float) (i32 + i33);
                                            if (z2) {
                                                BBString.C0685o oVar6 = pVar6.f2220a[i19];
                                                f13 += gVar.f2261e;
                                                oVar6.f2333j = f13;
                                            }
                                            f3 = gVar.f2267k;
                                        }
                                        f13 += f3;
                                    } else if (this.f527d.f2220a[i19].m451c()) {
                                        if (i22 == 7168) {
                                            BBString.C0688q qVar2 = this.f527d.f2194A;
                                            int i34 = qVar2.f2260d;
                                            int i35 = qVar2.f2265i;
                                            i20 -= (i34 + i35) + qVar2.f2266j;
                                            f12 += (float) (i34 + i35);
                                            f3 = qVar2.f2263g;
                                        } else if (i22 == 7424) {
                                            BBString.C0692u uVar2 = this.f527d.f2195B;
                                            int i36 = uVar2.f2260d;
                                            int i37 = uVar2.f2265i;
                                            i20 -= (i36 + i37) + uVar2.f2266j;
                                            f12 += (float) (i36 + i37);
                                            f3 = uVar2.f2263g;
                                        } else if (i22 == 6912) {
                                            BBString pVar7 = this.f527d;
                                            BBString.C0672c cVar2 = pVar7.f2196C;
                                            int i38 = cVar2.f2260d;
                                            int i39 = cVar2.f2265i;
                                            i20 -= (i38 + i39) + cVar2.f2266j;
                                            f12 += (float) (i38 + i39);
                                            z4 = pVar7.f2220a[i19].m447g();
                                            f3 = this.f527d.f2196C.f2263g;
                                        } else if (i22 == 6656) {
                                            BBString.C0676g gVar2 = this.f527d.f2197D;
                                            int i40 = gVar2.f2260d;
                                            int i41 = gVar2.f2265i;
                                            i20 -= (i40 + i41) + gVar2.f2266j;
                                            f12 += (float) (i40 + i41);
                                            f3 = gVar2.f2263g;
                                        }
                                        f13 += f3;
                                    } else {
                                        if (this.f527d.f2220a[i19].m450d()) {
                                            BBString.C0674e eVar = this.f527d.f2246z;
                                            int i42 = eVar.f2265i;
                                            i20 -= eVar.f2266j + i42;
                                            f12 += (float) i42;
                                            f3 = eVar.f2263g;
                                        } else if (i22 == 5376) {
                                            BBString pVar8 = this.f527d;
                                            BBString.C0675f fVar = pVar8.f2198E;
                                            int i43 = fVar.f2260d;
                                            int i44 = fVar.f2265i;
                                            i20 -= (i43 + i44) + fVar.f2266j;
                                            f12 += (float) (i43 + i44);
                                            if (z2) {
                                                BBString.C0685o oVar7 = pVar8.f2220a[i19];
                                                f13 += fVar.f2261e;
                                                oVar7.f2333j = f13;
                                            }
                                            f3 = fVar.f2263g;
                                        } else if (i22 == 5632) {
                                            BBString pVar9 = this.f527d;
                                            BBString.C0684n nVar = pVar9.f2199F;
                                            int i45 = nVar.f2260d;
                                            int i46 = nVar.f2265i;
                                            i20 -= (i45 + i46) + nVar.f2266j;
                                            f12 += (float) (i45 + i46);
                                            if (z2) {
                                                BBString.C0685o oVar8 = pVar9.f2220a[i19];
                                                f13 += nVar.f2261e;
                                                oVar8.f2333j = f13;
                                            }
                                            f3 = nVar.f2263g;
                                        } else if (i22 == 5888) {
                                            BBString pVar10 = this.f527d;
                                            BBString.C0673d dVar = pVar10.f2200G;
                                            int i47 = dVar.f2260d;
                                            int i48 = dVar.f2265i;
                                            i20 -= (i47 + i48) + dVar.f2266j;
                                            f12 += (float) (i47 + i48);
                                            if (z2) {
                                                BBString.C0685o oVar9 = pVar10.f2220a[i19];
                                                f13 += dVar.f2261e;
                                                oVar9.f2333j = f13;
                                            }
                                            f3 = dVar.f2263g;
                                        } else if (i22 == 6400) {
                                            if (z2) {
                                                BBString pVar11 = this.f527d;
                                                BBString.C0685o oVar10 = pVar11.f2220a[i19];
                                                f13 += pVar11.f2201H.f2261e;
                                                oVar10.f2333j = f13;
                                            }
                                            BBString.C0685o[] oVarArr6 = this.f527d.f2220a;
                                            int i49 = oVarArr6[i19].f2328e;
                                            int i50 = oVarArr6[i19].f2330g;
                                            if (i50 < 0) {
                                                i50 = i49;
                                            }
                                            int i51 = i19;
                                            int i52 = 0;
                                            int i53 = 1;
                                            while (true) {
                                                int i54 = i51 + i53;
                                                while (i54 < i50) {
                                                    BBString.C0685o[] oVarArr7 = this.f527d.f2220a;
                                                    if (oVarArr7[i54].f2327d == i19) {
                                                        i5 = 1;
                                                        int l = m969l(i54 + 1, oVarArr7[i54].f2328e - 1);
                                                        if (l > i52) {
                                                            i52 = l;
                                                        }
                                                    } else {
                                                        i5 = 1;
                                                    }
                                                    i54 = this.f527d.f2220a[i54].f2328e + i5;
                                                }
                                                if (i50 == i49) {
                                                    break;
                                                }
                                                BBString.C0685o[] oVarArr8 = this.f527d.f2220a;
                                                int i55 = oVarArr8[i50].f2328e;
                                                i50 = oVarArr8[i55].f2330g;
                                                i53 = 1;
                                                i51 = i55;
                                            }
                                            this.f527d.f2220a[i19].f2331h = i52;
                                            i21 = i4;
                                            f13 = f13;
                                        } else if (i22 == 8960) {
                                            BBString pVar12 = this.f527d;
                                            BBString.C0671b bVar = pVar12.f2245y;
                                            int i56 = bVar.f2265i;
                                            i20 -= bVar.f2266j + i56;
                                            f12 += (float) i56;
                                            if (z2) {
                                                BBString.C0685o oVar11 = pVar12.f2220a[i19];
                                                f13 += bVar.f2261e;
                                                oVar11.f2333j = f13;
                                            }
                                            f3 = bVar.f2263g;
                                        } else if (i22 == 9216) {
                                            if (z2) {
                                                BBString pVar13 = this.f527d;
                                                BBString.C0685o oVar12 = pVar13.f2220a[i19];
                                                f13 += pVar13.f2245y.f2261e;
                                                oVar12.f2333j = f13;
                                            }
                                        } else if (i22 == 9728) {
                                            BBString.C0671b bVar2 = this.f527d.f2245y;
                                            int i57 = bVar2.f2265i;
                                            i20 -= bVar2.f2266j + i57;
                                            f12 += (float) i57;
                                            f3 = bVar2.f2263g;
                                        }
                                        f13 += f3;
                                    }
                                }
                            }
                            z2 = false;
                            if (!this.f527d.f2220a[i19].m448f()) {
                            }
                        }
                    }
                } else if (j != 130) {
                    i4 = i21;
                } else {
                    BBString.C0685o[] oVarArr9 = this.f527d.f2220a;
                    int i58 = oVarArr9[i19].f2328e;
                    BBString.C0685o oVar13 = oVarArr9[i19];
                    int i59 = oVarArr9[i58].f2329f;
                    oVar13.f2329f = i59;
                    BBString.C0685o oVar14 = oVarArr9[i19];
                    float f14 = oVarArr9[i58].f2332i;
                    oVar14.f2332i = f14;
                    oVarArr9[i19].f2333j = oVarArr9[i58].f2333j;
                    int i60 = oVarArr9[i19].f2327d;
                    if (i60 < 0 || oVarArr9[i60].m445i() != 6400) {
                        i6 = i59;
                        f4 = f14;
                        i7 = 0;
                    } else {
                        BBString.C0685o[] oVarArr10 = this.f527d.f2220a;
                        i7 = oVarArr10[i60].f2331h;
                        if (!oVarArr10[i19].m442l()) {
                            f14 -= (float) i7;
                            i59 += i7;
                        } else if (i19 == i18) {
                            m969l(i58 + 1, i19 - 1);
                        }
                        i6 = i59;
                        f4 = f14;
                    }
                    if (!z5) {
                        float f15 = 0.0f;
                        if (this.f527d.f2220a[i19].m442l()) {
                            i9 = i58;
                            i12 = i6;
                            i8 = i21;
                            float n = m967n(i58 + 1, i19 - 1, (float) i6, f4, f13, z4, this.f527d.f2220a[i19].f2330g, i22, (float) i7);
                            if (n > 0.0f) {
                                f13 = n;
                                z3 = z5;
                            } else {
                                this.f527d.f2220a[i9].m440n(true);
                                z3 = true;
                            }
                            z5 = z3;
                            i11 = i22;
                        } else {
                            i9 = i58;
                            i12 = i6;
                            i11 = i22;
                            i8 = i21;
                            if (i11 == 6912 && this.f527d.f2220a[i19].m451c()) {
                                z4 = false;
                            }
                        }
                        if (this.f527d.f2220a[i19].m448f()) {
                            if (i11 == 7168) {
                                f11 = this.f527d.f2194A.f2268l;
                            } else if (i11 == 7424) {
                                f11 = this.f527d.f2195B.f2268l;
                            } else if (i11 == 6912) {
                                f11 = this.f527d.f2196C.f2268l;
                            } else if (i11 == 6656) {
                                f11 = this.f527d.f2197D.f2268l;
                            }
                            f15 = f11;
                        } else {
                            if (this.f527d.f2220a[i19].m451c()) {
                                if (i11 == 7168) {
                                    f11 = this.f527d.f2194A.f2264h;
                                } else if (i11 == 7424) {
                                    f11 = this.f527d.f2195B.f2264h;
                                } else if (i11 == 6912) {
                                    f11 = this.f527d.f2196C.f2264h;
                                } else if (i11 == 6656) {
                                    f11 = this.f527d.f2197D.f2264h;
                                }
                            } else if (this.f527d.f2220a[i19].m450d()) {
                                f11 = this.f527d.f2246z.f2264h;
                            } else {
                                int i61 = 5376;
                                if (i11 != 5376) {
                                    i14 = 5888;
                                    i13 = 8960;
                                    if (i11 == 5632 || i11 == 5888 || i11 == 8960 || i11 == 9216 || i11 == 9728) {
                                        i61 = 5376;
                                    }
                                } else {
                                    i14 = 5888;
                                    i13 = 8960;
                                }
                                if (i11 == i61) {
                                    BBString.C0675f fVar2 = this.f527d.f2198E;
                                    f6 = fVar2.f2263g;
                                    f10 = fVar2.f2264h;
                                } else if (i11 == 5632) {
                                    BBString.C0684n nVar2 = this.f527d.f2199F;
                                    f6 = nVar2.f2263g;
                                    f10 = nVar2.f2264h;
                                } else if (i11 == i14) {
                                    BBString.C0673d dVar2 = this.f527d.f2200G;
                                    f6 = dVar2.f2263g;
                                    f10 = dVar2.f2264h;
                                } else if (i11 == i13) {
                                    BBString.C0671b bVar3 = this.f527d.f2245y;
                                    f6 = bVar3.f2263g;
                                    f10 = bVar3.f2264h;
                                } else {
                                    if (i11 == 9216) {
                                        try {
                                            Vector vector2 = new Vector(10);
                                            int i62 = i9 + 1;
                                            BBString.C0685o oVar15 = null;
                                            int i63 = 0;
                                            int i64 = 0;
                                            for (int i65 = i62; i65 < i19; i65++) {
                                                try {
                                                    BBString.C0685o oVar16 = this.f527d.f2220a[i65];
                                                    int j2 = oVar16.m444j();
                                                    int i66 = oVar16.m445i();
                                                    if (j2 == 3) {
                                                        if (oVar15 != null) {
                                                            i63 = (int) (((float) i63) + (oVar16.f2334k - oVar16.f2332i));
                                                        } else {
                                                            oVar16.m440n(true);
                                                        }
                                                    } else if (i66 == 9728) {
                                                        if (j2 == 129) {
                                                            oVar15 = oVar16;
                                                        } else if (j2 == 130) {
                                                            int max = Math.max(1, oVar15.f2325b & 255);
                                                            BBString.C0671b bVar4 = this.f527d.f2245y;
                                                            int max2 = bVar4.f2265i + Math.max(i63 / max, bVar4.f2258b) + this.f527d.f2245y.f2266j;
                                                            int i67 = i64;
                                                            while (true) {
                                                                i17 = i64 + max;
                                                                if (i67 >= i17) {
                                                                    break;
                                                                }
                                                                if (vector2.size() <= i67) {
                                                                    vector2.add(0);
                                                                }
                                                                if (((Integer) vector2.get(i67)).intValue() < max2) {
                                                                    vector2.set(i67, Integer.valueOf(max2));
                                                                }
                                                                i67++;
                                                            }
                                                            i64 = i17;
                                                            oVar15 = null;
                                                            i63 = 0;
                                                        }
                                                    } else if (i66 == 9472 && j2 == 130) {
                                                        i64 = 0;
                                                    }
                                                } catch (Exception unused) {
                                                    i10 = i12;
                                                    f6 = 0.0f;
                                                    BBString pVar14 = this.f527d;
                                                    oVarArr = pVar14.f2220a;
                                                    f7 = pVar14.f2245y.f2259c;
                                                    if ((f13 + f15) - oVarArr[i19].f2333j < f7) {
                                                    }
                                                    f13 += f15;
                                                    if (i11 != 9216) {
                                                    }
                                                    BBString.C0685o[] oVarArr11 = this.f527d.f2220a;
                                                    BBString.C0685o oVar17 = oVarArr11[i9];
                                                    oVarArr11[i19].f2335l = f13;
                                                    oVar17.f2335l = f13;
                                                    if (z5) {
                                                    }
                                                    if (!this.f527d.f2220a[i19].m451c()) {
                                                    }
                                                    if (z) {
                                                    }
                                                    i19++;
                                                    i18 = i2;
                                                }
                                            }
                                            int i68 = 0;
                                            for (int i69 = 0; i69 < vector2.size(); i69++) {
                                                i68 += ((Integer) vector2.get(i69)).intValue();
                                            }
                                            i10 = i12;
                                            float f16 = i68 > i10 ? ((float) i10) / ((float) i68) : 1.0f;
                                            int i70 = i62;
                                            BBString.C0685o oVar18 = null;
                                            int i71 = 0;
                                            float f17 = 0.0f;
                                            float f18 = 0.0f;
                                            while (i70 < i19) {
                                                try {
                                                    BBString.C0685o oVar19 = this.f527d.f2220a[i70];
                                                    int j3 = oVar19.m444j();
                                                    int i72 = oVar19.m445i();
                                                    if (i72 == 9472) {
                                                        if (j3 == 129) {
                                                            try {
                                                                oVar19.f2333j = this.f527d.f2220a[i9].f2333j + f17;
                                                                oVar18 = oVar19;
                                                                i15 = i70;
                                                                vector = vector2;
                                                                f8 = f13;
                                                                i71 = 0;
                                                                f18 = 0.0f;
                                                            } catch (Exception unused2) {
                                                            }
                                                        } else if (j3 == 130) {
                                                            int i73 = oVar19.f2328e + 1;
                                                            while (i73 < i70) {
                                                                BBString.C0685o oVar20 = this.f527d.f2220a[i73];
                                                                if (9728 == oVar20.m445i() && 130 == oVar20.m444j()) {
                                                                    BBString.C0685o oVar21 = this.f527d.f2220a[oVar20.f2328e];
                                                                    f9 = f13;
                                                                    float f19 = oVar20.f2333j + ((float) i71);
                                                                    try {
                                                                        oVar21.f2335l = f19;
                                                                        oVar20.f2335l = f19;
                                                                        continue;
                                                                    } catch (Exception unused3) {
                                                                    }
                                                                } else {
                                                                    f9 = f13;
                                                                    continue;
                                                                }
                                                                i73++;
                                                                f13 = f9;
                                                            }
                                                            f8 = f13;
                                                            float f20 = oVar18.f2333j;
                                                            oVar19.f2333j = f20;
                                                            float f21 = (float) i71;
                                                            float f22 = f20 + f21;
                                                            oVar19.f2335l = f22;
                                                            oVar18.f2335l = f22;
                                                            f17 += f21;
                                                            i15 = i70;
                                                            vector = vector2;
                                                            oVar18 = null;
                                                            i64 = 0;
                                                        } else {
                                                            f8 = f13;
                                                        }
                                                        i70 = i15 + 1;
                                                        f13 = f8;
                                                        vector2 = vector;
                                                    } else {
                                                        f8 = f13;
                                                        if (i72 == 9728 && j3 == 130) {
                                                            int max3 = Math.max(1, this.f527d.f2220a[oVar19.f2328e].f2325b & 255);
                                                            int i74 = i64;
                                                            int i75 = 0;
                                                            while (true) {
                                                                i16 = i64 + max3;
                                                                if (i74 >= i16) {
                                                                    break;
                                                                }
                                                                i75 += ((Integer) vector2.get(i74)).intValue();
                                                                i74++;
                                                            }
                                                            BBString.C0671b bVar5 = this.f527d.f2245y;
                                                            int max4 = (int) Math.max(((float) i75) * f16, (float) (bVar5.f2265i + bVar5.f2258b + bVar5.f2266j));
                                                            vector = vector2;
                                                            oVar = oVar18;
                                                            i15 = i70;
                                                            m968m(oVar18.f2332i + f18, oVar18.f2333j, oVar19.f2328e, i70, max4);
                                                            int i76 = (int) (oVar19.f2335l - oVar19.f2333j);
                                                            i71 = i76 > i71 ? i76 : i71;
                                                            f18 += (float) max4;
                                                            i64 = i16;
                                                            oVar18 = oVar;
                                                            i70 = i15 + 1;
                                                            f13 = f8;
                                                            vector2 = vector;
                                                        }
                                                    }
                                                    oVar = oVar18;
                                                    i15 = i70;
                                                    vector = vector2;
                                                    i71 = i71;
                                                    oVar18 = oVar;
                                                    i70 = i15 + 1;
                                                    f13 = f8;
                                                    vector2 = vector;
                                                } catch (Exception unused4) {
                                                }
                                            }
                                            BBString.C0685o[] oVarArr12 = this.f527d.f2220a;
                                            f13 = oVarArr12[i9].f2333j + f17;
                                            BBString.C0685o oVar22 = oVarArr12[i9];
                                            BBString.C0685o oVar23 = oVarArr12[i19];
                                            float f23 = oVarArr12[i9].f2332i + (((float) i68) * f16);
                                            oVar23.f2334k = f23;
                                            oVar22.f2334k = f23;
                                        } catch (Exception unused5) {
                                        }
                                        f6 = 0.0f;
                                        BBString pVar142 = this.f527d;
                                        oVarArr = pVar142.f2220a;
                                        f7 = pVar142.f2245y.f2259c;
                                        if ((f13 + f15) - oVarArr[i19].f2333j < f7) {
                                            float f24 = (float) ((int) (((f7 - ((f13 - oVarArr[i19].f2333j) - f6)) / 2.0f) + 0.5f));
                                            int i77 = i9 + 1;
                                            int i78 = i19 - 1;
                                            f13 = i77 <= i78 ? m962s(f24 - f6, i77, i78) : f13 + (f24 - f6);
                                            f15 = f24;
                                        }
                                        f13 += f15;
                                    } else {
                                        f9 = f13;
                                        i10 = i12;
                                        if (i11 == 9728) {
                                            BBString.C0671b bVar6 = this.f527d.f2245y;
                                            f6 = bVar6.f2263g;
                                            f15 = bVar6.f2264h;
                                            f13 = f9;
                                            BBString pVar1422 = this.f527d;
                                            oVarArr = pVar1422.f2220a;
                                            f7 = pVar1422.f2245y.f2259c;
                                            if ((f13 + f15) - oVarArr[i19].f2333j < f7) {
                                            }
                                            f13 += f15;
                                        }
                                    }
                                    f13 = f9;
                                    f6 = 0.0f;
                                    BBString pVar14222 = this.f527d;
                                    oVarArr = pVar14222.f2220a;
                                    f7 = pVar14222.f2245y.f2259c;
                                    if ((f13 + f15) - oVarArr[i19].f2333j < f7) {
                                    }
                                    f13 += f15;
                                }
                                f15 = f10;
                                i10 = i12;
                                BBString pVar142222 = this.f527d;
                                oVarArr = pVar142222.f2220a;
                                f7 = pVar142222.f2245y.f2259c;
                                if ((f13 + f15) - oVarArr[i19].f2333j < f7) {
                                }
                                f13 += f15;
                            }
                            f15 = f11;
                        }
                        i10 = i12;
                        f13 += f15;
                    } else {
                        i9 = i58;
                        i10 = i6;
                        i11 = i22;
                        i8 = i21;
                    }
                    if (i11 != 9216) {
                        BBString.C0685o[] oVarArr13 = this.f527d.f2220a;
                        BBString.C0685o oVar24 = oVarArr13[i9];
                        float f25 = f4 + ((float) i10);
                        oVarArr13[i19].f2334k = f25;
                        oVar24.f2334k = f25;
                    }
                    BBString.C0685o[] oVarArr112 = this.f527d.f2220a;
                    BBString.C0685o oVar172 = oVarArr112[i9];
                    oVarArr112[i19].f2335l = f13;
                    oVar172.f2335l = f13;
                    if ((z5 || i9 == i8) && !oVarArr112[i19].m450d() && this.f527d.f2220a[i19 + 1].m444j() != 130) {
                        if (!this.f527d.f2220a[i19].m451c()) {
                            if (i11 == 7168) {
                                f5 = this.f527d.f2194A.f2262f;
                            } else if (i11 == 7424) {
                                f5 = this.f527d.f2195B.f2262f;
                            } else if (i11 == 6912) {
                                f5 = this.f527d.f2196C.f2262f;
                            } else if (i11 == 6656) {
                                f5 = this.f527d.f2197D.f2262f;
                            }
                            f13 += f5;
                        } else if (!this.f527d.f2220a[i19].m448f()) {
                            if (i11 == 5376) {
                                f5 = this.f527d.f2198E.f2262f;
                            } else if (i11 == 5632) {
                                f5 = this.f527d.f2199F.f2262f;
                            } else if (i11 == 5888) {
                                f5 = this.f527d.f2200G.f2262f;
                            } else if (i11 == 6400) {
                                f5 = this.f527d.f2201H.f2262f;
                            } else if (i11 == 8960) {
                                f5 = this.f527d.f2245y.f2262f;
                            } else if (i11 == 9216) {
                                f5 = this.f527d.f2245y.f2262f;
                            }
                            f13 += f5;
                        }
                        if (z) {
                            this.f527d.f2220a[i19].f2324a ^= 1073741824;
                        }
                        i19++;
                        i18 = i2;
                    }
                    if (i9 == i8) {
                        i20 = i10;
                        z = z5;
                        f12 = f4;
                        i21 = -1;
                    } else {
                        i21 = i8;
                        i20 = i10;
                        z = z5;
                        f12 = f4;
                    }
                    if (z) {
                    }
                    i19++;
                    i18 = i2;
                }
                z = z5;
                if (z) {
                }
                i19++;
                i18 = i2;
            } else {
                i4 = i21;
                BBString.C0685o[] oVarArr14 = this.f527d.f2220a;
                BBString.C0685o oVar25 = oVarArr14[i19];
                oVarArr14[i19].f2334k = f12;
                oVar25.f2332i = f12;
                BBString.C0685o oVar26 = oVarArr14[i19];
                oVarArr14[i19].f2335l = f13;
                oVar26.f2333j = f13;
            }
            i21 = i4;
            z = z5;
            if (z) {
            }
            i19++;
            i18 = i2;
        }
        return f13;
    }

    float m967n(int i, int i2, float f, float f2, float f3, boolean z, int i3, int i4, float f4) {
        int i5;
        float f5;
        int i6 = 0;
        float f6=0;
        float[] fArr = new float[0];
        int i7=0;
        int i8=0;
        String str;
        int i9=0;
        String str2;
        boolean z2;
        float f7=0;
        char c;
        int i10=0;
        int i11=0;
        int i12=0;
        float f8=0;
        int i13=0;
        String str3;
        float[] fArr2;
        int i14=0;
        float[] fArr3;
        BBString.C0677h[] hVarArr;
        float f9=0;
        float f10=0;
        float f11=0;
        float f12=0;
        float f13=0;
        float f14=0;
        int i15=0;
        int i16=0;
        int i17=0;
        boolean z3 = true;
        int i18 = (i2 - i) + 1;
        if (i18 <= 0) {
            return f3;
        }
        BBString.C0693v[] vVarArr = new BBString.C0693v[i18];
        int i19 = 0;
        int i20 = 0;
        while (true) {
            i5 = 7;
            int i21 = 3;
            f5 = 0.0f;
            if (i19 >= i18) {
                break;
            }
            vVarArr[i19] = new BBString.C0693v();
            int i22 = i + i19;
            int j = this.f527d.f2220a[i22].m444j();
            if (3 == j) {
                i17 = this.f527d.f2220a[i22].f2329f;
            } else if (5 == j) {
                BBString pVar = this.f527d;
                i17 = pVar.f2205L[pVar.f2220a[i22].f2328e].length();
            } else if (7 == j) {
                BBString pVar2 = this.f527d;
                pVar2.f2208O[pVar2.f2220a[i22].f2328e].f2285i = 0.0f;
                i17 = 1;
            } else {
                i17 = 0;
            }
            if (8 != j) {
                vVarArr[i19].f2357e = f;
            } else if (this.f527d.f2220a[i22].f2328e != -1) {
                i20 += i21;
                vVarArr[i19].f2359g = 0.0f;
                i19++;
            }
            i21 = i17;
            i20 += i21;
            vVarArr[i19].f2359g = 0.0f;
            i19++;
        }
        float[] fArr4 = f519r;
        float f15 = f - f4;
        this.f527d.f2211R.m458e(i3, vVarArr, i20);
        String str4 = "";
        int i23 = 0;
        int i24 = 0;
        float f16 = 0.0f;
        boolean z4 = true;
        while (true) {
            if (i23 >= i18) {
                break;
            }
            int i25 = i + i23;
            if (this.f527d.f2220a[i25].m449e()) {
                vVarArr[i23].f2353a = z3;
                fArr = fArr4;
            } else {
                int j2 = this.f527d.f2220a[i25].m444j();
                float f17 = 1.0f;
                if (i5 == j2) {
                    BBString pVar3 = this.f527d;
                    int i26 = pVar3.f2220a[i25].f2328e;
                    if (!Prefs.scaleImages) {
                        BBString.C0677h[] hVarArr2 = pVar3.f2208O;
                        if (hVarArr2[i26].f2285i == f5 && f15 >= pVar3.f2215V) {
                            float f18 = hVarArr2[i26].f2287k > f5 ? hVarArr2[i26].f2287k : BBString.f2167K0;
                            int i27 = i23;
                            int i28 = i27;
                            float f19 = 0.0f;
                            while (true) {
                                if (i27 >= i18) {
                                    fArr3 = fArr4;
                                    break;
                                }
                                int i29 = i27 + i;
                                BBString.C0685o oVar = this.f527d.f2220a[i29];
                                fArr3 = fArr4;
                                if (!(oVar.m444j() == i5 && (((i16 = oVar.f2331h) == -2 && this.f527d.f2212S[oVar.f2329f].f2253g == 0) || i16 == -1))) {
                                    break;
                                }
                                BBString pVar4 = this.f527d;
                                int i30 = pVar4.f2220a[i29].f2328e;
                                BBString.C0677h[] hVarArr3 = pVar4.f2208O;
                                if (f18 != (hVarArr3[i30].f2287k > 0.0f ? hVarArr3[i30].f2287k : BBString.f2167K0)) {
                                    break;
                                }
                                f19 += hVarArr3[i30].f2286j > 0.0f ? hVarArr3[i30].f2286j : BBString.f2166J0;
                                i27++;
                                i28 = i27;
                                i5 = 7;
                                fArr4 = fArr3;
                            }
                            if (i28 > i23) {
                                float f20 = f15 / f19;
                                for (int i31 = i23; i31 <= i28; i31++) {
                                    BBString pVar5 = this.f527d;
                                    pVar5.f2208O[pVar5.f2220a[i31 + i].f2328e].f2285i = f20;
                                }
                            }
                            BBString pVar6 = this.f527d;
                            hVarArr = pVar6.f2208O;
                            f9 = hVarArr[i26].f2286j <= 0.0f ? hVarArr[i26].f2286j : BBString.f2166J0;
                            f10 = hVarArr[i26].f2287k <= 0.0f ? hVarArr[i26].f2287k : BBString.f2167K0;
                            if (hVarArr[i26].f2291o) {
                                float f21 = this.f529f;
                                f11 = f9 / f21;
                                f12 = f10 / f21;
                            } else {
                                boolean z5 = Prefs.scaleImages;
                                if (z5 || hVarArr[i26].f2285i == 0.0f) {
                                    if (!z5 || pVar6.f2220a[i25].m443k(8388608) || f9 < 80.0f || f10 < 80.0f) {
                                        f11 = f9;
                                        f12 = f10;
                                    } else {
                                        f17 = this.f527d.f2214U / this.f529f;
                                    }
                                } else if (hVarArr[i26].f2285i > 0.0f) {
                                    f17 = hVarArr[i26].f2285i;
                                }
                                f11 = f9 * f17;
                                f12 = f10 * f17;
                            }
                            if (f11 <= f15 || this.f527d.f2208O[i26].f2292p) {
                                f17 = f15 / f9;
                                f12 = f10 * f17;
                                f11 = f15;
                            }
                            BBString pVar7 = this.f527d;
                            BBString.C0677h[] hVarArr4 = pVar7.f2208O;
                            hVarArr4[i26].f2283g = f17;
                            f13 = (float) ((int) (f11 + 0.5f));
                            float f22 = (float) ((int) (f12 + 0.5f));
                            hVarArr4[i26].f2281e = f13;
                            hVarArr4[i26].f2282f = f22;
                            if (f15 - f16 >= f13) {
                                i15 = i24 + 1;
                                f14 = 0.0f;
                            } else {
                                i15 = i24;
                                f14 = f16;
                            }
                            if (z || i15 < 10) {
                                pVar7.f2211R.m456g(i3, i20);
                                i20--;
                                BBString.C0679j jVar = this.f527d.f2211R;
                                jVar.f2311e++;
                                float f23 = f13 + f14;
                                jVar.m462a(i23, i15, f14, f23);
                                vVarArr[i23].f2354b = f22;
                                vVarArr[i23].f2353a = false;
                                i24 = i15;
                                f16 = f23;
                                fArr4 = fArr3;
                                z4 = false;
                            }
                        }
                    }
                    fArr3 = fArr4;
                    BBString pVar62 = this.f527d;
                    hVarArr = pVar62.f2208O;
                    if (hVarArr[i26].f2286j <= 0.0f) {
                    }
                    if (hVarArr[i26].f2287k <= 0.0f) {
                    }
                    if (hVarArr[i26].f2291o) {
                    }
                    if (f11 <= f15) {
                    }
                    f17 = f15 / f9;
                    f12 = f10 * f17;
                    f11 = f15;
                    BBString pVar72 = this.f527d;
                    BBString.C0677h[] hVarArr42 = pVar72.f2208O;
                    hVarArr42[i26].f2283g = f17;
                    f13 = (float) ((int) (f11 + 0.5f));
                    float f222 = (float) ((int) (f12 + 0.5f));
                    hVarArr42[i26].f2281e = f13;
                    hVarArr42[i26].f2282f = f222;
                    if (f15 - f16 >= f13) {
                    }
                    if (z) {
                    }
                    pVar72.f2211R.m456g(i3, i20);
                    i20--;
                    BBString.C0679j jVar2 = this.f527d.f2211R;
                    jVar2.f2311e++;
                    float f232 = f13 + f14;
                    jVar2.m462a(i23, i15, f14, f232);
                    vVarArr[i23].f2354b = f222;
                    vVarArr[i23].f2353a = false;
                    i24 = i15;
                    f16 = f232;
                    fArr4 = fArr3;
                    z4 = false;
                } else {
                    fArr = fArr4;
                    if (8 == j2) {
                        if (i23 > 0) {
                            i24++;
                        }
                        BBString pVar8 = this.f527d;
                        BBString.C0685o[] oVarArr = pVar8.f2220a;
                        if (oVarArr[i25].f2328e != -1) {
                            int i32 = oVarArr[i25].f2329f;
                            int i33 = oVarArr[i25].f2331h;
                            oVarArr[i25].f2331h = -1;
                            pVar8.f2211R.m456g(i3, i20);
                            i20 -= 3;
                            BBString pVar9 = this.f527d;
                            BBString.C0679j jVar3 = pVar9.f2211R;
                            jVar3.f2311e += 3;
                            BBString.C0693v vVar = vVarArr[i23];
                            BBString.C0685o[] oVarArr2 = pVar9.f2220a;
                            vVar.f2354b = oVarArr2[i25].f2333j;
                            vVarArr[i23].f2353a = !jVar3.m461b(i23, i24, i32, i33, oVarArr2[i25].f2332i, 0.0f);
                        } else {
                            vVarArr[i23].f2353a = true;
                        }
                        i24 = i24;
                        fArr4 = fArr;
                        f16 = 0.0f;
                        z4 = true;
                    } else if (9 == j2) {
                        vVarArr[i23].f2357e = 0.0f;
                        vVarArr[i23].f2359g = f15;
                        BBString.C0693v vVar2 = vVarArr[i23];
                        BBString.C0685o[] oVarArr3 = this.f527d.f2220a;
                        vVar2.f2354b = m963r(oVarArr3[i25].f2327d, oVarArr3[i25].f2324a, false);
                        vVarArr[i23].f2353a = false;
                    } else {
                        BBString.C0693v vVar3 = vVarArr[i23];
                        BBString.C0685o[] oVarArr4 = this.f527d.f2220a;
                        vVar3.f2354b = m963r(oVarArr4[i25].f2327d, oVarArr4[i25].f2324a, false);
                        if (3 == j2) {
                            BBString.C0685o[] oVarArr5 = this.f527d.f2220a;
                            i8 = oVarArr5[i25].f2328e;
                            int i34 = oVarArr5[i25].f2329f;
                            if ((oVarArr5[i25].f2327d & -65536) == 65536) {
                                fArr2 = m974g(i34);
                                str3 = str4;
                                this.f525b.getTextWidths("code", 0, 1, fArr2);
                                float f24 = fArr2[0];
                                int i35 = i8;
                                int i36 = 0;
                                while (i36 < i34) {
                                    char[] cArr = this.f527d.f2231k;
                                    if (cArr[i35] != 0) {
                                        i14 = i20;
                                        if (cArr[i35] != 8203) {
                                            fArr2[i36] = f24;
                                            i36++;
                                            i35++;
                                            i20 = i14;
                                        }
                                    } else {
                                        i14 = i20;
                                    }
                                    fArr2[i36] = 0.0f;
                                    i36++;
                                    i35++;
                                    i20 = i14;
                                }
                                i7 = i20;
                            } else {
                                str3 = str4;
                                i7 = i20;
                                fArr2 = m974g(i34);
                                this.f525b.getTextWidths(this.f527d.f2231k, i8, i34, fArr2);
                            }
                            str = str3;
                            fArr4 = fArr2;
                            i9 = i34;
                        } else {
                            i7 = i20;
                            if (5 == j2) {
                                BBString pVar10 = this.f527d;
                                str = pVar10.f2205L[pVar10.f2220a[i25].f2328e];
                                int length = str.length();
                                fArr4 = m974g(length);
                                this.f525b.getTextWidths(str, 0, length + 0, fArr4);
                                i9 = length;
                            } else {
                                str = str4;
                                fArr4 = fArr;
                                i9 = 0;
                            }
                            i8 = 0;
                        }
                        int i37 = i9 + i8;
                        float f25 = f15;
                        int i38 = i8;
                        int i39 = i38;
                        int i40 = i24;
                        float f26 = f16;
                        float f27 = f26;
                        boolean z6 = z4;
                        i6 = i7;
                        int i41 = 0;
                        boolean z7 = false;
                        int i42 = i37;
                        while (true) {
                            if (i38 >= i37) {
                                str2 = str;
                                break;
                            }
                            if (3 == j2) {
                                c = this.f527d.f2231k[i38];
                            } else {
                                c = 5 == j2 ? str.charAt(i38) : ' ';
                            }
                            if (c == 0) {
                                i41++;
                                str2 = str;
                                i10 = j2;
                            } else {
                                str2 = str;
                                if ('\n' == c) {
                                    if (z && i40 >= 10) {
                                        break;
                                    }
                                    this.f527d.f2211R.m456g(i3, i6);
                                    int i43 = i38 - i39;
                                    int i44 = i43 + 1;
                                    int i45 = i6 - i44;
                                    BBString.C0679j jVar4 = this.f527d.f2211R;
                                    i10 = j2;
                                    jVar4.f2311e += i44;
                                    if (i43 <= i41) {
                                        if (z6) {
                                            i13 = i40 + 1;
                                            jVar4.m462a(i23, i40, f26, fArr4[i38 - i8] + f26);
                                        } else {
                                            i13 = i40 + 1;
                                            jVar4.m462a(i23, i40, f26, f26);
                                        }
                                        i40 = i13;
                                    } else {
                                        jVar4.m461b(i23, i40, i39, i43, f26, f27);
                                        i40++;
                                    }
                                    i39 = i38 + 1;
                                    i42 = i37;
                                    i6 = i45;
                                } else {
                                    i10 = j2;
                                    char c2 = ' ';
                                    if (' ' == c) {
                                        if (f27 < 1.0f) {
                                            i6--;
                                            this.f527d.f2211R.f2311e++;
                                            i39 = i38 + 1;
                                        } else {
                                            c2 = ' ';
                                        }
                                    }
                                    if (c2 != c) {
                                        i12 = i42;
                                        z7 = false;
                                    } else if (!z7) {
                                        i12 = i38;
                                        f25 = f27;
                                        z7 = true;
                                    } else {
                                        i12 = i42;
                                    }
                                    int i46 = i38 - i8;
                                    float f28 = f27 + fArr4[i46];
                                    if (f28 > f15) {
                                        if (i12 <= i38) {
                                            i38 = i12;
                                            f8 = f25;
                                        } else if (z6) {
                                            f8 = f28 - fArr4[i46];
                                        } else {
                                            f8 = f28;
                                            i38 = i39;
                                        }
                                        int i47 = i38 - 1;
                                        int i48 = i38 - i39;
                                        if (i48 > 0) {
                                            if (z && i40 >= 10) {
                                                f7 = f8;
                                                z2 = true;
                                                break;
                                            }
                                            this.f527d.f2211R.m456g(i3, i6);
                                            i6 -= i48;
                                            BBString.C0679j jVar5 = this.f527d.f2211R;
                                            jVar5.f2311e += i48;
                                            int i49 = i40 + 1;
                                            if (jVar5.m461b(i23, i40, i39, i48, f26, f8)) {
                                                z6 = true;
                                            }
                                            i39 = i38;
                                            i38 = i47;
                                            i40 = i49;
                                            i42 = i37;
                                            i11 = 1;
                                            f26 = 0.0f;
                                            i41 = 0;
                                            f27 = 0.0f;
                                            i38 += i11;
                                            i37 = i37;
                                            str = str2;
                                            j2 = i10;
                                        } else {
                                            i40++;
                                            i38 = i47;
                                            i42 = i37;
                                        }
                                    } else {
                                        i42 = i12;
                                        f27 = f28;
                                    }
                                }
                                i11 = 1;
                                f26 = 0.0f;
                                i41 = 0;
                                f27 = 0.0f;
                                z6 = true;
                                i38 += i11;
                                i37 = i37;
                                str = str2;
                                j2 = i10;
                            }
                            i11 = 1;
                            i38 += i11;
                            i37 = i37;
                            str = str2;
                            j2 = i10;
                        }
                        z2 = true;
                        f7 = f27;
                        if (!z || i40 < 10) {
                            this.f527d.f2211R.m456g(i3, i6);
                            int i50 = i37 - i39;
                            i20 = i6 - i50;
                            BBString.C0679j jVar6 = this.f527d.f2211R;
                            jVar6.f2311e += i50;
                            z4 = jVar6.m461b(i23, i40, i39, i50, f26, f7) ? false : z6;
                            vVarArr[i23].f2353a = vVarArr[i23].f2355c == 0;
                            f16 = f7;
                            i24 = i40;
                            str4 = str2;
                        } else {
                            int i51 = z2 ? 1 : 0;
                            int i52 = z2 ? 1 : 0;
                            int i53 = z2 ? 1 : 0;
                            int i54 = z2 ? 1 : 0;
                            int i55 = i23 + i51;
                            while (i55 < i18) {
                                vVarArr[i55].f2353a = z2;
                                i55++;
                                z2 = true;
                            }
                        }
                    }
                }
                i23++;
                z3 = true;
                i5 = 7;
                f5 = 0.0f;
            }
            fArr4 = fArr;
            i23++;
            z3 = true;
            i5 = 7;
            f5 = 0.0f;
        }
        BBString.C0679j jVar7 = this.f527d.f2211R;
        BBString.C0678i[] iVarArr = jVar7.f2307a[i3].f2313a;
        jVar7.f2311e += i6;
        int i56 = 0;
        int i57 = 0;
        float f29 = 0.0f;
        float f30 = 0.0f;
        float f31 = 0.0f;
        for (int i58 = 0; i58 < i18; i58++) {
            if (!vVarArr[i58].f2353a) {
                if (this.f527d.f2220a[i + i58].m444j() == 9) {
                    BBString.C0693v vVar4 = vVarArr[i58];
                    BBString.C0693v vVar5 = vVarArr[i58];
                    float f32 = (float) ((int) (f31 + (vVarArr[i58].f2354b / 2.0f)));
                    vVar5.f2360h = f32;
                    vVar4.f2358f = f32;
                } else {
                    int i59 = vVarArr[i58].f2356d;
                    int i60 = (vVarArr[i58].f2355c + i59) - 1;
                    if (i56 != iVarArr[i59].f2294a) {
                        f31 += f29;
                    }
                    float f33 = f31;
                    vVarArr[i58].f2358f = f33;
                    while (i59 <= i60) {
                        float f34 = vVarArr[i58].f2354b;
                        int i61 = iVarArr[i59].f2294a;
                        for (int i62 = i58 + 1; i62 < i18; i62++) {
                            if (!vVarArr[i62].f2353a && this.f527d.f2220a[i + i62].m444j() != 9) {
                                if (i61 != iVarArr[vVarArr[i62].f2356d].f2294a) {
                                    break;
                                } else if (f34 < vVarArr[i62].f2354b) {
                                    f34 = vVarArr[i62].f2354b;
                                }
                            }
                        }
                        if (i56 == i61 && f29 > f34) {
                            f34 = f29;
                        }
                        iVarArr[i59].f2301h = f34;
                        iVarArr[i59].f2298e = f33;
                        float f35 = f33 + f34;
                        iVarArr[i59].f2300g = f35;
                        if (i59 != i60) {
                            f33 = f35;
                        }
                        i59++;
                        f29 = f34;
                        i57 = i61;
                        f30 = f29;
                    }
                    vVarArr[i58].f2360h = f33 + f30;
                    i56 = i57;
                    f31 = f33;
                }
            }
        }
        if (i4 == 33536 || i4 == 33280) {
            for (int i63 = 0; i63 < i18; i63++) {
                if (this.f527d.f2220a[i + i63].m444j() != 8 && !vVarArr[i63].f2353a) {
                    int i64 = vVarArr[i63].f2356d;
                    int i65 = (vVarArr[i63].f2355c + i64) - 1;
                    vVarArr[i63].f2357e = f15;
                    vVarArr[i63].f2359g = 0.0f;
                    while (i64 <= i65) {
                        float f36 = iVarArr[i64].f2299f;
                        int i66 = iVarArr[i64].f2294a;
                        for (int i67 = i63 + 1; i67 < i18; i67++) {
                            if (!vVarArr[i67].f2353a) {
                                if (i66 != iVarArr[vVarArr[i67].f2356d].f2294a) {
                                    break;
                                }
                                f36 = iVarArr[vVarArr[i67].f2356d].f2299f;
                            }
                        }
                        float f37 = f15 - f36;
                        if (i4 == 33536) {
                            f37 /= 2.0f;
                        }
                        float f38 = (float) ((int) (f37 + 0.5f));
                        BBString.C0678i iVar = iVarArr[i64];
                        float f39 = iVar.f2297d + f38;
                        iVar.f2297d = f39;
                        BBString.C0678i iVar2 = iVarArr[i64];
                        float f40 = iVar2.f2299f + f38;
                        iVar2.f2299f = f40;
                        if (f39 < vVarArr[i63].f2357e) {
                            vVarArr[i63].f2357e = f39;
                        }
                        if (vVarArr[i63].f2359g < f40) {
                            vVarArr[i63].f2359g = f40;
                        }
                        i64++;
                    }
                }
            }
        }
        float f41 = -1.0f;
        for (int i68 = 0; i68 < i18; i68++) {
            BBString.C0685o[] oVarArr6 = this.f527d.f2220a;
            int i69 = i + i68;
            oVarArr6[i69].f2324a |= 1073741824;
            if (!vVarArr[i68].f2353a) {
                int i70 = vVarArr[i68].f2356d;
                int i71 = (vVarArr[i68].f2355c + i70) - 1;
                int j3 = oVarArr6[i69].m444j();
                BBString pVar11 = this.f527d;
                BBString.C0685o[] oVarArr7 = pVar11.f2220a;
                oVarArr7[i69].f2332i = vVarArr[i68].f2357e + f2 + f4;
                oVarArr7[i69].f2333j = vVarArr[i68].f2358f + f3;
                oVarArr7[i69].f2334k = vVarArr[i68].f2359g + f2 + f4;
                BBString.C0685o oVar2 = oVarArr7[i69];
                float f42 = vVarArr[i68].f2360h + f3;
                oVar2.f2335l = f42;
                if (j3 != 9) {
                    if (j3 == 7) {
                        float f43 = iVarArr[i70].f2301h;
                        float f44 = vVarArr[i68].f2354b;
                        if (f43 > f44) {
                            float f45 = 0.25f * f43;
                            float f46 = f43 - f44;
                            f6 = f46 > f45 ? f45 : f46 / 2.0f;
                        } else {
                            f6 = 0.0f;
                        }
                        pVar11.f2208O[oVarArr7[i69].f2328e].f2284h = (float) ((int) (f6 + (BBString.f2187t0 / 4.0f) + 0.5f));
                    } else {
                        for (int i72 = i70; i72 <= i71; i72++) {
                            float f47 = f2 + f4;
                            iVarArr[i72].f2297d += f47;
                            iVarArr[i72].f2298e += f3;
                            iVarArr[i72].f2299f += f47;
                            iVarArr[i72].f2300g += f3;
                            iVarArr[i72].f2301h = (float) ((int) (((iVarArr[i72].f2301h * -0.25f) - (BBString.f2187t0 / 4.0f)) - 0.5f));
                            iVarArr[i72].f2294a = i71;
                        }
                        this.f527d.f2220a[i69].f2330g = i70;
                    }
                }
                this.f527d.f2220a[i69].f2324a ^= 1073741824;
                f41 = f42;
            }
        }
        this.f527d.f2211R.m459d(i3);
        return f41;
    }

    boolean m966o(int i, boolean z) {
        BBString.C0685o oVar = this.f527d.f2220a[i];
        if (129 != oVar.m444j() || 7424 != oVar.m445i() || !oVar.m448f()) {
            return false;
        }
        int i2 = oVar.f2328e + 1;
        BBString.C0685o[] oVarArr = this.f527d.f2220a;
        int i3 = oVarArr[i2].f2328e;
        if (oVarArr[i2].m446h() != z) {
            return false;
        }
        this.f527d.f2232l.put(i, z);
        if (z) {
            BBString pVar = this.f527d;
            BBString.C0685o[] oVarArr2 = pVar.f2220a;
            oVarArr2[i2].f2324a &= Integer.MAX_VALUE;
            oVarArr2[i3].f2324a &= Integer.MAX_VALUE;
            oVar.f2325b = pVar.f2195B.f2274r;
        } else {
            BBString pVar2 = this.f527d;
            BBString.C0685o[] oVarArr3 = pVar2.f2220a;
            oVarArr3[i2].f2324a |= Integer.MIN_VALUE;
            oVarArr3[i3].f2324a |= Integer.MIN_VALUE;
            oVar.f2325b = pVar2.f2195B.f2275s;
        }
        BBString.C0685o[] oVarArr4 = this.f527d.f2220a;
        float f = oVarArr4[i3].f2335l;
        m968m(oVarArr4[i2].f2332i, oVarArr4[i2].f2333j, i2, i3, oVarArr4[i2].f2329f);
        BBString pVar3 = this.f527d;
        pVar3.f2217X = (int) m962s(pVar3.f2220a[i3].f2335l - f, i3 + 1, pVar3.f2222b - 1);
        return true;
    }

    @Override
    public void onClick(View view) {
        IBBDisplayCallback bVar;
        boolean z;
        if (this.f527d != null) {
            boolean z2 = false;
            BBString.C0678i[] iVarArr = null;
            int i = 0;
            int i2 = -1;
            int i3 = -1;
            while (true) {
                BBString pVar = this.f527d;
                if (i >= pVar.f2222b) {
                    break;
                }
                BBString.C0685o oVar = pVar.f2220a[i];
                int j = oVar.m444j();
                if (j == 129) {
                    if (oVar.m442l()) {
                        iVarArr = this.f527d.f2211R.f2307a[oVar.f2330g].f2313a;
                    }
                } else if (j == 130 && oVar.m442l()) {
                    iVarArr = null;
                }
                if (!this.f527d.f2220a[i].m449e()) {
                    float f = oVar.f2332i;
                    float f2 = this.f536m;
                    if (f <= f2 && oVar.f2334k > f2) {
                        float f3 = oVar.f2333j;
                        float f4 = this.f537n;
                        if (f3 <= f4 && oVar.f2335l > f4) {
                            if (j == 129) {
                                if (oVar.m448f()) {
                                    if (oVar.m445i() == 7424 || oVar.m445i() == 6912) {
                                        break;
                                    }
                                } else if (oVar.m451c() && oVar.m445i() == 6912) {
                                    int i4 = oVar.f2328e;
                                    BBString.C0685o[] oVarArr = this.f527d.f2220a;
                                    oVarArr[i].f2324a ^= 134217728;
                                    oVarArr[i4].f2324a ^= 134217728;
                                    if (oVarArr[i].m447g()) {
                                        BBString pVar2 = this.f527d;
                                        BBString.C0685o[] oVarArr2 = pVar2.f2220a;
                                        int i5 = i - 1;
                                        BBString.C0685o oVar2 = oVarArr2[oVarArr2[i5].f2328e];
                                        BBString.C0685o oVar3 = oVarArr2[i5];
                                        int i6 = pVar2.f2196C.f2275s;
                                        oVar3.f2325b = i6;
                                        oVar2.f2325b = i6;
                                        BBString.C0685o oVar4 = oVarArr2[i];
                                        oVarArr2[i4].f2325b = i6;
                                        oVar4.f2325b = i6;
                                    } else {
                                        BBString pVar3 = this.f527d;
                                        BBString.C0685o[] oVarArr3 = pVar3.f2220a;
                                        int i7 = i - 1;
                                        BBString.C0685o oVar5 = oVarArr3[oVarArr3[i7].f2328e];
                                        BBString.C0685o oVar6 = oVarArr3[i7];
                                        int i8 = pVar3.f2196C.f2274r;
                                        oVar6.f2325b = i8;
                                        oVar5.f2325b = i8;
                                        BBString.C0685o oVar7 = oVarArr3[i];
                                        oVarArr3[i4].f2325b = i8;
                                        oVar7.f2325b = i8;
                                    }
                                    float f5 = this.f527d.f2220a[i4].f2335l;
                                    m968m(oVar.f2332i, oVar.f2333j, i, i4, oVar.f2329f);
                                    BBString pVar4 = this.f527d;
                                    pVar4.f2217X = (int) m962s(pVar4.f2220a[i4].f2335l - f5, i4 + 1, pVar4.f2222b - 1);
                                    IBBDisplayCallback bVar2 = this.f538o;
                                    if (bVar2 != null) {
                                        BBString pVar5 = this.f527d;
                                        bVar2.mo133c(this, pVar5, pVar5.f2217X);
                                    }
                                    m964q(true);
                                }
                            } else if (oVar.f2331h > -1 || oVar.m452b()) {
                                int i9 = oVar.f2331h;
                                if (i9 == -2) {
                                    BBString.C0670a[] aVarArr = this.f527d.f2212S;
                                    int i10 = oVar.f2329f;
                                    if (aVarArr[i10].f2249c) {
                                        i3 = i10;
                                        break;
                                    }
                                }
                                if (j == 7) {
                                    i2 = i9;
                                    break;
                                } else if (iVarArr != null) {
                                    int i11 = oVar.f2330g;
                                    int i12 = iVarArr[i11].f2294a;
                                    while (true) {
                                        if (i11 > i12) {
                                            z = false;
                                            break;
                                        }
                                        BBString.C0678i iVar = iVarArr[i11];
                                        float f6 = iVar.f2297d;
                                        float f7 = this.f536m;
                                        if (f6 <= f7 && iVar.f2299f > f7) {
                                            float f8 = iVar.f2298e;
                                            float f9 = this.f537n;
                                            if (f8 <= f9 && iVar.f2300g > f9) {
                                                int i13 = oVar.f2331h;
                                                if (i13 == -2) {
                                                    i3 = oVar.f2329f;
                                                } else {
                                                    i2 = i13;
                                                }
                                                z = true;
                                            }
                                        }
                                        i11++;
                                    }
                                    if (z) {
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                } else if (j == 129) {
                    i = this.f527d.f2220a[i].f2328e;
                }
                i++;
            }
            if (!z2 && (bVar = this.f538o) != null) {
                bVar.mo129f(this, this.f527d, new C0143c(i2, i3, -1, -1));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BBString.C0678i[] iVarArr;
        int i = 0;
        int i2 = 0;
        int i3=0;
        int i4=0;
        int i5=0;
        int i6=0;
        int i7=0;
        float f=0;
        String str;
        if (this.f527d != null) {
            canvas.getClipBounds(this.f526c);
            this.f525b.setStrokeCap(Paint.Cap.BUTT);
            this.f525b.setStrokeJoin(Paint.Join.MITER);
            this.f525b.setStrokeMiter(BBString.f2188u0);
            boolean z = false;
            BBString.C0678i[] iVarArr2 = null;
            int i8 = 0;
            while (true) {
                BBString pVar = this.f527d;
                if (i8 < pVar.f2222b) {
                    int j = pVar.f2220a[i8].m444j();
                    if (j == 129) {
                        if (this.f527d.f2220a[i8].m442l()) {
                            BBString pVar2 = this.f527d;
                            BBString.C0679j.C0680a[] aVarArr = pVar2.f2211R.f2307a;
                            BBString.C0685o[] oVarArr = pVar2.f2220a;
                            iVarArr2 = aVarArr[oVarArr[i8].f2330g] != null ? aVarArr[oVarArr[i8].f2330g].f2313a : null;
                        }
                    } else if (j == 130 && this.f527d.f2220a[i8].m442l()) {
                        iVarArr = null;
                        if (!this.f527d.f2220a[i8].m449e()) {
                            if (j == 129) {
                                i = this.f527d.f2220a[i8].f2328e;
                                i8 = i + 1;
                                iVarArr2 = iVarArr;
                                z = false;
                            }
                            i2 = i8;
                        } else {
                            BBString.C0685o[] oVarArr2 = this.f527d.f2220a;
                            float f2 = oVarArr2[i8].f2335l;
                            Rect rect = this.f526c;
                            if (f2 < ((float) rect.top) || oVarArr2[i8].f2333j >= ((float) rect.bottom)) {
                                i2 = i8;
                                if (j == 129) {
                                    i = oVarArr2[i2].f2328e;
                                    i8 = i + 1;
                                    iVarArr2 = iVarArr;
                                    z = false;
                                }
                            } else if (j != 3) {
                                if (j != 5) {
                                    if (j == 7) {
                                        if (oVarArr2[i8].f2326c != 0) {
                                            this.f525b.setColor(oVarArr2[i8].f2326c);
                                            BBString.C0685o[] oVarArr3 = this.f527d.f2220a;
                                            canvas.drawRect(oVarArr3[i8].f2332i, oVarArr3[i8].f2333j, oVarArr3[i8].f2334k, oVarArr3[i8].f2335l, this.f525b);
                                        }
                                        BBString pVar3 = this.f527d;
                                        BBString.C0677h[] hVarArr = pVar3.f2208O;
                                        BBString.C0685o[] oVarArr4 = pVar3.f2220a;
                                        BBString.C0677h hVar = hVarArr[oVarArr4[i8].f2328e];
                                        Drawable drawable = this.f531h[oVarArr4[i8].f2328e];
                                        if (drawable == null) {
                                            this.f525b.setColor(BBString.f2185r0);
                                            BBString.C0685o[] oVarArr5 = this.f527d.f2220a;
                                            float f3 = oVarArr5[i8].f2335l;
                                            float f4 = hVar.f2284h;
                                            float f5 = this.f529f;
                                            canvas.drawRect(oVarArr5[i8].f2332i + f5, ((oVarArr5[i8].f2335l - hVar.f2282f) - f4) + f5, oVarArr5[i8].f2334k - f5, (oVarArr5[i8].f2335l - f4) - f5, this.f525b);
                                            this.f525b.setColor(BBString.f2186s0);
                                            this.f525b.setStyle(Paint.Style.STROKE);
                                            this.f525b.setStrokeWidth(f5);
                                            float f6 = f5 + (f5 / 2.0f);
                                            BBString.C0685o[] oVarArr6 = this.f527d.f2220a;
                                            float f7 = oVarArr6[i8].f2335l - hVar.f2282f;
                                            float f8 = hVar.f2284h;
                                            canvas.drawRect(oVarArr6[i8].f2332i + f6, (f7 - f8) + f6, oVarArr6[i8].f2334k - f6, (oVarArr6[i8].f2335l - f8) - f6, this.f525b);
                                            this.f525b.setStyle(Paint.Style.FILL);
                                        } else if (!(drawable instanceof PicoDrawable) || !((PicoDrawable) drawable).isAnimated()) {
                                            BBString.C0685o[] oVarArr7 = this.f527d.f2220a;
                                            float f9 = oVarArr7[i8].f2335l;
                                            float f10 = hVar.f2284h;
                                            drawable.setBounds((int) oVarArr7[i8].f2332i, (int) ((f9 - f10) - hVar.f2282f), (int) oVarArr7[i8].f2334k, (int) (oVarArr7[i8].f2335l - f10));
                                            drawable.draw(canvas);
                                        }
                                        if (hVar.f2290n) {
                                            canvas.save();
                                            canvas.translate((this.f527d.f2220a[i8].f2332i + (hVar.f2281e / 2.0f)) - (((float) f517p.getWidth()) / 2.0f), (this.f527d.f2220a[i8].f2335l - ((hVar.f2282f + hVar.f2284h) / 2.0f)) - (((float) f517p.getHeight()) / 2.0f));
                                            canvas.drawBitmap(f517p, 0.0f, 0.0f, (Paint) null);
                                            canvas.restore();
                                        }
                                        i2 = i8;
                                    } else if (j != 8) {
                                        if (j == 9) {
                                            this.f525b.setColor(oVarArr2[i8].f2325b);
                                            m975f(i8, i8, canvas, true, false);
                                        } else if (j != 129) {
                                            if (j == 130) {
                                                int i9 = oVarArr2[i8].m445i();
                                                this.f525b.setStrokeWidth(0.0f);
                                                if (i9 == 7168) {
                                                    this.f525b.setColor(this.f527d.f2194A.f2276t);
                                                } else if (i9 == 7424) {
                                                    this.f525b.setColor(this.f527d.f2195B.f2276t);
                                                } else if (i9 == 6912) {
                                                    this.f525b.setColor(this.f527d.f2196C.f2276t);
                                                } else if (i9 == 6656) {
                                                    this.f525b.setColor(this.f527d.f2197D.f2276t);
                                                } else {
                                                    this.f525b.setColor(this.f527d.f2245y.f2276t);
                                                }
                                                if (this.f527d.f2220a[i8].m448f()) {
                                                    if (this.f527d.f2220a[i8 + 1].m446h()) {
                                                        m975f(i8, i8, canvas, true, true);
                                                    }
                                                } else if (this.f527d.f2220a[i8].m451c()) {
                                                    m975f(this.f527d.f2220a[i8].f2328e - 1, i8, canvas, true, true);
                                                } else if (i9 == 9216 || i9 == 9728) {
                                                    this.f525b.setColor(Skin.SkinColorModel.f1359O);
                                                    m975f(i8, i8, canvas, i9 == 9216, i9 == 9728);
                                                    this.f525b.setColor(-1);
                                                } else if (i9 == 5376 || i9 == 5632 || i9 == 5888) {
                                                    if (i9 == 5376) {
                                                        this.f525b.setColor(this.f527d.f2198E.f2276t);
                                                    } else if (i9 == 5632) {
                                                        this.f525b.setColor(this.f527d.f2199F.f2276t);
                                                    } else if (i9 == 5888) {
                                                        this.f525b.setColor(this.f527d.f2200G.f2276t);
                                                    }
                                                    m975f(i8, i8, canvas, true, true);
                                                    this.f525b.setColor(-1);
                                                    int i10 = z ? 1 : 0;
                                                    int i11 = z ? 1 : 0;
                                                    int i12 = z ? 1 : 0;
                                                    float r = (float) ((int) (m963r(5, i10, z) + 0.5f));
                                                    if (i9 == 5888) {
                                                        f = (float) this.f527d.f2200G.f2271o;
                                                        str = "К";
                                                    } else if (i9 == 5632) {
                                                        f = (float) this.f527d.f2199F.f2271o;
                                                        str = "М";
                                                    } else if (i9 == 5376) {
                                                        f = (float) this.f527d.f2198E.f2271o;
                                                        str = "!";
                                                    } else {
                                                        str = " ";
                                                        f = 0.0f;
                                                    }
                                                    BBString.C0685o[] oVarArr8 = this.f527d.f2220a;
                                                    canvas.drawText(str, oVarArr8[i8].f2332i + f, oVarArr8[i8].f2333j + r, this.f525b);
                                                }
                                            }
                                        } else if (!oVarArr2[i8].m442l()) {
                                            BBString.C0685o[] oVarArr9 = this.f527d.f2220a;
                                            int i13 = oVarArr9[i8].f2331h;
                                            float f11 = oVarArr9[i8].f2333j;
                                            float f12 = oVarArr9[i8].f2335l;
                                            if (i13 > 0 && oVarArr9[i8].f2325b != 0) {
                                                this.f525b.setColor(oVarArr9[i8].f2325b);
                                                BBString.C0685o[] oVarArr10 = this.f527d.f2220a;
                                                canvas.drawRect(oVarArr10[i8].f2332i, f11, oVarArr10[i8].f2332i + ((float) i13), f12, this.f525b);
                                            }
                                            BBString.C0685o[] oVarArr11 = this.f527d.f2220a;
                                            if (oVarArr11[i8].f2326c != 0) {
                                                this.f525b.setColor(oVarArr11[i8].f2326c);
                                                BBString.C0685o[] oVarArr12 = this.f527d.f2220a;
                                                int i14 = oVarArr12[i8].f2328e;
                                                int i15 = oVarArr12[i8].f2330g;
                                                float f13 = oVarArr12[i8].f2332i + ((float) i13);
                                                float f14 = oVarArr12[i8].f2334k;
                                                if (i15 >= 0) {
                                                    float f15 = f11;
                                                    int i16 = i15;
                                                    while (true) {
                                                        if (!this.f527d.f2220a[i16].m449e()) {
                                                            BBString.C0685o[] oVarArr13 = this.f527d.f2220a;
                                                            float f16 = i16 == i14 ? oVarArr13[i16].f2335l : oVarArr13[i16].f2333j;
                                                            canvas.drawRect(f13, f15, f14, f16, this.f525b);
                                                            BBString.C0685o[] oVarArr14 = this.f527d.f2220a;
                                                            float f17 = oVarArr14[i16].f2335l;
                                                            canvas.drawRect(f13, f16, oVarArr14[i16].f2332i, f17, this.f525b);
                                                            canvas.drawRect(this.f527d.f2220a[i16].f2334k, f16, f14, f17, this.f525b);
                                                            f15 = f17;
                                                        }
                                                        if (i16 == i14) {
                                                            break;
                                                        }
                                                        BBString.C0685o[] oVarArr15 = this.f527d.f2220a;
                                                        i16 = oVarArr15[oVarArr15[i16].f2328e].f2330g;
                                                    }
                                                } else {
                                                    canvas.drawRect(f13, f11, f14, f12, this.f525b);
                                                }
                                            }
                                        }
                                        i2 = i8;
                                    }
                                }
                                if (iVarArr != null) {
                                    float r2 = m963r(oVarArr2[i8].f2327d, oVarArr2[i8].f2324a, true);
                                    BBString pVar4 = this.f527d;
                                    String[] strArr = pVar4.f2205L;
                                    BBString.C0685o[] oVarArr16 = pVar4.f2220a;
                                    String str2 = strArr[oVarArr16[i8].f2328e];
                                    int i17 = oVarArr16[i8].f2330g;
                                    int i18 = iVarArr[i17].f2294a;
                                    int i19 = i17;
                                    while (i19 <= i18) {
                                        BBString.C0678i iVar = iVarArr[i19];
                                        float f18 = iVar.f2300g;
                                        Rect rect2 = this.f526c;
                                        if (f18 >= ((float) rect2.top) && iVar.f2298e < ((float) rect2.bottom)) {
                                            BBString.C0685o[] oVarArr17 = this.f527d.f2220a;
                                            if (oVarArr17[i8].f2326c != 0) {
                                                this.f525b.setColor(oVarArr17[i8].f2326c);
                                                canvas.drawRect(iVar.f2297d, iVar.f2298e, iVar.f2299f, iVar.f2300g, this.f525b);
                                            }
                                            if (iVar.f2296c > 0) {
                                                this.f525b.setColor(this.f527d.f2220a[i8].f2325b);
                                                int i20 = iVar.f2295b;
                                                i6 = i19;
                                                i7 = i18;
                                                i5 = i8;
                                                canvas.drawText(str2, i20, iVar.f2296c + i20, iVar.f2297d, iVar.f2300g + iVar.f2301h + r2, this.f525b);
                                                i19 = i6 + 1;
                                                i18 = i7;
                                                i8 = i5;
                                            }
                                        }
                                        i6 = i19;
                                        i7 = i18;
                                        i5 = i8;
                                        i19 = i6 + 1;
                                        i18 = i7;
                                        i8 = i5;
                                    }
                                    i2 = i8;
                                }
                                i2 = i8;
                            } else {
                                i2 = i8;
                                if (iVarArr != null) {
                                    float r3 = m963r(oVarArr2[i2].f2327d, oVarArr2[i2].f2324a, true);
                                    int i21 = this.f527d.f2220a[i2].f2330g;
                                    int i22 = iVarArr[i21].f2294a;
                                    int i23 = i21;
                                    while (i23 <= i22) {
                                        BBString.C0678i iVar2 = iVarArr[i23];
                                        float f19 = iVar2.f2300g;
                                        Rect rect3 = this.f526c;
                                        if (f19 < ((float) rect3.top) || iVar2.f2298e >= ((float) rect3.bottom)) {
                                            i3 = i23;
                                            i4 = i22;
                                        } else {
                                            BBString.C0685o[] oVarArr18 = this.f527d.f2220a;
                                            if (oVarArr18[i2].f2326c != 0) {
                                                this.f525b.setColor(oVarArr18[i2].f2326c);
                                                canvas.drawRect(iVar2.f2297d, iVar2.f2298e, iVar2.f2299f, iVar2.f2300g, this.f525b);
                                            }
                                            if (iVar2.f2296c > 0) {
                                                this.f525b.setColor(this.f527d.f2220a[i2].f2325b);
                                                i3 = i23;
                                                i4 = i22;
                                                canvas.drawText(this.f527d.f2231k, iVar2.f2295b, iVar2.f2296c, iVar2.f2297d, iVar2.f2300g + iVar2.f2301h + r3, this.f525b);
                                            } else {
                                                i3 = i23;
                                                i4 = i22;
                                            }
                                        }
                                        i23 = i3 + 1;
                                        i22 = i4;
                                    }
                                }
                            }
                            i = i2;
                            i8 = i + 1;
                            iVarArr2 = iVarArr;
                            z = false;
                        }
                        i = i2;
                        i8 = i + 1;
                        iVarArr2 = iVarArr;
                        z = false;
                    }
                    iVarArr = iVarArr2;
                    if (!this.f527d.f2220a[i8].m449e()) {
                    }
                    i = i2;
                    i8 = i + 1;
                    iVarArr2 = iVarArr;
                    z = false;
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        boolean z;
        if (!(this.f527d == null || this.f538o == null)) {
            int i = -1;
            BBString.C0678i[] iVarArr = null;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            while (true) {
                BBString pVar = this.f527d;
                if (i2 >= pVar.f2222b) {
                    break;
                }
                BBString.C0685o oVar = pVar.f2220a[i2];
                float f = oVar.f2332i;
                float f2 = this.f536m;
                if (f <= f2 && oVar.f2334k > f2) {
                    float f3 = oVar.f2333j;
                    float f4 = this.f537n;
                    if (f3 <= f4 && oVar.f2335l > f4 && !oVar.m446h()) {
                        if (oVar.m444j() == 129) {
                            if (oVar.m442l()) {
                                iVarArr = this.f527d.f2211R.f2307a[oVar.f2330g].f2313a;
                            }
                            if (oVar.m448f() && oVar.m445i() == 7424) {
                                i5 = i2;
                            }
                        } else if (oVar.m444j() == 130) {
                            if (oVar.m442l()) {
                                iVarArr = null;
                            }
                        } else if (oVar.f2331h > -1 || oVar.m452b()) {
                            if (oVar.f2331h == -2) {
                                BBString.C0670a[] aVarArr = this.f527d.f2212S;
                                int i6 = oVar.f2329f;
                                if (aVarArr[i6].f2249c) {
                                    i = oVar.f2328e;
                                    i4 = i6;
                                    break;
                                }
                            }
                            if (oVar.m444j() != 7) {
                                if (iVarArr == null) {
                                    break;
                                }
                                int i7 = oVar.f2330g;
                                int i8 = iVarArr[i7].f2294a;
                                while (true) {
                                    if (i7 > i8) {
                                        z = false;
                                        break;
                                    }
                                    BBString.C0678i iVar = iVarArr[i7];
                                    float f5 = iVar.f2297d;
                                    float f6 = this.f536m;
                                    if (f5 <= f6 && iVar.f2299f > f6) {
                                        float f7 = iVar.f2298e;
                                        float f8 = this.f537n;
                                        if (f7 <= f8 && iVar.f2300g > f8) {
                                            int i9 = oVar.f2331h;
                                            if (i9 == -2) {
                                                i4 = oVar.f2329f;
                                            } else {
                                                i3 = i9;
                                            }
                                            z = true;
                                        }
                                    }
                                    i7++;
                                }
                                if (z) {
                                    break;
                                }
                            } else {
                                i3 = oVar.f2331h;
                                i = oVar.f2328e;
                                if (oVar.m452b()) {
                                    i4 = oVar.f2329f;
                                }
                            }
                        } else if (oVar.m444j() == 7) {
                            i = oVar.f2328e;
                            break;
                        }
                        i2++;
                    }
                }
                if (oVar.m444j() == 129) {
                    i2 = oVar.f2328e;
                }
                i2++;
            }
            this.f538o.showBBOptionMenu(this, this.f527d, new C0143c(i3, i4, i, i5));
        }
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onMeasure(int i, int i2) {
        if (this.f527d == null) {
            setMeasuredDimension(0, 0);
            return;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) == 0) {
            size2 = ((WindowManager) this.f524a.getSystemService("window")).getDefaultDisplay().getWidth();
        }
        m970k(size2);
        if (mode == 0 || (Integer.MIN_VALUE == mode && size > this.f527d.f2217X)) {
            size = this.f527d.f2217X;
        }
        m976e();
        setMeasuredDimension(size2, size);
    }

    public void m965p(int i) {
        boolean z;
        BBString.C0685o[] oVarArr = this.f527d.f2220a;
        int i2 = oVarArr[i].f2328e + 1;
        int i3 = oVarArr[i2].f2328e;
        if (oVarArr[i2].m446h()) {
            m966o(i, true);
            z = true;
        } else {
            z = false;
        }
        for (int i4 = i2 + 1; i4 < i3; i4++) {
            BBString.C0685o oVar = this.f527d.f2220a[i4];
            if (129 == oVar.m444j() && 7424 == oVar.m445i() && oVar.m448f() && this.f527d.f2220a[oVar.f2328e + 1].m446h()) {
                m966o(i4, true);
                z = true;
            }
        }
        if (z) {
            IBBDisplayCallback bVar = this.f538o;
            if (bVar != null) {
                BBString pVar = this.f527d;
                bVar.mo133c(this, pVar, pVar.f2217X);
            }
            m964q(true);
        }
    }

    void m964q(boolean z) {
        if (z) {
            this.f534k.f547a = true;
        }
        removeCallbacks(this.f534k);
        if (z || this.f533j <= 0) {
            post(this.f534k);
        } else {
            postDelayed(this.f534k, 500);
        }
    }

    public float m963r(int i, int i2, boolean z) {
        float f = BBString.f2188u0;
        float f2 = (4194304 & i2) > 0 ? 0.86f * f : f;
        if (!z) {
            switch (i & 65535) {
                case 1:
                    this.f525b.setTextSize(f2 * 0.8f);
                    break;
                case 2:
                    this.f525b.setTextSize(f2);
                    break;
                case 3:
                    this.f525b.setTextSize(f2 * 1.2f);
                    break;
                case 4:
                    this.f525b.setTextSize(f2 * 1.4f);
                    break;
                case 5:
                    this.f525b.setTextSize(f2 * 1.8f);
                    break;
                case 6:
                    this.f525b.setTextSize(f2 * 2.4f);
                    break;
                case 7:
                    this.f525b.setTextSize(f2 * 3.6f);
                    break;
                default:
                    this.f525b.setTextSize(f2);
                    break;
            }
            f = (float) ((int) (this.f525b.getFontSpacing() + BBString.f2187t0 + 0.5f));
        }
        if ((3145728 & i2) > 0) {
            f2 *= 0.6f;
        }
        switch (65535 & i) {
            case 1:
                this.f525b.setTextSize(f2 * 0.8f);
                break;
            case 2:
                this.f525b.setTextSize(f2);
                break;
            case 3:
                this.f525b.setTextSize(f2 * 1.2f);
                break;
            case 4:
                this.f525b.setTextSize(f2 * 1.4f);
                break;
            case 5:
                this.f525b.setTextSize(f2 * 1.8f);
                break;
            case 6:
                this.f525b.setTextSize(f2 * 2.4f);
                break;
            case 7:
                this.f525b.setTextSize(f2 * 3.6f);
                break;
            default:
                this.f525b.setTextSize(f2);
                break;
        }
        float fontSpacing = this.f525b.getFontSpacing();
        float f3 = (1048576 & i2) > 0 ? (fontSpacing * 0.4f) - (fontSpacing * 0.25f) : 0.0f;
        if ((2097152 & i2) > 0) {
            f3 -= (0.4f * fontSpacing) + (fontSpacing * 0.25f);
        }
        boolean z2 = false;
        if (this.f535l) {
            this.f535l = false;
            this.f525b.setFakeBoldText(false);
            this.f525b.setTextSkewX(0.0f);
        }
        Util.C0425g.C0426a a = Util.C0425g.m645a(i);
        if ((65536 & i2) > 0) {
            if ((i2 & 131072) > 0) {
                this.f525b.setTypeface(a.f1643d);
                if (a.f1646g) {
                    this.f535l = true;
                    this.f525b.setFakeBoldText(true);
                }
                if (a.f1647h) {
                    this.f535l = true;
                    this.f525b.setTextSkewX(-0.25f);
                }
            } else {
                this.f525b.setTypeface(a.f1641b);
                if (a.f1644e) {
                    this.f535l = true;
                    this.f525b.setFakeBoldText(true);
                }
            }
        } else if ((i2 & 131072) > 0) {
            this.f525b.setTypeface(a.f1642c);
            if (a.f1645f) {
                this.f535l = true;
                this.f525b.setTextSkewX(-0.25f);
            }
        } else {
            this.f525b.setTypeface(a.f1640a);
        }
        this.f525b.setStrikeThruText((524288 & i2) > 0);
        Paint paint = this.f525b;
        if ((i2 & 262144) > 0) {
            z2 = true;
        }
        paint.setUnderlineText(z2);
        return z ? f3 : f;
    }

    float m962s(float f, int i, int i2) {
        float f2 = 0;
        float f3 = this.f527d.f2220a[i2].f2335l;
        BBString.C0678i[] iVarArr = null;
        while (i <= i2) {
            boolean e = this.f527d.f2220a[i].m449e();
            BBString.C0685o[] oVarArr = this.f527d.f2220a;
            oVarArr[i].f2324a |= 1073741824;
            int j = oVarArr[i].m444j();
            if (9 == j || 6 == j) {
                BBString.C0685o[] oVarArr2 = this.f527d.f2220a;
                BBString.C0685o oVar = oVarArr2[i];
                BBString.C0685o oVar2 = oVarArr2[i];
                f2 = oVar2.f2335l + f;
                oVar2.f2335l = f2;
                oVar.f2333j = f2;
            } else if (129 == j) {
                if (this.f527d.f2220a[i].m442l()) {
                    BBString pVar = this.f527d;
                    BBString.C0679j.C0680a[] aVarArr = pVar.f2211R.f2307a;
                    BBString.C0685o[] oVarArr3 = pVar.f2220a;
                    iVarArr = aVarArr[oVarArr3[i].f2330g] != null ? aVarArr[oVarArr3[i].f2330g].f2313a : null;
                }
                BBString.C0685o[] oVarArr4 = this.f527d.f2220a;
                BBString.C0685o oVar3 = oVarArr4[oVarArr4[i].f2328e];
                BBString.C0685o oVar4 = oVarArr4[i];
                f2 = oVar4.f2333j + f;
                oVar4.f2333j = f2;
                oVar3.f2333j = f2;
            } else if (130 == j) {
                if (this.f527d.f2220a[i].m442l()) {
                    iVarArr = null;
                }
                BBString.C0685o[] oVarArr5 = this.f527d.f2220a;
                BBString.C0685o oVar5 = oVarArr5[oVarArr5[i].f2328e];
                BBString.C0685o oVar6 = oVarArr5[i];
                f2 = oVar6.f2335l + f;
                oVar6.f2335l = f2;
                oVar5.f2335l = f2;
            } else {
                if (!e) {
                    BBString.C0685o[] oVarArr6 = this.f527d.f2220a;
                    oVarArr6[i].f2333j += f;
                    BBString.C0685o oVar7 = oVarArr6[i];
                    float f4 = oVar7.f2335l + f;
                    oVar7.f2335l = f4;
                    if (iVarArr != null && (3 == j || 5 == j || 8 == j)) {
                        int i3 = oVarArr6[i].f2330g;
                        int i4 = iVarArr[i3].f2294a;
                        while (i3 <= i4) {
                            iVarArr[i3].f2298e += f;
                            iVarArr[i3].f2300g += f;
                            i3++;
                        }
                    }
                    f3 = f4;
                }
                if (e) {
                    this.f527d.f2220a[i].m440n(e);
                }
                i++;
            }
            f3 = f2;
            if (e) {
            }
            i++;
        }
        return f3;
    }

    public void setBBString(BBString pVar) {
        if (this.f527d != null) {
            this.f533j = 0;
            this.f531h = null;
            this.f532i = null;
            this.f527d = null;
            BBOverlay bBOverlay = this.f528e;
            if (bBOverlay != null) {
                bBOverlay.f555b.m937k(false);
            }
        }
        this.f527d = pVar;
        if (pVar != null) {
            this.f533j = 0;
            int i = pVar.f2210Q;
            this.f531h = new Drawable[i];
            this.f532i = new boolean[i];
            this.f525b.setTextSize(BBString.f2188u0);
            requestLayout();
            BBOverlay bBOverlay2 = this.f528e;
            if (bBOverlay2 != null) {
                bBOverlay2.requestLayout();
            }
        }
        invalidate();
    }

    public void setCallback(IBBDisplayCallback bVar) {
        this.f538o = bVar;
    }

    public void setOverlay(View view) {
        if (view instanceof BBOverlay) {
            BBOverlay bBOverlay = (BBOverlay) view;
            this.f528e = bBOverlay;
            bBOverlay.f554a = this;
        }
    }

    public static class C0143c {
        int f543a;
        int f544b;
        int f545c;
        int f546d;

        public C0143c(int i, int i2, int i3, int i4) {
            this.f543a = i;
            this.f544b = i2;
            this.f545c = i3;
            this.f546d = i4;
        }

        public C0143c() {
            this(-1, -1, -1, -1);
        }
    }

    public BBDisplay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f525b = new Paint(1);
        this.f526c = new Rect();
        this.f530g = new float[48];
        this.f534k = new RunnableC0144d();
        this.f535l = false;
        m971j(context);
    }
}
