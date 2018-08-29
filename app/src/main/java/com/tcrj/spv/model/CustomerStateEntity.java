package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 客户状态
 * Created by leict on 2017/11/22.
 */

public class CustomerStateEntity implements Serializable {
    private int stateId;
    private String stateName;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
