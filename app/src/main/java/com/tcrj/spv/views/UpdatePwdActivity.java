package com.tcrj.spv.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.PasswordCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.PasswordEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.PasswordPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener, PasswordCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private EditText edtOldPassword;
    private EditText edtNewPassword;
    private EditText edtRepeatPassword;
    private Button btnPasswordSubmit;
    private PasswordCallBack.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);
        new PasswordPresenter(this);
        initView();
    }

    /**
     * 加载页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        edtOldPassword = (EditText) findViewById(R.id.edt_old_password);
        edtNewPassword = (EditText) findViewById(R.id.edt_new_password);
        edtRepeatPassword = (EditText) findViewById(R.id.edt_repeat_password);
        btnPasswordSubmit = (Button) findViewById(R.id.btn_password_submit);
        tvTitle.setText("修改密码");
        imgBack.setOnClickListener(this);
        btnPasswordSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_password_submit:
                UserInfoEntity user = BaseApplication.getUserInfoEntity();
                String newPwd = edtNewPassword.getText().toString();
                String oldPwd = edtOldPassword.getText().toString();
                String repeat = edtRepeatPassword.getText().toString();
                if (!Utils.isStringEmpty(oldPwd)) {
                    if (oldPwd.equals(user.getEntity().getUserPwd())) {
                        if (!Utils.isStringEmpty(newPwd)) {
                            if (!newPwd.equals(user.getEntity().getUserPwd())) {
                                if (!Utils.isStringEmpty(repeat)) {
                                    if (repeat.equals(newPwd)) {
                                        presenter.start();
                                    } else {
                                        displayToast("输入的密码不一致");
                                    }
                                } else {
                                    displayToast("密码不能为空");
                                }
                            } else {
                                displayToast("新密码与旧密码不能一样");
                            }
                        } else {
                            displayToast("密码不能为空");
                        }
                    } else {
                        displayToast("原密码错误");
                    }
                } else {
                    displayToast("密码不能为空");
                }
                break;
        }
    }

    @Override
    public void setPresenter(PasswordCallBack.Presenter presenter) {
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

    @Override
    public void getData(PasswordEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                displayToast(entity.getMsg());
                finish();
            } else {
                displayToast(entity.getMsg());
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        String oldPwd = edtOldPassword.getText().toString().trim();
        String newPwd = edtNewPassword.getText().toString().trim();
        entity.setStaffId(user.getEntity().getUserId());
        entity.setOldPwd(oldPwd);
        entity.setNewPwd(newPwd);
        LogUtils.info("UpdatePwdActivity", entity);
        return entity;
    }
}

