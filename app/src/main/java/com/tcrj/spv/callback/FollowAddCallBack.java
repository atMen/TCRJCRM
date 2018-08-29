package com.tcrj.spv.callback;

import com.tcrj.spv.model.ContactListEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.TracedMaturityEntity;
import com.tcrj.spv.presenter.BasePresenter;

/**
 * 添加跟进
 * Created by leict on 2017/12/1.
 */

public interface FollowAddCallBack {
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
         * 获取联系人数据
         *
         * @param entity
         */
        void loadContact(ContactListEntity entity);

        /**
         * 获取客户成熟度
         *
         * @param entity
         */
        void loadTraced(TracedMaturityEntity entity);

        /**
         * 提交跟进记录
         *
         * @param entity
         */
        void save(SubmitEntity entity);

        /**
         * 参数
         *
         * @return
         */
        ParameterEntity getParameters();
    }

    interface Presenter extends BasePresenter {
        void getContact();

        void getTraced();

        void submit();
    }
}
