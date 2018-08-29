package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.SignRecordCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SignRecordEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 签到记录
 * Created by leict on 2017/11/1.
 */

public class SignRecordPresenter implements SignRecordCallBack.Presenter {
    private SignRecordCallBack.View view;
    private Api api;

    public SignRecordPresenter(SignRecordCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameter();
        Log.e("TAG","ID:"+entity.getStaffId()+"----NO:"+entity.getStaffNo()+"-----lastmouth:"+entity.getLastMouth());
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getSignRecordList(new SignRecordEntity(entity.getStaffId(), entity.getStaffNo(),entity.getLastMouth()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignRecordEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(SignRecordEntity entity) {
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
