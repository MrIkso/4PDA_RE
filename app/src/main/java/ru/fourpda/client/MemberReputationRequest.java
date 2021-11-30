package ru.fourpda.client;

class MemberReputationRequest extends DocumentManager.IGenerateRequest {
    int userId;
    int i4;
    int mode;
    int allVotesCount;

    public MemberReputationRequest(int userId, int mode, int allVotesCount, int i4) {
        super(29293);
        this.userId = userId;
        this.allVotesCount = allVotesCount;
        this.i4 = i4;
        this.mode = mode;
    }

    @Override
    public Document generate() {
        return new Document(this.userId, this.mode, this.allVotesCount, this.i4);
    }
}
