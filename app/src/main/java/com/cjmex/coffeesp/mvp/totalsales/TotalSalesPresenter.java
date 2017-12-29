package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.mvp.DataOfModel;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ding
 * @date 2017/12/8
 */
public class TotalSalesPresenter extends AbstractMvpPresenter<ITotalSalesView> {

    private Context context;

    public TotalSalesPresenter() {
        LogUtils.i("TotalSalesPresenter:", "new Object");
    }

    @Override
    public void attachMvpView(ITotalSalesView view) {
        super.attachMvpView(view);
        context = ((TotalSalesFragment) view).getContext();
    }

    @Override
    public void detachMvpView() {
        context = null;
        super.detachMvpView();
    }

    public void loadAdvertising() {
        Resources resources = context.getResources();
        List<Drawable> bitmapList = new ArrayList<>();
        bitmapList.add(resources.getDrawable(R.drawable.img1));
        bitmapList.add(resources.getDrawable(R.drawable.img2));
        bitmapList.add(resources.getDrawable(R.drawable.img3));
        bitmapList.add(resources.getDrawable(R.drawable.img4));
        bitmapList.add(resources.getDrawable(R.drawable.img5));
        getmMvpView().loadAdvertisingSuccess(bitmapList);
    }

    public void requestFirstChartData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals4 = new ArrayList<Entry>();
        float value1 = 50;
        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + value1;
            value1 = val;
            yVals1.add(new Entry(i, val, (i + 7) + "月"));
//            yVals4.add(new Entry(i,val/10,(i+1)+"月"));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        float value2 = 450;
        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + value2;
            value2 = val;

            yVals2.add(new Entry(i, val, (i + 7) + "月"));

        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        float value3 = 500;
        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + value3;
            value3 = val;
            yVals3.add(new Entry(i, val, (i + 7) + "月"));
        }
        ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
        datas.add(yVals1);
        datas.add(yVals2);
        datas.add(yVals3);
//        getmMvpView().requestData1(datas);
        getmMvpView().requestData2(datas);
        getmMvpView().requestData3(datas);

    }


    public void requestModelData() {
        ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        DataOfModel model = DataOfModel.getInstance();
        HashMap<Integer, AllSale> hm = model.getHashMap();
        AllSale allSale = hm.get(8);
        AllSale allSale2 = hm.get(9);
        AllSale allSale3 = hm.get(10);
        AllSale allSale4 = hm.get(11);
        yVals1.add(new Entry(0, allSale.getSaleMoney(), allSale.getMonth() + "月"));
        yVals1.add(new Entry(1, allSale2.getSaleMoney(), allSale2.getMonth() + "月"));
        yVals1.add(new Entry(2, allSale3.getSaleMoney(), allSale3.getMonth() + "月"));
        yVals1.add(new Entry(3, allSale4.getSaleMoney(), allSale4.getMonth() + "月"));
        long all = allSale.getSaleMoney();
        yVals2.add(new Entry(0, all, allSale.getMonth() + "月"));
        yVals2.add(new Entry(1, all += allSale2.getSaleMoney(), allSale2.getMonth() + "月"));
        yVals2.add(new Entry(2, all += allSale3.getSaleMoney(), allSale3.getMonth() + "月"));
        yVals2.add(new Entry(3, all + allSale4.getSaleMoney(), allSale4.getMonth() + "月"));
        datas.add(yVals1);
        datas.add(yVals2);
        getmMvpView().requestData1(datas);
    }
}

