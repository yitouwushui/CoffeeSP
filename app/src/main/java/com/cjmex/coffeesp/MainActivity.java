package com.cjmex.coffeesp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cjmex.coffeesp.mvp.about.AboutFragment;
import com.cjmex.coffeesp.mvp.data.DataFragment;
import com.cjmex.coffeesp.mvp.totalsales.TotalSalesFragment;
import com.cjmex.coffeesp.uitls.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 自助咖啡机总销售额页面
 *
 * @author ding
 */
public class MainActivity extends AppCompatActivity {

    private static final String FIRST = "FIRST";
    private static final String SECOND = "SECOND";
    private static final String THIRD = "THIRD";

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.bt_home)
    Button btHome;
    @BindView(R.id.bt_data)
    Button btData;
    @BindView(R.id.bt_about)
    Button btAbout;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    Unbinder unbinder;
    FragmentManager fm;
    TotalSalesFragment totalSalesFragment;
    DataFragment dataFragment;
    AboutFragment aboutFragment;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        unbinder = ButterKnife.bind(this);
        totalSalesFragment = new TotalSalesFragment();
        dataFragment = new DataFragment();
        aboutFragment = new AboutFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, totalSalesFragment, FIRST);
        fragmentTransaction.add(R.id.frameLayout, dataFragment, SECOND);
        fragmentTransaction.add(R.id.frameLayout, aboutFragment, THIRD);
        fragmentTransaction.show(currentFragment = totalSalesFragment);
        fragmentTransaction.hide(dataFragment);
        fragmentTransaction.hide(aboutFragment).commit();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.bt_home, R.id.bt_data, R.id.bt_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_home:
                showFragment(totalSalesFragment);
                break;
            case R.id.bt_data:
                showFragment(dataFragment);
                break;
            case R.id.bt_about:
                showFragment(aboutFragment);
                break;
        }
    }

    /**
     * 显示相应的fragment
     *
     * @param showFragment
     */
    private void showFragment(Fragment showFragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        if(showFragment != null){
            currentFragment = showFragment;
            ft.show(showFragment).commit();
        } else {
            UIUtils.showToast(this,"显示错误....");
        }
    }
}
