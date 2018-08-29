package com.tcrj.spv.views.control;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.views.adapter.CustomerFilterSortAdapter;
import com.tcrj.spv.views.adapter.CustomerFilterTypeAdapter;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户管理条件查询
 * Created by leict on 2017/12/14.
 */

public class CustomerPopWindow {
    private Context context;
    private ListView listView;
    private XListView xListView;
    private View view;
    private PopupWindow popupWindow;
    private CustomerFilterTypeAdapter adapter;
    private CustomerFilterSortAdapter sAdapter;
    private LayoutInflater mInflater;

    public CustomerPopWindow(Context context) {
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.popu_fileter_customer, null);
        popupWindow = new PopupWindow(view);
        listView = (ListView) view.findViewById(R.id.type_listview);
        xListView = (XListView) view.findViewById(R.id.sort_listview);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(false);
        popupWindow.setWidth(Utils.getWidth(context));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.update();
        setFatherData();
        setSonData();
    }

    /**
     * 分类数据源
     */
    private void setFatherData() {
        CharSequence[] typeArray = context.getResources().getStringArray(R.array.father_data);
        List<PublicListEntity> dataList = new ArrayList<>();
        for (int i = 0; i < typeArray.length; i++) {
            PublicListEntity entity = new PublicListEntity();
            entity.setId(i + 1);
            entity.setpName(typeArray[i].toString());
            dataList.add(entity);
        }
        adapter = new CustomerFilterTypeAdapter(context);
        adapter.setData(dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelection(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 我的客户
     */
    private void setSonData() {
        CharSequence[] typeArray = context.getResources().getStringArray(R.array.son_customer_intent);
        List<PublicListEntity> dataList = new ArrayList<>();
        for (int i = 0; i < typeArray.length; i++) {
            PublicListEntity entity = new PublicListEntity();
            entity.setmId(i);
            entity.setsName(typeArray[i].toString());
            entity.setSid("0");
            dataList.add(entity);
        }
        sAdapter = new CustomerFilterSortAdapter(context);
        sAdapter.setData(dataList);
        xListView.setAdapter(sAdapter);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PublicListEntity entity = (PublicListEntity) sAdapter.getItem(position - 1);
                sAdapter.setSelectItem(position - 1);
                sAdapter.notifyDataSetChanged();
                Toast.makeText(context, entity.getsName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showPopupWindow(View parent) {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(parent, 0, 0);
        } else {
            popupWindow.dismiss();
        }
    }
}
