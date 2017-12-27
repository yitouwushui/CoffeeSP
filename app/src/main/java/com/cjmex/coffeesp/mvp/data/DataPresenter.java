package com.cjmex.coffeesp.mvp.data;

import android.content.Context;
import android.content.res.Resources;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;

import java.util.ArrayList;

/**
 * @author ding
 * @date 2017/12/13
 */

public class DataPresenter extends AbstractMvpPresenter<IDataView> {


    private Context context;

    public DataPresenter() {
        LogUtils.i("DataPresenter:", "new Object");
    }

    @Override
    public void attachMvpView(IDataView view) {
        super.attachMvpView(view);
        context = ((DataFragment) view).getContext();
    }

    @Override
    public void detachMvpView() {
        context = null;
        super.detachMvpView();
    }

    public void requestData() {
        Resources resources = context.getResources();
        ArrayList<SaleData> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SaleData saleData = new SaleData();
            saleData.setIcon(resources.getDrawable(R.mipmap.ic_launcher_round));
            saleData.setAddress("浦东南路110号");
            saleData.setName("第" + i + "台");
            saleData.setContent("当月销售金额");
            saleData.setContent2("累积销售金额");
            list.add(saleData);
        }
        getmMvpView().requestData(list);
    }
}
