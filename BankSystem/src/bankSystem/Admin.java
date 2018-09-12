package bankSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

import Utils.BaseDao;
import vo.customer;

public class Admin {
	Scanner sc = new Scanner(System.in);
	BaseDao bd = new BaseDao();
	boolean go_on = true;
	
	public void main_Admin() {
		while (go_on) {
			showUI();
			switch (sc.nextInt()) {
			case 1:
				//添加顾客
				addUser();
				break;
			case 2:
				//计算储蓄总额
				calcuTotal();
				break;
			case 3:
				//排行榜
				userSort();
				break;
			case 4:
				//退出
				go_on=false;
				break;
			default:
				System.out.println("输入有误，请重新输入");
				break;
			}
		}
	}
	private void userSort() {
		ArrayList<customer> cs = new ArrayList<customer>();
		//获取resultSet
		ResultSet rs = bd.Query("select * from customer");
		try {
			//添加到list中
			while (rs.next()) {
				cs.add(new customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getTimestamp(7)));
			}
		} catch (SQLException e) {
		}
		//排序
		cs.sort(new Comparator<customer>() {
			public int compare(customer o1, customer o2) {
				return (int)(o2.getMoney()-o1.getMoney());
			}
		});
		//输出
		for (int i = 0; i < cs.size(); i++) {
			System.out.println((i+1)+"\t"+cs.get(i).getName()+"\t\t"+cs.get(i).getIdcard()+"\t"+cs.get(i).getMoney());
		}
	}
	@Test
	private void calcuTotal() {
		//获取resultSet
		double sum = 0;
		ResultSet rs = bd.Query("select * from customer");
		try {
			while (rs.next()) {
				sum+=rs.getDouble(6);
			}
		} catch (SQLException e) {
		}
		System.out.println("储蓄总额为："+sum);
	}
	private void addUser() {
		System.out.println("输入卡号：");
		String number = sc.next();
		System.out.println("输入用户名：");
		String name = sc.next();
		System.out.println("输入身份证：");
		String idcard = sc.next();
		System.out.println("输入开户金额");
		String money = sc.next();
		System.out.println("输入初始密码：");
		String pass = sc.next();
		Timestamp time = new Timestamp(new Date().getTime());
		if (bd.Update("insert into customer values (null,?,?,?,?,?,?)", number,name,pass,idcard,money,time)==1) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
	}
	private void showUI() {
		System.out.println("银行系统【管理员】");
		System.out.println("**********************************************");
		System.out.println("1.添加顾客\t2.计算储蓄总额\t3.土豪排行榜\t4.退出");
		System.out.println("**********************************************");
	}
}
