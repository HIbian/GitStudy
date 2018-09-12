package bankSystem;

import java.util.Scanner;

import Dao.login;

public class BankSystem {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		login login = new login();
		while (true) {
			System.out.println("---------------------------银行系统---------------------------");
			System.out.println("                         ******1.管理员*******");
			System.out.println("                         ******2.顾客*********");
			System.out.println("---------------------------------------------------------------");
			switch (sc.nextInt()) {
			case 1:
				//管理员登陆
				if (login.log_admin()) {
					//管理员界面
					Admin admin = new Admin();
					admin.main_Admin();
				}else {
					System.out.println("账号或密码错误");
				}
				break;
			case 2:
				//顾客登陆
				int cusId =login.log_user();
				if (cusId!=-1) {
					//顾客界面
					Cus c = new Cus(cusId);
					c.main_cus();
				}else {
					System.out.println("账号或密码错误");
				}
				break;
			default:
				System.out.println("输入有误，请重新输入");
				break;
			}
		}
	}
}
