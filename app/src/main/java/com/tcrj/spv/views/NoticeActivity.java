package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NoticeCallBack;
import com.tcrj.spv.model.NoticeEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.presenter.NoticePresenter;
import com.tcrj.spv.views.adapter.NoticeAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通知公告
 */
public class NoticeActivity extends BaseActivity implements View.OnClickListener, NoticeCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private XListView listview;
    private NoticeCallBack.Presenter presenter;
    private NoticeAdapter adapter;
    private int pageIndex = 1;
    private List<NoticeEntity.ListInfoBean> dataList = null;
    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        new NoticePresenter(this);
        initView();
        presenter.start();
    }

    @Override
    public void initView() {
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        listview = (XListView) findViewById(R.id.listview_notice);
        listview.setPullLoadEnable(true);
        listview.setPullRefreshEnable(true);
        imgBack.setOnClickListener(this);
        tvTitle.setText("通知公告");
        layoutSearchResult.setOnClickListener(this);
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
                pageIndex = 1;
                presenter.start();
            }

            @Override
            public void onLoadMore() {
                listview.setRefreshTime(DateUtils.getDateTimeString(new Date()));
                pageIndex++;
                presenter.start();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoticeEntity.ListInfoBean entity = (NoticeEntity.ListInfoBean) adapter.getItem(position - 1);
                Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                intent.putExtra("Id", entity.getId());
                startActivity(intent);
            }
        });
        dataList = new ArrayList<>();
        adapter = new NoticeAdapter(this, dataList);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_search_result:
                String result = edtSearchResult.getText().toString().trim();
                if (Utils.isStringEmpty(result)) {
                    displayToast("请输入关键字查询");
                    return;
                }
                presenter.start();
                break;
        }
    }

    @Override
    public void setPresenter(NoticeCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    @Override
    public void getData(NoticeEntity entity) {
        List<NoticeEntity.ListInfoBean> listInfo = entity.getListInfo();
        if (!Utils.isStringEmpty(listInfo)) {
            if (pageIndex == 1) {
                dataList.clear();
                listview.stopRefresh();
            } else {
                listview.setSelection(dataList.size() - 1);
                listview.stopLoadMore();
            }
            dataList.addAll(listInfo);
            adapter.notifyDataSetChanged();
            if (listInfo.size() < 10) {
                listview.setPullLoadEnable(false);
            } else {
                listview.setPullLoadEnable(true);
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        String result = edtSearchResult.getText().toString().trim();
        ParameterEntity entity = new ParameterEntity();
        entity.setKeyWord(result);
        entity.setPageSize(10);
        entity.setPageIndex(pageIndex);
        return entity;
    }
}
