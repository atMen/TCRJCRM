package com.tcrj.spv.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tcrj.spv.callback.TravelApplyCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 出差申请
 * Created by leict on 2017/11/15.
 */

public class TravelApplyPresenter implements TravelApplyCallBack.Presenter {
    public TravelApplyCallBack.View view;
    public Api api;
    Context context;

    public TravelApplyPresenter(Context context) {

        this.context = context;
        this.view = (TravelApplyCallBack.View) context;
        view.setPresenter(this);
    }

    /**
     * 提交数据
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


        Log.e("TAG","--"+entity.getFanHuiRiQi()+"--"+entity.getJiHuaShiJian()+"--"+entity.getMuBiaoDiZhi()
                +"--"+entity.getQiTaZaFei()+"--"+entity.getShenQingRenBuMen()+"--"+entity.getUserNo()
                +"--"+entity.getTongXingRenYuan()+"--"+entity.getZhuSuFei()+"--"+entity.getMuBiaoJiHua()
                +"--"+entity.getJiaoTongFei()+"--"+entity.getJiaoTongGongJu()+"--"+entity.getChuChaShenQingRen());
        api.getTravelApplyInfo(new TravelApplyEntity(1,
                entity.getFanHuiRiQi(), entity.getJiHuaShiJian(), entity.getMuBiaoDiZhi(), entity.getQiTaZaFei(),
                entity.getShenQingRenBuMen(), entity.getUserNo(), entity.getTongXingRenYuan(),
                entity.getZhuSuFei(), entity.getMuBiaoJiHua(), entity.getJiaoTongFei(),
                entity.getJiaoTongGongJu(), entity.getChuChaShenQingRen(),
                entity.getChuChaShiYou(),entity.getFkNodeId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TravelApplyEntity>() {
                    @Override
                    public void onCompleted() {
                        view.LoadingOff();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "信息提交失败", Toast.LENGTH_SHORT).show();
                        view.LoadingOff();
                    }

                    @Override
                    public void onNext(TravelApplyEntity entity) {
                        view.LoadingOff();
                        view.submit(entity);
                    }
                });
    }

    @Override
    public void start() {
        submit();
    }
}
