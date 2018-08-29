package com.tcrj.spv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tcrj.spv.MyFollowRecordSearchActivity;
import com.tcrj.spv.R;
import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.model.ListInfoBean;
import com.tcrj.spv.model.TracedMaturityEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.adapter.ExpandableListAdapter;
import com.tcrj.spv.views.adapter.FollowRecordExisAdapter;
import com.tcrj.spv.views.adapter.FollowRecrodTypeAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;
import com.tcrj.spv.views.utils.ContextUtils;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.view.PullToRefreshLayout;
import com.tcrj.spv.views.xlist.XListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tcrj.spv.R.id.refresh_view;

/**
 * 写跟进
 */
public class WriteFollowActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener {

    private TextView tvOne;

    private TextView tvTwo;

    private LinearLayout layout_word;
    private ImageButton img_back;

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    PullToRefreshLayout viewById;

    /**
     * 筛选框
     **/
    private PopupWindow popLeft;
    private FollowRecordExisAdapter fatheradpater;
    private FollowRecrodTypeAdapter sonadpater;
    private List<ListInfoBean> fatherlist;
    private List<ListInfoBean> sonlist;
    private ListView lv1;
    private XListView lv2;
    private int fid = 0;
    private int PageSize = 8;
    private int CurrentPageIndex = 1;
    private LinearLayout layoutRecordExist = null;
    private LinearLayout layoutll = null;
    private Button btnSubmit;
    private Button btnCancel;

    private CharSequence[] searchArray;

    /**
     * 参数
     *
     * @param savedInstanceState
     */
    private String KeyWord = "";
    private String ContactType = "0";
    private String ContactStatu = "0";
    private String TracedMaturity = "0";
    private String CusId = "0";
    private int CurrentIndex = 1;
    private String OwerId = "0";
    private String Type = "1";

