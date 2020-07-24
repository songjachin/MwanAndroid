package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.navigation.INavChildCallback;

/**
 * Created by matthew
 */
public interface INavChildPresenter extends IBasePresenter<INavChildCallback> {
    void getNavigationChild();
}
