package com.tang.testdemo.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tang.testdemo.R;
import com.tang.testdemo.base.BaseAppActivity;
import com.tang.testdemo.bean.User;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.dagger.Component.DaggerLoginComponent;
import com.tang.testdemo.dagger.Component.LoginComponent;
import com.tang.testdemo.moudel.viewModel.LoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseAppActivity {

    @BindView(R.id.loginimg)
    ImageView loginimg;
    @BindView(R.id.login_useredt)
    EditText loginUseredt;
    @BindView(R.id.login_pwdedt)
    EditText loginPwdedt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.register_btn_lv)
    Button registerBtnLv;

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
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        loginViewModel.getLoginBean().observe(this, loginObserver);
    }

    private Observer loginObserver = (Observer<User>) user -> {
        if (user != null && !TextUtils.isEmpty(user.getMemPin())) {
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        } else {
            String resultMas = user != null ? user.getErrorMsg() : "";
            Toast.makeText(mContext, getString(R.string.login_error, resultMas), Toast.LENGTH_SHORT).show();
        }
    };

    @OnTextChanged(value = {R.id.login_useredt, R.id.login_pwdedt},
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onLoginTextChange() {
        Editable userName = loginUseredt.getText();
        Editable pwd = loginPwdedt.getText();
        loginBtn.setEnabled(userName.length() > 0 && pwd.length() > 0);
    }


    @OnClick({R.id.login_btn, R.id.register_btn_lv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                checkUser();
                break;
            case R.id.register_btn_lv:
                break;
        }
    }

    private void checkUser() {
        String userName = loginUseredt.getText().toString().trim();
        String pwd = loginPwdedt.getText().toString().trim();
        loginViewModel.login(userName,pwd);
    }

}
