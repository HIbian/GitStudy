package com.hr.utils;

import java.util.List;

import com.hr.bean.User;

public class pageHelper {
	private User parms;
	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private List<User> userPage;
	public User getParms() {
		return parms;
	}
	public void setParms(User parms) {
		this.parms = parms;
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
	public List<User> getUserPage() {
		return userPage;
	}
	public void setUserPage(List<User> userPage) {
		this.userPage = userPage;
	}
	public pageHelper(User parms, int pageIndex, int pageSize, int totalCount, List<User> userPage) {
		super();
		this.parms = parms;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.userPage = userPage;
	}
}
