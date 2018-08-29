package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.TravelApplyDetailEntity;
import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 出差申请：详情
 * Created by leict on 2017/12/8.
 */

public interface TravelApplyDetailCallBack {
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
        void getData(TravelApplyDetailEntity entity);

        /**
         * 获取类型
         *
         * @param entity
         */
        void getType(TravelApplyEntity entity);

        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void kind();
    }
}
