package studentManageSystem.demo;

import java.sql.SQLException;

public interface IManager<T>{
	//添加
	boolean add(T t) throws SQLException;
	//删除
	boolean delete(T t);
	//修改
	boolean updata(T t,T n_t);
	//查询
	int query(T t);
	//打印
	void print() throws SQLException;
}
