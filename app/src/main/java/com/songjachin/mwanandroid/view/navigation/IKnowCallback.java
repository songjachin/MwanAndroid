package com.songjachin.mwanandroid.view.navigation;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.KnowledgeBean;

import java.util.List;

/**
 * Created by matthew
 */
public interface IKnowCallback extends IBaseCallback {
    void onContentLoaded(List<KnowledgeBean.DataBean> data);
}
