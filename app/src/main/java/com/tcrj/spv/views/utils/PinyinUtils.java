package com.tcrj.spv.views.utils;

import com.tcrj.spv.model.PersionBookEntity;

import java.util.Comparator;

/**
 * Created by leict on 2017/11/17.
 */

public class PinyinUtils implements Comparator<PersionBookEntity.ListinfoBean> {

    public int compare(PersionBookEntity.ListinfoBean o1, PersionBookEntity.ListinfoBean o2) {
        if (o1.getPinYin().equals("@") || o2.getPinYin().equals("#")) {
            return -1;
        } else if (o1.getPinYin().equals("#") || o2.getPinYin().equals("@")) {
            return 1;
        } else {
            return o1.getPinYin().compareTo(o2.getPinYin());
        }
    }
}