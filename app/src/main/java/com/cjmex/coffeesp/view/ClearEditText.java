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

import com.cjmex.coffeesp.R;


/**
 *
 * @author yitouwushui
 * @date 2016/3/14
 */
public class ClearEditText extends AppCompatEditText {

    // 清除文本内容的图标
    private Drawable clearIcon;
    boolean hasFocus = false;

    /**
     * 构造方法【必需】
     *
     * @param context
     * @param attrs
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void setShowText(boolean isShow) {
        if (isShow) {
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        // 加载图标
        clearIcon = ContextCompat.getDrawable(context, R.drawable.ic_clear_24dp);
        // 设置图标边框
        clearIcon.setBounds(
                0,
                0,
                clearIcon.getIntrinsicWidth(),
                clearIcon.getIntrinsicHeight());
        // 焦点改变监听器
        setOnFocusChangeListener((v, hasFocus) -> hasFocusIsShow(ClearEditText.this.hasFocus = hasFocus));

        // 点击监听器
        setOnTouchListener((v, event) -> {

            if (MotionEvent.ACTION_UP == event.getAction()) {
                // 点击图片区域
                // TODO 关于内边距
                if (event.getX() > getWidth() - clearIcon.getIntrinsicWidth()) {
                    setText("");
                    return true;
                }
            }
            return false;
        });
        // 文本内容改变
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChangedIsShow(s);
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
        setCompoundDrawables(
                null,
                null,
                (hasFocus && (getText().length() > 0)) ? clearIcon : null,
                null);
    }

    /**
     * 文本改变是否显示图标
     *
     * @param s
     */
    public void textChangedIsShow(CharSequence s) {
        // 是否显示图标
        setCompoundDrawables(
                null,
                null,
                s.length() > 0 && hasFocus ? clearIcon : null,
                null);
    }


}