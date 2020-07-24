package com.songjachin.mwanandroid.view.home;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.Articles;
import com.songjachin.mwanandroid.model.domain.Histories;
import com.songjachin.mwanandroid.model.domain.HotKey;

import java.util.List;

/**
 * Created by matthew
 */
public interface ISearchCallback extends IBaseCallback {
    /**
     * 搜索历史结果
     *
     * @param
     */
    void onHistoriesLoaded(Histories histories);

    /**
     * 历史记录删除完成
     */
    void onHistoriesDeleted();

    /**
     * 搜索结果：成功
     *
     * @param
     */
    void onSearchSuccess(Articles data);

    /**
     * 加载到了更多内容
     *
     * @param
     */
    void onMoreLoaded(Articles data);

    /**
     * 加载更多时网络出错
     */
    void onMoreLoadedError();

    /**
     * 没有更多内容
     */
    void onMoreLoadedEmpty();

    /**
     * 推荐词获取结果
     *
     * @param
     */
    void onRecommendWordsLoaded(List<HotKey> data );

}
