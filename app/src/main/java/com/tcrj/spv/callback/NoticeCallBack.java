package com.tcrj.spv.callback;

import com.tcrj.spv.model.NoticeEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 通知公告
 * Created by leict on 2017/10/31.
 */

public interface NoticeCallBack {
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
        void getData(NoticeEntity entity);

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
