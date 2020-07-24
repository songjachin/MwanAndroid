package com.songjachin.mwanandroid.presenter.wx;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.wx.IWxCallback;

/**
 * Created by matthew
 */
public interface IWxPresenter extends IBasePresenter<IWxCallback> {
    void getCategories();
}
