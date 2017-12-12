package com.custom.frame.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangxin
 * @date 2017/12/12
 * @description 时间工具
 */
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_EN = "dd MMMM yyyy hh:mm";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 转化为时间格式
     *
     * @param millis
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringFromLong(long millis, @NonNull String format) {
        String time = "0";
        if (TextUtils.isEmpty(format)) {
            return time;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date dt = new Date(millis);
            time = sdf.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getStringFromLong(long millis) {
        return getStringFromLong(millis, DATE_FORMAT);
    }

}
