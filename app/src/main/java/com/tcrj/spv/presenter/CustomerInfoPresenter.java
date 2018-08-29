package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.CustomerInfoCallBack;
import com.tcrj.spv.model.CustomerInfoEntity;
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
 * 获取客户信息列表
 * Created by leict on 2017/11/24.
 */

public class CustomerInfoPresenter implements CustomerInfoCallBack.Presenter {
    private CustomerInfoCallBack.View view;
    private Api api;
    private int datatype = 0;

    public CustomerInfoPresenter(CustomerInfoCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        ParameterEntity entity = view.setParameter();
        int dayType = entity.getDayType();

        if("1".equals(dayType+"")){
            datatype = dayType;
        }

        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","datatype:"+datatype);
        api.getCustomerList(new CustomerInfoEntity(
                entity.getOwerUserId(),
                entity.getIntentionPro(),
                entity.getPageSize(),
                entity.getKeyWord(),
                entity.getOrder(),
                entity.getReportStatus(),
                entity.getCusClass(),
                entity.getBelongArea(),
                entity.getUserId(),
                entity.getCusStatus(),
                entity.getCurrentPageIndex(),
                entity.getCurrenttype(),
                entity.getIsHot(),
                datatype+""
        ))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerInfoEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(CustomerInfoEntity entity) {
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
