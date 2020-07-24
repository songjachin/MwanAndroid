package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.mine.ICollectActivityCallback;

/**
 * Created by matthew
 */
public interface ICollectPresenter extends IBasePresenter<ICollectActivityCallback> {
    //https://www.wanandroid.com/lg/collect/list/0/json

    void getCollect(int pageId);
}
