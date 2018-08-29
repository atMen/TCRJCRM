package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

/**
 * Loading加载
 * Created by leict on 2017/10/25.
 */

public class DialogLoading extends Dialog implements DialogInterface {
    private RelativeLayout relativeMain;
    private TextView tvMessage;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private boolean isCancelable = true;
    private static DialogLoading instance;

    public DialogLoading(Context context) {
        super(context, R.style.dialog_untran);
        initView(context);
    }

    public static DialogLoading getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (DialogLoading.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogLoading(context);
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
        p.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(p);
    }

    private void initView(Context context) {
        view = View.inflate(context, R.layout.dialog_loading, null);
        relativeMain = (RelativeLayout) view.findViewById(R.id.relative_loading);
        tvMessage = (TextView) view.findViewById(R.id.tv_loading);
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
    }

    public DialogLoading setMessage(int textResId) {
        tvMessage.setText(textResId);
        return this;
    }

    public DialogLoading setMessage(CharSequence msg) {
        tvMessage.setText(msg);
        return this;
    }

    public DialogLoading setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogLoading setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public DialogLoading isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public DialogLoading isCancelable(boolean cancelable) {
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
}
