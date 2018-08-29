package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.NoticeDetailCallBack;
import com.tcrj.spv.model.NoticeDetailEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 通知公告：详情
 * Created by leict on 2017/11/6.
 */

public class NoticeDetailPresenter implements NoticeDetailCallBack.Presenter {
    private NoticeDetailCallBack.View view;
    private Api api;

    public NoticeDetailPresenter(NoticeDetailCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        int id = view.setParameterId();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","id:"+id);
        api.getNoticeDetails(new NoticeDetailEntity(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NoticeDetailEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(NoticeDetailEntity entity) {
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
