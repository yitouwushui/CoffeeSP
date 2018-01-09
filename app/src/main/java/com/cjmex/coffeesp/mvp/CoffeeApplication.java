package com.cjmex.coffeesp.mvp;

import android.app.Application;

import com.cjmex.coffeesp.uitls.LogUtils;

/**
 * Created by ding on 2018/1/9.
 */

public class CoffeeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i("生命周期","CoffeeApplication onCreate()");
    }

    @Override
    public void onTerminate() {
        LogUtils.i("生命周期","CoffeeApplication onTerminate");
        super.onTerminate();
    }
}
