package com.songjachin.mwanandroid.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.customview.TextFlowLayout;
import com.songjachin.mwanandroid.model.domain.Article;
import com.songjachin.mwanandroid.model.domain.Articles;
import com.songjachin.mwanandroid.model.domain.Histories;
import com.songjachin.mwanandroid.model.domain.HotKey;
import com.songjachin.mwanandroid.presenter.CollectionUtils;
import com.songjachin.mwanandroid.presenter.ICollectCallback;
import com.songjachin.mwanandroid.presenter.IUnCollectCallback;
import com.songjachin.mwanandroid.presenter.home.SearchPresenter;
import com.songjachin.mwanandroid.ui.adapters.SearchAdapter;
import com.songjachin.mwanandroid.ui.home.ArticleActivity;
import com.songjachin.mwanandroid.ui.mine.LoginActivity;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.KeyboardUtil;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.SizeUtils;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.home.ISearchCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public class SearchActivity extends AppCompatActivity implements ISearchCallback, TextFlowLayout.OnFlowTextItemClickListener, SearchAdapter.OnItemClickListener {

    @BindView(R.id.iv_back)
    ImageView btnBack;
    @BindView(R.id.search_history_view)
    public TextFlowLayout mHistoriesView;

    @BindView(R.id.search_recommend_view)
    public TextFlowLayout mRecommendView;

    @BindView(R.id.search_recommend_container)
    public View mRecommendContainer;

    @BindView(R.id.search_history_container)
    public View mHistoriesContainer;


    @BindView(R.id.search_history_delete)
    public View mHistoryDelete;


    @BindView(R.id.search_result_list)
    public RecyclerView mSearchList;

    @BindView(R.id.search_btn)
    public TextView mSearchBtn;

    @BindView(R.id.search_clean_btn)
    public ImageView mCleanInputBtn;

    @BindView(R.id.search_input_box)
    public EditText mSearchInputBox;


    @BindView(R.id.search_result_container)
    public SmartRefreshLayout mRefreshContainer;
    private Unbinder mBind;
    private SearchAdapter mSearchResultAdapter;
    private SearchPresenter mSearchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBind = ButterKnife.bind(this);

        initView();
        initPresenter();
        initEvent();
        loadData();
    }

    private void initView() {
        mSearchList.setLayoutManager(new LinearLayoutManager(this));
        mSearchList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getApplicationContext(),1.5f);
                outRect.bottom = SizeUtils.dip2px(getApplicationContext(),1.5f);
            }
        });
        //
        mSearchResultAdapter = new SearchAdapter();
        mSearchList.setAdapter(mSearchResultAdapter);

        //mRefreshContainer.setEnableLoadMore(true);
        mRefreshContainer.setEnableRefresh(false);
        mRefreshContainer.setEnableAutoLoadMore(false);
    }

    private void initPresenter() {
        mSearchPresenter = SearchPresenter.getInstance();
        mSearchPresenter.registerViewCallback(this);
    }

    private void initEvent() {
        mHistoriesView.setOnFlowTextItemClickListener(this);
        mRecommendView.setOnFlowTextItemClickListener(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //发起搜索
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有内容搜索
                //如果输入框没有内容则取消
                if(hasInput(false)) {
                    //没有空格时长度不为0则搜索
                    if(mSearchPresenter != null) {
                        //mSearchPresenter.doSearch(mSearchInputBox.getText().toString().trim());
                        toSearch(mSearchInputBox.getText().toString().trim());
                        KeyboardUtil.hide(BaseApplication.getAppContext(),v);
                    }
                } else {
                    //隐藏键盘
                    KeyboardUtil.hide(BaseApplication.getAppContext(),v);
                }
            }
        });

        //清除输入框里的内容
        mCleanInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchInputBox.setText("");
                //回到历史记录界面
                switch2HistoryPage();
            }
        });

        //监听输入框的变化
        mSearchInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //变化时候的通知
                //LogUtils.d(SearchFragment.this,"input text === > " + s.toString().trim());
                //如果长度不为0，那么显示删除按钮
                //否则隐藏删除按钮
                mCleanInputBtn.setVisibility(hasInput(true)?View.VISIBLE : View.GONE);
                mSearchBtn.setText(hasInput(false) ? "搜索" : "取消");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchInputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH&&mSearchPresenter!=null){
                    String keyword = v.getText().toString().trim();
                    if(TextUtils.isEmpty(keyword)){
                        return false;
                    }

                    //判断拿到的内容是否为空
                    LogUtils.d(SearchActivity.this," input text === > " + keyword);
                    //发起搜索
                    toSearch(keyword);
                    //mSearchPresenter.doSearch(keyword);
                }
                return false;

            }
        });

        mHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchPresenter.delHistories();
            }
        });

        mRefreshContainer.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mSearchPresenter != null) {
                    mSearchPresenter.loaderMore();
                }
            }
        });

        mSearchResultAdapter.setOnItemClickListener(this);
    }


    /**
     * 切换到历史和推荐界面
     */
    private void switch2HistoryPage() {
        if(mSearchPresenter != null) {
            mSearchPresenter.getHistories();
        }
        if(mRecommendView.getContentSize() != 0) {
            mRecommendContainer.setVisibility(View.VISIBLE);
        } else {
            mRecommendContainer.setVisibility(View.GONE);
        }
        //内容要隐藏
        mRefreshContainer.setVisibility(View.GONE);
        mRefreshContainer.setEnableAutoLoadMore(false);
    }

    private void toSearch(String text) {
        if(mSearchPresenter != null) {
            mSearchList.scrollToPosition(0);
            mSearchInputBox.setText(text);
            mSearchInputBox.setFocusable(true);
            mSearchInputBox.requestFocus();
            //mSearchInputBox.setSelection(text.length());
            mSearchInputBox.setSelection(text.length(),text.length());
            mSearchPresenter.doSearch(text);
        }
    }

    private boolean hasInput(boolean containSpace) {
        if(containSpace) {
            return mSearchInputBox.getText().toString().length() > 0;
        } else {
            return mSearchInputBox.getText().toString().trim().length() > 0;
        }
    }

    private void loadData() {
        mSearchPresenter.getHotKey();
        mSearchPresenter.getHistories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
        if (mSearchPresenter != null) {
            mSearchPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onError() {
        ToastUtil.showToast("网络错误");
    }

    @Override
    public void onLoading() {
        ToastUtil.showToast("正在加载");
    }

    @Override
    public void onEmpty() {
        ToastUtil.showToast("内容为空");
    }

    @Override
    public void onHistoriesLoaded(Histories histories) {
//        List<String> test = new ArrayList<>();
//        test.add("hello");
//        test.add("hello");
//        test.add("hello");
//        test.add("hello");
//        test.add("hello");
//        histories.setHistories(test);
        if(histories == null || histories.getHistories().size() == 0) {
            mHistoriesContainer.setVisibility(View.GONE);
        } else {
            LogUtils.d(SearchActivity.this,"---------history"+histories.getHistories().size());
            mHistoriesContainer.setVisibility(View.VISIBLE);
            mHistoriesView.setTextList(histories.getHistories());
        }
    }

    @Override
    public void onHistoriesDeleted() {
        //更新历史记录
        if(mSearchPresenter != null) {
            mSearchPresenter.getHistories();
        }
    }

    @Override
    public void onSearchSuccess(Articles data) {
        //隐藏掉历史记录和推荐
        mRecommendContainer.setVisibility(View.GONE);
        mHistoriesContainer.setVisibility(View.GONE);
        //显示搜索界面
        mRefreshContainer.setVisibility(View.VISIBLE);
        mRefreshContainer.setEnableAutoLoadMore(true);
        //设置数据
        try{
            mSearchResultAdapter.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            ToastUtil.showToast("内容为空");
        }
    }

    @Override
    public void onMoreLoaded(Articles data) {
        mRefreshContainer.finishLoadMore();
        //加载到更多的结果
        //拿到结果，添加到适配器的尾部
        mSearchResultAdapter.addData(data);
    }

    @Override
    public void onMoreLoadedError() {
        mRefreshContainer.finishLoadMore();
        ToastUtil.showToast("网络异常，请稍后重试");
    }

    @Override
    public void onMoreLoadedEmpty() {
        mRefreshContainer.finishLoadMore();
        ToastUtil.showToast("没有更多数据");
    }

    @Override
    public void onRecommendWordsLoaded(List<HotKey> data ) {
        List<String> recommendKeywords = new ArrayList<>();
        for (HotKey hotKey : data) {
            recommendKeywords.add(hotKey.getName());
        }
        if(data.size() == 0) {
            mRecommendContainer.setVisibility(View.GONE);
        } else {
            mRecommendView.setTextList(recommendKeywords);
            mRecommendContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFlowItemClick(String text) {
        toSearch(text);
    }

    @Override
    public void onItemClick(Article data) {
        ArticleActivity.startActivityByActivity(
                this,
                data.getLink(),
                data.getTitle()
        );
    }

    @Override
    public void onCollectClick(Article data, int position) {
        if(User.getInstance().isLoginStatus()){
            if(data.isCollect()){
                CollectionUtils.getInstance().unCollect( data.getId(),-1, new IUnCollectCallback() {
                    @Override
                    public void onUnCollectSuccessful() {
                        ToastUtil.showToast("取消收藏成功");
                        mSearchResultAdapter.setCollectStatus(position,false);
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
                        mSearchResultAdapter.setCollectStatus(position,true);
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
}
