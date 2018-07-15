package com.tang.testdemo.bean;

import java.io.Serializable;
import java.util.List;

public class HomeItemBean implements Serializable {

    public static final int TYPE_CAROUSEL = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_SKU = 2;

    private int viewType;//视图类型 0：轮播图；1：item列表；2：商品列表

    private HomeSku homeSku;//首页商品

    private List<HomePicture> homePictureList;//轮播图

    private List<HomeItem> homeItemList;//中间item列表

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public HomeSku getHomeSku() {
        return homeSku;
    }

    public void setHomeSku(HomeSku homeSku) {
        this.homeSku = homeSku;
    }

    public List<HomePicture> getHomePictureList() {
        return homePictureList;
    }

    public void setHomePictureList(List<HomePicture> homePictureList) {
        this.homePictureList = homePictureList;
    }

    public List<HomeItem> getHomeItemList() {
        return homeItemList;
    }

    public void setHomeItemList(List<HomeItem> homeItemList) {
        this.homeItemList = homeItemList;
    }

    @Override
    public String toString() {
        return "HomeItemBean{" +
                "viewType=" + viewType +
                ", homeSku=" + homeSku +
                ", homePictureList=" + homePictureList +
                ", homeItemList=" + homeItemList +
                '}';
    }
}
