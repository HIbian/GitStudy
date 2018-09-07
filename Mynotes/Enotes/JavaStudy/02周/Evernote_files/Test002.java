import java.util.Scanner;

public class Test002{
	public static void main(String[] args) {
		DogManager dManager = new DogManager(5);
		int num =0;
		boolean flag = true;
		
		Scanner scanner = new Scanner(System.in);
		int id;
		String name;
		int age;
		
		System.out.println("-----------------------------------------");
		System.out.println("|          欢迎使用狗舍管理系统         |");
		System.out.println("|          1.添加小狗                   |");
		System.out.println("|          2.删除小狗                   |");
		System.out.println("|          3.修改小狗信息               |");
		System.out.println("|          4.查询小狗                   |");
		System.out.println("|          5.打印所有小狗               |");
		System.out.println("|          0.退出                       |");
		System.out.println("-----------------------------------------");
		
		while (flag) {
			num = scanner.nextInt();
			switch (num) {
			case 1:
				System.out.println("输入id");
				id = scanner.nextInt();
				System.out.println("输入名字");
				name = scanner.next();
				System.out.println("输入年龄");
				age = scanner.nextInt();
				if (dManager.add(new Dog(id, name, age))) {
					System.out.println("添加成功");
					break;
				}
				System.out.println("添加失败");
				break;
			case 2:
				System.out.println("输入id：");
				id = scanner.nextInt();
				if (dManager.delete(id)) {
					System.out.println("删除成功");
					break;
				}
				System.err.println("删除失败");
				break;
			case 3:
				System.err.println("输入id:");
				id = scanner.nextInt();
				System.out.println("输入新的名字：");
				name = scanner.next();
				System.out.println("输入新的年龄：");
				age = scanner.nextInt();
				if (dManager.update(id, name, age)) {
					System.out.println("修改成功");
					break;
				}
				System.out.println("修改失败");
				break;
			case 4:
				System.err.println("输入id");
				id = scanner.nextInt();
				if (dManager.find(id)==null) {
					System.out.println("这里没有这只狗");
					break;
				}
				dManager.PrintSingleDog(id);
				break;
			case 5:
				dManager.print();
				break;
			case 0:
				System.out.println("感谢使用，再见！");
				flag = false;
				break;

			default:
				break;
			}
		}
		scanner.close();
	}
}