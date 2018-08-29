package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 获取客户信息列表
 * Created by leict on 2017/11/24.
 */

public class CustomerInfoEntity implements Serializable {

    /**
     * ConCounts : 22
     * ListInfo : [{"Name":"jjj","Status":"潜在客户","IsHave":"1","Cid":"7543"}]
     * Msg : 操作成功
     * RecordCount : 33
     * State : 1
     */

    private int ConCounts;
    private String Msg;
    private int RecordCount;
    private int State;
    private List<ListInfoBean> ListInfo;
    private String OwerUserId;
    private String IntentionPro;
    private int PageSize;
    private String KeyWord;
    private String Order;
    private String ReportStatus;
    private String CusClass;
    private String BelongArea;
    private int UserId;
    private String CusStatus;
    private int CurrentPageIndex;
    private String Currenttype;
    private String IsHot;

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    private String datatype;

    public CustomerInfoEntity(String owerUserId, String intentionPro, int pageSize, String keyWord, String order, String reportStatus, String cusClass, String belongArea, int userId, String cusStatus, int currentPageIndex, String currenttype, String isHot, String datatype) {
        this.OwerUserId = owerUserId;
        this.IntentionPro = intentionPro;
        this.PageSize = pageSize;
        this.KeyWord = keyWord;
        this.Order = order;
        this.ReportStatus = reportStatus;
        this.CusClass = cusClass;
        this.BelongArea = belongArea;
        this.UserId = userId;
        this.CusStatus = cusStatus;
        this.CurrentPageIndex = currentPageIndex;
        this.Currenttype = currenttype;
        this.IsHot = isHot;
        this.datatype = datatype;
    }

    public int getConCounts() {
        return ConCounts;
    }

    public void setConCounts(int ConCounts) {
        this.ConCounts = ConCounts;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int RecordCount) {
        this.RecordCount = RecordCount;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * Name : jjj
         * Status : 潜在客户
         * IsHave : 1
         * Cid : 7543
         */

        private String Name;
        private String Status;
        private String IsHave;
        private String Cid;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getIsHave() {
            return IsHave;
        }

        public void setIsHave(String IsHave) {
            this.IsHave = IsHave;
        }

        public String getCid() {
            return Cid;
        }

        public void setCid(String Cid) {
            this.Cid = Cid;
        }
    }
}
