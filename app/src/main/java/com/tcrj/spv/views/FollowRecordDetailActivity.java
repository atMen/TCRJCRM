package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.TraceDetailCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.CustomerTraceEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.TraceDetailPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;

/**
 * 跟进记录详情
 */
public class FollowRecordDetailActivity extends BaseActivity implements View.OnClickListener, TraceDetailCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvRecordUname;
    private TextView tvRecordPerson;
    private TextView tvRecordDate;
    private TextView tvRecordName;
    private TextView tvRecordJob;
    private TextView tvRecordTime;
    private TextView tvRecordWays;
    private TextView tvRecordStatus;
    private TextView tvRecordMaturity;
    private TextView tvRecordFollow;
    private Button btnRecordDelete;
    private TraceDetailCallBack.Presenter presenter;
    private int tid;
    private CommonalityEntity commonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followrecordetail);
        tid = getIntent().getIntExtra("TID", 0);
        new TraceDetailPresenter(this, 0);
        setCommonality();
        initView();
        presenter.start();
    }

    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setContactType("");
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRecordUname = (TextView) findViewById(R.id.tv_record_uname);
        tvRecordPerson = (TextView) findViewById(R.id.tv_record_person);
        tvRecordDate = (TextView) findViewById(R.id.tv_record_date);
        tvRecordName = (TextView) findViewById(R.id.tv_record_name);
        tvRecordJob = (TextView) findViewById(R.id.tv_record_job);
        tvRecordTime = (TextView) findViewById(R.id.tv_record_time);
        tvRecordWays = (TextView) findViewById(R.id.tv_record_ways);
        tvRecordStatus = (TextView) findViewById(R.id.tv_record_status);
        tvRecordMaturity = (TextView) findViewById(R.id.tv_record_maturity);
        tvRecordFollow = (TextView) findViewById(R.id.tv_record_follow);
        btnRecordDelete = (Button) findViewById(R.id.btn_record_delete);
        imgBack.setOnClickListener(this);
        tvTitle.setText("跟进记录详情");
        btnRecordDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_record_delete:
                new TraceDetailPresenter(this, 1);
                final MessageDialogBuilder mssage = MessageDialogBuilder.getInstance(this);
                mssage.setEffect(Effectstype.Slidetop);
                mssage.setDuration(700);
                mssage.setTitles("删除跟进记录");
                mssage.setContents("确定要删除跟进记录吗？");
                mssage.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        presenter.start();
                        mssage.dismiss();
                    }

                    @Override
                    public void setCancelListener() {
                        mssage.dismiss();
                    }
                });
                mssage.show();
                break;
        }
    }

    @Override
    public void setPresenter(TraceDetailCallBack.Presenter presenter) {
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

    /**
     * 加载数据
     *
     * @param entity
     */
    @Override
    public void getData(CustomerTraceEntity entity) {
        if (entity != null) {
            Log.e("TAG","详情："+entity.getEntity().toString());
            commonality.setContactType(entity.getEntity().getContactTypeId());
            tvRecordUname.setText(entity.getEntity().getCustomerName());
            tvRecordPerson.setText(entity.getEntity().getStaffName());
            tvRecordDate.setText(entity.getEntity().getContactDate());
            tvRecordName.setText(entity.getEntity().getContactName());
            tvRecordJob.setText(entity.getEntity().getPosition());
            tvRecordTime.setText(entity.getEntity().getContactDate());
            tvRecordWays.setText(entity.getEntity().getContactType());
            tvRecordStatus.setText(entity.getEntity().getStatus());
            tvRecordMaturity.setText(entity.getEntity().getMaturityName());
            tvRecordFollow.setText(entity.getEntity().getTraceResult());
        }
    }

    /**
     * 删除跟进记录
     *
     * @param entity
     */
    @Override
    public void delete(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                Intent intent = new Intent();
                setResult(3, intent);
                finish();
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setTId(String.valueOf(tid));
        entity.setCTID(tid);
        entity.setContactType(commonality.getContactType());
        entity.setUserId(user.getEntity().getUserId());
        return entity;
    }
}
