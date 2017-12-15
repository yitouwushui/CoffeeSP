package com.cjmex.coffeesp.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 抽象fragment统一处理绑定和解绑方法
 * 指定子类具体的View必须继承自IMvpBaseView
 * 指定子类具体的Presenter必须继承自AbstractMvpPresenter
 * Created by ding on 2017/12/13.
 */

public abstract class AbstractMvpFragment<V extends IMvpBaseView, P extends AbstractMvpPresenter<V>>
        extends Fragment implements IMvpBaseView {

    private P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (presenter == null){
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        // 绑定view
        presenter.attachMvpView((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
        }
        super.onDestroyView();
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
