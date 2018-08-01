package com.arimac.projectManegement.model;

import java.io.Serializable;

public class ResponceLogin implements Serializable {

    private String userId;
    private String token;
    private String secretKey;
    private boolean isSuccess;
    private String desc;

    public ResponceLogin() {
    }

    public ResponceLogin(String userId, String token, String secretKey, boolean isSuccess, String desc) {
        this.userId = userId;
        this.token = token;
        this.secretKey = secretKey;
        this.isSuccess = isSuccess;
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
