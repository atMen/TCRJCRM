package com.tcrj.spv.callback;

import com.tcrj.spv.model.NoticeDetailEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 通知公告详情
 * Created by leict on 2017/11/6.
 */

public interface NoticeDetailCallBack {
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
        void getData(NoticeDetailEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        int setParameterId();
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
