package com.tang.testdemo.dagger.Component;

import com.tang.testdemo.annotaion.App;
import com.tang.testdemo.app.MyApplication;
import com.tang.testdemo.dagger.Module.AppMoudle;
import com.tang.testdemo.repositorys.CacheRepository;

import dagger.BindsInstance;
import dagger.Component;


@App
@Component(modules = {AppMoudle.class})
public interface AppComponent {

    void inject(MyApplication application);

    CacheRepository getCache();

    @Component.Builder
    interface Builder{

        @BindsInstance
        AppComponent.Builder bindApplication(MyApplication application);

        AppComponent.Builder AppMoudle(AppMoudle module);

        AppComponent build();
    }

}
