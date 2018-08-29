package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.WorkProjectCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.WorkProjectEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 写日报
 * Created by leict on 2017/12/17.
 */

public class WorkProjectPresenter implements WorkProjectCallBack.Presenter {
    private WorkProjectCallBack.View view;
    private Api api;

    public WorkProjectPresenter(WorkProjectCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameter();

        Log.e("TAG",entity.getCurrentIndex()+"--CurrentIndex()");
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getProjectList(new WorkProjectEntity(

                entity.getPageIndex(),
                entity.getPageSize(),
                entity.getProjectName(),
                entity.getCurrentIndex()
                ))

                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<WorkProjectEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(WorkProjectEntity entity) {
                        Log.e("TAG",entity.getData().size()+"--数据集合大小");

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
