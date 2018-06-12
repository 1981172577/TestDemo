package com.tang.testdemo.app;

import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.tang.testdemo.moudel.net.ApiManager;

import javax.inject.Inject;


public class MyApplication  extends MultiDexApplication {

    private static MyApplication INSTANCE = null;

    @Inject
    ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        DaggerAppComponent.create().inject(this);
        INSTANCE = this;
    }


    public static MyApplication getInstance() {
        return INSTANCE;
    }

    public ApiManager getApiManager(){
        return apiManager;
    }

}
