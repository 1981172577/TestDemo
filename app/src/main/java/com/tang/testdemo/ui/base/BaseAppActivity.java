package com.tang.testdemo.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.tang.testdemo.app.MyApplication;
import com.tang.testdemo.dagger.Component.AppComponent;

public abstract class BaseAppActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        inject(getAppcompoent());
        initView();
        initData();
    }

    protected abstract void inject(AppComponent appcompoent);

    protected void initView() {

    }

    protected void initData() {

    }

    protected abstract int getLayoutId();



    /**
     * 处理5.0以上版本状态栏不透明
     */
    protected  void dealStatusBarStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected AppComponent getAppcompoent(){
        return getMyApplication().getAppComponent();
    }

    protected MyApplication getMyApplication(){
        return MyApplication.getInstance();
    }
}
