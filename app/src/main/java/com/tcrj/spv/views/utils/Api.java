package com.tcrj.spv.views.utils;

import com.tcrj.spv.checkUpdata.VersionInfo;
import com.tcrj.spv.model.ApprovalEntity;
import com.tcrj.spv.model.CommunicationEntity;
import com.tcrj.spv.model.ContactDetailEntity;
import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.CustomerEntity;
import com.tcrj.spv.model.CustomerInfoEntity;
import com.tcrj.spv.model.CustomerTraceEntity;
import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.model.LeaveApplyDetailEntity;
import com.tcrj.spv.model.LocationEntity1;
import com.tcrj.spv.model.LocationInfo;
import com.tcrj.spv.model.NewDay;
import com.tcrj.spv.model.NewFollowRecordEntity;
import com.tcrj.spv.model.NewWorkDailyEntity;
import com.tcrj.spv.model.RelationManEntity;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.CustomerTypeEntity;
import com.tcrj.spv.model.DailyEntity;
import com.tcrj.spv.model.IntentProductEntity;
import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.model.NoticeDetailEntity;
import com.tcrj.spv.model.NoticeEntity;
import com.tcrj.spv.model.PasswordEntity;
import com.tcrj.spv.model.PersionBookEntity;
import com.tcrj.spv.model.SignRecordEntity;
import com.tcrj.spv.model.TracedMaturityEntity;
import com.tcrj.spv.model.TravelApplyDetailEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.model.VisitRecordEntity;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.model.WorkDailyEntity;
import com.tcrj.spv.model.WorkProjectEntity;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.model.dialogListviewInfo;
import com.tcrj.spv.model.spInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import rx.Observable;
import retrofit2.http.POST;

/**
 * 接口类
 * Created by leict on 2017/10/23.
 */

public interface Api {

    /**
     * 获取筛选项“客户成熟度”
     *
     *
     * @return
     */
    @POST("TracedMaturityList.ashx?")
    Observable<List<NewFollowRecordEntity>> getTracedMaturityList(@Body NewFollowRecordEntity entity);
    /**
     * 用户登录
     *
     * @param entity
     * @return
     */
    @POST("Login.ashx?")
    Observable<UserInfoEntity> getLoginInfo(@Body UserInfoEntity entity);

    /**
     * 写日报
     *
     * @param entity
     * @return
     */
    @POST("MyDayLogEdit.ashx?")
    Observable<DailyEntity> getWriteDaily(@Body DailyEntity entity);

    /**
     * 通知公告
     *
     * @param entity
     * @return
     */
    @POST("AnnouncementList.ashx?")
    Observable<NoticeEntity> getNoticeList(@Body NoticeEntity entity);

    /**
     * 通知公告详情
     *
     * @param entity
     * @return
     */
    @POST("AnnouncementDetails.ashx?")
    Observable<NoticeDetailEntity> getNoticeDetails(@Body NoticeDetailEntity entity);

    /**
     * 签到记录
     *
     * @param entity
     * @return
     */
    @POST("MyKQRecords.ashx?")
    Observable<SignRecordEntity> getSignRecordList(@Body SignRecordEntity entity);

    /**
     * 工作日报
     *
     * @param entity
     * @return MyDayLogList PM_NewDayLogList
     */
    @POST("PM_NewDayLogList.ashx?")
    Observable<WorkDailyEntity> getWorkDailyList(@Body WorkDailyEntity entity);

    /**
     * 工作日报部门
     *
     * @param entity
     * @return MyDayLogList PM_NewDayLogList
     */
    @POST("DeptList.ashx?")
    Observable<SpinnerEntity> getWorkDailyBmList(@Body SpinnerEntity entity);


    /**
     * 工作日报项列表
     *
     * @param entity
     * @return MyDayLogList PM_NewDayLogList
     */
    @POST("PM_NewDayLogItemList.ashx?")
    Observable<WorkDailtItemEntity> getWorkDailyItemList(@Body WorkDailtItemEntity entity);

    /**
     * 待办审批
     *
     * @param entity
     * @return
     */
    @POST("MyToDoList.ashx?")
    Observable<ApprovalEntity> getPendApprovalList(@Body ApprovalEntity entity);

    /**
     * 在办审批
     *
     * @param entity
     * @return
     */
    @POST("RunningList.ashx?")
    Observable<ApprovalEntity> getOfficeApprovalList(@Body ApprovalEntity entity);


    /**
     * 完结审批
     *
     * @param entity
     * @return
     */
    @POST("CompleteList.ashx?")
    Observable<ApprovalEntity> getFinishApprovalList(@Body ApprovalEntity entity);

    /**
     * 请假申请/出差申请 类别
     *
     * @param entity
     * @return
     */
    @POST("Leave.ashx?")
    Observable<LeaveApplyEntity> getLeaveTypeInfo(@Body LeaveApplyEntity entity);

    /**
     * 出差申请
     *
     * @param entity
     * @return
     */
    @POST("TravelRequests.ashx?")
    Observable<TravelApplyEntity> getTravelApplyInfo(@Body TravelApplyEntity entity);

