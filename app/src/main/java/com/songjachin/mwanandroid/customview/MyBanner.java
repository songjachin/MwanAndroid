package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.utils.SizeUtils;

/**
 * Created by matthew
 */
public class MyBanner extends LinearLayout {

    private ViewPager mViewPager;
    private TextView mTitleView;
    private LinearLayout mPointContainer;
    private InnerPagerAdapter mInnerPagerAdapter;
    public MyBanner(Context context) {
        this(context,null);
    }

    public MyBanner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //ViewPager
        //TextView
        //点容器，点需要动态地创建，因为点的个数跟内容个数有关系，同学们现在明白第三个参数为什么填写true了吧。
        //填写true的话，就是自动填充到前面的viewGroup里
        LayoutInflater.from(context).inflate(R.layout.layout_my_banner,this,true);
        //等价于如下：
        //View content = LayoutInflater.from(context).inflate(R.layout.layout_looper_view,this,false);
        //addView(content);
        initView();
        initEvent();
    }

    private void initView() {
        mViewPager = this.findViewById(R.id.content_pager);
        mTitleView = this.findViewById(R.id.content_title);
        mPointContainer = this.findViewById(R.id.content_pointer_container);
    }
    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动以后停下来的回调，position指所停在的位置
                //这个时候我们去获取标题
                if(mInnerPagerAdapter.getDataSize() == 0) return;
                int target = position % mInnerPagerAdapter.getDataSize();
                updateIndicator(target);
                mTitleView.setText(mInnerPagerAdapter.getTitle(target));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateIndicator(int position){
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            View point = mPointContainer.getChildAt(i);
            if (i == position) {
                point.setBackgroundColor(Color.parseColor("#ff0000"));
            } else {
                point.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
    }

    public void initIndicator() {
        if(mInnerPagerAdapter != null) {
            //先删除
            mPointContainer.removeAllViews();
            int indicatorSize = mInnerPagerAdapter.getDataSize();
            for(int i = 0; i < indicatorSize; i++){
                View point = new View(getContext());
                int size = SizeUtils.dip2px(getContext(),5);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
                layoutParams.leftMargin = SizeUtils.dip2px(getContext(), 5);
                layoutParams.rightMargin = SizeUtils.dip2px(getContext(), 5);
                point.setLayoutParams(layoutParams);
                if (i == 0) {
                    point.setBackgroundColor(Color.parseColor("#ff0000"));
                } else {
                    point.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                mPointContainer.addView(point);
            }
        }
    }


    //==============================public
    public void setAdapter(InnerPagerAdapter adapter){
        mInnerPagerAdapter = adapter;
        mViewPager.setAdapter(mInnerPagerAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2 + 1 );
    }

    public static abstract class InnerPagerAdapter extends PagerAdapter{
        public abstract int getDataSize();
        protected abstract String getTitle(int target);
    }

}
