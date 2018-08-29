package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PhoneListEntity;
import com.tcrj.spv.views.adapter.PhoneListAdapter;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

import java.util.List;

/**
 * 联系人电话
 * Created by leict on 2017/11/30.
 */

public class DialogAddressList extends Dialog implements DialogInterface {
    private View view;
    private static Context mContext;
    private static DialogAddressList instance;
    private ListView listView;
    private int mDuration = -1;
    private Effectstype type = null;
    private LinearLayout layoutPhone;
    private PhoneListAdapter adapter;
    private List<PhoneListEntity> dataList;
    private IPhoneListCallBack callBack;

    public DialogAddressList(Context context, List<PhoneListEntity> itemList) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        this.dataList = itemList;
        initView(context);
    }

    public static DialogAddressList getInstance(Context context, List<PhoneListEntity> dataList) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (DialogAddressList.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogAddressList(context, dataList);
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
        view = View.inflate(context, R.layout.dialog_listview, null);
        layoutPhone = (LinearLayout) view.findViewById(R.id.layout_phone);
        listView = (ListView) view.findViewById(R.id.phone_listview);
        this.setCanceledOnTouchOutside(true);
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
        initView();
    }

    private void initView() {
        adapter = new PhoneListAdapter(mContext);
        adapter.setData(dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneListEntity entity = (PhoneListEntity) adapter.getItem(position);
                if (callBack != null) {
                    callBack.setOnClickListener(entity.getPhoneCord());
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
        animator.start(layoutPhone);
    }

    public DialogAddressList setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogAddressList setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public void setOnItemClickListener(IPhoneListCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IPhoneListCallBack {
        void setOnClickListener(String number);
    }
}
