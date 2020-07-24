package com.songjachin.mwanandroid.ui.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseActivity;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.model.domain.KnowArticleBean;

import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.presenter.navigation.KnowArticlePresenter;

import com.songjachin.mwanandroid.ui.adapters.KnowArticleAdapter;

import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.navigation.IKnowArticleCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;


/**
 * Created by matthew
 */
public class KnowledgeArticleActivity extends BaseActivity implements KnowArticleAdapter.OnItemClickListener, IKnowArticleCallback {

    @BindView(R.id.know_article_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.know_article_toolbar)
    Toolbar mToolbar;

    private int mId;
    private KnowArticleAdapter mAdapter;
    private KnowArticlePresenter mKnowArticlePresenter;

    public static void start(Context context, int target) {
        Intent intent = new Intent(context, KnowledgeArticleActivity.class);

        intent.putExtra("id", target);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_knowledge_article;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 150);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new KnowArticleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mToolbar.setTitle("title");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void initPresenter() {
        mKnowArticlePresenter = KnowArticlePresenter.getInstance();
        mKnowArticlePresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        if (mKnowArticlePresenter != null) {
            mKnowArticlePresenter.getKnowArticle(0, mId);
        }
    }

    @Override
    protected void release() {
        mKnowArticlePresenter.unregisterViewCallback(this);
    }


    @Override
    public void onItemClick( KnowArticleBean.DataBean.DatasBean data) {
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
        ArticleActivity.startActivityByActivity(this,
                data.getLink(),
                data.getTitle()
                );
    }

    @Override
    public void toLoad() {
        if (mKnowArticlePresenter != null) {
            mKnowArticlePresenter.loadMore(mId);
        }
    }

    @Override
    public void onCollectClick(KnowArticleBean.DataBean.DatasBean data, int position) {
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
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onContentLoaded(KnowArticleBean body) {
        if(body == null) return;
        KnowArticleBean.DataBean data = body.getData();
        List<KnowArticleBean.DataBean.DatasBean> datas = data.getDatas();
        String chapterName = datas.get(0).getSuperChapterName()+" : "+datas.get(0).getChapterName();
        mToolbar.setTitle(chapterName);
        mAdapter.setData(datas);
    }

    @Override
    public void onLoadMore(List<KnowArticleBean.DataBean.DatasBean> datas) {
        mAdapter.addData(datas);

    }

    @Override
    public void onLoadMoreError() {
        ToastUtil.showToast("加载错误，请查看网络是否有问题！");
    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtil.showToast("没有更多内容了");
    }


}
