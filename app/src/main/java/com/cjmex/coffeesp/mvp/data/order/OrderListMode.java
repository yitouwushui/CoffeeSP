package com.cjmex.coffeesp.mvp.data.order;


import com.cjmex.coffeesp.bean.MachineGson;
import com.cjmex.coffeesp.bean.OrderListGson;
import com.cjmex.coffeesp.uitls.ApiService;
import com.cjmex.coffeesp.uitls.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public class OrderListMode {
    private Call<OrderListGson> mGetOrderList;

    public void requestOrderList(String machineCode, int pageNum, Callback<OrderListGson> callback) {
        requestOrderList(machineCode, pageNum, 20, callback);
    }

    public void requestOrderList(String machineCode, int pageNum, int pageSize, Callback<OrderListGson> callback) {
        ApiService apiService = RetrofitUtil.getInstance().getRetrofit().create(ApiService.class);
        mGetOrderList = apiService.getOrderList(machineCode, pageNum, pageSize);
        mGetOrderList.enqueue(callback);
    }

    public void interruptHttp() {
        if (mGetOrderList != null && !mGetOrderList.isCanceled()) {
            mGetOrderList.cancel();
        }

    }
}
