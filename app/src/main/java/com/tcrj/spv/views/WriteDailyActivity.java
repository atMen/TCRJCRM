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
import com.tcrj.spv.callback.WriteDailyCallBack;
import com.tcrj.spv.model.CommonEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.presenter.WriteDailyPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogWorkDaily;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.Date;

/**
 * 编辑日报
 */
public class WriteDailyActivity extends BaseActivity implements View.OnClickListener, WriteDailyCallBack.View {
    private static String TAG = "WriteDailyActivity";
    private WriteDailyCallBack.Presenter presenter;
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvDailyNowtime;
    private TextView tvBeforePlan;
    private EditText edtTodayPlan;
    private EditText edtTomorrowPlan;
    private Button btnSubmitDaily;
    private CommonEntity commonEntity;
    private LinearLayout layoutWorkNaturejob;
    private TextView tvWorkNaturejob;
    private LinearLayout layoutWorkProject;
    private TextView tvWorkProject;
    private EditText edtWorkAddress;
    private EditText edtWorkDatetime;
    private EditText edtWorkOvertime;
    private WorkDailtItemEntity.DataBean person = null;
//    int userID;
//    UserInfoEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writedaily);

        person = (WorkDailtItemEntity.DataBean) getIntent().getSerializableExtra("data");//编辑操作传进来的对象
        String Time = getIntent().getStringExtra("Time");
        if(Time != null){
            dateTimeString = Time;
            Log.e("TAG","编辑时间");
        }
        String logDate = getIntent().getStringExtra("LogDate");
        if(logDate != null){
            dateTimeString = logDate;
            Log.e("TAG","补写时间");
        }

//        user = BaseApplication.getUserInfoEntity();
//        userID = user.getEntity().getUserId();
        Log.e("TAG","logDate--"+logDate);
        new WriteDailyPresenter(this, 0);
        setCommon();
        initView();
    }

    /**
     * 公共参数
     */
    private void setCommon() {
        commonEntity = new CommonEntity();
        commonEntity.setIsCheck("1");
        commonEntity.setPID("0");
        commonEntity.setWorkid(0);
    }

    int projectID = -1;
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDailyNowtime = (TextView) findViewById(R.id.tv_daily_nowtime);
        tvBeforePlan = (TextView) findViewById(R.id.tv_before_plan);
        edtTodayPlan = (EditText) findViewById(R.id.edt_today_plan);
        edtTomorrowPlan = (EditText) findViewById(R.id.edt_tomorrow_plan);
        btnSubmitDaily = (Button) findViewById(R.id.btn_submit_daily);
        layoutWorkNaturejob = (LinearLayout) findViewById(R.id.layout_work_naturejob);
        tvWorkNaturejob = (TextView) findViewById(R.id.tv_work_naturejob);
        layoutWorkProject = (LinearLayout) findViewById(R.id.layout_work_project);
        tvWorkProject = (TextView) findViewById(R.id.tv_work_project);
        edtWorkAddress = (EditText) findViewById(R.id.edt_work_address);
        edtWorkDatetime = (EditText) findViewById(R.id.edt_work_datetime);
        edtWorkOvertime = (EditText) findViewById(R.id.edt_work_overtime);
