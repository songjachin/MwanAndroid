package com.songjachin.mwanandroid.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.songjachin.mwanandroid.customview.MyBanner;
import com.songjachin.mwanandroid.model.domain.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew
 */
public class BannerAdapter extends MyBanner.InnerPagerAdapter {

    private List<BannerBean.DataBean> mList = new ArrayList<>();

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(mList.size() == 0) return null;
        int realPosition = position % mList.size();
        BannerBean.DataBean dataBean = mList.get(realPosition);
        String coverUrl = dataBean.getImagePath();
        ImageView iv = new ImageView(container.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(coverUrl).into(iv);

        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getDataSize() {
        return mList.size();
    }

    @Override
    protected String getTitle(int target) {
        return mList.get(target).getTitle();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public void setData(List<BannerBean.DataBean> data){
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
