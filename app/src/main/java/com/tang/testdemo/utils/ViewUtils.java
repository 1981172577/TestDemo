package com.tang.testdemo.utils;

import android.content.Context;

public class ViewUtils {
    /**
     * 获取状态栏高度——方法1
     * */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight1 = 30;
        try {
            //获取status_bar_height资源的ID
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight1;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
