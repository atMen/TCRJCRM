package com.tcrj.spv.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.FollowAddCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.TracedMaturityEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.FollowAddPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogContactList;
import com.tcrj.spv.views.dialog.DialogContactWay;
import com.tcrj.spv.views.dialog.DialogDateTimePicker;
import com.tcrj.spv.views.dialog.DialogFollowState;
import com.tcrj.spv.views.dialog.DialogTracedList;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加跟进
 */
public class FollowAddActivity extends BaseActivity implements View.OnClickListener, FollowAddCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvFollowCustomer;
    private LinearLayout layoutFollowContact;
    private TextView tvFollowContact;
    private TextView tvFollowPosition;
    private LinearLayout layoutFollowDatetime;
    private TextView tvFollowDatetime;
    private LinearLayout layoutFollowPhone;
    private TextView tvFollowPhone;
    private LinearLayout layoutFollowStatus;
    private TextView tvFollowStatus;
    private LinearLayout layoutFollowMaturity;
    private TextView tvFollowMaturity;
    private EditText edtFollowRecord;
    private Button btnFollowSubmit;
    private String customerId;
    private String customerName;
    private LinearLayout layoutLocation;
    private LinearLayout layoutFollowLocation;
    private TextView tvFollowLocation;

    private FollowAddCallBack.Presenter presenter;
    private CommonalityEntity commonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followadd);
        customerId = getIntent().getStringExtra("CustomerId");
        customerName = getIntent().getStringExtra("CustomerName");
        setCommonality();
        initView();
    }

    /**
     * 参数
     */
    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setStatus("0");
        commonality.setVisId("0");
        commonality.setContactID(0);
        commonality.setTracedMaturity("0");
        commonality.setContactType("0");
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvFollowCustomer = (TextView) findViewById(R.id.tv_follow_customer);
        layoutFollowContact = (LinearLayout) findViewById(R.id.layout_follow_contact);
        tvFollowContact = (TextView) findViewById(R.id.tv_follow_contact);
        tvFollowPosition = (TextView) findViewById(R.id.tv_follow_position);
        layoutFollowDatetime = (LinearLayout) findViewById(R.id.layout_follow_datetime);
        tvFollowDatetime = (TextView) findViewById(R.id.tv_follow_datetime);
        layoutFollowPhone = (LinearLayout) findViewById(R.id.layout_follow_phone);
        tvFollowPhone = (TextView) findViewById(R.id.tv_follow_phone);
        layoutFollowStatus = (LinearLayout) findViewById(R.id.layout_follow_status);
        tvFollowStatus = (TextView) findViewById(R.id.tv_follow_status);
        layoutFollowMaturity = (LinearLayout) findViewById(R.id.layout_follow_maturity);
        tvFollowMaturity = (TextView) findViewById(R.id.tv_follow_maturity);
        edtFollowRecord = (EditText) findViewById(R.id.edt_follow_record);
        btnFollowSubmit = (Button) findViewById(R.id.btn_follow_submit);
        layoutLocation = (LinearLayout) findViewById(R.id.layout_location);
        layoutFollowLocation = (LinearLayout) findViewById(R.id.layout_follow_location);
        tvFollowLocation = (TextView) findViewById(R.id.tv_follow_location);
        imgBack.setOnClickListener(this);
        layoutFollowContact.setOnClickListener(this);
        layoutFollowDatetime.setOnClickListener(this);
        layoutFollowMaturity.setOnClickListener(this);
        layoutFollowPhone.setOnClickListener(this);
        layoutFollowStatus.setOnClickListener(this);
        btnFollowSubmit.setOnClickListener(this);
        layoutFollowLocation.setOnClickListener(this);
        tvFollowCustomer.setText(customerName);
        tvTitle.setText("添加跟进");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_follow_contact:
                new FollowAddPresenter(this, 0);
                presenter.start();
                break;
            case R.id.layout_follow_datetime:
                DialogDateTimePicker picker = new DialogDateTimePicker(this);
                picker.onDatePickerListener(new DialogDateTimePicker.DatePickerCallBack() {
                    @Override
                    public void onClickListener(String time) {
                        tvFollowDatetime.setText(time);
                    }
                });
                picker.show();
                break;
            case R.id.layout_follow_maturity:
                new FollowAddPresenter(this, 1);
                presenter.start();
                break;
            case R.id.layout_follow_phone:
                getContactWay();
                break;
            case R.id.layout_follow_status:
                getFollowState();
                break;
            case R.id.layout_follow_location:
                //TODO:获取拜访签到信息

                break;
            case R.id.btn_follow_submit:
                String name = tvFollowCustomer.getText().toString().trim();
                String contact = tvFollowContact.getText().toString().trim();
                String datetime = tvFollowDatetime.getText().toString().trim();
                String way = tvFollowPhone.getText().toString().trim();
                String status = tvFollowStatus.getText().toString().trim();
                String maturity = tvFollowMaturity.getText().toString().trim();
                String record = edtFollowRecord.getText().toString().trim();
                if (Utils.isStringEmpty(name)) {
                    displayToast("客户名称不能为空");
                    return;
                }
                if (Utils.isStringEmpty(contact)) {
                    displayToast("联系人姓名不能为空");
                    return;
                }
                if (Utils.isStringEmpty(datetime)) {
                    displayToast("联系时间不能为空");
                    return;
                }
                if (Utils.isStringEmpty(way)) {
                    displayToast("请选择联系方式");
                    return;
                }
                if (Utils.isStringEmpty(status)) {
                    displayToast("请选择跟进状态");
                    return;
                }
                if (Utils.isStringEmpty(maturity)) {
                    displayToast("请选择客户成熟度");
                    return;
                }
                if (Utils.isStringEmpty(record)) {
                    displayToast("请填写跟进记录");
                    return;
                }
                new FollowAddPresenter(this, 2);
                presenter.start();
                break;
        }
    }

    /**
     * 联系方式
     */
    private void getContactWay() {
        List<PublicListEntity> dataList = new ArrayList<>();
        CharSequence[] array = this.getResources().getStringArray(R.array.contacts_way);
        for (int i = 0; i < array.length; i++) {
            PublicListEntity entity = new PublicListEntity();
            entity.setId(i + 1);
            entity.setpName(array[i].toString());
            dataList.add(entity);
        }
        DialogContactWay dialog = DialogContactWay.getInstance(this, dataList);
        dialog.setEffect(Effectstype.Slidetop);
        dialog.setDuration(700);
        dialog.setTitleName("选择联系方式");
        dialog.setOnItemClickListener(new DialogContactWay.IListViewCallBack() {
            @Override
            public void setOnClickListener(PublicListEntity entity) {
                tvFollowPhone.setText(entity.getpName());
                commonality.setContactType(String.valueOf(entity.getId()));
                if (entity.getpName().equals("登门拜访")) {
//                    layoutLocation.setVisibility(View.VISIBLE);
                } else {
                    layoutLocation.setVisibility(View.GONE);
                    commonality.setVisId("0");
                }
            }
        });
        dialog.show();
    }

    /**
     * 跟进状态
     */
    private void getFollowState() {
        List<PublicListEntity> dataList = new ArrayList<>();
        CharSequence[] array = this.getResources().getStringArray(R.array.follow_state);
        for (int i = 0; i < array.length; i++) {
            PublicListEntity entity = new PublicListEntity();
            entity.setId(i + 1);
            entity.setpName(array[i].toString());
            dataList.add(entity);
        }
        DialogFollowState dialog = DialogFollowState.getInstance(this, dataList);
        dialog.setEffect(Effectstype.Slidetop);
        dialog.setDuration(700);
        dialog.setTitleName("选择跟进状态");
        dialog.setOnItemClickListener(new DialogFollowState.IListViewCallBack() {
            @Override
            public void setOnClickListener(PublicListEntity entity) {
                tvFollowStatus.setText(entity.getpName());
                commonality.setStatus(String.valueOf(entity.getId()));
            }
        });
        dialog.show();
    }

    @Override
    public void setPresenter(FollowAddCallBack.Presenter presenter) {
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

    /**
     * 联系人
     *
     * @param entity
     */
    @Override
    public void loadContact(ContactListEntity entity) {
        List<PublicListEntity> dataList = new ArrayList<>();
        if (!Utils.isStringEmpty(entity.getListInfo())) {
            for (int i = 0; i < entity.getListInfo().size(); i++) {
                PublicListEntity pEntity = new PublicListEntity();
                pEntity.setId(entity.getListInfo().get(i).getContactID());
                pEntity.setpName(entity.getListInfo().get(i).getContactName());
                pEntity.setJob(entity.getListInfo().get(i).getPosition());
                dataList.add(pEntity);
            }
            final DialogContactList contact = DialogContactList.getInstance(this, dataList);
            contact.setEffect(Effectstype.Slidetop);
            contact.setDuration(700);
            contact.setTitleName("选择联系人");
            contact.setOnItemClickListener(new DialogContactList.IListViewCallBack() {
                @Override
                public void setOnClickListener(PublicListEntity entity) {
                    tvFollowContact.setText(entity.getpName());
                    tvFollowPosition.setText(entity.getJob());
                    commonality.setContactID(entity.getId());
                }
            });
            contact.show();
        }
    }

    /**
     * 客户成熟度
     *
     * @param entity
     */
    @Override
    public void loadTraced(TracedMaturityEntity entity) {
        List<PublicListEntity> dataList = new ArrayList<>();
        if (!Utils.isStringEmpty(entity.getListInfo())) {
            for (int i = 0; i < entity.getListInfo().size(); i++) {
                PublicListEntity pEntity = new PublicListEntity();
                pEntity.setId(entity.getListInfo().get(i).getMaturityID());
                pEntity.setpName(entity.getListInfo().get(i).getMaturityName());
                dataList.add(pEntity);
            }
            final DialogTracedList maturity = DialogTracedList.getInstance(this, dataList);
            maturity.setEffect(Effectstype.Slidetop);
            maturity.setDuration(700);
            maturity.setTitleName("选择客户成熟度");
            maturity.setOnItemClickListener(new DialogTracedList.IListViewCallBack() {
                @Override
                public void setOnClickListener(PublicListEntity entity) {
                    tvFollowMaturity.setText(entity.getpName());
                    commonality.setTracedMaturity(String.valueOf(entity.getId()));
                }
            });
            maturity.show();
        }
    }

    /**
     * 提交跟进记录
     *
     * @param entity
     */
    @Override
    public void save(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
                return;
            } else {
                displayToast(entity.getMsg());
                finish();
            }
        }
    }

    @Override
    public ParameterEntity getParameters() {
        String result = edtFollowRecord.getText().toString().trim();
        String datetime = tvFollowDatetime.getText().toString().trim();
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setContactDate(datetime);
        entity.setWriteUserID(String.valueOf(user.getEntity().getUserId()));
        entity.setStatus(commonality.getStatus());
        entity.setTracedResult(result);
        entity.setVisId(commonality.getVisId());
        entity.setCustomerID(customerId);
        entity.setContactID(commonality.getContactID());
        entity.setTracedMaturity(commonality.getTracedMaturity());
        entity.setContactType(commonality.getContactType());
        entity.setCTID(0);
        //客户成熟度
        entity.setCusId(customerId);
        entity.setUserId(user.getEntity().getUserId());
        entity.setKey("");
        return entity;
    }
}
