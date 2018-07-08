package com.tang.testdemo.dagger.Module;


import android.arch.lifecycle.ViewModelProviders;

import com.tang.testdemo.annotaion.PerActivity;
import com.tang.testdemo.api.LoginApi;
import com.tang.testdemo.base.BaseViewModelFactory;
import com.tang.testdemo.moudel.net.WebApiService;
import com.tang.testdemo.moudel.viewModel.LoginViewModel;
import com.tang.testdemo.repositorys.CacheRepository;
import com.tang.testdemo.repositorys.LoginRepositioy;
import com.tang.testdemo.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginMoudle {

    @PerActivity
    @Provides
    LoginApi provideLoginApi(){
        return WebApiService.generateApi(LoginApi.class,null);
    }

    @PerActivity
    @Provides
    LoginViewModel provideLoginViewMoudel(LoginActivity loginActivity, LoginRepositioy loginRepositioy){
        return ViewModelProviders.of(loginActivity, new BaseViewModelFactory<>(loginRepositioy, LoginRepositioy.class))
                .get(LoginViewModel.class);
    }

    @PerActivity
    @Provides
    LoginRepositioy provideLoginRepositioy(LoginApi loginApi, CacheRepository cacheRepository){
        return new LoginRepositioy(loginApi,cacheRepository);
    }
}
