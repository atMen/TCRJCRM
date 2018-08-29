package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.RelationManCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.RelationManEntity;
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
 * 客户联系人
 * Created by leict on 2017/12/6.
 */

public class RelationManPresenter implements RelationManCallBack.Presenter {
    private RelationManCallBack.View view;
    private Api api;
    private int flag;

    public RelationManPresenter(RelationManCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    /**
     * 请求数据
     */
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
        api.getUserListInfo(new RelationManEntity(entity.getStaffId(), entity.getType()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RelationManEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(RelationManEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 客户共享
     */
    @Override
    public void enjoy() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCustomerShareHandler(new SubmitEntity(entity.getCustomerID(), entity.getUserID(), entity.getAuthority()))
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
                        view.getShare(entity);
                    }
                });
    }

    /**
     * 客户转移
     */
    @Override
    public void move() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","uid:"+entity.getUserID()+"cid:"+entity.getCId());
        api.getCustomerMigrateHandler(new SubmitEntity(entity.getUserID(), entity.getCId()))
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
                        view.getTransfer(entity);
                    }
                });
    }

    @Override
    public void start() {
        switch (flag) {
            case 2:
                loadData();
                break;
            case 1:
                enjoy();
                break;
            case 0:
                move();
                break;
        }
    }
}
