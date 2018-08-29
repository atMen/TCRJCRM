package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.WorkDailyTypeCallBack;
import com.tcrj.spv.model.NewWorkDailyEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 日报分类
 * Created by leict on 2017/12/16.
 */

public class WorkDailyTypePresenter implements WorkDailyTypeCallBack.Presenter {
    private WorkDailyTypeCallBack.View view;
    private Api api;

    public WorkDailyTypePresenter(WorkDailyTypeCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getBaseDataTypeList(new NewWorkDailyEntity("-1", "1"))
                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<NewWorkDailyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(NewWorkDailyEntity entity) {
                        view.LoadingOff();
                        view.getType(entity);
                    }
                });
    }

    @Override
    public void start() {
        loadData();
    }
}
