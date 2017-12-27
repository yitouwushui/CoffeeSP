package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.view.absrecyclerview.CommonAdapter;
import com.cjmex.coffeesp.view.absrecyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by ding on 2017/12/20.
 */

public class HomeMemberAdapter extends CommonAdapter<SaleData> {


    public HomeMemberAdapter(Context context, int layoutId, List<SaleData> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SaleData member, int position) {
//        holder.setImageDrawable(R.id.img_icon,member.getIcon());
//        holder.setText(R.id.tv_name,member.getName());
//        holder.setText(R.id.tv_content,member.getContent());
    }

}

