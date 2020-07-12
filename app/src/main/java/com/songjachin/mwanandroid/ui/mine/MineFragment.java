package com.songjachin.mwanandroid.ui.mine;

import android.widget.TextView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class MineFragment extends BaseFragment {

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void release() {

    }
}
