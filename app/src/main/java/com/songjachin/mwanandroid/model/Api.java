package com.songjachin.mwanandroid.model;

import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.Articles;
import com.songjachin.mwanandroid.model.domain.BannerBean;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Categories;
import com.songjachin.mwanandroid.model.domain.CollectBean;
import com.songjachin.mwanandroid.model.domain.HotKey;
import com.songjachin.mwanandroid.model.domain.KnowArticleBean;
import com.songjachin.mwanandroid.model.domain.KnowledgeBean;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.model.domain.NaviBean;
import com.songjachin.mwanandroid.model.domain.ProjectCategory;
import com.songjachin.mwanandroid.model.domain.Ranking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

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

    @GET("wenda/list/{page}/json ")
    Call<ArticleHomeBean> getQuestion(@Path("page") int page);

    @GET("wxarticle/chapters/json")
    Call<Categories> getCategories();

    @GET("wxarticle/list/{wxId}/{page}/json")
    Call<ArticleHomeBean> getWxArticle(@Path("wxId") int wxId, @Path("page") int page);

    @GET("project/tree/json")
    Call<ProjectCategory> getProjectCategory();

    @GET
    Call<ArticleHomeBean> getProjectContent(@Url String url);

    @GET("tree/json")
    Call<KnowledgeBean> getKnowledge();

    @GET
    Call<KnowArticleBean> getKnowArticle(@Url String url);

    @GET("navi/json")
    Call<NaviBean>  getNavigation();

    @POST("user/login")
    @FormUrlEncoded
    Call<BaseResponse<Login>> login(
            @Field("username") String userName,
            @Field("password") String password
    );

    //https://www.wanandroid.com/lg/coin/userinfo/json
    @GET("lg/coin/userinfo/json")
    Call<BaseResponse<Ranking>> getRanking();
    //https://www.wanandroid.com/lg/collect/1165/json
    @POST("lg/collect/{id}/json")
    Call<BaseResponse>  collect(@Path("id") int articleId);

    //https://www.wanandroid.com/lg/uncollect_originId/2333/json
//    @POST("lg/uncollect_originId/{id}/json")
//    Call<BaseResponse> unCollect(@Path("id") int articleId);

    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Call<BaseResponse> unCollect(@Path("id") int id,//收藏在我的收藏列表的id
                                          @Field("originId") int originId);//收藏在原始文章列表的id);

    //https://www.wanandroid.com/lg/collect/list/0/json
    @GET("lg/collect/list/{page}/json")
    Call<CollectBean> getCollect(@Path("page") int page);

    /**
     * 获取搜索热词
     * http://www.wanandroid.com//hotkey/json
     */
    @GET("hotkey/json")
    Call<BaseResponse<List<HotKey>>> getHotKey();
    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     */
    @POST("article/query/{pageNum}/json")
    @FormUrlEncoded
    Call<BaseResponse<Articles>> getSearchArticles(@Field("k") String key,//关键字
                                                   @Path("pageNum") int pageNum//页数
    );
}

