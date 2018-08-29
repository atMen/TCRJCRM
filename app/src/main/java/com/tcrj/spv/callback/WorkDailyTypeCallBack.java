package com.tcrj.spv.callback;

import com.tcrj.spv.model.NewWorkDailyEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 日报分类
 * Created by leict on 2017/12/16.
 */

public interface WorkDailyTypeCallBack {
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
        void getType(NewWorkDailyEntity entity);
    }

    interface Presenter extends BasePresenter {

        void loadData();
    }
}
