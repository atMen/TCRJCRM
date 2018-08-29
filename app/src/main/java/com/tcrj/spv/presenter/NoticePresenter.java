package com.tcrj.spv.presenter;

import com.tcrj.spv.callback.NoticeCallBack;
import com.tcrj.spv.model.NoticeEntity;
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
 * 通知公告:表示器
 * Created by leict on 2017/10/31.
 */

public class NoticePresenter implements NoticeCallBack.Presenter {
    private NoticeCallBack.View view;
    private Api api;

    public NoticePresenter(NoticeCallBack.View view) {
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
        api.getNoticeList(new NoticeEntity(entity.getKeyWord(), entity.getPageSize(), entity.getPageIndex()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NoticeEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(NoticeEntity entity) {
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
