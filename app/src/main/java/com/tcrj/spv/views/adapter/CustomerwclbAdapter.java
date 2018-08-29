package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.CustomerInfoEntity;
import com.tcrj.spv.views.utils.Tools;

import java.util.List;

/**
 * 获取客户信息列表
 * Created by leict on 2017/11/24.
 */

public class CustomerwclbAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<CustomerInfoEntity.ListInfoBean> itemList;

    public CustomerwclbAdapter(Context context, List<CustomerInfoEntity.ListInfoBean> dataList) {
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
        final CustomerInfoEntity.ListInfoBean entity = (CustomerInfoEntity.ListInfoBean) itemList.get(position);
        if (entity == null) {
            return null;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_customer_wclb_listview, null);
        }
        TextView tvCustomerName = Tools.ViewHolder.get(view, R.id.item_customer_name);
        TextView tvCustomerState = Tools.ViewHolder.get(view, R.id.item_customer_state);
        view.setId(position);
        tvCustomerName.setText(entity.getName());
        tvCustomerState.setText(entity.getStatus());

        return view;
    }
}
