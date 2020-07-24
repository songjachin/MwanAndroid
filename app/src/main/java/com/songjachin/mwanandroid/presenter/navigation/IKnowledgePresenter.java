package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.navigation.IKnowCallback;

/**
 * Created by matthew
 */
public interface IKnowledgePresenter extends IBasePresenter<IKnowCallback> {
    void getKnowledgeContent();
}
