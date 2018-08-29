package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SpinnerEntity;
import com.tcrj.spv.model.WorkDailyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 工作日报
 * Created by leict on 2017/11/2.
 */

public interface WorkDailyCallBack {
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
         * 获取日报数据
         *
         * @param entity
         */
        void getData(WorkDailyEntity entity);

        void getBmData(SpinnerEntity spinnerEntity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void loadBmData();
    }
}
