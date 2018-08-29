package com.tcrj.spv.views;

import android.view.View;
import android.widget.ExpandableListView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.application.BaseFragment;

/**
 * Created by hy on 2017/12/24.
 */

public class MyGjFregment extends BaseFragment {

    ExpandableListView expandableListView;
    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fregment_mygj,null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }


}
