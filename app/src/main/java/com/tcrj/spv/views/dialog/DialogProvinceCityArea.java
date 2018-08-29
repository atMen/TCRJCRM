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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tcrj.spv.R;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.cityBean;
import com.tcrj.spv.views.adapter.AreaAdapter;
import com.tcrj.spv.views.adapter.CityAdapter;
import com.tcrj.spv.views.adapter.ProvinceAdapter;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 省市区
 * Created by leict on 2017/11/21.
 */

public class DialogProvinceCityArea extends Dialog implements DialogInterface {
    private static DialogProvinceCityArea instance;
    private static Context mContext;
    private int mDuration = -1;
    private View view;
    private Effectstype type = null;
    private LinearLayout layoutProvince;
    private ProvinceAdapter pAdapter;
    private AreaAdapter aAdapter;
    private CityAdapter cAdapter;
    private ListView pListview;
    private ListView cListview;
    private ListView aListview;
    private IProvinceCityAreaCallBack callBack = null;

    public DialogProvinceCityArea(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        initView(context);
    }

    public static DialogProvinceCityArea getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new DialogProvinceCityArea(context);
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
        view = View.inflate(context, R.layout.dialog_province_city_area, null);
        layoutProvince = (LinearLayout) view.findViewById(R.id.layout_province);
        pListview = (ListView) view.findViewById(R.id.province_listview);
        cListview = (ListView) view.findViewById(R.id.city_listview);
        aListview = (ListView) view.findViewById(R.id.area_listview);
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
        setProvince();
    }

    private String pid;
    private String pName;

    private String cid;
    private String cName;

    /**
     * 省份数据
     */
    private void setProvince() {
//        Type listType = new TypeToken<ProvinceEntity>() {
//        }.getType();
//        ProvinceEntity entity = new Gson().fromJson(Utils.getJson("province.json", mContext), listType);

        Type listType = new TypeToken<cityBean>() {
        }.getType();
        cityBean entity = new Gson().fromJson(Utils.getJson("ssq", mContext), listType);
        final List<cityBean.ListInfoBean> listInfo = entity.getListInfo();


        pAdapter = new ProvinceAdapter(mContext);
        pAdapter.setData(listInfo);
        pListview.setAdapter(pAdapter);
        pListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ProvinceEntity.ProvinceListBean entity = (ProvinceEntity.ProvinceListBean) pAdapter.getItem(position);
                List<cityBean.ListInfoBean.AEntityBean> aEntity = listInfo.get(position).getAEntity();

                //记录点击到的省市id
                 pid = listInfo.get(position).getPid();
                 pName = listInfo.get(position).getPName();



                pAdapter.getItem(position);
                pAdapter.setSelection(position);
                pAdapter.notifyDataSetInvalidated();
                clearAreaList();
                setCityData(aEntity);//entity.getCode()
            }
        });
    }

    /**
     * 城市数据
     */
    private void setCityData(final List<cityBean.ListInfoBean.AEntityBean> pEntity) {
//        List<CityEntity.CityListBean> dataList = new ArrayList<>();
//        Type listType = new TypeToken<CityEntity>() {
//        }.getType();
//        CityEntity entity = new Gson().fromJson(Utils.getJson("city.json", mContext), listType);
//        List<CityEntity.CityListBean> itemList = entity.getCityList();
//        for (int i = 0; i < itemList.size(); i++) {
//            if (pEntity.getCode().equals(itemList.get(i).getPcode())) {
//                CityEntity.CityListBean bean = new CityEntity.CityListBean();
//                bean.setCode(itemList.get(i).getCode());
//                bean.setName(itemList.get(i).getName());
//                bean.setPcode(itemList.get(i).getPcode());
//                dataList.add(bean);
//            }
//        }
        cAdapter = new CityAdapter(mContext);
        cAdapter.setData(pEntity);
        cListview.setAdapter(cAdapter);


        cListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CityEntity.CityListBean entity = (CityEntity.CityListBean) cAdapter.getItem(position);
                List<cityBean.ListInfoBean.AEntityBean.CEntityBean> cEntity = pEntity.get(position).getCEntity();

                cid = pEntity.get(position).getCid();
                cName = pEntity.get(position).getCName();

                cAdapter.setSelection(position);
                cAdapter.notifyDataSetInvalidated();
                setAreaData(pEntity, cEntity);//entity.getCode()
            }
        });
    }

    /**
     * 区域数据
     */
    private void setAreaData(final List<cityBean.ListInfoBean.AEntityBean> pEntity,
                             final List<cityBean.ListInfoBean.AEntityBean.CEntityBean> cEntity) {

//        List<AreaEntity.AreaListBean> dataList = new ArrayList<>();
//        Type listType = new TypeToken<AreaEntity>() {
//        }.getType();
//        AreaEntity entity = new Gson().fromJson(Utils.getJson("area.json", mContext), listType);
//        List<AreaEntity.AreaListBean> itemList = entity.getAreaList();
//        for (int i = 0; i < itemList.size(); i++) {
//            if (cEntity.getCode().equals(itemList.get(i).getAcode())) {
//                AreaEntity.AreaListBean bean = new AreaEntity.AreaListBean();
//                bean.setCcode(itemList.get(i).getCcode());
//                bean.setName(itemList.get(i).getName());
//                bean.setAcode(itemList.get(i).getAcode());
//                dataList.add(bean);
//            }
//        }


        aAdapter = new AreaAdapter(mContext);
        aAdapter.setData(cEntity);
        aListview.setAdapter(aAdapter);
        aListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cityBean.ListInfoBean.AEntityBean.CEntityBean entity = cEntity.get(position);
//                AreaEntity.AreaListBean entity = (AreaEntity.AreaListBean) aAdapter.getItem(position);
                aAdapter.setSelection(position);
                aAdapter.notifyDataSetInvalidated();//刷新数据 会清空所有信息，重新布局，位置会到最上方   notifyDataSetChanged会保存刷新前的状态，比如Listview滑动的位置；

                CommonalityEntity commonality = new CommonalityEntity();
                commonality.setProvinceId(Integer.parseInt(pid));
                commonality.setProvinceName(pName);

                commonality.setCityId(Integer.parseInt(cid));
                commonality.setCityName(cName);

                commonality.setAreaId(Integer.parseInt(entity.getAid()));
                commonality.setAreaName(entity.getAName());
                if (callBack != null) {
                    callBack.setOnItemListener(commonality);
                }
                dismiss();
            }
        });
    }

    /**
     * 清空区域数据
     */
    private void clearAreaList() {
        List<cityBean.ListInfoBean.AEntityBean.CEntityBean> dataList = new ArrayList<>();
        aAdapter = new AreaAdapter(mContext);
        aAdapter.setData(dataList);
        aListview.setAdapter(aAdapter);
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(layoutProvince);
    }

    public DialogProvinceCityArea setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public DialogProvinceCityArea setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public void setOnItemClickListener(IProvinceCityAreaCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IProvinceCityAreaCallBack {
        void setOnItemListener(CommonalityEntity entity);
    }
}
