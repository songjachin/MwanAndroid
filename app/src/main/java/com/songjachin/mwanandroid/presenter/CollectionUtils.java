package com.songjachin.mwanandroid.presenter;

import android.database.Observable;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.utils.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class CollectionUtils {
    private final Api mApi;
    private CollectionUtils(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static class CollectionUtilsHolder{
        private static final CollectionUtils instance = new CollectionUtils();
    }

    public static CollectionUtils getInstance(){
        return CollectionUtilsHolder.instance;
    }

    public void collect(int id,ICollectCallback callback){
        Call<BaseResponse> collect = mApi.collect(id);
        collect.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body()!=null&&response.body().getErrorCode()==0) {
                    callback.onCollectSuccessful();
                }else{
                    callback.onCollectFail();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onCollectFail();
            }
        });
    }
    public void unCollect(int id,int originalId,IUnCollectCallback callback){
        Call<BaseResponse> baseResponseCall = mApi.unCollect(id, originalId);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body()!=null&&response.body().getErrorCode()==0) {
                    callback.onUnCollectSuccessful();
                }else{
                    callback.onUnCollectFail();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onUnCollectFail();
            }
        });
    }
}
