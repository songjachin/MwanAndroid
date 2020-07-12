package com.songjachin.mwanandroid.base;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.songjachin.mwanandroid.R;


import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public abstract class BaseFragment extends BaseLazyFragment {
    private State currentState = State.NONE;
    private View mLoadingView;
    private View mSuccessView;
    private View mErrorView;
    private View mEmptyView;

    public enum State {
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }

    @OnClick(R.id.network_error_tips)
    public void retry(){
        onRetryClick();
    }
    private Unbinder mBind;

    private FrameLayout mBaseContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container);
        //根据具体的rootView查找相应的container
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        //loadStateView要在ButterKnife 的前面，先有布局文件，再然后bind

        mBind = ButterKnife.bind(this, rootView);
        initView();
        initListener();
        initPresenter();
        //loadData();
        return rootView;
    }

    @Override
    protected void onLazyLoadData() {
        loadData();
    }

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    //=======================================================================================
    private void loadStatesView(LayoutInflater inflater, ViewGroup container) {
        //成功的view
        mSuccessView = loadSuccessView(inflater,container);
        mBaseContainer.addView(mSuccessView);
        //Loading的View
        mLoadingView = loadLoadingView(inflater,container);
        mBaseContainer.addView(mLoadingView);
        //错误页面
        mErrorView = loadErrorView(inflater,container);
        mBaseContainer.addView(mErrorView);
        //内容为空的页面
        mEmptyView = loadEmptyView(inflater,container);
        mBaseContainer.addView(mEmptyView);
        setUpState(State.NONE);
    }

    protected View loadSuccessView(LayoutInflater inflater,ViewGroup container) {
        int resId = getSuccessViewResId();
        return inflater.inflate(resId,container,false);
    }

    /**
     * 加Loading界面
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View loadLoadingView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading,container,false);
    }

    protected View loadErrorView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error,container,false);
    }


    protected View loadEmptyView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }
    /**
     * 子类通过这个方法来切换状态页面即可
     *
     * @param state
     */
    public void setUpState(State state) {
        this.currentState = state;
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);
    }

    /**
     * 如果子类需要去设置相关的事件，覆盖此方法
     */
    protected abstract int getSuccessViewResId();
    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initPresenter();
    protected abstract void loadData();
    /**
     * 如果子fragment需要知道网络错误以后的点击，那覆盖些方法即可
     */
    protected abstract void onRetryClick();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected abstract void release();
}
