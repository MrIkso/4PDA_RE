package ru.fourpda.client;

import android.widget.Toast;

class API_Member extends DocumentManager.IGenerateRequest {
    MainActivity mainActivity;
    int userId;
    String postMessage;

    public API_Member(MainActivity mainActivity, int userId, String postMessage) {
        super(30573);
        this.mainActivity = mainActivity;
        this.userId = userId;
        this.postMessage = postMessage;
        this.statusMessage = "Добавление заметки";
    }

    @Override
    void prepareResult(int status, Document uVar) {
        if (status == 0) {
            Toast.makeText(this.mainActivity, "Заметка добавлена", Toast.LENGTH_SHORT).show();
            this.mainActivity.mainLayout.tab.page.tabReload();
        } else if (status == 5) {
            Toast.makeText(this.mainActivity, "Не указана причина", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.mainActivity, "Нет доступа", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Document generate() {
        return new Document(this.userId, 3, 0, this.postMessage);
    }
}
