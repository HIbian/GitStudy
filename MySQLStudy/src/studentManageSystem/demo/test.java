package studentManageSystem.demo;

public class test {
	public static void main(String[] args) {
		StudentManager smg = new StudentManager();
		Student s1 = new Student(80, "杜甫", 500, '男');
		//添加
//		System.out.println(smg.add(s1));
		//删除
//		System.out.println(smg.delete(s1));
		//修改
//		System.out.println(smg.updata(new Student(89, "老崔", 39, '男'), s1));
		//查询
		System.out.println(smg.query(new Student(811, "老崔", 39, '男')));
		//打印表
		smg.print();
	}
}
