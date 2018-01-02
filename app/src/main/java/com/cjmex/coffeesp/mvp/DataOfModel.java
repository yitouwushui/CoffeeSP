package com.cjmex.coffeesp.mvp;

import com.cjmex.coffeesp.bean.AllSale;
import com.cjmex.coffeesp.bean.SaleData;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private List<AllSale> allSaleList = new ArrayList<>();


    public List<AllSale> initMoth(int startYear, int startMonth, int count) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, startYear);
        cal.set(Calendar.MONTH, startMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        for (int j = 0; j < count; j++) {
            AllSale allSale = getAllSale(cal);
            cal.add(Calendar.MONTH, 1);
            allSaleList.add(allSale);
        }
        return allSaleList;
    }

    private AllSale getAllSale(Calendar cal) {
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (cal.getTimeInMillis() > System.currentTimeMillis()) {
            last = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        }
        AllSale allSale = new AllSale();
        allSale.setTimeL(cal.getTimeInMillis());
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
            saleData.setCurrentCup(cupOne);
            saleData.setSaleMoney(saleData.getCurrentCup() * saleData.getPriceOfOneCup());
            saleData.setTimeL(cal.getTimeInMillis());
            saleDateList.add(saleData);
        }
        allSale.setSaleData(saleDateList);
        allSale.setAllCupOfNumber(cupAll);
        allSale.setSaleMoney(cupAll * 10);
        allSale.setMonth(month);
        allSale.setYear(year);
        return allSale;
    }


    public List<AllSale> getAllSaleList() {
        return allSaleList;
    }
}
