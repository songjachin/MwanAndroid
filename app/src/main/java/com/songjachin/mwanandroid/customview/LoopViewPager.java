package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by matthew
 */
public class LoopViewPager  extends ViewPager {
    private Path mPath;
    private Paint mPaint;

    private Context mContext;
    //控件的宽
    private float mWidth;
    //控件的高
    private float mHeight;

    //偏移量(注意在程序中需要适当的修改一下偏移量才能满足你的需求)
    private float offset;

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
        post(mTask);
    }


    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            //mHandler.postDelayed(this,2000);//view本身内部提供了post系列方法，没有必要使用handler，除非需要发送消息

            if(isLoop){
                postDelayed(this, 4000);
            }
        }
    };

    public void stopLoop() {
        isLoop = false;
        removeCallbacks(mTask);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mWidth=(float) w;
//        mHeight=(float) h;
//
//        mPath = new Path();
//        mPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
//        //设置弧形下部分需要填充的颜色
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        float h=(mHeight/8)*7;
//
//        mPath.moveTo(0F,h);
//
//        /***
//         * 二阶贝塞尔曲线
//         *  在程序中适当的修改一下offset定制自己的需求
//         */
//        mPath.quadTo(mWidth/2,mHeight+offset,mWidth,h);
//
//
//        mPath.lineTo(mWidth, mHeight+offset);
//        mPath.lineTo(0.0F, mHeight+offset);
//
//        canvas.drawPath(mPath,mPaint);
//
//    }
}
