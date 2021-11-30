package ru.fourpda.client;

public class MemberLoginRequest extends DocumentManager.IGenerateRequest {
    String memberLogin;
    String memberPassword;
    boolean isHidden;
    int captcha;

    public MemberLoginRequest(String memberLogin, String memberPassword, boolean isHidden) {
        super(27757);
        this.memberLogin = memberLogin;
        this.memberPassword = memberPassword;
        this.isHidden = isHidden;
    }

    @Override
    public Document generate() {
        return new Document(this.memberLogin, this.memberPassword, this.isHidden ? 1 : 0, this.captcha);
    }
}
