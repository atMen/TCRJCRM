package com.tcrj.spv.callback;

import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 联系人列表
 * Created by leict on 2017/11/28.
 */

public interface ContactListCallBack {
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
        void getData(ContactListEntity entity);

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
