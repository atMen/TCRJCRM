package com.tcrj.spv;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tcrj.spv.doubleprogress.AccountHelper;
import com.tcrj.spv.model.LocationInfo;
import com.tcrj.spv.model.MenuEntity;
import com.tcrj.spv.model.NewDay;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.onePXactivity.BootCompleteReceiver;
import com.tcrj.spv.service.MyIntentService;
import com.tcrj.spv.views.CommunicationListActvity;
import com.tcrj.spv.views.ConcerningActivity;
import com.tcrj.spv.views.CustomerInputActivity;
import com.tcrj.spv.views.ExpatriateSignActivity;
import com.tcrj.spv.views.GPSActivity;
import com.tcrj.spv.views.LeaderExamineActivity;
import com.tcrj.spv.views.LeaveApplyActivity;
import com.tcrj.spv.views.LoginActivity;
import com.tcrj.spv.views.MineCustomerActivity;
import com.tcrj.spv.views.NoticeActivity;
import com.tcrj.spv.views.SignRecordActivity;
import com.tcrj.spv.views.TravelApplyActivity;
import com.tcrj.spv.views.UpdatePwdActivity;
import com.tcrj.spv.views.VisitRecordActivity;
import com.tcrj.spv.views.WorkDailyActivity;
import com.tcrj.spv.views.WriteDailyAddActivity;
import com.tcrj.spv.views.WriteFollowActivity;
import com.tcrj.spv.views.adapter.MainMenuAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.control.MyGridView;
import com.tcrj.spv.views.dialog.SweetAlertDialog;
import com.tcrj.spv.views.utils.Api;
import com.tcrj.spv.views.utils.XMLName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.tcrj.spv.views.application.BaseApplication.context;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout layoutManage = null;
    private NavigationView navigationView = null;
    private ActionBarDrawerToggle toggle = null;
    private Toolbar toolbar = null;
    private DrawerLayout drawer = null;
    private MyGridView gridQuick = null;
    private MyGridView gridWork = null;
    private MyGridView gridCustom = null;
    private MainMenuAdapter adapter1 = null;
    private MainMenuAdapter adapter2 = null;
    private MainMenuAdapter adapter3 = null;
    private ImageView imgCenterPhoto;
    private TextView tvCenterName;
    private TextView tvCenterScore;
    private UserInfoEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        initView();
        loadQuick();
        loadWork();
        loadCustom();
        CheckUpdate();//检查更新

    }
    private BootCompleteReceiver mBootCompleteReceiver;

    private void initReceiver() {

        if (mBootCompleteReceiver == null){
            mBootCompleteReceiver = new BootCompleteReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mBootCompleteReceiver,filter);
        }
    }



    /**
     * 检查最新版本
     *
     * @return
     */
    private void CheckUpdate() {
//        Log.e("TAG","登录时的版本号："+entity.getEntity().getCurrentVersion());
//        UpdateManager um = new UpdateManager(MainActivity.this);
//        um.checkUpdate(entity.getEntity().getCurrentVersion(), 1);

        initprermmission();
//        init();
    }

    private void initprermmission() {
        RxPermissions.getInstance(MainActivity.this)
                .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            init();
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "yes");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "no");
                            Toast.makeText(MainActivity.this, "SD卡权限被拒绝，无法进行在线更新功能", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    private void init() {
        showProgressDialog();
        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(MainActivity.this,
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        dismisProgressDialog();
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(MainActivity.this)
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
                                                        MainActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        dismisProgressDialog();

                    }
                });
    }



    @Override
    public void initView() {
        gridQuick = (MyGridView) findViewById(R.id.grid_sign);
        gridWork = (MyGridView) findViewById(R.id.grid_office);
        gridCustom = (MyGridView) findViewById(R.id.grid_custom);

        layoutManage = (LinearLayout) findViewById(R.id.layout_custom_manage);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.setDrawerListener(toggle);
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        tvCenterName = (TextView) headerLayout.findViewById(R.id.tv_center_name);
        imgCenterPhoto = (ImageView) headerLayout.findViewById(R.id.img_center_photo);
        tvCenterScore = (TextView) headerLayout.findViewById(R.id.tv_center_score);
        entity = BaseApplication.getUserInfoEntity();

        if (entity != null) {

            tvCenterName.setText(entity.getEntity().getUserName());
            tvCenterScore.setText("当前积分：" + entity.getEntity().getIntegral());

            Log.e("TAG","entity.getEntity().getIsSell():"+entity.getEntity().getIsSell());
            if (entity.getEntity().getIsSell() == 1) {

                AccountHelper.addAccount(this);
                AccountHelper.autoSync();

                //上传实时位置信息
                Intent intent = new Intent(this, MyIntentService.class);
                //启动IntentService
                intent.putExtra("userid", entity.getEntity().getUserId());

                startService(intent);

            }


        }
    }

    /**
     * 加载快捷菜单数据
     */
    private void loadQuick() {
//      UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        CharSequence[] sign = this.getResources().getStringArray(R.array.quick_menu_sign);
        CharSequence[] sign_in = this.getResources().getStringArray(R.array.quick_menu_sign_in);
        CharSequence[] sign_out = this.getResources().getStringArray(R.array.quick_menu_sign_out);
        CharSequence[] market = this.getResources().getStringArray(R.array.quick_menu_market);
        CharSequence[] market_in = this.getResources().getStringArray(R.array.quick_menu_market_in);
        CharSequence[] market_out = this.getResources().getStringArray(R.array.quick_menu_market_out);

        TypedArray imagesign = this.getResources().obtainTypedArray(R.array.image_menu_sign);
        TypedArray imagesign_in = this.getResources().obtainTypedArray(R.array.image_menu_sign_in);

        TypedArray imagesign_out = this.getResources().obtainTypedArray(R.array.image_menu_sign_out);
        TypedArray imagemarket = this.getResources().obtainTypedArray(R.array.image_menu_market);
        TypedArray imagemarket_in = this.getResources().obtainTypedArray(R.array.image_menu_market_in);
        TypedArray imagemarket_out = this.getResources().obtainTypedArray(R.array.image_menu_market_out);
        List<MenuEntity> dataList = new ArrayList<>();
        adapter1 = new MainMenuAdapter(this);

        Log.e("TAG","签到签退："+entity.getEntity().getIsKq());
        //判断是否是营销
        if (entity.getEntity().getIsSell() == 0) {

            //判断是否外派人员：外派签到
            if (entity.getEntity().getIsOut() == 1 && entity.getEntity().getIsKq() == 0) {
                for (int i = 0; i < sign_in.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagesign_in.getResourceId(i, 0));
                    quick.setMenuName(sign_in[i].toString());
                    dataList.add(quick);
                }
            }
            //外派签退
            else if (entity.getEntity().getIsOut() == 1 && entity.getEntity().getIsKq() == 1) {
                for (int i = 0; i < sign_out.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagesign_out.getResourceId(i, 0));
                    quick.setMenuName(sign_out[i].toString());
                    dataList.add(quick);
                }
            } else {
                for (int i = 0; i < sign.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagesign.getResourceId(i, 0));
                    quick.setMenuName(sign[i].toString());
                    dataList.add(quick);
                }
            }
        } else {
            //判断是否外派人员：外派签到
            if (entity.getEntity().getIsOut() == 1 && entity.getEntity().getIsKq() == 0) {
                for (int i = 0; i < market_in.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagemarket_in.getResourceId(i, 0));
                    quick.setMenuName(market_in[i].toString());
                    dataList.add(quick);
                }
            }
            //外派签退
            else if (entity.getEntity().getIsOut() == 1 && entity.getEntity().getIsKq() == 1) {
                for (int i = 0; i < market_out.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagemarket_out.getResourceId(i, 0));
                    quick.setMenuName(market_out[i].toString());
                    dataList.add(quick);
                }
            } else {
                for (int i = 0; i < market.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imagemarket.getResourceId(i, 0));
                    quick.setMenuName(market[i].toString());
                    dataList.add(quick);
                }
            }
        }

        adapter1.setData(dataList);
        gridQuick.setAdapter(adapter1);
        gridQuick.setOnItemClickListener(new QuickOnItemClickListener());

    }

    /**
     * 快捷菜单GridView事件
     */
    private class QuickOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuEntity entity = (MenuEntity) adapter1.getItem(position);
            if (entity.getMenuName().equals("写日报")) {

                //判断昨日日报是否填写
                loadTimeData();
//                openNewActivity(WriteDailyActivity.class);
            } else if (entity.getMenuName().equals("请假申请")) {
                openNewActivity(LeaveApplyActivity.class);
            } else if (entity.getMenuName().equals("出差申请")) {
                openNewActivity(TravelApplyActivity.class);
            } else if (entity.getMenuName().equals("拜访签到")) {
                openNewActivity(VisitRecordActivity.class);
            } else if (entity.getMenuName().equals("写跟进")) {
                openNewActivity(WriteFollowActivity.class);
            } else if (entity.getMenuName().equals("客户录入")) {
                openNewActivity(CustomerInputActivity.class);
            } else if (entity.getMenuName().equals("外派签到")) {
                openNewActivity(ExpatriateSignActivity.class);
            } else if (entity.getMenuName().equals("外派签退")) {
                openNewActivity(ExpatriateSignActivity.class);
            } else if (entity.getMenuName().equals("签到记录")) {
                openNewActivity(SignRecordActivity.class);
            }
        }
    }

    /**
     * 加载日常办公数据
     */
    private void loadWork() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();

        CharSequence[] workNormal = this.getResources().getStringArray(R.array.work_menu_normal);
        CharSequence[] workLocation = this.getResources().getStringArray(R.array.work_menu_location);
        CharSequence[] sdLocation = this.getResources().getStringArray(R.array.work_menu_sd_location);
        CharSequence[] sdworkNormal = this.getResources().getStringArray(R.array.work_menu_sd_normal);

        TypedArray sdimageNormal = this.getResources().obtainTypedArray(R.array.image_work_sd_location);
        TypedArray imageNormal = this.getResources().obtainTypedArray(R.array.image_work_normal);
        TypedArray imageLocation = this.getResources().obtainTypedArray(R.array.image_work_location);

        TypedArray sdimageLocation = this.getResources().obtainTypedArray(R.array.image_work_dw_location);

        List<MenuEntity> dataList = new ArrayList<>();
        adapter2 = new MainMenuAdapter(this);

