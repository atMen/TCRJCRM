package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.RelationManEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 客户联系人
 * Created by leict on 2017/12/6.
 */

public interface RelationManCallBack {
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
        void getData(RelationManEntity entity);

        /**
         * 共享客户
         *
         * @param entity
         */
        void getShare(SubmitEntity entity);

        /**
         * 转移客户
         *
         * @param entity
         */
        void getTransfer(SubmitEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void enjoy();

        void move();
    }
}
