package com.songjachin.mwanandroid.utils;

import com.songjachin.mwanandroid.base.BaseFragment;
import com.songjachin.mwanandroid.ui.navigation.KnowledgeFragment;
import com.songjachin.mwanandroid.ui.navigation.NavigationChildFragment;
import com.songjachin.mwanandroid.ui.navigation.ProjectFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthew
 */
public class NavigationFragmentCreator {
    private static final  int INDEX_KNOWLEDGE = 0;
    private static final int INDEX_NAVIGATION_CHILD = 1;
    private static final int INDEX_PROJECT = 2;

    public static final int PAGE_COUNT = 3;

    private static Map<Integer, BaseFragment> cache = new HashMap<>();

    public static BaseFragment getFragment(int position){
        BaseFragment baseFragment = cache.get(position);
        if(baseFragment!=null){
            return baseFragment;
        }
        switch (position){
            case INDEX_KNOWLEDGE:
                baseFragment = new KnowledgeFragment();
                break;
            case INDEX_NAVIGATION_CHILD:
                baseFragment = new NavigationChildFragment();
                break;
            case INDEX_PROJECT:
                baseFragment = new ProjectFragment();
            default:
                break;
        }
        cache.put(position,baseFragment);
        return baseFragment;
    }

    public  static String[] title = {"体系","导航","项目"};
}
