package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.VisitRecordCallBack;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.VisitRecordEntity;
import com.tcrj.spv.presenter.VisitRecordPresenter;
import com.tcrj.spv.views.adapter.VisitRecordAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;

/**
 * 拜访记录
 */
public class VisitRecordActivity extends BaseActivity implements View.OnClickListener, VisitRecordCallBack.View {
    private VisitRecordCallBack.Presenter presenter;
    private ImageButton imgBack;
    private TextView tvTitle;
    private VisitRecordAdapter adapter;
    private ExpandableListView listView;

    private TextView tvSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitrecord);
        new VisitRecordPresenter(this);
        initView();
        presenter.start();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        listView = (ExpandableListView) findViewById(R.id.visit_recordlistview);

        tvSubTitle = (TextView) findViewById(R.id.tv_more);
        imgBack.setOnClickListener(this);
        tvSubTitle.setOnClickListener(this);
        tvTitle.setText("拜访记录");
        tvSubTitle.setVisibility(View.VISIBLE);
        tvSubTitle.setText("签到");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_more:
                Intent intent = new Intent(VisitRecordActivity.this, VisitSignActivity.class);
//                Intent intent = new Intent(VisitRecordActivity.this, SignMineCustomerActivity.class);
                startActivityForResult(intent,1);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(VisitRecordCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.start();
        Log.e("ATG","回调");
    }

    @Override
    public void LoadingOn() {
        showProgressDialog("正在加载..");
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();

    }

    @Override
    public void getData(VisitRecordEntity entity) {
        if (entity != null) {
            adapter = new VisitRecordAdapter(this);
            adapter.setData(entity.getListInfo());
            listView.setGroupIndicator(null);
            listView.setAdapter(adapter);
            listView.expandGroup(0);
            adapter.setOnClickListener(new VisitRecordAdapter.IonClickCallBack() {
                @Override
                public void setVisitListener(double latting, double longlation, String signPlace) {

                }

                @Override
                public void setCustomerListener(int g, int c) {
                    VisitRecordEntity.ListInfoBean.ListtVisitRecordsBean entity = adapter.getChild(g, c);
                    Intent intent = new Intent(VisitRecordActivity.this, CustomerDetailActivity.class);
                    intent.putExtra("CustomerID", entity.getCustomerId());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public int StaffId() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        return entity.getEntity().getUserId();
    }
}
