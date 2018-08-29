package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2017/12/16.
 */

public class NewWorkDailyEntity implements Serializable {

    /**
     * State : 1
     * Msg :
     * Data : [{"ID":2,"PID":1,"Code":"1","Val":"项目","Status":1,"Sort":1,"Hierarchy":2,"Path":"-1-"}]
     */

    private int State;
    private String Msg;
    private List<DataBean> Data;
    public String ID;
    public String PID;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public NewWorkDailyEntity(String ID, String PID) {
        this.ID = ID;
        this.PID = PID;
    }

    public static class DataBean {
        /**
         * ID : 2
         * PID : 1
         * Code : 1
         * Val : 项目
         * Status : 1
         * Sort : 1
         * Hierarchy : 2
         * Path : -1-
         */

        private int ID;
        private int PID;
        private String Code;
        private String Val;
        private int Status;
        private int Sort;
        private int Hierarchy;
        private String Path;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getPID() {
            return PID;
        }

        public void setPID(int PID) {
            this.PID = PID;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getVal() {
            return Val;
        }

        public void setVal(String Val) {
            this.Val = Val;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public int getHierarchy() {
            return Hierarchy;
        }

        public void setHierarchy(int Hierarchy) {
            this.Hierarchy = Hierarchy;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }
    }
}
