package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.IntentProductCallBack;
import com.tcrj.spv.model.IntentProductEntity;
import com.tcrj.spv.presenter.IntentProductPresenter;
import com.tcrj.spv.views.adapter.IntentProductAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

/**
 * 意向产品
 * Created by leict on 2017/11/22.
 */

public class DialogIntentProduct extends Dialog implements DialogInterface, IntentProductCallBack.View {
    private RelativeLayout relativeMain;
    private ListView listview;
    private Button btnIntentSubmit;
    private Button btnIntentCancel;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private static DialogIntentProduct instance;
    private IntentProductCallBack.Presenter presenter;
    private IntentProductAdapter adapter;
    private IProductCallBack callBack;
    private SparseArray sparseArray = new SparseArray();

    public DialogIntentProduct(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        new IntentProductPresenter(this);
        initView(context);
        presenter.start();
    }

    public static DialogIntentProduct getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogIntentProduct(context);
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
        view = View.inflate(context, R.layout.dialog_intent_product, null);
        relativeMain = (RelativeLayout) view.findViewById(R.id.relative_main);
        listview = (ListView) view.findViewById(R.id.intent_listview);
        btnIntentSubmit = (Button) view.findViewById(R.id.btn_intent_submit);
        btnIntentCancel = (Button) view.findViewById(R.id.btn_intent_cancel);
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
        btnIntentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnIntentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder _strValue = new StringBuilder();
                if (sparseArray.size() > 0) {
                    for (int i = 0; i < sparseArray.size(); i++) {
                        //_strValue.append(String.valueOf(sparseArray.keyAt(i)) + ",");
                        _strValue.append(String.valueOf(sparseArray.valueAt(i)) + ",");
                    }
                    //String keys = _strValue.substring(0, _strValue.toString().length() - 1);
                    String values = _strValue.substring(0, _strValue.toString().length() - 1);
                    if (callBack != null) {
                        callBack.setOnClickListener(values);
                        dismiss();
                    }
                } else {
                    ((BaseActivity) mContext).displayToast("请您选择数据");
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

    public DialogIntentProduct setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogIntentProduct setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    @Override
    public void setPresenter(IntentProductCallBack.Presenter presenter) {
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
    public void getData(IntentProductEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getListInfo())) {
                adapter = new IntentProductAdapter(mContext);
                adapter.setData(entity.getListInfo());
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IntentProductEntity.ListInfoBean listEntity = (IntentProductEntity.ListInfoBean) adapter.getItem(position);
                        SparseBooleanArray checkedItemPositions = listview.getCheckedItemPositions();
                        boolean isChecked = checkedItemPositions.get(position);
                        if (isChecked) {
                            sparseArray.put(listEntity.getPId(), listEntity.getPName());
                            adapter.setSelectItemId(position, 1);
                        } else {
                            sparseArray.delete(listEntity.getPId());
                            adapter.setSelectItemId(position, 0);
                        }
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(IProductCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IProductCallBack {
        void setOnClickListener(String value);
    }
}
