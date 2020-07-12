package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by matthew
 */
public class LoopViewPager  extends ViewPager {
    public LoopViewPager(@NonNull Context context) {
        this(context,null);
    }

    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean isLoop = false;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.startLoop();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.stopLoop();
    }

    public void startLoop() {
        isLoop = true;
        //先拿到当前的位置
        postDelayed(mTask,3000);
    }


    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            //mHandler.postDelayed(this,2000);//view本身内部提供了post系列方法，没有必要使用handler，除非需要发送消息

            if(isLoop){
                postDelayed(this, 3000);
            }
        }
    };

    public void stopLoop() {
        isLoop = false;
        removeCallbacks(mTask);
    }

}
