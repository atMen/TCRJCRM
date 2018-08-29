package com.tcrj.spv.views.adapter.expand;

import java.io.Serializable;

/**
 * Created by leict on 2017/11/8.
 */

public class ItemEntity  implements Serializable{
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Hour : 11:45
     * LogContent :
     * IsDo : 完成
     * ReviewsQuality : 未审核
     * StaffName : 测试1
     * PlanContent :
     * LogId : 62943
     */
private String time;

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
        return "ItemEntity{" +
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
