package com.tang.testdemo.utils;



import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tang.testdemo.BuildConfig;

public class LogUtils {

    static {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void i(String msg){
        if (BuildConfig.DEBUG)
        Logger.i(msg);
    }

    public static void v(String msg){
        if (BuildConfig.DEBUG)
        Logger.v(msg);
    }

    public static void e(String msg){
        if (BuildConfig.DEBUG)
        Logger.e(msg);
    }
}
