package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.CollectBean;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.mine.ICollectActivityCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class CollectPresenter implements ICollectPresenter{

    private ICollectActivityCallback mCollectActivityCallback;
    private final Api mApi;
    private int currentPage = 0;

    private CollectPresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static class Holder{
        private static final CollectPresenter INSTANCE = new CollectPresenter();
    }

    public static CollectPresenter getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public void registerViewCallback(ICollectActivityCallback callback) {
        mCollectActivityCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ICollectActivityCallback callback) {
        mCollectActivityCallback =null;
    }

    @Override
    public void getCollect(int pageId) {
        Call<CollectBean> collect = mApi.getCollect(currentPage);
        collect.enqueue(new Callback<CollectBean>() {
            @Override
            public void onResponse(Call<CollectBean> call, Response<CollectBean> response) {
                CollectBean body = response.body();
                if(body!=null&&body.getErrorCode()==0){
                    List<CollectBean.DataBean.DatasBean> datas = body.getData().getDatas();
                    mCollectActivityCallback.onContentLoaded(datas);
                }else{
                    mCollectActivityCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<CollectBean> call, Throwable t) {
                mCollectActivityCallback.onError();
            }
        });
    }
}
