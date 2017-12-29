package com.cjmex.coffeesp.mvp.data;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.base.AbstractMvpFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DataFragment extends AbstractMvpFragment<IDataView, DataPresenter> implements IDataView {

    View mView;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    DataPresenter dataPresenter;

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
            dataPresenter.requestData();
        }
        return mView;
    }

    private void init() {
        recycler.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        ));
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
        if (!hidden){
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recycler:
                break;
            default:
        }
    }

    @Override
    public void requestData(ArrayList<SaleData> list) {
        if (recycler.getAdapter() == null) {
            recycler.setAdapter(new DataAdapter(getContext(), R.layout.item_home_member_list, list));
        }
        recycler.getAdapter().notifyDataSetChanged();
    }

    /**
     *
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
