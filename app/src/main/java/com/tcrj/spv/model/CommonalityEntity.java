package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 通用类
 * Created by leict on 2017/11/23.
 */

public class CommonalityEntity implements Serializable {
    public String type;
    public String state;
    public String isHot;
    public int provinceId;
    public int cityId;
    public int areaId;
    public String provinceName;
    public String cityName;
    public String areaName;
    public String sex;

    public String CusStatus;
    public String CusClass;
    public String IntentionPro;
    public String ReportStatus;
    public String BelongArea;
    public String OwerUserId;
    public String KeyWord;
    public String Order;
    public String Currenttype;
    public String XCode;
    public String YCode;
    public String phoneNumber;
    public String CustomerName;
    public String LxrCount;
    public List<CustomerDetailEntity.EntityBean.LxrInfoAllBean> lxrInfoall;
    public List<CustomerDetailEntity.EntityBean.LxrInfosjBean> lxrInfosj;
    public String Status;
    public String VisId;
    public int ContactID;
    public String TracedMaturity;
    public String ContactType;
    public List<PublicListEntity> itemList;
    public List<PublicListEntity> dataList;
    public CustomerDetailEntity.EntityBean entityDetails;
    public String CustomerID;
    public String InHighSeas;

    public double latitude;
    public double longitude;
    public String address;

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

    public String getCameraImg() {
        return CameraImg;
    }

    public void setCameraImg(String cameraImg) {
        CameraImg = cameraImg;
    }

    public String CameraImg = "";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getInHighSeas() {
        return InHighSeas;
    }

    public void setInHighSeas(String inHighSeas) {
        InHighSeas = inHighSeas;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public CustomerDetailEntity.EntityBean getEntityDetails() {
        return entityDetails;
    }

    public void setEntityDetails(CustomerDetailEntity.EntityBean entityDetails) {
        this.entityDetails = entityDetails;
    }

    public List<PublicListEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<PublicListEntity> itemList) {
        this.itemList = itemList;
    }

    public List<PublicListEntity> getDataList() {
        return dataList;
    }

    public void setDataList(List<PublicListEntity> dataList) {
        this.dataList = dataList;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getVisId() {
        return VisId;
    }

    public void setVisId(String visId) {
        VisId = visId;
    }

    public int getContactID() {
        return ContactID;
    }

    public void setContactID(int contactID) {
        ContactID = contactID;
    }

    public String getTracedMaturity() {
        return TracedMaturity;
    }

    public void setTracedMaturity(String tracedMaturity) {
        TracedMaturity = tracedMaturity;
    }

    public String getContactType() {
        return ContactType;
    }

    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    public String getLxrCount() {
        return LxrCount;
    }

    public void setLxrCount(String lxrCount) {
        LxrCount = lxrCount;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getYCode() {
        return YCode;
    }

    public void setYCode(String YCode) {
        this.YCode = YCode;
    }

    public String getXCode() {
        return XCode;
    }

    public void setXCode(String XCode) {
        this.XCode = XCode;
    }

    public List<CustomerDetailEntity.EntityBean.LxrInfoAllBean> getLxrInfoall() {
        return lxrInfoall;
    }

    public void setLxrInfoall(List<CustomerDetailEntity.EntityBean.LxrInfoAllBean> lxrInfoall) {
        this.lxrInfoall = lxrInfoall;
    }

    public List<CustomerDetailEntity.EntityBean.LxrInfosjBean> getLxrInfosj() {
        return lxrInfosj;
    }

    public void setLxrInfosj(List<CustomerDetailEntity.EntityBean.LxrInfosjBean> lxrInfosj) {
        this.lxrInfosj = lxrInfosj;
    }

    public String getCusStatus() {
        return CusStatus;
    }

    public void setCusStatus(String cusStatus) {
        CusStatus = cusStatus;
    }

    public String getCusClass() {
        return CusClass;
    }

    public void setCusClass(String cusClass) {
        CusClass = cusClass;
    }

    public String getIntentionPro() {
        return IntentionPro;
    }

    public void setIntentionPro(String intentionPro) {
        IntentionPro = intentionPro;
    }

    public String getReportStatus() {
        return ReportStatus;
    }

    public void setReportStatus(String reportStatus) {
        ReportStatus = reportStatus;
    }

    public String getBelongArea() {
        return BelongArea;
    }

    public void setBelongArea(String belongArea) {
        BelongArea = belongArea;
    }

    public String getOwerUserId() {
        return OwerUserId;
    }

    public void setOwerUserId(String owerUserId) {
        OwerUserId = owerUserId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getCurrenttype() {
        return Currenttype;
    }

    public void setCurrenttype(String currenttype) {
        Currenttype = currenttype;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
