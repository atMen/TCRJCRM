package com.tcrj.spv.views.control;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.LeaveTypeCallBack;
import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.presenter.LeaveTypePresenter;
import com.tcrj.spv.views.adapter.LeaveApplyAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ------暂时废除-----
 * Created by leict on 2017/11/14.
 */

public class SpinnerPopWindow implements LeaveTypeCallBack.View {
    private Context context;
    private ListView listView;
    private View view;
    private PopupWindow popupWindow;
    private LeaveApplyAdapter adapter;
    private LayoutInflater mInflater;
    private LeaveTypeCallBack.Presenter presenter;
    private ILeaveTypeCallBack callBack;

    public SpinnerPopWindow(Context context) {
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.popuwindow_listview, null);
        popupWindow = new PopupWindow(view);
        listView = (ListView) view.findViewById(R.id.pop_listview);
        popupWindow.setWidth(Utils.getWidth(context));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.update();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LeaveApplyEntity entity = (LeaveApplyEntity) adapter.getItem(position);
                if (callBack != null) {
                    callBack.setOnClickListener(entity.getTypeName());
                    popupWindow.dismiss();
                }
            }
        });
        new LeaveTypePresenter(SpinnerPopWindow.this);
        presenter.start();
    }

    public void showPopupWindow(View parent) {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(parent, 0, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    @Override
    public void setPresenter(LeaveTypeCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) context).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) context).dismisProgressDialog();
    }

    @Override
    public void getData(LeaveApplyEntity entity) {
        List<String> dataList = entity.getLeavType();
        if (!Utils.isStringEmpty(entity.getLeavType())) {
            List<LeaveApplyEntity> itemList = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                LeaveApplyEntity leave = new LeaveApplyEntity();
                LogUtils.info("FFFFFFF", dataList.get(i));
                leave.setTypeName(dataList.get(i));
                itemList.add(leave);
            }
            adapter = new LeaveApplyAdapter(context);
            adapter.setData(itemList);
            listView.setAdapter(adapter);
        }
    }

    public void setOnItemClickListener(ILeaveTypeCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ILeaveTypeCallBack {
        void setOnClickListener(String name);
    }
}
