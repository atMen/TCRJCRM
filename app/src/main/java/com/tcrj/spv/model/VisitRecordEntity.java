package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 签到记录
 * Created by leict on 2017/12/11.
 */

public class VisitRecordEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * ListInfo : [{"Time":"2017-11-22","ListtVisitRecords":[{"Id":189,"StaffName":"测试2","MapImg":null,"CaMeraImg":null,"BigMapImg":null,"SingPlace":"陕西省西安市雁塔区丈八沟街道嘉昱大厦","CustomerId":"4543","CustomerName":"123","Hour":"10:57","XCoord":"34.190616607666","YCoord":"108.8701171875"}]}]
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private int CusId;
    private int StaffId;

    public VisitRecordEntity(int CusId, int StaffId) {
        this.CusId = CusId;
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

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * Time : 2017-11-22
         * ListtVisitRecords : [{"Id":189,"StaffName":"测试2","MapImg":null,"CaMeraImg":null,"BigMapImg":null,"SingPlace":"陕西省西安市雁塔区丈八沟街道嘉昱大厦","CustomerId":"4543","CustomerName":"123","Hour":"10:57","XCoord":"34.190616607666","YCoord":"108.8701171875"}]
         */

        private String Time;
        private List<ListtVisitRecordsBean> ListtVisitRecords;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public List<ListtVisitRecordsBean> getListtVisitRecords() {
            return ListtVisitRecords;
        }

        public void setListtVisitRecords(List<ListtVisitRecordsBean> ListtVisitRecords) {
            this.ListtVisitRecords = ListtVisitRecords;
        }

        public static class ListtVisitRecordsBean {
            /**
             * Id : 189
             * StaffName : 测试2
             * MapImg : null
             * CaMeraImg : null
             * BigMapImg : null
             * SingPlace : 陕西省西安市雁塔区丈八沟街道嘉昱大厦
             * CustomerId : 4543
             * CustomerName : 123
             * Hour : 10:57
             * XCoord : 34.190616607666
             * YCoord : 108.8701171875
             */

            private int Id;
            private String StaffName;
            private String MapImg;
            private String CaMeraImg;
            private String BigMapImg;
            private String SingPlace;
            private String CustomerId;
            private String CustomerName;
            private String Hour;
            private String XCoord;
            private String YCoord;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getStaffName() {
                return StaffName;
            }

            public void setStaffName(String StaffName) {
                this.StaffName = StaffName;
            }

            public String getMapImg() {
                return MapImg;
            }

            public void setMapImg(String MapImg) {
                this.MapImg = MapImg;
            }

            public String getCaMeraImg() {
                return CaMeraImg;
            }

            public void setCaMeraImg(String CaMeraImg) {
                this.CaMeraImg = CaMeraImg;
            }

            public String getBigMapImg() {
                return BigMapImg;
            }

            public void setBigMapImg(String BigMapImg) {
                this.BigMapImg = BigMapImg;
            }

            public String getSingPlace() {
                return SingPlace;
            }

            public void setSingPlace(String SingPlace) {
                this.SingPlace = SingPlace;
            }

            public String getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(String CustomerId) {
                this.CustomerId = CustomerId;
            }

            public String getCustomerName() {
                return CustomerName;
            }

            public void setCustomerName(String CustomerName) {
                this.CustomerName = CustomerName;
            }

            public String getHour() {
                return Hour;
            }

            public void setHour(String Hour) {
                this.Hour = Hour;
            }

            public String getXCoord() {
                return XCoord;
            }

            public void setXCoord(String XCoord) {
                this.XCoord = XCoord;
            }

            public String getYCoord() {
                return YCoord;
            }

            public void setYCoord(String YCoord) {
                this.YCoord = YCoord;
            }
        }
    }
}
