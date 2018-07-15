package com.tang.testdemo.dagger.Module;

import android.arch.lifecycle.ViewModelProviders;

import com.tang.testdemo.annotaion.PerActivity;
import com.tang.testdemo.api.LoginApi;
import com.tang.testdemo.api.SkuApi;
import com.tang.testdemo.base.BaseViewModelFactory;
import com.tang.testdemo.moudel.cache.CacheProviders;
import com.tang.testdemo.moudel.net.WebApiService;
import com.tang.testdemo.moudel.viewModel.MainViewModel;
import com.tang.testdemo.repositorys.CacheRepository;
import com.tang.testdemo.repositorys.MainRespositiory;
import com.tang.testdemo.ui.activity.MainActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

@Module
public class MainMoudle {

    @PerActivity
    @Provides
    LoginApi provideLoginApi(@Named("LOG") Interceptor interceptor){
        return WebApiService.generateApi(LoginApi.class,interceptor);
    }

    @PerActivity
    @Provides
    MainViewModel provideMainViewModel(MainActivity mainActivity, MainRespositiory mainRespositiory){
        return ViewModelProviders.of(mainActivity, new BaseViewModelFactory<>(mainRespositiory, MainRespositiory.class))
                .get(MainViewModel.class);
    }

    @PerActivity
    @Provides
    MainRespositiory provideLoginRepositioy(SkuApi akuapi, CacheRepository cacheRepository,CacheProviders cacheProviders){
        return new MainRespositiory(akuapi,cacheRepository,cacheProviders);
    }
}
