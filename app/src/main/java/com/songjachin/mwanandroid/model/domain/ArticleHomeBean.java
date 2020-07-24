package com.songjachin.mwanandroid.model.domain;

import java.util.List;

/**
 * Created by matthew
 */
public class ArticleHomeBean {
    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14246,"link":"https://juejin.im/post/5e1b43f6e51d454d94422502","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594347499000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594347499000,"shareUser":"躬行之","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"我的2019复盘","type":0,"userId":23270,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14252,"link":"https://mp.weixin.qq.com/s/0GVrEKICUK5kL96EudzhFQ","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395205000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"深入理解布局约束 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14253,"link":"https://mp.weixin.qq.com/s/c-L7-KkEDplbIkOcaogoRA","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395221000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"那些初学者实践 Flutter 更常出现的错误","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14254,"link":"https://mp.weixin.qq.com/s/psrDADxwl782Fbs_vzxnQg","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395237000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android UI 渲染机制的演进，你需要了解什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14233,"link":"https://mp.weixin.qq.com/s/5k00vaDGCd7zlj1z-EIGSg","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594259878000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594259878000,"shareUser":"飞洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Activity启动流程源码分析，基于Android10，进阶必备～","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14240,"link":"https://mp.weixin.qq.com/s/D86krymBKK02zGEPRjV09g","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306150000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"全方面分析 Hilt 和 Koin 性能","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14241,"link":"https://mp.weixin.qq.com/s/0ehKdZdunsuU94aGaHifyg","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306172000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"这交互炸了系列： 仿微信键盘弹出体验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14242,"link":"https://mp.weixin.qq.com/s/zfLQSKScMI-1eI0KlgXyzQ","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306194000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"自定义TabLayout，神奇效果竟是这么简单！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":423,"chapterName":"Architecture","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14215,"link":"https://juejin.im/post/5f04935af265da22ce392fe4","niceDate":"2天前","niceShareDate":"2020-07-08 10:20","origin":"","prefix":"","projectLink":"","publishTime":1594222395000,"realSuperChapterId":422,"selfVisible":0,"shareDate":1594174833000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"从源码看 Jetpack（1） -Lifecycle源码解析","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":484,"chapterName":"okhttp","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14214,"link":"https://juejin.im/post/5f0452615188252e5522b747","niceDate":"2天前","niceShareDate":"2020-07-08 08:52","origin":"","prefix":"","projectLink":"","publishTime":1594222378000,"realSuperChapterId":460,"selfVisible":0,"shareDate":1594169564000,"shareUser":"goweii","superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"雨露均沾的OkHttp&mdash;WebSocket长连接（使用篇）","type":0,"userId":20382,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":185,"chapterName":"组件化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14224,"link":"https://juejin.im/post/5f03ca85f265da22b1369069","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594222325000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1594221262000,"shareUser":"鸿洋","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"Android组件化问题思考","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":455,"chapterName":"反射","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14225,"link":"https://www.jianshu.com/p/d040c7f1a46f","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594222269000,"realSuperChapterId":244,"selfVisible":0,"shareDate":1594221963000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"震惊！他竟然把反射用得这么优雅！","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很多时候，我们会在编译期间，利用 Transform ，通过 javassist,asm等在一些类的方法中插入一些代码逻辑。<\/p>\r\n<p>插入代码之后，如果该类在线上发生异常崩溃，发生异常的方法调用所对应的行号，在Java源文件中还能对应上吗？<\/p>\r\n<p>如果对应不上，那么有什么办法可以让其对应上呢？<\/p>\r\n<blockquote>\r\n<p>本题难度 5 颗星。<\/p>\r\n<\/blockquote>","descMd":"","envelopePic":"","fresh":false,"id":14081,"link":"https://www.wanandroid.com/wenda/show/14081","niceDate":"2天前","niceShareDate":"2020-06-29 21:26","origin":"","prefix":"","projectLink":"","publishTime":1594220730000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1593437161000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | 插桩之后，异常堆栈还能正确的定位到代码行吗？","type":0,"userId":2,"visible":1,"zan":3},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14219,"link":"https://juejin.im/post/5eb152c7e51d454dac4b792e","niceDate":"2020-07-08 15:47","niceShareDate":"2020-07-08 15:47","origin":"","prefix":"","projectLink":"","publishTime":1594194421000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594194421000,"shareUser":"无伤大雅的你呀","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android自定义View之滑块验证码","type":0,"userId":31023,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14218,"link":"https://juejin.im/post/5e68cd72f265da571a39e29d","niceDate":"2020-07-08 15:45","niceShareDate":"2020-07-08 15:45","origin":"","prefix":"","projectLink":"","publishTime":1594194334000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594194334000,"shareUser":"无伤大雅的你呀","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"浅谈Android动画的那些事","type":0,"userId":31023,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14213,"link":"https://juejin.im/post/5f03ef91f265da22df3ccc5a","niceDate":"2020-07-08 04:13","niceShareDate":"2020-07-08 04:13","origin":"","prefix":"","projectLink":"","publishTime":1594152817000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594152817000,"shareUser":"KunMinX","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"是让人 提神醒脑 的 MVP、MVVM 关系精讲！","type":0,"userId":12482,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14226,"link":"https://mp.weixin.qq.com/s/zxqGjX5ZGoQqHXCBtU-VjQ","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222718000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"在 CI 中使用 Benchmark 进行回归分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14227,"link":"https://mp.weixin.qq.com/s/bhECYxxqlE5FsIRmKdAIWg","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222738000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Jetpack 更新成员 AndroidX App Startup 实践以及原理分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14228,"link":"https://mp.weixin.qq.com/s/D0MJtIR_dfh-1i0qjz7ZGw","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222759000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"LiveData vs EventBus？不，他们其实可以一起","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":442,"size":20,"total":8830}
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
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14246,"link":"https://juejin.im/post/5e1b43f6e51d454d94422502","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594347499000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594347499000,"shareUser":"躬行之","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"我的2019复盘","type":0,"userId":23270,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14252,"link":"https://mp.weixin.qq.com/s/0GVrEKICUK5kL96EudzhFQ","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395205000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"深入理解布局约束 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14253,"link":"https://mp.weixin.qq.com/s/c-L7-KkEDplbIkOcaogoRA","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395221000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"那些初学者实践 Flutter 更常出现的错误","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14254,"link":"https://mp.weixin.qq.com/s/psrDADxwl782Fbs_vzxnQg","niceDate":"1天前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1594310400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594395237000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android UI 渲染机制的演进，你需要了解什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14233,"link":"https://mp.weixin.qq.com/s/5k00vaDGCd7zlj1z-EIGSg","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594259878000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594259878000,"shareUser":"飞洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Activity启动流程源码分析，基于Android10，进阶必备～","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14240,"link":"https://mp.weixin.qq.com/s/D86krymBKK02zGEPRjV09g","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306150000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"全方面分析 Hilt 和 Koin 性能","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14241,"link":"https://mp.weixin.qq.com/s/0ehKdZdunsuU94aGaHifyg","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306172000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"这交互炸了系列： 仿微信键盘弹出体验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14242,"link":"https://mp.weixin.qq.com/s/zfLQSKScMI-1eI0KlgXyzQ","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1594224000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594306194000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"自定义TabLayout，神奇效果竟是这么简单！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":423,"chapterName":"Architecture","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14215,"link":"https://juejin.im/post/5f04935af265da22ce392fe4","niceDate":"2天前","niceShareDate":"2020-07-08 10:20","origin":"","prefix":"","projectLink":"","publishTime":1594222395000,"realSuperChapterId":422,"selfVisible":0,"shareDate":1594174833000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"从源码看 Jetpack（1） -Lifecycle源码解析","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":484,"chapterName":"okhttp","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14214,"link":"https://juejin.im/post/5f0452615188252e5522b747","niceDate":"2天前","niceShareDate":"2020-07-08 08:52","origin":"","prefix":"","projectLink":"","publishTime":1594222378000,"realSuperChapterId":460,"selfVisible":0,"shareDate":1594169564000,"shareUser":"goweii","superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"雨露均沾的OkHttp&mdash;WebSocket长连接（使用篇）","type":0,"userId":20382,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":185,"chapterName":"组件化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14224,"link":"https://juejin.im/post/5f03ca85f265da22b1369069","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594222325000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1594221262000,"shareUser":"鸿洋","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"Android组件化问题思考","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":455,"chapterName":"反射","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14225,"link":"https://www.jianshu.com/p/d040c7f1a46f","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594222269000,"realSuperChapterId":244,"selfVisible":0,"shareDate":1594221963000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"震惊！他竟然把反射用得这么优雅！","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很多时候，我们会在编译期间，利用 Transform ，通过 javassist,asm等在一些类的方法中插入一些代码逻辑。<\/p>\r\n<p>插入代码之后，如果该类在线上发生异常崩溃，发生异常的方法调用所对应的行号，在Java源文件中还能对应上吗？<\/p>\r\n<p>如果对应不上，那么有什么办法可以让其对应上呢？<\/p>\r\n<blockquote>\r\n<p>本题难度 5 颗星。<\/p>\r\n<\/blockquote>","descMd":"","envelopePic":"","fresh":false,"id":14081,"link":"https://www.wanandroid.com/wenda/show/14081","niceDate":"2天前","niceShareDate":"2020-06-29 21:26","origin":"","prefix":"","projectLink":"","publishTime":1594220730000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1593437161000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | 插桩之后，异常堆栈还能正确的定位到代码行吗？","type":0,"userId":2,"visible":1,"zan":3},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14219,"link":"https://juejin.im/post/5eb152c7e51d454dac4b792e","niceDate":"2020-07-08 15:47","niceShareDate":"2020-07-08 15:47","origin":"","prefix":"","projectLink":"","publishTime":1594194421000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594194421000,"shareUser":"无伤大雅的你呀","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android自定义View之滑块验证码","type":0,"userId":31023,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14218,"link":"https://juejin.im/post/5e68cd72f265da571a39e29d","niceDate":"2020-07-08 15:45","niceShareDate":"2020-07-08 15:45","origin":"","prefix":"","projectLink":"","publishTime":1594194334000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594194334000,"shareUser":"无伤大雅的你呀","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"浅谈Android动画的那些事","type":0,"userId":31023,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14213,"link":"https://juejin.im/post/5f03ef91f265da22df3ccc5a","niceDate":"2020-07-08 04:13","niceShareDate":"2020-07-08 04:13","origin":"","prefix":"","projectLink":"","publishTime":1594152817000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1594152817000,"shareUser":"KunMinX","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"是让人 提神醒脑 的 MVP、MVVM 关系精讲！","type":0,"userId":12482,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14226,"link":"https://mp.weixin.qq.com/s/zxqGjX5ZGoQqHXCBtU-VjQ","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222718000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"在 CI 中使用 Benchmark 进行回归分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14227,"link":"https://mp.weixin.qq.com/s/bhECYxxqlE5FsIRmKdAIWg","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222738000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Jetpack 更新成员 AndroidX App Startup 实践以及原理分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":14228,"link":"https://mp.weixin.qq.com/s/D0MJtIR_dfh-1i0qjz7ZGw","niceDate":"2020-07-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1594137600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1594222759000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"LiveData vs EventBus？不，他们其实可以一起","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 442
         * size : 20
         * total : 8830
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
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 502
             * chapterName : 自助
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : false
             * id : 14246
             * link : https://juejin.im/post/5e1b43f6e51d454d94422502
             * niceDate : 1天前
             * niceShareDate : 1天前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1594347499000
             * realSuperChapterId : 493
             * selfVisible : 0
             * shareDate : 1594347499000
             * shareUser : 躬行之
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : 我的2019复盘
             * type : 0
             * userId : 23270
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

            @Override
            public int getOriginId() {
                return -1;
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
                return false;
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
}
