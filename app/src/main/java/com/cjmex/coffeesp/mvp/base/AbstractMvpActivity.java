package com.cjmex.coffeesp.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 抽象activity统一处理绑定和解绑方法
 * 指定子类具体的View必须继承自IMvpBaseView
 * 指定子类具体的Presenter必须继承自AbstractMvpPresenter
 *
 * @author ding
 * @date 2017/12/8
 */

public abstract class AbstractMvpActivity<V extends IMvpBaseView, P extends AbstractMvpPresenter<V>>
        extends AppCompatActivity implements IMvpBaseView {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        // 绑定view
        presenter.attachMvpView((V) this);
    }

    @Override
    protected void onDestroy() {
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
        }
        super.onDestroy();
    }

    /**
     * 创建Presenter
     *
     * @return 子类自己需要的Presenter
     */
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     *
     * @return 返回子类创建的Presenter
     */
    public P getPresenter() {
        return presenter;
    }
}
