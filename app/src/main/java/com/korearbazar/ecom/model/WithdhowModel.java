package com.korearbazar.ecom.model;

public class WithdhowModel {

    private String id;
    private String created_at;
    private String method;
    private String acc_email;
    private String amount;
    private String status;


    public WithdhowModel(String id, String created_at, String method, String acc_email, String amount, String status) {
        this.id = id;
        this.created_at = created_at;
        this.method = method;
        this.acc_email = acc_email;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAcc_email() {
        return acc_email;
    }

    public void setAcc_email(String acc_email) {
        this.acc_email = acc_email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
