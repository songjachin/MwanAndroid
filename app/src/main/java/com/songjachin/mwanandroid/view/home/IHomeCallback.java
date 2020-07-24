package com.songjachin.mwanandroid.view.home;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.BannerBean;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;

import java.util.List;

/**
 * Created by matthew
 */
public interface IHomeCallback extends IBaseCallback {

    void onBannerLoaded(List<BannerBean.DataBean> data);

    void onArticleLoad(List<? extends IBaseArticleInfo> data);

    /**
     * 加更多时网络错误
     */
    void onLoadMoreError();

    /**
     * 没有更多内容
     */
    void onLoadMoreEmpty();

    /**
     * 加到了更多内容
     *
     */
    void onLoadMoreLoaded( List<? extends IBaseArticleInfo> data);

    void onCollectSuccessful();

    void onCollectFail();

    void onUnCollectSuccessful();

    void onUnCollectFail();
}
