package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.SignRecordEntity;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 签到记录
 * Created by leict on 2017/11/1
 */

public class SignRecordAdapter extends RecyclerView.Adapter<SignRecordAdapter.ViewHolder> {
    public List<SignRecordEntity.KaoQinListBean> itemList;
    private LayoutInflater inflater;
    public Context context;

    public SignRecordAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<SignRecordEntity.KaoQinListBean> list) {
        this.itemList.clear();


        this.itemList.addAll(list);
        Collections.sort(itemList, new Comparator<SignRecordEntity.KaoQinListBean>() {

            @Override
            public int compare(SignRecordEntity.KaoQinListBean o1, SignRecordEntity.KaoQinListBean o2) {

                String substring = o1.getKQRQ().substring(0,10);
                String substring1 = o2.getKQRQ().substring(0,10);
                long aLong = 0;
                long aLong1 = 0;
                Log.e("TAG","sub:"+substring+"sub1:"+substring1);
                try {
                     aLong = DateUtils.stringToLong(substring, "yyyy-MM-dd");
                     aLong1 = DateUtils.stringToLong(substring1, "yyyy-MM-dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e("TAG","aLong:"+aLong+"aLong1:"+aLong1);
                return (int) (aLong1 - aLong);
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_group_listview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SignRecordEntity.KaoQinListBean entity = itemList.get(position);
        holder.tvGroupName.setText(entity.getKQRQ());
        if (entity.getQDDZ().contains("迟到") || entity.getQDDZ().contains("未签到")) {
            //设置字体颜色
            holder.tvSignInState.setTextColor(ContextCompat.getColor(context, R.color.word_6616));
        } else {
            //设置字体颜色
            holder.tvSignInState.setTextColor(ContextCompat.getColor(context, R.color.word_7373));
        }
        if (entity.getQTDZ().contains("未签退") || entity.getQTDZ().contains("早退")) {
            //设置字体颜色
            holder.tvSignOutState.setTextColor(ContextCompat.getColor(context, R.color.word_6616));
        } else {
            //设置字体颜色
            holder.tvSignOutState.setTextColor(ContextCompat.getColor(context, R.color.word_7373));
        }
        if (Utils.isStringEmpty(entity.getQDSJ()) && Utils.isStringEmpty(entity.getQTSJ())) {
            holder.layoutSing.setVisibility(View.GONE);
        } else {
            holder.layoutSing.setVisibility(View.VISIBLE);
        }
        if (Utils.isStringEmpty(entity.getQDDZ()) && Utils.isStringEmpty(entity.getQTDZ())) {
            holder.layoutState.setVisibility(View.GONE);
        } else {
            holder.layoutState.setVisibility(View.VISIBLE);
        }
        if (Utils.isStringEmpty(entity.getQDSJ()) && Utils.isStringEmpty(entity.getQTSJ()) && Utils.isStringEmpty(entity.getQDDZ()) && Utils.isStringEmpty(entity.getQTDZ())) {
            holder.layoutNothing.setVisibility(View.VISIBLE);
        } else {
            holder.layoutNothing.setVisibility(View.GONE);
        }
        if (Utils.isStringEmpty(entity.getQDDZ())) {
            holder.tvSignInState.setText("正常");
        } else {
            holder.tvSignInState.setText(entity.getQDDZ());
        }
        if (Utils.isStringEmpty(entity.getQTDZ())) {
            holder.tvSignOutState.setText("正常");
        } else {
            holder.tvSignOutState.setText(entity.getQTDZ());
        }
        if (Utils.isStringEmpty(entity.getQTSJ())) {
            holder.tvSignOutTime.setText("正常");
        } else {
            holder.tvSignOutTime.setText(entity.getQTSJ());
        }
        if (Utils.isStringEmpty(entity.getQDSJ())) {
            holder.tvSignInTime.setText("正常");
        } else {
            holder.tvSignInTime.setText(entity.getQDSJ());
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSignInState = null;
        public TextView tvSignInTime = null;
        public TextView tvSignOutState = null;
        public TextView tvSignOutTime = null;
        public TextView tvGroupName = null;
        public LinearLayout layoutSing = null;
        public LinearLayout layoutState = null;
        public LinearLayout layoutNothing = null;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGroupName = (TextView) itemView.findViewById(R.id.tv_group_name);
            tvSignInState = (TextView) itemView.findViewById(R.id.tv_signin_state);
            tvSignInTime = (TextView) itemView.findViewById(R.id.tv_signin_time);
            tvSignOutState = (TextView) itemView.findViewById(R.id.tv_signout_state);
            tvSignOutTime = (TextView) itemView.findViewById(R.id.tv_signout_time);
            layoutSing = (LinearLayout) itemView.findViewById(R.id.layout_sign_time);
            layoutState = (LinearLayout) itemView.findViewById(R.id.layout_sign_state);
            layoutNothing = (LinearLayout) itemView.findViewById(R.id.layout_nothing);
        }
    }
}
