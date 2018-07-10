package com.tang.testdemo.bean;

import java.io.Serializable;

public class User implements Serializable {

    private long vnedorId;

    private String token;

    private String memPin;

    private String phoneNo;

    private String account;

    private String memNo;

    private int islogined;

    private String appId;

    private String shopMan;

    private String majorAccount;

    private String storeCode;

    private String storeName;

    private String fristCheck;

    private String logoPath;

    private String shopImgUrl;

    private String errorMsg;

    public User(){}

    private User(Builder builder) {
        setVnedorId(builder.vnedorId);
        setToken(builder.token);
        setMemPin(builder.memPin);
        setPhoneNo(builder.phoneNo);
        setAccount(builder.account);
        setMemNo(builder.memNo);
        setIslogined(builder.islogined);
        setAppId(builder.appId);
        setShopMan(builder.shopMan);
        setMajorAccount(builder.majorAccount);
        setStoreCode(builder.storeCode);
        setStoreName(builder.storeName);
        setFristCheck(builder.fristCheck);
        setLogoPath(builder.logoPath);
        setShopImgUrl(builder.shopImgUrl);
        setErrorMsg(builder.errorMsg);
    }

    public long getVnedorId() {
        return vnedorId;
    }

    public void setVnedorId(long vnedorId) {
        this.vnedorId = vnedorId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMemPin() {
        return memPin;
    }

    public void setMemPin(String memPin) {
        this.memPin = memPin;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getShopMan() {
        return shopMan;
    }

    public void setShopMan(String shopMan) {
        this.shopMan = shopMan;
    }

    public String getMajorAccount() {
        return majorAccount;
    }

    public void setMajorAccount(String majorAccount) {
        this.majorAccount = majorAccount;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getFristCheck() {
        return fristCheck;
    }

    public void setFristCheck(String fristCheck) {
        this.fristCheck = fristCheck;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getShopImgUrl() {
        return shopImgUrl;
    }

    public void setShopImgUrl(String shopImgUrl) {
        this.shopImgUrl = shopImgUrl;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }



    @Override
    public String toString() {
        return "User{" +
                "vnedorId=" + vnedorId +
                ", token='" + token + '\'' +
                ", memPin='" + memPin + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", account='" + account + '\'' +
                ", memNo='" + memNo + '\'' +
                ", islogined=" + islogined +
                ", appId='" + appId + '\'' +
                ", shopMan='" + shopMan + '\'' +
                ", majorAccount='" + majorAccount + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", fristCheck='" + fristCheck + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", shopImgUrl='" + shopImgUrl + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public static final class Builder {
        private long vnedorId;
        private String token;
        private String memPin;
        private String phoneNo;
        private String account;
        private String memNo;
        private int islogined;
        private String appId;
        private String shopMan;
        private String majorAccount;
        private String storeCode;
        private String storeName;
        private String fristCheck;
        private String logoPath;
        private String shopImgUrl;
        private String errorMsg;

        public Builder() {
        }

        public Builder vnedorId(long val) {
            vnedorId = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder memPin(String val) {
            memPin = val;
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

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder shopMan(String val) {
            shopMan = val;
            return this;
        }

        public Builder majorAccount(String val) {
            majorAccount = val;
            return this;
        }

        public Builder storeCode(String val) {
            storeCode = val;
            return this;
        }

        public Builder storeName(String val) {
            storeName = val;
            return this;
        }

        public Builder fristCheck(String val) {
            fristCheck = val;
            return this;
        }

        public Builder logoPath(String val) {
            logoPath = val;
            return this;
        }

        public Builder shopImgUrl(String val) {
            shopImgUrl = val;
            return this;
        }

        public Builder errorMsg(String val) {
            errorMsg = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
