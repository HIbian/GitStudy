package com.chen.movieSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.chen.DAO.movieDao;

public class SystemManager {
	movieDao md = new movieDao();
	public void runManager() {
		boolean go_on = true;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (go_on) {
			System.out.println("===========管理员界面==========");
			System.out.println("=          1.添加电影         =");
			System.out.println("=          2.删除电影         =");
			System.out.println("=          3.修改电影         =");
			System.out.println("=          4.打印电影         =");
			System.out.println("=          5.退出系统         =");
			System.out.println("=============================+");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("1/6输入电影名：");
				String name = sc.next();
				System.out.println("2/6输入电影英文名：");
				String ename = sc.next();
				System.out.println("3/6输入导演：");
				String actor = sc.next();
				System.out.println("4/6输入类型：");
				String type = sc.next();
				System.out.println("5/6输入价格：");
				double price = sc.nextDouble();
				System.out.println("6/6输入日期：");
				String date = sc.next();
				if (md.addMove(name, ename, actor, type, price, date)) {
					System.out.println("添加成功");
				} else {
					System.out.println("添加失败");
				}

				break;
			case 2:
				System.out.println("输入电影id：");
				int id = sc.nextInt();
				if (md.deleteMove(id)) {
					System.out.println("删除成功");
				} else {
					System.out.println("删除失败");
				}
				break;
			case 3:
				System.out.println("输入电影id：");
				int id3 = sc.nextInt();
				//判断是否存在这个电影
				
				System.out.println("1/6输入电影名：");
				String name3 = sc.next();
				System.out.println("2/6输入电影英文名：");
				String ename3 = sc.next();
				System.out.println("3/6输入导演：");
				String actor3 = sc.next();
				System.out.println("4/6输入类型：");
				String type3 = sc.next();
				System.out.println("5/6输入价格：");
				double price3 = sc.nextDouble();
				System.out.println("6/6输入日期：");
				String date3 = sc.next();
				if (md.updateMove(id3, name3, ename3, actor3, type3, price3, date3)) {
					System.out.println("跟新成功");
				} else {
					System.out.println("跟新失败");
				}
				break;
			case 4:
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
				break;
			case 5:
				go_on = false;
				break;
			default:
				break;
			}
		}
	}
}
