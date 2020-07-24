package com.songjachin.mwanandroid.presenter.home;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.home.ISearchCallback;

/**
 * Created by matthew
 */
public interface ISearchPresenter extends IBasePresenter<ISearchCallback> {
    void getHotKey();


    void getHistories();
    /**
     * 删除搜索历史
     */
    void delHistories();

    /**
     * 搜索
     *
     * @param keyword
     */
    void doSearch(String keyword);

    /**
     * 重新搜索
     */
    void research();

    /**
     * 获取更多的搜索结果
     */
    void loaderMore();
}
