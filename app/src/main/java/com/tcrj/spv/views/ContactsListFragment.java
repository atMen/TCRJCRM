package com.tcrj.spv.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.ContactListCallBack;
import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.ContactListPresenter;
import com.tcrj.spv.views.adapter.ContactsListAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.control.MyLetterListView;
import com.tcrj.spv.views.utils.CharacterParser;
import com.tcrj.spv.views.utils.ContactPinyin;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户信息：联系人
 * Created by leict on 2017/11/27.
 */

@SuppressLint("ValidFragment")
public class ContactsListFragment extends Fragment implements ContactListCallBack.View {
    private View view;
    private ListView listviewLetter;
    private TextView tvShowWord;
    private MyLetterListView letterListview;
    private ContactListCallBack.Presenter presenter;
    private ContactsListAdapter adapter;
    private CharacterParser character = null;
    private ContactPinyin pinyin = new ContactPinyin();
    private List<ContactListEntity.ListInfoBean> dataList;
    private String cusId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_contact_listview, container, false);
        new ContactListPresenter(ContactsListFragment.this);
        character = CharacterParser.getInstance();
        listviewLetter = (ListView) view.findViewById(R.id.listview_letter);
        tvShowWord = (TextView) view.findViewById(R.id.tv_show_word);
        letterListview = (MyLetterListView) view.findViewById(R.id.letter_listview);
        loadData();
        return view;
    }

    public ContactsListFragment(String cusId) {
        this.cusId = cusId;
    }

    /**
     * 加载数据
     */
    public void loadData() {
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
                ContactListEntity.ListInfoBean entity = (ContactListEntity.ListInfoBean) adapter.getItem(position);
                Intent intent = new Intent(getContext(), ContactDetailActivity.class);
                intent.putExtra("CusID", entity.getContactID());
                ((BaseActivity) getContext()).startActivityForResult(intent, 101);
            }
        });
        presenter.start();
        List<ContactListEntity.ListInfoBean> dataList = new ArrayList<>();
        // 根据a-z进行排序源数据
        Collections.sort(dataList, pinyin);
        adapter = new ContactsListAdapter(getContext());
        listviewLetter.setAdapter(adapter);
    }

    @Override
    public void setPresenter(ContactListCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        ((BaseActivity) getContext()).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) getContext()).dismisProgressDialog();
    }

    @Override
    public void getData(ContactListEntity entity) {
        if (entity != null) {
            dataList = new ArrayList<>();
            if (!Utils.isStringEmpty(entity.getListInfo())) {
                for (int i = 0; i < entity.getListInfo().size(); i++) {
                    ContactListEntity.ListInfoBean bookEntity = entity.getListInfo().get(i);
                    String pinyin = character.getSelling(character.getSelling(entity.getListInfo().get(i).getContactName()));
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
                adapter = new ContactsListAdapter(getContext());
                adapter.setData(entity.getListInfo());
                listviewLetter.setAdapter(adapter);
            }
        }
    }

    @Override
    public ParameterEntity setParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setKey("");
        entity.setCusId(cusId);
        entity.setUserId(user.getEntity().getUserId());
        return entity;
    }
}
