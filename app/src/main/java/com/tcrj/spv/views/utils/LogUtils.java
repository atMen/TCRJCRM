package com.tcrj.spv.views.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.tcrj.spv.model.CustomerEntity;
import com.tcrj.spv.model.ParameterEntity;

/**
 * 日志工具类
 * Created by leict on 2017/10/27.
 */

public class LogUtils {
    /**
     * 实体转JSON
     *
     * @param tag
     * @param entity
     */
    public static void info(String tag, ParameterEntity entity) {
        Gson g = new Gson();
        String log = g.toJson(entity);
        Log.i(tag, "param-----------" + log);
    }
    public static void info(String tag, CustomerEntity entity) {
        Gson g = new Gson();
        String log = g.toJson(entity);
        Log.i(tag, "param-----------" + log);
    }


    /**
     * 日志
     *
     * @param tag  类名
     * @param str1 参数
     */
    public static void info(String tag, String str1) {
        Log.i(tag, "---------------：" + str1);
    }

    /**
     * 日志
     *
     * @param tag  类名
     * @param str1 参数1
     * @param str2 参数2
     */
    public static void info(String tag, String str1, String str2) {
        Log.i(tag, "===============：" + str1 + "\n\r===============：" + str2);
    }

    /**
     * 日志
     *
     * @param tag  类名
     * @param str1 参数1
     * @param str2 参数2
     * @param str3 参数3
     */
    public static void info(String tag, String str1, String str2, String str3) {
        Log.i(tag, "~~~~~~~~~~~~~~~：" + str1 + "\n\r~~~~~~~~~~~~~~~：" + str2 + "\n\r~~~~~~~~~~~~~~~：" + str3);
    }

    /**
     * 日志
     *
     * @param tag  类名
     * @param str1 参数1
     * @param str2 参数2
     * @param str3 参数3
     * @param str4 参数4
     */
    public static void info(String tag, String str1, String str2, String str3, String str4) {
        Log.i(tag, "***************：" + str1 + "\n\r***************：" + str2 + "\n\r***************：" + str3 + "\n\r***************：" + str4);
    }

    /**
     * 日志
     *
     * @param tag  类名
     * @param str1 参数1
     * @param str2 参数2
     * @param str3 参数3
     * @param str4 参数4
     * @param str5 参数5
     */
    public static void info(String tag, String str1, String str2, String str3, String str4, String str5) {
        Log.i(tag, "###############：" + str1 + "\n\r###############：" + str2 + "\n\r###############：" + str3 + "\n\r###############：" + str4 + "\n\r###############：" + str5);
    }
}
