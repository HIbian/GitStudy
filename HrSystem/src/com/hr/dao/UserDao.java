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
	//id ->User
	public User getUserById(int userId);
	//id->删除用户
	public int deleteUserById(int userId);
	//用户登陆
	public User userLogin(String userLoginName, String userPwd);
}
