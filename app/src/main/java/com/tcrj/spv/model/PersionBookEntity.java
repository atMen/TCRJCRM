package com.tcrj.spv.model;

import java.util.List;

/**
 * 公共通讯录
 * Created by leict on 2017/11/17.
 */

public class PersionBookEntity {

    /**
     * RecordCount : 0
     * State : 1
     * Msg : 操作成功
     * Listinfo : [{"StaffID":"1443","StaffNo":"A00389","DeptName":"项目申报组","RoleName":"项目申报组员工","StaffName":"张寒露","StaffSex":"女","Tel":"15129057216","Email":"805203223@qq.com"}]
     */

    private int RecordCount;
    private int State;
    private String Msg;
    private List<ListinfoBean> Listinfo;


    public PersionBookEntity() {
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

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<ListinfoBean> getListinfo() {
        return Listinfo;
    }

    public void setListinfo(List<ListinfoBean> Listinfo) {
        this.Listinfo = Listinfo;
    }

    public static class ListinfoBean {
        /**
         * StaffID : 1443
         * StaffNo : A00389
         * DeptName : 项目申报组
         * RoleName : 项目申报组员工
         * StaffName : 张寒露
         * StaffSex : 女
         * Tel : 15129057216
         * Email : 805203223@qq.com
         */

        private String StaffID;
        private String StaffNo;
        private String DeptName;
        private String RoleName;
        private String StaffName;
        private String StaffSex;
        private String Tel;
        private String Email;
        public String PinYin;
        public String Address;

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getPinYin() {
            return PinYin;
        }

        public void setPinYin(String pinYin) {
            PinYin = pinYin;
        }

        public String getStaffID() {
            return StaffID;
        }

        public void setStaffID(String StaffID) {
            this.StaffID = StaffID;
        }

        public String getStaffNo() {
            return StaffNo;
        }

        public void setStaffNo(String StaffNo) {
            this.StaffNo = StaffNo;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public String getStaffSex() {
            return StaffSex;
        }

        public void setStaffSex(String StaffSex) {
            this.StaffSex = StaffSex;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }
    }
}
