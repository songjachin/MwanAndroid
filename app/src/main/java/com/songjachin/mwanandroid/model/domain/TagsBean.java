package com.songjachin.mwanandroid.model.domain;

/**
 * Created by matthew
 */
public class TagsBean extends BaseBean {
    /**
     * name : 公众号
     * url : /wxarticle/list/410/1
     */

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
