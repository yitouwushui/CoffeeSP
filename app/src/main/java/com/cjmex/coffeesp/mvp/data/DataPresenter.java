package com.cjmex.coffeesp.mvp.data;

import android.content.Context;

import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.uitls.TimeUtils;

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
        ArrayList<SaleData> list = new ArrayList<>();
        int data = TimeUtils.getCurrentDateOfMonth();
        for (int i = 1; i < 31; i++) {
            SaleData saleData = new SaleData();

            if (i < 10) {
                saleData.setName("YKFM000" + i);
            } else {
                saleData.setName("YKFM00" + i);
            }
            saleData.setCupOfNumber((int) (Math.random() * 30 * data));
            saleData.setSaleMoney((  (int) (Math.random() * 30 * 30) + (int) (Math.random() * 30 * 30) ) * saleData.getPriceOfOneCup() + saleData.getSubsidyMoney());
            saleData.setSubsidyMoney(saleData.getSaleMoney()/10);
            list.add(saleData);
        }
        getmMvpView().requestData(list);
    }
}
