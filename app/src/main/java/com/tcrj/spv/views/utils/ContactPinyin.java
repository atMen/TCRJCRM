package com.tcrj.spv.views.utils;

import com.tcrj.spv.model.ContactListEntity;

import java.util.Comparator;

/**
 * Created by leict on 2017/11/28.
 */

public class ContactPinyin implements Comparator<ContactListEntity.ListInfoBean> {

    public int compare(ContactListEntity.ListInfoBean o1, ContactListEntity.ListInfoBean o2) {
        if (o1.getPinYin().equals("@") || o2.getPinYin().equals("#")) {
            return -1;
        } else if (o1.getPinYin().equals("#") || o2.getPinYin().equals("@")) {
            return 1;
        } else {
            return o1.getPinYin().compareTo(o2.getPinYin());
        }
    }
}