package com.tcrj.spv.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.SignRecordCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SignRecordEntity;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.SignRecordPresenter;
import com.tcrj.spv.views.adapter.SignRecordAdapter;
import com.tcrj.spv.views.adapter.SpinnerDateAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 签到记录
 */
public class SignRecordActivity extends BaseActivity implements SignRecordCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private SignRecordCallBack.Presenter presenter;
    private RecyclerView recyclerView;
    private SignRecordAdapter adapter;

    private Spinner subordinate_bumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signrecord);
        new SignRecordPresenter(this);
        initView();
        SpinnerLoad();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void initView() {
        subordinate_bumen = (Spinner) findViewById(R.id.subordinate_time);
        subordinate_bumen.setFocusable(false);

        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_signrecord);
        tvTitle.setText("签到记录");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<SpinnerEntity> dateList;
    private void SpinnerLoad() {
        dateList = new ArrayList<>();
        CharSequence[] dates = this.getResources().getStringArray(R.array.qd_date);
        for (int i = 0; i < dates.length; i++) {
            SpinnerEntity dateentity = new SpinnerEntity();
            dateentity.setId(i);
            dateentity.setSpinnerName(dates[i].toString());
            dateentity.setCityId("0");
            dateList.add(dateentity);
        }
        setdate_Spinner(dateList);
    }

    private SpinnerDateAdapter Dadapter = null;
    public void setdate_Spinner(List<SpinnerEntity> r_Spinner) {

        Dadapter = new SpinnerDateAdapter(this);
        Dadapter.setData(r_Spinner);
        subordinate_bumen.setAdapter(Dadapter);

        subordinate_bumen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntity entity = (SpinnerEntity) Dadapter.getItem(position);

                time = entity.getId()+"";
                presenter.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setPresenter(SignRecordCallBack.Presenter presenter) {
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
    public void getData(SignRecordEntity entity) {
        List<SignRecordEntity.KaoQinListBean> dataList = entity.getKaoQinList();
        if (!Utils.isStringEmpty(dataList)) {
            recyclerView.setHasFixedSize(true);
            adapter = new SignRecordAdapter(this);
            LinearLayoutManager manager = new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return true;
                }
            };
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            adapter.setData(dataList);
            recyclerView.setAdapter(adapter);
        }
    }

    private String time = "0";
    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity entity = BaseApplication.getUserInfoEntity();
        ParameterEntity params = new ParameterEntity();
        params.setStaffId(entity.getEntity().getUserId());
        params.setStaffNo(entity.getEntity().getUserTitle());
        params.setLastMouth(time);
        return params;
    }
}
