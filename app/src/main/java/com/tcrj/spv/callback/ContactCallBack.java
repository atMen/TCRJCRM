package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 添加及修改客户联系人详细信息
 * Created by leict on 2017/11/24.
 */

public interface ContactCallBack {
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
        void getData(SubmitEntity entity);

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
