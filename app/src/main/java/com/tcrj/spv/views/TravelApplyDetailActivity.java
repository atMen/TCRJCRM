package com.tcrj.spv.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.TravelApplyDetailCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyDetailEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.presenter.TravelApplyDetailPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.Utils;

import java.util.List;

/**
 * 出差申请
 */
public class TravelApplyDetailActivity extends BaseActivity implements TravelApplyDetailCallBack.View {
    private TravelApplyDetailCallBack.Presenter presenter;
    private int workId;
    private String nodeId;
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
    private LinearLayout layoutTravelBmsh;
    private TextView tvTravelBmExamine;
    private TextView tvTravelBmPerson;
    private TextView tvTravelBmDatetime;
    private LinearLayout layoutTravelRlzyfh;
    private TextView tvTravelRlExamine;
    private TextView tvTravelRlPerson;
    private TextView tvTravelRlDatetime;
    private LinearLayout layoutTravelSjfhsj;
    private TextView tvTravelSjDatetime;
    private TextView tvTravelSjClocktime;
    private LinearLayout layoutTravelCczj;
    private TextView tvTravelCcSummary;
    private LinearLayout layoutTravelBmshfhsj;
    private TextView tvTravelBmsTime;
    private TextView tvTravelBmsPerson;
    private TextView tvTravelBmsDate;
    private LinearLayout layoutTravelRzfjfhsj;
    private TextView tvTravelRlsTime;
    private TextView tvTravelRlsPerson;
    private TextView tvTravelRlsDate;
    private LinearLayout layoutTravelCwbxgjfy;
    private TextView tvTravelCwTotal;
    private TextView tvTravelCwPerson;
    private TextView tvTravelCwDate;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workId = getIntent().getIntExtra("WorkID", -1);
        nodeId = getIntent().getStringExtra("NodeID");
        setContentView(R.layout.activity_travelapplydetail);
        new TravelApplyDetailPresenter(this, 0);
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
        tvTravelApplyname = (TextView) findViewById(R.id.tv_travel_applyname);
        tvTravelDepart = (TextView) findViewById(R.id.tv_travel_depart);
        tvTravelTogether = (TextView) findViewById(R.id.tv_travel_together);
        tvTravelThing = (TextView) findViewById(R.id.tv_travel_thing);
        tvTravelPlan = (TextView) findViewById(R.id.tv_travel_plan);
        tvTravelPlantime = (TextView) findViewById(R.id.tv_travel_plantime);
        tvTravelPlandays = (TextView) findViewById(R.id.tv_travel_plandays);
        tvTravelPlanaddress = (TextView) findViewById(R.id.tv_travel_planaddress);
        tvTravelTools = (TextView) findViewById(R.id.tv_travel_tools);
        tvTravelCarfare = (TextView) findViewById(R.id.tv_travel_carfare);
        tvTravelHotel = (TextView) findViewById(R.id.tv_travel_hotel);
        tvTravelOthers = (TextView) findViewById(R.id.tv_travel_others);
        tvTravelCosttotal = (TextView) findViewById(R.id.tv_travel_costtotal);
        layoutTravelBmsh = (LinearLayout) findViewById(R.id.layout_travel_bmsh);
        tvTravelBmExamine = (TextView) findViewById(R.id.tv_travel_bm_examine);
        tvTravelBmPerson = (TextView) findViewById(R.id.tv_travel_bm_person);
        tvTravelBmDatetime = (TextView) findViewById(R.id.tv_travel_bm_datetime);
        layoutTravelRlzyfh = (LinearLayout) findViewById(R.id.layout_travel_rlzyfh);
        tvTravelRlExamine = (TextView) findViewById(R.id.tv_travel_rl_examine);
        tvTravelRlPerson = (TextView) findViewById(R.id.tv_travel_rl_person);
        tvTravelRlDatetime = (TextView) findViewById(R.id.tv_travel_rl_datetime);
        layoutTravelSjfhsj = (LinearLayout) findViewById(R.id.layout_travel_sjfhsj);
        tvTravelSjDatetime = (TextView) findViewById(R.id.tv_travel_sj_datetime);
        tvTravelSjClocktime = (TextView) findViewById(R.id.tv_travel_sj_clocktime);
        layoutTravelCczj = (LinearLayout) findViewById(R.id.layout_travel_cczj);
        tvTravelCcSummary = (TextView) findViewById(R.id.tv_travel_cc_summary);
        layoutTravelBmshfhsj = (LinearLayout) findViewById(R.id.layout_travel_bmshfhsj);
        tvTravelBmsTime = (TextView) findViewById(R.id.tv_travel_bms_time);
        tvTravelBmsPerson = (TextView) findViewById(R.id.tv_travel_bms_person);
        tvTravelBmsDate = (TextView) findViewById(R.id.tv_travel_bms_date);
        layoutTravelRzfjfhsj = (LinearLayout) findViewById(R.id.layout_travel_rzfjfhsj);
        tvTravelRlsTime = (TextView) findViewById(R.id.tv_travel_rls_time);
        tvTravelRlsPerson = (TextView) findViewById(R.id.tv_travel_rls_person);
        tvTravelRlsDate = (TextView) findViewById(R.id.tv_travel_rls_date);
        layoutTravelCwbxgjfy = (LinearLayout) findViewById(R.id.layout_travel_cwbxgjfy);
        tvTravelCwTotal = (TextView) findViewById(R.id.tv_travel_cw_total);
        tvTravelCwPerson = (TextView) findViewById(R.id.tv_travel_cw_person);
        tvTravelCwDate = (TextView) findViewById(R.id.tv_travel_cw_date);
        tvTitle.setText("出差申请详情");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setPresenter(TravelApplyDetailCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 加载Loading
     */
    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    /**
     * 关闭Loading
     */
    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    /**
     * 获取数据
     *
     * @param entity
     */
    @Override
    public void getData(TravelApplyDetailEntity entity) {
        if (entity != null) {

            if (!Utils.isStringEmpty(entity.getTemp())) {
                List<TravelApplyDetailEntity.TempBean> dataList = entity.getTemp();
                new TravelApplyDetailPresenter(this, 1);
                for (int i = 0; i < dataList.size(); i++) {
                    tvTravelApplyname.setText(dataList.get(i).getChuChaShenQingRen());
                    tvTravelDepart.setText(dataList.get(i).getShenQingRenBuMen());
                    tvTravelTogether.setText(dataList.get(i).getTongXingRenYuan());
                    tvTravelThing.setText(dataList.get(i).getChuChaShiYou());
                    tvTravelPlan.setText(dataList.get(i).getMuBiaoJiHua());
                    tvTravelPlantime.setText(dataList.get(i).getJiHuaShiJian() + " ~ " + dataList.get(i).getFanHuiRiQi());
                    if (!Utils.isStringEmpty(dataList.get(i).getGongJi())) {
                        tvTravelPlandays.setText(dataList.get(i).getGongJi() + "天");
                    }
                    tvTravelPlanaddress.setText(dataList.get(i).getMuBiaoDiZhi());
                    type = Integer.parseInt(dataList.get(i).getJiaoTongGongJu());
                    tvTravelCarfare.setText(dataList.get(i).getJiaoTongFei());
                    tvTravelHotel.setText(dataList.get(i).getZhuSuFei());
                    tvTravelOthers.setText(dataList.get(i).getQiTaZaFei());
                    tvTravelCosttotal.setText(dataList.get(i).getFeiYongHeJi());
                    if (Utils.isStringEmpty(dataList.get(i).getBumenshenhe_Note()) && Utils.isStringEmpty(dataList.get(i).getBumenshenhe_Checker())) {
                        layoutTravelBmsh.setVisibility(View.GONE);
                    } else {
                        layoutTravelBmsh.setVisibility(View.VISIBLE);
                    }
                    tvTravelBmExamine.setText(dataList.get(i).getBumenshenhe_Note());
                    tvTravelBmPerson.setText(dataList.get(i).getBumenshenhe_Checker());
                    tvTravelBmDatetime.setText(dataList.get(i).getBumenshenhe_RDT());
                    if (Utils.isStringEmpty(dataList.get(i).getRenliziyuanfuhe_Note()) && Utils.isStringEmpty(dataList.get(i).getRenliziyuanfuhe_Checker())) {
                        layoutTravelRlzyfh.setVisibility(View.GONE);
                    } else {
                        layoutTravelRlzyfh.setVisibility(View.VISIBLE);
                    }
                    tvTravelRlExamine.setText(dataList.get(i).getRenliziyuanfuhe_Note());
                    tvTravelRlPerson.setText(dataList.get(i).getRenliziyuanfuhe_Checker());
                    tvTravelRlDatetime.setText(dataList.get(i).getRenliziyuanfuhe_RDT());
                    if (Utils.isStringEmpty(dataList.get(i).getShiJiFanHuanShiJian()) && Utils.isStringEmpty(dataList.get(i).getDaKaShiJian())) {
                        layoutTravelSjfhsj.setVisibility(View.GONE);
                    } else {
                        layoutTravelSjfhsj.setVisibility(View.VISIBLE);
                    }
                    tvTravelSjDatetime.setText(dataList.get(i).getShiJiFanHuanShiJian());
                    tvTravelSjClocktime.setText(dataList.get(i).getDaKaShiJian());
                    if (Utils.isStringEmpty(dataList.get(i).getChuChaZongJie())) {
                        layoutTravelCczj.setVisibility(View.GONE);
                    } else {
                        layoutTravelCczj.setVisibility(View.VISIBLE);
                    }
                    tvTravelCcSummary.setText(dataList.get(i).getChuChaZongJie());
                    if (Utils.isStringEmpty(dataList.get(i).getBumenshenhefanhuishijian_Checker()) && Utils.isStringEmpty(dataList.get(i).getBuMenShenHeFanHuiShiJian())) {
                        layoutTravelBmshfhsj.setVisibility(View.GONE);
                    } else {
                        layoutTravelBmshfhsj.setVisibility(View.VISIBLE);
                    }
                    tvTravelBmsTime.setText(dataList.get(i).getBuMenShenHeFanHuiShiJian());
                    tvTravelBmsPerson.setText(dataList.get(i).getBumenshenhefanhuishijian_Checker());
                    tvTravelBmsDate.setText(dataList.get(i).getBumenshenhefanhuishijian_RDT());
                    if (Utils.isStringEmpty(dataList.get(i).getRenzifuhefanhuishijian_Note()) && Utils.isStringEmpty(dataList.get(i).getRenzifuhefanhuishijian_Checker())) {
                        layoutTravelRzfjfhsj.setVisibility(View.GONE);
                    } else {
                        layoutTravelRzfjfhsj.setVisibility(View.VISIBLE);
                    }
                    tvTravelRlsTime.setText(dataList.get(i).getRenzifuhefanhuishijian_Note());
                    tvTravelRlsPerson.setText(dataList.get(i).getRenzifuhefanhuishijian_Checker());
                    tvTravelRlsDate.setText(dataList.get(i).getRenzifuhefanhuishijian_RDT());
                    if (Utils.isStringEmpty(dataList.get(i).getCaiwubaoxiao_Note()) && Utils.isStringEmpty(dataList.get(i).getCaiwubaoxiao_Checker())) {
                        layoutTravelCwbxgjfy.setVisibility(View.GONE);
                    } else {
                        layoutTravelCwbxgjfy.setVisibility(View.VISIBLE);
                    }
                    if (!Utils.isStringEmpty(dataList.get(i).getCaiwubaoxiao_Note())) {
                        tvTravelCwTotal.setText(dataList.get(i).getCaiwubaoxiao_Note() + " 元");
                    }
                    tvTravelCwPerson.setText(dataList.get(i).getCaiwubaoxiao_Checker());
                    tvTravelCwDate.setText(dataList.get(i).getCaiwubaoxiao_RDT());
                }
                presenter.start();
            }
        }
    }

    @Override
    public void getType(TravelApplyEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getTransportType())) {
                for (int i = 0; i < entity.getTransportType().size(); i++) {
                    if (type == i) {
                        tvTravelTools.setText(entity.getTransportType().get(i).toString());
                    }
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
        ParameterEntity entity = new ParameterEntity();
        entity.setWorkId(workId);
        entity.setFkNodeId(nodeId);
        return entity;
    }
}
