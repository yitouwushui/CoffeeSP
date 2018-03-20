package com.cjmex.coffeesp.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.uitls.LogUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yitouwushui
 */
public class LoadingActivity extends AppCompatActivity {

    int loadingTime = 3;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.loading_activity)
    FrameLayout loadingActivity;
//    @BindView(R.id.img_load)
//    ImageView imgLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        LogUtils.i("生命周期", "---------------------");
        LogUtils.i("生命周期", "LoadingActivity onCreate");

//        Resources resources = getResources();
//        Drawable drawable = resources.getDrawable(R.drawable.img_loading,null);
//        Drawable drawable = ContextCompat.getDrawable(LoadingActivity.this.getApplicationContext(),R.drawable.img_loading);
//        imgLoad.setImageDrawable(drawable);
//        loadingActivity = (FrameLayout) findViewById(R.id.loading_activity);

//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            ImageOprator imageOprator = new ImageOprator();
//            Drawable drawable = new BitmapDrawable(imageOprator.decodeSampledBitmapFromResource(getResources(), R.drawable.loading, this));
//            loadingActivity.setBackground(drawable);
//        }

//        int versionCode = getPreferences(MODE_PRIVATE).getInt(App.VERSION_CODE, 0);
//        if (UIUtils.getVersionCode(this) > versionCode) {

        handler.post(runnable);
//        FileProvider
//        getPreferences(MODE_PRIVATE).edit().putInt(App.VERSION_CODE, UIUtils.getVersionCode(this)).commit();
//        } else {
//            startMain();
//        }

    }

    MyHandler handler = new MyHandler(this);
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tvTime.setText(String.valueOf(loadingTime) + "s");
            loadingTime--;
            if (loadingTime == 0) {
                startMain();
            } else {
                handler.postDelayed(runnable, 800);
            }

        }
    };

    private static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivityReference.get();
            if (activity != null) {
//                mImageView.setImageBitmap(mBitmap);
            }
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
//        handler.removeCallbacks(null);
        super.onDestroy();
        LogUtils.i("生命周期", "LoadingActivity onDestroy");

    }

    /**
     *
     */
    public void startMain() {
        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(intent);
        LoadingActivity.this.finish();
    }
}
