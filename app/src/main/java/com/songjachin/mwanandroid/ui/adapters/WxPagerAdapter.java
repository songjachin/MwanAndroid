package com.songjachin.mwanandroid.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.songjachin.mwanandroid.model.domain.Categories;
import com.songjachin.mwanandroid.ui.wx.WxPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew
 */
public class WxPagerAdapter extends FragmentPagerAdapter {

    private List<Categories.DataBean> mData  = new ArrayList<>();
    public WxPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getName();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WxPagerFragment.newInstance(mData.get(position));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void setData(Categories categories) {
        mData.clear();
        List<Categories.DataBean> data = categories.getData();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
