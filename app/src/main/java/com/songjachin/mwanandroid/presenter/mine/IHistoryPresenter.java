package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.view.mine.IHistoryCallback;

/**
 * Created by matthew
 */
public interface IHistoryPresenter extends IBasePresenter<IHistoryCallback> {
    /**
     * 获取历史内容.
     */
    void listHistories();

    /**
     * 添加历史
     *
     */
    void addHistory(HistoryArticle article);

    /**
     * 删除历史
     *
     * @param article
     */
    void delHistory(HistoryArticle article);

    /**
     * 清除历史
     */
    void cleanHistories();
}
