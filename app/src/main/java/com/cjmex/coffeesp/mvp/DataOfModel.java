package com.cjmex.coffeesp.mvp;

import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.bean.SaleData;
import com.cjmex.coffeesp.uitls.TimeUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by ding on 2017/12/29.
 */

public class DataOfModel {

    private static volatile DataOfModel instance;

    public static DataOfModel getInstance() {
        if (instance == null) {
            synchronized (DataOfModel.class) {
                if (instance == null) {
                    instance = new DataOfModel();
                }
            }
        }
        return instance;
    }




    /**
     * 存在每月相应的数据
     * Integer是月份
     */
    private HashMap<Integer, AllSale> hashMap = new HashMap<>();

    ArrayList<Entry> yVals1 = new ArrayList<Entry>();

    public void initData() {
        ArrayList<SaleData> list = new ArrayList<>();
        int data = TimeUtils.getCurrentDateOfMonth();
        for (int i = 1; i < 31; i++) {
            SaleData saleData = new SaleData();
            if (i < 10) {
                saleData.setName("YKFM000" + i);
            } else {
                saleData.setName("YKFM00" + i);
            }
            saleData.setCupOfNumber((int) (Math.random() * 30 * data));
            saleData.setSubsidyMoney(saleData.getCupOfNumber() * saleData.getPriceOfOneCup());
            saleData.setSaleMoney(((int) (Math.random() * 30 * 30) + (int) (Math.random() * 30 * 30)) * saleData.getPriceOfOneCup() + saleData.getSubsidyMoney());
            list.add(saleData);
        }
    }

    public AllSale initOneMoth(Integer year, Integer month) {
        AllSale allSale = new AllSale();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        allSale.setTimeL(cal.getTimeInMillis());
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        int first = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        int number = 30;
        int cupAll = 0;
        ArrayList<SaleData> saleDateList = new ArrayList<>();
        for (int no = 1; no <= number; no++) {
            SaleData saleData = new SaleData();
            if (no < 10) {
                saleData.setName("YKFM000" + no);
            } else {
                saleData.setName("YKFM00" + no);
            }
            int cupOne = (int) (Math.random() * 30 * last);
            cupAll += cupOne;
            saleData.setCupOfNumber(cupOne);
            saleData.setSaleMoney(saleData.getCupOfNumber() * saleData.getPriceOfOneCup());
            saleData.setTimeL(cal.getTimeInMillis());
            saleDateList.add(saleData);
        }
        allSale.setSaleData(saleDateList);
        allSale.setAllCupOfNumber(cupAll);
        allSale.setSaleMoney(cupAll * 10);
        allSale.setMonth(month);
        // 存档
        hashMap.put(month, allSale);
//        yVals1.add(new Entry())
        return allSale;
    }

    public HashMap<Integer, AllSale> getHashMap() {
        return hashMap;
    }


    //    public AllSale initOneMothOfDay(Integer year, Integer moth) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.MONTH, moth - 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//
//        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
////        int first = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
//        int number = 30;
//        ArrayList<>
//        for (int i = 1; i <= last; i++) {
//            AllSale allSale = new AllSale();
//            int cupAll = 0;
//            cal.set(Calendar.DAY_OF_MONTH,i);
//            for (int no = 1; no <= number; no++) {
//                SaleData saleData = new SaleData();
//                if (no < 10) {
//                    saleData.setName("YKFM000" + i);
//                } else {
//                    saleData.setName("YKFM00" + i);
//                }
//                int cupOne = (int) (Math.random() * 30);
//                cupAll += cupOne;
//                saleData.setCupOfNumber(cupOne);
//                saleData.setSaleMoney(saleData.getCupOfNumber() * saleData.getPriceOfOneCup());
//                saleData.setTimeL(cal.getTimeInMillis());
//                list.add(saleData);
//            }
//        }
//        return null;
//    }

}
