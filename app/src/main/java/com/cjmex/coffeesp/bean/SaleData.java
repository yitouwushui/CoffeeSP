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
     * 当月销售额度
     */
    private long saleMoney;
    /**
     * 累计销售销售额度
     */
    private long allSaleMoney;
    /**
     * 当月销售数量
     */
    private int currentCup;
    /**
     * 当月销售数量
     */
    private int allCurrentCup;
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

    public int getAllCurrentCup() {
        return allCurrentCup;
    }

    public void setAllCurrentCup(int allCurrentCup) {
        this.allCurrentCup = allCurrentCup;
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

    public long getAllSaleMoney() {
        return allSaleMoney;
    }

    public void setAllSaleMoney(long allSaleMoney) {
        this.allSaleMoney = allSaleMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSaleMoney(long saleMoney) {
        this.saleMoney = saleMoney;
    }

    public int getCurrentCup() {
        return currentCup;
    }

    public void setCurrentCup(int currentCup) {
        this.currentCup = currentCup;
    }

    public long getPriceOfOneCup() {
        return PriceOfOneCup;
    }

}
