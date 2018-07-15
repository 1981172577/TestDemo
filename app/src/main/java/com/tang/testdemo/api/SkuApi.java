package com.tang.testdemo.api;

import com.tang.testdemo.bean.RespossenBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SkuApi {
    /**
     * 获取首页推荐商品
     */
    @FormUrlEncoded
    @POST("api/appExec?m=appRecommendSku")
    Observable<RespossenBean> appRecommendSku(@Query("token") String token, @FieldMap Map<String,String> maps);
    /**
     * 获取购物车商品数
     * @param token
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("api/appExec?m=getShopcarCount")
    Observable<RespossenBean> getShopcarCount(@Query("token") String token,@FieldMap Map<String,String> maps);

    /**
     * 获取首页轮播图
     * @param token
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("api/appExec?m=getTopImg")
    Observable<RespossenBean> getTopImg(@Query("token") String token,@FieldMap Map<String,String> maps);
}
