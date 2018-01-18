package com.cjmex.coffeesp.mvp.data;

import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.base.IMvpBaseView;

import java.util.ArrayList;

/**
 * Created by ding on 2017/12/13.
 */

public interface IDataView extends IMvpBaseView {

    void requestData(ArrayList<HouseholdFamily> list);
}
