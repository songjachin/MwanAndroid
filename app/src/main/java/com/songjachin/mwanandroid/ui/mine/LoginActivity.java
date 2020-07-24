package com.songjachin.mwanandroid.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.presenter.mine.LoginPresenter;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.utils.RetrofitManager;
import com.songjachin.mwanandroid.utils.ToastUtil;
import com.songjachin.mwanandroid.view.mine.ILoginCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public class LoginActivity extends AppCompatActivity implements ILoginCallback {
    @BindView(R.id.backBtn)
    Button btnBack;
    @BindView(R.id.edit_count)
    EditText editCount;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private Unbinder mBind;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBind = ButterKnife.bind(this);
        initView();
        initEvent();
        initPresenter();
    }

    private void initView() {


    }

    private void initEvent() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getEditText(editCount))) {
                    ToastUtil.showToast("账号为空");
                } else if (TextUtils.isEmpty(getEditText(editPassword))) {
                    ToastUtil.showToast("密码为空");
                } else {
                    mLoginPresenter.login(getEditText(editCount),getEditText(editPassword));

                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void initPresenter() {
        mLoginPresenter = LoginPresenter.getInstance();
        mLoginPresenter.registerViewCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.unregisterViewCallback(this);
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
    }

    private String getEditText(EditText editText) {
        return editText.getText().toString();
    }


//============================================================================
    @Override
    public void onLoginSuccess(Login data) {
        ToastUtil.showToast("登录成功");
        RetrofitManager.reBuildRetrofit();
        Intent intent = new Intent();
        intent.putExtra("login_info" ,data);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onLoginFail(String msg) {
        ToastUtil.showToast(msg);
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
