package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.TravelApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyDetailEntity;
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
 * 出差详情
 * Created by leict on 2017/12/8.
 */

public class TravelApplyDetailPresenter implements TravelApplyDetailCallBack.Presenter {
    private TravelApplyDetailCallBack.View view;
    private Api api;
    private int flag;

    public TravelApplyDetailPresenter(TravelApplyDetailCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getTravelDetails(new TravelApplyDetailEntity(entity.getWorkId(), entity.getFkNodeId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TravelApplyDetailEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(TravelApplyDetailEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    @Override
    public void kind() {
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
                        view.getType(entity);
                    }
                });
    }

    @Override
    public void start() {
        switch (flag) {
            case 0:
                loadData();
                break;
            case 1:
                kind();
                break;
        }
    }
}
