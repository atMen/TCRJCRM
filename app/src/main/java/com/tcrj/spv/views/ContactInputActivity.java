package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.ContactCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.ContactPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogDatePicker;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.Utils;

/**
 * 录入联系人信息
 */
public class ContactInputActivity extends BaseActivity implements View.OnClickListener, ContactCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView edtCustomername;
    private EditText edtContactName;
    private TextView tvContactBirthday;
    private EditText edtContactJob;
    private EditText edtContactPhone;
    private EditText edtContactTele;
    private EditText edtContactEmail;
    private EditText edtContactChat;
    private RadioGroup rgpRadioChecked;
    private RadioButton rbtRadioCheck;
    private RadioButton rbtRadioUncheck;
    private Button btnContactSubmit;
    private LinearLayout layoutBirthday;
    private ContactCallBack.Presenter presenter = null;
    private CommonalityEntity commonality = null;
    private String customerId;
    private String customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinput);
        new ContactPresenter(this);
        initView();
        setCommonality();
    }

    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setSex("0");
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        customerId = getIntent().getStringExtra("CustomerId");
        customerName = getIntent().getStringExtra("CustomerName");
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        edtCustomername = (TextView) findViewById(R.id.edt_contact_customername);
        edtContactName = (EditText) findViewById(R.id.edt_contact_name);
        tvContactBirthday = (TextView) findViewById(R.id.tv_contact_birthday);
        edtContactJob = (EditText) findViewById(R.id.edt_contact_job);
        edtContactPhone = (EditText) findViewById(R.id.edt_contact_phone);
        edtContactTele = (EditText) findViewById(R.id.edt_contact_tele);
        edtContactEmail = (EditText) findViewById(R.id.edt_contact_email);
        edtContactChat = (EditText) findViewById(R.id.edt_contact_chat);
        rgpRadioChecked = (RadioGroup) findViewById(R.id.rgp_radio_checked);
        rbtRadioCheck = (RadioButton) findViewById(R.id.rbt_radio_check);
        rbtRadioUncheck = (RadioButton) findViewById(R.id.rbt_radio_uncheck);
        btnContactSubmit = (Button) findViewById(R.id.btn_contact_submit);
        layoutBirthday = (LinearLayout) findViewById(R.id.layout_contact_birthday);
        tvTitle.setText("录入联系人");
        edtCustomername.setText(customerName);
        rbtRadioCheck.setText("女");
        rbtRadioUncheck.setText("男");
        imgBack.setOnClickListener(this);
        btnContactSubmit.setOnClickListener(this);
        layoutBirthday.setOnClickListener(this);
        rgpRadioChecked.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_radio_check:
                        commonality.setSex("0");
                        break;
                    case R.id.rbt_radio_uncheck:
                        commonality.setSex("1");
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_contact_submit:
                String name = edtContactName.getText().toString().trim();
                String job = edtContactJob.getText().toString().trim();
                String phone = edtContactPhone.getText().toString().trim();
                if (Utils.isStringEmpty(name)) {
                    displayToast("联系人姓名不能为空");
                    return;
                }
                if (Utils.isStringEmpty(job)) {
                    displayToast("职务不能为空");
                    return;
                }

                Log.e("TAG","判空："+Utils.isStringEmpty(phone)+"length:"+phone.length());
                if (Utils.isStringEmpty(phone) || phone.length() != 11) {
                    displayToast("请正确填写手机号码");
                    return;
                }
                presenter.start();
                break;
            case R.id.layout_contact_birthday:
                DialogDatePicker picker = new DialogDatePicker(this);
                picker.onDatePickerListener(new DialogDatePicker.IDatePickerCallBack() {
                    @Override
                    public void onClickListener(String time) {
                        tvContactBirthday.setText(time);
                    }
                });
                picker.show();
                break;
        }
    }

    @Override
    public void setPresenter(ContactCallBack.Presenter presenter) {
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
    public void getData(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                final MessageDialogBuilder message = MessageDialogBuilder.getInstance(this);
                message.setDuration(700);
                message.setEffect(Effectstype.Slidetop);
                message.setTitles("添加成功");
                message.setContents("是否继续添加联系人");
                message.setCancel("否，谢谢");
                message.setSubmit("继续添加");
                message.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        edtContactName.setText("");
                        tvContactBirthday.setText("");
                        edtContactJob.setText("");
                        edtContactPhone.setText("");
                        edtContactTele.setText("");
                        edtContactEmail.setText("");
                        edtContactChat.setText("");
                        message.dismiss();
                    }

                    @Override
                    public void setCancelListener() {
                        message.dismiss();
                        finish();
                    }
                });
                message.show();
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        String contactName = edtContactName.getText().toString().trim();
        String birthday = tvContactBirthday.getText().toString().trim();
        String job = edtContactJob.getText().toString().trim();
        String phone = edtContactPhone.getText().toString().trim();
        String tele = edtContactTele.getText().toString().trim();
        String email = edtContactEmail.getText().toString().trim();
        String chart = edtContactChat.getText().toString().trim();
        entity.setCustomerID(customerId);
        entity.setContactName(contactName);
        entity.setContactSex(commonality.getSex());
        entity.setPosition(job);
        entity.setMobileNumber(phone);
        entity.setPhoneNumber(tele);
        entity.setMSNQQ(chart);
        entity.setEmail(email);
        if (Utils.isStringEmpty(birthday)) {
            entity.setBirthDay("1900-01-01 00:00:00");
        } else {
            entity.setBirthDay(birthday);
        }
        entity.setWriteUserID(String.valueOf(user.getEntity().getUserId()));
        entity.setContactID(0);
        return entity;
    }
}
