package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.NewDayLogItemListCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leict on 2017/12/27.
 */

public class NewDayLogItemListPresenter implements NewDayLogItemListCallBack.Presenter {

    private Api api;
    private NewDayLogItemListCallBack.View view;
    public NewDayLogItemListPresenter(NewDayLogItemListCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {

        //从activity获取到参数数据
        final ParameterEntity entity = view.setParameter();

        Log.e("TAG","参数:"+entity.getLogID()+"---"+entity.getStaffId());
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        //Integer.parseInt(entity.getLogDate()   错误时间能否转换为String
        //TODO:错误时间能否转换为String

//        api.getWorkDailyItemList(new WorkDailtItemEntity(entity.getStaffId(),entity.getLogID(), Integer.parseInt(entity.getLogDate())))
        api.getWorkDailyItemList(new WorkDailtItemEntity(entity.getStaffId(),entity.getLogID(), 1))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkDailtItemEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Log.e("TAG","失败");
                    }

                    @Override
                    public void onNext(WorkDailtItemEntity workDailtItemEntity) {
                        view.LoadingOff();
                        view.getData(workDailtItemEntity);
                        Log.e("TAG","成功:"+entity.getLogID());
                    }
                });
    }
}
