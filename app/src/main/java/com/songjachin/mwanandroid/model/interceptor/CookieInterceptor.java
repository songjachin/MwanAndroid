package com.songjachin.mwanandroid.model.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by matthew
 */
public class CookieInterceptor implements Interceptor {
    private String username;
    private String password;
    public CookieInterceptor(String username,String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", username);
        builder.addHeader("Cookie", password);
        return chain.proceed(builder.build());
    }
}
