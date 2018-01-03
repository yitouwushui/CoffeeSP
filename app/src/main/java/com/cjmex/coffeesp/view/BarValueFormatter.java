package com.cjmex.coffeesp.view;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class BarValueFormatter implements IValueFormatter
{

    private DecimalFormat mFormat;
    
    public BarValueFormatter() {
        mFormat = new DecimalFormat("###,###,###");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        BarEntry barEntry = ((BarEntry) entry);

        return mFormat.format(value) + "";
    }
}
