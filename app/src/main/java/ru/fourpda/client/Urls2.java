package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Urls2 {

    public static class View$OnClickListenerC0407a implements View.OnClickListener {
        final int f1589a;
        final MainActivity f1590b;
        final String f1591c;

        View$OnClickListenerC0407a(int i, MainActivity mainActivity, String str) {
            this.f1589a = i;
            this.f1590b = mainActivity;
            this.f1591c = str;
        }

        @Override
        public void onClick(View view) {
            DocumentManager.getResultRequest(new API.LoadForumAttachRequest(this.f1589a, this.f1590b, this.f1591c));
        }
    }

    public static class C0408b implements TextWatcher {
        final CustomDialog f1592a;
        final EditText f1593b;
        final EditText f1594c;

        C0408b(CustomDialog l1Var, EditText editText, EditText editText2) {
            this.f1592a = l1Var;
            this.f1593b = editText;
            this.f1594c = editText2;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1592a.m625a(this.f1593b.getText().length() > 0 && this.f1594c.getText().length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public static class View$OnClickListenerC0409c implements View.OnClickListener {
        final EditText f1595a;
        final EditText f1596b;
        final MainActivity f1597c;
        final Map f1598d;

        View$OnClickListenerC0409c(EditText editText, EditText editText2, MainActivity mainActivity, Map map) {
            this.f1595a = editText;
            this.f1596b = editText2;
            this.f1597c = mainActivity;
            this.f1598d = map;
        }

        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.f1595a.getText().toString()) || TextUtils.isEmpty(this.f1596b.getText().toString())) {
                Toast.makeText(this.f1597c, "Введите пароль", Toast.LENGTH_SHORT).show();
            } else if (this.f1595a.getText().toString().equals(this.f1596b.getText().toString())) {
                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(this.f1597c, 6, Urls2.m674i((String) this.f1598d.get("m")), Urls2.m674i((String) this.f1598d.get("t")), (String) this.f1598d.get("s"), this.f1595a.getText().toString()));
            } else {
                Toast.makeText(this.f1597c, "Новые пароли не совпадают", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class C0410d implements TextWatcher {
        final DlgSimple f1599a;

        C0410d(DlgSimple q1Var) {
            this.f1599a = q1Var;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.f1599a.m625a(editable.length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public static class View$OnClickListenerC0411e implements View.OnClickListener {
        final MainActivity f1600a;
        final int f1601b;
        final boolean f1602c;
        final int f1603d;
        final DlgSimple f1604e;

        View$OnClickListenerC0411e(MainActivity mainActivity, int i, boolean z, int i2, DlgSimple q1Var) {
            this.f1600a = mainActivity;
            this.f1601b = i;
            this.f1602c = z;
            this.f1603d = i2;
            this.f1604e = q1Var;
        }

        @Override
        public void onClick(View view) {
            MainActivity mainActivity = this.f1600a;
            int i = this.f1601b;
            boolean z = this.f1602c;
            DocumentManager.getResultRequest(new MemberChangeReputationRequest(mainActivity, i, z ? 1 : 0, this.f1603d, this.f1604e.editText.getText().toString()));
        }
    }

    public static class ChangeReputationRequest extends MemberInfoRequest {
        final DlgSimple f1605h;

        ChangeReputationRequest(int memberId, DlgSimple q1Var) {
            super(memberId);
            this.f1605h = q1Var;
        }

        @Override
        void prepareResult(int status, Document uVar) {
            if (status == 0) {
                TextView textView = this.f1605h.promtMessage;
                textView.setText(((Object) this.f1605h.promtMessage.getText()) + " " + Util.C0427h.UnEscapeString(uVar.getString(1)));
            }
        }
    }

    public static class RunnableC0413g implements Runnable {
        final Map f1606a;
        final MainActivity f1607b;

        RunnableC0413g(Map map, MainActivity mainActivity) {
            this.f1606a = map;
            this.f1607b = mainActivity;
        }

        @Override
        public void run() {
            int i = Urls2.m674i((String) this.f1606a.get("t"));
            if (i != 0) {
                Tab f1Var = new Tab(this.f1607b);
                f1Var.addPage(new Page_Topic(this.f1607b, i, 0));
                this.f1607b.mainLayout.setCurrentTab(f1Var);
            }
        }
    }

    @SuppressLint("WrongConstant")
    public static Page openPage(MainActivity mainActivity, Uri uri, boolean z, int i) {
        int i2;
        int parseInt = 0;
        int i3;
        boolean z2;
        boolean z3;
        int i4 = 0;
        String str;
        String str2;
        String str3;
        String str4;
        Document uVar;
        String str5;
        int i5;
        char c;
        int i6;
        Document uVar2;
        String str6;
        Document uVar3;
        String str7;
        Object obj;
        int i7;
        int i8;
        int i9;
        if (is4pdaHost(uri)) {
            String d = Util.C0427h.urlDecode(uri.getPath());
            if (!d.startsWith("/")) {
                d = "/" + d;
            }
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            String encodedQuery = uri.getEncodedQuery();
            boolean z4 = false;
            if (!TextUtils.isEmpty(encodedQuery)) {
                for (String str8 : encodedQuery.replace("&amp;", "&").split("&")) {
                    String[] split = str8.split("=");
                    if (split.length == 2) {
                        String d2 = Util.C0427h.urlDecode(split[0].trim());
                        String d3 = Util.C0427h.urlDecode(split[1].trim());
                        if (treeMap.containsKey(d2)) {
                            treeMap.put(d2, ((String) treeMap.get(d2)) + "," + d3);
                        } else {
                            treeMap.put(d2, d3);
                        }
                    }
                }
            }
            if (d.equals("/")) {
                String str9 = (String) treeMap.get("p");
                if (str9 == null || (i9 = m674i(str9)) == 0) {
                    return new Page_Start(mainActivity);
                }
                return new Page_Article(mainActivity, i9, m674i((String) treeMap.get("c")));
            } else if (d.matches("(?i:^/DEVDB/?$)")) {
                return new Page_DevType(mainActivity, "phones", false);
            } else {
                Matcher matcher = Pattern.compile("(?i:^/DEVDB/(PHONES|PAD|EBOOK)(/ALL)?/?$)").matcher(d);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    if (matcher.group(2) != null && matcher.group(2).equalsIgnoreCase("/ALL")) {
                        z4 = true;
                    }
                    return new Page_DevType(mainActivity, group, z4);
                }
                Matcher matcher2 = Pattern.compile("(?i:^/DEVDB/(PHONES|PAD|EBOOK)/([^/]+)(/ALL)?/?$)").matcher(d);
                if (!matcher2.matches() || "new".equalsIgnoreCase(matcher2.group(2))) {
                    Matcher matcher3 = Pattern.compile("(?i:^/DEVDB/([^/]+)/?$)").matcher(d);
                    if (matcher3.matches()) {
                       // return Page_Device.m742d0(mainActivity, matcher3.group(1));
                    }
                    Matcher matcher4 = Pattern.compile("(?i:^/(\\d{4})/(\\d{1,2})/(\\d{1,2})/(\\d+)).*").matcher(d);
                    if (matcher4.matches()) {
                        try {
                            parseInt = Integer.parseInt(matcher4.group(1));
                        } catch (Exception unused) {
                            i2 = 0;
                        }
                        if (parseInt < 1900 || parseInt > 2100) {
                            throw new InvalidParameterException();
                        }
                        int parseInt2 = Integer.parseInt(matcher4.group(2));
                        if (parseInt2 < 1 || parseInt2 > 12) {
                            throw new InvalidParameterException();
                        }
                        int parseInt3 = Integer.parseInt(matcher4.group(3));
                        if (parseInt3 < 1 || parseInt3 > 31) {
                            throw new InvalidParameterException();
                        }
                        i2 = Integer.parseInt(matcher4.group(4));
                        if (i2 > 0) {
                            return new Page_Article(mainActivity, i2, false);
                        }
                    } else {
                        i2 = 0;
                    }
                    Matcher matcher5 = Pattern.compile("(?i:^/PAGE/(\\d+)?).*").matcher(d);
                    if (matcher5.matches()) {
                        try {
                            i3 = Integer.parseInt(matcher5.group(1));
                        } catch (Exception unused2) {
                            i3 = 1;
                        }
                        if (i3 >= 1 && i3 <= 100000) {
                            return new Page_Site_List(mainActivity, 0, (i3 - 1) * Page_Site_List.f2688K);
                        }
                        throw new InvalidParameterException();
                    } else {
                        Matcher matcher6 = Pattern.compile("(?i:^/TAG/(\\d+)(/PAGE/(\\d+)?)?).*").matcher(d);
                        if (matcher6.matches()) {
                            return new Page_Site_List(mainActivity, m674i(matcher6.group(1)), (Math.max(1, m674i(matcher6.group(3))) - 1) * Page_Site_List.f2688K);
                        }
                        if (d.startsWith("/forum/")) {
                            Matcher matcher7 = Pattern.compile("(?i:^/FORUM/DL/POST/(\\d+)(/([^/]+))?)").matcher(d);
                            if (matcher7.matches()) {
                                int i10 = m674i(matcher7.group(1));
                                if (i10 != 0) {
                                    if (!z) {
                                        String group2 = matcher7.group(3);
                                        DlgSimple q1Var = new DlgSimple(mainActivity, String.format("Вы хотите скачать файл #%d \"%s\"?", Integer.valueOf(i10), group2), false, null, null);
                                        q1Var.promtMessage.setTextSize(0, q1Var.editText.getTextSize());
                                        q1Var.editText.setVisibility(8);
                                        q1Var.m620f(new View$OnClickListenerC0407a(i10, mainActivity, group2), true);
                                        q1Var.show(true, true, true);
                                    }
                                    return null;
                                }
                                i2 = i10;
                            }
                            if (d.startsWith("/forum/lofiversion/")) {
                                if (!TextUtils.isEmpty(encodedQuery)) {
                                    Matcher matcher8 = Pattern.compile("(?i:^t(\\d+)(-(\\d+)?)?).*").matcher(encodedQuery);
                                    if (matcher8.matches()) {
                                        return new Page_Topic(mainActivity, m674i(matcher8.group(1)), m674i(matcher8.group(3)));
                                    }
                                    Matcher matcher9 = Pattern.compile("(?i:^f(\\d+)(-(\\d+)?)?).*").matcher(encodedQuery);
                                    if (matcher9.matches()) {
                                        return new Page_Forum(mainActivity, m674i(matcher9.group(1)), m674i(matcher9.group(3)), "");
                                    }
                                }
                                return new Page_Forums(mainActivity);
                            }
                            String actionType = (String) treeMap.get("act");
                            if (actionType != null) {
                                if (actionType.equalsIgnoreCase("st")) {
                                    i2 = m674i((String) treeMap.get("t"));
                                    z2 = i2 != 0;
                                    z3 = false;
                                    if (z3 && (z2 || (actionType = (String) treeMap.get("showtopic")) != null)) {
                                        int topicId = !z2 ? m674i(actionType) : i2;
                                        String str11 = (String) treeMap.get("view");
                                        if (str11 == null || z) {
                                            int i12 = Prefs.showAllPost ? 3 : 0;
                                            String str12 = (String) treeMap.get("modfilter");
                                            if (str12 != null) {
                                                if ("invisible-posts".equals(str12)) {
                                                    i12 = 1;
                                                } else if ("deleted-posts".equals(str12)) {
                                                    i12 = 2;
                                                } else if ("all-posts".equals(str12)) {
                                                    i12 = 3;
                                                } else if ("regular-posts".equals(str12)) {
                                                    i12 = 4;
                                                }
                                            }
                                            int page = m674i((String) treeMap.get("st"));
                                            int maxPageOnScreen = Prefs.memberPostsPerPage;
                                            return new Page_Topic(mainActivity, topicId, (page / maxPageOnScreen) * maxPageOnScreen, i12, 0, null, "");
                                        }
                                        if (str11.equalsIgnoreCase("findpost")) {
                                            API.ForumTopicPostRequest lVar = new API.ForumTopicPostRequest(mainActivity, 3, m674i((String) treeMap.get("p")));
                                            lVar.m825t(topicId);
                                            lVar.m829p((String) treeMap.get("anchor"));
                                            lVar.m824u((i & 1) != 0 ? mainActivity.mainLayout.tab : null);
                                            lVar.m826s((i & 2) != 0);
                                            lVar.m828q((i & 4) != 0);
                                            DocumentManager.getResultRequest(lVar);
                                        } else if (str11.equalsIgnoreCase("getnewpost")) {
                                            API.ForumTopicPostRequest lVar2 = new API.ForumTopicPostRequest(mainActivity, 1, topicId);
                                            lVar2.m824u((i & 1) != 0 ? mainActivity.mainLayout.tab : null);
                                            lVar2.m826s((i & 2) != 0);
                                            lVar2.m828q((i & 4) != 0);
                                            DocumentManager.getResultRequest(lVar2);
                                        } else if (str11.equalsIgnoreCase("getlastpost")) {
                                            API.ForumTopicPostRequest lVar3 = new API.ForumTopicPostRequest(mainActivity, 2, topicId);
                                            lVar3.m824u((i & 1) != 0 ? mainActivity.mainLayout.tab : null);
                                            lVar3.m826s((i & 2) != 0);
                                            lVar3.m828q((i & 4) != 0);
                                            DocumentManager.getResultRequest(lVar3);
                                        }
                                        return null;
                                    } else if (!z3 || (actionType = (String) treeMap.get("showforum")) != null) {
                                        if (!z3) {
                                            i2 = m674i(actionType);
                                        }
                                        int i15 = m674i((String) treeMap.get("st"));
                                        int i16 = Prefs.memberTopicsPerPage;
                                        return new Page_Forum(mainActivity, i2, (i15 / i16) * i16, null);
                                    } else {
                                        String str13 = (String) treeMap.get("showuser");
                                        if (str13 != null) {
                                            return new Page_Profile(mainActivity, m674i(str13), 0);
                                        }
                                    }
                                } else if (actionType.equalsIgnoreCase("sf")) {
                                    i2 = m674i((String) treeMap.get("f"));
                                    if (i2 != 0) {
                                        z3 = true;
                                        z2 = false;
                                        if (z3) {
                                        }
                                        if (!z3) {
                                        }
                                        if (!z3) {
                                        }
                                        int i152 = m674i((String) treeMap.get("st"));
                                        int i162 = Prefs.memberTopicsPerPage;
                                        return new Page_Forum(mainActivity, i2, (i152 / i162) * i162, null);
                                    }
                                } else if (actionType.equalsIgnoreCase("idx")) {
                                    return new Page_Forums(mainActivity);
                                } else {
                                    if (actionType.equalsIgnoreCase("findpost")) {
                                        if (!z) {
                                            API.ForumTopicPostRequest lVar4 = new API.ForumTopicPostRequest(mainActivity, 3, m674i((String) treeMap.get("pid")));
                                            lVar4.m829p((String) treeMap.get("anchor"));
                                            lVar4.m824u((i & 1) != 0 ? mainActivity.mainLayout.tab : null);
                                            lVar4.m826s((i & 2) != 0);
                                            lVar4.m828q((i & 4) != 0);
                                            DocumentManager.getResultRequest(lVar4);
                                        }
                                        return null;
                                    } else if (actionType.equalsIgnoreCase("qms")) {
                                        String str14 = (String) treeMap.get("t");
                                        if (str14 == null || (i8 = m674i(str14)) == 0) {
                                            String str15 = (String) treeMap.get("mid");
                                            if (!(str15 == null || (i7 = m674i(str15)) == 0)) {
                                                return new Page_QMS_List(mainActivity, i7);
                                            }
                                            String str16 = (String) treeMap.get("search");
                                            if (!TextUtils.isEmpty(str16)) {
                                                return new Page_QMS_List(mainActivity, str16);
                                            }
                                            return new Page_QMS_List(mainActivity);
                                        }
                                        String str17 = (String) treeMap.get("search");
                                        if (!TextUtils.isEmpty(str17)) {
                                            return new Page_QMS_Talk(mainActivity, i8, str17);
                                        }
                                        return new Page_QMS_Talk(mainActivity, i8);
                                    } else if (actionType.equalsIgnoreCase("search") && treeMap.get("ip_address") == null) {
                                        String str18 = (String) treeMap.get("source");
                                        if (str18 != null) {
                                            if (str18.equalsIgnoreCase("all")) {
                                                i4 = 3;
                                            } else if (str18.equalsIgnoreCase("top")) {
                                                i4 = 2;
                                            } else if (str18.equalsIgnoreCase("pst")) {
                                                i4 = 1;
                                            }
                                            str = (String) treeMap.get("site");
                                            if (!(str == null || m674i(str) == 0)) {
                                                i4 |= 4;
                                            }
                                            if ("topics".equalsIgnoreCase((String) treeMap.get("result"))) {
                                                i4 |= 65536;
                                            }
                                            str2 = (String) treeMap.get("sort");
                                            if (str2 != null) {
                                                if (str2.equalsIgnoreCase("dd")) {
                                                    i4 |= 524288;
                                                } else if (str2.equalsIgnoreCase("da")) {
                                                    i4 |= 1572864;
                                                }
                                            }
                                            str3 = (String) treeMap.get("subforums");
                                            if (!(str3 == null || m674i(str3) == 0)) {
                                                i4 |= 131072;
                                            }
                                            str4 = (String) treeMap.get("nohl");
                                            if (!(str4 == null || m674i(str4) == 0)) {
                                                i4 |= 262144;
                                            }
                                            Object[] objArr = new Object[1];
                                            str5 = (String) treeMap.get("author_id");
                                            if (str5 == null && (str5 = (String) treeMap.get("username-id")) == null) {
                                                obj = (String) treeMap.get("username");
                                                if (obj == null) {
                                                    c = 0;
                                                    i5 = 0;
                                                    objArr[c] = i5;
                                                    uVar = new Document(objArr);
                                                    if (uVar.count() > 0) {
                                                        if ((i4 & 524288) == 0) {
                                                            i4 |= 524288;
                                                        }
                                                        if ((i4 & 3) == 0) {
                                                            i6 = i4 | 1;
                                                            uVar2 = new Document();
                                                            str6 = (String) treeMap.get("forums");
                                                            if (str6 == null) {
                                                                str6 = (String) treeMap.get("forums[]");
                                                            }
                                                            if (str6 != null) {
                                                                for (String str19 : str6.split(",")) {
                                                                    int i17 = m674i(str19);
                                                                    if (i17 != 0) {
                                                                        uVar2.append(Integer.valueOf(i17));
                                                                    }
                                                                }
                                                            }
                                                            if (uVar2.count() == 0) {
                                                                uVar2.append(0);
                                                            }
                                                            uVar3 = new Document();
                                                            str7 = (String) treeMap.get("topics");
                                                            if (str7 == null) {
                                                                str7 = (String) treeMap.get("topics[]");
                                                            }
                                                            if (str7 != null) {
                                                                for (String str20 : str7.split(",")) {
                                                                    int i18 = m674i(str20);
                                                                    if (i18 != 0) {
                                                                        uVar3.append(Integer.valueOf(i18));
                                                                    }
                                                                }
                                                            }
                                                            if (uVar3.count() == 0) {
                                                                uVar3.append(0);
                                                            }
                                                            return new Page_Search(mainActivity, i6, uVar2, uVar3, uVar, (String) treeMap.get("query"), 0, "");
                                                        }
                                                    }
                                                    i6 = i4;
                                                    uVar2 = new Document();
                                                    str6 = (String) treeMap.get("forums");
                                                    if (str6 == null) {
                                                    }
                                                    if (str6 != null) {
                                                    }
                                                    if (uVar2.count() == 0) {
                                                    }
                                                    uVar3 = new Document();
                                                    str7 = (String) treeMap.get("topics");
                                                    if (str7 == null) {
                                                    }
                                                    if (str7 != null) {
                                                    }
                                                    if (uVar3.count() == 0) {
                                                    }
                                                    return new Page_Search(mainActivity, i6, uVar2, uVar3, uVar, (String) treeMap.get("query"), 0, "");
                                                }
                                            } else {
                                                obj = m674i(str5);
                                            }
                                            i5 = (int) obj;
                                            c = 0;
                                            objArr[c] = i5;
                                            uVar = new Document(objArr);
                                            if (uVar.count() > 0) {
                                            }
                                            i6 = i4;
                                            uVar2 = new Document();
                                            str6 = (String) treeMap.get("forums");
                                            if (str6 == null) {
                                            }
                                            if (str6 != null) {
                                            }
                                            if (uVar2.count() == 0) {
                                            }
                                            uVar3 = new Document();
                                            str7 = (String) treeMap.get("topics");
                                            if (str7 == null) {
                                            }
                                            if (str7 != null) {
                                            }
                                            if (uVar3.count() == 0) {
                                            }
                                            return new Page_Search(mainActivity, i6, uVar2, uVar3, uVar, (String) treeMap.get("query"), 0, "");
                                        }
                                        i4 = 0;
                                        str = (String) treeMap.get("site");
                                        if (str == null) {
                                            i4 |= 4;
                                        }
                                        if ("topics".equalsIgnoreCase((String) treeMap.get("result"))) {
                                        }
                                        str2 = (String) treeMap.get("sort");
                                        if (str2 != null) {
                                        }
                                        str3 = (String) treeMap.get("subforums");
                                        if (str3 == null) {
                                            i4 |= 131072;
                                        }
                                        str4 = (String) treeMap.get("nohl");
                                        if (str4 == null) {
                                            i4 |= 262144;
                                        }
                                        Object[] objArr2 = new Object[1];
                                        str5 = (String) treeMap.get("author_id");
                                        if (str5 == null) {
                                            obj = (String) treeMap.get("username");
                                            if (obj == null) {
                                            }
                                            i5 = (int) obj;
                                            c = 0;
                                            objArr2[c] = i5;
                                            uVar = new Document(objArr2);
                                            if (uVar.count() > 0) {
                                            }
                                            i6 = i4;
                                            uVar2 = new Document();
                                            str6 = (String) treeMap.get("forums");
                                            if (str6 == null) {
                                            }
                                            if (str6 != null) {
                                            }
                                            if (uVar2.count() == 0) {
                                            }
                                            uVar3 = new Document();
                                            str7 = (String) treeMap.get("topics");
                                            if (str7 == null) {
                                            }
                                            if (str7 != null) {
                                            }
                                            if (uVar3.count() == 0) {
                                            }
                                            return new Page_Search(mainActivity, i6, uVar2, uVar3, uVar, (String) treeMap.get("query"), 0, "");
                                        }
                                        obj = Integer.valueOf(m674i(str5));
                                        i5 = (int) obj;
                                        c = 0;
                                        objArr2[c] = i5;
                                        uVar = new Document(objArr2);
                                        if (uVar.count() > 0) {
                                        }
                                        i6 = i4;
                                        uVar2 = new Document();
                                        str6 = (String) treeMap.get("forums");
                                        if (str6 == null) {
                                        }
                                        if (str6 != null) {
                                        }
                                        if (uVar2.count() == 0) {
                                        }
                                        uVar3 = new Document();
                                        str7 = (String) treeMap.get("topics");
                                        if (str7 == null) {
                                        }
                                        if (str7 != null) {
                                        }
                                        if (uVar3.count() == 0) {
                                        }
                                        return new Page_Search(mainActivity, i6, uVar2, uVar3, uVar, (String) treeMap.get("query"), 0, "");
                                    } else if (actionType.equalsIgnoreCase("announce")) {
                                        actionType = (String) treeMap.get("id");
                                        if (actionType == null) {
                                            actionType = (String) treeMap.get("st");
                                        }
                                        if (actionType != null) {
                                            return new Page_Announcement(mainActivity, m674i(actionType));
                                        }
                                    } else if (actionType.equalsIgnoreCase("boardrules")) {
                                        return new Page_Announcement(mainActivity, 0);
                                    } else {
                                        if (actionType.equalsIgnoreCase("auth")) {
                                            if (z) {
                                                return new Page_Login(mainActivity, false);
                                            }
                                            actionType = (String) treeMap.get("action");
                                            if ("validate".equalsIgnoreCase(actionType)) {
                                                actionType = (String) treeMap.get("type");
                                                if ("newemail".equalsIgnoreCase(actionType)) {
                                                    if (DocumentManager.isLoggined()) {
                                                        DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(mainActivity, 3, m674i((String) treeMap.get("m")), m674i((String) treeMap.get("t")), (String) treeMap.get("s"), (String) treeMap.get("p")));
                                                    } else {
                                                        Toast.makeText(mainActivity, "Вы должны быть авторизованы.", Toast.LENGTH_LONG).show();
                                                    }
                                                    return null;
                                                } else if ("lostpass".equalsIgnoreCase(actionType)) {
                                                    if (!DocumentManager.isLoggined()) {
                                                        ViewGroup viewGroup = (ViewGroup) mainActivity.getLayoutInflater().inflate(R.layout.dlg_set_pass, (ViewGroup) null);
                                                        EditText editText = (EditText) viewGroup.findViewById(R.id.set_pass_new);
                                                        EditText editText2 = (EditText) viewGroup.findViewById(R.id.set_pass_new2);
                                                        CustomDialog l1Var = new CustomDialog(mainActivity, viewGroup, "СОХРАНИТЬ", null);
                                                        l1Var.m625a(false);
                                                        C0408b bVar = new C0408b(l1Var, editText, editText2);
                                                        editText.addTextChangedListener(bVar);
                                                        editText2.addTextChangedListener(bVar);
                                                        l1Var.m620f(new View$OnClickListenerC0409c(editText, editText2, mainActivity, treeMap), true);
                                                        l1Var.show(true, true, true);
                                                    } else {
                                                        Toast.makeText(mainActivity, "Вы должны выйти из аккаунта", Toast.LENGTH_LONG).show();
                                                    }
                                                    return null;
                                                } else if ("reg".equalsIgnoreCase(actionType)) {
                                                    if (DocumentManager.isLoggined()) {
                                                        DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(mainActivity, 1, m674i((String) treeMap.get("m")), m674i((String) treeMap.get("t")), (String) treeMap.get("s"), ""));
                                                    } else {
                                                        Toast.makeText(mainActivity, "Вы должны быть авторизованы.", Toast.LENGTH_LONG).show();
                                                    }
                                                    return null;
                                                }
                                            } else if ("lostpass".equalsIgnoreCase(actionType)) {
                                                DocumentManager.initAuthenticate();
                                                return new Page_Login(mainActivity, true);
                                            } else if ("lostpass-2".equalsIgnoreCase(actionType)) {
                                                DocumentManager.initAuthenticate();
                                                return new Page_Login(mainActivity, true);
                                            } else if ("regrevalidate".equalsIgnoreCase(actionType)) {
                                                DocumentManager.getResultRequest(new MainActivity.MemberAuthorizationRequest(mainActivity, 0, 0, 0, "", ""));
                                                return null;
                                            } else if ("registration".equalsIgnoreCase(actionType)) {
                                                DocumentManager.initAuthenticate();
                                                return new Page_PreReg(mainActivity);
                                            } else if ("logout".equalsIgnoreCase(actionType)) {
                                                DocumentManager.initAuthenticate();
                                                return new Page_Login(mainActivity, false);
                                            }
                                        } else if (actionType.equalsIgnoreCase("rep")) {
                                            int memberId = m674i((String) treeMap.get("mid"));
                                            if (memberId != 0) {
                                                actionType = (String) treeMap.get("type");
                                                if (TextUtils.isEmpty(actionType)) {
                                                    actionType = (String) treeMap.get("view");
                                                }
                                                if (!TextUtils.isEmpty(actionType)) {
                                                    if (actionType.equalsIgnoreCase("win_add") || actionType.equalsIgnoreCase("win_minus")) {
                                                        if (!z) {
                                                            int i20 = m674i((String) treeMap.get("p"));
                                                            boolean equalsIgnoreCase = actionType.equalsIgnoreCase("win_add");
                                                            DlgSimple q1Var2 = new DlgSimple(mainActivity, equalsIgnoreCase ? "Укажите причину повышения репутации" : "Укажите причину понижения репутации", false, equalsIgnoreCase ? "ПОВЫСИТЬ" : "ПОНИЗИТЬ", null);
                                                            String str21 = (String) treeMap.get("message");
                                                            if (TextUtils.isEmpty(str21)) {
                                                                q1Var2.m625a(false);
                                                            } else {
                                                                q1Var2.editText.setText(Util.C0427h.UnEscapeString(str21));
                                                            }
                                                            q1Var2.editText.addTextChangedListener(new C0410d(q1Var2));
                                                            q1Var2.m620f(new View$OnClickListenerC0411e(mainActivity, memberId, equalsIgnoreCase, i20, q1Var2), true);
                                                            q1Var2.show(true, true, true);
                                                            mainActivity.mainLayout.hideKeyboard(q1Var2.editText);
                                                            DocumentManager.getResultRequest(new ChangeReputationRequest(memberId, q1Var2));
                                                        }
                                                        return null;
                                                    }
                                                    int i21 = "from".equalsIgnoreCase((String) treeMap.get("mode")) ? 3 : 2;
                                                    String fragment = uri.getFragment();
                                                    return new Page_Reputation(mainActivity, memberId, i21, (fragment == null || !fragment.startsWith("rep-row-")) ? 0 : m674i(fragment.substring(8)));
                                                }
                                            }
                                            i2 = memberId;
                                        } else if (actionType.equalsIgnoreCase("ticket")) {
                                            String str22 = (String) treeMap.get("t_id");
                                            if (str22 != null) {
                                                return new Page_Ticket(mainActivity, m674i(str22));
                                            }
                                            String str23 = (String) treeMap.get("st");
                                            int i22 = str23 != null ? m674i(str23) : 0;
                                            String str24 = (String) treeMap.get("only-topic");
                                            return new Page_Ticket(mainActivity, i22, str24 != null ? m674i(str24) : 0, null);
                                        } else if (actionType.equalsIgnoreCase("fav")) {
                                            return new Page_Favorites(mainActivity, 0);
                                        } else {
                                            if (actionType.equalsIgnoreCase("mentions")) {
                                                return new Page_Mention(mainActivity, 0);
                                            }
                                            if (actionType.equalsIgnoreCase("history")) {
                                                return new Page_History(mainActivity, 0);
                                            }
                                            if (actionType.equalsIgnoreCase("app-options")) {
                                                return new Page_Options(mainActivity);
                                            }
                                            if (actionType.equalsIgnoreCase("app-skins")) {
                                             //   return new Page_Skins(mainActivity);
                                            }
                                            if (actionType.equalsIgnoreCase("zmod") && "update-pinned".equalsIgnoreCase((String) treeMap.get("CODE"))) {
                                                if (!z) {
                                                    API.ForumModifyRequest.modifyForum(m674i((String) treeMap.get("p")), null, 6, 0, 0, mainActivity.mainLayout.tab.page, "поднятие поста в шапку", "ПОДНЯТЬ", new RunnableC0413g(treeMap, mainActivity));
                                                }
                                                return null;
                                            }
                                        }
                                    }
                                }
                            }
                            z3 = false;
                            z2 = false;
                            if (z3) {
                            }
                            if (!z3) {
                            }
                            if (!z3) {
                            }
                            int i1522 = m674i((String) treeMap.get("st"));
                            int i1622 = Prefs.memberTopicsPerPage;
                            return new Page_Forum(mainActivity, i2, (i1522 / i1622) * i1622, null);
                        }
                    }
                } else {
                    String group3 = matcher2.group(1);
                    String group4 = matcher2.group(2);
                    if (matcher2.group(3) != null && matcher2.group(3).equalsIgnoreCase("/ALL")) {
                        z4 = true;
                    }
                    return new Page_Vendor(mainActivity, group3, group4, z4);
                }
            }
        }
        if (!z) {
            visitPage(mainActivity, uri);
        }
        return null;
    }

    public static Page openPage(MainActivity mainActivity, String link, boolean z, int i) {
        Uri uri;
        if (!TextUtils.isEmpty(link) && (uri = Uri.parse(Util.C0427h.UnEscapeString(link))) != null) {
            return openPage(mainActivity, uri, z, i);
        }
        return null;
    }

    static boolean is4pdaHost(Uri uri) {
        String host = uri != null ? uri.getHost() : null;
        return host != null && host.equalsIgnoreCase("4pda.ru");
    }

    public static boolean is4pdaHost(String str) {
        return is4pdaHost(Uri.parse(str));
    }

    public static boolean isEmailAddress(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    static void visitPage(Context context, Uri uri) {
        if (DataDB.containsTrackUrls(uri.toString().toLowerCase())) {
            DocumentManager.getResultRequest(new API.ForumLoginAnonymous(uri, context));
            return;
        }
        String host = uri.getHost();
        while (host != null) {
            if (!DataDB.containsTrackUrls(host)) {
                int indexOf = host.indexOf(46);
                if (indexOf < 0) {
                    break;
                }
                host = host.substring(indexOf + 1);
            } else {
                DocumentManager.getResultRequest(new API.ForumLoginAnonymous(uri, context));
                return;
            }
        }
        m675h(context, uri);
    }

    public static void visitPage(Context context, String str) {
        Uri parse;
        if (!TextUtils.isEmpty(str) && (parse = Uri.parse(Util.C0427h.UnEscapeString(str))) != null) {
            visitPage(context, parse);
        }
    }

    public static void m675h(Context context, Uri uri) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", uri), 0);
        Vector vector = new Vector();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (!str.equalsIgnoreCase(context.getPackageName())) {
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.setPackage(str);
                vector.add(intent);
            }
        }
        if (vector.size() > 0) {
            Intent createChooser = Intent.createChooser((Intent) vector.get(0), "Открыть с помощью");
            vector.remove(0);
            if (vector.size() > 0) {
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) vector.toArray(new Parcelable[0]));
            }
            context.startActivity(createChooser);
            return;
        }
        Toast.makeText(context, "Не нашлось подходящих приложений\n" + uri.toString(), Toast.LENGTH_SHORT).show();
    }

    public static int m674i(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return NumberFormat.getInstance().parse(str).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
