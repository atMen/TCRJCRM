package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.GpsCallBack;
import com.tcrj.spv.model.LocationEntity1;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 拜访签到：提交
 * Created by leict on 2017/12/18.
 */

public class GpsPresenter implements GpsCallBack.Presenter{


    private GpsCallBack.View view;
    private Api api;

    public GpsPresenter(GpsCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }



    /**
     * 提交外派签到信息
     */
    @Override
    public void save() {
        ParameterEntity entity = view.setParameter();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

//        //替换跟踪定位的接口
//        api.getRealLocationInfo(new LocationEntity1())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("TAG","错误");
//                    }
//
//                    @Override
//                    public void onNext(Object locationEntity1) {
//                        view.LoadingOff();
////                        view.getData(locationEntity1);
//                        Log.e("TAG","成功"+locationEntity1.toString());
//
//                    }
//                });

        //替换跟踪定位的接口
        api.getRealLocationInfo(new LocationEntity1())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationEntity1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","错误");
                    }

                    @Override
                    public void onNext(LocationEntity1 locationEntity1) {
                        view.LoadingOff();
                        view.getData(locationEntity1);
                        Log.e("TAG","成功");
                    }
                });

    }


    @Override
    public void start() {
        save();
    }
}
