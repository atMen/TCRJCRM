package com.tcrj.spv.model;

/**
 * 修改密码
 * Created by leict on 2017/11/20.
 */

public class PasswordEntity {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"ClassList":null,"StatusList":null,"Entity":null}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private int UserId;
    private String OPwd;            //旧密码
    private String NPwd;            //新密码

    public PasswordEntity(int UserId, String OPwd, String NPwd) {
        this.UserId = UserId;
        this.OPwd = OPwd;
        this.NPwd = NPwd;
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
         * ClassList : null
         * StatusList : null
         * Entity : null
         */

        private Object ClassList;
        private Object StatusList;
        private Object Entity;

        public Object getClassList() {
            return ClassList;
        }

        public void setClassList(Object ClassList) {
            this.ClassList = ClassList;
        }

        public Object getStatusList() {
            return StatusList;
        }

        public void setStatusList(Object StatusList) {
            this.StatusList = StatusList;
        }

        public Object getEntity() {
            return Entity;
        }

        public void setEntity(Object Entity) {
            this.Entity = Entity;
        }
    }
}
