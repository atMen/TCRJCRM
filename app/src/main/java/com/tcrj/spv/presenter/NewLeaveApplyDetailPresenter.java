package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.NewLeaveApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.spInfo;
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

public class NewLeaveApplyDetailPresenter implements NewLeaveApplyDetailCallBack.Presenter {
    private NewLeaveApplyDetailCallBack.View view;
    private Api api;
    private int flag;

    public NewLeaveApplyDetailPresenter(NewLeaveApplyDetailCallBack.View view, int flag) {
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
        api.getNewLeaveDetails(new spInfo(entity.getWorkId(), entity.getFkNodeId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<spInfo>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Log.e("TAG","onError"+e.toString());
                    }

                    @Override
                    public void onNext(spInfo entity) {
                        Log.e("TAG","onNext");
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }



    @Override
    public void start() {
        switch (flag) {
            case 0:
                loadData();
                break;

        }
    }
}
