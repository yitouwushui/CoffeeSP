package com.cjmex.coffeesp.bean;

/**
 *
 * @author ding
 * @date 2018/4/2
 */

public class OrderList {

    /**
     * createDate : 1522140351000
     * currency : RMB
     * depositRate : 0.0
     * firm : 天平
     * firmId : 1
     * id : 2
     * machineCode : E630I5XK
     * orderDetailCount : 1
     * orderNo : 20180312160044288240
     * orgAmount : 0.01
     * payDate : 1520841659000
     * payType : 1
     * realAmount : 0.01
     */

    private long createDate;
    private String currency;
    private double depositRate;
    private String firm;
    private int firmId;
    private int id;
    private String machineCode;
    private int orderDetailCount;
    private String orderNo;
    private double orgAmount;
    private long payDate;
    private String payType;
    private double realAmount;

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(double depositRate) {
        this.depositRate = depositRate;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public int getOrderDetailCount() {
        return orderDetailCount;
    }

    public void setOrderDetailCount(int orderDetailCount) {
        this.orderDetailCount = orderDetailCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getOrgAmount() {
        return orgAmount;
    }

    public void setOrgAmount(double orgAmount) {
        this.orgAmount = orgAmount;
    }

    public long getPayDate() {
        return payDate;
    }

    public void setPayDate(long payDate) {
        this.payDate = payDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount = realAmount;
    }
}
