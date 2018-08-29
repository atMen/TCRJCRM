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
import com.tcrj.spv.callback.WorkDailyTypeCallBack;
import com.tcrj.spv.model.NewWorkDailyEntity;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.presenter.WorkDailyTypePresenter;
import com.tcrj.spv.views.adapter.PublicListAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 日报类型
 * Created by leict on 2017/12/16.
 */

public class DialogWorkDaily extends Dialog implements DialogInterface, WorkDailyTypeCallBack.View {
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private boolean isCancelable = true;
    private static DialogTracedList instance;
    private RelativeLayout relativeMain;
    private ListView listView;
    private TextView txtTitle;
    private IListViewCallBack callBack;
    private PublicListAdapter adapter;
    private WorkDailyTypeCallBack.Presenter presenter;

    public DialogWorkDaily(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        new WorkDailyTypePresenter(DialogWorkDaily.this);
        initView(context);
        presenter.start();
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
    }

    public DialogWorkDaily setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogWorkDaily setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public DialogWorkDaily setTitleName(int textResId) {
        txtTitle.setText(textResId);
        return this;
    }

    public DialogWorkDaily setTitleName(CharSequence msg) {
        txtTitle.setText(msg);
        return this;
    }

    public DialogWorkDaily isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public DialogWorkDaily isCancelable(boolean cancelable) {
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

    @Override
    public void setPresenter(WorkDailyTypeCallBack.Presenter presenter) {
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
    public void getType(NewWorkDailyEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                List<PublicListEntity> dataList = new ArrayList<>();
//                for (int i = 0; i < entity.getData().size(); i++) {
//                    PublicListEntity bean = new PublicListEntity();
//                    bean.setpName(entity.getData().get(i).getVal());
//                    bean.setPID(entity.getData().get(i).getPID());
//                    bean.setCode(entity.getData().get(i).getCode());
//                    bean.setId(entity.getData().get(i).getID());
//                    dataList.add(bean);
                    PublicListEntity bean = new PublicListEntity();
                    bean.setpName("实施/售前/售后/产品");
                    bean.setCode("1");
                    dataList.add(bean);
                    PublicListEntity bean1 = new PublicListEntity();
                    bean1.setpName("非项目");
                    bean1.setCode("2");
                    bean1.setId(4);
                    dataList.add(bean1);
//                }
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
    }

    public interface IListViewCallBack {
        void setOnClickListener(PublicListEntity entity);
    }
}
