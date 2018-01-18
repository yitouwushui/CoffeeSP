package com.cjmex.coffeesp.mvp.data;

import android.content.Context;

import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.mvp.DataOfModel;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.uitls.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ding
 * @date 2017/12/13
 */

public class DataPresenter extends AbstractMvpPresenter<IDataView> {


    private Context mContext;
    private Gson gson;

    public DataPresenter() {
        LogUtils.i("DataPresenter:", "new Object");
    }

    @Override
    public void attachMvpView(IDataView view) {
        super.attachMvpView(view);
        mContext = ((DataFragment) view).getContext();
    }

    @Override
    public void detachMvpView() {
        mContext = null;
        super.detachMvpView();
    }

//    public void requestData() {
//        DataOfModel model = DataOfModel.getInstance();
//        ArrayList<AllSale> allSaleList = (ArrayList<AllSale>) model.getAllSaleList();
//        ArrayList<SaleData> list = new ArrayList<>();
//        int monthNumber = allSaleList.size();
//        if (monthNumber > 0) {
//            int number = allSaleList.get(monthNumber - 1).getSaleData().size();
//            list.addAll(allSaleList.get(monthNumber - 1).getSaleData());
//            if (monthNumber > 1) {
//                for (int i = monthNumber - 2; i >= 0; i--) {
//                    for (int j = 0; j < number; j++) {
//                        SaleData saleData = list.get(j);
//                        int before = saleData.getAllCurrentCup() == 0 ? saleData.getCurrentCup() : saleData.getAllCurrentCup();
//                        saleData.setAllCurrentCup(before + allSaleList.get(i).getSaleData().get(j).getCurrentCup());
//                    }
//                }
//            }
//            getmMvpView().requestData(list);
//        }
//    }


    public void requestData2() {
        String json = readAssetsTxt(mContext, "json");
        try {
            ArrayList<HouseholdFamily> householdFamilies = RetrofitUtil.getInstance().getGson().fromJson(
                    json,
                    new TypeToken<List<HouseholdFamily>>() {}.getType());

            if (householdFamilies != null && !householdFamilies.isEmpty()){
                getmMvpView().requestData(householdFamilies);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName + ".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }


//        int data = TimeUtils.getCurrentDateOfMonth();
//        for (int i = 1; i < 31; i++) {
//            SaleData saleData = new SaleData();
//
//            if (i < 10) {
//                saleData.setName("YKFM000" + i);
//            } else {
//                saleData.setName("YKFM00" + i);
//            }
//            saleData.setCurrentCup((int) (Math.random() * 30 * data));
//            saleData.setSaleMoney((  (int) (Math.random() * 30 * 30) + (int) (Math.random() * 30 * 30) ) * saleData.getPriceOfOneCup() + saleData.getSubsidyMoney());
//            saleData.setSubsidyMoney(saleData.getSaleMoney()/10);
//            list.add(saleData);
//        }
}
