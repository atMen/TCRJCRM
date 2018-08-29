package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.tcrj.spv.R;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.views.adapter.GouldPoiAdapter;
import com.tcrj.spv.views.application.BaseActivity;

import java.util.List;

/**
 * 位置信息
 */
public class PositionInfoActivity extends BaseActivity implements View.OnClickListener, LocationSource {
    private ImageButton imgBack;
    private TextView tvTitle;
    private ListView listView;
    private AMap aMap;
    private LinearLayout layoutWord;
    private TextView tvSubTitle;
    private MapView mapView;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private List<PoiItem> poiItems;// poi数据
    private LatLonPoint point = null;
    private GouldPoiAdapter adapter;
    private double latitude;
    private double longitude;
    private CommonalityEntity commonal;
    private boolean isSelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioninfo);
        initView();
        mapView.onCreate(savedInstanceState);//此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        }
        setCommonal();
        onLocation();
        loadData();
    }

    private void setCommonal() {
        commonal = new CommonalityEntity();
        commonal.setLatitude(39.993743);
        commonal.setLongitude(116.472995);
        commonal.setAddress("");
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        latitude = getIntent().getDoubleExtra("latitude", 39.993743);
        longitude = getIntent().getDoubleExtra("longitude", 116.472995);
        point = new LatLonPoint(latitude, longitude);
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mapView = (MapView) findViewById(R.id.position_map);
        listView = (ListView) findViewById(R.id.position_listview);
        layoutWord = (LinearLayout) findViewById(R.id.layout_word);
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        imgBack.setOnClickListener(this);
        layoutWord.setOnClickListener(this);
        tvTitle.setText("位置信息");
        tvSubTitle.setText("确定");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_word:
                if (isSelect) {
                    Intent intent = new Intent();
                    intent.putExtra("address", commonal.getAddress());
                    intent.putExtra("latitude", commonal.getLatitude());
                    intent.putExtra("longitude", commonal.getLongitude());
                    setResult(5, intent);
                    finish();
                } else {
                    displayToast("请选择位置");
                    return;
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
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
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
     * 查询结果
     */
    private void loadData() {
        showProgressDialog();
        query = new PoiSearch.Query("大厦", "", "");
        query.setPageNum(1);
        query.setPageSize(15);
        if (point != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setBound(new PoiSearch.SearchBound(point, 1000, true));
            poiSearch.searchPOIAsyn();//异步搜索
            poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                @Override
                public void onPoiSearched(PoiResult result, int code) {
                    dismisProgressDialog();
                    if (code == AMapException.CODE_AMAP_SUCCESS) {
                        if (result != null && result.getQuery() != null) {
                            poiItems = result.getPois();
                            int count = result.getPois().size();
                            if (poiItems != null && count > 0) {
                                adapter = new GouldPoiAdapter(PositionInfoActivity.this);
                                adapter.setData(poiItems);
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        isSelect = true;
                                        PoiItem item = (PoiItem) adapter.getItem(position);
                                        LatLonPoint lngPoint = item.getLatLonPoint();
                                        LatLng lng = new LatLng(lngPoint.getLatitude(), lngPoint.getLongitude());
                                        commonal.setLatitude(lngPoint.getLatitude());
                                        commonal.setLongitude(lngPoint.getLongitude());
                                        commonal.setAddress(item.getProvinceName() + item.getCityName() + item.getAdName() + item.getTitle());
                                        adapter.setSelectItem(position);
                                        adapter.notifyDataSetChanged();
                                        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(lng, 18, 30, 30)));
                                        aMap.clear();
                                        aMap.addMarker(new MarkerOptions().position(lng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.img_location_address)));
                                    }
                                });
                            } else {
                                displayToast("对不起，没有搜索到相关数据！");
                            }
                        } else {
                            displayToast("对不起，没有搜索到相关数据！");
                        }
                    } else {
                        displayToast("错误编码：" + code);
                    }
                }
                @Override
                public void onPoiItemSearched(PoiItem poiItem, int code) {

                }
            });
        } else {
            dismisProgressDialog();
        }
    }

    /**
     * 初始化定位
     */
    public void onLocation() {
        if (point != null) {
            LatLng latLng = new LatLng(point.getLatitude(), point.getLongitude());
            aMap.clear();
            aMap.addMarker(new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.img_location_address))
                    .anchor(0.5f, 0.5f));
            //首次定位,选择移动到地图中心点并修改级别到15级
            changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 30)));
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            //mlocationClient.setLocationListener(this);
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

    @Override
    public void deactivate() {
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update) {
        aMap.moveCamera(update);
    }
}
