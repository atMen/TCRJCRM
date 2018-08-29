package com.tcrj.spv.callback;

import com.tcrj.spv.model.TravelApplyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * Created by leict on 2017/11/15.
 */

public interface TrafficToolCallBack {
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
        void getData(TravelApplyEntity entity);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
