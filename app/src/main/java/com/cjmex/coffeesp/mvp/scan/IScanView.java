package com.cjmex.coffeesp.mvp.scan;

import com.cjmex.coffeesp.mvp.base.IMvpBaseView;

import java.util.Map;

/**
 * Created by ding on 2017/12/13.
 */

public interface IScanView extends IMvpBaseView {

    /**
     * 请求扫码
     *
     * @param type
     */
    void requestScan(int type);

    /**
     * 上传信息
     *
     * @param map
     */
    void uploadQrCode(Map<String, Object> map);
}
