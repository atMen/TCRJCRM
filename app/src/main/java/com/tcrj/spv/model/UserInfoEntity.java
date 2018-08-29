package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 用户实体类
 * Created by leict on 2017/10/23.
 */

public class UserInfoEntity implements Serializable {
    /**
     * State : 1
     * Msg : 登录成功
     * Entity : {"UserId":183,"UserTitle":"A00156","UserName":"测试2","RoleId":6,"RoleName":"海外事业部负责人","Email":"","UserPwd":"aaa","Tel":"","StaffImg":"http://113.200.26.66:8000/Images/noimg.png","CurrentVersion":"13","IsSell":1,"IsHavexj":1,"Integral":53,"IsOut":1,"IsKq":0}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private String UserTitle;
    private String UserPwd;
    private String RegID;
    private String RegType;



    public UserInfoEntity(String userTitle, String userPwd, String regID, String regType) {
        this.UserTitle = userTitle;
        this.UserPwd = userPwd;
        this.RegID = regID;
        this.RegType = regType;
    }

    public UserInfoEntity() {
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
         * UserId : 183
         * UserTitle : A00156
         * UserName : 测试2
         * RoleId : 6
         * RoleName : 海外事业部负责人
         * Email :
         * UserPwd : aaa
         * Tel :
         * StaffImg : http://113.200.26.66:8000/Images/noimg.png
         * CurrentVersion : 13
         * IsSell : 1
         * IsHavexj : 1
         * Integral : 53
         * IsOut : 1
         * IsKq : 0
         */

        private int UserId;
        private String UserTitle;
        private String UserName;
        private int RoleId;
        private String RoleName;
        private String Email;
        private String UserPwd;
        private String Tel;
        private String StaffImg;
        private String CurrentVersion;
        private int IsSell;
        private int IsHavexj;
        private int Integral;
        private int IsOut;
        private int IsKq;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getUserTitle() {
            return UserTitle;
        }

        public void setUserTitle(String UserTitle) {
            this.UserTitle = UserTitle;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getRoleId() {
            return RoleId;
        }

        public void setRoleId(int RoleId) {
            this.RoleId = RoleId;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getUserPwd() {
            return UserPwd;
        }

        public void setUserPwd(String UserPwd) {
            this.UserPwd = UserPwd;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getStaffImg() {
            return StaffImg;
        }

        public void setStaffImg(String StaffImg) {
            this.StaffImg = StaffImg;
        }

        public String getCurrentVersion() {
            return CurrentVersion;
        }

        public void setCurrentVersion(String CurrentVersion) {
            this.CurrentVersion = CurrentVersion;
        }

        public int getIsSell() {
            return IsSell;
        }

        public void setIsSell(int IsSell) {
            this.IsSell = IsSell;
        }

        public int getIsHavexj() {
            return IsHavexj;
        }

        public void setIsHavexj(int IsHavexj) {
            this.IsHavexj = IsHavexj;
        }

        public int getIntegral() {
            return Integral;
        }

        public void setIntegral(int Integral) {
            this.Integral = Integral;
        }

        public int getIsOut() {
            return IsOut;
        }

        public void setIsOut(int IsOut) {
            this.IsOut = IsOut;
        }

        public int getIsKq() {
            return IsKq;
        }

        public void setIsKq(int IsKq) {
            this.IsKq = IsKq;
        }

        @Override
        public String toString() {
            return "EntityBean{" +
                    "UserId=" + UserId +
                    ", UserTitle='" + UserTitle + '\'' +
                    ", UserName='" + UserName + '\'' +
                    ", RoleId=" + RoleId +
                    ", RoleName='" + RoleName + '\'' +
                    ", Email='" + Email + '\'' +
                    ", UserPwd='" + UserPwd + '\'' +
                    ", Tel='" + Tel + '\'' +
                    ", StaffImg='" + StaffImg + '\'' +
                    ", CurrentVersion='" + CurrentVersion + '\'' +
                    ", IsSell=" + IsSell +
                    ", IsHavexj=" + IsHavexj +
                    ", Integral=" + Integral +
                    ", IsOut=" + IsOut +
                    ", IsKq=" + IsKq +
                    '}';
        }
    }
}
