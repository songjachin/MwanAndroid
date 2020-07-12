package com.songjachin.mwanandroid.model.domain;

import java.util.List;

/**
 * Created by matthew
 */
public class ArticleBean {

    /**
     * data : [{"apkLink":"","audit":1,"author":"扔物线","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12554,"link":"https://www.bilibili.com/video/BV1fV411r7Lw","niceDate":"刚刚","niceShareDate":"2020-03-23 16:36","origin":"","prefix":"","projectLink":"","publishTime":1596211200000,"realSuperChapterId":248,"selfVisible":0,"shareDate":1584952597000,"shareUser":"","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"我被 Kotlin 和 Android 两个官方约谈了","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":377,"chapterName":"优质内推","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14229,"link":"https://mp.weixin.qq.com/s/7pxn9H5xXlNAF4tr4q3g_A","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594225157000,"realSuperChapterId":376,"selfVisible":0,"shareDate":1594225134000,"shareUser":"","superChapterId":377,"superChapterName":"内推","tags":[],"title":"字节跳动，招人！","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>谈到 RecyclerView，相信不少同学，张口都能说出它的几级缓存机制：<\/p>\r\n<p>例如：<\/p>\r\n<ul>\r\n<li>一级缓存：mAttachedScrap 和 mChangedScrap <\/li>\r\n<li>二级缓存：mCachedViews <\/li>\r\n<li>三级缓存：ViewCacheExtension <\/li>\r\n<li>四级缓存：RecycledViewPool <\/li>\r\n<\/ul>\r\n<p>然后说怎么用，就是先从 1 级找，然后 2 级...然后4 级，找不到 create ViewHolder。<\/p>\r\n<p>那么，有没有思考过，其实上面几级缓存都属于\u201c内存缓存&quot;，那么这么分级肯定有一定区别。<\/p>\r\n<p>问题来了：<\/p>\r\n<ol>\r\n<li>每一级缓存具体作用是什么？<\/li>\r\n<li>分别在什么场景下会用到哪些缓存呢？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":14222,"link":"https://www.wanandroid.com/wenda/show/14222","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594220710000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1594220631000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | RecyclerView的多级缓存机制，每级缓存到底起到什么样的作用？","type":1,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>上一个问答<a href=\"https://wanandroid.com/wenda/show/13775\">每日一问 | Activity与Fragment的那些事，\u201c用起来没问题，我都要走了，你崩溃了？\u201d<\/a> 其实离不开 onSaveInstanceState方法被调用的关系。<\/p>\r\n<p>记得很久以前总记得：\u201conSaveInstanceState 会在系统意外杀死 Activity 时调用，或者横纵屏切换的时候调用\u201d。<\/p>\r\n<p>问题是：<\/p>\r\n<ol>\r\n<li>随着Android SDK版本的变化，这一方法的调用时机有哪些变化？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":13906,"link":"https://wanandroid.com/wenda/show/13906","niceDate":"2020-06-14 19:34","niceShareDate":"2020-06-14 19:33","origin":"","prefix":"","projectLink":"","publishTime":1592134458000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1592134434000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | 曾经的记忆中&ldquo;onSaveInstanceState 会在系统意外杀死 Activity 时调用&rdquo;，正确吗？","type":1,"userId":2,"visible":1,"zan":10}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IBaseArticleInfo {
        /**
         * apkLink :
         * audit : 1
         * author : 扔物线
         * canEdit : false
         * chapterId : 249
         * chapterName : 干货资源
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : true
         * id : 12554
         * link : https://www.bilibili.com/video/BV1fV411r7Lw
         * niceDate : 刚刚
         * niceShareDate : 2020-03-23 16:36
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1596211200000
         * realSuperChapterId : 248
         * selfVisible : 0
         * shareDate : 1584952597000
         * shareUser :
         * superChapterId : 249
         * superChapterName : 干货资源
         * tags : []
         * title : 我被 Kotlin 和 Android 两个官方约谈了
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<TagsBean> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        @Override
        public boolean isTop() {
            return true;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public long getShareDate() {
            return shareDate;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }


    }
}
