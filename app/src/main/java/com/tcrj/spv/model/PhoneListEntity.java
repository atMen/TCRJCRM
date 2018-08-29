package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 公共通讯录列表实体类
 * Created by leict on 2017/11/30.
 */

public class PhoneListEntity implements Serializable {
    private int Id;
    private String phoneId;
    private String phoneName;
    private String phoneCord;

    public String getPhoneCord() {
        return phoneCord;
    }

    public void setPhoneCord(String phoneCord) {
        this.phoneCord = phoneCord;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }
}
