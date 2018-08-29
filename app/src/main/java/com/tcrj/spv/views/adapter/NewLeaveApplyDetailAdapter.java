package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.spv.R;
import com.tcrj.spv.model.spInfo;

import java.util.List;

/**
 * Created on 2018/8/1.
 */

public class NewLeaveApplyDetailAdapter extends BaseQuickAdapter<spInfo.ListBean, BaseViewHolder> {


    public NewLeaveApplyDetailAdapter(@Nullable List<spInfo.ListBean> data, Context context) {
        super(R.layout.item_sp, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, spInfo.ListBean item) {
        helper.setText(R.id.tv_leave_rz_examine, item.getAppModel().getNDName());
        helper.setText(R.id.tv_leave_rz_person, item.getAppModel().getAuditorName());
        helper.setText(R.id.tv_leave_rz_date, item.getIAppTime());
        helper.setText(R.id.tv_leave_rz_content, item.getAppModel().getAuditorDesc());

    }

}
