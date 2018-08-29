package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.utils.Tools;
import com.tcrj.spv.views.utils.Utils;

import java.util.List;

/**
 * 自定义TabLayout
 * Created by leict on 2017/11/13.
 */

public class CustomTabView extends FragmentPagerAdapter {
    private List<Fragment> view;
    private List<String> titles;
    private LayoutInflater mInflater;
    private Context context;

    public CustomTabView(FragmentManager fm, Context mContext, List<Fragment> mView, List<String> mTitles) {
        super(fm);
        this.view = mView;
        this.titles = mTitles;
        this.context = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return view.get(position);
    }

    @Override
    public int getCount() {
        return view.size();
    }

    public View getTabView1() {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_tablayout_left, null);
        TextView tvName = Tools.ViewHolder.get(view, R.id.tv_mine_daily);
        if (!Utils.isStringEmpty(titles)) {
            tvName.setText(titles.get(0).toString());
        }
        return view;
    }

    public View getTabView2() {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_tablayout_right, null);
        TextView tvName = Tools.ViewHolder.get(view, R.id.tv_subordinate_daily);
        if (!Utils.isStringEmpty(titles)) {
            tvName.setText(titles.get(1).toString());
        }
        return view;
    }
}