    private UserInfoEntity User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writefollow);
        User = BaseApplication.getUserInfoEntity();//获取当前用户信息

        findview();
        //初始化pop
        initPopupWindow();

        //加载数据
        loadData();


    }


    private List<FollowRecordEntity.ListInfoBean> listInfoLoad = null;

    private void loadData() {


        //显示加载动画
        showProgressDialog(null);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        api.getCustomerTraceListGj(new FollowRecordEntity(CusId,KeyWord,
                ContactType,ContactStatu,TracedMaturity,CurrentIndex,OwerId
                ,Type,10,User.getEntity().getUserId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowRecordEntity>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","数据加载失败//");
                        dismisProgressDialog();
                        viewById.refreshFinish(1);
                        viewById.loadmoreFinish(1);
                    }

                    @Override
                    public void onNext(FollowRecordEntity entity) {
                        List<FollowRecordEntity.ListInfoBean> listInfo = entity.getListInfo();

                        Log.e("TAG","数据加载成功"+listInfo.size());
                        setDate(listInfo);

//                      initExpandableListView(listInfoLoad);


                    }
                });
    }

    private void setDate(List<FollowRecordEntity.ListInfoBean> recordList) {

        if (recordList.size() == 0) {
            if (CurrentIndex == 1) {
                displayToast("暂无数据");
                listInfoLoad.clear();
                adapter.notifyDataSetChanged();
                viewById.refreshFinish(0);
            } else {
                Log.e("TAG","暂无更多数据----"+listInfoLoad.size());
//                expandableListView.setSelection(listInfoLoad.size() - 1);
                viewById.loadmoreFinish(0);
                displayToast("暂无更多数据");
                CurrentIndex--;
            }
            dismisProgressDialog();
            return;
        } else {
            if (CurrentIndex == 1) {
                listInfoLoad.clear();
                viewById.refreshFinish(0);
            } else {
                expandableListView.setSelection(listInfoLoad.size() - 1);
                viewById.loadmoreFinish(0);
            }
            dismisProgressDialog();


            if (listInfoLoad.size() > 0 && listInfoLoad.get(listInfoLoad.size() - 1).getTime().equals(recordList.get(0).getTime())) {
                List<FollowRecordEntity.ListInfoBean.ListtTraceInfosBean> traceList = recordList.get(0).getListtTraceInfos();
                listInfoLoad.get(listInfoLoad.size() - 1).getListtTraceInfos().addAll(traceList);
                if (recordList.size() > 1) {
                    listInfoLoad.addAll(recordList.subList(1, recordList.size()));
                }
            } else {
                Log.e("TAG","数据添加"+recordList.size());
                listInfoLoad.addAll(recordList);
            }
            Log.e("TAG","数据添加listInfoLoad"+listInfoLoad.size());
            adapter.notifyDataSetChanged();
            dismisProgressDialog();

        }


    }


    private LinearLayout layout_recordsearch;
    private LinearLayout ll_tab;
    private TextView tvMy;
    private void findview() {


        layout_recordsearch = (LinearLayout) findViewById(R.id.layout_recordsearch);
        ll_tab = (LinearLayout) findViewById(R.id.ll_tab);

        img_back = (ImageButton) findViewById(R.id.img_back);

        layout_word = (LinearLayout) findViewById(R.id.layout_word);//添加跟进记录

        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);

        tvMy = (TextView) findViewById(R.id.tv_my);

        viewById = (PullToRefreshLayout) findViewById(refresh_view);
        expandableListView = (ExpandableListView) findViewById(R.id.content_view);

        if (User.getEntity().getIsHavexj() == 0) {
            tvMy.setVisibility(View.VISIBLE);
            ll_tab.setVisibility(View.GONE);
        } else {
            tvMy.setVisibility(View.GONE);
            ll_tab.setVisibility(View.VISIBLE);
        }

        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        layout_recordsearch.setOnClickListener(this);
        layout_word.setOnClickListener(this);
        img_back.setOnClickListener(this);

        viewById.setOnRefreshListener(this);

        tvOne.setSelected(true);

        listInfoLoad = new ArrayList<FollowRecordEntity.ListInfoBean>();
        adapter = new ExpandableListAdapter(listInfoLoad,this);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
        expandableListView.expandGroup(0);//默认展开第一项内容
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id)
            {

                FollowRecordEntity.ListInfoBean.ListtTraceInfosBean entity = (FollowRecordEntity.ListInfoBean.ListtTraceInfosBean)
                        adapter.getChild(groupPosition, childPosition);

                //判断是否可以编辑跟进详情
                if (Type.equals("1")) {//可编辑
                    Intent intent = new Intent(WriteFollowActivity.this, FollowRecordDetailActivity.class);
                    intent.putExtra("TID", entity.getCTID());
                    startActivityForResult(intent, 103);
                } else {//不可编辑
//                    可编辑的跟进详情页面
                    Intent intent = new Intent(WriteFollowActivity.this, FollowRecordDetailActivity.class);
                    intent.putExtra("TID", entity.getCTID());
                    startActivity(intent);
                }
                return true;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id)
            {
                if (parent.isGroupExpanded(groupPosition))
                {
                    // 如果展开则关闭
                    parent.collapseGroup(groupPosition);
                } else
                {
                    // 如果关闭则打开，注意这里是手动打开不要默认滚动否则会有bug
                    parent.expandGroup(groupPosition);
                }
                return true;
            }
        });







    }

    private void initPopupWindow() {

        layoutll = (LinearLayout) findViewById(R.id.layout_ll);

        layoutRecordExist = (LinearLayout) findViewById(R.id.layout_record_exist);

        View left_view = ContextUtils.getLayoutInflater(this).inflate(R.layout.dialog_followrecordexist, null, false);
        popLeft = new PopupWindow(left_view, Utils.getWidth(this), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        btnSubmit = (Button) left_view.findViewById(R.id.btn_search_ensure);
        btnCancel = (Button) left_view.findViewById(R.id.btn_search_reset);
        lv1 = (ListView) left_view.findViewById(R.id.customstate_leftlist);
        lv2 = (XListView) left_view.findViewById(R.id.customway_rightlist);
        layoutRecordExist.setOnClickListener(this);
        lv2.setPullLoadEnable(true);
        lv2.setPullRefreshEnable(false);
        lv2.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
            }
        });
        fatherlist = new ArrayList<ListInfoBean>();
        sonlist = new ArrayList<ListInfoBean>();
        fatheradpater = new FollowRecordExisAdapter(WriteFollowActivity.this, fatherlist);
        sonadpater = new FollowRecrodTypeAdapter(WriteFollowActivity.this, sonlist);
        lv1.setAdapter(fatheradpater);
        lv2.setAdapter(sonadpater);

        //父菜单的点击事件
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CurrentPageIndex = 1;
                fid = position;
                fatheradpater.setSelectItemId(position);
                sonlist.clear();
                if (fatherlist.get(position).getID().equals("0")) {
                    getLocalSonDate_A();
                }
                if (fatherlist.get(position).getID().equals("1")) {
                    getLocalSonDate_B();
                }
                if (fatherlist.get(position).getID().equals("2")) {
                    getLocalSonDate_C(CurrentPageIndex);
                }
                if (fatherlist.get(position).getID().equals("3")) {
                    getLocalSonDate_D();
                }
            }
        });


        //子菜单点击事件
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fatherlist.get(fid).setIndex(position - 1);
                fatherlist.get(fid).setSid(sonlist.get(position - 1).getMaturityID()+"");
                sonadpater.setSelectItemId(position - 1);
                Log.e("TAG","筛选标识："+sonlist.get(position - 1).getMaturityID());
            }
        });
        getFatherDate();
        fatheradpater.notifyDataSetChanged();


        //确定按钮的点击事件
        //确定案件
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG","-----"+fatherlist.size()+"" );
                if (fatherlist.size() == 3) {

                    ContactType = fatherlist.get(0).getSid();
                    if (ContactType == null || ContactType.equals("") || ContactType.equals("null")) {
                        ContactType = "0";
                    }
                    ContactStatu = fatherlist.get(1).getSid();
                    if (ContactStatu == null || ContactStatu.equals("") ||  ContactStatu.equals("null")) {
                        ContactStatu = "0";
                    }
                    TracedMaturity = fatherlist.get(2).getSid();
                    Log.e("TAG","TracedMaturity-----"+TracedMaturity );
                    if (TracedMaturity == null || TracedMaturity.equals("") ||  TracedMaturity.equals("null")) {
                        TracedMaturity = "-1";
                    }

                } else {
                    ContactType = fatherlist.get(0).getSid();
                    if ( ContactType == null || ContactType.equals("") || ContactType.equals("null")) {
                        ContactType = "0";
                    }
                    ContactStatu = fatherlist.get(1).getSid();
                    if ( ContactStatu == null || ContactStatu.equals("") || ContactStatu.equals("null")) {
                        ContactStatu = "0";
                    }
                    TracedMaturity = fatherlist.get(2).getSid();
                    if (TracedMaturity == null || TracedMaturity.equals("") ||  TracedMaturity.equals("null")) {
                        TracedMaturity = "-1";
                    }
                    OwerId = fatherlist.get(3).getSid();
                    if (OwerId == null || OwerId.equals("") || OwerId.equals("null") ||  OwerId.equals("意向产品") || OwerId.equals("不限")) {
                        OwerId = "0";
                    }
                }
                System.out.println("ContactType:" + ContactType + "@@@" + "ContactStatu:" + ContactStatu + "@@@" + "TracedMaturity:" + TracedMaturity + "@@@" + "OwerId:" + OwerId + "@@@");

                CurrentIndex = 1;
                loadData();//加载数据
                popLeft.dismiss();

            }
        });

        //重置按键
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ListInfoBean entity : fatherlist) {
                    entity.setIndex(0);
                    entity.setSid("null");
                }
                fid = 0;
                KeyWord = "";
                ContactType = "0";
                ContactStatu = "0";
                TracedMaturity = "0";
                CurrentIndex = 1;
                OwerId = "0";
                lv1.performItemClick(fatheradpater.getView(fid, null, null), fid, lv1.getItemIdAtPosition(fid));

            }
        });

        //点击其他地方关闭popwindon
        left_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popLeft != null && popLeft.isShowing()) {
                    popLeft.dismiss();
                }
                return false;
            }
        });
    }


    //获取父类数据
    public void getFatherDate() {
        searchArray = this.getResources().getStringArray(R.array.search_followrecord);
        for (int i = 0; i < searchArray.length - 1; i++) {
            ListInfoBean entity = new ListInfoBean();
            entity.setID(String.valueOf(i));
            entity.setIndex(0);
            entity.setSid("");
            entity.setMaturityName(searchArray[i].toString());
            fatherlist.add(entity);
        }
        fatheradpater.notifyDataSetChanged();
    }

    //获取联系方式
    public void getLocalSonDate_A() {

        CharSequence[] list = this.getResources().getStringArray(R.array.search_ways_follow);
        for (int i = 0; i < list.length; i++) {
            ListInfoBean entity = new ListInfoBean();
            entity.setMaturityID(i);
            entity.setIndex(0);
            entity.setSid("");
            entity.setMaturityName(list[i].toString());
            sonlist.add(entity);
        }
        sonadpater.setSelectItemId(fatherlist.get(fid).getIndex());
        lv2.setPullLoadEnable(false);
    }

    //获取跟进状态
    public void getLocalSonDate_B() {
        CharSequence[] list = this.getResources().getStringArray(R.array.followstate_list_follow);
        for (int i = 0; i < list.length; i++) {
            ListInfoBean entity = new ListInfoBean();
            entity.setMaturityID(i);
            entity.setIndex(0);
            entity.setSid("");
            entity.setMaturityName(list[i].toString());
            sonlist.add(entity);
        }
        sonadpater.setSelectItemId(fatherlist.get(fid).getIndex());
        lv2.setPullLoadEnable(false);
    }

    //获取客户成熟度
    public void getLocalSonDate_C( final int pageNum) {

        loadData1(pageNum);
    }

    private Api api;
    /**
     * 加载数据
     */
    public void loadData1(final int pageNum) {
        Log.e("TAG","数据开始加载");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getTracedMaturityList(new TracedMaturityEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TracedMaturityEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TracedMaturityEntity entity) {

                        Log.e("TAG","数据加载成功"+fatherlist.get(fid).getIndex());
                        List<ListInfoBean> listInfo = entity.getListInfo();
                        //第一页添加不限项
                        ListInfoBean listInfoBean = new ListInfoBean();
                        listInfoBean.setID("-1");
                        listInfoBean.setMaturityName("不限");
                        sonlist.add(listInfoBean);
                        sonlist.addAll(listInfo);
                        sonadpater.setSelectItemId(fatherlist.get(fid).getIndex());
                        lv2.setSelection(fatherlist.get(fid).getIndex());
                        lv2.setPullLoadEnable(false);
                    }
                });

    }

    //获取客户成熟度
    public void getLocalSonDate_D( ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCurrentUserUnderlingList(new TracedMaturityEntity(User.getEntity().getUserId(),"1"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TracedMaturityEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","数据加载失败");
                    }

                    @Override
                    public void onNext(TracedMaturityEntity entity) {

                        Log.e("TAG","数据加载成功"+entity.getListInfo().get(0).getMaturityName());
                        Log.e("TAG","User.getEntity().getUserId()---"+User.getEntity().getUserId());
                        List<ListInfoBean> listInfo = entity.getListInfo();
                        //第一页添加不限项
                        ListInfoBean listInfoBean = new ListInfoBean();
                        listInfoBean.setID("-1");
                        listInfoBean.setMaturityName("不限");
                        sonlist.add(listInfoBean);
                        sonlist.addAll(listInfo);
                        sonadpater.setSelectItemId(fatherlist.get(fid).getIndex());
                        lv2.setSelection(fatherlist.get(fid).getIndex());
                        lv2.setPullLoadEnable(false);
                    }
                });

    }

    @Override
    public void initView() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_one:
                tvOne.setSelected(true);
                tvTwo.setSelected(false);

                deletePerson();
                Type = "1";
                KeyWord = "";
                ContactType = "0";
                ContactStatu = "0";
                TracedMaturity = "0";
                CurrentIndex = 1;
                OwerId = "0";
                loadData();

                break;
            case R.id.tv_two:
                tvOne.setSelected(false);
                tvTwo.setSelected(true);

                Type = "0";
                KeyWord = "";
                ContactType = "0";
                ContactStatu = "0";
                TracedMaturity = "0";
                CurrentIndex = 1;
                OwerId = "0";
                addPerson();
                loadData();

                break;

            case R.id.layout_record_exist:
                popLeft.showAsDropDown(layoutll);
                //默认触发选中的父类的onclick事件
                lv1.performItemClick(fatheradpater.getView(fid, null, null), fid, lv1.getItemIdAtPosition(fid));
                break;

            case R.id.layout_word:
                openNewActivity(MineCustomerActivity.class);
                break;

            case R.id.img_back:
                finish();
                break;
            case R.id.layout_recordsearch:
                Intent intent = new Intent(this, MyFollowRecordSearchActivity.class);
