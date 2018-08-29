package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2018/8/1.
 */

public class spInfo implements Serializable {


    private int type;
    private int WorkId;
    private String FkNodeId;
    /**
     * State : 1
     * Msg : 操作成功
     * list : [{"AppModel":{"ID":7274,"LCode":"20180802028-9049","NDName":"请假申请","AuditorTime":"2018-08-02T09:44:42.983","AuditorID":1467,"AuditorName":"王凯锋","AuditorDesc":"132","AuditorStatus":0,"Step":2801,"StatusDesc":"已提交"},"IAppTime":"2018-08-02 09:44"},{"AppModel":{"ID":7275,"LCode":"20180802028-9049","NDName":"部门领导审批","AuditorTime":"2018-08-02T09:45:03.663","AuditorID":6,"AuditorName":"王伟平","AuditorDesc":"fds","AuditorStatus":2,"Step":2802,"StatusDesc":"在办"},"IAppTime":"2018-08-02 09:45"},{"AppModel":{"ID":7276,"LCode":"20180802028-9049","NDName":"人力资源报备","AuditorTime":"2018-08-02T09:45:20.29","AuditorID":1429,"AuditorName":"季虹","AuditorDesc":"gfdgfd","AuditorStatus":3,"Step":2806,"StatusDesc":"办结"},"IAppTime":"2018-08-02 09:45"}]
     * leaveInfo : {"leave":{"ID":5107,"HolidayTypeID":0,"PlanBeginDate":"2018-09-01T09:44:00","PlanEndDate":"2018-09-02T09:44:00","Things":"132","FactBeginDate":"2018-09-01T09:44:00","FactEndDate":"2018-09-02T09:44:00","AddPerson":1467,"AddNo":"A00413","AddDate":"2018-08-02T09:44:42.97","State":8,"CancelDate":"1900-01-01T00:00:00","LCode":"20180802028-9049","IsDel":false,"PNum":1,"FNum":1,"AddPersonName":"王凯锋","WorkID":9049,"FlowNo":"028","HolidayName":"事假"},"IStart":"2018-09-01 09:44","IEnd":"2018-09-02 09:44"}
     * DeptName : 产品三部
     */

    private int State;
    private String Msg;
    private LeaveInfoBean leaveInfo;
    private String DeptName;
    private List<ListBean> list;
    private String StaffNo;
    private String AuditorDesc;
    private int NoID;

    private List<NodesBean> nodes;


    public int getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(int isReturn) {
        IsReturn = isReturn;
    }

    private int IsReturn;


    public spInfo(int WorkId, String FkNodeId) {
        this.WorkId = WorkId;
        this.FkNodeId = FkNodeId;
    }

    public spInfo(int type,int WorkId, String FkNodeId,String StaffNo,String AuditorDesc,int NoID) {
        this.WorkId = WorkId;
        this.type = type;
        this.NoID = NoID;
        this.FkNodeId = FkNodeId;
        this.StaffNo = StaffNo;
        this.AuditorDesc = AuditorDesc;
    }

    public spInfo(int type,int WorkId, String FkNodeId) {
        this.WorkId = WorkId;
        this.type = type;
        this.FkNodeId = FkNodeId;

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

    public LeaveInfoBean getLeaveInfo() {
        return leaveInfo;
    }

    public void setLeaveInfo(LeaveInfoBean leaveInfo) {
        this.leaveInfo = leaveInfo;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "spInfo{" +
                "WorkId=" + WorkId +
                ", FkNodeId='" + FkNodeId + '\'' +
                ", State=" + State +
                ", Msg='" + Msg + '\'' +
                ", leaveInfo=" + leaveInfo +
                ", DeptName='" + DeptName + '\'' +
                ", list=" + list +
                '}';
    }

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }
    public static class LeaveInfoBean implements Serializable{
        /**
         * leave : {"ID":5107,"HolidayTypeID":0,"PlanBeginDate":"2018-09-01T09:44:00","PlanEndDate":"2018-09-02T09:44:00","Things":"132","FactBeginDate":"2018-09-01T09:44:00","FactEndDate":"2018-09-02T09:44:00","AddPerson":1467,"AddNo":"A00413","AddDate":"2018-08-02T09:44:42.97","State":8,"CancelDate":"1900-01-01T00:00:00","LCode":"20180802028-9049","IsDel":false,"PNum":1,"FNum":1,"AddPersonName":"王凯锋","WorkID":9049,"FlowNo":"028","HolidayName":"事假"}
         * IStart : 2018-09-01 09:44
         * IEnd : 2018-09-02 09:44
         */

