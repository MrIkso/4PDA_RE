package ru.fourpda.client;

public class MemberProfileRequest extends DocumentManager.IGenerateRequest {
    Document memberDocument;

    public MemberProfileRequest(Document memberDocument) {
        super(28781);
        this.memberDocument = memberDocument;
        this.statusMessage = "Сохранение профиля";
    }

    @Override
    Document generate() {
        return this.memberDocument;
    }
}
