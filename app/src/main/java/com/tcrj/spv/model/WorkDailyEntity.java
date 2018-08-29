package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 工作日报
 * Created by leict on 2017/11/2.
 */

public class WorkDailyEntity implements Serializable {

    /**
     * State : 1
     * ListInfo : [{"ListtDayLogs":[{"Hour":"11:45","LogContent":"","IsDo":"完成","ReviewsQuality":"未审核","StaffName":"测试1","PlanContent":"","LogId":"62943"}],"Time":"2017-10-27"}]
     * Msg : 操作成功
     */

    private int State;
    private String Msg;
    private List<ListInfoBean> ListInfo;
    private int UserId;
    private int DayType;
    private String OwerId;
    private String DeptId;
    private int CurrentIndex;
    private int PageSize;
    private int IsMe;

    public WorkDailyEntity(int UserId, int DayType, String OwerId, String DeptId, int CurrentIndex, int PageSize, int IsMe) {
        this.UserId = UserId;
        this.DayType = DayType;
        this.OwerId = OwerId;
        this.DeptId = DeptId;
        this.CurrentIndex = CurrentIndex;
        this.PageSize = PageSize;
        this.IsMe = IsMe;
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

    @Override
    public String toString() {
        return "WorkDailyEntity{" +
                "State=" + State +
                ", Msg='" + Msg + '\'' +
                ", ListInfo=" + ListInfo +
                ", UserId=" + UserId +
                ", DayType=" + DayType +
                ", OwerId='" + OwerId + '\'' +
                ", DeptId='" + DeptId + '\'' +
                ", CurrentIndex=" + CurrentIndex +
                ", PageSize=" + PageSize +
                ", IsMe=" + IsMe +
                '}';
    }

    public static class ListInfoBean {
        /**
         * ListtDayLogs : [{"Hour":"11:45","LogContent":"","IsDo":"完成","ReviewsQuality":"未审核","StaffName":"测试1","PlanContent":"","LogId":"62943"}]
         * Time : 2017-10-27
         */
        public boolean isExpanded = true;
        private String Time;
        private List<ListtDayLogsBean> ListtDayLogs;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public List<ListtDayLogsBean> getListtDayLogs() {
            return ListtDayLogs;
        }

        public void setListtDayLogs(List<ListtDayLogsBean> ListtDayLogs) {
            this.ListtDayLogs = ListtDayLogs;
        }

        public static class ListtDayLogsBean {
            /**
             * Hour : 11:45
             * LogContent :
             * IsDo : 完成
             * ReviewsQuality : 未审核
             * StaffName : 测试1
             * PlanContent :
             * LogId : 62943
             */

            private String Hour;
            private String LogContent;
            private String IsDo;
            private String ReviewsQuality;
            private String StaffName;
            private String PlanContent;
            private String LogId;

            public String getHour() {
                return Hour;
            }

            public void setHour(String Hour) {
                this.Hour = Hour;
            }

            public String getLogContent() {
                return LogContent;
            }

            public void setLogContent(String LogContent) {
                this.LogContent = LogContent;
            }

            public String getIsDo() {
                return IsDo;
            }

            public void setIsDo(String IsDo) {
                this.IsDo = IsDo;
            }

            public String getReviewsQuality() {
                return ReviewsQuality;
            }

            public void setReviewsQuality(String ReviewsQuality) {
                this.ReviewsQuality = ReviewsQuality;
            }

            public String getStaffName() {
                return StaffName;
            }

            public void setStaffName(String StaffName) {
                this.StaffName = StaffName;
            }

            public String getPlanContent() {
                return PlanContent;
            }

            public void setPlanContent(String PlanContent) {
                this.PlanContent = PlanContent;
            }

            public String getLogId() {
                return LogId;
            }

            public void setLogId(String LogId) {
                this.LogId = LogId;
            }

            @Override
            public String toString() {
                return "ListtDayLogsBean{" +
                        "Hour='" + Hour + '\'' +
                        ", LogContent='" + LogContent + '\'' +
                        ", IsDo='" + IsDo + '\'' +
                        ", ReviewsQuality='" + ReviewsQuality + '\'' +
                        ", StaffName='" + StaffName + '\'' +
                        ", PlanContent='" + PlanContent + '\'' +
                        ", LogId='" + LogId + '\'' +
                        '}';
            }
        }
    }
}
