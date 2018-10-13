package com.hr.servce;

import java.util.List;
import com.hr.bean.User;
import com.hr.dao.UserDao;
import com.hr.dao.UserDaoImpl;

public class UserServceImpl implements UserServce{
	UserDao ud=  new UserDaoImpl();
	@Override
	public int getTotalCount(User u) {
		return ud.getTotalCount(u);
	}

	@Override
	public List<User> getUserPage(User u, int pageIndex, int pageSize) {
		return  ud.getUserPage(u, pageIndex, pageSize);
	}

	@Override
	public int addUser(User u) {
		return ud.addUser(u);
	}

	@Override
	public int updateUser(User u) {
		return ud.updateUser(u);
	}

	@Override
	public User getUserById(int userId) {
		return ud.getUserById(userId);
	}

	@Override
	public int deleteUserById(int userId) {
		return ud.deleteUserById(userId);
	}

	@Override
	public User userLogin(String userLoginName, String userPwd) {
		return ud.userLogin(userLoginName,userPwd);
	}
}
