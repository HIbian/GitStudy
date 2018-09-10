package test.t01;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PageProcess实现方法 implements PageProcessor{
	private static int count =123422;
	//设置网站相关配置
	private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");//重试次数和抓取间隔
	public void process(Page page) {
		System.out.println(page.getHtml().xpath("//h1/span/text()").get());
	}
	public Site getSite() {
		return site;
	}
	public static void main(String[] args) {
			while (count<130000) {
				Spider.create(new PageProcess实现方法()).addUrl("https://www.bilibili.com/video/av" + count + "/").thread(1).run();
				count++;
			}
	}
}
