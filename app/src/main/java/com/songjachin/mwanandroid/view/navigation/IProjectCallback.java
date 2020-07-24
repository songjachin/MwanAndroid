package com.songjachin.mwanandroid.view.navigation;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.model.domain.ProjectCategory;

import java.util.List;

/**
 * Created by matthew
 */
public interface IProjectCallback extends IBaseCallback {
    void onCategoryLoaded(ProjectCategory category);

    void onContentLoaded(List<? extends IBaseArticleInfo> data);

    void onLoadMreLoaded(List<? extends IBaseArticleInfo> data);

    void onLoadMoreEmpty();

    void onLoadMoreError();
}
