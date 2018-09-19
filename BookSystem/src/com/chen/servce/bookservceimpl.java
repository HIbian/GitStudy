package com.chen.servce;

import java.util.List;
import com.chen.bean.Books;
import com.chen.dao.bookdao;
import com.chen.dao.bookdaoimpl;

public class bookservceimpl implements bookservce{
	bookdao bd = new bookdaoimpl();
	@Override
	public List<Books> getbooks() {
		return bd.getbooks();
	}
	@Override
	public int deletebook(int id) {
		return bd.deletebook(id);
	}
	@Override
	public int addbook(Books b) {
		return bd.addbook(b);
	}
	@Override
	public Books getbookById(int bid) {
		return bd.getbookById(bid);
	}
	@Override
	public int updatebook(Books b) {
		return bd.updatebook(b);
	}
}
