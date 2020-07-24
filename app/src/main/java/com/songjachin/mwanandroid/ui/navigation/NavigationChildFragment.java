package com.songjachin.mwanandroid.ui.navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.model.domain.NaviBean;
import com.songjachin.mwanandroid.presenter.navigation.NavChildPresenter;
import com.songjachin.mwanandroid.ui.adapters.KnowledgeAdapter;
import com.songjachin.mwanandroid.ui.adapters.NavigationChildAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.view.navigation.INavChildCallback;

import java.util.List;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class NavigationChildFragment extends BaseFragment implements NavigationChildAdapter.OnItemClickListener, INavChildCallback {
    @BindView(R.id.nav_child_rv)
    RecyclerView mRecyclerView;
    private NavigationChildAdapter mAdapter;
    private NavChildPresenter mNavChildPresenter;


    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_navigation_child;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NavigationChildAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mNavChildPresenter = NavChildPresenter.getInstance();
        mNavChildPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mNavChildPresenter != null) {
            mNavChildPresenter.getNavigationChild();
        }
    }

    @Override
    protected void onRetryClick() {
        loadData();
    }

    @Override
    protected void release() {
        mNavChildPresenter.unregisterViewCallback(this);
    }

    @Override
    public void onItemClick(NaviBean.DataBean.ArticlesBean article) {
        ArticleActivity.startActivityByFragment(
                mActivity,this,
                article.getLink(),
                article.getTitle()
        );
    }

    @Override
    public void onContentLoaded(List<NaviBean.DataBean> data) {
        mAdapter.setData(data);
        setUpState(State.SUCCESS);
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }
}
