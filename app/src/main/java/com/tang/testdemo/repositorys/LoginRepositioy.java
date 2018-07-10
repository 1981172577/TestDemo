package com.tang.testdemo.repositorys;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tang.testdemo.api.LoginApi;
import com.tang.testdemo.bean.LoginBean;
import com.tang.testdemo.bean.RespossenBean;
import com.tang.testdemo.utils.LogUtils;
import com.tang.testdemo.utils.RxScheduleMapper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class LoginRepositioy implements BaseRepository {

    private LoginApi loginApi;

    private CacheRepository cacheRepository;

    private Gson gson;

    private MutableLiveData<LoginBean> loginBeanMutableLiveData = new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginRepositioy(LoginApi loginApi,CacheRepository cacheRepository){
        this.loginApi = loginApi;
        this.cacheRepository = cacheRepository;
        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    public MutableLiveData<LoginBean> login(String phone,String pwd){
        Observable.just(pwd)
                .flatMap((Function<String, ObservableSource<RespossenBean>>) s -> {
                    Map<String,String> requestMap = new HashMap<>();
                    Map<String, String> result = new HashMap<String, String>();
                    requestMap.put("phoneNo",phone);
                    requestMap.put("password",s);
                    result.put("reqJson",gson.toJson(requestMap));
                    return loginApi.verificationLogin(result);
                })
                .compose(RxScheduleMapper.io2main())
                .subscribe(new MyLoginApiService());
        return loginBeanMutableLiveData;
    }

    public MutableLiveData<LoginBean> getLoginBeanMutableLiveData(){
        return loginBeanMutableLiveData;
    }

    private class MyLoginApiService implements Observer<RespossenBean> {
        @Override
        public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
        }
        @Override
        public void onNext(RespossenBean respossenBean) {
            if (RespossenBean.SUCCESS == respossenBean.getReturnCode()) {
                LoginBean result = gson.fromJson(respossenBean.getReturnData(),LoginBean.class);
                loginBeanMutableLiveData.setValue(result);
                saveLoginInfo(result);
            }else{
                LoginBean result = new LoginBean.Builder()
                        .errorMsg(respossenBean.getReturnMsg())
                        .build();
                loginBeanMutableLiveData.setValue(result);
            }
        }
        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            loginBeanMutableLiveData.setValue(null);
        }
        @Override
        public void onComplete() {
        }
    }

    @Override
    public void release() {
        compositeDisposable.dispose();
    }

    private void saveLoginInfo(LoginBean loginBean){
        if(loginBean == null){
            return;
        }
        cacheRepository.put(LoginBean.LOGIN_KEY,loginBean);
    }

}
