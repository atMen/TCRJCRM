package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * Created by leict on 2017/10/23.
 */

public interface UserInfoCallBack {
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
         * 服务端失败信息
         */
        void ServiceFailInfo(String e);

        /**
         * 将网络请求得到的用户信息回调
         *
         * @param entity
         */
        void getData(UserInfoEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
