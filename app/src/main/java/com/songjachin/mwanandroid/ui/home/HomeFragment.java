package com.songjachin.mwanandroid.ui.home;

import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.customview.MyBanner;
import com.songjachin.mwanandroid.customview.SelfNestedScrollView;
import com.songjachin.mwanandroid.model.domain.ArticleBean;
import com.songjachin.mwanandroid.model.domain.ArticleHomeBean;
import com.songjachin.mwanandroid.model.domain.BannerBean;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.presenter.home.HomePresenter;
import com.songjachin.mwanandroid.ui.adapters.BannerAdapter;
import com.songjachin.mwanandroid.ui.adapters.HomeArticleAdapter;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.home.IHomeCallback;

import java.util.List;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class HomeFragment extends BaseFragment implements IHomeCallback, HomeArticleAdapter.OnItemClickListener {

    private HomePresenter mHomePresenter;

    @BindView(R.id.home_layout)
    LinearLayout mHomeLayout;
    @BindView(R.id.home_nested_scroller)
    SelfNestedScrollView  mNestedScrollView;
    @BindView(R.id.home_search_input_box)
    EditText mSearchInputBox;
    @BindView(R.id.home_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.my_banner)
    MyBanner mMyBanner;
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecyclerView;
    private BannerAdapter mBannerAdapter;
    private HomeArticleAdapter mAdapter;


    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_home,container,false);
    }

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        //looper
        mBannerAdapter = new BannerAdapter();
        mMyBanner.setAdapter(mBannerAdapter);

        //recyclerview
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(), 1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(), 1.5f);
            }
        });

        mAdapter = new HomeArticleAdapter();
        mHomeRecyclerView.setAdapter(mAdapter);
        //refresh
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(true);

        int measuredHeight = mHomeLayout.getMeasuredHeight();
        ViewGroup.LayoutParams layoutParams = mHomeRecyclerView.getLayoutParams();
        layoutParams.height = measuredHeight;
        mHomeRecyclerView.setLayoutParams(layoutParams);
    }

    private boolean flag = true;
    @Override
    protected void initListener() {
        mHomeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(mMyBanner == null){
                    return;
                }
                int measuredHeight1 = mMyBanner.getMeasuredHeight();
                mNestedScrollView.setHeaderHeight(measuredHeight1);
                LogUtils.d(HomeFragment.class,"---------height-->"+measuredHeight1);
                int measuredHeight = mHomeLayout.getMeasuredHeight();
                ViewGroup.LayoutParams layoutParams = mHomeRecyclerView.getLayoutParams();
                layoutParams.height = measuredHeight;
                mHomeRecyclerView.setLayoutParams(layoutParams);
                if(measuredHeight!=0){
                    mHomeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mHomePresenter != null) {
                    mHomePresenter.loadMore();
                }
            }
        });
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mHomePresenter = HomePresenter.getInstance();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mHomePresenter != null) {
            mHomePresenter.getBanner();
            mHomePresenter.getArticle();
        }

    }

    @Override
    protected void onRetryClick() {
        if (mHomePresenter != null) {
            mHomePresenter.getBanner();
            mHomePresenter.getArticle();
        }
    }

    @Override
    protected void release() {
        if (mHomePresenter != null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }
//=================================== view callback===================


    @Override
    public void onBannerLoaded(List<BannerBean.DataBean> data) {
        mBannerAdapter.setData(data);
        mMyBanner.initIndicator();
        setUpState(State.SUCCESS);
    }

    @Override
    public void onArticleLoad(List<? extends IBaseArticleInfo> data) {
        mAdapter.setData(data);
        setUpState(State.SUCCESS);
    }

    @Override
    public void onLoadMoreError() {
        ToastUtil.showToast("加载错误^_^");
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtil.showToast("没有更多了");
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onLoadMoreLoaded(List<? extends IBaseArticleInfo> data) {
        mAdapter.addData(data);
        mRefreshLayout.finishLoadMore();
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
    public void onItemTitleClick() {

    }
}
