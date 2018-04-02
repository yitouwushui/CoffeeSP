package com.cjmex.coffeesp.bean;

/**
 * Created by ding on 2018/4/2.
 */

public class Machine {


    /**
     * batchId : 1
     * code : 1234
     * createDate : 1521043199000
     * firm : 天平
     * firmId : 1
     * id : 1
     * position : xxxx
     * positionX : 1.0
     * positionY : 1.0
     * status : 1
     */

    private int batchId;
    private String code;
    private long createDate;
    private String firm;
    private int firmId;
    private int id;
    private String position;
    private double positionX;
    private double positionY;
    private String status;

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
