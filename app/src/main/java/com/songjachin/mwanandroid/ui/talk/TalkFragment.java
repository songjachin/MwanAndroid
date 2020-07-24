package com.songjachin.mwanandroid.ui.talk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.presenter.talk.TalkPresenter;
import com.songjachin.mwanandroid.ui.adapters.HomeArticleAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.talk.ITalkCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class TalkFragment extends BaseFragment implements ITalkCallback, HomeArticleAdapter.OnItemClickListener {

    @BindView(R.id.talk_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.talk_rv)
    RecyclerView mRecyclerView;
    private HomeArticleAdapter mAdapter;
    private TalkPresenter mTalkPresenter;


    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_with_bar,container,false);
    }

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_talk;
    }

    @Override
    protected void initView() {
        setUpState(State.LOADING);
        //recycler

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

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mTalkPresenter != null) {
                    mTalkPresenter.loadMore();
                }
            }
        });
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mTalkPresenter = TalkPresenter.getInstance();
        mTalkPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mTalkPresenter != null) {
            mTalkPresenter.getTalkArticle();
        }
    }

    @Override
    protected void onRetryClick() {
        loadData();
    }

    @Override
    protected void release() {
        if (mTalkPresenter != null) {
            mTalkPresenter.unregisterViewCallback(this);
        }
    }

    //================================================
    @Override
    public void onArticleLoad(List<? extends IBaseArticleInfo> data) {
        if (mAdapter != null) {
            mAdapter.setData(data);
            setUpState(State.SUCCESS);
        }
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
                mActivity,this,
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


}
