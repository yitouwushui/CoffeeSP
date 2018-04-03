package com.cjmex.coffeesp.mvp.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cjmex.coffeesp.bean.HouseholdFamily;
import com.cjmex.coffeesp.bean.MachineGson;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;
import com.cjmex.coffeesp.mvp.base.AbstractMvpPresenter;
import com.cjmex.coffeesp.uitls.Const;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.uitls.RetrofitUtil;
import com.cjmex.coffeesp.uitls.ToastUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ding
 * @date 2017/12/13
 */

public class DataPresenter extends AbstractMvpPresenter<IDataView> {

    private final DateRequestMode mDateRequestMode;
    private Context mContext;

    public DataPresenter() {
        mDateRequestMode = new DateRequestMode();
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

    public void requestMachineData(int indexPage) {
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
//            getmMvpView().requestMachineData(list);
//        }
        mDateRequestMode.requestMachine(1, indexPage, new Callback<MachineGson>() {
            @Override
            public void onResponse(@NonNull Call<MachineGson> call, @NonNull Response<MachineGson> response) {
                MachineGson machineGson = response.body();
                if (machineGson == null) {
                    getmMvpView().resultFailure(Const.RESPONSE_RESULT_FAILURE);
                    return;
                }
                if (machineGson.getResponseCode().equals(Const.RESPONSE_CODE)) {
                    getmMvpView().requestMachineData(machineGson.getList());
                    return;
                }
                ToastUtils.showToast(machineGson.getResponseMsg());
                getmMvpView().resultFailure(Const.RESPONSE_RESULT_FAILURE);
            }

            @Override
            public void onFailure(@NonNull Call<MachineGson> call, @NonNull Throwable t) {
                ToastUtils.showToast(t.toString());
                getmMvpView().resultFailure(Const.RESPONSE_RESULT_FAILURE);
            }
        });
    }


    public void requestTotalSaleCup() {
        mDateRequestMode.getTotalSaleCup(new Callback<TotalSaleCupGson>() {
            @Override
            public void onResponse(@NonNull Call<TotalSaleCupGson> call, @NonNull Response<TotalSaleCupGson> response) {
                TotalSaleCupGson totalSaleCupGson = response.body();
                if (totalSaleCupGson == null) {
                    return;
                }
                if (totalSaleCupGson.getResponseCode().equals(Const.RESPONSE_CODE)) {
                    getmMvpView().requestTotalSaleData(totalSaleCupGson);
                    return;
                }
                ToastUtils.showToast(totalSaleCupGson.getResponseMsg());
            }

            @Override
            public void onFailure(@NonNull Call<TotalSaleCupGson> call, @NonNull Throwable t) {
                ToastUtils.showToast(t.toString());
            }
        });
    }


    public void requestData2() {
        String json = readAssetsTxt(mContext, "json");
        try {
            ArrayList<HouseholdFamily> householdFamilies = RetrofitUtil.getInstance().getGson().fromJson(
                    json,
                    new TypeToken<List<HouseholdFamily>>() {
                    }.getType());

            if (householdFamilies != null && !householdFamilies.isEmpty()) {
                getmMvpView().requestData2(householdFamilies);
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

}
