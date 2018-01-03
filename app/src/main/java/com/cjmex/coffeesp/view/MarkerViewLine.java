
package com.cjmex.coffeesp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class MarkerViewLine extends MarkerView {

//    private static final int POW_10[] = {
//            1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000
//    };


    /**
     * 绘制x
     */
    private int translateX;
    /**
     * 绘制y
     */
    private int translateY;
    private DecimalFormat format;

//    private int parentWidth;
//    private int parentHeight;

    private TextView tv_time_minute, tv_height_point;

    public MarkerViewLine(Context context, int layoutResource) {
        super(context, layoutResource);
        tv_time_minute = findViewById(R.id.tv_time_minute);
        tv_height_point = findViewById(R.id.tv_height_point);
        format = new DecimalFormat("###");

    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
//            tvOpen.setText(formatNumber(e.getY(), 0, true, ','));
        tv_time_minute.setText("时间：" + e.getData());
        tv_height_point.setText("价格：" + e.getY() + "元");
        super.refreshContent(e, highlight);
    }

    /**
     * event 手势监听
     *
     * @param action       手势交互种类
     * @param x            x坐标
     * @param y            y坐标
     * @param parentWidth  父布局宽度
     * @param parentHeight 父布局高度
     */
    public void setEventPosition(int action, float x, float y, int parentWidth, int parentHeight) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (x <= (parentWidth / 2)) {
                    translateX = parentWidth - getRight();
                    translateY = (parentHeight / 2 - getTop()) - getHeight() / 2;
                } else {
                    translateX = -(getRight() - getWidth());
                    translateY = (parentHeight / 2 - getTop()) - getHeight() / 2;
                }
                break;
            default:
        }
    }

    /**
     * 绘图位置
     *
     * @param canvas
     * @param posx
     * @param posy
     */
    @Override
    public void draw(Canvas canvas, float posx, float posy) {
//        super.draw(canvas, posx, posy);
        // take offsets into consideration
        posx = translateX;
        posy = translateY;

        // translate to the correct position and draw
        canvas.translate(posx, posy);
        draw(canvas);
        canvas.translate(-posx, -posy);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 200;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 200;//根据自己的需要更改
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    //    @Override
//    public int getXOffset(float xpos) {
//        // this will center the marker-view horizontally
//        return -(getWidth() / 2);
//    }
//
//    @Override
//    public int getYOffset(float ypos) {
//        // this will cause the marker-view to be above the selected value
//        return -getHeight();
//    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
