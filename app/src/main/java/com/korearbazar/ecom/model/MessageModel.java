package com.korearbazar.ecom.model;

public class MessageModel {
    private String id;
    private String subject;
    private String message;

    public MessageModel(String id, String subject, String message) {
        this.id = id;
        this.subject = subject;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
