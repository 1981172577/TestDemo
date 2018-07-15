package com.tang.testdemo.repositorys;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tang.testdemo.R;
import com.tang.testdemo.api.SkuApi;
import com.tang.testdemo.bean.HomeItem;
import com.tang.testdemo.bean.HomeItemBean;
import com.tang.testdemo.bean.HomePicture;
import com.tang.testdemo.bean.RespossenBean;
import com.tang.testdemo.bean.User;
import com.tang.testdemo.moudel.cache.CacheProviders;
import com.tang.testdemo.utils.CollectionUtil;
import com.tang.testdemo.utils.RxScheduleMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

public class MainRespositiory implements BaseRepository {

    private static final String[] itemNames = new String[]{"最新商品","促销商品","收藏商品","最近购买"};
    private static final int[] itemResuse = new int[]{R.mipmap.zuixinshangp,R.mipmap.cuxiao,R.mipmap.shoucang,R.mipmap.zuijingoumai};

    private SkuApi skuApi;

    private CacheRepository cacheRepository;

    private CacheProviders cacheProviders;

    private Gson gson;

    private MutableLiveData<List<HomeItemBean>> HomeItemLiveData = new MutableLiveData<>();

    public MainRespositiory(SkuApi skuApi, CacheRepository cacheRepository, CacheProviders cacheProviders){
        this.skuApi = skuApi;
        this.cacheRepository = cacheRepository;
        this.cacheProviders = cacheProviders;
        this.gson =  new GsonBuilder()
                .serializeNulls()
                .create();
    }

    public MutableLiveData<List<HomeItemBean>> getHomeItemLiveData() {
        return HomeItemLiveData;
    }

    public void setHomeItemLiveData(MutableLiveData<List<HomeItemBean>> homeItemLiveData) {
        HomeItemLiveData = homeItemLiveData;
    }

    public MutableLiveData<List<HomeItemBean>> initData(int pageIndex,int pageLength){
        User user = cacheRepository.getLoginedUser();
        if(user != null){
            Map<String,String> reqestMap = new HashMap<>();

            Map<String,String> tempMap = new HashMap<>();

            tempMap.put("vendorId",String.valueOf(user.getVnedorId()));
            tempMap.put("memPin",String.valueOf(user.getVnedorId()));
            tempMap.put("storeCode",String.valueOf(user.getVnedorId()));
            tempMap.put("pageIndex",String.valueOf(pageIndex));
            tempMap.put("length",String.valueOf(pageLength));

            reqestMap.put("reqJson",gson.toJson(tempMap));

            Observable<RespossenBean> skuObs = skuApi.appRecommendSku(user.getToken(),reqestMap);
            Observable<RespossenBean> appImgObs = cacheProviders.getHomeBean(skuApi.getTopImg(user.getToken(),reqestMap),new DynamicKey(user.getVnedorId()),
                    new EvictDynamicKey(pageIndex == 0));
            Observable.zip(skuObs,appImgObs,(RespossenBean rb1,RespossenBean rb2)->{
                List<HomeItemBean> homeItemBeanList = new ArrayList<>();

                List<HomeItem> homeItemList = new ArrayList<>();
                for(int i = 0; i< itemNames.length;i++){
                    HomeItem item = new HomeItem.Builder()
                            .itemPosition(i)
                            .itemName(itemNames[i])
                            .picResuse(itemResuse[i])
                            .build();
                    homeItemList.add(item);
                }
                HomeItemBean itemBean = new HomeItemBean();
                itemBean.setViewType(HomeItemBean.TYPE_ITEM);
                itemBean.setHomeItemList(homeItemList);
                homeItemBeanList.add(itemBean) ;

                if(rb2 != null && rb2.getReturnCode() == RespossenBean.SUCCESS){
                    List<HomePicture> pictureList = gson.fromJson(rb2.getReturnData(),new TypeToken<List<HomePicture>>(){}.getType());
                    if(CollectionUtil.isNotEmpty(pictureList)){
                        HomeItemBean homeItemBean = new HomeItemBean();
                        homeItemBean.setViewType(HomeItemBean.TYPE_CAROUSEL);
                        homeItemBean.setHomePictureList(pictureList);
                        homeItemBeanList.add(0,homeItemBean);
                    }
                }

                if(rb1 != null && rb1.getReturnCode() == RespossenBean.SUCCESS){
                    List<HomeItemBean> skuList = gson.fromJson(rb1.getReturnData(),new TypeToken<List<HomeItemBean>>(){}.getType());
                    if(CollectionUtil.isNotEmpty(skuList)){
                        for(HomeItemBean homeItemBean :skuList){
                            homeItemBean.setViewType(HomeItemBean.TYPE_SKU);
                        }
                        homeItemBeanList.addAll(skuList);
                    }
                }
                return homeItemBeanList;
            })
                    .compose(RxScheduleMapper.io2main())
                    .subscribe(new Observer<List<HomeItemBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(List<HomeItemBean> homeItemBeans) {
                            HomeItemLiveData.setValue(homeItemBeans);
                        }
                        @Override
                        public void onError(Throwable e) {

                        }
                        @Override
                        public void onComplete() {

                        }
                    });
        }
        return HomeItemLiveData;
    }

    @Override
    public void release() {
        compositeDisposable.dispose();
    }
}
