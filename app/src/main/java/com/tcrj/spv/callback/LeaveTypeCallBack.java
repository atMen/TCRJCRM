package com.tcrj.spv.callback;

import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 出差申请/请假申请：类别
 * Created by leict on 2017/11/14.
 */

public interface LeaveTypeCallBack {
    interface View extends BaseView<Presenter> {
        /**
         * 展示加载框
         */
        void LoadingOn();

        /**
         * 取消加载框
         */
        void LoadingOff();

        /**
         * 将网络请求得到的用户信息回调
         *
         * @param entity
         */
        void getData(LeaveApplyEntity entity);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