        private LeaveBean leave;
        private String IStart;
        private String IEnd;

        public LeaveBean getLeave() {
            return leave;
        }

        public void setLeave(LeaveBean leave) {
            this.leave = leave;
        }

        public String getIStart() {
            return IStart;
        }

        public void setIStart(String IStart) {
            this.IStart = IStart;
        }

        public String getIEnd() {
            return IEnd;
        }

        public void setIEnd(String IEnd) {
            this.IEnd = IEnd;
        }

        @Override
        public String toString() {
            return "LeaveInfoBean{" +
                    "leave=" + leave +
                    ", IStart='" + IStart + '\'' +
                    ", IEnd='" + IEnd + '\'' +
                    '}';
        }

        public static class LeaveBean implements Serializable {
            /**
             * ID : 5107
             * HolidayTypeID : 0
             * PlanBeginDate : 2018-09-01T09:44:00
             * PlanEndDate : 2018-09-02T09:44:00
             * Things : 132
             * FactBeginDate : 2018-09-01T09:44:00
             * FactEndDate : 2018-09-02T09:44:00
             * AddPerson : 1467
             * AddNo : A00413
             * AddDate : 2018-08-02T09:44:42.97
             * State : 8
             * CancelDate : 1900-01-01T00:00:00
             * LCode : 20180802028-9049
             * IsDel : false
             * PNum : 1
             * FNum : 1
             * AddPersonName : 王凯锋
             * WorkID : 9049
             * FlowNo : 028
             * HolidayName : 事假
             */

            private int ID;
            private int HolidayTypeID;
            private String PlanBeginDate;
            private String PlanEndDate;
            private String Things;
            private String FactBeginDate;
            private String FactEndDate;
            private int AddPerson;
            private String AddNo;
            private String AddDate;
            private int State;
            private String CancelDate;
            private String LCode;
            private boolean IsDel;
            private double PNum;
            private double FNum;
            private String AddPersonName;
            private int WorkID;
            private String FlowNo;
            private String HolidayName;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getHolidayTypeID() {
                return HolidayTypeID;
            }

            public void setHolidayTypeID(int HolidayTypeID) {
                this.HolidayTypeID = HolidayTypeID;
            }

            public String getPlanBeginDate() {
                return PlanBeginDate;
            }

            public void setPlanBeginDate(String PlanBeginDate) {
                this.PlanBeginDate = PlanBeginDate;
            }

            public String getPlanEndDate() {
                return PlanEndDate;
            }

            public void setPlanEndDate(String PlanEndDate) {
                this.PlanEndDate = PlanEndDate;
            }

            public String getThings() {
                return Things;
            }

            public void setThings(String Things) {
                this.Things = Things;
            }

            public String getFactBeginDate() {
                return FactBeginDate;
            }

            public void setFactBeginDate(String FactBeginDate) {
                this.FactBeginDate = FactBeginDate;
            }

            public String getFactEndDate() {
                return FactEndDate;
            }

            public void setFactEndDate(String FactEndDate) {
                this.FactEndDate = FactEndDate;
            }

            public int getAddPerson() {
                return AddPerson;
            }

            public void setAddPerson(int AddPerson) {
                this.AddPerson = AddPerson;
            }

            public String getAddNo() {
                return AddNo;
            }

            public void setAddNo(String AddNo) {
                this.AddNo = AddNo;
            }

            public String getAddDate() {
                return AddDate;
            }

