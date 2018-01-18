package com.cjmex.coffeesp.uitls;


import com.cjmex.coffeesp.bean.Plate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @date 2017/11/16
 * @description 请求接口
 */
public interface ApiService {

    /**
     * 请求天气接口
     *
     * @return Call
     */
    @GET("coffeeSP-web/order/sum/list")
    Call<List<Plate>> requestData();
}
