package com.songjachin.mwanandroid.presenter.home;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.BannerBean;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.home.IHomeCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class HomePresenter implements IHomePresenter {

    private Retrofit mRetrofit;
    private int mPageId = 0;

    private HomePresenter(){
        mRetrofit = RetrofitManager.getInstance().getRetrofit();
    }

    private static volatile HomePresenter instance;


    public static HomePresenter getInstance(){
        if (instance == null) {
            synchronized (HomePresenter.class){
                if (instance == null) {
                    instance = new HomePresenter();
                }
            }
        }

        return instance;
    }

    private IHomeCallback mIHomeCallback = null;

    @Override
    public void getBanner() {
        if (mIHomeCallback != null) {
            mIHomeCallback.onLoading();
        }

        Api api = mRetrofit.create(Api.class);
        Call<BannerBean> task = api.getBanner();
        task.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                int code = response.code();

                LogUtils.d(HomePresenter.class,"response code is--->"+code);
                if(code == HttpURLConnection.HTTP_OK&&mIHomeCallback!=null){
                    BannerBean body = response.body();
                    handleResult(body);
                }else {
                    if (mIHomeCallback != null) {
                        mIHomeCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                if (mIHomeCallback != null) {
                    mIHomeCallback.onError();
                }
            }
        });
    }

    private void handleResult(BannerBean body) {
        List<BannerBean.DataBean> data = body.getData();
        //data.clear();
        if (data.size()==0) {
            mIHomeCallback.onEmpty();
        }else{
            mIHomeCallback.onBannerLoaded(data);
        }
    }

    @Override
    public void getArticle() {
        if (mIHomeCallback != null) {
            mIHomeCallback.onLoading();
        }
        Api api = mRetrofit.create(Api.class);
        Call<ArticleBean> task = api.getArticle();
        task.enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.class,"response code is--->"+code);
                if(code == HttpURLConnection.HTTP_OK&&mIHomeCallback!=null){
                    ArticleBean body = response.body();
                    handleArticle(body);
                }else{
                    if (mIHomeCallback != null) {
                        mIHomeCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                if (mIHomeCallback != null) {
                    mIHomeCallback.onError();
                }
            }
        });

    }

    private void handleArticle(ArticleBean body) {
        List<ArticleBean.DataBean> data = body.getData();
        if (data.size()==0) {
            mIHomeCallback.onEmpty();
        }else{
            mIHomeCallback.onArticleLoad(data);
        }
    }

    @Override
    public void loadMore() {

        Api api = mRetrofit.create(Api.class);
        Call<ArticleHomeBean> task = api.getHomeArticle(mPageId);
        task.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.class,"response code is--->"+code);
                if(code == HttpURLConnection.HTTP_OK&&mIHomeCallback!=null){
                    ArticleHomeBean body = response.body();
                    handleHomeArticle(body);
                }else{
                    if (mIHomeCallback != null) {
                        mIHomeCallback.onLoadMoreError();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                if (mIHomeCallback != null) {
                    mIHomeCallback.onLoadMoreError();
                }
            }
        });

    }

    private void handleHomeArticle(ArticleHomeBean body) {
        List<ArticleHomeBean.DataBean.DatasBean> datas = body.getData().getDatas();
        if (datas.size()==0) {
            mIHomeCallback.onLoadMoreEmpty();
        }else{
            mPageId++;
            mIHomeCallback.onLoadMoreLoaded(datas);
        }

    }

    @Override
    public void collect() {

    }

    @Override
    public void unCollect() {

    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        mIHomeCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        mIHomeCallback = null;
    }
}
