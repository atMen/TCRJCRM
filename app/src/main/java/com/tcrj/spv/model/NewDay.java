package com.tcrj.spv.model;

/**
 * Created by leict on 2018/1/3.
 */

public class NewDay {

    /**
     * State : 1
     * Msg : 昨日日报未编写
     * LogDate : 2018-01-02
     */

    private int State;
    private String Msg;
    private String LogDate;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    private int UserID;


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

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String LogDate) {
        this.LogDate = LogDate;
    }

    public NewDay(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "NewDay{" +
                "State=" + State +
                ", Msg='" + Msg + '\'' +
                ", LogDate='" + LogDate + '\'' +
                '}';
    }
}
