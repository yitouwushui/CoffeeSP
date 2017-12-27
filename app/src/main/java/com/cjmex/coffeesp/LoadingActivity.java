package com.cjmex.coffeesp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends AppCompatActivity {

    int loadingTime = 3;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.loading_activity)
    FrameLayout loadingActivity;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);


//        loadingActivity = (FrameLayout) findViewById(R.id.loading_activity);

//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            ImageOprator imageOprator = new ImageOprator();
//            Drawable drawable = new BitmapDrawable(imageOprator.decodeSampledBitmapFromResource(getResources(), R.drawable.loading, this));
//            loadingActivity.setBackground(drawable);
//        }

//        int versionCode = getPreferences(MODE_PRIVATE).getInt(App.VERSION_CODE, 0);
//        if (UIUtils.getVersionCode(this) > versionCode) {
        handler.post(runnable);
//        getPreferences(MODE_PRIVATE).edit().putInt(App.VERSION_CODE, UIUtils.getVersionCode(this)).commit();
//        } else {
//            startMain();
//        }

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LoadingActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvTime.setText(String.valueOf(loadingTime) + "s");
                }
            });
            loadingTime--;
            if (loadingTime == 0) {
                startMain();
            } else {
                handler.postDelayed(runnable, 800);
            }

        }
    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
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