    /**
     * 通讯录
     *
     * @param entity
     * @return
     */
    @POST("PersionBookList.ashx?")
    Observable<PersionBookEntity> getPersionBookList(@Body PersionBookEntity entity);

    /**
     * 通讯录：详情
     *
     * @param entity
     * @return
     */
    @POST("PerSonBookDetail.ashx?")
    Observable<CommunicationEntity> getPsersionBookDetail(@Body CommunicationEntity entity);

    /**
     * 修改密码
     *
     * @param entity
     * @return
     */
    @POST("UserInfoHandler.ashx?")
    Observable<PasswordEntity> getUpdatePassword(@Body PasswordEntity entity);

    /**
     * 客户分类
     *
     * @param entity
     * @return
     */
    @POST("CustomerClassList.ashx?")
    Observable<CustomerTypeEntity> getCustomerTypeList(@Body CustomerTypeEntity entity);

    /**
     * 意向产品列表
     *
     * @param entity
     * @return
     */
    @POST("ProductList.ashx?")
    Observable<IntentProductEntity> getProductList(@Body IntentProductEntity entity);

    /**
     * 添加/修改/客户详细信息
     *
     * @param entity
     * @return
     */
    @POST("CutomerEdit.ashx?")
    Observable<CustomerEntity> getCutomerInfo(@Body CustomerEntity entity);

    /**
     * 添加及修改客户联系人详细信息
     *
     * @param entity
     * @return
     */
    @POST("ContactInfoEdit.ashx?")
    Observable<SubmitEntity> getContactInfo(@Body SubmitEntity entity);

    /**
     * 获取客户信息列表
     *
     * @param entity
     * @return
     */
    @POST("CustomerList.ashx?")
    Observable<CustomerInfoEntity> getCustomerList(@Body CustomerInfoEntity entity);

    /**
     * 客户详情
     *
     * @param entity
     * @return
     */
    @POST("CustomerDetails.ashx?")
    Observable<CustomerDetailEntity> getCustomerDetails(@Body CustomerDetailEntity entity);

    /**
     * 联系人
     *
     * @param entity
     * @return
     */
    @POST("ContactList.ashx?")
    Observable<ContactListEntity> getContactList(@Body ContactListEntity entity);

    /**
     * 联系人详情
     *
     * @param entity
     * @return
     */
    @POST("ContactDetails.ashx?")
    Observable<ContactDetailEntity> getContactDetails(@Body ContactDetailEntity entity);

    /**
     * 删除联系人
     *
     * @param entity
     * @return
     */
    @POST("ContactHandler.ashx?")
    Observable<SubmitEntity> getContactHandler(@Body SubmitEntity entity);

    /**
     * 删除客户
     *
     * @param entity
     * @return
     */
    @POST("CustomerHandler.ashx?")
    Observable<SubmitEntity> getCustomerHandler(@Body SubmitEntity entity);

    /**
     * 跟进记录
     *
     * @param entity
     * @return
     */
    @POST("CustomerTraceListByCus.ashx?")
    Observable<FollowRecordEntity> getCustomerTraceList(@Body FollowRecordEntity entity);


    /**
     * 获取所有跟进记录
     *
     * @return
     */
    @POST("CustomerTraceList.ashx?")
    Observable<FollowRecordEntity> getCustomerTraceListGj(@Body FollowRecordEntity entity);


    /**
     * 提交客户跟进记录
     *
     * @param entity
     * @return
     */
    @POST("CustomerTraceEdit.ashx?")
    Observable<SubmitEntity> getCustomerTraceEdit(@Body SubmitEntity entity);

    /**
     * 客户成熟度
     *
     * @param entity
     * @return
     */
    @POST("TracedMaturityList.ashx?")
    Observable<TracedMaturityEntity> getTracedMaturityList(@Body TracedMaturityEntity entity);

    /**
     * 获取当前用户的下属用户
     */

    @POST("DeptList.ashx?")
    Observable<TracedMaturityEntity> getCurrentUserUnderlingList(@Body TracedMaturityEntity entity);


    /**
     * 客户跟进：详情
     *
     * @param entity
     * @return
     */
    @POST("CustomerTraceDetails.ashx?")
    Observable<CustomerTraceEntity> getCustomerTraceDetails(@Body CustomerTraceEntity entity);

    /**
     * 删除跟进记录
     *
     * @param entity
     * @return
     */
    @POST("CustomerTraceHandler.ashx?")
    Observable<SubmitEntity> getCustomerTraceHandler(@Body SubmitEntity entity);

    /**
     * 投入公海
     *
     * @param entity
     * @return
     */
    @POST("CustomerInSeaHandler.ashx?")
    Observable<SubmitEntity> getCustomerInSeaHandler(@Body SubmitEntity entity);

    /**
     * 客户报备
     *
     * @param entity
     * @return
     */
    @POST("CustomerReport.ashx?")
    Observable<SubmitEntity> getCustomerReport(@Body SubmitEntity entity);

