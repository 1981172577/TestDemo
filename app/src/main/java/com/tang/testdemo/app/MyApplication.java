package com.tang.testdemo.app;

import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

public class MyApplication  extends MultiDexApplication {

    private static MyApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        INSTANCE = this;
    }


    public static MyApplication getInstance() {
        return INSTANCE;
    }

}
