package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.KnowArticleBean;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.utils.UrlUtils;
import com.songjachin.mwanandroid.view.navigation.IKnowArticleCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class KnowArticlePresenter implements IKnowArticlePresenter{
    private int mCurrentPage = 0;
    private static final String TAG = "KnowArticlePresenter";
    private Api mApi;

    private IKnowArticleCallback mIKnowArticleCallback;

    private KnowArticlePresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static volatile KnowArticlePresenter sInstance;

    public static KnowArticlePresenter getInstance(){
        if (sInstance == null) {
            synchronized (KnowArticlePresenter.class){
                if (sInstance == null) {
                    sInstance = new KnowArticlePresenter();
                }
            }
        }

        return sInstance;
    }


    @Override
    public void getKnowArticle(int page, int id) {
        mCurrentPage = 0;
        String url = UrlUtils.getKnowArticleUrl(mCurrentPage, id);

        Call<KnowArticleBean> knowArticle = mApi.getKnowArticle(url);
        knowArticle.enqueue(new Callback<KnowArticleBean>() {
            @Override
            public void onResponse(Call<KnowArticleBean> call, Response<KnowArticleBean> response) {
                KnowArticleBean body = response.body();
                int code = response.code();
                boolean flag = (body ==null);
                LogUtils.d(TAG,"code-----------"+code+"--------------------------->"+flag);
                if (mIKnowArticleCallback != null) {
                    mIKnowArticleCallback.onContentLoaded(body);
                }
            }

            @Override
            public void onFailure(Call<KnowArticleBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadMore(int id) {
        mCurrentPage++;
        String url = UrlUtils.getKnowArticleUrl(mCurrentPage, id);

        Call<KnowArticleBean> knowArticle = mApi.getKnowArticle(url);
        knowArticle.enqueue(new Callback<KnowArticleBean>() {
            @Override
            public void onResponse(Call<KnowArticleBean> call, Response<KnowArticleBean> response) {
                KnowArticleBean body = response.body();
                List<KnowArticleBean.DataBean.DatasBean> datas = body.getData().getDatas();
                if(datas.size() ==0||datas==null){
                    mIKnowArticleCallback.onLoadMoreEmpty();
                }else{
                    mIKnowArticleCallback.onLoadMore(datas);
                }
            }

            @Override
            public void onFailure(Call<KnowArticleBean> call, Throwable t) {
                mIKnowArticleCallback.onLoadMoreError();
            }
        });
    }



    @Override
    public void registerViewCallback(IKnowArticleCallback callback) {
        mIKnowArticleCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IKnowArticleCallback callback) {
        mIKnowArticleCallback =null;
    }
}
