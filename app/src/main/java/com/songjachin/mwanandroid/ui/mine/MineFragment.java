package com.songjachin.mwanandroid.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.model.domain.Login;
import com.songjachin.mwanandroid.model.domain.Ranking;
import com.songjachin.mwanandroid.presenter.mine.MinePresenter;
import com.songjachin.mwanandroid.view.mine.IMineCallback;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class MineFragment extends BaseFragment implements IMineCallback {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_user_ranking)
    TextView tvUserRanking;
    @BindView(R.id.my_ranking)
    LinearLayout mRanking;
    @BindView(R.id.my_collect)
    LinearLayout mCollect;
    @BindView(R.id.my_history)
    LinearLayout mHistory;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.digit_rank)
    TextView digitRank;
    private MinePresenter mPresenter;

    @Override
    protected int getSuccessViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void initListener() {
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        mRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.getInstance().isLoginStatus()){
                    Intent intent = new Intent(mActivity,CollectActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivityForResult(intent, 1);
                }

            }
        });
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, HistoryActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               User.getInstance().reset();
               tvUserName.setText("点击登录");
               tvUserName.setClickable(true);
               tvUserId.setText("");
                tvUserLevel.setText("");
                tvUserRanking.setText("");
                digitRank.setText("我的积分：");
            }
        });
    }

    @Override
    public void onResume() {
        if(User.getInstance().isLoginStatus()){
            mPresenter.getRanking();
            tvUserName.setText(User.getInstance().getUsername());
            tvUserName.setClickable(false);
        }
        super.onResume();
    }

    @Override
    protected void initPresenter() {
        mPresenter = MinePresenter.getInstance();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getRanking();
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void release() {
        mPresenter.unregisterViewCallback(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == 1) {
            //refersh
            Login info = (Login)data.getSerializableExtra("login_info");
            if(info.getUsername()!=null){
                tvUserName.setText(info.getUsername());
                tvUserName.setClickable(false);
            }

            mPresenter.getRanking();
        }
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onRankingInfo(Ranking data) {
        String level = data.getLevel()+"";
        String rank = data.getRank()+"";
        String userId= data.getUserId()+"";
        String rankNum ="我的积分: "+ data.getCoinCount();

        tvUserLevel.setText(level);
        tvUserRanking.setText(rank);
        tvUserId.setText(userId);
        digitRank.setText(rankNum);
    }
}
