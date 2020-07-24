package com.songjachin.mwanandroid.view.mine;

import com.songjachin.mwanandroid.base.IBaseCallback;
import com.songjachin.mwanandroid.model.domain.Ranking;

/**
 * Created by matthew
 */
public interface IMineCallback extends IBaseCallback {
    void onRankingInfo(Ranking data);
}
