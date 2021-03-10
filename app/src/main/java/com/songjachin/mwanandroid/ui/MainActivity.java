package com.songjachin.mwanandroid.ui;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.ui.home.HomeFragment;
import com.songjachin.mwanandroid.ui.mine.MineFragment;
import com.songjachin.mwanandroid.ui.navigation.NavigationFragment;
import com.songjachin.mwanandroid.ui.talk.TalkFragment;
import com.songjachin.mwanandroid.ui.wx.WxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


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