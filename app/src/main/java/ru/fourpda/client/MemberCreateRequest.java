package ru.fourpda.client;

class MemberCreateRequest extends DocumentManager.IGenerateRequest {
    String name;
    String email;
    String password;
    int captcha;

    public MemberCreateRequest(String regName, String reqEmail, String reqPassword) {
        super(25453);
        this.name = regName;
        this.email = reqEmail;
        this.password = reqPassword;
        this.statusMessage = "Регистрация";
    }

    @Override
    Document generate() {
        return new Document(this.name, this.email, this.password, this.captcha);
    }
}
