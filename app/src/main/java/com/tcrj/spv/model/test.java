package com.tcrj.spv.model;

/**
 * Created by leict on 2017/11/15.
 */

public class test {
    public int LogItemID;
    public int LogID;
    public int UserID;
    public String LogDate;
    public int WorkNature;
    public int ProjectID;
    public String WorkPlace;
    public String WorkHour;
    public String Overtime;
    public String LogContent;
    public String PlanContent;

    public test(int logItemID, int logID, int userID, String logDate, int workNature, int projectID, String workPlace, String workHour, String overtime, String logContent, String planContent) {
        LogItemID = logItemID;
        LogID = logID;
        UserID = userID;
        LogDate = logDate;
        WorkNature = workNature;
        ProjectID = projectID;
        WorkPlace = workPlace;
        WorkHour = workHour;
        Overtime = overtime;
        LogContent = logContent;
        PlanContent = planContent;
    }
};
