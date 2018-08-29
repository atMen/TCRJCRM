package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerTypeCallBack;
import com.tcrj.spv.model.CustomerTypeEntity;
import com.tcrj.spv.presenter.CustomerTypePresenter;
import com.tcrj.spv.views.adapter.CustomerTypeAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

/**
 * 客户分类
 * Created by leict on 2017/11/22.
 */

public class DialogCustomerType extends Dialog implements DialogInterface, CustomerTypeCallBack.View {
    private RelativeLayout relativeMain;
    private TextView tvTitleName;
    private ImageView spinnerClose;
    private ListView listView;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private static DialogCustomerType instance;
    private CustomerTypeCallBack.Presenter presenter;
    private CustomerTypeAdapter adapter;
    private ICustomerTypeCallBack callBack = null;

    public DialogCustomerType(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        new CustomerTypePresenter(DialogCustomerType.this);
        initView(context);
        presenter.start();
    }

    public static DialogCustomerType getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogCustomerType(context);
                }
            }
        }
        mContext = context;
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();  // 获取对话框当前的参数值
        p.width = (int) (Utils.getWidth(mContext) * 0.8);            // 宽度设置为屏幕的0.8
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
    }

    private void initView(Context context) {
        view = View.inflate(context, R.layout.dialog_spinner_leaveapply, null);
        relativeMain = (RelativeLayout) view.findViewById(R.id.relative_main);
        tvTitleName = (TextView) view.findViewById(R.id.spinner_title);
        spinnerClose = (ImageView) view.findViewById(R.id.spinner_close);
        listView = (ListView) view.findViewById(R.id.spinner_listview);
        this.setCanceledOnTouchOutside(false);
        setContentView(view);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);
            }
        });
        tvTitleName.setText("请选择客户分类");
        spinnerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeMain);
    }

    public DialogCustomerType setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogCustomerType setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    @Override
    public void setPresenter(CustomerTypeCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) mContext).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) mContext).dismisProgressDialog();
    }

    @Override
    public void getData(CustomerTypeEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getListInfo())) {
                adapter = new CustomerTypeAdapter(mContext);
                adapter.setData(entity.getListInfo());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        adapter.setSelectItem(position);
                        CustomerTypeEntity.ListInfoBean entity = (CustomerTypeEntity.ListInfoBean) adapter.getItem(position);
                        if (callBack != null) {
                            callBack.setOnItemListener(entity);
                            dismiss();
                        }
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(ICustomerTypeCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ICustomerTypeCallBack {
        void setOnItemListener(CustomerTypeEntity.ListInfoBean entity);
    }
}
