package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PhoneListEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录
 * Created by leict on 2017/11/30.
 */

public class PhoneListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<PhoneListEntity> itemList;

    public PhoneListAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<PhoneListEntity> list) {
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
        final PhoneListEntity entity = (PhoneListEntity) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_phone_listview, null);
        }
        TextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.tv_phone_link);
        view.setId(position);
        tvNoticeTitle.setText(entity.getPhoneName() + "：" + entity.getPhoneCord());
        return view;
    }
}
