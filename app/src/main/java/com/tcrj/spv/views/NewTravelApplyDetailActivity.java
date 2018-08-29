package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NewTravelApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.presenter.NewTravelApplyDetailPresenter;
import com.tcrj.spv.views.adapter.NewTravelApplyDetailAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 审批详情
 */
public class NewTravelApplyDetailActivity extends BaseActivity implements NewTravelApplyDetailCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvTravelApplyname;
    private TextView tvTravelDepart;
    private TextView tvTravelTogether;
    private TextView tvTravelThing;
    private TextView tvTravelPlan;
    private TextView tvTravelPlantime;
    private TextView tvTravelPlandays;
    private TextView tvTravelPlanaddress;
    private TextView tvTravelTools;
    private TextView tvTravelCarfare;
    private TextView tvTravelHotel;
    private TextView tvTravelOthers;
    private TextView tvTravelCosttotal;
    private TextView tv_travel_sj_datetime;
    private TextView tv_travel_cc_summary;
    private LinearLayout layoutTravelBack;

    private RecyclerView mRecyclerView;

    private List<ccSPInfo.ListBean> beanList;
    NewTravelApplyDetailAdapter detailAdapter;
    private NewTravelApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
    View headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fregment_spinfo);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        new NewTravelApplyDetailPresenter(this, 0);
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
        tvTitle.setText("出差审批详情");


        headView = LayoutInflater.from(this).inflate(R.layout.travel_header_view , null);

        layoutTravelBack = (LinearLayout) headView.findViewById(R.id.layout_travel_back);
        tvTravelApplyname = (TextView) headView.findViewById(R.id.tv_travel_applyname);
        tvTravelDepart = (TextView) headView.findViewById(R.id.tv_travel_depart);
        tvTravelTogether = (TextView) headView.findViewById(R.id.tv_travel_together);
        tvTravelThing = (TextView) headView.findViewById(R.id.tv_travel_thing);
        tvTravelPlan = (TextView) headView.findViewById(R.id.tv_travel_plan);
        tvTravelPlantime = (TextView) headView.findViewById(R.id.tv_travel_plantime);
        tvTravelPlandays = (TextView) headView.findViewById(R.id.tv_travel_plandays);
        tvTravelPlanaddress = (TextView) headView.findViewById(R.id.tv_travel_planaddress);
        tvTravelTools = (TextView) headView.findViewById(R.id.tv_travel_tools);
        tvTravelCarfare = (TextView) headView.findViewById(R.id.tv_travel_carfare);
        tvTravelHotel = (TextView) headView.findViewById(R.id.tv_travel_hotel);
        tvTravelOthers = (TextView) headView.findViewById(R.id.tv_travel_others);
        tvTravelCosttotal = (TextView) headView.findViewById(R.id.tv_travel_costtotal);

        tv_travel_sj_datetime = (TextView) headView.findViewById(R.id.tv_travel_sj_datetime);
        tv_travel_cc_summary = (TextView) headView.findViewById(R.id.tv_travel_cc_summary);

        beanList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NewTravelApplyDetailActivity.this));
        mRecyclerView.setAdapter(detailAdapter = new NewTravelApplyDetailAdapter(beanList, NewTravelApplyDetailActivity.this));


    }

    @Override
    public void setPresenter(NewTravelApplyDetailCallBack.Presenter presenter) {
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
    public void getData(ccSPInfo entity) {
        Log.e("TAG",entity.toString());
        if (entity != null) {
            List<ccSPInfo.ListBean> list = entity.getList();

            setDataHeader(entity);

            if (!Utils.isStringEmpty(list)) {
                detailAdapter.setNewData(list);
            }
        }
    }

    private void setDataHeader(ccSPInfo entity) {
        ccSPInfo.TravelBeanX leaveBean = entity.getTravel();

        if(leaveBean != null){

            ccSPInfo.TravelBeanX.TravelBean leave = leaveBean.getTravel();
            if(leave != null){

                String iBackTime = leaveBean.getIReturn();
                String summary = leave.getSummary();
                if(!Utils.isStringEmpty(summary)){
                    layoutTravelBack.setVisibility(View.VISIBLE);
                    tv_travel_sj_datetime.setText(iBackTime);
                    tv_travel_cc_summary.setText(summary);
                }else {
                    layoutTravelBack.setVisibility(View.GONE);
                }

                tvTravelApplyname.setText(leave.getStaffName());
                tvTravelDepart.setText(entity.getDeptName());
                tvTravelTogether.setText(leave.getPartner());
                tvTravelThing.setText(leave.getCause());
                tvTravelPlan.setText(leave.getPlan());
                tvTravelPlantime.setText(leaveBean.getIStart()+" ~ "+leaveBean.getIEnd());
                tvTravelPlanaddress.setText(leave.getAdress());
                tvTravelTools.setText(leave.getVehicle());
                tvTravelCarfare.setText(leave.getTraffic()+"");
                tvTravelHotel.setText(leave.getHotel()+"");
                tvTravelOthers.setText(leave.getOther()+"");
                tvTravelCosttotal.setText(leave.getCost()+"");
//                tvLeaveUsername.setText(leave.getAddPersonName());
//                tvLeaveDepartment.setText(entity.getDeptName());
//                tvLeaveNumber.setText(leave.getAddNo());
//                tvLeaveWays.setText(leave.getHolidayName());
//                tvLeaveTime.setText(leaveBean.getIStart()+" ~ "+leaveBean.getIEnd());
//                tvLeaveDays.setText(leave.getPNum()+" 天");
//                tvLeaveThing.setText(leave.getThings());
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
}
