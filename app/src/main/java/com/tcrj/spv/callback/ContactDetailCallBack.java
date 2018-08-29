package com.tcrj.spv.callback;

import com.tcrj.spv.model.ContactDetailEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 联系人详情
 * Created by leict on 2017/11/28.
 */

public interface ContactDetailCallBack {
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
        void getData(ContactDetailEntity entity);

        /**
         * 删除联系人
         *
         * @param entity
         */
        void altered(SubmitEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        int cusId();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void delete();
    }
}
