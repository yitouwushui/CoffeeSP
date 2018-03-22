package com.cjmex.coffeesp.uitls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.cjmex.coffeesp.MyApplication;

/**
 * Created by ding on 2017/4/24.
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 弹出提示消息
     *
     * @param context
     * @param content
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 弹出提示消息
     *
     * @param content
     */
    @SuppressLint("ShowToast")
    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.app().getContext().getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
