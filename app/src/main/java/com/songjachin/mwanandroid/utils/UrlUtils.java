package com.songjachin.mwanandroid.utils;

/**
 * Created by matthew
 */
public class UrlUtils {
    public static String getProjectContentUrl(int page,int id){
        return "project/list/"+page+"/json?cid="+id;
    }
    //https://www.wanandroid.com/article/list/0/json?cid=60

    public static String getKnowArticleUrl(int page,int id){
        return "article/list/"+page+"/json?cid="+id;
    }
}
