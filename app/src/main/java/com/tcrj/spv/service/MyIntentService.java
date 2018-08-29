package com.tcrj.spv.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tcrj.spv.MainActivity;
import com.tcrj.spv.R;
import com.tcrj.spv.model.LocationInfo;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.XMLName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tcrj.spv.views.application.BaseApplication.context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

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

                    toUpdate(amapLocation.getLatitude(),amapLocation.getLongitude(),amapLocation.getAddress());

                    mHandler.sendEmptyMessageDelayed(1,300000);

                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());

                    toUpdate(0,0,"获取位置信息失败"+" ( 注："+amapLocation.getErrorInfo() +" )");
                    mHandler.sendEmptyMessageDelayed(1,300000);
                }
            }
        }
    };


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获得刚才发送的Message对象，然后在这里进行UI操作
            Log.e("TAG","------------> msg.what = " + msg.what);
            SharedPreferences e = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
            IsSell = e.getInt(XMLName.NAME_ISSELL, -1);
            Log.e("TAG","需要定位的人员"+IsSell);
            if(IsSell != 0){
                //启动定位
                mLocationClient.startLocation();
            }else{
                Log.e("TAG","不需要定位的人员");
                mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
            }



        }
    };


    private void toUpdate(double latitude, double longitude,String address) {
//        String latitudeold = getloction("latitude");
        long time = getloction("time");
        Log.e("TAG","保存的时间："+time);
        if(time < 0){
            Updata(latitude,longitude,address);//上传数据到服务器
        }else {
            final String s = gatData();
            long l = stringTOlong(s);
            long l1 = l - time;
            Log.e("TAG","当前的时间："+l+"对比的时间："+l1);
            if(l1 >= 300000){
                Updata(latitude,longitude,address);//上传数据到服务器
            }else {
                Log.e("TAG","不上传位置信息");
            }

        }




////      Log.e("TAG","latitudeold："+latitudeold+"  longitudeold:"+longitudeold);
//        Log.e("TAG","latitude："+latitude+"  longitude:"+longitude);
//
//        if( latitudeold.equals(latitude+"") && longitudeold.equals(longitude+"")){//位置没有变化，不上传到服务器
//            Log.e("TAG","位置没有发生变化不进行上传");
////            Updata(latitude,longitude,address);//上传数据到服务器
//        }else{
//            Log.e("TAG","位置变化正在进行上传");
//            addsp(latitude,longitude);//并保存新位置
////            Updata(latitude,longitude,address);//上传数据到服务器
//        }
    }

    private long stringTOlong(String l) {

        try {
            return DateUtils.stringToLong(l,"yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private String gatData(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        String format = simpleDateFormat.format(date);

        return format;

    }


    //联网上传位置信息
    private void Updata(double latitude, double longitude,String address) {

        if(UsereID == -1){
            SharedPreferences e = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
            UsereID = e.getInt(XMLName.NAME_USERID, -1);
        }



        final String s = gatData();
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
                        Log.e("TAG","错误");
                    }

                    @Override
                    public void onNext(LocationInfo locationEntity) {
                        Log.e("TAG","locationEntity:"+locationEntity.getMsg());

                        addTime(stringTOlong(s));
                        Log.e("TAG","保存long:"+stringTOlong(s));
                    }
                });

    }


    private void addsp(double latitude, double longitude){
        //方法注释得知，建议以0或者MODE_PRIVATE为默认值。
        SharedPreferences app2 = getSharedPreferences("loction", 0);
        //获取Editor对象
        SharedPreferences.Editor edit = app2.edit();
        //根据要保存的数据的类型，调用对应的put方法,
        //以键值对的形式添加新值。
        edit.putString("latitude",  latitude+"");
        edit.putString("longitude",  longitude+"");
        //提交新值。必须执行，否则前面的操作都无效。
        edit.commit();
    }

    private void addTime(long latitude){
        //方法注释得知，建议以0或者MODE_PRIVATE为默认值。
        SharedPreferences app2 = getSharedPreferences("loction", 0);
        //获取Editor对象
        SharedPreferences.Editor edit = app2.edit();
        //根据要保存的数据的类型，调用对应的put方法,
        //以键值对的形式添加新值。
        edit.putLong("time",  latitude);
//        edit.putString("longitude",  longitude+"");
        //提交新值。必须执行，否则前面的操作都无效。
        edit.commit();
    }

    private long getloction(String type){
        SharedPreferences pf = getSharedPreferences("loction", 0);
        //根据数据类型，调用对应的get方法，通过键取得对应的值。
        long string = pf.getLong(type, -1);

        return string;
    }

    public void getLoction(){
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption.setInterval(3000);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //启动定位
        mLocationClient.startLocation();

    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("TAG","onHandleIntent");

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("TAG","onCreate");
    }

    private int UsereID;
    private int IsSell;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        useForeground("协同办公","请保持程序在后台运行");

        mHandler.removeCallbacksAndMessages(null);
        SharedPreferences pf = getSharedPreferences("Login", 0);
        //根据数据类型，调用对应的get方法，通过键取得对应的值。
        UsereID = pf.getInt("userid", -1);
//        UsereID = intent.getIntExtra("userid",-1);
        //启动定位
        getLoction();
        Log.e("TAG","onStartCommand");
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    public void useForeground(CharSequence tickerText, String currSong) {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
	/* Method 01
	 * this method must SET SMALLICON!
	 * otherwise it can't do what we want in Android 4.4 KitKat,
	 * it can only show the application info page which contains the 'Force Close' button.*/
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(MyIntentService.this)
                .setSmallIcon(R.mipmap.logo)
                .setTicker(tickerText)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(getString(R.string.app_name))
                .setContentText(currSong)
                .setContentIntent(pendingIntent);
        Notification notification = mNotifyBuilder.build();

	/* Method 02
	Notification notification = new Notification(R.drawable.ic_launcher, tickerText,
	        System.currentTimeMillis());
	notification.setLatestEventInfo(PlayService.this, getText(R.string.app_name),
			currSong, pendingIntent);
	*/

        startForeground(1, notification);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG","onDestroy");
//        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        stopForeground(true);
        Intent localIntent = new Intent();
        localIntent.setClass(this, MyIntentService.class);  //销毁时重新启动Service
        this.startService(localIntent);
    }

}
