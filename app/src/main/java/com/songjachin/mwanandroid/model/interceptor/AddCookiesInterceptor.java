package com.songjachin.mwanandroid.model.interceptor;

import android.content.Context;

import com.songjachin.mwanandroid.base.BaseApplication;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by matthew
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> stringSet = BaseApplication.getAppContext().getSharedPreferences("cookieData", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (stringSet != null) {
            for (String s : stringSet) {
                builder.addHeader("Cookie",s);
            }
        }
        return chain.proceed(builder.build());
    }
}
