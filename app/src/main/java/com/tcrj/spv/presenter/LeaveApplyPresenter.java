package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.LeaveApplyCallBack;
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
 * 请假申请
 * Created by leict on 2017/11/15.
 */

public class LeaveApplyPresenter implements LeaveApplyCallBack.Presenter {
    public LeaveApplyCallBack.View view;
    public Api api;
    public int flag;

    public LeaveApplyPresenter(LeaveApplyCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    /**
     * 调用
     */
    @Override
    public void start() {
        if (flag == 0) {
            loadData();
        } else {
            submit();
        }
    }

    /**
     * 提交
     */
    @Override
    public void submit() {
        ParameterEntity entity = view.setParameter();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","=="+entity.getQjqssj()+
                "=="+entity.getQjjssj()+"=="+
                entity.getQjsy()+"=="+entity.getName()+
                "=="+entity.getYgbh()+"=="+entity.getQjlx()+"=="+entity.getBumen()+"=="+entity.getGjts()+" --entity.getArea()-"+ entity.getArea());
        api.getLeaveTypeInfo(new LeaveApplyEntity
                (2, entity.getQjqssj(), entity.getQjjssj(),
                        entity.getQjsy(), entity.getName(), entity.getYgbh(),
                        entity.getQjlx(), entity.getBumen(), entity.getGjts(), entity.getArea()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeaveApplyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG","e:"+e.getMessage());
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(LeaveApplyEntity entity) {
                        view.LoadingOff();
                        view.save(entity);
                    }
                });
    }

    /**
     * 获取天数
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
        api.getLeaveTypeInfo(new LeaveApplyEntity(1, entity.getQjqssj(), entity.getQjjssj(), entity.getQjlx(), entity.getYgbh()))
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
                        view.getData(entity);
                    }
                });
    }
}
