package com.tcrj.spv.views.utils;

import com.tcrj.spv.model.RelationManEntity;

import java.util.Comparator;

/**
 * Created by leict on 2017/12/6.
 */

public class RelationUtils implements Comparator<RelationManEntity.ListInfoBean> {

    public int compare(RelationManEntity.ListInfoBean o1, RelationManEntity.ListInfoBean o2) {
        if (o1.getPinYin().equals("@") || o2.getPinYin().equals("#")) {
            return -1;
        } else if (o1.getPinYin().equals("#") || o2.getPinYin().equals("@")) {
            return 1;
        } else {
            return o1.getPinYin().compareTo(o2.getPinYin());
        }
    }
}