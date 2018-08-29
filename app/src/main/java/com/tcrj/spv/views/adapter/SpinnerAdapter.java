package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leict on 2016/1/22.
 */
public class SpinnerAdapter extends BaseAdapter {
    private List<SpinnerEntity.ListInfoBean> itemList;
    public Context context;
    private LayoutInflater inflater;

    public SpinnerAdapter(Context context) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = new ArrayList<>();
    }

    public void setData(List<SpinnerEntity.ListInfoBean> list) {
        clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<SpinnerEntity.ListInfoBean> list) {
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        if (!Utils.isStringEmpty(itemList)) {
            itemList.clear();
        }
    }

    @Override
    public int getCount() {
        if (itemList == null)
            return 0;
        return itemList.size();
    }

    @Override
    public SpinnerEntity.ListInfoBean getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ViewHolder holder = null;

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final SpinnerEntity.ListInfoBean entity = itemList.get(position);
        if (entity == null)
            return null;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_spinner, null);
            holder.tvItemTitle = (TextView) view.findViewById(R.id.item_spinner_name);
            view.setTag(holder);
            view.setId(position);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.tvItemTitle.setText(entity.getDName());
        return view;
    }

    private class ViewHolder {
        TextView tvItemTitle = null;
    }
}
