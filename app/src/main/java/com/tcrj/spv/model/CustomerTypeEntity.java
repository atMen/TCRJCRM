package com.tcrj.spv.model;

import java.util.List;

/**
 * 客户分类
 * Created by leict on 2017/11/22.
 */

public class CustomerTypeEntity {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"CustomerClassID":1,"CustomerClassName":"直销客户","CustomerClassDesc":"原为普通分类客户","OrderID":1,"CMID":1}]
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;

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

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * CustomerClassID : 1
         * CustomerClassName : 直销客户
         * CustomerClassDesc : 原为普通分类客户
         * OrderID : 1
         * CMID : 1
         */

        private int CustomerClassID;
        private String CustomerClassName;
        private String CustomerClassDesc;
        private int OrderID;
        private int CMID;

        public int getCustomerClassID() {
            return CustomerClassID;
        }

        public void setCustomerClassID(int CustomerClassID) {
            this.CustomerClassID = CustomerClassID;
        }

        public String getCustomerClassName() {
            return CustomerClassName;
        }

        public void setCustomerClassName(String CustomerClassName) {
            this.CustomerClassName = CustomerClassName;
        }

        public String getCustomerClassDesc() {
            return CustomerClassDesc;
        }

        public void setCustomerClassDesc(String CustomerClassDesc) {
            this.CustomerClassDesc = CustomerClassDesc;
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
}
