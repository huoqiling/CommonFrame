package com.custom.frame.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * @author zhangxin
 * @date 2017/12/12
 * @description Gson解析工具类
 */
public class GsonTools {

    /**
     * 用Gson方式 把object 保存为 json字符串
     *
     * @param object
     * @return
     */
    public static String creatJsonString(@NonNull Object object) throws Exception {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }

    /**
     * 用Gson方式 把json字符串 保存为 object
     *
     * @param response
     * @param clz
     * @return
     */
    public static <T> T getResponse(@NonNull String response, @NonNull Class<T> clz) throws Exception {
        Gson gson = new Gson();
        T rsp = gson.fromJson(response, clz);
        return rsp;
    }

}
