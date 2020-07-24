package com.songjachin.mwanandroid.model;

/**
 * Created by matthew
 */
public class Constant {
    //Url
    public final static String BASE_URL = "https://www.wanandroid.com/";

    //文章详细的url的key
    public final static String KEY_ARTICLE_URL="article_url";
    public final static String KEY_ARTICLE_TITLE="article_title";
    public final static String KEY_ARTICLE_ID ="article_id";
    public final static String KEY_ARTICLE_COLLECT ="article_collect";
    public final static String KEY_ARTICLE_COLLECT_HIDE="article_collect_hide";

    //wx pager fragment bundle key.
    public final static String KEY_WX_PAGER_TITLE = "key_wx_pager_title";
    public final static String KEY_WX_PAGER_ID = "key_wx_pager_id";

    //数据库相关的常量
    public static final String DB_NAME = "wanandroid.db";
    //数据库的版本
    public static final int DB_VERSION_CODE = 1;
    //历史记录的表名
    //String getAuthor();
    //    String getTitle();
    //    String getLink
    //time
    public static final String HISTORY_TB_NAME = "tb_history";
    public static final String HISTORY_ID = "_id";
    public static final String HISTORY_ARTICLE_ID = "history_article_id";
    public static final String HISTORY_AUTHOR = "history_author";
    public static final String HISTORY_TITLE = "history_title";
    public static final String HISTORY_LINK = "history_link";
    public static final String HISTORY_TIME = "history_time";

    //最大的历史记录数
    public static final int MAX_HISTORY_COUNT = 100;
}
