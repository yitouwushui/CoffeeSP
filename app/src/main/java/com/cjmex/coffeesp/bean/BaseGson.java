package com.cjmex.coffeesp.bean;

/**
 * Created by ding on 2018/4/2.
 */

public class BaseGson {

    /**
     * responseCode : 0
     * responseMsg : 查询数据成功!
     */

    private String responseCode;
    private String responseMsg;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
