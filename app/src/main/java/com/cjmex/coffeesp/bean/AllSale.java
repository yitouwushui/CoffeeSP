package com.cjmex.coffeesp.bean;

import java.util.ArrayList;

/**
 * Created by ding on 2017/12/29.
 * 所有机器汇总销售额度
 */
public class AllSale {

    /**
     * 时间字符串
     */
    private String timeStr;

    /**
     * 时间L
     */
    private long timeL;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 销售数量
     */
    private int allCupOfNumber;

    /**
     * 补贴的钱
     */
    private long subsidyMoney;
    /**
     * 销售额度
     */
    private long saleMoney;

    /**
     * 每一台机器当月销售总量
     */
    private ArrayList<Integer> oneCupNumber;

    /**
     * 当月所有机器销售情况
     */
    private ArrayList<SaleData> saleData;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public ArrayList<Integer> getOneCupNumber() {
        return oneCupNumber;
    }

    public void setOneCupNumber(ArrayList<Integer> oneCupNumber) {
        this.oneCupNumber = oneCupNumber;
    }

    public ArrayList<SaleData> getSaleData() {
        return saleData;
    }

    public void setSaleData(ArrayList<SaleData> saleData) {
        this.saleData = saleData;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public long getTimeL() {
        return timeL;
    }

    public void setTimeL(long timeL) {
        this.timeL = timeL;
    }

    public int getAllCupOfNumber() {
        return allCupOfNumber;
    }

    public void setAllCupOfNumber(int allCupOfNumber) {
        this.allCupOfNumber = allCupOfNumber;
    }

    public long getSubsidyMoney() {
        return subsidyMoney;
    }

    public void setSubsidyMoney(long subsidyMoney) {
        this.subsidyMoney = subsidyMoney;
    }

    public long getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(long saleMoney) {
        this.saleMoney = saleMoney;
    }
}
