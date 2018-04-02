package com.cjmex.coffeesp.mvp.data.order;

import com.cjmex.coffeesp.bean.OrderList;
import com.cjmex.coffeesp.mvp.base.IMvpBaseView;

import java.util.List;

/**
 * @author ding
 * @date 2017/12/13
 */

public interface IOrderListView extends IMvpBaseView {

    /**
     * 请求订单list
     *
     * @param list
     */
    void returnOrderListData(List<OrderList> list);

}
