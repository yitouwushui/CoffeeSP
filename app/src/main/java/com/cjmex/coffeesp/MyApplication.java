package com.cjmex.coffeesp;

import android.app.Application;
import android.content.Context;

/**
 * Created by ding on 2018/3/22.
 */

public class MyApplication extends Application {

    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static synchronized MyApplication app() {
        return context;
    }

    public synchronized Context getContext() {
        return context;
    }
}
