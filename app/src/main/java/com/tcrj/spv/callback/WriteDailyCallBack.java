package com.tcrj.spv.callback;

import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 写日报
 * Created by leict on 2017/10/26.
 */

public interface WriteDailyCallBack {

    //ui逻辑
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
        void submit(SubmitEntity entity);

        /**
         * 获取日报数据
         *
         * @param entity
         */
        void getData(SubmitEntity entity);

        /**
         * 设置参数
         *
         * @return
         */
        ParameterEntity setParameter();
    }



    //业务逻辑
    interface Presenter extends BasePresenter {
        void submit();

        void loadData();

        void WriterloadData();
    }
}
