package com.cjmex.coffeesp.mvp.totalsales;

import android.content.Context;
import android.content.res.Resources;
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
import com.cjmex.coffeesp.mvp.MainActivity;
import com.cjmex.coffeesp.mvp.base.AbstractMvpFragment;
import com.cjmex.coffeesp.uitls.Const;
import com.cjmex.coffeesp.uitls.DensityUtils;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.view.BarAxisValueFormatter;
import com.cjmex.coffeesp.view.GesturesScrollView;
import com.cjmex.coffeesp.view.LineChartValueFormatter;
import com.cjmex.coffeesp.view.MarkerViewLine;
import com.cjmex.coffeesp.view.BarValueFormatter;
import com.cjmex.coffeesp.view.TimeAxisValueFormatter;
import com.cjmex.coffeesp.view.XYMarkerView;
import com.cjmex.coffeesp.view.banner.CBViewHolderCreator;
import com.cjmex.coffeesp.view.banner.ConvenientBanner;
import com.cjmex.coffeesp.view.banner.Holder;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.bar_chart)
    BarChart barChart;
//    @BindView(R.id.pie1)
//    PieChart pie1;


    //    protected String[] mParties = new String[]{
//            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
//            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
//            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
//            "Party Y", "Party Z"
//    };
    Drawable[] citysIcon = new Drawable[5];

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

            //请求假数据
//            totalSalesPresenter.requestFirstChartData(MainActivity.dataSize, 10, MainActivity.startMonth);
            // 真数据
            totalSalesPresenter.requersData();
//            //请求
//            totalSalesPresenter.requestModelData();

            setBarChartData();
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

        initLineChart(chart1);

        initLineChart(chart2);

        initLineChart(chart3);

//        Description description = new Description();
//        description.setText("其中每杯1元咖农补贴，1元作为报价专项基金");
//        description.setTextColor(getResources().getColor(R.color.colorAccent));
//        description.setTextSize(10);
//        description.setTextAlign(Paint.Align.RIGHT);
////        LogUtils.d("坐标",chart1.get+" : "+chart1.getLeft());
////        description.setPosition(0, DensityUtils.dp2px(getContext(), 160));
//        chart1.setDescription(description);

//        initPie(pie1);

        initBarChart();
    }

    private void initBarChart() {

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(40);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleXEnabled(false);
        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        barChart.setDrawValueAboveBar(false);
        barChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        barChart.getAxisRight().setEnabled(false);

        XAxis xLabels = barChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);

        // barChart.setDrawXLabels(false);
        // barChart.setDrawYLabels(false);

        Resources resources = getContext().getResources();
        citysIcon[0] = resources.getDrawable(R.drawable.icon_a);
        citysIcon[1] = resources.getDrawable(R.drawable.icon_b);
        citysIcon[2] = resources.getDrawable(R.drawable.icon_c);
        citysIcon[3] = resources.getDrawable(R.drawable.icon_d);
        citysIcon[4] = null;


        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
    }

    private void setBarChartData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        int count = 0;
        float valstart1 = 20;
        float valstart2 = 100;
        for (int i = 0; i < Const.citys.length * 3 - 1; i++) {
            float mult = (10 + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 2;
            valstart1 += val1;
            valstart2 += val2;
//            float val3 = (float) (Math.random() * mult) + mult / 3;
            if (i != 0 && i % (Const.citys.length) == Const.citys.length - 1) {
                yVals1.add(new BarEntry(
                        i, new float[]{0, 0}, ""));
                count++;
            } else {
                int start = MainActivity.currentMonth - 3;
                if (start > 12) {
                    start -= 12;
                }
                if (start <= 0) {
                    start += 12;
                }
                yVals1.add(new BarEntry(
                        i,
                        new float[]{valstart1, valstart2},
                        citysIcon[(i) % Const.citys.length],
                        Const.citys[(i) % Const.citys.length] + (count + start)));
            }
        }

        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");

            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"已脱贫", "未脱贫"});
            set1.setValueTextSize(DensityUtils.sp2px(getContext(), 4));
            set1.setDrawIcons(true);
//            set1.setIconsOffset(new MPPointF(0, -15));

            IAxisValueFormatter xAxisFormatter = new BarAxisValueFormatter(barChart, yVals1);
            XAxis xLabels = barChart.getXAxis();
            xLabels.setLabelCount(yVals1.size());
            xLabels.setValueFormatter(xAxisFormatter);

            xLabels.setTextSize(DensityUtils.sp2px(getContext(), 6));

            XYMarkerView mv = new XYMarkerView(getContext());
            mv.setChartView(barChart); // For bounds control
            barChart.setMarker(mv); // Set the marker to the chart

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new BarValueFormatter());
            data.setValueTextColor(Color.BLACK);

            barChart.setData(data);


        }

        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private int[] getColors() {

        int stacksize = 2;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }

        return colors;
    }

