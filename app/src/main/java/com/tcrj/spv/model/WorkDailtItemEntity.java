package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2017/12/27.
 */

public class WorkDailtItemEntity implements Serializable {

    private int UserID;
    private int LogID;
    private int LogDate;


    public WorkDailtItemEntity(int userID, int logID, int logDate) {
        UserID = userID;
        LogID = logID;
        LogDate = logDate;
    }

    /**
     * State : 1
     * Msg : 查询成功
     * Data : [{"LogItemID":9,"LogID":6,"ProjectID":196,"WorkNature":"项目","WorkPlace":"延长县","WorkHour":8,"Overtime":1,"LogContent":"档案扫描100分","PlanContent":"档案扫描90分","DoDate":"2017-11-15 09:35:04","PName":"延长县不动产信息平台建设项目","WorkNatureID":"1"},{"LogItemID":8,"LogID":6,"ProjectID":200,"WorkNature":"项目","WorkPlace":"嘉峪关","WorkHour":8,"Overtime":1,"LogContent":"1、处理地形图<br/>2、处理总规","PlanContent":"1、处理其他事务","DoDate":"2017-11-15 09:34:27","PName":"嘉峪关市规划局数据处理服务","WorkNatureID":"1"}]
     */

    private int State;
    private String Msg;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * LogItemID : 9
         * LogID : 6
         * ProjectID : 196
         * WorkNature : 项目
         * WorkPlace : 延长县
         * WorkHour : 8.0
         * Overtime : 1.0
         * LogContent : 档案扫描100分
         * PlanContent : 档案扫描90分
         * DoDate : 2017-11-15 09:35:04
         * PName : 延长县不动产信息平台建设项目
         * WorkNatureID : 1
         */

        private int LogItemID;
        private int LogID;
        private int ProjectID;
        private String WorkNature;
        private String WorkPlace;
        private double WorkHour;
        private double Overtime;
        private String LogContent;
        private String PlanContent;
        private String DoDate;
        private String PName;
        private String WorkNatureID;

        public int getLogItemID() {
            return LogItemID;
        }

        public void setLogItemID(int LogItemID) {
            this.LogItemID = LogItemID;
        }

        public int getLogID() {
            return LogID;
        }

        public void setLogID(int LogID) {
            this.LogID = LogID;
        }

        public int getProjectID() {
            return ProjectID;
        }

        public void setProjectID(int ProjectID) {
            this.ProjectID = ProjectID;
        }

        public String getWorkNature() {
            return WorkNature;
        }

        public void setWorkNature(String WorkNature) {
            this.WorkNature = WorkNature;
        }

        public String getWorkPlace() {
            return WorkPlace;
        }

        public void setWorkPlace(String WorkPlace) {
            this.WorkPlace = WorkPlace;
        }

        public double getWorkHour() {
            return WorkHour;
        }

        public void setWorkHour(double WorkHour) {
            this.WorkHour = WorkHour;
        }

        public double getOvertime() {
            return Overtime;
        }

        public void setOvertime(double Overtime) {
            this.Overtime = Overtime;
        }

        public String getLogContent() {
            return LogContent;
        }

        public void setLogContent(String LogContent) {
            this.LogContent = LogContent;
        }

        public String getPlanContent() {
            return PlanContent;
        }

        public void setPlanContent(String PlanContent) {
            this.PlanContent = PlanContent;
        }

        public String getDoDate() {
            return DoDate;
        }

        public void setDoDate(String DoDate) {
            this.DoDate = DoDate;
        }

        public String getPName() {
            return PName;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }

        public String getWorkNatureID() {
            return WorkNatureID;
        }

        public void setWorkNatureID(String WorkNatureID) {
            this.WorkNatureID = WorkNatureID;
        }
    }
}
