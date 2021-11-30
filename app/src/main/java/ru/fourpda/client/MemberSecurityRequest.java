package ru.fourpda.client;

public class MemberSecurityRequest extends DocumentManager.IGenerateRequest {
    int action;
    int mid;
    int ts;
    String sig;
    String value;

    public MemberSecurityRequest(int action, int mid, int ts, String sig, String value) {
        super(30061);
        this.action = action;
        this.mid = mid;
        this.ts = ts;
        this.sig = sig;
        this.value = value;
    }

    @Override
    public Document generate() {
        return new Document(this.action, this.mid, this.ts, this.sig, this.value);
    }
}
