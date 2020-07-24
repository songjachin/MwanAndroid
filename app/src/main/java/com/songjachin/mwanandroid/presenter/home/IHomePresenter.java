package com.songjachin.mwanandroid.presenter.home;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.home.IHomeCallback;

/**
 * Created by matthew
 */
public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    void getBanner();

    void getArticle();

    void loadMore();

    void collect(int id);

    void unCollect(int id);
}
