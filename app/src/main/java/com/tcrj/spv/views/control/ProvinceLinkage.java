package com.tcrj.spv.views.control;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tcrj.spv.R;
import com.tcrj.spv.views.utils.Utils;

/**
 * 省市区联动
 * Created by leict on 2017/11/17.
 */

public class ProvinceLinkage {
    private Context context;
    private ListView provinceList;
    private ListView cityList;
    private ListView areaList;
    private View view;
    private PopupWindow popupWindow;
    private LayoutInflater mInflater;

    public ProvinceLinkage(Context context) {
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.dialog_province_city_area, null);
        popupWindow = new PopupWindow(view);
        provinceList = (ListView) view.findViewById(R.id.province_listview);
        cityList = (ListView) view.findViewById(R.id.city_listview);
        areaList = (ListView) view.findViewById(R.id.area_listview);
        popupWindow.setWidth(Utils.getWidth(context));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.update();
    }

    public void showPopupWindow(View parent) {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(parent, Gravity.BOTTOM, 0);
        } else {
            popupWindow.dismiss();
        }
    }
}
