package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.ProjectCategory;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.utils.UrlUtils;
import com.songjachin.mwanandroid.view.navigation.IProjectCallback;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class ProjectPresenter implements IProjectPresenter {

    private IProjectCallback mIProjectCallback;
    private Api mApi;
    private int mCurrentPage = 1;

    private ProjectPresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static volatile ProjectPresenter instance;

    public static ProjectPresenter getInstance(){
        if (instance == null) {
            synchronized (ProjectPresenter.class){
                if (instance == null) {
                    instance = new ProjectPresenter();
                }
            }
        }

        return instance;
    }

    @Override
    public void registerViewCallback(IProjectCallback callback) {
        mIProjectCallback = callback;
    }
    @Override
    public void unregisterViewCallback(IProjectCallback callback) {
        mIProjectCallback = null;
    }

    @Override
    public void getCategories() {
        if (mIProjectCallback != null) {
            mIProjectCallback.onLoading();
        }

        Call<ProjectCategory> projectCategory = mApi.getProjectCategory();
        projectCategory.enqueue(new Callback<ProjectCategory>() {
            @Override
            public void onResponse(Call<ProjectCategory> call, Response<ProjectCategory> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    ProjectCategory body = response.body();
                    if (mIProjectCallback != null) {
                        mIProjectCallback.onCategoryLoaded(body);
                    }
                }else{
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<ProjectCategory> call, Throwable t) {
                onLoadedError();
            }
        });
    }

    @Override
    public void getContentByCategory(int categoryId) {
        mCurrentPage = 1;
        String targetUrl = UrlUtils.getProjectContentUrl(mCurrentPage,categoryId);
        Call<ArticleHomeBean> projectContent = mApi.getProjectContent(targetUrl);
        projectContent.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int resultCode = response.code();
                if(resultCode == HttpURLConnection.HTTP_OK) {
                    ArticleHomeBean body = response.body();
                    handleResult(body);

                } else {
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                onLoadedError();
            }
        });
    }

    private void handleResult(ArticleHomeBean body) {
        ArticleHomeBean.DataBean data = body.getData();
        List<ArticleHomeBean.DataBean.DatasBean> datas = data.getDatas();
        if (datas.size()==0) {
            mIProjectCallback.onEmpty();
        }
        if (mIProjectCallback != null) {
            mIProjectCallback.onContentLoaded(datas);
        }
    }


    private void onLoadedError() {
        if (mIProjectCallback != null) {
            mIProjectCallback.onError();
        }
    }

    @Override
    public void loadMore(int categoryId) {
        mCurrentPage++;
        String targetUrl = UrlUtils.getProjectContentUrl(mCurrentPage,categoryId);
        Call<ArticleHomeBean> projectContent = mApi.getProjectContent(targetUrl);
        projectContent.enqueue(new Callback<ArticleHomeBean>() {
            @Override
            public void onResponse(Call<ArticleHomeBean> call, Response<ArticleHomeBean> response) {
                int resultCode = response.code();
                if(resultCode == HttpURLConnection.HTTP_OK) {
                    ArticleHomeBean body = response.body();
                    handleLoadMoreResult(body);
                } else {
                    mCurrentPage--;
                    mIProjectCallback.onLoadMoreError();
                }
            }

            @Override
            public void onFailure(Call<ArticleHomeBean> call, Throwable t) {
                mCurrentPage--;
                mIProjectCallback.onLoadMoreError();
            }
        });
    }

    private void handleLoadMoreResult(ArticleHomeBean body) {
        ArticleHomeBean.DataBean data = body.getData();
        List<ArticleHomeBean.DataBean.DatasBean> datas = data.getDatas();
        if (datas.size()==0) {
            mIProjectCallback.onLoadMoreEmpty();
        }
        if (mIProjectCallback != null) {
            mIProjectCallback.onLoadMreLoaded(datas);
        }
    }
}
