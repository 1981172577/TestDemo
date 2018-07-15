package com.tang.testdemo.bean;

import java.io.Serializable;

class Unit implements Serializable{
    private String unit;//单位
    private int unitType ;//单位类别
    private double goodPrice;// 批发价
    private double salePrice;// 零售价

    private int fullNumber1;//一级阶梯数量
    private int fullNumber2;//二级阶梯数量
    private int fullNumber3;//三级阶梯数量
    private double ladderPrice1;//一级阶梯价
    private double ladderPrice2;//二级阶梯价
    private double ladderPrice3;//三级阶梯价
    private double startNum ;//起订量

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

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    public double getStartNum() {
        return startNum;
    }

    public void setStartNum(double startNum) {
        this.startNum = startNum;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unit='" + unit + '\'' +
                ", unitType=" + unitType +
                ", goodPrice=" + goodPrice +
                ", salePrice=" + salePrice +
                ", fullNumber1=" + fullNumber1 +
                ", fullNumber2=" + fullNumber2 +
                ", fullNumber3=" + fullNumber3 +
                ", ladderPrice1=" + ladderPrice1 +
                ", ladderPrice2=" + ladderPrice2 +
                ", ladderPrice3=" + ladderPrice3 +
                ", startNum=" + startNum +
                '}';
    }
}
