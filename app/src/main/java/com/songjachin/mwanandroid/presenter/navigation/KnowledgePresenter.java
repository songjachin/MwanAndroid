package com.songjachin.mwanandroid.presenter.navigation;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.KnowledgeBean;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.navigation.IKnowCallback;

import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class KnowledgePresenter implements IKnowledgePresenter {

    private IKnowCallback mIKnowCallback;
    private final Api mApi;

    private KnowledgePresenter(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private static class KnowledgePresenterHolder{
        private static final KnowledgePresenter instance = new KnowledgePresenter();
    }

    public static KnowledgePresenter  getInstance(){
        return KnowledgePresenterHolder.instance;
    }

    @Override
    public void registerViewCallback(IKnowCallback callback) {
        mIKnowCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IKnowCallback callback) {
        mIKnowCallback = null;
    }

    @Override
    public void getKnowledgeContent() {
        if (mIKnowCallback != null) {
            mIKnowCallback.onLoading();
        }
        Call<KnowledgeBean> knowledge = mApi.getKnowledge();
        knowledge.enqueue(new Callback<KnowledgeBean>() {
            @Override
            public void onResponse(Call<KnowledgeBean> call, Response<KnowledgeBean> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK&&mIKnowCallback!=null) {
                    List<KnowledgeBean.DataBean> data = response.body().getData();
                    handleResult(data);
                }
            }

            @Override
            public void onFailure(Call<KnowledgeBean> call, Throwable t) {
                if (mIKnowCallback != null) {
                    mIKnowCallback.onError();
                }
            }
        });
    }

    private void handleResult(List<KnowledgeBean.DataBean> data) {
        if (data.size()==0) {
            mIKnowCallback.onEmpty();
        }else{
            mIKnowCallback.onContentLoaded(data);
        }

    }
}
