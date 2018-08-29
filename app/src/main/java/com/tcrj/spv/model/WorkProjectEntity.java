package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2017/12/17.
 */

public class WorkProjectEntity implements Serializable {

    /**
     * State : 1
     * Msg :
     * DataCont : 0
     * Data : [{"Pid":210,"PName":"延安新区智慧城市基础设施建设项目","ClientName":"延安新区管委会","ManagerStaffId":3,"BusinessStaffId":129,"DevelopStatffId":0,"CurrentState":1,"VisitUrlLocal":"TCRJ-20171213-0067"}]
     */

    private int State;
    private String Msg;

    private int DataCont;
    private List<DataBean> Data;
    private int PageIndex;
    private int PageSize;

    private String ProjectName;
    private int CurrentState;

    public int getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(int currentState) {
        CurrentState = currentState;
    }



    public WorkProjectEntity(int PageIndex, int PageSize, String ProjectName,int CurrentState) {
        this.PageIndex = PageIndex;
        this.PageSize = PageSize;
        this.ProjectName = ProjectName;
        this.CurrentState = CurrentState;
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

    public int getDataCont() {
        return DataCont;
    }

    public void setDataCont(int DataCont) {
        this.DataCont = DataCont;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Pid : 210
         * PName : 延安新区智慧城市基础设施建设项目
         * ClientName : 延安新区管委会
         * ManagerStaffId : 3
         * BusinessStaffId : 129
         * DevelopStatffId : 0
         * CurrentState : 1
         * VisitUrlLocal : TCRJ-20171213-0067
         */

        private int Pid;
        private String PName;
        private String ClientName;
        private int ManagerStaffId;
        private int BusinessStaffId;
        private int DevelopStatffId;
        private int CurrentState;
        private String VisitUrlLocal;

        public int getPid() {
            return Pid;
        }

        public void setPid(int Pid) {
            this.Pid = Pid;
        }

        public String getPName() {
            return PName;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }

        public String getClientName() {
            return ClientName;
        }

        public void setClientName(String ClientName) {
            this.ClientName = ClientName;
        }

        public int getManagerStaffId() {
            return ManagerStaffId;
        }

        public void setManagerStaffId(int ManagerStaffId) {
            this.ManagerStaffId = ManagerStaffId;
        }

        public int getBusinessStaffId() {
            return BusinessStaffId;
        }

        public void setBusinessStaffId(int BusinessStaffId) {
            this.BusinessStaffId = BusinessStaffId;
        }

        public int getDevelopStatffId() {
            return DevelopStatffId;
        }

        public void setDevelopStatffId(int DevelopStatffId) {
            this.DevelopStatffId = DevelopStatffId;
        }

        public int getCurrentState() {
            return CurrentState;
        }

        public void setCurrentState(int CurrentState) {
            this.CurrentState = CurrentState;
        }

        public String getVisitUrlLocal() {
            return VisitUrlLocal;
        }

        public void setVisitUrlLocal(String VisitUrlLocal) {
            this.VisitUrlLocal = VisitUrlLocal;
        }
    }
}
