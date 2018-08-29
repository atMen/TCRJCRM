package com.tcrj.spv.callback;

import com.tcrj.spv.model.VisitRecordEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 拜访记录
 * Created by leict on 2017/12/11.
 */

public interface VisitRecordCallBack {
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
        void getData(VisitRecordEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        int StaffId();
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
