package com.songjachin.mwanandroid.view.mine;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.database.HistoryArticle;

import java.util.List;

/**
 * Created by matthew
 */
public interface IHistoryCallback extends IBaseCallback {
    /**
     * 历史内容加载结果.
     *
     * @param articles
     */
    void onHistoriesLoaded(List<HistoryArticle> articles);
}
