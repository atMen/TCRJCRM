package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.NoticeEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.List;

/**
 * 通知公告
 * Created by leict on 2017/10/31.
 */

public class NoticeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<NoticeEntity.ListInfoBean> itemList;

    public NoticeAdapter(Context context, List<NoticeEntity.ListInfoBean> dataList) {
        this.context = context;
        this.itemList = dataList;
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
        final NoticeEntity.ListInfoBean entity = (NoticeEntity.ListInfoBean) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_listview_notice, null);
        }
        TextView tvNoticeTitle = Tools.ViewHolder.get(view, R.id.tv_notice_title);
        TextView tvNoticeTime = Tools.ViewHolder.get(view, R.id.tv_notice_time);
        TextView tvNoticePerson = Tools.ViewHolder.get(view, R.id.tv_notice_person);
        TextView tvNoticeScope = Tools.ViewHolder.get(view, R.id.tv_notice_scope);
        view.setId(position);
        tvNoticeTitle.setText(entity.getTitle());
        tvNoticeTime.setText("发布时间：" + entity.getReleseTime());
        tvNoticePerson.setText("发布人：" + entity.getStaffName());
        tvNoticeScope.setText("发布范围：" + entity.getReceivingObject());
        return view;
    }
}
