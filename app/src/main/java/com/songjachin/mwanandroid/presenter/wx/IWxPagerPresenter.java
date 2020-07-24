package com.songjachin.mwanandroid.presenter.wx;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.wx.IWxPagerCallback;

/**
 * Created by matthew
 */
public interface IWxPagerPresenter extends IBasePresenter<IWxPagerCallback> {
    void getWxArticle(int wxId);

    void loadMore(int wxId);
}
