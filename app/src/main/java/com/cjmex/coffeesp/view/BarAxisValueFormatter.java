package com.cjmex.coffeesp.view;

import com.cjmex.coffeesp.uitls.LogUtils;
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

    public BarAxisValueFormatter(BarChart chart, List<BarEntry> mTime) {
        this.chart = chart;
        this.mTime = mTime;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int minute = (int) value;
        String minuteStr;
        if (minute < mTime.size() && minute >= 0) {
            minuteStr = (String) mTime.get(minute).getData();
        } else {
            minuteStr = "";
        }
        LogUtils.i("x"+chart.getVisibleXRange()+" : " + "c:" + axis.getLabelCount() );
//        if (chart.getVisibleXRange() > axis.getLabelCount()) {
//            return minute % 4 == 3 ? minuteStr + " 月" : "";
//        } else if (chart.getVisibleXRange() > axis.getLabelCount() / 2) {
//            return minute % 3 == 2 ? minuteStr + " 月" : "";
//        } else if (chart.getVisibleXRange() > axis.getLabelCount() / 3) {
//            return minute % 2 == 1 ? minuteStr + " 月" : "";
//        } else {
            return minuteStr;
//        }

    }

}
