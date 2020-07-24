package com.songjachin.mwanandroid.presenter.wx;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.wx.IWxPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class WxPagerPresenter implements IWxPagerPresenter {

    private final Retrofit mRetrofit;
    private List<IWxPagerCallback> mCallbacks = new ArrayList<>();
    private Map<Integer, Integer> pageOfWxIdInfos = new HashMap<>();
    public static final int DEFAULT_PAGE = 1;
    private Integer mCurrentPage;

    private WxPagerPresenter() {
        mRetrofit = RetrofitManager.getInstance().getRetrofit();
    }

    private static class WxPagerPresenterHolder {
        private static final WxPagerPresenter instance = new WxPagerPresenter();
    }

    public static WxPagerPresenter getInstance() {
        return WxPagerPresenterHolder.instance;
    }

    @Override
    public void registerViewCallback(IWxPagerCallback callback) {
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(IWxPagerCallback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public void getWxArticle(int wxId) {
        for (IWxPagerCallback callback : mCallbacks) {
            if (callback.getWxId() == wxId) {
                callback.onLoading();
            }
        }
        Integer targetPage = pageOfWxIdInfos.get(wxId);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pageOfWxIdInfos.put(wxId, targetPage);
        }
        Api api = mRetrofit.create(Api.class);
        Call<ArticleHomeBean> wxArticle = api.getWxArticle(wxId, targetPage);
        wxArticle.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    ArticleHomeBean body = response.body();
                    handleResult(body, wxId);
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                for (IWxPagerCallback callback : mCallbacks) {
                    if (callback.getWxId() == wxId) {
                        callback.onError();
                    }
                }
            }
        });

    }

    private void handleResult(ArticleHomeBean body, int wxId) {
        List<ArticleHomeBean.DataBean.DatasBean> datas = body.getData().getDatas();
        for (IWxPagerCallback callback : mCallbacks) {
            if (callback.getWxId() == wxId) {
                if (datas.size() == 0) {
                    callback.onEmpty();
                } else {
                    callback.onArticleLoad(datas);
                }
            }
        }
    }

    @Override
    public void loadMore(int wxId) {
        mCurrentPage = pageOfWxIdInfos.get(wxId);
        if (mCurrentPage == null) {
            mCurrentPage = DEFAULT_PAGE;
        }
        mCurrentPage++;
        Api api = mRetrofit.create(Api.class);
        Call<ArticleHomeBean> wxArticle = api.getWxArticle(wxId, mCurrentPage);
        wxArticle.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    ArticleHomeBean body = response.body();
                    handleLoadMore(body, wxId);
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                handleLoadMoreError(wxId);
            }
        });

    }

    private void handleLoadMoreError(int wxId) {
        mCurrentPage--;
        pageOfWxIdInfos.put(wxId, mCurrentPage);
        for (IWxPagerCallback callback : mCallbacks) {
            if (callback.getWxId() == wxId) {
                callback.onLoaderMoreError();
            }
        }
    }

    private void handleLoadMore(ArticleHomeBean body, int wxId) {
        List<ArticleHomeBean.DataBean.DatasBean> datas = body.getData().getDatas();
        for (IWxPagerCallback callback : mCallbacks) {
            if (callback.getWxId() == wxId) {
                if (datas.size() == 0) {
                    mCurrentPage--;
                    callback.onLoaderMoreEmpty();
                } else {
                    callback.onLoaderMoreLoaded(datas);
                }
                pageOfWxIdInfos.put(wxId, mCurrentPage);
            }
        }
    }
}
