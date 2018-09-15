package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.entity.User;
import com.util.BDconn;

public class UserDaoImpl implements UserDao{
	
	@Override@Test
	public boolean login(String name, String pwd) {
		BDconn.init();
		ResultSet rs = BDconn.Query("select * from user where name=? and pwd=?", name,pwd);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				BDconn.Close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	@Override
	public boolean register(com.entity.User user) {
		BDconn.init();
		int count =BDconn.addUpDelete("insert into user values (null,?,?,?,?,?)",user.getName(),user.getPwd(),user.getSex(),user.getHome(),user.getInfo());
		if (count==1) {
			return true;
		}
		return false;
	}
	@Override
	public List<com.entity.User> getUserAll() {
		BDconn.init();
		ResultSet rs = BDconn.Query("select * from user");
		List<User> users = new ArrayList<>();
		try {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setSex(rs.getString(4));
				u.setHome(rs.getString(5));
				u.setInfo(rs.getString(6));
				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				BDconn.Close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		BDconn.init();
		int count = BDconn.addUpDelete("delete from user where id =?", id);
		if (count==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(com.entity.User user) {
		BDconn.init();
		int count =BDconn.addUpDelete("update user set name=?,pwd=?,sex=?,home=?,info=? where id=?", user.getName(),user.getPwd(),user.getSex(),user.getHome(),user.getInfo(),user.getId());
		if (count==1) {
			return true;
		}
		return false;
	}

}
