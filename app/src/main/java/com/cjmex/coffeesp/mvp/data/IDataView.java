package com.cjmex.coffeesp.mvp.data;

import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.Machine;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;
import com.cjmex.coffeesp.mvp.base.IMvpBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2017/12/13.
 */

public interface IDataView extends IMvpBaseView {

    void requestData2(ArrayList<HouseholdFamily> list);

    void requestMachineData(List<Machine> list);

    void requestTotalSaleData(TotalSaleCupGson totalSaleCupGson);
}
