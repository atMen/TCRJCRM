package com.tcrj.spv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.FollowRecordDetailActivity;
import com.tcrj.spv.views.adapter.ExpandableListAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;
import com.tcrj.spv.views.view.PullToRefreshLayout;

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
 * 搜索跟进
 */
public class MyFollowRecordSearchActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener {

    private TextView tvOne;
    private TextView gjEmpty;

    private TextView tvTwo;

    private LinearLayout layout_word;
    private ImageButton img_back;

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    PullToRefreshLayout viewById;


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
        setContentView(R.layout.activity_mywritefollow);
        User = BaseApplication.getUserInfoEntity();//获取当前用户信息
        findview();
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

            if(isOnLoadMore == false){

                Log.e("TAG","刷新时候");
                gjEmpty.setVisibility(View.VISIBLE);
                viewById.setVisibility(View.GONE);
                expandableListView.setVisibility(View.GONE);

            }
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
            gjEmpty.setVisibility(View.GONE);
            viewById.setVisibility(View.VISIBLE);
            expandableListView.setVisibility(View.VISIBLE);

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

    private LinearLayout layoutSearchResult;
    private EditText edtSearchResult;
    private void findview() {

        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        img_back = (ImageButton) findViewById(R.id.img_back);

        layout_word = (LinearLayout) findViewById(R.id.layout_word);//添加跟进记录

        tvOne = (TextView) findViewById(R.id.tv_one);

        gjEmpty = (TextView) findViewById(R.id.tv_gj_empty);
        tvTwo = (TextView) findViewById(R.id.tv_two);


        viewById = (PullToRefreshLayout) findViewById(refresh_view);
        expandableListView = (ExpandableListView) findViewById(R.id.content_view);

        layoutSearchResult.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
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
                    Intent intent = new Intent(MyFollowRecordSearchActivity.this, FollowRecordDetailActivity.class);
                    intent.putExtra("TID", entity.getCTID());
                    startActivityForResult(intent, 103);
                } else {//不可编辑
//                    可编辑的跟进详情页面
                    Intent intent = new Intent(MyFollowRecordSearchActivity.this, FollowRecordDetailActivity.class);
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

        layout_word.setVisibility(View.GONE);


    }




    private Api api;


    @Override
    public void initView() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_one:
                edtSearchResult.setText("");
                tvOne.setSelected(true);
                tvTwo.setSelected(false);

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
                edtSearchResult.setText("");
                edtSearchResult.clearComposingText();
                tvOne.setSelected(false);
                tvTwo.setSelected(true);

                Type = "0";
                KeyWord = "";
                ContactType = "0";
                ContactStatu = "0";
                TracedMaturity = "0";
                CurrentIndex = 1;
                OwerId = "0";
                loadData();

                break;


            case R.id.img_back:
                finish();
                break;

            case R.id.layout_search_result:
                isOnLoadMore = false;
                String trim = edtSearchResult.getText().toString().trim();
                    KeyWord = trim;
                loadData();


                break;

            default:
                break;
        }


    }




//    private PullToRefreshLayout pullToRefreshLayout;

    //刷新的回调
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//        this.pullToRefreshLayout = pullToRefreshLayout;
        CurrentIndex = 1;
        loadData();


    }

    boolean isOnLoadMore = true;
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//        this.pullToRefreshLayout = pullToRefreshLayout;
        isOnLoadMore = true;
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
