package com.chen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.bean.Books;
import com.chen.utils.DaoUtil;

public class bookdaoimpl implements bookdao{
	//获取所有图书
	@Override
	public List<Books> getbooks() {
		List<Books> bks = new ArrayList<Books>(); 
		ResultSet rs = DaoUtil.Query("select * from book");
		try {
			while (rs.next()) {
				int bid = rs.getInt(1);
				String bname = rs.getString(2);
				String bauthor=rs.getString(3);
				double bprice=rs.getDouble(4);
				String bdate = rs.getString(5);
				String bimage = rs.getString(6);
				int bisonline = rs.getInt(7);
				bks.add(new Books(bid, bname, bauthor, bprice, bdate, bimage, bisonline));
			}
			return bks;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DaoUtil.CloseConn();
		}
		return null;
	}
	//删除图书
	@Override
	public int deletebook(int id) {
		return DaoUtil.Updata("delete from book where bid=?", id);
	}
	
	//添加书籍
	@Override
	public int addbook(Books b) {
		return DaoUtil.Updata("insert into book values(null,?,?,?,?,?,?)", b.getBname(),b.getBauthor(),b.getBprice(),b.getBdate(),b.getBimage(),b.getBisonline());
	}
	@Override
	public Books getbookById(int bid) {
		ResultSet rs = DaoUtil.Query("select * from book where bid=?", bid);
		try {
			if (rs.next()) {
				return new Books(bid, rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DaoUtil.CloseConn();
		}
		return null;
	}
	@Override
	public int updatebook(Books b) {
		return DaoUtil.Updata("update book set bname=?,bauthor=?,bprice=?,bdate=?,bimage=?,bisonline=? where bid=?", b.getBname(),b.getBauthor(),b.getBprice(),b.getBdate(),b.getBimage(),b.getBisonline(),b.getBid());
	}
}
