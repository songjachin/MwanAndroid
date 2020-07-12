package com.songjachin.mwanandroid.ui.talk;



import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;


/**
 * Created by matthew
 */
public class TalkFragment extends BaseFragment {

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_talk;
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
