package com.songjachin.mwanandroid.presenter.wx;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.Categories;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.wx.IWxCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class WxPresenter implements IWxPresenter {

    private final Retrofit mRetrofit;
    private IWxCallback mIWxCallback;

    private WxPresenter(){
        mRetrofit = RetrofitManager.getInstance().getRetrofit();
    }

    private static volatile WxPresenter sInstance;

    public static WxPresenter getInstance(){
        if (sInstance == null) {
            synchronized (WxPresenter.class){
                if (sInstance == null) {
                    sInstance = new WxPresenter();
                }
            }
        }

        return sInstance;
    }

    @Override
    public void registerViewCallback(IWxCallback callback) {
        mIWxCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IWxCallback callback) {
        mIWxCallback = null;
    }

    @Override
    public void getCategories() {
        Api api = mRetrofit.create(Api.class);
        Call<Categories> categories = api.getCategories();
        categories.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK && mIWxCallback!=null){
                    Categories body = response.body();
                    handleResult(body);
                }else{
                    if (mIWxCallback != null) {
                        mIWxCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                if (mIWxCallback != null) {
                    mIWxCallback.onError();
                }
            }
        });
    }

    private void handleResult(Categories body) {
        List<Categories.DataBean> data = body.getData();
        if(data.size() == 0){
            mIWxCallback.onEmpty();
        }else{
            mIWxCallback.onCategoriesLoaded(body);
        }
    }
}
