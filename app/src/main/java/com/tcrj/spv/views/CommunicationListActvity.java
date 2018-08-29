package com.tcrj.spv.views;

import android.content.Intent;
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
import com.tcrj.spv.callback.PersionBookCallBack;
import com.tcrj.spv.model.PersionBookEntity;
import com.tcrj.spv.presenter.PersionBookPresenter;
import com.tcrj.spv.views.adapter.CommunicationAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.control.MyLetterListView;
import com.tcrj.spv.views.utils.CharacterParser;
import com.tcrj.spv.views.utils.PinyinUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通讯录
 */
public class CommunicationListActvity extends BaseActivity implements View.OnClickListener, PersionBookCallBack.View {
    private ImageButton imgBack;
    private TextView tvTitle;
    private EditText edtSearchResult;
    private LinearLayout layoutSearchResult;
    private ListView listviewLetter;
    private TextView tvShowWord;
    private MyLetterListView letterListview;
    private PersionBookCallBack.Presenter presenter;
    private CommunicationAdapter adapter;
    private CharacterParser character = null;
    private PinyinUtils pinyin = new PinyinUtils();
    private List<PersionBookEntity.ListinfoBean> dataList;


    private List<PersionBookEntity.ListinfoBean> searchdataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicationlist);
        character = CharacterParser.getInstance();
        new PersionBookPresenter(this);
        initView();
        loadData();
    }

    @Override
    public void initView() {
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        edtSearchResult = (EditText) findViewById(R.id.edt_search_result);
        layoutSearchResult = (LinearLayout) findViewById(R.id.layout_search_result);
        listviewLetter = (ListView) findViewById(R.id.listview_letter);
        tvShowWord = (TextView) findViewById(R.id.tv_show_word);
        letterListview = (MyLetterListView) findViewById(R.id.letter_listview);
        tvTitle.setText("通讯录");
        imgBack.setOnClickListener(this);
        layoutSearchResult.setOnClickListener(this);
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
        listviewLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PersionBookEntity.ListinfoBean entity = (PersionBookEntity.ListinfoBean) adapter.getItem(position);
                Intent intent = new Intent(CommunicationListActvity.this, CommunicationDetailActvity.class);
                intent.putExtra("StaffID", entity.getStaffID());
                Log.e("TAG","stiffid"+entity.getStaffID());
                startActivity(intent);
            }
        });
        presenter.start();
        List<PersionBookEntity.ListinfoBean> dataList = new ArrayList<>();
        // 根据a-z进行排序源数据
        Collections.sort(dataList, pinyin);
        adapter = new CommunicationAdapter(CommunicationListActvity.this);
        listviewLetter.setAdapter(adapter);
    }

    @Override
    public void setPresenter(PersionBookCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    @Override
    public void getData(PersionBookEntity entity) {
        if (entity != null) {
            dataList = new ArrayList<>();
            if (!Utils.isStringEmpty(entity.getListinfo())) {
                for (int i = 0; i < entity.getListinfo().size(); i++) {
                    PersionBookEntity.ListinfoBean bookEntity = entity.getListinfo().get(i);
                    String pinyin = character.getSelling(character.getSelling(entity.getListinfo().get(i).getStaffName()));
                    bookEntity.setDeptName(entity.getListinfo().get(i).getDeptName());
                    bookEntity.setAddress(entity.getListinfo().get(i).getAddress());
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
                Collections.sort(entity.getListinfo(), pinyin);
                adapter = new CommunicationAdapter(CommunicationListActvity.this);
                adapter.setData(entity.getListinfo());
                listviewLetter.setAdapter(adapter);
            }
        }
    }

    /**
     * 用户姓名关键字查询
     */
    private void search(List<PersionBookEntity.ListinfoBean> itemList) {
        searchdataList = new ArrayList<>();
        if (Utils.isStringEmpty(itemList)) {
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            PersionBookEntity.ListinfoBean entity = itemList.get(i);
            if (entity.getStaffName().contains(edtSearchResult.getText().toString())) {
                String pinyin = character.getSelling(character.getSelling(entity.getStaffName()));
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
                searchdataList.add(entity);
            }
        }
        // 根据a-z进行排序源数据
        if (searchdataList.size() == 0) {
            displayToast("暂无相关信息");
        }
        Collections.sort(searchdataList, pinyin);
        adapter = new CommunicationAdapter(this);
        adapter.setData(searchdataList);
        listviewLetter.setAdapter(adapter);

    }
}
