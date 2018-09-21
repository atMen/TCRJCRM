package com.tcrj.spv.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tcrj.spv.MainActivity;
import com.tcrj.spv.R;
import com.tcrj.spv.callback.UserInfoCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.UserInfoPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.PhoneInfo;

/**
 * 用户登录
 * Created by leict on 2017/10/23.
 */

public class LoginActivity extends BaseActivity implements UserInfoCallBack.View {
    private static String TAG = "LoginActivity";
    private UserInfoCallBack.Presenter presenter;
    private EditText edtUesraccount;
    private EditText edtUserpassword;
    private Button btnLogin;
    UserInfoEntity userInfoEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String userid = getIntent().getStringExtra("userid");
        String userpwd = getIntent().getStringExtra("userpwd");

//        userInfoEntity = BaseApplication.getUserInfoEntity();
        new UserInfoPresenter(this);
        initView();

        if(userid != null || userpwd != null){
            edtUesraccount.setText(userid);
            edtUserpassword.setText(userpwd);
        }
    }

//    private void getLoginInfo(){
//
//        SharedPreferences sp = getSharedPreferences(XMLName.NAME_USER_INFO, MODE_PRIVATE);
//        String name = sp.getString(XMLName.NAME_USERTITLE,null);
//        String pwd = sp.getString(XMLName.NAME_USERPWD,null);
//        Log.e("TAG","yhm:"+name+"psw:"+pwd);
//        if(name != null || pwd != null){
//            edtUesraccount.setText(name);
//            edtUserpassword.setText(pwd);
//        }
//
//    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        edtUesraccount = (EditText) findViewById(R.id.edt_account);
        edtUserpassword = (EditText) findViewById(R.id.edt_password);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenter.start();
            }
        });
    }

    @Override
    public void setPresenter(UserInfoCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    /**
     * 访问服务单失败后返回的信息
     */
    @Override
    public void ServiceFailInfo(String message) {
        displayToast("服务端返回信息：" + message);
    }

    @Override
    public void getData(UserInfoEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {

                spLogin(entity.getEntity().getUserId(),entity.getEntity().getUserTitle());
                Log.e("TAG","登录时："+entity.getEntity().getIsKq());
                BaseApplication.setUserInfoEntity(this, entity);
                openNewActivity(MainActivity.class);
                finish();
            }
        }
    }

    private void spLogin(int s, String userTitle) {
        SharedPreferences app2 = getSharedPreferences("Login", 0);
        SharedPreferences.Editor edit = app2.edit();
        edit.putBoolean("isLogin", true);
        edit.putInt("userid",s);
        edit.putString("userTitle",userTitle);
        edit.commit();
    }

    @Override
    public ParameterEntity setParameter() {
        String uniqueId = PhoneInfo.getUniqueId(this);
        String account = edtUesraccount.getText().toString().trim();
        String password = edtUserpassword.getText().toString().trim();
        ParameterEntity entity = new ParameterEntity();
        entity.setUserName(account);
        entity.setUserPwd(password);
        entity.setAddress(uniqueId);
        LogUtils.info(TAG, entity);
        return entity;
    }
}
