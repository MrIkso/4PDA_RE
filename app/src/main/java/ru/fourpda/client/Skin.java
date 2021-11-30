package ru.fourpda.client;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.lang.reflect.Field;

public class Skin {
    private String f1337a;
    private Resources f1338b;
    private Resources f1339c;
    private SparseIntArray f1340d;
    private SparseIntArray f1341e;
    private float f1342f;
    private boolean f1343g;
    private boolean f1344h;

    public static class C0353a {
        static int f1345A;
        static int f1346B;
        static int f1347C;
        static int f1348D;
        static int f1349E;
        static int f1350F;
        static int f1351G;
        static int f1352H;
        static int f1353I;
        static int f1354J;
        static int f1355K;
        static int f1356L;
        static int f1357M;
        static int f1358N;
        static int f1359O;
        static int f1360P;
        static int f1361Q;
        static int f1362R;
        static int f1363S;
        static int f1364T;
        static int f1365U;
        static int f1366V;
        static int f1367W;
        static int f1368X;
        static int f1369Y;
        static int f1370Z;
        static int f1371a;
        static int f1372a0;
        static int f1373b;
        static int f1374b0;
        static int f1375c;
        static int f1376c0;
        static int f1377d;
        static int f1378d0;
        static int f1379e;
        static int f1380e0;
        static int f1381f;
        static ColorStateList f1382f0;
        static int f1383g;
        static ColorStateList f1384g0;
        static int f1385h;
        static ColorStateList f1386h0;
        static int f1387i;
        static Drawable f1388i0;
        static int f1389j;
        static Drawable f1390j0;
        static int f1391k;
        static boolean f1392k0;
        static int f1393l;
        static boolean f1394l0;
        static int f1395m;
        static boolean f1396m0;
        static int f1397n;
        static String[] f1398n0 = {"main_text", "main_text", "main_text", "group_user", "group_admin", "group_banned", "main_text", "group_active_user", "group_friend", "group_moderator", "group_super_moderator", "group_mod_helper", "group_faqmakers", "group_honorable", "main_text", "group_developer", "group_router", "group_businessman", "group_spec_project", "group_mod_school", "group_curator", "main_text", "main_text", "main_text", "main_text", "main_text"};
        static int f1399o;
        static int f1400p;
        static int f1401q;
        static int f1402r;
        static int f1403s;
        static int f1404t;
        static int f1405u;
        static int f1406v;
        static int f1407w;
        static int f1408x;
        static int f1409y;
        static int f1410z;

