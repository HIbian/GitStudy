package com.chen.dao;

import java.util.List;

import com.chen.bean.Books;

public interface bookdao {
	//跟新图书
	public int updatebook(Books b);
	//id->书
	public Books getbookById(int bid);
	//获取所有图书
	public List<Books> getbooks();
	//删除指定图书
	public int deletebook(int id);
	//添加书籍
	public int addbook(Books b);
}
