package com.tcrj.spv.views;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.tcrj.spv.R;
import com.tcrj.spv.callback.GpsCallBack;
import com.tcrj.spv.model.LocationEntity1;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.GpsPresenter;
import com.tcrj.spv.views.adapter.InfoWinAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 定位跟踪//数据解析错误
 */
public class GPSActivity extends BaseActivity implements View.OnClickListener
       , AMap.OnMapTouchListener, GpsCallBack.View,AMap.OnMapClickListener{

    private ImageButton imgBack;
    private TextView tv_more;
    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvName;
    private TextView tvAdress;
    private LinearLayout map_layout;
    private LinearLayout layoutSearchResult;
    private EditText edtSearchResult;

    private AMap aMap;
    private MapView mapView;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private boolean useMoveToLocationWithMapMode = true;
    //自定义定位小蓝点的Marker
    private Marker locationMarker;
    //坐标和经纬度转换工具
    private Projection projection;

    private GpsCallBack.Presenter presenter;
    private GPSActivity.MyCancelCallback myCancelCallback = new GPSActivity.MyCancelCallback();

    UserInfoEntity user;
    private InfoWinAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        user = BaseApplication.getUserInfoEntity();
        initView();

        //初始化地图
        initMap(savedInstanceState);

        //构造业务逻辑控制类
        new GpsPresenter(this);

        presenter.start();

    }

    private void initMap(Bundle savedInstanceState) {
        //初始化高德地图
        mapView.onCreate(savedInstanceState);//此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setOnMapTouchListener(this);
            aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                   //标题框的点击事件

//                    Toast.makeText(GPSActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }




    @Override
    public void initView() {
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tv_more = (TextView) findViewById(R.id.tv_more);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAdress = (TextView) findViewById(R.id.tv_adress);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvTime = (TextView) findViewById(R.id.tv_time);
        map_layout = (LinearLayout) findViewById(R.id.map_layout);

        tv_more.setVisibility(View.VISIBLE);
        tv_more.setOnClickListener(this);
        tvTitle.setText("定位跟踪");
        mapView = (MapView) findViewById(R.id.sign_mapview);
        tv_more.setVisibility(View.VISIBLE);
        //返回的点击事件
        imgBack.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        layoutSearchResult.setOnClickListener(this);
    }



    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        useMoveToLocationWithMapMode = true;
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        useMoveToLocationWithMapMode = false;
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }


    /**
     * 展示InfoWindow
     *

     */
    private void setInfoWindow() {

        aMap.clear();

        if(markers.size()>0){
            final ArrayList<Marker> list = aMap.addMarkers(markers, true);
            LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度

            for(int i=0;i<list.size();i++){
                boundsBuilder.include(markers.get(i).getPosition());//把所有点都include进去（LatLng类型）

//                screenMarkerJump(aMap,list.get(i));
//                Animation animation = new TranslateAnimation(markers.get(i).getPosition()); //初始化生长效果动画
//
//                long duration = 1300L;
//                animation.setDuration(duration);
//                animation.setInterpolator(new LinearInterpolator());
//                list.get(i).setAnimation(animation);
//                list.get(i).startAnimation();

            }
            aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));//第二个参数为四周留空宽度

            aMap.setOnMapClickListener(this);

            adapter = new InfoWinAdapter();
            aMap.setInfoWindowAdapter(adapter);
//      marker的点击事件
            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    currentMarker=marker;
                    String title1 = marker.getTitle();

                    marker.showInfoWindow();
                    map_layout.setVisibility(View.VISIBLE);
                    tvAdress.setText(marker.getSnippet());
                    tvName.setText(marker.getTitle());
                    int position = (int)marker.getZIndex();
                    Log.e("TAG","时间："+(int)marker.getZIndex());
                    tvTime.setText(mapLocation.get(position).getAddDatetime());


                    return true;
                }
            });




        }else{
            displayToast("未检索到相关人员");
        }


    }

