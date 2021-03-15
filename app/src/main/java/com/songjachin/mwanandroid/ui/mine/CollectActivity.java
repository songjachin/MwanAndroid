package com.songjachin.mwanandroid.ui.mine;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.mine.CollectPresenter;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.ui.adapters.CollectAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.mine.ICollectActivityCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public class CollectActivity extends AppCompatActivity implements CollectAdapter.OnItemClickListener, ICollectActivityCallback {

    @BindView(R.id.collect_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Unbinder mBind;
    private CollectAdapter mAdapter;
    private CollectPresenter mCollectPresenter;
    private int mSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        mBind = ButterKnife.bind(this);
        initView();
        initEvent();
        initPresenter();
        loadData();
    }

    private void initView() {
        initToolbar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(BaseApplication.getAppContext(), 1.5f);
                outRect.bottom = SizeUtils.dip2px(BaseApplication.getAppContext(), 1.5f);
            }
        });

        mAdapter = new CollectAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initToolbar() {
        mToolbar.setTitle("我的收藏");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(this);
    }

    private void initPresenter() {
        mCollectPresenter = CollectPresenter.getInstance();
        mCollectPresenter.registerViewCallback(this);

    }
    private void loadData() {
        mCollectPresenter.getCollect(0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
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
        ArticleActivity.startActivityByActivity(
                this,
                data.getLink(),
                data.getTitle()
        );
    }

    @Override
    public void onCollectClick(IBaseArticleInfo data, int position,int size) {
        if(data.isCollect()) {
            CollectionUtils.getInstance().unCollect(data.getId(),data.getOriginId(), new IUnCollectCallback() {
                @Override
                public void onUnCollectSuccessful() {
                    ToastUtil.showToast("取消收藏成功");
                    if(size == 1){
                        mRecyclerView.setVisibility(View.INVISIBLE);
                        return;
                    }
                    mAdapter.remove(position);
                }

                @Override
                public void onUnCollectFail() {
                    ToastUtil.showToast("取消收藏失败");
                }
            });
        }
    }


    @Override
    public void onContentLoaded(List<? extends IBaseArticleInfo> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}
