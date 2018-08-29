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

import com.tcrj.spv.R;
import com.tcrj.spv.callback.TravelApplyCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.presenter.TravelApplyPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogDateTimePicker;
import com.tcrj.spv.views.dialog.DialogTrafficTool;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.text.ParseException;
import java.util.Date;

/**
 * 出差申请
 */
public class TravelApplyActivity extends BaseActivity implements View.OnClickListener, TravelApplyCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvTravelName;
    private TextView tvTravelDepart;
    private EditText edtTravelPerson;
    private EditText edtTravelThing;
    private EditText edtTravelTarget;
    private TextView tvLeaveTime;
    private LinearLayout layoutLeaveStartime;
    private TextView tvLeaveStartime;
    private LinearLayout layoutLeaveEndtime;
    private TextView tvLeaveEndtime;
    private EditText edtTravelAddress;
    private TextView tvLeaveKind;
    private LinearLayout layoutLeaveType;
    private TextView tvLeaveType;
    private EditText edtTravelTraffic;
    private EditText edtTravelStay;
    private EditText edtTravelExtras;
    private Button btnTravelSubmit;
    private TravelApplyCallBack.Presenter presenter;

    private String workID = "";
    private long time;
    private ccSPInfo listBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelapply);
        listBean = (ccSPInfo) getIntent().getSerializableExtra("ccInfo");
        new TravelApplyPresenter(this);
        initView();

         time =(new Date()).getTime();
        Log.e("TAG","time"+time);
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTravelName = (TextView) findViewById(R.id.tv_travel_name);
        tvTravelDepart = (TextView) findViewById(R.id.tv_travel_depart);
        edtTravelPerson = (EditText) findViewById(R.id.edt_travel_person);
        edtTravelThing = (EditText) findViewById(R.id.edt_travel_thing);
        edtTravelTarget = (EditText) findViewById(R.id.edt_travel_target);
        tvLeaveTime = (TextView) findViewById(R.id.tv_leave_time);
        layoutLeaveStartime = (LinearLayout) findViewById(R.id.layout_leave_startime);
        tvLeaveStartime = (TextView) findViewById(R.id.tv_leave_startime);
        layoutLeaveEndtime = (LinearLayout) findViewById(R.id.layout_leave_endtime);
        tvLeaveEndtime = (TextView) findViewById(R.id.tv_leave_endtime);
        edtTravelAddress = (EditText) findViewById(R.id.edt_travel_address);
        tvLeaveKind = (TextView) findViewById(R.id.tv_leave_kind);
        layoutLeaveType = (LinearLayout) findViewById(R.id.layout_leave_type);
        tvLeaveType = (TextView) findViewById(R.id.tv_leave_type);
        edtTravelTraffic = (EditText) findViewById(R.id.edt_travel_traffic);
        edtTravelStay = (EditText) findViewById(R.id.edt_travel_stay);
        edtTravelExtras = (EditText) findViewById(R.id.edt_travel_extras);
        btnTravelSubmit = (Button) findViewById(R.id.btn_travel_submit);
        tvTitle.setText("出差申请");
        tvLeaveKind.setText("\u3000交通工具：");
        tvLeaveTime.setText("\u3000计划时间：");
        imgBack.setOnClickListener(this);
        btnTravelSubmit.setOnClickListener(this);
        layoutLeaveEndtime.setOnClickListener(this);
        layoutLeaveStartime.setOnClickListener(this);
        layoutLeaveType.setOnClickListener(this);

        if(listBean != null){
            ccSPInfo.TravelBeanX appModel = listBean.getTravel();
            ccSPInfo.TravelBeanX.TravelBean leave = appModel.getTravel();

            workID = leave.getWorkID();
            date = appModel.getIStart();
            enddate = appModel.getIEnd();

            edtTravelPerson.setText(leave.getPartner());//同行人
            edtTravelThing.setText(leave.getCause());//事由
            edtTravelTarget.setText(leave.getPlan());//目标计划
            edtTravelAddress.setText(leave.getAdress());//目标地
            tvLeaveType.setText(leave.getVehicle());
            edtTravelTraffic.setText(leave.getTraffic()+"");
            edtTravelStay.setText(leave.getHotel()+"");
            edtTravelExtras.setText(leave.getOther()+"");
            tvLeaveStartime.setText(appModel.getIStart());//开始时间
            tvLeaveEndtime.setText(appModel.getIEnd());//结束时间
        }

        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        if (entity != null) {
            tvTravelName.setText(entity.getEntity().getUserName());
            tvTravelDepart.setText(entity.getEntity().getRoleName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_travel_submit:

                if(isOK){
                    displayToast("请正确选择时间");
                }else{
                    String mthing = edtTravelThing.getText().toString().trim();
                    String mtarget = edtTravelTarget.getText().toString().trim();
                    String mstart = tvLeaveStartime.getText().toString().trim();
                    String mend = tvLeaveEndtime.getText().toString().trim();
                    String maddress = edtTravelAddress.getText().toString().trim();
                    String mtraffic = edtTravelTraffic.getText().toString().trim();
                    String mstay = edtTravelStay.getText().toString().trim();
                    String mother = edtTravelExtras.getText().toString().trim();

                    if (Utils.isStringEmpty(mthing)) {
                        displayToast("出差事由不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mtarget)) {
                        displayToast("目标计划不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mstart)) {
                        displayToast("计划开始时间不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mend)) {
                        displayToast("计划结束时间不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(maddress)) {
                        displayToast("目标地址不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mtraffic)) {
                        displayToast("交通费不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mstay)) {
                        displayToast("住宿费不能为空");
                        return;
                    }
                    if (Utils.isStringEmpty(mother)) {
                        displayToast("其他杂费不能为空");
                        return;
                    }

                    presenter.start();
                }

                break;
            case R.id.layout_leave_endtime:
                Log.e("TAG","date:"+date);

                if(date != null){
                    final long tOlong = stringTOlong(date);
                    DialogDateTimePicker end = new DialogDateTimePicker(this);
                    end.onDatePickerListener(new DialogDateTimePicker.DatePickerCallBack() {
                        @Override
                        public void onClickListener(String time) {
                            tvLeaveEndtime.setText(time);
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
//                    end.setMinData(tOlong);
                }else{
                    displayToast("请先选择开始时间");
                }

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

                                isOK = false;
                                tvLeaveEndtime.setTextColor(getResources().getColor(R.color.word_7373));
                            }
                        }

                    }
                });
                start.show();
//                start.setMinData(time);
                break;
            case R.id.layout_leave_type:
                DialogTrafficTool spinner = DialogTrafficTool.getInstance(this);
                spinner.setTitleName("请选择交通工具");
                spinner.setOnItemClickListener(new DialogTrafficTool.ITrafficToolCallBack() {
                    @Override
                    public void setOnItemListener(String typeName) {
                        tvLeaveType.setText(typeName);
                    }
                });
                spinner.setDuration(700);
                spinner.setEffect(Effectstype.Slidetop);
                spinner.show();
                break;

            default:
                break;
        }
    }

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
    public void setPresenter(TravelApplyCallBack.Presenter presenter) {
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
     * 提交申请
     *
     * @param entity
     */
    @Override
    public void submit(TravelApplyEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
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
     * 参数设置
     *
     * @return
     */
    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity users = BaseApplication.getUserInfoEntity();
        String name = tvTravelName.getText().toString().trim();
        String start = tvLeaveStartime.getText().toString().trim();
        String end = tvLeaveEndtime.getText().toString().trim();
        String address = edtTravelAddress.getText().toString().trim();
        String extral = edtTravelExtras.getText().toString().trim();
        String depart = tvTravelDepart.getText().toString().trim();
        String person = edtTravelPerson.getText().toString().trim();
        String stay = edtTravelStay.getText().toString().trim();
        String target = edtTravelTarget.getText().toString().trim();
        String traffic = edtTravelTraffic.getText().toString().trim();
        String type = tvLeaveType.getText().toString().trim();
        String thing = edtTravelThing.getText().toString().trim();
        ParameterEntity entity = new ParameterEntity();
        entity.setFanHuiRiQi(end);
        entity.setJiHuaShiJian(start);
        entity.setMuBiaoDiZhi(address);
        entity.setQiTaZaFei(extral);
        entity.setShenQingRenBuMen(depart);
        entity.setUserNo(users.getEntity().getUserTitle());
        entity.setTongXingRenYuan(person);
        entity.setZhuSuFei(stay);
        entity.setMuBiaoJiHua(target);
        entity.setJiaoTongFei(traffic);
        entity.setJiaoTongGongJu(type);
        entity.setChuChaShenQingRen(name);
        entity.setChuChaShiYou(thing);
        entity.setFkNodeId(workID);
        LogUtils.info("TravelApplyActivity", entity);
        return entity;
    }
}
