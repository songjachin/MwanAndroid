package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.mine.ILoginCallback;

/**
 * Created by matthew
 */
public interface ILoginPresenter extends IBasePresenter<ILoginCallback> {
    void login(String count,String password);

    void register();
}
