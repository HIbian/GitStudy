//import java.util.Arrays;

public class DogManager {
	private Dog[] dm = null;
	private int count =0;//记录当前狗的数量
	public DogManager(int size) {
		 if (size>0) {
		 	dm = new Dog[size];
		 }else {
		 	dm = new Dog[5];
		}
	}
	//添加
	public boolean add(Dog dog) {
		if (count==dm.length) {
			//创建新的空间
			Dog[] newDog = new Dog[dm.length*2];
			//复制对象
			for (int i = 0; i < dm.length; i++) {
				newDog[i] = dm[i];
			}
			//改变引用地址
			dm = newDog;
			System.out.println("添加了新的狗舍，大小为"+dm.length);
			
//			方法二
//			int newLength = dm.length*2;
//			dm = Arrays.copyOf(dm, newLength);

		}
		if (find(dog.getId())!=null) {
			return false;
		}
		dm[count]=dog;
		count++;
		return true;
	}
	//删除
	public boolean delete(int id) {
		int index= getOrderById(id);
		if (index==-1) {
			return false;
		}
		for (int i = index; i < count; i++) {
			if (i<dm.length-1) {
				dm[i]=dm[i+1];
			}else {
				dm[i]=null;
			}
		}
		count--;
		dm[count]=null;
		return true;
	}
	
	//更新 三种重载
//	public void update(int id,String name) {
//		int index = getOrderById(id);
//		if (index==-1) {
//			return;
//		}
//		dm[index].setName(name);
//		return;
//	}
//	public void update(int id,int age) {
//		int index = getOrderById(id);
//		if (index==-1) {
//			return;
//		}
//		dm[index].setAge(age);
//		return;
//	}
	public boolean update(int id,String name,int age) {
		int index = getOrderById(id);
		if (index==-1) {
			return false;
		}
		dm[index].setName(name);
		dm[index].setAge(age);
		return true;
	}
	
	//查询
	public Dog find(int id) {
		for (int i = 0; i < count; i++) {
			if (dm[i].getId()==id) {
				return dm[i];
			}
		}
		return null;
	}
	//打印
	public void print() {
		for (int i = 0; i < count; i++) {
			System.out.println("id:"+dm[i].getId()+"\tname:"+dm[i].getName()+"\tage:"+dm[i].getAge());
		}
	}
	//通过id获得order
	public int getOrderById(int id) {
		for (int i = 0; i < count; i++) {
			if (dm[i].getId()==id) {
				return i;
			}
		}
		return -1;
	}
	//打印狗的信息
	public void PrintSingleDog(int id) {
		int index = getOrderById(id);
		System.out.println("id:"+dm[index].getId()+"\tName:"+dm[index].getName()+"\tAge:"+dm[index].getAge());
	}
}
