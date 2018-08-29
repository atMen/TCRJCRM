package com.tcrj.spv.callback;

import com.tcrj.spv.model.LeaveApplyEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 请假申请
 * Created by leict on 2017/11/15.
 */

public interface LeaveApplyCallBack {
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

        /**
         * 提交接口参数
         *
         * @param entity
         */
        void save(LeaveApplyEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void submit();

        void loadData();
    }
}
