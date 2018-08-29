package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 公共参数实体类
 * Created by leict on 2017/10/24.
 */

public class ParameterEntity implements Serializable {
    private String userName;    //账号
    private String userPwd;     //密码

    public String getBackSum() {
        return BackSum;
    }

    public void setBackSum(String backSum) {
        BackSum = backSum;
    }

    public String getBackTime() {
        return BackTime;
    }

    public void setBackTime(String backTime) {
        BackTime = backTime;
    }

    private String BackSum;
    private String BackTime;

    public String getLastMouth() {
        return lastMouth;
    }

    private String lastMouth;

    private int staffId;        //用户ID
    private String logContent;  //日报内容
    private String planContent; //今日计划
    private String state;       //是否完成
    private int isEdit;         //是否编辑

    private String KeyWord;     //关键字
    private int PageIndex;      //索引
    private int PageSize;       //页码
    private String StaffNo;     //用户账号

    private int DayType;        //类型
    private String OwerId;      //自己的ID
    private String DeptId;      //部门ID
    private int IsMe;           //标识
    private int CurrentIndex;   //索引

    private int type;           //类型
    private String qjqssj;      //请假起始时间
    private String qjjssj;      //请假结束时间
    private String qjsy;        //请假事由
    private String name;        //名称
    private String ygbh;        //账号
    private String qjlx;        //请假类型
    private String bumen;       //部门名称
    private double gjts;        //请假总天数
    private String FanHuiRiQi;  //返回日期
    private String JiHuaShiJian;//计划时间
    private String MuBiaoDiZhi; //目标地址
    private String QiTaZaFei;   //其他杂费（可不传）
    private String ShenQingRenBuMen;  //部门
    private String UserNo;            //账号
    private String TongXingRenYuan;   //同行人员（可不传）
    private String ZhuSuFei;          //住宿费
    private String MuBiaoJiHua;       //目标计划
    private String JiaoTongFei;       //交通费
    private String JiaoTongGongJu;    //交通工具
    private String ChuChaShenQingRen; //出差人
    private String ChuChaShiYou;      //出差事由
    private String oldPwd;            //旧密码
    private String newPwd;            //新密码
    private String CustomerName;      //客户姓名
    private String CustomerClass;     //客户分类
    private int Province;             //省份
    private int City;                 //城市
    private int Area;                 //区域
    private String Address;           //地址
    private String CustomerStatus;    //客户状态
    private String IntentionProducts; //意向产品
    private String IsHot;             //是否热点客户
    private String WriteUserID;       //当前用户ID
    private String CustomerID;        //客户ID

    public String ContactName;        //联系人姓名
    public String ContactSex;         //性别
    public String Position;           //职位
    public String MobileNumber;       //手机号
    public String PhoneNumber;        //座机
    public String MSNQQ;              //QQ/微信
    public String Email;              //邮箱
    public String BirthDay;           //生日
    public int ContactID;             //联系人ID
    private String OwerUserId;
    private String IntentionPro;
    private String Order;
    private String ReportStatus;
    private String CusClass;
    private String BelongArea;
    private int UserId;
    private String CusStatus;
    private int CurrentPageIndex;
    private String Currenttype;
    private String CusId;
    private String key;

    private String CId;      //删除的客户ID


    private String ContactDate;
    private String Status;
    private String TracedResult;
    private String VisId;
    private String TracedMaturity;
    private int CTID;
    private String ContactType;
    private String TId;
    private String CustomerId;
    private String InHighSeasReason;
    private int InHighSeasUserID;
    private String Ids;
    private int UserID;
    private int Authority;

    private int WorkId;
    private String FkNodeId;

    private String ProjectID;

    private String CameraImg;
    private String MapImg;
    private double XCoord;
    private double YCoord;
    private String SingPlace;
    private String BigMapImg;

    public String getJQNo() {
        return JQNo;
    }

    public void setJQNo(String JQNo) {
        this.JQNo = JQNo;
    }

    public int getQDQT() {
        return QDQT;
    }

    public void setQDQT(int QDQT) {
        this.QDQT = QDQT;
    }

    //外派签到
    private String JQNo;
    private int QDQT;

    private int LogItemID;
    private int LogID;
    private String LogDate;
    private int WorkNature;
    private String WorkPlace;
    private String WorkHour;
    private String Overtime;
    private String LogContent;
    private String PlanContent;

    private String ProjectName;



    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public int getLogItemID() {
        return LogItemID;
    }

