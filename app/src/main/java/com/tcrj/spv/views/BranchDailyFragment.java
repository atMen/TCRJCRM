package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.tcrj.spv.views.adapter.SpinnerAdapter;
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
 * 下级日报
 * Created by leict on 2017/11/13.
 */

public class BranchDailyFragment extends Fragment implements WorkDailyCallBack.View, ItemClickListener {
    private String TAG = "BranchDailyFragment";
    private WorkDailyCallBack.Presenter presenter;
    private View view;
    private int pageIndex = 1;
    //private WorkDailyAdapter adapter = null;
    private XRecyclerView mRecyclerview;

    private Spinner subordinate_bumen;
    private TextView tv_context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily, container, false);
        new WorkDailyPresenter(this);
        initView(view);
        presenter.start();
        presenter.loadBmData();
        return view;
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {

        subordinate_bumen = (Spinner) view.findViewById(R.id.subordinate_bumen);
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
                mRecyclerview.loadMoreComplete();//---------------

//                pageIndex++;
//                presenter.start();
            }
        });
    }

    private SpinnerAdapter Padapter = null;

    private void setSpinner(List<SpinnerEntity.ListInfoBean> PdataList) {
        Padapter = new SpinnerAdapter(getContext());
        Padapter.setData(PdataList);
        subordinate_bumen.setAdapter(Padapter);
        subordinate_bumen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntity.ListInfoBean item = Padapter.getItem(position);
                DeptedId = item.getDId();
                pageIndex = 1;
                presenter.start();
//              Toast.makeText(getContext(), item.getDId()+"部门筛选条件"+item.getDName(), Toast.LENGTH_SHORT).show();
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
//        //将数据传入日报详情页面
//        Intent _Intent = new Intent();
//        _Intent.setClass(getContext(), WorkDailyItemActivity.class);
//        _Intent.putExtra("person_data", item);
//        _Intent.putExtra("person_type", "1");
//        startActivity(_Intent);
    }

    @Override
    public void itemClicked(Section section) {

    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) getContext()).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) getContext()).dismisProgressDialog();
    }

    @Override
    public void getData(WorkDailyEntity entity) {
        List<WorkDailyEntity.ListInfoBean> listInfo = entity.getListInfo();


        if (!Utils.isStringEmpty(listInfo)) {
            tv_context.setVisibility(View.GONE);
            mRecyclerview.setVisibility(View.VISIBLE);

            ExpandableLayoutHelper sectionedHelper = new ExpandableLayoutHelper(getActivity(), mRecyclerview, this, 1);
            for (int i = 0; i < listInfo.size(); i++) {
                List<ItemEntity> itemList = new ArrayList<>();
                for (int j = 0; j < listInfo.get(i).getListtDayLogs().size(); j++) {
                    ItemEntity item = new ItemEntity();
                    item.setHour(listInfo.get(i).getListtDayLogs().get(j).getHour());
                    item.setIsDo(listInfo.get(i).getListtDayLogs().get(j).getIsDo());
                    item.setLogContent(listInfo.get(i).getListtDayLogs().get(j).getLogContent());
                    item.setLogId(listInfo.get(i).getListtDayLogs().get(j).getLogId());
                    item.setPlanContent(listInfo.get(i).getListtDayLogs().get(j).getPlanContent());
                    item.setReviewsQuality(listInfo.get(i).getListtDayLogs().get(j).getReviewsQuality());
                    item.setStaffName(listInfo.get(i).getListtDayLogs().get(j).getStaffName());
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
            if (listInfo.size() < 10) {
                mRecyclerview.loadMoreComplete();
            }
        } else {
//            Toast.makeText(getContext(), "暂无数据", Toast.LENGTH_SHORT).show();

            tv_context.setVisibility(View.VISIBLE);


            mRecyclerview.refreshComplete();
            mRecyclerview.loadMoreComplete();
            mRecyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void getBmData(SpinnerEntity spinnerEntity) {
        List<SpinnerEntity.ListInfoBean> listInfo = spinnerEntity.getListInfo();
        SpinnerEntity.ListInfoBean listInfoBean = new SpinnerEntity.ListInfoBean();
        listInfoBean.setDName("全部部门");
        listInfoBean.setDId("0");
        listInfo.add(0,listInfoBean);
        setSpinner(listInfo);
    }

    private String DeptedId = "0";

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();

        entity.setStaffId(user.getEntity().getUserId());
        //下拉框参数
        entity.setDayType(0);
        //下拉框参数
        entity.setOwerId("0");
        //下拉框参数
        entity.setDeptId(DeptedId);
        entity.setCurrentIndex(pageIndex);
        //        entity.setPageSize(Utils.pageSize);//此处上拉加载有问题
        entity.setPageSize(500);
        //是否下属
        entity.setIsMe(1);
        LogUtils.info(TAG, entity);
        return entity;
    }
}
