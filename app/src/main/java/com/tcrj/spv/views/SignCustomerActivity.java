package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.adapter.FragmentAdapter;
import com.tcrj.spv.views.application.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 拜访签到：获取拜访人
 * Created by leict on 2017/12/15.
 */

public class SignCustomerActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TabLayout tabList;
    private ViewPager viewPager;
    private List<Fragment> fragments = null;
    private SignMineCustomerFragment fragment1;
    private SignShareCustomerFragment fragment2;
    private LinearLayout layoutFilterType;
    private TextView tvTypeTitle;
    private ImageView imgTypeTitle;
    private LinearLayout layoutFilterSort;
    private TextView tvSortTitle;
    private ImageView imgSortTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecustomer);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tabList = (TabLayout) findViewById(R.id.tabs_customer);
        viewPager = (ViewPager) findViewById(R.id.pager_customer);
        layoutFilterType = (LinearLayout) findViewById(R.id.layout_filter_type);
        tvTypeTitle = (TextView) findViewById(R.id.tv_type_title);
        imgTypeTitle = (ImageView) findViewById(R.id.img_type_title);
        layoutFilterSort = (LinearLayout) findViewById(R.id.layout_filter_sort);
        tvSortTitle = (TextView) findViewById(R.id.tv_sort_title);
        imgSortTitle = (ImageView) findViewById(R.id.img_sort_title);
        tvTitle.setText("客户管理");
        imgBack.setOnClickListener(this);
        layoutFilterSort.setOnClickListener(this);
        layoutFilterType.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("我的客户");
        titles.add("共享客户");
        for (int i = 0; i < titles.size(); i++) {
            tabList.addTab(tabList.newTab().setText(titles.get(i)));
        }
        fragments = new ArrayList<>();
        fragment1 = new SignMineCustomerFragment();
        fragment2 = new SignShareCustomerFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabList.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabList.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
