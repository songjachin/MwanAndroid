package com.songjachin.mwanandroid.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;

import android.text.TextUtils;

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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.customview.MyBanner;
import com.songjachin.mwanandroid.customview.SelfNestedScrollView;
import com.songjachin.mwanandroid.database.HistoryArticle;

import com.songjachin.mwanandroid.model.domain.BannerBean;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.home.HomePresenter;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;

import com.songjachin.mwanandroid.ui.SearchActivity;
import com.songjachin.mwanandroid.ui.adapters.BannerAdapter;
import com.songjachin.mwanandroid.ui.adapters.HomeArticleAdapter;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.home.IHomeCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class HomeFragment extends BaseFragment implements IHomeCallback, HomeArticleAdapter.OnItemClickListener, BannerAdapter.OnViewPagerClickListener {

    private HomePresenter mHomePresenter;

    @BindView(R.id.home_layout)
    LinearLayout mHomeLayout;
    @BindView(R.id.home_nested_scroller)
    SelfNestedScrollView  mNestedScrollView;
    @SuppressLint("NonConstantResourceId")
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
    private int mCollectPosition;


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
                outRect.top = SizeUtils.dip2px(Objects.requireNonNull(getContext()), 1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(), 1.5f);
            }
        });

        mAdapter = new HomeArticleAdapter();
        mHomeRecyclerView.setAdapter(mAdapter);
        //refresh
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);

    }

    @Override
    protected void initListener() {
        mHomeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(mMyBanner == null){
                    return;
                }
                //联动滑动问题
                int measuredHeight1 = mMyBanner.getMeasuredHeight();
                mNestedScrollView.setHeaderHeight(measuredHeight1);
                //防止RecyclerView不断地create和bind
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
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                BaseApplication.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomePresenter.getArticle();
                        mRefreshLayout.finishRefresh();
                    }
                }, 1000);
            }
        });
        mAdapter.setOnItemClickListener(this);
        mBannerAdapter.setOnViewPagerClick(this);

        mSearchInputBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mHomePresenter = HomePresenter.getInstance();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        LogUtils.d(this,"load---------------------new data!");
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
    public void onCollectSuccessful() {
        ToastUtil.showToast("收藏成功");
        //loadData();
        mAdapter.setCollectStatus(mCollectPosition,true);
    }

    @Override
    public void onCollectFail() {
        ToastUtil.showToast("收藏失败");
    }

    @Override
    public void onUnCollectSuccessful() {
        ToastUtil.showToast("取消收藏成功");
        //loadData();
        mAdapter.setCollectStatus(mCollectPosition,false);
    }

    @Override
    public void onUnCollectFail() {
        ToastUtil.showToast("取消收藏失败");
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
        //long timeMillis = System.currentTimeMillis();
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
    public void onCollectClick(IBaseArticleInfo data,int position) {
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
    public void onPagerItemClick(BannerBean.DataBean bean ) {
        ArticleActivity.startActivityByFragment(
                mActivity,this,
               bean.getUrl(),
                bean.getTitle()
        );
    }
}
