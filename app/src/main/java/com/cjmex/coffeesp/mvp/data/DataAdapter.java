package com.cjmex.coffeesp.mvp.data;

import android.content.Context;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.Machine;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.view.absrecyclerview.CommonAdapter;
import com.cjmex.coffeesp.view.absrecyclerview.base.ViewHolder;

import java.util.List;


/**
 * Created by ding on 2017/12/20.
 */
//public class DataAdapter extends CommonAdapter<SaleData> {
//
//
//    public DataAdapter(Context context, int layoutId, List<SaleData> datas) {
//        super(context, layoutId, datas);
//    }
//
//    @Override
//    protected void convert(ViewHolder holder, SaleData saleData, int position) {
////        holder.setImageDrawable(R.id.img_icon, saleData.getIcon());
//        holder.setText(R.id.tv_name, saleData.getName());
//        holder.setText(R.id.tv_address, saleData.getCurrentCup() + "杯");
//        holder.setText(R.id.tv_content, saleData.getAllCurrentCup() + "元");
//        holder.setText(R.id.tv_content2, saleData.getAllCurrentCup() * saleData.getPriceOfOneCup() + "元");
//    }
//
//}

/**
 *
 * @author ding
 * @date 2017/12/20
 */
public class DataAdapter extends CommonAdapter<Machine> {


    public DataAdapter(Context context, int layoutId, List<Machine> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Machine machine, int position) {
//        holder.setImageDrawable(R.id.img_icon, saleData.getIcon());
        holder.setText(R.id.tv_name, machine.getCode());
        holder.setText(R.id.tv_address, machine.getFirm());
        holder.setText(R.id.tv_content, machine.getPosition());
        holder.setText(R.id.tv_content2, String.valueOf(machine.getBatchId()));
    }

}

