package com.songjachin.mwanandroid.database;

/**
 * Created by matthew
 */
public interface IHistoryDao {
    /**
     * 设置回调接口
     *
     * @param callback
     */
    void setCallback(IHistoryDaoCallback callback);

    /**
     * 添加历史.
     *
     */
    void addHistory(HistoryArticle article);

    /**
     * 删除历史
     *
     */
    void delHistory(HistoryArticle article);


    /**
     * 清空历史内容。
     */
    void clearHistory();


    /**
     * 获取历史内容.
     */
    void listHistories();
}
