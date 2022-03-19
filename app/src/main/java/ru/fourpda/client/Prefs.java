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
    static boolean swipeNextprev;
    static boolean autoRefresh;
    static boolean swipeRefreshTop;
    static boolean swipeRefreshBottom;
    static boolean showReloadButton;
    static boolean loadImages;
    static boolean animAvatars;
    static boolean animSmiles;
    static boolean animImages;
    static boolean scaleImages;
    static int cacheSize;
    static boolean confirmAction;
    static boolean attachChooser;
    static boolean uploadChooser;
    static boolean sendReport;
    static boolean firstStart;
    static boolean favUnreadFirst;
    static boolean favSortByName;
    static boolean favSortReverse;
    static boolean mentionShowPosts;
    static boolean ticketForumSort;
    static int ticketPerPage;
    static boolean optionsExtended;
    static boolean qmsNotify;
    static boolean notifyQmsSystem;
    static boolean favNotify;
    static boolean favImportantNotify;
    static boolean menNotify;
    static boolean notifyGroup;
    static int backgroundMode;
    static String notificationSound;
    static boolean notificationVibration;
    static boolean notifySilence;
    static boolean topicHideHeader;
    static boolean topicHidePoll;
    static boolean postShowSign;
    static boolean postEditorTagsBelow;
    static boolean qmsHideTags;
    static int textSize;
    static int memberPostsPerPage;
    static boolean topicPppServer;
    static int memberTopicsPerPage;
    static boolean forumTppServer;
    static String skinId;
    static int nightMode;
    static int scrollMode;
    static boolean backButton;
    static int topicAction;
    static boolean showAllPost;

    public static void exportSettings(Context context) {
        try {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            File file = new File(Environment.getExternalStorageDirectory(), "4PDA.opt");
            if (!Environment.getExternalStorageDirectory().exists()) {
                Environment.getExternalStorageDirectory().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {
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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            qmsNotify = sp.getBoolean("qms_notify", true);
        } catch (ClassCastException unused) {
            qmsNotify = sp.getInt("qms_notify", 1) != 0;
        }
        notifyQmsSystem = sp.getBoolean("notify_qms_system", true);
        try {
            favNotify = sp.getBoolean("fav_notify", true);
        } catch (ClassCastException unused2) {
            favNotify = sp.getInt("fav_notify", 1) != 0;
        }
        favImportantNotify = sp.getBoolean("fav_important_notify", false);
        menNotify = sp.getBoolean("men_notify", true);
        notifyGroup = sp.getBoolean("notify_group", false);
        backgroundMode = sp.getInt("background_mode", -1);
        notificationSound = sp.getString("notification_sound", RingtoneManager.getDefaultUri(2).toString());
        notificationVibration = sp.getBoolean("notification_vibration", true);
        notifySilence = sp.getBoolean("notify_silence", false);
        if (-1 == backgroundMode) {
            SharedPreferences.Editor edit = sp.edit();
            try {
                if (PicoFCM.m14u(context)) {
                    backgroundMode = 4;
                    edit.putInt("background_mode", 4);
                } else if (PicoHCM.m784s(context)) {
                    backgroundMode = 5;
                    edit.putInt("background_mode", 5);
                } else {
                    backgroundMode = 3;
                    edit.putInt("background_mode", 3);
                }
            } catch (Exception unused3) {
                backgroundMode = 3;
                edit.putInt("background_mode", 3);
            }
            edit.commit();
        }
        topicHideHeader = sp.getBoolean("topic_hide_header", true);
        topicHidePoll = sp.getBoolean("topic_hide_poll", true);
        postShowSign = sp.getBoolean("post_show_sign", false);
        postEditorTagsBelow = sp.getBoolean("post_editor_tags_below", false);
        qmsHideTags = !sp.getBoolean("qms_hide_tags", true);
        textSize = sp.getInt("text_size", 15);
        memberPostsPerPage = sp.getInt("topic_ppp", 20);
        topicPppServer = sp.getBoolean("topic_ppp_server", true);
        memberTopicsPerPage = sp.getInt("forum_tpp", 30);
        forumTppServer = sp.getBoolean("forum_tpp_server", true);
        skinId = sp.getString("skin", "");
        nightMode = sp.getInt("nightMode", 0);
        scrollMode = sp.getInt("scroll_mode", 0);
        backButton = sp.getBoolean("back_button", true);
        topicAction = sp.getInt("topic_action", 0);
        showAllPost = sp.getBoolean("show_all_posts", false);
        swipeNextprev = sp.getBoolean("swipe_nextprev", true);
        autoRefresh = sp.getBoolean("auto_refresh", true);
        swipeRefreshTop = sp.getBoolean("swipe_refresh_top", true);
        swipeRefreshBottom = sp.getBoolean("swipe_refresh_bottom", true);
        showReloadButton = sp.getBoolean("reload_button", false);
        loadImages = sp.getBoolean("load_images", true);
        animAvatars = sp.getBoolean("anim_avatars", true);
        animSmiles = sp.getBoolean("anim_smiles", true);
        animImages = sp.getBoolean("anim_images", true);
        scaleImages = sp.getBoolean("scale_images", true);
        cacheSize = sp.getInt("cache_size", 1024);
        confirmAction = sp.getBoolean("confirm_action", true);
        attachChooser = sp.getBoolean("attach_chooser", false);
        uploadChooser = sp.getBoolean("upload_chooser", false);
        sendReport = sp.getBoolean("send_report", true);
        //ACRA.getErrorReporter().setEnabled(f1154O);
       // sp.getBoolean("options_extended", false);
        firstStart = sp.getBoolean("first_start", true);
        favUnreadFirst = sp.getBoolean("fav_unread_first", false);
        favSortByName = sp.getBoolean("fav_sort_by_name", false);
        favSortReverse = sp.getBoolean("fav_sort_reverse", false);
        mentionShowPosts = sp.getBoolean("mention_show_posts", false);
        ticketForumSort = sp.getBoolean("ticket_forum_sort", false);
        ticketPerPage = sp.getInt("ticket_per_page", 40);
        optionsExtended = sp.getBoolean("options_extended", false);
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
