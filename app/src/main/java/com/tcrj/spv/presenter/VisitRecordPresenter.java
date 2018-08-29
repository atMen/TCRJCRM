package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.VisitRecordCallBack;
import com.tcrj.spv.model.VisitRecordEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 拜访记录：详情
 * Created by leict on 2017/12/11.
 */

public class VisitRecordPresenter implements VisitRecordCallBack.Presenter {
    private VisitRecordCallBack.View view;
    private Api api;

    public VisitRecordPresenter(VisitRecordCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {

        Log.e("TAG","开始加载");
        view.LoadingOn();
        int userId = view.StaffId();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getVisitRecorList(new VisitRecordEntity(0, userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VisitRecordEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","onError");
                    }

                    @Override
                    public void onNext(VisitRecordEntity entity) {
                        Log.e("TAG","onNext");
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
