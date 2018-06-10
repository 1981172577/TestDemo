package com.tang.testdemo.utils;


import com.migrsoft.posbusiness.BuildConfig;
import com.orhanobut.logger.Logger;

public class LogUtils {


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
