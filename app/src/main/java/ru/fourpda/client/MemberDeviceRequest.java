package ru.fourpda.client;

public class MemberDeviceRequest extends DocumentManager.IGenerateRequest {
    int id;
    int code;
    String fullTag;
    boolean primary;

    public MemberDeviceRequest(int id, int code, String fullTag, boolean primary) {
        super(25709);
        this.id = id;
        this.code = code;
        this.fullTag = fullTag;
        this.primary = primary;
    }

    @Override
    public Document generate() {
        return new Document(this.id, this.code, this.fullTag, this.primary ? 1 : 0);
    }
}
