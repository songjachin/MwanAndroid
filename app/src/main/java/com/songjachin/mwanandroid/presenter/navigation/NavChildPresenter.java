package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.NaviBean;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.navigation.INavChildCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class NavChildPresenter implements INavChildPresenter {
    private final Api mApi;

    private  NavChildPresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static class NavChildHolder{
        private static final NavChildPresenter sInstance = new NavChildPresenter();
    }

    public static NavChildPresenter getInstance(){
        return NavChildHolder.sInstance;
    }

    private INavChildCallback mINavChildCallback;

    @Override
    public void getNavigationChild() {
        Call<NaviBean> navigation = mApi.getNavigation();
        navigation.enqueue(new Callback<NaviBean>() {
            @Override
            public void onResponse(Call<NaviBean> call, Response<NaviBean> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK&&mINavChildCallback!=null){
                    NaviBean body = response.body();
                    List<NaviBean.DataBean> data = body.getData();
                    if(data.size()==0){
                        mINavChildCallback.onEmpty();
                    }else{
                        mINavChildCallback.onContentLoaded(data);
                    }
                }
            }

            @Override
            public void onFailure(Call<NaviBean> call, Throwable t) {
                mINavChildCallback.onError();
            }
        });
    }

    @Override
    public void registerViewCallback(INavChildCallback callback) {
        mINavChildCallback = callback;
    }

    @Override
    public void unregisterViewCallback(INavChildCallback callback) {
        mINavChildCallback =null;
    }
}
