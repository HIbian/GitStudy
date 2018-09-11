package com.chen.movieSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chen.DAO.movieDao;
import com.chen.vo.Movie;
import com.chen.vo.StuTicket;
import com.chen.vo.Ticket;

public class Client {
	Scanner sc = new Scanner(System.in);
	movieDao md =  new movieDao();
	String movieName = null;//电影名字
	String movieTime = null;//电影时间
	String seat = null;//座位号
	Movie m = null;
	//显示电影列表
	public void showMovieList() {
		System.out.println("--------------------欢迎来到XXXX电影院--------------------");
		ResultSet rs = md.getMovieSet();
		try {
			System.out.println("序号\t"+"电影名      \t"+"英文名\t"+"导演\t"+"类型\t"+"价格\t"+"时间\t");
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					System.out.print(rs.getObject(i)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//显示座位图
	public void showSeats() {
		System.out.println("======================座位表======================");
		for (int i = 1; i <=5; i++) {
			for (int j = 1; j <=7; j++) {
				String seat = i+"-"+j;
				System.out.print(seat+"\t");
			}
			System.out.println();
		}
	}
	//输入电影名字
	public void inputMovieNameAndTime() {
		
		System.out.println("请输入电影名字：");
		String movieName = sc.next();
		while (!md.isName(movieName)) {
			System.out.println("请重新输入电影名字：");
			movieName = sc.next();
		}
		this.movieName = movieName;
		//-------------------------------------
		System.out.println("请输入电影时间：");
		String movieTime = sc.next();
		while (!md.isTime(movieTime)) {
			System.out.println("请重新输入电影时间：");
			movieTime = sc.next();
		}
		this.movieTime = movieTime;	
	}
	//获取电影信息
	public void movieInfo() {
		m = md.getMovie(movieName, movieTime);
	}
	//输入票的类型
	public void inputTicketType() {
		System.out.println("==============================");
		System.out.println("=         1.普通票           =");
		System.out.println("=         2.学生票           =");
		System.out.println("=         3.赠送票           =");
		System.out.println("==============================");
		System.out.println("输入票的类型：");
		int tType = sc.nextInt();
		switch (tType) {
		case 1:
			Ticket t = new Ticket(movieName, movieTime, seat, m.getPrice());
			t.show();
			t.print();
			break;
		case 2:
			System.out.println("请输入折扣");
			double count  = sc.nextDouble();
			StuTicket st = new StuTicket(movieName, movieTime, seat, m.getPrice(), count);
			st.show();
			st.print();
			break;
		case 3:
			System.out.println("请输入赠送人");
			String gifter  = sc.next();
			FreeTicket ft = new FreeTicket(movieName, movieTime, seat, m.getPrice(), gifter);
			ft.show();
			ft.print();
			break;
		}
		sc.close();
	}
	//输入座位号
	public void inputSeat() {
		System.out.println("请输入座位号：");
		String seat = sc.next();
		//正则判断
		Pattern ptn = Pattern.compile("[1-5]-[1-7]");
		Matcher matcher = ptn.matcher(seat);
		//判断是否存在
		while (!matcher.matches()||md.isexist(m.getId(),seat)) {
			System.out.println("请重新输入座位号：");
			seat = sc.next();
			matcher = ptn.matcher(seat);
		}
		//添加座位
		md.addSeat(m.getId(), seat);
		this.seat = seat;
	}
	public static void main(String[] args) {
		Client c = new Client();
		//显示电影列表
		c.showMovieList();
		//显示座位图
		c.showSeats();
		//输入电影名字和时间
		c.inputMovieNameAndTime();
//		c.movieInfo();
		//输入座位号
		c.inputSeat();
		//输入类型
		c.inputTicketType();
	}
}