        public static void m731a(Skin e1Var) {
            f1382f0 = e1Var.m737e(R.color.btn_text_color);
            f1384g0 = e1Var.m737e(R.color.link_color);
            f1386h0 = e1Var.m737e(R.color.label_text_csl);
            f1365U = e1Var.m738d(R.color.main_text);
            e1Var.m738d(R.color.alter_text);
            e1Var.m738d(R.color.start_btn_text);
            int d = e1Var.m738d(R.color.main_bg);
            f1363S = d;
            f1364T = d & 16777215;
            f1366V = e1Var.m738d(R.color.hidden);
            e1Var.m738d(R.color.deleted);
            f1367W = e1Var.m738d(R.color.brend);
            f1368X = e1Var.m738d(R.color.status_bar);
            f1369Y = e1Var.m738d(R.color.navigation_bar);
            f1388i0 = e1Var.m736f(R.color.cardlist_bg);
            f1390j0 = e1Var.m736f(R.color.qmslist_bg);
            e1Var.m738d(R.color.unread_text);
            f1370Z = e1Var.m738d(R.color.label_text);
            e1Var.m738d(R.color.border_line);
            f1371a = e1Var.m738d(R.color.bb_font);
            f1373b = e1Var.m738d(R.color.bb_link);
            f1375c = e1Var.m738d(R.color.bb_node_background);
            f1377d = e1Var.m738d(R.color.bb_list_item);
            f1379e = e1Var.m738d(R.color.bb_empty_image_background);
            f1381f = e1Var.m738d(R.color.bb_empty_image_edge);
            f1383g = e1Var.m738d(R.color.bb_empty_image_x);
            f1385h = e1Var.m738d(R.color.bb_selection_font);
            f1387i = e1Var.m738d(R.color.bb_selection_background);
            f1389j = e1Var.m738d(R.color.bb_link_hl_font);
            f1391k = e1Var.m738d(R.color.bb_link_hl_background);
            f1393l = e1Var.m738d(R.color.bb_background);
            f1395m = e1Var.m738d(R.color.bb_quote_head);
            f1397n = e1Var.m738d(R.color.bb_quote_body);
            f1399o = e1Var.m738d(R.color.bb_quote_border);
            f1400p = e1Var.m738d(R.color.bb_quote_indent);
            f1401q = e1Var.m738d(R.color.bb_spoiler_head);
            f1402r = e1Var.m738d(R.color.bb_spoiler_body);
            f1403s = e1Var.m738d(R.color.bb_spoiler_border);
            f1404t = e1Var.m738d(R.color.bb_spoiler_indent);
            f1405u = e1Var.m738d(R.color.bb_spoiler_indent_close);
            f1406v = e1Var.m738d(R.color.bb_code_head);
            f1407w = e1Var.m738d(R.color.bb_code_body);
            f1408x = e1Var.m738d(R.color.bb_code_border);
            f1409y = e1Var.m738d(R.color.bb_code_indent);
            f1410z = e1Var.m738d(R.color.bb_code_indent_close);
            f1345A = e1Var.m738d(R.color.bb_hide_head);
            f1346B = e1Var.m738d(R.color.bb_hide_body);
            f1347C = e1Var.m738d(R.color.bb_hide_border);
            f1348D = e1Var.m738d(R.color.bb_hide_indent);
            f1349E = e1Var.m738d(R.color.bb_ex_body);
            f1350F = e1Var.m738d(R.color.bb_ex_border);
            f1351G = e1Var.m738d(R.color.bb_ex_indent);
            f1352H = e1Var.m738d(R.color.bb_mod_body);
            f1353I = e1Var.m738d(R.color.bb_mod_border);
            f1354J = e1Var.m738d(R.color.bb_mod_indent);
            f1355K = e1Var.m738d(R.color.bb_cur_body);
            f1356L = e1Var.m738d(R.color.bb_cur_border);
            f1357M = e1Var.m738d(R.color.bb_cur_indent);
            f1358N = e1Var.m738d(R.color.bb_offtop);
            f1359O = e1Var.m738d(R.color.bb_table_border);
            f1360P = e1Var.m738d(R.color.bb_edit_tag);
            f1361Q = e1Var.m738d(R.color.bb_edit_tagsel);
            f1362R = e1Var.m738d(R.color.bb_edit_val);
            f1372a0 = e1Var.m738d(R.color.qms_typing_dots);
            f1374b0 = e1Var.m738d(R.color.circle_bg);
            f1376c0 = e1Var.m738d(R.color.refresh_arrow_1);
            f1378d0 = e1Var.m738d(R.color.refresh_arrow_2);
            f1380e0 = e1Var.m738d(R.color.refresh_arrow_3);
            BBDisplay.f517p = e1Var.m739c(R.drawable.playbtn);
            BBDisplay.f518q = new BitmapDrawable(e1Var.m739c(R.drawable.ic_snapback));
            Util.C0424f.m648a();
            Util.C0424f.m647b("group_user", e1Var.m738d(R.color.group_user));
            Util.C0424f.m647b("group_active_user", e1Var.m738d(R.color.group_active_user));
            Util.C0424f.m647b("group_friend", e1Var.m738d(R.color.group_friend));
            Util.C0424f.m647b("group_honorable", e1Var.m738d(R.color.group_honorable));
            Util.C0424f.m647b("group_faqmakers", e1Var.m738d(R.color.group_faqmakers));
            Util.C0424f.m647b("group_spec_project", e1Var.m738d(R.color.group_spec_project));
            Util.C0424f.m647b("group_businessman", e1Var.m738d(R.color.group_businessman));
            Util.C0424f.m647b("group_curator", e1Var.m738d(R.color.group_curator));
            Util.C0424f.m647b("group_mod_helper", e1Var.m738d(R.color.group_mod_helper));
            Util.C0424f.m647b("group_moderator", e1Var.m738d(R.color.group_moderator));
            Util.C0424f.m647b("group_router", e1Var.m738d(R.color.group_router));
            Util.C0424f.m647b("group_super_moderator", e1Var.m738d(R.color.group_super_moderator));
            Util.C0424f.m647b("group_admin", e1Var.m738d(R.color.group_admin));
            Util.C0424f.m647b("group_banned", e1Var.m738d(R.color.group_banned));
            Util.C0424f.m647b("group_developer", e1Var.m738d(R.color.group_developer));
            Util.C0424f.m647b("group_mod_school", e1Var.m738d(R.color.group_mod_school));
            Util.C0424f.m647b("main_text", f1365U);
            Util.C0424f.m647b("border_line", e1Var.m738d(R.color.border_line));
            Util.C0424f.m647b("label_text", e1Var.m738d(R.color.label_text));
            Util.C0424f.m647b("aliceblue", e1Var.m738d(R.color.aliceblue));
            Util.C0424f.m647b("antiquewhite", e1Var.m738d(R.color.antiquewhite));
            Util.C0424f.m647b("aqua", e1Var.m738d(R.color.aqua));
            Util.C0424f.m647b("aquamarine", e1Var.m738d(R.color.aquamarine));
            Util.C0424f.m647b("azure", e1Var.m738d(R.color.azure));
            Util.C0424f.m647b("beige", e1Var.m738d(R.color.beige));
            Util.C0424f.m647b("bisque", e1Var.m738d(R.color.bisque));
            Util.C0424f.m647b("black", e1Var.m738d(R.color.black));
            Util.C0424f.m647b("blanchedalmond", e1Var.m738d(R.color.blanchedalmond));
            Util.C0424f.m647b("blue", e1Var.m738d(R.color.blue));
            Util.C0424f.m647b("blueviolet", e1Var.m738d(R.color.blueviolet));
            Util.C0424f.m647b("brown", e1Var.m738d(R.color.brown));
            Util.C0424f.m647b("burlywood", e1Var.m738d(R.color.burlywood));
            Util.C0424f.m647b("cadetblue", e1Var.m738d(R.color.cadetblue));
            Util.C0424f.m647b("chartreuse", e1Var.m738d(R.color.chartreuse));
            Util.C0424f.m647b("chocolate", e1Var.m738d(R.color.chocolate));
            Util.C0424f.m647b("coral", e1Var.m738d(R.color.coral));
            Util.C0424f.m647b("cornflowerblue", e1Var.m738d(R.color.cornflowerblue));
            Util.C0424f.m647b("cornsilk", e1Var.m738d(R.color.cornsilk));
            Util.C0424f.m647b("crimson", e1Var.m738d(R.color.crimson));
            Util.C0424f.m647b("cyan", e1Var.m738d(R.color.cyan));
            Util.C0424f.m647b("darkblue", e1Var.m738d(R.color.darkblue));
            Util.C0424f.m647b("darkcyan", e1Var.m738d(R.color.darkcyan));
            Util.C0424f.m647b("darkgoldenrod", e1Var.m738d(R.color.darkgoldenrod));
            Util.C0424f.m647b("darkgray", e1Var.m738d(R.color.darkgray));
            Util.C0424f.m647b("darkgreen", e1Var.m738d(R.color.darkgreen));
            Util.C0424f.m647b("darkkhaki", e1Var.m738d(R.color.darkkhaki));
            Util.C0424f.m647b("darkmagenta", e1Var.m738d(R.color.darkmagenta));
            Util.C0424f.m647b("darkolivegreen", e1Var.m738d(R.color.darkolivegreen));
            Util.C0424f.m647b("darkorange", e1Var.m738d(R.color.darkorange));
            Util.C0424f.m647b("darkorchid", e1Var.m738d(R.color.darkorchid));
            Util.C0424f.m647b("darkred", e1Var.m738d(R.color.darkred));
            Util.C0424f.m647b("darksalmon", e1Var.m738d(R.color.darksalmon));
            Util.C0424f.m647b("darkseagreen", e1Var.m738d(R.color.darkseagreen));
            Util.C0424f.m647b("darkslateblue", e1Var.m738d(R.color.darkslateblue));
            Util.C0424f.m647b("darkslategray", e1Var.m738d(R.color.darkslategray));
            Util.C0424f.m647b("darkturquoise", e1Var.m738d(R.color.darkturquoise));
            Util.C0424f.m647b("darkviolet", e1Var.m738d(R.color.darkviolet));
            Util.C0424f.m647b("deeppink", e1Var.m738d(R.color.deeppink));
            Util.C0424f.m647b("deepskyblue", e1Var.m738d(R.color.deepskyblue));
            Util.C0424f.m647b("dimgray", e1Var.m738d(R.color.dimgray));
            Util.C0424f.m647b("dodgerblue", e1Var.m738d(R.color.dodgerblue));
            Util.C0424f.m647b("firebrick", e1Var.m738d(R.color.firebrick));
            Util.C0424f.m647b("floralwhite", e1Var.m738d(R.color.floralwhite));
            Util.C0424f.m647b("forestgreen", e1Var.m738d(R.color.forestgreen));
            Util.C0424f.m647b("fuchsia", e1Var.m738d(R.color.fuchsia));
            Util.C0424f.m647b("gainsboro", e1Var.m738d(R.color.gainsboro));
            Util.C0424f.m647b("ghostwhite", e1Var.m738d(R.color.ghostwhite));
            Util.C0424f.m647b("gold", e1Var.m738d(R.color.gold));
            Util.C0424f.m647b("goldenrod", e1Var.m738d(R.color.goldenrod));
            Util.C0424f.m647b("gray", e1Var.m738d(R.color.gray));
            Util.C0424f.m647b("green", e1Var.m738d(R.color.green));
            Util.C0424f.m647b("greenyellow", e1Var.m738d(R.color.greenyellow));
            Util.C0424f.m647b("honeydew", e1Var.m738d(R.color.honeydew));
            Util.C0424f.m647b("hotpink", e1Var.m738d(R.color.hotpink));
            Util.C0424f.m647b("indianred", e1Var.m738d(R.color.indianred));
            Util.C0424f.m647b("indigo", e1Var.m738d(R.color.indigo));
            Util.C0424f.m647b("ivory", e1Var.m738d(R.color.ivory));
            Util.C0424f.m647b("khaki", e1Var.m738d(R.color.khaki));
            Util.C0424f.m647b("lavender", e1Var.m738d(R.color.lavender));
            Util.C0424f.m647b("lavenderblush", e1Var.m738d(R.color.lavenderblush));
            Util.C0424f.m647b("lawngreen", e1Var.m738d(R.color.lawngreen));
            Util.C0424f.m647b("lemonchiffon", e1Var.m738d(R.color.lemonchiffon));
            Util.C0424f.m647b("lightblue", e1Var.m738d(R.color.lightblue));
            Util.C0424f.m647b("lightcoral", e1Var.m738d(R.color.lightcoral));
            Util.C0424f.m647b("lightcyan", e1Var.m738d(R.color.lightcyan));
            Util.C0424f.m647b("lightgoldenrodyellow", e1Var.m738d(R.color.lightgoldenrodyellow));
            Util.C0424f.m647b("lightgray", e1Var.m738d(R.color.lightgray));
            Util.C0424f.m647b("lightgreen", e1Var.m738d(R.color.lightgreen));
            Util.C0424f.m647b("lightpink", e1Var.m738d(R.color.lightpink));
            Util.C0424f.m647b("lightsalmon", e1Var.m738d(R.color.lightsalmon));
            Util.C0424f.m647b("lightseagreen", e1Var.m738d(R.color.lightseagreen));
            Util.C0424f.m647b("lightskyblue", e1Var.m738d(R.color.lightskyblue));
            Util.C0424f.m647b("lightslategray", e1Var.m738d(R.color.lightslategray));
            Util.C0424f.m647b("lightsteelblue", e1Var.m738d(R.color.lightsteelblue));
            Util.C0424f.m647b("lightyellow", e1Var.m738d(R.color.lightyellow));
            Util.C0424f.m647b("lime", e1Var.m738d(R.color.lime));
            Util.C0424f.m647b("limegreen", e1Var.m738d(R.color.limegreen));
            Util.C0424f.m647b("linen", e1Var.m738d(R.color.linen));
            Util.C0424f.m647b("magenta", e1Var.m738d(R.color.magenta));
            Util.C0424f.m647b("maroon", e1Var.m738d(R.color.maroon));
            Util.C0424f.m647b("mediumaquamarine", e1Var.m738d(R.color.mediumaquamarine));
            Util.C0424f.m647b("mediumblue", e1Var.m738d(R.color.mediumblue));
            Util.C0424f.m647b("mediumorchid", e1Var.m738d(R.color.mediumorchid));
            Util.C0424f.m647b("mediumpurple", e1Var.m738d(R.color.mediumpurple));
            Util.C0424f.m647b("mediumseagreen", e1Var.m738d(R.color.mediumseagreen));
            Util.C0424f.m647b("mediumslateblue", e1Var.m738d(R.color.mediumslateblue));
            Util.C0424f.m647b("mediumspringgreen", e1Var.m738d(R.color.mediumspringgreen));
            Util.C0424f.m647b("mediumturquoise", e1Var.m738d(R.color.mediumturquoise));
            Util.C0424f.m647b("mediumvioletred", e1Var.m738d(R.color.mediumvioletred));
            Util.C0424f.m647b("midnightblue", e1Var.m738d(R.color.midnightblue));
            Util.C0424f.m647b("mintcream", e1Var.m738d(R.color.mintcream));
            Util.C0424f.m647b("mistyrose", e1Var.m738d(R.color.mistyrose));
            Util.C0424f.m647b("moccasin", e1Var.m738d(R.color.moccasin));
            Util.C0424f.m647b("navajowhite", e1Var.m738d(R.color.navajowhite));
            Util.C0424f.m647b("navy", e1Var.m738d(R.color.navy));
            Util.C0424f.m647b("oldlace", e1Var.m738d(R.color.oldlace));
            Util.C0424f.m647b("olive", e1Var.m738d(R.color.olive));
            Util.C0424f.m647b("olivedrab", e1Var.m738d(R.color.olivedrab));
            Util.C0424f.m647b("orange", e1Var.m738d(R.color.orange));
            Util.C0424f.m647b("orangered", e1Var.m738d(R.color.orangered));
            Util.C0424f.m647b("orchid", e1Var.m738d(R.color.orchid));
            Util.C0424f.m647b("palegoldenrod", e1Var.m738d(R.color.palegoldenrod));
            Util.C0424f.m647b("palegreen", e1Var.m738d(R.color.palegreen));
            Util.C0424f.m647b("paleturquoise", e1Var.m738d(R.color.paleturquoise));
            Util.C0424f.m647b("palevioletred", e1Var.m738d(R.color.palevioletred));
            Util.C0424f.m647b("papayawhip", e1Var.m738d(R.color.papayawhip));
            Util.C0424f.m647b("peachpuff", e1Var.m738d(R.color.peachpuff));
            Util.C0424f.m647b("peru", e1Var.m738d(R.color.peru));
            Util.C0424f.m647b("pink", e1Var.m738d(R.color.pink));
            Util.C0424f.m647b("plum", e1Var.m738d(R.color.plum));
            Util.C0424f.m647b("powderblue", e1Var.m738d(R.color.powderblue));
            Util.C0424f.m647b("purple", e1Var.m738d(R.color.purple));
            Util.C0424f.m647b("red", e1Var.m738d(R.color.red));
            Util.C0424f.m647b("rosybrown", e1Var.m738d(R.color.rosybrown));
            Util.C0424f.m647b("royalblue", e1Var.m738d(R.color.royalblue));
            Util.C0424f.m647b("saddlebrown", e1Var.m738d(R.color.saddlebrown));
            Util.C0424f.m647b("salmon", e1Var.m738d(R.color.salmon));
            Util.C0424f.m647b("sandybrown", e1Var.m738d(R.color.sandybrown));
            Util.C0424f.m647b("seagreen", e1Var.m738d(R.color.seagreen));
            Util.C0424f.m647b("seashell", e1Var.m738d(R.color.seashell));
            Util.C0424f.m647b("sienna", e1Var.m738d(R.color.sienna));
            Util.C0424f.m647b("silver", e1Var.m738d(R.color.silver));
            Util.C0424f.m647b("skyblue", e1Var.m738d(R.color.skyblue));
            Util.C0424f.m647b("slateblue", e1Var.m738d(R.color.slateblue));
            Util.C0424f.m647b("slategray", e1Var.m738d(R.color.slategray));
            Util.C0424f.m647b("snow", e1Var.m738d(R.color.snow));
            Util.C0424f.m647b("springgreen", e1Var.m738d(R.color.springgreen));
            Util.C0424f.m647b("steelblue", e1Var.m738d(R.color.steelblue));
            Util.C0424f.m647b("tan", e1Var.m738d(R.color.tan));
            Util.C0424f.m647b("teal", e1Var.m738d(R.color.teal));
            Util.C0424f.m647b("thistle", e1Var.m738d(R.color.thistle));
            Util.C0424f.m647b("tomato", e1Var.m738d(R.color.tomato));
            Util.C0424f.m647b("turquoise", e1Var.m738d(R.color.turquoise));
            Util.C0424f.m647b("violet", e1Var.m738d(R.color.violet));
            Util.C0424f.m647b("wheat", e1Var.m738d(R.color.wheat));
            Util.C0424f.m647b("white", e1Var.m738d(R.color.white));
            Util.C0424f.m647b("whitesmoke", e1Var.m738d(R.color.whitesmoke));
            Util.C0424f.m647b("yellow", e1Var.m738d(R.color.yellow));
            Util.C0424f.m647b("yellowgreen", e1Var.m738d(R.color.yellowgreen));
            int d2 = e1Var.m738d(R.color.skin_flags);
            int i = d2 & 15;
            boolean z = false;
            f1392k0 = i == 1 || (i == 0 && !Skin.m734h(f1365U));
            int i2 = (d2 >> 4) & 15;
            f1394l0 = i2 == 1 || (i2 == 0 && Skin.m734h(f1368X));
            int i3 = (d2 >> 8) & 15;
            if (i3 == 1 || (i3 == 0 && Skin.m734h(f1369Y))) {
                z = true;
            }
            f1396m0 = z;
        }
    }

