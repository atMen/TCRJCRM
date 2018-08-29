package com.tcrj.spv.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CommunicationCallBack;
import com.tcrj.spv.model.CommunicationEntity;
import com.tcrj.spv.presenter.CommunicationPresenter;
import com.tcrj.spv.views.application.BaseActivity;

/**
 * 通讯录：详情
 */
public class CommunicationDetailActvity extends BaseActivity implements View.OnClickListener, CommunicationCallBack.View {
    private CommunicationCallBack.Presenter presenter;
    private String staffId;
    private TextView tvPersonName;
    private TextView tvPersonSex;
    private TextView tvPersonBirthday;
    private TextView tvPersonDepart;
    private TextView tvPersonJob;
    private TextView tvPersonAddress;
    private TextView tvPersonEmail;
    private TextView tvPersonTele;
//    private TextView tvAddressName;
    private TextView tvPersonPhone;
    private LinearLayout layoutPhone;
    private LinearLayout layoutTele;
//    private Toolbar toolbar;

    private ImageButton imgBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicationdetail);
        new CommunicationPresenter(this);
        initView();
        presenter.start();
    }

    @Override
    public void initView() {
        staffId = getIntent().getStringExtra("StaffID");

        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("通讯录详情");
        imgBack.setOnClickListener(this);

        layoutPhone = (LinearLayout) findViewById(R.id.layout_person_phone);
        layoutTele = (LinearLayout) findViewById(R.id.layout_person_tele);
        tvPersonName = (TextView) findViewById(R.id.tv_person_name);
        tvPersonSex = (TextView) findViewById(R.id.tv_person_sex);
        tvPersonBirthday = (TextView) findViewById(R.id.tv_person_birthday);
        tvPersonDepart = (TextView) findViewById(R.id.tv_person_depart);
        tvPersonJob = (TextView) findViewById(R.id.tv_person_job);
//        tvAddressName = (TextView) findViewById(R.id.tv_address_name);
        tvPersonAddress = (TextView) findViewById(R.id.tv_person_address);
        tvPersonEmail = (TextView) findViewById(R.id.tv_person_email);
        tvPersonTele = (TextView) findViewById(R.id.tv_person_tele);
        tvPersonPhone = (TextView) findViewById(R.id.tv_person_phone);
//        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        layoutTele.setOnClickListener(this);
        layoutPhone.setOnClickListener(this);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    public void setPresenter(CommunicationCallBack.Presenter presenter) {
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
    public void getData(CommunicationEntity entity) {
        if (entity != null) {
            tvPersonName.setText(entity.getEntity().getStaffName());
            tvPersonSex.setText(entity.getEntity().getStaffSex());
            tvPersonBirthday.setText(entity.getEntity().getBirthday());
            tvPersonDepart.setText(entity.getEntity().getDeptName());
            tvPersonJob.setText(entity.getEntity().getRoleName());
            tvPersonAddress.setText(entity.getEntity().getAddress());
            tvPersonEmail.setText(entity.getEntity().getEmail());
            tvPersonTele.setText(entity.getEntity().getTelNo());
            tvPersonPhone.setText(entity.getEntity().getMoblieTel());
//            tvAddressName.setText(entity.getEntity().getStaffName());
        }
    }

    @Override
    public String staffId() {
        return staffId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_person_phone:
//                displayToast("敬请期待");
                //进入拨打电话页面
                String trim = tvPersonPhone.getText().toString().trim();

                if(!trim.equals("暂无号码")){
                    call(trim);
                }

                break;
            case R.id.layout_person_tele:
                displayToast("敬请期待");
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
