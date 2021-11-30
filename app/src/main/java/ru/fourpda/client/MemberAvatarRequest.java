package ru.fourpda.client;

public class MemberAvatarRequest extends DocumentManager.IGenerateRequest {
    Document avatarDocument;

    public MemberAvatarRequest(Document avatarDocument) {
        super(30317);
        this.avatarDocument = avatarDocument;
        this.statusMessage = "Сохранение аватара";
    }

    @Override
    Document generate() {
        return this.avatarDocument;
    }
}
