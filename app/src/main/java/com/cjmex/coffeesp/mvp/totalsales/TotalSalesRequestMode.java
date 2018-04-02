package com.cjmex.coffeesp.mvp.totalsales;

import com.cjmex.coffeesp.bean.Plate;
import com.cjmex.coffeesp.uitls.ApiService;
import com.cjmex.coffeesp.uitls.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author ding
 * @date 2017/11/17
 * @description
 */
public class TotalSalesRequestMode {

    private Call<List<Plate>> weatherBeanCall;

    public void request(Callback<List<Plate>> callback) {
        ApiService apiService = RetrofitUtil.getInstance().getRetrofit().create(ApiService.class);
        weatherBeanCall = apiService.requestData();
        weatherBeanCall.enqueue(callback);
    }

    public void interruptHttp() {
        if (weatherBeanCall != null && !weatherBeanCall.isCanceled()) {
            weatherBeanCall.cancel();
        }
    }
}
