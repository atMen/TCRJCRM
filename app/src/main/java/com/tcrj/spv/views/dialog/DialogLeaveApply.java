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
import com.tcrj.spv.callback.LeaveTypeCallBack;
import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.presenter.LeaveTypePresenter;
import com.tcrj.spv.views.adapter.LeaveApplyAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.LogUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类别
 * Created by leict on 2017/11/16.
 */

public class DialogLeaveApply extends Dialog implements DialogInterface, LeaveTypeCallBack.View {
    private RelativeLayout relativeMain;
    private TextView tvTitleName;
    private ImageView spinnerClose;
    private ListView listView;
    private ILeaveApplyCallBack callBack;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private boolean isCancelable = true;
    private static DialogLeaveApply instance;
    private LeaveApplyAdapter adapter;
    private Context context;
    private LeaveTypeCallBack.Presenter presenter;

    public DialogLeaveApply(Context context) {
        super(context, R.style.dialog_untran);
        this.context = context;
        new LeaveTypePresenter(DialogLeaveApply.this);
        initView(context);
        presenter.start();
    }

    public static DialogLeaveApply getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogLeaveApply(context);
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
        spinnerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
                LeaveApplyEntity entity = (LeaveApplyEntity) adapter.getItem(position);
                if (callBack != null) {
                    callBack.setOnItemListener(entity.getTypeName());
                    dismiss();
                }
            }
        });
    }

    public DialogLeaveApply setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogLeaveApply setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public DialogLeaveApply setTitleName(int textResId) {
        tvTitleName.setText(textResId);
        return this;
    }

    public DialogLeaveApply setTitleName(CharSequence msg) {
        tvTitleName.setText(msg);
        return this;
    }

    public DialogLeaveApply isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public DialogLeaveApply isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeMain);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void setPresenter(LeaveTypeCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) context).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) context).dismisProgressDialog();
    }

    @Override
    public void getData(LeaveApplyEntity entity) {
        List<String> dataList = entity.getLeavType();
        if (!Utils.isStringEmpty(entity.getLeavType())) {
            List<LeaveApplyEntity> itemList = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                LeaveApplyEntity leave = new LeaveApplyEntity();
                LogUtils.info("FFFFFFF", dataList.get(i));
                leave.setTypeName(dataList.get(i));
                itemList.add(leave);
            }
            adapter = new LeaveApplyAdapter(context);
            adapter.setData(itemList);
            listView.setAdapter(adapter);
        }
    }

    public void setOnItemClickListener(ILeaveApplyCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ILeaveApplyCallBack {
        void setOnItemListener(String typeName);
    }
}