package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.views.adapter.PublicListAdapter;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

import java.util.List;

/**
 * 客户成熟度
 * Created by leict on 2017/12/1.
 */

public class DialogTracedList extends Dialog implements DialogInterface {
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private boolean isCancelable = true;
    private static DialogTracedList instance;
    private RelativeLayout relativeMain;
    private ListView listView;
    private TextView txtTitle;
    private List<PublicListEntity> dataList;
    private IListViewCallBack callBack;
    private PublicListAdapter adapter;

    public DialogTracedList(Context context, List<PublicListEntity> itemList) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        this.dataList = itemList;
        initView(context);
    }

    public static DialogTracedList getInstance(Context context, List<PublicListEntity> itemList) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogTracedList(context, itemList);
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
        view = View.inflate(context, R.layout.dialog_expand_listview, null);
        relativeMain = (RelativeLayout) view.findViewById(R.id.relative_listview);
        txtTitle = (TextView) view.findViewById(R.id.public_title);
        listView = (ListView) view.findViewById(R.id.public_listview);
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
        loadData();
    }

    /**
     * 请求数据
     */
    private void loadData() {
        if (!Utils.isStringEmpty(dataList)) {
            adapter = new PublicListAdapter(mContext);
            adapter.setData(dataList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PublicListEntity entity = (PublicListEntity) adapter.getItem(position);
                    if (callBack != null) {
                        callBack.setOnClickListener(entity);
                        dismiss();
                    }
                }
            });
        }
    }

    public DialogTracedList setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogTracedList setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public DialogTracedList setTitleName(int textResId) {
        txtTitle.setText(textResId);
        return this;
    }

    public DialogTracedList setTitleName(CharSequence msg) {
        txtTitle.setText(msg);
        return this;
    }

    public DialogTracedList isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public DialogTracedList isCancelable(boolean cancelable) {
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

    public void setOnItemClickListener(IListViewCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IListViewCallBack {
        void setOnClickListener(PublicListEntity entity);
    }
}
