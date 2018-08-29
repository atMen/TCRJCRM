package com.tcrj.spv.presenter;


import android.util.Log;

import com.tcrj.spv.callback.WriteDailyCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
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
 * Created by leict on 2017/10/26.
 */

public class WriteDailyPresenter implements WriteDailyCallBack.Presenter {
    public WriteDailyCallBack.View view;
    private Api api;
    private int flag;

    public WriteDailyPresenter(WriteDailyCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    /**
     * 提交日报
     */

    private String Overtime = "0";
    @Override
    public void submit() {


        ParameterEntity entity = view.setParameter();

        String time = entity.getOvertime();

        if(!time.equals("")){
            Overtime = time;

        }
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Log.e("TAG","日报地址："+entity.getWorkPlace()+"时间："+entity.getLogDate()+"Overtime："+Overtime);

        api.getNewDayLogItemEdit(  new SubmitEntity(entity.getLogItemID(),
                                                    entity.getLogID(),
                                                    entity.getUserID(),
                                                    entity.getLogDate(),
                                                    entity.getWorkNature(),
                                                    entity.getProjectID(),
                                                    entity.getWorkPlace(),
                                                    entity.getWorkHour(),
                                                    Overtime,
                                                    entity.getLogContent(),
                                                    entity.getPlanContent()))

                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<SubmitEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","错误-加班时间");
                        view.LoadingOn();
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(SubmitEntity entity) {
                        view.LoadingOff();
                        view.submit(entity);
                    }
                });
    }

    /**
     * 请求数据
     */
    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameter();
        Log.e("TAG","昨天计划"+entity.getUserID()+"--"+entity.getProjectID()+"--"+entity.getWorkNature()+"--"+entity.getLogDate());
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getPlanProjectToStr(new SubmitEntity(
                entity.getProjectID(),
                entity.getUserID(),
                entity.getWorkNature(),
                entity.getLogDate()))

                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<SubmitEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(SubmitEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                        Log.e("TAG","昨天计划"+entity.getPlanContent());
                    }
                });
    }


    /**
     * 编辑日报
     */
    @Override
    public void WriterloadData() {
        ParameterEntity entity = view.setParameter();
        String time = entity.getOvertime();
        if(!time.equals("")){
            Overtime = time;

        }
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewDayLogItemEdit(new SubmitEntity(  entity.getLogItemID(),
                entity.getLogID(),
                entity.getUserID(),
                entity.getLogDate(),
                entity.getWorkNature(),
                entity.getProjectID(),
                entity.getWorkPlace(),
                entity.getWorkHour(),
                Overtime,
                entity.getLogContent(),
                entity.getPlanContent()))

                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<SubmitEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","错误-加班时间");
                        view.LoadingOn();
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(SubmitEntity entity) {
                        view.LoadingOff();
                        view.submit(entity);
                    }
                });

    }

    @Override
    public void start() {
        if (flag == 0) {
            loadData();
        } else {
            submit();
        }
    }
}
