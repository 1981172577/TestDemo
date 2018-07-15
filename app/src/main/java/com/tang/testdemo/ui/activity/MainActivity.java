package com.tang.testdemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tang.testdemo.R;
import com.tang.testdemo.base.BaseAppActivity;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.ui.fragment.CartGrayFragment;
import com.tang.testdemo.ui.fragment.HomeFragment;
import com.tang.testdemo.ui.fragment.OrderFragment;
import com.tang.testdemo.ui.fragment.PersonFragment;
import com.tang.testdemo.ui.widget.NoScrollViewpager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseAppActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.viewpager)
    NoScrollViewpager viewpager;
    @BindView(R.id.design_bottom_sheet)
    BottomNavigationBar bottomNavigationBar;

    private List<Fragment> fragmentList = new ArrayList<>();

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
        initFragments();
    }

    private void initFragments() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new CartGrayFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new PersonFragment());

        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(4);
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
        if(fragmentList != null && fragmentList.size() > 0  && position < fragmentList.size()){
            viewpager.setCurrentItem(position);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };
}
