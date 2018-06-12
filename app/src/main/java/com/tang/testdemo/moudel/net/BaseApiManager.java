package com.tang.testdemo.moudel.net;


import com.tang.testdemo.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseApiManager {

    private final OkHttpClient mClient;

    public OkHttpClient getClient() {
        return mClient;
    }

    public BaseApiManager(){
        //cache
//        File httpCacheDir = new File(MyApplication.getContextntext().getCacheDir(), "response");
//        int cacheSize = 10 * 1024 * 1024;// 10 MiB
//        Cache cache = new Cache(httpCacheDir,cacheSize);

        //cookie
//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.getContext()));
        //OkHttpClient

        //开启okhttp日志拦截器
//    	HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new Logger() {
//			@Override
//			public void log(String arg0) {
//
//			}
//		});

//    	logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_HEADER_CONTROL_INTERCEPTOR)
//                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .addInterceptor(logInterceptor)
                .addInterceptor(new LoggingInterceptor())
//                .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)
//                .cookieJar(cookieJar)
                .build();
    }


    Interceptor REWRITE_HEADER_CONTROL_INTERCEPTOR = new Interceptor(){
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Charset", "UTF-8")
                    .addHeader("Connection", "keep-alive")
//		                .addHeader("Accept-Encoding", "gzip, deflate")
//		                .addHeader("Accept", "*/*")
//		                .addHeader("Cookie", "add cookies here")
                    .build();
            return chain.proceed(request);
        }
    };


//    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor(){
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            //通过 CacheControl 控制缓存数据
//            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//            cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
//            cacheBuilder.maxStale(7, TimeUnit.DAYS);//这个是控制缓存的过时时间
//            CacheControl cacheControl = cacheBuilder.build();
//
//            //设置拦截器
//            Request request = chain.request();
//            if (!Utils.isNetworkConnected(MyApplication.getContext())) {
//                request = request.newBuilder()
//                        .cacheControl(cacheControl)
//                        .build();
//            }
//
//            Response originalResponse = chain.proceed(request);
//            if (Utils.isNetworkConnected(MyApplication.getContext())) {
//                int maxAge = 0;//read from cache
//                return originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public ,max-age=" + maxAge)
//                        .build();
//            } else {
//                int maxStale = 60 * 60 * 24 * 7;//tolerate 1-weeks stale
//                return originalResponse.newBuilder()
//                        .removeHeader("Prama")
//                        .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
//                        .build();
//            }
//        }
//    };

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.i("----------Request Start----------------");
            LogUtils.i("request| " + request.toString());
            LogUtils.i("| Response:" + content);
            LogUtils.i("----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }
}
