package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Ranking;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.mine.IMineCallback;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class MinePresenter implements IMinePresenter {

    private IMineCallback mMineCallback;

    private MinePresenter(){

    }

    private static volatile MinePresenter instance;

    public static MinePresenter getInstance(){
        if (instance == null) {
            synchronized (MinePresenter.class){
                if (instance == null) {
                    instance = new MinePresenter();
                }
            }
        }
        return instance;
    }
    @Override
    public void registerViewCallback(IMineCallback callback) {
        mMineCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IMineCallback callback) {
        mMineCallback =null;
    }

    @Override
    public void getRanking() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<BaseResponse<Ranking>> ranking = api.getRanking();
        ranking.enqueue(new Callback<BaseResponse<Ranking>>() {
            @Override
            public void onResponse(Call<BaseResponse<Ranking>> call, Response<BaseResponse<Ranking>> response) {
                BaseResponse<Ranking> body = response.body();
                int code = response.code();
                LogUtils.d(MinePresenter.class,"code-------------------"+code+"--->"+body.getErrorCode()+"---msg"+body.getErrorMsg());
                if (body!=null&&body.getErrorCode()==0) {

                    mMineCallback.onRankingInfo(body.getData());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Ranking>> call, Throwable t) {

            }
        });
    }
}
