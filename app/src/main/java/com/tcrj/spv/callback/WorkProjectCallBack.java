package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.WorkProjectEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 项目列表
 * Created by leict on 2017/12/17.
 */

public interface WorkProjectCallBack {
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
         * 类型
         *
         * @param entity
         */
        void getData(WorkProjectEntity entity);

        /**
         * 参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {

        void loadData();
    }
}
