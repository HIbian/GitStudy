package com.hr.servce;

import java.util.List;
import com.hr.bean.User;

public interface UserServce {
	//查询总数(条件)
	public int getTotalCount(User u);
	//查询list集合，页码，每页大小
	public List<User> getUserPage(User u,int pageIndex,int pageSize);
	//添加
	public int addUser(User u);
	//修改用户信息
	public int updateUser(User u);
	//通过Id获取User，用于数据回显
	public User getUserById(int userId);
	//删除用户
	public int deleteUserById(int userId);
	//用户登陆
	public User userLogin(String userLoginName, String userPwd);
}
