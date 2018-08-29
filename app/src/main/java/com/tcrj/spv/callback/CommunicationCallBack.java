package com.tcrj.spv.callback;

import com.tcrj.spv.model.CommunicationEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 通讯录详情
 * Created by leict on 2017/11/20.
 */

public interface CommunicationCallBack {
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
        void getData(CommunicationEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        String staffId();
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
