package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * Created by leict on 2017/12/27.
 */

public interface NewDayLogItemListCallBack {
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
        void getData(WorkDailtItemEntity entity);

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
