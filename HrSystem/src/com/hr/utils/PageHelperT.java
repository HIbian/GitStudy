package com.hr.utils;

import java.util.List;

public class PageHelperT<T> {
	private T parms;//回显的参数
	private List<T> listData;//数据列表
	//分页信息
	private int pageIndex;
	private int pageSize;
	//数据总数
	private int totalCount;
	
	//计算出的总页数
	private int totalPage;
	
	public PageHelperT() {
		super();
	}
	public PageHelperT(T parms, List<T> listData, int pageIndex, int pageSize, int totalCount) {
		super();
		this.parms = parms;
		this.listData = listData;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
	}
	public T getParms() {
		return parms;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setParms(T parms) {
		this.parms = parms;
	}
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
