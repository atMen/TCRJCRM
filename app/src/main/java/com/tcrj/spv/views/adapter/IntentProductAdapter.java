package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.IntentProductEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 意向产品
 * Created by leict on 2017/11/22.
 */

public class IntentProductAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<IntentProductEntity.ListInfoBean> itemList;

    public IntentProductAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    public void setData(List<IntentProductEntity.ListInfoBean> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
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
        final IntentProductEntity.ListInfoBean entity = (IntentProductEntity.ListInfoBean) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_intent_listview, null);
        }
        final TextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.tv_multiple_choice);
        view.setId(position);
        tvNoticeTitle.setText(entity.getPName());
        if (entity.getFlag() == 0) {
            tvNoticeTitle.setTextColor(Color.parseColor("#808080"));
        } else {
            tvNoticeTitle.setTextColor(Color.parseColor("#ff7e00"));
        }
        return view;
    }

    public void setSelectItemId(int positionId, int flag) {
        itemList.get(positionId).setFlag(flag);
        notifyDataSetChanged();
    }
}
