package test.t01;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class test01 implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");

    public void process(Page page) {
    	//继续请求的地址
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }
    
    //返回站点设置
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//    	BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境。
    	String[] kStrings = new String[1];
    	kStrings[0] = "https://github.com/HIbian";
        Spider.create(new test01()).addUrl(kStrings).thread(5).run();
    }

}
