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
import com.tcrj.spv.model.CustomerStateEntity;
import com.tcrj.spv.views.adapter.CustomerStateAdapter;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户状态
 * Created by leict on 2017/11/22.
 */

public class DialogCustomerState extends Dialog implements DialogInterface {
    private RelativeLayout relativeMain;
    private TextView tvTitleName;
    private ImageView spinnerClose;
    private ListView listView;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private static DialogCustomerState instance;
    private CustomerStateAdapter adapter;
    private ICustomerStateCallBack callBack = null;

    public DialogCustomerState(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        initView(context);
    }

    public static DialogCustomerState getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogCustomerState(context);
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
        tvTitleName.setText("请选择客户状态");
        spinnerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        loadData();
    }

    /**
     * 客户状态数据源
     */
    private void loadData() {
        List<CustomerStateEntity> dataList = new ArrayList<>();
        CharSequence[] arrayList = mContext.getResources().getStringArray(R.array.customer_state);
        for (int i = 0; i < arrayList.length; i++) {
            CustomerStateEntity entity = new CustomerStateEntity();
            entity.setStateId(i + 1);
            entity.setStateName(arrayList[i].toString());
            dataList.add(entity);
        }
        adapter = new CustomerStateAdapter(mContext);
        adapter.setData(dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
                CustomerStateEntity entity = (CustomerStateEntity) adapter.getItem(position);
                if (callBack != null) {
                    callBack.setOnItemListener(entity);
                    dismiss();
                }
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

    public DialogCustomerState setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogCustomerState setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public void setOnItemClickListener(ICustomerStateCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ICustomerStateCallBack {
        void setOnItemListener(CustomerStateEntity entity);
    }
}
