package com.songjachin.mwanandroid.ui.mine;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.songjachin.mwanandroid.presenter.mine.HistoryPresenter;
import com.songjachin.mwanandroid.ui.adapters.HistoryAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.view.mine.IHistoryCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public class HistoryActivity extends AppCompatActivity implements IHistoryCallback, HistoryAdapter.OnItemClickListener {

    @BindView(R.id.history_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.history_back)
    ImageView tvBack;

    @BindView(R.id.history_clean)
    TextView tvClean;

    private Unbinder mBind;
    private HistoryPresenter mHistoryPresenter;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mBind = ButterKnife.bind(this);
        initView();
        initEvent();
        initPresenter();
        loadData();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(BaseApplication.getAppContext(),1.5f);
                outRect.bottom = SizeUtils.dip2px(BaseApplication.getAppContext(),1.5f);
            }
        });

        mAdapter = new HistoryAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    private void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHistoryPresenter != null) {
                    mHistoryPresenter.cleanHistories();
                }
            }
        });

        mAdapter.setOnItemClickListener(this);
    }

    private void initPresenter() {
        mHistoryPresenter = HistoryPresenter.getInstance();
        mHistoryPresenter.registerViewCallback(this);
    }


    private void loadData() {
        mHistoryPresenter.listHistories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
        mHistoryPresenter.unregisterViewCallback(this);
    }

    private static final String TAG = "HistoryActivity";
    @Override
    public void onHistoriesLoaded(List<HistoryArticle> articles) {
        Log.d(TAG, "onHistoriesLoaded: -->articles==="+articles.size());
        mAdapter.setData(articles);
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

    @Override
    public void onItemClick(HistoryArticle data) {
        ArticleActivity.startActivityByActivity(
                this,
                data.getLink(),
                data.getTitle()
        );
    }
}
