package com.tcrj.spv.model;

/**
 * Created by leict on 2018/1/4.
 */

public class Qdao {

    /**
     * State : 1
     * Msg : 操作成功
     */

    private int State;
    private String Msg;

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
}