//    private void initPie(PieChart mPieChart) {
//        mPieChart.setUsePercentValues(true);
//        mPieChart.getDescription().setEnabled(false);
//        mPieChart.setExtraOffsets(5, 10, 5, 5);
//
//        mPieChart.setDragDecelerationFrictionCoef(0.95f);
//        mPieChart.setCenterText(generateCenterSpannableText());
//
//        mPieChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);
//
//        mPieChart.setDrawHoleEnabled(true);
//        mPieChart.setHoleColor(Color.WHITE);
//
//        mPieChart.setCenterTextColor(Color.GRAY);
//        mPieChart.setTransparentCircleColor(Color.WHITE);
//        mPieChart.setTransparentCircleAlpha(110);
//
//        mPieChart.setHoleRadius(58f);
//        mPieChart.setTransparentCircleRadius(61f);
//
//        mPieChart.setDrawCenterText(true);
//
//        mPieChart.setRotationAngle(0);
//        // enable rotation of the chart by touch
//        mPieChart.setRotationEnabled(true);
//        mPieChart.setHighlightPerTapEnabled(true);
//
//        // mPieChart.setUnit(" €");
//        // mPieChart.setDrawUnitsInChart(true);
//
//        // add a selection listener
//        mPieChart.setOnChartValueSelectedListener(new PieListener());
//
//        setPieData(8, 100);
//
//        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        // mPieChart.spin(2000, 0, 360);
//
//        Legend l = mPieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setEnabled(false);
//    }
//
//    private void setPieData(int count, float range) {
//
//        float mult = range;
//
//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        for (int i = 0; i < count; i++) {
//            entries.add(new PieEntry((float) (Math.random() * mult) + mult / 5, mParties[i % mParties.length]));
//        }
//
//        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
//        dataSet.setSliceSpace(3f);
//        dataSet.setSelectionShift(5f);
//
//        // add a lot of colors
//
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
//
//        dataSet.setColors(colors);
//        //dataSet.setSelectionShift(0f);
//
//
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
//        dataSet.setValueLinePart2Length(0.4f);
//        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//
//        PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
//        data.setValueTextSize(11f);
//        data.setValueTextColor(Color.BLACK);
//        pie1.setData(data);
//
//        // undo all highlights
//        pie1.highlightValues(null);
//
//        pie1.invalidate();
//    }
//
//    public class PieListener implements OnChartValueSelectedListener {
//
//        @Override
//        public void onValueSelected(Entry e, Highlight h) {
//
//        }
//
//        @Override
//        public void onNothingSelected() {
//
//        }
//    }
//
//    private SpannableString generateCenterSpannableText() {
//
//        SpannableString s = new SpannableString("咖农建档立卡户数量\n与累计脱贫户占比\n数据来源于 by chang jiang");
//        s.setSpan(new RelativeSizeSpan(1.1f), 0, 18, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 18, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 18, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.65f), 18, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 18, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 15, s.length(), 0);
//        return s;
//    }

    /**
     * 初始化相应的chart
     *
     * @param chart
     */
    private void initLineChart(LineChart chart) {

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
        chart.setDescription(null);
        // popupWindow
        MarkerViewLine popupWindowLineChart = new MarkerViewLine(getContext(), R.layout.popup_data_view_show);
        // set the marker to the chart
        chart.setMarker(popupWindowLineChart);
        chart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LineChart lineChart = ((LineChart) v);
                ((MarkerViewLine) lineChart.getMarker()).setEventPosition(
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
        xAxis.setSpaceMax(0.5f);
        xAxis.setSpaceMin(0.5f);
        xAxis.setAvoidFirstLastClipping(true);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setLabelCount(8, false);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.colorBlack333333));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    @OnClick({R.id.chart1, R.id.chart2, R.id.chart3})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.chart1:
//                break;
//            case R.id.chart2:
//                break;
//            case R.id.chart3:
//                break;
//            default:
//        }
//    }

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
        ArrayList<Entry> list = dataList.get(0);
        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart1.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) chart1.getData().getDataSetByIndex(2);
            set1.setValues(dataList.get(0));
            set2.setValues(dataList.get(1));
