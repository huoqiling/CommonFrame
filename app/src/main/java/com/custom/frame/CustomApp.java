package com.custom.frame;

import android.app.Activity;
import android.app.Application;

import com.custom.frame.net.LoggerInterceptor;
import com.custom.frame.utils.CustomActivityManage;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;

/**
 * @author zhangxin
 * @date 2017/12/11
 * @description 自定义application
 */
public class CustomApp extends Application {

    private static CustomApp instance;
    private CustomActivityManage activityManage;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initOkGo();
    }

    private void init() {
        instance = this;
        activityManage = new CustomActivityManage();
    }

    /**
     * 初始化okgo
     */
    private void initOkGo() {

        HttpHeaders headers = new HttpHeaders();
        headers.put("Charset", "UTF-8");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        OkGo.init(this);
        OkGo.getInstance()
                .addInterceptor(new LoggerInterceptor("OkGoDebug", BuildConfig.DEBUG)) //建议设置OkHttpClient，不设置会使用默认的
                .setConnectTimeout(15 * 1000)  //全局的连接超时时间
                .setReadTimeOut(15 * 1000)     //全局的读取超时时间
                .setWriteTimeOut(15 * 1000)    //全局的写入超时时间
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                .setRetryCount(0)
                .setCookieStore(new PersistentCookieStore())
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .addCommonHeaders(headers);
    }


    public static CustomApp getInstance() {
        if (instance == null) {
            instance = new CustomApp();
        }
        return instance;
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityManage.addActivity(activity);
    }

    /**
     * 删除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityManage.removeActivity(activity);
    }

    /**
     * 删除所有activity
     */
    public void finishAllActivity() {
        activityManage.finishAllActivity();

    }
}
