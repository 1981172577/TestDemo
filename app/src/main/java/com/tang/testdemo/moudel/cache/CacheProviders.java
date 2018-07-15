package com.tang.testdemo.moudel.cache;


import com.tang.testdemo.bean.RespossenBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;


/**
 * Created by dev1 on 2017/12/13.
 */

public interface CacheProviders {
    //设置缓存失效时间为2天。
    @LifeCache(duration = 2, timeUnit = TimeUnit.DAYS)
    Observable<RespossenBean> getHomeBean(Observable<RespossenBean> observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
