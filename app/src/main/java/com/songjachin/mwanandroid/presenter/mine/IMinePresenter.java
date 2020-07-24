package com.songjachin.mwanandroid.presenter.mine;


import com.songjachin.mwanandroid.base.IBasePresenter;
import com.songjachin.mwanandroid.view.mine.IMineCallback;

/**
 * Created by matthew
 */
public interface IMinePresenter extends IBasePresenter<IMineCallback> {

    void getRanking();


}
