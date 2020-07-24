package com.songjachin.mwanandroid.presenter.talk;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.talk.ITalkCallback;

/**
 * Created by matthew
 */
public interface ITalkPresenter extends IBasePresenter<ITalkCallback> {
    void getTalkArticle();

    void loadMore();
}
