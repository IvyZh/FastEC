# FastEC
init

https://github.com/YoKeyword/Fragmentation


### 引入ButterKnife 8.8.1报错

解决参考链接：https://blog.csdn.net/p576518762/article/details/78356137
查阅了很久发现其实是Android Studio3.0与butterknife的冲突，github上面已经有了这个问题issue，JakeWharton大神也给了相关解释，暂时的解决方法是降至8.4.
0的版本。


### AVLoadingIndicatorView

在4.4的模拟器上面没有动画效果，参考这篇文章：https://blog.csdn.net/developer_jiangqq/article/details/49612399

加入 compile'com.nineoldandroids:library:2.4.0'   试试

需要设置大小