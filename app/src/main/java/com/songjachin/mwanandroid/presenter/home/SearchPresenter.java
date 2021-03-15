package com.songjachin.mwanandroid.presenter.home;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.Articles;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Histories;
import com.songjachin.mwanandroid.model.domain.HotKey;
import com.songjachin.mwanandroid.utils.JsonCacheUtil;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.home.ISearchCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class SearchPresenter implements ISearchPresenter {

    private ISearchCallback mSearchCallback;
    private final Api mApi;
    private final JsonCacheUtil mJsonCacheUtil;

    private SearchPresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
        mJsonCacheUtil = JsonCacheUtil.getInstance();
    }


    private static volatile SearchPresenter INSTANCE;

    public static SearchPresenter getInstance(){
        if (INSTANCE == null) {//减少不必要的同步
            synchronized (SearchPresenter.class) {
                if (INSTANCE == null) {//同步后再次判断
                    INSTANCE = new SearchPresenter();
                }
            }
        }
        return INSTANCE;
    }

    public static final int DEFAULT_PAGE = 0;

    /**
     * 搜索的当前页面
     */
    private int mCurrentPage = DEFAULT_PAGE;

    @Override
    public void getHotKey() {
        Call<BaseResponse<List<HotKey>>> hotKey = mApi.getHotKey();
        hotKey.enqueue(new Callback<BaseResponse<List<HotKey>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<HotKey>>> call, Response<BaseResponse<List<HotKey>>> response) {
                BaseResponse<List<HotKey>> body = response.body();
                if(body!=null&&body.getErrorCode()==0){
                    List<HotKey> data = body.getData();
                    mSearchCallback.onRecommendWordsLoaded(data);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<HotKey>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public static final String KEY_HISTORIES = "key_histories";
    @Override
    public void getHistories() {
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES, Histories.class);
        if(mSearchCallback!=null){
            mSearchCallback.onHistoriesLoaded(histories);
        }
    }

    @Override
    public void delHistories() {
        mJsonCacheUtil.delCache(KEY_HISTORIES);
        if(mSearchCallback != null) {
            mSearchCallback.onHistoriesDeleted();
        }
    }

    public static final int DEFAULT_HISTORIES_SIZE = 10;
    private int mHistoriesMaxSize = DEFAULT_HISTORIES_SIZE;

    /**
     * 添加历史记录
     *
     * @param history
     */
    private void saveHistory(String history){
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES,Histories.class);
        //如果说已经在了，就干掉，然后再添加
        List<String> historiesList = histories.getHistories();
        if(histories != null && histories.getHistories() != null) {
            //两者不空则去重
            historiesList.remove(history);

        }
        //去重完成
        //处理没有数据的情况
        if(historiesList == null) {
            historiesList = new ArrayList<>();
        }
        if(histories == null) {
            histories = new Histories();
        }

        //对个数进行限制
        if(historiesList.size() >= mHistoriesMaxSize) {
            historiesList = historiesList.subList(1,mHistoriesMaxSize);
        }
        //添加记录
        historiesList.add(history);
        histories.setHistories(historiesList);
        //保存记录
        mJsonCacheUtil.saveCache(KEY_HISTORIES,histories);
    }
    private String mCurrentKeyword = null;
    @Override
    public void doSearch(String keyword) {
        if(mCurrentKeyword == null || !mCurrentKeyword.equals(keyword)) {
            this.saveHistory(keyword);
            this.mCurrentKeyword = keyword;
        }
        if (mSearchCallback != null) {
            mSearchCallback.onLoading();
        }
        Call<BaseResponse<Articles>> searchArticles = mApi.getSearchArticles(keyword, mCurrentPage);
        searchArticles.enqueue(new Callback<BaseResponse<Articles>>() {
            @Override
            public void onResponse(Call<BaseResponse<Articles>> call, Response<BaseResponse<Articles>> response) {
                BaseResponse<Articles> body = response.body();
                if (body!=null&&body.getErrorCode()==0) {
                    Articles data = body.getData();
                    mSearchCallback.onSearchSuccess(data);
                }else{
                    mSearchCallback.onEmpty();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Articles>> call, Throwable t) {
                mSearchCallback.onError();
            }
        });
    }

    @Override
    public void research() {
        if(mCurrentKeyword == null) {
            if(mSearchCallback != null) {
                mSearchCallback.onEmpty();
            }
        } else {
            this.doSearch(mCurrentKeyword);
        }
    }

    @Override
    public void loaderMore() {
        mCurrentPage++;
        if(mCurrentKeyword == null) {
            if(mSearchCallback != null) {
                mSearchCallback.onMoreLoadedEmpty();
            }
        } else {
            doSearchMore();
        }
    }

    private void doSearchMore() {
        Call<BaseResponse<Articles>> searchArticles = mApi.getSearchArticles(mCurrentKeyword, mCurrentPage);
        searchArticles.enqueue(new Callback<BaseResponse<Articles>>() {
            @Override
            public void onResponse(Call<BaseResponse<Articles>> call, Response<BaseResponse<Articles>> response) {
                BaseResponse<Articles> body = response.body();
                if (body!=null&&body.getErrorCode()==0) {
                    Articles data = body.getData();
                    mSearchCallback.onMoreLoaded(data);
                }else{
                    mSearchCallback.onMoreLoadedEmpty();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Articles>> call, Throwable t) {
                mSearchCallback.onMoreLoadedError();
            }
        });
    }

    @Override
    public void registerViewCallback(ISearchCallback callback) {
        mSearchCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISearchCallback callback) {
        mSearchCallback = null;
    }
}
