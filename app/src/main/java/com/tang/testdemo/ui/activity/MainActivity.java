package com.tang.testdemo.ui.activity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tang.testdemo.R;
import com.tang.testdemo.base.BaseAppActivity;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.ui.widget.NoScrollViewpager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseAppActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.viewpager)
    NoScrollViewpager viewpager;
    @BindView(R.id.design_bottom_sheet)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(AppComponent appcompoent) {
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initBottomBar();
    }

    private void initBottomBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_black_24dp, R.string.title_home).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_dashboard_black_24dp, R.string.title_catgray).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_shopping_cart_black_24dp, R.string.title_shopCar).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_perm_identity_black_24dp, R.string.title_person).setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
