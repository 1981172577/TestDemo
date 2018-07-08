package com.tang.testdemo.moudel.cache;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 14/11/17.
 */

public interface Editor<T> {
    /**
     * 保存数据
     *
     * @param key
     * @param value
     * @return
     */
    Editor<T> put(String key, T value);

    /**
     * 设置过期时间
     *
     * @param unit 单位
     * @param time 时间
     * @return
     */
    Editor expire(TimeUnit unit, int time);

    /**
     * 同步方式提交
     *
     * @return true 成功
     */
    boolean commit();

    /**
     * 异步方式提交
     */
    void apply();

    /**
     * 提交并订阅返回
     *
     * @return true成功
     */
    Observable<Boolean> suscribe();
}
