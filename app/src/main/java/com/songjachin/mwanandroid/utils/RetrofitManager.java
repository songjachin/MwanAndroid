package com.songjachin.mwanandroid.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matthew
 */
public class RetrofitManager {
    private Retrofit mRetrofit;
    private static  RetrofitManager ourInstance;
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
    private RetrofitManager(){
         mRetrofit = new Retrofit.Builder()
         .baseUrl("https://www.wanandroid.com/")
         .addConverterFactory(GsonConverterFactory.create())
         .build();
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
