package com.songjachin.mwanandroid.view.wx;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.Categories;

/**
 * Created by matthew
 */
public interface IWxCallback extends IBaseCallback {
    void onCategoriesLoaded(Categories categories);
}
