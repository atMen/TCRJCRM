package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.WorkProjectEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.List;

/**
 * 项目列表
 * Created by leict on 2017/12/17.
 */

public class WriteDailyProjectAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<WorkProjectEntity.DataBean> itemList;
    private int select = -1;

    public WriteDailyProjectAdapter(Context context, List<WorkProjectEntity.DataBean> dataBeanList) {
        this.context = context;
        this.itemList = dataBeanList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        final WorkProjectEntity.DataBean entity = (WorkProjectEntity.DataBean) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_project_listview, null);
        }
        CheckedTextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.ctv_project_choice);
        view.setId(position);
        tvNoticeTitle.setText(entity.getPName());
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
