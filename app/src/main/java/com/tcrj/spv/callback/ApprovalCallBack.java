package com.tcrj.spv.callback;

import com.tcrj.spv.model.ApprovalEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 审批
 * Created by leict on 2017/11/3.
 */

public interface ApprovalCallBack {
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
        void getData(ApprovalEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        String setParamStaffNo();
    }

    interface Presenter extends BasePresenter {
        void loadPendedData();

        void loadOfficeData();

        void loadFinishData();
    }
}
