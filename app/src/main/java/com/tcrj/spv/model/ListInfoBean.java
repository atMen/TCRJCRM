package com.tcrj.spv.model;

/**
 * Created by leict on 2017/12/25.
 */

public class ListInfoBean {


    /**
     * MaturityID : 1
     * MaturityName : A+:明确成交意向
     * MaturityDesc :
     * OrderID : 1
     * CMID : 1
     */

    private int MaturityID;
    private String MaturityName;
    private String MaturityDesc;
    private int OrderID;
    private int CMID;

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    private int Index = 0;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String ID;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    private String Sid;


    public int getMaturityID() {
        return MaturityID;
    }

    public void setMaturityID(int MaturityID) {
        this.MaturityID = MaturityID;
    }

    public String getMaturityName() {
        return MaturityName;
    }

    public void setMaturityName(String MaturityName) {
        this.MaturityName = MaturityName;
    }

    public String getMaturityDesc() {
        return MaturityDesc;
    }

    public void setMaturityDesc(String MaturityDesc) {
        this.MaturityDesc = MaturityDesc;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCMID() {
        return CMID;
    }

    public void setCMID(int CMID) {
        this.CMID = CMID;
    }
}
