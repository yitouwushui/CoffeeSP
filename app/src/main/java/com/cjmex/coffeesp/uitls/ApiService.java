package com.cjmex.coffeesp.uitls;


import com.cjmex.coffeesp.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description 请求接口
 */
public interface ApiService {

    /**
     * 请求天气接口
     * @param cityId 城市
     * @return Call
     */
    @GET("data/cityinfo/{cityId}.html")
    Call<User> requestWeather(@Path("cityId") String cityId);
}