    /**
     * 客户联系人
     *
     * @param entity
     * @return
     */
    @POST("UserList.ashx?")
    Observable<RelationManEntity> getUserListInfo(@Body RelationManEntity entity);

    /**
     * 共享客户
     *
     * @param entity
     * @return
     */
    @POST("CustomerShareHandler.ashx?")
    Observable<SubmitEntity> getCustomerShareHandler(@Body SubmitEntity entity);

    /**
     * 转移客户
     *
     * @param entity
     * @return
     */
    @POST("CustomerMigrateHandler.ashx?")
    Observable<SubmitEntity> getCustomerMigrateHandler(@Body SubmitEntity entity);

    /**
     * 请假申请：详情
     *
     * @param entity
     * @return
     */
    @POST("LeaveDetails.ashx?")
    Observable<LeaveApplyDetailEntity> getLeaveDetails(@Body LeaveApplyDetailEntity entity);

    /**
     * 新--请假申请：详情 在办 完结

     *
     * @param entity
     * @return
     */
    @POST("LeaveDetails.ashx?")
    Observable<spInfo> getNewLeaveDetails(@Body spInfo entity);

    /**
     * 新--请假申请：待办详情
     *
     * @param entity
     * @return
     */
    @POST("LeaveCheck.ashx?")
    Observable<spInfo> getNewLeaveCheck(@Body spInfo entity);

    /**
     * 新--请假申请：回退节点列表
     *
     * @param entity
     * @return
     */
    @POST("LeaveCheck.ashx?")
    Observable<dialogListviewInfo> getNewLeaveCheckDialogList(@Body dialogListviewInfo entity);

    /**
     * 新--出差申请：回退节点列表
     *
     * @param entity
     * @return
     */
    @POST("TravelCheck.ashx?")
    Observable<ccSPInfo> getNewTravelCheckDialogList(@Body ccSPInfo entity);

    /**
     * 出差
     * 申请：详情
     *
     * @param entity
     * @return
     */
    @POST("TravelDetails.ashx?")
    Observable<TravelApplyDetailEntity> getTravelDetails(@Body TravelApplyDetailEntity entity);


    /**
     * 新----出差
     * 申请：详情
     *
     * @param entity
     * @return
     */
    @POST("TravelDetails.ashx?")
    Observable<ccSPInfo> getNewTravelDetails(@Body ccSPInfo entity);

    /**
     * 签到记录
     *
     * @param entity
     * @return
     */
    @POST("VisitRecordList.ashx?")
    Observable<VisitRecordEntity> getVisitRecorList(@Body VisitRecordEntity entity);

    /**
     * (新) 写日报==类型
     *
     * @param entity
     * @return
     */
    @POST("BaseDataTypeList.ashx")
    Observable<NewWorkDailyEntity> getBaseDataTypeList(@Body NewWorkDailyEntity entity);

    /**
     * (新) 写日报==获取昨天日报情况
     *
     * @param entity
     * @return
     */
    @POST("PM_GetPlanProjectToStr.ashx")
    Observable<SubmitEntity> getPlanProjectToStr(@Body SubmitEntity entity);


    /**
     * (新) 填写昨日日报
     *
     * @param entity
     * @return
     */
    @POST("PM_NewDayLogFill.ashx")
    Observable<NewDay> getNewDayLogFill(@Body NewDay entity);

    /**
     * (新) 写日报==提交日报
     *
     * @param entity
     * @return
     */
    @POST("PM_NewDayLogItemEdit.ashx")
    Observable<SubmitEntity> getNewDayLogItemEdit(@Body SubmitEntity entity);

//    /**
//     * (新) 编辑日报==提交日报
//     *
//     * @param entity
//     * @return PM_NewDayLogItemEdit
//     */
//    @POST("PM_NewDayLogItemEdit.ashx")
//    Observable<SubmitEntity> getNewDayLogWriter(@Body SubmitEntity entity);

    /**
     * 项目列表
     *
     * @param entity
     * @return
     */
    @POST("GetProjectList.ashx")
    Observable<WorkProjectEntity> getProjectList(@Body WorkProjectEntity entity);

    /**
     * 拜访签到：提交
     *
     * @param entity
     * @return
     */
    @POST("VisitRecordEdit.ashx?")
    Observable<SubmitEntity> getVisitRecordEdit(@Body SubmitEntity entity);

    /**
     * 外派签到：提交
     *
     * @param entity
     * @return
     */
    @POST("OutKq.ashx?")
    Observable<SubmitEntity> getOutKq(@Body SubmitEntity entity);

    /**
     * 实时定位
     *
     * @return
     */

    @POST("GetNewestMapLocation.ashx?")
    Observable<LocationEntity1> getRealLocationInfo(@Body LocationEntity1 entity);

    /**
     * 上传位置信息
     *
     * @return
     */

    @POST("AddMapLocation.ashx?")
    Observable<LocationInfo> getLoctionInfo(@Body LocationInfo entity);

    /**
     * 版本检查
     *
     * @return
     */

    @POST("GetVersionDetails.ashx?")
    Observable<VersionInfo> getGetVersion(@Body RequestBody body);
}
