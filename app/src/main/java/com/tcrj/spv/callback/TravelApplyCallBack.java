package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 出差申请
 * Created by leict on 2017/11/15.
 */

public interface TravelApplyCallBack {
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
         * 提交申请
         *
         * @param entity
         */
        void submit(TravelApplyEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void submit();
    }
}
