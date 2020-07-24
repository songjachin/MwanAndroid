package com.songjachin.mwanandroid.utils;

import com.songjachin.mwanandroid.model.Constant;
import com.songjachin.mwanandroid.model.interceptor.AddCookiesInterceptor;
import com.songjachin.mwanandroid.model.interceptor.CacheInterceptor;
import com.songjachin.mwanandroid.model.interceptor.CookieInterceptor;
import com.songjachin.mwanandroid.model.interceptor.ReceivedCookiesInterceptor;
import com.songjachin.mwanandroid.ui.mine.User;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matthew
 */
public class RetrofitManager {
    private static Retrofit mRetrofit;
    private RetrofitManager(){
        if(User.getInstance().isLoginStatus()){
//            LogUtils.d(RetrofitManager.class,"hahahahahha"+User.getInstance().isLoginStatus());
//            User instance = User.getInstance();
//            String password = instance.getPassword();
//            String username = instance.getUsername();
//            CookieInterceptor interceptor = new CookieInterceptor(username,password);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ReceivedCookiesInterceptor())
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new CacheInterceptor())
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }else{
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


    }
    private static volatile RetrofitManager ourInstance;
    public static RetrofitManager getInstance(){
        if (ourInstance == null) {
            synchronized (RetrofitManager.class){
                if (ourInstance == null) {
                    ourInstance = new RetrofitManager();
                }
            }
        }
        return ourInstance;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public static void reBuildRetrofit(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new CacheInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
