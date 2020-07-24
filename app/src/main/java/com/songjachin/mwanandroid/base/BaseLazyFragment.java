package com.songjachin.mwanandroid.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.songjachin.mwanandroid.utils.LogUtils;

/**
 * Created by matthew
 */
public abstract class BaseLazyFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    //    abstract class LazyFragment :
//
//    Fragment() {
//
//        /**
//         * 是否执行懒加载
//         */
//        private var isLoaded = false
    private boolean isLoaded = false;
    //        /**
//         * 当前Fragment是否对用户可见
//         */
//        private var isVisibleToUser = false
    private boolean isVisibleToUser = false;
    //        /**
//         * 当使用ViewPager+Fragment形式会调用该方法时，setUserVisibleHint会优先Fragment生命周期函数调用，
//         * 所以这个时候就,会导致在setUserVisibleHint方法执行时就执行了懒加载，
//         * 而不是在onResume方法实际调用的时候执行懒加载。所以需要这个变量
//         */
//        private var isCallResume = false
    private boolean isCallResume = false;
    //        /**
//         * 是否调用了setUserVisibleHint方法。处理show+add+hide模式下，默认可见 Fragment 不调用
//         * onHiddenChanged 方法，进而不执行懒加载方法的问题。
//         */
//        private var isCallUserVisibleHint = false
    private boolean isCallUserVisibleHint = false;
//        override fun onResume() {
//            super.onResume()
//            isCallResume = true
//            if (!isCallUserVisibleHint) isVisibleToUser = !isHidden
//            judgeLazyInit()
//        }
//

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(this,"cc onResume");
        isCallResume =true;
        if(!isCallUserVisibleHint) isVisibleToUser = !isHidden();
        judgeLazyInit();
    }

//        private fun judgeLazyInit () {
//            if (!isLoaded && isVisibleToUser && isCallResume) {
//                lazyInit()
//                Log.d(TAG, "lazyInit:!!!!!!!”)
//                        isLoaded = true
//            }
//        }

    private void judgeLazyInit(){
        if(!isLoaded && isVisibleToUser && isCallResume){
            lazyInit();
            isLoaded = true;
        }
    }
//        override fun onHiddenChanged(hidden:Boolean){
//            super.onHiddenChanged(hidden)
//            isVisibleToUser = !hidden
//            judgeLazyInit()
//        }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d(this,"cc onHiddenChanged : hidden--"+hidden);
        isVisibleToUser = !hidden;
        judgeLazyInit();
    }

    //        override fun onDestroyView() {
//            super.onDestroyView()
//            isLoaded = false
//            isVisibleToUser = false
//            isCallUserVisibleHint = false
//            isCallResume = false
//        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        isLoaded =false;
        isVisibleToUser = false;
        isCallUserVisibleHint =false;
        isCallResume = false;
    }

    //        override fun setUserVisibleHint(isVisibleToUser:Boolean){
//            super.setUserVisibleHint(isVisibleToUser)
//            this.isVisibleToUser = isVisibleToUser
//            isCallUserVisibleHint = true
//            judgeLazyInit()
//        }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtils.d(this,"cc setUserVisibleHint-->"+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        isCallUserVisibleHint = true;
        judgeLazyInit();
    }

    //        abstract fun lazyInit ()
//    }
    protected abstract void lazyInit();

}
