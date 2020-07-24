package com.songjachin.mwanandroid.ui.navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.model.domain.KnowledgeBean;
import com.songjachin.mwanandroid.presenter.navigation.KnowledgePresenter;
import com.songjachin.mwanandroid.ui.adapters.KnowledgeAdapter;
import com.songjachin.mwanandroid.view.navigation.IKnowCallback;

import java.util.List;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class KnowledgeFragment extends BaseFragment implements IKnowCallback, KnowledgeAdapter.OnItemClickListener {

    @BindView(R.id.know_rv)
    RecyclerView mRecyclerView;
    private KnowledgeAdapter mAdapter;
    private KnowledgePresenter mKnowledgePresenter;

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mKnowledgePresenter = KnowledgePresenter.getInstance();
        mKnowledgePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mKnowledgePresenter != null) {
            mKnowledgePresenter.getKnowledgeContent();
        }
    }

    @Override
    protected void onRetryClick() {
        loadData();
    }

    @Override
    protected void release() {
        mKnowledgePresenter.unregisterViewCallback(this);
    }

    @Override
    public void onContentLoaded(List<KnowledgeBean.DataBean> data) {
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

    @Override
    public void onItemClick( int target) {
        KnowledgeArticleActivity.start(getContext(),target);
    }
}
