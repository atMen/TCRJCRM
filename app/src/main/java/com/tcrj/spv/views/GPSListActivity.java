package com.tcrj.spv.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.GpsCallBack;
import com.tcrj.spv.model.LocationEntity1;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.GpsPresenter;
import com.tcrj.spv.views.adapter.GpsInfoAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.xlist.XListView;

import java.util.List;

public class GPSListActivity extends BaseActivity implements GpsCallBack.View, View.OnClickListener {

    private ImageButton imgBack;
    private XListView listview;
    GpsInfoAdapter adapter;

    private TextView tvOne;

    private TextView tvTwo;

    private GpsCallBack.Presenter presenter;
    private int pageIndex = 1;
    UserInfoEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslist);
        user = BaseApplication.getUserInfoEntity();
        initView();
        showProgressDialog();
        //构造业务逻辑控制类
        new GpsPresenter(this);
        presenter.start();
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);

        //返回的点击事件
        imgBack.setOnClickListener(this);


        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvOne.setSelected(true);
        listview = (XListView) findViewById(R.id.customer_listview);
        listview.setPullRefreshEnable(false);
        listview.setPullLoadEnable(false);
//        listview.setXListViewListener(new XListView.IXListViewListener() {
//            @Override
//            public void onRefresh() {
//                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
//                pageIndex = 1;
//                presenter.start();
//            }
//
//            @Override
//            public void onLoadMore() {
//                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
//                pageIndex++;
//                presenter.start();
//            }
//        });



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:
                finish();
                break;

            case R.id.tv_one:
                tvOne.setSelected(true);
                tvTwo.setSelected(false);
                adapter = new GpsInfoAdapter(this, locationEntities1,0);
                listview.setAdapter(adapter);
                break;
            case R.id.tv_two:
                tvOne.setSelected(false);
                tvTwo.setSelected(true);

                adapter = new GpsInfoAdapter(this, locationEntities1,1);
                listview.setAdapter(adapter);
                break;



        }
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }


    //签到信息提交成功后的操作----存在的问题：没有刷新列表界面
    @Override
    public void submit(SubmitEntity entity) {

        if (entity != null) {
            if (entity.getState() == 1) {//刷新页面

                displayToast(entity.getMsg());
                finish();

            } else {
                displayToast(entity.getMsg());
            }
        }
    }



    List<LocationEntity1.MapLocationBean> mapLocation;
    List<LocationEntity1.NoMapLocationBean> noMapLocation;
    LocationEntity1 locationEntities1;
    @Override
    public void getData(LocationEntity1 locationEntities) {
        dismisProgressDialog();
        locationEntities1 = locationEntities;
        mapLocation = locationEntities.getMapLocation();
        noMapLocation = locationEntities.getNoMapLocation();


        if(mapLocation != null){
            tvOne.setText("已展示 ("+mapLocation.size()+") 人");
        }
//        Log.e("TAG","签到多少个点："+mapLocation.size());

        if(noMapLocation != null){
            tvTwo.setText("未展示 ("+noMapLocation.size()+") 人");
        }


        adapter = new GpsInfoAdapter(this, locationEntities,0);
        listview.setAdapter(adapter);

    }


    //存在问题：未设置相关图片，导致在列表页没有显示签到的图片
    @Override
    public ParameterEntity setParameter() {


        ParameterEntity entity = new ParameterEntity();

        entity.setUserId(user.getEntity().getUserId());


        return entity;
    }

    @Override
    public void setPresenter(GpsCallBack.Presenter presenter) {
        this.presenter = presenter;
    }



    }



