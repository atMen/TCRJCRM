package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 写日报：实体类
 * Created by leict on 2017/10/26.
 */

public class DailyEntity implements Serializable {

    /**
     * State : 1
     * IsNew : null
     * LastContent : null
     * Msg : 操作成功
     */

    private int State;
    private String IsNew;
    private String LastContent;
    private String Msg;
    private int UserId;
    private int LogClassId;
    private String LogContent;
    private String PlanContent;
    private String IsDo;
    private int IsEdit;

    public DailyEntity(int UserId, int IsEdit) {
        this.UserId = UserId;
        this.IsEdit = IsEdit;
    }

    public DailyEntity(int UserId, int LogClassId, String LogContent, String PlanContent, String IsDo, int IsEdit) {
        this.UserId = UserId;
        this.LogClassId = LogClassId;
        this.LogContent = LogContent;
        this.PlanContent = PlanContent;
        this.IsDo = IsDo;
        this.IsEdit = IsEdit;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public String getIsNew() {
        return IsNew;
    }

    public void setIsNew(String IsNew) {
        this.IsNew = IsNew;
    }

    public String getLastContent() {
        return LastContent;
    }

    public void setLastContent(String LastContent) {
        this.LastContent = LastContent;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
}
