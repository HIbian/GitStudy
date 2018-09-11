package test.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PageProcess实现方法 implements PageProcessor{
	private static int count =123422;
	//设置网站相关配置
	private Site site = Site.me().setRetryTimes(5).setSleepTime(0).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");//重试次数和抓取间隔
	public void process(Page page) {
		String title = page.getHtml().xpath("//h1/span/text()").get();
		String time = page.getHtml().xpath("//div/time/text()").get();
		intoSql(title, time);
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
	public static void intoSql(String title,String time) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql="insert into biliinfo values (null,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, count);
			ps.setString(2, title);
			ps.setString(3, time);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
