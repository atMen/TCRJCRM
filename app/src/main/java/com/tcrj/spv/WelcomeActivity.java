package com.tcrj.spv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.LoginActivity;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;
import com.tcrj.spv.views.utils.PhoneInfo;
import com.tcrj.spv.views.utils.XMLName;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {



    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case 2:

                    openNewActivity(MainActivity.class);
                    finish();
                    break;

                default:
                    break;

            }
        }
    };

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);

        getPhoneInfo();

        getLoginInfo();

        SharedPreferences pf = getSharedPreferences("Login", 0);
        //根据数据类型，调用对应的get方法，通过键取得对应的值。
        boolean isLogin = pf.getBoolean("isLogin", false);

        if(isLogin){
            toLogin();
        }else{
            handler.sendEmptyMessageDelayed(1,2000);
        }

    }

    private void getPhoneInfo() {
        String machineImei = PhoneInfo.getMachineImei(this);
        String imei = PhoneInfo.getImei(this);
        Log.e("TAG","machineImei:"+machineImei+"---imei:"+imei);
    }

    private Api api;
    private void toLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getLoginInfo(new UserInfoEntity(name, pwd, "120c83f7602202584d2", "android"))
                .subscribeOn(Schedulers.io())               //http请求线程
                .observeOn(AndroidSchedulers.mainThread())  //回调线程
                .subscribe(new Observer<UserInfoEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onNext(UserInfoEntity entity) {
                        Log.e("TAG","自动登录信息："+entity.getEntity().toString());
                        BaseApplication.setUserInfoEntity(WelcomeActivity.this, entity);


                        handler.sendEmptyMessageDelayed(2,2000);

                    }
                });
    }

    private String name;
    private String pwd;

    private void getLoginInfo(){

        SharedPreferences sp = getSharedPreferences(XMLName.NAME_USER_INFO, MODE_PRIVATE);
        name = sp.getString(XMLName.NAME_USERTITLE,null);
        pwd = sp.getString(XMLName.NAME_USERPWD,null);
        Log.e("TAG","yhm:"+name+"psw:"+pwd);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
