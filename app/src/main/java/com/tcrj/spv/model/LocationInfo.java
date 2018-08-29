package com.tcrj.spv.model;

/**
 * Created by leict on 2018/3/23.
 */

public class LocationInfo {

    /**
     * State : 1
     * Msg : 操作成功
     */

    private int State;
    private String Msg;

    public LocationInfo() {
    }

//    {
//        Address = "陕西省西安市雁塔区锦业一路靠近嘉昱大厦",
//                Y = "108.870397",
//                AddDatetime = "2018-03-23 11:20:07",
//                StaffID = 182,
//                X = "34.190617",
//    }

    private String Address;
    private String Y;
    private String X;
    private int StaffID;

    public LocationInfo(String address, String y, String addDatetime, int staffID,String x) {
        Address = address;
        Y = y;
        X = x;
        StaffID = staffID;
        AddDatetime = addDatetime;
    }



    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int staffID) {
        StaffID = staffID;
    }

    public String getAddDatetime() {
        return AddDatetime;
    }

    public void setAddDatetime(String addDatetime) {
        AddDatetime = addDatetime;
    }

    private String AddDatetime;


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
