package com.songjachin.mwanandroid.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by matthew
 */
public class BaseApplication extends Application {
    private static Context appContext;
    private  static android.os.Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        sHandler = new android.os.Handler();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Handler getHandler(){
        return  sHandler;
    }

}
