package com.tcrj.spv.presenter;

import android.util.Log;

import com.tcrj.spv.callback.UserInfoCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用户：表示器
 * Created by leict on 2017/10/23.
 */
public class UserInfoPresenter implements UserInfoCallBack.Presenter {
    private UserInfoCallBack.View view;
    private Api api;

    public UserInfoPresenter(UserInfoCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 加载数据
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
        Log.e("TAG","登录信息："+entity.getUserName()+"--"+entity.getUserPwd());
        api.getLoginInfo(new UserInfoEntity(entity.getUserName(), entity.getUserPwd(), "120c83f7602202584d2", "android"))
                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<UserInfoEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.LoadingOff();
                        view.ServiceFailInfo(e.toString());
                        Log.e("TAG","登录信息："+e.toString());
                    }

                    @Override
                    public void onNext(UserInfoEntity entity) {
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
