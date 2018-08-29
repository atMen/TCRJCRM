package com.tcrj.spv.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerDeleteCallBack;
import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.CustomerDeletePresenter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;

/**
 * 客户详情
 * Created by leict on 2017/11/27.
 */

@SuppressLint("ValidFragment")
public class CustomerDetailFragment extends Fragment implements View.OnClickListener, CustomerDeleteCallBack.View {
    private View view;
    private TextView tvName;
    private TextView tvType;
    private TextView tvArea;
    private TextView tvAddress;
    private TextView tvState;
    private TextView tvIshot;
    private TextView tvIntent;
    private TextView tvPerson;
    private TextView tvDatetime;
    private Button btnDelete;
    private String cusId;
    private CustomerDeleteCallBack.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_customer_details, container, false);
        new CustomerDeletePresenter(CustomerDetailFragment.this, 0);
        initView(view);
        presenter.start();
        return view;
    }

    public CustomerDetailFragment(String cusId) {
        this.cusId = cusId;
    }

    /**
     * 刷新数据
     */
    public void refresh() {
        presenter.start();
    }

    /**
     * 获取页面控件
     */
    private void initView(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_customer_details_name);
        tvType = (TextView) view.findViewById(R.id.tv_customer_details_type);
        tvArea = (TextView) view.findViewById(R.id.tv_customer_details_area);
        tvAddress = (TextView) view.findViewById(R.id.tv_customer_details_address);
        tvState = (TextView) view.findViewById(R.id.tv_customer_details_state);
        tvIshot = (TextView) view.findViewById(R.id.tv_customer_details_ishot);
        tvIntent = (TextView) view.findViewById(R.id.tv_customer_details_intent);
        tvPerson = (TextView) view.findViewById(R.id.tv_customer_details_person);
        tvDatetime = (TextView) view.findViewById(R.id.tv_customer_details_datetime);
        btnDelete = (Button) view.findViewById(R.id.btn_customer_delete);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_customer_delete:
                new CustomerDeletePresenter(CustomerDetailFragment.this, 1);
                final MessageDialogBuilder builder = MessageDialogBuilder.getInstance(getContext());
                builder.setTitles("删除客户");
                builder.setContents("确定删除此客户吗？");
                builder.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        presenter.start();
                        Intent intent = new Intent();
                        ((BaseActivity) getContext()).setResult(2, intent);
                        builder.dismiss();
                        ((BaseActivity) getContext()).finish();
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
    public void LoadingOn() {
        ((BaseActivity) getContext()).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) getContext()).dismisProgressDialog();
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Override
    public void altered(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                ((BaseActivity) getContext()).displayToast(entity.getMsg());
            } else {
                ((BaseActivity) getContext()).displayToast(entity.getMsg());
            }
        }
    }

    /**
     * 获取数据
     *
     * @param entity
     */
    @Override
    public void getData(CustomerDetailEntity entity) {
        if (entity != null) {
            tvName.setText(entity.getEntity().getCusName());
            tvType.setText(entity.getEntity().getCustomerClass());
            tvArea.setText(entity.getEntity().getArea());
            tvAddress.setText(entity.getEntity().getAddress());
            tvState.setText(entity.getEntity().getCusStatus());
            tvIshot.setText(entity.getEntity().getIsHotCus());
            tvIntent.setText(entity.getEntity().getYxPro());
//            tvPerson.setText(entity.getEntity().getOwerName() + "(" + entity.getEntity().getOwerTel() + ")");
            tvPerson.setText(entity.getEntity().getOwerName());

            tvDatetime.setText(entity.getEntity().getUpdateTime());
        }
    }

    /**
     * 设置参数
     *
     * @return
     */
    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setCusId(cusId);
        entity.setUserId(user.getEntity().getUserId());
        entity.setCId(cusId);
        return entity;
    }

    @Override
    public void setPresenter(CustomerDeleteCallBack.Presenter presenter) {
        this.presenter = presenter;
    }
}
