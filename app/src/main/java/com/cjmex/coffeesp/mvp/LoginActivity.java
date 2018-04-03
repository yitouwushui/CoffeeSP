package com.cjmex.coffeesp.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.Account;
import com.cjmex.coffeesp.bean.BaseGson;
import com.cjmex.coffeesp.bean.TotalSaleCupGson;
import com.cjmex.coffeesp.uitls.Const;
import com.cjmex.coffeesp.uitls.LogUtils;
import com.cjmex.coffeesp.uitls.RetrofitUtil;
import com.cjmex.coffeesp.uitls.ToastUtils;
import com.cjmex.coffeesp.view.ClearEditText;
import com.cjmex.coffeesp.view.HideEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author yitouwushui
 */
public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_account)
    ClearEditText etAccount;
    @BindView(R.id.et_psw)
    HideEditText etPsw;
    @BindView(R.id.bt_yes)
    Button btYes;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);
        etAccount.setText("admin");
        etPsw.setText("123456");
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @OnClick(R.id.bt_yes)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String psw = etPsw.getText().toString();
        Call<BaseGson> call = RetrofitUtil.getInstance().getApiService().goLogin(account, psw);
        call.enqueue(new Callback<BaseGson>() {
            @Override
            public void onResponse(@NonNull Call<BaseGson> call, @NonNull Response<BaseGson> response) {
                BaseGson baseGson = response.body();
                if (baseGson == null){
                    ToastUtils.showToast("登录失败");
                    return;
                }
                if (Const.RESPONSE_CODE.equals(baseGson.getResponseCode())){
                    Account.getInstance().setToken(Const.LOGIN_SUCCESSFUL);
                    ToastUtils.showToast("登录成功");
                    finish();
                    return;
                }
                ToastUtils.showToast(baseGson.getResponseMsg());
            }

            @Override
            public void onFailure(@NonNull Call<BaseGson> call, @NonNull Throwable t) {
                ToastUtils.showToast(t.toString());
            }
        });
    }
}
