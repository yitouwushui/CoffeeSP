package com.cjmex.coffeesp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cjmex.coffeesp.R;


/**
 *
 * @author ding
 * @date 2016/3/14
 */
public class HideEditText extends AppCompatEditText {


    private Drawable showIcon;
    private Drawable hideIcon;
    private boolean isChecked = false;

    /**
     * 构造方法【必需】
     *
     * @param context
     * @param attrs
     */
    public HideEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        // 加载图标
        showIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_24dp);
        hideIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off_24dp);
        // 设置图标边框
        showIcon.setBounds(0, 0, showIcon.getIntrinsicWidth(), showIcon.getIntrinsicHeight());
        hideIcon.setBounds(0, 0, showIcon.getIntrinsicWidth(), showIcon.getIntrinsicHeight());

        // 焦点改变监听器
        setOnFocusChangeListener((v, hasFocus) -> {
            hasFocusIsShow(hasFocus);

        });
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        // 点击监听器
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (MotionEvent.ACTION_UP == event.getAction()) {
                    // 点击图片区域
                    // TODO 关于内边距
                    if (event.getX() > getWidth() - showIcon.getIntrinsicWidth()) {
                        if (isChecked) {
                            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                        setCompoundDrawables(null, null, isChecked ? showIcon : hideIcon, null);
                        isChecked = !isChecked;
                        return true;
                    }
                }
                return false;
            }
        });
        // 文本内容改变
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 是否显示图标
                setCompoundDrawables(
                        null,
                        null,
                        s.length() > 0 ? isChecked ? hideIcon : showIcon : null,
                        null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 焦点改变是否显示图标
     *
     * @param hasFocus
     */
    public void hasFocusIsShow(boolean hasFocus) {
        setCompoundDrawables(null, null,
                (hasFocus && (getText().length() > 0)) ? isChecked ? showIcon : hideIcon : null,
                null);
    }


}