//        if (entity.getEntity().getIsHavexj() == 0 && entity.getEntity().getIsSell() == 0) {
//            for (int i = 0; i < workNormal.length; i++) {
//                MenuEntity quick = new MenuEntity();
//                quick.setMenuId(i + 1);
//                quick.setImageId(imageNormal.getResourceId(i, 0));
//                quick.setMenuName(workNormal[i].toString());
//                dataList.add(quick);
//            }
//        } else if (entity.getEntity().getIsHavexj() == 1){
//            for (int i = 0; i < workLocation.length; i++) {
//                MenuEntity quick = new MenuEntity();
//                quick.setMenuId(i + 1);
//                quick.setImageId(imageLocation.getResourceId(i, 0));
//                quick.setMenuName(workLocation[i].toString());
//                dataList.add(quick);
//            }
//        }else{
//            for (int i = 0; i < workNormal.length; i++) {
//                MenuEntity quick = new MenuEntity();
//                quick.setMenuId(i + 1);
//                quick.setImageId(imageNormal.getResourceId(i, 0));
//                quick.setMenuName(workNormal[i].toString());
//                dataList.add(quick);
//            }
//        }

        if (entity.getEntity().getIsHavexj() == 0 ) {

            if(entity.getEntity().getIsSell() == 1){
                for (int i = 0; i < sdworkNormal.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(sdimageLocation.getResourceId(i, 0));
                    quick.setMenuName(sdworkNormal[i].toString());
                    dataList.add(quick);
                }
            }else {
                for (int i = 0; i < workNormal.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imageNormal.getResourceId(i, 0));
                    quick.setMenuName(workNormal[i].toString());
                    dataList.add(quick);
                }
            }



        } else if (entity.getEntity().getIsHavexj() == 1){


            if(entity.getEntity().getIsSell() == 1){
                for (int i = 0; i < sdLocation.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(sdimageNormal.getResourceId(i, 0));
                    quick.setMenuName(sdLocation[i].toString());
                    dataList.add(quick);
                }
            }else {
                for (int i = 0; i < workLocation.length; i++) {
                    MenuEntity quick = new MenuEntity();
                    quick.setMenuId(i + 1);
                    quick.setImageId(imageLocation.getResourceId(i, 0));
                    quick.setMenuName(workLocation[i].toString());
                    dataList.add(quick);
                }
            }


        }

        adapter2.setData(dataList);
        gridWork.setAdapter(adapter2);
        gridWork.setOnItemClickListener(new WorkOnItemListener());
    }

    private class WorkOnItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuEntity entity = (MenuEntity) adapter2.getItem(position);
            if (entity.getMenuName().equals("通知公告")) {
                openNewActivity(NoticeActivity.class);
            } else if (entity.getMenuName().equals("工作日报")) {
                openNewActivity(WorkDailyActivity.class);
            } else if (entity.getMenuName().equals("领导审批")) {
                openNewActivity(LeaderExamineActivity.class);
            } else if (entity.getMenuName().equals("手动定位")){

                Log.e("TAG","手动定位");

                getLoction();

            }else{
                /**
                 *
                 * 定位跟踪
                 */
                openNewActivity(GPSActivity.class);
            }
        }
    }

    /**
     * 加载客户管理
     */
    private void loadCustom() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        Log.e("TAG","是否是营销："+entity.getEntity().getIsSell());
        if (entity.getEntity().getIsSell() == 0) {
            layoutManage.setVisibility(View.GONE);
        } else {
            layoutManage.setVisibility(View.VISIBLE);
            CharSequence[] customManager = this.getResources().getStringArray(R.array.custom_menu_manager);
            TypedArray imageManager = this.getResources().obtainTypedArray(R.array.image_custom_manager);
            List<MenuEntity> dataList = new ArrayList<>();
            adapter3 = new MainMenuAdapter(this);
            for (int i = 0; i < customManager.length; i++) {
                MenuEntity quick = new MenuEntity();
                quick.setMenuId(i + 1);
                quick.setImageId(imageManager.getResourceId(i, 0));
                quick.setMenuName(customManager[i].toString());
                dataList.add(quick);
            }
            adapter3.setData(dataList);
            gridCustom.setAdapter(adapter3);
            gridCustom.setOnItemClickListener(new CustomerItemClikListener());
        }
    }

    /**
     * 客户管理
     */
    private class CustomerItemClikListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuEntity entity = (MenuEntity) adapter3.getItem(position);
            if (entity.getMenuName().equals("客户录入")) {
                openNewActivity(CustomerInputActivity.class);
            } else if (entity.getMenuName().equals("客户管理")) {
                openNewActivity(MineCustomerActivity.class);
            } else if (entity.getMenuName().equals("客户跟进")) {
                displayToast("敬请期待");
            } else if (entity.getMenuName().equals("客户分析")) {

                displayToast("敬请期待");
            }
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        BaseApplication.context.startActivity(intent);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //侧滑菜单点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_address://通讯录
                openNewActivity(CommunicationListActvity.class);
                break;
            case R.id.menu_password://修改密码
                openNewActivity(UpdatePwdActivity.class);
                break;
            case R.id.menu_about://关于我们
                openNewActivity(ConcerningActivity.class);
                break;
            case R.id.menu_out://注销登录
                showDialog();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

