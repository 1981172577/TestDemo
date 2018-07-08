package com.tang.testdemo.moudel.cache;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.Serializable;

import io.reactivex.Observable;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 2017/9/4.
 */

public interface SuperCache {
    /**
     * 存储String
     *
     * @param key
     * @param value
     * @return
     */
    Editor<String> put(String key, String value);

    /**
     * 取String类型缓存
     *
     * @param key
     * @return
     */
    @Nullable
    String getAsString(String key);

    /**
     * 存储Serializable
     *
     * @param key
     * @param value
     * @return
     */
    @Nullable
    Editor<Serializable> put(String key, Serializable value);

    /**
     * 取Serializable类型的缓存
     *
     * @param key
     * @return
     */
    @Nullable
    <T extends Serializable> T getAsObject(String key);

    /**
     * 存储Bitmap
     *
     * @param key
     * @param value
     * @return
     */
    Editor<Bitmap> put(String key, Bitmap value);

    /**
     * 取Bitmap类型的缓存
     *
     * @param key
     * @return
     */
    @Nullable
    Bitmap getAsBitmap(String key);

    /**
     * 订阅方式读取缓存
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> Observable<T> subscribe(String key);

    /**
     * 获取缓存文件
     *
     * @param key
     * @return value 缓存的文件
     */
    File file(String key);

    /**
     * 删除key对应的缓存
     *
     * @param key
     */
    boolean remove(String key);

    /**
     * 清除所有的缓存
     */
    void clear();
}
