package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.mvp.base.AbstractMvpFragment;
import com.cjmex.coffeesp.view.GesturesScrollView;
import com.cjmex.coffeesp.view.MarkerViewLine;
import com.cjmex.coffeesp.view.TimeAxisValueFormatter;
import com.cjmex.coffeesp.view.banner.CBViewHolderCreator;
import com.cjmex.coffeesp.view.banner.ConvenientBanner;
import com.cjmex.coffeesp.view.banner.Holder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ding
 */
public class TotalSalesFragment extends AbstractMvpFragment<ITotalSalesView, TotalSalesPresenter>
        implements ITotalSalesView {

    @BindView(R.id.convenient_banner)
    ConvenientBanner<Drawable> convenientBanner;
    View mView;
    Unbinder unbinder;
    TotalSalesPresenter totalSalesPresenter;
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.chart2)
    LineChart chart2;
    @BindView(R.id.chart3)
    LineChart chart3;

    ChartListener1 chartListener = new ChartListener1();


    public TotalSalesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_total_sales, container, false);
            unbinder = ButterKnife.bind(this, mView);
            init();
            // 加载广告界面
            totalSalesPresenter.loadAdvertising();

            totalSalesPresenter.requestFirstChartData(20, 10);
        }
        return mView;
    }

    /**
     * 初始化图表
     */
    private void init() {
        // chart1
        chart1.setOnChartGestureListener(new ChartListener1());
        chart1.setOnChartValueSelectedListener(new ChartListener1());

        initChart(chart1);

        initChart(chart2);

        initChart(chart3);

    }

    /**
     * 初始化相应的chart
     *
     * @param chart
     */
    private void initChart(LineChart chart) {

        chart.setTouch(new LineChart.ITouch() {
            @Override
            public void isOnTouch(boolean b) {
                ((GesturesScrollView) mView).requestDisallowInterceptTouchEvent(b);
            }
        });

        chart.setDrawGridBackground(false);

        chart.setNoDataText("没有获取到行情数据");
        // enable touch gestures
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setScaleXEnabled(false);
        // enable scaling and dragging
        // false x,y轴分开缩放
        chart.setPinchZoom(false);
//        Description description = new Description();
//        description.setText("我是一段描述，字体12");
//        description.setTextColor(getResources().getColor(R.color.colorAccent));
//        description.setTextSize(12);
//        description.setPosition(0,);
        chart.setDescription(null);
        // popupWindow
        MarkerViewLine popupWindowLineChart = new MarkerViewLine(getContext(), R.layout.popup_data_view_show);
        // set the marker to the chart
        chart.setMarker(popupWindowLineChart);
        chart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LineChart lineChart = ((LineChart)v);
                ((MarkerViewLine)lineChart.getMarker()).setEventPosition(
                        event.getAction(), event.getX(), event.getY(),
                        lineChart.getWidth(), lineChart.getHeight());
                return false;
            }
        });

        chart.getLegend().setEnabled(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setLabelCount(8, false);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.colorAccent));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.chart1, R.id.chart2, R.id.chart3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chart1:
                break;
            case R.id.chart2:
                break;
            case R.id.chart3:
                break;
                default:
        }
    }

    /**
     * 轮播banner图片holder类
     */
    public class BannerImageHolderView implements Holder<Drawable> {
        private AppCompatImageView mIvBanner;

        @Override
        public View createView(Context context) {
            mIvBanner = (AppCompatImageView) View.inflate(context, R.layout.item_convenient_banner, null);
            return mIvBanner;
        }

        @Override
        public void UpdateUI(Context context, int position, Drawable data) {
            mIvBanner.setImageDrawable(data);
        }
    }

    @Override
    protected TotalSalesPresenter createPresenter() {
        return totalSalesPresenter = new TotalSalesPresenter();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }


    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void requestLoading() {

    }

    @Override
    public void loadAdvertisingSuccess(List<Drawable> result) {
        convenientBanner.setPages(
                new CBViewHolderCreator<BannerImageHolderView>() {
                    @Override
                    public BannerImageHolderView createHolder() {
                        return new BannerImageHolderView();
                    }
                }, result)
                .setPageIndicator(new int[]{R.drawable.indicator_unchecked, R.drawable.indicator_checked})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000);
    }

    @Override
    public void requestData1(List<ArrayList<Entry>> dataList) {
        LineDataSet set1, set2, set3;
        ArrayList<Entry> list = dataList.get(1);
        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(1);
//            set2 = (LineDataSet) chart1.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) chart1.getData().getDataSetByIndex(2);
            set1.setValues(dataList.get(0));
//            set2.setValues(dataList.get(1));
//            set3.setValues(dataList.get(2));
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(dataList.get(0), "咖啡机累计销售金额");

            set1.setColor(Color.RED);
            set1.setHighLightColor(Color.BLUE);
            set1.enableDashedLine(10f, 0f, 0f);
            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setDrawFilled(true);


            XAxis xAxis = chart2.getXAxis();
            xAxis.setLabelCount(list.size());
            xAxis.setValueFormatter(new TimeAxisValueFormatter(chart2, list));

            YAxis left = chart1.getAxisLeft();
            left.setAxisMinimum(set1.getYMin() * 0.9f);
            left.setAxisMaximum(set1.getYMax() * 1.1f);
            YAxis right = chart1.getAxisRight();
            right.setAxisMinimum(set1.getYMin() * 0.9f);
            right.setAxisMaximum(set1.getYMin() * 1.1f);


            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);

            // set data
            chart1.setData(data);
        }
    }

    @Override
    public void requestData2(List<ArrayList<Entry>> dataList) {
        ArrayList<Entry> list = dataList.get(1);
        LineDataSet set1, set2, set3;
        if (chart2.getData() != null &&
                chart2.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart2.getData().getDataSetByIndex(1);
//            set2 = (LineDataSet) chart1.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) chart1.getData().getDataSetByIndex(2);
            set1.setValues(list);
//            set2.setValues(dataList.get(1));
//            set3.setValues(dataList.get(2));
            chart2.getData().notifyDataChanged();
            chart2.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(list, "咖啡豆挂牌数据");

            set1.setColor(Color.RED);
            set1.setHighLightColor(Color.BLUE);
            set1.enableDashedLine(10f, 0f, 0f);
            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setDrawFilled(true);


            XAxis xAxis = chart2.getXAxis();
            xAxis.setLabelCount(list.size());
            xAxis.setValueFormatter(new TimeAxisValueFormatter(chart2, list));

            YAxis left = chart2.getAxisLeft();
            left.setAxisMinimum(set1.getYMin() * 0.9f);
            left.setAxisMaximum(set1.getYMax() * 1.1f);
            YAxis right = chart2.getAxisRight();
            right.setAxisMinimum(set1.getYMin() * 0.9f);
            right.setAxisMaximum(set1.getYMin() * 1.1f);


            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);

            // set data
            chart2.setData(data);
        }
    }

    @Override
    public void requestData3(List<ArrayList<Entry>> dataList) {

    }

    @Override
    public void resultFailure(String result) {

    }

    // 以下为chart图表接口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private class ChartListener1 implements OnChartGestureListener, OnChartValueSelectedListener {

        @Override
        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

        }

        @Override
        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

        }

        @Override
        public void onChartLongPressed(MotionEvent me) {

        }

        @Override
        public void onChartDoubleTapped(MotionEvent me) {

        }

        @Override
        public void onChartSingleTapped(MotionEvent me) {

        }

        @Override
        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

        }

        @Override
        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

        }

        @Override
        public void onChartTranslate(MotionEvent me, float dX, float dY) {

        }

        @Override
        public void onValueSelected(Entry e, Highlight h) {

        }

        @Override
        public void onNothingSelected() {

        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
