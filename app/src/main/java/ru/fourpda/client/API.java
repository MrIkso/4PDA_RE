package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class API {
    static SparseArray<String> userGroups;

    // start article request
    static class C0247a0 extends DocumentManager.IGenerateRequest {
        int f945g;
        int f946h;
        int f947i;

        public C0247a0(int i, int i2, int i3) {
            super(27507);
            this.f945g = i;
            this.f946h = i2;
            this.f947i = i3;
        }

        @Override
        Document generate() {
            return new Document(this.f945g, this.f946h, this.f947i);
        }
    }

    static class C0248b extends DocumentManager.IGenerateRequest {
        int f948g;

        public C0248b(int i) {
            super(24929);
            this.f948g = i;
        }

        @Override
        public Document generate() {
            return new Document(this.f948g);
        }
    }

    static class C0249b0 extends DocumentManager.IGenerateRequest {
        int f949g;
        Document f950h;

        public C0249b0(int i, Document uVar) {
            super(28531);
            this.f949g = i;
            this.f950h = uVar;
        }

        @Override
        Document generate() {
            return new Document(this.f949g, this.f950h);
        }
    }
    // end article request

    public static class NotifyRequest extends DocumentManager.IGenerateRequest {
        private String token;
        private int f952h;
        private int f953i;

        public NotifyRequest(String token, int i, int i2) {
            super(26977);
            this.token = token;
            this.f952h = i;
            this.f953i = i2;
        }

        @Override
        public Document generate() {
            return new Document(this.token, this.f952h, this.f953i);
        }
    }

    public static class ForumLoginAnonymous extends DocumentManager.IGenerateRequest {
        private Uri linkUri;
        private Context context;

        public ForumLoginAnonymous(Uri linkUri, Context context) {
            super(30049);
            this.isAuth = true;
            this.linkUri = linkUri;
            this.context = context;
        }

        @Override
        public void prepareResult(int status, Document document) {
            if (this.linkUri == null) {
                Document l = document.getDocument(1);
                if (l != null) {
                    DataDB.m354w();
                    for (int i2 = 0; i2 < l.count(); i2++) {
                        DataDB.m356u(l.getString(i2));
                    }
                    DataDB.m362o(6, document.getInt(0));
                    return;
                }
                return;
            }
            String n = document.getString(0);
            Uri parse = TextUtils.isEmpty(n) ? null : Uri.parse(n);
            Context context = this.context;
            if (parse == null) {
                parse = this.linkUri;
            }
            Urls2.m675h(context, parse);
        }

        @Override
        public Document generate() {
            return this.linkUri == null ? new Document(DataDB.m363n(6, 0)) : new Document(this.linkUri.toString());
        }

        public ForumLoginAnonymous() {
            this(null, null);
        }
    }

    static class ForumGetFromRequest extends DocumentManager.IGenerateRequest {
        int forumNumber;
        int topicId;
        int sendCode;
        Document wizardDocument;

        /**
         * @param forumNumber номер форума
         * @param topicId номер топика
         * @param sendCode статус код отпрвки 0 - нормально\ничего, 1 - отпавка из предосмотра, 2 - пресдосмотр
         * @param wizardDocument документ предесмотра. Если не нужно то null
         */
        public ForumGetFromRequest(int forumNumber, int topicId, int sendCode, Document wizardDocument) {
            super(30566);
            this.forumNumber = forumNumber;
            this.topicId = topicId;
            this.sendCode = sendCode;
            this.wizardDocument = wizardDocument;
        }

        @Override
        Document generate() {
            Document forumDocument = new Document(this.forumNumber, this.topicId, this.sendCode);
            if (wizardDocument != null) {
                forumDocument.append(wizardDocument);
            }
            return forumDocument;
        }
    }

    static class SearchGetUsersRequest extends DocumentManager.IGenerateRequest {
        int flags;
        String term;
        int offset;
        int number;

        public SearchGetUsersRequest(int flags, String term, int offset, int number) {
            super(28018);
            this.flags = flags;
            this.term = term;
            this.offset = offset;
            this.number = number;
        }

        @Override
        public Document generate() {
            return new Document(this.flags, this.term, this.offset, this.number);
        }
    }

    static class ReportRequest extends DocumentManager.IGenerateRequest {
        Context context;
        int codeReport;
        int postId;
        String reportMessage;

        /**
         * @param context
         * @param codeReport код жаобы, 0 - жалоба на пост на форме, 1 - жалоба на комментарий на сайтеб, 2 - жалоба на репутацию
         * @param postId ид поста\комментария\репутации на который нужно пожаловаться
         * @param reportMessage текст жалобы
         */
        public ReportRequest(Context context, int codeReport, int postId, String reportMessage) {
            super(24948);
            this.context = context;
            this.codeReport = codeReport;
            this.postId = postId;
            this.reportMessage = reportMessage;
            this.statusMessage = "Отправка жалобы";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Toast.makeText(this.context, status == 0 ? "Жалоба отправлена" : status == 4 ? "Слишком короткая жалоба" : "Ошибка отправки жалобы", Toast.LENGTH_SHORT).show();
        }

        @Override
        public Document generate() {
            return new Document(this.codeReport, this.postId, this.reportMessage);
        }
    }

    static class DeviceTypeRequest extends DocumentManager.IGenerateRequest {
        String deviceType;

        /**
         * @param deviceType "phones - Телефоны", "ebook - Эл.книги", "pad - Планшеты", "smartwatch - Смарт часы"
         */
        public DeviceTypeRequest(String deviceType) {
            super(29796);
            this.deviceType = deviceType;
        }

        @Override
        public Document generate() {
            return new Document(this.deviceType);
        }
    }

    static class EventPushRequest extends DocumentManager.IGenerateRequest {
        String myId;
        String dialogId;
        int eventCode;
        int eventStatusCode;

        public EventPushRequest(String myId, String dialogId, int eventCode, int eventStatusCode) {
            super(28773);
            this.myId = myId;
            this.dialogId = dialogId;
            this.eventCode = eventCode;
            this.eventStatusCode = eventStatusCode;
        }

        @Override
        public Document generate() {
            return new Document(this.dialogId, this.eventCode, this.eventStatusCode, this.myId);
        }
    }

    static class ForumAnnouncementRequest extends DocumentManager.IGenerateRequest {
        int id;

        public ForumAnnouncementRequest(int id) {
            super(28262);
            this.id = id;
            this.statusMessage = id > 0 ? "Загрузка объявления" : "Загрузка правил";
        }

        @Override
        Document generate() {
            return new Document(this.id);
        }
    }

    static class TicketHistoryRequest extends DocumentManager.IGenerateRequest {
        int id;

        public TicketHistoryRequest(int id) {
            super(26740);
            this.id = id;
        }

        @Override
        public Document generate() {
            return new Document(this.id);
        }
    }

    static class TicketMessageRequest extends DocumentManager.IGenerateRequest {
        int ticketID;
        String comment;
        int messageID;

        public TicketMessageRequest(int ticketID, String comment, int messageID) {
            super(28020);
            this.ticketID = ticketID;
            this.comment = comment;
            this.messageID = messageID;
        }

        @Override
        public Document generate() {
            return new Document(this.ticketID, this.messageID, this.comment);
        }
    }

    static class TicketDeleteRequest extends DocumentManager.IGenerateRequest {
        int ticketID;
        int messageID;

        public TicketDeleteRequest(int ticketID, int messageID) {
            super(25716);
            this.ticketID = ticketID;
            this.messageID = messageID;
        }

        @Override
        public Document generate() {
            return new Document(this.ticketID, this.messageID);
        }
    }

    static class TicketContentRequest extends DocumentManager.IGenerateRequest {
        int ticketID;

        public TicketContentRequest(int ticketID) {
            super(25460);
            this.ticketID = ticketID;
        }

        @Override
        public Document generate() {
            return new Document(this.ticketID);
        }
    }

    static class TicketStatusRequest extends DocumentManager.IGenerateRequest {
        int ticketID;
        int ticketStatus;
        int ticketHandler;

        public TicketStatusRequest(int ticketID, int ticketStatusCode, int ticketHandler) {
            super(29556);
            this.ticketID = ticketID;
            this.ticketStatus = ticketStatusCode;
            this.ticketHandler = ticketHandler;
        }

        @Override
        public Document generate() {
            return new Document(this.ticketID, this.ticketStatus, this.ticketHandler);
        }
    }

    static class ForumAttachRequest extends DocumentManager.IGenerateRequest {
        int attachId;

        public ForumAttachRequest(int attachId) {
            super(24934);
            this.attachId = 0;
            this.attachId = attachId;
        }

        @Override
        public Document generate() {
            return new Document(this.attachId);
        }
    }

    static class LoadForumAttachRequest extends ForumAttachRequest {
        static String f977j;
        static String f978k;
        MainActivity f979h;
        String f980i;

        public static class View$OnClickListenerC0263a implements View.OnClickListener {
            final MainActivity f981a;

            View$OnClickListenerC0263a(MainActivity mainActivity) {
                this.f981a = mainActivity;
            }

            @Override
            public void onClick(View view) {
                this.f981a.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES").setData(Uri.parse(String.format("package:%s", this.f981a.getPackageName()))));
            }
        }

        public static class View$OnClickListenerC0264b implements View.OnClickListener {
            final MainActivity f982a;
            final String f983b;
            final String f984c;

            View$OnClickListenerC0264b(MainActivity mainActivity, String str, String str2) {
                this.f982a = mainActivity;
                this.f983b = str;
                this.f984c = str2;
            }

            @Override
            public void onClick(View view) {
                LoadForumAttachRequest.m831p(this.f982a, this.f983b, this.f984c, Boolean.FALSE);
            }
        }

        public static class View$OnClickListenerC0265c implements View.OnClickListener {
            final MainActivity f985a;
            final String f986b;
            final String f987c;

            View$OnClickListenerC0265c(MainActivity mainActivity, String str, String str2) {
                this.f985a = mainActivity;
                this.f986b = str;
                this.f987c = str2;
            }

            @Override
            public void onClick(View view) {
                LoadForumAttachRequest.m831p(this.f985a, this.f986b, this.f987c, Boolean.TRUE);
            }
        }

        public static class View$OnClickListenerC0266d implements View.OnClickListener {
            final MainActivity f988a;

            View$OnClickListenerC0266d(MainActivity mainActivity) {
                this.f988a = mainActivity;
            }

            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.parse("package:com.android.providers.downloads"));
                    this.f988a.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    this.f988a.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
                }
            }
        }

        public static class View$OnClickListenerC0267e implements View.OnClickListener {
            final MainActivity f989a;
            final String f990b;
            final String f991c;

            View$OnClickListenerC0267e(MainActivity mainActivity, String str, String str2) {
                this.f989a = mainActivity;
                this.f990b = str;
                this.f991c = str2;
            }

            @Override
            public void onClick(View view) {
                LoadForumAttachRequest.m831p(this.f989a, this.f990b, this.f991c, Boolean.TRUE);
            }
        }

        public LoadForumAttachRequest(int attachId, MainActivity mainActivity, String str) {
            super(attachId);
            String str2;
            this.f979h = mainActivity;
            this.f980i = str;
            if (str != null) {
                str2 = "Получение файла " + this.f980i;
            } else {
                str2 = String.format("Получение URL изображения %d", this.attachId);
            }
            this.statusMessage = str2;
        }

        @SuppressLint({"NewApi"})
        static void m831p(MainActivity mainActivity, String str, String str2, Boolean bool) {
            int i = Build.VERSION.SDK_INT;
            if (str2 == null) {
                //ACRA.getErrorReporter().handleSilentException(new Exception("null attach location."));
                return;
            }
            if (i >= 26 && str.toLowerCase().endsWith(".apk") && !mainActivity.getPackageManager().canRequestPackageInstalls()) {
                DlgSimple q1Var = new DlgSimple(mainActivity, "Чтобы система разрешила установку этого файла, вам надо разрешить установку файлов, скачанных с 4PDA в настройках.", false, "НАСТРОЙКИ", "ОТМЕНА");
                q1Var.promtMessage.setTextSize(16.0f);
                q1Var.editText.setVisibility(View.GONE);
                q1Var.m620f(new View$OnClickListenerC0263a(mainActivity), true);
                q1Var.show(true, true, true);
            }
            if (bool == null ? !Prefs.f1152M : !bool) {
                try {
                    int applicationEnabledSetting = mainActivity.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
                    if (applicationEnabledSetting == 2 || applicationEnabledSetting == 3 || applicationEnabledSetting == 4) {
                        DlgSimple q1Var2 = new DlgSimple(mainActivity, "Менеджер загрузок отключен в настройках системы.\n\nНажмите Ок, чтобы попробовать скачать другим приложением.\nНажмите Настройки, чтобы перейти в настройки Менеджера загрузок.", false, null, "Настройки");
                        q1Var2.promtMessage.setTextSize(2, 16.0f);
                        q1Var2.editText.setVisibility(View.GONE);
                        q1Var2.m620f(new View$OnClickListenerC0265c(mainActivity, str, str2), true);
                        q1Var2.m621e(new View$OnClickListenerC0266d(mainActivity), true);
                        q1Var2.show(true, true, true);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    @SuppressLint("WrongConstant") DownloadManager downloadManager = (DownloadManager) mainActivity.getSystemService("download");
                    String str3 = str2;
                    boolean z = false;
                    while (true) {
                        try {
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str3));
                            request.setTitle(str);
                            request.setDescription("4PDA");
                            if (!z) {
                                try {
                                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                if (i >= 11) {
                                    request.setNotificationVisibility(1);
                                }
                            }
                            long enqueue = downloadManager.enqueue(request);
                            if (i < 11) {
                                BootReceiver.f635a.add(enqueue);
                            }
                            Toast.makeText(mainActivity, "Загрузка " + str, Toast.LENGTH_LONG).show();
                            return;
                        } catch (Exception e3) {
                            if ((e3 instanceof IllegalArgumentException) && str3.startsWith("https:")) {
                                str3 = "http" + str3.substring(5);
                            } else if (!z) {
                                z = true;
                            } else {
                                throw e3;
                            }
                        }
                    }
                } catch (Exception e4) {
                    //ACRA.getErrorReporter().putCustomData("extra", str2);
                    //ACRA.getErrorReporter().handleSilentException(new Exception("MA Attach DownloadMgr", e4));
                    //ACRA.getErrorReporter().removeCustomData("extra");
                    DlgSimple q1Var3 = new DlgSimple(mainActivity, "При запросе к системному загрузчику произошла ошибка " + e4.getClass().getCanonicalName() + ".\n\nНажмите Ок, чтобы выбрать сторонний загрузчик или Отмена, чтобы отказаться от загрузки.", false, null, null);
                    q1Var3.promtMessage.setTextSize(2, 16.0f);
                    q1Var3.editText.setVisibility(View.GONE);
                    q1Var3.m620f(new View$OnClickListenerC0267e(mainActivity, str, str2), true);
                    q1Var3.show(true, true, true);
                }
            } else {
                try {
                    mainActivity.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse(str2)), "Загрузить с помощью"));
                } catch (Exception e5) {
                    //ACRA.getErrorReporter().putCustomData("extra", str2);
                    //ACRA.getErrorReporter().handleSilentException(new Exception("MA Attach Chooser", e5));
                    //ACRA.getErrorReporter().removeCustomData("extra");
                    DlgSimple q1Var4 = new DlgSimple(mainActivity, "При выборе внешнего загрузчика произошла ошибка " + e5.getClass().getCanonicalName() + ".\n\nНажмите Ок, чтобы скачать системным загрузчиком или Отмена, чтобы отказаться от загрузки.", false, null, null);
                    q1Var4.promtMessage.setTextSize(2, 16.0f);
                    q1Var4.editText.setVisibility(View.GONE);
                    q1Var4.m620f(new View$OnClickListenerC0264b(mainActivity, str, str2), true);
                    q1Var4.show(true, true, true);
                }
            }
        }

        public static void m830q(MainActivity mainActivity) {
            m831p(mainActivity, f977j, f978k, null);
        }

        @Override
        @SuppressLint({"NewApi", "WrongConstant"})
        public void prepareResult(int status, Document uVar) {
            String str;
            if (status == 0) {
                String n = uVar.getString(0);
                if (this.f980i == null) {
                    int lastIndexOf = n.lastIndexOf(63);
                    if (lastIndexOf < 0) {
                        str = n;
                    } else {
                        str = n.substring(0, lastIndexOf);
                    }
                    this.f980i = str;
                    int lastIndexOf2 = str.lastIndexOf(47) + 1;
                    this.f980i = lastIndexOf2 <= 0 ? this.f980i : this.f980i.substring(lastIndexOf2);
                }
                if (Build.VERSION.SDK_INT < 23 || this.f979h.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    m831p(this.f979h, this.f980i, n, null);
                    return;
                }
                f977j = this.f980i;
                f978k = n;
                this.f979h.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            } else if (4 == status) {
                Toast.makeText(this.f979h, "Ошибка. Похоже, вы скачали слишком много файлов за последнее время. Попробуйте еще раз завтра.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.f979h, "Ошибка при запросе адреса", Toast.LENGTH_LONG).show();
            }
        }
    }

    static class ForumGetTopicsRequest extends DocumentManager.IGenerateRequest {
        MainActivity mainActivity;
        int forumId;

        class View$OnClickListenerC0269a implements View.OnClickListener {
            View$OnClickListenerC0269a() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = ForumGetTopicsRequest.this.mainActivity;
                Util.copyToClipboard(mainActivity, "https://4pda.ru/forum/index.php?showforum=" + ForumGetTopicsRequest.this.forumId, "Ссылка на форум скопирована в буфер");
            }
        }

        class View$OnClickListenerC0270b implements View.OnClickListener {
            View$OnClickListenerC0270b() {
            }

            @Override
            public void onClick(View view) {
                MainActivity mainActivity = ForumGetTopicsRequest.this.mainActivity;
                Urls2.m676g(mainActivity, "https://4pda.ru/forum/index.php?showforum=" + ForumGetTopicsRequest.this.forumId);
            }
        }

        class C0271c implements BBDisplay.IBBDisplayCallback {
            final Dialog f996a;

            C0271c(Dialog dialog) {
                this.f996a = dialog;
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
                if (cVar.f543a >= 0) {
                    this.f996a.dismiss();
                    Page b = Urls2.openPage(ForumGetTopicsRequest.this.mainActivity, pVar.f2202I.get(cVar.f543a).link, false, 2);
                    if (b != null) {
                        Tab f1Var = new Tab(ForumGetTopicsRequest.this.mainActivity);
                        f1Var.addPage(b);
                        ForumGetTopicsRequest.this.mainActivity.mainLayout.setCurrentTab(f1Var);
                    }
                }
            }
        }

        public ForumGetTopicsRequest(int forumId, MainActivity mainActivity) {
            super(25702);
            this.mainActivity = mainActivity;
            this.forumId = forumId;
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (status == 0) {
                Dialog dialog = new Dialog(this.mainActivity, Skin.C0353a.f1392k0 ? R.style.Dialog_Light : R.style.Dialog_Dark);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.dlg_foruminfo);
                dialog.getWindow().setBackgroundDrawable(this.mainActivity.skin.m736f(R.drawable.np_dialog));
                dialog.getWindow().setLayout(-1, -2);
                dialog.setCanceledOnTouchOutside(true);
                ((TextView) dialog.findViewById(R.id.forumName)).setText(Util.C0427h.UnEscapeString(uVar.getString(2)));
                ((TextView) dialog.findViewById(R.id.forumDesc)).setText(Util.C0427h.UnEscapeString(uVar.getString(3)));
                dialog.findViewById(R.id.forumShare).setOnClickListener(new View$OnClickListenerC0269a());
                TextView textView = (TextView) dialog.findViewById(R.id.forumLink);
                textView.setPaintFlags(textView.getPaintFlags() | 8);
                textView.setText("https://4pda.ru/forum/index.php?showforum=" + this.forumId);
                textView.setOnClickListener(new View$OnClickListenerC0270b());
                ((TextView) dialog.findViewById(R.id.forumForums)).setText(Integer.valueOf(uVar.getInt(6)).toString());
                ((TextView) dialog.findViewById(R.id.forumTopics)).setText(Integer.valueOf(uVar.getInt(7)).toString());
                ((TextView) dialog.findViewById(R.id.forumPosts)).setText(Integer.valueOf(uVar.getInt(8)).toString());
                StringBuilder sb = new StringBuilder();
                Document l = uVar.getDocument(9);
                for (int i2 = 0; i2 < l.count(); i2++) {
                    Document l2 = l.getDocument(i2);
                    int intValue = l2.getInt(0);
                    if (intValue > 0) {
                        sb.append("[url=https://4pda.ru/forum/index.php?showuser=" + intValue + "]");
                    }
                    sb.append("[color=" + Skin.C0353a.f1398n0[l2.getInt(2)] + "]");
                    sb.append(l2.getString(1).replace("[", "&#91;").replace("]", "&#93;"));
                    sb.append("[/color]");
                    if (intValue > 0) {
                        sb.append("[/url]");
                    }
                    if (i2 < l.count() - 1) {
                        sb.append(",");
                    }
                    sb.append(" ");
                }
                BBDisplay bBDisplay = (BBDisplay) dialog.findViewById(R.id.forumMods);
                bBDisplay.setCallback(new C0271c(dialog));
                bBDisplay.setBBString(BBString.getBBString(sb.toString(), null));
                dialog.show();
                CustomDialog.m623c(dialog);
                return;
            }
            Toast.makeText(this.mainActivity, "Ошибка при запросе форума", Toast.LENGTH_LONG).show();
        }

        @Override
        Document generate() {
            return new Document(this.forumId);
        }
    }

    static class PostHistoryRequest extends DocumentManager.IGenerateRequest {
        BBDisplay bBDisplay;
        int topicId;

        public PostHistoryRequest(int topicId, BBDisplay bBDisplay) {
            super(26726);
            this.bBDisplay = bBDisplay;
            this.topicId = topicId;
        }

        @Override
        public void prepareResult(int status, Document document) {
            if (status == 0) {
                StringBuilder sb = new StringBuilder();
                Document l = document.getDocument(0);
                for (int i2 = 0; i2 < l.count(); i2++) {
                    Document l2 = l.getDocument(i2);
                    sb.append(Util.formatDate(l2.getInt(0)));
                    sb.append(" [url=https://4pda.ru/forum/index.php?showuser=");
                    sb.append(l2.getInt(1));
                    sb.append("][color=");
                    sb.append(Skin.C0353a.f1398n0[l2.getInt(3)]);
                    sb.append("]");
                    sb.append(l2.getString(2).replace("[", "&#91;").replace("]", "&#93;"));
                    sb.append("[/color][/url]:\n");
                    sb.append(l2.getString(4));
                    sb.append("\n\n");
                }
                if (sb.length() == 0) {
                    sb.append("История пуста");
                }
                BBString bbString = BBString.getBBString("[size=4]История темы[/size]\n\n" + sb.toString(), null);
                BBString.C0674e eVar = (BBString.C0674e) bbString.f2246z.clone();
                bbString.f2246z = eVar;
                eVar.f2266j = 0;
                eVar.f2265i = 0;
                this.bBDisplay.setBBString(bbString);
                return;
            }
            this.bBDisplay.setBBString(BBString.getBBString(String.format("Статус %d при загрузке истории", status), null));
        }

        @Override
        Document generate() {
            return new Document(this.topicId);
        }
    }

    static class ForumTopicPostRequest extends DocumentManager.IGenerateRequest {
        private MainActivity mainActivity;
        private int statusCode;
        private int postId;
        private int showAllPosts;
        private int topicId;
        private Tab tab;
        private boolean f1006m;
        private String f1007n;
        private String f1008o;
        private boolean f1009p;
        private boolean f1010q;

        public ForumTopicPostRequest(MainActivity mainActivity, int statusCode, int postId) {
            super(27238);
            int i3 = -1;
            this.showAllPosts = -1;
            this.mainActivity = mainActivity;
            this.statusCode = statusCode;
            this.postId = postId;
            this.showAllPosts = Prefs.showAllPost ? 3 : i3;
            if (statusCode == 1) {
                this.statusMessage = "Новые в топике " + postId;
            } else if (statusCode == 2) {
                this.statusMessage = "Последние в топике " + postId;
            } else if (statusCode == 3) {
                this.statusMessage = "Поиск поста " + postId;
            }
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            Page_Topic x0Var;
            if (status != 0) {
                Toast.makeText(this.mainActivity, "Пост не найден", Toast.LENGTH_SHORT).show();
                return;
            }
            Tab f1Var = this.tab;
            if (f1Var == null || f1Var.forumsListView != null) {
                int intValue = uVar.getInt(0);
                int intValue2 = uVar.getInt(2);
                int intValue3 = uVar.getInt(1);
                int i2 = Prefs.f1179q;
                int i3 = (intValue3 / i2) * i2;
                int intValue4 = uVar.getInt(3);
                Page_Topic x0Var2 = new Page_Topic(this.mainActivity, intValue, i3, intValue4, intValue2, this.f1007n, this.f1008o);
                x0Var2.f1079m = this.f1009p;
                if (this.f1006m) {
                    Tab f1Var2 = new Tab(this.mainActivity);
                    f1Var2.addPage(x0Var2);
                    this.mainActivity.mainLayout.setCurrentTab(f1Var2);
                } else {
                    Tab f1Var3 = this.tab;
                    if (f1Var3 != null) {
                        Page a0Var = f1Var3.page;
                        if (!(a0Var instanceof Page_Topic) || !a0Var.mo109y(intValue, i3, intValue4)) {
                            this.tab.addPage(x0Var2);
                        } else {
                            Page_Topic x0Var3 = (Page_Topic) this.tab.page;
                            Form_Post wVar = x0Var3.f3031d0;
                            if (wVar != null && wVar.m201t()) {
                                x0Var3.f3031d0.m202s();
                            }
                            if (this.f1010q) {
                                x0Var3.tabReload();
                            }
                            x0Var3.m123k0(intValue2, this.f1007n);
                        }
                    } else {
                        int size = this.mainActivity.mainLayout.tabList.size() - 1;
                        boolean z = false;
                        while (true) {
                            if (size < 0) {
                                break;
                            }
                            Tab f1Var4 = this.mainActivity.mainLayout.tabList.get(size);
                            Page a0Var2 = f1Var4.page;
                            if ((a0Var2 instanceof Page_Topic) && (z = (x0Var = (Page_Topic) a0Var2).mo109y(Integer.valueOf(intValue), Integer.valueOf(i3), Integer.valueOf(intValue4)))) {
                                Form_Post wVar2 = x0Var.f3031d0;
                                if (wVar2 != null && wVar2.m201t()) {
                                    x0Var.f3031d0.m202s();
                                }
                                x0Var.m123k0(intValue2, this.f1007n);
                                this.mainActivity.mainLayout.setCurrentTab(f1Var4);
                            }
                            size--;
                        }
                        if (!z) {
                            int size2 = this.mainActivity.mainLayout.tabList.size() - 1;
                            while (true) {
                                if (size2 < 0) {
                                    break;
                                }
                                Tab f1Var5 = this.mainActivity.mainLayout.tabList.get(size2);
                                Page a0Var3 = f1Var5.page;
                                if ((a0Var3 instanceof Page_Topic) && (z = ((Page_Topic) a0Var3).mo109y(Integer.valueOf(intValue)))) {
                                    f1Var5.addPage(x0Var2);
                                    this.mainActivity.mainLayout.setCurrentTab(f1Var5);
                                    break;
                                }
                                size2--;
                            }
                        }
                        if (!z) {
                            Tab f1Var6 = new Tab(this.mainActivity);
                            f1Var6.addPage(x0Var2);
                            this.mainActivity.mainLayout.setCurrentTab(f1Var6);
                        }
                    }
                }
                if (this.statusCode == 3 && intValue2 != this.postId) {
                    Toast.makeText(this.mainActivity, "Пост был удален, показываю ближайший", Toast.LENGTH_LONG).show();
                }
                CustomDialog.dismiss();
            }
        }

        @Override
        public Document generate() {
            return new Document(this.statusCode, this.postId, this.showAllPosts, this.topicId);
        }

        public ForumTopicPostRequest m829p(String str) {
            this.f1007n = str;
            return this;
        }

        public ForumTopicPostRequest m828q(boolean z) {
            this.f1009p = z;
            return this;
        }

        public ForumTopicPostRequest m827r(boolean z) {
            this.f1010q = z;
            return this;
        }

        public ForumTopicPostRequest m826s(boolean z) {
            this.f1006m = z;
            return this;
        }

        public ForumTopicPostRequest m825t(int topicId) {
            this.topicId = topicId;
            return this;
        }

        public ForumTopicPostRequest m824u(Tab f1Var) {
            this.tab = f1Var;
            return this;
        }

        public ForumTopicPostRequest m823v(String str) {
            this.f1008o = str;
            return this;
        }

        public ForumTopicPostRequest m822w(int i) {
            this.showAllPosts = i;
            return this;
        }
    }

    static class ForumKarmaRequest extends DocumentManager.IGenerateRequest {
        int id;
        int action;

        /**
         * @param postId ид поста на который нужно поставить оценку
         * @param action действие, 0 - история, 1 - минус, 2 - плюс
         */
        public ForumKarmaRequest(int postId, int action) {
            super(27494);
            this.id = postId;
            this.action = action;
        }

        @Override
        public Document generate() {
            return new Document(this.id, this.action);
        }
    }

    static class ForumModifyRequest extends DocumentManager.IGenerateRequest {
        Document f1013g;
        int f1014h;
        int f1015i;
        int f1016j;
        private Page f1017k;
        private SparseArray f1018l;
        private Runnable f1019m;

        static class View$OnClickListenerC0276a implements View.OnClickListener {
            final Document document;
            final int action;
            final int cmd;
            final int param;
            final Page f1024e;
            final String f1025f;
            final SparseArray f1026g;
            final Runnable f1027h;

            View$OnClickListenerC0276a(Document document, int action, int cmd, int param, Page a0Var, String str, SparseArray sparseArray, Runnable runnable) {
                this.document = document;
                this.action = action;
                this.cmd = cmd;
                this.param = param;
                this.f1024e = a0Var;
                this.f1025f = str;
                this.f1026g = sparseArray;
                this.f1027h = runnable;
            }

            @Override
            public void onClick(View view) {
                DocumentManager.getResultRequest(new ForumModifyRequest(this.document, this.action, this.cmd, this.param, this.f1024e, this.f1025f, this.f1026g, this.f1027h));
            }
        }

        public static void m821p(int postId, SparseArray attaches, int action, int cmd, int param, Page page, String str, String str2, Runnable runnable) {
            Document document = new Document();
            if (postId != 0) {
                document.append(postId);
            }
            if (attaches != null) {
                for (int i5 = 0; i5 < attaches.size(); i5++) {
                    int keyAt = attaches.keyAt(i5);
                    if (keyAt != postId) {
                        document.append(keyAt);
                    }
                }
            }
            if ((Prefs.f1151L || 14 == action || 4 == action) && !TextUtils.isEmpty(str2)) {
                DlgSimple q1Var = new DlgSimple(page.mainActivity, String.format("Подтвердите %s (%d)", str, document.count()), false, str2, null);
                q1Var.editText.setVisibility(View.GONE);
                q1Var.m620f(new View$OnClickListenerC0276a(document, action, cmd, param, page, str, attaches, runnable), true);
                q1Var.show(true, true, true);
                return;
            }
            DocumentManager.getResultRequest(new ForumModifyRequest(document, action, cmd, param, page, str, attaches, runnable));
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (!this.f1017k.isLoading) {
                Integer m = uVar.getInt(0);
                int d = this.f1013g.count();
                if (status != 0 && 1 == d && 6 == this.f1014h) {
                    MainActivity mainActivity = this.f1017k.mainActivity;
                    Toast.makeText(mainActivity, "Не удалось поднять пост в шапку: " + uVar.getDocument(1).getString(0), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this.f1017k.mainActivity, status == 0 ? (m == null || m.intValue() >= d) ? "Действие выполнено" : String.format("Действие выполнено частично (%d/%d)", m, Integer.valueOf(d)) : "Действие завершилось ошибкой", Toast.LENGTH_LONG).show();
                }
                if (status == 0) {
                    int i2 = this.f1014h;
                    if (i2 == 11 || i2 == 12 || i2 == 14 || i2 == 13) {
                        for (int i3 = 0; i3 < this.f1013g.count(); i3++) {
                            DocumentManager.f2752I.m649c(1, this.f1013g.getInt(i3), this.f1017k);
                        }
                    } else if (i2 == 21) {
                        for (int i4 = 0; i4 < this.f1013g.count(); i4++) {
                            DocumentManager.f2752I.m649c(0, this.f1013g.getInt(i4), this.f1017k);
                        }
                    }
                    int i5 = this.f1014h;
                    if ((i5 == 11 || i5 == 21) && this.f1015i == 8 && this.f1016j == 0) {
                        DocumentManager.isMemberValid();
                    }
                    SparseArray sparseArray = this.f1018l;
                    if (sparseArray != null) {
                        sparseArray.clear();
                    }
                    Runnable runnable = this.f1019m;
                    if (runnable != null) {
                        runnable.run();
                    } else {
                        this.f1017k.tabReload();
                    }
                }
            }
        }

        @Override
        public Document generate() {
            return new Document(this.f1014h, this.f1013g, this.f1015i, this.f1016j);
        }

        private ForumModifyRequest(Document uVar, int i, int i2, int i3, Page a0Var, String str, SparseArray sparseArray, Runnable runnable) {
            super(28006);
            this.f1013g = uVar;
            this.f1014h = i;
            this.f1015i = i2;
            this.f1016j = i3;
            this.f1017k = a0Var;
            this.f1018l = sparseArray;
            this.f1019m = runnable;
            if (!TextUtils.isEmpty(str)) {
                this.statusMessage = uVar.count() > 1 ? String.format("%s (%d)", str, uVar.count()) : str;
            }
        }
    }

    static class ForumPostRequest extends DocumentManager.IGenerateRequest {
        int topicId;
        int postId;
        String postMessage;
        int[] attaches;
        String reason;
        int postFlags;

        public ForumPostRequest(int topicId, int postId, String postMessage, int[] attaches, String reason) {
            super(28774);
            this.topicId = topicId;
            this.postId = postId;
            this.postMessage = postMessage;
            this.attaches = attaches;
            this.reason = reason;
        }

        @Override
        public Document generate() {
            Document document = new Document(this.topicId, this.postId, this.postFlags, this.postMessage);
            Document attachesDocument = new Document();
            if (this.attaches != null) {
                for (int attach : this.attaches) {
                    attachesDocument.append(attach);
                }
            }
            document.append(attachesDocument);

            if (!(reason == null || this.postId == 0)) {
                document.append(reason);
            }
            return document;
        }
    }

    static class ForumTopicEditRequest extends DocumentManager.IGenerateRequest {
        int forumId;
        int topicId;
        String title;
        String description;
        Document pool;

        public ForumTopicEditRequest(int forumId, int topicId, String title, String description, Document pool) {
            super(29798);
            this.forumId = forumId;
            this.topicId = topicId;
            this.title = title;
            this.description = description;
            this.pool = pool;
        }

        @Override
        public Document generate() {
            if (this.pool == null) {
                return new Document(this.forumId, this.topicId, this.title, this.description);
            }
            return new Document(this.forumId, this.topicId, this.title, this.description, this.pool);
        }
    }

    static class ForumVoteRequest extends DocumentManager.IGenerateRequest {
        int topicId;
        Document answers;

        public ForumVoteRequest(int topicId, Document answers) {
            super(30310);
            this.topicId = topicId;
            this.answers = answers;
            this.statusMessage = "Ответ на опрос";
        }

        @Override
        Document generate() {
            return new Document(this.topicId, this.answers);
        }
    }

    static class QmsBlacklistRequest extends DocumentManager.IGenerateRequest {
        int opponentId;
        boolean add;

        public QmsBlacklistRequest(int opponentId, boolean add) {
            super(25201);
            this.opponentId = opponentId;
            this.add = add;
        }

        @Override
        public Document generate() {
            return new Document(this.add ? this.opponentId : -this.opponentId);
        }
    }

    static class QmsDeleteRequest extends DocumentManager.IGenerateRequest {
        int dialogId;
        int messageId;
        boolean isOnlyMe;

        public QmsDeleteRequest(int dialogId, int messageId, boolean isOnlyMe) {
            super(25713);
            this.dialogId = dialogId;
            this.messageId = messageId;
            this.isOnlyMe = isOnlyMe;
        }

        @Override
        public Document generate() {
            return new Document(this.dialogId, this.messageId, this.isOnlyMe ? 1 : 0);
        }
    }

    static class QmsListRequest extends DocumentManager.IGenerateRequest {
        int opponentId;

        public QmsListRequest(int opponentId) {
            super(27761);
            this.opponentId = opponentId;
        }

        @Override
        public Document generate() {
            return new Document(this.opponentId);
        }
    }

    static class QmsThreadRequest extends DocumentManager.IGenerateRequest {
        int dialogId;
        int messageId;
        int limit;

        public QmsThreadRequest(int dialogId, int messageId, int limit) {
            super(29809);
            this.dialogId = dialogId;
            this.messageId = messageId;
            this.limit = limit;
        }

        @Override
        public Document generate() {
            return new Document(this.dialogId, this.messageId, this.limit);
        }
    }

    static class QmsPostRequest extends DocumentManager.IGenerateRequest {
        int threadId;
        int opponentId;
        String message;
        String talkTitle;
        List<Integer> list;

        public QmsPostRequest(int threadId, String message, List<Integer> list) {
            super(28785);
            this.threadId = 0;
            this.threadId = threadId;
            this.message = message;
            this.list = list;
        }

        @Override
        public Document generate() {
            Document document = new Document();
            if (this.list != null) {
                for (int i = 0; i < this.list.size(); i++) {
                    document.append(this.list.get(i));
                }
            }
            if (this.threadId > 0) {
                return new Document(this.threadId, this.message, document);
            }
            return new Document(0, this.message, document, this.opponentId, this.talkTitle);
        }

        public QmsPostRequest(int opponentId, String talkTitle, String message) {
            super(28785);
            this.threadId = 0;
            this.opponentId = opponentId;
            this.talkTitle = talkTitle;
            this.message = message;
        }
    }

    static class SiteCommentRequest extends DocumentManager.IGenerateRequest {
        int articleId;
        int parentId;
        String msg;

        public SiteCommentRequest(int articleId, int parentId, String msg) {
            super(25459);
            this.articleId = articleId;
            this.parentId = parentId;
            this.msg = msg;
        }

        @Override
        public Document generate() {
            return new Document(this.articleId, this.parentId, this.msg);
        }
    }

    static class SiteJumpRequest extends DocumentManager.IGenerateRequest {
        int time;
        boolean next;
        int category;

        public SiteJumpRequest(int time, boolean next, int category) {
            super(27251);
            this.time = time;
            this.next = next;
            this.category = category;
        }

        @Override
        Document generate() {
            return new Document(this.next ? 1 : 0, this.time, this.category);
        }
    }

    static {
        userGroups = new SparseArray<>(20);
        userGroups.put(1, "Неактивированные");
        userGroups.put(2, "Гости");
        userGroups.put(3, "Пользователи");
        userGroups.put(4, "Админы");
        userGroups.put(5, "Забанен");
        userGroups.put(7, "Активные пользователи");
        userGroups.put(8, "Друзья 4PDA");
        userGroups.put(9, "Модераторы");
        userGroups.put(10, "Супермодераторы");
        userGroups.put(11, "Помощник модератора");
        userGroups.put(12, "FAQMakers");
        userGroups.put(13, "Почетные форумчане");
        userGroups.put(15, "Разработчики");
        userGroups.put(16, "Роутеры");
        userGroups.put(17, "Бизнесмены");
        userGroups.put(18, "Участник спецпроекта");
        userGroups.put(19, "Школа Модераторов");
        userGroups.put(20, "Кураторы");
    }
}
