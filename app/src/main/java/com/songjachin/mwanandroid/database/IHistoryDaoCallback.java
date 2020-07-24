package com.songjachin.mwanandroid.database;

import java.util.List;

/**
 * Created by matthew
 */
public interface IHistoryDaoCallback {
    /**
     * 添加历史的结果
     *
     * @param isSuccess
     */
    void onHistoryAdd(boolean isSuccess);


    /**
     * 删除历史的结果
     *
     * @param isSuccess
     */
    void onHistoryDel(boolean isSuccess);


    /**
     * 历史数据加载的结果
     *
     */
    void onHistoriesLoaded(List<HistoryArticle> articles);


    /**
     * 历史内容清空结果
     */
    void onHistoriesClean(boolean isSuccess);
}