//    public void screenMarkerJump(AMap aMap, Marker screenMarker) {
//        if (screenMarker != null) {
//            final LatLng latLng = screenMarker.getPosition();
//            Point point = aMap.getProjection().toScreenLocation(latLng);
//            point.y -= Utils.dip2px(this, 20);
//            LatLng target = aMap.getProjection()
//                    .fromScreenLocation(point);
//            //使用TranslateAnimation,填写一个需要移动的目标点
//            Animation animation = new TranslateAnimation(target);
//            animation.setInterpolator(new Interpolator() {
//                @Override
//                public float getInterpolation(float input) {
//                    // 模拟重加速度的interpolator
//                    if (input <= 0.5) {
//                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
//                    } else {
//                        return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
//                    }
//                }
//            });
//            //整个移动所需要的时间
//            animation.setDuration(1000);
//            //设置动画
//            screenMarker.setAnimation(animation);
//            //开始动画
//            screenMarker.startAnimation();
//        }
//    }

    private Marker currentMarker;



    @Override
    public void onMapClick(LatLng arg0) {
        Log.e("TAG","地图点击："+currentMarker.isInfoWindowShown());
        if(currentMarker.isInfoWindowShown()){
            currentMarker.hideInfoWindow();//这个是隐藏infowindow窗口的方法
        }
    }


    @Override
    public void onTouch(MotionEvent motionEvent) {
        Log.i("amap", "onTouch 关闭地图和小蓝点一起移动的模式");
        useMoveToLocationWithMapMode = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_search_result:
                showProgressDialog();
                String trim = edtSearchResult.getText().toString().trim();
                if(!("".equals(trim))){
                    List<LocationEntity1.MapLocationBean> list = getList(trim);
                    Log.e("TAG","检索到的集合："+list.size());
                    if(list.size() <= 0){
                        showMark(list);
                    }else{
                        //显示mark
                        showMark(list);
                    }
                }else{

                    for(int i=0;i<mapLocation.size();i++){
                        LocationEntity1.MapLocationBean mapLocationBean = mapLocation.get(i);
                        MarkerOptions markerOption = new MarkerOptions();
                        markerOption.icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.map_gz))
                                .position(new LatLng( Double.valueOf(mapLocationBean.getX()),Double.valueOf(mapLocationBean.getY())))
                                .title(mapLocationBean.getStaffName())
                                .snippet(mapLocationBean.getAddress())
                                .zIndex(i)

                                .draggable(true);
                        markers.add(markerOption);
                    }

                    setInfoWindow();
                }
                 dismisProgressDialog();


                break;
            case R.id.img_back:
                finish();
                break;

            case R.id.tv_more:
                if(mapLocation.size() == 0){
                    Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
                }else {
                    openNewActivity(GPSListActivity.class);
                }

                break;

        }
    }

    private void showMark(List<LocationEntity1.MapLocationBean> list) {
        markers.clear();

        if(list.size() > 0){
            Log.e("TAG","showMark:"+list.size());
            for(int i=0;i<list.size();i++){
                LocationEntity1.MapLocationBean mapLocationBean = list.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.map_gz))
                        .position(new LatLng( Double.valueOf(mapLocationBean.getX()),Double.valueOf(mapLocationBean.getY())))
                        .title(mapLocationBean.getStaffName())
                        .snippet(mapLocationBean.getAddress())
                        .zIndex(i)
                        .draggable(true);
                markers.add(markerOption);
            }
        }

        setInfoWindow();
    }

    //获取搜索到的信息
    private List<LocationEntity1.MapLocationBean> SsmapLocation = new ArrayList<>();
    private List<LocationEntity1.MapLocationBean> getList(String trim) {
        SsmapLocation.clear();

        for(int i=0;i<mapLocation.size();i++){
            LocationEntity1.MapLocationBean mapLocationBean = mapLocation.get(i);
            String staffName = mapLocationBean.getStaffName();
            if(staffName.contains(trim)){
                Log.e("TAG","姓名："+staffName+"输入框："+trim);
                SsmapLocation.add(mapLocationBean);
            }
        }

        return SsmapLocation;
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



    ArrayList<MarkerOptions> markers = new ArrayList<>();
    List<LocationEntity1.MapLocationBean> mapLocation;

    @Override
    public void getData(LocationEntity1 locationEntities) {

        mapLocation = locationEntities.getMapLocation();
        Log.e("TAG","多少个点："+mapLocation.size());


        for(int i=0;i<mapLocation.size();i++){
            LocationEntity1.MapLocationBean mapLocationBean = mapLocation.get(i);
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.icon(BitmapDescriptorFactory
                    .fromResource(R.mipmap.map_gz))
                    .position(new LatLng( Double.valueOf(mapLocationBean.getX()),Double.valueOf(mapLocationBean.getY())))
                    .title(mapLocationBean.getStaffName())
                    .snippet(mapLocationBean.getAddress())
                    .zIndex(i)
                    .draggable(true);
            markers.add(markerOption);
        }

        setInfoWindow();

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



    /**
     * 监控地图动画移动情况，如果结束或者被打断，都需要执行响应的操作
     */
    class MyCancelCallback implements AMap.CancelableCallback {
        LatLng targetLatlng;

        public void setTargetLatlng(LatLng latlng) {
            this.targetLatlng = latlng;
        }

        @Override
        public void onFinish() {
            if (locationMarker != null && targetLatlng != null) {
                locationMarker.setPosition(targetLatlng);
            }
        }

        @Override
        public void onCancel() {
            if (locationMarker != null && targetLatlng != null) {
                locationMarker.setPosition(targetLatlng);
            }
        }
    }





}
