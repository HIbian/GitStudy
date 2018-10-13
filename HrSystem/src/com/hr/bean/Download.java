package com.hr.bean;

public class Download {
	private int downloadId;
	private String downloadTittle;
	private String downloadCreateTime;
	private int userId;
	private String downloadDes;
	private String downPath;
	
	public Download(String downloadTittle, String downloadCreateTime, int userId, String downloadDes, String downPath) {
		super();
		this.downloadTittle = downloadTittle;
		this.downloadCreateTime = downloadCreateTime;
		this.userId = userId;
		this.downloadDes = downloadDes;
		this.downPath = downPath;
	}
	public Download() {}
	public Download(int downloadId, String downloadTittle, String downloadCreateTime, int userId, String downloadDes,
			String downPath) {
		super();
		this.downloadId = downloadId;
		this.downloadTittle = downloadTittle;
		this.downloadCreateTime = downloadCreateTime;
		this.userId = userId;
		this.downloadDes = downloadDes;
		this.downPath = downPath;
	}
	
	public int getDownloadId() {
		return downloadId;
	}
	public void setDownloadId(int downloadId) {
		this.downloadId = downloadId;
	}
	public String getDownloadTittle() {
		return downloadTittle;
	}
	public void setDownloadTittle(String downloadTittle) {
		this.downloadTittle = downloadTittle;
	}
	public String getDownloadCreateTime() {
		return downloadCreateTime;
	}
	public void setDownloadCreateTime(String downloadCreateTime) {
		this.downloadCreateTime = downloadCreateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDownloadDes() {
		return downloadDes;
	}
	public void setDownloadDes(String downloadDes) {
		this.downloadDes = downloadDes;
	}
	public String getDownPath() {
		return downPath;
	}
	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	@Override
	public String toString() {
		return "Download [downloadId=" + downloadId + ", downloadTittle=" + downloadTittle + ", downloadCreateTime="
				+ downloadCreateTime + ", userId=" + userId + ", downloadDes=" + downloadDes + ", downPath=" + downPath
				+ "]";
	}
}
