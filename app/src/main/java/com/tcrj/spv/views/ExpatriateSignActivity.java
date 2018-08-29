package com.tcrj.spv.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.tcrj.spv.R;
import com.tcrj.spv.callback.VisitSignCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.ExpatriateSignPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * 外派签到
 */
public class ExpatriateSignActivity extends BaseActivity implements View.OnClickListener,
        LocationSource, AMapLocationListener, AMap.OnMapTouchListener, VisitSignCallBack.View{


    private ImageButton imgBack;
    private TextView tvTitle;
    private Button btnVisitsignSubmit;


    private AMap aMap;
    private MapView mapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private boolean useMoveToLocationWithMapMode = true;
    //自定义定位小蓝点的Marker
    private Marker locationMarker;
    //坐标和经纬度转换工具
    private Projection projection;
    private MarkerOptions markerOption;
    private VisitSignCallBack.Presenter presenter;
    private ExpatriateSignActivity.MyCancelCallback myCancelCallback = new ExpatriateSignActivity.MyCancelCallback();
    private CommonalityEntity commonality;

    UserInfoEntity user;
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expatriatesign);
        user = BaseApplication.getUserInfoEntity();
        this.savedInstanceState = savedInstanceState;
        initView();
//
//        //判断是否为android6.0系统版本，如果是，需要动态添加权限
//        if (Build.VERSION.SDK_INT>=23){
//            showContacts();
//            Log.e("TAG","等待权限");
//        }else{
//            Log.e("TAG","直接开启");
//
//        }

        setCommonality();
        initMap(savedInstanceState);




        //构造业务逻辑控制类
        new ExpatriateSignPresenter(this);


    }




    private void initMap(Bundle savedInstanceState) {
        //初始化高德地图
        mapView.onCreate(savedInstanceState);//此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setOnMapTouchListener(this);
            aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    LatLng latlng = marker.getPosition();
                    Intent intent = new Intent(ExpatriateSignActivity.this, PositionInfoActivity.class);
                    intent.putExtra("latitude", latlng.latitude);
                    intent.putExtra("longitude", latlng.longitude);
                    startActivityForResult(intent, 100);
                }
            });
        }

    }
    private int QDState = 1;
    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setJQNo(takeData());
        commonality.setQDQT(QDState);
        commonality.setAddress("");
    }



    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("外派签到");
        mapView = (MapView) findViewById(R.id.sign_mapview);
        btnVisitsignSubmit = (Button) findViewById(R.id.btn_visitsign_submit);

        //返回的点击事件
        imgBack.setOnClickListener(this);
        //提交的点击事件
        btnVisitsignSubmit.setOnClickListener(this);

        if (user.getEntity().getIsKq() == 0) {
            tvTitle.setText("外派签到");
            QDState = 1;
            btnVisitsignSubmit.setText("签到");
        } else {
            tvTitle.setText("外派签退");
            QDState = 2;
            btnVisitsignSubmit.setText("签退");
        }


    }

    //获取到手机相关信息
    private String takeData(){

        final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;

        tmDevice = "" + tm.getDeviceId();

        tmSerial = "" + tm.getSimSerialNumber();

        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.
                hashCode() << 32) | tmSerial.hashCode());

        String uniqueId = deviceUuid.toString();

        Log.e("TAG","uniqueId"+uniqueId);

        return uniqueId;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == 5) {
                    Bundle b = data.getExtras(); //data为B中回传的Intent
                    String address = b.getString("address");//str即为回传的值
                    double lating = b.getDouble("latitude");
                    double longitude = b.getDouble("longitude");
                    LatLng lng = new LatLng(lating, longitude);
                    commonality.setLatitude(lating);
                    commonality.setLongitude(longitude);
                    commonality.setAddress(address);
                    setInfoWindow(address, lng);
                }
                break;

        }
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
        deactivate();
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
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                commonality.setLatitude(latLng.latitude);
                commonality.setLongitude(latLng.longitude);
                commonality.setAddress(amapLocation.getAddress());
                //展示自定义定位小蓝点
                if (locationMarker == null) {
                    //首次定位
                    setInfoWindow(amapLocation.getAddress(), latLng);
                } else {
                    if (useMoveToLocationWithMapMode) {
                        //二次以后定位，使用sdk中没有的模式，让地图和小蓝点一起移动到中心点（类似导航锁车时的效果）
                        startMoveLocationAndMap(latLng);
                    } else {
                        startChangeLocation(latLng);
                    }
                }

                //定位成功后显示签到按钮
                btnVisitsignSubmit.setVisibility(View.VISIBLE);
            } else {
                displayToast("定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 展示InfoWindow
     *
     * @param title
     * @param latLng
     */
    private void setInfoWindow(String title, LatLng latLng) {
        //首次定位
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromResource(R.mipmap.img_location))
                .position(latLng)
                .title(title)
                .draggable(true);
        aMap.clear();
        locationMarker = aMap.addMarker(markerOption);

        locationMarker.showInfoWindow();
        //首次定位,选择移动到地图中心点并修改级别到15级
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        //marker的点击事件
//        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                String title1 = marker.getTitle();
//                Log.e("TAG","点击marker的监听事件："+title1);
//                return true;
//            }
//        });

    }

    /**
     * 修改自定义定位小蓝点的位置
     *
     * @param latLng
     */
    private void startChangeLocation(LatLng latLng) {
        if (locationMarker != null) {
            LatLng curLatlng = locationMarker.getPosition();
            if (curLatlng == null || !curLatlng.equals(latLng)) {
                locationMarker.setPosition(latLng);
            }
        }
    }

    /**
     * 同时修改自定义定位小蓝点和地图的位置
     *
     * @param latLng
     */
    private void startMoveLocationAndMap(LatLng latLng) {
        //将小蓝点提取到屏幕上
        if (projection == null) {
            projection = aMap.getProjection();
        }
        if (locationMarker != null && projection != null) {
            LatLng markerLocation = locationMarker.getPosition();
            Point screenPosition = aMap.getProjection().toScreenLocation(markerLocation);
            locationMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        }
        //移动地图，移动结束后，将小蓝点放到放到地图上
        myCancelCallback.setTargetLatlng(latLng);
        //动画移动的时间，最好不要比定位间隔长，如果定位间隔2000ms 动画移动时间最好小于2000ms，可以使用1000ms
        //如果超过了，需要在myCancelCallback中进行处理被打断的情况
        aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng), 1000, myCancelCallback);
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        Log.i("amap", "onTouch 关闭地图和小蓝点一起移动的模式");
        useMoveToLocationWithMapMode = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.btn_visitsign_submit:

                //提交的点击处理
                presenter.start();

                break;
        }
    }



    @Override
    public void setPresenter(VisitSignCallBack.Presenter presenter) {
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


    private SubmitEntity entity;
    //签到信息提交成功后的操作----存在的问题：没有刷新列表界面
    @Override
    public void submit(SubmitEntity entity) {

        if (entity != null) {
            this.entity = entity;
            if (entity.getState() == 1) {//刷新页面

                if(QDState == 1){
                    user.getEntity().setIsKq(1);
                }else{
                    user.getEntity().setIsKq(2);
                }

                BaseApplication.setUserInfoEntity(ExpatriateSignActivity.this,user);

                handler3.sendEmptyMessage(1);


            } else {
                handler3.sendEmptyMessage(2);

            }
        }
    }

    public Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    displayToast(entity.getMsg());
                    finish();
                    break;
                case 2:
                    displayToast(entity.getMsg());
                    break;

            }
        }
    };

    private JSONObject setJsonParameter() {
        Log.e("TAG","标识："+commonality.getQDQT()+"id:"+user.getEntity().getUserId()+"地址："+commonality.getAddress());
        JSONObject json = new JSONObject();
        try {
            json.put("JQNo", commonality.getJQNo());
            json.put("QDQT", commonality.getQDQT());
            json.put("Address", commonality.getAddress());
            json.put("StaffID", user.getEntity().getUserId());
        } catch (JSONException e) {
        }
        return json;
    }

    //存在问题：未设置相关图片，导致在列表页没有显示签到的图片
    @Override
    public ParameterEntity setParameter() {

        String s = setJsonParameter().toString();


        ParameterEntity entity = new ParameterEntity();


        entity.setAddress(s);

        return entity;
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

    /**
     * 激活定位
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //是指定位间隔
            mLocationOption.setInterval(2000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }



    private static final int BAIDU_READ_PHONE_STATE =100;

    public void showContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                ) {
//            Toast.makeText(getApplicationContext(),"没有权限,请手动开启定位权限",Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(ExpatriateSignActivity.this,new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE
            }, BAIDU_READ_PHONE_STATE);
        }else{
//            init();
            Toast.makeText(this, "获取到权限1", Toast.LENGTH_SHORT).show();
//            setCommonality();
//            initMap(savedInstanceState);
        }
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
//                    init();
                    Toast.makeText(this, "获取到权限2", Toast.LENGTH_SHORT).show();

//                    setCommonality();
//                    initMap(savedInstanceState);
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，无法开启定位", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


}
