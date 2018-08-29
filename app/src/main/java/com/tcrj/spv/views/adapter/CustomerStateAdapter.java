package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.CustomerStateEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户状态
 * Created by leict on 2017/11/22.
 */

public class CustomerStateAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<CustomerStateEntity> itemList;
    private int select = -1;

    public CustomerStateAdapter(Context context) {
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

    public void setData(List<CustomerStateEntity> list) {
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
        final CustomerStateEntity entity = (CustomerStateEntity) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_single_select, null);
        }
        CheckedTextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.ctv_single_choice);
        view.setId(position);
        tvNoticeTitle.setText(entity.getStateName());
        if (select == position) {
            tvNoticeTitle.setChecked(true);
            tvNoticeTitle.setTextColor(Color.parseColor("#ff7e00"));
        } else {
            tvNoticeTitle.setChecked(false);
            tvNoticeTitle.setTextColor(Color.parseColor("#808080"));
        }
        return view;
    }

    public void setSelectItem(int position) {
        this.select = position;
    }
}
