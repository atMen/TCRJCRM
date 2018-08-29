package com.tcrj.spv.views;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.tcrj.spv.presenter.VisitSignPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.BitmapToBase64Util;
import com.tcrj.spv.views.utils.BitmapUtil;
import com.tcrj.spv.views.utils.ImageUtils;
import com.tcrj.spv.views.utils.PermissionUtils;

import java.io.File;

/**
 * 拜访签到
 */
public class VisitSignActivity extends BaseActivity implements View.OnClickListener,
        LocationSource, AMapLocationListener, AMap.OnMapTouchListener, VisitSignCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private LinearLayout tj_qd;
    private ImageView imgVisitPhoto;
    private LinearLayout layoutCustomer;
    private TextView tvVisitsingCustomer;
    private TextView wclb;
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
    private MyCancelCallback myCancelCallback = new MyCancelCallback();
    private CommonalityEntity commonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitsign);
        initView();
        setCommonality();

        showProgressDialog();
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
                    Intent intent = new Intent(VisitSignActivity.this, PositionInfoActivity.class);
                    intent.putExtra("latitude", latlng.latitude);
                    intent.putExtra("longitude", latlng.longitude);
                    startActivityForResult(intent, 100);
                }
            });
        }


        //构造业务逻辑控制类
        new VisitSignPresenter(this);
    }



    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setCustomerID("0");
        commonality.setLatitude(34.4654654);
        commonality.setLongitude(101.545648);
        commonality.setAddress("");
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tj_qd = (LinearLayout) findViewById(R.id.tj_qd);
        mapView = (MapView) findViewById(R.id.sign_mapview);
        imgVisitPhoto = (ImageView) findViewById(R.id.img_visit_photo);
        layoutCustomer = (LinearLayout) findViewById(R.id.layout_visitsign_customer);
        tvVisitsingCustomer = (TextView) findViewById(R.id.tv_visitsing_customer);
        wclb = (TextView) findViewById(R.id.wclb);

        btnVisitsignSubmit = (Button) findViewById(R.id.btn_visitsign_submit);
        tvTitle.setText("拜访签到");
        imgBack.setOnClickListener(this);
        layoutCustomer.setOnClickListener(this);
        imgVisitPhoto.setOnClickListener(this);
        btnVisitsignSubmit.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO) {
            //这中情况下 data是为null的，因为自定义了路径 所以通过这个路径来获取
            Bitmap smallBitmap = BitmapUtil.getimage(photoPath);

            // ok 拿到图片的base64 上传
            String base64 = BitmapToBase64Util.bitmapToBase64(smallBitmap);

            Log.e("TAG","base64: "+base64);
            if(base64 != null){
                displayToast("拍照成功");
                commonality.setCameraImg(base64);
            }else {
                displayToast("拍照失败,请重新拍照");
                commonality.setCameraImg("");
            }
        }

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
            case 102:
                if (resultCode == 4) {
                    Bundle b = data.getExtras();             //data为B中回传的Intent
                    String pid = b.getString("VisitId");     //str即为回传的值
                    String pName = b.getString("VisitName");
                    wclb.setVisibility(View.GONE);
                    commonality.setCustomerID(pid);
                    tvVisitsingCustomer.setText(pName);
                }
                break;


        }
    }

//---------------------------------------------------------------------------------------------
    private static final String TAG = "MainActivity";

    public static final int TAKE_PHOTO = 111;


    //保存 照片的目录
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mms";
    private File photo_file = new File(path);

    private String photoPath;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
        outState.putString("photoPath", photoPath);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (TextUtils.isEmpty(photoPath)) {
            photoPath = savedInstanceState.getString("photoPath");
        }
        Log.d(TAG, "onRestoreInstanceState");
    }

    public void takePhoto() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (!photo_file.exists()) {
            photo_file.mkdirs();
        }
        photo_file = new File(path, "/temp.jpg");
        photoPath = path + "/temp.jpg";
        if (photo_file != null) {
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo_file));
            startActivityForResult(captureIntent, TAKE_PHOTO);
        }
    }


//---------------------------------------------------------------------------------------------


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

                tj_qd.setVisibility(View.VISIBLE);
                dismisProgressDialog();
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
            case R.id.layout_visitsign_customer:
                Intent intent = new Intent(VisitSignActivity.this, SignCustomerActivity.class);
//                Intent intent = new Intent(VisitSignActivity.this, SignMineCustomerActivity.class);
                startActivityForResult(intent, 102);
                break;
            case R.id.img_visit_photo:
                takePhoto();


//                DialogTakePhoto photo = new DialogTakePhoto(this);
//                photo.setOnClickListener(new DialogTakePhoto.ITakePhotoCallBack() {
//                    @Override
//                    public void setOnTakePhotoListener() {
////                        tackPhoto();
////                        displayToast("敬请期待");
//                        takePhoto();
//                    }
//
//                    @Override
//                    public void setOnPicturesListener() {
//                        Intent intents = new Intent(
//                                Intent.ACTION_PICK,
//                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intents, ImageUtils.RESULT_LOAD_IMAGE);
//                    }
//                });
//                photo.show();
                break;
            case R.id.btn_visitsign_submit:
                String trim = tvVisitsingCustomer.getText().toString().trim();
                Log.e("TAG","拜访人："+trim);
//                if("".equals(trim)){
//                    displayToast("请选择拜访人");
//                }
//
                if("".equals(commonality.getCameraImg())){
                    displayToast("请进行拍照");
                }else{
                    showProgressDialog("正在上传信息..");
                    presenter.start();
                }

                break;
        }
    }

    /**
     * 拍照功能
     */
    public void tackPhoto() {
        Log.e("TAG","拍照");
        ImageUtils.photo(VisitSignActivity.this);

        PermissionUtils.requestPermissions(this, 1, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new
                PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ImageUtils.photo(VisitSignActivity.this);
                        Toast.makeText(VisitSignActivity.this, "已授权", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied() {

                        Toast.makeText(VisitSignActivity.this, "未授权", Toast.LENGTH_SHORT).show();
                    }
                }
        );
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


    //签到信息提交成功后的操作----存在的问题：没有刷新列表界面
    @Override
    public void submit(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                displayToast(entity.getMsg());
                finish();
            } else {
                displayToast(entity.getMsg());
            }
        }else {
            displayToast("信息上传失败,请重新上传");
        }
    }

    //存在问题：未设置相关图片，导致在列表页没有显示签到的图片
    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setUserId(user.getEntity().getUserId());
        entity.setCusId(commonality.getCustomerID());
        entity.setCameraImg(".jpg|"+commonality.getCameraImg());
        entity.setMapImg("");
        entity.setXCoord(commonality.getLatitude());
        entity.setYCoord(commonality.getLongitude());
        entity.setSingPlace(commonality.getAddress());
        entity.setBigMapImg("");
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
    public void activate(OnLocationChangedListener listener) {
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
}