    public Skin(Context context) {
        String str;
        String str2;
        this.f1337a = null;
        this.f1338b = null;
        this.f1339c = null;
        Resources resources = context.getResources();
        this.f1338b = resources;
        this.f1342f = resources.getDisplayMetrics().density;
        boolean z = (this.f1338b.getConfiguration().uiMode & 48) == 32;
        this.f1343g = z;
        int i = Prefs.f1184v;
        this.f1344h = 3 == i || (i == 0 && z);
        try {
            if (!TextUtils.isEmpty(Prefs.f1183u)) {
                PackageManager packageManager = context.getPackageManager();
                this.f1337a = packageManager.getApplicationInfo(Prefs.f1183u, 0).loadLabel(packageManager).toString();
                this.f1339c = packageManager.getResourcesForApplication(Prefs.f1183u);
           //     ACRA.getErrorReporter().putCustomData("skin", Prefs.f1183u);
            }
        } catch (Exception unused) {
            Prefs.f1183u = null;
            this.f1337a = null;
            this.f1339c = null;
        }
        Resources resources2 = this.f1339c;
        if (!(resources2 == null || Prefs.f1184v == 1 || resources2.getIdentifier("zn_main_text", "color", Prefs.f1183u) != 0)) {
            Prefs.f1184v = 1;
            Prefs.saveInt(context, "nightMode", 1);
            this.f1344h = false;
        }
        if (this.f1339c != null || this.f1344h) {
            this.f1340d = new SparseIntArray(300);
            this.f1341e = new SparseIntArray(300);
            Field[] fields = R.color.class.getFields();
            int length = fields.length;
            int i2 = 0;
            while (true) {
                str = "zn_";
                if (i2 >= length) {
                    break;
                }
                Field field = fields[i2];
                try {
                    String name = field.getName();
                    StringBuilder sb = new StringBuilder();
                    if (!this.f1344h) {
                        str = "";
                    }
                    sb.append(str);
                    sb.append(name);
                    String sb2 = sb.toString();
                    int intValue = ((Integer) field.get(name)).intValue();
                    Resources resources3 = this.f1339c;
                    int i3 = resources3 != null ? -resources3.getIdentifier(sb2, "color", Prefs.f1183u) : 0;
                    if (i3 == 0 && this.f1344h) {
                        i3 = this.f1338b.getIdentifier(sb2, "color", "ru.fourpda.client");
                    }
                    if (i3 != 0) {
                        this.f1340d.put(intValue, i3);
                    }
                } catch (Exception unused2) {
                }
                i2++;
            }
            Field[] fields2 = R.drawable.class.getFields();
            for (Field field2 : fields2) {
                try {
                    String name2 = field2.getName();
                    StringBuilder sb3 = new StringBuilder();
                    if (this.f1344h) {
                        str2 = str;
                    } else {
                        str2 = "";
                    }
                    sb3.append(str2);
                    sb3.append(name2);
                    String sb4 = sb3.toString();
                    int intValue2 = (Integer) field2.get(name2);
                    if (!(R.drawable.ic_launcher == intValue2 || R.drawable.overview == intValue2)) {
                        Resources resources4 = this.f1339c;
                        int i4 = resources4 != null ? -resources4.getIdentifier(sb4, "drawable", Prefs.f1183u) : 0;
                        if (i4 == 0 && this.f1344h) {
                            i4 = this.f1338b.getIdentifier(sb4, "drawable", "ru.fourpda.client");
                        }
                        if (i4 != 0) {
                            this.f1341e.put(intValue2, i4);
                        }
                    }
                } catch (Exception unused3) {
                }
            }
        }
    }

