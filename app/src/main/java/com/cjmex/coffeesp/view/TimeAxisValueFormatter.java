package com.cjmex.coffeesp.view;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by ding on 09/09/16.
 */
public class TimeAxisValueFormatter implements IAxisValueFormatter {

    private LineChart chart;

    private List<Entry> mTime;

    public TimeAxisValueFormatter(LineChart chart, List<Entry> mTime) {
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
//        if (chart.getVisibleXRange() > axis.getLabelCount()) {
//            return minute % 2 == 1 ? minuteStr : "";
//        } else if (chart.getVisibleXRange() > axis.getLabelCount() / 2) {
//            return minute % 2 == 1 ? minuteStr : "";
//    }
            return minuteStr;

    }

}
