package com.hr.dao;

import java.util.List;

import com.hr.bean.User;

public interface UserDao {
	//查询总数(条件)
	public int getTotalCount(User u);
	//查询list集合，页码，每页大小
	public List<User> getUserPage(User u,int pageIndex,int pageSize);
	//添加User
	public int addUser(User u);
	//修改用户
	public int updateUser(User u);
}
