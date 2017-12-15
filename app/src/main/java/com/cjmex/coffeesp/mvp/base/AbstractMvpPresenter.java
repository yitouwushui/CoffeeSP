package com.cjmex.coffeesp.mvp.base;

/**
 * 指定绑定的View必须继承自IMvpBaseView4
 *
 * @author ding
 * @date 2017/12/8
 */
public abstract class AbstractMvpPresenter<V extends IMvpBaseView> {

    private V mMvpView;

    public void attachMvpView(V view) {
        this.mMvpView = view;
    }

    public void detachMvpView() {
        mMvpView = null;
    }

    /**
     * 获取V层
     *
     * @return
     */
    public V getmMvpView() {
        return mMvpView;
    }
}
