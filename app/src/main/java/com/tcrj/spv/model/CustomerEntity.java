package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2017/11/24.
 */

public class CustomerEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"ClassList":[{"CustomerClassID":1,"CustomerClassName":"直销客户","CustomerClassDesc":"原为普通分类客户","OrderID":1,"CMID":1}],"StatusList":[{"StatusID":4,"StatusName":"失败客户","StatusDesc":null,"OrderID":0,"CMID":0}],"Entity":{"CusId":7544,"CusName":"张三丰333"}}
     */

    private int State;
    private String Msg;
    private EntityBeanX Entity;
    /**
     * State : 1
     * Msg : 操作成功
     */
    public String CustomerName;
    public String CustomerClass;
    public int Province;
    public int City;
    public int Area;
    public String Address;
    public String CustomerStatus;
    public String IntentionProducts;
    public String IsHot;
    public String WriteUserID;
    public String CustomerID;

    /**
     * 客户录入信息参数
     *
     * @param customerName
     * @param customerClass
     * @param province
     * @param city
     * @param area
     * @param address
     * @param customerStatus
     * @param intentionProducts
     * @param isHot
     * @param UserId
     * @param customerID
     */
    public CustomerEntity(String customerName, String customerClass, int province, int city, int area, String address, String customerStatus, String intentionProducts, String isHot, String UserId, String customerID) {
        this.CustomerName = customerName;
        this.CustomerClass = customerClass;
        this.Province = province;
        this.City = city;
        this.Area = area;
        this.Address = address;
        this.CustomerStatus = customerStatus;
        this.IntentionProducts = intentionProducts;
        this.IsHot = isHot;
        this.WriteUserID = UserId;
        this.CustomerID = customerID;
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

    public EntityBeanX getEntity() {
        return Entity;
    }

    public void setEntity(EntityBeanX Entity) {
        this.Entity = Entity;
    }

    public static class EntityBeanX {
        /**
         * ClassList : [{"CustomerClassID":1,"CustomerClassName":"直销客户","CustomerClassDesc":"原为普通分类客户","OrderID":1,"CMID":1}]
         * StatusList : [{"StatusID":4,"StatusName":"失败客户","StatusDesc":null,"OrderID":0,"CMID":0}]
         * Entity : {"CusId":7544,"CusName":"张三丰333"}
         */

        private EntityBean Entity;
        private List<ClassListBean> ClassList;
        private List<StatusListBean> StatusList;

        public EntityBean getEntity() {
            return Entity;
        }

        public void setEntity(EntityBean Entity) {
            this.Entity = Entity;
        }

        public List<ClassListBean> getClassList() {
            return ClassList;
        }

        public void setClassList(List<ClassListBean> ClassList) {
            this.ClassList = ClassList;
        }

        public List<StatusListBean> getStatusList() {
            return StatusList;
        }

        public void setStatusList(List<StatusListBean> StatusList) {
            this.StatusList = StatusList;
        }

        public static class EntityBean {
            /**
             * CusId : 7544
             * CusName : 张三丰333
             */

            private int CusId;
            private String CusName;

            public int getCusId() {
                return CusId;
            }

            public void setCusId(int CusId) {
                this.CusId = CusId;
            }

            public String getCusName() {
                return CusName;
            }

            public void setCusName(String CusName) {
                this.CusName = CusName;
            }
        }

        public static class ClassListBean {
            /**
             * CustomerClassID : 1
             * CustomerClassName : 直销客户
             * CustomerClassDesc : 原为普通分类客户
             * OrderID : 1
             * CMID : 1
             */

            private int CustomerClassID;
            private String CustomerClassName;
            private String CustomerClassDesc;
            private int OrderID;
            private int CMID;

            public int getCustomerClassID() {
                return CustomerClassID;
            }

            public void setCustomerClassID(int CustomerClassID) {
                this.CustomerClassID = CustomerClassID;
            }

            public String getCustomerClassName() {
                return CustomerClassName;
            }

            public void setCustomerClassName(String CustomerClassName) {
                this.CustomerClassName = CustomerClassName;
            }

            public String getCustomerClassDesc() {
                return CustomerClassDesc;
            }

            public void setCustomerClassDesc(String CustomerClassDesc) {
                this.CustomerClassDesc = CustomerClassDesc;
            }

            public int getOrderID() {
                return OrderID;
            }

            public void setOrderID(int OrderID) {
                this.OrderID = OrderID;
            }

            public int getCMID() {
                return CMID;
            }

            public void setCMID(int CMID) {
                this.CMID = CMID;
            }
        }

        public static class StatusListBean {
            /**
             * StatusID : 4
             * StatusName : 失败客户
             * StatusDesc : null
             * OrderID : 0
             * CMID : 0
             */

            private int StatusID;
            private String StatusName;
            private Object StatusDesc;
            private int OrderID;
            private int CMID;

            public int getStatusID() {
                return StatusID;
            }

            public void setStatusID(int StatusID) {
                this.StatusID = StatusID;
            }

            public String getStatusName() {
                return StatusName;
            }

            public void setStatusName(String StatusName) {
                this.StatusName = StatusName;
            }

            public Object getStatusDesc() {
                return StatusDesc;
            }

            public void setStatusDesc(Object StatusDesc) {
                this.StatusDesc = StatusDesc;
            }

            public int getOrderID() {
                return OrderID;
            }

            public void setOrderID(int OrderID) {
                this.OrderID = OrderID;
            }

            public int getCMID() {
                return CMID;
            }

            public void setCMID(int CMID) {
                this.CMID = CMID;
            }
        }
    }
}
