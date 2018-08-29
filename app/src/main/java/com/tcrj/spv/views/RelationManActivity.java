package com.tcrj.spv.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.RelationManCallBack;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.RelationManEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.RelationManPresenter;
import com.tcrj.spv.views.adapter.RelationManAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.control.MyLetterListView;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.CharacterParser;
import com.tcrj.spv.views.utils.RelationUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户：联系人
 */
public class RelationManActivity extends BaseActivity implements View.OnClickListener, RelationManCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;
    private ListView listviewLetter;
    private TextView tvShowWord;
    private MyLetterListView letterListview;
    private RelationManCallBack.Presenter presenter;
    private RelationManAdapter adapter;
    private CharacterParser character = null;
    private RelationUtils pinyin = new RelationUtils();
    private List<RelationManEntity.ListInfoBean> dataList;
    private String cusId = "";
    private int mbuid;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicationlist);
        character = CharacterParser.getInstance();
        cusId = getIntent().getStringExtra("CusID");
        type = getIntent().getIntExtra("Type", -1);
        new RelationManPresenter(this, 2);
        initView();
        loadData();
        presenter.start();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        listviewLetter = (ListView) findViewById(R.id.listview_letter);
        tvShowWord = (TextView) findViewById(R.id.tv_show_word);
        letterListview = (MyLetterListView) findViewById(R.id.letter_listview);
        tvTitle.setText("联系人");
        imgBack.setOnClickListener(this);
        layoutSearchResult.setOnClickListener(this);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        letterListview.setTextView(tvShowWord);
        // 设置右侧触摸监听
        letterListview.setOnTouchingLetterChangedListener(new MyLetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listviewLetter.setSelection(position);
                }
            }
        });

        presenter.start();
        List<RelationManEntity.ListInfoBean> dataList = new ArrayList<>();
        // 根据a-z进行排序源数据
        Collections.sort(dataList, pinyin);
        adapter = new RelationManAdapter(RelationManActivity.this);
        listviewLetter.setAdapter(adapter);
        listviewLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<RelationManEntity.ListInfoBean> listInfo = entitydata.getListInfo();
                String uid = listInfo.get(position).getUid();
                int a = Integer.parseInt(uid);
                mbuid = a;
                Log.e("TAG","下标："+position+"目标客户："+uid);
//                List<RelationManEntity.ListInfoBean> dataList = (List<RelationManEntity.ListInfoBean>) adapter.getItem(position);
//                Log.e("TAG","要转移到的uid:"+dataList.get(position).getUid());
                final MessageDialogBuilder msg = MessageDialogBuilder.getInstance(RelationManActivity.this);
                msg.setDuration(700);
                msg.setEffect(Effectstype.Slidetop);
                msg.setTitles("操作提示");
                if (type == 0) {
                    msg.setContents("确定要转移客户吗？");
                } else {
                    msg.setContents("确定要共享客户吗？");
                }
                msg.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        msg.dismiss();
                        switch (type) {
                            case 0:
                                new RelationManPresenter(RelationManActivity.this, 0);
                                presenter.start();
                                break;
                            case 1:
                                new RelationManPresenter(RelationManActivity.this, 1);
                                presenter.start();
                                break;
                        }
                    }

                    @Override
                    public void setCancelListener() {
                        msg.dismiss();
                    }
                });
                msg.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_search_result:
                String result = edtSearchResult.getText().toString().trim();
                if (Utils.isStringEmpty(result)) {
                    presenter.start();
                } else {
                    search(dataList);
                }
                break;
        }
    }

    @Override
    public void setPresenter(RelationManCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 开始Loading
     */
    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    /**
     * 结束Loading
     */
    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    /**
     * 请求列表数据
     *
     * @param entity
     */

    RelationManEntity entitydata;
    @Override
    public void getData(RelationManEntity entity) {
        entitydata = entity;
        if (entity != null) {
            dataList = new ArrayList<>();
            if (!Utils.isStringEmpty(entity.getListInfo())) {
                for (int i = 0; i < entity.getListInfo().size(); i++) {
                    RelationManEntity.ListInfoBean bookEntity = entity.getListInfo().get(i);
                    String pinyin = character.getSelling(character.getSelling(entity.getListInfo().get(i).getUName()));
                    if (pinyin.equals("") || pinyin == null) {
                        continue;
                    }
                    String sortString = pinyin.substring(0, 1).toUpperCase();
                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        bookEntity.setPinYin(sortString.toUpperCase());
                    } else {
                        bookEntity.setPinYin("#");
                    }
                    dataList.add(bookEntity);
                }
                // 根据a-z进行排序源数据
                Collections.sort(entity.getListInfo(), pinyin);
                adapter = new RelationManAdapter(RelationManActivity.this);
                Log.e("TAG","转移到："+entity.getListInfo().get(1).getUid()+"--"+entity.getListInfo().get(1).getUName());
                adapter.setData(entity.getListInfo());
                listviewLetter.setAdapter(adapter);
            }
        }
    }

    /**
     * 用户姓名关键字查询
     */
    private void search(List<RelationManEntity.ListInfoBean> itemList) {
        dataList = new ArrayList<>();
        if (Utils.isStringEmpty(itemList)) {
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            RelationManEntity.ListInfoBean entity = itemList.get(i);
            if (entity.getUName().contains(edtSearchResult.getText().toString())) {
                String pinyin = character.getSelling(character.getSelling(entity.getUName()));
                if (pinyin.equals("") || pinyin == null) {
                    continue;
                }
                String sortString = pinyin.substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]")) {
                    entity.setPinYin(sortString.toUpperCase());
                } else {
                    entity.setPinYin("#");
                }
                dataList.add(entity);
            }
        }
        // 根据a-z进行排序源数据
        if (dataList.size() == 0) {
            displayToast("暂无相关信息");
        }
        Collections.sort(dataList, pinyin);
        adapter = new RelationManAdapter(this);
        adapter.setData(dataList);
        listviewLetter.setAdapter(adapter);
    }

    /**
     * 客户共享
     *
     * @param entity
     */
    @Override
    public void getShare(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                finish();
            }
        }
    }

    /**
     * 客户转移
     *
     * @param entity
     */
    @Override
    public void getTransfer(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                finish();
            }
        }
    }

    /**
     * 设置参数
     *
     * @return
     */
    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
//        mbuid = user.getEntity().getUserId();
        ParameterEntity entity = new ParameterEntity();
        entity.setStaffId(user.getEntity().getUserId());
        entity.setType(type);
        entity.setCustomerID(cusId);
        entity.setUserID(mbuid);
        entity.setAuthority(1);
        entity.setCId(cusId);
        return entity;
    }
}
