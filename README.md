
# android-app-architecture-demo
[参照google android-architecture](https://github.com/googlesamples/android-architecture) 
通过查看天气这个案例来研究一下MVP、MVVM架构，包括Rxjava等组件。

| Sample | Description |
| ------------- | ------------- |
| [mvp](https://github.com/zhhp1121/android-app-architecture-demo/tree/mvp/) |MVP架构 |
| [mvvm-databinding](https://github.com/zhhp1121/android-app-architecture-demo/tree/mvp/) |MVVM架构 |
| [mvp-rxjava](https://github.com/zhhp1121/android-app-architecture-demo/tree/mvp-rxjava/) |MVP-rxjava ---等待完成 |

[天气API使用雅虎提供的免费接口](https://www.yahoo.com/news/weather/)
解析这个html，通过woid字段信息查找到当前ip所对应的城市，然后进一步查询此城市的天气数据。
HTTP Client使用[OKHTTP](https://github.com/square/okhttp),[Retrofit](https://github.com/square/retrofit)

# 使用Android studio打开

Clone the repository:

```
git clone https://github.com/zhhp1121/android-app-architecture-demo
```

Checkout the todo-mvp sample:
```
git checkout mvp
```

如果要查看mvvm

```
git checkout mvvm
```
