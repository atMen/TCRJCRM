package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.ApprovalEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办审批/在办审批/完结审批
 * Created by leict on 2017/11/3.
 */

public class LeaderExamineAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<ApprovalEntity.ListInfoBean> itemList;

    public LeaderExamineAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<ApprovalEntity.ListInfoBean> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ApprovalEntity.ListInfoBean entity = (ApprovalEntity.ListInfoBean) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_approval_listview, null);
        }
        TextView tvApprovalApply = Tools.ViewHolder.get(view, R.id.tv_approval_apply);
        TextView tvApprovalRemain = Tools.ViewHolder.get(view, R.id.tv_approval_remain);
        TextView tvApprovalLaunch = Tools.ViewHolder.get(view, R.id.tv_approval_launch);
        TextView tvApprovalNode = Tools.ViewHolder.get(view, R.id.tv_approval_node);
        TextView tvApprovalApplytime = Tools.ViewHolder.get(view, R.id.tv_approval_applytime);
        view.setId(position);
        tvApprovalApply.setText(entity.getFlowType());
        tvApprovalRemain.setText("\u3000剩余时间：" + entity.getSurplusTime());
        tvApprovalLaunch.setText(entity.getApplyPerson());
        tvApprovalNode.setText(entity.getFkNodeName());
        tvApprovalApplytime.setText("申请时间：" + entity.getApplyTime());
        return view;
    }
}
