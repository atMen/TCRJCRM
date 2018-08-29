package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户信息：排序
 * Created by leict on 2017/12/14.
 */

public class CustomerFilterSortAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<PublicListEntity> itemList;
    private int select = -1;

    public CustomerFilterSortAdapter(Context context) {
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

    public void setData(List<PublicListEntity> list) {
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
        final PublicListEntity entity = (PublicListEntity) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_popson_listview, null);
        }
        CheckedTextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.ctv_popson_choice);
        view.setId(position);
        tvNoticeTitle.setText(entity.getsName());
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
