package com.songjachin.mwanandroid.view.mine;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;

import java.util.List;

/**
 * Created by matthew
 */
public interface ICollectActivityCallback extends IBaseCallback {
    void onContentLoaded(List<? extends IBaseArticleInfo> data);
}
