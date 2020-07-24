package com.songjachin.mwanandroid.presenter.talk;


import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.presenter.home.HomePresenter;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.talk.ITalkCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class TalkPresenter   implements ITalkPresenter {

    private ITalkCallback mITalkCallback;
    private final Retrofit mRetrofit;
    private int mPage = 0;

    private TalkPresenter(){
        mRetrofit = RetrofitManager.getInstance().getRetrofit();
    }

    private static class TalkPresenterHolder{
        private static final TalkPresenter sInstance = new TalkPresenter();
    }

    public static TalkPresenter getInstance(){
        return TalkPresenterHolder.sInstance;
    }

    @Override
    public void getTalkArticle() {
        Api api = mRetrofit.create(Api.class);
        Call<ArticleHomeBean> question = api.getQuestion(mPage);
        question.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK && mITalkCallback!=null){
                    ArticleHomeBean body = response.body();
                    handleQuestion(body);
                }else{
                    if(mITalkCallback!=null){
                        mITalkCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                if(mITalkCallback!=null){
                    mITalkCallback.onError();
                }
            }
        });
    }

    private void handleQuestion(ArticleHomeBean body) {
        List<ArticleHomeBean.DataBean.DatasBean> datas = body.getData().getDatas();
        if(datas.size()==0){
            mITalkCallback.onEmpty();
        }else{
            mPage++;
            mITalkCallback.onArticleLoad(datas);
        }
    }


    @Override
    public void loadMore() {
        Api api = mRetrofit.create(Api.class);
        Call<ArticleHomeBean> question = api.getQuestion(mPage);
        question.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK && mITalkCallback!=null) {
                    ArticleHomeBean body = response.body();
                    handleLoadMore(body);
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                if(mITalkCallback!=null){
                    mITalkCallback.onLoadMoreError();
                }
            }
        });
    }

    private void handleLoadMore(ArticleHomeBean body) {
        List<ArticleHomeBean.DataBean.DatasBean> datas = body.getData().getDatas();
        if(datas.size()==0){
            mITalkCallback.onLoadMoreEmpty();
        }else{
            mPage++;
            mITalkCallback.onLoadMoreLoaded(datas);
        }
    }

    @Override
    public void registerViewCallback(ITalkCallback callback) {
        mITalkCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ITalkCallback callback) {
        mITalkCallback = null;
    }
}
