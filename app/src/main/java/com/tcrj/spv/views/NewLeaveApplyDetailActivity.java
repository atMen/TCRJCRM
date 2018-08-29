package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NewLeaveApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.spInfo;
import com.tcrj.spv.presenter.NewLeaveApplyDetailPresenter;
import com.tcrj.spv.views.adapter.NewLeaveApplyDetailAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 审批详情
 */
public class NewLeaveApplyDetailActivity extends BaseActivity implements NewLeaveApplyDetailCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvLeaveUsername;
    private TextView tvLeaveDepartment;
    private TextView tvLeaveNumber;
    private TextView tvLeaveWays;
    private TextView tvLeaveTime;
    private TextView tvLeaveDays;
    private TextView tvLeaveThing;


    private RecyclerView mRecyclerView;

    private List<spInfo.ListBean> beanList;
    NewLeaveApplyDetailAdapter detailAdapter;
    private NewLeaveApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
    View headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fregment_spinfo);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        new NewLeaveApplyDetailPresenter(this, 0);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NewLeaveApplyDetailActivity.this));
        mRecyclerView.setAdapter(detailAdapter = new NewLeaveApplyDetailAdapter(beanList, NewLeaveApplyDetailActivity.this));



//        tvLeaveBmDepart = (TextView) findViewById(R.id.tv_leave_bm_depart);
//        tvLeaveBmPerson = (TextView) findViewById(R.id.tv_leave_bm_person);
//        tvLeaveBmDate = (TextView) findViewById(R.id.tv_leave_bm_date);
//        layoutLeaveFzsh = (LinearLayout) findViewById(R.id.layout_leave_fzsh);
//        layoutLeaveRlsp = (LinearLayout) findViewById(R.id.layout_leave_rlsp);
//        tvLeaveFzExamine = (TextView) findViewById(R.id.tv_leave_fz_examine);
//        tvLeaveFzPerson = (TextView) findViewById(R.id.tv_leave_fz_person);
//        tvLeaveFzDate = (TextView) findViewById(R.id.tv_leave_fz_date);
//        tvLeaveRzExamine = (TextView) findViewById(R.id.tv_leave_rz_examine);
//        tvLeaveRzPerson = (TextView) findViewById(R.id.tv_leave_rz_person);
//        tvLeaveRzDate = (TextView) findViewById(R.id.tv_leave_rz_date);
    }

    @Override
    public void setPresenter(NewLeaveApplyDetailCallBack.Presenter presenter) {
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
            List<spInfo.ListBean> list = entity.getList();

            setDataHeader(entity);

            if (!Utils.isStringEmpty(list)) {
                detailAdapter.setNewData(list);
            }
        }
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
                tvLeaveDays.setText(leave.getPNum()+" 天");
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
        entity.setFkNodeId("ND" + nodeId);
        return entity;
    }
}
