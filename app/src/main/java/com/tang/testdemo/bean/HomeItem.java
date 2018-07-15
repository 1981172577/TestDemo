package com.tang.testdemo.bean;

public class HomeItem {
    private int picResuse;

    private int itemPosition;

    private String itemName;

    private HomeItem(Builder builder) {
        setPicResuse(builder.picResuse);
        setItemPosition(builder.itemPosition);
        setItemName(builder.itemName);
    }

    public int getPicResuse() {
        return picResuse;
    }

    public void setPicResuse(int picResuse) {
        this.picResuse = picResuse;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public static final class Builder {
        private int picResuse;
        private int itemPosition;
        private String itemName;

        public Builder() {
        }

        public Builder picResuse(int val) {
            picResuse = val;
            return this;
        }

        public Builder itemPosition(int val) {
            itemPosition = val;
            return this;
        }

        public Builder itemName(String val) {
            itemName = val;
            return this;
        }

        public HomeItem build() {
            return new HomeItem(this);
        }
    }
}
