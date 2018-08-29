package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2018/8/7.
 */

public class ccSPInfo implements Serializable{

    /**
     * State : 1
     * Msg : 调用成功
     * list : [{"AppModel":{"ID":8354,"LCode":"20180807031-9141","NDName":"出差申请","AuditorTime":"2018-08-07T13:53:33.647","AuditorID":1477,"AuditorName":"韩天琛","AuditorDesc":"都是对的","AuditorStatus":0,"Step":3101,"StatusDesc":"已提交"},"IAppTime":"2018-08-07 13:53"},{"AppModel":{"ID":8355,"LCode":"20180807031-9141","NDName":"部门审核","AuditorTime":"2018-08-07T14:14:20.323","AuditorID":6,"AuditorName":"王伟平","AuditorDesc":"通过","AuditorStatus":2,"Step":3102,"StatusDesc":"在办"},"IAppTime":"2018-08-07 14:14"}]
     * travel : {"travel":{"ID":4019,"StaffName":"韩天琛","StaffID":1477,"StaffDept":19,"StarTime":"2018-10-05T00:00:00","EndTime":"2018-10-08T00:00:00","Partner":"ffff","Adress":"789","Vehicle":"汽车","Traffic":20,"Hotel":123,"Other":0,"Cost":2213,"Cause":"都是对的","Plan":"无所谓","AddTime":"2018-08-07T13:53:24.283","State":6,"LCID":"20180807031-9141","WorkID":"9141","BackTime":"1900-01-01T00:00:00","Summary":null},"IStart":"2018-10-05 00:00","IEnd":"2018-10-08 00:00","IBackTime":"1900-01-01 00:00"}
     * nodes : [{"ID":3101,"NName":"出差申请"}]
     * IsReturn : 0
     * temp : []
     */
    public ccSPInfo(int type,int WorkId, String FkNodeId,String StaffNo,String AuditorDesc,int NoID,String BackTime,String BackSum) {
        this.WorkId = WorkId;
        this.type = type;
        this.NoID = NoID;
        this.FkNodeId = FkNodeId;
        this.StaffNo = StaffNo;
        this.AuditorDesc = AuditorDesc;
        this.BackTime = BackTime;
        this.BackSum = BackSum;
    }
    private String BackSum;
    private String BackTime;
    private int type;
    private String StaffNo;
    private String AuditorDesc;
    private int NoID;
    private int State;
    private String Msg;
    private TravelBeanX travel;
    private int IsReturn;

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    private String DeptName;
    private List<ListBean> list;
    private List<NodesBean> nodes;
    private List<?> temp;

    private int WorkId;
    private String FkNodeId;
    public ccSPInfo(int workId, String fkNodeId) {
        this.WorkId = workId;
        this.FkNodeId = fkNodeId;
    }

