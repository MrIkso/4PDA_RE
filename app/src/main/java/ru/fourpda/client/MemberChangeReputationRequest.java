package ru.fourpda.client;

import android.content.Context;
import android.widget.Toast;

class MemberChangeReputationRequest extends DocumentManager.IGenerateRequest {
    Context context;
    int userId;
    int reputationCode;
    int postId;
    String reputationText;

    /**
     *
     * @param context
     * @param userId ид пользователя, которому нужно изменить репутацию
     * @param reputationCode код репутации 0 - понизить, 1 - повысить, 4 - удалить, 5 - востоновить
     * @param postId ид поста за который меняется репутация
     * @param reputationText причина изменения репутации
     */
    public MemberChangeReputationRequest(Context context, int userId, int reputationCode, int postId, String reputationText) {
        super(29293);
        this.context = context;
        this.userId = userId;
        this.reputationCode = reputationCode;
        this.postId = postId;
        this.reputationText = reputationText;
        this.statusMessage = "Изменение репутации";
    }

    @Override
    public void prepareResult(int status, Document document) {
        if (status == 0) {
            Toast.makeText(this.context, "Репутация изменена", 0).show();
        } else if (status <= 3) {
            Toast.makeText(this.context, "Ошибка изменения репутации", 1).show();
        } else if (status == 4) {
            Toast.makeText(this.context, "Изменение репутации заблокировано", 1).show();
        } else if (status == 5) {
            Toast.makeText(this.context, "Вы не можете менять репутацию самому себе", 1).show();
        } else if (status == 6) {
            Toast.makeText(this.context, "У вас недостаточно постов, чтобы менять репутацию", 1).show();
        } else if (status == 7) {
            Toast.makeText(this.context, "У вас слишком низкая репутация", 1).show();
        } else if (status == 8) {
            Toast.makeText(this.context, "Вы больше не можете менять репутацию сегодня", 1).show();
        } else if (status == 9) {
            Toast.makeText(this.context, "Вы больше не можете менять репутацию этому человеку сегодня", 1).show();
        } else if (status == 10) {
            Toast.makeText(this.context, "Вы больше не можете менять репутацию за этот пост", 1).show();
        } else if (status == 11) {
            Toast.makeText(this.context, "В данный момент вы не можете изменить репутацию этому человеку", 1).show();
        } else if (status == 12) {
            Toast.makeText(this.context, "Изменение отклонено, т.к. вы недавно ставили минус этому человеку.", 1).show();
        } else if (status == 13) {
            Toast.makeText(this.context, "Изменение отклонено, т.к. он недавно поставил вам минус", 1).show();
        }
    }

    @Override
    Document generate() {
        return new Document(this.userId, this.reputationCode, this.postId, this.reputationText);
    }
}
