package com.hr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hr.bean.User;
import com.hr.utils.C3P0Utils;

public class UserDaoImpl implements UserDao{
	// 将数据源传递给QueryRunner，QueryRunner内部通过数据源获取数据库连接
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
	public static void main(String[] args) {
		UserDaoImpl d = new UserDaoImpl();
//		System.out.println(d.getTotalCount(new User()));
		List<User> userPage = d.getUserPage(new User(), 1, 5);
		for (User user : userPage) {
			System.out.println(user);
		}
	}
	
	@Override
	public int getTotalCount(User u) {
		//获取查询的两个条件
		String userName = u.getUserName();
		//states -1查询全部 0禁用 1启用
		int state = u.getState();
		//sql字符串
		StringBuffer sql = new StringBuffer("select * from t_user where delState=0");
		//创建存放sql参数的list
		List<Object> parms = new ArrayList<Object>();
		if (userName!=null) {
			sql.append(" and userName like ?");
			parms.add("%"+userName+"%");
		}
		//查询启用或禁用的user
		if (state==1||state==0) {
			sql.append(" and state =?");
			parms.add(state);
		}
		//执行查询语句
		List<User> users = null;
		try {
			users = qr.query(sql.toString(), new BeanListHandler<User>(User.class),parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users.size();
	}

	@Override
	public List<User> getUserPage(User u, int pageIndex, int pageSize) {
		//获取查询的两个条件
		String userName = u.getUserName();
		//states -1查询全部 0禁用 1启用
		int state = u.getState();
		//sql字符串
		StringBuffer sql = new StringBuffer("select * from t_user where delState=0");
		//创建存放sql参数的list
		List<Object> parms = new ArrayList<Object>();
		if (userName!=null) {
			sql.append(" and userName like ?");
			parms.add("%"+userName+"%");
		}
		//查询启用或禁用的user
		if (state==1||state==0) {
			sql.append(" and state =?");
			parms.add(state);
		}
		//添加分页条件
		sql.append(" limit ?,?");
		parms.add((pageIndex-1)*pageSize);//查询的起始位置
		parms.add(pageSize);
		//执行查询语句
		List<User> users = null;
		try {
			users = qr.query(sql.toString(), new BeanListHandler<User>(User.class),parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	//添加User
	@Override
	public int addUser(User u) {
		String userLoginName = u.getUserLoginName();
		String userName = u.getUserName();
		String userPwd = u.getUserPwd();
		String createTime = u.getCreateTime();
		try {
			return qr.update("insert into t_user values(null,?,?,?,1,?,0)",userLoginName,userPwd,userName,createTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateUser(User u) {
		int userId = u.getUserId();
		String userLoginName = u.getUserLoginName();
		String userName = u.getUserName();
		String userPwd = u.getUserPwd();
		String createTime = u.getCreateTime();
		int state = u.getState();
		int delState = u.getDelState();
		try {
			return qr.update("update  t_user "
					+ "set userLoginName=?,userName=?,userPwd=?,createTime=?,state=?,delState=? "
					+ " where userId=?",userLoginName,userName,userPwd,createTime,state,delState,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public User getUserById(int userId) {
		try {
			return qr.query("select * from t_user where userId=?", new BeanHandler<>(User.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteUserById(int userId) {
		//假删除更改删除状态
		System.out.println(userId);
		try {
			return qr.update("update t_user set delState=1 where userId=?", userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User userLogin(String userLoginName, String userPwd) {
		try {
			User u = qr.query("select * from t_user where userLoginName=? and userPwd=? and state=1 and delState=0", new BeanHandler<>(User.class), userLoginName,userPwd);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
