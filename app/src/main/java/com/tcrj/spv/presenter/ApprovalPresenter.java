package com.tcrj.spv.presenter;

import android.widget.Toast;

import com.tcrj.spv.callback.ApprovalCallBack;
import com.tcrj.spv.model.ApprovalEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 审批：待办审批/在办审批/完结审批
 * Created by leict on 2017/11/3.
 */

public class ApprovalPresenter implements ApprovalCallBack.Presenter {
    private ApprovalCallBack.View view;
    private Api api;
    private int flag;

    public ApprovalPresenter(ApprovalCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    /**
     * 待办审批
     */
    @Override
    public void loadPendedData() {
        String staffNo = view.setParamStaffNo();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getPendApprovalList(new ApprovalEntity(staffNo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApprovalEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Toast.makeText(BaseApplication.context, "数据加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ApprovalEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 在办审批
     */
    @Override
    public void loadOfficeData() {
        String staffNo = view.setParamStaffNo();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getOfficeApprovalList(new ApprovalEntity(staffNo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApprovalEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Toast.makeText(BaseApplication.context, "数据加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ApprovalEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    /**
     * 完结审批
     */
    @Override
    public void loadFinishData() {
        String staffNo = view.setParamStaffNo();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getFinishApprovalList(new ApprovalEntity(staffNo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApprovalEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Toast.makeText(BaseApplication.context, "数据加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ApprovalEntity entity) {
                        view.LoadingOff();
                        view.getData(entity);
                    }
                });
    }

    @Override
    public void start() {
        switch (flag) {
            case 1:
                loadPendedData();
                break;
            case 2:
                loadOfficeData();
                break;
            case 3:
                loadFinishData();
                break;
            default:
                break;
        }
    }
}