            public void setAddDate(String AddDate) {
                this.AddDate = AddDate;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public String getCancelDate() {
                return CancelDate;
            }

            public void setCancelDate(String CancelDate) {
                this.CancelDate = CancelDate;
            }

            public String getLCode() {
                return LCode;
            }

            public void setLCode(String LCode) {
                this.LCode = LCode;
            }

            public boolean isIsDel() {
                return IsDel;
            }

            public void setIsDel(boolean IsDel) {
                this.IsDel = IsDel;
            }

            public double getPNum() {
                return PNum;
            }

            public void setPNum(double PNum) {
                this.PNum = PNum;
            }

            public double getFNum() {
                return FNum;
            }

            public void setFNum(double FNum) {
                this.FNum = FNum;
            }

            public String getAddPersonName() {
                return AddPersonName;
            }

            public void setAddPersonName(String AddPersonName) {
                this.AddPersonName = AddPersonName;
            }

            public int getWorkID() {
                return WorkID;
            }

            public void setWorkID(int WorkID) {
                this.WorkID = WorkID;
            }

            public String getFlowNo() {
                return FlowNo;
            }

            public void setFlowNo(String FlowNo) {
                this.FlowNo = FlowNo;
            }

            public String getHolidayName() {
                return HolidayName;
            }

            public void setHolidayName(String HolidayName) {
                this.HolidayName = HolidayName;
            }

            @Override
            public String toString() {
                return "LeaveBean{" +
                        "ID=" + ID +
                        ", HolidayTypeID=" + HolidayTypeID +
                        ", PlanBeginDate='" + PlanBeginDate + '\'' +
                        ", PlanEndDate='" + PlanEndDate + '\'' +
                        ", Things='" + Things + '\'' +
                        ", FactBeginDate='" + FactBeginDate + '\'' +
                        ", FactEndDate='" + FactEndDate + '\'' +
                        ", AddPerson=" + AddPerson +
                        ", AddNo='" + AddNo + '\'' +
                        ", AddDate='" + AddDate + '\'' +
                        ", State=" + State +
                        ", CancelDate='" + CancelDate + '\'' +
                        ", LCode='" + LCode + '\'' +
                        ", IsDel=" + IsDel +
                        ", PNum=" + PNum +
                        ", FNum=" + FNum +
                        ", AddPersonName='" + AddPersonName + '\'' +
                        ", WorkID=" + WorkID +
                        ", FlowNo='" + FlowNo + '\'' +
                        ", HolidayName='" + HolidayName + '\'' +
                        '}';
            }
        }
    }

    public static class ListBean implements Serializable{
        /**
         * AppModel : {"ID":7274,"LCode":"20180802028-9049","NDName":"请假申请","AuditorTime":"2018-08-02T09:44:42.983","AuditorID":1467,"AuditorName":"王凯锋","AuditorDesc":"132","AuditorStatus":0,"Step":2801,"StatusDesc":"已提交"}
         * IAppTime : 2018-08-02 09:44
         */

        private AppModelBean AppModel;
        private String IAppTime;

        public AppModelBean getAppModel() {
            return AppModel;
        }

        public void setAppModel(AppModelBean AppModel) {
            this.AppModel = AppModel;
        }

        public String getIAppTime() {
            return IAppTime;
        }

        public void setIAppTime(String IAppTime) {
            this.IAppTime = IAppTime;
        }

        public static class AppModelBean implements Serializable {
            /**
             * ID : 7274
             * LCode : 20180802028-9049
             * NDName : 请假申请
             * AuditorTime : 2018-08-02T09:44:42.983
             * AuditorID : 1467
             * AuditorName : 王凯锋
             * AuditorDesc : 132
             * AuditorStatus : 0
             * Step : 2801
             * StatusDesc : 已提交
             */

            private int ID;
            private String LCode;
            private String NDName;
            private String AuditorTime;
            private int AuditorID;
            private String AuditorName;
            private String AuditorDesc;
            private int AuditorStatus;
            private int Step;
            private String StatusDesc;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getLCode() {
                return LCode;
            }

            public void setLCode(String LCode) {
                this.LCode = LCode;
            }

            public String getNDName() {
                return NDName;
            }

            public void setNDName(String NDName) {
                this.NDName = NDName;
            }

            public String getAuditorTime() {
                return AuditorTime;
            }

            public void setAuditorTime(String AuditorTime) {
                this.AuditorTime = AuditorTime;
            }

            public int getAuditorID() {
                return AuditorID;
            }

            public void setAuditorID(int AuditorID) {
                this.AuditorID = AuditorID;
            }

            public String getAuditorName() {
                return AuditorName;
            }

            public void setAuditorName(String AuditorName) {
                this.AuditorName = AuditorName;
            }

            public String getAuditorDesc() {
                return AuditorDesc;
            }

            public void setAuditorDesc(String AuditorDesc) {
                this.AuditorDesc = AuditorDesc;
            }

            public int getAuditorStatus() {
                return AuditorStatus;
            }

            public void setAuditorStatus(int AuditorStatus) {
                this.AuditorStatus = AuditorStatus;
            }

            public int getStep() {
                return Step;
            }

            public void setStep(int Step) {
                this.Step = Step;
            }

            public String getStatusDesc() {
                return StatusDesc;
            }

            public void setStatusDesc(String StatusDesc) {
                this.StatusDesc = StatusDesc;
            }
        }
    }

    public static class NodesBean implements Serializable{
        /**
         * ID : 2802
         * NName : 部门领导审批
         */

        private int ID;
        private String NName;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getNName() {
            return NName;
        }

        public void setNName(String NName) {
            this.NName = NName;
        }
    }
}
