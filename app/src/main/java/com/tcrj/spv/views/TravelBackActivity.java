package com.tcrj.spv.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogDateTimePicker;
import com.tcrj.spv.views.utils.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tcrj.spv.views.application.BaseApplication.context;

/**
 * Created by leict on 2018/8/8.
 */

public class TravelBackActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tvtime;
    private LinearLayout ll_time;
    private Button btn_send;
    private EditText content;

    private ccSPInfo listBean;
    private String nodeId;
    private String userTitle;
    private int workId;

    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        listBean = (ccSPInfo) getIntent().getSerializableExtra("ccInfo");

        nodeId = getIntent().getStringExtra("nodeId");
        workId = getIntent().getIntExtra("workId",-1);


        initView();

    }

    private void initView() {


        tvtime = (TextView) findViewById(R.id.time);
        ll_time = (LinearLayout) findViewById(R.id.ll_time);
        btn_send = (Button) findViewById(R.id.btn_send);
        content = (EditText) findViewById(R.id.content);

        ll_time.setOnClickListener(this);
        btn_send.setOnClickListener(this);


        SharedPreferences e = context.getSharedPreferences("Login", 0);
        userTitle = e.getString("userTitle","");

        ccSPInfo.TravelBeanX.TravelBean travel = listBean.getTravel().getTravel();
        String iBackTime = listBean.getTravel().getIBackTime();
        String summary = travel.getSummary();


        if(!TextUtils.isEmpty(iBackTime)){
            tvtime.setText(iBackTime);
            content.setText(summary);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_time:
                setTimd();
                break;

            case R.id.btn_send:
                String summary = content.getText().toString().trim();
                String time = tvtime.getText().toString().trim();

                if(TextUtils.isEmpty(summary)){
                    Toast.makeText(this, "请填写出差总结", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("请选择时间".equals(time)){
                    Toast.makeText(this, "请选择返回时间", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendMsg(summary,time);
                break;

            default:
                break;
        }
    }

    private void sendMsg(String summary, String time) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewTravelCheckDialogList(new ccSPInfo(
                1,workId, nodeId,
                userTitle,"",0,
                time,summary))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ccSPInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TravelBackActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ccSPInfo entity) {

                        Toast.makeText(TravelBackActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent();
                            setResult(3, i);
                            finish();

                    }
                });
    }




    private void setTimd() {

        DialogDateTimePicker start = new DialogDateTimePicker(this);
        start.onDatePickerListener(new DialogDateTimePicker.DatePickerCallBack() {
            @Override
            public void onClickListener(String time) {
                tvtime.setText(time);

            }
        });
        start.show();
    }
}
