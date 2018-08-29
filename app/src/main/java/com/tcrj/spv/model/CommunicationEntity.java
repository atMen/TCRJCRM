package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 通讯录详情
 * Created by leict on 2017/11/20.
 */

public class CommunicationEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"StaffNo":"A00132","StaffName":"梁美娜","StaffSex":"女","Birthday":"1992-05-16","DeptName":"综合部","RoleName":"综合部员工","Address":"西安市碑林区","Email":"18821796400@163.com","TelNo":"","MoblieTel":"18821796400"}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private String StaffId;

    public CommunicationEntity(String StaffId) {
        this.StaffId = StaffId;
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

    public EntityBean getEntity() {
        return Entity;
    }

    public void setEntity(EntityBean Entity) {
        this.Entity = Entity;
    }

    public static class EntityBean {
        /**
         * StaffNo : A00132
         * StaffName : 梁美娜
         * StaffSex : 女
         * Birthday : 1992-05-16
         * DeptName : 综合部
         * RoleName : 综合部员工
         * Address : 西安市碑林区
         * Email : 18821796400@163.com
         * TelNo :
         * MoblieTel : 18821796400
         */

        private String StaffNo;
        private String StaffName;
        private String StaffSex;
        private String Birthday;
        private String DeptName;
        private String RoleName;
        private String Address;
        private String Email;
        private String TelNo;
        private String MoblieTel;

        public String getStaffNo() {
            return StaffNo;
        }

        public void setStaffNo(String StaffNo) {
            this.StaffNo = StaffNo;
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

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
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

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getTelNo() {
            return TelNo;
        }

        public void setTelNo(String TelNo) {
            this.TelNo = TelNo;
        }

        public String getMoblieTel() {
            return MoblieTel;
        }

        public void setMoblieTel(String MoblieTel) {
            this.MoblieTel = MoblieTel;
        }
    }
}
