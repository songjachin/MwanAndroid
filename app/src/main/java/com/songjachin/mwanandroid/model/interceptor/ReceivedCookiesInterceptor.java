package com.songjachin.mwanandroid.model.interceptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.utils.LogUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by matthew
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @SuppressLint("CommitPrefEdits")
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if(!originalResponse.headers("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                LogUtils.d(this,"-----interceptor"+header);
                cookies.add(header);
            }
            SharedPreferences sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putStringSet("cookie",cookies);
            edit.apply();

            edit.commit();

        }
        return originalResponse;
    }
}
