package com.custom.frame.utils;

import com.custom.frame.CustomApp;

/**
 * @Author zhangxin
 * @date 2017/7/3 14:53
 * @description 转换工具
 **/
public class DensityUtil {

    public static int dip2px(float dpValue) {
        final float scale = CustomApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = CustomApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



}
