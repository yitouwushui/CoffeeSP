package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
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

        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < count - 1; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 450;
            yVals2.add(new Entry(i, val));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 500;
            yVals3.add(new Entry(i, val));
        }
        ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
        datas.add(yVals1);
        datas.add(yVals2);
        datas.add(yVals3);
        getmMvpView().requestData1(datas);
    }
}
