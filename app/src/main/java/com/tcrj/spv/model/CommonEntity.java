package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * Created by leict on 2017/10/27.
 */

public class CommonEntity implements Serializable {
    private String isCheck;
    private String PID;
    private int workid;

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
