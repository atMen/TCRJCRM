package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 联系人列表
 * Created by leict on 2017/11/28.
 */

public class ContactListEntity implements Serializable {

    /**
     * State : 1
     * ListInfo : [{"ContactID":11012,"Position":"Dsds","CustomerName":"Dsds","ContactName":"Ddsds","CustomerID":7551}]
     * Msg : 操作成功
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private String CusId;
    private String key;
    private int UserId;

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

    public ContactListEntity(String CusId, String key, int UserId) {
        this.CusId = CusId;
        this.key = key;
        this.UserId = UserId;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * ContactID : 11012
         * Position : Dsds
         * CustomerName : Dsds
         * ContactName : Ddsds
         * CustomerID : 7551
         */

        private int ContactID;
        private String Position;
        private String CustomerName;
        private String ContactName;
        private int CustomerID;
        public String PinYin;

        public String getPinYin() {
            return PinYin;
        }

        public void setPinYin(String pinYin) {
            PinYin = pinYin;
        }

        public int getContactID() {
            return ContactID;
        }

        public void setContactID(int ContactID) {
            this.ContactID = ContactID;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getContactName() {
            return ContactName;
        }

        public void setContactName(String ContactName) {
            this.ContactName = ContactName;
        }

        public int getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(int CustomerID) {
            this.CustomerID = CustomerID;
        }
    }
}
