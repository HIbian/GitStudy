package com.hr.dao;

import java.util.List;

import com.hr.bean.Download;

public interface DownloadDao {
	int uploadFile(Download dl);
	int getTotalCount();
	List<Download> getDownloadFiles();
	Download getFileById(int downloadId);
}
