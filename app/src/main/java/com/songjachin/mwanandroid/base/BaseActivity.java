package com.songjachin.mwanandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matthew
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mBind = ButterKnife.bind(this);
        initView();
        initListener();
        initPresenter();
        loadData();
    }

    protected abstract void loadData();

    protected abstract void initPresenter();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int getLayoutResId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBind != null) {
            mBind.unbind();
        }
        this.release();
    }
    /**
     * 子类需要释放资源，覆盖即可
     */
    protected abstract void release();
}
