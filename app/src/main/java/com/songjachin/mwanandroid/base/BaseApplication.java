package com.songjachin.mwanandroid.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class BaseApplication extends Application {
    private static Context appContext;
    private  static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        sHandler = new Handler();

        if(User.getInstance().isLoginStatus()){
            String count = User.getInstance().getUsername();
            String password = User.getInstance().getPassword();

            if(!TextUtils.isEmpty(count)&& !TextUtils.isEmpty(password)){
                Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
                Api api = retrofit.create(Api.class);
                Call<BaseResponse<Login>> login = api.login(count, password);
                login.enqueue(new Callback<BaseResponse<Login>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Login>> call, Response<BaseResponse<Login>> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {

                    }
                });
            }
        }
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Handler getHandler(){
        return  sHandler;
    }

}
