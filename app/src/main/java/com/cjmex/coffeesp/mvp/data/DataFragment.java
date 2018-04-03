package com.cjmex.coffeesp.mvp.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.Machine;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;
import com.cjmex.coffeesp.mvp.base.AbstractMvpFragment;
import com.cjmex.coffeesp.mvp.data.order.OrderListActivity;
import com.cjmex.coffeesp.uitls.ToastUtils;
import com.cjmex.coffeesp.view.absrecyclerview.MultiItemTypeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ding
 */
public class DataFragment extends AbstractMvpFragment<IDataView, DataPresenter> implements IDataView {

    View mView;
    Unbinder unbinder;
    DataPresenter dataPresenter;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_cup)
    TextView tvCup;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    int indexPage = 1;

    private OnFragmentInteractionListener mListener;

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_data, container, false);
            unbinder = ButterKnife.bind(this, mView);
            init();

            dataPresenter.requestMachineData(indexPage);
            dataPresenter.requestTotalSaleCup();

        }
        return mView;
    }

    private void init() {
        recycler.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        ));

        refresh.setOnRefreshListener(refreshlayout -> {
            indexPage = 1;
            loadData(indexPage);
        });
        refresh.setOnLoadmoreListener(refreshlayout -> {
            indexPage++;
            loadData(indexPage);
        });

    }

    private void loadData(int indexPage) {

        dataPresenter.requestMachineData(indexPage);
    }

    @Override
    protected DataPresenter createPresenter() {
        return dataPresenter = new DataPresenter();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
//            if (recycler != null && recycler.getAdapter() != null){
//
//                recycler.getAdapter().notifyDataSetChanged();
//            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        mListener = null;
    }

    @Override
    public void resultFailure(String result) {
        refresh.finishLoadmore();
        refresh.finishRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void requestData2(ArrayList<HouseholdFamily> list) {

    }

    @Override
    public void requestMachineData(List<Machine> list) {
        refresh.finishLoadmore();
        refresh.finishRefresh();
        if (list == null || list.isEmpty()) {
            ToastUtils.showToast("没有更多数据了");
            return;
        }
        DataAdapter dataAdapter = (DataAdapter) recycler.getAdapter();
        if (dataAdapter == null) {
            recycler.setAdapter(new DataAdapter(getContext(), R.layout.item_home_member_list, list));
            ((DataAdapter) recycler.getAdapter()).setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(getContext(), OrderListActivity.class);
                    Machine machine = ((DataAdapter) recycler.getAdapter()).getDatas().get(position);
                    intent.putExtra("machineCode", machine.getCode());
                    getActivity().startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        } else {
            if (indexPage == 1) {
                dataAdapter.setmDatas(list);
            } else {
                dataAdapter.addmDatas(list);
            }
        }
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void requestTotalSaleData(TotalSaleCupGson totalSaleCupGson) {
        tvCup.setText(totalSaleCupGson.getTotalSaleCup() + "杯");
        tvMoney.setText(totalSaleCupGson.getTotalSaleMoney() + "元");
    }

    @OnClick({R.id.tv_cup, R.id.tv_money, R.id.recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cup:
                break;
            case R.id.tv_money:
                break;
            case R.id.recycler:
                break;
            default:
        }
    }


    /**
     *
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
}
