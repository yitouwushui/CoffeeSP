package com.cjmex.coffeesp.mvp.data.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.OrderList;
import com.cjmex.coffeesp.mvp.base.AbstractMvpActivity;
import com.cjmex.coffeesp.uitls.ToastUtils;
import com.cjmex.coffeesp.view.AlertDialog;
import com.cjmex.coffeesp.view.absrecyclerview.MultiItemTypeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ding
 */
public class OrderListActivity extends AbstractMvpActivity<IOrderListView, OrderListPresenter> implements IOrderListView {

    OrderListPresenter orderListPresenter;
    @BindView(R.id.tv_machine_code)
    TextView tvMachineCode;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Context mContext;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    String machineCode = "";
    int pageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        mContext = this;
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        Intent intent = getIntent();
        machineCode = intent.getStringExtra("machineCode");
        machineCode = "E630I5XK";
        tvMachineCode.setText(machineCode);
        orderListPresenter.requestOrderList(machineCode, pageNum);
        recycler.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false
        ));

        refresh.setOnRefreshListener(refreshlayout -> {
            pageNum = 1;
            loadData(pageNum);
        });
        refresh.setOnLoadmoreListener(refreshlayout -> {
            pageNum++;
            loadData(pageNum);
        });
    }

    private void loadData(int pageNum) {
        orderListPresenter.requestOrderList(machineCode, pageNum);
    }

    @Override
    protected OrderListPresenter createPresenter() {
        return orderListPresenter = new OrderListPresenter();
    }

    @Override
    public void resultFailure(String result) {
        refresh.finishLoadmore();
        refresh.finishRefresh();
    }

    @Override
    public void returnOrderListData(List<OrderList> list) {
        refresh.finishLoadmore();
        refresh.finishRefresh();
        if (list == null || list.isEmpty()) {
            ToastUtils.showToast("没有更多数据了");
            return;
        }
        OrderListAdapter dataAdapter = (OrderListAdapter) recycler.getAdapter();
        if (dataAdapter == null) {
            recycler.setAdapter(new OrderListAdapter(mContext, R.layout.item_order_list, list));
//            ((OrderListAdapter) recycler.getAdapter()).setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
////                    AlertDialog alertDialog = new AlertDialog(mContext);
////                    alertDialog.show();
//                }
//
//                @Override
//                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                    return false;
//                }
//            });
        } else {
            if (pageNum == 1) {
                dataAdapter.setmDatas(list);
            } else {
                dataAdapter.addmDatas(list);
            }
        }
        recycler.getAdapter().notifyDataSetChanged();
    }

    @OnClick({R.id.tv_machine_code, R.id.recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_machine_code:
                break;
            case R.id.recycler:
                break;
            default:
        }
    }
}
