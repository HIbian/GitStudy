package bankSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Utils.BaseDao;

public class Cus {
	Scanner sc = new Scanner(System.in);
	BaseDao bd = new BaseDao();
	boolean go_on=true;
	//用户识别id
	int id;
	public Cus(int id) {
		this.id = id;
	}
	public void main_cus() {
		while(go_on) {
			showUI();
			switch (sc.nextInt()) {
			case 1:
				//存款
				addMoney();
				break;
			case 2:
				//取款
				deleteMoney();
				break;
			case 3:
				//查询余额
				leftMoney();
				break;
			case 4:
				//转账（使用事务）
				giveMoney();
				break;
			case 5:
				//修改密码
				changePass();
				break;
			case 6:
				//退出
				go_on=false;
				break;
			default:
				System.out.println("输入有误");
				break;
			}
		}
	}
	private void changePass() {
		System.out.println("输入原密码：");
		String oldPass = sc.next();
		while(!bd.QueryisIn("select * from customer where pass=? and id =?", oldPass,id)) {
			System.out.println("密码输入错误，重新输入：");
			oldPass = sc.next();
		}
		System.out.println("输入新密码：");
		String newPass = sc.next();
		while(newPass.length()!=6) {
			System.out.println("长度不为6,重新输入：");
			newPass = sc.next();
		}
		System.out.println("确认新密码：");
		String newPass_confirm = sc.next();
		while(!newPass.equals(newPass_confirm)) {
			System.out.println("两次输入不一致,重新输入：");
			newPass_confirm = sc.next();
		}
		//设定新密码
		if (bd.Update("update customer set pass = ? where id =?", newPass,id)==1) {
			System.out.println("设定成功");
		}else {
			System.out.println("设定失败");
		}
	}
	private void giveMoney() {
		System.out.println("输入转入账户银行卡号：");
		String number = sc.next();
		//判断是否存在
		while(!bd.QueryisIn("select * from customer where number=?", number)) {
			System.out.println("卡号不存在，重新输入：");
			number = sc.next();
		}
		//转账金额
		System.out.println("输入转账金额:");
		double money = sc.nextDouble();
		try {
			ResultSet rs = bd.Query("select money from customer where Id = ?", id);
			rs.next();
			double havingMoney = rs.getDouble(1);
			while(havingMoney<money) {
				System.out.println("账户金额不足，重新输入：");
				money = sc.nextDouble();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转入指定账户
		if (bd.giveMoney(id, number, money)) {
			System.out.println("转账成功");
		}else {
			System.out.println("转账失败");
		}
	}
	private void leftMoney() {
		ResultSet rs = bd.Query("select money from customer where Id=?", id);
		try {
			rs.next();
			System.out.println("当前余额为："+rs.getDouble(1));
		} catch (SQLException e) {
		}
	}
	private void deleteMoney() {
		System.out.println("输入取款金额");
		double add = sc.nextDouble();
		if(bd.Update("update customer set money=money-? where Id =? and money>=?", add,id,add)==1) {
			System.out.println("取款成功");
		}else {
			System.out.println("取款失败");
		}
	}
	private void addMoney() {
		System.out.println("输入存款金额");
		double add = sc.nextDouble();
		if(bd.Update("update customer set money=money+? where Id =?", add,id)==1) {
			System.out.println("存入成功");
		}else {
			System.out.println("存入失败");
		}
	}
	
	private void showUI() {
		System.out.println("银行系统【用户】");
		System.out.println("**********************************************");
		System.out.println("1.存款\t2.取款\t3.查询余额\t4.转账\t5.修改密码\t6.退出");
		System.out.println("**********************************************");
	}
}
