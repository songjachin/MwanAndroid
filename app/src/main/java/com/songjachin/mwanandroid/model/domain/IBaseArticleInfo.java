package com.songjachin.mwanandroid.model.domain;

import java.util.List;

/**
 * Created by matthew
 */
public interface IBaseArticleInfo {

    String getAuthor();

    String getChapterName();

    String getDesc();

    String getEnvelopePic();

    boolean isFresh();

    boolean isTop();

    String getShareUser();

    String getNiceDate();

    String getSuperChapterName();

    String getTitle();

    List<TagsBean> getTags();

    String getLink();

    boolean isCollect();

    int getId();

    void setCollect(boolean b);

    int getOriginId();
}
