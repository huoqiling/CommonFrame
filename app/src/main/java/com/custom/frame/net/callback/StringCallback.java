package com.custom.frame.net.callback;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.request.BaseRequest;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * @date 2017/12/12
 * @author zhangxin
 * @description String
 */
public abstract class StringCallback extends AbsCallback<String> {

    private HttpUrl httpUrl = null;

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        try {
            CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
            List<Cookie> cookies = cookieStore.getCookie(httpUrl);
            request.headers("Cookie", cookies.toString().substring(1, cookies.toString().length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        response.close();
        return s;
    }
}