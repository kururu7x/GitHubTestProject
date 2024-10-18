package com.rest.pojo.blob;

public class RootBlob {
    private String content;
    private String encoding;

    public RootBlob() {
    }

    public RootBlob(String content, String encoding) {
        this.content = content;
        this.encoding = encoding;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
