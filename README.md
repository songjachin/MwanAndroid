# MwanAndroid
这是根据wanAndroid API开发的一个Android程序，项目采用MVP的架构，主要功能包括有登录登出、轮播图的实现、搜索查询、体系导航、收藏和阅读历史、⽹⻚浏览、搜素历史缓存、⻚⾯懒加载等

## 主界面

主界面是一个MainActivity，功能主要是切换页面，包含有5个Fragment，采用FragmentTransactiond的add()、show()、hide()来增添Fragment

HomeFragment主要展示的是轮播图和置顶相关和本站发布的每日问答

![HomeFragment](F:\PNG_Picture\2021-03-15-16-00-51.png)

TalkFragment展示的是所有问答和讨论的集合

![2021-03-15-16-00-18](F:\PNG_Picture\2021-03-15-16-00-18.png)
NavigationFragment包括三个次级Fragment：一个是总站所有内容体系的地址目录

![2021-03-15-16-00-22](F:\PNG_Picture\2021-03-15-16-00-22.png)

KnowledgeFragment，二是对常用网站地址的导航NavigationChildFragment，三是所有项目的地址导航ProjectFragment

WxFragment采用Tablayout+ViewPager绑定的形式创建多个子Fragment，在FragmentPagerAdapter里创建提供多个newInstance、

![2021-03-15-16-00-30](F:\PNG_Picture\2021-03-15-16-00-30.png)

MineFragment主要包含个人积分展示、收藏和阅读历史