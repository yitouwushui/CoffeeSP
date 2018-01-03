package com.cjmex.coffeesp.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.uitls.Const;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class XYMarkerView extends MarkerView {


    private TextView tvContent,tvContent2,tvContent3,tvContent4;
    private View markerView;

    private DecimalFormat format;

    public XYMarkerView(Context context) {
        super(context, R.layout.custom_marker_view);
        markerView = findViewById(R.id.marker_view);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvContent2 = (TextView) findViewById(R.id.tvContent2);
        tvContent3 = (TextView) findViewById(R.id.tvContent3);
        tvContent4 = (TextView) findViewById(R.id.tvContent4);
        format = new DecimalFormat("###");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        BarEntry barEntry = ((BarEntry) e);
        String city = null;
        String month = null;
        String x = (String) barEntry.getData();
        if (x != null && !x.equals("")) {
            city = Const.cityName.get(x.substring(0, 1));
            month = x.substring(1, x.length());
            markerView.setVisibility(VISIBLE);
        } else {
            markerView.setVisibility(GONE);
        }
        tvContent.setText("城市: " + city);
        tvContent2.setText("月份: " + month+"月");
        tvContent3.setText("已脱贫: " + format.format(barEntry.getYVals()[0]) + "人");
        tvContent4.setText("未脱贫: " + format.format(barEntry.getYVals()[1]) + "人");

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
