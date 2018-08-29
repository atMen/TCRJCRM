package com.tcrj.spv.model;

import java.util.List;

/**
 * Created by leict on 2017/10/31.
 */

public class NoticeEntity {
    private String KeyWord;
    private int PageSize;
    private int CurrentPageIndex;
    /**
     * State : 1
     * Msg : 操作成功
     * recordCount : 21
     * ListInfo : [{"Id":35,"Title":"关于国庆节放假时间安排的通知","ReleseTime":"2016-09-27 15:03:56","StaffName":"张海艳","ReceivingObject":"企业"}]
     */

    private int State;
    private String Msg;
    private int recordCount;
    private List<ListInfoBean> ListInfo;

    public NoticeEntity() {
    }

    public NoticeEntity(String KeyWord, int PageSize, int CurrentPageIndex) {
        this.KeyWord = KeyWord;
        this.PageSize = PageSize;
        this.CurrentPageIndex = CurrentPageIndex;
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

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * Id : 35
         * Title : 关于国庆节放假时间安排的通知
         * ReleseTime : 2016-09-27 15:03:56
         * StaffName : 张海艳
         * ReceivingObject : 企业
         */

        private int Id;
        private String Title;
        private String ReleseTime;
        private String StaffName;
        private String ReceivingObject;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getReleseTime() {
            return ReleseTime;
        }

        public void setReleseTime(String ReleseTime) {
            this.ReleseTime = ReleseTime;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public String getReceivingObject() {
            return ReceivingObject;
        }

        public void setReceivingObject(String ReceivingObject) {
            this.ReceivingObject = ReceivingObject;
        }
    }
}
