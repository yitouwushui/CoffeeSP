package com.cjmex.coffeesp.view;

import com.cjmex.coffeesp.uitls.Const;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by ding on 09/09/16.
 */
public class BarAxisValueFormatter implements IAxisValueFormatter {

    private BarChart chart;

    private List<BarEntry> mTime;

    private int length;

    private int middle;

    public BarAxisValueFormatter(BarChart chart, List<BarEntry> mTime) {
        this.chart = chart;
        this.mTime = mTime;
        length = Const.cityName.size();
        middle = length % 2 == 0 ? length / 2 - 1 : length / 2;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int minute = (int) value;
        String minuteStr;
        if (minute < mTime.size() && minute >= 0) {
            minuteStr = (String) mTime.get(minute).getData();
        } else {
            minuteStr = "  ";
        }
        if (value % (length + 1) == middle) {
            minuteStr = minuteStr.substring(1, minuteStr.length()) + " 月";
        } else {
            minuteStr = "";
        }
//        LogUtils.i("x" + chart.getVisibleXRange() + " : " + "c:" + axis.getLabelCount() + "minute:" + minute);
//        if (chart.getVisibleXRange() > axis.getLabelCount()) {
//            return minute % 4 == 3 ? minuteStr : "";
//        } else if (chart.getVisibleXRange() > axis.getLabelCount() / 2) {
//            return minute % 3 == 2 ? minuteStr : "";
//        } else if (chart.getVisibleXRange() > axis.getLabelCount() / 3) {
//            return minute % 2 == 1 ? minuteStr : "";
//        } else {
        return minuteStr;
//        }

    }

}
