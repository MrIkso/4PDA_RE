package ru.fourpda.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

class Prefs {
    static boolean f1140A;
    static boolean f1141B;
    static boolean f1142C;
    static boolean f1143D;
    static boolean showReloadButton;
    static boolean f1145F;
    static boolean f1146G;
    static boolean f1147H;
    static boolean f1148I;
    static boolean f1149J;
    static int f1150K;
    static boolean f1151L;
    static boolean f1152M;
    static boolean f1153N;
    static boolean f1154O;
    static boolean f1155P;
    static boolean f1156Q;
    static boolean f1157R;
    static boolean f1158S;
    static boolean f1159T;
    static boolean f1160U;
    static int f1161V;
    static boolean f1162W;
    static boolean f1163a;
    static boolean f1164b;
    static boolean f1165c;
    static boolean f1166d;
    static boolean f1167e;
    static boolean f1168f;
    static int f1169g;
    static String f1170h;
    static boolean f1171i;
    static boolean f1172j;
    static boolean f1173k;
    static boolean f1174l;
    static boolean f1175m;
    static boolean f1176n;
    static boolean f1177o;
    static int f1178p;
    static int f1179q;
    static boolean f1180r;
    static int f1181s;
    static boolean f1182t;
    static String f1183u;
    static int f1184v;
    static int f1185w;
    static boolean f1186x;
    static int f1187y;
    static boolean showAllPost;

    public static void exportSettings(Context context) {
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            File file = new File(Environment.getExternalStorageDirectory(), "4PDA.opt");
            if (!Environment.getExternalStorageDirectory().exists()) {
                Environment.getExternalStorageDirectory().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map.Entry<String, ?> entry : defaultSharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();
                if (!key.equals("member_id") && !key.equals("login_key") && !key.equals("member_name")) {
                    byte[] bytes = key.getBytes();
                    fileOutputStream.write(new byte[]{(byte) (bytes.length & 255), (byte) ((bytes.length >> 8) & 255), (byte) ((bytes.length >> 16) & 255), (byte) ((bytes.length >> 24) & 255)});
                    fileOutputStream.write(bytes);
                    Object value = entry.getValue();
                    if (value instanceof Boolean) {
                        fileOutputStream.write(1);
                    } else if (value instanceof Integer) {
                        fileOutputStream.write(2);
                    } else if (value instanceof String) {
                        fileOutputStream.write(3);
                    } else {
                        fileOutputStream.write(4);
                    }
                    byte[] bytes2 = value.toString().getBytes();
                    fileOutputStream.write(new byte[]{(byte) (bytes2.length & 255), (byte) ((bytes2.length >> 8) & 255), (byte) ((bytes2.length >> 16) & 255), (byte) ((bytes2.length >> 24) & 255)});
                    fileOutputStream.write(bytes2);
                }
            }
            fileOutputStream.write(new byte[]{-1, -1, -1, Byte.MAX_VALUE});
            Toast.makeText(context, "Настройки экспортированы\n" + file.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Ошибка экспорта", Toast.LENGTH_SHORT).show();
            //ACRA.getErrorReporter().handleSilentException(e);
            e.printStackTrace();
        }
    }

