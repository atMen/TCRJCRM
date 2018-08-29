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
 * 完成审批
 * Created by leict on 2017/11/6.
 */

public class FinishedAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<ApprovalEntity.ListInfoBean> itemList;

    public FinishedAdapter(Context context) {
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
            view = inflater.inflate(R.layout.item_approval_finish, null);
        }
        TextView tvFinishType = Tools.ViewHolder.get(view, R.id.tv_finish_type);
        TextView tvFinishUserName = Tools.ViewHolder.get(view, R.id.tv_finish_username);
        TextView tvFinishState = Tools.ViewHolder.get(view, R.id.tv_finish_state);
        TextView tvFinishTime = Tools.ViewHolder.get(view, R.id.tv_finish_applytime);
        view.setId(position);
        tvFinishType.setText(entity.getFlowType());
        tvFinishUserName.setText(entity.getApplyPerson());
        tvFinishState.setText(entity.getState());
        tvFinishTime.setText("申请时间：" + entity.getApplyTime());
        return view;
    }
}
