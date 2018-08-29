package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 客户信息：详情
 * Created by leict on 2017/11/27.
 */

public class CustomerDetailEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"CusId":7552,"CusName":"Feicehng","CustomerClass":"直销客户","CustomerClassId":"1","Area":"北京市市辖区东城区","Address":"Dead as","CustomerStatusId":"2","CusStatus":"意向客户","YxPro":"房产GIS管理信息系统,\n工业园区管委会管理系统,\n园林绿化综合业务监管平台","IsHotCus":"是","OwerName":"测试2","OwerTel":"","UpdateTime":"2017-11-24 14:32","LxrCount":"1","LxrInfoAll":[{"LxrName":"Dsdsa(手机)","LxrNum":"12345434431"},{"LxrName":"Dsdsa (座机) ","LxrNum":"ds"}],"LxrInfosj":[{"LxrName":"Dsdsa (手机) ","Lxrphone":"12345434431"}],"Gjcount":"0","IsShare":null,"XCode":null,"YCode":null,"SingPlace":null,"MapImg":null,"Pid":"1","Aid":"1","Cid":"1"}
     */

    private int State;
    private String Msg;
    private EntityBean Entity;
    private String CusId;

    public CustomerDetailEntity(String CusId) {
        this.CusId = CusId;
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

    public static class EntityBean implements Serializable {
        /**
         * CusId : 7552
         * CusName : Feicehng
         * CustomerClass : 直销客户
         * CustomerClassId : 1
         * Area : 北京市市辖区东城区
         * Address : Dead as
         * CustomerStatusId : 2
         * CusStatus : 意向客户
         * YxPro : 房产GIS管理信息系统,
         * 工业园区管委会管理系统,
         * 园林绿化综合业务监管平台
         * IsHotCus : 是
         * OwerName : 测试2
         * OwerTel :
         * UpdateTime : 2017-11-24 14:32
         * LxrCount : 1
         * LxrInfoAll : [{"LxrName":"Dsdsa(手机)","LxrNum":"12345434431"},{"LxrName":"Dsdsa (座机) ","LxrNum":"ds"}]
         * LxrInfosj : [{"LxrName":"Dsdsa (手机) ","Lxrphone":"12345434431"}]
         * Gjcount : 0
         * IsShare : null
         * XCode : null
         * YCode : null
         * SingPlace : null
         * MapImg : null
         * Pid : 1
         * Aid : 1
         * Cid : 1
         */

        private int CusId;
        private String CusName;
        private String CustomerClass;
        private String CustomerClassId;
        private String Area;
        private String Address;
        private String CustomerStatusId;
        private String CusStatus;
        private String YxPro;
        private String IsHotCus;
        private String OwerName;
        private String OwerTel;
        private String UpdateTime;
        private String LxrCount;
        private String Gjcount;
        private Object IsShare;
        private String XCode;
        private String YCode;
        private Object SingPlace;
        private Object MapImg;
        private String Pid;
        private String Aid;
        private String Cid;
        private List<LxrInfoAllBean> LxrInfoAll;
        private List<LxrInfosjBean> LxrInfosj;

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

        public String getCustomerClass() {
            return CustomerClass;
        }

        public void setCustomerClass(String CustomerClass) {
            this.CustomerClass = CustomerClass;
        }

        public String getCustomerClassId() {
            return CustomerClassId;
        }

        public void setCustomerClassId(String CustomerClassId) {
            this.CustomerClassId = CustomerClassId;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String Area) {
            this.Area = Area;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCustomerStatusId() {
            return CustomerStatusId;
        }

        public void setCustomerStatusId(String CustomerStatusId) {
            this.CustomerStatusId = CustomerStatusId;
        }

        public String getCusStatus() {
            return CusStatus;
        }

        public void setCusStatus(String CusStatus) {
            this.CusStatus = CusStatus;
        }

        public String getYxPro() {
            return YxPro;
        }

        public void setYxPro(String YxPro) {
            this.YxPro = YxPro;
        }

        public String getIsHotCus() {
            return IsHotCus;
        }

        public void setIsHotCus(String IsHotCus) {
            this.IsHotCus = IsHotCus;
        }

        public String getOwerName() {
            return OwerName;
        }

        public void setOwerName(String OwerName) {
            this.OwerName = OwerName;
        }

        public String getOwerTel() {
            return OwerTel;
        }

        public void setOwerTel(String OwerTel) {
            this.OwerTel = OwerTel;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getLxrCount() {
            return LxrCount;
        }

        public void setLxrCount(String LxrCount) {
            this.LxrCount = LxrCount;
        }

        public String getGjcount() {
            return Gjcount;
        }

        public void setGjcount(String Gjcount) {
            this.Gjcount = Gjcount;
        }

        public Object getIsShare() {
            return IsShare;
        }

        public void setIsShare(Object IsShare) {
            this.IsShare = IsShare;
        }

        public String getXCode() {
            return XCode;
        }

        public void setXCode(String XCode) {
            this.XCode = XCode;
        }

        public String getYCode() {
            return YCode;
        }

        public void setYCode(String YCode) {
            this.YCode = YCode;
        }

        public Object getSingPlace() {
            return SingPlace;
        }

        public void setSingPlace(Object SingPlace) {
            this.SingPlace = SingPlace;
        }

        public Object getMapImg() {
            return MapImg;
        }

        public void setMapImg(Object MapImg) {
            this.MapImg = MapImg;
        }

        public String getPid() {
            return Pid;
        }

        public void setPid(String Pid) {
            this.Pid = Pid;
        }

        public String getAid() {
            return Aid;
        }

        public void setAid(String Aid) {
            this.Aid = Aid;
        }

        public String getCid() {
            return Cid;
        }

        public void setCid(String Cid) {
            this.Cid = Cid;
        }

        public List<LxrInfoAllBean> getLxrInfoAll() {
            return LxrInfoAll;
        }

        public void setLxrInfoAll(List<LxrInfoAllBean> LxrInfoAll) {
            this.LxrInfoAll = LxrInfoAll;
        }

        public List<LxrInfosjBean> getLxrInfosj() {
            return LxrInfosj;
        }

        public void setLxrInfosj(List<LxrInfosjBean> LxrInfosj) {
            this.LxrInfosj = LxrInfosj;
        }

        public static class LxrInfoAllBean implements Serializable {
            /**
             * LxrName : Dsdsa(手机)
             * LxrNum : 12345434431
             */

            private String LxrName;
            private String LxrNum;

            public String getLxrName() {
                return LxrName;
            }

            public void setLxrName(String LxrName) {
                this.LxrName = LxrName;
            }

            public String getLxrNum() {
                return LxrNum;
            }

            public void setLxrNum(String LxrNum) {
                this.LxrNum = LxrNum;
            }
        }

        public static class LxrInfosjBean implements Serializable {
            /**
             * LxrName : Dsdsa (手机)
             * Lxrphone : 12345434431
             */

            private String LxrName;
            private String Lxrphone;

            public String getLxrName() {
                return LxrName;
            }

            public void setLxrName(String LxrName) {
                this.LxrName = LxrName;
            }

            public String getLxrphone() {
                return Lxrphone;
            }

            public void setLxrphone(String Lxrphone) {
                this.Lxrphone = Lxrphone;
            }
        }
    }
}
