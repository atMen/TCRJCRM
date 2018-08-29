package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.FollowAddCallBack;
import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.TracedMaturityEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 跟进记录
 * Created by leict on 2017/12/1.
 */

public class FollowAddPresenter implements FollowAddCallBack.Presenter {
    private FollowAddCallBack.View view;
    private Api api;
    private int flag;

    public FollowAddPresenter(FollowAddCallBack.View view, int flag) {
        this.view = view;
        this.flag = flag;
        view.setPresenter(this);
    }

    /**
     * 获取联系人数据
     */
    @Override
    public void getContact() {
        ParameterEntity entity = view.getParameters();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getContactList(new ContactListEntity(entity.getCusId(), entity.getKey(), entity.getUserId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContactListEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(ContactListEntity entity) {
                        view.LoadingOff();
                        view.loadContact(entity);
                    }
                });
    }

    /**
     * 获取客户成熟度
     */
    @Override
    public void getTraced() {
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getTracedMaturityList(new TracedMaturityEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TracedMaturityEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(TracedMaturityEntity entity) {
                        view.LoadingOff();
                        view.loadTraced(entity);
                    }
                });
    }

    /**
     * 提交跟进记录
     */
    @Override
    public void submit() {
        ParameterEntity entity = view.getParameters();
        view.LoadingOn();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Log.e("TAG","ContactDate:"+entity.getContactDate()+"--WriteUserID:"+entity.getWriteUserID()+"--Status:"+entity.getStatus()
                +"--TracedResult:"+entity.getTracedResult()+"--VisId:"+ entity.getVisId()+"--CustomerID:"+ entity.getCustomerID()
                +"--ContactID:"+entity.getContactID()+"--TracedMaturity:"+entity.getTracedMaturity()+"--CTID:"+entity.getCTID()
                +"--ContactType:"+entity.getContactType());
        api.getCustomerTraceEdit(new SubmitEntity(entity.getContactDate(),
                entity.getWriteUserID(), entity.getStatus(), entity.getTracedResult(),
                entity.getVisId(), entity.getCustomerID(), entity.getContactID(),
                entity.getTracedMaturity(), entity.getCTID(), entity.getContactType()))
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
                        view.save(entity);
                    }
                });
    }

    @Override
    public void start() {
        switch (flag) {
            case 0:
                getContact();
                break;
            case 1:
                getTraced();
                break;
            case 2:
                submit();
                break;
        }
    }
}