//        TODO:测试是否可以进行按钮的刷新
        loadQuick();
        Log.e("TAG","onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PgyCrashManager.unregister();
    }

    public void loadTimeData() {
        int userId = entity.getEntity().getUserId();
        Log.e("TAG","userId"+userId);
        showProgressDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewDayLogFill(new NewDay(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewDay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewDay newDay) {
                        Log.e("TAG","成功"+newDay.toString());

                        dismisProgressDialog();
                        int state = newDay.getState();
                        if(state == 1){
                            //昨日日报未编辑，弹出dialog提示用户补写昨日日报

                            showDialog(newDay);

                        }else{
//                            showDialog(newDay);
                            //昨日日报已编辑
                            openNewActivity(WriteDailyAddActivity.class);
                        }

                    }
                });
    }

    private void showDialog(final NewDay newDay) {
        final SweetAlertDialog teldialog = new SweetAlertDialog(this);
        teldialog.setTitleText("温馨提示");
        teldialog.setContentText("昨天日报没有填写");
        teldialog.setConfirmText("补写昨日日报");
        teldialog.setCancelText("写今天日报");
        teldialog.setCanceledOnTouchOutside(true);
        teldialog.setCancelable(true);
        teldialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                teldialog.dismiss();
                openNewActivity(WriteDailyAddActivity.class);
            }
        });
        teldialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                teldialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("LogDate", newDay.getLogDate());
                intent.setClass(getApplicationContext(), WriteDailyAddActivity.class);
                startActivity(intent);
            }
        });
        teldialog.show();
    }

    private void showDialog() {
        final SweetAlertDialog teldialog = new SweetAlertDialog(this);
        teldialog.setTitleText("温馨提示");
        teldialog.setContentText("确定要注销登录吗");
        teldialog.setConfirmText("取消");
        teldialog.setCancelText("确定");
        teldialog.setCanceledOnTouchOutside(true);
        teldialog.setCancelable(true);
        teldialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                teldialog.dismiss();
                out();
            }
        });
        teldialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                teldialog.dismiss();
            }
        });
        teldialog.show();
    }

    private void out() {
        SharedPreferences app2 = getSharedPreferences("Login", 0);
        SharedPreferences.Editor edit = app2.edit();
        edit.putBoolean("isLogin", false);
        edit.commit();

        openNewActivity(LoginActivity.class);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
            moveTaskToBack(false);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息

            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
//            System.exit(0);
        }
    }

    //定义一个变量，
    //来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };





    private Api api;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    Log.e("定位成功","时间："+gatData()+"维度："
                            + amapLocation.getLatitude() + ", 经度:"
                            + amapLocation.getLongitude()+ ", 位置信息:"+amapLocation.getAddress());

                    //判断是否上传位置到服务器
                    Updata(amapLocation.getLatitude(),amapLocation.getLongitude(),amapLocation.getAddress());


                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    showErrorDialog();
                }
            }
        }
    };
    //手动定位
    public void getLoction(){

        showProgressDialog("正在获取位置信息");
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//      mLocationOption.setInterval(3000);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //启动定位
        mLocationClient.startLocation();

    }


    private String gatData(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        String format = simpleDateFormat.format(date);

        return format;

    }


    private int UsereID;
    //联网上传位置信息
    private void Updata(double latitude, double longitude,String address) {

        SharedPreferences e = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
        UsereID = e.getInt(XMLName.NAME_USERID, -1);

        String s = gatData();
        Log.e("TAG","开始上传:UsereID"+UsereID+"时间："+s);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        //替换跟踪定位的接口
        api.getLoctionInfo(new LocationInfo(address,longitude+"",s,UsereID,latitude+""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        dismisProgressDialog();
                        Log.e("TAG","错误");

                        showErrorDialog();

                    }

                    @Override
                    public void onNext(LocationInfo locationEntity) {
                        dismisProgressDialog();
                        Log.e("TAG","手动--locationEntity:"+locationEntity.getMsg());
                        showUpdateDialog();

                    }
                });

    }

    private void showErrorDialog() {
        final SweetAlertDialog sad = new SweetAlertDialog(this);
        sad.setTitleText("手动定位失败");
        sad.setContentText("");
        sad.setConfirmText("重新定位");
        sad.setCancelText("取消");
        sad.setCanceledOnTouchOutside(true);
        sad.setCancelable(true);
        sad.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sad.dismiss();


            }
        });
        sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                getLoction();
                sad.dismiss();

            }
        });
        sad.show();
    }


    private void showUpdateDialog() {

/*		Intent intent = new Intent();
        intent.setClass(mContext, IsDownLoad.class);
		mContext.startActivity(intent);*/
        final SweetAlertDialog sad = new SweetAlertDialog(this);
        sad.setTitleText("手动定位成功");
        sad.setContentText("");
        sad.setConfirmText("确定");
        sad.setCanceledOnTouchOutside(true);
        sad.setCancelable(true);
        sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sad.dismiss();

            }
        });
        sad.show();
    }


}
