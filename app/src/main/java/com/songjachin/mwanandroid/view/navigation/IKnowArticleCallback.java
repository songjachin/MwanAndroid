package com.songjachin.mwanandroid.view.navigation;

import com.songjachin.mwanandroid.model.domain.KnowArticleBean;

import java.util.List;

/**
 * Created by matthew
 */
public interface IKnowArticleCallback {
    void onContentLoaded(KnowArticleBean body);

    void onLoadMore(List<KnowArticleBean.DataBean.DatasBean> datas);

    void onLoadMoreError();

    void onLoadMoreEmpty();

}
