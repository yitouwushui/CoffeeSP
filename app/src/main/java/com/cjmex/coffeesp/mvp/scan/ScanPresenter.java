package com.cjmex.coffeesp.mvp.scan;

import android.content.Context;

import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.DataOfModel;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.mvp.data.DataFragment;
import com.cjmex.coffeesp.mvp.data.IDataView;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.uitls.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ding
 * @date 2017/12/13
 */

public class ScanPresenter extends AbstractMvpPresenter<IScanView> {


    private Context mContext;

    public ScanPresenter() {
        LogUtils.i("DataPresenter:", "new Object");
    }

    @Override
    public void attachMvpView(IScanView view) {
        super.attachMvpView(view);
        mContext = ((DataFragment) view).getContext();
    }

    @Override
    public void detachMvpView() {
        mContext = null;
        super.detachMvpView();
    }

}
