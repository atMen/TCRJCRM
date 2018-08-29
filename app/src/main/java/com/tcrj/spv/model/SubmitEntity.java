package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 客户
 * Created by leict on 2017/11/22.
 */

public class SubmitEntity implements Serializable {
    public String WriteUserID;
    public String CustomerID;
    public String ContactName;
    public String ContactSex;
    public String Position;
    public String MobileNumber;
    public String PhoneNumber;
    public String MSNQQ;
    public String Email;
    public String BirthDay;
    public int ContactID;
    public int UId;
    public String CId;
    private String ContactDate;
    private String Status;
    private String TracedResult;
    private String VisId;
    private String TracedMaturity;
    private int CTID;
    private String ContactType;
    private String CustomerId;
    private String InHighSeasReason;
    private int InHighSeasUserID;
    private String Ids;
    private int StaffId;
    private int type;
    private int UserID;
    private int Authority;
    private String ProjectID;
    private String LogDate;

    private String CusId;
    private String CameraImg;
    private String MapImg;
    private double XCoord;
    private double YCoord;
    private String SingPlace;
    private String BigMapImg;

    public int LogItemID;
    public int LogID;
    public int WorkNature;
    public String WorkPlace;
    public String WorkHour;
    public String Overtime;
    public String LogContent;


//    外派签到
    private String JQNo;
    private int QDQT;
    private String Address;
    private int StaffID;
    public SubmitEntity(String JQNo, int QDQT, String address, int staffID) {
        this.JQNo = JQNo;
        this.QDQT = QDQT;
        Address = address;
        StaffID = staffID;
    }


    public SubmitEntity() {

    }



    public SubmitEntity(int logItemID, int logID, int userID, String logDate, int workNature, String projectID, String workPlace, String workHour, String overtime, String logContent, String planContent) {
        this.LogItemID = logItemID;
        this.LogID = logID;
        this.UserID = userID;
        this.LogDate = logDate;
        this.WorkNature = workNature;
        this.ProjectID = projectID;
        this.WorkPlace = workPlace;
        this.WorkHour = workHour;
        this.Overtime = overtime;
        this.LogContent = logContent;
        this.PlanContent = planContent;
    }

    public SubmitEntity(int staffId, String cusId, String cameraImg, String mapImg, double XCoord, double YCoord, String singPlace, String bigMapImg) {
        this.StaffId = staffId;
        this.CusId = cusId;
        this.CameraImg = cameraImg;
        this.MapImg = mapImg;
        this.XCoord = XCoord;
        this.YCoord = YCoord;
        this.SingPlace = singPlace;
        this.BigMapImg = bigMapImg;
    }

    public SubmitEntity(String InHighSeasReason, int InHighSeasUserID, String Ids) {
        this.InHighSeasReason = InHighSeasReason;
        this.InHighSeasUserID = InHighSeasUserID;
        this.Ids = Ids;
    }

    public SubmitEntity(int UId, String CId) {
        this.UId = UId;
        this.CId = CId;
    }

    public SubmitEntity(int ContactID) {
        this.ContactID = ContactID;
    }

    public SubmitEntity(int StaffId, int type) {
        this.StaffId = StaffId;
        this.type = type;
    }

    public SubmitEntity(String ProjectID, int UserID, int WorkNature, String LogDate) {
        this.ProjectID = ProjectID;
        this.UserID = UserID;
        this.WorkNature = WorkNature;
        this.LogDate = LogDate;
    }

    public SubmitEntity(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public SubmitEntity(int CTID, String ContactType, int UID) {
        this.CTID = CTID;
        this.UId = UID;
        this.ContactType = ContactType;
    }

    public SubmitEntity(String CustomerID, int UserID, int Authority) {
        this.CustomerID = CustomerID;
        this.UserID = UserID;
        this.Authority = Authority;
    }

    public SubmitEntity(String contactDate, String writeUserID, String status, String tracedResult, String visId, String customerID, int contactID, String tracedMaturity, int CTID, String contactType) {
        this.ContactDate = contactDate;
        this.WriteUserID = writeUserID;
        this.Status = status;
        this.TracedResult = tracedResult;
        this.VisId = visId;
        this.CustomerID = customerID;
        this.ContactID = contactID;
        this.TracedMaturity = tracedMaturity;
        this.CTID = CTID;
        this.ContactType = contactType;
    }

    /**
     * 录入联系人信息
     *
     * @param customerID
     * @param contactName
     * @param contactSex
     * @param position
     * @param mobileNumber
     * @param phoneNumber
     * @param MSNQQ
     * @param email
     * @param birthDay
     * @param writeUserID
     * @param contactID
     */
    public SubmitEntity(String customerID, String contactName, String contactSex, String position, String mobileNumber, String phoneNumber, String MSNQQ, String email, String birthDay, String writeUserID, int contactID) {
        this.CustomerID = customerID;
        this.ContactName = contactName;
        this.ContactSex = contactSex;
        this.Position = position;
        this.MobileNumber = mobileNumber;
        this.PhoneNumber = phoneNumber;
        this.MSNQQ = MSNQQ;
        this.Email = email;
        this.BirthDay = birthDay;
        this.WriteUserID = writeUserID;
        this.ContactID = contactID;
    }

    private int State;
    private String Msg;
    private String PlanContent;

    public String getPlanContent() {
        return PlanContent;
    }

    public void setPlanContent(String planContent) {
        PlanContent = planContent;
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
}
