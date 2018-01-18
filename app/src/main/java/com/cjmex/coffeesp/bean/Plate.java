package com.cjmex.coffeesp.bean;

/**
 * Created by ding on 2018/1/18.
 * <p>
 * * sUnitAmount : 2721
 * sUnitQuantity : 211
 * bUnitAmount : 414749
 * bUnitQuantity : 22652
 * unitDate : 2018-01
 */
public class Plate {


    /**
     * 卖出总金额 元
     */
    private int sUnitAmount;
    /**
     * 卖出总数量 公斤
     */
    private int sUnitQuantity;
    /**
     * 买的总金额 元
     */
    private int bUnitAmount;
    /**
     * 买的总数量 公斤
     */
    private int bUnitQuantity;
    /**
     * 单位时间 月
     */
    private String unitDate;

    public int getSUnitAmount() {
        return sUnitAmount;
    }

    public void setSUnitAmount(int sUnitAmount) {
        this.sUnitAmount = sUnitAmount;
    }

    public int getSUnitQuantity() {
        return sUnitQuantity;
    }

    public void setSUnitQuantity(int sUnitQuantity) {
        this.sUnitQuantity = sUnitQuantity;
    }

    public int getBUnitAmount() {
        return bUnitAmount;
    }

    public void setBUnitAmount(int bUnitAmount) {
        this.bUnitAmount = bUnitAmount;
    }

    public int getBUnitQuantity() {
        return bUnitQuantity;
    }

    public void setBUnitQuantity(int bUnitQuantity) {
        this.bUnitQuantity = bUnitQuantity;
    }

    public String getUnitDate() {
        return unitDate;
    }

    public void setUnitDate(String unitDate) {
        this.unitDate = unitDate;
    }
}
