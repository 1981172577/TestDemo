package com.tang.testdemo.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

    public static final String LOGIN_KEY = "login_account";

    private long vnedorId;

    private String storeCode;

    private String token;

    private String mempin;

    private String phoneNo;

    private String account;

    private String memNo;

    private int islogined;

    private String User;

    private String fristCheck;

    private String errorMsg;

    public LoginBean(){}

    private LoginBean(Builder builder) {
        setVnedorId(builder.vnedorId);
        setStoreCode(builder.storeCode);
        setToken(builder.token);
        setMempin(builder.mempin);
        setPhoneNo(builder.phoneNo);
        setAccount(builder.account);
        setMemNo(builder.memNo);
        setIslogined(builder.islogined);
        setUser(builder.User);
        setFristCheck(builder.fristCheck);
        setErrorMsg(builder.errorMsg);
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public long getVnedorId() {
        return vnedorId;
    }

    public void setVnedorId(long vnedorId) {
        this.vnedorId = vnedorId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMempin() {
        return mempin;
    }

    public void setMempin(String mempin) {
        this.mempin = mempin;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public int getIslogined() {
        return islogined;
    }

    public void setIslogined(int islogined) {
        this.islogined = islogined;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getFristCheck() {
        return fristCheck;
    }

    public void setFristCheck(String fristCheck) {
        this.fristCheck = fristCheck;
    }


    @Override
    public String toString() {
        return "LoginBean{" +
                "vnedorId=" + vnedorId +
                ", storeCode='" + storeCode + '\'' +
                ", token='" + token + '\'' +
                ", mempin='" + mempin + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", account='" + account + '\'' +
                ", memNo='" + memNo + '\'' +
                ", islogined=" + islogined +
                ", User='" + User + '\'' +
                ", fristCheck='" + fristCheck + '\'' +
                '}';
    }


    public static final class Builder {
        private long vnedorId;
        private String storeCode;
        private String token;
        private String mempin;
        private String phoneNo;
        private String account;
        private String memNo;
        private int islogined;
        private String User;
        private String fristCheck;
        private String errorMsg;

        public Builder() {
        }

        public Builder vnedorId(long val) {
            vnedorId = val;
            return this;
        }

        public Builder storeCode(String val) {
            storeCode = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder mempin(String val) {
            mempin = val;
            return this;
        }

        public Builder phoneNo(String val) {
            phoneNo = val;
            return this;
        }

        public Builder account(String val) {
            account = val;
            return this;
        }

        public Builder memNo(String val) {
            memNo = val;
            return this;
        }

        public Builder islogined(int val) {
            islogined = val;
            return this;
        }

        public Builder User(String val) {
            User = val;
            return this;
        }

        public Builder fristCheck(String val) {
            fristCheck = val;
            return this;
        }

        public Builder errorMsg(String val) {
            errorMsg = val;
            return this;
        }

        public LoginBean build() {
            return new LoginBean(this);
        }
    }
}
