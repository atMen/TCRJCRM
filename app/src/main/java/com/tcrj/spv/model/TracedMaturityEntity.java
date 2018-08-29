package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 客户成熟度
 * Created by leict on 2017/12/1.
 */

public class TracedMaturityEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"MaturityID":1,"MaturityName":"A+:明确成交意向","MaturityDesc":"","OrderID":1,"CMID":1}]
     */

    private int SId;

    public TracedMaturityEntity() {

    }

    public TracedMaturityEntity(int SId, String isXs) {
        this.SId = SId;
        IsXs = isXs;
    }

    private String IsXs;

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

//    public static class ListInfoBean {
//        /**
//         * MaturityID : 1
//         * MaturityName : A+:明确成交意向
//         * MaturityDesc :
//         * OrderID : 1
//         * CMID : 1
//         */
//
//        private int MaturityID;
//        private String MaturityName;
//        private String MaturityDesc;
//        private int OrderID;
//        private int CMID;
//
//        public int getMaturityID() {
//            return MaturityID;
//        }
//
//        public void setMaturityID(int MaturityID) {
//            this.MaturityID = MaturityID;
//        }
//
//        public String getMaturityName() {
//            return MaturityName;
//        }
//
//        public void setMaturityName(String MaturityName) {
//            this.MaturityName = MaturityName;
//        }
//
//        public String getMaturityDesc() {
//            return MaturityDesc;
//        }
//
//        public void setMaturityDesc(String MaturityDesc) {
//            this.MaturityDesc = MaturityDesc;
//        }
//
//        public int getOrderID() {
//            return OrderID;
//        }
//
//        public void setOrderID(int OrderID) {
//            this.OrderID = OrderID;
//        }
//
//        public int getCMID() {
//            return CMID;
//        }
//
//        public void setCMID(int CMID) {
//            this.CMID = CMID;
//        }
//    }
}
