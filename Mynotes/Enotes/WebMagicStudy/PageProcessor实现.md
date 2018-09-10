### 实现PageProcessor接口
需要重写两个方法

```java
//定制爬虫逻辑的核心接口，在这里编写抽取逻辑
public void process(Page page);
//返回配置信息
public Site getSite();
```

* 抓取网站相关配置编码，抓取间隔，重试次数
* 定义如何抽取网页信息
* 抓取发现的后续地址
