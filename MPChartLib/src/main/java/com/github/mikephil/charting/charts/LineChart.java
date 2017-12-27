
package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;

/**
 * Chart that draws lines, surfaces, circles, ...
 *
 * @author Philipp Jahoda
 */
public class LineChart extends BarLineChartBase<LineData> implements LineDataProvider {

    /**
     * 手势回调接口
     */
    private ITouch touch;
    /**
     * 是否拦截
     */
    private boolean isIntercept = true;
    /**
     * 记录位置
     */
    private float x1, y1;


    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mRenderer = new LineChartRenderer(this, mAnimator, mViewPortHandler);
    }

    @Override
    public LineData getLineData() {
        return mData;
    }

    @Override
    protected void onDetachedFromWindow() {
        // releases the bitmap in the renderer to avoid oom error
        if (mRenderer != null && mRenderer instanceof LineChartRenderer) {
            ((LineChartRenderer) mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

    public ITouch getTouch() {
        return touch;
    }

    public void setTouch(ITouch touch) {
        this.touch = touch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("lineChartTouch", "event" + event.getAction());
        if (touch != null) {
            touch.isOnTouch(true);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                float x2 = event.getX();
                float y2 = event.getY();
                float x = Math.abs(x2 - x1) + 20;
                float y = Math.abs(y2 - y1);
                if (x > y ) {
                    isIntercept = true;
                } else {
                    isIntercept = false;
                }
        }
        if (touch != null) {
            touch.isOnTouch(isIntercept);
        }
        return super.onTouchEvent(event);
    }

    public interface ITouch {
        void isOnTouch(boolean b);
    }
}
