package com.tcrj.spv.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.tcrj.spv.callback.VisitSignCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 拜访签到：提交
 * Created by leict on 2017/12/18.
 */

public class ExpatriateSignPresenter implements VisitSignCallBack.Presenter {


    private VisitSignCallBack.View view;
    private Api api;

    public ExpatriateSignPresenter(VisitSignCallBack.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    private void post(String url, String json)  {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "签到登录："+e.toString());
                view.LoadingOff();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                SubmitEntity entity = gson.fromJson(response.body().string(), SubmitEntity.class);
                view.LoadingOff();
                view.submit(entity);

            }
        });

    }



//    private String json = "{\"JQNo\":\"\",\"QDQT\":\"2\",\"Address\":\"陕西\",\"StaffID\":\"183\"}";

    /**
     * 提交外派签到信息
     */
    @Override
    public void save() {

        ParameterEntity entity = view.setParameter();
        Log.e("TAG","json:"+entity.getAddress());
        //TODO:单独的联网请求
        post(BaseApplication.getConfig()+"OutKq.ashx?",entity.getAddress());

//        OkHttpClient okHttpClient = new OkHttpClient();
////        //Form表单格式的参数传递
////        FormBody formBody = new FormBody
////                .Builder()
////                .add("JQNo","ffffffff-aba9-0151-b0a7-7dd80033c587")
////                .add("QDQT","2")
////                .add("Address","陕西")
////                .add("StaffID","183")
//
//        RequestBody formBody = new FormEncodingBuilder()
//                .add("platform", "android")
//                .add("name", "bug")
//                .add("subject", "XXXXXXXXXXXXXXX")
//
////        //Form表单格式的参数传递
////        FormBody formBody = new FormBody
////                .Builder()
////                .add("UserTitle","a00156")
////                .add("UserPwd","aaa")
////                .add("RegID","120c83f7602202584d2")
////                .add("RegType","android")
//
//                .build();
//        Request request = new Request
//                .Builder()
//                .post(formBody)//Post请求的参数传递
//                .url("http://113.200.26.66:8000/DTKCRM/Api/Login.ashx?")
//                .build();
//        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                Log.e("TAG","失败啦");
////                handler3.sendEmptyMessage(2);
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//
//                Log.e("TAG","签到登录："+response.body().string());
////                Gson gson = new Gson();
////                VersionInfo stu = gson.fromJson(response.body().string(), VersionInfo.class);
//
////                info = stu;
//
//            }
//
//        });

//        ParameterEntity entity = view.setParameter();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseApplication.getConfig())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        api = retrofit.create(Api.class);
//        Log.e("TAG","标识："+entity.getQDQT()+"--id:"+entity.getUserId()+"--jqno:"+entity.getJQNo()+"--地址："+entity.getAddress());
////        //替换外派签到的接口
//        api.getOutKq(new SubmitEntity(
//                entity.getJQNo(),
//                entity.getQDQT(),
//                entity.getAddress(),
//                entity.getUserId(
//
//                )
//        ))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SubmitEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        view.LoadingOff();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.LoadingOff();
//                        Log.e("TAG","签到出错啦：");
//                    }
//
//                    @Override
//                    public void onNext(SubmitEntity entity) {
//                        Log.e("TAG","签到："+entity.getMsg());
//                        view.LoadingOff();
//                        view.submit(entity);
//                    }
//                });
    }


    @Override
    public void start() {
        save();
    }
}
