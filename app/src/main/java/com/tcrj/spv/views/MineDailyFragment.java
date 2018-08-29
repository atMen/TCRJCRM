package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.core.control.XRecyclerView;
import com.core.control.utils.ProgressStyle;
import com.tcrj.spv.R;
import com.tcrj.spv.callback.WorkDailyCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.WorkDailyEntity;
import com.tcrj.spv.presenter.WorkDailyPresenter;
import com.tcrj.spv.views.adapter.SpinnerDateAdapter;
import com.tcrj.spv.views.adapter.WorkDailyAdapter;
import com.tcrj.spv.views.adapter.expand.ExpandableLayoutHelper;
import com.tcrj.spv.views.adapter.expand.ItemClickListener;
import com.tcrj.spv.views.adapter.expand.ItemEntity;
import com.tcrj.spv.views.adapter.expand.Section;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的日报
 * Created by leict on 2017/11/13.
 */

public class MineDailyFragment extends Fragment implements WorkDailyCallBack.View, ItemClickListener {
    private String TAG = "MineDailyFragment";
    private WorkDailyCallBack.Presenter presenter;
    private View view;
    private int pageIndex = 1;
    private WorkDailyAdapter adapter = null;
    private XRecyclerView mRecyclerview;
    private Spinner subordinate_bumen;
    private TextView tv_context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily_mine, container, false);
        new WorkDailyPresenter(this);
        initView(view);
        SpinnerLoad();
        presenter.start();
        return view;
    }

    private List<SpinnerEntity> dateList;
    private void SpinnerLoad() {
        dateList = new ArrayList<>();
        CharSequence[] dates = this.getResources().getStringArray(R.array.daily_date);
        for (int i = 0; i < dates.length; i++) {
            SpinnerEntity dateentity = new SpinnerEntity();
            dateentity.setId(i);
            dateentity.setSpinnerName(dates[i].toString());
            dateentity.setCityId("0");
            dateList.add(dateentity);
        }
        setdate_Spinner(dateList);
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {
        subordinate_bumen = (Spinner) view.findViewById(R.id.subordinate_time);
        subordinate_bumen.setFocusable(false);
        tv_context = (TextView)view.findViewById(R.id.tv_context);

        mRecyclerview = (XRecyclerView) view.findViewById(R.id.daily_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerview.setArrowImageView(R.mipmap.img_refurbish_down);
        mRecyclerview.getDefaultFootView().setLoadingHint("加载中...");
        mRecyclerview.getDefaultFootView().setNoMoreHint("没有更多了");
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                presenter.start();
            }

            @Override
            public void onLoadMore() {
//                pageIndex++;
//                presenter.start();
                mRecyclerview.loadMoreComplete();//---------------

            }
        });
    }
    private int DayType = 0;
    private SpinnerDateAdapter Dadapter = null;

    public void setdate_Spinner(List<SpinnerEntity> r_Spinner) {

        Dadapter = new SpinnerDateAdapter(getContext());
        Dadapter.setData(r_Spinner);
        subordinate_bumen.setAdapter(Dadapter);

        subordinate_bumen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntity entity = (SpinnerEntity) Dadapter.getItem(position);
                DayType = entity.getId();
                pageIndex = 1;
                presenter.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void setPresenter(WorkDailyCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void itemClicked(ItemEntity item) {
        //将数据传入日报详情页面
//        String time = listInfo.get(position-1).getTime();
        Log.e("TAG","时间："+item.getTime());
        Intent _Intent = new Intent();
        _Intent.setClass(getContext(), WorkDailyItemActivity.class);
        _Intent.putExtra("person_data", item);
        startActivity(_Intent);
    }

    @Override
    public void itemClicked(Section section) {
        Log.e("TAG","Section");

    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) getContext()).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) getContext()).dismisProgressDialog();
    }
    List<WorkDailyEntity.ListInfoBean> listInfo;

    @Override
    public void getData(WorkDailyEntity entity) {
         listInfo = entity.getListInfo();
        if (!Utils.isStringEmpty(listInfo)) {
            Log.e("TAG","今日工作："+listInfo.get(0).getListtDayLogs().get(0).getLogContent());
            tv_context.setVisibility(View.GONE);
            mRecyclerview.setVisibility(View.VISIBLE);

            ExpandableLayoutHelper sectionedHelper = new ExpandableLayoutHelper(getActivity(), mRecyclerview, this, 1);
            for (int i = 0; i < listInfo.size(); i++) {
                List<ItemEntity> itemList = new ArrayList<>();
                for (int j = 0; j < listInfo.get(i).getListtDayLogs().size(); j++) {
                    ItemEntity item = new ItemEntity();

                    String logContent = listInfo.get(i).getListtDayLogs().get(j).getLogContent();
                    String planContent = listInfo.get(i).getListtDayLogs().get(j).getPlanContent();
                    String replace = logContent.replace("◆", "\n");
                    String planreplace = planContent.replace("◆", "\n");

                    item.setHour(listInfo.get(i).getListtDayLogs().get(j).getHour());
                    Log.e("TAG","时间："+item.getHour());
                    item.setIsDo(listInfo.get(i).getListtDayLogs().get(j).getIsDo());
                    item.setLogContent(replace);
                    item.setLogId(listInfo.get(i).getListtDayLogs().get(j).getLogId());
                    item.setPlanContent(planreplace);
                    item.setReviewsQuality(listInfo.get(i).getListtDayLogs().get(j).getReviewsQuality());
                    item.setStaffName(listInfo.get(i).getListtDayLogs().get(j).getStaffName());
                    item.setTime(listInfo.get(i).getTime());
                    itemList.add(item);
                }
                sectionedHelper.addSection(listInfo.get(i).getTime(), itemList);
                sectionedHelper.notifyDataSetChanged();
            }
            if (pageIndex == 1) {
                mRecyclerview.refreshComplete();
            } else {
                mRecyclerview.loadMoreComplete();
            }
            //adapter.notifyDataSetChanged();
            if (listInfo.size() < 10) {
                mRecyclerview.loadMoreComplete();
            }
        } else {
            tv_context.setVisibility(View.VISIBLE);
            mRecyclerview.refreshComplete();
            mRecyclerview.loadMoreComplete();
            mRecyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void getBmData(SpinnerEntity spinnerEntity) {

    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        Log.e("TAG","userID"+user.getEntity().getUserId());
        entity.setStaffId(user.getEntity().getUserId());
        //下拉框参数
        entity.setDayType(DayType);
        //下拉框参数
        entity.setOwerId("0");
        //下拉框参数
        entity.setDeptId("0");
        entity.setCurrentIndex(pageIndex);
//      entity.setPageSize(Utils.pageSize);//此处上拉加载有问题
        entity.setPageSize(500);
        //是否下属
        entity.setIsMe(0);
        LogUtils.info(TAG, entity);
        return entity;
    }
}
