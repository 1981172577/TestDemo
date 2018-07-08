package com.tang.testdemo.app;

import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.tang.testdemo.dagger.Component.AppComponent;
import com.tang.testdemo.dagger.Component.DaggerAppComponent;
import com.tang.testdemo.dagger.Module.AppMoudle;



public class MyApplication  extends MultiDexApplication {

    private static MyApplication INSTANCE = null;

    private AppComponent appComponent;

//    @Inject
//    ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
//        DaggerAppComponent.create().inject(this);
        INSTANCE = this;

        appComponent = DaggerAppComponent.builder()
                .AppMoudle(new AppMoudle(this))
                .bindApplication(this)
                .build();
        appComponent.inject(this);


    }


    public static MyApplication getInstance() {
        return INSTANCE;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    //    public ApiManager getApiManager(){
//        return apiManager;
//    }

}
