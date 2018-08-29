package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.CustomerCallBack;
import com.tcrj.spv.model.CustomerEntity;
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
 * 添加/修改/客户详细信息
 * Created by leict on 2017/11/22.
 */

public class CustomerPresenter implements CustomerCallBack.Presenter {
    private CustomerCallBack.View view;
    private Api api;

    public CustomerPresenter(CustomerCallBack.View view) {
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


        api.getCutomerInfo(new CustomerEntity(entity.getCustomerName(),
                entity.getCustomerClass(), entity.getProvince(),
                entity.getCity(), entity.getArea(),
                entity.getAddress(), entity.getCustomerStatus(),
                entity.getIntentionProducts(), entity.getIsHot(),
                entity.getWriteUserID(), entity.getCustomerID()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(CustomerEntity entity) {
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
