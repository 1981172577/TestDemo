package com.tang.testdemo.bean;

import java.io.Serializable;
import java.util.List;


public class HomeSku implements Serializable {
    private String goodNo;// 商品编号
    private String goodName;// 商品名称
    private String goodImgUrl;// 商品图片
    private int isCollect;// 是否收藏
    private int cartNum;// 件数
    private long stockGoodCount;//库存
    private int localCount;//本地使用
    private int isSelect;//是否勾选
    private String transCode;//运费模版
    private String saleNum;//销量
    private int status; //是否下架   0：上架；1：下架。
    private String barCode;//条码
    private String unit;//单位
    private int unitType;//单位类型
    private String desPath;;//商品详情展示地址
    private String profile;//商品规格
    private int salesStatus;//赠品  0 不打折不赠送 1打折  2 赠送 3 议价高出 4会员折扣
    private double goodPrice;// 商品价格
    private double realPrice;//真实价格（提交订单时使用）
    private String saleUnit;//销售单位
    private String stockunit;//库存单位

    private int fullNumber1;//一级阶梯数量
    private int fullNumber2;//二级阶梯数量
    private int fullNumber3;//三级阶梯数量
    private double ladderPrice1;//一级阶梯价
    private double ladderPrice2;//二级阶梯价
    private double ladderPrice3;//三级阶梯价

    private List<Specification> parameterList;//商品规格列表
    private List<Unit> unitList;//商品多单位列表

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImgUrl() {
        return goodImgUrl;
    }

    public void setGoodImgUrl(String goodImgUrl) {
        this.goodImgUrl = goodImgUrl;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public long getStockGoodCount() {
        return stockGoodCount;
    }

    public void setStockGoodCount(long stockGoodCount) {
        this.stockGoodCount = stockGoodCount;
    }

    public int getLocalCount() {
        return localCount;
    }

    public void setLocalCount(int localCount) {
        this.localCount = localCount;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getDesPath() {
        return desPath;
    }

    public void setDesPath(String desPath) {
        this.desPath = desPath;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(int salesStatus) {
        this.salesStatus = salesStatus;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getStockunit() {
        return stockunit;
    }

    public void setStockunit(String stockunit) {
        this.stockunit = stockunit;
    }

    public int getFullNumber1() {
        return fullNumber1;
    }

    public void setFullNumber1(int fullNumber1) {
        this.fullNumber1 = fullNumber1;
    }

    public int getFullNumber2() {
        return fullNumber2;
    }

    public void setFullNumber2(int fullNumber2) {
        this.fullNumber2 = fullNumber2;
    }

    public int getFullNumber3() {
        return fullNumber3;
    }

    public void setFullNumber3(int fullNumber3) {
        this.fullNumber3 = fullNumber3;
    }

    public double getLadderPrice1() {
        return ladderPrice1;
    }

    public void setLadderPrice1(double ladderPrice1) {
        this.ladderPrice1 = ladderPrice1;
    }

    public double getLadderPrice2() {
        return ladderPrice2;
    }

    public void setLadderPrice2(double ladderPrice2) {
        this.ladderPrice2 = ladderPrice2;
    }

    public double getLadderPrice3() {
        return ladderPrice3;
    }

    public void setLadderPrice3(double ladderPrice3) {
        this.ladderPrice3 = ladderPrice3;
    }

    public List<Specification> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Specification> parameterList) {
        this.parameterList = parameterList;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    @Override
    public String toString() {
        return "HomeSku{" +
                "goodNo='" + goodNo + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodImgUrl='" + goodImgUrl + '\'' +
                ", isCollect=" + isCollect +
                ", cartNum=" + cartNum +
                ", stockGoodCount=" + stockGoodCount +
                ", localCount=" + localCount +
                ", isSelect=" + isSelect +
                ", transCode='" + transCode + '\'' +
                ", saleNum='" + saleNum + '\'' +
                ", status=" + status +
                ", barCode='" + barCode + '\'' +
                ", unit='" + unit + '\'' +
                ", unitType=" + unitType +
                ", desPath='" + desPath + '\'' +
                ", profile='" + profile + '\'' +
                ", salesStatus=" + salesStatus +
                ", goodPrice=" + goodPrice +
                ", realPrice=" + realPrice +
                ", saleUnit='" + saleUnit + '\'' +
                ", stockunit='" + stockunit + '\'' +
                ", fullNumber1=" + fullNumber1 +
                ", fullNumber2=" + fullNumber2 +
                ", fullNumber3=" + fullNumber3 +
                ", ladderPrice1=" + ladderPrice1 +
                ", ladderPrice2=" + ladderPrice2 +
                ", ladderPrice3=" + ladderPrice3 +
                ", parameterList=" + parameterList +
                ", unitList=" + unitList +
                '}';
    }
}