//            set3.setValues(dataList.get(2));
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {

//            chart1.getAxisRight().setEnabled(true);
//            YAxis leftAxis = chart1.getAxisLeft();
//            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//            leftAxis.setLabelCount(8, false);
//            leftAxis.enableGridDashedLine(10f, 10f, 0f);
//            leftAxis.setDrawZeroLine(false);
//            leftAxis.setDrawLimitLinesBehindData(false);

            // create a dataset and give it a type
            set1 = new LineDataSet(dataList.get(0), "咖啡机每月销售额(元)");
            set1.setColor(getResources().getColor(R.color.colorMainBlue));
            set1.setHighLightColor(Color.BLUE);
            set1.enableDashedLine(10f, 0f, 0f);
            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setDrawFilled(true);

            set2 = new LineDataSet(dataList.get(1), "咖啡机累计销售额(元)");
            set2.setColor(Color.RED);
            set2.setHighLightColor(Color.RED);
            set2.enableDashedLine(10f, 0f, 0f);
            set2.setDrawValues(false);
            set2.setDrawCircles(false);
            set2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            set2.enableDashedHighlightLine(10f, 0f, 0f);
            set2.setLineWidth(1f);
            set2.setDrawCircleHole(false);
            set2.setDrawFilled(true);


            XAxis xAxis = chart1.getXAxis();
            xAxis.setLabelCount(12);
            xAxis.setAvoidFirstLastClipping(true);
            xAxis.setValueFormatter(new TimeAxisValueFormatter(chart1, list));

            YAxis left = chart1.getAxisLeft();
            float min = set2.getYMin() - (set2.getYMax() - set2.getYMin()) * 0.3f;
            if (min < 0) {
                min = 0;
            }
            left.setAxisMinimum(min);

            left.setAxisMaximum(set2.getYMax() + (set2.getYMax() - set2.getYMin()) * 0.3f);
//            left.setAxisMaximum(set1.getYMax() + (set1.getYMax() - set1.getYMin()) * 0.3f);
//            YAxis right = chart1.getAxisRight();
//            right.setAxisMinimum(set2.getYMin() - (set2.getYMax() - set2.getYMin()) * 0.3f);
//            right.setAxisMaximum(set2.getYMax() + (set2.getYMax() - set2.getYMin()) * 0.3f);


            // create a data object with the datasets
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);
            data.setValueFormatter(new LineChartValueFormatter());

            // set data
            chart1.setData(data);
        }
    }

    @Override
    public void requestData2(List<ArrayList<Entry>> dataList) {
        ArrayList<Entry> list = dataList.get(1);
        LineDataSet set1;
        if (chart2.getData() != null &&
                chart2.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart2.getData().getDataSetByIndex(1);

            set1.setValues(list);
            chart2.getData().notifyDataChanged();
            chart2.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(list, "咖啡豆销售金额(元)");
            set1.setColor(Color.RED);
            set1.setHighLightColor(Color.RED);

            settingXAndY(list, set1, chart2);

            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);
            data.setValueFormatter(new LineChartValueFormatter());

            // set data
            chart2.setData(data);
            chart2.notifyDataSetChanged();
        }
    }

    @Override
    public void requestData3(List<ArrayList<Entry>> dataList) {
        ArrayList<Entry> list = dataList.get(2);
        LineDataSet set1;
        if (chart3.getData() != null &&
                chart3.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart3.getData().getDataSetByIndex(2);
            set1.setValues(list);
            chart3.getData().notifyDataChanged();
            chart3.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(list, "咖啡豆购买金额(元)");
            set1.setColor(Color.GRAY);
            set1.setHighLightColor(Color.GRAY);

            settingXAndY(list, set1, chart3);

            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);
            data.setValueFormatter(new LineChartValueFormatter());

            // set data
            chart3.setData(data);
        }
    }

    /**
     * 统一设置x和y轴数据
     *
     * @param list
     * @param set1
     * @param chart
     */
    private void settingXAndY(ArrayList<Entry> list, LineDataSet set1, LineChart chart) {
        set1.enableDashedLine(10f, 0f, 0f);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set1.enableDashedHighlightLine(10f, 0f, 0f);
        set1.setLineWidth(1f);
        set1.setDrawCircleHole(false);
        set1.setDrawFilled(true);

        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelCount(12);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new TimeAxisValueFormatter(chart, list));

        YAxis left = chart.getAxisLeft();
        float min = set1.getYMin() - (set1.getYMax() - set1.getYMin()) * 0.3f;
        if (min < 0) {
            min = 0;
        }
        left.setAxisMinimum(min);
        left.setAxisMaximum(set1.getYMax() + (set1.getYMax() - set1.getYMin()) * 0.3f);
//        YAxis right = chart.getAxisRight();
//        right.setAxisMinimum(set1.getYMin() - (set1.getYMax() - set1.getYMin()) * 0.3f);
//        right.setAxisMaximum(set1.getYMax() + (set1.getYMax() - set1.getYMin()) * 0.3f);
    }

    @Override
    public void resultFailure(String result) {

    }

    // 以下为chart图表接口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private class ChartListener1 implements OnChartGestureListener, OnChartValueSelectedListener {

        @Override
        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            LogUtils.d("test2", lastPerformedGesture.toString());
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
