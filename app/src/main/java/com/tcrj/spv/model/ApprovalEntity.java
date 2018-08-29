package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 审批
 * Created by leict on 2017/11/3.
 */

public class ApprovalEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"FlowType":"出差申请","ApplyTime":"2017-10-27 10:07","ApplyPerson":"测试1","FkNodeId":"2401","FkNodeName":"出差申请","SurplusTime":0,"WorkId":5601,"FkFlow":"024","FId":0,"IsRead":false,"Paras":"","DUrl":"http://113.200.26.66:8000/WF/MyFlow.aspx"}]
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private String StaffNo;

    public ApprovalEntity(String StaffNo) {
        this.StaffNo = StaffNo;
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
         * FlowType : 出差申请
         * ApplyTime : 2017-10-27 10:07
         * ApplyPerson : 测试1
         * FkNodeId : 2401
         * FkNodeName : 出差申请
         * SurplusTime : 0
         * WorkId : 5601
         * FkFlow : 024
         * FId : 0
         * IsRead : false
         * Paras :
         * DUrl : http://113.200.26.66:8000/WF/MyFlow.aspx
         */

        private String FlowType;
        private String ApplyTime;
        private String ApplyPerson;
        private String FkNodeId;
        private String FkNodeName;
        private int SurplusTime;
        private int WorkId;
        private String FkFlow;
        private int FId;
        private boolean IsRead;
        private String Paras;
        private String DUrl;
        private String State;

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getFlowType() {
            return FlowType;
        }

        public void setFlowType(String FlowType) {
            this.FlowType = FlowType;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public String getApplyPerson() {
            return ApplyPerson;
        }

        public void setApplyPerson(String ApplyPerson) {
            this.ApplyPerson = ApplyPerson;
        }

        public String getFkNodeId() {
            return FkNodeId;
        }

        public void setFkNodeId(String FkNodeId) {
            this.FkNodeId = FkNodeId;
        }

        public String getFkNodeName() {
            return FkNodeName;
        }

        public void setFkNodeName(String FkNodeName) {
            this.FkNodeName = FkNodeName;
        }

        public int getSurplusTime() {
            return SurplusTime;
        }

        public void setSurplusTime(int SurplusTime) {
            this.SurplusTime = SurplusTime;
        }

        public int getWorkId() {
            return WorkId;
        }

        public void setWorkId(int WorkId) {
            this.WorkId = WorkId;
        }

        public String getFkFlow() {
            return FkFlow;
        }

        public void setFkFlow(String FkFlow) {
            this.FkFlow = FkFlow;
        }

        public int getFId() {
            return FId;
        }

        public void setFId(int FId) {
            this.FId = FId;
        }

        public boolean isIsRead() {
            return IsRead;
        }

        public void setIsRead(boolean IsRead) {
            this.IsRead = IsRead;
        }

        public String getParas() {
            return Paras;
        }

        public void setParas(String Paras) {
            this.Paras = Paras;
        }

        public String getDUrl() {
            return DUrl;
        }

        public void setDUrl(String DUrl) {
            this.DUrl = DUrl;
        }
    }
}
