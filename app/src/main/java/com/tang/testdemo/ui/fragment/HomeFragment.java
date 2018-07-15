package com.tang.testdemo.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tang.testdemo.R;
import com.tang.testdemo.base.BaseFragment;
import com.tang.testdemo.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.home_tv_address)
    TextView homeTvAddress;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.home_address)
    RelativeLayout homeAddress;
    @BindView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R.id.ll_title_container)
    LinearLayout llTitleContainer;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public static HomeFragment getInstance(AppCompatActivity atc){
        HomeFragment homeFragment = (HomeFragment) atc.getSupportFragmentManager().findFragmentByTag(TAG);
        if(homeFragment == null){
            homeFragment = new HomeFragment();
        }
        return  homeFragment;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        llTitleContainer.setPadding(0, ViewUtils.getStatusBarHeight(mContext),0,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.iv_scan, R.id.iv_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                break;
            case R.id.iv_msg:
                break;
        }
    }


}
