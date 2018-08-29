package com.tcrj.spv.views;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.WorkProjectCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.WorkProjectEntity;
import com.tcrj.spv.presenter.WorkProjectPresenter;
import com.tcrj.spv.views.adapter.WriteDailyProjectAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目列表：写日报
 */
public class WriteDailyProjectActivity extends BaseActivity implements View.OnClickListener, WorkProjectCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tv_listview;
    private LinearLayout ll_listview;

    private TextView tv_currentstatenumber;
    private XListView listview;
    private int pageIndex = 1;
    private WorkProjectCallBack.Presenter presenter;
    private WriteDailyProjectAdapter adapter;
    private List<WorkProjectEntity.DataBean> dataList;
    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writedaily_project);
        new WorkProjectPresenter(this);
        initView();
        setPopuWindow();
        presenter.start();
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tv_listview = (TextView) findViewById(R.id.tv_listview);
        ll_listview = (LinearLayout)findViewById(R.id.ll_listview);

        tv_currentstatenumber = (TextView)findViewById(R.id.tv_currentstatenumber);
        listview = (XListView) findViewById(R.id.project_listview);
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        listview.setPullLoadEnable(true);
        listview.setPullRefreshEnable(true);
        imgBack.setOnClickListener(this);
        tv_currentstatenumber.setOnClickListener(this);
        layoutSearchResult.setOnClickListener(this);
        tvTitle.setText("项目列表");
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
                pageIndex = 1;
                presenter.start();
            }

            @Override
            public void onLoadMore() {
                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
                pageIndex++;
                presenter.start();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkProjectEntity.DataBean entity = (WorkProjectEntity.DataBean) adapter.getItem(position - 1);
                Intent project = new Intent();
                project.putExtra("PID", entity.getPid());
                project.putExtra("PName", entity.getPName());
                project.putExtra("CurrentState", entity.getCurrentState());
                setResult(3, project);
                finish();
            }
        });
        dataList = new ArrayList<>();
        adapter = new WriteDailyProjectAdapter(this, dataList);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_search_result:
                showProgressDialog();
                pageIndex = 1;
                presenter.start();
                break;
            case R.id.tv_currentstatenumber:
                //弹窗改变项目状态参数
                mPopTop.showAsDropDown(tv_currentstatenumber);
                break;
        }
    }
    private PopupWindow mPopTop;
//    private TextView tvPopuCustomer;
    private TextView tvPopuReport;
    private TextView tvPopuShare;
    private TextView tvPopuMove;
    private TextView tvPopuSeas;
    /**
     * 弹出框样式设置
     */
    private void setPopuWindow() {
        mPopTop = new PopupWindow(this);
        int w = Utils.getWidth(this);
        mPopTop.setWidth(w / 2);
        mPopTop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopTop.setFocusable(true);//TODO 获取焦点
        mPopTop.setTouchable(true);
        mPopTop.setOutsideTouchable(true);//TODO 设置popupwindow外部可点击
        ColorDrawable dw = new ColorDrawable(0000000000);// TODO 实例化一个ColorDrawable颜色为半透明
        mPopTop.setBackgroundDrawable(dw);// TODO 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mPopTop.setAnimationStyle(R.style.mystyle);//TODO 设置显示和消失动画
        View conentView = Utils.getLayoutInflater(this).inflate(R.layout.item_popu_current, null);
        setConentViewClickListener(conentView);
        mPopTop.setContentView(conentView);
    }

    /**
     * 事件
     *
     * @param v
     */
    private void setConentViewClickListener(View v) {
//        tvPopuCustomer = (TextView) v.findViewById(R.id.tv_popu_customer);
        tvPopuReport = (TextView) v.findViewById(R.id.tv_popu_report);
        tvPopuShare = (TextView) v.findViewById(R.id.tv_popu_share);
        tvPopuMove = (TextView) v.findViewById(R.id.tv_popu_move);
        tvPopuSeas = (TextView) v.findViewById(R.id.tv_popu_seas);
//        tvPopuCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPopTop.dismiss();
//                tv_currentstatenumber.setText("全部项目");
//                CurrentStateNumber = 0;
//            }
//        });
        tvPopuReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                tv_currentstatenumber.setText("实施阶段");
                CurrentStateNumber = 0;
            }
        });
        tvPopuShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                tv_currentstatenumber.setText("售前阶段");
                CurrentStateNumber = 12;
            }
        });
        tvPopuMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                tv_currentstatenumber.setText("售后阶段");
                CurrentStateNumber = 8;
            }
        });
        tvPopuSeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                tv_currentstatenumber.setText("公司产品");
                CurrentStateNumber = 13;
            }
        });
    }

    @Override
    public void setPresenter(WorkProjectCallBack.Presenter presenter) {
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
    public void getData(WorkProjectEntity entity) {
        dismisProgressDialog();
        List<WorkProjectEntity.DataBean> listInfo = entity.getData();

        if(listInfo.size() == 0){
            Toast.makeText(this, "没有搜索到相关信息", Toast.LENGTH_SHORT).show();
            ll_listview.setVisibility(View.GONE);
            tv_listview.setVisibility(View.VISIBLE);
        }else{
            ll_listview.setVisibility(View.VISIBLE);
            tv_listview.setVisibility(View.GONE);

            if (!Utils.isStringEmpty(listInfo)) {
                if (pageIndex == 1) {
                    dataList.clear();
                    listview.stopRefresh();
                } else {
                    listview.setSelection(dataList.size() - 1);
                    listview.stopLoadMore();
                }
                dataList.addAll(listInfo);
                adapter.notifyDataSetChanged();
                if (listInfo.size() < 10) {
                    listview.setPullLoadEnable(false);
                } else {
                    listview.setPullLoadEnable(true);
                }
            }

        }


    }


    private int CurrentStateNumber = 0;

    @Override
    public ParameterEntity setParameter() {
        String keywork = edtSearchResult.getText().toString().trim();


        ParameterEntity entity = new ParameterEntity();
        entity.setPageIndex(pageIndex);
        entity.setPageSize(10);
        entity.setProjectName(keywork);
        entity.setCurrentIndex(CurrentStateNumber);
        return entity;
    }
}