    private int m740b(String str) {
        float parseFloat;
        float f;
        if (str.endsWith("dip")) {
            parseFloat = Float.parseFloat(str.substring(0, str.length() - 3));
            f = this.f1342f;
        } else if (str.endsWith("dp")) {
            parseFloat = Float.parseFloat(str.substring(0, str.length() - 2));
            f = this.f1342f;
        } else {
            if (str.endsWith("px")) {
                return (int) Float.parseFloat(str.substring(0, str.length() - 2));
            }
            return 0;
        }
        return (int) (parseFloat * f);
    }

    public static boolean m734h(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        return Math.max(i2, Math.max(i3, i4)) > 192 || ((i2 + i3) + i4) / 3 > 128;
    }

    public Bitmap m739c(int i) {
        int i2;
        SparseIntArray sparseIntArray = this.f1341e;
        if (sparseIntArray == null || (i2 = sparseIntArray.get(i)) == 0) {
            return BitmapFactory.decodeResource(this.f1338b, i);
        }
        return BitmapFactory.decodeResource(i2 > 0 ? this.f1338b : this.f1339c, Math.abs(i2));
    }

    public int m738d(int i) {
        int i2;
        Resources resources;
        SparseIntArray sparseIntArray = this.f1340d;
        if (sparseIntArray == null || (i2 = sparseIntArray.get(i)) == 0) {
            return this.f1338b.getColor(i);
        }
        if (i2 > 0) {
            resources = this.f1338b;
        } else {
            resources = this.f1339c;
            i2 = -i2;
        }
        return resources.getColor(i2);
    }

