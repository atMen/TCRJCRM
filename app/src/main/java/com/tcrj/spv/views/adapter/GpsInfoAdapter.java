package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.LocationEntity1;
import com.tcrj.spv.views.utils.Tools;

import java.util.List;

/**
 * 获取客户信息列表
 * Created by leict on 2017/11/24.
 */

public class GpsInfoAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private  LocationEntity1 itemList;
    private int type;

    public GpsInfoAdapter(Context context, LocationEntity1 dataList, int i) {
        this.type = i;
        this.context = context;
        this.itemList = dataList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (itemList == null) {
            return 0;
        }

        if(type == 0){
            return itemList.getMapLocation().size();
        }
        return itemList.getNoMapLocation().size();
    }

    @Override
    public Object getItem(int position) {
        if(type == 0){
            return itemList.getMapLocation().get(position);
        }
        return itemList.getNoMapLocation().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        List<LocationEntity1.MapLocationBean> mapLocation = itemList.getMapLocation();
        List<LocationEntity1.NoMapLocationBean> noMapLocation = itemList.getNoMapLocation();


        if(type == 0){
            if (mapLocation == null) {
                return null;
            }
        }else {
            if (noMapLocation == null) {
                return null;
            }
        }

        if (view == null) {
            view = inflater.inflate(R.layout.item_gps_listview, null);
        }
        TextView tvCustomerName = Tools.ViewHolder.get(view, R.id.item_customer_name);
        TextView tvCustomerState = Tools.ViewHolder.get(view, R.id.item_customer_state);
        TextView tvCustomeradress = Tools.ViewHolder.get(view, R.id.item_adress);
        LinearLayout llCustomeradress = Tools.ViewHolder.get(view, R.id.ll_dress);

        view.setId(position);


        if(type == 0){
            tvCustomerState.setVisibility(View.VISIBLE);
            llCustomeradress.setVisibility(View.VISIBLE);

            tvCustomerState.setText(mapLocation.get(position).getAddDatetime());
            tvCustomerName.setText(mapLocation.get(position).getStaffName());
            tvCustomeradress.setText(mapLocation.get(position).getAddress());
        }else{
            tvCustomerState.setVisibility(View.GONE);
            llCustomeradress.setVisibility(View.GONE);
            tvCustomerName.setText(noMapLocation.get(position).getStaffName());
        }





        return view;
    }
}
