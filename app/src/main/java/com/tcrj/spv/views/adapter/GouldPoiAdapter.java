package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.amap.api.services.core.PoiItem;
import com.tcrj.spv.R;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Poi搜索
 * Created by leict on 2017/12/12.
 */

public class GouldPoiAdapter extends BaseAdapter {
    private Context context;
    private int select = -1;
    private LayoutInflater inflater;
    private List<PoiItem> itemList;

    public GouldPoiAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<PoiItem> list) {
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
        final PoiItem entity = (PoiItem) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_single_select, null);
        }
        CheckedTextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.ctv_single_choice);
        view.setId(position);
        tvNoticeTitle.setText(entity.getTitle() + "\n" + entity.getSnippet());
        if (select == position) {
            tvNoticeTitle.setChecked(true);
            tvNoticeTitle.setTextColor(Color.parseColor("#FC6616"));
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
