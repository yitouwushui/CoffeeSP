package com.cjmex.coffeesp.mvp.totalsales;

import android.graphics.drawable.Drawable;

import com.cjmex.coffeesp.mvp.base.IMvpBaseView;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ding
 * @date 2017/12/8
 */
public interface ITotalSalesView extends IMvpBaseView {

    void requestLoading();

    void loadAdvertisingSuccess(List<Drawable> result);

    void requestData1(List<ArrayList<Entry>> dataList);
}
