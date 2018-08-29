package com.tcrj.spv.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NewNowLeaveApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.spInfo;
import com.tcrj.spv.presenter.NewNowLeaveApplyDetailPresenter;
import com.tcrj.spv.views.adapter.NewLeaveApplyDetailAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.selectDialog;
import com.tcrj.spv.views.dialog.selectListviewDialog;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.tcrj.spv.views.application.BaseApplication.context;

/**
 * 审批详情
 */
public class NewNowLeaveApplyDetailActivity extends BaseActivity implements NewNowLeaveApplyDetailCallBack.View, View.OnClickListener {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvLeaveUsername;
    private TextView tvLeaveDepartment;
    private TextView tvLeaveNumber;
    private TextView tvLeaveWays;
    private TextView tvLeaveTime;
    private TextView tvLeaveDays;
    private TextView tvLeaveThing;

    private Button btnTY;
    private Button btnTH;
    private Button btnTHZDJD;
    private Button btn_updata;

    private LinearLayout ll_sp;
    private LinearLayout ll_cxtj;


    private RecyclerView mRecyclerView;

    private List<spInfo.ListBean> beanList;
    NewLeaveApplyDetailAdapter detailAdapter;
    private NewNowLeaveApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
    View headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fregment_spinfo_sp);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        new NewNowLeaveApplyDetailPresenter(this, 0);
        initView();
        presenter.start();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnTY = (Button) findViewById(R.id.btn_yes);
        btnTH = (Button) findViewById(R.id.btn_no);
        btnTHZDJD = (Button) findViewById(R.id.btn_no_where);
        btn_updata = (Button) findViewById(R.id.btn_updata);

        ll_sp = (LinearLayout) findViewById(R.id.ll_sp);
        ll_cxtj = (LinearLayout) findViewById(R.id.ll_cxtj);

        btnTY.setOnClickListener(this);
        btnTH.setOnClickListener(this);
        btnTHZDJD.setOnClickListener(this);
        btn_updata.setOnClickListener(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("请假审批详情");
        headView = LayoutInflater.from(this).inflate(R.layout.layout_header_view , null);
        tvLeaveUsername = (TextView) headView.findViewById(R.id.tv_leave_username);
        tvLeaveDepartment = (TextView) headView.findViewById(R.id.tv_leave_department);
        tvLeaveNumber = (TextView) headView.findViewById(R.id.tv_leave_number);
        tvLeaveWays = (TextView) headView.findViewById(R.id.tv_leave_ways);
        tvLeaveTime = (TextView) headView.findViewById(R.id.tv_leave_time);
        tvLeaveDays = (TextView) headView.findViewById(R.id.tv_newleave_days);
        tvLeaveThing = (TextView) headView.findViewById(R.id.tv_leave_thing);

        beanList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NewNowLeaveApplyDetailActivity.this));
        mRecyclerView.setAdapter(detailAdapter = new NewLeaveApplyDetailAdapter(beanList, NewNowLeaveApplyDetailActivity.this));

    }

    @Override
    public void setPresenter(NewNowLeaveApplyDetailCallBack.Presenter presenter) {
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

    @Override
    public void getData(spInfo entity) {
        Log.e("TAG",entity.toString());
        if (entity != null) {
            spInfo = entity;
            int isReturn = entity.getIsReturn();
            if(isReturn == 0){
                ll_sp.setVisibility(View.VISIBLE);
            }else {
                ll_cxtj.setVisibility(View.VISIBLE);
            }


            List<spInfo.ListBean> list = entity.getList();

            setDataHeader(entity);

            if (!Utils.isStringEmpty(list)) {
                detailAdapter.setNewData(list);
            }
        }
    }

    @Override
    public void getTJData(spInfo entity) {

        Intent i = new Intent();
        setResult(4, i);
        finish();

        Toast.makeText(this, entity.getMsg(), Toast.LENGTH_SHORT).show();
    }

    private void setDataHeader(spInfo entity) {
        spInfo.LeaveInfoBean leaveBean = entity.getLeaveInfo();

        if(leaveBean != null){
            spInfo.LeaveInfoBean.LeaveBean leave = leaveBean.getLeave();
            if(leave != null){
                tvLeaveUsername.setText(leave.getAddPersonName());
                tvLeaveDepartment.setText(entity.getDeptName());
                tvLeaveNumber.setText(leave.getAddNo());
                tvLeaveWays.setText(leave.getHolidayName());
                tvLeaveTime.setText(leaveBean.getIStart()+" ~ "+leaveBean.getIEnd());
                tvLeaveDays.setText(leave.getPNum()+"天");
                tvLeaveThing.setText(leave.getThings());
            }

            if (detailAdapter.getHeaderLayoutCount()<1){
                detailAdapter.addHeaderView(headView);
            }
        }

    }


    @Override
    public ParameterEntity setParameters() {
        ParameterEntity entity = new ParameterEntity();
        entity.setWorkId(workId);
        entity.setFkNodeId(nodeId);
        return entity;
    }

    private String userTitle;
    private int type = 1;
    @Override
    public ParameterEntity setSPParameters() {
        SharedPreferences e = context.getSharedPreferences("Login", 0);
        userTitle = e.getString("userTitle","");

        Log.e("TAG","userTitle:"+userTitle);
        ParameterEntity entity = new ParameterEntity();
        entity.setWorkId(workId);
        entity.setFkNodeId(nodeId);
        entity.setUserName(userTitle);
        entity.setDayType(type);
        entity.setPlanContent(command_txt);
        entity.setArea(jdid);
        return entity;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_yes:
                type = 1;
                showSelectDialog("确定要同意本次申请吗？");
                break;
            case R.id.btn_no:
                type = 2;
                showSelectDialog("确定要退回本次申请吗？");
                break;
            case R.id.btn_no_where:
                type = 3;
                showSelectDialogList("退回到指定节点",workId,nodeId);
                break;

            case R.id.btn_updata:

                Bundle bundle = new Bundle();
                bundle.putSerializable("qjinfo",spInfo);
                toClass(this,LeaveApplyActivity.class,bundle,2);

                break;

            default:
                break;
        }
    }

    spInfo spInfo;

    private void showSelectDialogList(String title, int workId, String nodeId) {

        selectListviewDialog.Builder builder = new selectListviewDialog.Builder(this);
        final selectListviewDialog selectListviewdialog = builder
                .style(R.style.Dialog)
                .cancelTouchout(false)
                .setTitle(title)
                .setWorkId(workId)
                .setNodeId(nodeId)
                .cancelTouchout(true)
                .build();
        selectListviewdialog.show();

        selectListviewdialog.setClicklistener(new selectListviewDialog.ClickListenerInterface() {
            @Override
            public void doConfirm(String info, int jdID) {
//                Toast.makeText(NewNowLeaveApplyDetailActivity.this, "意见："+info+"----节点id："+jdID, Toast.LENGTH_SHORT).show();
                if(!("".equals(info))){
                    command_txt = info;
                    jdid = jdID;
                    selectListviewdialog.dismiss();
                    tjSpInfo();
                }else {
                    Toast.makeText(NewNowLeaveApplyDetailActivity.this, "请填写审核意见", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    selectDialog dialog;
    private void showSelectDialog(String title) {

        selectDialog.Builder builder = new selectDialog.Builder(this);
        dialog = builder
                .style(R.style.Dialog)
                .cancelTouchout(false)
                .view(R.layout.dialog_select)
                .setTitle(title)
                .cancelTouchout(true)
                .addViewOnclick(R.id.btn_sure,listener)
                .build();
        dialog.show();
    }


    private String command_txt;
    private int jdid = 0;//直接同意或回退默认id为0
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            switch (view.getId()){
                case R.id.btn_sure :

                    command_txt = ((EditText)dialog.getEditText()).getText().toString();
                    if(command_txt != null && !("".equals(command_txt))){
//                        Toast.makeText(NewNowLeaveApplyDetailActivity.this, command_txt, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        tjSpInfo();
                    }else {
                        Toast.makeText(NewNowLeaveApplyDetailActivity.this, "请填写审核意见", Toast.LENGTH_SHORT).show();
                    }

                    break;

                default:
                    break;
            }
        }
    };

    //提交审批信息
    private void tjSpInfo() {
        new NewNowLeaveApplyDetailPresenter(this, 1);
        presenter.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == 3 && data != null){
            Intent i = new Intent();
            setResult(4, i);
            finish();
        }
    }


}
