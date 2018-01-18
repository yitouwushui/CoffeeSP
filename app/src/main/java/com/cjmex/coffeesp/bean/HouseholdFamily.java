package com.cjmex.coffeesp.bean;

/**
 * Created by ding on 2017/12/8.
 */

public class HouseholdFamily {


    /**
     * no : 1
     * village : 赧亢村委会
     * names : 陈*绍
     * idCard : 5330011981******12
     * familyNumber : 4
     * attribute : 一般农户
     * phone : 133****3160
     */

    /**
     * 农户编号
     */
    private String no;
    /**
     * 村委会
     */
    private String village;
    /**
     * 姓名
     */
    private String names;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 家庭人数
     */
    private String familyNumber;
    private String attribute;
    private String phone;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
