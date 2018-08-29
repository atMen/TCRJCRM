package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.NoticeEntity;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Tools;

import java.util.List;

/**
 * 工作日报
 * Created by leict on 2017/11/2.
 */

public class WorkDailyAdapter extends RecyclerView.Adapter<WorkDailyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    public Context context;
    private List<NoticeEntity.ListInfoBean> itemList;

    public WorkDailyAdapter(Context context, List<NoticeEntity.ListInfoBean> dataList) {
        this.context = context;
        this.itemList = dataList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groupView = mInflater.inflate(R.layout.item_daily_group, parent, false);
        return new ViewHolder(groupView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String value = itemList.get(position).getTitle();
        LogUtils.info("----", value);
        holder.tvGroupName.setText(itemList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGroupName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGroupName = Tools.ViewHolder.get(itemView, R.id.tv_daily_date);
        }
    }
}
