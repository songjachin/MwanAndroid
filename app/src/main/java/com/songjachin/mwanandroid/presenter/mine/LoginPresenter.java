package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.view.mine.ILoginCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matthew
 */
public class LoginPresenter implements ILoginPresenter {

    private final Retrofit mRetrofit;
    private ILoginCallback mILoginCallback;


    private LoginPresenter(){
        mRetrofit = RetrofitManager.getInstance().getRetrofit();
    }

    private static class Holder{
        private static final LoginPresenter INSTANCE = new LoginPresenter();
    }

    public static LoginPresenter getInstance(){
        return Holder.INSTANCE;
    }
    @Override
    public void registerViewCallback(ILoginCallback callback) {
        mILoginCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ILoginCallback callback) {
        mILoginCallback = null;
    }


    @Override
    public void login(String count, String password) {
        Api api = mRetrofit.create(Api.class);
        Call<BaseResponse<Login>> login = api.login(count, password);
        login.enqueue(new Callback<BaseResponse<Login>>() {
            @Override
            public void onResponse(Call<BaseResponse<Login>> call, Response<BaseResponse<Login>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    BaseResponse<Login> body = response.body();

                    if (body!=null&&body.getErrorCode()==0) {
                        User user=User.getInstance();
                        user.setPassword(password);
                        user.setUsername(count);
                        user.setLoginStatus(true);
                        user.save();
                        Login data = body.getData();

                        mILoginCallback.onLoginSuccess(data);
                    }else{
                        mILoginCallback.onLoginFail(body.getErrorMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {
                mILoginCallback.onLoginFail("网络错误");
            }
        });
    }

    @Override
    public void register() {

    }

}
