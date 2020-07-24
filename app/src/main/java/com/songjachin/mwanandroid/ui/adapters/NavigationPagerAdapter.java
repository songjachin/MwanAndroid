package com.songjachin.mwanandroid.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.songjachin.mwanandroid.utils.NavigationFragmentCreator;

/**
 * Created by matthew
 */
public class NavigationPagerAdapter extends FragmentPagerAdapter {

    public NavigationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return NavigationFragmentCreator.title[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NavigationFragmentCreator.getFragment(position);
    }

    @Override
    public int getCount() {
        return NavigationFragmentCreator.PAGE_COUNT;
    }
}