    public static void importSettings(Context context) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "4PDA.opt");
            if (!file.exists()) {
                Toast.makeText(context, "Нет файла импорта", Toast.LENGTH_SHORT).show();
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            while (true) {
                byte[] bArr = new byte[4];
                fileInputStream.read(bArr);
                int i = -1;
                if (-1 == bArr[0] && -1 == bArr[1] && -1 == bArr[2] && Byte.MAX_VALUE == bArr[3]) {
                    edit.commit();
                    initPreference(context);
                    Toast.makeText(context, "Настройки импортированы\n" + file.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                int i2 = ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[0] & 255);
                if (i2 >= 0 && i2 <= 65535) {
                    i = i2;
                }
                byte[] bArr2 = new byte[i];
                fileInputStream.read(bArr2);
                String str = new String(bArr2);
                int read = fileInputStream.read();
                fileInputStream.read(bArr);
                byte[] bArr3 = new byte[(bArr[0] & 255) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8)];
                fileInputStream.read(bArr3);
                String str2 = new String(bArr3);
                if (!str.equals("member_id") && !str.equals("login_key") && !str.equals("member_name")) {
                    if (read == 1) {
                        edit.putBoolean(str, Boolean.parseBoolean(str2));
                    } else if (read == 2) {
                        edit.putInt(str, Integer.parseInt(str2));
                    } else if (read == 3) {
                        edit.putString(str, str2);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Ошибка импорта", Toast.LENGTH_SHORT).show();
            //ACRA.getErrorReporter().handleSilentException(e);
            e.printStackTrace();
        }
    }

    public static void initPreference(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            f1163a = defaultSharedPreferences.getBoolean("qms_notify", true);
        } catch (ClassCastException unused) {
            f1163a = defaultSharedPreferences.getInt("qms_notify", 1) != 0;
        }
        f1164b = defaultSharedPreferences.getBoolean("notify_qms_system", true);
        try {
            f1165c = defaultSharedPreferences.getBoolean("fav_notify", true);
        } catch (ClassCastException unused2) {
            f1165c = defaultSharedPreferences.getInt("fav_notify", 1) != 0;
        }
        f1166d = defaultSharedPreferences.getBoolean("fav_important_notify", false);
        f1167e = defaultSharedPreferences.getBoolean("men_notify", true);
        f1168f = defaultSharedPreferences.getBoolean("notify_group", false);
        f1169g = defaultSharedPreferences.getInt("background_mode", -1);
        f1170h = defaultSharedPreferences.getString("notification_sound", RingtoneManager.getDefaultUri(2).toString());
        f1171i = defaultSharedPreferences.getBoolean("notification_vibration", true);
        f1172j = defaultSharedPreferences.getBoolean("notify_silence", false);
        if (-1 == f1169g) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            try {
                if (PicoFCM.m14u(context)) {
                    f1169g = 4;
                    edit.putInt("background_mode", 4);
                } else if (PicoHCM.m784s(context)) {
                    f1169g = 5;
                    edit.putInt("background_mode", 5);
                } else {
                    f1169g = 3;
                    edit.putInt("background_mode", 3);
                }
            } catch (Exception unused3) {
                f1169g = 3;
                edit.putInt("background_mode", 3);
            }
            edit.commit();
        }
        f1173k = defaultSharedPreferences.getBoolean("topic_hide_header", true);
        f1174l = defaultSharedPreferences.getBoolean("topic_hide_poll", true);
        f1175m = defaultSharedPreferences.getBoolean("post_show_sign", false);
        f1176n = defaultSharedPreferences.getBoolean("post_editor_tags_below", false);
        f1177o = !defaultSharedPreferences.getBoolean("qms_hide_tags", true);
        f1178p = defaultSharedPreferences.getInt("text_size", 15);
        f1179q = defaultSharedPreferences.getInt("topic_ppp", 20);
        f1180r = defaultSharedPreferences.getBoolean("topic_ppp_server", true);
        f1181s = defaultSharedPreferences.getInt("forum_tpp", 30);
        f1182t = defaultSharedPreferences.getBoolean("forum_tpp_server", true);
        f1183u = defaultSharedPreferences.getString("skin", "");
        f1184v = defaultSharedPreferences.getInt("nightMode", 0);
        f1185w = defaultSharedPreferences.getInt("scroll_mode", 0);
        f1186x = defaultSharedPreferences.getBoolean("back_button", true);
        f1187y = defaultSharedPreferences.getInt("topic_action", 0);
        showAllPost = defaultSharedPreferences.getBoolean("show_all_posts", false);
        f1140A = defaultSharedPreferences.getBoolean("swipe_nextprev", true);
        f1141B = defaultSharedPreferences.getBoolean("auto_refresh", true);
        f1142C = defaultSharedPreferences.getBoolean("swipe_refresh_top", true);
        f1143D = defaultSharedPreferences.getBoolean("swipe_refresh_bottom", true);
        showReloadButton = defaultSharedPreferences.getBoolean("reload_button", false);
        f1145F = defaultSharedPreferences.getBoolean("load_images", true);
        f1146G = defaultSharedPreferences.getBoolean("anim_avatars", true);
        f1147H = defaultSharedPreferences.getBoolean("anim_smiles", true);
        f1148I = defaultSharedPreferences.getBoolean("anim_images", true);
        f1149J = defaultSharedPreferences.getBoolean("scale_images", true);
        f1150K = defaultSharedPreferences.getInt("cache_size", 1024);
        f1151L = defaultSharedPreferences.getBoolean("confirm_action", true);
        f1152M = defaultSharedPreferences.getBoolean("attach_chooser", false);
        f1153N = defaultSharedPreferences.getBoolean("upload_chooser", false);
        f1154O = defaultSharedPreferences.getBoolean("send_report", true);
        //ACRA.getErrorReporter().setEnabled(f1154O);
        defaultSharedPreferences.getBoolean("options_extended", false);
        f1155P = defaultSharedPreferences.getBoolean("first_start", true);
        f1156Q = defaultSharedPreferences.getBoolean("fav_unread_first", false);
        f1157R = defaultSharedPreferences.getBoolean("fav_sort_by_name", false);
        f1158S = defaultSharedPreferences.getBoolean("fav_sort_reverse", false);
        f1159T = defaultSharedPreferences.getBoolean("mention_show_posts", false);
        f1160U = defaultSharedPreferences.getBoolean("ticket_forum_sort", false);
        f1161V = defaultSharedPreferences.getInt("ticket_per_page", 40);
        f1162W = defaultSharedPreferences.getBoolean("options_extended", false);
    }

    public static void resetSettings(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, ?> all = defaultSharedPreferences.getAll();
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (!key.startsWith("member") && !key.startsWith("login") && !key.equals("first_start")) {
                edit.remove(key);
            }
        }
        edit.commit();
    }

    public static void saveInt(Context context, String str, int i) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void saveString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void saveBoolean(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }
}
