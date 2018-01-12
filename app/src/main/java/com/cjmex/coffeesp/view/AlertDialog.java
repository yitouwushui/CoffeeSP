package com.cjmex.coffeesp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.TextView;

import com.cjmex.coffeesp.R;


/**
 * @author lq
 * @date 2016-8-23
 */
public class AlertDialog extends Dialog implements View.OnClickListener {
    private View mDialogView;

    private TextView mTitleTextView;

    private TextView mContentTextView;

    private Button mConfirmButton;

    private Animation mModalOutAnim;

    private Animation mOverlayOutAnim;

    private String mContentText;

    private boolean mShowContent;

    private boolean mShowCancel;

    private String mCancelText;

    private String mConfirmText;

    private OnSweetClickListener mCancelClickListener;

    private OnSweetClickListener mConfirmClickListener;

    private Button mCancelButton;

    private View mDivider;

    private String mTitleText;
    private boolean mShowTitle;

    public AlertDialog(Context context) {
        super(context, R.style.alert_dialog);
    }

    public AlertDialog(Context context, int theme) {
        super(context, theme);
    }


    protected AlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public AlertDialog(Context context, String message) {
        super(context, R.style.alert_dialog);
        mContentText = message;
        mCancelText = "取消";
        mConfirmText = "确定";
        setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_alert);


        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mTitleTextView = (TextView) findViewById(R.id.title_text);
        mContentTextView = (TextView) findViewById(R.id.content_text);
        mConfirmButton = (Button) findViewById(R.id.confirm_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mConfirmButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
        mDivider = findViewById(R.id.divider);
        mModalOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.modal_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                            /*if (mCloseFromCancel) {
                                SweetAlertDialog.super.cancel();
                            } else {
                                SweetAlertDialog.super.dismiss();
                            }
                            dismiss();*/
                    }
                });
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // dialog overlay fade out
        mOverlayOutAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                WindowManager.LayoutParams wlp = getWindow().getAttributes();
                wlp.alpha = 1 - interpolatedTime;
                getWindow().setAttributes(wlp);
            }
        };
        mOverlayOutAnim.setDuration(120);

        setTitleText(mTitleText);
        setContentText(mContentText);
        setCancelText(mCancelText);
        setConfirmText(mConfirmText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDialogView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.modal_in));
    }

    @Override
    public void cancel() {
        super.cancel();
        dismissWithAnimation();
    }

    private void dismissWithAnimation() {
        mConfirmButton.startAnimation(mOverlayOutAnim);
        mDialogView.startAnimation(mModalOutAnim);
    }

    public AlertDialog setTitleText(String text) {
        mTitleText = text;
        if (mTitleTextView != null && mTitleText != null) {
            showTitleText(true);
            mTitleTextView.setText(mTitleText);
        }
        return this;
    }

    public AlertDialog showTitleText(boolean isShow) {
        mShowTitle = isShow;
        if (mTitleTextView != null) {
            mTitleTextView.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        }
        return this;
    }


    public AlertDialog setContentText(String text) {
        mContentText = text;
        if (mContentTextView != null && mContentText != null) {
            showContentText(true);
            mContentTextView.setText(mContentText);
        }
        return this;
    }

    public AlertDialog showContentText(boolean isShow) {
        mShowContent = isShow;
        if (mContentTextView != null) {
            mContentTextView.setVisibility(mShowContent ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public boolean isShowCancelButton() {
        return mShowCancel;
    }

    public AlertDialog showCancelButton(boolean isShow) {
        mShowCancel = isShow;
        if (mCancelButton != null) {
            mConfirmButton.setBackgroundResource(R.drawable.right_btn_selector);
            mDivider.setVisibility(View.VISIBLE);
            mCancelButton.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public boolean isShowContentText() {
        return mShowContent;
    }


    public String getCancelText() {
        return mCancelText;
    }

    public AlertDialog setCancelText(String text) {
        mCancelText = text;
        if (mCancelButton != null && mCancelText != null) {
            showCancelButton(true);
            mCancelButton.setText(mCancelText);
        }
        return this;
    }

    public String getConfirmText() {
        return mConfirmText;
    }

    public AlertDialog setConfirmText(String text) {
        mConfirmText = text;
        if (mConfirmButton != null && mConfirmText != null) {
            mConfirmButton.setText(mConfirmText);
        }
        return this;
    }


    /**
     * 按钮接口
     */
    public interface OnSweetClickListener {
        void onClick(AlertDialog dialog);
    }

    public AlertDialog setCancelClickListener(OnSweetClickListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public AlertDialog setConfirmClickListener(OnSweetClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_button) {
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(AlertDialog.this);
            } else {
                dismiss();
            }
        } else if (v.getId() == R.id.confirm_button) {
            if (mConfirmClickListener != null) {
                mConfirmClickListener.onClick(AlertDialog.this);
            } else {
                dismiss();
            }

        }
    }
}
