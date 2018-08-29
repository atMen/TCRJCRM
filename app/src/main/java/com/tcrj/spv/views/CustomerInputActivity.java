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
import com.tcrj.spv.callback.CustomerCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.CustomerEntity;
import com.tcrj.spv.model.CustomerStateEntity;
import com.tcrj.spv.model.CustomerTypeEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.CustomerPresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogCustomerState;
import com.tcrj.spv.views.dialog.DialogCustomerType;
import com.tcrj.spv.views.dialog.DialogIntentProduct;
import com.tcrj.spv.views.dialog.DialogProvinceCityArea;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

/**
 * 客户录入
 */
public class CustomerInputActivity extends BaseActivity implements View.OnClickListener, CustomerCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private EditText edtCustomerName;
    private LinearLayout layoutCustomerType;
    private TextView tvCustomerType;
    private LinearLayout layoutCustomerArea;
    private TextView tvCustomerArea;
    private EditText tvCustomerAddress;
    private LinearLayout layoutCustomerState;
    private TextView tvCustomerState;
    private RadioGroup rgpRadioChecked;
    private RadioButton rbtRadioCheck;
    private RadioButton rbtRadioUncheck;
    private LinearLayout layoutCustomerIntent;
    private TextView tvCustomerIntent;
    private Button btnCustomerSubmit;
    private CustomerCallBack.Presenter presenter;
    private CommonalityEntity common;
    private CustomerDetailEntity.EntityBean entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinput);
        entity = (CustomerDetailEntity.EntityBean) getIntent().getSerializableExtra("entity");
        new CustomerPresenter(this);
        setCommon();
        initView();
        setData();
    }

    public void setCommon() {
        common = new CommonalityEntity();
        common.setType("1");
        common.setState("1");
        common.setIsHot("1");
        common.setProvinceId(0);
        common.setCityId(0);
        common.setAreaId(0);
        common.setCustomerID("0");
    }

    /**
     * 设置详情数据
     */
    private void setData() {
        if (entity != null) {
            common.setCustomerID(String.valueOf(entity.getCusId()));
            if (!Utils.isStringEmpty(entity.getPid())) {
                common.setProvinceId(Integer.parseInt(entity.getPid()));
            } else {
                common.setProvinceId(0);
            }
            if (!Utils.isStringEmpty(entity.getCid())) {
                common.setCityId(Integer.parseInt(entity.getCid()));
            } else {
                common.setCityId(0);
            }
            if (!Utils.isStringEmpty(entity.getAid())) {
                common.setAreaId(Integer.parseInt(entity.getAid()));
            } else {
                common.setAreaId(0);
            }
            common.setState(entity.getCustomerStatusId());
            edtCustomerName.setText(entity.getCusName());
            tvCustomerType.setText(entity.getCustomerClass());
            tvCustomerArea.setText(entity.getArea());
            tvCustomerAddress.setText(entity.getAddress());
            tvCustomerState.setText(entity.getCusStatus());
            if (entity.getIsHotCus().equals("否")) {
                rbtRadioUncheck.setChecked(true);
                rbtRadioCheck.setChecked(false);
                common.setIsHot("2");
            } else {
                rbtRadioUncheck.setChecked(false);
                rbtRadioCheck.setChecked(true);
                common.setIsHot("1");
            }
            tvCustomerIntent.setText(entity.getYxPro());
        }
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        edtCustomerName = (EditText) findViewById(R.id.edt_customer_name);
        layoutCustomerType = (LinearLayout) findViewById(R.id.layout_customer_type);
        tvCustomerType = (TextView) findViewById(R.id.tv_customer_type);
        layoutCustomerArea = (LinearLayout) findViewById(R.id.layout_customer_area);
        tvCustomerArea = (TextView) findViewById(R.id.tv_customer_area);
        tvCustomerAddress = (EditText) findViewById(R.id.tv_customer_address);
        layoutCustomerState = (LinearLayout) findViewById(R.id.layout_customer_state);
        tvCustomerState = (TextView) findViewById(R.id.tv_customer_state);
        rgpRadioChecked = (RadioGroup) findViewById(R.id.rgp_radio_checked);
        rbtRadioCheck = (RadioButton) findViewById(R.id.rbt_radio_check);
        rbtRadioUncheck = (RadioButton) findViewById(R.id.rbt_radio_uncheck);
        layoutCustomerIntent = (LinearLayout) findViewById(R.id.layout_customer_intent);
        tvCustomerIntent = (TextView) findViewById(R.id.tv_customer_intent);
        btnCustomerSubmit = (Button) findViewById(R.id.btn_customer_submit);
        tvTitle.setText("客户录入");
        imgBack.setOnClickListener(this);
        btnCustomerSubmit.setOnClickListener(this);
        layoutCustomerType.setOnClickListener(this);
        layoutCustomerArea.setOnClickListener(this);
        layoutCustomerState.setOnClickListener(this);
        layoutCustomerIntent.setOnClickListener(this);
        rgpRadioChecked.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_radio_check:
                        common.setIsHot("1");
                        break;
                    case R.id.rbt_radio_uncheck:
                        common.setIsHot("0");
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_customer_submit:
                String name = edtCustomerName.getText().toString().trim();
                String area = tvCustomerArea.getText().toString().trim();
                String address = tvCustomerAddress.getText().toString().trim();
                if (Utils.isStringEmpty(name)) {
                    displayToast("请输入客户名称");
                    return;
                }
                if (Utils.isStringEmpty(area)) {
                    displayToast("请选择所属地区");
                    return;
                }
                if (Utils.isStringEmpty(address)) {
                    displayToast("请输入详细地址");
                    return;
                }
                presenter.start();
                break;
            case R.id.layout_customer_type:
                DialogCustomerType type = DialogCustomerType.getInstance(this);
                type.setDuration(700);
                type.setEffect(Effectstype.Slidetop);
                type.setOnItemClickListener(new DialogCustomerType.ICustomerTypeCallBack() {
                    @Override
                    public void setOnItemListener(CustomerTypeEntity.ListInfoBean entity) {
                        tvCustomerType.setText(entity.getCustomerClassName());
                        common.setType(String.valueOf(entity.getCustomerClassID()));
                    }
                });
                type.show();
                break;
            case R.id.layout_customer_area:
                DialogProvinceCityArea areas = DialogProvinceCityArea.getInstance(this);
                areas.setDuration(700);
                areas.setOnItemClickListener(new DialogProvinceCityArea.IProvinceCityAreaCallBack() {
                    @Override
                    public void setOnItemListener(CommonalityEntity entity) {
                        tvCustomerArea.setText(entity.getProvinceName() + "-" + entity.getCityName() + "-" + entity.getAreaName());
                        Log.e("TAG",entity.getProvinceId() + "-" + entity.getCityId() + "-" + entity.getAreaId());
                        Log.e("TAG",entity.getProvinceName() + "-" + entity.getCityName() + "-" + entity.getAreaName());
                        common.setProvinceId(entity.getProvinceId());
                        common.setCityId(entity.getCityId());
                        common.setAreaId(entity.getAreaId());
                    }
                });
                areas.setEffect(Effectstype.Slidetop);
                areas.show();
                break;
            case R.id.layout_customer_state:
                DialogCustomerState state = DialogCustomerState.getInstance(this);
                state.setDuration(700);
                state.setEffect(Effectstype.Slidetop);
                state.setOnItemClickListener(new DialogCustomerState.ICustomerStateCallBack() {
                    @Override
                    public void setOnItemListener(CustomerStateEntity entity) {
                        tvCustomerState.setText(entity.getStateName());
                        common.setState(String.valueOf(entity.getStateId()));
                    }
                });
                state.show();
                break;
            case R.id.layout_customer_intent:
                DialogIntentProduct intent = DialogIntentProduct.getInstance(this);
                intent.setDuration(700);
                intent.setEffect(Effectstype.Slidetop);
                intent.setOnItemClickListener(new DialogIntentProduct.IProductCallBack() {
                    @Override
                    public void setOnClickListener(String value) {
                        tvCustomerIntent.setText(value.replace(",", "\n\r").toString());
                    }
                });
                intent.show();
                break;
        }
    }

    @Override
    public void setPresenter(CustomerCallBack.Presenter presenter) {
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
    public void getData(final CustomerEntity entity) {
        LogUtils.info("TAG",entity);
        if (entity != null) {
            if (entity.getState() == 1) {
//                final MessageDialogBuilder message = MessageDialogBuilder.getInstance(this);
//                message.setDuration(700);
//                message.setEffect(Effectstype.Slidetop);
//                message.setTitles("客户新增成功");
//                message.setContents("是否为该客户继续添加联系人");
//                message.setCancel("否，谢谢");
//                message.setSubmit("添加联系人");
//                message.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
//                    @Override
//                    public void setSubmitListener() {
//                        Intent intent = new Intent(CustomerInputActivity.this, ContactInputActivity.class);
//                        intent.putExtra("CustomerId", entity.getEntity().getEntity().getCusId());
//                        intent.putExtra("CustomerName", entity.getEntity().getEntity().getCusName());
//                        startActivity(intent);
//                        message.dismiss();
//                        finish();
//                    }
//
//                    @Override
//                    public void setCancelListener() {
//                        message.dismiss();
//                        finish();
//                    }
//                });
//                message.show();
                displayToast("客户信息修改成功");
                finish();
            } else {
                displayToast(entity.getMsg());
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        String customerName = edtCustomerName.getText().toString().trim();
        String address = tvCustomerAddress.getText().toString().trim();
        String product = tvCustomerIntent.getText().toString().trim();
        ParameterEntity entity = new ParameterEntity();
        entity.setCustomerName(customerName);
        entity.setCustomerClass(common.getType());
        entity.setProvince(common.getProvinceId());
        entity.setCity(common.getCityId());
        entity.setArea(common.getAreaId());
        entity.setAddress(address);
        entity.setCustomerStatus(common.getState());
        entity.setIntentionProducts(product);
        entity.setWriteUserID(String.valueOf(user.getEntity().getUserId()));
        entity.setIsHot(common.getIsHot());
        entity.setCustomerID(common.getCustomerID());
        LogUtils.info("CustomerInputActivity", entity);
        return entity;
    }
}
