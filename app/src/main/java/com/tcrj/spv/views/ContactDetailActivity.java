package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.ContactDetailCallBack;
import com.tcrj.spv.model.ContactDetailEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.ContactDetailPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;

/**
 * 联系人详情
 */
public class ContactDetailActivity extends BaseActivity implements View.OnClickListener, ContactDetailCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvContactCustomer;
    private TextView tvContactName;
    private TextView tvContactSex;
    private TextView tvContactBorn;
    private TextView tvContactJob;
    private TextView tvContactPhone;
    private TextView tvContactTelephone;
    private TextView tvContactEmail;
    private TextView tvContactWebchat;
    private TextView tvContactPerson;
    private TextView tvContactDatetime;
    private Button btnContactDelete;
    private ContactDetailCallBack.Presenter presenter;
    private int cusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);
        cusId = getIntent().getIntExtra("CusID", -1);
        new ContactDetailPresenter(this, 0);
        initView();
        presenter.start();
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContactCustomer = (TextView) findViewById(R.id.tv_contact_customer);
        tvContactName = (TextView) findViewById(R.id.tv_contact_name);
        tvContactSex = (TextView) findViewById(R.id.tv_contact_sex);
        tvContactBorn = (TextView) findViewById(R.id.tv_contact_born);
        tvContactJob = (TextView) findViewById(R.id.tv_contact_job);
        tvContactPhone = (TextView) findViewById(R.id.tv_contact_phone);
        tvContactTelephone = (TextView) findViewById(R.id.tv_contact_telephone);
        tvContactEmail = (TextView) findViewById(R.id.tv_contact_email);
        tvContactWebchat = (TextView) findViewById(R.id.tv_contact_webchat);
        tvContactPerson = (TextView) findViewById(R.id.tv_contact_person);
        tvContactDatetime = (TextView) findViewById(R.id.tv_contact_datetime);
        btnContactDelete = (Button) findViewById(R.id.btn_contact_delete);
        tvTitle.setText("联系人详情");
        imgBack.setOnClickListener(this);
        btnContactDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_contact_delete:
                new ContactDetailPresenter(this, 1);
                final MessageDialogBuilder builder = MessageDialogBuilder.getInstance(this);
                builder.setTitles("删除联系人");
                builder.setContents("您确定删除此联系人吗？");
                builder.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        presenter.start();
                        Intent intent = new Intent();
                        setResult(2, intent);
                        builder.dismiss();
                    }

                    @Override
                    public void setCancelListener() {
                        builder.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void setPresenter(ContactDetailCallBack.Presenter presenter) {
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
    public void getData(ContactDetailEntity entity) {
        if (entity != null) {
            tvContactCustomer.setText(entity.getEntity().getCusName());
            tvContactName.setText(entity.getEntity().getCName());
            tvContactSex.setText(entity.getEntity().getSex());
            tvContactBorn.setText(entity.getEntity().getBrithday());
            tvContactJob.setText(entity.getEntity().getJob());
            tvContactPhone.setText(entity.getEntity().getTel());
            tvContactTelephone.setText(entity.getEntity().getZTel());
            tvContactEmail.setText(entity.getEntity().getEmail());
            tvContactWebchat.setText(entity.getEntity().getQq());
            tvContactPerson.setText(entity.getEntity().getStaffName());
            tvContactDatetime.setText(entity.getEntity().getUpdateTime());
        }
    }

    /**
     * 删除联系人
     *
     * @param entity
     */
    @Override
    public void altered(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                finish();
            }
        }
    }

    /**
     * 参数
     *
     * @return
     */
    @Override
    public int cusId() {
        return cusId;
    }
}
