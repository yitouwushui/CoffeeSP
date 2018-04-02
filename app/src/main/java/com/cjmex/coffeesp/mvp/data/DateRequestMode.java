package com.cjmex.coffeesp.mvp.data;


import com.cjmex.coffeesp.bean.MachineGson;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;
import com.cjmex.coffeesp.uitls.ApiService;
import com.cjmex.coffeesp.uitls.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public class DateRequestMode {
    private Call<MachineGson> mMachineGsonCall;
    private Call<TotalSaleCupGson> mTotalSaleCupGsonCall;

    public void requestMachine(int firmId, int pageNum, Callback<MachineGson> callback) {
        requestMachine(firmId, pageNum, 20, callback);
    }

    public void requestMachine(int firmId, int pageNum, int pageSize, Callback<MachineGson> callback) {
        ApiService apiService = RetrofitUtil.getInstance().getRetrofit().create(ApiService.class);
        mMachineGsonCall = apiService.requestMachineList(firmId, pageNum, pageSize);
        mMachineGsonCall.enqueue(callback);
    }


    public void getTotalSaleCup(Callback<TotalSaleCupGson> callback) {
        ApiService apiService = RetrofitUtil.getInstance().getRetrofit().create(ApiService.class);
        mTotalSaleCupGsonCall = apiService.getTotalSaleCup();
        mTotalSaleCupGsonCall.enqueue(callback);
    }

    public void interruptHttp() {
        if (mMachineGsonCall != null && !mMachineGsonCall.isCanceled()) {
            mMachineGsonCall.cancel();
        }
        if (mTotalSaleCupGsonCall != null && !mTotalSaleCupGsonCall.isCanceled()) {
            mTotalSaleCupGsonCall.cancel();
        }
    }
}
