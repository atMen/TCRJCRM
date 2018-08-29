package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * Created by leict on 2016/4/7.
 */
public class NewFollowRecordEntity implements Serializable {
    private String ID;
    private String Name;
    private int Index = 0;
    private String Sid;


    private String CtId;
    private String CustomerName;

    private String StaffName;
    private String StaffTel;
    private String ContactDate;
    private String ContactName;
    private String Position;
    private String ContactType;
    private String ContactTypeId;
    private String Status;
    private String StatusId;
    private String MaturityName;
    private String SingPlace;
    private String XCoord;
    private String YCoord;
    private String MapImg;
    private String TraceResult;
    private String CustomerID;
    private String ContactID;
    private String MaturityId;
    private String VisitId;

    public String getVisitId() {
        return VisitId;
    }

    public void setVisitId(String visitId) {
        VisitId = visitId;
    }

    public String getMaturityId() {
        return MaturityId;
    }

    public void setMaturityId(String maturityId) {
        MaturityId = maturityId;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getContactTypeId() {
        return ContactTypeId;
    }

    public void setContactTypeId(String contactTypeId) {
        ContactTypeId = contactTypeId;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String statusId) {
        StatusId = statusId;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCtId() {
        return CtId;
    }

    public void setCtId(String ctId) {
        CtId = ctId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public String getStaffTel() {
        return StaffTel;
    }

    public void setStaffTel(String staffTel) {
        StaffTel = staffTel;
    }

    public String getContactDate() {
        return ContactDate;
    }

    public void setContactDate(String contactDate) {
        ContactDate = contactDate;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getContactType() {
        return ContactType;
    }

    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    public String getMaturityName() {
        return MaturityName;
    }

    public void setMaturityName(String maturityName) {
        MaturityName = maturityName;
    }

    public String getSingPlace() {
        return SingPlace;
    }

    public void setSingPlace(String singPlace) {
        SingPlace = singPlace;
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

    public void setMapImg(String mapImg) {
        MapImg = mapImg;
    }

    public String getTraceResult() {
        return TraceResult;
    }

    public void setTraceResult(String traceResult) {
        TraceResult = traceResult;
    }

    public NewFollowRecordEntity() {

    }

    public NewFollowRecordEntity(String id, String name, int index, String sid) {
        this.ID = id;
        this.Name = name;
        this.Index = index;
        this.Sid = sid;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }
}