//                intent.putExtra("Title", title_liner_name.getText().toString().trim());
//                intent.putExtra("Type", Type);
                startActivity(intent);
                break;
            default:
                break;
        }


    }

    //删除拥有人
    private void deletePerson() {
        fatherlist.clear();
        for (int i = 0; i < searchArray.length - 1; i++) {
            ListInfoBean entity = new ListInfoBean();
            entity.setID(String.valueOf(i));
            entity.setIndex(0);
            entity.setSid("");
            entity.setMaturityName(searchArray[i].toString());
            fatherlist.add(entity);
        }
        fatheradpater.notifyDataSetChanged();
    }

    //添加拥有人
    private void addPerson() {
        fatherlist.clear();
        for (int i = 0; i < searchArray.length; i++) {
            ListInfoBean entity = new ListInfoBean();
            entity.setID(String.valueOf(i));
            entity.setIndex(0);
            entity.setSid("");
            entity.setMaturityName(searchArray[i].toString());
            fatherlist.add(entity);
        }
        fatheradpater.notifyDataSetChanged();
    }


//    private PullToRefreshLayout pullToRefreshLayout;

    //刷新的回调
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//        this.pullToRefreshLayout = pullToRefreshLayout;
        CurrentIndex = 1;
        loadData();


    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//        this.pullToRefreshLayout = pullToRefreshLayout;
        CurrentIndex++;
        loadData();

    }


    //不可取的更新方式，后续需要修改
    @Override
    protected void onRestart() {
        super.onRestart();
        CurrentIndex = 1;
        loadData();
    }
}
