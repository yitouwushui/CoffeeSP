package com.cjmex.coffeesp.mvp.scan;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjmex.coffeesp.BitmapUtil;
import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.mvp.base.AbstractMvpFragment;
import com.cjmex.coffeesp.zxing.android.CaptureActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends AbstractMvpFragment<IScanView, ScanPresenter> implements IScanView {

    View mView;
    Unbinder mUnbinder;
    ScanPresenter mScanPresenter;
    @BindView(R.id.btn_scanning)
    Button btnScanning;
    @BindView(R.id.tv_scanning_result)
    TextView tvScanningResult;
    @BindView(R.id.btn_scanning2)
    Button btnScanning2;
    @BindView(R.id.tv_scanning_result2)
    TextView tvScanningResult2;
    @BindView(R.id.et_erweima_content)
    EditText etErweimaContent;
    @BindView(R.id.btn_make_erweima)
    Button btnMakeErweima;
    @BindView(R.id.result_img)
    ImageView resultImg;


    /**
     * 扫咖啡豆
     */
    private static final int SCANNING_CODE = 1;
    /**
     * 扫机器码
     */
    private static final int SCANNING_CODE_2 = 2;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    private RxPermissions rxPermissions;

    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            rxPermissions = new RxPermissions(getActivity());
            mView = inflater.inflate(R.layout.fragment_scan, container, false);
            mUnbinder = ButterKnife.bind(this, mView);
//            init();
        }
        return mView;
    }

    private void init() {


    }

    @Override
    protected ScanPresenter createPresenter() {
        return null;
    }


    //点击跳转到扫描界面
    public void canningMethod(int requestCode) {
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        //如果已经授权就直接跳转到二维码扫面界面
                        Intent intent = new Intent(getContext(),
                                CaptureActivity.class);
                        intent.putExtra("requestCode", requestCode);
                        startActivityForResult(intent, requestCode);
                        Toast.makeText(getContext(), "扫一扫", Toast.LENGTH_SHORT).show();
                    } else { // Oups permission denied
                        Toast.makeText(getContext(), "相机权限被拒绝，无法扫描二维码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SCANNING_CODE:
                    if (data != null) {
                        String content = data.getStringExtra(DECODED_CONTENT_KEY);
                        Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                        tvScanningResult.setText("咖啡豆码： " + content);
                    }
                    break;
                case SCANNING_CODE_2:
                    if (data != null) {
                        String content = data.getStringExtra(DECODED_CONTENT_KEY);
                        Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                        tvScanningResult2.setText("机器码： " + content);
                    }
                    break;
                default:
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
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public void requestScan(int type) {

    }

    @Override
    public void uploadQrCode(Map<String, Object> map) {

    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @OnClick({R.id.btn_scanning, R.id.btn_scanning2, R.id.btn_make_erweima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scanning:
                canningMethod(SCANNING_CODE);
                break;
            case R.id.btn_scanning2:
                canningMethod(SCANNING_CODE_2);
                break;
            case R.id.btn_make_erweima:
                makeErweimaMethod(view);
                break;
        }
    }
}
