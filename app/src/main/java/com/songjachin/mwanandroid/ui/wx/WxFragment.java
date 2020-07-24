package com.songjachin.mwanandroid.ui.wx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.model.domain.Categories;
import com.songjachin.mwanandroid.presenter.wx.WxPresenter;
import com.songjachin.mwanandroid.ui.adapters.WxPagerAdapter;
import com.songjachin.mwanandroid.view.wx.IWxCallback;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class WxFragment extends BaseFragment implements IWxCallback {

    @BindView(R.id.wx_pager)
    ViewPager wxPager;

    @BindView(R.id.wx_indicator)
    TabLayout mTabLayout;

    private WxPagerAdapter mWxPagerAdapter;
    private WxPresenter mWxPresenter;

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_wx;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wx_base,container,false);
    }

    @Override
    protected void initView() {
        mWxPagerAdapter = new WxPagerAdapter(getChildFragmentManager());
        wxPager.setAdapter(mWxPagerAdapter);
        mTabLayout.setupWithViewPager(wxPager);
        wxPager.setOffscreenPageLimit(5);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        mWxPresenter = WxPresenter.getInstance();
        mWxPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mWxPresenter.getCategories();
    }

    @Override
    protected void onRetryClick() {
        if (mWxPresenter != null) {
            mWxPresenter.getCategories();
        }
    }

    @Override
    protected void release() {
        if (mWxPresenter != null) {
            mWxPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        setUpState(State.SUCCESS);
        if (mWxPagerAdapter != null) {
            mWxPagerAdapter.setData(categories);
        }
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
