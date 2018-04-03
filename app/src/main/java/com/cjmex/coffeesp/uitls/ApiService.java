package com.cjmex.coffeesp.uitls;


import com.cjmex.coffeesp.bean.BaseGson;
import com.cjmex.coffeesp.bean.MachineGson;
import com.cjmex.coffeesp.bean.OrderListGson;
import com.cjmex.coffeesp.bean.Plate;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @date 2017/11/16
 * @description 请求接口
 */
public interface ApiService {

    /**
     * 请求首页数据
     *
     * @return Call
     */
    @GET("coffeeSP-web/order/sum/list")
    Call<List<Plate>> requestData();

    /**
     * 根据公司id获取机器列表
     *
     * @param firmId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("coffeeSP-web/getMachineList?")
    Call<MachineGson> requestMachineList(@Query("firmId") int firmId, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * 根据机器码获取订单
     *
     * @param machineCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("coffeeSP-web/getOrderList?")
    Call<OrderListGson> getOrderList(@Query("machineCode") String machineCode, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * 获取扶贫总额
     *
     * @return
     */
    @GET("coffeeSP-web/getTotalSaleCup?")
    Call<TotalSaleCupGson> getTotalSaleCup();

    /**
     * 获取扶贫总额
     *
     * @param loginName
     * @param password
     * @return
     */
    @POST("coffeeSP-web/login?")
    @FormUrlEncoded
    Call<BaseGson> goLogin(@Field("loginName") String loginName, @Field("password") String password);
}
