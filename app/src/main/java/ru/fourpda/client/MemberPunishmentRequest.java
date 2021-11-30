package ru.fourpda.client;

import android.widget.Toast;

class MemberPunishmentRequest extends DocumentManager.IGenerateRequest {
    MainActivity mainActivity;
    int userId;
    String textForCancelPunishment;

    public MemberPunishmentRequest(MainActivity mainActivity, int userId, String textForCancelPunishment) {
        super(30573);
        this.mainActivity = mainActivity;
        this.userId = userId;
        this.textForCancelPunishment = textForCancelPunishment;
        this.statusMessage = "Отмена наказания";
    }

    @Override
    void prepareResult(int status, Document uVar) {
        if (status == 0) {
            Toast.makeText(this.mainActivity, "Наказание отменено", 1).show();
            this.mainActivity.mainLayout.tab.page.tabReload();
        } else if (status == 5) {
            Toast.makeText(this.mainActivity, "Не указана причина", 1).show();
        } else {
            Toast.makeText(this.mainActivity, "Нет доступа", 1).show();
        }
    }

    @Override
    public Document generate() {
        return new Document(this.userId, 4, 0, this.textForCancelPunishment);
    }
}