    public ccSPInfo(int type,int workId, String fkNodeId) {
        this.type = type;
        this.WorkId = workId;
        this.FkNodeId = fkNodeId;
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

    public TravelBeanX getTravel() {
        return travel;
    }

    public void setTravel(TravelBeanX travel) {
        this.travel = travel;
    }

    public int getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(int IsReturn) {
        this.IsReturn = IsReturn;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }

    public List<?> getTemp() {
        return temp;
    }

    public void setTemp(List<?> temp) {
        this.temp = temp;
    }

    public static class TravelBeanX implements Serializable{
        /**
         * travel : {"ID":4019,"StaffName":"韩天琛","StaffID":1477,"StaffDept":19,"StarTime":"2018-10-05T00:00:00","EndTime":"2018-10-08T00:00:00","Partner":"ffff","Adress":"789","Vehicle":"汽车","Traffic":20,"Hotel":123,"Other":0,"Cost":2213,"Cause":"都是对的","Plan":"无所谓","AddTime":"2018-08-07T13:53:24.283","State":6,"LCID":"20180807031-9141","WorkID":"9141","BackTime":"1900-01-01T00:00:00","Summary":null}
         * IStart : 2018-10-05 00:00
         * IEnd : 2018-10-08 00:00
         * IBackTime : 1900-01-01 00:00
         */

        private TravelBean travel;
        private String IStart;
        private String IEnd;
        private String IBackTime;

        public String getIReturn() {
            return IReturn;
        }

        public void setIReturn(String IReturn) {
            this.IReturn = IReturn;
        }

        private String IReturn;

        public TravelBean getTravel() {
            return travel;
        }

        public void setTravel(TravelBean travel) {
            this.travel = travel;
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

        public String getIBackTime() {
            return IBackTime;
        }

        public void setIBackTime(String IBackTime) {
            this.IBackTime = IBackTime;
        }

        public static class TravelBean implements Serializable{
            /**
             * ID : 4019
             * StaffName : 韩天琛
             * StaffID : 1477
             * StaffDept : 19
             * StarTime : 2018-10-05T00:00:00
             * EndTime : 2018-10-08T00:00:00
             * Partner : ffff
             * Adress : 789
             * Vehicle : 汽车
             * Traffic : 20
             * Hotel : 123
             * Other : 0
             * Cost : 2213
             * Cause : 都是对的
             * Plan : 无所谓
             * AddTime : 2018-08-07T13:53:24.283
             * State : 6
             * LCID : 20180807031-9141
             * WorkID : 9141
             * BackTime : 1900-01-01T00:00:00
             * Summary : null
             */

            private int ID;
            private String StaffName;
            private int StaffID;
            private int StaffDept;
            private String StarTime;
            private String EndTime;
            private String Partner;
            private String Adress;
            private String Vehicle;
            private int Traffic;
            private int Hotel;
            private int Other;
            private int Cost;
            private String Cause;
            private String Plan;
            private String AddTime;
            private int State;
            private String LCID;
            private String WorkID;
            private String BackTime;
            private String Summary;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getStaffName() {
                return StaffName;
            }

            public void setStaffName(String StaffName) {
                this.StaffName = StaffName;
            }

            public int getStaffID() {
                return StaffID;
            }

            public void setStaffID(int StaffID) {
                this.StaffID = StaffID;
            }

            public int getStaffDept() {
                return StaffDept;
            }

            public void setStaffDept(int StaffDept) {
                this.StaffDept = StaffDept;
            }

            public String getStarTime() {
                return StarTime;
            }

            public void setStarTime(String StarTime) {
                this.StarTime = StarTime;
            }

            public String getEndTime() {
                return EndTime;
            }

            public void setEndTime(String EndTime) {
                this.EndTime = EndTime;
            }

            public String getPartner() {
                return Partner;
            }

            public void setPartner(String Partner) {
                this.Partner = Partner;
            }

            public String getAdress() {
                return Adress;
            }

            public void setAdress(String Adress) {
                this.Adress = Adress;
            }

            public String getVehicle() {
                return Vehicle;
            }

            public void setVehicle(String Vehicle) {
                this.Vehicle = Vehicle;
            }

            public int getTraffic() {
                return Traffic;
            }

            public void setTraffic(int Traffic) {
                this.Traffic = Traffic;
            }

            public int getHotel() {
                return Hotel;
            }

            public void setHotel(int Hotel) {
                this.Hotel = Hotel;
            }

            public int getOther() {
                return Other;
            }

            public void setOther(int Other) {
                this.Other = Other;
            }

            public int getCost() {
                return Cost;
            }

            public void setCost(int Cost) {
                this.Cost = Cost;
            }

            public String getCause() {
                return Cause;
            }

            public void setCause(String Cause) {
                this.Cause = Cause;
            }

            public String getPlan() {
                return Plan;
            }

            public void setPlan(String Plan) {
                this.Plan = Plan;
            }

            public String getAddTime() {
                return AddTime;
            }

            public void setAddTime(String AddTime) {
                this.AddTime = AddTime;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public String getLCID() {
                return LCID;
            }

            public void setLCID(String LCID) {
                this.LCID = LCID;
            }

            public String getWorkID() {
                return WorkID;
            }

            public void setWorkID(String WorkID) {
                this.WorkID = WorkID;
            }

            public String getBackTime() {
                return BackTime;
            }

            public void setBackTime(String BackTime) {
                this.BackTime = BackTime;
            }

            public String getSummary() {
                return Summary;
            }

            public void setSummary(String Summary) {
                this.Summary = Summary;
            }
        }
    }

    public static class ListBean implements Serializable{
        /**
         * AppModel : {"ID":8354,"LCode":"20180807031-9141","NDName":"出差申请","AuditorTime":"2018-08-07T13:53:33.647","AuditorID":1477,"AuditorName":"韩天琛","AuditorDesc":"都是对的","AuditorStatus":0,"Step":3101,"StatusDesc":"已提交"}
         * IAppTime : 2018-08-07 13:53
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

        public static class AppModelBean implements Serializable{
            /**
             * ID : 8354
             * LCode : 20180807031-9141
             * NDName : 出差申请
             * AuditorTime : 2018-08-07T13:53:33.647
             * AuditorID : 1477
             * AuditorName : 韩天琛
             * AuditorDesc : 都是对的
             * AuditorStatus : 0
             * Step : 3101
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
         * ID : 3101
         * NName : 出差申请
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
