package com.custom.frame.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;

/**
 * @author zhangxin
 * @date 2017/12/12
 * @description OkGo帮助类 公共的doGet,doPost
 */
public class OkGoUtil {

    /**
     * post方法 不带参数
     *
     * @param url 地址
     * @param tag 标志
     * @param <T> 实体类
     */
    public static <T> void doPost(@NonNull String url, @Nullable Object tag, @NonNull AbsCallback<T> callback) {
        doPost(url, tag, null, callback);
    }

    /**
     * post方法 带参数
     *
     * @param url        地址
     * @param tag        标志
     * @param httpParams 参数
     * @param <T>        实体类
     */
    public static <T> void doPost(@NonNull String url, @Nullable Object tag, @Nullable HttpParams httpParams, @NonNull AbsCallback<T> callback) {
        if (null != httpParams) {
            OkGo.post(url).tag(tag).params(httpParams).execute(callback);
        } else {
            OkGo.post(url).tag(tag).execute(callback);
        }
    }

    /**
     * 不带参数的get方法
     *
     * @param url 地址
     * @param tag 标志
     * @param <T> 实体类
     */
    public static <T> void doGet(@NonNull String url, @Nullable Object tag, @NonNull AbsCallback<T> callback) {
        doGet(url, tag, null, callback);
    }

    /**
     * 待参数的get方法
     *
     * @param url        地址
     * @param tag        标志
     * @param httpParams 参数
     * @param <T>        实体类
     */
    public static <T> void doGet(@NonNull String url, @Nullable Object tag, @Nullable HttpParams httpParams, @NonNull AbsCallback<T> callback) {
        if (null != httpParams) {
            OkGo.get(url).tag(tag).params(httpParams).execute(callback);
        } else {
            OkGo.get(url).tag(tag).execute(callback);
        }
    }

}


