package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.LeaveApplyCallBack;
import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.spInfo;
import com.tcrj.spv.presenter.LeaveApplyPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogDateTimePicker;
import com.tcrj.spv.views.dialog.DialogLeaveApply;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.text.ParseException;
import java.util.Date;

/**
 * 请假申请
 */
public class LeaveApplyActivity extends BaseActivity implements View.OnClickListener, LeaveApplyCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private LinearLayout layoutType;
    private TextView tvLeaveType;
    private LinearLayout layoutLeaveStartime;
    private TextView tvLeaveStartime;
    private LinearLayout layoutLeaveEndtime;
    private TextView tvLeaveEndtime;
    private TextView tvLeaveDays;
    private Button btnLeaveSubmit;
    private EditText edtLeaveThing;
    private TextView tvLeaveName;
    private TextView tvLeaveDepart;
    private TextView tvLeaveNumber;

    private LeaveApplyCallBack.Presenter presenter;
    private spInfo listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaveapply);
        new LeaveApplyPresenter(this, 0);
        time =(new Date()).getTime();
        listBean = (spInfo) getIntent().getSerializableExtra("qjinfo");
        initView();
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        layoutLeaveStartime = (LinearLayout) findViewById(R.id.layout_leave_startime);
        tvLeaveStartime = (TextView) findViewById(R.id.tv_leave_startime);
        layoutLeaveEndtime = (LinearLayout) findViewById(R.id.layout_leave_endtime);
        tvLeaveEndtime = (TextView) findViewById(R.id.tv_leave_endtime);
        tvLeaveDays = (TextView) findViewById(R.id.tv_leave_days);
        layoutType = (LinearLayout) findViewById(R.id.layout_leave_type);
        tvLeaveType = (TextView) findViewById(R.id.tv_leave_type);
        btnLeaveSubmit = (Button) findViewById(R.id.btn_leave_submit);
        edtLeaveThing = (EditText) findViewById(R.id.edt_leave_thing);
        tvLeaveName = (TextView) findViewById(R.id.tv_leave_name);
        tvLeaveDepart = (TextView) findViewById(R.id.tv_leave_depart);
        tvLeaveNumber = (TextView) findViewById(R.id.tv_leave_number);
        tvTitle.setText("请假申请");
        imgBack.setOnClickListener(this);
        layoutType.setOnClickListener(this);
        layoutLeaveStartime.setOnClickListener(this);
        btnLeaveSubmit.setOnClickListener(this);
        layoutLeaveEndtime.setOnClickListener(this);

        if(listBean != null){
            spInfo.LeaveInfoBean appModel = listBean.getLeaveInfo();
            spInfo.LeaveInfoBean.LeaveBean leave = appModel.getLeave();

             workID = leave.getWorkID();
            date = appModel.getIStart();
            enddate = appModel.getIEnd();

            edtLeaveThing.setText(leave.getThings());//请假事由
            tvLeaveDays.setText(leave.getPNum()+"");//天数
            tvLeaveType.setText(leave.getHolidayName());//类别
            tvLeaveStartime.setText(appModel.getIStart());//开始时间
            tvLeaveEndtime.setText(appModel.getIEnd());//结束时间
        }

        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        if (entity != null) {
            tvLeaveName.setText(entity.getEntity().getUserName());
            tvLeaveDepart.setText(entity.getEntity().getRoleName());
            tvLeaveNumber.setText(entity.getEntity().getUserTitle());
        }
    }
    int workID;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_leave_type:
                final DialogLeaveApply spinner = DialogLeaveApply.getInstance(this);
                spinner.setTitleName("请选择请假类别");
                spinner.setOnItemClickListener(new DialogLeaveApply.ILeaveApplyCallBack() {
                    @Override
                    public void setOnItemListener(String typeName) {
                        tvLeaveType.setText(typeName);
                    }
                });
                spinner.setDuration(700);
                spinner.setEffect(Effectstype.Slidetop);
                spinner.show();
                break;
            case R.id.layout_leave_startime:
                DialogDateTimePicker start = new DialogDateTimePicker(this);
                start.onDatePickerListener(new DialogDateTimePicker.DatePickerCallBack() {
                    @Override
                    public void onClickListener(String time) {
                        tvLeaveStartime.setText(time);
                        date = time;
                        String endTime = tvLeaveEndtime.getText().toString().trim();
                        if (!Utils.isStringEmpty(endTime)) {


                            long olong = stringTOlong1(enddate);
                            long olong1 = stringTOlong1(time);
                            Log.e("TAG","olong:"+olong+"----olong1:"+olong1);
                            if(olong <= olong1){
                                isOK = true;
                                tvLeaveEndtime.setTextColor(getResources().getColor(R.color.word_eb30));
                                displayToast("结束时间不能小于开始时间");
                            }else{

                                presenter.start();
                                isOK = false;
                                tvLeaveEndtime.setTextColor(getResources().getColor(R.color.word_7373));
                            }
                        }
                    }
                });
                start.show();
