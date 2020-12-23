package com.frz.korearbazar.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class TokenResponse {

    @SerializedName("ResponseCode")
    private String mResponseCode;
    @SerializedName("ResponseMsg")
    private String mResponseMsg;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("access_type")
    private String access_type;

    @SerializedName("email")
    private String email;

    public TokenResponse() {
    }

    public String getmResponseCode() {
        return mResponseCode;
    }

    public void setmResponseCode(String mResponseCode) {
        this.mResponseCode = mResponseCode;
    }

    public String getmResponseMsg() {
        return mResponseMsg;
    }

    public void setmResponseMsg(String mResponseMsg) {
        this.mResponseMsg = mResponseMsg;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getAccess_type() {
        return access_type;
    }

    public String getEmail() {
        return email;
    }
}
