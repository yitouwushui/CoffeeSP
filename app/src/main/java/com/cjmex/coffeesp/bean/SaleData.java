package com.cjmex.coffeesp.bean;

/**
 * Created by yitouwushui on 2017/12/28.
 * 每台机器的单月销售额
 */
public class SaleData {

    /**
     * 机器编号
     */
    private String name;
    /**
     * 补贴的钱
     */
    private long subsidyMoney;
    /**
     * 销售额度
     */
    private long saleMoney;
    /**
     * 销售数量
     */
    private int cupOfNumber;
    /**
     * 单价(元)
     */
    private long PriceOfOneCup = 10L;
    /**
     * 时间string
     */
    private String timeStr;
    /**
     * 时间long
     */
    private long timeL;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCupOfNumber() {
        return cupOfNumber;
    }

    public void setCupOfNumber(int cupOfNumber) {
        this.cupOfNumber = cupOfNumber;
    }

    public long getPriceOfOneCup() {
        return PriceOfOneCup;
    }

    public void setPriceOfOneCup(long priceOfOneCup) {
        PriceOfOneCup = priceOfOneCup;
    }
}
