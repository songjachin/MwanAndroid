package com.songjachin.mwanandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by matthew
 */
public class CommonUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
