package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.NewNowLeaveApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.spInfo;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 请假申请：详情
 * Created by leict on 2017/12/7.
 */

public class NewNowLeaveApplyDetailPresenter implements NewNowLeaveApplyDetailCallBack.Presenter {
    private NewNowLeaveApplyDetailCallBack.View view;
    private Api api;
    private int flag;

    public NewNowLeaveApplyDetailPresenter(NewNowLeaveApplyDetailCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameters();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewLeaveCheck(new spInfo(0,entity.getWorkId(), entity.getFkNodeId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<spInfo>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Log.e("TAG","onError"+e.toString());
                    }

                    @Override
                    public void onNext(spInfo entity) {
                        Log.e("TAG","onNext");
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    @Override
    public void uploadData() {
        ParameterEntity entity = view.setSPParameters();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewLeaveCheck(new spInfo(entity.getDayType(),entity.getWorkId(), entity.getFkNodeId(),entity.getUserName(),entity.getPlanContent(),entity.getArea()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<spInfo>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Log.e("TAG","onError："+e.toString());
                    }

                    @Override
                    public void onNext(spInfo entity) {
                        Log.e("TAG","onNext");
                        view.LoadingOff();
                        view.getTJData(entity);
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
                uploadData();
                break;
        }
    }
}
