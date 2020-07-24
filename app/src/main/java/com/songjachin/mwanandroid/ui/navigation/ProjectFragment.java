package com.songjachin.mwanandroid.ui.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.model.domain.ProjectCategory;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.presenter.navigation.ProjectPresenter;
import com.songjachin.mwanandroid.ui.adapters.HomeArticleAdapter;
import com.songjachin.mwanandroid.ui.adapters.ProjectLeftAdapter;
import com.songjachin.mwanandroid.ui.adapters.ProjectRightAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.navigation.IProjectCallback;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class ProjectFragment extends BaseFragment implements IProjectCallback, ProjectRightAdapter.OnItemClickListener {

    @BindView(R.id.left_list)
    public RecyclerView leftCategoryList;

    @BindView(R.id.right_list)
    public RecyclerView rightContentList;

//    @BindView(R.id.project_refresh)
//    SmartRefreshLayout mRefreshLayout;

    private ProjectLeftAdapter mLeftAdapter;
    private ProjectRightAdapter mRightAdapter;
    private ProjectPresenter mProjectPresenter;
    private int mId;


    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {

        leftCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mLeftAdapter = new ProjectLeftAdapter();
        leftCategoryList.setAdapter(mLeftAdapter);

        rightContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRightAdapter = new ProjectRightAdapter();
        rightContentList.setAdapter(mRightAdapter);
        rightContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int topAndBottom = SizeUtils.dip2px(getContext(),4);
                int leftAndRight = SizeUtils.dip2px(getContext(),6);
                outRect.left = leftAndRight;
                outRect.right = leftAndRight;
                outRect.top = topAndBottom;
                outRect.bottom = topAndBottom;
            }
        });
//        mRefreshLayout.setEnableRefresh(false);
//        mRefreshLayout.setEnableLoadMore(true);

    }

    @Override
    protected void initListener() {
        mLeftAdapter.setOnLeftItemClickListener(new ProjectLeftAdapter.OnLeftItemClickListener() {
            @Override
            public void onLeftItemClick(ProjectCategory.DataBean item) {
                mId = item.getId();
                mProjectPresenter.getContentByCategory(mId);
            }
        });
        mRightAdapter.setOnItemClickListener(this);



//        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mProjectPresenter.loadMore(mId);
//            }
//        });
    }

    @Override
    protected void initPresenter() {
        mProjectPresenter = ProjectPresenter.getInstance();
        mProjectPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mProjectPresenter != null) {
            mProjectPresenter.getCategories();
        }
    }

    @Override
    protected void onRetryClick() {
        loadData();
    }

    @Override
    protected void release() {
        mProjectPresenter.unregisterViewCallback(this);
    }

    @Override
    public void onCategoryLoaded(ProjectCategory category) {
        mLeftAdapter.setData(category);
        setUpState(State.SUCCESS);
    }

    @Override
    public void onContentLoaded(List<? extends IBaseArticleInfo> data) {
        mRightAdapter.setData(data);
        setUpState(State.SUCCESS);
    }

    @Override
    public void onLoadMreLoaded(List<? extends IBaseArticleInfo> data) {
        ToastUtil.showToast("加载了一些内容");
        mRightAdapter.addData(data);
    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtil.showToast("没有更多内容了");
        //mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onLoadMoreError() {
        ToastUtil.showToast("加载错误，请查看网络是否有问题！");
        //mRefreshLayout.finishLoadMore();
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
    public void toLoad() {
        if (mProjectPresenter != null) {
            mProjectPresenter.loadMore(mId);
        }
    }

    @Override
    public void onCollectClick(IBaseArticleInfo data, int position) {
        if(User.getInstance().isLoginStatus()){
            if(data.isCollect()){
                CollectionUtils.getInstance().unCollect( data.getId(), -1,new IUnCollectCallback() {
                    @Override
                    public void onUnCollectSuccessful() {
                        ToastUtil.showToast("取消收藏成功");
                        mRightAdapter.setCollectStatus(position,false);
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
                       mRightAdapter.setCollectStatus(position,true);
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
