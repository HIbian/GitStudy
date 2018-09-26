package com.hr.servce;

import java.util.List;
import com.hr.bean.User;
import com.hr.dao.UserDaoImpl;

public class UserServceImpl implements UserServce{
	UserDaoImpl ud=  new UserDaoImpl();
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
}
