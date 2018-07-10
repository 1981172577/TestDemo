package com.tang.testdemo.utils;


import android.util.Log;

import com.orhanobut.logger.Logger;
import com.tang.testdemo.BuildConfig;

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
        Log.e("-----",msg);
        if (BuildConfig.DEBUG)
        Logger.e(msg);
    }
}
