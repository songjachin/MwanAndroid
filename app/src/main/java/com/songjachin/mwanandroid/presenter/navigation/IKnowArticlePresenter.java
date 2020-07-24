package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.navigation.IKnowArticleCallback;

/**
 * Created by matthew
 */
public interface IKnowArticlePresenter extends IBasePresenter<IKnowArticleCallback> {
    void getKnowArticle(int page ,int id);

    void loadMore(int id);

}
