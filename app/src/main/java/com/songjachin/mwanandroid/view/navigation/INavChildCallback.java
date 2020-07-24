package com.songjachin.mwanandroid.view.navigation;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.NaviBean;

import java.util.List;

/**
 * Created by matthew
 */
public interface INavChildCallback extends IBaseCallback {
    void onContentLoaded(List<NaviBean.DataBean> data);
}
