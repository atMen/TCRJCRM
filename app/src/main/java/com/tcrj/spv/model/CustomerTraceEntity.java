package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 客户跟进记录：详情
 * Created by leict on 2017/12/4.
 */

public class CustomerTraceEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"CtId":"16107","CustomerID":"7539","CustomerName":"测试1","StaffName":"测试2","StaffTel":"","ContactId":"11019","ContactDate":"2017-12-01 17:21:00","ContactName":"wewqe","Position":"aa","ContactTypeId":"2","ContactType":"电话","Status":"计划中","StatusId":"2","MaturityId":"4","MaturityName":"B-:关键人成交意向不确定","VisitId":"","SingPlace":"","XCoord":"","YCoord":"","MapImg":"","TraceResult":"测试"}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private String TId;

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

    public EntityBean getEntity() {
        return Entity;
    }

    public void setEntity(EntityBean Entity) {
        this.Entity = Entity;
    }

    public CustomerTraceEntity(String TId) {
        this.TId = TId;
    }

    public static class EntityBean {
        /**
         * CtId : 16107
         * CustomerID : 7539
         * CustomerName : 测试1
         * StaffName : 测试2
         * StaffTel :
         * ContactId : 11019
         * ContactDate : 2017-12-01 17:21:00
         * ContactName : wewqe
         * Position : aa
         * ContactTypeId : 2
         * ContactType : 电话
         * Status : 计划中
         * StatusId : 2
         * MaturityId : 4
         * MaturityName : B-:关键人成交意向不确定
         * VisitId :
         * SingPlace :
         * XCoord :
         * YCoord :
         * MapImg :
         * TraceResult : 测试
         */

        private String CtId;
        private String CustomerID;
        private String CustomerName;
        private String StaffName;
        private String StaffTel;
        private String ContactId;
        private String ContactDate;
        private String ContactName;
        private String Position;
        private String ContactTypeId;
        private String ContactType;
        private String Status;
        private String StatusId;
        private String MaturityId;
        private String MaturityName;
        private String VisitId;
        private String SingPlace;
        private String XCoord;
        private String YCoord;
        private String MapImg;
        private String TraceResult;

        public String getCtId() {
            return CtId;
        }

        public void setCtId(String CtId) {
            this.CtId = CtId;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String CustomerID) {
            this.CustomerID = CustomerID;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public String getStaffTel() {
            return StaffTel;
        }

        public void setStaffTel(String StaffTel) {
            this.StaffTel = StaffTel;
        }

        public String getContactId() {
            return ContactId;
        }

        public void setContactId(String ContactId) {
            this.ContactId = ContactId;
        }

        public String getContactDate() {
            return ContactDate;
        }

        public void setContactDate(String ContactDate) {
            this.ContactDate = ContactDate;
        }

        public String getContactName() {
            return ContactName;
        }

        public void setContactName(String ContactName) {
            this.ContactName = ContactName;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public String getContactTypeId() {
            return ContactTypeId;
        }

        public void setContactTypeId(String ContactTypeId) {
            this.ContactTypeId = ContactTypeId;
        }

        public String getContactType() {
            return ContactType;
        }

        public void setContactType(String ContactType) {
            this.ContactType = ContactType;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getStatusId() {
            return StatusId;
        }

        public void setStatusId(String StatusId) {
            this.StatusId = StatusId;
        }

        public String getMaturityId() {
            return MaturityId;
        }

        public void setMaturityId(String MaturityId) {
            this.MaturityId = MaturityId;
        }

        public String getMaturityName() {
            return MaturityName;
        }

        public void setMaturityName(String MaturityName) {
            this.MaturityName = MaturityName;
        }

        public String getVisitId() {
            return VisitId;
        }

        public void setVisitId(String VisitId) {
            this.VisitId = VisitId;
        }

        public String getSingPlace() {
            return SingPlace;
        }

        public void setSingPlace(String SingPlace) {
            this.SingPlace = SingPlace;
        }

        public String getXCoord() {
            return XCoord;
        }

        public void setXCoord(String XCoord) {
            this.XCoord = XCoord;
        }

        public String getYCoord() {
            return YCoord;
        }

        public void setYCoord(String YCoord) {
            this.YCoord = YCoord;
        }

        public String getMapImg() {
            return MapImg;
        }

        public void setMapImg(String MapImg) {
            this.MapImg = MapImg;
        }

        public String getTraceResult() {
            return TraceResult;
        }

        public void setTraceResult(String TraceResult) {
            this.TraceResult = TraceResult;
        }
    }
}
