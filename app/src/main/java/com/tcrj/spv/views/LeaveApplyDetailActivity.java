package com.tcrj.spv.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.LeaveApplyDetailCallBack;
import com.tcrj.spv.model.LeaveApplyDetailEntity;
import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.presenter.LeaveApplyDetailPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.Utils;

import java.util.List;

/**
 * 审批详情
 */
public class LeaveApplyDetailActivity extends BaseActivity implements LeaveApplyDetailCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvLeaveUsername;
    private TextView tvLeaveDepartment;
    private TextView tvLeaveNumber;
    private TextView tvLeaveWays;
    private TextView tvLeaveTime;
    private TextView tvLeaveDays;
    private TextView tvLeaveThing;
    private TextView tvLeaveBmDepart;
    private TextView tvLeaveBmPerson;
    private TextView tvLeaveBmDate;
    private LinearLayout layoutLeaveFzsh;
    private LinearLayout layoutLeaveRlsp;
    private TextView tvLeaveFzExamine;
    private TextView tvLeaveFzPerson;
    private TextView tvLeaveFzDate;
    private TextView tvLeaveRzExamine;
    private TextView tvLeaveRzPerson;
    private TextView tvLeaveRzDate;
    private LeaveApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaveapplydetail);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        new LeaveApplyDetailPresenter(this, 0);
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
        tvLeaveUsername = (TextView) findViewById(R.id.tv_leave_username);
        tvLeaveDepartment = (TextView) findViewById(R.id.tv_leave_department);
        tvLeaveNumber = (TextView) findViewById(R.id.tv_leave_number);
        tvLeaveWays = (TextView) findViewById(R.id.tv_leave_ways);
        tvLeaveTime = (TextView) findViewById(R.id.tv_leave_time);
        tvLeaveDays = (TextView) findViewById(R.id.tv_leave_days);
        tvLeaveThing = (TextView) findViewById(R.id.tv_leave_thing);
        tvLeaveBmDepart = (TextView) findViewById(R.id.tv_leave_bm_depart);
        tvLeaveBmPerson = (TextView) findViewById(R.id.tv_leave_bm_person);
        tvLeaveBmDate = (TextView) findViewById(R.id.tv_leave_bm_date);
        layoutLeaveFzsh = (LinearLayout) findViewById(R.id.layout_leave_fzsh);
        layoutLeaveRlsp = (LinearLayout) findViewById(R.id.layout_leave_rlsp);
        tvLeaveFzExamine = (TextView) findViewById(R.id.tv_leave_fz_examine);
        tvLeaveFzPerson = (TextView) findViewById(R.id.tv_leave_fz_person);
        tvLeaveFzDate = (TextView) findViewById(R.id.tv_leave_fz_date);
        tvLeaveRzExamine = (TextView) findViewById(R.id.tv_leave_rz_examine);
        tvLeaveRzPerson = (TextView) findViewById(R.id.tv_leave_rz_person);
        tvLeaveRzDate = (TextView) findViewById(R.id.tv_leave_rz_date);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("请假审批详情");
    }

    @Override
    public void setPresenter(LeaveApplyDetailCallBack.Presenter presenter) {
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
    public void getData(LeaveApplyDetailEntity entity) {
        if (entity != null) {
            List<LeaveApplyDetailEntity.LeavesBean> dataList = entity.getLeaves();
            if (!Utils.isStringEmpty(dataList)) {
                new LeaveApplyDetailPresenter(this, 1);
                for (int i = 0; i < dataList.size(); i++) {
                    tvLeaveUsername.setText(dataList.get(i).getXingMing());
                    tvLeaveDepartment.setText(dataList.get(i).getBuMen());
                    tvLeaveNumber.setText(dataList.get(i).getYuanGongBianHao());
                    type = Integer.parseInt(dataList.get(i).getQingJiaLeiBie());
                    tvLeaveTime.setText(dataList.get(i).getQingJiaShiJian() + " ~ " + dataList.get(i).getQingJiaJieShu());
                    tvLeaveDays.setText(dataList.get(i).getGongjitianshu_Note() + " 天");
                    tvLeaveThing.setText(dataList.get(i).getQingJiaShiYou());
                    tvLeaveBmDepart.setText(dataList.get(i).getBumenshenhe_Note());
                    tvLeaveBmPerson.setText(dataList.get(i).getBumenshenhe_Checker());
                    tvLeaveBmDate.setText(dataList.get(i).getBumenshenhe_RDT());
                    tvLeaveFzExamine.setText(dataList.get(i).getLdsh_Note());
                    tvLeaveFzPerson.setText(dataList.get(i).getLdsh_Checker());
                    tvLeaveFzDate.setText(dataList.get(i).getLdsh_RDT());
                    tvLeaveRzExamine.setText(dataList.get(i).getRenzishenhe_Note());
                    tvLeaveRzPerson.setText(dataList.get(i).getRenzishenhe_Checker());
                    tvLeaveRzDate.setText(dataList.get(i).getRenzishenhe_RDT());
                    if (Utils.isStringEmpty(dataList.get(i).getLdsh_Note()) && Utils.isStringEmpty(dataList.get(i).getLdsh_Checker())) {
                        layoutLeaveFzsh.setVisibility(View.GONE);
                    } else {
                        layoutLeaveFzsh.setVisibility(View.VISIBLE);
                    }
                    if (Utils.isStringEmpty(dataList.get(i).getRenzishenhe_Note()) && Utils.isStringEmpty(dataList.get(i).getRenzishenhe_Checker())) {
                        layoutLeaveRlsp.setVisibility(View.GONE);
                    } else {
                        layoutLeaveRlsp.setVisibility(View.VISIBLE);
                    }
                }
                presenter.start();
            }
        }
    }

    @Override
    public void getType(LeaveApplyEntity entity) {
        if (!Utils.isStringEmpty(entity.getLeavType())) {
            for (int i = 0; i < entity.getLeavType().size(); i++) {
                if (type == i) {
                    tvLeaveWays.setText(entity.getLeavType().get(i).toString());
                }
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
}
