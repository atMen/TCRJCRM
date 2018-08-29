package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.TrafficToolCallBack;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 出差申请
 * Created by leict on 2017/11/15.
 */

public class TrafficToolPresenter implements TrafficToolCallBack.Presenter {
    public TrafficToolCallBack.View view;
    public Api api;

    public TrafficToolPresenter(TrafficToolCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 请求数据
     */
    @Override
    public void loadData() {
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getTravelApplyInfo(new TravelApplyEntity(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TravelApplyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(TravelApplyEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    @Override
    public void start() {
        loadData();
    }
}