    ColorStateList m737e(int i) {
        int i2;
        Resources resources;
        SparseIntArray sparseIntArray = this.f1340d;
        if (sparseIntArray == null || (i2 = sparseIntArray.get(i)) == 0) {
            return this.f1338b.getColorStateList(i);
        }
        if (i2 > 0) {
            resources = this.f1338b;
        } else {
            resources = this.f1339c;
            i2 = -i2;
        }
        return resources.getColorStateList(i2);
    }

    public Drawable m736f(int i) {
        int i2;
        Resources resources;
        SparseIntArray sparseIntArray;
        SparseIntArray sparseIntArray2 = this.f1341e;
        if ((sparseIntArray2 == null || (i2 = sparseIntArray2.get(i)) == 0) && ((sparseIntArray = this.f1340d) == null || (i2 = sparseIntArray.get(i)) == 0)) {
            return this.f1338b.getDrawable(i);
        }
        if (i2 > 0) {
            resources = this.f1338b;
        } else {
            resources = this.f1339c;
            i2 = -i2;
        }
        return resources.getDrawable(i2);
    }

    public String m735g() {
        return this.f1337a;
    }

    public boolean m733i(Configuration configuration) {
        if (((configuration.uiMode & 48) == 32) != this.f1343g) {
            return true;
        }
        return false;
    }