//        presenter.start();
        tvTitle.setText("写日报");

        tvDailyNowtime.setText(dateTimeString);


        imgBack.setOnClickListener(this);
        btnSubmitDaily.setOnClickListener(this);



        if(person != null){
            btnSubmitDaily.setText("提交编辑");
            tvTitle.setText("编辑日报");
            tvWorkNaturejob.setText(person.getWorkNature());//工作性质
            tvWorkProject.setText(person.getPName());//项目名称
            edtWorkAddress.setText(person.getWorkPlace());//工作地点
            edtWorkDatetime.setText(person.getWorkHour()+"");//工作时间
            edtWorkOvertime.setText(person.getOvertime()+"");//加班时间

            edtTodayPlan.setText(person.getLogContent());//今日工作内容
            edtTomorrowPlan.setText(person.getPlanContent());//明日工作计划

            projectID = person.getProjectID();
            if(projectID == 0){
                layoutWorkProject.setVisibility(View.GONE);

            }else{
                layoutWorkProject.setVisibility(View.VISIBLE);
            }
            commonEntity.setPID(String.valueOf(projectID));
            commonEntity.setWorkid(Integer.valueOf(person.getWorkNatureID()));

            presenter.start();
        }else{

            layoutWorkProject.setOnClickListener(this);
            layoutWorkNaturejob.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_submit_daily:

                String todayPlan = edtTodayPlan.getText().toString().trim();
                String tomorrowPlan = edtTomorrowPlan.getText().toString().trim();
                String project = tvWorkProject.getText().toString().trim();
                String address = edtWorkAddress.getText().toString().trim();
                String worktime = edtWorkDatetime.getText().toString().trim();
                String trim = tvWorkNaturejob.getText().toString().trim();

                if (layoutWorkProject.getVisibility() == View.VISIBLE ) {
                    if (Utils.isStringEmpty(project)) {
                        displayToast("请选择项目名称");
                        return;
                    }
                }
                if(Utils.isStringEmpty(trim)){
                    displayToast("请选择工作性质");
                    return;
                }


//                if (Utils.isStringEmpty(address)) {
//                    displayToast("请输入工作地点");
//                    return;
//                }
                if (Utils.isStringEmpty(worktime)) {
                    displayToast("请输入工作工时");
                    return;
                }
                if (Utils.isStringEmpty(todayPlan)) {
                    displayToast("请输入今日工作情况");
                    return;
                }
                if (Utils.isStringEmpty(tomorrowPlan)) {
                    displayToast("请输入明日计划情况");
                    return;
                }



                if(person != null){
                    commonEntity.setWorkid(Integer.valueOf(person.getWorkNatureID()));
                    commonEntity.setPID(String.valueOf(person.getProjectID()));

                    LogItemID = person.getLogItemID();
                    LogID = person.getLogID();

                    userId = 0;
                    dateTimeString = DateUtils.getDateTimeString(new Date());
                }else{
                    userId = BaseApplication.getUserInfoEntity().getEntity().getUserId();
//                    dateTimeString = DateUtils.getDateTimeString(new Date());
                }


                    new WriteDailyPresenter(this, 1);
                    presenter.start();


                break;
            case R.id.layout_work_naturejob:


                DialogWorkDaily dialog = new DialogWorkDaily(this);
                dialog.setEffect(Effectstype.Slidetop);
                dialog.setDuration(700);
                dialog.setTitleName("选择项目性质");
                dialog.setOnItemClickListener(new DialogWorkDaily.IListViewCallBack() {
                    @Override
                    public void setOnClickListener(PublicListEntity entity) {
                        commonEntity.setWorkid(entity.getId());
                        Log.e("TAG","项目性质："+entity.getId());
                        tvWorkNaturejob.setText(entity.getpName());

                        if (entity.getCode().equals("4") || entity.getCode().equals("5")) {
                            layoutWorkProject.setVisibility(View.GONE);
                            new WriteDailyPresenter(WriteDailyActivity.this, 0);
                            presenter.start();//请求无项目时的昨日计划
                        } else {
                            layoutWorkProject.setVisibility(View.VISIBLE);
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.layout_work_project:
                Intent pro = new Intent(WriteDailyActivity.this, WriteDailyProjectActivity.class);
                startActivityForResult(pro, 100);
                break;
        }
    }

    /**
     * 设置回调
     *
     * @param presenter
     */
    @Override
    public void setPresenter(WriteDailyCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 加载Loading
     */
    @Override
    public void LoadingOn() {
        showProgressDialog("正在提交...");
    }

    /**
     * 关闭Loading
     */
    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    @Override
    public void submit(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {

                displayToast(entity.getMsg());
                finish();

            } else {
                displayToast(entity.getMsg());
            }
        }
    }

    @Override
    public void getData(SubmitEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getPlanContent())) {
                tvBeforePlan.setText(entity.getPlanContent());
            }
        }
    }

    /**
     * 接口参数赋值
     *
     * @return
     */

    private int LogItemID = 0;
    private int LogID = 0;

    private int userId = BaseApplication.getUserInfoEntity().getEntity().getUserId();
    private String dateTimeString = DateUtils.getDateTimeString(new Date());

    String address = "西安";
    @Override
    public ParameterEntity setParameter() {


        if(person != null){//编辑日报
            dateTimeString = person.getDoDate();
        }

        String todayPlan = edtTodayPlan.getText().toString().trim();
        String tomorrowPlan = edtTomorrowPlan.getText().toString().trim();
        address = edtWorkAddress.getText().toString().trim();
        String workTime = edtWorkDatetime.getText().toString().trim();
        String overTime = edtWorkOvertime.getText().toString().trim();

        ParameterEntity entity = new ParameterEntity();
        //获取昨日计划数据

        entity.setLogItemID(LogItemID);
        entity.setLogID(LogID);


        Log.e("TAG","userid--"+userId+"address:"+address);
        Log.e("TAG","时间戳："+dateTimeString);
        entity.setUserID(userId);
        entity.setLogDate(dateTimeString);


        entity.setWorkNature(commonEntity.getWorkid());
        entity.setProjectID(commonEntity.getPID());
        entity.setWorkPlace(address);
        entity.setWorkHour(workTime);
        entity.setOvertime(overTime);
        entity.setLogContent(todayPlan);
        entity.setPlanContent(tomorrowPlan);
        LogUtils.info("WriteDailyActivity", entity);
        return entity;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == 3) {
                    new WriteDailyPresenter(this, 0);
                    Bundle b = data.getExtras();  //data为B中回传的Intent
                    int pid = b.getInt("PID");    //str即为回传的值
                    String name = b.getString("PName");
                    commonEntity.setPID(String.valueOf(pid));
                    tvWorkProject.setText(name);
                    Log.e("TAG","明日计划回调：："+pid);
                    presenter.start();
                }
                break;
        }
    }
}
