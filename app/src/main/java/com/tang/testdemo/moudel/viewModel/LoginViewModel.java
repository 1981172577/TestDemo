package com.tang.testdemo.moudel.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.tang.testdemo.bean.User;
import com.tang.testdemo.repositorys.LoginRepositioy;

public class LoginViewModel extends ViewModel {

    private LoginRepositioy loginRepositioy;

    public LoginViewModel (LoginRepositioy loginRepositioy){
        this.loginRepositioy = loginRepositioy;
    }

    public MutableLiveData<User> getLoginBean(){
        return  loginRepositioy.getLoginBeanMutableLiveData();
    }

    public MutableLiveData<User> login(String userName,String pwd){
        return loginRepositioy.login(userName,pwd);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        loginRepositioy.release();
        loginRepositioy = null;
    }
}
