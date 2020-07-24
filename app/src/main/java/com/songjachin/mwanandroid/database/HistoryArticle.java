package com.songjachin.mwanandroid.database;

/**
 * Created by matthew
 */
public class HistoryArticle {
    public HistoryArticle(){

    }

    /**
     *
     * @param articleId id
     * @param author author
     * @param title title
     * @param link link
     * @param time time
     */
    public HistoryArticle(int articleId, String author, String title, String link, String time) {
        this.articleId = articleId;
        this.author = author;
        this.title = title;
        this.link = link;
        this.time = time;
    }

    //String getAuthor();
    //    String getTitle();
    //    String getLink
    //time
    private int articleId;
    private String author;
    private String title;
    private String link;
    private String time;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
