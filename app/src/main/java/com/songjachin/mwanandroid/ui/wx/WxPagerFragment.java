package com.songjachin.mwanandroid.ui.wx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.model.Constant;
import com.songjachin.mwanandroid.model.domain.Categories;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.presenter.wx.WxPagerPresenter;
import com.songjachin.mwanandroid.ui.adapters.HomeArticleAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.wx.IWxPagerCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class WxPagerFragment extends BaseFragment implements HomeArticleAdapter.OnItemClickListener, IWxPagerCallback {

    @BindView(R.id.wx_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.wx_rv)
    RecyclerView mRecyclerView;
    private HomeArticleAdapter mAdapter;
    private WxPagerPresenter mWxPagerPresenter;
    private int mWxId;

    public static WxPagerFragment newInstance(Categories.DataBean category) {
        WxPagerFragment wxPagerFragment = new WxPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_WX_PAGER_TITLE, category.getName());
        bundle.putInt(Constant.KEY_WX_PAGER_ID, category.getId());
        wxPagerFragment.setArguments(bundle);
        return wxPagerFragment;
    }

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_wx_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(), 1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(), 1.5f);
            }
        });

        mAdapter = new HomeArticleAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setEnableLoadMore(true);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(this);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mWxPagerPresenter != null) {
                    mWxPagerPresenter.loadMore(mWxId);
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        mWxPagerPresenter = WxPagerPresenter.getInstance();
        mWxPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constant.KEY_WX_PAGER_TITLE);
        mWxId = arguments.getInt(Constant.KEY_WX_PAGER_ID);
        if (mWxPagerPresenter != null) {
            mWxPagerPresenter.getWxArticle(mWxId);
        }

    }

    @Override
    protected void onRetryClick() {
        loadData();
    }

    @Override
    protected void release() {
        if (mWxPagerPresenter != null) {
            mWxPagerPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onItemTitleClick(IBaseArticleInfo data) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        String author = data.getAuthor();
        if(TextUtils.isEmpty(author)){
            author = data.getShareUser()+" 分享";
        }

        HistoryArticle article = new HistoryArticle(data.getId(),data.getAuthor(),data.getTitle(),data.getLink(),time);
        HistoryPresenter historyPresenter = HistoryPresenter.getInstance();
        historyPresenter.addHistory(article);
        ArticleActivity.startActivityByFragment(
                mActivity, this,
                data.getLink(),
                data.getTitle()
        );
    }

    @Override
    public void onCollectClick(IBaseArticleInfo data, int position) {
        if(User.getInstance().isLoginStatus()){
            if(data.isCollect()){
                CollectionUtils.getInstance().unCollect( data.getId(),-1, new IUnCollectCallback() {
                    @Override
                    public void onUnCollectSuccessful() {
                        ToastUtil.showToast("取消收藏成功");
                        mAdapter.setCollectStatus(position,false);
                    }

                    @Override
                    public void onUnCollectFail() {
                        ToastUtil.showToast("取消收藏失败");
                    }
                });
            }else{
                CollectionUtils.getInstance().collect( data.getId(), new ICollectCallback() {
                    @Override
                    public void onCollectSuccessful() {
                        ToastUtil.showToast("收藏成功");
                        mAdapter.setCollectStatus(position,true);
                    }

                    @Override
                    public void onCollectFail() {
                        ToastUtil.showToast("收藏失败");
                    }
                });
            }
        }else{
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivityForResult(intent, 1);
        }
    }



    @Override
    public void onArticleLoad(List<? extends IBaseArticleInfo> data) {
        if (mAdapter != null) {
            mAdapter.setData(data);
            setUpState(State.SUCCESS);
        }
    }

    @Override
    public int getWxId() {
        return mWxId;
    }

    @Override
    public void onLoaderMoreError() {
        ToastUtil.showToast("网络异常，请稍后重试");
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onLoaderMoreEmpty() {
        ToastUtil.showToast("没有更多内容");
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onLoaderMoreLoaded(List<? extends IBaseArticleInfo> data) {
        mAdapter.addData(data);
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
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
