package ru.fourpda.client;

public class MemberInfoRequest extends DocumentManager.IGenerateRequest {
    int memberId;

    public MemberInfoRequest(int memberId) {
        super(26989);
        this.memberId = memberId;
    }

    @Override
    public Document generate() {
        return new Document(this.memberId);
    }
}
