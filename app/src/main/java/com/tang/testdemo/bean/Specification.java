package com.tang.testdemo.bean;

import java.io.Serializable;

class Specification implements Serializable{
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
