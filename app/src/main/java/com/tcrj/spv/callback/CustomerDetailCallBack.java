package com.tcrj.spv.callback;

import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 客户详情
 * Created by leict on 2017/11/27.
 */

public interface CustomerDetailCallBack {
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
        void getData(CustomerDetailEntity entity);

        /**
         * 投入公海
         *
         * @param entity
         */
        void highSeas(SubmitEntity entity);

        /**
         * 客户报备
         *
         * @param entity
         */
        void report(SubmitEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        String cusId();

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity getParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void intoSeas();

        void records();
    }
}
