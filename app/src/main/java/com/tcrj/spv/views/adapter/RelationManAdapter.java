package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.RelationManEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户：联系人
 * Created by leict on 2017/12/6.
 */

public class RelationManAdapter extends BaseAdapter {
    private List<RelationManEntity.ListInfoBean> itemList = null;
    private Context mContext;
    private LayoutInflater inflater;

    public RelationManAdapter(Context mContext) {
        this.mContext = mContext;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void setData(List<RelationManEntity.ListInfoBean> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.itemList.size();
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        final RelationManEntity.ListInfoBean entity = itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_letter_listview, null);
        }
        TextView tvLetterIndex = Tools.ViewHolder.get(view, R.id.tv_letter_index);
        TextView tvLetterName = Tools.ViewHolder.get(view, R.id.tv_letter_name);
        TextView tvLetterJob = Tools.ViewHolder.get(view, R.id.tv_letter_job);
        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            tvLetterIndex.setVisibility(View.VISIBLE);
            tvLetterIndex.setText(entity.getPinYin());
        } else {
            tvLetterIndex.setVisibility(View.GONE);
        }
        tvLetterName.setText(entity.getUName());
        tvLetterJob.setText(entity.getUDept() + " | " + entity.getURole());
        return view;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return itemList.get(position).getPinYin().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = itemList.get(i).getPinYin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
