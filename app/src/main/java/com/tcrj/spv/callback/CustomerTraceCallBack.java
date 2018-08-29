package com.tcrj.spv.callback;

import com.tcrj.spv.model.FollowRecordEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 跟进记录
 * Created by leict on 2017/11/30.
 */

public interface CustomerTraceCallBack {
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
        void getData(FollowRecordEntity entity);

        /**
         * 参数
         *
         * @return
         */
        String cusId();
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
