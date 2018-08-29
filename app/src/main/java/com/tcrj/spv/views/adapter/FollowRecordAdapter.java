package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.views.control.CircleImageView;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户详情：跟进记录
 * Created by leict on 2017/11/30.
 */

public class FollowRecordAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<FollowRecordEntity.ListInfoBean> dataList;

    public FollowRecordAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<FollowRecordEntity.ListInfoBean> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataList.get(groupPosition).getListtTraceInfos().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).getListtTraceInfos().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int roupPosition, boolean isExpanded, View groupView, ViewGroup parent) {
        FollowRecordEntity.ListInfoBean entity = (FollowRecordEntity.ListInfoBean) dataList.get(roupPosition);
        if (groupView == null) {
            groupView = inflater.inflate(R.layout.item_group_record, parent, false);
        }
        groupView.setId(roupPosition);
        LinearLayout layoutRecordDatetime = Tools.ViewHolder.get(groupView, R.id.layout_record_datetime);
        TextView tvRecordDatetime = Tools.ViewHolder.get(groupView, R.id.tv_record_datetime);
        tvRecordDatetime.setText(entity.getTime());
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childView, ViewGroup parent) {
        FollowRecordEntity.ListInfoBean.ListtTraceInfosBean entity = (FollowRecordEntity.ListInfoBean.ListtTraceInfosBean) dataList.get(groupPosition).getListtTraceInfos().get(childPosition);
        if (childView == null) {
            childView = inflater.inflate(R.layout.item_child_record, parent, false);
        }
        childView.setId(childPosition);
        CircleImageView recordImageview = Tools.ViewHolder.get(childView, R.id.record_imageview);
        TextView tvRecordUsername = Tools.ViewHolder.get(childView, R.id.tv_record_username);
        TextView tvRecordLinkway = Tools.ViewHolder.get(childView, R.id.tv_record_linkway);
        TextView tvRecordName = Tools.ViewHolder.get(childView, R.id.tv_record_customername);
        TextView tvRecordDatetime = Tools.ViewHolder.get(childView, R.id.tv_record_datetime);
        TextView tvRecordContent = Tools.ViewHolder.get(childView, R.id.tv_record_content);
        TextView tvRecordSource = Tools.ViewHolder.get(childView, R.id.tv_record_source);
        tvRecordUsername.setText(entity.getUName());
        tvRecordLinkway.setText(entity.getTraceType());
        tvRecordName.setText(entity.getTraceName());
        tvRecordDatetime.setText(entity.getHour());
        tvRecordContent.setText(entity.getTracedResult());
        tvRecordSource.setText("客户来源：" + entity.getPosition() + "\u3000|\u3000" + entity.getCustomerName());
        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
