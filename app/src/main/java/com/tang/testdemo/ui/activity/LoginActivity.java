package com.tang.testdemo.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.tang.testdemo.R;
import com.tang.testdemo.bean.LoginBean;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.dagger.Component.DaggerLoginComponent;
import com.tang.testdemo.dagger.Component.LoginComponent;
import com.tang.testdemo.moudel.viewModel.LoginViewModel;
import com.tang.testdemo.ui.base.BaseAppActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseAppActivity {

    private LoginComponent loginComponent;

    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject(AppComponent appcompoent) {
        loginComponent = DaggerLoginComponent.builder()
                .posComponent(appcompoent)
                .bindActivity(this)
                .build();
        loginComponent.inject(this);
    }

    @Override
    protected void initData() {
        loginViewModel.getLoginBean().observe(this,loginObserver);
    }

    private Observer loginObserver = (Observer<LoginBean>) loginBean -> {
        if(loginBean != null && !TextUtils.isEmpty(loginBean.getAccount())){
            startActivity(new Intent(mContext,MainActivity.class));
            finish();
        }else{
            String resultMas = loginBean != null ? loginBean.getErrorMsg() : "";
            Toast.makeText(mContext,getString(R.string.login_error,resultMas),Toast.LENGTH_SHORT).show();
        }
    };
}
