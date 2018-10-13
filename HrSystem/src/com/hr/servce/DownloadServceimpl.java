package com.hr.servce;

import java.util.List;

import com.hr.bean.Download;
import com.hr.dao.DownloadDao;
import com.hr.dao.DownloadDaoimpl;

public class DownloadServceimpl implements DownloadServce{
	DownloadDao dd = new DownloadDaoimpl();
	@Override
	public int uploadFile(Download dl) {
		return dd.uploadFile(dl);
	}
	@Override
	public int getTotalCount() {
		return dd.getTotalCount();
	}
	@Override
	public List<Download> getDownloadFiles() {
		return dd.getDownloadFiles();
	}
	@Override
	public Download getFileById(int downloadId) {
		return dd.getFileById(downloadId);
	}
}
