package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcrj.spv.R;

import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.adapter.CustomTabView;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作日报
 */
public class WorkDailyActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imgBack;
    private ImageButton imgCommon;
    private TextView tvTitle;
//    private TextView tv_bm;
    private TabLayout tabList;
    private ViewPager viewPager;
    private List<Fragment> fragments = null;
    private int currentItem = 0;
    private RelativeLayout includeWork;
    private RelativeLayout includeDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdaily);
        initView();
        setTabLayout();
    }

    @Override
    public void initView() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        includeDaily = (RelativeLayout) findViewById(R.id.include_daily);
        includeWork = (RelativeLayout) findViewById(R.id.include_work);
        tabList = (TabLayout) findViewById(R.id.comment_tab);
        imgCommon = (ImageButton) findViewById(R.id.img_back_common);
        viewPager = (ViewPager) findViewById(R.id.work_viewPager);
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
//      tv_bm = (TextView) findViewById(R.id.tv_bm);

        imgBack.setOnClickListener(this);
        imgCommon.setOnClickListener(this);
        if (entity.getEntity().getIsHavexj() == 0) {
            includeWork.setVisibility(View.VISIBLE);
            includeDaily.setVisibility(View.GONE);
        } else {
            includeWork.setVisibility(View.GONE);
            includeDaily.setVisibility(View.VISIBLE);
        }
        tvTitle.setText("我的日报");
    }

    /**
     * 设置TabLayout
     */
    private void setTabLayout() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        if (entity.getEntity().getIsHavexj() == 0) {
            fragments.add(new MineDailyFragment());
        } else {
            titles.add("我的日报");
            titles.add("部门日报");
            for (int i = 0; i < titles.size(); i++) {
                tabList.addTab(tabList.newTab().setText(titles.get(i)));
            }
            fragments.add(new MineDailyFragment());
            fragments.add(new BranchDailyFragment());
        }
        CustomTabView adapter = new CustomTabView(getSupportFragmentManager(), WorkDailyActivity.this, fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabList.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabList.setTabsFromPagerAdapter(adapter);
        //这里就是将两个自定义tab添加到tablayout中
        if (entity.getEntity().getIsHavexj() == 0) {
            TabLayout.Tab tabs = tabList.getTabAt(0);
            tabs.setCustomView(adapter.getTabView1());
        } else {
            TabLayout.Tab tabs = tabList.getTabAt(0);
            tabs.setCustomView(adapter.getTabView1());
            tabs = tabList.getTabAt(1);
            tabs.setCustomView(adapter.getTabView2());
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setButtonPerformClick(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 0:
                        LogUtils.info("Work", "~~~~~~~~~~无动作");
                        currentItem = 0;
                        break;
                    case 1:
                        LogUtils.info("Work", "~~~~~~~~~~点击、滑屏");
                        currentItem = 1;
                        break;
                    case 2:
                        LogUtils.info("Work", "~~~~~~~~~~释放");
                        currentItem = 2;
                        break;
                }
            }
        });
        viewPager.setCurrentItem(currentItem);
    }

    protected void setButtonPerformClick(int position) {
        switch (position) {
            case 0:
                Log.e("TAG","滑动到："+position);
                break;
            case 1:
                Log.e("TAG","滑动到了："+position);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_back_common:
                finish();
                break;
        }
    }
}
