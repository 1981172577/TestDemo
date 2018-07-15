package com.tang.testdemo.bean;

public class HomePicture {
    private String imgPath;
    private String clickUrl;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    @Override
    public String toString() {
        return "HomePicture{" +
                "imgPath='" + imgPath + '\'' +
                ", clickUrl='" + clickUrl + '\'' +
                '}';
    }
}
