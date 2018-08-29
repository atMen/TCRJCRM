package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.LeaveApplyDetailCallBack;
import com.tcrj.spv.model.LeaveApplyDetailEntity;
import com.tcrj.spv.model.LeaveApplyEntity;
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
 * 请假申请：详情
 * Created by leict on 2017/12/7.
 */

public class LeaveApplyDetailPresenter implements LeaveApplyDetailCallBack.Presenter {
    private LeaveApplyDetailCallBack.View view;
    private Api api;
    private int flag;

    public LeaveApplyDetailPresenter(LeaveApplyDetailCallBack.View view, int flag) {
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
        api.getLeaveDetails(new LeaveApplyDetailEntity(entity.getWorkId(), entity.getFkNodeId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeaveApplyDetailEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(LeaveApplyDetailEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 获取类型数据
     */
    @Override
    public void kind() {
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getLeaveTypeInfo(new LeaveApplyEntity(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeaveApplyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(LeaveApplyEntity entity) {
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
