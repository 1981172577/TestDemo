package com.tang.testdemo.dagger.Component;

import com.tang.testdemo.annotaion.App;
import com.tang.testdemo.app.MyApplication;
import com.tang.testdemo.dagger.Module.AppMoudle;
import com.tang.testdemo.repositorys.CacheRepository;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.Interceptor;


@App
@Component(modules = {AppMoudle.class})
public interface AppComponent {

    void inject(MyApplication application);

    CacheRepository getCache();

    @Named("GET")
    Interceptor getInterceptor();

    @Named("LOG")
    Interceptor getLogIntercept();

    @Named("POST")
    Interceptor providePOSTIntercept();

    @Component.Builder
    interface Builder{

        @BindsInstance
        AppComponent.Builder bindApplication(MyApplication application);

        AppComponent.Builder AppMoudle(AppMoudle module);

        AppComponent build();
    }

}
