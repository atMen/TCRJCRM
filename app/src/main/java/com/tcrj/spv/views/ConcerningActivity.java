package com.tcrj.spv.views;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tcrj.spv.FeedbackActivity;
import com.tcrj.spv.R;
import com.tcrj.spv.checkUpdata.UpdateManager;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;

import rx.functions.Action1;

/**
 * 关于
 */
public class ConcerningActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvVersionCode;
    private LinearLayout layoutVersionFuncation;
    private LinearLayout layoutVersionFeedback;
    private LinearLayout layoutVersionUpdate;
    private LinearLayout layoutVersionSkill;

    private ImageView layout_versions_pic_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concerning);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvVersionCode = (TextView) findViewById(R.id.tv_version_code);
        layoutVersionFuncation = (LinearLayout) findViewById(R.id.layout_version_funcation);
        layoutVersionFeedback = (LinearLayout) findViewById(R.id.layout_version_feedback);
        layoutVersionUpdate = (LinearLayout) findViewById(R.id.layout_version_update);
        layoutVersionSkill = (LinearLayout) findViewById(R.id.layout_version_skill);
        layout_versions_pic_new = (ImageView) findViewById(R.id.layout_versions_pic_new);
        tvTitle.setText("关于我们");
        imgBack.setOnClickListener(this);
        layoutVersionFeedback.setOnClickListener(this);
        layoutVersionFuncation.setOnClickListener(this);
        layoutVersionSkill.setOnClickListener(this);
        layoutVersionUpdate.setOnClickListener(this);
        ss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_version_feedback:
                Intent advice = new Intent(ConcerningActivity.this, FeedbackActivity.class);
                startActivity(advice);
                break;
            case R.id.layout_version_funcation:
                displayToast("敬请期待");
                break;
            case R.id.layout_version_skill:
                //技术支持
                call("13546385762");
                break;
            case R.id.layout_version_update:
//                CheckUpdate();
                initprermmission();
                break;
        }
    }



    private void initprermmission() {
        RxPermissions.getInstance(ConcerningActivity.this)
                .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            init();
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "yes");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "no");
                            Toast.makeText(ConcerningActivity.this, "SD卡权限被拒绝，无法进行在线更新功能", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    private void init() {
        showProgressDialog();
        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(ConcerningActivity.this,
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        dismisProgressDialog();
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(ConcerningActivity.this)
                                .setTitle("更新")
                                .setMessage(appBean.getReleaseNote())
                                .setNegativeButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(



                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
                                                        ConcerningActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        dismisProgressDialog();
                        Toast.makeText(ConcerningActivity.this, "当前为最新版本", Toast.LENGTH_LONG).show();

                    }
                });
    }


    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**
     * 检查最新版本
     *
     * @return
     */
    private void CheckUpdate() {
        UpdateManager um = new UpdateManager(ConcerningActivity.this);
        um.checkUpdate("", 0);
    }


    private void ss(){

        try {
            UserInfoEntity Usesr = BaseApplication.getUserInfoEntity();
            PackageManager um = this.getPackageManager();
            PackageInfo pi = um.getPackageInfo(this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            int versionCode = pi.versionCode;
            tvVersionCode.setText("V " + pi.versionName);
            String rt = Usesr.getEntity().getCurrentVersion();
            if (versionCode >= Integer.parseInt(rt)) {
                layout_versions_pic_new.setVisibility(View.GONE);
            } else {
                layout_versions_pic_new.setVisibility(View.VISIBLE);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}
