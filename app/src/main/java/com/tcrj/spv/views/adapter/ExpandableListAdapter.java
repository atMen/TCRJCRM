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

import java.util.List;

/**
 * Created by leict on 2017/12/25.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;

    List<FollowRecordEntity.ListInfoBean> listInfo;

    public ExpandableListAdapter(List<FollowRecordEntity.ListInfoBean> entity,Context context)
    {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listInfo = entity;
    }

    @Override
    public int getGroupCount() {

        if (listInfo.size() > 0) {
            return listInfo.size();
        } else {
            return -1;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (listInfo.size() > 0) {
            return listInfo.get(groupPosition).getListtTraceInfos().size();
        } else {
            return -1;
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listInfo.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listInfo.get(groupPosition).getListtTraceInfos().get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        FollowRecordEntity.ListInfoBean entity = (FollowRecordEntity.ListInfoBean) listInfo.get(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_group_record, parent, false);
        }
        convertView.setId(groupPosition);
        LinearLayout layoutRecordDatetime = Tools.ViewHolder.get(convertView, R.id.layout_record_datetime);
        TextView tvRecordDatetime = Tools.ViewHolder.get(convertView, R.id.tv_record_datetime);
        tvRecordDatetime.setText(entity.getTime());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        FollowRecordEntity.ListInfoBean.ListtTraceInfosBean entity = (FollowRecordEntity.ListInfoBean.ListtTraceInfosBean) listInfo.get(groupPosition).getListtTraceInfos().get(childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child_record, parent, false);
        }
        convertView.setId(childPosition);
        CircleImageView recordImageview = Tools.ViewHolder.get(convertView, R.id.record_imageview);
        TextView tvRecordUsername = Tools.ViewHolder.get(convertView, R.id.tv_record_username);
        TextView tvRecordLinkway = Tools.ViewHolder.get(convertView, R.id.tv_record_linkway);
        TextView tvRecordName = Tools.ViewHolder.get(convertView, R.id.tv_record_customername);
        TextView tvRecordDatetime = Tools.ViewHolder.get(convertView, R.id.tv_record_datetime);
        TextView tvRecordContent = Tools.ViewHolder.get(convertView, R.id.tv_record_content);
        TextView tvRecordSource = Tools.ViewHolder.get(convertView, R.id.tv_record_source);
        tvRecordUsername.setText(entity.getUName());
        tvRecordLinkway.setText(entity.getTraceType());
        tvRecordName.setText(entity.getTraceName());
        tvRecordDatetime.setText(entity.getHour());
        tvRecordContent.setText(entity.getTracedResult());
        tvRecordSource.setText("客户来源：" + entity.getPosition() + "\u3000|\u3000" + entity.getCustomerName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



//    public void upLoadData(List<FollowRecordEntity.ListInfoBean> listInfoLoad) {
//        listInfo.addAll(listInfoLoad);
//        notifyDataSetChanged();
//    }
}
