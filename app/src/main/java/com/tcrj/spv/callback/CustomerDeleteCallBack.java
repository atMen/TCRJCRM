package com.tcrj.spv.callback;

import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 删除客户/客户详情
 * Created by leict on 2017/11/28.
 */

public interface CustomerDeleteCallBack {
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
        void altered(SubmitEntity entity);

        /**
         * 获取数据
         *
         * @param entity
         */
        void getData(CustomerDetailEntity entity);

        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void delete();

        void loadData();
    }
}