//                start.setMinData(time);
                break;
            case R.id.layout_leave_endtime:
                String startTime = tvLeaveStartime.getText().toString().trim();
                if (Utils.isStringEmpty(startTime)) {
                    displayToast("请选择申请开始时间");
                    return;
                }
                final long tOlong = stringTOlong(date);
                DialogDateTimePicker end = new DialogDateTimePicker(this);
                end.onDatePickerListener(new DialogDateTimePicker.DatePickerCallBack() {
                    @Override
                    public void onClickListener(String time) {
                        tvLeaveEndtime.setText(time);
                        presenter.start();
                        enddate = time;

                        long olong = stringTOlong1(time);
                        long olong1 = stringTOlong1(date);
                        Log.e("TAG","olong:"+olong+"----olong1:"+olong1);
                        if(olong <= olong1){
                            isOK = true;
                            tvLeaveEndtime.setTextColor(getResources().getColor(R.color.word_eb30));
                            displayToast("结束时间不能小于开始时间");
                        }else{
                            isOK = false;
                            tvLeaveEndtime.setTextColor(getResources().getColor(R.color.word_7373));
                        }

                    }
                });
                end.show();
//                end.setMinData(tOlong);
                break;
            case R.id.btn_leave_submit:
                if(isOK){
                    displayToast("请正确选择时间");
                }else {
                    String type = tvLeaveType.getText().toString().trim();
                    String beginTime = tvLeaveStartime.getText().toString().trim();
                    String finishTime = tvLeaveEndtime.getText().toString().trim();
                    String content = edtLeaveThing.getText().toString().trim();
                    if (Utils.isStringEmpty(type)) {
                        displayToast("请选择请假类别");
                        return;
                    }
                    if (Utils.isStringEmpty(beginTime)) {
                        displayToast("请选择开始时间");
                        return;
                    }
                    if (Utils.isStringEmpty(finishTime)) {
                        displayToast("请选择结束时间");
                        return;
                    }
                    if (Utils.isStringEmpty(content)) {
                        displayToast("请填写请假理由");
                        return;
                    }
                    if(daystate == 0){
                        displayToast(daymsg);
                        return;
                    }
                    new LeaveApplyPresenter(this, 1);
                    presenter.start();
                    break;
                }

                default:
                    break;
        }
    }

    long time;
    private String date;
    private String enddate;
    private boolean isOK;
    private long stringTOlong(String l) {
        Log.e("TAG","选择的时间"+l);

        try {
            return DateUtils.stringToLong(l,"yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
    private long stringTOlong1(String l) {
        Log.e("TAG","选择的时间"+l);

        try {
            return DateUtils.stringToLong(l,"yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void setPresenter(LeaveApplyCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 开启进度
     */
    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    /**
     * 结束进度
     */
    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    /**
     * 获取总天数
     *
     * @param entity
     */

    private int daystate = -1;
    private String daymsg;
    private String leavDayCount;

    @Override
    public void getData(LeaveApplyEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getLeavDayCount())) {

                leavDayCount = entity.getLeavDayCount();
                 daystate = entity.getState();
                 daymsg = entity.getMsg();
                if(daystate == 0){
                    new LeaveApplyPresenter(this, 0);
                    tvLeaveDays.setText("("+entity.getMsg()+")"+entity.getLeavDayCount());
                    tvLeaveDays.setTextColor(getResources().getColor(R.color.word_eb30));
                    Toast.makeText(this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }else {
                    tvLeaveDays.setText(entity.getLeavDayCount());
                    tvLeaveDays.setTextColor(getResources().getColor(R.color.word_7373));
                }
            }
        }
    }

    /**
     * 请假申请提交
     *
     * @param entity
     */
    @Override
    public void save(LeaveApplyEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                new LeaveApplyPresenter(this, 0);
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                if(listBean != null){//修改信息
                    Intent i = new Intent();
                    setResult(3, i);
                    finish();
                }else {
                    finish();
                }

            }
        }
    }

    /**
     * 参数
     *
     * @return
     */
    @Override
    public ParameterEntity setParameter() {
        String startTime = tvLeaveStartime.getText().toString().trim();
        String endTime = tvLeaveEndtime.getText().toString().trim();
        String edtThing = edtLeaveThing.getText().toString().trim();
        String type = tvLeaveType.getText().toString().trim();
        String name = tvLeaveName.getText().toString().trim();
        String depart = tvLeaveDepart.getText().toString().trim();
        String number = tvLeaveNumber.getText().toString().trim();
        String days = tvLeaveDays.getText().toString().trim();
        LogUtils.info("TAG", "---------:" + days);
        ParameterEntity entity = new ParameterEntity();
        entity.setQjqssj(startTime);
        entity.setQjjssj(endTime);
        entity.setQjsy(edtThing);
        entity.setName(name);
        entity.setYgbh(number);
        entity.setBumen(depart);
        entity.setQjlx(type);
        entity.setArea(workID);

        if(daystate == 1){
            if (!Utils.isStringEmpty(leavDayCount)) {
                entity.setGjts(Double.parseDouble(leavDayCount));
            }
        }


        return entity;
    }
}
