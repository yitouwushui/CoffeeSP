package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.bean.Plate;
import com.cjmex.coffeesp.mvp.DataOfModel;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ding
 * @date 2017/12/8
 */
public class TotalSalesPresenter extends AbstractMvpPresenter<ITotalSalesView> {

    private Context context;
    private final TotalSalesRequestMode mTotalSalesRequestMode;

    public TotalSalesPresenter() {
        this.mTotalSalesRequestMode = new TotalSalesRequestMode();
    }

    @Override
    public void attachMvpView(ITotalSalesView view) {
        super.attachMvpView(view);
        context = ((TotalSalesFragment) view).getContext();
    }

    @Override
    public void detachMvpView() {
        context = null;
        mTotalSalesRequestMode.interruptHttp();
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

    public void requestFirstChartData(int count, float range, int start) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals4 = new ArrayList<Entry>();
        float value1 = 50;
        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + value1;
            value1 = val;
            if (i + start <= 12) {
                yVals1.add(new Entry(i, val, (i + start) + "月"));
            } else {
                yVals1.add(new Entry(i, val, (i + start - 12) + "月"));
            }
//            yVals4.add(new Entry(i,val/10,(i+1)+"月"));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        float value2 = 450;
        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + value2;
            value2 = val;
            if (i + start <= 12) {
                yVals2.add(new Entry(i, val, (i + start) + "月"));
            } else {
                yVals2.add(new Entry(i, val, (i + start - 12) + "月"));
            }

        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        float value3 = 500;
        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + value3;
            value3 = val;
            if (i + start <= 12) {
                yVals3.add(new Entry(i, val, (i + start) + "月"));
            } else {
                yVals3.add(new Entry(i, val, (i + start - 12) + "月"));
            }
        }
        ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
        datas.add(yVals1);
        datas.add(yVals2);
        datas.add(yVals3);
//        getmMvpView().requestData1(datas);
        getmMvpView().requestData2(datas);
        getmMvpView().requestData3(datas);

    }

    public void requersData() {
        mTotalSalesRequestMode.request(new Callback<List<Plate>>() {
            @Override
            public void onResponse(Call<List<Plate>> call, Response<List<Plate>> response) {
                List<Plate> list = response.body();
                if (list != null && !list.isEmpty()) {
                    ArrayList<Entry> yVals1 = new ArrayList<>();
                    ArrayList<Entry> yVals2 = new ArrayList<>();
                    ArrayList<Entry> yVals3 = new ArrayList<>();
                    for (int i = list.size() - 1, n = 0; i >= 0; i--, n++) {
                        Plate plate = list.get(i);
                        yVals2.add(new Entry(n, plate.getSUnitAmount(), plate.getUnitDate()));
                        yVals3.add(new Entry(n, plate.getBUnitAmount(), plate.getUnitDate()));
                    }
                    ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
                    datas.add(yVals1);
                    datas.add(yVals2);
                    datas.add(yVals3);
                    getmMvpView().requestData2(datas);
                    getmMvpView().requestData3(datas);
                }
            }

            @Override
            public void onFailure(Call<List<Plate>> call, Throwable t) {
                LogUtils.d(t.toString());
            }
        });
    }

    public void requestModelData() {
        DataOfModel model = DataOfModel.getInstance();
        ArrayList<AllSale> allSaleList = (ArrayList<AllSale>) model.getAllSaleList();
        ArrayList<ArrayList<Entry>> datas = new ArrayList<>();
        ArrayList<Entry> yVals1 = new ArrayList<>();
        ArrayList<Entry> yVals2 = new ArrayList<>();
        long all = 0L;
        for (int i = 0; i < allSaleList.size(); i++) {
            AllSale allSale = allSaleList.get(i);
            all += allSale.getSaleMoney();
            yVals1.add(new Entry(i, allSale.getSaleMoney(), allSale.getMonth() + "月"));
            yVals2.add(new Entry(i, all, allSale.getMonth() + "月"));
            datas.add(yVals1);
            datas.add(yVals2);
        }
        getmMvpView().requestData1(datas);
    }
}

