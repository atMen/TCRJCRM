package com.tcrj.spv.callback;

import com.tcrj.spv.model.CustomerTraceEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 删除跟进记录/详情
 * Created by leict on 2017/12/4.
 */

public interface TraceDetailCallBack {
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
        void getData(CustomerTraceEntity entity);

        /**
         * 删除跟进记录
         *
         * @param entity
         */
        void delete(SubmitEntity entity);

        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void strike();
    }
}
