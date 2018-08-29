package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.CustomerDetailCallBack;
import com.tcrj.spv.model.CustomerDetailEntity;
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
 * 客户详情
 * Created by leict on 2017/11/27.
 */

public class CustomerDetailPresenter implements CustomerDetailCallBack.Presenter {
    private CustomerDetailCallBack.View view;
    private Api api;
    private int flag;

    public CustomerDetailPresenter(CustomerDetailCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        String cusId = view.cusId();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCustomerDetails(new CustomerDetailEntity(cusId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerDetailEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(CustomerDetailEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 投入公海
     */
    @Override
    public void intoSeas() {
        ParameterEntity entity = view.getParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCustomerInSeaHandler(new SubmitEntity(entity.getInHighSeasReason(), entity.getInHighSeasUserID(), entity.getIds()))
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
                        view.highSeas(entity);
                    }
                });
    }

    /**
     * 客户报备
     */
    @Override
    public void records() {
        ParameterEntity entity = view.getParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCustomerReport(new SubmitEntity(entity.getCustomerId()))
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
                        view.report(entity);
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
                intoSeas();
                break;
            case 2:
                records();
                break;
        }
    }
}
