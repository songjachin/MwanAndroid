package com.songjachin.mwanandroid.view.talk;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;

import java.util.List;

/**
 * Created by matthew
 */
public interface ITalkCallback extends IBaseCallback {
    void onArticleLoad(List<? extends IBaseArticleInfo> data);

    /**
     * 加更多时网络错误
     */
    void onLoadMoreError();

    /**
     * 没有更多内容
     */
    void onLoadMoreEmpty();

    /**
     * 加到了更多内容
     *
     */
    void onLoadMoreLoaded( List<? extends IBaseArticleInfo> data);
}
