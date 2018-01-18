package com.cjmex.coffeesp.mvp.data;

import android.content.Context;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.view.absrecyclerview.CommonAdapter;
import com.cjmex.coffeesp.view.absrecyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ding on 2017/12/20.
 */

public class DataAdapter extends CommonAdapter<HouseholdFamily> {


    public DataAdapter(Context context, int layoutId, List<HouseholdFamily> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, HouseholdFamily saleData, int position) {
//        holder.setImageDrawable(R.id.img_icon, saleData.getIcon());
        holder.setText(R.id.tv_name, saleData.getNo());
        holder.setText(R.id.tv_address, saleData.getNames());
        holder.setText(R.id.tv_content, saleData.getIdCard());
        holder.setText(R.id.tv_content2, saleData.getFamilyNumber() + "人");
    }

}

