package com.tcrj.spv.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerInfoCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.CustomerInfoEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.CustomerInfoPresenter;
import com.tcrj.spv.views.adapter.CustomerwclbAdapter;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogLoading;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.DateUtils;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignMineCustomerActivity extends AppCompatActivity implements CustomerInfoCallBack.View, View.OnClickListener {

    private int pageIndex = 1;
    private List<CustomerInfoEntity.ListInfoBean> dataList;
    private XListView listview;
    private CustomerInfoCallBack.Presenter presenter;
    private CustomerwclbAdapter adapter;
    private CommonalityEntity commonality;

    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;
    private LinearLayout ll_search;
    TextView tvTitle;
    ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_mine_customer);
        new CustomerInfoPresenter(this);
        setCommonality();
        initView();
        presenter.start();
    }

    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setCusStatus("0");
        commonality.setCusClass("0");
        commonality.setIsHot("-1");
        commonality.setIntentionPro("");
        commonality.setReportStatus("-1");
        commonality.setBelongArea("0");
        commonality.setOwerUserId("-1");
        commonality.setKeyWord("");
        commonality.setOrder("0");
        commonality.setCurrenttype("0");
    }

    /**
     * 获取页面控件
     *
     * @param
     */
    private void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("外出列表");
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        edtSearchResult.setHint("请输入外出地址名称");
        ll_search = (LinearLayout)findViewById(R.id.ll_search);
        ll_search.setVisibility(View.VISIBLE);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        listview = (XListView) findViewById(R.id.customer_listview);
        layoutSearchResult.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        listview.setPullRefreshEnable(true);
        listview.setPullLoadEnable(true);
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
                CustomerInfoEntity.ListInfoBean entity = (CustomerInfoEntity.ListInfoBean) adapter.getItem(position - 1);
                Intent intent = new Intent();
                intent.putExtra("VisitId", entity.getCid());
                intent.putExtra("VisitName", entity.getName());
                setResult(4, intent);
                finish();
            }
        });
        dataList = new ArrayList<>();
        adapter = new CustomerwclbAdapter(this, dataList);
        listview.setAdapter(adapter);
    }
    public DialogLoading loading;
    /**
     * 加载进度条
     *
     * @param message
     */
    public void showProgressDialog(String message) {
        loading = DialogLoading.getInstance(this);
        loading.setMessage(message)
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    public void showProgressDialog() {
        loading = DialogLoading.getInstance(this);
        loading.setMessage("正在加载...")
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }


    /**
     * 关闭进度条
     */
    public void dismisProgressDialog() {
        if (loading == null) {
            return;
        } else {
            if (loading.isShowing()) {
                loading.dismiss();
                loading = null;
            }
        }
    }


    @Override
    public void setPresenter(CustomerInfoCallBack.Presenter presenter) {
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
    public void getData(CustomerInfoEntity entity) {
        List<CustomerInfoEntity.ListInfoBean> listInfo = entity.getListInfo();
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
        }else {
            dataList.clear();
            listview.stopRefresh();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "没有检索到相关人员", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setUserId(user.getEntity().getUserId());
        entity.setCusStatus(commonality.getCusStatus());
        entity.setCusClass(commonality.getCusClass());
        entity.setIsHot(commonality.getIsHot());
        entity.setIntentionPro(commonality.getIntentionPro());
        entity.setReportStatus(commonality.getReportStatus());
        entity.setBelongArea(commonality.getBelongArea());
        entity.setOwerUserId(commonality.getOwerUserId());
        entity.setPageSize(Utils.pageSize);
        entity.setCurrentPageIndex(pageIndex);
        entity.setKeyWord(commonality.getKeyWord());
        entity.setOrder(commonality.getOrder());
        entity.setCurrenttype(commonality.getCurrenttype());
        entity.setDayType(1);
        return entity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.layout_search_result:
                String result = edtSearchResult.getText().toString().trim();
                if (Utils.isStringEmpty(result)) {
                    commonality.setKeyWord("");
                } else {
                    commonality.setKeyWord(result);
                }
                pageIndex = 1;
                presenter.start();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
