package ru.fourpda.client;

public class MemberSessionsRequest extends DocumentManager.IGenerateRequest {
    int pageID;
    int pageType;

    public MemberSessionsRequest(int pageType, int pageID) {
        super(25965);
        this.pageType = pageType;
        this.pageID = pageID;
    }

    @Override
    public Document generate() {
        return new Document(pageType == 0 ? "topic" : pageType == 1 ? "forum" : pageType == 2 ? "idx" : null, this.pageID);
    }
}
