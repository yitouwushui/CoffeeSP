package com.cjmex.coffeesp.uitls;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

/**
 * Created by ding on 2017/12/15.
 */

public interface Const {

    String APP_HOST = "";

    //        String[] citys = {"A", "B", "C", "D"};
    String[] citys = {
            "A"
            , "B"
            , "C"
            , "D"
            , ""
    };
    //    public static final String[] cityName = {"云南宝山地区", "普洱市", "临沧市", "文山市"};
    HashMap<String, String> cityName = new HashMap<String, String>() {
        {
            put(citys[0], "云南宝山地区");
            put(citys[1], "普洱市");
            put(citys[2], "临沧市");
            put(citys[3], "文山市");
        }
    };

    public static final String RESPONSE_CODE = "0";

    public static final String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";

    public static final String RESPONSE_RESULT_FAILURE = "RESPONSE_RESULT_FAILURE";

//    String label = "注：A,B,C,D依次为 云南宝山地区,普洱市,临沧市,文山市,数字指月份";

    public static final int[] BAR_COLORS = {
            Color.rgb(192, 255, 140), Color.rgb(255, 140, 157),
            Color.rgb(255, 247, 140), Color.rgb(255, 140, 157),
            Color.rgb(255, 208, 140), Color.rgb(255, 140, 157),
            Color.rgb(140, 234, 255), Color.rgb(255, 140, 157),
            Color.rgb(255, 140, 157), Color.rgb(255, 140, 157),
    };
}
