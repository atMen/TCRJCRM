package com.tcrj.spv.callback;

import com.tcrj.spv.model.IntentProductEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 意向产品
 * Created by leict on 2017/11/22.
 */

public interface IntentProductCallBack {
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
        void getData(IntentProductEntity entity);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}