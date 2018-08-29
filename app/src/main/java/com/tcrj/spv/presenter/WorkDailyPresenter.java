package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.WorkDailyCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.model.WorkDailyEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 工作日报
 * Created by leict on 2017/11/2.
 */

public class WorkDailyPresenter implements WorkDailyCallBack.Presenter {
    private WorkDailyCallBack.View view;
    private Api api;

    public WorkDailyPresenter(WorkDailyCallBack.View view) {
        this.view = view;
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
        Log.e("TAG","我的日报参数："+entity.getStaffId()+"---"+entity.getDayType()+"---"+entity.getOwerId()+"---"+entity.getDeptId()+"---"+entity.getCurrentIndex()+"---"+entity.getPageSize()+"---"+entity.getIsMe());
        api.getWorkDailyList(new WorkDailyEntity(

                entity.getStaffId(),
                entity.getDayType(),
                entity.getOwerId(),
                entity.getDeptId(),
                entity.getCurrentIndex(),
                entity.getPageSize(),
                entity.getIsMe()))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkDailyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(WorkDailyEntity entity) {
                        Log.e("TAG","我的日报："+entity.toString());
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    @Override
    public void loadBmData() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","部门："+entity.getStaffId());
        api.getWorkDailyBmList(new SpinnerEntity(entity.getStaffId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpinnerEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SpinnerEntity spinnerEntity) {

                        Log.e("TAG","获取到了部门数据："+spinnerEntity.getListInfo().get(0).getDName());
                        view.LoadingOff();
                        view.getBmData(spinnerEntity);

                    }
                });
    }

    @Override
    public void start() {
        loadData();
    }
}
