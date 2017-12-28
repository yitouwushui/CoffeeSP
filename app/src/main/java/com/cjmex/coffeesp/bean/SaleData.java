package com.cjmex.coffeesp.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by yitouwushui on 2017/12/28.
 */

public class SaleData {

    private Drawable icon;
    private String name;
    private String subsidyMoney;
    private String saleMoney;
    private String address;
    private int cupOfNumber;
    private long PriceOfOneCup = 10L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getSubsidyMoney() {
        return subsidyMoney;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubsidyMoney(String subsidyMoney) {
        this.subsidyMoney = subsidyMoney;
    }

    public String getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(String saleMoney) {
        this.saleMoney = saleMoney;
    }
}
