package com.cjmex.coffeesp.mvp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cjmex.coffeesp.BitmapUtil;
import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.mvp.scan.ScanPresenter;
import com.cjmex.coffeesp.zxing.android.CaptureActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScanActivity extends AppCompatActivity {
    Unbinder mUnbinder;
    ScanPresenter mScanPresenter;
    @BindView(R.id.btn_scanning)
    Button btnScanning;
    @BindView(R.id.tv_scanning_result)
    TextView tvScanningResult;
    @BindView(R.id.et_erweima_content)
    EditText etErweimaContent;
    @BindView(R.id.btn_make_erweima)
    Button btnMakeErweima;
    @BindView(R.id.result_img)
    ImageView resultImg;

    private static final int SCANNING_CODE = 1;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    private RxPermissions rxPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scan);

        mUnbinder = ButterKnife.bind(this);
        // where this is an Activity instance
        rxPermissions = new RxPermissions(this);

    }


    //点击跳转到扫描界面
    public void canningMethod(View v) {
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        //如果已经授权就直接跳转到二维码扫面界面
                        Intent intent = new Intent(ScanActivity.this,
                                CaptureActivity.class);
                        startActivityForResult(intent, SCANNING_CODE);

                        Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                    } else { // Oups permission denied
                        Toast.makeText(this, "相机权限被拒绝，无法扫描二维码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == SCANNING_CODE && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                tvScanningResult.setText("扫描结果： " + content);

            }
        }
    }

    //点击生成带图片的二维码
    public void makeErweimaMethod(View v) {
        String aimContent = etErweimaContent.getText().toString();
        Create2QR2(aimContent, resultImg);
    }

    //生成二维码的方法
    private void Create2QR2(String urls, ImageView imageView) {
        String uri = urls;
        int mScreenWidth = 0;
        Bitmap bitmap;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            mScreenWidth = dm.widthPixels;

            bitmap = BitmapUtil.createQRImage(uri, mScreenWidth,
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));//自己写的方法

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @OnClick({R.id.btn_scanning, R.id.btn_make_erweima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scanning:
                canningMethod(view);
                break;
            case R.id.btn_make_erweima:
                makeErweimaMethod(view);
                break;
        }
    }

}
