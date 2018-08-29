package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 联系人详情
 * Created by leict on 2017/11/28.
 */

public class ContactDetailEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"ConId":11012,"CusId":"7551","CusName":"Dsds","CName":"Ddsds","Sex":"女","Job":"Dsds","Tel":"17895123432","ZTel":"ds","Email":"12321@qq.com","Qq":"321312","Brithday":"2018-11-24","StaffName":"测试2","UpdateTime":"2017/11/24 14:28:12","StafffPhone":""}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private int ConId;

    public ContactDetailEntity(int ConId) {
        this.ConId = ConId;
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
         * ConId : 11012
         * CusId : 7551
         * CusName : Dsds
         * CName : Ddsds
         * Sex : 女
         * Job : Dsds
         * Tel : 17895123432
         * ZTel : ds
         * Email : 12321@qq.com
         * Qq : 321312
         * Brithday : 2018-11-24
         * StaffName : 测试2
         * UpdateTime : 2017/11/24 14:28:12
         * StafffPhone :
         */

        private int ConId;
        private String CusId;
        private String CusName;
        private String CName;
        private String Sex;
        private String Job;
        private String Tel;
        private String ZTel;
        private String Email;
        private String Qq;
        private String Brithday;
        private String StaffName;
        private String UpdateTime;
        private String StafffPhone;

        public int getConId() {
            return ConId;
        }

        public void setConId(int ConId) {
            this.ConId = ConId;
        }

        public String getCusId() {
            return CusId;
        }

        public void setCusId(String CusId) {
            this.CusId = CusId;
        }

        public String getCusName() {
            return CusName;
        }

        public void setCusName(String CusName) {
            this.CusName = CusName;
        }

        public String getCName() {
            return CName;
        }

        public void setCName(String CName) {
            this.CName = CName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getJob() {
            return Job;
        }

        public void setJob(String Job) {
            this.Job = Job;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getZTel() {
            return ZTel;
        }

        public void setZTel(String ZTel) {
            this.ZTel = ZTel;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getQq() {
            return Qq;
        }

        public void setQq(String Qq) {
            this.Qq = Qq;
        }

        public String getBrithday() {
            return Brithday;
        }

        public void setBrithday(String Brithday) {
            this.Brithday = Brithday;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getStafffPhone() {
            return StafffPhone;
        }

        public void setStafffPhone(String StafffPhone) {
            this.StafffPhone = StafffPhone;
        }
    }
}
