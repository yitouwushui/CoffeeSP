package com.cjmex.coffeesp.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by yitouwushui on 2017/12/28.
 */

public class SaleData {

    private Drawable icon;
    private String name;
    private String content;
    private String content2;
    private String address;

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

    public String getContent() {
        return content;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
