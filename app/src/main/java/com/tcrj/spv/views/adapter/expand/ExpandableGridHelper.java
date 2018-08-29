package com.tcrj.spv.views.adapter.expand;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tcrj.spv.R;
import com.tcrj.spv.views.control.CircleImageView;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leict on 2017/11/8.
 */

public class ExpandableGridHelper extends RecyclerView.Adapter<ExpandableGridHelper.ViewHolder> {

    //data array
    private List<Object> mDataArrayList;

    //context
    private final Context mContext;

    //listeners
    private final ItemClickListener mItemClickListener;
    private final IStateChangeListener mSectionStateChangeListener;


    //view type
    private static final int VIEW_TYPE_SECTION = R.layout.item_daily_group;
    private static final int VIEW_TYPE_ITEM = R.layout.item_daily_recycler; //TODO : change this

    public ExpandableGridHelper(Context context, ArrayList<Object> dataArrayList,
                                final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener,
                                IStateChangeListener sectionStateChangeListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mSectionStateChangeListener = sectionStateChangeListener;
        mDataArrayList = dataArrayList;

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isSection(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    private boolean isSection(int position) {
        return mDataArrayList.get(position) instanceof Section;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(viewType, parent, false), viewType);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (holder.viewType) {
            case VIEW_TYPE_ITEM:
                final ItemEntity item = (ItemEntity) mDataArrayList.get(position);
                holder.tvDailyUsername.setText(item.getStaffName());
                holder.tvDailyExamine.setText(item.getReviewsQuality());
                holder.tvDailyTime.setText(item.getHour());
                Log.e("TAG",""+item.getHour());
                holder.tvDailyTodaywork.setText(item.getLogContent());
                holder.tvDailyTomorrowplan.setText(item.getPlanContent());
                holder.layoutItemDaily.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.itemClicked(item);
                    }
                });
                break;
            case VIEW_TYPE_SECTION:
                final Section section = (Section) mDataArrayList.get(position);
                holder.tvGroupName.setText(section.getName());
                holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mSectionStateChangeListener.onStateChanged(section, isChecked);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isSection(position)) {
            return VIEW_TYPE_SECTION;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imgPhoto;
        public TextView tvDailyUsername;
        public TextView tvDailyExamine;
        public TextView tvDailyTime;
        public TextView tvDailyTodaywork;
        public TextView tvDailyTomorrowplan;
        public TextView tvGroupName;
        public int viewType;
        public ToggleButton toggleButton;
        public LinearLayout layoutItemDaily;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            if (viewType == VIEW_TYPE_ITEM) {
                imgPhoto = Tools.ViewHolder.get(itemView, R.id.img_photo);
                tvDailyUsername = Tools.ViewHolder.get(itemView, R.id.tv_daily_username);
                tvDailyExamine = Tools.ViewHolder.get(itemView, R.id.tv_daily_examine);
                tvDailyTime = Tools.ViewHolder.get(itemView, R.id.tv_daily_time);
                tvDailyTodaywork = Tools.ViewHolder.get(itemView, R.id.tv_daily_todaywork);
                tvDailyTomorrowplan = Tools.ViewHolder.get(itemView, R.id.tv_daily_tomorrowplan);
                layoutItemDaily = Tools.ViewHolder.get(itemView, R.id.layout_item_daily);
            } else {
                tvGroupName = Tools.ViewHolder.get(itemView, R.id.tv_daily_date);
                toggleButton = Tools.ViewHolder.get(itemView, R.id.toggle_btn_section);
            }
        }
    }
}
