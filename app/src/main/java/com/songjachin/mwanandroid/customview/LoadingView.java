package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.songjachin.mwanandroid.R;

/**
 * Created by matthew
 */
public class LoadingView extends AppCompatImageView {
    private float mDegrees = 0;
    private boolean mNeedRotate = true;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context,@Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        setImageResource(R.mipmap.loading);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mNeedRotate = true;
        //LogUtils.d(this,"onAttachedToWindow...");
        startRotate();
    }

    private void startRotate() {
        post(new Runnable() {
            @Override
            public void run() {
                mDegrees += 20;
                if (mDegrees >= 360) {
                    mDegrees = 0;
                }
                //我们在自定义View时，当需要刷新View时，如果是在UI线程中，那就直接调用
                // invalidate方法，如果是在非UI线程中，那就通过postInvalidate方法来刷新View
                //postInvalidate方法实现了消息机制，最终调用的也是invalidate方法来刷新View
                //invalidate方法最终调用的是ViewRootImpl中的performTraversals来重新绘制View
                invalidate();
                // LogUtils.d(LoadingView.this,"loading....");
                //判断是否要继续旋转
                //如果不可见，或者已经DetachedFromWindow就不再转动了
                if (getVisibility() != VISIBLE || !mNeedRotate) {
                    removeCallbacks(this);
                } else {
                    postDelayed(this, 20);
                }
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //LogUtils.d(this,"onDetachedFromWindow...");
        stopRotate();
    }

    private void stopRotate() {
        mNeedRotate = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mDegrees,getWidth() / 2,getHeight() / 2);
        //这里改变canvas的一些参数，后面继承父类的方法,先旋转，后绘制
        super.onDraw(canvas);
    }
}
