package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.ccSPInfo;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 详情
 * Created by leict on 2017/12/7.
 */

public interface NewNowTravelApplyDetailCallBack {
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
        void getData(ccSPInfo entity);

        /**
         * 将网络请求得到的用户信息回调
         *
         * @param entity
         */
        void getTJData(ccSPInfo entity);


        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setParameters();


        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setSPParameters();
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void uploadData();
    }
}
