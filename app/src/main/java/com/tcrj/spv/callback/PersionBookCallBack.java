package com.tcrj.spv.callback;

import com.tcrj.spv.model.PersionBookEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 通讯录
 * Created by leict on 2017/11/17.
 */

public interface PersionBookCallBack {
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
        void getData(PersionBookEntity entity);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
