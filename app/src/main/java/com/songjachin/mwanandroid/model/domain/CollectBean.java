package com.songjachin.mwanandroid.model.domain;

import java.util.List;

/**
 * Created by matthew
 */
public class CollectBean {

    /**
     * data : {"curPage":1,"datas":[{"author":"鸿洋","chapterId":377,"chapterName":"优质内推","courseId":13,"desc":"","envelopePic":"","id":145228,"link":"https://wanandroid.com/blog/show/2775","niceDate":"刚刚","origin":"","originId":14338,"publishTime":1595306914000,"title":"字节跳动 「教育团队/抖音/懂车帝」 杭州/北京 岗位内推","userId":48273,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":1}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"author":"鸿洋","chapterId":377,"chapterName":"优质内推","courseId":13,"desc":"","envelopePic":"","id":145228,"link":"https://wanandroid.com/blog/show/2775","niceDate":"刚刚","origin":"","originId":14338,"publishTime":1595306914000,"title":"字节跳动 「教育团队/抖音/懂车帝」 杭州/北京 岗位内推","userId":48273,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 1
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean implements IBaseArticleInfo {
            /**
             * author : 鸿洋
             * chapterId : 377
             * chapterName : 优质内推
             * courseId : 13
             * desc :
             * envelopePic :
             * id : 145228
             * link : https://wanandroid.com/blog/show/2775
             * niceDate : 刚刚
             * origin :
             * originId : 14338
             * publishTime : 1595306914000
             * title : 字节跳动 「教育团队/抖音/懂车帝」 杭州/北京 岗位内推
             * userId : 48273
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
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

            public String getEnvelopePic() {
                return envelopePic;
            }

            @Override
            public boolean isFresh() {
                return false;
            }

            @Override
            public boolean isTop() {
                return false;
            }

            @Override
            public String getShareUser() {
                return null;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public int getId() {
                return id;
            }

            @Override
            public void setCollect(boolean b) {

            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            @Override
            public boolean isCollect() {
                return true;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            @Override
            public String getSuperChapterName() {
                return null;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            @Override
            public List<TagsBean> getTags() {
                return null;
            }

            public void setTitle(String title) {
                this.title = title;
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
        }
    }
}
