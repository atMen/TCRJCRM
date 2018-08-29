package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.adapter.FragmentAdapter;
import com.tcrj.spv.views.application.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 领导审批
 */
public class LeaderExamineActivity extends BaseActivity {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TabLayout tabList;
    private ViewPager viewPager;
    private List<Fragment> fragments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderexamine);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tabList = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvTitle.setText("我的审批");
        List<String> titles = new ArrayList<>();
        titles.add("待办审批");
        titles.add("在办审批");
        titles.add("完结审批");
        for (int i = 0; i < titles.size(); i++) {
            tabList.addTab(tabList.newTab().setText(titles.get(i)));
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        fragments.add(new PendApprovalFragment(this));
        fragments.add(new OfficeApprovalFragment());
        fragments.add(new FinishApprovalFragment());


        viewPager.setOffscreenPageLimit(3);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabList.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabList.setTabsFromPagerAdapter(adapter);
    }
}
