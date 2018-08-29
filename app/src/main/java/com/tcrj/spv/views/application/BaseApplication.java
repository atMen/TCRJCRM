package com.tcrj.spv.views.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.views.utils.XMLName;

/**
 * 全局变量
 * Created by leict on 2017/10/23.
 */

public class BaseApplication extends Application {
    public static UserInfoEntity user;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mApplication = this;
    }

    /**
     * http://192.168.20.121:1230/DTKCRM/Api/ 刘飞龙
     * http://111.21.32.158:8000/DTKCRM/api/  外网
     * http://123.139.46.180:8006/DTKCRM/api/  智慧延安
     * @return
     */
    public static String getConfig() {
        return "http://123.139.46.180:8006/DTKCRM/api/";
    }

    /**
     * 设置用户数据
     *
     * @param context
     * @param entity
     */
    public static void setUserInfoEntity(Context context, UserInfoEntity entity) {
        user = entity;
        if (entity == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
        SharedPreferences.Editor e = share.edit();
        e.putInt(XMLName.NAME_USERID, entity.getEntity().getUserId());
        e.putInt(XMLName.NAME_ROLEID, entity.getEntity().getRoleId());
        e.putString(XMLName.NAME_USERTITLE, entity.getEntity().getUserTitle());
        e.putString(XMLName.NAME_USERNAME, entity.getEntity().getUserName());
        e.putString(XMLName.NAME_ROLENAME, entity.getEntity().getRoleName());
        e.putString(XMLName.NAME_EMAIL, entity.getEntity().getEmail());
        e.putString(XMLName.NAME_USERPWD, entity.getEntity().getUserPwd());
        e.putString(XMLName.NAME_TEL, entity.getEntity().getTel());
        e.putString(XMLName.NAME_CURRENTVERSION, entity.getEntity().getCurrentVersion());
        e.putInt(XMLName.NAME_ISSELL, entity.getEntity().getIsSell());
        e.putInt(XMLName.NAME_ISHAVEXJ, entity.getEntity().getIsHavexj());
        e.putInt(XMLName.NAME_INTEGRAL, entity.getEntity().getIntegral());
        e.putInt(XMLName.NAME_ISOUT, entity.getEntity().getIsOut());
        e.putInt(XMLName.NAME_ISKQ, entity.getEntity().getIsKq());
        e.commit();
    }

//    private static void setUserInfo() {
//
//        SharedPreferences e = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
//        user.getEntity().setUserId(e.getInt(XMLName.NAME_USERID, -1));
//        user.getEntity().setRoleId(e.getInt(XMLName.NAME_ROLEID, -1));
//        user.getEntity().setIsSell(e.getInt(XMLName.NAME_ROLEID, -1));
//        user.getEntity().setIsHavexj(e.getInt(XMLName.NAME_ISHAVEXJ, -1));
//        user.getEntity().setIntegral(e.getInt(XMLName.NAME_INTEGRAL, -1));
//        user.getEntity().setIsOut(e.getInt(XMLName.NAME_ISOUT, -1));
//        user.getEntity().setIsKq(e.getInt(XMLName.NAME_ISKQ, -1));
//        user.getEntity().setUserTitle(e.getString(XMLName.NAME_USERTITLE, null));
//        user.getEntity().setUserName(e.getString(XMLName.NAME_USERNAME, null));
//        user.getEntity().setRoleName(e.getString(XMLName.NAME_ROLENAME, null));
//        user.getEntity().setEmail(e.getString(XMLName.NAME_EMAIL, null));
//        user.getEntity().setUserPwd(e.getString(XMLName.NAME_USERPWD, null));
//        user.getEntity().setTel(e.getString(XMLName.NAME_TEL, null));
//        user.getEntity().setCurrentVersion(e.getString(XMLName.NAME_CURRENTVERSION, null));
//    }

    /**
     * 获取用户数据
     *
     * @return
     */
    public static UserInfoEntity getUserInfoEntity() {
        return user;
    }

    private static BaseApplication mApplication;
    public static BaseApplication getIntance() {
        return mApplication;
    }

}
