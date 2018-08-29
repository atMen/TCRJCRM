package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户管理详情
 * Created by leict on 2017/12/14.
 */

public class CustomerFilterTypeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<PublicListEntity> itemList;
    private int selectPosition = -1;

    public CustomerFilterTypeAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<PublicListEntity> list) {
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
        final PublicListEntity entity = (PublicListEntity) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_province_listview, null);
        }
        TextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.tv_province);
        LinearLayout layoutProvince = Tools.ViewHolder.get(view, R.id.layout_dialog_province);
        view.setId(position);
        tvNoticeTitle.setText(entity.getpName());
        if (selectPosition == position) {
            layoutProvince.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            layoutProvince.setBackgroundColor(context.getResources().getColor(R.color.bg_F7F7));
        }
        return view;
    }

    public void setSelection(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