    public View m732j(String str, Context context, AttributeSet attributeSet) {
        View view;
        Resources resources;
        Resources resources2;
        Resources resources3;
        Resources resources4;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Resources resources5 = null;
        Resources resources6 = null;
        Resources resources7 = null;
        Resources resources8 = null;
        int i;
        Resources resources9;
        Resources resources10;
        Resources resources11;
        int i2 = 0;
        int i3;
        int i4;
        if (this.f1339c == null && !this.f1344h) {
            return null;
        }
        if (str.equals("Button")) {
            view = new Button(context, attributeSet);
        } else if (str.equals("EditText")) {
            view = new EditText(context, attributeSet);
        } else if (str.equals("FrameLayout")) {
            view = new FrameLayout(context, attributeSet);
        } else if (str.equals("HorizontalScrollView")) {
            view = new HorizontalScrollView(context, attributeSet);
        } else if (str.equals("ImageView")) {
            view = new ImageView(context, attributeSet);
        } else if (str.equals("LinearLayout")) {
            view = new LinearLayout(context, attributeSet);
        } else if (str.equals("RadioButton")) {
            view = new RadioButton(context, attributeSet);
        } else if (str.equals("RadioGroup")) {
            view = new RadioGroup(context, attributeSet);
        } else if (str.equals("RelativeLayout")) {
            view = new RelativeLayout(context, attributeSet);
        } else if (str.equals("ScrollView")) {
            view = new ScrollView(context, attributeSet);
        } else if (str.equals("TextView")) {
            view = new TextView(context, attributeSet);
        } else if (str.equals("ToggleButton")) {
            view = new ToggleButton(context, attributeSet);
        } else if (str.equals("View")) {
            view = new View(context, attributeSet);
        } else if (str.equals("ViewStub")) {
            view = new ViewStub(context, attributeSet);
        } else if (str.equals(ArticleLayout.class.getName())) {
            view = new ArticleLayout(context, attributeSet);
        } else if (str.equals(BBDisplay.class.getName())) {
            view = new BBDisplay(context, attributeSet);
        } else if (str.equals(BBOverlay.class.getName())) {
            view = new BBOverlay(context, attributeSet);
        } else if (str.equals(MainLayout.class.getName())) {
            view = new MainLayout(context, attributeSet);
        } else if (str.equals(MainLayout.TopLayout.class.getName())) {
            view = new MainLayout.TopLayout(context, attributeSet);
        } else if (str.equals(MainLayout.DrawerLayout.class.getName())) {
            view = new MainLayout.DrawerLayout(context, attributeSet);
        } else if (str.equals(Widgets$AvatarView.class.getName())) {
            view = new Widgets$AvatarView(context, attributeSet);
        } else if (str.equals(Widgets$CheckboxView.class.getName())) {
            view = new Widgets$CheckboxView(context, attributeSet);
        } else if (str.equals(Widgets$CheckboxTextView.class.getName())) {
            view = new Widgets$CheckboxTextView(context, attributeSet);
        } else if (str.equals(Widgets$CircleImageView.class.getName())) {
            view = new Widgets$CircleImageView(context, attributeSet);
        } else if (str.equals(Widgets$MemberView.class.getName())) {
            view = new Widgets$MemberView(context, attributeSet);
        } else if (str.equals(Widgets$ScrollingEditText.class.getName())) {
            view = new Widgets$ScrollingEditText(context, attributeSet);
        } else if (str.equals(Widgets$StartButtonsLayout.class.getName())) {
            view = new Widgets$StartButtonsLayout(context, attributeSet);
        } else if (str.equals(Widgets$WrappingLinearLayout.class.getName())) {
            view = new Widgets$WrappingLinearLayout(context, attributeSet);
        } else {
            Log.e("createView", str);
            view = null;
        }
        if (view != null) {
            int i5 = 0;
            int i6 = 0;
            boolean z = false;
            int i7 = 0;
            boolean z2 = false;
            int i8 = 0;
            boolean z3 = false;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            boolean z4 = false;
            boolean z5 = false;
            int i13 = 0;
            boolean z6 = false;
            int i14 = 0;
            while (i5 < attributeSet.getAttributeCount()) {
                int attributeNameResource = attributeSet.getAttributeNameResource(i5);
                z4 = true;
                if (16842964 == attributeNameResource) {
                    String attributeValue = attributeSet.getAttributeValue(i5);
                    i3 = i12;
                    if ('@' == attributeValue.charAt(0)) {
                        i7 = Integer.parseInt(attributeValue.substring(1));
                        z = true;
                    }
                } else {
                    i3 = i12;
                    if (16842904 == attributeNameResource) {
                        String attributeValue2 = attributeSet.getAttributeValue(i5);
                        i4 = i11;
                        if ('@' == attributeValue2.charAt(0)) {
                            i8 = Integer.parseInt(attributeValue2.substring(1));
                            z2 = true;
                        }
                    } else {
                        i4 = i11;
                        if (16843119 == attributeNameResource) {
                            String attributeValue3 = attributeSet.getAttributeValue(i5);
                            if ('@' == attributeValue3.charAt(0)) {
                                i9 = Integer.parseInt(attributeValue3.substring(1));
                                z3 = true;
                            }
                        } else if (16843117 == attributeNameResource) {
                            String attributeValue4 = attributeSet.getAttributeValue(i5);
                            if ('@' == attributeValue4.charAt(0)) {
                                i10 = Integer.parseInt(attributeValue4.substring(1));
                                z3 = true;
                            }
                        } else if (16843120 == attributeNameResource) {
                            String attributeValue5 = attributeSet.getAttributeValue(i5);
                            if ('@' == attributeValue5.charAt(0)) {
                                i11 = Integer.parseInt(attributeValue5.substring(1));
                                z3 = true;
                            } else {
                                i11 = i4;
                            }
                        } else if (16843118 == attributeNameResource) {
                            String attributeValue6 = attributeSet.getAttributeValue(i5);
                            if ('@' == attributeValue6.charAt(0)) {
                                i12 = Integer.parseInt(attributeValue6.substring(1));
                                z3 = true;
                            } else {
                                i12 = i3;
                            }
                            i2 = i6;
                            z4 = z4;
                            i11 = i4;
                        } else if (16843121 == attributeNameResource) {
                            i14 = m740b(attributeSet.getAttributeValue(i5));
                            z6 = i14 != 0;
                        } else {
                            if (16843033 == attributeNameResource) {
                                String attributeValue7 = attributeSet.getAttributeValue(i5);
                                if ('@' == attributeValue7.charAt(0)) {
                                    i2 = Integer.parseInt(attributeValue7.substring(1));
                                    i12 = i3;
                                    i11 = i4;
                                    i5++;
                                    i6 = i2;
                                }
                            } else if (16843126 == attributeNameResource) {
                                String attributeValue8 = attributeSet.getAttributeValue(i5);
                                if ('@' == attributeValue8.charAt(0)) {
                                    i13 = Integer.parseInt(attributeValue8.substring(1));
                                    i2 = i6;
                                    z4 = z4;
                                    i12 = i3;
                                    i11 = i4;
                                    z5 = true;
                                    i5++;
                                    i6 = i2;
                                }
                            }
                            i2 = i6;
                            z4 = z4;
                            i12 = i3;
                            i11 = i4;
                            i5++;
                            i6 = i2;
                        }
                        i5++;
                        i6 = i2;
                    }
                    i2 = i6;
                    z4 = z4;
                    i12 = i3;
                    i11 = i4;
                    i5++;
                    i6 = i2;
                }
                i2 = i6;
                z4 = z4;
                i12 = i3;
                i5++;
                i6 = i2;
            }
            if (z) {
                int i15 = this.f1340d.get(i7);
                if (i15 != 0) {
                    if (i15 > 0) {
                        resources11 = this.f1338b;
                    } else {
                        resources11 = this.f1339c;
                        i15 = -i15;
                    }
                    view.setBackgroundColor(resources11.getColor(i15));
                } else {
                    int i16 = this.f1341e.get(i7);
                    if (i16 != 0) {
                        if (i16 > 0) {
                            resources10 = this.f1338b;
                        } else {
                            resources10 = this.f1339c;
                            i16 = -i16;
                        }
                        view.setBackgroundDrawable(resources10.getDrawable(i16));
                    }
                }
            }
            if (z2 && (view instanceof TextView) && (i = this.f1340d.get(i8)) != 0) {
                TextView textView = (TextView) view;
                if (i > 0) {
                    resources9 = this.f1338b;
                } else {
                    resources9 = this.f1339c;
                    i = -i;
                }
                textView.setTextColor(resources9.getColorStateList(i));
            }
            if (z3 && (view instanceof TextView)) {
                int i17 = this.f1341e.get(i9);
                int i18 = this.f1341e.get(i10);
                int i19 = this.f1341e.get(i11);
                int i20 = this.f1341e.get(i12);
                if (!(i17 == 0 && i18 == 0 && i19 == 0 && i20 == 0)) {
                    TextView textView2 = (TextView) view;
                    if (i17 > 0) {
                        resources8 = this.f1338b;
                    } else if (i17 < 0) {
                        resources8 = this.f1339c;
                        i17 = -i17;
                    } else {
                        drawable = null;
                        if (i18 <= 0) {
                            resources7 = this.f1338b;
                        } else if (i18 < 0) {
                            resources7 = this.f1339c;
                            i18 = -i18;
                        } else {
                            drawable2 = null;
                            if (i19 > 0) {
                                resources6 = this.f1338b;
                            } else if (i19 < 0) {
                                resources6 = this.f1339c;
                                i19 = -i19;
                            } else {
                                drawable3 = null;
                                if (i20 <= 0) {
                                    resources5 = this.f1338b;
                                } else if (i20 < 0) {
                                    resources5 = this.f1339c;
                                    i20 = -i20;
                                } else {
                                    drawable4 = null;
                                    textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                                }
                                drawable4 = resources5.getDrawable(i20);
                                textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                            }
                            drawable3 = resources6.getDrawable(i19);
                            if (i20 <= 0) {
                            }
                            drawable4 = resources5.getDrawable(i20);
                            textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                        }
                        drawable2 = resources7.getDrawable(i18);
                        if (i19 > 0) {
                        }
                        drawable3 = resources6.getDrawable(i19);
                        if (i20 <= 0) {
                        }
                        drawable4 = resources5.getDrawable(i20);
                        textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                    }
                    drawable = resources8.getDrawable(i17);
                    if (i18 <= 0) {
                    }
                    drawable2 = resources7.getDrawable(i18);
                    if (i19 > 0) {
                    }
                    drawable3 = resources6.getDrawable(i19);
                    if (i20 <= 0) {
                    }
                    drawable4 = resources5.getDrawable(i20);
                    textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                }
            }
            if (z6 && (view instanceof TextView)) {
                ((TextView) view).setCompoundDrawablePadding(i14);
            }
            if (z4 && (view instanceof ImageView)) {
                int i21 = this.f1341e.get(i6);
                if (i21 != 0) {
                    ImageView imageView = (ImageView) view;
                    if (i21 > 0) {
                        resources4 = this.f1338b;
                    } else {
                        resources4 = this.f1339c;
                        i21 = -i21;
                    }
                    imageView.setImageDrawable(resources4.getDrawable(i21));
                } else {
                    int i22 = this.f1340d.get(i6);
                    if (i22 != 0) {
                        ImageView imageView2 = (ImageView) view;
                        if (i22 > 0) {
                            resources3 = this.f1338b;
                        } else {
                            resources3 = this.f1339c;
                            i22 = -i22;
                        }
                        imageView2.setImageDrawable(resources3.getDrawable(i22));
                    }
                }
            }
            if (z5 && (view instanceof AutoCompleteTextView)) {
                int i23 = this.f1341e.get(i13);
                if (i23 != 0) {
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view;
                    if (i23 > 0) {
                        resources2 = this.f1338b;
                    } else {
                        resources2 = this.f1339c;
                        i23 = -i23;
                    }
                    autoCompleteTextView.setDropDownBackgroundDrawable(resources2.getDrawable(i23));
                } else {
                    int i24 = this.f1340d.get(i13);
                    if (i24 != 0) {
                        AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) view;
                        if (i24 > 0) {
                            resources = this.f1338b;
                        } else {
                            resources = this.f1339c;
                            i24 = -i24;
                        }
                        autoCompleteTextView2.setDropDownBackgroundDrawable(resources.getDrawable(i24));
                    }
                }
            }
        }
        return view;
    }
}
