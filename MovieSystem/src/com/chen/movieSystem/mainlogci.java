package com.chen.movieSystem;

import java.util.Scanner;

import com.chen.DAO.loginDao;

public class mainlogci {
	
	public static void main(String[] args) {
		//登陆,管理员管理电影，服务员售票
		switch (inputAccout()) {
		case 0:
			System.out.println("售票员登陆");
			Client c = new Client();
			//显示电影列表
			c.showMovieList();
			//显示座位图
			c.showSeats();
			//输入电影名字和时间
			c.inputMovieNameAndTime();
			//获取电影信息
			c.movieInfo();
			//输入座位号
			c.inputSeat();
			//输入类型
			c.inputTicketType();
			break;
		case 1:
			System.out.println("管理员登陆");
			SystemManager sm = new SystemManager();
			sm.runManager();
			break;
		case -1:
			System.out.println("账号或密码有误");
			break;
		}
		
		
	}
	private static int inputAccout() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------登陆界面----------------");
		System.out.println("请输入账号：");
		String username = sc.next();
		System.out.println("请输入密码：");
		String password = sc.next();
//		sc.close();
		loginDao login = new loginDao(username,password);
		boolean islogin = login.login();
		if (!islogin) {
			return -1;
		}
		return login.getRight();
	}
}
