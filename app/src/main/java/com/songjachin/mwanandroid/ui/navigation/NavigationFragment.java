package com.songjachin.mwanandroid.ui.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.ui.adapters.NavigationPagerAdapter;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class NavigationFragment extends BaseFragment {

    @BindView(R.id.logo)
    TextView mTextView;
    @BindView(R.id.nav_indicator)
    TabLayout mTabLayout;

    @BindView(R.id.nav_pager)
    ViewPager mViewPager;

    private NavigationPagerAdapter mAdapter;

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wx_base,container,false);
    }
    @Override
    protected void initView() {
        mAdapter = new NavigationPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTextView.setText(R.string.text_navigation);
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
