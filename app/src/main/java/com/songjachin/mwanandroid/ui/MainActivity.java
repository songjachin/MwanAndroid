package com.songjachin.mwanandroid.ui;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.model.Api;
import com.songjachin.mwanandroid.model.domain.BaseResponse;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.ui.home.HomeFragment;
import com.songjachin.mwanandroid.ui.mine.MineFragment;
<<<<<<< HEAD
import com.songjachin.mwanandroid.ui.mine.User;
import com.songjachin.mwanandroid.ui.navigation.NavigationFragment;
import com.songjachin.mwanandroid.ui.talk.TalkFragment;
import com.songjachin.mwanandroid.ui.wx.WxFragment;
import com.songjachin.mwanandroid.utils.RetrofitManager;

import java.net.HttpURLConnection;
=======
import com.songjachin.mwanandroid.ui.navigation.NavigationFragment;
import com.songjachin.mwanandroid.ui.talk.TalkFragment;
import com.songjachin.mwanandroid.ui.wx.WxFragment;
>>>>>>> 7f475a1... finish the most

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private Unbinder mBind;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.main_navigation_bar)
    BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private TalkFragment mTalkFragment;
    private NavigationFragment mNavigationFragment;
    private MineFragment mMineFragment;
    private WxFragment mWxFragment;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initView();
        initFragment();
        initListener();
        if(User.getInstance().isLoginStatus()){
            String count = User.getInstance().getUsername();
            String password = User.getInstance().getPassword();

            if(!TextUtils.isEmpty(count)&& !TextUtils.isEmpty(password)){
                Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
                Api api = retrofit.create(Api.class);
                Call<BaseResponse<Login>> login = api.login(count, password);
                login.enqueue(new Callback<BaseResponse<Login>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Login>> call, Response<BaseResponse<Login>> response) {
                        if (response.code()== HttpURLConnection.HTTP_OK) {
                            BaseResponse<Login> body = response.body();
                            Login data = body.getData();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {

                    }
                });
            }
        }
    }

    private void initView() {
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mTalkFragment = new TalkFragment();
        mMineFragment = new MineFragment();
        mNavigationFragment = new NavigationFragment();
        mWxFragment = new WxFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private BaseFragment lastFragment = null;
    protected void switchFragment(BaseFragment targetFragment) {

        //如果上一个fragment跟当前要切换的fragment是同一个，那么不需要切换
        if(lastFragment == targetFragment) {
            return;
        }
        //修改成add和hide的方式来控制Fragment的切换
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        if(!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_page_container,targetFragment);
        } else {
            fragmentTransaction.show(targetFragment);
        }
        if(lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        lastFragment = targetFragment;
        fragmentTransaction.commit();
    }


    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.talk:
                        switchFragment(mTalkFragment);
                        break;
                    case R.id.project:
                        switchFragment(mNavigationFragment);
                        break;
                    case R.id.mine:
                        switchFragment(mMineFragment);
                        break;
                    case R.id.wx:
                        switchFragment(mWxFragment);
                        break;
                    default: break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}