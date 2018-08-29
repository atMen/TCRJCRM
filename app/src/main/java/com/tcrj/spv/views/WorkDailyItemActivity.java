package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.adapter.expand.ItemEntity;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.control.CircleImageView;
import com.tcrj.spv.views.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 编辑页面
 * TODO：还需要根据时间来判断是否可以进行修改内容
 */
public class WorkDailyItemActivity extends BaseActivity implements View.OnClickListener {

    public CircleImageView imgPhoto;
    public TextView tvDailyUsername;
    public TextView tvDailyExamine;
    public TextView tvDailyTime;
    public TextView tvDailyTodaywork;
    public TextView tvDailyTomorrowplan;

    private ItemEntity person = null;
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvRightBtn;
    private LinearLayout layout_word;

    String person_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_daily_item);

        person = (ItemEntity) getIntent().getSerializableExtra("person_data");
        person_type = getIntent().getStringExtra("person_type");




        initView();
        initData();
    }

    @Override
    public void initView() {

        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRightBtn = (TextView) findViewById(R.id.tv_sub_title);
        layout_word = (LinearLayout) findViewById(R.id.layout_word);

        imgPhoto = (CircleImageView) findViewById(R.id.img_photo);
        tvDailyUsername = (TextView) findViewById(R.id.tv_daily_username);
        tvDailyExamine = (TextView) findViewById(R.id.tv_daily_examine);
        tvDailyTime = (TextView) findViewById(R.id.tv_daily_time);
        tvDailyTodaywork = (TextView) findViewById(R.id.tv_daily_todaywork);
        tvDailyTomorrowplan = (TextView) findViewById(R.id.tv_daily_tomorrowplan);


        try {
            isToWrite();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getData(){
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
        String format = matter1.format(dt);
        return format;
    }

    private String getDatasfm(){
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = matter1.format(dt);
        return format;
    }

    boolean isWrite = true;
    private void isToWrite() throws ParseException {
        String time = person.getTime();
        String data = getData();
        Log.e("TAG","当前时间："+data+"日报时间："+time);
        long l = DateUtils.stringToLong(data, "yyyy-MM-dd");
        long l1 = DateUtils.stringToLong(time, "yyyy-MM-dd");
        Log.e("TAG","差值："+(l-l1));//86400000

        if((l-l1) == 86400000){

            //判断是否超过了中午十二点

            String lastTimeString = data + " 12:00:00";
            long lastTime = DateUtils.stringToLong(lastTimeString, "yyyy-MM-dd HH:mm:ss");
            Log.e("TAG","判断是否超过了中午十二点"+lastTimeString);
            String datasfm = getDatasfm();
            long nowTime = DateUtils.stringToLong(datasfm, "yyyy-MM-dd HH:mm:ss");
            Log.e("TAG","当前时间："+datasfm);
            Log.e("TAG","当天十二点的long值："+lastTime);
            Log.e("TAG","当前时间的long值："+nowTime);
            Log.e("TAG","编辑判断（差值）："+(lastTime - nowTime));
            if(lastTime - nowTime >= 0){
                Log.e("TAG","可以进行编辑");
                isWrite = true;


            }else{
                Log.e("TAG","不可以进行编辑");

                isWrite = false;

            }


        }else if((l-l1) == 0){
            isWrite = true;
        } else{
            isWrite = false;
            Log.e("TAG","已超过了编辑时间");
//            displayToast("已超过了编辑时间");
        }
    }

//    //获取当天十二点的long值
//    private long getLastTime() {
//
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, 12);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        return c.getTimeInMillis();
//    }

    private void initData() {

        tvTitle.setText("日报详情");
        tvRightBtn.setText("编辑");
        if (person!=null){
            tvDailyUsername.setText(person.getStaffName());
            tvDailyExamine.setText(person.getReviewsQuality());
            tvDailyTime.setText(person.getHour());
            tvDailyTodaywork.setText(person.getLogContent());
            tvDailyTomorrowplan.setText(person.getPlanContent());
        }

        if(person_type != null){

            layout_word.setVisibility(View.GONE);
        }else{
            layout_word.setVisibility(View.VISIBLE);
        }

        imgBack.setOnClickListener(this);
        layout_word.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_word:
                //根据当前时间与日报创建时间比较判断是否可以进行编辑日报（可以在刚进入页面的时候进行判断）
                //进入编辑页面

                if(isWrite){

                    Intent _Intent = new Intent();
                    _Intent.setClass(this, NewDayLogItemListActivity.class);
                    _Intent.putExtra("person_data", person);
                    startActivity(_Intent);
                }else{
                    displayToast("已超过了可编辑时间");
                }


                break;

        }
    }
}
