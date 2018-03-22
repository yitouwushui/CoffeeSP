package com.cjmex.coffeesp.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.cjmex.coffeesp.R;
import com.cjmex.coffeesp.bean.Account;
import com.cjmex.coffeesp.uitls.ToastUtils;
import com.cjmex.coffeesp.view.ClearEditText;
import com.cjmex.coffeesp.view.HideEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        if (account.equals(psw)) {
            Account.getInstance().setToken("23333");
            ToastUtils.showToast("登录成功");
            finish();
        }
    }
}
