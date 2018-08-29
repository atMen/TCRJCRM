package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NewDayLogItemListCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.presenter.NewDayLogItemListPresenter;
import com.tcrj.spv.views.adapter.NewDayLogItemLisAdapter;
import com.tcrj.spv.views.adapter.expand.ItemEntity;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class NewDayLogItemListActivity extends BaseActivity implements View.OnClickListener, NewDayLogItemListCallBack.View {

    private ImageButton imgBack;
    private TextView tvTitle;

    private ListView daylogitem_list;
    private ItemEntity person = null;

    private NewDayLogItemListCallBack.Presenter presenter;
    UserInfoEntity user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_day_log_item_list);
        new NewDayLogItemListPresenter(this);
        user = BaseApplication.getUserInfoEntity();

        person = (ItemEntity) getIntent().getSerializableExtra("person_data");

        initView();
        initData();

        presenter.start();


    }

    private void toActivity(int position) {
        WorkDailtItemEntity.DataBean dataBean = entity.getData().get(position);
        Intent _Intent = new Intent();
        _Intent.setClass(this, WriteDailyActivity.class);
        _Intent.putExtra("data",dataBean);
        Log.e("TAG","getTime:"+person.getTime());
        _Intent.putExtra("Time",person.getTime());
        startActivityForResult(_Intent, 100);
    }

    private void initData() {
        imgBack.setOnClickListener(this);
        tvTitle.setText("日报项列表");
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        daylogitem_list = (ListView) findViewById(R.id.daylogitem_list);

    }

    @Override
    public void onClick(View v) {

        finish();
    }



    //获取到业务逻辑实例
    @Override
    public void setPresenter(NewDayLogItemListCallBack.Presenter presenter) {
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


    WorkDailtItemEntity entity;
    NewDayLogItemLisAdapter newDayLogItemLisAdapter;
    //获取数据
    @Override
    public void getData(WorkDailtItemEntity entity) {

        Log.e("TAG","getData:"+entity.getMsg());
        this.entity = entity;

        if(entity != null){
            List<WorkDailtItemEntity.DataBean> data = entity.getData();
            newDayLogItemLisAdapter = new NewDayLogItemLisAdapter(entity,NewDayLogItemListActivity.this);
            daylogitem_list.setAdapter(newDayLogItemLisAdapter);
            newDayLogItemLisAdapter.setOnclikListener(new NewDayLogItemLisAdapter.OnMyclikListener() {
                @Override
                public void onclik(int v) {
                    toActivity(v);
                }
            });
        }

    }

    @Override
    public ParameterEntity setParameter() {
        ParameterEntity entity = new ParameterEntity();
        entity.setStaffId(user.getEntity().getUserId());
        entity.setLogID(Integer.parseInt(person.getLogId()));
        entity.setLogDate(DateUtils.getDateTimeString(new Date()));//日报时间
        return entity;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                Log.e("TAG","回调的刷新");
                presenter.start();
                break;
        }
    }


}
