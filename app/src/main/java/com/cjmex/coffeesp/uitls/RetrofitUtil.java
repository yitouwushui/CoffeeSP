package com.cjmex.coffeesp.uitls;


import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author yitouwushui
 */
public class RetrofitUtil {

    private static final long DEFAULT_MILLISECONDS = 5L;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private Gson gson;

    private volatile static RetrofitUtil mInstance;

    public static final String BASE_URL = "http://192.168.2.206:8080/";//测试地址

    /**
     * 构造方法
     *
     * @param okHttpClient
     */
    private RetrofitUtil(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            mOkHttpClient = okHttpClient;
        } else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS);
            mOkHttpClient = builder.build();
        }
        gson = new Gson();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }

    /**
     * 双重校验单例
     *
     * @param okHttpClient
     * @return
     */
    public static RetrofitUtil initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtil(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        return initClient(null);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public Gson getGson() {
        return gson;
    }

}

