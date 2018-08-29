package com.tcrj.spv.views.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leict on 2017/10/27.
 */

public class DateUtils {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 取得日期时间字串
     *
     * @param date Date对象
     * @return 日期时间字串，格式如：2003-12-02 13:10:00
     */
    public static String getDateTimeString(Date date) {
        return getString(date, DATETIME_PATTERN);
    }

    /**
     * 按照指定格式取得时间字串
     *
     * @param date    Date对象
     * @param pattern 时间字串的日期格式，如yyyy-MM-dd
     * @return 日期时间字串，格式如：2003-12-02 13:10:00
     */
    public static String getString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }





    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

}
