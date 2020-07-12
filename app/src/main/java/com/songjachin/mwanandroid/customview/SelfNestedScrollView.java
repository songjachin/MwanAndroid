package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.utils.LogUtils;

/**
 * Created by matthew
 */
public class SelfNestedScrollView extends NestedScrollView {
    private int mHeaderHeight = 0;
    private float mFloat;
    private int originScroll = 0;

    public SelfNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public SelfNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setHeaderHeight(int headerHeight) {
        this.mHeaderHeight = headerHeight;
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed,
                                  @ViewCompat.NestedScrollType int type) {
        //        LogUtils.d(this,"dy === > " + dy);
        //父类消耗的不超过一个mHeaderHeight+一次滑动的值
        LogUtils.d(this,"origin----->"+originScroll+" height--->"+ mHeaderHeight);
        if(originScroll < mHeaderHeight) {
            //如果滑动的值小于设定的值，给NestedScrollView消耗
            //Move the scrolled position of your view. This will cause a call to
            //{@link #onScrollChanged(int, int, int, int)} and the view will be
            //invalidated.
            scrollBy(dx,dy);
            consumed[0] = dx;
            consumed[1] = dy;
            //React to a nested scroll in progress before the target view consumes a portion of the scroll.
            //在目标视图消耗滚动的一部分之前，对正在进行的嵌套滚动做出反应。
            //When working with nested scrolling often the parent view may want an opportunity to consume the scroll
            // before the nested scrolling child does. An example of this is a drawer that contains a scrollable list.
            //在使用嵌套滚动时，通常父视图可能希望有机会在嵌套滚动子级之前使用滚动。
            //一个示例是包含可滚动列表的抽屉。
            // The user will want to be able to scroll the list fully into view before the list itself begins scrolling.
            //在列表本身开始滚动之前，用户将希望能够将列表完全滚动到视图中。
            //onNestedPreScroll is called when a nested scrolling child invokes View.dispatchNestedPreScroll(int, int, int[], int[]).
            //onNestedPreScroll is called 当 一个滚动的。。。触发。。。
            // The implementation should report how any pixels of the scroll reported by dx, dy were consumed in the consumed array.
            // Index 0 corresponds to dx and index 1 corresponds to dy. This parameter will never be null. Initial values for
            // consumed[0] and consumed[1] will always be 0.
            //当子类调用 View.dispatchNestedPreScroll时，实现方法应声明多少px被consumed
        }
        super.onNestedPreScroll(target,dx,dy,consumed,type);
    }

    @Override
    protected void onScrollChanged(int l,int t,int oldl,int oldt) {
        this.originScroll = t;
        //        LogUtils.d(this,"vertical -- > " + t);整体滑动的值
        super.onScrollChanged(l,t,oldl,oldt);
    }

    //大概流程：onNestPreScroll在滑动之前调用，dx.dy代表滑动的值，
    //originScroll = 0,（<headerHeight),先调用scrollBy，scrollBy触发onScrollChanged为originalScroll重新赋值
    //调用consume赋值，super父类，
}