    public void setLogItemID(int logItemID) {
        LogItemID = logItemID;
    }

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int logID) {
        LogID = logID;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String logDate) {
        LogDate = logDate;
    }

    public int getWorkNature() {
        return WorkNature;
    }

    public void setWorkNature(int workNature) {
        WorkNature = workNature;
    }

    public String getWorkPlace() {
        return WorkPlace;
    }

    public void setWorkPlace(String workPlace) {
        WorkPlace = workPlace;
    }

    public String getWorkHour() {
        return WorkHour;
    }

    public void setWorkHour(String workHour) {
        WorkHour = workHour;
    }

    public String getOvertime() {
        return Overtime;
    }

    public void setOvertime(String overtime) {
        Overtime = overtime;
    }

    public String getCameraImg() {
        return CameraImg;
    }

    public void setCameraImg(String cameraImg) {
        CameraImg = cameraImg;
    }

    public String getMapImg() {
        return MapImg;
    }

    public void setMapImg(String mapImg) {
        MapImg = mapImg;
    }

    public double getXCoord() {
        return XCoord;
    }

    public void setXCoord(double XCoord) {
        this.XCoord = XCoord;
    }

    public double getYCoord() {
        return YCoord;
    }

    public void setYCoord(double YCoord) {
        this.YCoord = YCoord;
    }

    public String getSingPlace() {
        return SingPlace;
    }

    public void setSingPlace(String singPlace) {
        SingPlace = singPlace;
    }

    public String getBigMapImg() {
        return BigMapImg;
    }

    public void setBigMapImg(String bigMapImg) {
        BigMapImg = bigMapImg;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public int getWorkId() {
        return WorkId;
    }

    public void setWorkId(int workId) {
        WorkId = workId;
    }

    public String getFkNodeId() {
        return FkNodeId;
    }

    public void setFkNodeId(String fkNodeId) {
        FkNodeId = fkNodeId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAuthority() {
        return Authority;
    }

    public void setAuthority(int authority) {
        Authority = authority;
    }

    public String getIds() {
        return Ids;
    }

    public void setIds(String ids) {
        Ids = ids;
    }

    public int getInHighSeasUserID() {
        return InHighSeasUserID;
    }

    public void setInHighSeasUserID(int inHighSeasUserID) {
        InHighSeasUserID = inHighSeasUserID;
    }

    public String getInHighSeasReason() {
        return InHighSeasReason;
    }

    public void setInHighSeasReason(String inHighSeasReason) {
        InHighSeasReason = inHighSeasReason;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getTId() {
        return TId;
    }

    public void setTId(String TId) {
        this.TId = TId;
    }

    public String getContactDate() {
        return ContactDate;
    }

    public void setContactDate(String contactDate) {
        ContactDate = contactDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTracedResult() {
        return TracedResult;
    }

    public void setTracedResult(String tracedResult) {
        TracedResult = tracedResult;
    }

    public String getVisId() {
        return VisId;
    }

    public void setVisId(String visId) {
        VisId = visId;
    }

    public String getTracedMaturity() {
        return TracedMaturity;
    }

    public void setTracedMaturity(String tracedMaturity) {
        TracedMaturity = tracedMaturity;
    }

    public int getCTID() {
        return CTID;
    }

    public void setCTID(int CTID) {
        this.CTID = CTID;
    }

    public String getContactType() {
        return ContactType;
    }

    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwerUserId() {
        return OwerUserId;
    }

    public void setOwerUserId(String owerUserId) {
        OwerUserId = owerUserId;
    }

    public String getIntentionPro() {
        return IntentionPro;
    }

    public void setIntentionPro(String intentionPro) {
        IntentionPro = intentionPro;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getReportStatus() {
        return ReportStatus;
    }

    public void setReportStatus(String reportStatus) {
        ReportStatus = reportStatus;
    }

    public String getCusClass() {
        return CusClass;
    }

    public void setCusClass(String cusClass) {
        CusClass = cusClass;
    }

    public String getBelongArea() {
        return BelongArea;
    }

    public void setBelongArea(String belongArea) {
        BelongArea = belongArea;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCusStatus() {
        return CusStatus;
    }

    public void setCusStatus(String cusStatus) {
        CusStatus = cusStatus;
    }

    public int getCurrentPageIndex() {
        return CurrentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        CurrentPageIndex = currentPageIndex;
    }

    public String getCurrenttype() {
        return Currenttype;
    }

    public void setCurrenttype(String currenttype) {
        Currenttype = currenttype;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactSex() {
        return ContactSex;
    }

    public void setContactSex(String contactSex) {
        ContactSex = contactSex;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMSNQQ() {
        return MSNQQ;
    }

    public void setMSNQQ(String MSNQQ) {
        this.MSNQQ = MSNQQ;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public int getContactID() {
        return ContactID;
    }

    public void setContactID(int contactID) {
        ContactID = contactID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerClass() {
        return CustomerClass;
    }

    public void setCustomerClass(String customerClass) {
        CustomerClass = customerClass;
    }

    public int getProvince() {
        return Province;
    }

    public void setProvince(int province) {
        Province = province;
    }

    public int getCity() {
        return City;
    }

    public void setCity(int city) {
        City = city;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCustomerStatus() {
        return CustomerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        CustomerStatus = customerStatus;
    }

    public String getIntentionProducts() {
        return IntentionProducts;
    }

    public void setIntentionProducts(String intentionProducts) {
        IntentionProducts = intentionProducts;
    }

    public String getIsHot() {
        return IsHot;
    }

    public void setIsHot(String isHot) {
        IsHot = isHot;
    }

    public String getWriteUserID() {
        return WriteUserID;
    }

    public void setWriteUserID(String writeUserID) {
        WriteUserID = writeUserID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getMuBiaoJiHua() {
        return MuBiaoJiHua;
    }

    public void setMuBiaoJiHua(String muBiaoJiHua) {
        MuBiaoJiHua = muBiaoJiHua;
    }

    public String getJiaoTongFei() {
        return JiaoTongFei;
    }

    public void setJiaoTongFei(String jiaoTongFei) {
        JiaoTongFei = jiaoTongFei;
    }

    public String getJiaoTongGongJu() {
        return JiaoTongGongJu;
    }

    public void setJiaoTongGongJu(String jiaoTongGongJu) {
        JiaoTongGongJu = jiaoTongGongJu;
    }

    public String getChuChaShenQingRen() {
        return ChuChaShenQingRen;
    }

    public void setChuChaShenQingRen(String chuChaShenQingRen) {
        ChuChaShenQingRen = chuChaShenQingRen;
    }

    public String getChuChaShiYou() {
        return ChuChaShiYou;
    }

    public void setChuChaShiYou(String chuChaShiYou) {
        ChuChaShiYou = chuChaShiYou;
    }

    public String getFanHuiRiQi() {
        return FanHuiRiQi;
    }

    public void setFanHuiRiQi(String fanHuiRiQi) {
        FanHuiRiQi = fanHuiRiQi;
    }

    public String getJiHuaShiJian() {
        return JiHuaShiJian;
    }

    public void setJiHuaShiJian(String jiHuaShiJian) {
        JiHuaShiJian = jiHuaShiJian;
    }

    public String getMuBiaoDiZhi() {
        return MuBiaoDiZhi;
    }

    public void setMuBiaoDiZhi(String muBiaoDiZhi) {
        MuBiaoDiZhi = muBiaoDiZhi;
    }

    public String getQiTaZaFei() {
        return QiTaZaFei;
    }

    public void setQiTaZaFei(String qiTaZaFei) {
        QiTaZaFei = qiTaZaFei;
    }

    public String getShenQingRenBuMen() {
        return ShenQingRenBuMen;
    }

    public void setShenQingRenBuMen(String shenQingRenBuMen) {
        ShenQingRenBuMen = shenQingRenBuMen;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String userNo) {
        UserNo = userNo;
    }

    public String getTongXingRenYuan() {
        return TongXingRenYuan;
    }

    public void setTongXingRenYuan(String tongXingRenYuan) {
        TongXingRenYuan = tongXingRenYuan;
    }

    public String getZhuSuFei() {
        return ZhuSuFei;
    }

    public void setZhuSuFei(String zhuSuFei) {
        ZhuSuFei = zhuSuFei;
    }

    public String getQjsy() {
        return qjsy;
    }

    public void setQjsy(String qjsy) {
        this.qjsy = qjsy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYgbh() {
        return ygbh;
    }

    public void setYgbh(String ygbh) {
        this.ygbh = ygbh;
    }

    public String getQjlx() {
        return qjlx;
    }

    public void setQjlx(String qjlx) {
        this.qjlx = qjlx;
    }

    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

    public double getGjts() {
        return gjts;
    }

    public void setGjts(double gjts) {
        this.gjts = gjts;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQjqssj() {
        return qjqssj;
    }

    public void setQjqssj(String qjqssj) {
        this.qjqssj = qjqssj;
    }

    public String getQjjssj() {
        return qjjssj;
    }

    public void setQjjssj(String qjjssj) {
        this.qjjssj = qjjssj;
    }

    public int getCurrentIndex() {
        return CurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        CurrentIndex = currentIndex;
    }

    public int getDayType() {
        return DayType;
    }

    public void setDayType(int dayType) {
        DayType = dayType;
    }

    public String getOwerId() {
        return OwerId;
    }

    public void setOwerId(String owerId) {
        OwerId = owerId;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public int getIsMe() {
        return IsMe;
    }

    public void setIsMe(int isMe) {
        IsMe = isMe;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public String getStaffNo() {
        return StaffNo;
    }

    public void setStaffNo(String staffNo) {
        StaffNo = staffNo;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(int isEdit) {
        this.isEdit = isEdit;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setLastMouth(String lastMouth) {
        this.lastMouth = lastMouth;
    }
}
