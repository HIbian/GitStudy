package com.chen.servce;

import java.util.List;
import com.chen.bean.Books;

public interface bookservce {
	public int updatebook(Books b);
	public Books getbookById(int bid);
	public List<Books> getbooks();
	public int deletebook(int id);
	public int addbook(Books b);
}
