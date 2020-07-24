package com.songjachin.mwanandroid.view.mine;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.Login;

/**
 * Created by matthew
 */
public interface ILoginCallback extends IBaseCallback {
    void onLoginSuccess(Login data);

    void onLoginFail(String msg);
}
