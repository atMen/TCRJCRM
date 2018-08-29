package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 联系人
 * Created by leict on 2017/12/6.
 */

public class RelationManEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"Uid":"3","UName":"张锦煜"}]
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private int StaffId;
    private int type;

    public RelationManEntity(int StaffId, int type) {
        this.StaffId = StaffId;
        this.type = type;
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

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * Uid : 3
         * UName : 张锦煜
         */

        private String Uid;
        private String UName;
        private String PinYin;
        private String UDept;
        private String URole;

        public String getUDept() {
            return UDept;
        }

        public void setUDept(String UDept) {
            this.UDept = UDept;
        }

        public String getURole() {
            return URole;
        }

        public void setURole(String URole) {
            this.URole = URole;
        }

        public String getPinYin() {
            return PinYin;
        }

        public void setPinYin(String pinYin) {
            PinYin = pinYin;
        }

        public String getUid() {
            return Uid;
        }

        public void setUid(String Uid) {
            this.Uid = Uid;
        }

        public String getUName() {
            return UName;
        }

        public void setUName(String UName) {
            this.UName = UName;
        }
    }
}
