package com.cjmex.coffeesp.mvp.base;

/**
 * 所有mvpView的父类
 *
 * @author ding
 * @date 2017/12/8
 */
public interface IMvpBaseView {
    /**
     * 请求失败
     *
     * @param result
     */
    void resultFailure(String result);
}
