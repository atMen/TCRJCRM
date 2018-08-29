package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tcrj.spv.R;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.VisitRecordEntity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.control.CircleImageView;
import com.tcrj.spv.views.utils.Tools;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 拜访记录
 * Created by leict on 2017/12/11.
 */

public class VisitRecordAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private IonClickCallBack callBack;
    private List<VisitRecordEntity.ListInfoBean> dataList;

    public VisitRecordAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<VisitRecordEntity.ListInfoBean> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataList.get(groupPosition).getListtVisitRecords().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public VisitRecordEntity.ListInfoBean.ListtVisitRecordsBean getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).getListtVisitRecords().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int roupPosition, boolean isExpanded, View groupView, ViewGroup parent) {
        VisitRecordEntity.ListInfoBean entity = (VisitRecordEntity.ListInfoBean) dataList.get(roupPosition);
        if (groupView == null) {
            groupView = inflater.inflate(R.layout.item_group_record, parent, false);
        }
        groupView.setId(roupPosition);
        TextView tvRecordDatetime = Tools.ViewHolder.get(groupView, R.id.tv_record_datetime);
        tvRecordDatetime.setText(entity.getTime());
        return groupView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View childView, ViewGroup parent) {
        final VisitRecordEntity.ListInfoBean.ListtVisitRecordsBean entity = (VisitRecordEntity.ListInfoBean.ListtVisitRecordsBean) dataList.get(groupPosition).getListtVisitRecords().get(childPosition);
        if (childView == null) {
            childView = inflater.inflate(R.layout.item_visit_record, parent, false);
        }
        childView.setId(childPosition);
        CircleImageView visitCircleImage = Tools.ViewHolder.get(childView, R.id.visit_circle_image);
        TextView tvVisitName = Tools.ViewHolder.get(childView, R.id.tv_visit_name);
        TextView tvVisitWays = Tools.ViewHolder.get(childView, R.id.tv_visit_ways);
        TextView tvVisitTime = Tools.ViewHolder.get(childView, R.id.tv_visit_time);
//        LinearLayout layoutVisitMap = Tools.ViewHolder.get(childView, R.id.layout_visit_map);
//        ImageView imgVisitMap = Tools.ViewHolder.get(childView, R.id.img_visit_map);
        TextView tvVisitLocation = Tools.ViewHolder.get(childView, R.id.tv_visit_location);
        LinearLayout layoutVisitCustomer = Tools.ViewHolder.get(childView, R.id.layout_visit_customer);
        TextView tvVisitCustomer = Tools.ViewHolder.get(childView, R.id.tv_visit_customer);
        tvVisitName.setText(entity.getStaffName());
        tvVisitTime.setText(entity.getHour());
        tvVisitLocation.setText(entity.getSingPlace());
        tvVisitCustomer.setText(entity.getCustomerName());
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        if (!Utils.isStringEmpty(user.getEntity().getStaffImg())) {
            Glide.with(context).load(user.getEntity().getStaffImg()).into(visitCircleImage);
        }
//        if (!Utils.isStringEmpty(entity.getMapImg())) {
//
//            //获取签到时提交的图片，显示出来----点击小图显示bigImage（entity.getBigImg()）
//            Glide.with(context).load(entity.getMapImg()).into(imgVisitMap);
//        }
//        layoutVisitMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack != null) {
//                    callBack.setVisitListener(Double.parseDouble(entity.getXCoord()), Double.parseDouble(entity.getYCoord()), entity.getSingPlace());
//                }
//            }
//        });
        layoutVisitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.setCustomerListener(groupPosition, childPosition);
                }
            }
        });
        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setOnClickListener(IonClickCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IonClickCallBack {
        void setVisitListener(double latting, double longlation, String signPlace);

        void setCustomerListener(int g, int c);
    }
}
