package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 跟进记录
 * Created by leict on 2017/11/30.
 */

public class FollowRecordEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"Time":"2016-08-29","ListtTraceInfos":[{"CTID":10444,"UName":"测试2","TraceType":"传真","TraceName":"张芳芳","Hour":"17:00","TracedResult":"喜欢上飞机商晶","Position":"电大老师","CustomerName":"陕西电大"}]}]
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private String CusId;

    private String KeyWord;
    private String ContactType;
    private String ContactStatu;
    private String TracedMaturity;
    private int CurrentIndex;
    private String OwerId ;
    private String Type ;
    private int PageSize;
    private int UId;

    public FollowRecordEntity(String cusId, String keyWord, String contactType,
                              String contactStatu, String tracedMaturity,
                              int currentIndex, String owerId, String type,
                              int pageSize, int UId) {
        CusId = cusId;
        KeyWord = keyWord;
        ContactType = contactType;
        ContactStatu = contactStatu;
        TracedMaturity = tracedMaturity;
        CurrentIndex = currentIndex;
        OwerId = owerId;
        Type = type;
        PageSize = pageSize;
        this.UId = UId;
    }

    public FollowRecordEntity(String CusId) {
        this.CusId = CusId;
    }



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
         * Time : 2016-08-29
         * ListtTraceInfos : [{"CTID":10444,"UName":"测试2","TraceType":"传真","TraceName":"张芳芳","Hour":"17:00","TracedResult":"喜欢上飞机商晶","Position":"电大老师","CustomerName":"陕西电大"}]
         */

        private String Time;
        private List<ListtTraceInfosBean> ListtTraceInfos;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public List<ListtTraceInfosBean> getListtTraceInfos() {
            return ListtTraceInfos;
        }

        public void setListtTraceInfos(List<ListtTraceInfosBean> ListtTraceInfos) {
            this.ListtTraceInfos = ListtTraceInfos;
        }

        public static class ListtTraceInfosBean {
            /**
             * CTID : 10444
             * UName : 测试2
             * TraceType : 传真
             * TraceName : 张芳芳
             * Hour : 17:00
             * TracedResult : 喜欢上飞机商晶
             * Position : 电大老师
             * CustomerName : 陕西电大
             */

            private int CTID;
            private String UName;
            private String TraceType;
            private String TraceName;
            private String Hour;
            private String TracedResult;
            private String Position;
            private String CustomerName;

            public int getCTID() {
                return CTID;
            }

            public void setCTID(int CTID) {
                this.CTID = CTID;
            }

            public String getUName() {
                return UName;
            }

            public void setUName(String UName) {
                this.UName = UName;
            }

            public String getTraceType() {
                return TraceType;
            }

            public void setTraceType(String TraceType) {
                this.TraceType = TraceType;
            }

            public String getTraceName() {
                return TraceName;
            }

            public void setTraceName(String TraceName) {
                this.TraceName = TraceName;
            }

            public String getHour() {
                return Hour;
            }

            public void setHour(String Hour) {
                this.Hour = Hour;
            }

            public String getTracedResult() {
                return TracedResult;
            }

            public void setTracedResult(String TracedResult) {
                this.TracedResult = TracedResult;
            }

            public String getPosition() {
                return Position;
            }

            public void setPosition(String Position) {
                this.Position = Position;
            }

            public String getCustomerName() {
                return CustomerName;
            }

            public void setCustomerName(String CustomerName) {
                this.CustomerName = CustomerName;
            }
        }
    }
}
