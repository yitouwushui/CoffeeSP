package com.cjmex.coffeesp.bean;

/**
 * Created by ding on 2018/3/22.
 */

public class Account {

    private volatile static Account instance;

    private Account() {
    }

    public static Account getInstance() {
        if (instance == null) {
            synchronized (Account.class) {
                if (instance == null) {
                    instance = new Account();
                }
            }
        }
        return instance;
    }

    public static void setInstance(Account instance) {
        Account.instance = instance;
    }

    private String token;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
