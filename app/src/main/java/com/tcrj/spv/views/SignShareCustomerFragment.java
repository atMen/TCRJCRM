package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerInfoCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.CustomerInfoEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.CustomerInfoPresenter;
import com.tcrj.spv.views.adapter.CustomerInfoAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 共享客户
 * Created by leict on 2017/12/15.
 */

public class SignShareCustomerFragment extends Fragment implements CustomerInfoCallBack.View, View.OnClickListener {
    private View view;
    private int pageIndex = 1;
    private List<CustomerInfoEntity.ListInfoBean> dataList;
    private XListView listview;
    private CustomerInfoCallBack.Presenter presenter;
    private CustomerInfoAdapter adapter;
    private CommonalityEntity commonality;
    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;
    private LinearLayout ll_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        new CustomerInfoPresenter(SignShareCustomerFragment.this);
        setCommonality();
        initView(view);
        presenter.start();
        return view;
    }

    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setCusStatus("0");
        commonality.setCusClass("0");
        commonality.setIsHot("-1");
        commonality.setIntentionPro("");
        commonality.setReportStatus("-1");
        commonality.setBelongArea("0");
        commonality.setOwerUserId("-1");
        commonality.setKeyWord("");
        commonality.setOrder("0");
        commonality.setCurrenttype("1");
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {
        edtSearchResult = (EditText) view.findViewById(R.id.edt_search_result);
        edtSearchResult.setHint("请输入客户姓名");
        layoutSearchResult = (LinearLayout) view.findViewById(R.id.layout_search_result);
        ll_search = view.findViewById(R.id.ll_search);
        ll_search.setVisibility(View.VISIBLE);
        listview = (XListView) view.findViewById(R.id.customer_listview);
        layoutSearchResult.setOnClickListener(this);
        listview.setPullRefreshEnable(true);
        listview.setPullLoadEnable(true);
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
                CustomerInfoEntity.ListInfoBean entity = (CustomerInfoEntity.ListInfoBean) adapter.getItem(position - 1);
                Intent intent = new Intent();
                intent.putExtra("VisitId", entity.getCid());
                intent.putExtra("VisitName", entity.getName());
                ((BaseActivity) getContext()).setResult(4, intent);
                ((BaseActivity) getContext()).finish();
            }
        });
        dataList = new ArrayList<>();
        adapter = new CustomerInfoAdapter(getContext(), dataList);
        listview.setAdapter(adapter);
    }

    @Override
    public void setPresenter(CustomerInfoCallBack.Presenter presenter) {
        this.presenter = presenter;
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
    public void getData(CustomerInfoEntity entity) {
        List<CustomerInfoEntity.ListInfoBean> listInfo = entity.getListInfo();
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
        }else {
            Toast.makeText(getContext(), "没有检索到相关人员", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setUserId(user.getEntity().getUserId());
        entity.setCusStatus(commonality.getCusStatus());
        entity.setCusClass(commonality.getCusClass());
        entity.setIsHot(commonality.getIsHot());
        entity.setIntentionPro(commonality.getIntentionPro());
        entity.setReportStatus(commonality.getReportStatus());
        entity.setBelongArea(commonality.getBelongArea());
        entity.setOwerUserId(commonality.getOwerUserId());
        entity.setPageSize(Utils.pageSize);
        entity.setCurrentPageIndex(pageIndex);
        entity.setKeyWord(commonality.getKeyWord());
        entity.setOrder(commonality.getOrder());
        entity.setCurrenttype(commonality.getCurrenttype());
        return entity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.layout_search_result:
                String result = edtSearchResult.getText().toString().trim();
                if (Utils.isStringEmpty(result)) {
                    commonality.setKeyWord("");
                } else {
                    commonality.setKeyWord(result);
                }

                pageIndex = 1;
                presenter.start();
                break;
        }
    }
}