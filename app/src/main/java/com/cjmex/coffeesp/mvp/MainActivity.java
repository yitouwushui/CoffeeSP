package com.cjmex.coffeesp.mvp;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjmex.coffeesp.R;
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
    View btHome;
    @BindView(R.id.bt_data)
    View btData;
    @BindView(R.id.bt_about)
    View btAbout;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    Unbinder unbinder;
    FragmentManager fm;
    TotalSalesFragment totalSalesFragment;
    DataFragment dataFragment;
    AboutFragment aboutFragment;
    Fragment currentFragment;
    int currentPosition;
    @BindView(R.id.img_bar1)
    ImageView imgBar1;
    @BindView(R.id.tv_bar1)
    TextView tvBar1;
    @BindView(R.id.img_bar2)
    ImageView imgBar2;
    @BindView(R.id.tv_bar2)
    TextView tvBar2;
    @BindView(R.id.img_bar3)
    ImageView imgBar3;
    @BindView(R.id.tv_bar3)
    TextView tvBar3;
    // 导航栏颜色
    int colorB, colorG;
    //图标
    Drawable barLineB, barLineG, barDataG, barDataB, barAboutB, barAboutG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataOfModel model = DataOfModel.getInstance();
        model.initOneMoth(2017,8);
        model.initOneMoth(2017,9);
        model.initOneMoth(2017,10);
        model.initOneMoth(2017,11);

        init();

    }

    private void init() {
        unbinder = ButterKnife.bind(this);
        Resources resources = getResources();
        colorB = resources.getColor(R.color.color_bar_blue);
        colorG = resources.getColor(R.color.color_bar_gray);
        barLineB = resources.getDrawable(R.drawable.bar_line_b);
        barLineG = resources.getDrawable(R.drawable.bar_line_g);
        barDataB = resources.getDrawable(R.drawable.bar_data_b);
        barDataG = resources.getDrawable(R.drawable.bar_data_g);
        barAboutB = resources.getDrawable(R.drawable.bar_about_b);
        barAboutG = resources.getDrawable(R.drawable.bar_about_g);
        totalSalesFragment = new TotalSalesFragment();
        dataFragment = new DataFragment();
        aboutFragment = new AboutFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, totalSalesFragment, FIRST);
        fragmentTransaction.add(R.id.frameLayout, dataFragment, SECOND);
        fragmentTransaction.add(R.id.frameLayout, aboutFragment, THIRD);
        currentPosition = 1;
        imgBar1.setImageDrawable(barLineB);
        tvBar1.setTextColor(colorB);
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
                showFragment(totalSalesFragment, 1);
                break;
            case R.id.bt_data:
                showFragment(dataFragment, 2);
                break;
            case R.id.bt_about:
                showFragment(aboutFragment, 3);
                break;
            default:
        }
    }

    /**
     * 显示相应的fragment
     *
     * @param showFragment
     */
    private void showFragment(Fragment showFragment, int position) {
        if (position != currentPosition) {
            switch (currentPosition) {
                case 1:
                    imgBar1.setImageDrawable(barLineG);
                    tvBar1.setTextColor(colorG);
                    break;
                case 2:
                    imgBar2.setImageDrawable(barDataG);
                    tvBar2.setTextColor(colorG);
                    break;
                case 3:
                    imgBar3.setImageDrawable(barAboutG);
                    tvBar3.setTextColor(colorG);
                    break;
                default:
            }
            switch (position) {
                case 1:
                    imgBar1.setImageDrawable(barLineB);
                    tvBar1.setTextColor(colorB);
                    break;
                case 2:
                    imgBar2.setImageDrawable(barDataB);
                    tvBar2.setTextColor(colorB);
                    break;
                case 3:
                    imgBar3.setImageDrawable(barAboutB);
                    tvBar3.setTextColor(colorB);
                    break;
                default:
            }
            currentPosition = position;
            FragmentTransaction ft = fm.beginTransaction();
            if (currentFragment != null) {
                ft.hide(currentFragment);
            }
            if (showFragment != null) {
                currentFragment = showFragment;
                ft.show(showFragment).commit();
            } else {
                UIUtils.showToast(this, "显示错误....");
            }
        }

    }
}
