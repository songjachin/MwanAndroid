package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.navigation.IProjectCallback;

/**
 * Created by matthew
 */
interface IProjectPresenter extends IBasePresenter<IProjectCallback> {
    /**
     * 获取分类
     */
    void getCategories();

    /**
     * 根据分类获取内容
     *
     * @param categoryId
     */
    void getContentByCategory(int categoryId);
    /**
     * note:加载更多
     */
    void loadMore(int categoryId);
}
