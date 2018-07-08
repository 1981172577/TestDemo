package com.tang.testdemo.api;

import com.tang.testdemo.bean.RespossenBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {
    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("api/appExec?m=getPersonalInfo")
    Observable<RespossenBean> getPersionInfo(@Query("token") String token, @FieldMap Map<String,Object> maps);

    /**
     * 登录接口
     *
     */
    @FormUrlEncoded
    @POST("api/appLogin?token=H8DH9Snx9877SDER5667")
    Observable<RespossenBean> verificationLogin(@FieldMap Map<String,String> maps);

}
