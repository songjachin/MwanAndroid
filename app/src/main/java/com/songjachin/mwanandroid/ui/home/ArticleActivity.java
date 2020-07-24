package com.songjachin.mwanandroid.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
<<<<<<< HEAD
=======
import android.view.MenuItem;
>>>>>>> 7f475a1... finish the most
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.base.BaseActivity;
import com.songjachin.mwanandroid.model.Constant;

import butterknife.BindView;

/**
 * Created by matthew
 */
public class ArticleActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    FrameLayout mFrameLayout;
<<<<<<< HEAD
//   @BindView(R.id.web_linear)
//    LinearLayout mWebLinear;
=======
    @BindView(R.id.web_linear)
    LinearLayout mWebLinear;
>>>>>>> 7f475a1... finish the most

    private String mUrl;
    private String mTitle;
    private AgentWeb mAgentWeb;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {
        getBundleData();
        initToolbar();
        initWeb();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void release() {

    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    /**
     * 获取传入的数据
     */
    private void getBundleData() {
        mUrl = getIntent().getStringExtra(Constant.KEY_ARTICLE_URL);
        mTitle = getIntent().getStringExtra(Constant.KEY_ARTICLE_TITLE);
    }
    private void initToolbar() {
        mToolbar.setTitle(Html.fromHtml(mTitle));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mToolbar.setNavigationOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.putExtra(Constant.KEY_ARTICLE_COLLECT, isCollect);
//            setResult(RESULT_OK, intent);
//            finish();
//        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWeb(){
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mUrl);
        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
        mSettings.setAppCacheEnabled(true);
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);

        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
<<<<<<< HEAD
        mSettings.setDisplayZoomControls(true);
=======
        mSettings.setDisplayZoomControls(false);
>>>>>>> 7f475a1... finish the most
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    /**
     * 给没有文章id的调用
     * @param activity
     * @param fragment
     * @param url
     * @param title
     */
    public static void startActivityByFragment(Activity activity, Fragment fragment, String url, String title) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(Constant.KEY_ARTICLE_URL, url);
        intent.putExtra(Constant.KEY_ARTICLE_TITLE, title);
        fragment.startActivity(intent);
    }

    public static void startActivityByActivity(Activity activity,String url,String title){
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(Constant.KEY_ARTICLE_URL, url);
        intent.putExtra(Constant.KEY_ARTICLE_TITLE, title);
        activity.startActivity(intent);
    }
}
