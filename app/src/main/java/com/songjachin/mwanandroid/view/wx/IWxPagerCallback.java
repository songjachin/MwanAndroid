package com.songjachin.mwanandroid.view.wx;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;

import java.util.List;

/**
 * Created by matthew
 */
public interface IWxPagerCallback extends IBaseCallback {
    void onArticleLoad(List<? extends IBaseArticleInfo> data);

    int getWxId();


    /**
     * 加更多时网络错误
     */
    void onLoaderMoreError();

    /**
     * 没有更多内容
     */
    void onLoaderMoreEmpty();

    /**
     * 加到了更多内容
     *
     */
    void onLoaderMoreLoaded(List<? extends IBaseArticleInfo> data);
}
