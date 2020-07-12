package com.songjachin.mwanandroid.model;

import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.BannerBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by matthew
 */
public interface Api {
    @GET("banner/json")
    Call<BannerBean> getBanner();

    @GET("article/top/json")
    Call<ArticleBean> getArticle();

    @GET("article/list/{page}/json")
    Call<ArticleHomeBean> getHomeArticle(@Path("page") int page);
}
