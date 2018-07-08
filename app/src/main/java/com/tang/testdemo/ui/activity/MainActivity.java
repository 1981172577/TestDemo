package com.tang.testdemo.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

import com.tang.testdemo.R;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.ui.base.BaseAppActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseAppActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.design_bottom_sheet)
    BottomNavigationView designBottomSheet;

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
    }
}
