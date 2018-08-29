package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.TraceDetailCallBack;
import com.tcrj.spv.model.CustomerTraceEntity;
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
 * 跟进记录详情/删除
 * Created by leict on 2017/12/4.
 */

public class TraceDetailPresenter implements TraceDetailCallBack.Presenter {
    private TraceDetailCallBack.View view;
    private Api api;
    private int flag;

    public TraceDetailPresenter(TraceDetailCallBack.View view, int flag) {
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
        api.getCustomerTraceDetails(new CustomerTraceEntity(entity.getTId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerTraceEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(CustomerTraceEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 删除
     */
    @Override
    public void strike() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCustomerTraceHandler(new SubmitEntity(entity.getCTID(), entity.getContactType(), entity.getUserId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                        view.delete(entity);
                    }
                });
    }

    @Override
    public void start() {
        if (flag == 0) {
            loadData();
        } else {
            strike();
        }
    }
}
