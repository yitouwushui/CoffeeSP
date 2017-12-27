package com.cjmex.coffeesp.mvp.data;

import android.content.Context;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.view.absrecyclerview.CommonAdapter;
import com.cjmex.coffeesp.view.absrecyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ding on 2017/12/20.
 */

public class DataAdapter extends CommonAdapter<SaleData> {


    public DataAdapter(Context context, int layoutId, List<SaleData> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SaleData saleData, int position) {
//        holder.setImageDrawable(R.id.img_icon, saleData.getIcon());
        holder.setText(R.id.tv_name, saleData.getName());
        holder.setText(R.id.tv_address, saleData.getAddress());
        holder.setText(R.id.tv_content, saleData.getContent());
        holder.setText(R.id.tv_content2, saleData.getContent2());
    }

}

