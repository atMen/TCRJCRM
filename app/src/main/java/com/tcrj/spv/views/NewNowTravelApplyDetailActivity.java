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
import com.tcrj.spv.callback.NewNowTravelApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.presenter.NewNowTravelApplyDetailPresenter;
import com.tcrj.spv.views.adapter.NewTravelApplyDetailAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.TravelSelectListviewDialog;
import com.tcrj.spv.views.dialog.selectDialog;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.tcrj.spv.views.application.BaseApplication.context;

/**
 * 审批详情
 */
public class NewNowTravelApplyDetailActivity extends BaseActivity implements NewNowTravelApplyDetailCallBack.View, View.OnClickListener {
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

    private Button btn_fhzj;
    private Button btnTY;
    private Button btnTH;
    private Button btnTHZDJD;
    private Button btn_updata;

    private LinearLayout ll_sp;
    private LinearLayout ll_cxtj;
    private LinearLayout ll_fhzj;


    private RecyclerView mRecyclerView;

    private List<ccSPInfo.ListBean> beanList;
    NewTravelApplyDetailAdapter detailAdapter;
    private NewNowTravelApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
    View headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fregment_spinfo_sp);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        new NewNowTravelApplyDetailPresenter(this, 0);
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

        btn_fhzj = (Button) findViewById(R.id.btn_fhzj);
        btnTY = (Button) findViewById(R.id.btn_yes);
        btnTH = (Button) findViewById(R.id.btn_no);
        btnTHZDJD = (Button) findViewById(R.id.btn_no_where);
        btn_updata = (Button) findViewById(R.id.btn_updata);

        ll_sp = (LinearLayout) findViewById(R.id.ll_sp);
        ll_cxtj = (LinearLayout) findViewById(R.id.ll_cxtj);
        ll_fhzj = (LinearLayout) findViewById(R.id.ll_fhzj);

        btnTY.setOnClickListener(this);
        btnTH.setOnClickListener(this);
        btnTHZDJD.setOnClickListener(this);
        btn_updata.setOnClickListener(this);
        btn_fhzj.setOnClickListener(this);

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NewNowTravelApplyDetailActivity.this));
        mRecyclerView.setAdapter(detailAdapter = new NewTravelApplyDetailAdapter(beanList, NewNowTravelApplyDetailActivity.this));

    }

    @Override
    public void setPresenter(NewNowTravelApplyDetailCallBack.Presenter presenter) {
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
            spInfo = entity;
            int isReturn = entity.getIsReturn();
            if(isReturn == 0){
                ll_sp.setVisibility(View.VISIBLE);
            }else if(isReturn == 2){
                ll_cxtj.setVisibility(View.VISIBLE);
            }else {
                ll_fhzj.setVisibility(View.VISIBLE);
            }


            List<ccSPInfo.ListBean> list = entity.getList();

            setDataHeader(entity);

            if (!Utils.isStringEmpty(list)) {
                detailAdapter.setNewData(list);
            }
        }
    }

    @Override
    public void getTJData(ccSPInfo entity) {

        Intent i = new Intent();
        setResult(4, i);
        finish();

        Toast.makeText(this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
                tvTravelPlandays.setText("无");
                tvTravelPlanaddress.setText(leave.getAdress());
                tvTravelTools.setText(leave.getVehicle());
                tvTravelCarfare.setText(leave.getTraffic()+"");
                tvTravelHotel.setText(leave.getHotel()+"");
                tvTravelOthers.setText(leave.getOther()+"");
                tvTravelCosttotal.setText(leave.getCost()+"");
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
    private String BackSum = "";
    private String BackTime = "";
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
        entity.setBackSum(BackSum);
        entity.setBackTime(BackTime);
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
                bundle.putSerializable("ccInfo",spInfo);
                toClass(this,TravelApplyActivity.class,bundle,2);

                break;

            case R.id.btn_fhzj:

                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("ccInfo",spInfo);
                bundle1.putString("nodeId",nodeId);
                bundle1.putInt("workId",workId);

                toClass(this,TravelBackActivity.class,bundle1,2);

                break;


            default:
                break;
        }
    }

    ccSPInfo spInfo;

    private void showSelectDialogList(String title, int workId, String nodeId) {

        TravelSelectListviewDialog.Builder builder = new TravelSelectListviewDialog.Builder(this);
        final TravelSelectListviewDialog selectListviewdialog = builder
                .style(R.style.Dialog)
                .cancelTouchout(false)
                .setTitle(title)
                .setWorkId(workId)
                .setNodeId(nodeId)
                .cancelTouchout(true)
                .build();
        selectListviewdialog.show();

        selectListviewdialog.setClicklistener(new TravelSelectListviewDialog.ClickListenerInterface() {
            @Override
            public void doConfirm(String info, int jdID) {
//                Toast.makeText(NewNowLeaveApplyDetailActivity.this, "意见："+info+"----节点id："+jdID, Toast.LENGTH_SHORT).show();
                if(!("".equals(info))){
                    command_txt = info;
                    jdid = jdID;
                    selectListviewdialog.dismiss();
                    tjSpInfo();
                }else {
                    Toast.makeText(NewNowTravelApplyDetailActivity.this, "请填写审核意见", Toast.LENGTH_SHORT).show();
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
//                      Toast.makeText(NewNowLeaveApplyDetailActivity.this, command_txt, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        tjSpInfo();
                    }else {
                        Toast.makeText(NewNowTravelApplyDetailActivity.this, "请填写审核意见", Toast.LENGTH_SHORT).show();
                    }

                    break;

                default:
                    break;
            }
        }
    };

    //提交审批信息
    private void tjSpInfo() {
        new NewNowTravelApplyDetailPresenter(this, 1);
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
