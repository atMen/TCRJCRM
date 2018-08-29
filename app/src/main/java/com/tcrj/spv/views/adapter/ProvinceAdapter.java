package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.cityBean;
import com.tcrj.spv.views.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市区
 * Created by leict on 2017/11/21.
 */

public class ProvinceAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<cityBean.ListInfoBean> itemList;
    private int selectPosition = -1;

    public ProvinceAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<cityBean.ListInfoBean> list) {
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
//        final ProvinceEntity.ProvinceListBean entity = (ProvinceEntity.ProvinceListBean) itemList.get(position);
        final cityBean.ListInfoBean entity = itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_province_listview, null);
        }
        TextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.tv_province);
        LinearLayout layoutProvince = Tools.ViewHolder.get(view, R.id.layout_dialog_province);
        view.setId(position);
        tvNoticeTitle.setText(entity.getPName());
        if (selectPosition == position) {
            layoutProvince.setBackgroundColor(context.getResources().getColor(R.color.main_bg));
        } else {
            layoutProvince.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        return view;
    }

    public void setSelection(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
