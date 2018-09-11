package com.chen.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chen.utils.BaseDao;
import com.chen.vo.Movie;

public class movieDao {
	BaseDao bd = new BaseDao();
	public ResultSet getMovieSet() {
		return bd.Query("select * from t_movie");
	}
	public boolean isName(String movieName) {
		ResultSet rs = bd.Query("select * from t_movie where name =?", movieName);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				bd.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean isTime(String movieTime) {
		ResultSet rs = bd.Query("select * from t_movie where starttime =?", movieTime);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				bd.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public Movie getMovie(String movieName,String movieTime) {
		int id = 0;
		String name = null;
		String english = null;
		String actor = null;
		String type = null;
		double price = 0;
		String starttime =null;
		
		ResultSet rs = bd.Query("select * from t_movie where name=? and starttime =?", movieName,movieTime);
		try {
			if (rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				english = rs.getString(3);
				actor = rs.getString(4);
				type = rs.getString(5);
				price = rs.getDouble(6);
				starttime = rs.getString(7);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				bd.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Movie(id,name, english, actor, type, price, starttime);
	}
	//添加座位
	public void addSeat(int movieid,String seat) {
		bd.Update("insert into t_seat values(null,?,?)", movieid,seat);
	}
	//判断座位是否存在了
	public boolean isexist(int movieid,String seat) {
		ResultSet rs =  bd.Query("select * from t_seat where movieid=? and seatnum = ?", movieid,seat);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				bd.closeConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//添加电影
	public boolean addMove(String name,String ename,String acor,String type,double price,String date) {
		if (bd.Update("insert into t_movie values(null,?,?,?,?,?,?)", name, ename, acor, type, price, date)==1) {
			return true;
		}
		return false;
	}
	//删除电影
	public boolean deleteMove(int id) {
		if (bd.Update("delete from t_movie where id=?",id)==1) {
			return true;
		}
		return false;
	}
	//修改电影
	public boolean updateMove(int id,String name,String ename,String acor,String type,double price,String date) {
		if (bd.Update("update t_movie set name=?,english=?,actor=?,type=?,price=?,starttime=?  where id =?",name, ename, acor, type, price, date,id)==1) {
			return true;
		}
		return false;
	}
}
