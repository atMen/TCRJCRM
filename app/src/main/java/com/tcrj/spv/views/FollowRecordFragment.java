package com.tcrj.spv.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerTraceCallBack;
import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.presenter.CustomerTracePresenter;
import com.tcrj.spv.views.adapter.FollowRecordAdapter;
import com.tcrj.spv.views.application.BaseActivity;

/**
 * 跟进记录
 * Created by leict on 2017/11/27.
 */

@SuppressLint("ValidFragment")
public class FollowRecordFragment extends Fragment implements CustomerTraceCallBack.View {
    private View view;
    private ExpandableListView listView;
    private CustomerTraceCallBack.Presenter presenter;
    private String cusId;
    private FollowRecordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record, container, false);

        //业务逻辑类
        new CustomerTracePresenter(FollowRecordFragment.this);

        //可折叠的listview
        listView = (ExpandableListView) view.findViewById(R.id.expand_recordlistview);
        presenter.start();

        return view;
    }

    /**
     * 刷新数据
     */
    public void refresh() {
        presenter.start();
    }

    public FollowRecordFragment(String cusId) {
        this.cusId = cusId;
    }

    @Override
    public void setPresenter(CustomerTraceCallBack.Presenter presenter) {
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
    public void getData(FollowRecordEntity entity) {
        if (entity != null) {
            adapter = new FollowRecordAdapter(getContext());
            adapter.setData(entity.getListInfo());
            listView.setGroupIndicator(null);
            listView.setAdapter(adapter);
            listView.expandGroup(0);//默认展开第一项内容
            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {//二级列表的item点击事件监听
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    FollowRecordEntity.ListInfoBean.ListtTraceInfosBean entity = (FollowRecordEntity.ListInfoBean.ListtTraceInfosBean)
                            adapter.getChild(groupPosition, childPosition);
                    Intent child = new Intent(getContext(), FollowRecordDetailActivity.class);
                    child.putExtra("TID", entity.getCTID());
                    ((BaseActivity) getContext()).startActivityForResult(child, 103);
                    return false;
                }
            });
        }
    }


    //通过绑定view，获取到view层的相关数据
    @Override
    public String cusId() {
        return cusId;
    }
}
