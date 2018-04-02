package com.cjmex.coffeesp.mvp.data.order;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cjmex.coffeesp.bean.MachineGson;
import com.cjmex.coffeesp.bean.OrderListGson;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.Const;
import com.cjmex.coffeesp.uitls.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ding
 * @date 2017/12/13
 */

public class OrderListPresenter extends AbstractMvpPresenter<IOrderListView> {

    private final OrderListMode mOrderListMode;
    private Context mContext;

    public OrderListPresenter() {
        mOrderListMode = new OrderListMode();
    }

    @Override
    public void attachMvpView(IOrderListView view) {
        super.attachMvpView(view);
        mContext = ((OrderListActivity) view);
    }

    @Override
    public void detachMvpView() {
        mContext = null;
        super.detachMvpView();
    }

    public void requestOrderList(String machineCode, int pageNum) {
        mOrderListMode.requestOrderList(machineCode, pageNum, new Callback<OrderListGson>() {
            @Override
            public void onResponse(@NonNull Call<OrderListGson> call, @NonNull Response<OrderListGson> response) {
                OrderListGson orderListGson = response.body();
                if (orderListGson == null){
                    return;
                }
                if (orderListGson.getResponseCode().equals(Const.RESPONSE_CODE)){
                    getmMvpView().returnOrderListData(orderListGson.getList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderListGson> call, @NonNull Throwable t) {
                ToastUtils.showToast(t.toString());
            }
        });
    }
}
