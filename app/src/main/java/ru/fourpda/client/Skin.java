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
    private float density;
    private boolean f1343g;
    private boolean f1344h;

    public static class SkinColorModel {
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
        static int mainBgColor;
        static int f1364T;
        static int mainTextColor;
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
        static ColorStateList btnTextColor;
        static int f1383g;
        static ColorStateList linkColor;
        static int f1385h;
        static ColorStateList labelTextCsl;
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

        public static void initColors(Skin skin) {
            btnTextColor = skin.getSkinColorStateList(R.color.btn_text_color);
            linkColor = skin.getSkinColorStateList(R.color.link_color);
            labelTextCsl = skin.getSkinColorStateList(R.color.label_text_csl);
            mainTextColor = skin.getSkinColor(R.color.main_text);
            skin.getSkinColor(R.color.alter_text);
            skin.getSkinColor(R.color.start_btn_text);
            int d = skin.getSkinColor(R.color.main_bg);
            mainBgColor = d;
            f1364T = d & 16777215;
            f1366V = skin.getSkinColor(R.color.hidden);
            skin.getSkinColor(R.color.deleted);
            f1367W = skin.getSkinColor(R.color.brend);
            f1368X = skin.getSkinColor(R.color.status_bar);
            f1369Y = skin.getSkinColor(R.color.navigation_bar);
            f1388i0 = skin.getSkinDrawable(R.color.cardlist_bg);
            f1390j0 = skin.getSkinDrawable(R.color.qmslist_bg);
            skin.getSkinColor(R.color.unread_text);
            f1370Z = skin.getSkinColor(R.color.label_text);
            skin.getSkinColor(R.color.border_line);
            f1371a = skin.getSkinColor(R.color.bb_font);
            f1373b = skin.getSkinColor(R.color.bb_link);
            f1375c = skin.getSkinColor(R.color.bb_node_background);
            f1377d = skin.getSkinColor(R.color.bb_list_item);
            f1379e = skin.getSkinColor(R.color.bb_empty_image_background);
            f1381f = skin.getSkinColor(R.color.bb_empty_image_edge);
            f1383g = skin.getSkinColor(R.color.bb_empty_image_x);
            f1385h = skin.getSkinColor(R.color.bb_selection_font);
            f1387i = skin.getSkinColor(R.color.bb_selection_background);
            f1389j = skin.getSkinColor(R.color.bb_link_hl_font);
            f1391k = skin.getSkinColor(R.color.bb_link_hl_background);
            f1393l = skin.getSkinColor(R.color.bb_background);
            f1395m = skin.getSkinColor(R.color.bb_quote_head);
            f1397n = skin.getSkinColor(R.color.bb_quote_body);
            f1399o = skin.getSkinColor(R.color.bb_quote_border);
            f1400p = skin.getSkinColor(R.color.bb_quote_indent);
            f1401q = skin.getSkinColor(R.color.bb_spoiler_head);
            f1402r = skin.getSkinColor(R.color.bb_spoiler_body);
            f1403s = skin.getSkinColor(R.color.bb_spoiler_border);
            f1404t = skin.getSkinColor(R.color.bb_spoiler_indent);
            f1405u = skin.getSkinColor(R.color.bb_spoiler_indent_close);
            f1406v = skin.getSkinColor(R.color.bb_code_head);
            f1407w = skin.getSkinColor(R.color.bb_code_body);
            f1408x = skin.getSkinColor(R.color.bb_code_border);
            f1409y = skin.getSkinColor(R.color.bb_code_indent);
            f1410z = skin.getSkinColor(R.color.bb_code_indent_close);
            f1345A = skin.getSkinColor(R.color.bb_hide_head);
            f1346B = skin.getSkinColor(R.color.bb_hide_body);
            f1347C = skin.getSkinColor(R.color.bb_hide_border);
            f1348D = skin.getSkinColor(R.color.bb_hide_indent);
            f1349E = skin.getSkinColor(R.color.bb_ex_body);
            f1350F = skin.getSkinColor(R.color.bb_ex_border);
            f1351G = skin.getSkinColor(R.color.bb_ex_indent);
            f1352H = skin.getSkinColor(R.color.bb_mod_body);
            f1353I = skin.getSkinColor(R.color.bb_mod_border);
            f1354J = skin.getSkinColor(R.color.bb_mod_indent);
            f1355K = skin.getSkinColor(R.color.bb_cur_body);
            f1356L = skin.getSkinColor(R.color.bb_cur_border);
            f1357M = skin.getSkinColor(R.color.bb_cur_indent);
            f1358N = skin.getSkinColor(R.color.bb_offtop);
            f1359O = skin.getSkinColor(R.color.bb_table_border);
            f1360P = skin.getSkinColor(R.color.bb_edit_tag);
            f1361Q = skin.getSkinColor(R.color.bb_edit_tagsel);
            f1362R = skin.getSkinColor(R.color.bb_edit_val);
            f1372a0 = skin.getSkinColor(R.color.qms_typing_dots);
            f1374b0 = skin.getSkinColor(R.color.circle_bg);
            f1376c0 = skin.getSkinColor(R.color.refresh_arrow_1);
            f1378d0 = skin.getSkinColor(R.color.refresh_arrow_2);
            f1380e0 = skin.getSkinColor(R.color.refresh_arrow_3);
            BBDisplay.f517p = skin.getSkinBitmap(R.drawable.playbtn);
            BBDisplay.iconSnapback = new BitmapDrawable(skin.getSkinBitmap(R.drawable.ic_snapback));
            Util.C0424f.m648a();
            Util.C0424f.m647b("group_user", skin.getSkinColor(R.color.group_user));
            Util.C0424f.m647b("group_active_user", skin.getSkinColor(R.color.group_active_user));
            Util.C0424f.m647b("group_friend", skin.getSkinColor(R.color.group_friend));
            Util.C0424f.m647b("group_honorable", skin.getSkinColor(R.color.group_honorable));
            Util.C0424f.m647b("group_faqmakers", skin.getSkinColor(R.color.group_faqmakers));
            Util.C0424f.m647b("group_spec_project", skin.getSkinColor(R.color.group_spec_project));
            Util.C0424f.m647b("group_businessman", skin.getSkinColor(R.color.group_businessman));
            Util.C0424f.m647b("group_curator", skin.getSkinColor(R.color.group_curator));
            Util.C0424f.m647b("group_mod_helper", skin.getSkinColor(R.color.group_mod_helper));
            Util.C0424f.m647b("group_moderator", skin.getSkinColor(R.color.group_moderator));
            Util.C0424f.m647b("group_router", skin.getSkinColor(R.color.group_router));
            Util.C0424f.m647b("group_super_moderator", skin.getSkinColor(R.color.group_super_moderator));
            Util.C0424f.m647b("group_admin", skin.getSkinColor(R.color.group_admin));
            Util.C0424f.m647b("group_banned", skin.getSkinColor(R.color.group_banned));
            Util.C0424f.m647b("group_developer", skin.getSkinColor(R.color.group_developer));
            Util.C0424f.m647b("group_mod_school", skin.getSkinColor(R.color.group_mod_school));
            Util.C0424f.m647b("main_text", mainTextColor);
            Util.C0424f.m647b("border_line", skin.getSkinColor(R.color.border_line));
            Util.C0424f.m647b("label_text", skin.getSkinColor(R.color.label_text));
            Util.C0424f.m647b("aliceblue", skin.getSkinColor(R.color.aliceblue));
            Util.C0424f.m647b("antiquewhite", skin.getSkinColor(R.color.antiquewhite));
            Util.C0424f.m647b("aqua", skin.getSkinColor(R.color.aqua));
            Util.C0424f.m647b("aquamarine", skin.getSkinColor(R.color.aquamarine));
            Util.C0424f.m647b("azure", skin.getSkinColor(R.color.azure));
            Util.C0424f.m647b("beige", skin.getSkinColor(R.color.beige));
            Util.C0424f.m647b("bisque", skin.getSkinColor(R.color.bisque));
            Util.C0424f.m647b("black", skin.getSkinColor(R.color.black));
            Util.C0424f.m647b("blanchedalmond", skin.getSkinColor(R.color.blanchedalmond));
            Util.C0424f.m647b("blue", skin.getSkinColor(R.color.blue));
            Util.C0424f.m647b("blueviolet", skin.getSkinColor(R.color.blueviolet));
            Util.C0424f.m647b("brown", skin.getSkinColor(R.color.brown));
            Util.C0424f.m647b("burlywood", skin.getSkinColor(R.color.burlywood));
            Util.C0424f.m647b("cadetblue", skin.getSkinColor(R.color.cadetblue));
            Util.C0424f.m647b("chartreuse", skin.getSkinColor(R.color.chartreuse));
            Util.C0424f.m647b("chocolate", skin.getSkinColor(R.color.chocolate));
            Util.C0424f.m647b("coral", skin.getSkinColor(R.color.coral));
            Util.C0424f.m647b("cornflowerblue", skin.getSkinColor(R.color.cornflowerblue));
            Util.C0424f.m647b("cornsilk", skin.getSkinColor(R.color.cornsilk));
            Util.C0424f.m647b("crimson", skin.getSkinColor(R.color.crimson));
            Util.C0424f.m647b("cyan", skin.getSkinColor(R.color.cyan));
            Util.C0424f.m647b("darkblue", skin.getSkinColor(R.color.darkblue));
            Util.C0424f.m647b("darkcyan", skin.getSkinColor(R.color.darkcyan));
            Util.C0424f.m647b("darkgoldenrod", skin.getSkinColor(R.color.darkgoldenrod));
            Util.C0424f.m647b("darkgray", skin.getSkinColor(R.color.darkgray));
            Util.C0424f.m647b("darkgreen", skin.getSkinColor(R.color.darkgreen));
            Util.C0424f.m647b("darkkhaki", skin.getSkinColor(R.color.darkkhaki));
            Util.C0424f.m647b("darkmagenta", skin.getSkinColor(R.color.darkmagenta));
            Util.C0424f.m647b("darkolivegreen", skin.getSkinColor(R.color.darkolivegreen));
            Util.C0424f.m647b("darkorange", skin.getSkinColor(R.color.darkorange));
            Util.C0424f.m647b("darkorchid", skin.getSkinColor(R.color.darkorchid));
            Util.C0424f.m647b("darkred", skin.getSkinColor(R.color.darkred));
            Util.C0424f.m647b("darksalmon", skin.getSkinColor(R.color.darksalmon));
            Util.C0424f.m647b("darkseagreen", skin.getSkinColor(R.color.darkseagreen));
            Util.C0424f.m647b("darkslateblue", skin.getSkinColor(R.color.darkslateblue));
            Util.C0424f.m647b("darkslategray", skin.getSkinColor(R.color.darkslategray));
            Util.C0424f.m647b("darkturquoise", skin.getSkinColor(R.color.darkturquoise));
            Util.C0424f.m647b("darkviolet", skin.getSkinColor(R.color.darkviolet));
            Util.C0424f.m647b("deeppink", skin.getSkinColor(R.color.deeppink));
            Util.C0424f.m647b("deepskyblue", skin.getSkinColor(R.color.deepskyblue));
            Util.C0424f.m647b("dimgray", skin.getSkinColor(R.color.dimgray));
            Util.C0424f.m647b("dodgerblue", skin.getSkinColor(R.color.dodgerblue));
            Util.C0424f.m647b("firebrick", skin.getSkinColor(R.color.firebrick));
            Util.C0424f.m647b("floralwhite", skin.getSkinColor(R.color.floralwhite));
            Util.C0424f.m647b("forestgreen", skin.getSkinColor(R.color.forestgreen));
            Util.C0424f.m647b("fuchsia", skin.getSkinColor(R.color.fuchsia));
            Util.C0424f.m647b("gainsboro", skin.getSkinColor(R.color.gainsboro));
            Util.C0424f.m647b("ghostwhite", skin.getSkinColor(R.color.ghostwhite));
            Util.C0424f.m647b("gold", skin.getSkinColor(R.color.gold));
            Util.C0424f.m647b("goldenrod", skin.getSkinColor(R.color.goldenrod));
            Util.C0424f.m647b("gray", skin.getSkinColor(R.color.gray));
            Util.C0424f.m647b("green", skin.getSkinColor(R.color.green));
            Util.C0424f.m647b("greenyellow", skin.getSkinColor(R.color.greenyellow));
            Util.C0424f.m647b("honeydew", skin.getSkinColor(R.color.honeydew));
            Util.C0424f.m647b("hotpink", skin.getSkinColor(R.color.hotpink));
            Util.C0424f.m647b("indianred", skin.getSkinColor(R.color.indianred));
            Util.C0424f.m647b("indigo", skin.getSkinColor(R.color.indigo));
            Util.C0424f.m647b("ivory", skin.getSkinColor(R.color.ivory));
            Util.C0424f.m647b("khaki", skin.getSkinColor(R.color.khaki));
            Util.C0424f.m647b("lavender", skin.getSkinColor(R.color.lavender));
            Util.C0424f.m647b("lavenderblush", skin.getSkinColor(R.color.lavenderblush));
            Util.C0424f.m647b("lawngreen", skin.getSkinColor(R.color.lawngreen));
            Util.C0424f.m647b("lemonchiffon", skin.getSkinColor(R.color.lemonchiffon));
            Util.C0424f.m647b("lightblue", skin.getSkinColor(R.color.lightblue));
            Util.C0424f.m647b("lightcoral", skin.getSkinColor(R.color.lightcoral));
            Util.C0424f.m647b("lightcyan", skin.getSkinColor(R.color.lightcyan));
            Util.C0424f.m647b("lightgoldenrodyellow", skin.getSkinColor(R.color.lightgoldenrodyellow));
            Util.C0424f.m647b("lightgray", skin.getSkinColor(R.color.lightgray));
            Util.C0424f.m647b("lightgreen", skin.getSkinColor(R.color.lightgreen));
            Util.C0424f.m647b("lightpink", skin.getSkinColor(R.color.lightpink));
            Util.C0424f.m647b("lightsalmon", skin.getSkinColor(R.color.lightsalmon));
            Util.C0424f.m647b("lightseagreen", skin.getSkinColor(R.color.lightseagreen));
            Util.C0424f.m647b("lightskyblue", skin.getSkinColor(R.color.lightskyblue));
            Util.C0424f.m647b("lightslategray", skin.getSkinColor(R.color.lightslategray));
            Util.C0424f.m647b("lightsteelblue", skin.getSkinColor(R.color.lightsteelblue));
            Util.C0424f.m647b("lightyellow", skin.getSkinColor(R.color.lightyellow));
            Util.C0424f.m647b("lime", skin.getSkinColor(R.color.lime));
            Util.C0424f.m647b("limegreen", skin.getSkinColor(R.color.limegreen));
            Util.C0424f.m647b("linen", skin.getSkinColor(R.color.linen));
            Util.C0424f.m647b("magenta", skin.getSkinColor(R.color.magenta));
            Util.C0424f.m647b("maroon", skin.getSkinColor(R.color.maroon));
            Util.C0424f.m647b("mediumaquamarine", skin.getSkinColor(R.color.mediumaquamarine));
            Util.C0424f.m647b("mediumblue", skin.getSkinColor(R.color.mediumblue));
            Util.C0424f.m647b("mediumorchid", skin.getSkinColor(R.color.mediumorchid));
            Util.C0424f.m647b("mediumpurple", skin.getSkinColor(R.color.mediumpurple));
            Util.C0424f.m647b("mediumseagreen", skin.getSkinColor(R.color.mediumseagreen));
            Util.C0424f.m647b("mediumslateblue", skin.getSkinColor(R.color.mediumslateblue));
            Util.C0424f.m647b("mediumspringgreen", skin.getSkinColor(R.color.mediumspringgreen));
            Util.C0424f.m647b("mediumturquoise", skin.getSkinColor(R.color.mediumturquoise));
            Util.C0424f.m647b("mediumvioletred", skin.getSkinColor(R.color.mediumvioletred));
            Util.C0424f.m647b("midnightblue", skin.getSkinColor(R.color.midnightblue));
            Util.C0424f.m647b("mintcream", skin.getSkinColor(R.color.mintcream));
            Util.C0424f.m647b("mistyrose", skin.getSkinColor(R.color.mistyrose));
            Util.C0424f.m647b("moccasin", skin.getSkinColor(R.color.moccasin));
            Util.C0424f.m647b("navajowhite", skin.getSkinColor(R.color.navajowhite));
            Util.C0424f.m647b("navy", skin.getSkinColor(R.color.navy));
            Util.C0424f.m647b("oldlace", skin.getSkinColor(R.color.oldlace));
            Util.C0424f.m647b("olive", skin.getSkinColor(R.color.olive));
            Util.C0424f.m647b("olivedrab", skin.getSkinColor(R.color.olivedrab));
            Util.C0424f.m647b("orange", skin.getSkinColor(R.color.orange));
            Util.C0424f.m647b("orangered", skin.getSkinColor(R.color.orangered));
            Util.C0424f.m647b("orchid", skin.getSkinColor(R.color.orchid));
            Util.C0424f.m647b("palegoldenrod", skin.getSkinColor(R.color.palegoldenrod));
            Util.C0424f.m647b("palegreen", skin.getSkinColor(R.color.palegreen));
            Util.C0424f.m647b("paleturquoise", skin.getSkinColor(R.color.paleturquoise));
            Util.C0424f.m647b("palevioletred", skin.getSkinColor(R.color.palevioletred));
            Util.C0424f.m647b("papayawhip", skin.getSkinColor(R.color.papayawhip));
            Util.C0424f.m647b("peachpuff", skin.getSkinColor(R.color.peachpuff));
            Util.C0424f.m647b("peru", skin.getSkinColor(R.color.peru));
            Util.C0424f.m647b("pink", skin.getSkinColor(R.color.pink));
            Util.C0424f.m647b("plum", skin.getSkinColor(R.color.plum));
            Util.C0424f.m647b("powderblue", skin.getSkinColor(R.color.powderblue));
            Util.C0424f.m647b("purple", skin.getSkinColor(R.color.purple));
            Util.C0424f.m647b("red", skin.getSkinColor(R.color.red));
            Util.C0424f.m647b("rosybrown", skin.getSkinColor(R.color.rosybrown));
            Util.C0424f.m647b("royalblue", skin.getSkinColor(R.color.royalblue));
            Util.C0424f.m647b("saddlebrown", skin.getSkinColor(R.color.saddlebrown));
            Util.C0424f.m647b("salmon", skin.getSkinColor(R.color.salmon));
            Util.C0424f.m647b("sandybrown", skin.getSkinColor(R.color.sandybrown));
            Util.C0424f.m647b("seagreen", skin.getSkinColor(R.color.seagreen));
            Util.C0424f.m647b("seashell", skin.getSkinColor(R.color.seashell));
            Util.C0424f.m647b("sienna", skin.getSkinColor(R.color.sienna));
            Util.C0424f.m647b("silver", skin.getSkinColor(R.color.silver));
            Util.C0424f.m647b("skyblue", skin.getSkinColor(R.color.skyblue));
            Util.C0424f.m647b("slateblue", skin.getSkinColor(R.color.slateblue));
            Util.C0424f.m647b("slategray", skin.getSkinColor(R.color.slategray));
            Util.C0424f.m647b("snow", skin.getSkinColor(R.color.snow));
            Util.C0424f.m647b("springgreen", skin.getSkinColor(R.color.springgreen));
            Util.C0424f.m647b("steelblue", skin.getSkinColor(R.color.steelblue));
            Util.C0424f.m647b("tan", skin.getSkinColor(R.color.tan));
            Util.C0424f.m647b("teal", skin.getSkinColor(R.color.teal));
            Util.C0424f.m647b("thistle", skin.getSkinColor(R.color.thistle));
            Util.C0424f.m647b("tomato", skin.getSkinColor(R.color.tomato));
            Util.C0424f.m647b("turquoise", skin.getSkinColor(R.color.turquoise));
            Util.C0424f.m647b("violet", skin.getSkinColor(R.color.violet));
            Util.C0424f.m647b("wheat", skin.getSkinColor(R.color.wheat));
            Util.C0424f.m647b("white", skin.getSkinColor(R.color.white));
            Util.C0424f.m647b("whitesmoke", skin.getSkinColor(R.color.whitesmoke));
            Util.C0424f.m647b("yellow", skin.getSkinColor(R.color.yellow));
            Util.C0424f.m647b("yellowgreen", skin.getSkinColor(R.color.yellowgreen));
            int flagsColor = skin.getSkinColor(R.color.skin_flags);
            int i = flagsColor & 15;
            boolean z = false;
            f1392k0 = i == 1 || (i == 0 && !Skin.m734h(mainTextColor));
            int i2 = (flagsColor >> 4) & 15;
            f1394l0 = i2 == 1 || (i2 == 0 && Skin.m734h(f1368X));
            int i3 = (flagsColor >> 8) & 15;
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
        this.density = resources.getDisplayMetrics().density;
        boolean z = (this.f1338b.getConfiguration().uiMode & 48) == 32;
        this.f1343g = z;
        int i = Prefs.nightMode;
        this.f1344h = 3 == i || (i == 0 && z);
        try {
            if (!TextUtils.isEmpty(Prefs.skinId)) {
                PackageManager packageManager = context.getPackageManager();
                this.f1337a = packageManager.getApplicationInfo(Prefs.skinId, 0).loadLabel(packageManager).toString();
                this.f1339c = packageManager.getResourcesForApplication(Prefs.skinId);
           //     ACRA.getErrorReporter().putCustomData("skin", Prefs.f1183u);
            }
        } catch (Exception unused) {
            Prefs.skinId = null;
            this.f1337a = null;
            this.f1339c = null;
        }
        Resources resources2 = this.f1339c;
        if (!(resources2 == null || Prefs.nightMode == 1 || resources2.getIdentifier("zn_main_text", "color", Prefs.skinId) != 0)) {
            Prefs.nightMode = 1;
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
                    int i3 = resources3 != null ? -resources3.getIdentifier(sb2, "color", Prefs.skinId) : 0;
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
                        int i4 = resources4 != null ? -resources4.getIdentifier(sb4, "drawable", Prefs.skinId) : 0;
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
            f = this.density;
        } else if (str.endsWith("dp")) {
            parseFloat = Float.parseFloat(str.substring(0, str.length() - 2));
            f = this.density;
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

    public Bitmap getSkinBitmap(int i) {
        int i2;
        SparseIntArray sparseIntArray = this.f1341e;
        if (sparseIntArray == null || (i2 = sparseIntArray.get(i)) == 0) {
            return BitmapFactory.decodeResource(this.f1338b, i);
        }
        return BitmapFactory.decodeResource(i2 > 0 ? this.f1338b : this.f1339c, Math.abs(i2));
    }

    public int getSkinColor(int i) {
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

    ColorStateList getSkinColorStateList(int colorResId) {
        int i2;
        Resources resources;
        SparseIntArray sparseIntArray = this.f1340d;
        if (sparseIntArray == null || (i2 = sparseIntArray.get(colorResId)) == 0) {
            return this.f1338b.getColorStateList(colorResId);
        }
        if (i2 > 0) {
            resources = this.f1338b;
        } else {
            resources = this.f1339c;
            i2 = -i2;
        }
        return resources.getColorStateList(i2);
    }

    public Drawable getSkinDrawable(int i) {
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
