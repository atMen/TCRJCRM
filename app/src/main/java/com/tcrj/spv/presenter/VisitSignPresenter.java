package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.VisitSignCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 拜访签到：提交
 * Created by leict on 2017/12/18.
 */

public class VisitSignPresenter implements VisitSignCallBack.Presenter {
    private VisitSignCallBack.View view;
    private Api api;

    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(60, TimeUnit.SECONDS).
            readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build();

    public VisitSignPresenter(VisitSignCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 保存拜访签到数据
     */
    @Override
    public void save() {
        ParameterEntity entity = view.setParameter();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getVisitRecordEdit(new SubmitEntity(entity.getUserId(), entity.getCusId(),
                                                entity.getCameraImg(), entity.getMapImg(),
                                                entity.getXCoord(), entity.getYCoord(), entity.getSingPlace(),
                                                entity.getBigMapImg()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                        Log.e("TAG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        Log.e("TAG","onError"+e.toString());
                        view.submit(null);
                    }

                    @Override
                    public void onNext(SubmitEntity entity) {
                        view.LoadingOff();
                        view.submit(entity);
                    }
                });
    }

    @Override
    public void start() {
        save();
    }
}
